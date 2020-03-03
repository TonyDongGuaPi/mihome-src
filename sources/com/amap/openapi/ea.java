package com.amap.openapi;

import com.amap.location.common.log.ALLog;
import com.amap.location.common.network.HttpRequest;
import com.amap.location.common.network.HttpResponse;
import com.amap.location.common.network.IHttpClient;
import com.amap.location.common.util.d;
import com.amap.location.security.Core;
import java.util.HashMap;

public class ea {
    public static boolean a(IHttpClient iHttpClient, String str, byte[] bArr, int i) {
        try {
            byte[] a2 = d.a(bArr);
            if (a2 != null) {
                if (a2.length != 0) {
                    byte[] xxt = Core.xxt(a2, 1);
                    if (xxt != null) {
                        if (xxt.length != 0) {
                            if (iHttpClient != null) {
                                HashMap hashMap = new HashMap();
                                hashMap.put("ext", "120");
                                HttpRequest httpRequest = new HttpRequest();
                                httpRequest.f4591a = str;
                                httpRequest.b = hashMap;
                                httpRequest.c = xxt;
                                httpRequest.d = i;
                                HttpResponse a3 = iHttpClient.a(httpRequest);
                                if (a3 != null) {
                                    if (a3.c != null) {
                                        return a3.f4592a == 200 && "true".equals(new String(a3.c, "UTF-8"));
                                    }
                                }
                            }
                        }
                    }
                    ALLog.d("HttpRequestHelper", "xxt is null");
                    return false;
                }
            }
            ALLog.d("HttpRequestHelper", "gzip is null");
        } catch (Throwable unused) {
        }
        return false;
    }
}
