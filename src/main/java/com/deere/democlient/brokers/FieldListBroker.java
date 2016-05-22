package com.deere.democlient.brokers;

import com.deere.api.axiom.generated.v3.Field;
import com.deere.api.pagination.CollectionPage;
import com.deere.democlient.apis.AbstractApiBase;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import org.codehaus.jackson.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

public class FieldListBroker extends AbstractApiBase {

    public List<Field> getFieldList(String fieldListUri) {
        List<Field> fields = new ArrayList<>();
        while (fieldListUri != null) {
            CollectionPage<Field> page = getFieldsSinglePage(fieldListUri);
            fields.addAll(page);
            fieldListUri = page.getNextPage() == null? null : page.getNextPage().toString();
        }
        return fields;
    }

    private CollectionPage<Field> getFieldsSinglePage(String uri) {
        RestRequest request = oauthRequestTo(uri)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse response = request.fetchResponse();
        final CollectionPage<Field> fields =
                read(response).as(new TypeReference<CollectionPage<Field>>() {});
        return fields;
    }
}
