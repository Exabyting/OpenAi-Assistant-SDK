package org.com.model;

public class RunRequest {
    private String assistantId;

    public RunRequest(String assistantId) {
        this.assistantId = assistantId;
    }

    public String getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(String assistantId) {
        this.assistantId = assistantId;
    }
}
