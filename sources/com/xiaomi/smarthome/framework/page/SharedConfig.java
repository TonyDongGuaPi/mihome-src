package com.xiaomi.smarthome.framework.page;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedConfig {

    /* renamed from: a  reason: collision with root package name */
    Context f16922a;
    SharedPreferences b;

    public SharedConfig(Context context) {
        this.f16922a = context;
        this.b = context.getSharedPreferences("welcome_config", 0);
    }

    public SharedPreferences a() {
        return this.b;
    }
}
