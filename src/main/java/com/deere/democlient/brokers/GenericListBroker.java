package com.deere.democlient.brokers;

import com.deere.api.pagination.CollectionPage;
import com.deere.democlient.apis.AbstractApiBase;
import com.deere.rest.HttpHeader;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import org.codehaus.jackson.type.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenericListBroker extends AbstractApiBase {

    public <T> List<T> getList(String uri, final Class<T> clazz) {
        List<T> items = new ArrayList<>();
        while (uri != null) {
            CollectionPage<T> page = getSinglePage(uri, new TypeReference<CollectionPage<T>>() {});
            List itemsFromCurrentPage = page.stream().map(item -> getObjectMapper().convertValue(item, clazz)).collect(Collectors.toList());
            items.addAll(itemsFromCurrentPage);
            uri = page.getNextPage() == null? null : page.getNextPage().toString();
        }
        return items;
    }

    private <T> T getSinglePage(String uri, final TypeReference<T> type) {
        RestRequest request = oauthRequestTo(uri)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse response = request.fetchResponse();
        final T items = read(response).as(type);
        return items;
    }
}
