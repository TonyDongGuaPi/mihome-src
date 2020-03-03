package com.xiaomi.miot.support.monitor.core;

import android.content.ContentValues;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseInfo implements IInfo {
    public static final String KEY_ID_RECORD = "id";
    public static final String KEY_PARAM = "par";
    public static final String KEY_PROCESS_NAME = "pn";
    public static final String KEY_RESERVE_1 = "r1";
    public static final String KEY_RESERVE_2 = "r2";
    public static final String KEY_STACK_NAME = "sn";
    public static final String KEY_THREAD_ID = "tid";
    public static final String KEY_THREAD_NAME = "tn";
    public static final String KEY_TIME_RECORD = "tr";
    protected int mId = -1;
    protected String params;
    protected long recordTime;

    public void parserJson(JSONObject jSONObject) throws JSONException {
    }

    public void parserJsonStr(String str) throws JSONException {
    }

    public ContentValues toContentValues() {
        return null;
    }

    public JSONObject toJson() throws JSONException {
        return new JSONObject();
    }

    public void setId(int i) {
        this.mId = i;
    }

    public int getId() {
        return this.mId;
    }

    public long getRecordTime() {
        return this.recordTime;
    }

    public void setRecordTime(long j) {
        this.recordTime = j;
    }

    public String getParams() {
        return this.params;
    }

    public void setParams(String str) {
        this.params = str;
    }

    public String toString() {
        try {
            return toJson().toString();
        } catch (Exception unused) {
            return super.toString();
        }
    }
}
