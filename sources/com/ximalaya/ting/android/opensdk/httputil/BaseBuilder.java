package com.ximalaya.ting.android.opensdk.httputil;

import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.constants.ConstantsOpenSdk;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.httputil.util.SignatureUtil;
import com.ximalaya.ting.android.opensdk.httputil.util.Util;
import com.ximalaya.ting.android.opensdk.util.Logger;
import java.io.File;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class BaseBuilder {
    private static Request.Builder a(Request.Builder builder) {
        return builder;
    }

    public static Request.Builder a(String str) throws XimalayaException {
        return a(str, (Map<String, String>) null);
    }

    public static Request.Builder a(String str, Map<String, String> map) throws XimalayaException {
        if (ConstantsOpenSdk.b || !TextUtils.isEmpty(str)) {
            if (map != null && !map.isEmpty()) {
                str = str + "?" + Util.b(Util.c(map));
            }
            Logger.c("url123", str);
            try {
                return a(new Request.Builder().url(str));
            } catch (Exception e) {
                e.printStackTrace();
                throw new XimalayaException(1012, e.getMessage());
            }
        } else {
            throw XimalayaException.getExceptionByCode(1001);
        }
    }

    public static Request.Builder a(String str, Map<String, String> map, String str2) throws XimalayaException {
        if (ConstantsOpenSdk.b || !TextUtils.isEmpty(str)) {
            String b = SignatureUtil.b(str2, map);
            if (!TextUtils.isEmpty(b)) {
                map.put(DTransferConstants.n, b);
                return a(str, map);
            }
            throw XimalayaException.getExceptionByCode(1002);
        }
        throw XimalayaException.getExceptionByCode(1001);
    }

    public static Request.Builder b(String str, Map<String, String> map, String str2) throws XimalayaException {
        if (ConstantsOpenSdk.b || !TextUtils.isEmpty(str)) {
            String b = SignatureUtil.b(str2, map);
            if (!TextUtils.isEmpty(b)) {
                map.put(DTransferConstants.n, b);
                return b(str, map);
            }
            throw XimalayaException.getExceptionByCode(1002);
        }
        throw XimalayaException.getExceptionByCode(1001);
    }

    public static Request.Builder a(String str, String str2, String str3) throws XimalayaException {
        if (ConstantsOpenSdk.b || !TextUtils.isEmpty(str)) {
            return a(new Request.Builder().url(str).post(RequestBody.create(MediaType.parse(str3), str2)));
        }
        throw XimalayaException.getExceptionByCode(1001);
    }

    public static Request.Builder a(String str, byte[] bArr, String str2) throws XimalayaException {
        if (ConstantsOpenSdk.b || !TextUtils.isEmpty(str)) {
            return a(new Request.Builder().url(str).post(RequestBody.create(MediaType.parse(str2), bArr)));
        }
        throw XimalayaException.getExceptionByCode(1001);
    }

    public static Request.Builder b(String str, Map<String, String> map) throws XimalayaException {
        if (ConstantsOpenSdk.b || !TextUtils.isEmpty(str)) {
            FormBody.Builder builder = new FormBody.Builder();
            if (map == null || map.size() <= 0) {
                throw XimalayaException.getExceptionByCode(1003);
            }
            for (Map.Entry next : map.entrySet()) {
                if (next.getValue() != null) {
                    builder.add((String) next.getKey(), (String) next.getValue());
                }
            }
            return a(new Request.Builder().url(str).post(builder.build()));
        }
        throw XimalayaException.getExceptionByCode(1001);
    }

    public static Request.Builder b(String str, String str2, String str3) throws XimalayaException {
        if (ConstantsOpenSdk.b || !TextUtils.isEmpty(str)) {
            return a(new Request.Builder().url(str).put(RequestBody.create(MediaType.parse(str3), str2)));
        }
        throw XimalayaException.getExceptionByCode(1001);
    }

    public static Request.Builder c(String str, Map<String, String> map) throws XimalayaException {
        if (ConstantsOpenSdk.b || !TextUtils.isEmpty(str)) {
            FormBody.Builder builder = new FormBody.Builder();
            if (map == null || map.size() <= 0) {
                throw XimalayaException.getExceptionByCode(1003);
            }
            for (Map.Entry next : map.entrySet()) {
                if (next.getValue() != null) {
                    builder.add((String) next.getKey(), (String) next.getValue());
                }
            }
            return a(new Request.Builder().url(str).put(builder.build()));
        }
        throw XimalayaException.getExceptionByCode(1001);
    }

    public static Request.Builder a(String str, File file) throws XimalayaException {
        if (ConstantsOpenSdk.b || !TextUtils.isEmpty(str)) {
            return a(new Request.Builder().url(str).post(RequestBody.create((MediaType) null, file)));
        }
        throw XimalayaException.getExceptionByCode(1001);
    }

    public static Request.Builder a(String str, byte[] bArr) throws XimalayaException {
        if (ConstantsOpenSdk.b || !TextUtils.isEmpty(str)) {
            return a(new Request.Builder().url(str).post(RequestBody.create((MediaType) null, bArr)));
        }
        throw XimalayaException.getExceptionByCode(1001);
    }

    public static Request.Builder a(String str, String str2) throws XimalayaException {
        if (ConstantsOpenSdk.b || !TextUtils.isEmpty(str)) {
            return a(new Request.Builder().url(str).post(RequestBody.create((MediaType) null, str2)));
        }
        throw XimalayaException.getExceptionByCode(1001);
    }

    public static RequestBody a(String str, Map<String, File> map, Map<String, String> map2) throws XimalayaException {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (String next : map2.keySet()) {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + next + "\""), RequestBody.create((MediaType) null, map2.get(next)));
        }
        for (String next2 : map.keySet()) {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + next2 + "\"; filename=\"" + map.get(next2).getName() + "\""), RequestBody.create(MediaType.parse(str), map.get(next2)));
        }
        return builder.build();
    }

    public static Request.Builder a(String str, String str2, byte[] bArr, Map<String, String> map) throws XimalayaException {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (String next : map.keySet()) {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + next + "\""), RequestBody.create((MediaType) null, map.get(next)));
        }
        builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + "data" + "\"; filename=\"\""), RequestBody.create(MediaType.parse(str2), bArr));
        return a(new Request.Builder().url(str).post(builder.build()));
    }
}
