package com.koushikdutta.async.http.socketio;

import org.json.JSONArray;

public interface EventCallback {
    void a(String str, JSONArray jSONArray, Acknowledge acknowledge);
}
