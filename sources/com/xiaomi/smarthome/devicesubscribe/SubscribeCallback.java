package com.xiaomi.smarthome.devicesubscribe;

import com.xiaomi.smarthome.frame.Error;
import org.json.JSONArray;

public interface SubscribeCallback {
    void a(Error error);

    void a(String str);

    void a(String str, String str2, JSONArray jSONArray);
}
