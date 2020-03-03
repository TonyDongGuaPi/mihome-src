package com.xiaomi.jr.antifraud;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.jr.antifraud.por.MaskedPhoneNumHelper;
import com.xiaomi.jr.antifraud.por.PorData;
import com.xiaomi.jr.http.MifiHttpCallback;
import com.xiaomi.jr.http.MifiHttpManager;
import com.xiaomi.jr.http.model.MiFiResponse;

public class AntifraudManager {

    /* renamed from: a  reason: collision with root package name */
    private Context f1384a;
    private String b;
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public boolean e;
    private AntiFraudApi f;
    private MaskedPhoneNumHelper.PhoneNumUpdateListener g = new MaskedPhoneNumHelper.PhoneNumUpdateListener() {
        public void a() {
            boolean unused = AntifraudManager.this.c = true;
            if (!TextUtils.isEmpty(AntifraudManager.this.d) && !AntifraudManager.this.e) {
                AntifraudManager.this.b(AntifraudManager.this.d);
                String unused2 = AntifraudManager.this.d = null;
                boolean unused3 = AntifraudManager.this.e = true;
            }
        }
    };

    private static class SingletonCreator {

        /* renamed from: a  reason: collision with root package name */
        public static final AntifraudManager f10298a = new AntifraudManager();

        private SingletonCreator() {
        }
    }

    public static AntifraudManager a() {
        return SingletonCreator.f10298a;
    }

    public void a(Context context, String str) {
        this.f1384a = context;
        this.b = str;
        MaskedPhoneNumHelper.a(context, str, this.g);
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!this.c || this.e) {
            this.d = str;
            return;
        }
        b(str);
        this.e = true;
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        if (this.f == null) {
            this.f = (AntiFraudApi) MifiHttpManager.a().a(AntiFraudApi.class);
        }
        this.f.a(str, new PorData(this.f1384a).b().c().toString()).enqueue(new MifiHttpCallback<MiFiResponse<Void>>((Activity) null) {
            public void a(MiFiResponse<Void> miFiResponse) {
            }
        });
    }

    public Context b() {
        return this.f1384a;
    }

    public String c() {
        return this.b;
    }
}
