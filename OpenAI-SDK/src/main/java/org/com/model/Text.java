package org.com.model;

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
