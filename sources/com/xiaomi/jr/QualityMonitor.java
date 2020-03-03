package com.xiaomi.jr;

import com.xiaomi.jr.common.utils.MifiLog;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QualityMonitor {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1378a = "QualityMonitor";
    private static QualityApi b;
    private static boolean c;
    private static final Callback<Void> d = new Callback<Void>() {
        public void onFailure(Call<Void> call, Throwable th) {
        }

        public void onResponse(Call<Void> call, Response<Void> response) {
        }
    };

    public static void a(QualityApi qualityApi, boolean z) {
        b = qualityApi;
        c = z;
    }

    public static void a(String str, String str2, String str3) {
        a(false, str, str2, "log", str3);
    }

    public static void a(String str, String str2, String... strArr) {
        a(true, str, str2, strArr);
    }

    private static void a(boolean z, String str, String str2, String... strArr) {
        if (c) {
            String a2 = a(strArr);
            if (z) {
                MifiLog.e(str2, a2);
            } else {
                MifiLog.c(str2, a2);
            }
            if (a2 != null) {
                b.a(1, z, str, str2, a2, System.currentTimeMillis()).enqueue(d);
            }
        }
    }

    private static String a(String... strArr) {
        JSONObject jSONObject = new JSONObject();
        if (strArr != null && strArr.length > 0) {
            if (strArr.length % 2 == 0) {
                int i = 0;
                while (i < strArr.length) {
                    try {
                        jSONObject.put(strArr[i], strArr[i + 1]);
                        i += 2;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            } else {
                MifiLog.e(f1378a, "data list not in k-v pairs");
                return null;
            }
        }
        return jSONObject.toString();
    }
}
