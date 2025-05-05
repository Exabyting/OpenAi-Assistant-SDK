package org.com.model;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Assistant {
    public String id;
    public String object;
    public String name;
    public String model;
    public Map<String, Object> additionalProperties;
}
