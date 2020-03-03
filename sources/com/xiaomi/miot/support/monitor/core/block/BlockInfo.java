package com.xiaomi.miot.support.monitor.core.block;

import android.content.ContentValues;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class BlockInfo extends BaseInfo {
    private final String SUB_TAG = "BlockInfo";
    public String blockStack;
    public int blockTime;
    public String processName;

    public static class DBKey {

        /* renamed from: a  reason: collision with root package name */
        public static final String f11459a = "pn";
        public static final String b = "stack";
        public static final String c = "bt";
    }

    public BlockInfo(String str) {
        this.blockStack = str;
    }

    public JSONObject toJson() throws JSONException {
        return super.toJson().put("stack", this.blockStack);
    }

    public void parserJsonStr(String str) throws JSONException {
        parserJson(new JSONObject(str));
    }

    public void parserJson(JSONObject jSONObject) throws JSONException {
        this.processName = jSONObject.getString("pn");
        this.blockStack = jSONObject.getString("stack");
        this.blockTime = jSONObject.getInt("bt");
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        try {
            contentValues.put("pn", this.processName);
            contentValues.put("stack", this.blockStack);
            contentValues.put("bt", Integer.valueOf(this.blockTime));
        } catch (Exception unused) {
        }
        return contentValues;
    }
}
