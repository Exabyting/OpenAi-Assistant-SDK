package org.com.model;

public class MessageRequest {

    private String role;
    private String content;

    public MessageRequest(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }
}
