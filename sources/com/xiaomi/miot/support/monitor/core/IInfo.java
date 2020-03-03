package com.xiaomi.miot.support.monitor.core;

import android.content.ContentValues;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public interface IInfo extends Serializable {
    int getId();

    void parserJson(JSONObject jSONObject) throws JSONException;

    void parserJsonStr(String str) throws JSONException;

    ContentValues toContentValues();

    JSONObject toJson() throws JSONException;
}
