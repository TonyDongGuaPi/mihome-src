package com.xiaomi.stat.c;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.xiaomi.stat.ak;
import com.xiaomi.stat.b;
import com.xiaomi.stat.d.c;
import com.xiaomi.stat.d.d;
import com.xiaomi.stat.d.e;
import com.xiaomi.stat.d.j;
import com.xiaomi.stat.d.k;
import com.xiaomi.stat.d.l;
import com.xiaomi.stat.d.m;
import com.xiaomi.stat.d.r;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class i {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23042a = "3.0";
    private static final String b = "UploaderEngine";
    private static final String c = "code";
    private static final String d = "UTF-8";
    private static final String e = "mistat";
    private static final String f = "uploader";
    private static final String g = "3.0.12";
    private static final String h = "Android";
    private static final int i = 200;
    private static final int j = 1;
    private static final int k = -1;
    private static final int l = 3;
    private static volatile i m;
    private final byte[] n = new byte[0];
    private FileLock o;
    private FileChannel p;
    private g q;
    private a r;

    private int a(int i2) {
        if (i2 == 1) {
            return -1;
        }
        return i2 == 3 ? 0 : 1;
    }

    public static i a() {
        if (m == null) {
            synchronized (i.class) {
                if (m == null) {
                    m = new i();
                }
            }
        }
        return m;
    }

    private i() {
        e();
    }

    private void e() {
        HandlerThread handlerThread = new HandlerThread("mi_analytics_uploader_worker");
        handlerThread.start();
        this.r = new a(handlerThread.getLooper());
        this.q = new g(handlerThread.getLooper());
    }

    public void b() {
        this.q.b();
        c();
    }

    public void c() {
        if (!l.a()) {
            k.b(b, " postToServer network is not connected ");
        } else if (!b.a() || !b.b()) {
            k.b(b, " postToServer statistic disable or network disable access! ");
        } else if (!b.B()) {
            k.b(b, " postToServer can not upload data because of configuration!");
        } else if (!b.B()) {
            k.b("UploaderEngine postToServer can not upload data because of configuration!");
        } else {
            Message obtain = Message.obtain();
            obtain.what = 1;
            a(obtain);
        }
    }

    private void a(Message message) {
        synchronized (this.n) {
            if (this.r == null || this.q == null) {
                e();
            }
            this.r.sendMessage(message);
        }
    }

    public static byte[] a(String str) {
        GZIPOutputStream gZIPOutputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(str.getBytes("UTF-8").length);
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            } catch (Exception e2) {
                e = e2;
                gZIPOutputStream = null;
                try {
                    k.e(b, " zipData failed! " + e.toString());
                    j.a((OutputStream) byteArrayOutputStream);
                    j.a((OutputStream) gZIPOutputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    j.a((OutputStream) byteArrayOutputStream);
                    j.a((OutputStream) gZIPOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                gZIPOutputStream = null;
                j.a((OutputStream) byteArrayOutputStream);
                j.a((OutputStream) gZIPOutputStream);
                throw th;
            }
            try {
                gZIPOutputStream.write(str.getBytes("UTF-8"));
                gZIPOutputStream.finish();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                j.a((OutputStream) byteArrayOutputStream);
                j.a((OutputStream) gZIPOutputStream);
                return byteArray;
            } catch (Exception e3) {
                e = e3;
                k.e(b, " zipData failed! " + e.toString());
                j.a((OutputStream) byteArrayOutputStream);
                j.a((OutputStream) gZIPOutputStream);
                return null;
            }
        } catch (Exception e4) {
            e = e4;
            byteArrayOutputStream = null;
            gZIPOutputStream = null;
            k.e(b, " zipData failed! " + e.toString());
            j.a((OutputStream) byteArrayOutputStream);
            j.a((OutputStream) gZIPOutputStream);
            return null;
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            gZIPOutputStream = null;
            j.a((OutputStream) byteArrayOutputStream);
            j.a((OutputStream) gZIPOutputStream);
            throw th;
        }
    }

    private byte[] a(byte[] bArr) {
        return com.xiaomi.stat.b.i.a().a(bArr);
    }

    private String b(byte[] bArr) {
        return d.a(bArr);
    }

    private class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                i.this.f();
            }
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        if (h()) {
            if (b.e()) {
                b(true);
                b(false);
            } else {
                a(c(false), com.xiaomi.stat.b.d.a().c());
            }
            i();
        }
    }

    private void b(boolean z) {
        a(c(z), com.xiaomi.stat.b.d.a().a(z));
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00cd A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.xiaomi.stat.a.b[] r10, java.lang.String r11) {
        /*
            r9 = this;
            int r0 = r10.length
            if (r0 != 0) goto L_0x000b
            java.lang.String r10 = "UploaderEngine"
            java.lang.String r11 = "privacy policy or network state not matched"
            com.xiaomi.stat.d.k.e(r10, r11)
            return
        L_0x000b:
            com.xiaomi.stat.a.c r0 = com.xiaomi.stat.a.c.a()
            com.xiaomi.stat.a.k r0 = r0.a((com.xiaomi.stat.a.b[]) r10)
            java.util.concurrent.atomic.AtomicInteger r1 = new java.util.concurrent.atomic.AtomicInteger
            r1.<init>()
            r2 = 1
            if (r0 == 0) goto L_0x001e
            boolean r3 = r0.c
            goto L_0x001f
        L_0x001e:
            r3 = 1
        L_0x001f:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "UploaderEngine"
            r4.append(r5)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.xiaomi.stat.d.k.b(r4)
            r4 = 0
            r5 = r3
            r3 = 0
        L_0x0036:
            if (r0 == 0) goto L_0x00d2
            java.util.ArrayList<java.lang.Long> r3 = r0.b
            org.json.JSONArray r0 = r0.f23008a
            java.lang.String r0 = r9.a((org.json.JSONArray) r0, (java.lang.String) r11)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r6 = "UploaderEngine"
            java.lang.String r7 = " payload:"
            com.xiaomi.stat.d.k.a((java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r0)     // Catch:{ Exception -> 0x0085 }
            byte[] r0 = a((java.lang.String) r0)     // Catch:{ Exception -> 0x0085 }
            byte[] r0 = r9.a((byte[]) r0)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r0 = r9.b((byte[]) r0)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r6 = "UploaderEngine"
            java.lang.String r7 = " encodePayload "
            com.xiaomi.stat.d.k.a((java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r0)     // Catch:{ Exception -> 0x0085 }
            com.xiaomi.stat.b.g r6 = com.xiaomi.stat.b.g.a()     // Catch:{ Exception -> 0x0085 }
            java.lang.String r6 = r6.c()     // Catch:{ Exception -> 0x0085 }
            boolean r7 = com.xiaomi.stat.d.k.b()     // Catch:{ Exception -> 0x0085 }
            if (r7 == 0) goto L_0x006a
            java.lang.String r6 = "http://test.data.mistat.xiaomi.srv/mistats/v3"
        L_0x006a:
            java.util.HashMap r0 = r9.c((java.lang.String) r0)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r0 = com.xiaomi.stat.c.c.a((java.lang.String) r6, (java.util.Map<java.lang.String, java.lang.String>) r0, (boolean) r2)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r6 = "UploaderEngine"
            java.lang.String r7 = " sendDataToServer response: "
            com.xiaomi.stat.d.k.a((java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r0)     // Catch:{ Exception -> 0x0085 }
            boolean r6 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x0085 }
            if (r6 == 0) goto L_0x0080
            goto L_0x0085
        L_0x0080:
            boolean r0 = r9.b((java.lang.String) r0)     // Catch:{ Exception -> 0x0085 }
            goto L_0x0086
        L_0x0085:
            r0 = 0
        L_0x0086:
            if (r0 == 0) goto L_0x0090
            com.xiaomi.stat.a.c r6 = com.xiaomi.stat.a.c.a()
            r6.a((java.util.ArrayList<java.lang.Long>) r3)
            goto L_0x0093
        L_0x0090:
            r1.addAndGet(r2)
        L_0x0093:
            java.lang.String r3 = "UploaderEngine"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = " deleteData= "
            r6.append(r7)
            r6.append(r0)
            java.lang.String r7 = " retryCount.get()= "
            r6.append(r7)
            int r7 = r1.get()
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            com.xiaomi.stat.d.k.b(r3, r6)
            if (r5 != 0) goto L_0x00d3
            if (r0 != 0) goto L_0x00c1
            int r3 = r1.get()
            r6 = 3
            if (r3 <= r6) goto L_0x00c1
            goto L_0x00d3
        L_0x00c1:
            com.xiaomi.stat.a.c r3 = com.xiaomi.stat.a.c.a()
            com.xiaomi.stat.a.k r3 = r3.a((com.xiaomi.stat.a.b[]) r10)
            if (r3 == 0) goto L_0x00cd
            boolean r5 = r3.c
        L_0x00cd:
            r8 = r3
            r3 = r0
            r0 = r8
            goto L_0x0036
        L_0x00d2:
            r0 = r3
        L_0x00d3:
            com.xiaomi.stat.c.g r10 = r9.q
            if (r10 == 0) goto L_0x00dc
            com.xiaomi.stat.c.g r10 = r9.q
            r10.b(r0)
        L_0x00dc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.stat.c.i.a(com.xiaomi.stat.a.b[], java.lang.String):void");
    }

    private boolean b(String str) {
        try {
            int optInt = new JSONObject(str).optInt("code");
            if (optInt != 200) {
                if (!(optInt == 1002 || optInt == 1004 || optInt == 1005 || optInt == 1006 || optInt == 1007)) {
                    if (optInt != 1011) {
                        if (optInt == 2002 || optInt == 1012) {
                            com.xiaomi.stat.b.i.a().a(true);
                            com.xiaomi.stat.b.d.a().b();
                        }
                    }
                }
                com.xiaomi.stat.b.i.a().a(true);
                com.xiaomi.stat.b.d.a().b();
                return false;
            }
            return true;
        } catch (Exception e2) {
            k.d(b, "parseUploadingResult exception ", e2);
            return false;
        }
    }

    private com.xiaomi.stat.a.b[] c(boolean z) {
        ArrayList<String> g2 = g();
        int size = g2.size();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < size; i2++) {
            String str = g2.get(i2);
            int a2 = a(new f(str, z).a());
            if (a2 != -1) {
                arrayList.add(new com.xiaomi.stat.a.b(str, a2, z));
            }
        }
        com.xiaomi.stat.a.b d2 = d(z);
        if (d2 != null) {
            arrayList.add(d2);
        }
        return (com.xiaomi.stat.a.b[]) arrayList.toArray(new com.xiaomi.stat.a.b[arrayList.size()]);
    }

    private com.xiaomi.stat.a.b d(boolean z) {
        int a2 = new f(z).a();
        k.b(b, " createMainAppFilter: " + a2);
        int a3 = a(a2);
        if (a3 != -1) {
            return new com.xiaomi.stat.a.b((String) null, a3, z);
        }
        return null;
    }

    private ArrayList<String> g() {
        String[] o2 = b.o();
        int length = o2 != null ? o2.length : 0;
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < length; i2++) {
            if (!TextUtils.isEmpty(o2[i2])) {
                arrayList.add(o2[i2]);
            }
        }
        return arrayList;
    }

    private HashMap<String, String> c(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("ai", ak.b());
        hashMap.put("sv", "3.0.12");
        hashMap.put(com.xiaomi.stat.d.c, f23042a);
        hashMap.put(com.xiaomi.stat.d.d, m.g());
        hashMap.put("p", str);
        hashMap.put(com.xiaomi.stat.d.ak, com.xiaomi.stat.b.i.a().c());
        hashMap.put("sid", com.xiaomi.stat.b.i.a().b());
        return hashMap;
    }

    private String a(JSONArray jSONArray, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", str);
            a(str, jSONObject);
            jSONObject.put(com.xiaomi.stat.d.I, e.d());
            jSONObject.put("rc", m.h());
            jSONObject.put("av", c.b());
            jSONObject.put("ac", b.t());
            jSONObject.put("os", "Android");
            jSONObject.put(com.xiaomi.stat.d.Z, m.a(ak.a()));
            jSONObject.put(com.xiaomi.stat.d.m, this.q != null ? this.q.a() : 0);
            jSONObject.put("st", String.valueOf(r.b()));
            jSONObject.put(com.xiaomi.stat.d.o, m.e());
            jSONObject.put(com.xiaomi.stat.d.p, a.a(ak.b()));
            String[] o2 = b.o();
            if (o2 != null && o2.length > 0) {
                jSONObject.put(com.xiaomi.stat.d.v, a(o2));
            }
            jSONObject.put(com.xiaomi.stat.d.q, m.d());
            jSONObject.put("n", l.b(ak.a()));
            jSONObject.put(com.xiaomi.stat.d.t, b.h());
            jSONObject.put(com.xiaomi.stat.d.u, jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    private void a(String str, JSONObject jSONObject) {
        try {
            if (!b.e() && TextUtils.isEmpty(str)) {
                Context a2 = ak.a();
                jSONObject.put(com.xiaomi.stat.d.C, e.b(a2));
                jSONObject.put(com.xiaomi.stat.d.J, e.k(a2));
                jSONObject.put(com.xiaomi.stat.d.L, e.n(a2));
                jSONObject.put(com.xiaomi.stat.d.O, e.q(a2));
                jSONObject.put("ai", e.p(a2));
            }
        } catch (Exception unused) {
        }
    }

    private JSONArray a(String[] strArr) {
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(strArr[i2], a.a(strArr[i2]));
                jSONArray.put(jSONObject);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return jSONArray;
    }

    public synchronized void d() {
        if (this.q != null) {
            this.q.c();
        }
    }

    public void a(boolean z) {
        if (this.q != null) {
            this.q.a(z);
        }
    }

    private boolean h() {
        File file = new File(ak.a().getFilesDir(), "mistat");
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            this.p = new FileOutputStream(new File(file, f)).getChannel();
            try {
                this.o = this.p.tryLock();
                if (this.o != null) {
                    k.c(b, " acquire lock for uploader");
                    if (this.o == null) {
                        try {
                            this.p.close();
                            this.p = null;
                        } catch (Exception unused) {
                        }
                    }
                    return true;
                }
                k.c(b, " acquire lock for uploader failed");
                if (this.o == null) {
                    try {
                        this.p.close();
                        this.p = null;
                    } catch (Exception unused2) {
                    }
                }
                return false;
            } catch (Exception e2) {
                k.c(b, " acquire lock for uploader failed with " + e2);
                if (this.o == null) {
                    try {
                        this.p.close();
                        this.p = null;
                    } catch (Exception unused3) {
                    }
                }
                return false;
            } catch (Throwable th) {
                if (this.o == null) {
                    try {
                        this.p.close();
                        this.p = null;
                    } catch (Exception unused4) {
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e3) {
            k.c(b, " acquire lock for uploader failed with " + e3);
            return false;
        }
    }

    private void i() {
        try {
            if (this.o != null) {
                this.o.release();
                this.o = null;
            }
            if (this.p != null) {
                this.p.close();
                this.p = null;
            }
            k.c(b, " releaseLock lock for uploader");
        } catch (IOException e2) {
            k.c(b, " releaseLock lock for uploader failed with " + e2);
        }
    }
}
