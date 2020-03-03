package com.xiaomi.youpin.login.api;

import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.api.callback.BasePassportCallback;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.setting.LoginConstant;

public abstract class DefaultRefreshServiceTokenCallback<T> {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public LoginMiAccount f23398a;
    private MiServiceTokenInfo b;
    /* access modifiers changed from: private */
    public BasePassportCallback<T> c;

    public abstract void a(LoginMiAccount loginMiAccount, MiServiceTokenInfo miServiceTokenInfo, boolean z);

    public DefaultRefreshServiceTokenCallback(LoginMiAccount loginMiAccount, BasePassportCallback<T> basePassportCallback) {
        this.f23398a = loginMiAccount;
        if (this.f23398a != null) {
            this.b = loginMiAccount.a("passportapi");
        }
        this.c = basePassportCallback;
    }

    public void a() {
        if (this.f23398a == null) {
            this.c.a(-1, "loginMiAccount is null");
        } else if (this.b == null) {
            this.c.a(-2, "passportapi serviceToken is null");
        } else {
            a(this.f23398a, this.b, true);
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        AnonymousClass1 r0 = new AsyncCallback<MiServiceTokenInfo, ExceptionError>() {
            public void a(MiServiceTokenInfo miServiceTokenInfo) {
                MiServiceTokenInfo miServiceTokenInfo2 = new MiServiceTokenInfo("passportapi", miServiceTokenInfo.b, miServiceTokenInfo.c, miServiceTokenInfo.d, LoginConstant.a("passportapi"), miServiceTokenInfo.e);
                DefaultRefreshServiceTokenCallback.this.c.a(miServiceTokenInfo2);
                DefaultRefreshServiceTokenCallback.this.f23398a.a(miServiceTokenInfo2);
                DefaultRefreshServiceTokenCallback.this.a(DefaultRefreshServiceTokenCallback.this.f23398a, miServiceTokenInfo2, false);
            }

            public void a(ExceptionError exceptionError) {
                BasePassportCallback a2 = DefaultRefreshServiceTokenCallback.this.c;
                a2.a(-100, "refresh ServiceToken失败 " + exceptionError.a() + " " + exceptionError.b());
            }
        };
        if (this.f23398a.b()) {
            MiLoginApi.a("passportapi", this.b, false, (AsyncCallback<MiServiceTokenInfo, ExceptionError>) r0);
        } else {
            MiLoginApi.a("passportapi", this.f23398a.a(), this.f23398a.c(), false, r0);
        }
    }
}
