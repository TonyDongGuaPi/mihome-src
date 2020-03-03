package com.xiaomi.assemble.control;

import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.xiaomi.mipush.sdk.FCMPushHelper;

public class MiFireBaseInstanceIdService extends FirebaseInstanceIdService {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10005a = "FCM-PUSH";

    public void onTokenRefresh() {
        try {
            super.onTokenRefresh();
            Log.i(f10005a, "onTokenRefresh");
            String token = FirebaseInstanceId.getInstance().getToken();
            if (!FCMPushHelper.b(getApplicationContext())) {
                Log.i(f10005a, "fcm switch is closed but get refreshed token");
            } else if (!TextUtils.isEmpty(token)) {
                Log.i(f10005a, "get fcm token success! ====> " + token);
                FCMPushHelper.a(getApplicationContext(), token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
