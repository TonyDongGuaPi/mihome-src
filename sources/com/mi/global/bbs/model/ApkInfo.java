package com.mi.global.bbs.model;

import java.io.Serializable;
import org.json.JSONObject;

public class ApkInfo implements Serializable {
    private static final long serialVersionUID = 1;
    public boolean forceUpdate;
    public String notes;
    public String url;
    public String version;

    public static ApkInfo parse(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        ApkInfo apkInfo = new ApkInfo();
        apkInfo.version = jSONObject.optString("version");
        apkInfo.notes = jSONObject.optString("notes");
        apkInfo.url = jSONObject.optString("url");
        apkInfo.forceUpdate = jSONObject.optBoolean("forceUpdate");
        return apkInfo;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String str) {
        this.notes = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public boolean isForceUpdate() {
        return this.forceUpdate;
    }

    public void setForceUpdate(boolean z) {
        this.forceUpdate = z;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
