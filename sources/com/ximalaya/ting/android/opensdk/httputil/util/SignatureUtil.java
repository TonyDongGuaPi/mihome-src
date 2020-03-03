package com.ximalaya.ting.android.opensdk.httputil.util;

import com.alipay.sdk.sys.a;
import java.util.Map;
import java.util.TreeMap;

public class SignatureUtil {
    public static String a(String str, Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry next : map.entrySet()) {
            stringBuffer.append((String) next.getKey());
            stringBuffer.append("=");
            stringBuffer.append((String) next.getValue());
            stringBuffer.append(a.b);
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        try {
            return CrypterUtil.a(BASE64Encoder.a(stringBuffer.toString()), str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String b(String str, Map<String, String> map) {
        TreeMap treeMap = new TreeMap();
        treeMap.putAll(map);
        return a(str, treeMap);
    }
}
