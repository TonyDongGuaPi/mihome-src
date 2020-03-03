package com.xiaomi.jr.account;

import android.content.Context;

public interface IWebLoginProcessor {

    public interface WebLoginListener {
        void onWebLoginResult(boolean z, String str);
    }

    void a(Context context, String str, WebLoginListener webLoginListener);
}
