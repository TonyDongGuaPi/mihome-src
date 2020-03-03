package com.xiaomi.youpin.hawkeye.entity;

import com.google.gson.Gson;
import java.io.Serializable;

public class BaseInfo implements IData, Serializable {
    public static final String TAG = "BaseInfo";
    public int id;
    public int isUpload;
    public String mStatType;
    public String mTaskName;
    public String platform;
    public String statInfoJson;
    public String systemJson;
    public long timestamp;

    public static class SystemInfo implements Serializable {
        public String appVersion;
        public String availableMemory;
        public String deviceModel;
        public String networkType;
        public String osVersion;
        public int screenHeight;
        public int screenWidth;
        public String totalMemory;
    }

    public String toJson() {
        return new Gson().toJson((Object) this);
    }
}
