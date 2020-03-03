package com.xiaomi.smarthome.library.http.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import com.alipay.sdk.util.i;

public class WebViewCookieManager {

    /* renamed from: a  reason: collision with root package name */
    static final String f1552a = "CookieManager";
    static WebViewCookieManager b = null;
    /* access modifiers changed from: private */
    public static final boolean d = (Build.VERSION.SDK_INT < 21);
    Context c;
    /* access modifiers changed from: private */
    public CookieSaver e = new CookieSaver();
    private CookieManager f;

    WebViewCookieManager(Context context) {
        this.c = context;
    }

    public static void a(Context context) {
        b = new WebViewCookieManager(context);
    }

    public static WebViewCookieManager a() {
        return b;
    }

    private static void b(Context context) {
        if (d) {
            CookieSyncManager.createInstance(context).sync();
        }
    }

    public void a(String str, String str2, String str3) {
        if (str != null && str2 != null && str3 != null) {
            a(str3, str + "=" + str2 + "; domain=" + str3);
        }
    }

    public void a(final String str, final String str2) {
        Log.d(f1552a, "setCookie(): " + str + ", " + str2);
        if (d) {
            a((Runnable) new Runnable() {
                public void run() {
                    WebViewCookieManager.this.e().setCookie(str, str2);
                    WebViewCookieManager.this.e.a();
                }
            });
            return;
        }
        b(str, str2);
        this.e.a();
    }

    public void a(String str) {
        Log.d(f1552a, "removeCookie(): " + str);
        String[] split = b(str).split(i.b);
        if (split.length > 0) {
            for (String split2 : split) {
                String[] split3 = split2.split("=");
                if (split3.length > 0 && !TextUtils.isEmpty(split3[0])) {
                    a(str, split3[0] + "=EXPIRED; domain=" + str + "; expires=Thu, 01-Dec-1994 16:00:00 GMT");
                }
            }
        }
    }

    public String b(String str) {
        String cookie = e().getCookie(str);
        Log.d(f1552a, "getCookie(): " + str + ", " + cookie);
        return cookie;
    }

    public void b() {
        Log.d(f1552a, "removeAllCookies()");
        if (d) {
            new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Void doInBackground(Void... voidArr) {
                    WebViewCookieManager.this.e().removeAllCookie();
                    WebViewCookieManager.this.e.a();
                    return null;
                }
            }.execute(new Void[0]);
        } else {
            d();
        }
    }

    @TargetApi(21)
    private void d() {
        e().removeAllCookies(new ValueCallback<Boolean>() {
            /* renamed from: a */
            public void onReceiveValue(Boolean bool) {
                Log.d(WebViewCookieManager.f1552a, "clearCookiesAsync success");
                WebViewCookieManager.this.e.a();
            }
        });
    }

    /* access modifiers changed from: private */
    public CookieManager e() {
        if (this.f == null) {
            b(this.c);
            this.f = CookieManager.getInstance();
            if (d) {
                this.f.removeExpiredCookie();
            }
        }
        return this.f;
    }

    @TargetApi(21)
    private void b(String str, String str2) {
        e().setCookie(str, str2, (ValueCallback) null);
    }

    /* access modifiers changed from: private */
    public void a(final Runnable runnable) {
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Void doInBackground(Void... voidArr) {
                runnable.run();
                return null;
            }
        }.execute(new Void[0]);
    }

    private class CookieSaver {
        private static final int b = 1;
        private static final int c = 30000;
        private final Handler d;

        public CookieSaver() {
            this.d = new Handler(Looper.getMainLooper(), new Handler.Callback(WebViewCookieManager.this) {
                public boolean handleMessage(Message message) {
                    if (message.what != 1) {
                        return false;
                    }
                    CookieSaver.this.b();
                    return true;
                }
            });
        }

        public void a() {
            if (WebViewCookieManager.d) {
                this.d.sendEmptyMessageDelayed(1, 30000);
            }
        }

        public void b() {
            this.d.removeMessages(1);
            WebViewCookieManager.this.a((Runnable) new Runnable() {
                public void run() {
                    if (WebViewCookieManager.d) {
                        CookieSyncManager.getInstance().sync();
                    } else {
                        CookieSaver.this.c();
                    }
                }
            });
        }

        /* access modifiers changed from: private */
        @TargetApi(21)
        public void c() {
            WebViewCookieManager.this.e().flush();
        }
    }
}
