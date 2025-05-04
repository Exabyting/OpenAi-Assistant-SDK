package org.com.model;

import java.util.List;

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
}
