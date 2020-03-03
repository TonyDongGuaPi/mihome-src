package com.mipay.common.base;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

final class g implements Parcelable.Creator<f> {
    g() {
    }

    /* renamed from: a */
    public f createFromParcel(Parcel parcel) {
        f fVar = new f();
        ArrayList unused = fVar.d = parcel.readArrayList(d.class.getClassLoader());
        HashMap unused2 = fVar.e = new HashMap();
        Iterator it = fVar.d.iterator();
        while (it.hasNext()) {
            d dVar = (d) it.next();
            fVar.e.put(dVar.f8114a, dVar);
        }
        return fVar;
    }

    /* renamed from: a */
    public f[] newArray(int i) {
        return new f[i];
    }
}
