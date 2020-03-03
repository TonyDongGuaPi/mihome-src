package com.xiaomi.ai.utils;

import android.os.Environment;
import android.util.Log;
import com.xiaomi.ai.utils.o;
import java.io.File;

final class p implements o.a {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f9960a = new File(Environment.getExternalStorageDirectory(), "log_on_adb").exists();

    p() {
    }

    public void a(int i, String str, String str2) {
        if (this.f9960a) {
            Log.println(i, str, str2);
        }
    }
}
