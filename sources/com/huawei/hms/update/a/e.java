package com.huawei.hms.update.a;

import android.content.Context;
import com.huawei.hms.update.a.a.a;
import com.huawei.hms.update.a.a.c;
import com.huawei.hms.update.b.b;
import com.huawei.hms.update.b.d;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class e implements a {

    /* renamed from: a  reason: collision with root package name */
    private final Context f5906a;
    private final d b = new b();
    private com.huawei.hms.update.a.a.b c;
    private String d;
    private c e;

    public e(Context context) {
        this.f5906a = context.getApplicationContext();
    }

    private synchronized void b(com.huawei.hms.update.a.a.b bVar) {
        this.c = bVar;
    }

    private synchronized void a(int i) {
        if (this.c != null) {
            this.c.a(i, this.e);
        }
    }

    public Context a() {
        return this.f5906a;
    }

    public void b() {
        com.huawei.hms.support.log.a.b("OtaUpdateCheck", "Enter cancel.");
        b((com.huawei.hms.update.a.a.b) null);
        this.b.b();
    }

    public void a(com.huawei.hms.update.a.a.b bVar) {
        com.huawei.hms.c.a.a(bVar, "callback must not be null.");
        com.huawei.hms.support.log.a.b("OtaUpdateCheck", "Enter checkUpdate.");
        b(bVar);
        this.e = new c();
        this.e.a(this.f5906a);
        if (!this.e.a() || this.e.f5902a < 20502300) {
            try {
                c();
            } catch (com.huawei.hms.update.b.a unused) {
                com.huawei.hms.support.log.a.c("OtaUpdateCheck", "In checkUpdate, Canceled to download the update file.");
                a(1101);
            }
        } else {
            a(1000);
        }
    }

    public void a(com.huawei.hms.update.a.a.b bVar, c cVar) {
        throw new IllegalStateException("Not supported.");
    }

    private void c() throws com.huawei.hms.update.b.a {
        com.huawei.hms.support.log.a.b("OtaUpdateCheck", "Enter checkUpdate.");
        try {
            int d2 = d();
            if (d2 != 200) {
                com.huawei.hms.support.log.a.d("OtaUpdateCheck", "In CheckUpdateHelper.checkUpdate, Check whether has a new version, HTTP code: " + d2);
                a(1201);
            } else if (this.d == null) {
                a(1202);
            } else {
                int e2 = e();
                if (e2 != 200) {
                    com.huawei.hms.support.log.a.d("OtaUpdateCheck", "In CheckUpdateHelper.checkUpdate, Request the update-info of the new version, HTTP code: " + e2);
                    a(1201);
                    return;
                }
                if (this.e != null) {
                    if (this.e.f5902a >= 20502300) {
                        this.e.b(this.f5906a);
                        a(1000);
                        return;
                    }
                }
                a(1203);
            }
        } catch (IOException e3) {
            com.huawei.hms.support.log.a.d("OtaUpdateCheck", "In CheckUpdateHelper.checkUpdate, Failed to check update." + e3.getMessage());
            a(1201);
        }
    }

    private int d() throws IOException, com.huawei.hms.update.b.a {
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        a aVar = new a(this.f5906a);
        if (com.huawei.hms.support.log.a.a()) {
            com.huawei.hms.support.log.a.a("OtaUpdateCheck", "In doCheckUpdate, Check update params: " + aVar.toString());
        }
        try {
            byteArrayInputStream = new ByteArrayInputStream(aVar.a().toString().getBytes(Charset.defaultCharset()));
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    int a2 = this.b.a("https://query.hicloud.com/hwid/v2/CheckEx.action", byteArrayInputStream, byteArrayOutputStream);
                    if (a2 == 200) {
                        String str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                        if (com.huawei.hms.support.log.a.a()) {
                            com.huawei.hms.support.log.a.a("OtaUpdateCheck", "In CheckUpdateHelper.doCheckUpdate, Check update response: " + str);
                        }
                        this.d = b.a(str).a();
                    }
                    com.huawei.hms.c.c.a((OutputStream) byteArrayOutputStream);
                    com.huawei.hms.c.c.a((InputStream) byteArrayInputStream);
                    this.b.a();
                    return a2;
                } catch (Throwable th2) {
                    th = th2;
                    com.huawei.hms.c.c.a((OutputStream) byteArrayOutputStream);
                    com.huawei.hms.c.c.a((InputStream) byteArrayInputStream);
                    this.b.a();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                byteArrayOutputStream = null;
                com.huawei.hms.c.c.a((OutputStream) byteArrayOutputStream);
                com.huawei.hms.c.c.a((InputStream) byteArrayInputStream);
                this.b.a();
                throw th;
            }
        } catch (Throwable th4) {
            byteArrayInputStream = null;
            th = th4;
            byteArrayOutputStream = null;
            com.huawei.hms.c.c.a((OutputStream) byteArrayOutputStream);
            com.huawei.hms.c.c.a((InputStream) byteArrayInputStream);
            this.b.a();
            throw th;
        }
    }

    private int e() throws IOException, com.huawei.hms.update.b.a {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                int a2 = this.b.a(this.d + "full/filelist.xml", byteArrayOutputStream);
                if (a2 == 200) {
                    String str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                    if (com.huawei.hms.support.log.a.a()) {
                        com.huawei.hms.support.log.a.a("OtaUpdateCheck", "In doGetFilelist, Check update response: " + str);
                    }
                    d a3 = d.a(str);
                    this.e = new c(a3.d(), this.d + "full" + "/" + a3.a(), a3.b(), a3.c());
                }
                com.huawei.hms.c.c.a((OutputStream) byteArrayOutputStream);
                this.b.a();
                return a2;
            } catch (Throwable th2) {
                th = th2;
                com.huawei.hms.c.c.a((OutputStream) byteArrayOutputStream);
                this.b.a();
                throw th;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            byteArrayOutputStream = null;
            th = th4;
            com.huawei.hms.c.c.a((OutputStream) byteArrayOutputStream);
            this.b.a();
            throw th;
        }
    }
}
