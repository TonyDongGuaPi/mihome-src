package com.xiaomi.youpin.core.net;

import android.text.TextUtils;
import com.xiaomi.youpin.core.api.ProgressRequestBody;
import com.xiaomi.youpin.network.bean.FileType;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class KeyValuePairUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23340a = "&";
    private static final String b = "=";

    public static String a(String str, List<KeyValuePair> list) {
        if (list == null || list.isEmpty()) {
            return str;
        }
        String a2 = a((List<? extends KeyValuePair>) list, "UTF-8");
        if (!str.contains("?")) {
            return str + "?" + a2;
        }
        return str + "&" + a2;
    }

    private static String a(List<? extends KeyValuePair> list, String str) {
        StringBuilder sb = new StringBuilder();
        for (KeyValuePair keyValuePair : list) {
            String a2 = a(keyValuePair.a(), str);
            String b2 = keyValuePair.b();
            String a3 = b2 != null ? a(b2, str) : "";
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(a2);
            sb.append("=");
            sb.append(a3);
        }
        return sb.toString();
    }

    private static String a(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Headers a(List<KeyValuePair> list) {
        if (list == null) {
            return null;
        }
        Headers.Builder builder = new Headers.Builder();
        for (KeyValuePair next : list) {
            builder.add(next.a(), next.b());
        }
        return builder.build();
    }

    public static RequestBody a(List<KeyValuePair> list, boolean z, NetCallback<NetResult, NetError> netCallback) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (z) {
            MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
            for (KeyValuePair next : list) {
                String a2 = next.a();
                String b2 = next.b();
                String c = next.c();
                if (("file".equals(a2) || "pic".equals(a2) || FileType.USER_FILE.equals(a2)) && !TextUtils.isEmpty(c)) {
                    String str = "application/octet-stream";
                    if ("pic".equals(a2)) {
                        str = "image/jpeg";
                    } else if (FileType.USER_FILE.equals(a2)) {
                        str = "image/jpeg";
                    }
                    type.addFormDataPart(a2, b2, RequestBody.create(MediaType.parse(str), new File(c)));
                } else if (!TextUtils.isEmpty(a2) && b2 != null) {
                    type.addFormDataPart(a2, b2);
                }
            }
            return new ProgressRequestBody(type.build(), netCallback);
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (KeyValuePair next2 : list) {
            String a3 = next2.a();
            String b3 = next2.b();
            if (!TextUtils.isEmpty(a3) && b3 != null) {
                builder.add(a3, b3);
            }
        }
        return builder.build();
    }
}
