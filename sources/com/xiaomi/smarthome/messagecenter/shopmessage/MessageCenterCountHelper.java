package com.xiaomi.smarthome.messagecenter.shopmessage;

import android.text.TextUtils;
import com.adobe.xmp.XMPConst;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.messagecenter.AllTypeMsgManager;
import com.xiaomi.smarthome.messagecenter.MessageCenter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageCenterCountHelper {

    /* renamed from: a  reason: collision with root package name */
    private static long f10489a;

    public static Map<String, Long> a() {
        Set<String> keySet = AllTypeMsgManager.a().d().keySet();
        HashMap hashMap = new HashMap();
        for (String put : keySet) {
            hashMap.put(put, Long.valueOf(c()));
        }
        try {
            JSONArray jSONArray = new JSONArray(PreferenceUtils.a(MessageCenter.e, XMPConst.ai));
            if (jSONArray.length() == 0) {
                return hashMap;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                String optString = optJSONObject.optString("type");
                if (optJSONObject.optBoolean("message_read", false)) {
                    long a2 = a(optString);
                    if (a2 > 0) {
                        a2++;
                    }
                    hashMap.put(optString, Long.valueOf(a2));
                }
            }
            return hashMap;
        } catch (Exception unused) {
        }
    }

    public static void a(Map<String, Long> map) {
        if (map != null && !map.isEmpty()) {
            for (String next : map.keySet()) {
                a(next, map.get(next).longValue());
            }
        }
    }

    public static void a(String str, long j) {
        try {
            JSONArray jSONArray = new JSONArray(PreferenceUtils.a(MessageCenter.e, XMPConst.ai));
            if (jSONArray.length() != 0) {
                int i = 0;
                while (true) {
                    if (i >= jSONArray.length()) {
                        break;
                    }
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (!TextUtils.equals(optJSONObject.optString("type"), str)) {
                        i++;
                    } else if (j > optJSONObject.optLong("latest_time_stamp", 0)) {
                        optJSONObject.put("latest_time_stamp", j);
                    }
                }
                PreferenceUtils.b(SHApplication.getAppContext(), MessageCenter.e, jSONArray.toString());
            }
        } catch (Exception unused) {
        }
    }

    public static long a(String str) {
        try {
            JSONArray jSONArray = new JSONArray(PreferenceUtils.a(MessageCenter.e, XMPConst.ai));
            if (jSONArray.length() == 0) {
                return 0;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (TextUtils.equals(optJSONObject.optString("type"), str)) {
                    return optJSONObject.optLong("latest_time_stamp", 0);
                }
            }
            return 0;
        } catch (Exception unused) {
        }
    }

    private static long c() {
        if (f10489a != 0) {
            return f10489a;
        }
        long b = PreferenceUtils.b(SHApplication.getAppContext(), "msg_center_default_check_timestamp", 0);
        if (b == 0) {
            b = System.currentTimeMillis() / 1000;
            PreferenceUtils.a(SHApplication.getAppContext(), "msg_center_default_check_timestamp", b);
        }
        f10489a = b;
        return b;
    }

    public static Map<String, Integer> b() {
        int i;
        Set<String> keySet = AllTypeMsgManager.a().d().keySet();
        HashMap hashMap = new HashMap();
        Iterator<String> it = keySet.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            hashMap.put(it.next(), 0);
        }
        try {
            JSONArray jSONArray = new JSONArray(PreferenceUtils.a(MessageCenter.e, XMPConst.ai));
            if (jSONArray.length() == 0) {
                return hashMap;
            }
            for (i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                hashMap.put(optJSONObject.optString("type"), Integer.valueOf(optJSONObject.optInt("count")));
            }
            return hashMap;
        } catch (Exception unused) {
        }
    }

    public static void a(String str, int i) {
        try {
            JSONArray jSONArray = new JSONArray(PreferenceUtils.a(MessageCenter.e, XMPConst.ai));
            JSONObject jSONObject = null;
            int i2 = 0;
            while (true) {
                if (i2 < jSONArray.length()) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                    if (optJSONObject != null && TextUtils.equals(str, optJSONObject.optString("type"))) {
                        jSONObject = optJSONObject;
                        break;
                    }
                    i2++;
                } else {
                    break;
                }
            }
            if (jSONObject == null) {
                jSONObject = new JSONObject();
                jSONObject.put("type", str);
                jSONArray.put(jSONObject);
            }
            jSONObject.put("count", i);
            PreferenceUtils.c(SHApplication.getAppContext(), MessageCenter.e, jSONArray.toString());
        } catch (Exception unused) {
        }
    }

    public static void b(String str, long j) {
        try {
            JSONArray jSONArray = new JSONArray(PreferenceUtils.a(MessageCenter.e, XMPConst.ai));
            JSONObject jSONObject = null;
            int i = 0;
            while (true) {
                if (i < jSONArray.length()) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (optJSONObject != null && TextUtils.equals(str, optJSONObject.optString("type"))) {
                        jSONObject = optJSONObject;
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
            if (jSONObject == null) {
                jSONObject = new JSONObject();
                jSONObject.put("type", str);
                jSONArray.put(jSONObject);
            }
            if (!jSONObject.optBoolean("message_read", false)) {
                if (jSONObject.optLong("latest_check_timestamp") == 0) {
                    jSONObject.put("latest_check_timestamp", j);
                }
                PreferenceUtils.b(SHApplication.getAppContext(), MessageCenter.e, jSONArray.toString());
            }
        } catch (Exception unused) {
        }
    }

    public static void a(int i) {
        try {
            JSONArray jSONArray = new JSONArray(PreferenceUtils.a(MessageCenter.e, XMPConst.ai));
            JSONObject jSONObject = null;
            int i2 = 0;
            while (true) {
                if (i2 >= jSONArray.length()) {
                    break;
                }
                JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    if (TextUtils.equals(i + "", optJSONObject.optString("type"))) {
                        jSONObject = optJSONObject;
                        break;
                    }
                }
                i2++;
            }
            if (jSONObject == null) {
                jSONObject = new JSONObject();
                jSONObject.put("type", i + "");
                jSONArray.put(jSONObject);
            }
            jSONObject.put("message_read", true);
            PreferenceUtils.b(SHApplication.getAppContext(), MessageCenter.e, jSONArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void b(int i) {
        String a2 = PreferenceUtils.a(MessageCenter.e, "");
        if (!TextUtils.isEmpty(a2)) {
            try {
                JSONArray jSONArray = new JSONArray(a2);
                int i2 = 0;
                while (true) {
                    if (i2 >= jSONArray.length()) {
                        break;
                    }
                    JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                    if (TextUtils.equals(i + "", optJSONObject.optString("type"))) {
                        optJSONObject.put("count", 0);
                        break;
                    }
                    i2++;
                }
                PreferenceUtils.b(SHApplication.getAppContext(), MessageCenter.e, jSONArray.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
