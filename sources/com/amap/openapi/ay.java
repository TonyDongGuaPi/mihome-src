package com.amap.openapi;

import android.content.Context;
import android.text.TextUtils;
import com.amap.location.collection.CollectionConfig;
import com.amap.location.common.network.IHttpClient;
import org.json.JSONObject;

public class ay {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4616a = "ay";
    private Context b;
    private CollectionConfig c;
    private b d;
    private IHttpClient e;
    private a f;
    private f g = new f() {
        public void a() {
        }

        public void a(a aVar) {
            ay.this.a(aVar.a());
        }
    };

    public interface a {
        void a();
    }

    public ay(Context context, CollectionConfig collectionConfig, IHttpClient iHttpClient, a aVar) {
        this.b = context;
        this.c = collectionConfig;
        this.e = iHttpClient;
        this.f = aVar;
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                if (a(new JSONObject(str)) && this.f != null) {
                    this.f.a();
                }
            } catch (Throwable unused) {
            }
        }
    }

    private boolean a(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("c");
        boolean z = false;
        if (optJSONObject == null) {
            return false;
        }
        boolean optBoolean = optJSONObject.optBoolean("cls", this.c.g().a());
        if (optBoolean != this.c.g().a()) {
            this.c.g().a(optBoolean);
            z = true;
        }
        boolean optBoolean2 = optJSONObject.optBoolean("cts", this.c.h().b());
        if (optBoolean2 != this.c.h().b()) {
            this.c.h().a(optBoolean2);
            z = true;
        }
        boolean a2 = a(optJSONObject, "cnwuss", this.c.i().b());
        if (a2 != this.c.i().b()) {
            this.c.i().b(a2);
            z = true;
        }
        boolean optBoolean3 = optJSONObject.optBoolean("cfup", this.c.g().d());
        if (optBoolean3 == this.c.g().d()) {
            return z;
        }
        this.c.g().d(optBoolean3);
        return true;
    }

    private boolean a(JSONObject jSONObject, String str, boolean z) {
        return jSONObject.optInt(str, z ? 1 : 0) == 1;
    }

    public void a() {
        this.d = b.a();
        this.d.a(this.g);
        if (this.c.a() == 4) {
            d dVar = new d();
            dVar.a(this.c.a());
            dVar.a(this.c.b());
            dVar.c(this.c.c());
            dVar.b(this.c.d());
            dVar.d(this.c.e());
            dVar.e(com.amap.location.common.a.c(this.b));
            dVar.a(this.e);
            this.d.a(this.b, dVar);
        }
    }

    public void b() {
        this.d.b(this.g);
        if (this.c.a() == 4) {
            this.d.b();
        }
    }
}
