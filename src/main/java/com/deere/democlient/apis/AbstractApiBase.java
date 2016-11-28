package com.deere.democlient.apis;

import com.deere.api.axiom.generated.v3.ApiCatalog;
import com.deere.api.axiom.generated.v3.File;
import com.deere.api.axiom.generated.v3.Link;
import com.deere.api.axiom.generated.v3.Resource;
import com.deere.api.pagination.CollectionPageDeserializerFactory;
import com.deere.democlient.ApiCredentials;
import com.deere.rest.*;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.io.ByteStreams;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.hamcrest.Matcher;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.regex.Pattern;

import static com.deere.axiom.MatcherFactory.*;
import static com.deere.rest.RestRequestBuilder.request;
import static java.util.UUID.randomUUID;
import static org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_EMPTY;

public abstract class AbstractApiBase {

    private static final ClassLoader CLASS_LOADER = AbstractApiBase.class.getClassLoader();

    public final static String baseUri = "https://sandboxapi.deere.com/platform/";
    protected final static String basePartnershipUri = "https://sandboxapi.deere.com/platform/partnerships";
    protected final static String baseJobsUri = "https://sandboxapi.deere.com/platform/jobs";
    protected final static String V3_CONTENT_TYPE = "application/vnd.deere.axiom.v3+json";
    protected final static String V3_ACCEPTABLE_TYPE = "application/vnd.deere.axiom.v3+json";

    protected final Pattern contentDispositionPattern = Pattern.compile("\\s*attachment;\\s*filename=(\"?)([^\"]+)\\1");
    protected final String filename = randomUUID().toString() + ".zip";
    protected Map<String, Link> apiCatalog = initializeApiCatalog();
    private ObjectMapper objectMapper;

    private Map<String, Link> initializeApiCatalog() {
        final RestRequest apiCatalogRequest = oauthRequestTo(baseUri)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse apiCatalogResponse = apiCatalogRequest.fetchResponse();
        return linksFrom(read(apiCatalogResponse).as(ApiCatalog.class));
    }

    protected ContentExchanger read(final RestResponse restResponse) {
        final ContentExchanger read = ContentExchanger.read(restResponse);
        final ObjectMapper objectMapper = read.getObjectMapper();

        objectMapper.setDeserializerProvider(objectMapper
                .getDeserializerProvider()
                .withFactory(new CollectionPageDeserializerFactory(null)));
        return read;
    }

    public static <T> void assertThat(String reason, T actual, Matcher<T> matcher) {
        if (!matcher.matches(actual)) {
            throw new java.lang.AssertionError("No Match");

        }
    }


    public static java.io.File findFile(final String name) {
        try {
            return new java.io.File(CLASS_LOADER.getResource(name).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static RestRequestBuilder oauthRequestTo(final String baseUri) {
        return request()
                .baseUri(baseUri)
                .oauthClient(ApiCredentials.CLIENT)
                .oauthToken(ApiCredentials.TOKEN);
    }

    public static RestRequestBuilder oauthRequest2LeggedTo(final String baseUri) {
        return request()
                .baseUri(baseUri)
                .oauthClient(ApiCredentials.CLIENT);
    }

    public static Link linkWith(final String rel, final String uri) {
        final Link link = new Link();
        link.setRel(rel);
        link.setUri(uri);
        return link;
    }

    /*public static IsCollectionContaining<Link> contains(final Link link) {
        return isACollectionThatContainsSomethingThat(is(aLinkThatMatches(link)));
    }*/

    protected String getFileAsJson(final String filename) throws IOException {
        final File file = new File();
        file.setName(filename);
        return new ObjectMapper().writeValueAsString(file);
    }

    protected String doAddFileAndReturnLocation(final String uri, final String fileJson) {
        final RestRequest postNewFile = oauthRequestTo(uri)
                .method("POST")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader("Content-Type", V3_CONTENT_TYPE))
                .body(ByteStreams.newInputStreamSupplier(fileJson.getBytes()))
                .build();

        final RestResponse postResponse = postNewFile.fetchResponse();
        //checkThat("POST response code", postResponse.getResponseCode(), isEqualTo(CREATED));

        //assertThat("POST response code", postResponse.getResponseCode().series(), isEqualTo(SUCCESSFUL));

        final String location = postResponse.getHeaderFields().valueOf("Location");
        assertThat("POST Location", location, isNotNull());
        return location;
    }

    protected void doGetFileAtLocationAndCheckStatus(final String location, final Matcher<String> upload_pending) {
        final RestRequest fileRequest = oauthRequestTo(location)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse fileResponse = fileRequest.fetchResponse();
        //checkThat("FILE response", fileResponse.getResponseCode(), isEqualTo(OK));

        final File file = read(fileResponse).as(File.class);
        assertThat("FILE status", file.getStatus(), upload_pending);
    }

    protected void checkFilenameAndContentDispositionHeadersOn(final RestResponse rangeResponse) {
        assertThat("FileName header set?", rangeResponse.getHeaderFields().contains("Filename"), isFalse());
        if (rangeResponse.getHeaderFields().contains("Filename")) {
            assertThat("filename as reported by API", rangeResponse.getHeaderFields().valueOf("Filename"), isEqualTo(filename));
        }

        final String contentDispositionHeader = "Content-Disposition";
        assertThat("Content-Disposition header set?", rangeResponse.getHeaderFields().contains(contentDispositionHeader), isTrue());

        if (rangeResponse.getHeaderFields().contains(contentDispositionHeader)) {
            final String contentDisposition = rangeResponse.getHeaderFields().valueOf(contentDispositionHeader);
            final java.util.regex.Matcher matcher = contentDispositionPattern.matcher(contentDisposition);
            if (matcher.matches()) {
                assertThat("filename as reported by API", matcher.group(2), isEqualTo(filename));
            }
        }
    }

    protected void doDeleteFileAt(final String location) {
        //assume(location, isNotNull());
        final RestRequest deleteFile = oauthRequestTo(location)
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .method("DELETE")
                .build();

        final RestResponse deleteResponse = deleteFile.fetchResponse();

        //assertThat("DELETE response code", deleteResponse.getResponseCode(), isEqualTo(NO_CONTENT));
        //assertThat("DELETE response code", deleteResponse.getResponseCode().series(), isEqualTo(SUCCESSFUL));
    }

    public ImmutableMap<String, Link> linksFrom(final Resource resource) {
        return Maps.uniqueIndex(resource.getLinks(),
                new Function<Link, String>() {
                    @Override
                    public String apply(final Link input) {
                        return input.getRel();
                    }
                });
    }

    protected byte[] getBytesForObject(Object object) {
        try {
            final ObjectMapper objectMapper = getObjectMapper();
            return objectMapper.writeValueAsBytes(object);
        } catch (IOException e) {
            throw new RuntimeException("Failed to marshall the request input");
        }
    }

    protected ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.getSerializationConfig().setSerializationInclusion(NON_EMPTY);
            objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.setDeserializerProvider(objectMapper
                    .getDeserializerProvider()
                    .withFactory(new CollectionPageDeserializerFactory(null)));
        }
        return objectMapper;
    }
}
