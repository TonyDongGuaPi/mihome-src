package com.tmall.wireless.vaf.expr.engine.executor;

import android.util.Log;
import com.tmall.wireless.vaf.expr.engine.data.Data;
import java.util.HashSet;
import java.util.Set;

public class EqualExecutor extends ArithExecutor {
    private static final String u = "EqualExecutor_TMTEST";
    protected Set<Object> j = new HashSet();
    protected int k;

    public void a() {
        super.a();
    }

    public int a(Object obj) {
        Data data;
        int a2 = super.a(obj);
        switch (this.r.d()) {
            case 0:
                c();
                data = a(0);
                this.h = this.r.d();
                break;
            case 1:
                c();
                data = a(1);
                this.h = this.r.d();
                break;
            case 2:
                c();
                data = a(2);
                this.h = this.r.d();
                break;
            case 3:
                c();
                data = a(3);
                this.h = this.r.d();
                break;
            case 4:
                c();
                data = a(4);
                break;
            default:
                data = null;
                break;
        }
        if (data == null) {
            return a2;
        }
        Data a3 = this.s.a(this.h);
        if (a3 != null) {
            return a(a3, data);
        }
        Log.e(u, "result register is null");
        return a2;
    }

    /* access modifiers changed from: protected */
    public int a(Data data, Data data2) {
        data.a(data2);
        if (this.j.size() > 0) {
            for (Object a2 : this.j) {
                this.q.a(a2, this.k, data2);
            }
            return 1;
        }
        Log.e(u, "obj is empty");
        return 2;
    }

    private boolean c() {
        Set<Object> b = b();
        if (b != null) {
            this.j.clear();
            this.j.addAll(b);
            this.k = this.r.f();
            return true;
        }
        Log.e(u, "load var failed");
        return true;
    }
}
