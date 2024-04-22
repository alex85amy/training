package com.example.training.bean;


import com.google.gson.annotations.SerializedName;

public class ChannelTagMapping {
    @SerializedName("s_area_id")
    private String sourceAreaId;

    @SerializedName("tag_id")
    private int tagId;

    public ChannelTagMapping() {
    }

    public ChannelTagMapping(String sourceAreaId, int tagId) {
        this.sourceAreaId = sourceAreaId;
        this.tagId = tagId;
    }

    public String getSourceAreaId() {
        return sourceAreaId;
    }

    public void setSourceAreaId(String sourceAreaId) {
        this.sourceAreaId = sourceAreaId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
