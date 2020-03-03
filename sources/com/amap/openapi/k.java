package com.amap.openapi;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.amap.location.collection.CollectionConfig;
import com.amap.location.common.a;
import com.amap.location.common.util.d;
import com.amap.location.common.util.f;
import com.loc.fc;

public class k extends g {
    public k() {
        super(5120);
    }

    private byte[] a(Context context, CollectionConfig collectionConfig) {
        super.a();
        this.f4726a.h(bl.a(this.f4726a, collectionConfig.a(), this.f4726a.a(az.a(context)), this.f4726a.a(collectionConfig.b()), (byte) Build.VERSION.SDK_INT, this.f4726a.a(a.c(context)), this.f4726a.a(collectionConfig.e()), this.f4726a.a(bd.a(a.a(context))), this.f4726a.a(bd.a(a.d(context))), f.a(a.f(context)), this.f4726a.a(a.c()), this.f4726a.a(a.b()), this.f4726a.a(collectionConfig.c()), this.f4726a.a(collectionConfig.d())));
        try {
            return aw.a(az.a(context), this.f4726a.f());
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    public byte[] a(@NonNull Context context, @NonNull CollectionConfig collectionConfig, @NonNull au auVar) {
        try {
            byte[] a2 = bb.a(aw.a(az.a(context)));
            byte[] a3 = a(context, collectionConfig);
            int size = auVar.b.size();
            if (size <= 0 || a2 == null) {
                return null;
            }
            a();
            int a4 = ad.a((fc) this.f4726a, a2);
            int[] iArr = new int[size];
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                s sVar = auVar.b.get(i2);
                iArr[i2] = ai.a(this.f4726a, (byte) sVar.b(), ai.a((fc) this.f4726a, sVar.c()));
            }
            int a5 = ad.a((fc) this.f4726a, iArr);
            if (a3 != null) {
                i = ad.b((fc) this.f4726a, a3);
            }
            this.f4726a.h(ad.a(this.f4726a, a4, i, a5));
            return d.a(this.f4726a.f());
        } catch (Throwable unused) {
            return null;
        }
    }
}
