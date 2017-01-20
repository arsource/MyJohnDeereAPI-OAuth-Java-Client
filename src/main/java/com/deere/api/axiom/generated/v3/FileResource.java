package com.deere.api.axiom.generated.v3;

import org.joda.time.DateTime;

import java.util.List;

public class FileResource extends Resource{
    protected DateTime timestamp;
    protected String mimeType;
    protected List<ContributedMetadata> contributedMetadata;

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public List<ContributedMetadata> getContributedMetadata() {
        return contributedMetadata;
    }

    public void setContributedMetadata(List<ContributedMetadata> contributedMetadata) {
        this.contributedMetadata = contributedMetadata;
    }
}
