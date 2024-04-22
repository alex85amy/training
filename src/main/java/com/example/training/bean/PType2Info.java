package com.example.training.bean;

import com.google.gson.annotations.SerializedName;

public class PType2Info {
    @SerializedName("category")
    private String category;

    @SerializedName("name")
    private String name;

    public PType2Info() {
    }

    public PType2Info(String category, String name) {
        this.category = category;
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
