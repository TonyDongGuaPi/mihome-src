package com.xiaomi.jr.hybrid;

import android.app.Activity;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xiaomi.jr.hybrid.NativeInterface;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HybridUtils {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f1442a = false;
    private static final Executor b = Executors.newCachedThreadPool();
    private static final Gson c = new GsonBuilder().disableHtmlEscaping().create();

    public static void a(Request request, int i, Object obj) {
        a(request, i, obj, (NativeInterface.Callback) null);
    }

    public static void a(Request request, int i, Object obj, NativeInterface.Callback callback) {
        if (callback != null) {
            HybridCallbackManager.a(callback);
        }
        request.a().b().a(i, obj, callback);
    }

    public static Object a(Request request, int i) {
        return request.a().b().b(i);
    }

    public static Context a(Request request) {
        return request.a().d();
    }

    public static Activity b(Request request) {
        return request.a().c().getActivity();
    }

    public static void a(Request request, Response response) {
        request.a().a(response, request.e());
    }

    public static void a(Runnable runnable) {
        b.execute(runnable);
    }

    public static Gson a() {
        return c;
    }
}
