package com.xiaomi.jr.mipay.common.http;

import android.text.TextUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import java.io.IOException;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

public class HttpUtils {
    public static void a(String str) {
        MifiLog.b("MipayHttp", str);
    }

    public static boolean a(Request request) {
        return TextUtils.equals(request.method(), "POST");
    }

    public static String b(Request request) {
        try {
            if (request.body() == null) {
                return null;
            }
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (IOException unused) {
            return null;
        }
    }

    public static String a(Response response) {
        try {
            BufferedSource source = response.body().source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            if (response.body().contentLength() != 0) {
                return buffer.clone().readUtf8();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
