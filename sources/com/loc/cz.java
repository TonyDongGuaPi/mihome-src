package com.loc;

import android.content.Context;
import android.provider.Settings;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.zip.Adler32;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class cz {
    private static final Object b = new Object();
    private static cz c = null;
    private static final String j = (".UTSystemConfig" + File.separator + "Global");

    /* renamed from: a  reason: collision with root package name */
    private Context f6550a = null;
    private String d = null;
    private da e = null;
    private String f = "xx_utdid_key";
    private String g = "xx_utdid_domain";
    private dz h = null;
    private dz i = null;
    private Pattern k = Pattern.compile("[^0-9a-zA-Z=/+]+");

    private cz(Context context) {
        this.f6550a = context;
        this.i = new dz(context, j, "Alvin2");
        this.h = new dz(context, ".DataStorage", "ContextData");
        this.e = new da();
        this.f = String.format("K_%d", new Object[]{Integer.valueOf(dw.b(this.f))});
        this.g = String.format("D_%d", new Object[]{Integer.valueOf(dw.b(this.g))});
    }

    public static cz a(Context context) {
        if (context != null && c == null) {
            synchronized (b) {
                if (c == null) {
                    c = new cz(context);
                }
            }
        }
        return c;
    }

    private void a(String str) {
        long j2;
        if (e(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (str.length() == 24 && this.i != null) {
                String a2 = this.i.a("UTDID");
                String a3 = this.i.a("EI");
                if (dw.a(a3)) {
                    a3 = dv.a(this.f6550a);
                }
                String a4 = this.i.a("SI");
                if (dw.a(a4)) {
                    a4 = dv.b(this.f6550a);
                }
                String a5 = this.i.a("DID");
                if (dw.a(a5)) {
                    a5 = a3;
                }
                if (a2 == null || !a2.equals(str)) {
                    cx cxVar = new cx();
                    cxVar.a(a3);
                    cxVar.b(a4);
                    cxVar.d(str);
                    cxVar.c(a5);
                    cxVar.b(System.currentTimeMillis());
                    this.i.a("UTDID", str);
                    this.i.a("EI", cxVar.b());
                    this.i.a("SI", cxVar.c());
                    this.i.a("DID", cxVar.d());
                    this.i.a("timestamp", cxVar.a());
                    dz dzVar = this.i;
                    String format = String.format("%s%s%s%s%s", new Object[]{cxVar.e(), cxVar.d(), Long.valueOf(cxVar.a()), cxVar.c(), cxVar.b()});
                    if (!dw.a(format)) {
                        Adler32 adler32 = new Adler32();
                        adler32.reset();
                        adler32.update(format.getBytes());
                        j2 = adler32.getValue();
                    } else {
                        j2 = 0;
                    }
                    dzVar.a("S", j2);
                    this.i.a();
                }
            }
        }
    }

    private void b(String str) {
        if (str != null && this.h != null && !str.equals(this.h.a(this.f))) {
            this.h.a(this.f, str);
            this.h.a();
        }
    }

    private final byte[] b() throws Exception {
        String str;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int nextInt = new Random().nextInt();
        byte[] a2 = du.a((int) (System.currentTimeMillis() / 1000));
        byte[] a3 = du.a(nextInt);
        byteArrayOutputStream.write(a2, 0, 4);
        byteArrayOutputStream.write(a3, 0, 4);
        byteArrayOutputStream.write(3);
        byteArrayOutputStream.write(0);
        try {
            str = dv.a(this.f6550a);
        } catch (Exception unused) {
            StringBuilder sb = new StringBuilder();
            sb.append(new Random().nextInt());
            str = sb.toString();
        }
        byteArrayOutputStream.write(du.a(dw.b(str)), 0, 4);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(new SecretKeySpec("d6fc3a4a06adbde89223bvefedc24fecde188aaa9161".getBytes(), instance.getAlgorithm()));
        byteArrayOutputStream.write(du.a(dw.b(dt.a(instance.doFinal(byteArray), 2))));
        return byteArrayOutputStream.toByteArray();
    }

    private void c(String str) {
        if (this.f6550a.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0 && e(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (24 == str.length() && !e(Settings.System.getString(this.f6550a.getContentResolver(), "mqBRboGZkQPcAkyk"))) {
                Settings.System.putString(this.f6550a.getContentResolver(), "mqBRboGZkQPcAkyk", str);
            }
        }
    }

    private void d(String str) {
        if (this.f6550a.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0 && str != null && !str.equals(Settings.System.getString(this.f6550a.getContentResolver(), "dxCRMxhQkdGePGnp"))) {
            Settings.System.putString(this.f6550a.getContentResolver(), "dxCRMxhQkdGePGnp", str);
        }
    }

    private boolean e(String str) {
        if (str != null) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            return 24 == str.length() && !this.k.matcher(str).find();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00aa A[Catch:{ Exception -> 0x0125 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00bf A[SYNTHETIC, Splitter:B:49:0x00bf] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.lang.String a() {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.String r0 = r5.d     // Catch:{ all -> 0x012b }
            if (r0 == 0) goto L_0x0009
            java.lang.String r0 = r5.d     // Catch:{ all -> 0x012b }
            monitor-exit(r5)
            return r0
        L_0x0009:
            android.content.Context r0 = r5.f6550a     // Catch:{ all -> 0x012b }
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch:{ all -> 0x012b }
            java.lang.String r1 = "mqBRboGZkQPcAkyk"
            java.lang.String r0 = android.provider.Settings.System.getString(r0, r1)     // Catch:{ all -> 0x012b }
            boolean r1 = r5.e(r0)     // Catch:{ all -> 0x012b }
            if (r1 == 0) goto L_0x001d
            monitor-exit(r5)
            return r0
        L_0x001d:
            com.loc.db r0 = new com.loc.db     // Catch:{ all -> 0x012b }
            r0.<init>()     // Catch:{ all -> 0x012b }
            r1 = 0
            android.content.Context r2 = r5.f6550a     // Catch:{ all -> 0x012b }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ all -> 0x012b }
            java.lang.String r3 = "dxCRMxhQkdGePGnp"
            java.lang.String r2 = android.provider.Settings.System.getString(r2, r3)     // Catch:{ all -> 0x012b }
            boolean r3 = com.loc.dw.a(r2)     // Catch:{ all -> 0x012b }
            if (r3 != 0) goto L_0x0086
            java.lang.String r3 = r0.b(r2)     // Catch:{ all -> 0x012b }
            boolean r4 = r5.e(r3)     // Catch:{ all -> 0x012b }
            if (r4 == 0) goto L_0x0044
            r5.c(r3)     // Catch:{ all -> 0x012b }
            monitor-exit(r5)
            return r3
        L_0x0044:
            java.lang.String r3 = r0.a(r2)     // Catch:{ all -> 0x012b }
            boolean r4 = r5.e(r3)     // Catch:{ all -> 0x012b }
            if (r4 == 0) goto L_0x0069
            com.loc.da r4 = r5.e     // Catch:{ all -> 0x012b }
            java.lang.String r3 = r4.a((java.lang.String) r3)     // Catch:{ all -> 0x012b }
            boolean r4 = com.loc.dw.a(r3)     // Catch:{ all -> 0x012b }
            if (r4 != 0) goto L_0x0069
            r5.d(r3)     // Catch:{ all -> 0x012b }
            android.content.Context r2 = r5.f6550a     // Catch:{ all -> 0x012b }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ all -> 0x012b }
            java.lang.String r3 = "dxCRMxhQkdGePGnp"
            java.lang.String r2 = android.provider.Settings.System.getString(r2, r3)     // Catch:{ all -> 0x012b }
        L_0x0069:
            com.loc.da r3 = r5.e     // Catch:{ all -> 0x012b }
            java.lang.String r3 = r3.b(r2)     // Catch:{ all -> 0x012b }
            boolean r4 = r5.e(r3)     // Catch:{ all -> 0x012b }
            if (r4 == 0) goto L_0x0087
            r5.d = r3     // Catch:{ all -> 0x012b }
            r5.a((java.lang.String) r3)     // Catch:{ all -> 0x012b }
            r5.b(r2)     // Catch:{ all -> 0x012b }
            java.lang.String r0 = r5.d     // Catch:{ all -> 0x012b }
            r5.c(r0)     // Catch:{ all -> 0x012b }
            java.lang.String r0 = r5.d     // Catch:{ all -> 0x012b }
            monitor-exit(r5)
            return r0
        L_0x0086:
            r1 = 1
        L_0x0087:
            com.loc.dz r2 = r5.i     // Catch:{ all -> 0x012b }
            r3 = 0
            if (r2 == 0) goto L_0x00a3
            com.loc.dz r2 = r5.i     // Catch:{ all -> 0x012b }
            java.lang.String r4 = "UTDID"
            java.lang.String r2 = r2.a(r4)     // Catch:{ all -> 0x012b }
            boolean r4 = com.loc.dw.a(r2)     // Catch:{ all -> 0x012b }
            if (r4 != 0) goto L_0x00a3
            com.loc.da r4 = r5.e     // Catch:{ all -> 0x012b }
            java.lang.String r4 = r4.a((java.lang.String) r2)     // Catch:{ all -> 0x012b }
            if (r4 == 0) goto L_0x00a3
            goto L_0x00a4
        L_0x00a3:
            r2 = r3
        L_0x00a4:
            boolean r4 = r5.e(r2)     // Catch:{ all -> 0x012b }
            if (r4 == 0) goto L_0x00bf
            com.loc.da r0 = r5.e     // Catch:{ all -> 0x012b }
            java.lang.String r0 = r0.a((java.lang.String) r2)     // Catch:{ all -> 0x012b }
            if (r1 == 0) goto L_0x00b5
            r5.d(r0)     // Catch:{ all -> 0x012b }
        L_0x00b5:
            r5.c(r2)     // Catch:{ all -> 0x012b }
            r5.b(r0)     // Catch:{ all -> 0x012b }
            r5.d = r2     // Catch:{ all -> 0x012b }
            monitor-exit(r5)
            return r2
        L_0x00bf:
            com.loc.dz r2 = r5.h     // Catch:{ all -> 0x012b }
            java.lang.String r4 = r5.f     // Catch:{ all -> 0x012b }
            java.lang.String r2 = r2.a(r4)     // Catch:{ all -> 0x012b }
            boolean r4 = com.loc.dw.a(r2)     // Catch:{ all -> 0x012b }
            if (r4 != 0) goto L_0x00ff
            java.lang.String r0 = r0.a(r2)     // Catch:{ all -> 0x012b }
            boolean r4 = r5.e(r0)     // Catch:{ all -> 0x012b }
            if (r4 != 0) goto L_0x00dd
            com.loc.da r0 = r5.e     // Catch:{ all -> 0x012b }
            java.lang.String r0 = r0.b(r2)     // Catch:{ all -> 0x012b }
        L_0x00dd:
            boolean r2 = r5.e(r0)     // Catch:{ all -> 0x012b }
            if (r2 == 0) goto L_0x00ff
            com.loc.da r2 = r5.e     // Catch:{ all -> 0x012b }
            java.lang.String r2 = r2.a((java.lang.String) r0)     // Catch:{ all -> 0x012b }
            boolean r4 = com.loc.dw.a(r0)     // Catch:{ all -> 0x012b }
            if (r4 != 0) goto L_0x00ff
            r5.d = r0     // Catch:{ all -> 0x012b }
            if (r1 == 0) goto L_0x00f6
            r5.d(r2)     // Catch:{ all -> 0x012b }
        L_0x00f6:
            java.lang.String r0 = r5.d     // Catch:{ all -> 0x012b }
            r5.a((java.lang.String) r0)     // Catch:{ all -> 0x012b }
            java.lang.String r0 = r5.d     // Catch:{ all -> 0x012b }
            monitor-exit(r5)
            return r0
        L_0x00ff:
            byte[] r0 = r5.b()     // Catch:{ Exception -> 0x0125 }
            if (r0 == 0) goto L_0x0129
            r2 = 2
            java.lang.String r2 = com.loc.dt.a(r0, r2)     // Catch:{ Exception -> 0x0125 }
            r5.d = r2     // Catch:{ Exception -> 0x0125 }
            java.lang.String r2 = r5.d     // Catch:{ Exception -> 0x0125 }
            r5.a((java.lang.String) r2)     // Catch:{ Exception -> 0x0125 }
            com.loc.da r2 = r5.e     // Catch:{ Exception -> 0x0125 }
            java.lang.String r0 = r2.a((byte[]) r0)     // Catch:{ Exception -> 0x0125 }
            if (r0 == 0) goto L_0x0121
            if (r1 == 0) goto L_0x011e
            r5.d(r0)     // Catch:{ Exception -> 0x0125 }
        L_0x011e:
            r5.b(r0)     // Catch:{ Exception -> 0x0125 }
        L_0x0121:
            java.lang.String r0 = r5.d     // Catch:{ Exception -> 0x0125 }
            monitor-exit(r5)
            return r0
        L_0x0125:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x012b }
        L_0x0129:
            monitor-exit(r5)
            return r3
        L_0x012b:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cz.a():java.lang.String");
    }
}
