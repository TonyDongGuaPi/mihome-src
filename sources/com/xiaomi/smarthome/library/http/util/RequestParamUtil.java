package com.xiaomi.smarthome.library.http.util;

import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.xiaomi.smarthome.library.apache.http.NameValuePair;
import com.xiaomi.smarthome.library.apache.http.client.utils.URLEncodedUtils;
import java.util.List;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RequestParamUtil {
    public static String a(String str, List<NameValuePair> list) {
        if (list == null || list.isEmpty()) {
            return str;
        }
        String a2 = URLEncodedUtils.a((List<? extends NameValuePair>) list, "UTF-8");
        if (!str.contains("?")) {
            return str + "?" + a2;
        }
        return str + a.b + a2;
    }

    public static RequestBody a(List<NameValuePair> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (NameValuePair next : list) {
            String a2 = next.a();
            String b = next.b();
            if (!TextUtils.isEmpty(a2) && b != null) {
                builder.add(a2, b);
            }
        }
        return builder.build();
    }
}
