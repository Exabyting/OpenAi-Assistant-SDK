package com.exabyting.ai.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Content {

    private String type;
    private Text text;

    public String getType() {
        return type;
    }

    public Text getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Content{" +
                "type='" + type + '\'' +
                ", text=" + text +
                '}';
    }
}
