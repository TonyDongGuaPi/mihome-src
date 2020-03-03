package com.alipay.deviceid.module.x;

import android.util.Log;
import com.xiaomi.woltest.IOUtil;
import java.io.Closeable;
import java.io.IOException;

public final class bc {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.e(IOUtil.f23142a, "", e);
            }
        }
    }
}
