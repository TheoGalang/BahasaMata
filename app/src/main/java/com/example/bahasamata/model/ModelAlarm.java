package com.example.bahasamata.model;

import java.util.List;

public class ModelAlarm {
    private String status,message;
    private List<Alarm> data;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<Alarm> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Alarm> getData() {
        return data;
    }
}
