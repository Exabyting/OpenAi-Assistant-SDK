package com.exabyting.ai.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private String id;
    private String object;

    @JsonProperty("created_at")
    private long createdAt;

    @JsonProperty("assistant_id")
    private String assistantId;

    @JsonProperty("thread_id")
    private String threadId;

    @JsonProperty("run_id")
    private String runId;

    private String role;
    private List<Content> content;

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public String getAssistantId() {
        return assistantId;
    }

    public String getThreadId() {
        return threadId;
    }

    public String getRunId() {
        return runId;
    }

    public String getRole() {
        return role;
    }

    public List<Content> getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", object='" + object + '\'' +
                ", createdAt=" + createdAt +
                ", assistantId='" + assistantId + '\'' +
                ", threadId='" + threadId + '\'' +
                ", runId='" + runId + '\'' +
                ", role='" + role + '\'' +
                ", content=" + content +
                '}';
    }
}
