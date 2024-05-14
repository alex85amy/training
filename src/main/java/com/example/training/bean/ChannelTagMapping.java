package com.example.training.bean;


import com.google.gson.annotations.SerializedName;

public class ChannelTagMapping {
    @SerializedName("auto_id")
    private int autoId;

    @SerializedName("s_area_id")
    private String sourceAreaId;

    @SerializedName("tag_id")
    private int tagId;

    public ChannelTagMapping() {
    }

    public ChannelTagMapping(int autoId, String sourceAreaId, int tagId) {
        this.autoId = autoId;
        this.sourceAreaId = sourceAreaId;
        this.tagId = tagId;
    }

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
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
