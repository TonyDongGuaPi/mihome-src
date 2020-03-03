package com.ximalaya.ting.android.opensdk.httputil.util;

import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

public class Util {
    public static JSONObject a(Map<String, Object> map) {
        return new JSONObject(map);
    }

    public static String b(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        TreeMap treeMap = new TreeMap();
        treeMap.putAll(map);
        for (Map.Entry entry : treeMap.entrySet()) {
            String str = (String) entry.getValue();
            if (str != null) {
                sb.append((String) entry.getKey());
                sb.append("=");
                sb.append(str);
                sb.append(a.b);
            } else if (entry.getKey() != null) {
                map.remove(entry.getKey());
            }
        }
        if (!map.isEmpty() && sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0037, code lost:
        r0 = java.net.URLEncoder.encode((java.lang.String) r1.getValue(), "UTF-8");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0039, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003a, code lost:
        r1.printStackTrace();
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0040 A[EDGE_INSN: B:17:0x0040->B:14:0x0040 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x000e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map<java.lang.String, java.lang.String> c(java.util.Map<java.lang.String, java.lang.String> r4) {
        /*
            java.util.Set r0 = r4.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0008:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0040
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r3 = "q"
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto L_0x002a
            java.lang.String r3 = "tag_name"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x0008
        L_0x002a:
            r0 = 0
            java.lang.Object r1 = r1.getValue()     // Catch:{ UnsupportedEncodingException -> 0x0039 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ UnsupportedEncodingException -> 0x0039 }
            java.lang.String r3 = "UTF-8"
            java.lang.String r1 = java.net.URLEncoder.encode(r1, r3)     // Catch:{ UnsupportedEncodingException -> 0x0039 }
            r0 = r1
            goto L_0x003d
        L_0x0039:
            r1 = move-exception
            r1.printStackTrace()
        L_0x003d:
            r4.put(r2, r0)
        L_0x0040:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.httputil.util.Util.c(java.util.Map):java.util.Map");
    }

    public static String a(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return stringBuffer.toString();
            }
            stringBuffer.append(readLine.trim());
        }
    }

    public static boolean a(String str) {
        return !TextUtils.isEmpty(str) && !"null".equals(str);
    }

    public static Map<String, String> d(Map<String, Object> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            hashMap.put(next.getKey(), String.valueOf(next.getValue()));
        }
        return hashMap;
    }
}
