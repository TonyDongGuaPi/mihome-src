package com.xiaomi.smarthome.framework.push.listener;

import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.framework.push.PushListener;
import com.xiaomi.smarthome.messagecenter.CommonMessageManager;
import com.xiaomi.smarthome.messagecenter.ui.MessageCenterActivity;
import org.json.JSONObject;

public class CommonPushListener extends PushListener {
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
            if (TextUtils.isEmpty(str)) {
                a();
            }
            if (!CommonMessageManager.a(new JSONObject(str).optJSONObject("params"))) {
                a();
            }
        } catch (Exception unused) {
            a();
        }
    }

    private void a() {
        OpenApi.a(MessageCenterActivity.class, new Bundle(), true, Constants.CALLIGRAPHY_TAG_PRICE);
    }
}
