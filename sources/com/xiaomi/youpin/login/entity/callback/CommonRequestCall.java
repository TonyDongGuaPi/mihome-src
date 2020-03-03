package com.xiaomi.youpin.login.entity.callback;

import com.xiaomi.youpin.login.entity.Error;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class CommonRequestCall<T> implements Callback {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23517a = "&&&START&&&";

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
                String string = response.body().string();
                if (!string.startsWith("&&&START&&&")) {
                    a(new Error(-1, "json format error"));
                }
                a(a(string.substring("&&&START&&&".length())));
            } catch (Exception e) {
                a(new Error(-1, e.getMessage()));
            }
        }
    }
}
