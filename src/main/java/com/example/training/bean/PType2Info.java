package com.example.training.bean;

import com.google.gson.annotations.SerializedName;

public class PType2Info {
    @SerializedName("auto_id")
    private int autoId;

    @SerializedName("category")
    private String category;

    @SerializedName("name")
    private String name;

    public PType2Info() {
    }

    public PType2Info(int autoId, String category, String name) {
        this.autoId = autoId;
        this.category = category;
        this.name = name;
    }

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
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
