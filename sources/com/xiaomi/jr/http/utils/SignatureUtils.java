package com.xiaomi.jr.http.utils;

import com.alipay.sdk.sys.a;
import com.xiaomi.jr.common.utils.HashUtils;
import com.xiaomi.jr.http.encoding.UrlEncoder;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

public class SignatureUtils {
    public static String a(Map<String, String> map, String str, String str2) {
        String str3;
        String str4;
        String upperCase = str2.toUpperCase();
        try {
            URL url = new URL(str);
            str3 = url.getHost().toLowerCase();
            try {
                str4 = url.getPath();
                try {
                    int port = url.getPort();
                    if (!(port == -1 || port == 80 || port == 443)) {
                        str3 = str3 + ":" + port;
                    }
                } catch (MalformedURLException unused) {
                }
            } catch (MalformedURLException unused2) {
                str4 = null;
            }
        } catch (MalformedURLException unused3) {
            str3 = null;
            str4 = null;
        }
        TreeMap treeMap = new TreeMap(map);
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry entry : treeMap.entrySet()) {
                if (!((String) entry.getKey()).equals("sign")) {
                    String a2 = UrlEncoder.a((String) entry.getKey());
                    String a3 = UrlEncoder.a((String) entry.getValue());
                    sb.append(a2);
                    sb.append("=");
                    sb.append(a3);
                    sb.append(a.b);
                }
            }
            sb.append("secret=");
            sb.append("ruyW+hhS8TbCFk09GZBzwHB3Ezih27VUUEqMLqQjGmo=");
            return HashUtils.a(upperCase + 10 + str3 + 10 + str4 + 10 + sb);
        } catch (Exception unused4) {
            return null;
        }
    }
}
