package org.xutils.db.converter;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import org.xutils.common.util.LogUtil;
import org.xutils.db.sqlite.ColumnDbType;

public final class ColumnConverterFactory {

    /* renamed from: a  reason: collision with root package name */
    private static final ConcurrentHashMap<String, ColumnConverter> f4241a = new ConcurrentHashMap<>();

    private ColumnConverterFactory() {
    }

    public static ColumnConverter a(Class cls) {
        ColumnConverter columnConverter;
        if (f4241a.containsKey(cls.getName())) {
            columnConverter = f4241a.get(cls.getName());
        } else {
            if (ColumnConverter.class.isAssignableFrom(cls)) {
                try {
                    columnConverter = (ColumnConverter) cls.newInstance();
                    if (columnConverter != null) {
                        f4241a.put(cls.getName(), columnConverter);
                    }
                } catch (Throwable th) {
                    LogUtil.b(th.getMessage(), th);
                }
            }
            columnConverter = null;
        }
        if (columnConverter != null) {
            return columnConverter;
        }
        throw new RuntimeException("Database Column Not Support: " + cls.getName() + ", please impl ColumnConverter or use ColumnConverterFactory#registerColumnConverter(...)");
    }

    public static ColumnDbType b(Class cls) {
        return a(cls).a();
    }

    public static void a(Class cls, ColumnConverter columnConverter) {
        f4241a.put(cls.getName(), columnConverter);
    }

    public static boolean c(Class cls) {
        if (f4241a.containsKey(cls.getName())) {
            return true;
        }
        if (ColumnConverter.class.isAssignableFrom(cls)) {
            try {
                ColumnConverter columnConverter = (ColumnConverter) cls.newInstance();
                if (columnConverter != null) {
                    f4241a.put(cls.getName(), columnConverter);
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
        f4241a.put(Boolean.TYPE.getName(), booleanColumnConverter);
        f4241a.put(Boolean.class.getName(), booleanColumnConverter);
        f4241a.put(byte[].class.getName(), new ByteArrayColumnConverter());
        ByteColumnConverter byteColumnConverter = new ByteColumnConverter();
        f4241a.put(Byte.TYPE.getName(), byteColumnConverter);
        f4241a.put(Byte.class.getName(), byteColumnConverter);
        CharColumnConverter charColumnConverter = new CharColumnConverter();
        f4241a.put(Character.TYPE.getName(), charColumnConverter);
        f4241a.put(Character.class.getName(), charColumnConverter);
        f4241a.put(Date.class.getName(), new DateColumnConverter());
        DoubleColumnConverter doubleColumnConverter = new DoubleColumnConverter();
        f4241a.put(Double.TYPE.getName(), doubleColumnConverter);
        f4241a.put(Double.class.getName(), doubleColumnConverter);
        FloatColumnConverter floatColumnConverter = new FloatColumnConverter();
        f4241a.put(Float.TYPE.getName(), floatColumnConverter);
        f4241a.put(Float.class.getName(), floatColumnConverter);
        IntegerColumnConverter integerColumnConverter = new IntegerColumnConverter();
        f4241a.put(Integer.TYPE.getName(), integerColumnConverter);
        f4241a.put(Integer.class.getName(), integerColumnConverter);
        LongColumnConverter longColumnConverter = new LongColumnConverter();
        f4241a.put(Long.TYPE.getName(), longColumnConverter);
        f4241a.put(Long.class.getName(), longColumnConverter);
        ShortColumnConverter shortColumnConverter = new ShortColumnConverter();
        f4241a.put(Short.TYPE.getName(), shortColumnConverter);
        f4241a.put(Short.class.getName(), shortColumnConverter);
        f4241a.put(java.sql.Date.class.getName(), new SqlDateColumnConverter());
        f4241a.put(String.class.getName(), new StringColumnConverter());
    }
}
