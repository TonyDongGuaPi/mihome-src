package com.xiaomi.phonenum.utils;

import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.i;
import com.taobao.weex.el.parse.Operators;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class MapUtil {
    public static String a(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            String str2 = (String) next.getValue();
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                if (z) {
                    z = false;
                } else {
                    sb.append(a.b);
                }
                sb.append(str);
                sb.append("=");
                sb.append(str2);
            }
        }
        return sb.toString();
    }

    public static String a(List<String> list, String str) {
        if (list == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String next : list) {
            if (!TextUtils.isEmpty(next)) {
                if (z) {
                    z = false;
                } else {
                    sb.append(str);
                }
                sb.append(next);
            }
        }
        return sb.toString();
    }

    public static String b(Map<String, List<String>> map) {
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.BLOCK_START_STR);
        boolean z = true;
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            String a2 = a((List) next.getValue(), i.b);
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(a2)) {
                if (z) {
                    z = false;
                } else {
                    sb.append(",");
                }
                sb.append("\"");
                sb.append(str);
                sb.append("\"");
                sb.append(":");
                sb.append("\"");
                sb.append(a2);
                sb.append("\"");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static JSONObject c(Map<String, List<String>> map) throws JSONException {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            String a2 = a((List) next.getValue(), i.b);
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(a2)) {
                jSONObject.put(str, a2);
            }
        }
        return jSONObject;
    }

    public static Map<String, String> a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        Iterator<String> keys = jSONObject.keys();
        HashMap hashMap = new HashMap();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.getString(next));
        }
        return hashMap;
    }
}
