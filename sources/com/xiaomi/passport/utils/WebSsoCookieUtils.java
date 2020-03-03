package com.xiaomi.passport.utils;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.servicetoken.IServiceTokenUtil;
import com.xiaomi.passport.servicetoken.ServiceTokenResult;
import com.xiaomi.passport.servicetoken.ServiceTokenUtilFacade;
import java.net.MalformedURLException;
import java.net.URL;

public class WebSsoCookieUtils {
    private static final String TAG = "WebSsoCookieUtils";
    private final Context context;
    final String cookieDomain;
    final String cookiePath;
    private final ServiceTokenVerifier serviceTokenVerifier;
    private final String sid;
    private final String url;

    public interface ServiceTokenVerifier {
        boolean verify(ServiceTokenResult serviceTokenResult);
    }

    private WebSsoCookieUtils(Builder builder) {
        this.context = builder.context;
        this.sid = builder.sid;
        this.url = builder.url;
        this.cookiePath = builder.cookiePath;
        this.cookieDomain = builder.cookieDomain;
        this.serviceTokenVerifier = builder.serviceTokenVerifier;
    }

    public boolean setCookie() {
        return setCookieRet() != null;
    }

    public ServiceTokenResult setCookieRet() {
        if (isInMainThread()) {
            throw new IllegalStateException("WebSsoCookieUtils#setCookie() should NOT be called on main thread!");
        } else if (!fastCheckSlhPhCompatibility()) {
            AccountLog.w(TAG, "setCookie error: blocked on old miui versin");
            return null;
        } else {
            ServiceTokenResult serviceTokenResult = getServiceTokenResult(true);
            if (serviceTokenResult == null) {
                return null;
            }
            CookieSyncManager.createInstance(this.context);
            CookieManager cookieManager = getCookieManager();
            cookieManager.setCookie(this.url, getCookie(this.cookieDomain, "cUserId", serviceTokenResult.cUserId, this.cookiePath));
            cookieManager.setCookie(this.url, getCookie(this.cookieDomain, "serviceToken", serviceTokenResult.serviceToken, this.cookiePath));
            String str = this.url;
            String rootDomain = rootDomain(this.cookieDomain);
            cookieManager.setCookie(str, getCookie(rootDomain, this.sid + "_slh", serviceTokenResult.slh, this.cookiePath));
            String str2 = this.url;
            String str3 = this.cookieDomain;
            cookieManager.setCookie(str2, getCookie(str3, this.sid + "_ph", serviceTokenResult.ph, this.cookiePath));
            CookieSyncManager.getInstance().sync();
            return serviceTokenResult;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isInMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private ServiceTokenResult getServiceTokenResult(boolean z) {
        ServiceTokenResult serviceTokenResult = getAm().getServiceToken(this.context, this.sid).get();
        if (TextUtils.isEmpty(serviceTokenResult.serviceToken)) {
            AccountLog.w(TAG, String.format("setCookie error: no serviceToken for sid %s", new Object[]{this.sid}));
            return null;
        } else if (TextUtils.isEmpty(serviceTokenResult.cUserId)) {
            AccountLog.w(TAG, "setCookie error: no cUserId");
            return null;
        } else if (TextUtils.isEmpty(serviceTokenResult.slh)) {
            AccountLog.w(TAG, String.format("setCookie error: no %s_slh", new Object[]{this.sid}));
            if (z) {
                return reGetServiceTokenResult(serviceTokenResult);
            }
            return null;
        } else if (TextUtils.isEmpty(serviceTokenResult.ph)) {
            AccountLog.w(TAG, String.format("setCookie error: no %s_ph", new Object[]{this.sid}));
            if (z) {
                return reGetServiceTokenResult(serviceTokenResult);
            }
            return null;
        } else if (!verifyAndFail(z, serviceTokenResult, this.serviceTokenVerifier)) {
            return serviceTokenResult;
        } else {
            AccountLog.w(TAG, String.format("serviceToken for sid %s is invalid. Re-get again.", new Object[]{this.sid}));
            return reGetServiceTokenResult(serviceTokenResult);
        }
    }

    private static boolean verifyAndFail(boolean z, ServiceTokenResult serviceTokenResult, ServiceTokenVerifier serviceTokenVerifier2) {
        return z && serviceTokenResult.peeked && serviceTokenVerifier2 != null && !serviceTokenVerifier2.verify(serviceTokenResult);
    }

    private ServiceTokenResult reGetServiceTokenResult(ServiceTokenResult serviceTokenResult) {
        getAm().invalidateServiceToken(this.context, serviceTokenResult);
        return getServiceTokenResult(false);
    }

    /* access modifiers changed from: package-private */
    public boolean fastCheckSlhPhCompatibility() {
        return !getAm().isUseSystem() || getMiuiServiceTokenUtil().fastCheckSlhPhCompatibility(this.context);
    }

    /* access modifiers changed from: package-private */
    public IServiceTokenUtil getMiuiServiceTokenUtil() {
        return ServiceTokenUtilFacade.getInstance().buildMiuiServiceTokenUtil();
    }

    /* access modifiers changed from: package-private */
    public MiAccountManager getAm() {
        return MiAccountManager.get(this.context);
    }

    /* access modifiers changed from: package-private */
    public CookieManager getCookieManager() {
        return CookieManager.getInstance();
    }

    static String getCookie(String str, String str2, String str3, String str4) {
        return String.format("%s=%s; domain=%s; path=%s", new Object[]{str2, str3, str, str4});
    }

    static String rootDomain(String str) {
        String[] split = str.split("\\.");
        int length = split.length;
        if (length <= 2) {
            return str;
        }
        return String.format(".%s.%s", new Object[]{split[length - 2], split[length - 1]});
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String cookieDomain;
        /* access modifiers changed from: private */
        public String cookiePath = "/";
        /* access modifiers changed from: private */
        public ServiceTokenVerifier serviceTokenVerifier;
        /* access modifiers changed from: private */
        public String sid;
        /* access modifiers changed from: private */
        public String url;

        public Builder context(Context context2) {
            this.context = context2;
            return this;
        }

        public Builder sid(String str) {
            this.sid = str;
            return this;
        }

        public Builder url(String str) {
            this.url = str;
            return this;
        }

        public Builder cookiePath(String str) {
            this.cookiePath = str;
            return this;
        }

        public Builder cookieDomain(String str) {
            this.cookieDomain = str;
            return this;
        }

        public Builder serviceTokenVerifier(ServiceTokenVerifier serviceTokenVerifier2) {
            this.serviceTokenVerifier = serviceTokenVerifier2;
            return this;
        }

        public WebSsoCookieUtils build() {
            throwIfNull(this.context, "context");
            throwIfNull(this.sid, "sid");
            throwIfNull(this.url, "url");
            throwIfNull(this.cookiePath, "cookiePath");
            if (this.cookieDomain == null) {
                try {
                    this.cookieDomain = new URL(this.url).getHost();
                } catch (MalformedURLException e) {
                    AccountLog.w(WebSsoCookieUtils.TAG, "bad url", e);
                }
            }
            throwIfNull(this.cookieDomain, "cookieDomain");
            return new WebSsoCookieUtils(this);
        }

        private void throwIfNull(Object obj, String str) {
            if (obj == null) {
                throw new IllegalArgumentException("" + str + " is null");
            }
        }
    }
}
