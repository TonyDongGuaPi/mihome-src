package com.ximalaya.ting.android.opensdk.httputil;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import okhttp3.Response;

public class BaseResponse {

    /* renamed from: a  reason: collision with root package name */
    private Response f1986a;

    public void e() {
    }

    public BaseResponse(Response response) {
        this.f1986a = response;
    }

    public int a() {
        return this.f1986a.code();
    }

    public String b() {
        return this.f1986a.message();
    }

    public List<String> a(String str) {
        return this.f1986a.headers(str);
    }

    public String c() throws IOException {
        try {
            return this.f1986a.body().string();
        } catch (Exception e) {
            if (e instanceof IOException) {
                throw e;
            }
            throw new IOException("cause:" + e.getMessage());
        }
    }

    public Reader d() throws IOException {
        try {
            return this.f1986a.body().charStream();
        } catch (Exception e) {
            if (e instanceof IOException) {
                throw e;
            }
            throw new IOException("cause:" + e.getMessage());
        }
    }

    public static Object a(Type type, String str) throws Exception {
        try {
            return new Gson().fromJson(str, type);
        } catch (Throwable th) {
            th.printStackTrace();
            throw new RuntimeException(th);
        }
    }

    public Object a(Type type) throws Exception {
        return new Gson().fromJson(this.f1986a.body().charStream(), type);
    }
}
