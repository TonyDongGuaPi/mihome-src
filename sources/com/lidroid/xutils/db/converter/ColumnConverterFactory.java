package com.lidroid.xutils.db.converter;

import com.lidroid.xutils.db.sqlite.ColumnDbType;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class ColumnConverterFactory {

    /* renamed from: a  reason: collision with root package name */
    private static final ConcurrentHashMap<String, ColumnConverter> f6314a = new ConcurrentHashMap<>();

    private ColumnConverterFactory() {
    }

    public static ColumnConverter a(Class cls) {
        if (f6314a.containsKey(cls.getName())) {
            return f6314a.get(cls.getName());
        }
        if (!ColumnConverter.class.isAssignableFrom(cls)) {
            return null;
        }
        try {
            ColumnConverter columnConverter = (ColumnConverter) cls.newInstance();
            if (columnConverter != null) {
                f6314a.put(cls.getName(), columnConverter);
            }
            return columnConverter;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static ColumnDbType b(Class cls) {
        ColumnConverter a2 = a(cls);
        if (a2 != null) {
            return a2.a();
        }
        return ColumnDbType.TEXT;
    }

    public static void a(Class cls, ColumnConverter columnConverter) {
        f6314a.put(cls.getName(), columnConverter);
    }

    public static boolean c(Class cls) {
        if (f6314a.containsKey(cls.getName())) {
            return true;
        }
        if (ColumnConverter.class.isAssignableFrom(cls)) {
            try {
                ColumnConverter columnConverter = (ColumnConverter) cls.newInstance();
                if (columnConverter != null) {
                    f6314a.put(cls.getName(), columnConverter);
                }
                if (columnConverter == null) {
                    return true;
                }
                return false;
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    static {
        BooleanColumnConverter booleanColumnConverter = new BooleanColumnConverter();
        f6314a.put(Boolean.TYPE.getName(), booleanColumnConverter);
        f6314a.put(Boolean.class.getName(), booleanColumnConverter);
        f6314a.put(byte[].class.getName(), new ByteArrayColumnConverter());
        ByteColumnConverter byteColumnConverter = new ByteColumnConverter();
        f6314a.put(Byte.TYPE.getName(), byteColumnConverter);
        f6314a.put(Byte.class.getName(), byteColumnConverter);
        CharColumnConverter charColumnConverter = new CharColumnConverter();
        f6314a.put(Character.TYPE.getName(), charColumnConverter);
        f6314a.put(Character.class.getName(), charColumnConverter);
        f6314a.put(Date.class.getName(), new DateColumnConverter());
        DoubleColumnConverter doubleColumnConverter = new DoubleColumnConverter();
        f6314a.put(Double.TYPE.getName(), doubleColumnConverter);
        f6314a.put(Double.class.getName(), doubleColumnConverter);
        FloatColumnConverter floatColumnConverter = new FloatColumnConverter();
        f6314a.put(Float.TYPE.getName(), floatColumnConverter);
        f6314a.put(Float.class.getName(), floatColumnConverter);
        IntegerColumnConverter integerColumnConverter = new IntegerColumnConverter();
        f6314a.put(Integer.TYPE.getName(), integerColumnConverter);
        f6314a.put(Integer.class.getName(), integerColumnConverter);
        LongColumnConverter longColumnConverter = new LongColumnConverter();
        f6314a.put(Long.TYPE.getName(), longColumnConverter);
        f6314a.put(Long.class.getName(), longColumnConverter);
        ShortColumnConverter shortColumnConverter = new ShortColumnConverter();
        f6314a.put(Short.TYPE.getName(), shortColumnConverter);
        f6314a.put(Short.class.getName(), shortColumnConverter);
        f6314a.put(java.sql.Date.class.getName(), new SqlDateColumnConverter());
        f6314a.put(String.class.getName(), new StringColumnConverter());
    }
}
