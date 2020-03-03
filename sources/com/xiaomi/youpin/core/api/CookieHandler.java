package com.xiaomi.youpin.core.api;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import com.xiaomi.smarthome.application.SHApplication;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CookieHandler extends java.net.CookieHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23320a = "Set-cookie";
    private static final String b = "Set-cookie2";
    private static final String c = "Cookie";
    /* access modifiers changed from: private */
    public static final boolean d = (Build.VERSION.SDK_INT < 21);
    /* access modifiers changed from: private */
    public final CookieSaver e = new CookieSaver();
    private CookieManager f;

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(str.length());
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt > 31 && charAt < 127) {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    public Map<String, List<String>> get(URI uri, Map<String, List<String>> map) throws IOException {
        String cookie = e().getCookie(uri.toString());
        if (TextUtils.isEmpty(cookie)) {
            return Collections.emptyMap();
        }
        return Collections.singletonMap("Cookie", Collections.singletonList(a(cookie)));
    }

    public void put(URI uri, Map<String, List<String>> map) throws IOException {
        String uri2 = uri.toString();
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            if (str != null && b(str)) {
                a(uri2, (List<String>) (List) next.getValue());
            }
        }
    }

    public void a() {
        if (d) {
            new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Void doInBackground(Void... voidArr) {
                    CookieHandler.this.e().removeAllCookie();
                    CookieHandler.this.e.a();
                    return null;
                }
            }.execute(new Void[0]);
        } else {
            d();
        }
    }

    @RequiresApi(api = 21)
    private void d() {
        e().removeAllCookies(new ValueCallback<Boolean>() {
            /* renamed from: a */
            public void onReceiveValue(Boolean bool) {
                CookieHandler.this.e.a();
            }
        });
    }

    public void b() {
        if (d) {
            e().removeExpiredCookie();
            this.e.b();
        }
    }

    private void a(final String str, final List<String> list) {
        if (d) {
            a((Runnable) new Runnable() {
                public void run() {
                    for (String cookie : list) {
                        CookieHandler.this.e().setCookie(str, cookie);
                    }
                    CookieHandler.this.e.a();
                }
            });
            return;
        }
        for (String a2 : list) {
            a(str, a2);
        }
        this.e.a();
    }

    @TargetApi(21)
    private void a(String str, String str2) {
        e().setCookie(str, str2, (ValueCallback) null);
    }

    private static boolean b(String str) {
        return str.equalsIgnoreCase("Set-cookie") || str.equalsIgnoreCase("Set-cookie2");
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

    /* access modifiers changed from: private */
    public CookieManager e() {
        if (this.f == null) {
            a(SHApplication.getAppContext());
            this.f = CookieManager.getInstance();
            if (d) {
                this.f.removeExpiredCookie();
            }
        }
        return this.f;
    }

    private static void a(Context context) {
        if (d) {
            CookieSyncManager.createInstance(context).sync();
        }
    }

    private class CookieSaver {
        private static final int b = 1;
        private static final int c = 30000;
        private final Handler d;

        public CookieSaver() {
            this.d = new Handler(Looper.getMainLooper(), new Handler.Callback(CookieHandler.this) {
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
            if (CookieHandler.d) {
                this.d.sendEmptyMessageDelayed(1, 30000);
            }
        }

        public void b() {
            this.d.removeMessages(1);
            CookieHandler.this.a((Runnable) new Runnable() {
                public void run() {
                    if (CookieHandler.d) {
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
            CookieHandler.this.e().flush();
        }
    }
}
