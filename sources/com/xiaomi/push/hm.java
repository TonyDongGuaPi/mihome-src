package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.bf;
import com.xiaomi.push.service.bg;
import java.util.HashMap;
import java.util.Map;

public class hm {

    /* renamed from: a  reason: collision with root package name */
    private static hm f12779a;
    private final Context b;
    private Map<String, hn> c = new HashMap();

    private hm(Context context) {
        this.b = context;
    }

    public static hm a(Context context) {
        if (context == null) {
            b.d("[TinyDataManager]:mContext is null, TinyDataManager.getInstance(Context) failed.");
            return null;
        }
        if (f12779a == null) {
            synchronized (hm.class) {
                if (f12779a == null) {
                    f12779a = new hm(context);
                }
            }
        }
        return f12779a;
    }

    private boolean a(String str, String str2, String str3, String str4, long j, String str5) {
        hs hsVar = new hs();
        hsVar.d(str3);
        hsVar.c(str4);
        hsVar.a(j);
        hsVar.b(str5);
        hsVar.a(true);
        hsVar.a("push_sdk_channel");
        hsVar.e(str2);
        b.a("TinyData TinyDataManager.upload item:" + hsVar.d() + "   ts:" + System.currentTimeMillis());
        return a(hsVar, str);
    }

    /* access modifiers changed from: package-private */
    public hn a() {
        hn hnVar = this.c.get("UPLOADER_PUSH_CHANNEL");
        if (hnVar != null) {
            return hnVar;
        }
        hn hnVar2 = this.c.get("UPLOADER_HTTP");
        if (hnVar2 != null) {
            return hnVar2;
        }
        return null;
    }

    public void a(hn hnVar, String str) {
        if (hnVar == null) {
            b.d("[TinyDataManager]: please do not add null mUploader to TinyDataManager.");
        } else if (TextUtils.isEmpty(str)) {
            b.d("[TinyDataManager]: can not add a provider from unkown resource.");
        } else {
            b().put(str, hnVar);
        }
    }

    public boolean a(hs hsVar, String str) {
        if (TextUtils.isEmpty(str)) {
            b.a("pkgName is null or empty, upload ClientUploadDataItem failed.");
            return false;
        } else if (bf.a(hsVar, false)) {
            return false;
        } else {
            if (TextUtils.isEmpty(hsVar.d())) {
                hsVar.f(bf.a());
            }
            hsVar.g(str);
            bg.a(this.b, hsVar);
            return true;
        }
    }

    public boolean a(String str, String str2, long j, String str3) {
        return a(this.b.getPackageName(), this.b.getPackageName(), str, str2, j, str3);
    }

    /* access modifiers changed from: package-private */
    public Map<String, hn> b() {
        return this.c;
    }
}
