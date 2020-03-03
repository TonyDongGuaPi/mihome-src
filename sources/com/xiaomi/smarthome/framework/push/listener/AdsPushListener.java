package com.xiaomi.smarthome.framework.push.listener;

import android.content.Intent;
import android.net.Uri;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.navigate.SmartHomeLauncher;
import com.xiaomi.smarthome.framework.push.PushListener;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.youpin.youpin_common.statistic.StatManager;
import org.json.JSONException;
import org.json.JSONObject;

public class AdsPushListener extends PushListener {
    public boolean a(String str, String str2) {
        a(str2);
        return true;
    }

    public boolean b(String str, String str2) {
        a(str2);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        try {
            String optString = new JSONObject(str).optString("url");
            Miio.h("shop", "url: " + optString);
            if (!optString.contains("/main?")) {
                StatManager.a(optString);
            }
            Intent intent = new Intent(SHApplication.getAppContext(), SmartHomeLauncher.class);
            intent.setData(Uri.parse(optString));
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            SHApplication.getAppContext().startActivity(intent);
        } catch (Exception | JSONException unused) {
        }
    }
}
