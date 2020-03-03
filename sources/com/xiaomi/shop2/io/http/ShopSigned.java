package com.xiaomi.shop2.io.http;

import com.alipay.sdk.sys.a;
import com.xiaomi.mishopsdk.util.Log;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ShopSigned {
    public static native String getSigned(String str);

    public static synchronized String getSignedStr(ConcurrentHashMap<String, String> concurrentHashMap) {
        synchronized (ShopSigned.class) {
            String str = "";
            for (Map.Entry next : concurrentHashMap.entrySet()) {
                str = str + URLEncoder.encode((String) next.getKey()) + "=" + URLEncoder.encode((String) next.getValue()) + a.b;
            }
            if (str.endsWith(a.b)) {
                str = str.substring(0, str.length() - 1);
            }
            try {
                String signed = getSigned(str);
                return signed;
            } catch (Exception e) {
                Log.d("ShopSigned e:", e.getMessage());
                return "";
            } catch (Throwable th) {
                Log.d("ShopSigned t:", th.getMessage());
                return "";
            }
        }
    }
}
