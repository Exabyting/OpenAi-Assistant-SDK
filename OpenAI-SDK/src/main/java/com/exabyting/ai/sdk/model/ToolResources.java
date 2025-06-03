package com.exabyting.ai.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ToolResources {

    @JsonProperty("file_search")
    private FileSearch fileSearch;

    // Getter and Setter
    public FileSearch getFileSearch() {
        return fileSearch;
    }

    public void setFileSearch(FileSearch fileSearch) {
        this.fileSearch = fileSearch;
    }
}
