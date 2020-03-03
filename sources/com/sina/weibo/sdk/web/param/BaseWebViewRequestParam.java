package com.sina.weibo.sdk.web.param;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.web.BaseWebViewRequestData;
import com.sina.weibo.sdk.web.WebRequestType;

public abstract class BaseWebViewRequestParam {

    /* renamed from: a  reason: collision with root package name */
    protected Context f8862a;
    private BaseWebViewRequestData b;
    private String c;

    public interface ExtraTaskCallback {
        void a(String str);

        void b(String str);
    }

    /* access modifiers changed from: protected */
    public abstract void a(Bundle bundle);

    public void a(ExtraTaskCallback extraTaskCallback) {
    }

    public abstract void a(String str);

    public boolean a() {
        return false;
    }

    public abstract String b();

    /* access modifiers changed from: protected */
    public abstract void b(Bundle bundle);

    public BaseWebViewRequestParam() {
    }

    public BaseWebViewRequestParam(AuthInfo authInfo, WebRequestType webRequestType, String str, String str2, String str3, Context context) {
        this(authInfo, webRequestType, str, 0, str2, str3, context);
    }

    public BaseWebViewRequestParam(AuthInfo authInfo, WebRequestType webRequestType, String str, int i, String str2, String str3, Context context) {
        this.b = new BaseWebViewRequestData(authInfo, webRequestType, str, i, str2, str3);
        this.f8862a = context;
        this.c = String.valueOf(System.currentTimeMillis());
    }

    public void a(Context context) {
        this.f8862a = context;
    }

    public Context c() {
        return this.f8862a;
    }

    public Bundle c(Bundle bundle) {
        if (this.b != null) {
            bundle.putSerializable("base", this.b);
            switch (this.b.getType()) {
                case DEFAULT:
                    bundle.putInt("type", 0);
                    break;
                case SHARE:
                    bundle.putInt("type", 1);
                    break;
                case AUTH:
                    bundle.putInt("type", 2);
                    break;
            }
            bundle.putString(WBConstants.w, this.c);
            a(bundle);
            return bundle;
        }
        throw new NullPointerException("构造方法错误，请使用全参数的构造方法构建");
    }

    public void d(Bundle bundle) {
        this.b = (BaseWebViewRequestData) bundle.getSerializable("base");
        this.c = bundle.getString(WBConstants.w);
        b(bundle);
    }

    public BaseWebViewRequestData d() {
        return this.b;
    }
}
