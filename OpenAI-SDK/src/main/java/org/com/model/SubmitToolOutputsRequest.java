package org.com.model;

import java.util.List;

public class SubmitToolOutputsRequest {
    private List<ToolOutput> toolOutputs;

    public List<ToolOutput> getToolOutputs() {
        return toolOutputs;
    }

    public void setToolOutputs(List<ToolOutput> toolOutputs) {
        this.toolOutputs = toolOutputs;
    }
}
