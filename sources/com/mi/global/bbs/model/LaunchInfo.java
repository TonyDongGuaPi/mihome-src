package com.mi.global.bbs.model;

import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import org.json.JSONObject;

public class LaunchInfo implements Serializable {
    private static final long serialVersionUID = 1;
    public int duration;
    public long endTime;
    public String img;
    public String openType;
    public long startTime;
    public String url;

    public static LaunchInfo parse(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        LaunchInfo launchInfo = new LaunchInfo();
        launchInfo.startTime = jSONObject.optLong("startTime");
        launchInfo.endTime = jSONObject.optLong("endTime");
        launchInfo.url = jSONObject.optString("url");
        launchInfo.duration = jSONObject.optInt("duration");
        launchInfo.img = jSONObject.optString("img");
        launchInfo.openType = jSONObject.optString("openType");
        if (TextUtils.isEmpty(launchInfo.img)) {
            return null;
        }
        return launchInfo;
    }

    public String toString() {
        return "LaunchInfo{startTime=" + this.startTime + ", endTime=" + this.endTime + ", url='" + this.url + Operators.SINGLE_QUOTE + ", img='" + this.img + Operators.SINGLE_QUOTE + ", duration=" + this.duration + ", openType='" + this.openType + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String str) {
        this.img = str;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public String getOpenType() {
        return this.openType;
    }

    public void setOpenType(String str) {
        this.openType = str;
    }
}
