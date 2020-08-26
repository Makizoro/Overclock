package com.example.testing;

public class CSISubs {
    private String type;
    private String csi;
    private String username;

    public CSISubs(){}

    public CSISubs(String csi, String username, String type){
        this.csi = csi;
        this.username = username;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
