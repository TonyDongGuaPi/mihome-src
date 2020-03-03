package com.loc;

import android.content.Context;
import com.xiaomi.smarthome.framework.api.model.AreaPropInfo;
import java.util.Iterator;
import java.util.List;

public final class ba {

    /* renamed from: a  reason: collision with root package name */
    private av f6495a;
    private Context b;

    public ba(Context context, boolean z) {
        this.b = context;
        this.f6495a = a(this.b, z);
    }

    private static av a(Context context, boolean z) {
        try {
            return new av(context, av.a((Class<? extends au>) az.class));
        } catch (Throwable th) {
            if (!z) {
                aq.b(th, AreaPropInfo.j, "gdb");
            }
            return null;
        }
    }

    public final List<ac> a() {
        try {
            return this.f6495a.a(ac.g(), ac.class, true);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final void a(ac acVar) {
        if (acVar != null) {
            try {
                boolean z = false;
                if (this.f6495a == null) {
                    this.f6495a = a(this.b, false);
                }
                String a2 = ac.a(acVar.a());
                List<ac> a3 = this.f6495a.a(a2, ac.class, false);
                if (a3.size() == 0) {
                    this.f6495a.a(acVar);
                    return;
                }
                Iterator<ac> it = a3.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().equals(acVar)) {
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    this.f6495a.a(a2, (Object) acVar);
                }
            } catch (Throwable th) {
                aq.b(th, AreaPropInfo.j, "it");
            }
        }
    }
}
