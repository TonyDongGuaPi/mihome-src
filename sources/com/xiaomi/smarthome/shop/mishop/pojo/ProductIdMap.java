package com.xiaomi.smarthome.shop.mishop.pojo;

import com.google.gson.annotations.SerializedName;

public class ProductIdMap {
    @SerializedName("GidMap")
    private GidMap gidMap;
    @SerializedName("LastModified")
    private long lastModified;
    @SerializedName("PidMap")
    private PidMap pidMap;
    @SerializedName("Timestamp")
    private long timestamp;

    public long getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(long j) {
        this.lastModified = j;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public GidMap getGidMap() {
        return this.gidMap;
    }

    public void setGidMap(GidMap gidMap2) {
        this.gidMap = gidMap2;
    }

    public PidMap getPidMap() {
        return this.pidMap;
    }

    public void setPidMap(PidMap pidMap2) {
        this.pidMap = pidMap2;
    }
}
