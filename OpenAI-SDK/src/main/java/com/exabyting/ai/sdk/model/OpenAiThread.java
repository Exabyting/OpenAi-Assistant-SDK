package com.exabyting.ai.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenAiThread {

    private String id;

    @JsonProperty("created_at")
    private long createdAt;

    private String object;

    public String getId() {
        return id;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public String getObject() {
        return object;
    }
}
