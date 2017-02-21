package com.deere.api.axiom.generated.v3;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by dv30371 on 1/18/2017.
 */
public class GenericNote  extends Resource{

    protected String createdDate;
    protected DateTime lastModifiedDate;
    protected String title;
    protected String text;
    protected List<ContributedMetadata> metadata;
    protected User author;
    protected String geometry;
    protected String noteType;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }
}
