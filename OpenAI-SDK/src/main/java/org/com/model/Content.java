package org.com.model;

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
}
