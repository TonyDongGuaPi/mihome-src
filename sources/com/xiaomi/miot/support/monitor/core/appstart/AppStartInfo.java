package com.xiaomi.miot.support.monitor.core.appstart;

import android.content.ContentValues;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class AppStartInfo extends BaseInfo {
    public static String KEY_START_TIME = "t";
    public static String KEY_START_TYPE = "st";
    private int mStartTime;
    private int mStartType = 1;

    public AppStartInfo(int i, int i2) {
        this.mStartTime = i;
        this.mStartType = i2;
    }

    public JSONObject toJson() throws JSONException {
        return super.toJson().put(KEY_START_TIME, this.mStartTime).put(KEY_START_TYPE, this.mStartType);
    }

    public int getStartTime() {
        return this.mStartTime;
    }

    public void parserJsonStr(String str) throws JSONException {
        parserJson(new JSONObject(str));
    }

    public void parserJson(JSONObject jSONObject) throws JSONException {
        this.mStartTime = jSONObject.getInt(KEY_START_TIME);
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_START_TIME, Integer.valueOf(this.mStartTime));
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
