package com.amap.location;

import android.content.Context;
import android.support.annotation.NonNull;
import com.amap.location.common.a;
import com.amap.openapi.b;
import com.amap.openapi.d;

public class BasicLocateManager {

    /* renamed from: a  reason: collision with root package name */
    private static volatile BasicLocateManager f4558a;
    private b b;

    private BasicLocateManager() {
    }

    public static BasicLocateManager a() {
        if (f4558a == null) {
            synchronized (BasicLocateManager.class) {
                if (f4558a == null) {
                    f4558a = new BasicLocateManager();
                }
            }
        }
        return f4558a;
    }

    private void b(Context context, BasicLocateConfig basicLocateConfig) {
        if (this.b == null) {
            this.b = b.a();
            d dVar = new d();
            dVar.a(basicLocateConfig.a());
            dVar.a(basicLocateConfig.b());
            dVar.c(basicLocateConfig.d());
            dVar.b(basicLocateConfig.c());
            dVar.d(basicLocateConfig.f());
            dVar.e(basicLocateConfig.e());
            dVar.a(basicLocateConfig.g());
            this.b.a(context, dVar);
        }
        a.a(context, basicLocateConfig.f());
    }

    private void c() {
        if (this.b != null) {
            this.b.b();
        }
    }

    public synchronized void a(@NonNull Context context, @NonNull BasicLocateConfig basicLocateConfig) {
        b(context, basicLocateConfig);
    }

    public synchronized void b() {
        c();
    }
}
