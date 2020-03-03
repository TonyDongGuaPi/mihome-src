package com.xiaomi.youpin.login.api.manager;

import android.content.Context;
import com.xiaomi.youpin.login.LoginDependencyApi;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.manager.callback.BaseLoginCallback;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import java.util.List;

public abstract class CoreBaseLoginManager {
    protected Context c;
    protected int d = MiLoginApi.a().g();
    protected String e = MiLoginApi.a().d();
    protected List<String> f = MiLoginApi.a().e();
    protected List<String> g = MiLoginApi.a().r();
    protected boolean h;
    protected boolean i;

    public CoreBaseLoginManager(Context context) {
        this.c = context;
        LoginDependencyApi h2 = MiLoginApi.a().h();
        if (h2 != null) {
            this.i = h2.b();
        }
        this.h = MiLoginApi.a().j();
    }

    /* access modifiers changed from: protected */
    public void a(LoginMiAccount loginMiAccount, BaseLoginCallback baseLoginCallback) {
        baseLoginCallback.onLoginSuccess(loginMiAccount);
    }
}
