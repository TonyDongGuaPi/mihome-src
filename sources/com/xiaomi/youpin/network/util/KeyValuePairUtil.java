package com.xiaomi.youpin.network.util;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.xiaomi.youpin.network.bean.FileType;
import com.xiaomi.youpin.network.bean.KeyValuePair;
import com.xiaomi.youpin.network.bean.NetCallback;
import com.xiaomi.youpin.network.bean.NetError;
import com.xiaomi.youpin.network.bean.NetResult;
import com.xiaomi.youpin.network.bean.ProgressRequestBody;
import com.xiaomi.youpin.network.bean.UploadFileInfo;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class KeyValuePairUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23663a = "&";
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

    public static Map<String, String> b(List<KeyValuePair> list) {
        HashMap hashMap = new HashMap();
        if (list == null || list.isEmpty()) {
            return hashMap;
        }
        for (KeyValuePair next : list) {
            hashMap.put(next.a(), next.b());
        }
        return hashMap;
    }

    public static RequestBody c(List<KeyValuePair> list) {
        FormBody.Builder builder = new FormBody.Builder();
        if (list == null || list.isEmpty()) {
            return builder.build();
        }
        for (KeyValuePair next : list) {
            String a2 = next.a();
            String b2 = next.b();
            if (!TextUtils.isEmpty(a2) && b2 != null) {
                try {
                    builder.add(a2, b2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.build();
    }

    @Nullable
    public static ProgressRequestBody a(UploadFileInfo uploadFileInfo, List<KeyValuePair> list, NetCallback<NetResult, NetError> netCallback) {
        String e = uploadFileInfo.e();
        if (TextUtils.isEmpty(e)) {
            return null;
        }
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (list != null) {
            for (KeyValuePair next : list) {
                type.addFormDataPart(next.a(), next.b());
            }
        }
        String b2 = uploadFileInfo.b();
        byte[] c = uploadFileInfo.c();
        if (c != null) {
            type.addFormDataPart(e, b2, RequestBody.create(a(uploadFileInfo.d(), e, ""), c));
            return new ProgressRequestBody(type.build(), netCallback);
        } else if (TextUtils.isEmpty(b2)) {
            return null;
        } else {
            type.addFormDataPart(e, b2, RequestBody.create(a(uploadFileInfo.d(), e, b2), new File(b2)));
            return new ProgressRequestBody(type.build(), netCallback);
        }
    }

    private static MediaType a(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            return MediaType.parse(str);
        }
        char c = 65535;
        int hashCode = str2.hashCode();
        if (hashCode != -265944121) {
            if (hashCode != 110986) {
                if (hashCode == 3076010 && str2.equals("data")) {
                    c = 1;
                }
            } else if (str2.equals("pic")) {
                c = 0;
            }
        } else if (str2.equals(FileType.USER_FILE)) {
            c = 2;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
                return MediaType.parse("image/jpeg");
            default:
                if (TextUtils.isEmpty(str3)) {
                    return MediaType.parse("application/octet-stream");
                }
                return MediaTypeUtil.a(str3, "UTF-8");
        }
    }
}
