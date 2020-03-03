package com.xiaomi.youpin.cookie;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import com.alipay.sdk.util.i;
import com.xiaomi.youpin.log.LogUtils;
import java.util.ArrayList;
import java.util.List;

public class YouPinCookieManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23311a = "YouPinCookieManager";
    private static final boolean b = (Build.VERSION.SDK_INT < 21);
    private static volatile YouPinCookieManager c;
    @Nullable
    private CookieManager d;
    /* access modifiers changed from: private */
    public CookieSaver e = new CookieSaver(b, d());

    private YouPinCookieManager() {
    }

    public static YouPinCookieManager a() {
        if (c == null) {
            synchronized (YouPinCookieManager.class) {
                if (c == null) {
                    c = new YouPinCookieManager();
                }
            }
        }
        return c;
    }

    private static void a(Context context) {
        if (b) {
            CookieSyncManager.createInstance(context).sync();
        }
    }

    public void a(String str, String str2, String str3) {
        if (str != null && str2 != null && str3 != null) {
            a(str3, str + "=" + str2 + "; domain=" + str3);
        }
    }

    public void a(@NonNull String str, String str2) {
        LogUtils.d(f23311a, "setCookie(): " + str + ", " + str2);
        if (d() != null) {
            if (b) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(str);
                arrayList.add(str2);
                new CookieSetAsyncTask(d(), this.e).execute(new List[]{arrayList});
                return;
            }
            c(str, str2);
            this.e.a();
        }
    }

    public void a(@NonNull String str, List<String> list) {
        LogUtils.d(f23311a, "setCookie(): " + str + ", " + list);
        if (d() != null) {
            if (b) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(str);
                arrayList.addAll(list);
                new CookieSetAsyncTask(d(), this.e).execute(new List[]{arrayList});
                return;
            }
            for (String c2 : list) {
                c(str, c2);
            }
            this.e.a();
        }
    }

    public void a(@NonNull String str) {
        LogUtils.d(f23311a, "removeCookie(): " + str);
        String b2 = b(str);
        if (b2 != null) {
            String[] split = b2.split(i.b);
            if (split.length > 0) {
                for (String split2 : split) {
                    String[] split3 = split2.split("=");
                    if (split3.length > 0 && !TextUtils.isEmpty(split3[0])) {
                        a(str, split3[0] + "=EXPIRED; domain=" + str + "; expires=Thu, 01-Dec-1994 16:00:00 GMT");
                    }
                }
            }
        }
    }

    public void b(String str, String str2) {
        String b2 = b(str2);
        if (!TextUtils.isEmpty(b2)) {
            String[] split = b2.split(i.b);
            if (split.length > 0) {
                int length = split.length;
                int i = 0;
                while (i < length) {
                    String[] split2 = split[i].trim().split("=");
                    if (split2.length <= 0 || TextUtils.isEmpty(split2[0]) || !split2[0].equals(str)) {
                        i++;
                    } else {
                        a(str2, split2[0] + "=EXPIRED; domain=" + str2 + "; expires=Thu, 01-Dec-1994 16:00:00 GMT");
                        return;
                    }
                }
            }
        }
    }

    public String b(@NonNull String str) {
        CookieManager d2 = d();
        if (d2 == null) {
            return "";
        }
        String cookie = d2.getCookie(str);
        LogUtils.d(f23311a, "getCookie(): " + str + ", " + cookie);
        return cookie;
    }

    public void b() {
        LogUtils.d(f23311a, "removeAllCookies()");
        if (d() != null) {
            if (b) {
                new CookieRemoveAsyncTask(d(), this.e).execute(new Void[0]);
            } else {
                c();
            }
        }
    }

    @TargetApi(21)
    private void c() {
        CookieManager d2 = d();
        if (d2 != null) {
            d2.removeAllCookies(new ValueCallback<Boolean>() {
                /* renamed from: a */
                public void onReceiveValue(Boolean bool) {
                    LogUtils.d(YouPinCookieManager.f23311a, "clearCookiesAsync success");
                    YouPinCookieManager.this.e.a();
                }
            });
        }
    }

    @Nullable
    private CookieManager d() {
        if (this.d == null) {
            CookieConfig b2 = CookieConfigManager.a().b();
            if (b2 != null) {
                try {
                    a(b2.a());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            this.d = CookieManager.getInstance();
            if (b && this.d != null) {
                this.d.removeExpiredCookie();
            }
        }
        return this.d;
    }

    @TargetApi(21)
    private void c(String str, String str2) {
        CookieManager d2 = d();
        if (d2 != null) {
            d2.setCookie(str, str2, (ValueCallback) null);
        }
    }

    private static class CookieRemoveAsyncTask extends AsyncTask<Void, Void, Void> {

        /* renamed from: a  reason: collision with root package name */
        private CookieManager f23313a;
        private CookieSaver b;

        public CookieRemoveAsyncTask(CookieManager cookieManager, CookieSaver cookieSaver) {
            this.f23313a = cookieManager;
            this.b = cookieSaver;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            if (this.f23313a != null) {
                this.f23313a.removeAllCookie();
            }
            if (this.b == null) {
                return null;
            }
            this.b.a();
            return null;
        }
    }

    private static class CookieSetAsyncTask extends AsyncTask<List<String>, Void, Void> {

        /* renamed from: a  reason: collision with root package name */
        private CookieManager f23314a;
        private CookieSaver b;

        public CookieSetAsyncTask(CookieManager cookieManager, CookieSaver cookieSaver) {
            this.f23314a = cookieManager;
            this.b = cookieSaver;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(List<String>... listArr) {
            List<String> list = listArr[0];
            String str = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String str2 = list.get(i);
                if (this.f23314a != null) {
                    this.f23314a.setCookie(str, str2);
                }
            }
            if (this.b == null) {
                return null;
            }
            this.b.a();
            return null;
        }
    }
}
