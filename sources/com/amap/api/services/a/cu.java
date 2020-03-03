package com.amap.api.services.a;

import android.content.Context;
import com.xiaomi.smarthome.framework.api.model.AreaPropInfo;
import java.util.List;

public class cu {

    /* renamed from: a  reason: collision with root package name */
    private cp f4381a;
    private Context b;

    public cu(Context context, boolean z) {
        this.b = context;
        this.f4381a = a(this.b, z);
    }

    private cp a(Context context, boolean z) {
        try {
            return new cp(context, cp.a((Class<? extends co>) ct.class));
        } catch (Throwable th) {
            if (!z) {
                cl.c(th, AreaPropInfo.j, "gdb");
            }
            return null;
        }
    }

    public void a(by byVar) {
        if (byVar != null) {
            try {
                if (this.f4381a == null) {
                    this.f4381a = a(this.b, false);
                }
                String a2 = by.a(byVar.a());
                List<by> a3 = this.f4381a.a(a2, by.class);
                if (a3 != null) {
                    if (a3.size() != 0) {
                        if (a(a3, byVar)) {
                            this.f4381a.a(a2, (Object) byVar);
                            return;
                        }
                        return;
                    }
                }
                this.f4381a.a(byVar);
            } catch (Throwable th) {
                cl.c(th, AreaPropInfo.j, "it");
            }
        }
    }

    private boolean a(List<by> list, by byVar) {
        for (by equals : list) {
            if (equals.equals(byVar)) {
                return false;
            }
        }
        return true;
    }

    public List<by> a() {
        try {
            return this.f4381a.a(by.g(), by.class, true);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
