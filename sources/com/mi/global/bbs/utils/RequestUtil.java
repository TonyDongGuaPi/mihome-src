package com.mi.global.bbs.utils;

import android.text.TextUtils;
import com.alipay.sdk.util.i;
import com.mi.global.bbs.request.ExtendedAuthToken;
import com.mi.util.Coder;
import com.mi.util.Device;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.text.Typography;
import org.apache.http.Header;

public class RequestUtil {
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    private static ExtendedAuthToken mToken;
    public static String thirdPartyClientId;

    public static Map<String, String> getParams(Map<String, String> map, boolean z) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put("compressed", "1");
        map.put("client_id", TextUtils.isEmpty(thirdPartyClientId) ? "180100031022" : thirdPartyClientId);
        map.put("display_density", String.valueOf(Device.d));
        if (z) {
            map.put("app_rdm", String.valueOf(System.currentTimeMillis()));
            ArrayList arrayList = new ArrayList();
            StringBuffer stringBuffer = new StringBuffer();
            for (String add : map.keySet()) {
                arrayList.add(add);
            }
            Collections.sort(arrayList);
            for (int i = 0; i < arrayList.size(); i++) {
                Iterator<String> it = map.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String next = it.next();
                    if (TextUtils.equals((CharSequence) arrayList.get(i), next)) {
                        if (!TextUtils.isEmpty(map.get(next))) {
                            stringBuffer.append(map.get(next));
                        }
                    }
                }
            }
            stringBuffer.append((mToken == null || TextUtils.isEmpty(mToken.security)) ? "appsign" : mToken.security);
            map.put("app_sign", Coder.a(String.valueOf(stringBuffer)));
        }
        return map;
    }

    public static String showResponseCookies(Header[] headerArr) {
        StringBuilder sb = new StringBuilder();
        if (headerArr != null) {
            for (int i = 0; i < headerArr.length; i++) {
                if (headerArr[i].getName() != null && headerArr[i].getName().compareToIgnoreCase("Set-Cookie") == 0) {
                    String value = headerArr[i].getValue();
                    sb.append(value + i.b);
                }
            }
        }
        return sb.toString();
    }

    public static String encodeParameters(Map<String, String> map) {
        return encodeParameters(map, "UTF-8");
    }

    public static String encodeParameters(Map<String, String> map, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry next : map.entrySet()) {
                sb.append(URLEncoder.encode((String) next.getKey(), str));
                sb.append('=');
                sb.append(URLEncoder.encode((String) next.getValue(), str));
                sb.append(Typography.c);
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding not supported: " + str, e);
        }
    }
}
