package com.amap.openapi;

import android.content.Context;
import android.text.TextUtils;
import com.amap.location.offline.IOfflineCloudConfig;
import com.amap.location.offline.OfflineConfig;
import org.json.JSONObject;

public class bo {

    /* renamed from: a  reason: collision with root package name */
    private Context f4637a;
    private OfflineConfig b;
    private com.amap.location.offline.a c;
    private b d;
    private f e = new f() {
        public void a() {
        }

        public void a(a aVar) {
            bo.this.a(aVar.a());
        }
    };

    private static class a implements IOfflineCloudConfig {

        /* renamed from: a  reason: collision with root package name */
        private boolean f4639a = true;
        private long b = 0;
        private boolean c = false;
        private int d = 6;
        private int e = 8;
        private String[] f;
        private int g = 10;
        private int h = 5;
        private int i = 100;
        private boolean j = false;

        a(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.f4639a = jSONObject.optBoolean("loe", true);
                this.b = jSONObject.optLong("loct", 0);
                this.c = jSONObject.optBoolean("loca", false);
                this.d = jSONObject.optInt("lott", 6);
                this.e = jSONObject.optInt("lomwn", 8);
                try {
                    this.f = jSONObject.optString("locpl", "").split(",");
                } catch (Exception unused) {
                }
                this.g = jSONObject.optInt("lomrt", 10);
                this.h = jSONObject.optInt("lomnwrt", 5);
                this.i = jSONObject.optInt("lomnpr", 100);
                this.j = jSONObject.optBoolean("lonfd", false);
            }
        }

        public boolean a() {
            return this.f4639a;
        }

        public long b() {
            return this.b;
        }

        public boolean c() {
            return this.c;
        }

        public int d() {
            return this.d;
        }

        public int e() {
            return this.e;
        }

        public String[] f() {
            return this.f;
        }

        public int g() {
            return this.g;
        }

        public int h() {
            return this.h;
        }

        public int i() {
            return this.i;
        }

        public boolean j() {
            return this.j;
        }
    }

    public bo(Context context, OfflineConfig offlineConfig, com.amap.location.offline.a aVar) {
        this.f4637a = context;
        this.b = offlineConfig;
        this.c = aVar;
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.c.f4599a = new a(jSONObject);
            } catch (Throwable unused) {
            }
        }
    }

    public void a() {
        if (this.b.h == 4 && this.b.q && this.c.a()) {
            this.d = b.a();
            this.d.a(this.e);
            d dVar = new d();
            dVar.a(this.b.h);
            dVar.a(this.b.j);
            dVar.c(this.b.o);
            dVar.b(this.b.p);
            dVar.d(this.b.n);
            dVar.e(com.amap.location.common.a.c(this.f4637a));
            dVar.a(this.b.t);
            this.d.a(this.f4637a, dVar);
        }
    }

    public void b() {
        if (this.b.h == 4 && this.d != null) {
            this.d.b(this.e);
            this.d.b();
        }
    }
}
