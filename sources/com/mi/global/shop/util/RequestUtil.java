package com.mi.global.shop.util;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.util.i;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.request.ExtendedAuthToken;
import com.mi.multimonitor.Request;
import com.mi.util.Coder;
import com.mi.util.Device;
import com.mi.util.MiToast;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.text.Typography;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestUtil {

    /* renamed from: a  reason: collision with root package name */
    public static String f7105a = null;
    private static ExtendedAuthToken b = null;
    private static final String c = "UTF-8";

    public static Map<String, String> a(Map<String, String> map, boolean z) {
        if (map == null) {
            map = new HashMap<>();
        }
        if (ShopApp.n()) {
            map.put("ot", "5");
        }
        map.put("compressed", "1");
        map.put("client_id", TextUtils.isEmpty(f7105a) ? "180100031022" : f7105a);
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
            stringBuffer.append((b == null || TextUtils.isEmpty(b.b)) ? "appsign" : b.b);
            map.put("app_sign", Coder.a(String.valueOf(stringBuffer)));
        }
        return map;
    }

    public static String a(Header[] headerArr) {
        StringBuilder sb = new StringBuilder();
        if (headerArr != null) {
            for (int i = 0; i < headerArr.length; i++) {
                if (headerArr[i].getName() != null && headerArr[i].getName().compareToIgnoreCase("Set-Cookie") == 0) {
                    sb.append(headerArr[i].getValue());
                    sb.append(i.b);
                }
            }
        }
        return sb.toString();
    }

    public static void a(Context context, JSONObject jSONObject) {
        if (ShopApp.j() && jSONObject != null) {
            MiToast.a(context, R.string.shop_error_network, 0);
            try {
                int i = jSONObject.getInt(Request.RESULT_CODE_KEY);
                String string = jSONObject.getString("errmsg");
                MiToast.a(context, (CharSequence) "Errno:" + String.valueOf(i) + ", msg:" + string, 1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static String a(Map<String, String> map) {
        return a(map, "UTF-8");
    }

    public static String a(Map<String, String> map, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry next : map.entrySet()) {
                sb.append(URLEncoder.encode((String) next.getKey(), str));
                sb.append('=');
                String str2 = (String) next.getValue();
                if (str2 == null) {
                    str2 = "";
                }
                sb.append(URLEncoder.encode(str2, str));
                sb.append(Typography.c);
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding not supported: " + str, e);
        }
    }
}
