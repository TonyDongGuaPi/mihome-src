package com.xiaomi.smarthome.operation.js_sdk.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import com.xiaomi.payment.channel.WXPayUtils;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.core.server.internal.util.UserAgentUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.operation.js_sdk.utils.JsSdkUtils;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import java.net.URLEncoder;
import java.util.Locale;

public class BaseWebView extends WebView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21059a = "CommonWebView";
    private String b;
    private boolean c = false;

    public BaseWebView(Context context) {
        super(context);
        a(context);
    }

    public BaseWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public BaseWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        a();
        initCookie();
        if (DarkModeCompat.a(context)) {
            setBackgroundColor(ContextCompat.getColor(context, 17170445));
        }
    }

    @SuppressLint({"AddJavascriptInterface"})
    private void a() {
        WXPayUtils.a();
        WebSettings settings = getSettings();
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setLoadsImagesAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(getContext().getApplicationContext().getDir("database", 0).getPath());
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(-1);
        if (this.b == null) {
            this.b = settings.getUserAgentString() + " " + UserAgentUtil.a(getContext()) + " XiaoMi/MiuiBrowser/4.3  XiaoMi/HybridView/";
        }
        settings.setUserAgentString(this.b);
        settings.setAllowFileAccess(false);
        setDownloadListener(new DownloadListener() {
            public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                BaseWebView.this.a(str, str2, str3, str4, j);
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(String str, String str2, String str3, String str4, long j) {
        if (!TextUtils.isEmpty(str)) {
            getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }
    }

    public void initCookie() {
        LogUtil.a(f21059a, "initCookie");
        CookieSyncManager.createInstance(getContext());
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= 21) {
            instance.setAcceptThirdPartyCookies(this, true);
        }
        if (CoreApi.a().q()) {
            String w = CoreApi.a().w();
            if (!TextUtils.isEmpty(w)) {
                setCookie(instance, "passToken", w, ".account.xiaomi.com");
            }
            setCookie(instance, "userId", CoreApi.a().s(), "mi.com");
            try {
                MiServiceTokenInfo a2 = CoreApi.a().a("xiaomiio");
                if (a2 != null) {
                    setCookie(instance, "serviceToken", URLEncoder.encode(a2.c, "UTF-8"), ".io.mi.com");
                    setCookie(instance, "yetAnotherServiceToken", a2.c, ".api.io.mi.com");
                    setCookie(instance, "yetAnotherServiceToken", URLEncoder.encode(a2.c, "UTF-8"), ".home.mi.com");
                }
                MiServiceTokenInfo a3 = CoreApi.a().a("xiaomihome");
                if (a3 != null) {
                    setCookie(instance, "serviceToken", URLEncoder.encode(a3.c, "UTF-8"), ".home.mi.com");
                }
            } catch (Exception unused) {
            }
        }
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        setCookie(instance, "locale", LocaleUtil.a(I), ".io.mi.com");
        setCookie(instance, "locale", LocaleUtil.a(I), ".home.mi.com");
        setCookie(instance, "source", "web", ".api.io.mi.com");
        setCookie(instance, "region", I.getCountry(), ".api.io.mi.com");
        setCookie(instance, "channel", GlobalSetting.v, ".home.mi.com");
        if (Build.VERSION.SDK_INT >= 19) {
            setWebContentsDebuggingEnabled(true);
        }
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync();
        } else {
            CookieManager.getInstance().flush();
        }
    }

    /* access modifiers changed from: protected */
    public void setCookie(CookieManager cookieManager, String str, String str2, String str3) {
        if (str != null && str2 != null && str3 != null) {
            cookieManager.setCookie(str3, str + "=" + str2 + ";path=/;domain=" + str3);
            LogUtil.a(f21059a, "setCookie: domain: " + str3 + " ;name: " + str + " ;value: " + str2);
        }
    }

    protected static String getPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int indexOf = str.indexOf(63);
        return indexOf >= 0 ? str.substring(0, indexOf) : str;
    }

    protected static void runOnUiThread(Runnable runnable) {
        JsSdkUtils.a(runnable);
    }

    protected static void runOnUiThreadDelayed(Runnable runnable, long j) {
        JsSdkUtils.a(runnable, j);
    }

    public void setFixHorizontalSwipe(boolean z) {
        this.c = z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent a2;
        if (this.c && motionEvent.getAction() == 0 && (a2 = a((View) this)) != null) {
            a2.requestDisallowInterceptTouchEvent(true);
        }
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        ViewParent a2;
        if (this.c && z && (a2 = a((View) this)) != null) {
            a2.requestDisallowInterceptTouchEvent(false);
        }
        super.onOverScrolled(i, i2, z, z2);
    }

    private ViewParent a(View view) {
        ViewParent parent = view.getParent();
        if (parent == null) {
            return null;
        }
        return ((parent instanceof ViewPager) || (parent instanceof AbsListView) || (parent instanceof ScrollView) || (parent instanceof HorizontalScrollView) || !(parent instanceof View)) ? parent : a((View) parent);
    }
}
