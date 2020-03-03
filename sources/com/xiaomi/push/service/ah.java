package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.ad;
import com.xiaomi.push.bc;
import com.xiaomi.push.ht;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ah {

    /* renamed from: a  reason: collision with root package name */
    private static volatile ah f12875a;

    /* renamed from: a  reason: collision with other field name */
    protected SharedPreferences f276a;

    /* renamed from: a  reason: collision with other field name */
    private HashSet<a> f277a = new HashSet<>();

    public static abstract class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private int f12876a;

        /* renamed from: a  reason: collision with other field name */
        private String f278a;

        public a(int i, String str) {
            this.f12876a = i;
            this.f278a = str;
        }

        /* access modifiers changed from: protected */
        public abstract void a();

        public boolean equals(Object obj) {
            return (obj instanceof a) && this.f12876a == ((a) obj).f12876a;
        }

        public int hashCode() {
            return this.f12876a;
        }

        public final void run() {
            a();
        }
    }

    private ah(Context context) {
        this.f276a = context.getSharedPreferences("mipush_oc", 0);
    }

    public static ah a(Context context) {
        if (f12875a == null) {
            synchronized (ah.class) {
                if (f12875a == null) {
                    f12875a = new ah(context);
                }
            }
        }
        return f12875a;
    }

    private String a(int i) {
        return "normal_oc_" + i;
    }

    private void a(SharedPreferences.Editor editor, Pair<Integer, Object> pair, String str) {
        if (pair.second instanceof Integer) {
            editor.putInt(str, ((Integer) pair.second).intValue());
        } else if (pair.second instanceof Long) {
            editor.putLong(str, ((Long) pair.second).longValue());
        } else if (pair.second instanceof String) {
            String str2 = (String) pair.second;
            if (str.equals(a(ht.AppIsInstalledList.a()))) {
                str2 = bc.a(str2);
            }
            editor.putString(str, str2);
        } else if (pair.second instanceof Boolean) {
            editor.putBoolean(str, ((Boolean) pair.second).booleanValue());
        }
    }

    private String b(int i) {
        return "custom_oc_" + i;
    }

    public int a(int i, int i2) {
        String b = b(i);
        if (this.f276a.contains(b)) {
            return this.f276a.getInt(b, 0);
        }
        String a2 = a(i);
        return this.f276a.contains(a2) ? this.f276a.getInt(a2, 0) : i2;
    }

    public String a(int i, String str) {
        String b = b(i);
        if (this.f276a.contains(b)) {
            return this.f276a.getString(b, (String) null);
        }
        String a2 = a(i);
        return this.f276a.contains(a2) ? this.f276a.getString(a2, (String) null) : str;
    }

    public synchronized void a() {
        this.f277a.clear();
    }

    public synchronized void a(a aVar) {
        if (!this.f277a.contains(aVar)) {
            this.f277a.add(aVar);
        }
    }

    public void a(List<Pair<Integer, Object>> list) {
        if (!ad.a(list)) {
            SharedPreferences.Editor edit = this.f276a.edit();
            for (Pair next : list) {
                if (!(next.first == null || next.second == null)) {
                    a(edit, next, a(((Integer) next.first).intValue()));
                }
            }
            edit.commit();
        }
    }

    public boolean a(int i, boolean z) {
        String b = b(i);
        if (this.f276a.contains(b)) {
            return this.f276a.getBoolean(b, false);
        }
        String a2 = a(i);
        return this.f276a.contains(a2) ? this.f276a.getBoolean(a2, false) : z;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        b.c("OC_Callback : receive new oc data");
        HashSet hashSet = new HashSet();
        synchronized (this) {
            hashSet.addAll(this.f277a);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar != null) {
                aVar.run();
            }
        }
        hashSet.clear();
    }

    public void b(List<Pair<Integer, Object>> list) {
        if (!ad.a(list)) {
            SharedPreferences.Editor edit = this.f276a.edit();
            for (Pair next : list) {
                if (next.first != null) {
                    String b = b(((Integer) next.first).intValue());
                    if (next.second == null) {
                        edit.remove(b);
                    } else {
                        a(edit, next, b);
                    }
                }
            }
            edit.commit();
        }
    }
}
