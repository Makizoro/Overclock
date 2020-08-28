package com.example.testing;

public class Person {
    private String type;
    private String username;

    public Person(){}

    public Person(String type, String username){
        this.type = type;
        this.username = username;
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
