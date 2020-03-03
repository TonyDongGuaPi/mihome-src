package com.xiaomi.youpin.youpin_common;

import android.text.TextUtils;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_common.api.IStoreCallback;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

public class SharedDataManager {

    /* renamed from: a  reason: collision with root package name */
    private static String f23789a = "SharedDataManager";
    private static SharedDataManager b;
    private Map<String, String> c = new HashMap();
    private Map<String, Boolean> d = new HashMap();

    private SharedDataManager() {
    }

    public static synchronized SharedDataManager a() {
        SharedDataManager sharedDataManager;
        synchronized (SharedDataManager.class) {
            if (b == null) {
                b = new SharedDataManager();
            }
            sharedDataManager = b;
        }
        return sharedDataManager;
    }

    public synchronized void a(String str, String str2, IStoreCallback<Void> iStoreCallback) {
        String str3 = f23789a;
        LogUtils.d(str3, "setSharedValue " + str + ":" + str2);
        this.c.put(str, str2);
        if ("config".equals(str)) {
            try {
                b(str2);
            } catch (Exception unused) {
            }
        }
        if (iStoreCallback != null) {
            iStoreCallback.onSuccess(null);
        }
    }

    public synchronized void a(String str, IStoreCallback<String> iStoreCallback) {
        String str2 = this.c.get(str);
        if (iStoreCallback != null) {
            String str3 = f23789a;
            LogUtils.d(str3, "getSharedValue " + str + ":" + str2);
            iStoreCallback.onSuccess(str2);
        }
    }

    public boolean a(String str) {
        Boolean bool = this.d.get(str);
        return bool != null && bool.booleanValue();
    }

    private void b(String str) {
        if (TextUtils.isEmpty(str)) {
            this.d.clear();
            return;
        }
        try {
            JSONObject jSONObject = (JSONObject) new JSONObject(str).get("switch");
            Iterator<String> keys = jSONObject.keys();
            if (keys != null) {
                while (keys.hasNext()) {
                    String next = keys.next();
                    this.d.put(next, Boolean.valueOf(jSONObject.getBoolean(next)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
