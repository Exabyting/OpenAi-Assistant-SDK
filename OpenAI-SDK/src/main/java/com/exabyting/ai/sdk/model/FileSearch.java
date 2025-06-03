package com.exabyting.ai.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FileSearch {

    @JsonProperty("vector_store_ids")
    private List<String> vectorStoreIds;

    public List<String> getVectorStoreIds() {
        return vectorStoreIds;
    }

    public void setVectorStoreIds(List<String> vectorStoreIds) {
        this.vectorStoreIds = vectorStoreIds;
    }
}
