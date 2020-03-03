package com.loc;

import android.content.Context;
import java.util.ArrayList;

public class dc implements dd {

    /* renamed from: a  reason: collision with root package name */
    static dc f6556a = null;
    private static dg c = dg.a();
    private static cv d = null;
    private boolean b = false;

    private dc() {
    }

    public static dd a(Context context, String str) {
        if (f6556a == null) {
            synchronized (dc.class) {
                if (f6556a == null) {
                    if (!dl.a()) {
                        Thread thread = new Thread(new di(context));
                        thread.setUncaughtExceptionHandler(new dm());
                        thread.start();
                    }
                    Cdo.a(context);
                    dq.a(context);
                    dj.a(str);
                    f6556a = new dc();
                }
            }
        }
        return f6556a;
    }

    public final String a(String str) {
        String[] b2 = b(str);
        if (b2.length > 0) {
            return b2[0];
        }
        return null;
    }

    public final void a() {
        this.b = true;
    }

    public final void a(ArrayList arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            if (!dg.b(str)) {
                de.a().submit(new dq(str));
            }
        }
    }

    public final String[] b(String str) {
        if (!dn.a(str)) {
            return dj.b;
        }
        if (dn.b(str)) {
            return new String[]{str};
        } else if (d != null && d.a()) {
            return dj.b;
        } else {
            dh a2 = dg.a(str);
            if ((a2 == null || a2.b()) && !dg.b(str)) {
                dk.a("refresh host async: " + str);
                de.a().submit(new dq(str));
            }
            return (a2 == null || (a2.b() && (!a2.b() || !this.b))) ? dj.b : a2.a();
        }
    }
}
