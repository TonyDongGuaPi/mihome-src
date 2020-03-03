package com.xiaomi.smarthome.framework.push.listener;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.navigate.SmartHomeLauncher;
import com.xiaomi.smarthome.framework.push.PushListener;
import org.json.JSONException;
import org.json.JSONObject;

public class CommonUriPushListener extends PushListener {
    public boolean a(String str, String str2) {
        a(str2);
        return true;
    }

    public boolean b(String str, String str2) {
        a(str2);
        return true;
    }

    private void a(String str) {
        try {
            String optString = new JSONObject(str).optString("url");
            if (!TextUtils.isEmpty(optString)) {
                Intent intent = new Intent(SHApplication.getAppContext(), SmartHomeLauncher.class);
                intent.setData(Uri.parse(optString));
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                SHApplication.getAppContext().startActivity(intent);
            }
        } catch (Exception | JSONException unused) {
        }
    }
}
