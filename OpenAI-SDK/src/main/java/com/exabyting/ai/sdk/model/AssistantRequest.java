package com.exabyting.ai.sdk.model;

import java.util.List;

public class AssistantRequest {
    public String instructions;
    public String name;
    public String model;
    public List<Tool> tools;
    public ToolResources tool_resources;

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ToolResources getTool_resources() {
        return tool_resources;
    }

    public void setTool_resources(ToolResources tool_resources) {
        this.tool_resources = tool_resources;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }
}
