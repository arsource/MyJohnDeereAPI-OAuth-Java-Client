
package com.deere.api.axiom.generated.v3;

import java.util.ArrayList;
import java.util.List;

public class EventAssociation
    extends Resource
{
    private final static long serialVersionUID = 1L;
    protected String geometry;
    protected List<String> affectedTypes;
    public String getGeometry() {
        return geometry;
    }
    public void setGeometry(String value) {
        this.geometry = value;
    }

    public List<String> getAffectedTypes() {
        if (affectedTypes == null) {
            affectedTypes = new ArrayList<String>();
        }
        return this.affectedTypes;
    }
}
