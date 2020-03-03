package com.xiaomi.assemble.control;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.xiaomi.mipush.sdk.AbstractPushManager;
import com.xiaomi.mipush.sdk.FCMPushHelper;
import java.io.IOException;

public class FCMPushManager implements AbstractPushManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9999a = "FCM-PUSH";
    private Context b;

    private FCMPushManager(Context context) {
        this.b = context;
    }

    public static FCMPushManager newInstance(Context context) {
        return new FCMPushManager(context);
    }

    public void a() {
        Log.i(f9999a, "register fcm");
        FirebaseApp.initializeApp(this.b);
        c();
    }

    public void b() {
        Log.i(f9999a, "unregister fcm");
        try {
            FirebaseInstanceId.getInstance().deleteInstanceId();
        } catch (IOException unused) {
        }
        FCMPushHelper.a(this.b);
    }

    private void c() {
        String token = FirebaseInstanceId.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            Log.i(f9999a, "directly register fcm success");
            FCMPushHelper.a(this.b, token);
        }
    }
}
