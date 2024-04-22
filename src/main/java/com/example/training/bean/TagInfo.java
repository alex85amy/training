package com.example.training.bean;


import com.google.gson.annotations.SerializedName;

public class TagInfo {
    @SerializedName("tag_id")
    private int tagId;

    @SerializedName("tag_name")
    private String tagName;

    @SerializedName("type")
    private int type;

    public TagInfo() {
    }

    public TagInfo(int tagId, String tagName, int type) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.type = type;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
