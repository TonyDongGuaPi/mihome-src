package com.xiaomi.youpin.login.entity.callback;

import com.xiaomi.youpin.login.entity.Error;
import com.xiaomi.youpin.login.entity.wx.YoupinWxAccessTokenData;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class YoupinWxAccessTokenRequestCall<T extends YoupinWxAccessTokenData> implements Callback {
    public abstract T a(String str);

    public abstract void a(Error error);

    public abstract void a(T t);

    public void onFailure(Call call, IOException iOException) {
        a(new Error(-1, iOException.getMessage()));
    }

    public void onResponse(Call call, Response response) throws IOException {
        if (response == null) {
            a(new Error(-1, "net response is null"));
        } else if (!response.isSuccessful()) {
            a(new Error(response.code(), ""));
        } else {
            try {
                YoupinWxAccessTokenData a2 = a(response.body().string());
                if (a2 == null) {
                    a(new Error(-1, "on Parse is null"));
                } else if (a2.f23528a != 0) {
                    a(new Error(-1, "code not success " + a2.f23528a + " desc " + a2.c));
                } else {
                    a(a2);
                }
            } catch (Exception e) {
                a(new Error(-1, e.getMessage()));
            }
        }
    }
}
