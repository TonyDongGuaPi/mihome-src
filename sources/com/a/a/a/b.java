package com.a.a.a;

import a.a.b;
import a.a.c;
import a.a.d;
import a.a.e;
import a.a.f;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import com.mi.global.shop.model.Tags;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final String f674a = "hybrid";
    private static ExecutorService b = Executors.newCachedThreadPool();
    private Activity c;
    /* access modifiers changed from: private */
    public WebView d;
    private d e;
    private Set<c> f = new CopyOnWriteArraySet();

    private class a implements Runnable {
        private a.a.b b;
        private e c;
        private String d;

        public a(a.a.b bVar, e eVar, String str) {
            this.b = bVar;
            this.c = eVar;
            this.d = str;
        }

        public void run() {
            f a2 = this.b.a(this.c);
            if (this.b.c(this.c) == b.a.ASYNC) {
                b.this.a(a2, this.d);
            }
        }
    }

    /* renamed from: com.a.a.a.b$b  reason: collision with other inner class name */
    private class C0025b implements Runnable {
        private f b;
        private String c;

        public C0025b(f fVar, String str) {
            this.b = fVar;
            this.c = str;
        }

        public void run() {
            String a2 = b.this.b(this.b, this.c);
            WebView a3 = b.this.d;
            a3.loadUrl("javascript:" + a2);
        }
    }

    public b(Activity activity, WebView webView) {
        this.c = activity;
        this.d = webView;
        this.e = new d(this);
    }

    private a.a.b a(String str) throws a {
        if (TextUtils.equals(str, "com.mipay.hybrid.feature.ImageSelector")) {
            return new com.mipay.a.a.a();
        }
        throw new a(204, "feature not declared:" + str);
    }

    /* access modifiers changed from: private */
    public String b(f fVar, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str + "('" + fVar.toString().replace(Tags.MiHome.TEL_SEPARATOR4, "\\\\").replace("'", "\\'") + "');";
    }

    public Activity a() {
        return this.c;
    }

    public String a(String str, String str2, String str3, String str4) {
        f fVar;
        e eVar = new e();
        eVar.a(str2);
        eVar.b(str3);
        eVar.a(this.e);
        try {
            a.a.b a2 = a(str);
            b.a c2 = a2.c(eVar);
            if (c2 == b.a.SYNC) {
                a(new f(1), str4);
                fVar = a2.a(eVar);
            } else if (c2 == b.a.ASYNC) {
                b.execute(new a(a2, eVar, str4));
                fVar = new f(2);
            } else {
                eVar.a(new a.a.a(this, str4));
                b.execute(new a(a2, eVar, str4));
                fVar = new f(3);
            }
        } catch (Exception e2) {
            Log.e("hybrid", "lookupFeature failed", e2);
            fVar = new f("not supported");
            a(fVar, str4);
        }
        return fVar.toString();
    }

    public void a(int i, int i2, Intent intent) {
        for (c a2 : this.f) {
            a2.a(i, i2, intent);
        }
    }

    public void a(c cVar) {
        this.f.add(cVar);
    }

    public void a(f fVar, String str) {
        this.c.runOnUiThread(new C0025b(fVar, str));
    }

    public void b() {
        for (c b2 : this.f) {
            b2.b();
        }
    }

    public void b(c cVar) {
        this.f.remove(cVar);
    }

    public void c() {
        for (c c2 : this.f) {
            c2.c();
        }
    }

    public void d() {
        for (c d2 : this.f) {
            d2.d();
        }
    }

    public void e() {
        for (c e2 : this.f) {
            e2.e();
        }
    }

    public void f() {
        for (c f2 : this.f) {
            f2.f();
        }
    }
}
