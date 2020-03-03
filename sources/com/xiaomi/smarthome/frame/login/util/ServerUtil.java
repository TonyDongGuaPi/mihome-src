package com.xiaomi.smarthome.frame.login.util;

import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import java.util.Locale;

public class ServerUtil {
    public static boolean a() {
        return ServerCompact.c(CoreApi.a().F());
    }

    public static String b() {
        ServerBean F = CoreApi.a().F();
        if (F != null) {
            return F.b;
        }
        Locale I = CoreApi.a().I();
        if (I != null) {
            return I.getCountry();
        }
        return Locale.getDefault().getCountry();
    }
}
