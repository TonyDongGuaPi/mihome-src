package com.mi.global.shop.base.service;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.mi.global.shop.base.account.AccountListener;

public interface LoginManagerService extends IProvider {
    void a();

    void a(AccountListener accountListener);

    String b();

    void b(AccountListener accountListener);

    boolean c();
}
