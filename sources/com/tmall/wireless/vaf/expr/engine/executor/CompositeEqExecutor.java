package com.tmall.wireless.vaf.expr.engine.executor;

import android.util.Log;
import com.tmall.wireless.vaf.expr.engine.data.Data;

public class CompositeEqExecutor extends EqualExecutor {
    private static final String u = "ComEqExecutor_TMTEST";

    /* access modifiers changed from: protected */
    public void a(Data data, float f, float f2) {
    }

    /* access modifiers changed from: protected */
    public void a(Data data, float f, int i) {
    }

    /* access modifiers changed from: protected */
    public void a(Data data, float f, String str) {
    }

    /* access modifiers changed from: protected */
    public void a(Data data, int i, float f) {
    }

    /* access modifiers changed from: protected */
    public void a(Data data, int i, int i2) {
    }

    /* access modifiers changed from: protected */
    public void a(Data data, int i, String str) {
    }

    /* access modifiers changed from: protected */
    public void a(Data data, String str, float f) {
    }

    /* access modifiers changed from: protected */
    public void a(Data data, String str, int i) {
    }

    /* access modifiers changed from: protected */
    public void a(Data data, String str, String str2) {
    }

    /* access modifiers changed from: protected */
    public int a(Data data, Data data2) {
        if (this.j.size() > 0) {
            for (Object next : this.j) {
                Object a2 = this.q.a(next, this.k);
                if (a2 != null) {
                    Data data3 = new Data();
                    if (a2 instanceof Integer) {
                        switch (data2.g) {
                            case 1:
                                a(data3, ((Integer) a2).intValue(), data2.b());
                                break;
                            case 2:
                                a(data3, ((Integer) a2).intValue(), data2.c());
                                break;
                            case 3:
                                a(data3, ((Integer) a2).intValue(), data2.d());
                                break;
                        }
                    } else if (a2 instanceof Float) {
                        switch (data2.g) {
                            case 1:
                                a(data3, ((Float) a2).floatValue(), data2.b());
                                break;
                            case 2:
                                a(data3, ((Float) a2).floatValue(), data2.c());
                                break;
                            case 3:
                                a(data3, ((Float) a2).floatValue(), data2.d());
                                break;
                        }
                    } else if (a2 instanceof String) {
                        switch (data2.g) {
                            case 1:
                                a(data3, (String) a2, data2.b());
                                break;
                            case 2:
                                a(data3, (String) a2, data2.c());
                                break;
                            case 3:
                                a(data3, (String) a2, data2.d());
                                break;
                        }
                    } else {
                        Log.e(u, "var type invalidate:" + a2);
                    }
                    this.q.a(next, this.k, data3);
                } else {
                    Log.e(u, "result value is empty:" + a2);
                }
            }
            return 1;
        }
        Log.e(u, "obj is empty");
        return 2;
    }
}
