package com.mi.global.shop.componentService;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.mi.account.LoginManager;
import com.mi.global.shop.base.account.AccountListener;
import com.mi.global.shop.base.service.LoginManagerService;
import com.mi.global.shop.xmsf.account.LoginManager;

@Route(path = "/shopSdk/LoginManagerService")
public class LoginManagerServiceImpl implements LoginManagerService {
    public void init(Context context) {
    }

    public void a() {
        LoginManager.u().q();
    }

    public String b() {
        return LoginManager.u().r();
    }

    public void a(AccountListener accountListener) {
        LoginManager.u().a((LoginManager.AccountListener) accountListener);
    }

    public void b(AccountListener accountListener) {
        com.mi.global.shop.xmsf.account.LoginManager.u().b((LoginManager.AccountListener) accountListener);
    }

    public boolean c() {
        return com.mi.global.shop.xmsf.account.LoginManager.u().i();
    }
}
