
package com.deere.api.axiom.generated.v3;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

public class Partnership extends Resource {
    protected List<Link> links = new ArrayList<Link>();

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

}
