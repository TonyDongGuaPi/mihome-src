package com.xiaomi.miot.support.monitor.core.fps;

import android.content.ContentValues;
import android.text.TextUtils;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class FpsInfo extends BaseInfo {
    protected static int FPS_SAMPLE_TYPE_CHOREOGRAPHER = 0;
    protected static int FPS_SAMPLE_TYPE_SURFACEFLINGER = 1;
    public static final String KEY_ACTIVITY = "ac";
    public static final String KEY_CYCLE_TYPE = "ct";
    public static final String KEY_FPS = "fps";
    public static final String KEY_STACK = "s";
    public static final String KEY_TYPE = "ty";
    private String activity;
    private int cycleType;
    private int fps;
    private String processName;

    public int getFpsType() {
        return FPS_SAMPLE_TYPE_CHOREOGRAPHER;
    }

    public FpsInfo(int i) {
        this.cycleType = 1;
        this.mId = i;
    }

    public FpsInfo() {
        this(-1);
    }

    public FpsInfo(String str, int i, int i2) {
        this.cycleType = 1;
        this.activity = str;
        this.fps = i;
        this.cycleType = i2;
    }

    public void setActivity(String str) {
        this.activity = str;
    }

    public String getActivity() {
        return this.activity;
    }

    public void setFps(int i) {
        this.fps = i;
    }

    public int getFps() {
        return this.fps;
    }

    public String getProcessName() {
        return this.processName;
    }

    public void setProcessName(String str) {
        this.processName = str;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject put = super.toJson().put("fps", this.fps).put("ac", this.activity).put("ct", this.cycleType);
        if (!TextUtils.isEmpty(this.params)) {
            put.put(BaseInfo.KEY_PARAM, this.params);
        }
        return put;
    }

    public void parserJsonStr(String str) throws JSONException {
        parserJson(new JSONObject(str));
    }

    public void parserJson(JSONObject jSONObject) throws JSONException {
        this.activity = jSONObject.getString("ac");
        this.fps = jSONObject.getInt("fps");
        this.params = jSONObject.getString(BaseInfo.KEY_PARAM);
        if (jSONObject.has("pn")) {
            this.processName = jSONObject.getString("pn");
        }
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("fps", Integer.valueOf(this.fps));
        contentValues.put("ac", this.activity);
        contentValues.put("ty", Integer.valueOf(getFpsType()));
        contentValues.put(BaseInfo.KEY_PARAM, this.params);
        contentValues.put("pn", this.processName);
        return contentValues;
    }
}
