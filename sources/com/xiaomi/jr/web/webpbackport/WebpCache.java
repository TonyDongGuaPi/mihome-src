package com.xiaomi.jr.web.webpbackport;

import android.content.Context;
import com.xiaomi.jr.common.utils.FileUtils;
import com.xiaomi.jr.common.utils.HashUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

class WebpCache {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11087a = "WebpCache";
    private static String b;
    private static String c;
    private JSONObject d;

    WebpCache(Context context) {
        try {
            b = context.getCacheDir().getCanonicalPath() + "/webp_bitmaps";
            c = b + "/manifest.json";
            new File(b).mkdirs();
            a();
        } catch (IOException unused) {
            MifiLog.b(f11087a, "fail to get webp_bitmaps cache dir");
        }
    }

    private void a() {
        if (new File(c).exists()) {
            try {
                this.d = new JSONObject(FileUtils.c(c));
            } catch (JSONException unused) {
                MifiLog.b(f11087a, "fail to init webp cache manifest object");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str, byte[] bArr, String str2) {
        String a2 = HashUtils.a(str);
        if (!FileUtils.a(b + File.separator + a2, bArr)) {
            return false;
        }
        if (this.d == null) {
            this.d = new JSONObject();
        }
        try {
            this.d.put(a2, str2);
            return FileUtils.b(c, this.d.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public String a(String str) {
        String a2 = HashUtils.a(str);
        if (this.d == null || !this.d.has(a2)) {
            return null;
        }
        return this.d.optString(a2);
    }

    /* access modifiers changed from: package-private */
    public ByteArrayInputStream b(String str) {
        String a2 = HashUtils.a(str);
        if (this.d == null || !this.d.has(a2)) {
            return null;
        }
        return FileUtils.d(b + File.separator + a2);
    }
}
