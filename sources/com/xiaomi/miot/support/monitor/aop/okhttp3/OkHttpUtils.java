package com.xiaomi.miot.support.monitor.aop.okhttp3;

import java.util.Iterator;
import java.util.List;
import okhttp3.Interceptor;

public class OkHttpUtils {
    public static void a(List<Interceptor> list) {
        boolean z = false;
        try {
            Iterator<Interceptor> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next() instanceof NetWorkInterceptor) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (!z) {
                list.add(new NetWorkInterceptor());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
