
package com.deere.api.axiom.generated.v3;

public class File extends Resource {

    private final static long serialVersionUID = 1L;
    protected String name;
    protected String type;
    protected String createdTime;
    protected String modifiedTime;
    protected Long nativeSize;
    protected String source;
    protected Boolean transferPending;
    protected String visibleViaShare;
    protected Boolean shared;
    protected Boolean _new;
    protected String status;
    protected String invalidFileReasonText;
    protected Boolean archived;
    protected Boolean success;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Long getNativeSize() {
        return nativeSize;
    }

    public void setNativeSize(Long nativeSize) {
        this.nativeSize = nativeSize;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getTransferPending() {
        return transferPending;
    }

    public void setTransferPending(Boolean transferPending) {
        this.transferPending = transferPending;
    }

    public String getVisibleViaShare() {
        return visibleViaShare;
    }

    public void setVisibleViaShare(String visibleViaShare) {
        this.visibleViaShare = visibleViaShare;
    }

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public Boolean getNew() {
        return _new;
    }

    public void setNew(Boolean _new) {
        this._new = _new;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvalidFileReasonText() {
        return invalidFileReasonText;
    }

    public void setInvalidFileReasonText(String invalidFileReasonText) {
        this.invalidFileReasonText = invalidFileReasonText;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
