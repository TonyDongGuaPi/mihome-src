package com.lidroid.xutils.db.table;

import android.text.TextUtils;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.converter.ColumnConverterFactory;
import com.lidroid.xutils.util.LogUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class TableUtils {

    /* renamed from: a  reason: collision with root package name */
    private static ConcurrentHashMap<String, HashMap<String, Column>> f6329a = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Id> b = new ConcurrentHashMap<>();

    private TableUtils() {
    }

    public static String a(Class<?> cls) {
        Table table = (Table) cls.getAnnotation(Table.class);
        if (table == null || TextUtils.isEmpty(table.name())) {
            return cls.getName().replace('.', '_');
        }
        return table.name();
    }

    public static String b(Class<?> cls) {
        Table table = (Table) cls.getAnnotation(Table.class);
        if (table != null) {
            return table.execAfterTableCreated();
        }
        return null;
    }

    static synchronized HashMap<String, Column> c(Class<?> cls) {
        synchronized (TableUtils.class) {
            if (f6329a.containsKey(cls.getName())) {
                HashMap<String, Column> hashMap = f6329a.get(cls.getName());
                return hashMap;
            }
            HashMap<String, Column> hashMap2 = new HashMap<>();
            a(cls, e(cls), hashMap2);
            f6329a.put(cls.getName(), hashMap2);
            return hashMap2;
        }
    }

    private static void a(Class<?> cls, String str, HashMap<String, Column> hashMap) {
        if (!Object.class.equals(cls)) {
            try {
                for (Field field : cls.getDeclaredFields()) {
                    if (!ColumnUtils.d(field)) {
                        if (!Modifier.isStatic(field.getModifiers())) {
                            if (ColumnConverterFactory.c(field.getType())) {
                                if (!field.getName().equals(str)) {
                                    Column column = new Column(cls, field);
                                    if (!hashMap.containsKey(column.c())) {
                                        hashMap.put(column.c(), column);
                                    }
                                }
                            } else if (ColumnUtils.e(field)) {
                                Foreign foreign = new Foreign(cls, field);
                                if (!hashMap.containsKey(foreign.c())) {
                                    hashMap.put(foreign.c(), foreign);
                                }
                            } else if (ColumnUtils.f(field)) {
                                Finder finder = new Finder(cls, field);
                                if (!hashMap.containsKey(finder.c())) {
                                    hashMap.put(finder.c(), finder);
                                }
                            }
                        }
                    }
                }
                if (!Object.class.equals(cls.getSuperclass())) {
                    a(cls.getSuperclass(), str, hashMap);
                }
            } catch (Throwable th) {
                LogUtils.b(th.getMessage(), th);
            }
        }
    }

    static Column a(Class<?> cls, String str) {
        if (f(cls).equals(str)) {
            return d(cls);
        }
        return c(cls).get(str);
    }

    static synchronized Id d(Class<?> cls) {
        Field field;
        synchronized (TableUtils.class) {
            if (Object.class.equals(cls)) {
                throw new RuntimeException("field 'id' not found");
            } else if (b.containsKey(cls.getName())) {
                Id id = b.get(cls.getName());
                return id;
            } else {
                Field field2 = null;
                Field[] declaredFields = cls.getDeclaredFields();
                if (declaredFields != null) {
                    int length = declaredFields.length;
                    int i = 0;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        }
                        Field field3 = declaredFields[i2];
                        if (field3.getAnnotation(Id.class) != null) {
                            field2 = field3;
                            break;
                        }
                        i2++;
                    }
                    if (field2 == null) {
                        int length2 = declaredFields.length;
                        while (true) {
                            if (i >= length2) {
                                break;
                            }
                            field = declaredFields[i];
                            if ("id".equals(field.getName())) {
                                break;
                            } else if ("_id".equals(field.getName())) {
                                break;
                            } else {
                                i++;
                            }
                        }
                        field2 = field;
                    }
                }
                if (field2 == null) {
                    Id d = d(cls.getSuperclass());
                    return d;
                }
                Id id2 = new Id(cls, field2);
                b.put(cls.getName(), id2);
                return id2;
            }
        }
    }

    private static String e(Class<?> cls) {
        Id d = d(cls);
        if (d == null) {
            return null;
        }
        return d.e().getName();
    }

    private static String f(Class<?> cls) {
        Id d = d(cls);
        if (d == null) {
            return null;
        }
        return d.c();
    }
}
