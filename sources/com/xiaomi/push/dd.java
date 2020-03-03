package com.xiaomi.push;

import java.util.ArrayList;
import java.util.Iterator;

class dd extends cx {
    cx i = this.j;
    final /* synthetic */ cx j;
    final /* synthetic */ db k;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    dd(db dbVar, String str, cx cxVar) {
        super(str);
        this.k = dbVar;
        this.j = cxVar;
        this.b = this.b;
        if (this.j != null) {
            this.f = this.j.f;
        }
    }

    public synchronized ArrayList<String> a(boolean z) {
        ArrayList<String> arrayList;
        arrayList = new ArrayList<>();
        if (this.i != null) {
            arrayList.addAll(this.i.a(true));
        }
        synchronized (db.b) {
            cx cxVar = db.b.get(this.b);
            if (cxVar != null) {
                Iterator<String> it = cxVar.a(true).iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    if (arrayList.indexOf(next) == -1) {
                        arrayList.add(next);
                    }
                }
                arrayList.remove(this.b);
                arrayList.add(this.b);
            }
        }
        return arrayList;
    }

    public synchronized void a(String str, cw cwVar) {
        if (this.i != null) {
            this.i.a(str, cwVar);
        }
    }

    public boolean b() {
        return false;
    }
}
