package com.deere.democlient.apis;

import com.deere.api.axiom.generated.v3.Field;
import com.deere.api.axiom.generated.v3.FieldOperation;
import com.deere.api.axiom.generated.v3.Organization;
import com.deere.democlient.brokers.GenericListBroker;
import com.deere.democlient.brokers.OrganizationListBroker;
import com.deere.democlient.brokers.ShapefileDownloadBroker;
import com.deere.democlient.exceptions.UnavailableRelException;
import com.deere.democlient.exceptions.UnsupportedShapefileExportException;
import com.deere.democlient.util.LinkUtility;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AsyncShapefileDownload {
    private static final String DOWNLOAD_DIRECTORY = "FileDownloads";
    private ShapefileDownloadBroker shapefileDownloadBroker;

    public AsyncShapefileDownload() {
        shapefileDownloadBroker = new ShapefileDownloadBroker();
    }

    public static void main(String [] args) {
        AsyncShapefileDownload asyncShapefileDownload = new AsyncShapefileDownload();

        //Example1: Download a single shapefile
        //NOTE: You must replace the below URI with one that you have access to
        asyncShapefileDownload.downloadShapefile("https://sandboxapi.deere.com/platform/fieldOps/MjkyNDkyXzU4MTgyMzE0ZTQ1MTg2MDRlODExODU2YQ");
        
        //Example2: Download all shapefiles
//        asyncShapefileDownload.downloadAllOwnedAndPartnerShapefiles();
    }

    public void downloadAllOwnedAndPartnerShapefiles() {
        GenericListBroker genericListBroker = new GenericListBroker();

        List<Organization> organizations = new OrganizationListBroker().getOwnedAndPartnerOrganizations();
        for (Organization organization : organizations) {
            Optional<String> fieldsUri = LinkUtility.getUriOptionalForRel(organization, "fields");
            if (fieldsUri.isPresent()) {
                List<Field> fields = genericListBroker.getList(fieldsUri.get(), Field.class);
                for (Field field : fields) {
                    Optional<String> fieldOperationsUri = LinkUtility.getUriOptionalForRel(field, "fieldOperation");
                    if (fieldOperationsUri.isPresent()) {
                        List<FieldOperation> fieldOperations = genericListBroker.getList(fieldOperationsUri.get(), FieldOperation.class);
                        for (FieldOperation fieldOperation : fieldOperations) {
                        /*
                            Probably want to start the download in its own thread in order to load up the John Deere queue.
                            Until webhooks are supported, it would be wise to limit the size of the thread pool so that you don't use a ton of resources during polling.
                         */
                            downloadShapefile(fieldOperation);
                        }
                    }
                }
                }
            }
    }

    private void downloadShapefile(FieldOperation fieldOperation) {
        try {
            shapefileDownloadBroker.download(fieldOperation, DOWNLOAD_DIRECTORY, UUID.randomUUID().toString() + ".zip");
        } catch (IOException e) {
            String fieldOperationsUri = LinkUtility.getUriOptionalForRel(fieldOperation, "self").get();
            System.err.println("Shapefile download failed: " + fieldOperationsUri);
            System.err.println(e.getMessage());
        } catch (UnsupportedShapefileExportException|UnavailableRelException e) {
            String fieldOperationsUri = LinkUtility.getUriOptionalForRel(fieldOperation, "self").get();
            System.out.println("This operation does not support shapefile extraction: " + fieldOperationsUri);
        }
    }

    private void downloadShapefile(String asyncShapefileDownloadUri) {
        try {
            shapefileDownloadBroker.download(asyncShapefileDownloadUri, DOWNLOAD_DIRECTORY, UUID.randomUUID().toString() + ".zip");
        } catch (IOException e) {
            System.err.println("Shapefile download failed: " + asyncShapefileDownloadUri);
            System.err.println(e.getMessage());
        } catch (UnsupportedShapefileExportException e) {
            System.out.println("This operation does not support shapefile extraction: " + asyncShapefileDownloadUri);
        }
    }
}
