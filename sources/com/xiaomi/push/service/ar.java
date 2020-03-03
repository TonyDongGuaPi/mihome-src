package com.xiaomi.push.service;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import com.alipay.mobile.common.logging.api.ProcessInfo;
import com.daimajia.androidanimations.library.BuildConfig;
import com.xiaomi.push.az;
import com.xiaomi.push.cx;
import com.xiaomi.push.da;
import com.xiaomi.push.db;
import com.xiaomi.push.el;
import com.xiaomi.push.em;
import com.xiaomi.push.fj;
import com.xiaomi.push.fu;
import com.xiaomi.push.gw;
import com.xiaomi.push.hg;
import com.xiaomi.push.hi;
import com.xiaomi.push.service.bb;
import com.xiaomi.push.t;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class ar extends bb.a implements db.a {

    /* renamed from: a  reason: collision with root package name */
    private long f12889a;

    /* renamed from: a  reason: collision with other field name */
    private XMPushService f299a;

    static class a implements db.b {
        a() {
        }

        public String a(String str) {
            Uri.Builder buildUpon = Uri.parse(str).buildUpon();
            buildUpon.appendQueryParameter("sdkver", String.valueOf(39));
            buildUpon.appendQueryParameter("osver", String.valueOf(Build.VERSION.SDK_INT));
            buildUpon.appendQueryParameter("os", gw.a(Build.MODEL + ":" + Build.VERSION.INCREMENTAL));
            buildUpon.appendQueryParameter("mi", String.valueOf(t.b()));
            String builder = buildUpon.toString();
            com.xiaomi.channel.commonutils.logger.b.c("fetch bucket from : " + builder);
            URL url = new URL(builder);
            int port = url.getPort() == -1 ? 80 : url.getPort();
            try {
                long currentTimeMillis = System.currentTimeMillis();
                String a2 = az.a(t.a(), url);
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                hi.a(url.getHost() + ":" + port, (int) currentTimeMillis2, (Exception) null);
                return a2;
            } catch (IOException e) {
                hi.a(url.getHost() + ":" + port, -1, e);
                throw e;
            }
        }
    }

    static class b extends db {
        protected b(Context context, da daVar, db.b bVar, String str) {
            super(context, daVar, bVar, str);
        }

        /* access modifiers changed from: protected */
        public String a(ArrayList<String> arrayList, String str, String str2, boolean z) {
            try {
                if (hg.a().c()) {
                    str2 = bb.a();
                }
                return super.a(arrayList, str, str2, z);
            } catch (IOException e) {
                hi.a(0, fj.GSLB_ERR.a(), 1, (String) null, az.c(f12685a) ? 1 : 0);
                throw e;
            }
        }
    }

    ar(XMPushService xMPushService) {
        this.f299a = xMPushService;
    }

    public static void a(XMPushService xMPushService) {
        ar arVar = new ar(xMPushService);
        bb.a().a((bb.a) arVar);
        synchronized (db.class) {
            db.a((db.a) arVar);
            db.a(xMPushService, (da) null, new a(), "0", ProcessInfo.ALIAS_PUSH, BuildConfig.VERSION_NAME);
        }
    }

    public db a(Context context, da daVar, db.b bVar, String str) {
        return new b(context, daVar, bVar, str);
    }

    public void a(el.a aVar) {
    }

    public void a(em.b bVar) {
        cx b2;
        if (bVar.e() && bVar.d() && System.currentTimeMillis() - this.f12889a > 3600000) {
            com.xiaomi.channel.commonutils.logger.b.a("fetch bucket :" + bVar.d());
            this.f12889a = System.currentTimeMillis();
            db a2 = db.a();
            a2.a();
            a2.b();
            fu a3 = this.f299a.a();
            if (a3 != null && (b2 = a2.b(a3.d().c())) != null) {
                ArrayList<String> d = b2.d();
                boolean z = true;
                Iterator<String> it = d.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().equals(a3.e())) {
                            z = false;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (z && !d.isEmpty()) {
                    com.xiaomi.channel.commonutils.logger.b.a("bucket changed, force reconnect");
                    this.f299a.a(0, (Exception) null);
                    this.f299a.a(false);
                }
            }
        }
    }
}
