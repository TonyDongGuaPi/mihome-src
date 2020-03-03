package com.ximalaya.ting.android.opensdk.auth.component;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.auth.call.IXmlyAuthListener;
import com.ximalaya.ting.android.opensdk.auth.model.XmlyAuthInfo;

public final class c extends d {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1820a = "key_auth_info";
    public static final String b = "key_listener";
    private XmlyAuthInfo k;
    private IXmlyAuthListener l;
    private String m;

    public c(Context context) {
        super(context);
    }

    public final XmlyAuthInfo a() {
        return this.k;
    }

    public final void a(XmlyAuthInfo xmlyAuthInfo) {
        this.k = xmlyAuthInfo;
    }

    public final IXmlyAuthListener b() {
        return this.l;
    }

    public final void a(IXmlyAuthListener iXmlyAuthListener) {
        this.l = iXmlyAuthListener;
    }

    public final String c() {
        return this.m;
    }

    /* access modifiers changed from: protected */
    public final void a(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(f1820a);
        if (bundle2 != null) {
            this.k = XmlyAuthInfo.a(this.h, bundle2);
        }
        this.m = bundle.getString(b);
        if (!TextUtils.isEmpty(this.m)) {
            this.l = e.a(this.h).a(this.m);
        }
    }

    /* access modifiers changed from: protected */
    public final void b(Bundle bundle) {
        if (this.k != null) {
            bundle.putBundle(f1820a, this.k.i());
        }
        if (this.l != null) {
            e a2 = e.a(this.h);
            this.m = String.valueOf(System.currentTimeMillis());
            a2.a(this.m, this.l);
            bundle.putString(b, this.m);
        }
    }

    public final void a(Activity activity) {
        if (this.l != null) {
            this.l.a();
        }
        XmlyBrowserComponent.closeBrowser(activity, this.m);
    }
}
