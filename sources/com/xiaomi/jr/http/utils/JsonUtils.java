package com.xiaomi.jr.http.utils;

import com.xiaomi.jr.common.utils.MifiLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10841a = "JsonUtils";

    public static Map<String, String> a(String str) {
        try {
            HashMap hashMap = new HashMap();
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
            }
            return hashMap;
        } catch (JSONException e) {
            MifiLog.e(f10841a, "jsonToMap failed - " + e.toString());
            return null;
        }
    }
}
