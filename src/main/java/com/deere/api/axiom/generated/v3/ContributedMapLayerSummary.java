package com.deere.api.axiom.generated.v3;

import java.util.List;

public class ContributedMapLayerSummary extends Resource {
    protected String title;
    protected String text;
    protected List<ContributedMetadata> metadata;
    protected String dateCreated;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ContributedMetadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<ContributedMetadata> metadata) {
        this.metadata = metadata;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }


}
