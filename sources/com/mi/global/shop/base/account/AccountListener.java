package com.mi.global.shop.base.account;

import com.mi.account.LoginManager;

public interface AccountListener extends LoginManager.AccountListener {
    void onUserInfoUpdate(String str, String str2, String str3, int i, String str4);
}
