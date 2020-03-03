package com.ta.utdid2.device;

import android.content.Context;
import android.os.Binder;
import android.provider.Settings;
import android.text.TextUtils;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.ta.utdid2.a.a.b;
import com.ta.utdid2.a.a.d;
import com.ta.utdid2.a.a.e;
import com.ta.utdid2.a.a.f;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class c {

    /* renamed from: a  reason: collision with root package name */
    private static c f8973a = null;
    private static final Object e = new Object();
    private static final String j = (".UTSystemConfig" + File.separator + "Global");

    /* renamed from: a  reason: collision with other field name */
    private com.ta.utdid2.b.a.c f48a = null;

    /* renamed from: a  reason: collision with other field name */
    private d f49a = null;
    private com.ta.utdid2.b.a.c b = null;

    /* renamed from: b  reason: collision with other field name */
    private Pattern f50b = Pattern.compile("[^0-9a-zA-Z=/+]+");
    private String g = null;
    private String h = "xx_utdid_key";
    private String i = "xx_utdid_domain";
    private Context mContext = null;

    private c(Context context) {
        this.mContext = context;
        this.b = new com.ta.utdid2.b.a.c(context, j, "Alvin2", false, true);
        this.f48a = new com.ta.utdid2.b.a.c(context, ".DataStorage", "ContextData", false, true);
        this.f49a = new d();
        this.h = String.format("K_%d", new Object[]{Integer.valueOf(f.hashCode(this.h))});
        this.i = String.format("D_%d", new Object[]{Integer.valueOf(f.hashCode(this.i))});
    }

    /* renamed from: c  reason: collision with other method in class */
    private void m58c() {
        if (this.b != null) {
            if (f.isEmpty(this.b.getString("UTDID2"))) {
                String string = this.b.getString("UTDID");
                if (!f.isEmpty(string)) {
                    d(string);
                }
            }
            boolean z = false;
            if (!f.isEmpty(this.b.getString("DID"))) {
                this.b.remove("DID");
                z = true;
            }
            if (!f.isEmpty(this.b.getString("EI"))) {
                this.b.remove("EI");
                z = true;
            }
            if (!f.isEmpty(this.b.getString("SI"))) {
                this.b.remove("SI");
                z = true;
            }
            if (z) {
                this.b.commit();
            }
        }
    }

    public static c a(Context context) {
        if (context != null && f8973a == null) {
            synchronized (e) {
                if (f8973a == null) {
                    f8973a = new c(context);
                    f8973a.c();
                }
            }
        }
        return f8973a;
    }

    private void d(String str) {
        if (a(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (str.length() == 24 && this.b != null) {
                this.b.putString("UTDID2", str);
                this.b.commit();
            }
        }
    }

    private void e(String str) {
        if (str != null && this.f48a != null && !str.equals(this.f48a.getString(this.h))) {
            this.f48a.putString(this.h, str);
            this.f48a.commit();
        }
    }

    private void f(String str) {
        if (e() && a(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (24 == str.length()) {
                String str2 = null;
                try {
                    str2 = Settings.System.getString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk");
                } catch (Exception unused) {
                }
                if (!a(str2)) {
                    try {
                        Settings.System.putString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk", str);
                    } catch (Exception unused2) {
                    }
                }
            }
        }
    }

    private void g(String str) {
        String str2;
        try {
            str2 = Settings.System.getString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp");
        } catch (Exception unused) {
            str2 = null;
        }
        if (!str.equals(str2)) {
            try {
                Settings.System.putString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp", str);
            } catch (Exception unused2) {
            }
        }
    }

    private void h(String str) {
        if (e() && str != null) {
            g(str);
        }
    }

    private String c() {
        if (this.b == null) {
            return null;
        }
        String string = this.b.getString("UTDID2");
        if (f.isEmpty(string) || this.f49a.c(string) == null) {
            return null;
        }
        return string;
    }

    private boolean a(String str) {
        if (str != null) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            return 24 == str.length() && !this.f50b.matcher(str).find();
        }
    }

    public synchronized String getValue() {
        if (this.g != null) {
            return this.g;
        }
        return d();
    }

    public synchronized String d() {
        this.g = e();
        if (!TextUtils.isEmpty(this.g)) {
            return this.g;
        }
        try {
            byte[] b2 = b();
            if (b2 != null) {
                this.g = b.encodeToString(b2, 2);
                d(this.g);
                String c = this.f49a.c(b2);
                if (c != null) {
                    h(c);
                    e(c);
                }
                return this.g;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00e6, code lost:
        return null;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x0068 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0010 */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0018 A[SYNTHETIC, Splitter:B:10:0x0018] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0016 A[DONT_GENERATE] */
    /* renamed from: e  reason: collision with other method in class */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String m59e() {
        /*
            r6 = this;
            monitor-enter(r6)
            java.lang.String r0 = ""
            android.content.Context r1 = r6.mContext     // Catch:{ Exception -> 0x0010 }
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ Exception -> 0x0010 }
            java.lang.String r2 = "mqBRboGZkQPcAkyk"
            java.lang.String r1 = android.provider.Settings.System.getString(r1, r2)     // Catch:{ Exception -> 0x0010 }
            r0 = r1
        L_0x0010:
            boolean r1 = r6.a((java.lang.String) r0)     // Catch:{ all -> 0x00e7 }
            if (r1 == 0) goto L_0x0018
            monitor-exit(r6)
            return r0
        L_0x0018:
            com.ta.utdid2.device.e r0 = new com.ta.utdid2.device.e     // Catch:{ all -> 0x00e7 }
            r0.<init>()     // Catch:{ all -> 0x00e7 }
            r1 = 0
            r2 = 0
            android.content.Context r3 = r6.mContext     // Catch:{ Exception -> 0x002c }
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch:{ Exception -> 0x002c }
            java.lang.String r4 = "dxCRMxhQkdGePGnp"
            java.lang.String r3 = android.provider.Settings.System.getString(r3, r4)     // Catch:{ Exception -> 0x002c }
            goto L_0x002d
        L_0x002c:
            r3 = r2
        L_0x002d:
            boolean r4 = com.ta.utdid2.a.a.f.isEmpty(r3)     // Catch:{ all -> 0x00e7 }
            if (r4 != 0) goto L_0x0085
            java.lang.String r4 = r0.e(r3)     // Catch:{ all -> 0x00e7 }
            boolean r5 = r6.a((java.lang.String) r4)     // Catch:{ all -> 0x00e7 }
            if (r5 == 0) goto L_0x0042
            r6.f(r4)     // Catch:{ all -> 0x00e7 }
            monitor-exit(r6)
            return r4
        L_0x0042:
            java.lang.String r4 = r0.d(r3)     // Catch:{ all -> 0x00e7 }
            boolean r5 = r6.a((java.lang.String) r4)     // Catch:{ all -> 0x00e7 }
            if (r5 == 0) goto L_0x0068
            com.ta.utdid2.device.d r5 = r6.f49a     // Catch:{ all -> 0x00e7 }
            java.lang.String r4 = r5.c((java.lang.String) r4)     // Catch:{ all -> 0x00e7 }
            boolean r5 = com.ta.utdid2.a.a.f.isEmpty(r4)     // Catch:{ all -> 0x00e7 }
            if (r5 != 0) goto L_0x0068
            r6.h(r4)     // Catch:{ all -> 0x00e7 }
            android.content.Context r4 = r6.mContext     // Catch:{ Exception -> 0x0068 }
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch:{ Exception -> 0x0068 }
            java.lang.String r5 = "dxCRMxhQkdGePGnp"
            java.lang.String r4 = android.provider.Settings.System.getString(r4, r5)     // Catch:{ Exception -> 0x0068 }
            r3 = r4
        L_0x0068:
            com.ta.utdid2.device.d r4 = r6.f49a     // Catch:{ all -> 0x00e7 }
            java.lang.String r4 = r4.d(r3)     // Catch:{ all -> 0x00e7 }
            boolean r5 = r6.a((java.lang.String) r4)     // Catch:{ all -> 0x00e7 }
            if (r5 == 0) goto L_0x0086
            r6.g = r4     // Catch:{ all -> 0x00e7 }
            r6.d(r4)     // Catch:{ all -> 0x00e7 }
            r6.e(r3)     // Catch:{ all -> 0x00e7 }
            java.lang.String r0 = r6.g     // Catch:{ all -> 0x00e7 }
            r6.f(r0)     // Catch:{ all -> 0x00e7 }
            java.lang.String r0 = r6.g     // Catch:{ all -> 0x00e7 }
            monitor-exit(r6)
            return r0
        L_0x0085:
            r1 = 1
        L_0x0086:
            java.lang.String r3 = r6.c()     // Catch:{ all -> 0x00e7 }
            boolean r4 = r6.a((java.lang.String) r3)     // Catch:{ all -> 0x00e7 }
            if (r4 == 0) goto L_0x00a5
            com.ta.utdid2.device.d r0 = r6.f49a     // Catch:{ all -> 0x00e7 }
            java.lang.String r0 = r0.c((java.lang.String) r3)     // Catch:{ all -> 0x00e7 }
            if (r1 == 0) goto L_0x009b
            r6.h(r0)     // Catch:{ all -> 0x00e7 }
        L_0x009b:
            r6.f(r3)     // Catch:{ all -> 0x00e7 }
            r6.e(r0)     // Catch:{ all -> 0x00e7 }
            r6.g = r3     // Catch:{ all -> 0x00e7 }
            monitor-exit(r6)
            return r3
        L_0x00a5:
            com.ta.utdid2.b.a.c r3 = r6.f48a     // Catch:{ all -> 0x00e7 }
            java.lang.String r4 = r6.h     // Catch:{ all -> 0x00e7 }
            java.lang.String r3 = r3.getString(r4)     // Catch:{ all -> 0x00e7 }
            boolean r4 = com.ta.utdid2.a.a.f.isEmpty(r3)     // Catch:{ all -> 0x00e7 }
            if (r4 != 0) goto L_0x00e5
            java.lang.String r0 = r0.d(r3)     // Catch:{ all -> 0x00e7 }
            boolean r4 = r6.a((java.lang.String) r0)     // Catch:{ all -> 0x00e7 }
            if (r4 != 0) goto L_0x00c3
            com.ta.utdid2.device.d r0 = r6.f49a     // Catch:{ all -> 0x00e7 }
            java.lang.String r0 = r0.d(r3)     // Catch:{ all -> 0x00e7 }
        L_0x00c3:
            boolean r3 = r6.a((java.lang.String) r0)     // Catch:{ all -> 0x00e7 }
            if (r3 == 0) goto L_0x00e5
            com.ta.utdid2.device.d r3 = r6.f49a     // Catch:{ all -> 0x00e7 }
            java.lang.String r3 = r3.c((java.lang.String) r0)     // Catch:{ all -> 0x00e7 }
            boolean r4 = com.ta.utdid2.a.a.f.isEmpty(r0)     // Catch:{ all -> 0x00e7 }
            if (r4 != 0) goto L_0x00e5
            r6.g = r0     // Catch:{ all -> 0x00e7 }
            if (r1 == 0) goto L_0x00dc
            r6.h(r3)     // Catch:{ all -> 0x00e7 }
        L_0x00dc:
            java.lang.String r0 = r6.g     // Catch:{ all -> 0x00e7 }
            r6.d(r0)     // Catch:{ all -> 0x00e7 }
            java.lang.String r0 = r6.g     // Catch:{ all -> 0x00e7 }
            monitor-exit(r6)
            return r0
        L_0x00e5:
            monitor-exit(r6)
            return r2
        L_0x00e7:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.device.c.m59e():java.lang.String");
    }

    private byte[] b() throws Exception {
        String str;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int nextInt = new Random().nextInt();
        byte[] bytes = com.ta.utdid2.a.a.c.getBytes((int) (System.currentTimeMillis() / 1000));
        byte[] bytes2 = com.ta.utdid2.a.a.c.getBytes(nextInt);
        byteArrayOutputStream.write(bytes, 0, 4);
        byteArrayOutputStream.write(bytes2, 0, 4);
        byteArrayOutputStream.write(3);
        byteArrayOutputStream.write(0);
        try {
            str = d.getImei(this.mContext);
        } catch (Exception unused) {
            str = "" + new Random().nextInt();
        }
        byteArrayOutputStream.write(com.ta.utdid2.a.a.c.getBytes(f.hashCode(str)), 0, 4);
        byteArrayOutputStream.write(com.ta.utdid2.a.a.c.getBytes(f.hashCode(b(byteArrayOutputStream.toByteArray()))));
        return byteArrayOutputStream.toByteArray();
    }

    public static String b(byte[] bArr) throws Exception {
        byte[] bArr2 = {Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ELECTRONIC_USE_TYPE, -33, Constants.TagName.ELECTRONIC_PRICE_FAVOURABLE, -54, -31, Constants.TagName.QUERY_DATA_SORT_TYPE, -11, 11, Constants.TagName.APP_TYPE, ScriptToolsConst.TagName.CommandSingle, -17, Constants.TagName.INVOICE_TOKEN_OBJECT, 64, 23, ScriptToolsConst.TagName.ScriptDown, -126, -82, Constants.TagName.STATION_ENAME, Constants.TagName.ELECTRONIC_TYPE_ID, Constants.TagName.ELECTRONIC_USE_TYPE, -16, Constants.TagName.PRODUCT_CODE, 49, -30, 9, -39, Framer.ENTER_FRAME_PREFIX, -80, Constants.TagName.STATION_CONFIG_VERSION, Constants.TagName.APP_TYPE, Constants.TagName.PAY_CHANNEL_MIN, TarConstants.R, 30, Constants.TagName.ACTIVITY_TOTAL, 64, Constants.TagName.PRODUCT_INFO, 74, -49, Constants.TagName.PAY_ORDER_ID, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, -38, ScriptToolsConst.TagName.ResponseMultiple};
        Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(new SecretKeySpec(e.a(bArr2), instance.getAlgorithm()));
        return b.encodeToString(instance.doFinal(bArr), 2);
    }

    private boolean e() {
        return this.mContext.checkPermission("android.permission.WRITE_SETTINGS", Binder.getCallingPid(), Binder.getCallingUid()) == 0;
    }
}
