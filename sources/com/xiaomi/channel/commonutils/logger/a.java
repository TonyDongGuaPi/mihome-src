package com.xiaomi.channel.commonutils.logger;

import android.util.Log;
import com.xiaomi.youpin.UserMode;

public class a implements LoggerInterface {

    /* renamed from: a  reason: collision with root package name */
    private String f10047a = UserMode.f23179a;

    public void a(String str) {
        this.f10047a = str;
    }

    public void a(String str, Throwable th) {
        Log.v(this.f10047a, str, th);
    }

    public void b(String str) {
        Log.v(this.f10047a, str);
    }
}
