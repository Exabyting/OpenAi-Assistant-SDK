package com.exabyting.ai.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenAIVectorStoreResponse {

    private String id;
    private String object;

    @JsonProperty("created_at")
    private long createdAt;

    private String name;
    private long bytes;

    @JsonProperty("file_counts")
    private FileCounts fileCounts;

    // Getters and setters

    public static class FileCounts {
        @JsonProperty("in_progress")
        private int inProgress;

        private int completed;
        private int failed;
        private int cancelled;
        private int total;

        public int getInProgress() { return inProgress; }
        public void setInProgress(int inProgress) { this.inProgress = inProgress; }

        public int getCompleted() { return completed; }
        public void setCompleted(int completed) { this.completed = completed; }

        public int getFailed() { return failed; }
        public void setFailed(int failed) { this.failed = failed; }

        public int getCancelled() { return cancelled; }
        public void setCancelled(int cancelled) { this.cancelled = cancelled; }

        public int getTotal() { return total; }
        public void setTotal(int total) { this.total = total; }
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getObject() { return object; }
    public void setObject(String object) { this.object = object; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public long getBytes() { return bytes; }
    public void setBytes(long bytes) { this.bytes = bytes; }

    public FileCounts getFileCounts() { return fileCounts; }
    public void setFileCounts(FileCounts fileCounts) { this.fileCounts = fileCounts; }
}
