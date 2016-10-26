package com.deere.democlient.util;

import com.deere.api.axiom.generated.v3.Resource;
import com.deere.democlient.exceptions.UnavailableRelException;

public class LinkUtility {
    public static String getUriForRel(Resource resource, String rel) throws UnavailableRelException {
        return resource.getLinks().stream().filter(l -> l.getRel().equals(rel)).findAny().orElseThrow(() -> new UnavailableRelException()).getUri();
    }
}
