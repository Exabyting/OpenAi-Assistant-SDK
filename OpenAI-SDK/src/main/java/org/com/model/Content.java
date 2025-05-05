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

    @Override
    public String toString() {
        return "Content{" +
                "type='" + type + '\'' +
                ", text=" + text +
                '}';
    }
}
