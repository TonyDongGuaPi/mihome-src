package com.amap.location.collection;

import android.content.Context;
import android.support.annotation.NonNull;
import com.amap.location.common.network.IHttpClient;
import com.amap.openapi.at;
import com.amap.openapi.ay;

public class CollectionManagerProxy {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4563a = "CollectionManagerProxy";
    private Context b;
    private CollectionConfig c;
    private IHttpClient d;
    private a e;
    private ay f;
    private boolean g;

    public static String b() {
        return "v74";
    }

    /* access modifiers changed from: private */
    public synchronized void d() {
        if (this.g) {
            this.e.b();
            this.e = new a(this.b, this.c, this.d);
            this.e.a();
        }
    }

    public synchronized void a() {
        if (this.g) {
            this.f.b();
            this.e.b();
            this.g = false;
        }
    }

    public synchronized void a(@NonNull Context context, @NonNull CollectionConfig collectionConfig, @NonNull IHttpClient iHttpClient) {
        if (!this.g) {
            this.g = true;
            this.b = context.getApplicationContext();
            this.c = collectionConfig;
            this.d = iHttpClient;
            this.f = new ay(this.b, this.c, this.d, new ay.a() {
                public void a() {
                    CollectionManagerProxy.this.d();
                }
            });
            this.f.a();
            this.e = new a(this.b, this.c, iHttpClient);
            this.e.a();
        }
    }

    public synchronized void a(boolean z, at atVar) {
        if (this.g) {
            this.e.a(z, atVar);
        }
    }

    public synchronized at c() {
        at atVar;
        atVar = null;
        if (this.g) {
            atVar = this.e.c();
        }
        return atVar;
    }
}
