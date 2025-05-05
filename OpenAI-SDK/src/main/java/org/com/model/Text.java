package org.com.model;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Text {

    private String value;

    public String getValue() {
        return value;
    }
}
