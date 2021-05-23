package com.example.bahasamata.model;




import java.util.List;

public class ModelRegister {
    private String status;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<User> getData() {
        return data;
    }

    private String message;
    private List<User> data;
}
