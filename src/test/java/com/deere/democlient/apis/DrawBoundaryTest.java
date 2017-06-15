package com.deere.democlient.apis;

import com.deere.api.axiom.generated.v3.*;
import com.deere.democlient.brokers.GenericListBroker;
import com.deere.democlient.brokers.OrganizationListBroker;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestRequestBuilder;
import com.deere.rest.RestResponse;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import org.geojson.GeometryCollection;
import org.geojson.LngLatAlt;
import org.geojson.Polygon;
import org.geotools.data.FeatureReader;
import org.geotools.data.FileDataStore;
import org.junit.Before;
import org.junit.Test;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class DrawBoundaryTest extends Resource {

    private DrawBoundary underTest;
    private List<Organization> organizationList;
    private List<Field> fieldList;
    private GeometryCollection geometryCollection;
    private DeereGeoJsonBoundary deereGeoJsonBoundary;
    private static int HUGE_NUMBER = 500;

    @Before
    public void setUp() throws Exception {
        underTest = new DrawBoundary();
        deereGeoJsonBoundary = initBoundary();
        geometryCollection = initGeometryCollection();
        organizationList = initOrgList();
        fieldList = initFieldList();
        underTest.setBoundary(deereGeoJsonBoundary);
        underTest.setOrganizationListForUser(organizationList);
        underTest.setFieldListForUser(fieldList);
    }

    @Test
    public void shouldSelectOrgBasedOnAcceptedInput() throws Exception {
        assertEquals(organizationList.get(1), underTest.getOrganizationSelected(1));
    }

    @Test
    public void shouldSelectFieldBasedOnAcceptedInput() throws Exception {
        assertEquals(fieldList.get(1), underTest.getFieldSelected(1));
    }

    @Test
    public void callsToOrganizationBrokerToGetOrganizationList() throws Exception {
        OrganizationListBroker organizationListBroker = mock(OrganizationListBroker.class);
        underTest.setOrganizationBroker(organizationListBroker);
        underTest.getOrganizationListForUser();
        verify(organizationListBroker).getOwnedOrganizations();
    }

    @Test
    public void callsToGetFieldListCallBrokerCorrectly() throws Exception {
        GenericListBroker fieldBroker = mock(GenericListBroker.class);
        underTest.setFieldBroker(fieldBroker);
        underTest.getFieldListWithClientsAndFarmsEmbed("url to field list for org");
        verify(fieldBroker).getList(anyString(), eq(Field.class));
    }

    @Test
    public void shouldMakeSureToGetFieldListWithEmbeds() throws Exception {
        GenericListBroker fieldBroker = mock(GenericListBroker.class);
        underTest.setFieldBroker(fieldBroker);
        String fieldListUri = "uriWithoutEmbeds";
        underTest.getFieldListWithClientsAndFarmsEmbed(fieldListUri);
        verify(fieldBroker).getList(eq(fieldListUri + "?embed=farms,clients"), eq(Field.class));
    }

    @Test
    public void shouldThrowRuntimeExceptionIfUserHasNoAccessToFieldsForOrganizations() throws Exception {
        try {
            underTest.getOrganizationSelected(0);
            fail("RuntimeException should have happened");
        } catch (RuntimeException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void shouldThrowRuntimeExceptionIfUserHasNoAccessToBoundaryForField() throws Exception {
        try {
            underTest.getFieldSelected(0);
            fail("RuntimeException should have happened");
        } catch (RuntimeException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void shouldThrowRuntimeExceptionIfUserEntersInvalidInputToOrganizationSelection() throws Exception {
        try {
            underTest.getOrganizationSelected(10000);
            fail("RuntimeException should have happened");
        } catch (RuntimeException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void shouldThrowRuntimeExceptionIfUserEntersInvalidInputToFieldSelection() throws Exception {
        try {
            underTest.getFieldSelected(100);
            fail("RuntimeException should have happened");
        } catch (RuntimeException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void shouldNotAcceptInputUntilItIsAnInteger() throws Exception {
        underTest.setScanner(new Scanner("treehouse\n$2\n1"));
        try {
            underTest.getUserSelection(HUGE_NUMBER);
            assertTrue("user selection happened without error", true);
        } catch (Exception ex) {
            fail("No exception should have happened on parsing this string");
        }
    }

    @Test
    public void shouldReturnCorrectIndexFromUserChoice() throws Exception {
        underTest.setScanner(new Scanner("1"));
        assertEquals(0, underTest.getUserSelection(HUGE_NUMBER));
    }

    @Test
    public void userChoiceReturnedShouldBeLessThanMaxSize() throws Exception {
        underTest.setScanner(new Scanner("5\n2"));
        int choice = underTest.getUserSelection(organizationList.size());
        assertEquals(1, choice);
    }

    @Test
    public void shouldForceUserChoiceToBeGreaterThanZero() throws Exception {
        underTest.setScanner(new Scanner("0\n2"));
        int choice = underTest.getUserSelection(organizationList.size());
        assertEquals(1, choice);
    }

    @Test
    public void shouldStoreIfIrrigated() throws Exception {
        underTest.getDeereGeoJsonBoundary().setIrrigated(false);
        assertEquals(false, underTest.getDeereGeoJsonBoundary().isIrrigated());
    }

    @Test
    public void shouldStoreIfActive() throws Exception {
        underTest.getDeereGeoJsonBoundary().setActive(false);
        assertEquals(false, underTest.getDeereGeoJsonBoundary().isActive());
    }

    @Test
    public void shouldStoreFileType() throws Exception {
        underTest.setFileType("shapefile");
        assertEquals("shapefile", underTest.getFileType());
    }

    @Test
    public void shouldCreateADeereGeoJsonBoundary() throws Exception {
        assertEquals(deereGeoJsonBoundary, underTest.getDeereGeoJsonBoundary());
    }

    @Test
    public void shouldReturnAFieldURLForTheUserOrganization() throws Exception {
        assertEquals(null, underTest.getFieldUrlForUserOrganization(organizationList.get(1)));
    }

    @Test
    public void shouldGetBoundaryURLForTheUserField() throws Exception {
        assertEquals(null, underTest.getBoundaryUrlForUserField(fieldList.get(1)));
    }

    @Test
    public void shouldDisplayOrgsToUser() throws Exception {
        assertNotEquals(null, underTest.orgDisplay());
    }

    @Test
    public void shouldDisplayOneOrgWithNumberInFrontFollowedByOrgName() throws Exception {
        List<Organization> organizationListForUser = new ArrayList<>();
        Organization organization = new Organization();
        String organizationName = "Josh Amazing Org";
        organization.setName(organizationName);
        organizationListForUser.add(organization);

        String expectedOrgDisplay = "(1) Josh Amazing Org\n";

        underTest.setOrganizationListForUser(organizationListForUser);

        assertEquals(expectedOrgDisplay, underTest.orgDisplay());
    }

    @Test
    public void shouldDisplayFirstOrgWithOneBeforeSecondOrgWithTwoInFront() throws Exception {
        List<Organization> organizationListForUser = new ArrayList<>();
        Organization organization = new Organization();
        String organizationName = "Josh Amazing Org";
        organization.setName(organizationName);
        organizationListForUser.add(organization);
        Organization secondOrganization = new Organization();
        String secondOrgName = "Mike Farms";
        secondOrganization.setName(secondOrgName);
        organizationListForUser.add(1, secondOrganization);

        underTest.setOrganizationListForUser(organizationListForUser);

        String resultingOrgDisplay = underTest.orgDisplay();

        assertTrue(resultingOrgDisplay.indexOf("Josh Amazing Org") < resultingOrgDisplay.indexOf("Mike Farms"));
    }

    @Test
    public void shouldAccessBoundaryAttributes() throws Exception {
        underTest.getDeereGeoJsonBoundary().setIrrigated(false);
        assertEquals(false, underTest.getDeereGeoJsonBoundary().isIrrigated());
    }

    @Test
    public void shouldSurroundGivenStringWithBrackets() throws Exception {
        assertEquals("[hello]", underTest.surroundWithBrackets("hello"));
    }

    @Test
    public void shouldDisplayFieldsToUser() throws Exception {
        List<Field> fieldListForDisplayPrinting = getFieldListWhichHasFarmAndClient();
        underTest.setFieldListForUser(fieldListForDisplayPrinting);

        Field fieldForExpected = fieldListForDisplayPrinting.get(0);
        String expectedValue = "(1) " + fieldForExpected.getClient().getName() + " " + fieldForExpected.getFarm().getName() + " " + fieldForExpected.getName() + "\n";
        String actualValue = underTest.fieldDisplay();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void shouldDisplayFieldsInOrder() throws Exception {
        List<Field> fieldListWhichHasFarmAndClient = getFieldListWhichHasFarmAndClient();
        fieldListWhichHasFarmAndClient.add(getFieldListWhichHasFarmAndClient().get(0));
        underTest.setFieldListForUser(fieldListWhichHasFarmAndClient);

        String actualValue = underTest.fieldDisplay();

        assertTrue("1 didn't appear before 2", actualValue.indexOf("(1)") < actualValue.indexOf("(2)"));
    }

    @Test
    public void shouldDisplayShapefileOrJsonFileChoiceToUser() throws Exception {
        String UI = "Create From: \n(1) shapefile\n" +
                "(2) jsonfile";
        assertEquals(UI, underTest.fileTypeDisplay());
    }

    @Test
    public void shouldHandleSettingBoundaryFileVariableAccordingToUserChoice() throws Exception {
        underTest.setScanner(new Scanner("1"));
        underTest.readInFileType();
        assertEquals("shapefile", underTest.getFileType());
    }

    @Test
    public void shouldDisplayIrrigatedorNotChoiceToUser() throws Exception {
        String UI = "(1) irrigated\n" +
                "(2) not irrigated";
        assertEquals(UI, underTest.irrigatedOrNotDisplay());
    }

    @Test
    public void shouldHandleSettingBoundaryIrrigatedVariableAccordingToUserChoice() throws Exception {
        underTest.setScanner(new Scanner("1"));
        underTest.readInIrrigatedOrNot();
        assertEquals(true, underTest.getDeereGeoJsonBoundary().isIrrigated());
    }

    @Test
    public void shouldDisplayActiveOrNotChoiceToUser() throws Exception {
        String UI = "(1) active\n" +
                "(2) not active";
        assertEquals(UI, underTest.activeOrNotDisplay());
    }

    @Test
    public void shouldHandleSettingActiveVariableAccordingToUserChoice() throws Exception {
        underTest.setScanner(new Scanner("1"));
        underTest.readInActiveOrNot();
        assertEquals(true, underTest.getDeereGeoJsonBoundary().isActive());
    }

    @Test
    public void shouldDisplayAPromptForTheUserToNameTheBoundary() throws Exception {
        String UI = "Enter a name for this boundary: ";
        assertEquals(UI, underTest.nameDisplay());
    }

    @Test
    public void shouldHandleSettingNameVariableAccordingToUserInputEvenIfThatInputHasSpaces() throws Exception {
        String stringWithSpaces = "Amazing Field Of Just Grass";
        underTest.setScanner(new Scanner(stringWithSpaces));
        underTest.readNameInputFromUser();
        assertEquals(stringWithSpaces, underTest.getDeereGeoJsonBoundary().getName());
    }

    @Test
    public void shouldBeAbleToFindTheFile() throws Exception {
        String filePath = "src/main/resources/01_export/Merriweather Farms-JT-01.shp";
        File shapeFile = new File(filePath);
        assertEquals(shapeFile.getPath(), underTest.findMyShapeFile(filePath).getPath());
        filePath = "src/main/resources/DrawBoundary.json";
        File jsonFile = new File(filePath);
        assertEquals(jsonFile.getPath(), underTest.findMyShapeFile(filePath).getPath());
    }

    @Test
    public void shouldCreateJsonFromJsonFile() throws Exception {
        assertTrue(underTest.GeoJSONFromFile() != null);
    }

    @Test
    public void shouldConvertJsonToDeereJson() throws Exception {
        assertTrue(underTest.GeoJSONtoDeereJSON() != null);
    }

    @Test
    public void shouldPostBoundaryToOperationsCenterAndReturnCreatedMessage() throws Exception {
        AbstractApiBaseWrapper abstractApiBaseWrapper = mock(AbstractApiBaseWrapper.class);
        String uriToBoundaries = "uriToBoundaries";
        underTest.setApiBaseWrapper(abstractApiBaseWrapper);
        RestRequestBuilder restRequestBuilder = mock(RestRequestBuilder.class);
        RestRequest restRequest = mock(RestRequest.class);
        RestResponse restResponse = mock(RestResponse.class);

        when(abstractApiBaseWrapper.oauthRequestToWrapper(uriToBoundaries)).thenReturn(restRequestBuilder);
        when(restRequestBuilder.addHeader(any(HttpHeader.class))).thenReturn(restRequestBuilder);
        when(restRequestBuilder.body(any())).thenReturn(restRequestBuilder);
        when(restRequestBuilder.method("POST")).thenReturn(restRequestBuilder);
        when(restRequestBuilder.build()).thenReturn(restRequest);
        when(restRequest.fetchResponse()).thenReturn(restResponse);
        Field field = getFieldWithLinkTo("boundaries", uriToBoundaries);

        underTest.extractTheCorrectBytesForJsonFile();

        underTest.postBoundary(field);

        verify(restResponse).getResponseMessage();
    }

    @Test
    public void shouldReadFeatureReaderFromShp() throws Exception {
        File shapeFile = mock(File.class);
        FileDataStoreFinderWrapper fileDataStoreFinderWrapper = mock(FileDataStoreFinderWrapper.class);
        underTest.setFileDataStoreFinder(fileDataStoreFinderWrapper);
        FileDataStore fileDataStore = mock(FileDataStore.class);
        FeatureReader featureReaderExpected = mock(FeatureReader.class);

        when(fileDataStoreFinderWrapper.getDataStore(shapeFile)).thenReturn(fileDataStore);
        when(fileDataStore.getFeatureReader()).thenReturn(featureReaderExpected);
        FeatureReader<SimpleFeatureType, SimpleFeature> featureReaderActual = underTest.extractFeatureReaderFromShapefile(shapeFile);

        assertSame(featureReaderExpected, featureReaderActual);
    }

    @Test
    public void shouldGetCoordinatesFromSimpleFeature() throws Exception {
        Geometry geometry = mock(Geometry.class);
        Coordinate[] coordinates = new Coordinate[1];
        SimpleFeature simpleFeature = mock(SimpleFeature.class);

        when(simpleFeature.getDefaultGeometry()).thenReturn(geometry);
        when(geometry.getCoordinates()).thenReturn(coordinates);

        Coordinate[] coordinateListActual = underTest.extractCoordinatesFromSimpleFeature(simpleFeature);

        assertSame(coordinates, coordinateListActual);
    }

    @Test
    public void shouldCreatePointsListFromCoordinates() throws Exception {
        List<LngLatAlt> pointsExpected = new ArrayList<>();
        Coordinate coordinate = new Coordinate(100, 100);
        Coordinate[] coordinates = new Coordinate[1];
        coordinates[0] = coordinate;
        List<LngLatAlt> pointsActual = underTest.extractPoints(coordinates);
        pointsExpected.add(new LngLatAlt(coordinates[0].x, coordinates[0].y));
        assertEquals(pointsExpected, pointsActual);
    }

    @Test
    public void shouldAddPointsToThePointsListInTheCorrectOrder() throws Exception {
        List<LngLatAlt> pointsExpected = new ArrayList<>();
        Coordinate[] coordinates = new Coordinate[2];
        Coordinate coordinate = new Coordinate(100, 100);
        coordinates[0]=(coordinate);
        Coordinate coordinate1 = new Coordinate(75, 75);
        coordinates[1]=(coordinate1);
        List<LngLatAlt> pointsActual = underTest.extractPoints(coordinates);
        pointsExpected.add(new LngLatAlt(coordinates[0].x, coordinates[0].y));
        pointsExpected.add(new LngLatAlt(coordinates[1].x, coordinates[1].y));
        assertEquals(pointsExpected.get(1), pointsActual.get(1));
    }

    @Test
    public void shouldCreateAStringFromPointsInCorrectFormat() throws Exception {
        List<LngLatAlt> points = new ArrayList<>();
        points.add(new LngLatAlt(100, 100));
        String expected = "[[[100.0,100.0]]]";
        String pointsString = underTest.pointArrayAsString(points);
        assertEquals(expected, pointsString);
    }

    @Test
    public void shouldCreateAStringThatHandlesHavingMultiplePointsInCorrectFormat() throws Exception {
        List<LngLatAlt> points = new ArrayList<>();
        points.add(new LngLatAlt(100, 100));
        points.add(new LngLatAlt(75, 75));
        String expected = "[[[100.0,100.0],[75.0,75.0]]]";
        String pointsString = underTest.pointArrayAsString(points);
        assertEquals(expected, pointsString);

    }

    @Test
    public void shouldInitializeAPolygonObjectBeforeTryingToPutPointsIntoIt() throws Exception {
        Polygon polygon = new Polygon();
        underTest.Polygon();
        assertEquals(polygon, underTest.getPolygon());
        assertNotNull(underTest.getPolygon());
    }

    @Test
    public void shouldAddOurNewAmazingPolygonToTheGeometryCollection() throws Exception {
        List<LngLatAlt> points = new ArrayList<>();
        points.add(new LngLatAlt(100, 100));
        points.add(new LngLatAlt(0, 0));
        points.add(new LngLatAlt(50, 50));
        points.add(new LngLatAlt(100, 100));
        underTest.addPolygonToGeometryCollection(points);
        assertNotNull(underTest.getDeereGeoJsonBoundary().getGeometryCollection());
    }

    @Test
    public void shouldCreateADeereGeoJsonStringForCreatingBoundaryUsingShapefile() throws Exception {
        List<LngLatAlt> points = new ArrayList<>();
        points.add(new LngLatAlt(100, 100));
        points.add(new LngLatAlt(0, 0));
        points.add(new LngLatAlt(50, 50));
        points.add(new LngLatAlt(100, 100));
        underTest.addPolygonToGeometryCollection(points);
        assertNotNull(underTest.toSend());
    }

    @Test
    public void shouldGrabAndConvertTheJsonToBytesForShapeFile() throws Exception {
        underTest.setFileDataStoreFinder(new FileDataStoreFinderWrapper());
        underTest.setUpPolygonsFromShapefile();
        underTest.extractTheCorrectBytesForShapeFile();
        assertNotNull(underTest.getBytesObject());
    }

    @Test
    public void shouldSetUpThePolygonsAfterExtractingPointsFromShapeFile() throws Exception {
        underTest.setFileDataStoreFinder(new FileDataStoreFinderWrapper());
        underTest.setUpPolygonsFromShapefile();
        underTest.setBoundary(deereGeoJsonBoundary);
        underTest.Polygon();
        assertNotNull(underTest.getDeereGeoJsonBoundary().getGeometryCollection().getGeometries());
    }

    private Field getFieldWithLinkTo(String relOfLink, String uriOfLink) {
        Field field = new Field();
        ArrayList<Link> links = new ArrayList<>();
        Link link = new Link();
        link.setRel(relOfLink);
        link.setUri(uriOfLink);
        links.add(link);
        field.setLinks(links);
        return field;
    }

    private List<Field> getFieldListWhichHasFarmAndClient() {
        List<Field> fieldListForDisplayPrinting = new ArrayList<>();
        Field field = new Field();
        field.setName("Field1");
        Client client = new Client();
        client.setName("JimBob");
        field.setClient(client);
        Farm farm = new Farm();
        farm.setName("Grass");
        field.setFarm(farm);
        fieldListForDisplayPrinting.add(field);
        return fieldListForDisplayPrinting;
    }

    private DeereGeoJsonBoundary initBoundary() {
        deereGeoJsonBoundary = new DeereGeoJsonBoundary();
        return deereGeoJsonBoundary;
    }

    private GeometryCollection initGeometryCollection() {
        geometryCollection = new GeometryCollection();
        return geometryCollection;
    }

    private List<Organization> initOrgList() {
        ArrayList<Organization> toReturn = new ArrayList<>();
        Organization firstOrganization = new Organization();
        List<Link> emptyLinks = new ArrayList<>();
        firstOrganization.setLinks(emptyLinks);
        toReturn.add(firstOrganization);

        Organization secondOrganization = new Organization();
        secondOrganization.setLinks(getListOfLinksWhereOneLinkHasARelTo("fields"));
        toReturn.add(1, secondOrganization);

        return toReturn;
    }

    private List<Field> initFieldList() {
        ArrayList<Field> toReturn = new ArrayList<>();
        Field firstField = new Field();
        toReturn.add(firstField);
        Field secondField = new Field();

        secondField.setLinks(getListOfLinksWhereOneLinkHasARelTo("boundaries"));
        toReturn.add(1, secondField);
        return toReturn;
    }

    private List<Link> getListOfLinksWhereOneLinkHasARelTo(String relName) {
        List<Link> toReturn = new ArrayList<>();
        Link linkInList = new Link();
        linkInList.setRel(relName);
        toReturn.add(linkInList);
        return toReturn;
    }
}