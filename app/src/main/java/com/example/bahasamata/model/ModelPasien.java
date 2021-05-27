package com.example.bahasamata.model;

import java.util.List;

public class ModelPasien {
    private String status,message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Pasien> getData() {
        return data;
    }

    public void setData(List<Pasien> data) {
        this.data = data;
    }

    private List<Pasien> data;
}
