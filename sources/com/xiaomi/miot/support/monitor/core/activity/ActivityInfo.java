package com.xiaomi.miot.support.monitor.core.activity;

import android.content.ContentValues;
import android.text.TextUtils;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityInfo extends BaseInfo {
    public static final int COLD_START = 1;
    public static final int HOT_START = 2;
    public static final String KEY_LIFE_CYCLE = "lc";
    public static final String KEY_NAME = "n";
    public static final String KEY_START_TYPE = "st";
    public static final String KEY_TIME = "t";
    public static final String TAG = "ActivityInfo";
    public static final int TYPE_CREATE = 2;
    public static final int TYPE_DESTROY = 7;
    public static final int TYPE_FIRST_FRAME = 1;
    public static final int TYPE_PAUSE = 5;
    public static final int TYPE_RESUME = 4;
    public static final int TYPE_START = 3;
    public static final int TYPE_STOP = 6;
    public static final String TYPE_STR_FIRSTFRAME = "firstFrame";
    public static final String TYPE_STR_ONCREATE = "onCreate";
    public static final String TYPE_STR_ONDESTROY = "onDestroy";
    public static final String TYPE_STR_ONPAUSE = "onPause";
    public static final String TYPE_STR_ONRESUME = "onResume";
    public static final String TYPE_STR_ONSTART = "onStart";
    public static final String TYPE_STR_ONSTOP = "onStop";
    public static final String TYPE_STR_UNKNOWN = "unKnown";
    public static final int TYPE_UNKNOWN = 0;
    public String activityName = "";
    public int lifeCycle = 0;
    public String pluginName = "";
    public String pluginVer = "";
    public int startType = 0;
    public long time = 0;

    public void resetData() {
        this.mId = -1;
        this.activityName = "";
        this.recordTime = 0;
        this.startType = 0;
        this.time = 0;
        this.lifeCycle = 0;
        this.pluginName = "";
        this.pluginVer = "";
    }

    public JSONObject toJson() throws JSONException {
        return super.toJson().put("n", this.activityName).put("st", this.startType).put("t", this.time).put(KEY_LIFE_CYCLE, this.lifeCycle);
    }

    public void parserJsonStr(String str) throws JSONException {
        parserJson(new JSONObject(str));
    }

    public void parserJson(JSONObject jSONObject) throws JSONException {
        this.activityName = jSONObject.getString("n");
        this.startType = jSONObject.getInt("st");
        this.time = jSONObject.getLong("t");
        this.lifeCycle = jSONObject.getInt(KEY_LIFE_CYCLE);
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("n", this.activityName);
        contentValues.put("st", Integer.valueOf(this.startType));
        contentValues.put("t", Long.valueOf(this.time));
        contentValues.put(KEY_LIFE_CYCLE, Integer.valueOf(this.lifeCycle));
        return contentValues;
    }

    public String getLifeCycleString() {
        switch (this.lifeCycle) {
            case 1:
                return TYPE_STR_FIRSTFRAME;
            case 2:
                return "onCreate";
            case 3:
                return "onStart";
            case 4:
                return "onResume";
            case 5:
                return "onPause";
            case 6:
                return "onStop";
            case 7:
                return TYPE_STR_ONDESTROY;
            default:
                return TYPE_STR_UNKNOWN;
        }
    }

    public static int ofLifeCycleString(String str) {
        if (TextUtils.equals(str, TYPE_STR_FIRSTFRAME)) {
            return 1;
        }
        if (TextUtils.equals(str, "onCreate")) {
            return 2;
        }
        if (TextUtils.equals(str, "onStart")) {
            return 3;
        }
        if (TextUtils.equals(str, "onResume")) {
            return 4;
        }
        if (TextUtils.equals(str, "onPause")) {
            return 5;
        }
        if (TextUtils.equals(str, "onStop")) {
            return 6;
        }
        return TextUtils.equals(str, TYPE_STR_ONDESTROY) ? 7 : 0;
    }
}
