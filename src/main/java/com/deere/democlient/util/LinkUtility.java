package com.deere.democlient.util;

import com.deere.api.axiom.generated.v3.Resource;
import com.deere.democlient.exceptions.UnavailableRelException;

import java.util.Optional;

public class LinkUtility {
    public static String getUriForRel(Resource resource, String rel) throws UnavailableRelException {
        return resource.getLinks().stream().filter(link -> link.getRel().equals(rel)).findAny().orElseThrow(() -> new UnavailableRelException()).getUri();
    }

    public static Optional<String> getUriOptionalForRel(Resource resource, String rel) {
        return resource.getLinks().stream().filter(link->link.getRel().equals(rel)).map(link->link.getUri()).findAny();
    }
}
