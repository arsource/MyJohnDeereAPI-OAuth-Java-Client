package com.deere.democlient.apis;

import com.deere.api.axiom.generated.v3.DeereGeoJsonBoundary;
import com.deere.api.axiom.generated.v3.Field;
import com.deere.api.axiom.generated.v3.Organization;
import com.deere.democlient.brokers.GenericListBroker;
import com.deere.democlient.brokers.OrganizationListBroker;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import com.google.common.io.ByteStreams;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import org.geojson.GeometryCollection;
import org.geojson.LngLatAlt;
import org.geojson.Polygon;
import org.geotools.data.FeatureReader;
import org.geotools.data.FileDataStore;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.deere.democlient.apis.AbstractApiBase.V3_ACCEPTABLE_TYPE;

public class DrawBoundary {
    private OrganizationListBroker organizationBroker;
    private GenericListBroker fieldBroker;
    private List<Organization> organizationListForUser;
    private List<Field> fieldListForUser;
    private String fileType;
    private byte[] bytes;
    private Polygon polygon = new Polygon();
    private DeereGeoJsonBoundary deereGeoJsonBoundary;
    private AbstractApiBaseWrapper apiBaseWrapper;
    private FileDataStoreFinderWrapper fileDataStoreFinder;

    public void setFileDataStoreFinder(FileDataStoreFinderWrapper fileDataStoreFinder) {
        this.fileDataStoreFinder = fileDataStoreFinder;
    }


    public void setApiBaseWrapper(AbstractApiBaseWrapper apiBaseWrapper) {
        this.apiBaseWrapper = apiBaseWrapper;
    }

    private final String filePath = "src/main/resources/01_export/Merriweather Farms-JT-01.shp";
    private Scanner scanner;

    public static void main(String args[]) throws IOException {
        DrawBoundary drawBoundary = DrawBoundaryFactory.getDrawBoundary();
        List<Organization> organizationListForUser = drawBoundary.getOrganizationListForUser();
        drawBoundary.setOrganizationListForUser(organizationListForUser);
        System.out.println(drawBoundary.orgDisplay());
        int orgIdSelected = drawBoundary.getUserSelection(organizationListForUser.size());

        Organization organization = drawBoundary.getOrganizationSelected(orgIdSelected);
        String fieldListUri = drawBoundary.getFieldUrlForUserOrganization(organization);
        List<Field> fieldList = drawBoundary.getFieldListWithClientsAndFarmsEmbed(fieldListUri);
        drawBoundary.setFieldListForUser(fieldList);
        System.out.println(drawBoundary.fieldDisplay());
        int fieldIdSelected = drawBoundary.getUserSelection(fieldList.size());
        Field fieldSelected = drawBoundary.getFieldSelected(fieldIdSelected);

        drawBoundary.setBoundary(new DeereGeoJsonBoundary());

        System.out.println(drawBoundary.fileTypeDisplay());
        drawBoundary.readInFileType();
        System.out.println(drawBoundary.irrigatedOrNotDisplay());
        drawBoundary.readInIrrigatedOrNot();
        System.out.println(drawBoundary.activeOrNotDisplay());
        drawBoundary.readInActiveOrNot();
        System.out.println(drawBoundary.nameDisplay());
        drawBoundary.readNameInputFromUser();

        if (drawBoundary.fileType == "jsonfile") {
            drawBoundary.extractTheCorrectBytesForJsonFile();
        } else {
            drawBoundary.setUpPolygonsFromShapefile();
            drawBoundary.extractTheCorrectBytesForShapeFile();
        }
        drawBoundary.postBoundary(fieldSelected);
    }

    public void setOrganizationBroker(OrganizationListBroker organizationListBroker) {
        this.organizationBroker = organizationListBroker;
    }

    public void setOrganizationListForUser(List<Organization> organizationListForUser) {
        this.organizationListForUser = organizationListForUser;
    }

    public List<Organization> getOrganizationListForUser() {
        return organizationBroker.getOwnedOrganizations();
    }

    public String orgDisplay() {
        String orgDisplay = "";
        for (int i = 0; i < organizationListForUser.size(); i++) {
            orgDisplay = orgDisplay + "(" + (i + 1) + ") " + organizationListForUser.get(i).getName() + "\n";
        }
        return orgDisplay;
    }

    public OrganizationListBroker getOrganizationBroker() {
        return organizationBroker;
    }

    public Organization getOrganizationSelected(int i) {
        validateFieldIsAvailableForOrg(organizationListForUser.get(i));
        return organizationListForUser.get(i);
    }

    public void setFieldBroker(GenericListBroker fieldBroker) {
        this.fieldBroker = fieldBroker;
    }

    public GenericListBroker getFieldBroker() {
        return fieldBroker;
    }

    public void setFieldListForUser(List<Field> fieldListForUser) {
        this.fieldListForUser = fieldListForUser;
    }

    public Field getFieldSelected(int i) {
        Field fieldSelected = fieldListForUser.get(i);
        validateBoundaryIsAvailableForField(fieldSelected);
        return fieldSelected;
    }

    public int getUserSelection(int maxSize) {
        return getUserSelectionInRange(scanner, maxSize) - 1;
    }

    private int getUserSelectionInRange(Scanner scanner, int size) {
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
        }
        int choice = scanner.nextInt();
        if (choice > size || choice <= 0) {
            choice = getUserSelectionInRange(scanner, size);
        }
        return choice;
    }

    private void validateBoundaryIsAvailableForField(Field field) {
        field.getLinks().stream().filter(link -> link.getRel().equals("boundaries"))
                .findFirst().orElseThrow(() -> new RuntimeException("No access to the boundary list for this field")).getUri();
    }

    private void validateFieldIsAvailableForOrg(Organization organization) {
        organization.getLinks().stream().filter(link -> link.getRel().equals("fields"))
                .findFirst().orElseThrow(() -> new RuntimeException("No access to the field list for this organization")).getUri();
    }

    public String getFieldUrlForUserOrganization(Organization organization) {
        return organization.getLinks().stream().filter(link -> link.getRel().equals("fields"))
                .findFirst().orElseThrow(() -> new RuntimeException("No access to the field list for this organization")).getUri();
    }

    public String getBoundaryUrlForUserField(Field field) {
        return field.getLinks().stream().filter(link -> link.getRel().equals("boundaries"))
                .findFirst().orElseThrow(() -> new RuntimeException("No access to the boundary list for this field")).getUri();
    }

    public DeereGeoJsonBoundary getDeereGeoJsonBoundary() {
        return deereGeoJsonBoundary;
    }

    public void setBoundary(DeereGeoJsonBoundary deereGeoJsonBoundary) {
        this.deereGeoJsonBoundary = deereGeoJsonBoundary;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String surroundWithBrackets(String toBeSurrounded) {
        return "[" + toBeSurrounded + "]";
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String fieldDisplay() {
        String fieldDisplay = "";
        try {
            for (int i = 0; i < fieldListForUser.size(); i++) {
                Field field = fieldListForUser.get(i);
                fieldDisplay = fieldDisplay + "(" + (i + 1) + ") " + field.getClient().getName()
                        + " " + field.getFarm().getName() + " " + field.getName() + "\n";
            }
        } catch (Field.DataNotEmbeddedException ex) {
            throw new RuntimeException(ex);
        }
        return fieldDisplay;
    }

    public List<Field> getFieldListWithClientsAndFarmsEmbed(String fieldListUri) {
        List<Field> fields = fieldBroker.getList(fieldListUri + "?embed=farms,clients", Field.class);
        return fields;
    }

    public String fileTypeDisplay() {
        String display = "Create From: \n(1) shapefile\n(2) jsonfile";
        return display;
    }

    public void readInFileType() {
        int choice = getUserSelection(2) + 1;
        if (choice == 1) {
            setFileType("shapefile");
        } else {
            setFileType("jsonfile");
        }
    }

    public String irrigatedOrNotDisplay() {
        String display = "(1) irrigated\n" +
                "(2) not irrigated";
        return display;
    }

    public void readInIrrigatedOrNot() {
        int choice = getUserSelection(2) + 1;
        if (choice == 1) {
            deereGeoJsonBoundary.setIrrigated(true);
        } else {
            deereGeoJsonBoundary.setIrrigated(false);
        }
    }

    public String activeOrNotDisplay() {
        String display = "(1) active\n" +
                "(2) not active";
        return display;
    }

    public void readInActiveOrNot() {
        int choice = getUserSelection(2) + 1;
        if (choice == 1) {
            deereGeoJsonBoundary.setActive(true);
        } else {
            deereGeoJsonBoundary.setActive(false);
        }
    }

    public String nameDisplay() {
        String display = "Enter a name for this boundary: ";
        return display;
    }

    public void readNameInputFromUser() {
        String name = scanner.next();
        name += scanner.nextLine();
        deereGeoJsonBoundary.setName(name);
    }

    public java.io.File findMyShapeFile(String filePath) throws FileNotFoundException {
        java.io.File file = new java.io.File(filePath);
        return file;
    }

    public String GeoJSONFromFile() {
        try {
            final String path = new File("").getAbsolutePath() + "\\src\\main\\resources\\DrawBoundary.json";
            return new Scanner(new File(path)).useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(-1);
        }
        return "";
    }

    public String GeoJSONtoDeereJSON() {
        String GeoJSON = GeoJSONFromFile().split("coordinates")[1];
        GeoJSON = GeoJSON.split("}")[0];
        String result = "{ \"name\" : \"" + deereGeoJsonBoundary.getName() + "\",\n\"active\" : " +
                deereGeoJsonBoundary.isActive() + ",\n\"irrigated\" : " + deereGeoJsonBoundary.isIrrigated() + ",\n" +
                "\"geometryCollection\": {\n\"type\" : \"GeometryCollection\",\n\"geometries\" : [{\"type\": " +
                "\"Polygon\",\n\"coordinates" + GeoJSON + "}]}}";
        return result;
    }

    public void extractTheCorrectBytesForJsonFile() {
        bytes = GeoJSONtoDeereJSON().getBytes();
    }

    public void extractTheCorrectBytesForShapeFile() {
        bytes = toSend().getBytes();
    }

    public String postBoundary(Field fieldSelected) {
        final RestRequest boundaryDrawRequest = apiBaseWrapper.oauthRequestToWrapper(getBoundaryUrlForUserField(fieldSelected))
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", "application/vnd.deere.axiom.v3.geo+json"))
                .body(ByteStreams.newInputStreamSupplier(bytes))
                .build();
        final RestResponse boundaryDrawResponse = boundaryDrawRequest.fetchResponse();
        return boundaryDrawResponse.getResponseMessage();
    }

    public FeatureReader<SimpleFeatureType, SimpleFeature> extractFeatureReaderFromShapefile(File shapeFile) {
        try {
            FileDataStore dataStore = fileDataStoreFinder.getDataStore(shapeFile);
            FeatureReader<SimpleFeatureType, SimpleFeature> featureReader = dataStore.getFeatureReader();
            return featureReader;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Coordinate[] extractCoordinatesFromSimpleFeature(SimpleFeature simpleFeature) {
        Geometry geometry = createGeometry(simpleFeature);
        Coordinate[] coordinates = geometry.getCoordinates();
        return coordinates;
    }

    private Geometry createGeometry(SimpleFeature simpleFeature) {
        Geometry geometry = (Geometry) simpleFeature.getDefaultGeometry();
        return geometry;
    }

    public List<LngLatAlt> extractPoints(Coordinate[] coordinates) {
        List<LngLatAlt> points = new ArrayList<>();
        for (Coordinate cords : coordinates) {
            points.add(new LngLatAlt(cords.x, cords.y));
        }
        return points;
    }

    public void addPolygonToGeometryCollection(List<LngLatAlt> points) {
        polygon.setExteriorRing(points);
        GeometryCollection geometryCollection = new GeometryCollection();
        geometryCollection.add(polygon);
        deereGeoJsonBoundary.setGeometryCollection(geometryCollection);
    }

    public void Polygon() {
        polygon = new Polygon();
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public String toSend() {
        List<LngLatAlt> points = polygon.getExteriorRing();
        return "{ \"name\" : \"" + deereGeoJsonBoundary.getName() + "\",\n\"active\" : " + deereGeoJsonBoundary.isActive() + ",\n" + "" +
                "\"irrigated\" : " + deereGeoJsonBoundary.isIrrigated() + ",\n" + "\"geometryCollection\": {\n\"type\""
                + " : \"GeometryCollection\",\n\"geometries\" : [{\"type\":" + " \"Polygon\",\n\"coordinates\"" +
                " : " + pointArrayAsString(points) + "}]}}";
    }

    public String pointArrayAsString(List<LngLatAlt> points) {
        String toReturn = "";
        for (int i = 0; i < points.size(); i++)
            toReturn = toReturn + surroundWithBrackets(points.get(i).getLongitude() + "," + points.get(i).getLatitude()) + ",";
        return surroundWithBrackets(surroundWithBrackets(toReturn.substring(0, toReturn.length() - 1)));
    }

    public byte[] getBytesObject() {
        return bytes;
    }

    public void setUpPolygonsFromShapefile() throws IOException {
        FeatureReader<SimpleFeatureType, SimpleFeature> featureReader = extractFeatureReaderFromShapefile(findMyShapeFile(filePath));
        while (featureReader.hasNext()) {
            SimpleFeature simpleFeature = featureReader.next();
            Coordinate[] coordinates = extractCoordinatesFromSimpleFeature(simpleFeature);
            List<LngLatAlt> points = extractPoints(coordinates);
            Polygon();
            addPolygonToGeometryCollection(points);
        }
    }
}