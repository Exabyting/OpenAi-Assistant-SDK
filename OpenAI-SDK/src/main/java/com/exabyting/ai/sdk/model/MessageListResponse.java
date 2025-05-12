package com.exabyting.ai.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageListResponse {
    private String object;
    private List<Message> data;

    private String firstId;
    private String lastId;
    private boolean hasMore;

    public String getObject() {
        return object;
    }

    public List<Message> getData() {
        return data;
    }

    public String getFirstId() {
        return firstId;
    }

    public String getLastId() {
        return lastId;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    @Override
    public String toString() {
        return "MessageListResponse{" +
                "object='" + object + '\'' +
                ", data=" + data +
                ", firstId='" + firstId + '\'' +
                ", lastId='" + lastId + '\'' +
                ", hasMore=" + hasMore +
                '}';
    }

}
