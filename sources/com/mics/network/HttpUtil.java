package com.mics.network;

import android.support.annotation.WorkerThread;
import com.alipay.sdk.sys.a;
import com.mics.network.ProgressRequestBody;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONObject;

public class HttpUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7768a = "application/json";
    private static final MediaType b = MediaType.parse("application/json; charset=utf-8");
    private static AtomicInteger c = new AtomicInteger();

    public static void a(String str, HashMap<String, Object> hashMap, GoCallback goCallback) {
        GoHttp.a(b(str, hashMap), str + System.currentTimeMillis(), goCallback);
    }

    @WorkerThread
    public static String a(String str, HashMap<String, Object> hashMap) {
        return GoHttp.b(b(str, hashMap));
    }

    private static Request b(String str, HashMap<String, Object> hashMap) {
        Request.Builder builder = new Request.Builder();
        if (hashMap != null && hashMap.entrySet().size() > 0) {
            StringBuilder sb = new StringBuilder(str + "?");
            for (Map.Entry next : hashMap.entrySet()) {
                if (next.getValue() instanceof String) {
                    sb.append((String) next.getKey());
                    sb.append("=");
                    sb.append(next.getValue());
                    sb.append(a.b);
                } else if (next.getValue() instanceof List) {
                    for (Object next2 : (List) next.getValue()) {
                        sb.append((String) next.getKey());
                        sb.append("=");
                        sb.append(next2.toString());
                        sb.append(a.b);
                    }
                }
            }
            String sb2 = sb.toString();
            str = sb2.substring(0, sb2.length() - 1);
        }
        builder.url(str);
        builder.headers(a());
        return builder.build();
    }

    public static void b(String str, HashMap<String, Object> hashMap, GoCallback goCallback) {
        a(str, new JSONObject(hashMap).toString(), goCallback);
    }

    public static void a(String str, String str2, GoCallback goCallback) {
        GoHttp.a(b(str, str2), str + System.currentTimeMillis(), goCallback);
    }

    @WorkerThread
    public static String a(String str, String str2) {
        return GoHttp.b(b(str, str2));
    }

    private static Request b(String str, String str2) {
        Request.Builder builder = new Request.Builder();
        builder.url(str);
        builder.headers(a());
        builder.post(RequestBody.create(b, str2));
        return builder.build();
    }

    @WorkerThread
    public static String a(String str, File file, ProgressRequestBody.ProgressListener progressListener) {
        if (file == null || !file.exists()) {
            return null;
        }
        return a(str, new ProgressRequestBody(MultipartBody.FORM, file, progressListener));
    }

    private static String a(String str, ProgressRequestBody progressRequestBody) {
        int b2 = b();
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        type.addFormDataPart("file", String.format("android_image_%s_%s.jpg", new Object[]{Long.valueOf(System.currentTimeMillis()), Integer.valueOf(b2)}), progressRequestBody);
        return GoHttp.b(new Request.Builder().headers(a()).url(str).post(type.build()).build());
    }

    private static Headers a() {
        Headers.Builder builder = new Headers.Builder();
        builder.add("Content-Type", "application/json");
        return builder.build();
    }

    private static int b() {
        int i;
        int i2;
        do {
            i = c.get();
            i2 = i + 1;
            if (i2 > 16777215) {
                i2 = 1;
            }
        } while (!c.compareAndSet(i, i2));
        return i;
    }
}
