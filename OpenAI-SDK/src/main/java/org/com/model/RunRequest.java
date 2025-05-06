package org.com.model;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RunRequest {

    @JsonProperty("assistant_id")
    private String assistantId;

    public String getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(String assistantId) {
        this.assistantId = assistantId;
    }
}
