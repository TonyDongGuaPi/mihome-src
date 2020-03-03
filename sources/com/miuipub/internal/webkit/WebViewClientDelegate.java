package com.miuipub.internal.webkit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import com.miuipub.internal.hybrid.provider.AbsWebView;
import com.miuipub.internal.util.UrlResolverHelper;
import miuipub.util.UrlResolver;

public class WebViewClientDelegate {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8316a = 1;
    public static final int b = 2;
    private static final int c = -1;
    private boolean d;
    private boolean e;
    private DeviceAccountLogin f;
    private LoginState g;

    private enum LoginState {
        LOGIN_START,
        LOGIN_INPROGRESS,
        LOGIN_FINISHED
    }

    public WebViewClientDelegate() {
        this(-1);
    }

    public WebViewClientDelegate(int i) {
        this(i, -1);
    }

    public WebViewClientDelegate(int i, int i2) {
        this.g = LoginState.LOGIN_FINISHED;
        int i3 = (i & i2) | ((i2 ^ -1) & -1);
        boolean z = false;
        this.d = (i3 & 1) != 0;
        this.e = (i3 & 2) != 0 ? true : z;
    }

    public boolean a(AbsWebView absWebView, String str) {
        if (!this.d || !UrlResolverHelper.a(str)) {
            return false;
        }
        Context l = absWebView.l();
        PackageManager packageManager = l.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.addCategory("android.intent.category.BROWSABLE");
        ResolveInfo a2 = UrlResolver.a(l, packageManager, intent);
        if (a2 == null) {
            return false;
        }
        if (a2.activityInfo == null) {
            return true;
        }
        l.startActivity(intent);
        return true;
    }

    public void a(AbsWebView absWebView, String str, Bitmap bitmap) {
        if (this.e && this.g == LoginState.LOGIN_START) {
            this.g = LoginState.LOGIN_INPROGRESS;
        }
    }

    public void b(AbsWebView absWebView, String str) {
        if (this.e && this.g == LoginState.LOGIN_INPROGRESS) {
            this.g = LoginState.LOGIN_FINISHED;
            this.f.d();
        }
    }

    public void a(AbsWebView absWebView, String str, String str2, String str3) {
        if (this.e) {
            Activity activity = (Activity) absWebView.m().getContext();
            if (this.f == null) {
                this.f = new DefaultDeviceAccountLogin(activity, absWebView);
            }
            if (!absWebView.f()) {
                this.g = LoginState.LOGIN_START;
                absWebView.a(4);
                this.f.a(str, str2, str3);
            } else if (absWebView.e()) {
                absWebView.g();
            } else {
                activity.finish();
            }
        }
    }
}
