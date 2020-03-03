package com.xiaomi.push;

import com.xiaomi.push.ai;
import com.xiaomi.push.cd;
import java.util.ArrayList;

class ce extends ai.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ cd f12672a;

    ce(cd cdVar) {
        this.f12672a = cdVar;
    }

    public int a() {
        return 100957;
    }

    public void run() {
        synchronized (this.f12672a.f) {
            if (this.f12672a.f.size() > 0) {
                if (this.f12672a.f.size() > 1) {
                    this.f12672a.a((ArrayList<cd.a>) this.f12672a.f);
                } else {
                    this.f12672a.b((cd.a) this.f12672a.f.get(0));
                }
                this.f12672a.f.clear();
                System.gc();
            }
        }
    }
}
