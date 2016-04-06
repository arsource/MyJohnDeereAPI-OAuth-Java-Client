package com.deere.democlient.apis;

import com.deere.api.axiom.generated.v3.*;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import com.google.common.io.ByteStreams;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class PostJobs extends AbstractApiBase {

    private String newJobLink;

    public static void main(String[] arg) throws IOException {
        PostJobs jobs = new PostJobs();
        jobs.postJob();

        /** To post a TillageJob first need to get the fields, tillageType and  prescriptions for a given org**/
        //jobs.postTillageJobWithPrescriptions();

        /** To post a SeedingJob first need to get the fields, seeding product, reference-crop-id and  prescriptions for a given org**/
        //jobs.postSeedingJobWithPrescriptions();

        /** To post a SprayingJob first need to get the fields, application product and  prescriptions for a given org**/
        //jobs.postSprayingJobWithPrescriptions();
    }

    public void postJob() throws IOException {

        Job job = new Job();
        job.setCropYear("Please provide crop year");
        job.setNotes("Test Job Notes");
        job.setType(OperationType.SCOUTING.name());
        Link link = new Link();
        link.setRel("owningOrganization");
        link.setUri("/organizations/{id}");

        Link link1 = new Link();
        link1.setRel("location");
        link1.setUri("/organizations/{id}/fields/{fieldId}");

        List linkList = newArrayList(link, link1);
        job.setLinks(linkList);

        final ObjectMapper objectMapper = initObjectMapper();

        final RestRequest newJobsRequest = oauthRequestTo(baseJobsUri)
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(objectMapper.writeValueAsBytes(job)))
                .build();

        final RestResponse newJobsResponse = newJobsRequest.fetchResponse();
        System.out.println("Response code: " + newJobsResponse.getResponseCode());

        newJobLink = newJobsResponse.getHeaderFields().valueOf("Location");
        System.out.println("New Job Link : " + newJobLink);
    }

    public void postTillageJobWithPrescriptions() throws IOException {
        Job job = new Job();
        job.setCropYear("2021");
        Link owningOrganizationLink = new Link();
        owningOrganizationLink.setRel("owningOrganization");
        owningOrganizationLink.setUri("/organizations/{id}");
        Link locationLink = new Link();
        locationLink.setRel("location");
        locationLink.setUri("/organizations/{id}/fields/{fieldId}");
        List links = newArrayList(owningOrganizationLink, locationLink);
        job.setLinks(links);

        TillageOperation tillageOperation = new TillageOperation();
        Link tillageOperationLink = new Link();
        tillageOperationLink.setRel("tillageType");
        tillageOperationLink.setUri("/tillageTypes/{id}");
        tillageOperation.setLinks(newArrayList(tillageOperationLink));
        Link prescriptionLink = new Link();
        PrescriptionAssignment prescriptionAssignment = new PrescriptionAssignment();
        prescriptionLink.setRel("prescription");
        prescriptionLink.setUri("/prescriptions/{prescriptionId}");
        prescriptionAssignment.setLinks(newArrayList(prescriptionLink));
        tillageOperation.getPrescriptionAssignments().add(prescriptionAssignment);

        job.getOperations().add(tillageOperation);

        final ObjectMapper objectMapper = initObjectMapper();

        final RestRequest newJobsRequest = oauthRequestTo(baseJobsUri)
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(objectMapper.writeValueAsBytes(job)))
                .build();

        final RestResponse newJobsResponse = newJobsRequest.fetchResponse();
        System.out.println("Response code: " + newJobsResponse.getResponseCode());
        System.out.println("Response Body: " +newJobsResponse.readResponseAsString());

        newJobLink = newJobsResponse.getHeaderFields().valueOf("Location");
        System.out.println("New Job Link : " + newJobLink);
    }

    public void postSeedingJobWithPrescriptions() throws IOException {
        Job job = new Job();
        job.setCropYear("2021");
        Link owningOrganizationLink = new Link();
        owningOrganizationLink.setRel("owningOrganization");
        owningOrganizationLink.setUri("/organizations/{id}");
        Link locationLink = new Link();
        locationLink.setRel("location");
        locationLink.setUri("/organizations/{id}/fields/{fieldId}");
        List links = newArrayList(owningOrganizationLink, locationLink);
        job.setLinks(links);

        SeedingOperation seedingOperation = new SeedingOperation();
        Link seedingOperationLink = new Link();
        seedingOperationLink.setRel("cropType");
        seedingOperationLink.setUri("/reference-crop-id/{cropId}");
        seedingOperation.setLinks(newArrayList(seedingOperationLink));

        Link seedAssignments = new Link();
        SeedAssignment seedAssignment = new SeedAssignment();
        seedAssignments.setRel("product");
        seedAssignments.setUri("/organizations/{id}/products/{productId}");
        seedAssignment.setLinks(newArrayList(seedAssignments));
        seedingOperation.getSeedAssignments().add(seedAssignment);

        Link prescriptionLink = new Link();
        PrescriptionAssignment prescriptionAssignment = new PrescriptionAssignment();
        prescriptionLink.setRel("prescription");
        prescriptionLink.setUri("/prescriptions/{prescriptionId}");

        Link prescriptionProductLink = new Link();
        prescriptionProductLink.setRel("product");
        prescriptionProductLink.setUri("/organizations/{id}/products/{productId}");

        prescriptionAssignment.setLinks(newArrayList(prescriptionLink, prescriptionProductLink));
        seedingOperation.getPrescriptionAssignments().add(prescriptionAssignment);

        job.getOperations().add(seedingOperation);

        final ObjectMapper objectMapper = initObjectMapper();

        final RestRequest newJobsRequest = oauthRequestTo(baseJobsUri)
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(objectMapper.writeValueAsBytes(job)))
                .build();

        final RestResponse newJobsResponse = newJobsRequest.fetchResponse();
        System.out.println("Response code: " + newJobsResponse.getResponseCode());
        System.out.println("Response Body: " +newJobsResponse.readResponseAsString());

        newJobLink = newJobsResponse.getHeaderFields().valueOf("Location");
        System.out.println("New Job Link : " + newJobLink);
    }

    public void postSprayingJobWithPrescriptions() throws IOException {
        Job job = new Job();
        job.setCropYear("2021");
        Link owningOrganizationLink = new Link();
        owningOrganizationLink.setRel("owningOrganization");
        owningOrganizationLink.setUri("/organizations/{id}");
        Link locationLink = new Link();
        locationLink.setRel("location");
        locationLink.setUri("/organizations/{id}/fields/{fieldId}");
        List links = newArrayList(owningOrganizationLink, locationLink);
        job.setLinks(links);

        ApplicationOperation applicationOperation = new ApplicationOperation();
        Link productAssignmentLink = new Link();
        ProductAssignment productAssignment = new ProductAssignment();
        productAssignmentLink.setRel("product");
        productAssignmentLink.setUri("/organizations/{id}/products/{productId}");
        productAssignment.setLinks(newArrayList(productAssignmentLink));
        applicationOperation.getProductAssignments().add(productAssignment);
        applicationOperation.setTankMix(true);

        Link prescriptionLink = new Link();
        PrescriptionAssignment prescriptionAssignment = new PrescriptionAssignment();
        prescriptionLink.setRel("prescription");
        prescriptionLink.setUri("/prescriptions/{prescriptionId}");

        Link prescriptionProductLink = new Link();
        prescriptionProductLink.setRel("product");
        prescriptionProductLink.setUri("/organizations/{id}/products/{productId}");

        prescriptionAssignment.setLinks(newArrayList(prescriptionLink, prescriptionProductLink));
        applicationOperation.getPrescriptionAssignments().add(prescriptionAssignment);

        job.getOperations().add(applicationOperation);

        final ObjectMapper objectMapper = initObjectMapper();

        final RestRequest newJobsRequest = oauthRequestTo(baseJobsUri)
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(objectMapper.writeValueAsBytes(job)))
                .build();

        final RestResponse newJobsResponse = newJobsRequest.fetchResponse();
        System.out.println("Response code: " + newJobsResponse.getResponseCode());
        System.out.println("Response Body: " +newJobsResponse.readResponseAsString());

        newJobLink = newJobsResponse.getHeaderFields().valueOf("Location");
        System.out.println("New Job Link : " + newJobLink);
    }
}
