package org.com.model;

import java.util.List;

public class AssistantRequest {
    public String instructions;
    public String name;
    public String model;
    public List<Tool> tools;

    public static class Tool {
        public String type;
    }
}
