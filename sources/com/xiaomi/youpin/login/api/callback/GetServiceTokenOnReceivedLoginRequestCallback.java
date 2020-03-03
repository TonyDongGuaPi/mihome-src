package com.xiaomi.youpin.login.api.callback;

import android.content.Intent;
import com.xiaomi.passport.servicetoken.ServiceTokenResult;

public interface GetServiceTokenOnReceivedLoginRequestCallback {
    void a(Intent intent);

    void a(ServiceTokenResult.ErrorCode errorCode, String str);

    void a(Exception exc);

    void a(String str);
}
