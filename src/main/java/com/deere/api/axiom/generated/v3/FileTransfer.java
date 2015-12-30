package com.deere.api.axiom.generated.v3;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by mn56246 on 8/18/2015.
 */

public class FileTransfer extends Resource {
    private final static long serialVersionUID = 1L;
    protected String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
