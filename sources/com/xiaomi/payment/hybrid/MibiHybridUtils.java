package com.xiaomi.payment.hybrid;

import android.app.Activity;
import android.content.Intent;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.MibiPadHybridDialogActivity;
import com.xiaomi.payment.MibiPhoneHybridActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class MibiHybridUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f12336a = "mibi://mibiapp";

    public static JSONObject a(Intent intent) {
        JSONObject jSONObject = new JSONObject();
        if (intent == null) {
            return jSONObject;
        }
        int intExtra = intent.getIntExtra("code", 0);
        String stringExtra = intent.getStringExtra("message");
        try {
            jSONObject.put("code", intExtra);
            jSONObject.put("message", stringExtra);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    public static void a(String str, Activity activity, int i) {
        Intent intent = new Intent();
        if (Utils.b()) {
            intent.setClass(activity, MibiPadHybridDialogActivity.class);
        } else {
            intent.setClass(activity, MibiPhoneHybridActivity.class);
        }
        intent.putExtra("com.miui.sdk.hybrid.extra.URL", str);
        activity.startActivityForResult(intent, i);
    }
}
