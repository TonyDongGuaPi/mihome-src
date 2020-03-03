package com.miuipub.internal.hybrid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.mi.global.shop.model.Tags;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;
import miuipub.hybrid.Callback;
import miuipub.hybrid.HybridChromeClient;
import miuipub.hybrid.HybridFeature;
import miuipub.hybrid.HybridSettings;
import miuipub.hybrid.HybridView;
import miuipub.hybrid.HybridViewClient;
import miuipub.hybrid.LifecycleListener;
import miuipub.hybrid.NativeInterface;
import miuipub.hybrid.PageContext;
import miuipub.hybrid.Request;
import miuipub.hybrid.Response;

public class HybridManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8256a = "hybrid";
    private static ExecutorService b = Executors.newCachedThreadPool();
    private static String c;
    private Activity d;
    /* access modifiers changed from: private */
    public HybridView e;
    /* access modifiers changed from: private */
    public boolean f;
    private NativeInterface g;
    private FeatureManager h;
    private PermissionManager i;
    private PageContext j;
    private Set<LifecycleListener> k = new CopyOnWriteArraySet();

    public HybridManager(Activity activity, HybridView hybridView) {
        this.d = activity;
        this.e = hybridView;
    }

    public void a(int i2, String str) {
        this.g = new NativeInterface(this);
        Config a2 = a(i2);
        a(a2, false);
        j();
        if (str == null && !TextUtils.isEmpty(a2.c())) {
            str = c(a2.c());
        }
        if (str != null) {
            this.e.loadUrl(str);
        }
    }

    private Config a(int i2) {
        XmlConfigParser xmlConfigParser;
        if (i2 == 0) {
            try {
                xmlConfigParser = XmlConfigParser.a((Context) this.d);
            } catch (HybridException e2) {
                throw new RuntimeException("cannot load config: " + e2.getMessage());
            }
        } else {
            xmlConfigParser = XmlConfigParser.a((Context) this.d, i2);
        }
        return xmlConfigParser.a((Map<String, Object>) null);
    }

    private String a(Config config, boolean z) {
        if (z) {
            SecurityManager securityManager = new SecurityManager(config, this.d.getApplicationContext());
            if (securityManager.a() || !securityManager.b()) {
                return new Response(202).toString();
            }
        }
        this.h = new FeatureManager(config, this.d.getClassLoader());
        this.i = new PermissionManager(config);
        return new Response(0).toString();
    }

    public String a(String str) {
        try {
            return a(JsonConfigParser.a(str).a((Map<String, Object>) null), true);
        } catch (HybridException e2) {
            return new Response(201, e2.getMessage()).toString();
        }
    }

    public void a(PageContext pageContext) {
        this.j = pageContext;
    }

    private void j() {
        a(this.e.getSettings());
        this.e.setHybridViewClient(new HybridViewClient());
        this.e.setHybridChromeClient(new HybridChromeClient());
        this.e.addJavascriptInterface(new JsInterface(this), "MiuiJsBridge");
        this.e.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            public void onViewAttachedToWindow(View view) {
                boolean unused = HybridManager.this.f = false;
            }

            public void onViewDetachedFromWindow(View view) {
                boolean unused = HybridManager.this.f = true;
            }
        });
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void a(HybridSettings hybridSettings) {
        hybridSettings.a(true);
        hybridSettings.a(b(hybridSettings.a()));
    }

    private String b(String str) {
        if (c == null) {
            c = str + " XiaoMi/HybridView/" + a((Context) this.d, this.d.getPackageName()).versionName + " " + this.d.getPackageName() + "/" + a((Context) this.d, this.d.getPackageName()).versionName;
        }
        return c;
    }

    private static PackageInfo a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 128);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private String c(String str) {
        if (Pattern.compile("^[a-z-]+://").matcher(str).find()) {
            return str;
        }
        if (str.charAt(0) == '/') {
            str = str.substring(1);
        }
        return "file:///android_asset/hybrid/" + str;
    }

    private String d(String str) {
        return str.startsWith("miui.") ? str.replace("miui.", "miuipub.") : str;
    }

    private HybridFeature e(String str) throws HybridException {
        if (this.i.a(this.j.b())) {
            return this.h.a(str);
        }
        throw new HybridException(203, "feature not permitted: " + str);
    }

    private Request a(String str, String str2, String str3) {
        Request request = new Request();
        request.a(str2);
        request.b(str3);
        request.a(this.j);
        request.a((View) this.e);
        request.a(this.g);
        return request;
    }

    public String a(String str, String str2) {
        String d2 = d(str);
        try {
            if (e(d2).b(a(d2, str2, (String) null)) != null) {
                return new Response(0).toString();
            }
            return new Response(205, "action not supported: " + str2).toString();
        } catch (HybridException e2) {
            return e2.getResponse().toString();
        }
    }

    public String a(String str, String str2, String str3, String str4) {
        String d2 = d(str);
        try {
            HybridFeature e2 = e(d2);
            Request a2 = a(d2, str2, str3);
            HybridFeature.Mode b2 = e2.b(a2);
            if (b2 == HybridFeature.Mode.SYNC) {
                a(new Response(1), this.j, str4);
                return e2.a(a2).toString();
            } else if (b2 == HybridFeature.Mode.ASYNC) {
                b.execute(new AsyncInvocation(e2, a2, str4));
                return new Response(2).toString();
            } else {
                a2.a(new Callback(this, this.j, str4));
                b.execute(new AsyncInvocation(e2, a2, str4));
                return new Response(3).toString();
            }
        } catch (HybridException e3) {
            Response response = e3.getResponse();
            a(response, this.j, str4);
            return response.toString();
        }
    }

    public boolean a() {
        return this.f;
    }

    private class AsyncInvocation implements Runnable {
        private HybridFeature b;
        private Request c;
        private String d;

        public AsyncInvocation(HybridFeature hybridFeature, Request request, String str) {
            this.b = hybridFeature;
            this.c = request;
            this.d = str;
        }

        public void run() {
            Response a2 = this.b.a(this.c);
            if (this.b.b(this.c) == HybridFeature.Mode.ASYNC) {
                HybridManager.this.a(a2, this.c.d(), this.d);
            }
        }
    }

    public void a(Response response, PageContext pageContext, String str) {
        if (response != null && !TextUtils.isEmpty(str) && pageContext.equals(this.j) && !this.f && !this.d.isFinishing()) {
            if (Log.isLoggable("hybrid", 3)) {
                Log.d("hybrid", "non-blocking response is " + response.toString());
            }
            this.d.runOnUiThread(new JsInvocation(response, str));
        }
    }

    private class JsInvocation implements Runnable {
        private Response b;
        private String c;

        public JsInvocation(Response response, String str) {
            this.b = response;
            this.c = str;
        }

        public void run() {
            String a2 = HybridManager.this.a(this.b, this.c);
            HybridView a3 = HybridManager.this.e;
            a3.loadUrl("javascript:" + a2);
        }
    }

    /* access modifiers changed from: private */
    public String a(Response response, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str + "('" + response.toString().replace(Tags.MiHome.TEL_SEPARATOR4, "\\\\").replace("'", "\\'") + "');";
    }

    public Activity b() {
        return this.d;
    }

    public void a(LifecycleListener lifecycleListener) {
        this.k.add(lifecycleListener);
    }

    public void b(LifecycleListener lifecycleListener) {
        this.k.remove(lifecycleListener);
    }

    public void c() {
        for (LifecycleListener a2 : this.k) {
            a2.a();
        }
    }

    public void d() {
        for (LifecycleListener b2 : this.k) {
            b2.b();
        }
    }

    public void e() {
        for (LifecycleListener c2 : this.k) {
            c2.c();
        }
    }

    public void f() {
        for (LifecycleListener d2 : this.k) {
            d2.d();
        }
    }

    public void g() {
        for (LifecycleListener e2 : this.k) {
            e2.e();
        }
    }

    public void h() {
        for (LifecycleListener f2 : this.k) {
            f2.f();
        }
    }

    public void a(int i2, int i3, Intent intent) {
        for (LifecycleListener a2 : this.k) {
            a2.a(i2, i3, intent);
        }
    }

    public HybridView i() {
        return this.e;
    }
}
