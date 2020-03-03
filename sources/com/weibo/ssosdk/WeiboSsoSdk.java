package com.weibo.ssosdk;

import android.text.TextUtils;
import com.xiaomi.stat.a.j;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONObject;

public class WeiboSsoSdk {

    /* renamed from: a  reason: collision with root package name */
    public static final String f9856a = "1.0";
    private static final String b = "https://login.sina.com.cn/visitor/signin";
    private static final int c = 1;
    private static final int d = 2;
    private static final String e = "WeiboSsoSdk";
    private static final int g = 1;
    private static final String h = "weibo_sso_sdk_aid";
    private static final String i = "weibo_sso_sdk_init";
    private static WeiboSsoSdk j;
    private static WeiboSsoSdkConfig k;
    private volatile ReentrantLock f = new ReentrantLock(true);
    /* access modifiers changed from: private */
    public boolean l = true;
    /* access modifiers changed from: private */
    public VisitorLoginInfo m;
    private int n;

    private static void b(String str) {
    }

    private static void c(String str) {
    }

    private native String riseWind(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i2, int i3);

    static {
        System.loadLibrary("wind");
    }

    private WeiboSsoSdk() throws Exception {
        if (k == null || !k.c()) {
            throw new Exception("config error");
        }
        this.n = 0;
        new Thread(new Runnable() {
            public void run() {
                String str;
                while (true) {
                    try {
                        Thread.sleep(86400000);
                        if (WeiboSsoSdk.this.m == null || TextUtils.isEmpty(WeiboSsoSdk.this.m.a())) {
                            str = WeiboSsoSdk.this.d();
                        } else {
                            str = WeiboSsoSdk.this.m.a();
                        }
                        WeiboSsoSdk.a().a(str, 2);
                    } catch (Exception unused) {
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                String str;
                try {
                    Thread.sleep(60000);
                    if (WeiboSsoSdk.this.l) {
                        if (WeiboSsoSdk.this.m == null || TextUtils.isEmpty(WeiboSsoSdk.this.m.a())) {
                            str = WeiboSsoSdk.this.d();
                        } else {
                            str = WeiboSsoSdk.this.m.a();
                        }
                        WeiboSsoSdk.this.a(str, 2);
                    }
                } catch (Exception unused) {
                }
            }
        }).start();
    }

    public static synchronized boolean a(WeiboSsoSdkConfig weiboSsoSdkConfig) {
        synchronized (WeiboSsoSdk.class) {
            if (weiboSsoSdkConfig == null) {
                return false;
            }
            if (!weiboSsoSdkConfig.c()) {
                return false;
            }
            if (k != null) {
                return false;
            }
            k = (WeiboSsoSdkConfig) weiboSsoSdkConfig.clone();
            MfpBuilder.a(k.a());
            return true;
        }
    }

    public static synchronized WeiboSsoSdk a() throws Exception {
        WeiboSsoSdk weiboSsoSdk;
        synchronized (WeiboSsoSdk.class) {
            if (j == null) {
                j = new WeiboSsoSdk();
            }
            weiboSsoSdk = j;
        }
        return weiboSsoSdk;
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            k.a(str);
            String b2 = this.m.b();
            if (TextUtils.isEmpty(b2) || !b2.equals(str)) {
                new Thread(new Runnable() {
                    public void run() {
                        String str;
                        if (WeiboSsoSdk.this.m == null || TextUtils.isEmpty(WeiboSsoSdk.this.m.a())) {
                            str = WeiboSsoSdk.this.d();
                        } else {
                            str = WeiboSsoSdk.this.m.a();
                        }
                        try {
                            WeiboSsoSdk.this.a(str, 2);
                        } catch (Exception unused) {
                        }
                    }
                }).start();
            }
        }
    }

    public static final class VisitorLoginInfo {

        /* renamed from: a  reason: collision with root package name */
        private String f9862a;
        private String b;

        public String a() {
            return this.f9862a;
        }

        public String b() {
            return this.b;
        }

        static VisitorLoginInfo a(String str) throws Exception {
            VisitorLoginInfo visitorLoginInfo = new VisitorLoginInfo();
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("retcode", "");
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                if (!optString.equals("20000000") || jSONObject2 == null) {
                    throw new Exception("error： " + optString + " msg:" + jSONObject.optString("msg", ""));
                }
                visitorLoginInfo.f9862a = jSONObject2.optString("aid", "");
                visitorLoginInfo.b = jSONObject2.optString(j.i, "");
                return visitorLoginInfo;
            } catch (Exception e) {
                throw e;
            }
        }

        /* access modifiers changed from: package-private */
        public VisitorLoginInfo c() {
            VisitorLoginInfo visitorLoginInfo = new VisitorLoginInfo();
            visitorLoginInfo.f9862a = this.f9862a;
            visitorLoginInfo.b = this.b;
            return visitorLoginInfo;
        }
    }

    private String d(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(b).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setReadTimeout(3000);
            httpURLConnection.setConnectTimeout(1000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(str.getBytes());
            outputStream.flush();
            if (httpURLConnection.getResponseCode() != 200) {
                return null;
            }
            InputStream inputStream = httpURLConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    inputStream.close();
                    byteArrayOutputStream.close();
                    return new String(byteArrayOutputStream.toByteArray());
                }
            }
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, int i2) throws Exception {
        String str2;
        if (!TextUtils.isEmpty(k.f(false))) {
            if (!this.f.tryLock()) {
                this.f.lock();
                this.f.unlock();
                return;
            }
            this.l = false;
            String b2 = MfpBuilder.b(k.a());
            try {
                str2 = URLEncoder.encode(str, "utf-8");
            } catch (UnsupportedEncodingException unused) {
                str2 = "";
            }
            String d2 = d(riseWind(k.f(true), k.a().getPackageName(), str2, b2, k.e(true), k.d(true), k.c(true), k.b(true), k.g(true), k.a(true), i2, this.n));
            this.n++;
            if (d2 != null) {
                try {
                    VisitorLoginInfo a2 = VisitorLoginInfo.a(d2);
                    if (a2 != null && !TextUtils.isEmpty(a2.a())) {
                        e(a2.a());
                    }
                    if (i2 == 1) {
                        this.m = a2;
                    }
                    this.f.unlock();
                } catch (Exception e2) {
                    Exception exc = e2;
                    this.f.unlock();
                    throw exc;
                }
            } else {
                this.f.unlock();
                throw new Exception("network error.");
            }
        }
    }

    public VisitorLoginInfo b() throws Exception {
        if (this.m == null) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        WeiboSsoSdk.this.a("", 1);
                    } catch (Exception unused) {
                    }
                }
            });
            thread.start();
            thread.join();
        }
        if (this.m != null) {
            return this.m;
        }
        throw new Exception("visitor login failed");
    }

    public String c() throws Exception {
        String d2 = d();
        if (!TextUtils.isEmpty(d2)) {
            return d2;
        }
        if (this.m == null || TextUtils.isEmpty(this.m.a())) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        WeiboSsoSdk.this.a("", 1);
                    } catch (Exception unused) {
                    }
                }
            });
            thread.start();
            thread.join();
        }
        if (this.m != null) {
            return this.m.a();
        }
        throw new Exception("visitor login failed");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0023 A[SYNTHETIC, Splitter:B:13:0x0023] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002a A[SYNTHETIC, Splitter:B:21:0x002a] */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String d() {
        /*
            r3 = this;
            r0 = 1
            r1 = 0
            java.io.File r0 = r3.a((int) r0)     // Catch:{ Exception -> 0x0027, all -> 0x001f }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0027, all -> 0x001f }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0027, all -> 0x001f }
            int r0 = r2.available()     // Catch:{ Exception -> 0x0028, all -> 0x001d }
            byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x0028, all -> 0x001d }
            r2.read(r0)     // Catch:{ Exception -> 0x0028, all -> 0x001d }
            java.lang.String r1 = new java.lang.String     // Catch:{ Exception -> 0x0028, all -> 0x001d }
            r1.<init>(r0)     // Catch:{ Exception -> 0x0028, all -> 0x001d }
            r2.close()     // Catch:{ IOException -> 0x001c }
        L_0x001c:
            return r1
        L_0x001d:
            r0 = move-exception
            goto L_0x0021
        L_0x001f:
            r0 = move-exception
            r2 = r1
        L_0x0021:
            if (r2 == 0) goto L_0x0026
            r2.close()     // Catch:{ IOException -> 0x0026 }
        L_0x0026:
            throw r0
        L_0x0027:
            r2 = r1
        L_0x0028:
            if (r2 == 0) goto L_0x002d
            r2.close()     // Catch:{ IOException -> 0x002d }
        L_0x002d:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.weibo.ssosdk.WeiboSsoSdk.d():java.lang.String");
    }

    private File a(int i2) {
        File filesDir = k.a().getFilesDir();
        return new File(filesDir, h + i2);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:13|14|(0)|21|22) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x002a */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0027 A[SYNTHETIC, Splitter:B:19:0x0027] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x002d A[SYNTHETIC, Splitter:B:25:0x002d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void e(java.lang.String r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r3)
            return
        L_0x0009:
            r0 = 0
            r1 = 1
            java.io.File r1 = r3.a((int) r1)     // Catch:{ Exception -> 0x002b, all -> 0x0024 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x002b, all -> 0x0024 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x002b, all -> 0x0024 }
            byte[] r4 = r4.getBytes()     // Catch:{ Exception -> 0x0022, all -> 0x001f }
            r2.write(r4)     // Catch:{ Exception -> 0x0022, all -> 0x001f }
            r2.close()     // Catch:{ IOException -> 0x0030 }
            goto L_0x0030
        L_0x001f:
            r4 = move-exception
            r0 = r2
            goto L_0x0025
        L_0x0022:
            r0 = r2
            goto L_0x002b
        L_0x0024:
            r4 = move-exception
        L_0x0025:
            if (r0 == 0) goto L_0x002a
            r0.close()     // Catch:{ IOException -> 0x002a }
        L_0x002a:
            throw r4     // Catch:{ all -> 0x0032 }
        L_0x002b:
            if (r0 == 0) goto L_0x0030
            r0.close()     // Catch:{ IOException -> 0x0030 }
        L_0x0030:
            monitor-exit(r3)
            return
        L_0x0032:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.weibo.ssosdk.WeiboSsoSdk.e(java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0023 A[SYNTHETIC, Splitter:B:13:0x0023] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002a A[SYNTHETIC, Splitter:B:21:0x002a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String e() {
        /*
            r4 = this;
            r0 = 0
            java.io.File r1 = r4.f()     // Catch:{ Exception -> 0x0027, all -> 0x001e }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0027, all -> 0x001e }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0027, all -> 0x001e }
            int r1 = r2.available()     // Catch:{ Exception -> 0x0028, all -> 0x001c }
            byte[] r1 = new byte[r1]     // Catch:{ Exception -> 0x0028, all -> 0x001c }
            r2.read(r1)     // Catch:{ Exception -> 0x0028, all -> 0x001c }
            java.lang.String r3 = new java.lang.String     // Catch:{ Exception -> 0x0028, all -> 0x001c }
            r3.<init>(r1)     // Catch:{ Exception -> 0x0028, all -> 0x001c }
            r2.close()     // Catch:{ IOException -> 0x001b }
        L_0x001b:
            return r3
        L_0x001c:
            r0 = move-exception
            goto L_0x0021
        L_0x001e:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x0021:
            if (r2 == 0) goto L_0x0026
            r2.close()     // Catch:{ IOException -> 0x0026 }
        L_0x0026:
            throw r0
        L_0x0027:
            r2 = r0
        L_0x0028:
            if (r2 == 0) goto L_0x002d
            r2.close()     // Catch:{ IOException -> 0x002d }
        L_0x002d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.weibo.ssosdk.WeiboSsoSdk.e():java.lang.String");
    }

    private File f() {
        return new File(k.a().getFilesDir(), i);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:13|14|(0)|21|22) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0029 */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0026 A[SYNTHETIC, Splitter:B:19:0x0026] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x002c A[SYNTHETIC, Splitter:B:25:0x002c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void f(java.lang.String r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0031 }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r3)
            return
        L_0x0009:
            r0 = 0
            java.io.File r1 = r3.f()     // Catch:{ Exception -> 0x002a, all -> 0x0023 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x002a, all -> 0x0023 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x002a, all -> 0x0023 }
            byte[] r4 = r4.getBytes()     // Catch:{ Exception -> 0x0021, all -> 0x001e }
            r2.write(r4)     // Catch:{ Exception -> 0x0021, all -> 0x001e }
            r2.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x002f
        L_0x001e:
            r4 = move-exception
            r0 = r2
            goto L_0x0024
        L_0x0021:
            r0 = r2
            goto L_0x002a
        L_0x0023:
            r4 = move-exception
        L_0x0024:
            if (r0 == 0) goto L_0x0029
            r0.close()     // Catch:{ IOException -> 0x0029 }
        L_0x0029:
            throw r4     // Catch:{ all -> 0x0031 }
        L_0x002a:
            if (r0 == 0) goto L_0x002f
            r0.close()     // Catch:{ IOException -> 0x002f }
        L_0x002f:
            monitor-exit(r3)
            return
        L_0x0031:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.weibo.ssosdk.WeiboSsoSdk.f(java.lang.String):void");
    }
}
