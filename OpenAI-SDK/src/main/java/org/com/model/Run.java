package org.com.model;

public class Run {
    private String id;
    private String object;
    private long createdAt;
    private String assistantId;
    private String threadId;
    private String status;
    private Long startedAt;
    private Long expiresAt;
    private Long cancelledAt;
    private Long failedAt;
    private Long completedAt;
    private String lastError;
    private String model;
    private String instructions;
    private Object incompleteDetails;
    private Double temperature;
    private Double topP;
    private Integer maxPromptTokens;
    private Integer maxCompletionTokens;
    private String responseFormat;
    private String toolChoice;
    private Boolean parallelToolCalls;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(String assistantId) {
        this.assistantId = assistantId;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Long startedAt) {
        this.startedAt = startedAt;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Long getCancelledAt() {
        return cancelledAt;
    }

    public void setCancelledAt(Long cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public Long getFailedAt() {
        return failedAt;
    }

    public void setFailedAt(Long failedAt) {
        this.failedAt = failedAt;
    }

    public Long getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Long completedAt) {
        this.completedAt = completedAt;
    }

    public String getLastError() {
        return lastError;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Object getIncompleteDetails() {
        return incompleteDetails;
    }

    public void setIncompleteDetails(Object incompleteDetails) {
        this.incompleteDetails = incompleteDetails;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTopP() {
        return topP;
    }

    public void setTopP(Double topP) {
        this.topP = topP;
    }

    public Integer getMaxPromptTokens() {
        return maxPromptTokens;
    }

    public void setMaxPromptTokens(Integer maxPromptTokens) {
        this.maxPromptTokens = maxPromptTokens;
    }

    public Integer getMaxCompletionTokens() {
        return maxCompletionTokens;
    }

    public void setMaxCompletionTokens(Integer maxCompletionTokens) {
        this.maxCompletionTokens = maxCompletionTokens;
    }

    public String getResponseFormat() {
        return responseFormat;
    }

    public void setResponseFormat(String responseFormat) {
        this.responseFormat = responseFormat;
    }

    public String getToolChoice() {
        return toolChoice;
    }

    public void setToolChoice(String toolChoice) {
        this.toolChoice = toolChoice;
    }

    public Boolean getParallelToolCalls() {
        return parallelToolCalls;
    }

    public void setParallelToolCalls(Boolean parallelToolCalls) {
        this.parallelToolCalls = parallelToolCalls;
    }
}
