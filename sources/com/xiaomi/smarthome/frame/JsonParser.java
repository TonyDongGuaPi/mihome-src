package com.xiaomi.smarthome.frame;

import org.json.JSONException;
import org.json.JSONObject;

public interface JsonParser<T> {
    T parse(JSONObject jSONObject) throws JSONException;
}
