package com.amap.openapi;

import android.content.Context;
import android.support.annotation.NonNull;
import com.amap.location.common.util.d;
import com.loc.fc;
import java.util.List;

public class j extends g {
    public j() {
        super(500);
    }

    private int a(@NonNull v vVar) {
        return aj.a(this.f4726a, vVar.f4746a, vVar.b, vVar.c, vVar.d, vVar.e, vVar.f, vVar.g, vVar.h, vVar.i, vVar.j);
    }

    private int a(@NonNull List<y> list) {
        int size = list.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            y yVar = list.get(i);
            iArr[i] = ak.a(this.f4726a, yVar.f4749a, yVar.b, yVar.c, yVar.d, yVar.e);
        }
        return ac.a((fc) this.f4726a, iArr);
    }

    public byte[] a(@NonNull Context context, @NonNull v vVar, @NonNull List<y> list, byte b) {
        a();
        try {
            this.f4726a.h(ac.a(this.f4726a, a(vVar), a(list), b));
            return aw.a(az.a(context), d.a(this.f4726a.f()));
        } catch (Throwable unused) {
            return null;
        }
    }
}
