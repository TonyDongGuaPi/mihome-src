package com.lidroid.xutils.db.table;

import android.text.TextUtils;
import com.lidroid.xutils.DbUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Table {
    private static final HashMap<String, Table> f = new HashMap<>();

    /* renamed from: a  reason: collision with root package name */
    public final DbUtils f6328a;
    public final String b;
    public final Id c;
    public final HashMap<String, Column> d;
    public final HashMap<String, Finder> e = new HashMap<>();
    private boolean g;

    private Table(DbUtils dbUtils, Class<?> cls) {
        this.f6328a = dbUtils;
        this.b = TableUtils.a(cls);
        this.c = TableUtils.d(cls);
        this.d = TableUtils.c(cls);
        for (Column next : this.d.values()) {
            next.a(this);
            if (next instanceof Finder) {
                this.e.put(next.c(), (Finder) next);
            }
        }
    }

    public static synchronized Table a(DbUtils dbUtils, Class<?> cls) {
        Table table;
        synchronized (Table.class) {
            String str = String.valueOf(dbUtils.b().b()) + "#" + cls.getName();
            table = f.get(str);
            if (table == null) {
                table = new Table(dbUtils, cls);
                f.put(str, table);
            }
        }
        return table;
    }

    public static synchronized void b(DbUtils dbUtils, Class<?> cls) {
        synchronized (Table.class) {
            f.remove(String.valueOf(dbUtils.b().b()) + "#" + cls.getName());
        }
    }

    public static synchronized void a(DbUtils dbUtils, String str) {
        synchronized (Table.class) {
            if (f.size() > 0) {
                String str2 = null;
                Iterator<Map.Entry<String, Table>> it = f.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry next = it.next();
                    Table table = (Table) next.getValue();
                    if (table != null && table.b.equals(str)) {
                        str2 = (String) next.getKey();
                        if (str2.startsWith(String.valueOf(dbUtils.b().b()) + "#")) {
                            break;
                        }
                    }
                }
                if (TextUtils.isEmpty(str2)) {
                    f.remove(str2);
                }
            }
        }
    }

    public boolean a() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }
}
