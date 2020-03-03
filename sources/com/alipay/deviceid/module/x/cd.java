package com.alipay.deviceid.module.x;

import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class cd {
    public static boolean a(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static JSONArray a(List<Map<String, String>> list) {
        JSONArray jSONArray = new JSONArray();
        if (list == null || list.size() <= 0) {
            return jSONArray;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry entry : list.get(size).entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }
}
