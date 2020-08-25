package com.example.testing;

public class ForumCSI {
    private String topic;
    private String message;
    private String csi;
    private String username;

    public ForumCSI(){}

    public ForumCSI(String topic, String message, String username, String csi){
        this.topic = topic;
        this.csi = csi;
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCsi() {
        return csi;
    }

    public void setCsi(String csi) {
        this.csi = csi;
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
