package com.example.testing;

public class CSI {
    private String name;
    private String type;
    private String email;
    private String description;

    public CSI(){

    }

    public CSI(String name, String type, String email, String description){
        this.name = name;
        this.type = type;
        this.email = email;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
}
