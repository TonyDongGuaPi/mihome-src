package com.mi.mistatistic.sdk.controller.asyncjobs;

import android.text.TextUtils;
import com.mi.mistatistic.sdk.BuildSetting;
import com.mi.mistatistic.sdk.Constants;
import com.mi.mistatistic.sdk.controller.ApplicationContextHolder;
import com.mi.mistatistic.sdk.controller.AsyncJobDispatcher;
import com.mi.mistatistic.sdk.controller.Logger;
import com.mi.mistatistic.sdk.controller.NetworkUtils;
import com.mi.mistatistic.sdk.controller.PrefPersistUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class CorrectingServerTimeJob implements AsyncJobDispatcher.AsyncJob {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7354a = "http://agent.com/micra/state/server";
    public static String b = (Constants.f7315a + "micra/state/server");
    public static final String c = "server_delay";

    public void a() {
        try {
            b();
        } catch (Exception unused) {
        }
    }

    private void b() throws IOException {
        NetworkUtils.a(ApplicationContextHolder.a(), BuildSetting.a() ? f7354a : b, (Map<String, String>) new HashMap(), (NetworkUtils.IUploadCallback) new NetworkUtils.IUploadCallback() {
            public void a(String str) {
                try {
                    CorrectingServerTimeJob.this.a(str);
                } catch (Exception e) {
                    Logger.a("Session getServerTime Exception " + e.toString(), (Throwable) null);
                    e.printStackTrace();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str) throws JSONException {
        if (!TextUtils.isEmpty(str)) {
            try {
                String string = new JSONObject(str).getString("server_time");
                Logger.a("Session getServerTime time " + string, (Throwable) null);
                b(string);
            } catch (Exception unused) {
            }
        }
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            PrefPersistUtils.b(ApplicationContextHolder.a(), c, Long.valueOf(str).longValue() - System.currentTimeMillis());
        }
    }
}
