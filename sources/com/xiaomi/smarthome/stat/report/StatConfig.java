package com.xiaomi.smarthome.stat.report;

import android.content.Context;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.io.Streams;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.library.common.util.IOUtils;
import java.io.InputStream;
import org.json.JSONObject;

public class StatConfig {

    /* renamed from: a  reason: collision with root package name */
    private static StatConfig f22760a = new StatConfig();
    private JSONObject b = null;

    public static StatConfig a() {
        return f22760a;
    }

    private StatConfig() {
        b();
    }

    private JSONObject b() {
        InputStream inputStream;
        Exception e;
        if (this.b != null) {
            return this.b;
        }
        Context f = FrameManager.f();
        if (f == null) {
            return null;
        }
        try {
            inputStream = f.getResources().getAssets().open("stat_config.json");
            try {
                this.b = new JSONObject(new String(Streams.b(inputStream)));
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            inputStream = null;
            try {
                e.printStackTrace();
                IOUtils.a(inputStream);
                return this.b;
            } catch (Throwable th) {
                th = th;
                IOUtils.a(inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            IOUtils.a(inputStream);
            throw th;
        }
        IOUtils.a(inputStream);
        return this.b;
    }

    public String a(String str, String str2) {
        JSONObject jSONObject;
        JSONObject b2 = b();
        if (b2 == null) {
            return str2;
        }
        try {
            JSONObject jSONObject2 = b2.getJSONObject("key_map");
            if (jSONObject2 == null || (jSONObject = jSONObject2.getJSONObject(str)) == null) {
                return str2;
            }
            String string = jSONObject.getString(str2);
            return (string == null || string.length() < 1) ? str2 : string;
        } catch (Exception unused) {
            return str2;
        }
    }
}
