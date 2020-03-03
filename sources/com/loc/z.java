package com.loc;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

public final class z {

    /* renamed from: a  reason: collision with root package name */
    private volatile b f6651a = new b((byte) 0);
    private bb b = new bb("HttpsDecisionUtil");

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        static z f6652a = new z();
    }

    private static class b {

        /* renamed from: a  reason: collision with root package name */
        protected boolean f6653a;
        private int b;
        private final boolean c;
        private boolean d;

        private b() {
            this.b = 0;
            this.f6653a = true;
            this.c = true;
            this.d = false;
        }

        /* synthetic */ b(byte b2) {
            this();
        }

        public final void a(Context context) {
            if (context != null && this.b <= 0 && Build.VERSION.SDK_INT >= 4) {
                this.b = context.getApplicationContext().getApplicationInfo().targetSdkVersion;
            }
        }

        public final void a(boolean z) {
            this.f6653a = z;
        }

        /* JADX WARNING: Removed duplicated region for block: B:23:0x0031 A[RETURN] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean a() {
            /*
                r5 = this;
                boolean r0 = r5.d
                r1 = 1
                if (r0 != 0) goto L_0x0032
                int r0 = android.os.Build.VERSION.SDK_INT
                r2 = 28
                r3 = 0
                if (r0 < r2) goto L_0x000e
                r0 = 1
                goto L_0x000f
            L_0x000e:
                r0 = 0
            L_0x000f:
                boolean r4 = r5.f6653a
                if (r4 == 0) goto L_0x0026
                int r4 = r5.b
                if (r4 > 0) goto L_0x001a
                r4 = 28
                goto L_0x001c
            L_0x001a:
                int r4 = r5.b
            L_0x001c:
                if (r4 < r2) goto L_0x0020
                r2 = 1
                goto L_0x0021
            L_0x0020:
                r2 = 0
            L_0x0021:
                if (r2 == 0) goto L_0x0024
                goto L_0x0026
            L_0x0024:
                r2 = 0
                goto L_0x0027
            L_0x0026:
                r2 = 1
            L_0x0027:
                if (r0 == 0) goto L_0x002d
                if (r2 == 0) goto L_0x002d
                r0 = 1
                goto L_0x002e
            L_0x002d:
                r0 = 0
            L_0x002e:
                if (r0 == 0) goto L_0x0031
                goto L_0x0032
            L_0x0031:
                return r3
            L_0x0032:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loc.z.b.a():boolean");
        }

        public final void b(boolean z) {
            this.d = z;
        }
    }

    public static z a() {
        return a.f6652a;
    }

    public static String a(String str) {
        if (!TextUtils.isEmpty(str) && !str.startsWith("https")) {
            try {
                Uri.Builder buildUpon = Uri.parse(str).buildUpon();
                buildUpon.scheme("https");
                return buildUpon.build().toString();
            } catch (Throwable unused) {
            }
        }
        return str;
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT == 19;
    }

    public final void a(Context context) {
        if (this.f6651a == null) {
            this.f6651a = new b((byte) 0);
        }
        this.f6651a.a(this.b.a(context, "isTargetRequired"));
        this.f6651a.a(context);
    }

    /* access modifiers changed from: package-private */
    public final void a(Context context, boolean z) {
        if (this.f6651a == null) {
            this.f6651a = new b((byte) 0);
        }
        this.b.a(context, "isTargetRequired", z);
        this.f6651a.a(z);
    }

    public final void a(boolean z) {
        if (this.f6651a == null) {
            this.f6651a = new b((byte) 0);
        }
        this.f6651a.b(z);
    }

    public final void b(Context context) {
        this.b.a(context, "isTargetRequired", true);
    }

    public final boolean b(boolean z) {
        if (b()) {
            return false;
        }
        if (z) {
            return true;
        }
        if (this.f6651a == null) {
            this.f6651a = new b((byte) 0);
        }
        return this.f6651a.a();
    }
}
