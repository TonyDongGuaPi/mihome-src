package com.xiaomi.youpin.login.api.manager.callback;

import com.xiaomi.youpin.login.entity.oauth.LoginMiByOAuthResult;

public interface OAuthLoginCallback {
    void a(int i, String str);

    void a(LoginMiByOAuthResult loginMiByOAuthResult);
}
