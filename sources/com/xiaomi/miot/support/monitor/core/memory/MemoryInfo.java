package com.xiaomi.miot.support.monitor.core.memory;

import android.content.ContentValues;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class MemoryInfo extends BaseInfo {
    public static final String KEY_DALVIK_PSS = "dp";
    public static final String KEY_NATIVE_PSS = "np";
    public static final String KEY_OTHER_PSS = "op";
    public static final String KEY_PROCESS_NAME = "pn";
    public static final String KEY_TOTAL_PSS = "tp";
    public int dalvikPss;
    public int nativePss;
    public int otherPss;
    public String processName;
    public int totalPss;

    public MemoryInfo(int i, String str, int i2, int i3, int i4, int i5) {
        this(i, 0, str, i2, i3, i4, i5);
    }

    public MemoryInfo(int i, long j, String str, int i2, int i3, int i4, int i5) {
        this.mId = i;
        this.processName = str;
        this.totalPss = i2;
        this.dalvikPss = i3;
        this.nativePss = i4;
        this.otherPss = i5;
        this.recordTime = j;
    }

    public MemoryInfo(String str, int i, int i2, int i3, int i4) {
        this(-1, str, i, i2, i3, i4);
    }

    public JSONObject toJson() throws JSONException {
        return super.toJson().put("pn", this.processName).put("tp", this.totalPss);
    }

    public void parserJsonStr(String str) throws JSONException {
        parserJson(new JSONObject(str));
    }

    public void parserJson(JSONObject jSONObject) throws JSONException {
        this.processName = jSONObject.getString("pn");
        this.dalvikPss = jSONObject.getInt(KEY_DALVIK_PSS);
        this.nativePss = jSONObject.getInt(KEY_NATIVE_PSS);
        this.otherPss = jSONObject.getInt("op");
        this.totalPss = jSONObject.getInt("tp");
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("pn", this.processName);
        contentValues.put("tp", Integer.valueOf(this.totalPss));
        contentValues.put(KEY_DALVIK_PSS, Integer.valueOf(this.dalvikPss));
        contentValues.put(KEY_NATIVE_PSS, Integer.valueOf(this.nativePss));
        contentValues.put("op", Integer.valueOf(this.otherPss));
        return contentValues;
    }

    public String toString() {
        try {
            return toJson().toString();
        } catch (Exception unused) {
            return super.toString();
        }
    }
}
