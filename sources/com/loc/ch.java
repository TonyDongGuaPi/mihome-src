package com.loc;

import android.content.Context;
import android.os.Build;
import com.amap.location.collection.CollectionConfig;
import com.amap.location.collection.CollectionManagerProxy;
import com.amap.location.common.network.IHttpClient;
import com.lidroid.xutils.bitmap.BitmapGlobalConfig;
import com.taobao.weex.common.Constants;

public final class ch {

    /* renamed from: a  reason: collision with root package name */
    private Context f6532a = null;
    private CollectionManagerProxy b;

    public ch(Context context) {
        this.f6532a = context.getApplicationContext();
    }

    private CollectionConfig a(ci ciVar) {
        String g;
        CollectionConfig collectionConfig = new CollectionConfig();
        collectionConfig.a((byte) 4);
        if (ciVar != null) {
            try {
                collectionConfig.a(ciVar.b());
                collectionConfig.b(ciVar.c());
                collectionConfig.c(ciVar.d());
                g = ciVar.g();
            } catch (Throwable th) {
                cm.a(th, "", "");
            }
        } else {
            collectionConfig.a("");
            collectionConfig.b("S128DF1572465B890OE3F7A13167KLEI");
            collectionConfig.c(u.f(this.f6532a));
            g = x.g(this.f6532a);
        }
        collectionConfig.d(g);
        collectionConfig.g().a(true);
        collectionConfig.g().c(false);
        collectionConfig.h().a(false);
        collectionConfig.i().a(false);
        collectionConfig.i().b(false);
        collectionConfig.i().c(0);
        collectionConfig.i().b(0);
        collectionConfig.i().a((int) BitmapGlobalConfig.b);
        if (Build.VERSION.SDK_INT >= 28) {
            collectionConfig.g().b(false);
        }
        collectionConfig.a(true);
        return collectionConfig;
    }

    public final void a() {
        try {
            if (this.b != null) {
                this.b.a();
            }
        } catch (Throwable th) {
            cm.a(th, "CollectionManager", Constants.Event.SLOT_LIFECYCLE.DESTORY);
        }
        this.b = null;
    }

    public final void a(ci ciVar, IHttpClient iHttpClient) {
        try {
            if (this.b == null) {
                this.b = new CollectionManagerProxy();
                this.b.a(this.f6532a, a(ciVar), iHttpClient);
            }
        } catch (Throwable th) {
            cm.a(th, "CollectionManager", "start");
        }
    }
}
