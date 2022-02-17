package com.firebase.appesp32example1.fragment;

import java.io.Serializable;

public class ApplicationList implements Serializable {
    private int id ;
    private String name;
    private String status;
    private int time;
    private int image;

    public ApplicationList(String name, String status, int time, int image) {
        this.name = name;
        this.status = status;
        this.time = time;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
