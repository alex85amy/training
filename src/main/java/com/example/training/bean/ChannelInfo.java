package com.example.training.bean;

import com.google.gson.annotations.SerializedName;

public class ChannelInfo {

    @SerializedName("auto_id")
    private int autoId;

    @SerializedName("source_id")
    private String sourceId;

    @SerializedName("source_area_id")
    private String sourceAreaId;

    @SerializedName("is_used")
    private int isUsed;

    @SerializedName("p_type_2")
    private String pType2;

    public ChannelInfo() {
    }

    public ChannelInfo(int autoId, String sourceId, String sourceAreaId, int isUsed, String pType2) {
        this.autoId = autoId;
        this.sourceId = sourceId;
        this.sourceAreaId = sourceAreaId;
        this.isUsed = isUsed;
        this.pType2 = pType2;
    }


    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceAreaId() {
        return sourceAreaId;
    }

    public void setSourceAreaId(String sourceAreaId) {
        this.sourceAreaId = sourceAreaId;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }

    public String getPType2() {
        return pType2;
    }

    public void setPType2(String pType2) {
        this.pType2 = pType2;
    }

}
