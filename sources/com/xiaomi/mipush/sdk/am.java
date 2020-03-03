package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class am {

    /* renamed from: a  reason: collision with root package name */
    private static volatile am f11532a;
    private Context b;
    private List<z> c = new ArrayList();

    private am(Context context) {
        this.b = context.getApplicationContext();
        if (this.b == null) {
            this.b = context;
        }
    }

    public static am a(Context context) {
        if (f11532a == null) {
            synchronized (am.class) {
                if (f11532a == null) {
                    f11532a = new am(context);
                }
            }
        }
        return f11532a;
    }

    public synchronized String a(bb bbVar) {
        return this.b.getSharedPreferences("mipush_extra", 0).getString(bbVar.name(), "");
    }

    public synchronized void a(bb bbVar, String str) {
        SharedPreferences sharedPreferences = this.b.getSharedPreferences("mipush_extra", 0);
        sharedPreferences.edit().putString(bbVar.name(), str).commit();
    }

    public void a(String str) {
        synchronized (this.c) {
            z zVar = new z();
            zVar.f11569a = 0;
            zVar.b = str;
            if (this.c.contains(zVar)) {
                this.c.remove(zVar);
            }
            this.c.add(zVar);
        }
    }

    public void b(String str) {
        synchronized (this.c) {
            z zVar = new z();
            zVar.b = str;
            if (this.c.contains(zVar)) {
                Iterator<z> it = this.c.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    z next = it.next();
                    if (zVar.equals(next)) {
                        zVar = next;
                        break;
                    }
                }
            }
            zVar.f11569a++;
            this.c.remove(zVar);
            this.c.add(zVar);
        }
    }

    public int c(String str) {
        synchronized (this.c) {
            z zVar = new z();
            zVar.b = str;
            if (this.c.contains(zVar)) {
                for (z next : this.c) {
                    if (next.equals(zVar)) {
                        int i = next.f11569a;
                        return i;
                    }
                }
            }
            return 0;
        }
    }

    public void d(String str) {
        synchronized (this.c) {
            z zVar = new z();
            zVar.b = str;
            if (this.c.contains(zVar)) {
                this.c.remove(zVar);
            }
        }
    }

    public boolean e(String str) {
        synchronized (this.c) {
            z zVar = new z();
            zVar.b = str;
            return this.c.contains(zVar);
        }
    }
}
