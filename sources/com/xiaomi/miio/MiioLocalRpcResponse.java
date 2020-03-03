package com.xiaomi.miio;

import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import org.json.JSONObject;

public interface MiioLocalRpcResponse {
    void onResponse(String str);

    public static abstract class MiioLocalRpcResponseSimple implements MiioLocalRpcResponse {
        public abstract void a(int i, String str, Throwable th);

        public abstract void a(JSONObject jSONObject);

        public void onResponse(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int optInt = jSONObject.optInt("code", -1);
                if (optInt == 0) {
                    a(jSONObject.optJSONObject("result"));
                } else {
                    a(optInt, str, (Throwable) null);
                }
            } catch (Throwable th) {
                Log.e(AppMeasurement.Param.FATAL, "MiioLocalRpcResponseSimple.onResponse", th);
                a(Integer.MIN_VALUE, (String) null, th);
            }
        }
    }
}
