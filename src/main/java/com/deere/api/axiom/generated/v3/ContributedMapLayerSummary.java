package com.deere.api.axiom.generated.v3;

import java.util.List;

public class ContributedMapLayerSummary extends Resource {
    protected String title;
    protected String text;
    protected List<ContributedMetadata> contributedMetadata;
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

    public List<ContributedMetadata> getContributedMetadata() {
        return contributedMetadata;
    }

    public void setContributedMetadata(List<ContributedMetadata> contributedMetadata) {
        this.contributedMetadata = contributedMetadata;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }


}
