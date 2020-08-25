package com.example.testing;

public class GeneralForum {
    private String topic;
    private String message;
    private String username;

    public GeneralForum(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public GeneralForum(String topic, String message, String username){
        this.topic = topic;
        this.message = message;
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
