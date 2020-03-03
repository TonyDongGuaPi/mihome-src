package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.interfaces.IX5DateSorter;

public class DateSorter {
    public static int DAY_COUNT = 5;

    /* renamed from: a  reason: collision with root package name */
    private android.webkit.DateSorter f9072a;
    private IX5DateSorter b;

    static {
        boolean a2 = a();
    }

    public DateSorter(Context context) {
        bt a2 = bt.a();
        if (a2 == null || !a2.b()) {
            this.f9072a = new android.webkit.DateSorter(context);
        } else {
            this.b = a2.c().h(context);
        }
    }

    private static boolean a() {
        bt a2 = bt.a();
        return a2 != null && a2.b();
    }

    public long getBoundary(int i) {
        bt a2 = bt.a();
        return (a2 == null || !a2.b()) ? this.f9072a.getBoundary(i) : this.b.getBoundary(i);
    }

    public int getIndex(long j) {
        bt a2 = bt.a();
        return (a2 == null || !a2.b()) ? this.f9072a.getIndex(j) : this.b.getIndex(j);
    }

    public String getLabel(int i) {
        bt a2 = bt.a();
        return (a2 == null || !a2.b()) ? this.f9072a.getLabel(i) : this.b.getLabel(i);
    }
}
