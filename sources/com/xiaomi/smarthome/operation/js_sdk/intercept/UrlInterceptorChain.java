package com.xiaomi.smarthome.operation.js_sdk.intercept;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import com.xiaomi.smarthome.operation.js_sdk.utils.JsSdkUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class UrlInterceptorChain implements IUrlInterceptor {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21070a = "UrlInterceptorChain";
    private final ArrayList<Pair<String, IUrlInterceptor>> b = new ArrayList<>();

    public void a(WebView webView, String str, Bitmap bitmap) {
        synchronized (this.b) {
            Iterator<Pair<String, IUrlInterceptor>> it = this.b.iterator();
            while (it.hasNext()) {
                ((IUrlInterceptor) it.next().second).a(webView, str, bitmap);
            }
        }
    }

    public boolean a(WebView webView, String str) {
        synchronized (this.b) {
            Iterator<Pair<String, IUrlInterceptor>> it = this.b.iterator();
            while (it.hasNext()) {
                Pair next = it.next();
                if (((IUrlInterceptor) next.second).a(webView, str)) {
                    Log.d(f21070a, "shouldOverrideUrlLoading 1: by: " + ((String) next.first));
                    return true;
                }
            }
            return false;
        }
    }

    public boolean a(WebView webView, WebResourceRequest webResourceRequest) {
        synchronized (this.b) {
            Iterator<Pair<String, IUrlInterceptor>> it = this.b.iterator();
            while (it.hasNext()) {
                Pair next = it.next();
                if (((IUrlInterceptor) next.second).a(webView, webResourceRequest)) {
                    Log.d(f21070a, "shouldOverrideUrlLoading 2: by: " + ((String) next.first));
                    return true;
                }
            }
            return false;
        }
    }

    public void b(WebView webView, String str) {
        synchronized (this.b) {
            Iterator<Pair<String, IUrlInterceptor>> it = this.b.iterator();
            while (it.hasNext()) {
                ((IUrlInterceptor) it.next().second).b(webView, str);
            }
        }
    }

    public void a(WebView webView, String str, String str2, String str3) {
        synchronized (this.b) {
            Iterator<Pair<String, IUrlInterceptor>> it = this.b.iterator();
            while (it.hasNext()) {
                ((IUrlInterceptor) it.next().second).a(webView, str, str2, str3);
            }
        }
    }

    public void a() {
        synchronized (this.b) {
            Iterator<Pair<String, IUrlInterceptor>> it = this.b.iterator();
            while (it.hasNext()) {
                ((IUrlInterceptor) it.next().second).a();
            }
        }
    }

    public void b() {
        synchronized (this.b) {
            Iterator<Pair<String, IUrlInterceptor>> it = this.b.iterator();
            while (it.hasNext()) {
                ((IUrlInterceptor) it.next().second).b();
            }
        }
    }

    public void a(WebView webView, int i, String str, String str2) {
        synchronized (this.b) {
            Iterator<Pair<String, IUrlInterceptor>> it = this.b.iterator();
            while (it.hasNext()) {
                ((IUrlInterceptor) it.next().second).a(webView, i, str, str2);
            }
        }
    }

    public IUrlInterceptor a(String str) {
        synchronized (this.b) {
            Iterator<Pair<String, IUrlInterceptor>> it = this.b.iterator();
            while (it.hasNext()) {
                Pair next = it.next();
                if (TextUtils.equals((CharSequence) next.first, str)) {
                    IUrlInterceptor iUrlInterceptor = (IUrlInterceptor) next.second;
                    return iUrlInterceptor;
                }
            }
            return null;
        }
    }

    public <C extends IUrlInterceptor> C a(Class<C> cls) {
        synchronized (this.b) {
            Iterator<Pair<String, IUrlInterceptor>> it = this.b.iterator();
            while (it.hasNext()) {
                Pair next = it.next();
                if (cls.isInstance(next.second)) {
                    C c = (IUrlInterceptor) next.second;
                    return c;
                }
            }
            return null;
        }
    }

    public void a(String str, IUrlInterceptor iUrlInterceptor) {
        synchronized (this.b) {
            a(str, iUrlInterceptor, this.b.size());
        }
    }

    public void a(String str, IUrlInterceptor iUrlInterceptor, int i) {
        JsSdkUtils.a(str, "name is empty!");
        JsSdkUtils.a(iUrlInterceptor, "interceptor is null!");
        synchronized (this.b) {
            this.b.add(i, Pair.create(str, iUrlInterceptor));
            Log.d(f21070a, "install: name: " + str + " ;index: " + i);
        }
    }
}
