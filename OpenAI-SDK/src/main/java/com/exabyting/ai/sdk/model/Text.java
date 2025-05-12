package com.exabyting.ai.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Text {

    private String value;

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Text{" +
                "value='" + value + '\'' +
                '}';
    }
}
