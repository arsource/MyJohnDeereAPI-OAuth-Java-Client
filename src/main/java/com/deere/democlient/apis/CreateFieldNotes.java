package com.deere.democlient.apis;

import com.deere.api.axiom.generated.v3.ContributedMetadata;
import com.deere.api.axiom.generated.v3.FileResource;
import com.deere.api.axiom.generated.v3.GenericNote;
import com.deere.api.axiom.generated.v3.Link;
import com.deere.democlient.util.JodaConverter;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import com.google.common.io.ByteStreams;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;

public class CreateFieldNotes extends AbstractApiBase {

    public static final String DEFINITION_ID = "Enter contribution definition ID here";
    public static final int ORG_ID = 0; // Assign Organization ID here
    public static final String FIELD_ID = "Enter field ID here";
    public static final String USER = "Enter user ID here";

    public static final String FILE_RESOURCE_PATH = "src/main/resources/Hail.jpg"; // Enter your file resource path
    public static final String FILE_NAME = "Hail.jpg"; // Enter file name here
    public static final String MIME_TYPE = "image/jpg"; // Enter mime type here



    public static void main(String[] arg) throws IOException {
        CreateFieldNotes createFieldNotes = new CreateFieldNotes();
        CreateFileResource fileResourceHelper = new CreateFileResource();

        GenericNote fieldNoteSummary =   createGenericNoteSummaryObjForPost(DEFINITION_ID, ORG_ID);
        String fieldNoteSummaryLocation = createFieldNotes.createFieldNoteSummary(fieldNoteSummary);

        GenericNote fieldNote =   createNoteObjForPost(DEFINITION_ID, ORG_ID);
        String fieldNoteLocation = createFieldNotes.createFieldNote(fieldNote);

        FileResource fileResource = createFileResourceWith(ORG_ID, fieldNoteLocation);
        String fileResourceLocation = fileResourceHelper.createFileResource(fieldNoteLocation, fileResource);

        fileResourceHelper.uploadFileResource(fileResourceLocation, FILE_RESOURCE_PATH);

       /* ResourceDeletionBroker deleteResource = new ResourceDeletionBroker();
        deleteResource.deleteResource(fieldNoteSummaryLocation);
        deleteResource.deleteResource(fieldNoteLocation);
        deleteResource.deleteResource(fileResourceLocation);*/
    }

    private static GenericNote createGenericNoteSummaryObjForPost(String definitionId, int orgId) {
        GenericNote genericNote = new GenericNote();
        ArrayList<Link> links = newArrayList(linkWith("contributionDefinition", baseUri + "contributionDefinitions/" + definitionId),
                linkWith("owningOrganization", baseUri + "organizations/" + orgId),
                linkWith("author", baseUri + "users/" + USER));
        genericNote.setLinks(links);

        genericNote.setTitle("Note Summary");
        genericNote.setText("This is test Note Summary");
        ArrayList<ContributedMetadata> metadata = newArrayList();

        ContributedMetadata data = new ContributedMetadata();
        data.setName("Summary Name");
        data.setValue("Summary value");
        metadata.add(data);
        genericNote.setMetadata(metadata);

        genericNote.setCreatedDate(JodaConverter.marshal(new DateTime()));
        genericNote.setNoteType("GenericNote");
        return genericNote;
    }

    private static GenericNote createNoteObjForPost(String definitionId, int orgId) {
        GenericNote genericNote = new GenericNote();
        ArrayList<Link> links = newArrayList(linkWith("contributionDefinition", baseUri + "contributionDefinitions/" + definitionId),
                linkWith("owningOrganization", baseUri + "organizations/" + orgId),
                linkWith("author", baseUri + "users/" + USER));
        genericNote.setLinks(links);

        genericNote.setTitle("Field Note");
        genericNote.setText("This is Field Note");
        ArrayList<ContributedMetadata> metadata = newArrayList();

        ContributedMetadata data = new ContributedMetadata();
        data.setName("Field Note name");
        data.setValue("Field Note value");
        metadata.add(data);
        genericNote.setMetadata(metadata);

        genericNote.setCreatedDate(JodaConverter.marshal(new DateTime()));
        genericNote.setGeometry("{\"type\": \"Point\", \"coordinates\": [-93.148968, 41.667917] }");
        genericNote.setNoteType("GenericNote");
        return genericNote;
    }

    private static FileResource createFileResourceWith(int orgId, String noteId) {
        FileResource fileResource = new FileResource();
        fileResource.setLinks(newArrayList(linkWith("owningOrganization", baseUri + "organizations/" + orgId),
                linkWith("targetResource", baseUri + "notes/" + noteId)));
        fileResource.setMimeType(MIME_TYPE);
        fileResource.setMetadata(newArrayList(new ContributedMetadata("filename", FILE_NAME)));
        return fileResource;
    }

    private String createFieldNoteSummary(GenericNote genericNote) {

        final RestRequest restRequest = oauthRequestTo(baseUri + "organizations/" + ORG_ID + "/fields/" + FIELD_ID + "/fieldNoteSummary")
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(getBytesForObject(genericNote)))
                .build();

        final RestResponse restResponse = restRequest.fetchResponse();
        System.out.println("Response code: " + restResponse.getResponseCode());

        String location = restResponse.getHeaderFields().valueOf("Location");

        System.out.println("New Field Note summary Link : " + location);
        return location;
    }

    private String createFieldNote(GenericNote genericNote) {

        final RestRequest restRequest = oauthRequestTo(baseUri + "organizations/" + ORG_ID + "/fields/" + FIELD_ID + "/notes")
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(getBytesForObject(genericNote)))
                .build();

        final RestResponse restResponse = restRequest.fetchResponse();
        System.out.println("Response code: " + restResponse.getResponseCode());

        String location = restResponse.getHeaderFields().valueOf("Location");

        System.out.println("New Field Note Link : " + location);
        return location;
    }

     public static String getId(String uri) {
        if (uri.contains("?")) {
            return uri.substring(uri.lastIndexOf('/') + 1, uri.indexOf('?'));
        }
        return uri.substring(uri.lastIndexOf('/') + 1, uri.length());
    }

}
