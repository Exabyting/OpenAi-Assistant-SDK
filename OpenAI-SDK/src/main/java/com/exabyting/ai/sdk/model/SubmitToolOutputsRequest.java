package com.exabyting.ai.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubmitToolOutputsRequest {
    private List<ToolOutput> toolOutputs;

    public List<ToolOutput> getToolOutputs() {
        return toolOutputs;
    }

    public void setToolOutputs(List<ToolOutput> toolOutputs) {
        this.toolOutputs = toolOutputs;
    }
}
