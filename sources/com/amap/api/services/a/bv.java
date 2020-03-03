package com.amap.api.services.a;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

public class bv {

    /* renamed from: a  reason: collision with root package name */
    private volatile b f4358a = new b();
    private cv b = new cv("HttpsDecisionUtil");

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        static bv f4359a = new bv();
    }

    public static bv a() {
        return a.f4359a;
    }

    public void a(Context context) {
        if (this.f4358a == null) {
            this.f4358a = new b();
        }
        this.f4358a.a(c(context));
        this.f4358a.a(context);
    }

    public void b(Context context) {
        d(context);
    }

    public void a(boolean z) {
        if (this.f4358a == null) {
            this.f4358a = new b();
        }
        this.f4358a.b(z);
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, boolean z) {
        if (this.f4358a == null) {
            this.f4358a = new b();
        }
        b(context, z);
        this.f4358a.a(z);
    }

    public boolean b() {
        if (this.f4358a == null) {
            this.f4358a = new b();
        }
        return this.f4358a.a();
    }

    private void b(Context context, boolean z) {
        this.b.a(context, "isTargetRequired", z);
    }

    private boolean c(Context context) {
        return this.b.b(context, "isTargetRequired", true);
    }

    private void d(Context context) {
        this.b.a(context, "isTargetRequired", true);
    }

    private static class b {

        /* renamed from: a  reason: collision with root package name */
        protected boolean f4360a;
        private int b;
        private final boolean c;
        private boolean d;

        private b() {
            this.b = 0;
            this.f4360a = true;
            this.c = true;
            this.d = false;
        }

        public void a(Context context) {
            if (context != null && this.b <= 0 && Build.VERSION.SDK_INT >= 4) {
                this.b = context.getApplicationContext().getApplicationInfo().targetSdkVersion;
            }
        }

        public void a(boolean z) {
            this.f4360a = z;
        }

        public void b(boolean z) {
            this.d = z;
        }

        private int b() {
            if (this.b <= 0) {
                return 28;
            }
            return this.b;
        }

        private boolean c() {
            return b() >= 28;
        }

        private boolean d() {
            return Build.VERSION.SDK_INT >= 28;
        }

        private boolean e() {
            return d() && (!this.f4360a || c());
        }

        public boolean a() {
            return this.d || e();
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("https")) {
            return str;
        }
        try {
            Uri.Builder buildUpon = Uri.parse(str).buildUpon();
            buildUpon.scheme("https");
            return buildUpon.build().toString();
        } catch (Throwable unused) {
            return str;
        }
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT == 19;
    }

    public boolean b(boolean z) {
        if (c()) {
            return false;
        }
        if (z || b()) {
            return true;
        }
        return false;
    }
}
