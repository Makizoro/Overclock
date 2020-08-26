package com.example.testing;

public class Notification {
    private String csi;
    private String notification;

    public Notification(){}

    public Notification(String csi, String notification){
        this.csi = csi;
        this.notification = notification;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getCsi() {
        return csi;
    }

    public void setCsi(String csi) {
        this.csi = csi;
    }
}
