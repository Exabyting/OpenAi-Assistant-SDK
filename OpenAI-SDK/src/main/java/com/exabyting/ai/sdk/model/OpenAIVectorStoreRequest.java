package com.exabyting.ai.sdk.model;

public class OpenAIVectorStoreRequest {

    private String name;

    public OpenAIVectorStoreRequest() {
    }

    public OpenAIVectorStoreRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
