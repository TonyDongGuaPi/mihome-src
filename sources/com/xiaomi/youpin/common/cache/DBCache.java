package com.xiaomi.youpin.common.cache;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.xiaomi.youpin.log.LogUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;

public class DBCache implements ICache<String> {

    /* renamed from: a  reason: collision with root package name */
    static final String f23227a = "DBCache";
    private static final int c = 999;
    final DBSupplier b;

    public void c() {
    }

    public void d() {
    }

    public DBCache(Context context, String str) {
        this.b = DBSupplier.a(context, str);
    }

    public Set<String> a() {
        return new HashSet(e());
    }

    /* renamed from: a */
    public String d(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        try {
            return a((List<String>) arrayList).get(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public boolean b(String str) {
        return d(str) != null;
    }

    public boolean a(String str, String str2) {
        HashMap hashMap = new HashMap(1);
        hashMap.put(str, str2);
        try {
            a((Map<String, String>) hashMap);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean c(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        try {
            b((List<String>) arrayList);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public void b() {
        this.b.d();
    }

    public Map<String, String> a(List<String> list) {
        List<String> list2 = list;
        if (f()) {
            String[] strArr = {"key", "value"};
            HashSet hashSet = new HashSet();
            HashMap hashMap = new HashMap();
            int i = 0;
            while (i < list.size()) {
                int min = Math.min(list.size() - i, 999);
                Cursor query = this.b.b().query("KeyValueStorage", strArr, DBStorageUtil.a(min), DBStorageUtil.a(list2, i, min), (String) null, (String) null, (String) null);
                hashSet.clear();
                try {
                    if (query.getCount() != list.size()) {
                        for (int i2 = i; i2 < i + min; i2++) {
                            hashSet.add(list2.get(i2));
                        }
                    }
                    if (query.moveToFirst()) {
                        do {
                            hashMap.put(query.getString(0), query.getString(1));
                            hashSet.remove(query.getString(0));
                        } while (query.moveToNext());
                    }
                    query.close();
                    Iterator it = hashSet.iterator();
                    while (it.hasNext()) {
                        hashMap.put((String) it.next(), (Object) null);
                    }
                    hashSet.clear();
                    i += 999;
                } catch (Exception e) {
                    LogUtils.w(f23227a, e.getMessage(), e);
                    throw e;
                } catch (Throwable th) {
                    query.close();
                    throw th;
                }
            }
            return hashMap;
        }
        throw new RuntimeException("database not ready");
    }

    public void a(Map<String, String> map) {
        if (f()) {
            SQLiteStatement compileStatement = this.b.b().compileStatement("INSERT OR REPLACE INTO KeyValueStorage VALUES (?, ?);");
            try {
                this.b.b().beginTransaction();
                for (String next : map.keySet()) {
                    compileStatement.clearBindings();
                    compileStatement.bindString(1, next);
                    compileStatement.bindString(2, map.get(next));
                    compileStatement.execute();
                }
                this.b.b().setTransactionSuccessful();
                try {
                    this.b.b().endTransaction();
                } catch (Exception e) {
                    LogUtils.w(f23227a, e.getMessage(), e);
                    throw e;
                }
            } catch (Exception e2) {
                LogUtils.w(f23227a, e2.getMessage(), e2);
                throw e2;
            } catch (Throwable th) {
                try {
                    this.b.b().endTransaction();
                    throw th;
                } catch (Exception e3) {
                    LogUtils.w(f23227a, e3.getMessage(), e3);
                    throw e3;
                }
            }
        } else {
            throw new RuntimeException("database not ready");
        }
    }

    public void b(List<String> list) {
        if (list.size() != 0) {
            if (f()) {
                try {
                    this.b.b().beginTransaction();
                    for (int i = 0; i < list.size(); i += 999) {
                        int min = Math.min(list.size() - i, 999);
                        this.b.b().delete("KeyValueStorage", DBStorageUtil.a(min), DBStorageUtil.a(list, i, min));
                    }
                    this.b.b().setTransactionSuccessful();
                    try {
                        this.b.b().endTransaction();
                    } catch (Exception e) {
                        LogUtils.w(f23227a, e.getMessage(), e);
                        throw e;
                    }
                } catch (Exception e2) {
                    LogUtils.w(f23227a, e2.getMessage(), e2);
                    throw e2;
                } catch (Throwable th) {
                    try {
                        this.b.b().endTransaction();
                        throw th;
                    } catch (Exception e3) {
                        LogUtils.w(f23227a, e3.getMessage(), e3);
                        throw e3;
                    }
                }
            } else {
                throw new RuntimeException("database not ready");
            }
        }
    }

    public void b(Map<String, String> map) throws JSONException {
        if (map.size() != 0) {
            if (f()) {
                try {
                    this.b.b().beginTransaction();
                    for (String next : map.keySet()) {
                        DBStorageUtil.b(this.b.b(), next, map.get(next));
                    }
                    this.b.b().setTransactionSuccessful();
                    try {
                        this.b.b().endTransaction();
                    } catch (Exception e) {
                        LogUtils.w(f23227a, e.getMessage(), e);
                        throw e;
                    }
                } catch (Exception e2) {
                    LogUtils.w(f23227a, e2.getMessage(), e2);
                    throw e2;
                } catch (Throwable th) {
                    try {
                        this.b.b().endTransaction();
                        throw th;
                    } catch (Exception e3) {
                        LogUtils.w(f23227a, e3.getMessage(), e3);
                        throw e3;
                    }
                }
            } else {
                throw new RuntimeException("database not ready");
            }
        }
    }

    public List<String> e() {
        if (f()) {
            ArrayList arrayList = new ArrayList();
            Cursor query = this.b.b().query("KeyValueStorage", new String[]{"key"}, (String) null, (String[]) null, (String) null, (String) null, (String) null);
            try {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(query.getString(0));
                    } while (query.moveToNext());
                }
                query.close();
                return arrayList;
            } catch (Exception e) {
                LogUtils.w(f23227a, e.getMessage(), e);
                throw e;
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        } else {
            throw new RuntimeException("database not ready");
        }
    }

    private boolean f() {
        return this.b.a();
    }
}
