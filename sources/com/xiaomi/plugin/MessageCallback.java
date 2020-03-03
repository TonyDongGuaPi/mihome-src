package com.xiaomi.plugin;

import android.content.Intent;

public interface MessageCallback {
    void onFailure(int i, String str);

    void onSuccess(Intent intent);
}
