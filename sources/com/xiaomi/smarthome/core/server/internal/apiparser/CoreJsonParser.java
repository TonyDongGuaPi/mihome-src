package com.xiaomi.smarthome.core.server.internal.apiparser;

import org.json.JSONException;
import org.json.JSONObject;

public interface CoreJsonParser<T> {
    T a(JSONObject jSONObject) throws JSONException;
}
