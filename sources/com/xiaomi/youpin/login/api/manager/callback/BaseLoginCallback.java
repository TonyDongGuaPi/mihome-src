package com.xiaomi.youpin.login.api.manager.callback;

import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import java.util.Map;

public interface BaseLoginCallback {
    void onLoginFail(int i, String str, Map<String, String> map);

    void onLoginSuccess(LoginMiAccount loginMiAccount);
}
