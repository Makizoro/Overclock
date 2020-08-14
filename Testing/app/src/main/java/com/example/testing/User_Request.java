package com.example.testing;

public class User_Request {
    private String type;
    private String username;
    private String csi;

    public User_Request(){}

    public User_Request(String type,String username, String csi){
        this.type = type;
        this.username = username;
        this.csi = csi;
    }

    public String getCsi() {
        return csi;
    }

    public void setCsi(String csi) {
        this.csi = csi;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
