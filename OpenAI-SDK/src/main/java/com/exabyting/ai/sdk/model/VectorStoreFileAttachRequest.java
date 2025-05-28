package com.exabyting.ai.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VectorStoreFileAttachRequest {

    @JsonProperty("file_id")
    private String fileId;

    public VectorStoreFileAttachRequest() {}

    public VectorStoreFileAttachRequest(String fileId) {
        this.fileId = fileId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
