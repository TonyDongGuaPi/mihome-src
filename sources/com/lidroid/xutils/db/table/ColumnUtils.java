package com.lidroid.xutils.db.table;

import android.text.TextUtils;
import com.lidroid.xutils.db.annotation.Check;
import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Finder;
import com.lidroid.xutils.db.annotation.Foreign;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NotNull;
import com.lidroid.xutils.db.annotation.Transient;
import com.lidroid.xutils.db.annotation.Unique;
import com.lidroid.xutils.db.sqlite.FinderLazyLoader;
import com.lidroid.xutils.db.sqlite.ForeignLazyLoader;
import com.lidroid.xutils.util.LogUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;

public class ColumnUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final HashSet<String> f6325a = new HashSet<>(14);

    private ColumnUtils() {
    }

    static {
        f6325a.add(Integer.TYPE.getName());
        f6325a.add(Long.TYPE.getName());
        f6325a.add(Short.TYPE.getName());
        f6325a.add(Byte.TYPE.getName());
        f6325a.add(Float.TYPE.getName());
        f6325a.add(Double.TYPE.getName());
        f6325a.add(Integer.class.getName());
        f6325a.add(Long.class.getName());
        f6325a.add(Short.class.getName());
        f6325a.add(Byte.class.getName());
        f6325a.add(Float.class.getName());
        f6325a.add(Double.class.getName());
        f6325a.add(String.class.getName());
        f6325a.add(byte[].class.getName());
    }

    public static boolean a(Class<?> cls) {
        return f6325a.contains(cls.getName());
    }

    public static Method a(Class<?> cls, Field field) {
        String name = field.getName();
        Method a2 = field.getType() == Boolean.TYPE ? a(cls, name) : null;
        if (a2 == null) {
            String str = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
            try {
                a2 = cls.getDeclaredMethod(str, new Class[0]);
            } catch (NoSuchMethodException unused) {
                LogUtils.a(String.valueOf(str) + " not exist");
            }
        }
        return (a2 != null || Object.class.equals(cls.getSuperclass())) ? a2 : a((Class<?>) cls.getSuperclass(), field);
    }

    public static Method b(Class<?> cls, Field field) {
        String name = field.getName();
        Method c = field.getType() == Boolean.TYPE ? c(cls, field) : null;
        if (c == null) {
            String str = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
            try {
                c = cls.getDeclaredMethod(str, new Class[]{field.getType()});
            } catch (NoSuchMethodException unused) {
                LogUtils.a(String.valueOf(str) + " not exist");
            }
        }
        return (c != null || Object.class.equals(cls.getSuperclass())) ? c : b(cls.getSuperclass(), field);
    }

    public static String a(Field field) {
        Column column = (Column) field.getAnnotation(Column.class);
        if (column != null && !TextUtils.isEmpty(column.column())) {
            return column.column();
        }
        Id id = (Id) field.getAnnotation(Id.class);
        if (id != null && !TextUtils.isEmpty(id.column())) {
            return id.column();
        }
        Foreign foreign = (Foreign) field.getAnnotation(Foreign.class);
        if (foreign != null && !TextUtils.isEmpty(foreign.column())) {
            return foreign.column();
        }
        if (((Finder) field.getAnnotation(Finder.class)) != null) {
            return field.getName();
        }
        return field.getName();
    }

    public static String b(Field field) {
        Foreign foreign = (Foreign) field.getAnnotation(Foreign.class);
        if (foreign != null) {
            return foreign.foreign();
        }
        return field.getName();
    }

    public static String c(Field field) {
        Column column = (Column) field.getAnnotation(Column.class);
        if (column == null || TextUtils.isEmpty(column.defaultValue())) {
            return null;
        }
        return column.defaultValue();
    }

    public static boolean d(Field field) {
        return field.getAnnotation(Transient.class) != null;
    }

    public static boolean e(Field field) {
        return field.getAnnotation(Foreign.class) != null;
    }

    public static boolean f(Field field) {
        return field.getAnnotation(Finder.class) != null;
    }

    public static boolean g(Field field) {
        return field.getAnnotation(Unique.class) != null;
    }

    public static boolean h(Field field) {
        return field.getAnnotation(NotNull.class) != null;
    }

    public static String i(Field field) {
        Check check = (Check) field.getAnnotation(Check.class);
        if (check != null) {
            return check.value();
        }
        return null;
    }

    public static Class<?> a(Foreign foreign) {
        Class<?> type = foreign.e().getType();
        return (type.equals(ForeignLazyLoader.class) || type.equals(List.class)) ? (Class) ((ParameterizedType) foreign.e().getGenericType()).getActualTypeArguments()[0] : type;
    }

    public static Class<?> a(Finder finder) {
        Class<?> type = finder.e().getType();
        return (type.equals(FinderLazyLoader.class) || type.equals(List.class)) ? (Class) ((ParameterizedType) finder.e().getGenericType()).getActualTypeArguments()[0] : type;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
        r0 = com.lidroid.xutils.db.converter.ColumnConverterFactory.a(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object a(java.lang.Object r2) {
        /*
            if (r2 == 0) goto L_0x0016
            java.lang.Class r0 = r2.getClass()
            boolean r1 = a((java.lang.Class<?>) r0)
            if (r1 != 0) goto L_0x0016
            com.lidroid.xutils.db.converter.ColumnConverter r0 = com.lidroid.xutils.db.converter.ColumnConverterFactory.a(r0)
            if (r0 == 0) goto L_0x0016
            java.lang.Object r2 = r0.a(r2)
        L_0x0016:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.db.table.ColumnUtils.a(java.lang.Object):java.lang.Object");
    }

    private static boolean a(String str) {
        return str != null && str.startsWith("is");
    }

    private static Method a(Class<?> cls, String str) {
        String str2 = "is" + str.substring(0, 1).toUpperCase() + str.substring(1);
        if (!a(str)) {
            str = str2;
        }
        try {
            return cls.getDeclaredMethod(str, new Class[0]);
        } catch (NoSuchMethodException unused) {
            LogUtils.a(String.valueOf(str) + " not exist");
            return null;
        }
    }

    private static Method c(Class<?> cls, Field field) {
        String str;
        String name = field.getName();
        if (a(field.getName())) {
            str = "set" + name.substring(2, 3).toUpperCase() + name.substring(3);
        } else {
            str = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        try {
            return cls.getDeclaredMethod(str, new Class[]{field.getType()});
        } catch (NoSuchMethodException unused) {
            LogUtils.a(String.valueOf(str) + " not exist");
            return null;
        }
    }
}
