package com.sina.weibo.sdk.web.param;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.web.WebRequestType;

public class AuthWebViewRequestParam extends BaseWebViewRequestParam {
    /* access modifiers changed from: protected */
    public void a(Bundle bundle) {
    }

    public void a(String str) {
    }

    /* access modifiers changed from: protected */
    public void b(Bundle bundle) {
    }

    public AuthWebViewRequestParam() {
    }

    public AuthWebViewRequestParam(AuthInfo authInfo, WebRequestType webRequestType, String str, String str2, String str3, Context context) {
        super(authInfo, webRequestType, str, str2, str3, context);
    }

    public boolean a() {
        return super.a();
    }

    public String b() {
        return d().getUrl();
    }
}
