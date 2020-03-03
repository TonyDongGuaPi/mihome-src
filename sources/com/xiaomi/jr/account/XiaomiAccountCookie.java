package com.xiaomi.jr.account;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.xiaomi.jr.QualityMonitor;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.Utils;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

class XiaomiAccountCookie {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10277a = "XiaomiAccountCookie";
    private final Context b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final ServiceTokenVerifier g;

    public interface ServiceTokenVerifier {
        boolean a(Bundle bundle);
    }

    private boolean b() {
        return true;
    }

    private XiaomiAccountCookie(Builder builder) {
        this.b = builder.f10278a;
        this.c = builder.b;
        this.d = builder.c;
        this.e = builder.d;
        this.f = builder.e;
        this.g = builder.f;
    }

    /* access modifiers changed from: package-private */
    public boolean a(Bundle bundle) {
        String string = bundle.getString("cUserId");
        String string2 = bundle.getString("serviceToken");
        String string3 = bundle.getString(IAccountProvider.d);
        String string4 = bundle.getString("ph");
        if (TextUtils.isEmpty(string3)) {
            QualityMonitor.a(Constants.j, "set_cookie", String.format("setCookie error: no %s_slh. skip.", new Object[]{this.c}));
        }
        if (TextUtils.isEmpty(string4)) {
            QualityMonitor.a(Constants.j, "set_cookie", String.format("setCookie error: no %s_ph. skip.", new Object[]{this.c}));
        }
        CookieSyncManager.createInstance(this.b);
        CookieManager instance = CookieManager.getInstance();
        instance.setCookie(this.d, a(this.f, "cUserId", string, this.e));
        String str = this.d;
        String str2 = this.f;
        instance.setCookie(str, a(str2, this.c + "_serviceToken", string2, this.e));
        String str3 = this.d;
        String a2 = a(this.f);
        instance.setCookie(str3, a(a2, this.c + "_slh", string3, this.e));
        String str4 = this.d;
        String str5 = this.f;
        instance.setCookie(str4, a(str5, this.c + "_ph", string4, this.e));
        if (Build.VERSION.SDK_INT >= 21) {
            instance.flush();
        } else {
            CookieSyncManager.getInstance().sync();
        }
        MifiLog.c("TestCookie", "setCookie: " + instance.getCookie(this.d));
        return true;
    }

    /* access modifiers changed from: package-private */
    public Bundle a() {
        Utils.b();
        return a(true);
    }

    private Bundle a(boolean z) {
        String str;
        Bundle bundle;
        try {
            bundle = c().a(this.c).getResult();
            str = null;
        } catch (Exception e2) {
            e2.printStackTrace();
            str = e2.getMessage();
            bundle = null;
        }
        if (bundle == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("sdk error: ");
            sb.append(str);
            sb.append(" for sid ");
            sb.append(this.c);
            sb.append(z ? "" : " [reGet]");
            QualityMonitor.a(Constants.j, "get_account_info", sb.toString());
            return null;
        } else if (bundle.getParcelable("intent") != null) {
            return bundle;
        } else {
            if (TextUtils.isEmpty(bundle.getString("cUserId"))) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("sdk error: no cUserId for sid ");
                sb2.append(this.c);
                sb2.append(z ? "" : " [reGet]");
                sb2.append(". detail: ");
                sb2.append(XiaomiAccountUtils.a(bundle));
                QualityMonitor.a(Constants.j, "get_account_info", sb2.toString());
                return null;
            } else if (TextUtils.isEmpty(bundle.getString("serviceToken"))) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("sdk error: no serviceToken for sid ");
                sb3.append(this.c);
                sb3.append(z ? "" : " [reGet]");
                sb3.append(". detail: ");
                sb3.append(XiaomiAccountUtils.a(bundle));
                QualityMonitor.a(Constants.j, "get_account_info", sb3.toString());
                return null;
            } else if (!a(z, bundle, this.g)) {
                return bundle;
            } else {
                MifiLog.d(f10277a, String.format("serviceToken for sid %s is invalid. Re-get again.", new Object[]{this.c}));
                return b(bundle);
            }
        }
    }

    private static boolean a(boolean z, Bundle bundle, ServiceTokenVerifier serviceTokenVerifier) {
        return z && serviceTokenVerifier != null && !serviceTokenVerifier.a(bundle);
    }

    private Bundle b(Bundle bundle) {
        try {
            c().a(bundle);
            return a(false);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private IAccountProvider c() {
        return XiaomiAccountManager.j();
    }

    private static String a(String str, String str2, String str3, String str4) {
        return String.format("%s=%s; domain=%s; path=%s", new Object[]{str2, str3, str, str4});
    }

    private static String a(String str) {
        String[] split = str.split("\\.");
        int length = split.length;
        if (length <= 2) {
            return str;
        }
        return String.format(".%s.%s", new Object[]{split[length - 2], split[length - 1]});
    }

    public static final class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public Context f10278a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d;
        /* access modifiers changed from: private */
        public String e;
        /* access modifiers changed from: private */
        public ServiceTokenVerifier f;

        public Builder a(Context context) {
            this.f10278a = context;
            return this;
        }

        public Builder a(String str) {
            this.b = str;
            return this;
        }

        public Builder b(String str) {
            this.c = str;
            return this;
        }

        public Builder c(String str) {
            this.d = str;
            return this;
        }

        public Builder d(String str) {
            this.e = str;
            return this;
        }

        public Builder a(ServiceTokenVerifier serviceTokenVerifier) {
            this.f = serviceTokenVerifier;
            return this;
        }

        public XiaomiAccountCookie a() {
            a(this.f10278a, "context");
            a(this.b, "sid");
            a(this.c, "url");
            if (this.d == null) {
                try {
                    this.d = new URL(this.c).getPath();
                } catch (MalformedURLException e2) {
                    MifiLog.d(XiaomiAccountCookie.f10277a, "bad url", e2);
                }
                if (TextUtils.isEmpty(this.d)) {
                    this.d = File.separator;
                }
            }
            if (this.e == null) {
                try {
                    this.e = "." + new URL(this.c).getHost();
                } catch (MalformedURLException e3) {
                    MifiLog.d(XiaomiAccountCookie.f10277a, "bad url", e3);
                }
            }
            a(this.e, "cookieDomain");
            return new XiaomiAccountCookie(this);
        }

        private void a(Object obj, String str) {
            if (obj == null) {
                throw new IllegalArgumentException("" + str + " is null");
            }
        }
    }
}
