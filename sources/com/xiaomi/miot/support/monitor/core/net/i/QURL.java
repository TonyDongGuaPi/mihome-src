package com.xiaomi.miot.support.monitor.core.net.i;

import com.xiaomi.miot.support.monitor.core.net.impl.AopURL;
import com.xiaomi.miot.support.monitor.core.tasks.TaskManager;
import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public class QURL {
    public static URLConnection a(URL url) throws IOException {
        return a() ? AopURL.a(url) : url.openConnection();
    }

    public static URLConnection a(URL url, Proxy proxy) throws IOException {
        return a() ? AopURL.a(url, proxy) : url.openConnection(proxy);
    }

    private static boolean a() {
        return TaskManager.a().b("net");
    }
}
