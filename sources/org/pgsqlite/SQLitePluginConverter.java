package org.pgsqlite;

import com.facebook.react.bridge.NoSuchKeyException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

public abstract class SQLitePluginConverter {
    static String a(ReadableMap readableMap, String str, String str2) {
        if (readableMap == null) {
            return str2;
        }
        try {
            switch (readableMap.getType(str)) {
                case Number:
                    double d = readableMap.getDouble(str);
                    long j = (long) d;
                    if (d == ((double) j)) {
                        return String.valueOf(j);
                    }
                    return String.valueOf(d);
                case Boolean:
                    return String.valueOf(readableMap.getBoolean(str));
                case String:
                    return readableMap.getString(str);
                case Null:
                    return null;
                default:
                    return str2;
            }
        } catch (NoSuchKeyException unused) {
            return str2;
        }
    }

    static boolean a(ReadableMap readableMap, String str, boolean z) {
        if (readableMap == null) {
            return z;
        }
        try {
            switch (readableMap.getType(str)) {
                case Number:
                    if (readableMap.getDouble(str) == 0.0d) {
                        return Boolean.FALSE.booleanValue();
                    }
                    return Boolean.TRUE.booleanValue();
                case Boolean:
                    return readableMap.getBoolean(str);
                case String:
                    String string = readableMap.getString(str);
                    if ("true".equalsIgnoreCase(string)) {
                        return true;
                    }
                    return "false".equalsIgnoreCase(string) ? false : false;
                case Null:
                    return false;
                default:
                    return z;
            }
        } catch (NoSuchKeyException unused) {
            return z;
        }
    }

    static String a(ReadableArray readableArray, int i, String str) {
        if (readableArray == null) {
            return str;
        }
        try {
            switch (readableArray.getType(i)) {
                case Number:
                    double d = readableArray.getDouble(i);
                    long j = (long) d;
                    if (d == ((double) j)) {
                        return String.valueOf(j);
                    }
                    return String.valueOf(d);
                case Boolean:
                    return String.valueOf(readableArray.getBoolean(i));
                case String:
                    return readableArray.getString(i);
                case Null:
                    return null;
                default:
                    return str;
            }
        } catch (NoSuchKeyException unused) {
            return str;
        }
    }

    static boolean a(ReadableArray readableArray, int i, boolean z) {
        if (readableArray == null) {
            return z;
        }
        try {
            switch (readableArray.getType(i)) {
                case Number:
                    if (readableArray.getDouble(i) == 0.0d) {
                        return Boolean.FALSE.booleanValue();
                    }
                    return Boolean.TRUE.booleanValue();
                case Boolean:
                    return readableArray.getBoolean(i);
                case String:
                    String string = readableArray.getString(i);
                    if ("true".equalsIgnoreCase(string)) {
                        return true;
                    }
                    return "false".equalsIgnoreCase(string) ? false : false;
                case Null:
                    return false;
                default:
                    return z;
            }
        } catch (NoSuchKeyException unused) {
            return z;
        }
    }

    static Object a(ReadableMap readableMap, String str, Object obj) {
        if (readableMap == null) {
            return obj;
        }
        try {
            switch (readableMap.getType(str)) {
                case Number:
                    return Double.valueOf(readableMap.getDouble(str));
                case Boolean:
                    return Boolean.valueOf(readableMap.getBoolean(str));
                case String:
                    return readableMap.getString(str);
                case Null:
                    return null;
                case Map:
                    return readableMap.getMap(str);
                case Array:
                    return readableMap.getArray(str);
                default:
                    return null;
            }
        } catch (NoSuchKeyException unused) {
            return obj;
        }
    }

    static Object a(ReadableArray readableArray, int i, Object obj) {
        if (readableArray == null) {
            return obj;
        }
        try {
            switch (readableArray.getType(i)) {
                case Number:
                    return Double.valueOf(readableArray.getDouble(i));
                case Boolean:
                    return Boolean.valueOf(readableArray.getBoolean(i));
                case String:
                    return readableArray.getString(i);
                case Map:
                    return readableArray.getMap(i);
                case Array:
                    return readableArray.getArray(i);
                default:
                    return null;
            }
        } catch (NoSuchKeyException unused) {
            return obj;
        }
    }
}
