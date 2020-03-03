package com.xiaomi.smarthome.framework.config;

import android.content.Context;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.crypto.SHA1Util;

public class SHSetting {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f16477a = new Object();
    private static String b;

    public static void a() {
        synchronized (f16477a) {
            b = null;
        }
    }

    public static String a(boolean z) {
        String str;
        synchronized (f16477a) {
            if (b == null) {
                Context appContext = CommonApplication.getAppContext();
                boolean g = ServerCompact.g(appContext);
                String c = SystemApi.a().c(appContext);
                StringBuilder sb = new StringBuilder();
                sb.append(SystemApi.a().d().replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().f().replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().d(appContext).replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().g().replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().j().replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().a(appContext, g).replace("-", ""));
                sb.append("-");
                sb.append((g ? SHA1Util.b(c) : XMStringUtils.d(c)).replace("-", ""));
                sb.append("-");
                sb.append(CoreApi.a().s());
                b = sb.toString();
            }
            str = b;
        }
        return str;
    }
}
