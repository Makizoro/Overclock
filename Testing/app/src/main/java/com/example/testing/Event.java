package com.example.testing;

public class Event {
    private String csi;
    private String name;
    private String date;
    private String venue;

    public Event(){}

    public Event(String csi, String name, String date, String venue){
        this.csi = csi;
        this.date = date;
        this.name = name;
        this.venue = venue;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCsi() {
        return csi;
    }

    public void setCsi(String csi) {
        this.csi = csi;
    }
}
