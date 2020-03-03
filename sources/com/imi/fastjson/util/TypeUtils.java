package com.imi.fastjson.util;

import com.facebook.react.devsupport.StackTraceHelper;
import com.imi.fastjson.JSON;
import com.imi.fastjson.JSONException;
import com.imi.fastjson.JSONObject;
import com.imi.fastjson.annotation.JSONField;
import com.imi.fastjson.annotation.JSONType;
import com.imi.fastjson.parser.JSONScanner;
import com.imi.fastjson.parser.ParserConfig;
import com.imi.fastjson.parser.deserializer.FieldDeserializer;
import com.xiaomi.payment.data.MibiConstants;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TypeUtils {

    /* renamed from: a  reason: collision with root package name */
    private static ConcurrentMap<String, Class<?>> f6195a = new ConcurrentHashMap();

    public static final String a(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static final Byte b(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Byte.valueOf(((Number) obj).byteValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            return Byte.valueOf(Byte.parseByte(str));
        }
        throw new JSONException("can not cast to byte, value : " + obj);
    }

    public static final Character c(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Character) {
            return (Character) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            if (str.length() == 1) {
                return Character.valueOf(str.charAt(0));
            }
            throw new JSONException("can not cast to byte, value : " + obj);
        }
        throw new JSONException("can not cast to byte, value : " + obj);
    }

    public static final Short d(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Short.valueOf(((Number) obj).shortValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            return Short.valueOf(Short.parseShort(str));
        }
        throw new JSONException("can not cast to short, value : " + obj);
    }

    public static final BigDecimal e(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        if (obj instanceof BigInteger) {
            return new BigDecimal((BigInteger) obj);
        }
        String obj2 = obj.toString();
        if (obj2.length() == 0) {
            return null;
        }
        return new BigDecimal(obj2);
    }

    public static final BigInteger f(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigInteger) {
            return (BigInteger) obj;
        }
        if ((obj instanceof Float) || (obj instanceof Double)) {
            return BigInteger.valueOf(((Number) obj).longValue());
        }
        String obj2 = obj.toString();
        if (obj2.length() == 0) {
            return null;
        }
        return new BigInteger(obj2);
    }

    public static final Float g(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Float.valueOf(((Number) obj).floatValue());
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (obj2.length() == 0) {
                return null;
            }
            return Float.valueOf(Float.parseFloat(obj2));
        }
        throw new JSONException("can not cast to float, value : " + obj);
    }

    public static final Double h(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (obj2.length() == 0) {
                return null;
            }
            return Double.valueOf(Double.parseDouble(obj2));
        }
        throw new JSONException("can not cast to double, value : " + obj);
    }

    public static final Date i(Object obj) {
        String str;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Calendar) {
            return ((Calendar) obj).getTime();
        }
        if (obj instanceof Date) {
            return (Date) obj;
        }
        long j = -1;
        if (obj instanceof Number) {
            j = ((Number) obj).longValue();
        }
        if (obj instanceof String) {
            String str2 = (String) obj;
            if (str2.indexOf(45) != -1) {
                if (str2.length() == JSON.DEFFAULT_DATE_FORMAT.length()) {
                    str = JSON.DEFFAULT_DATE_FORMAT;
                } else if (str2.length() == 10) {
                    str = "yyyy-MM-dd";
                } else {
                    str = str2.length() == "yyyy-MM-dd HH:mm:ss".length() ? "yyyy-MM-dd HH:mm:ss" : "yyyy-MM-dd HH:mm:ss.SSS";
                }
                try {
                    return new SimpleDateFormat(str).parse(str2);
                } catch (ParseException unused) {
                    throw new JSONException("can not cast to Date, value : " + str2);
                }
            } else if (str2.length() == 0) {
                return null;
            } else {
                j = Long.parseLong(str2);
            }
        }
        if (j >= 0) {
            return new Date(j);
        }
        throw new JSONException("can not cast to Date, value : " + obj);
    }

    public static final java.sql.Date j(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Calendar) {
            return new java.sql.Date(((Calendar) obj).getTimeInMillis());
        }
        if (obj instanceof java.sql.Date) {
            return (java.sql.Date) obj;
        }
        if (obj instanceof Date) {
            return new java.sql.Date(((Date) obj).getTime());
        }
        long longValue = obj instanceof Number ? ((Number) obj).longValue() : 0;
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            longValue = Long.parseLong(str);
        }
        if (longValue > 0) {
            return new java.sql.Date(longValue);
        }
        throw new JSONException("can not cast to Date, value : " + obj);
    }

    public static final Timestamp k(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Calendar) {
            return new Timestamp(((Calendar) obj).getTimeInMillis());
        }
        if (obj instanceof Timestamp) {
            return (Timestamp) obj;
        }
        if (obj instanceof Date) {
            return new Timestamp(((Date) obj).getTime());
        }
        long longValue = obj instanceof Number ? ((Number) obj).longValue() : 0;
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            longValue = Long.parseLong(str);
        }
        if (longValue > 0) {
            return new Timestamp(longValue);
        }
        throw new JSONException("can not cast to Date, value : " + obj);
    }

    public static final Long l(Object obj) {
        Calendar calendar = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Long.valueOf(((Number) obj).longValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            try {
                return Long.valueOf(Long.parseLong(str));
            } catch (NumberFormatException unused) {
                JSONScanner jSONScanner = new JSONScanner(str);
                if (jSONScanner.b(false)) {
                    calendar = jSONScanner.q();
                }
                jSONScanner.close();
                if (calendar != null) {
                    return Long.valueOf(calendar.getTimeInMillis());
                }
            }
        }
        throw new JSONException("can not cast to long, value : " + obj);
    }

    public static final Integer m(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof Number) {
            return Integer.valueOf(((Number) obj).intValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            return Integer.valueOf(Integer.parseInt(str));
        }
        throw new JSONException("can not cast to int, value : " + obj);
    }

    public static final byte[] n(Object obj) {
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        if (obj instanceof String) {
            return Base64.a((String) obj);
        }
        throw new JSONException("can not cast to int, value : " + obj);
    }

    public static final Boolean o(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof Number) {
            boolean z = true;
            if (((Number) obj).intValue() != 1) {
                z = false;
            }
            return Boolean.valueOf(z);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            if ("true".equals(str)) {
                return Boolean.TRUE;
            }
            if ("false".equals(str)) {
                return Boolean.FALSE;
            }
            if ("1".equals(str)) {
                return Boolean.TRUE;
            }
        }
        throw new JSONException("can not cast to int, value : " + obj);
    }

    public static final <T> T a(Object obj, Class<T> cls) {
        return a(obj, cls, ParserConfig.b());
    }

    public static final <T> T a(Object obj, Class<T> cls, ParserConfig parserConfig) {
        T t;
        if (obj == null) {
            return null;
        }
        if (cls == null) {
            throw new IllegalArgumentException("clazz is null");
        } else if (cls == obj.getClass()) {
            return obj;
        } else {
            if (obj instanceof Map) {
                if (cls == Map.class) {
                    return obj;
                }
                Map map = (Map) obj;
                if (cls != Object.class || map.containsKey(JSON.DEFAULT_TYPE_KEY)) {
                    return a((Map<String, Object>) map, cls, parserConfig);
                }
                return obj;
            } else if (cls.isArray() && (obj instanceof Collection)) {
                Collection<Object> collection = (Collection) obj;
                int i = 0;
                T newInstance = Array.newInstance(cls.getComponentType(), collection.size());
                for (Object a2 : collection) {
                    Array.set(newInstance, i, a(a2, cls.getComponentType(), parserConfig));
                    i++;
                }
                return newInstance;
            } else if (cls.isAssignableFrom(obj.getClass())) {
                return obj;
            } else {
                if (cls == Boolean.TYPE || cls == Boolean.class) {
                    return o(obj);
                }
                if (cls == Byte.TYPE || cls == Byte.class) {
                    return b(obj);
                }
                if (cls == Short.TYPE || cls == Short.class) {
                    return d(obj);
                }
                if (cls == Integer.TYPE || cls == Integer.class) {
                    return m(obj);
                }
                if (cls == Long.TYPE || cls == Long.class) {
                    return l(obj);
                }
                if (cls == Float.TYPE || cls == Float.class) {
                    return g(obj);
                }
                if (cls == Double.TYPE || cls == Double.class) {
                    return h(obj);
                }
                if (cls == String.class) {
                    return a(obj);
                }
                if (cls == BigDecimal.class) {
                    return e(obj);
                }
                if (cls == BigInteger.class) {
                    return f(obj);
                }
                if (cls == Date.class) {
                    return i(obj);
                }
                if (cls == java.sql.Date.class) {
                    return j(obj);
                }
                if (cls == Timestamp.class) {
                    return k(obj);
                }
                if (cls.isEnum()) {
                    return b(obj, cls, parserConfig);
                }
                if (Calendar.class.isAssignableFrom(cls)) {
                    Date i2 = i(obj);
                    if (cls == Calendar.class) {
                        t = Calendar.getInstance();
                    } else {
                        try {
                            t = (Calendar) cls.newInstance();
                        } catch (Exception e) {
                            throw new JSONException("can not cast to : " + cls.getName(), e);
                        }
                    }
                    t.setTime(i2);
                    return t;
                } else if ((obj instanceof String) && ((String) obj).length() == 0) {
                    return null;
                } else {
                    throw new JSONException("can not cast to : " + cls.getName());
                }
            }
        }
    }

    public static final <T> T b(Object obj, Class<T> cls, ParserConfig parserConfig) {
        try {
            if (obj instanceof String) {
                String str = (String) obj;
                if (str.length() == 0) {
                    return null;
                }
                return Enum.valueOf(cls, str);
            }
            if (obj instanceof Number) {
                int intValue = ((Number) obj).intValue();
                for (T t : (Object[]) cls.getMethod(MibiConstants.gf, new Class[0]).invoke((Object) null, new Object[0])) {
                    T t2 = (Enum) t;
                    if (t2.ordinal() == intValue) {
                        return t2;
                    }
                }
            }
            throw new JSONException("can not cast to : " + cls.getName());
        } catch (Exception e) {
            throw new JSONException("can not cast to : " + cls.getName(), e);
        }
    }

    public static final <T> T a(Object obj, Type type, ParserConfig parserConfig) {
        if (obj == null) {
            return null;
        }
        if (type instanceof Class) {
            return a(obj, (Class) type, parserConfig);
        }
        if (type instanceof ParameterizedType) {
            return a(obj, (ParameterizedType) type, parserConfig);
        }
        if ((obj instanceof String) && ((String) obj).length() == 0) {
            return null;
        }
        if (type instanceof TypeVariable) {
            return obj;
        }
        throw new JSONException("can not cast to : " + type);
    }

    public static final <T> T a(Object obj, ParameterizedType parameterizedType, ParserConfig parserConfig) {
        Type rawType = parameterizedType.getRawType();
        if (rawType == List.class || rawType == ArrayList.class) {
            Type type = parameterizedType.getActualTypeArguments()[0];
            if (obj instanceof Iterable) {
                T arrayList = new ArrayList();
                for (Object a2 : (Iterable) obj) {
                    arrayList.add(a(a2, type, parserConfig));
                }
                return arrayList;
            }
        }
        if (rawType == Map.class || rawType == HashMap.class) {
            Type type2 = parameterizedType.getActualTypeArguments()[0];
            Type type3 = parameterizedType.getActualTypeArguments()[1];
            if (obj instanceof Map) {
                T hashMap = new HashMap();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    hashMap.put(a(entry.getKey(), type2, parserConfig), a(entry.getValue(), type3, parserConfig));
                }
                return hashMap;
            }
        }
        if ((obj instanceof String) && ((String) obj).length() == 0) {
            return null;
        }
        if (parameterizedType.getActualTypeArguments().length == 1 && (parameterizedType.getActualTypeArguments()[0] instanceof WildcardType)) {
            return a(obj, rawType, parserConfig);
        }
        throw new JSONException("can not cast to : " + parameterizedType);
    }

    public static final <T> T a(Map<String, Object> map, Class<T> cls, ParserConfig parserConfig) {
        JSONObject jSONObject;
        int i = 0;
        if (cls == StackTraceElement.class) {
            try {
                String str = (String) map.get("className");
                String str2 = (String) map.get("methodName");
                String str3 = (String) map.get("fileName");
                Number number = (Number) map.get(StackTraceHelper.LINE_NUMBER_KEY);
                if (number != null) {
                    i = number.intValue();
                }
                return new StackTraceElement(str, str2, str3, i);
            } catch (Exception e) {
                throw new JSONException(e.getMessage(), e);
            }
        } else {
            Object obj = map.get(JSON.DEFAULT_TYPE_KEY);
            if (obj instanceof String) {
                String str4 = (String) obj;
                Class<?> a2 = a(str4);
                if (a2 == null) {
                    throw new ClassNotFoundException(str4 + " not found");
                } else if (!a2.equals(cls)) {
                    return a(map, a2, parserConfig);
                }
            }
            if (cls.isInterface()) {
                if (map instanceof JSONObject) {
                    jSONObject = (JSONObject) map;
                } else {
                    jSONObject = new JSONObject(map);
                }
                return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls}, jSONObject);
            }
            if (parserConfig == null) {
                parserConfig = ParserConfig.b();
            }
            Map<String, FieldDeserializer> b = parserConfig.b(cls);
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            T newInstance = declaredConstructor.newInstance(new Object[0]);
            for (Map.Entry next : b.entrySet()) {
                String str5 = (String) next.getKey();
                FieldDeserializer fieldDeserializer = (FieldDeserializer) next.getValue();
                if (map.containsKey(str5)) {
                    Object obj2 = map.get(str5);
                    Method b2 = fieldDeserializer.b();
                    if (b2 != null) {
                        b2.invoke(newInstance, new Object[]{a(obj2, b2.getGenericParameterTypes()[0], parserConfig)});
                    } else {
                        Field c = fieldDeserializer.c();
                        c.set(newInstance, a(obj2, c.getGenericType(), parserConfig));
                    }
                }
            }
            return newInstance;
        }
    }

    static {
        a();
    }

    public static void a(String str, Class<?> cls) {
        if (str == null) {
            str = cls.getName();
        }
        f6195a.put(str, cls);
    }

    public static void a() {
        f6195a.put("byte", Byte.TYPE);
        f6195a.put("short", Short.TYPE);
        f6195a.put("int", Integer.TYPE);
        f6195a.put("long", Long.TYPE);
        f6195a.put("float", Float.TYPE);
        f6195a.put("double", Double.TYPE);
        f6195a.put("boolean", Boolean.TYPE);
        f6195a.put("char", Character.TYPE);
        f6195a.put("[byte", byte[].class);
        f6195a.put("[short", short[].class);
        f6195a.put("[int", int[].class);
        f6195a.put("[long", long[].class);
        f6195a.put("[float", float[].class);
        f6195a.put("[double", double[].class);
        f6195a.put("[boolean", boolean[].class);
        f6195a.put("[char", char[].class);
        f6195a.put(HashMap.class.getName(), HashMap.class);
    }

    public static void b() {
        f6195a.clear();
        a();
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Class<?> a(java.lang.String r5) {
        /*
            if (r5 == 0) goto L_0x0068
            int r0 = r5.length()
            if (r0 != 0) goto L_0x0009
            goto L_0x0068
        L_0x0009:
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Class<?>> r0 = f6195a
            java.lang.Object r0 = r0.get(r5)
            java.lang.Class r0 = (java.lang.Class) r0
            if (r0 == 0) goto L_0x0014
            return r0
        L_0x0014:
            r1 = 0
            char r2 = r5.charAt(r1)
            r3 = 91
            r4 = 1
            if (r2 != r3) goto L_0x002f
            java.lang.String r5 = r5.substring(r4)
            java.lang.Class r5 = a((java.lang.String) r5)
            java.lang.Object r5 = java.lang.reflect.Array.newInstance(r5, r1)
            java.lang.Class r5 = r5.getClass()
            return r5
        L_0x002f:
            java.lang.String r1 = "L"
            boolean r1 = r5.startsWith(r1)
            if (r1 == 0) goto L_0x004d
            java.lang.String r1 = ";"
            boolean r1 = r5.endsWith(r1)
            if (r1 == 0) goto L_0x004d
            int r0 = r5.length()
            int r0 = r0 - r4
            java.lang.String r5 = r5.substring(r4, r0)
            java.lang.Class r5 = a((java.lang.String) r5)
            return r5
        L_0x004d:
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ Throwable -> 0x005e }
            java.lang.ClassLoader r1 = r1.getContextClassLoader()     // Catch:{ Throwable -> 0x005e }
            java.lang.Class r1 = r1.loadClass(r5)     // Catch:{ Throwable -> 0x005e }
            a((java.lang.String) r5, (java.lang.Class<?>) r1)     // Catch:{ Throwable -> 0x005d }
            return r1
        L_0x005d:
            r0 = r1
        L_0x005e:
            java.lang.Class r1 = java.lang.Class.forName(r5)     // Catch:{ Throwable -> 0x0067 }
            a((java.lang.String) r5, (java.lang.Class<?>) r1)     // Catch:{ Throwable -> 0x0066 }
            return r1
        L_0x0066:
            r0 = r1
        L_0x0067:
            return r0
        L_0x0068:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.imi.fastjson.util.TypeUtils.a(java.lang.String):java.lang.Class");
    }

    public static List<FieldInfo> a(Class<?> cls, Map<String, String> map) {
        return a(cls, map, true);
    }

    public static List<FieldInfo> a(Class<?> cls, Map<String, String> map, boolean z) {
        String[] strArr;
        boolean z2;
        String str;
        JSONField jSONField;
        String str2;
        JSONField jSONField2;
        Class<?> cls2 = cls;
        Map<String, String> map2 = map;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Method[] methods = cls.getMethods();
        int length = methods.length;
        int i = 0;
        while (true) {
            strArr = null;
            if (i >= length) {
                break;
            }
            Method method = methods[i];
            String name = method.getName();
            if (!Modifier.isStatic(method.getModifiers()) && !method.getReturnType().equals(Void.TYPE) && method.getParameterTypes().length == 0 && method.getReturnType() != ClassLoader.class && (!method.getName().equals("getMetaClass") || !method.getReturnType().getName().equals("groovy.lang.MetaClass"))) {
                JSONField jSONField3 = (JSONField) method.getAnnotation(JSONField.class);
                if (jSONField3 == null) {
                    jSONField3 = a(cls2, method);
                }
                if (jSONField3 != null) {
                    if (jSONField3.serialize()) {
                        if (jSONField3.name().length() != 0) {
                            String name2 = jSONField3.name();
                            if (map2 == null || (name2 = map2.get(name2)) != null) {
                                linkedHashMap.put(name2, new FieldInfo(name2, method, (Field) null));
                            }
                        }
                    }
                }
                if (name.startsWith("get")) {
                    if (name.length() >= 4 && !name.equals("getClass")) {
                        char charAt = name.charAt(3);
                        if (Character.isUpperCase(charAt)) {
                            str2 = Character.toLowerCase(name.charAt(3)) + name.substring(4);
                        } else if (charAt == '_') {
                            str2 = name.substring(4);
                        } else if (charAt == 'f') {
                            str2 = name.substring(3);
                        }
                        if (!a(cls2, str2)) {
                            Field a2 = ParserConfig.a(cls2, str2);
                            if (a2 == null) {
                                a2 = ParserConfig.a(cls2, str2);
                            }
                            if (!(a2 == null || (jSONField2 = (JSONField) a2.getAnnotation(JSONField.class)) == null)) {
                                if (jSONField2.serialize()) {
                                    if (jSONField2.name().length() != 0) {
                                        str2 = jSONField2.name();
                                        if (map2 != null && (str2 = map2.get(str2)) == null) {
                                        }
                                    }
                                }
                            }
                            if (map2 == null || (str2 = map2.get(str2)) != null) {
                                linkedHashMap.put(str2, new FieldInfo(str2, method, a2));
                            }
                        }
                    }
                }
                if (name.startsWith("is") && name.length() >= 3) {
                    char charAt2 = name.charAt(2);
                    if (Character.isUpperCase(charAt2)) {
                        str = Character.toLowerCase(name.charAt(2)) + name.substring(3);
                    } else if (charAt2 == '_') {
                        str = name.substring(3);
                    } else if (charAt2 == 'f') {
                        str = name.substring(2);
                    }
                    Field a3 = ParserConfig.a(cls2, str);
                    if (!(a3 == null || (jSONField = (JSONField) a3.getAnnotation(JSONField.class)) == null)) {
                        if (jSONField.serialize()) {
                            if (jSONField.name().length() != 0) {
                                str = jSONField.name();
                                if (map2 != null && (str = map2.get(str)) == null) {
                                }
                            }
                        }
                    }
                    if (map2 == null || (str = map2.get(str)) != null) {
                        linkedHashMap.put(str, new FieldInfo(str, method, a3));
                    }
                }
            }
            i++;
        }
        for (Field field : cls.getFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                JSONField jSONField4 = (JSONField) field.getAnnotation(JSONField.class);
                String name3 = field.getName();
                if (jSONField4 != null) {
                    if (jSONField4.serialize()) {
                        if (jSONField4.name().length() != 0) {
                            name3 = jSONField4.name();
                        }
                    }
                }
                if ((map2 == null || (name3 = map2.get(name3)) != null) && !linkedHashMap.containsKey(name3)) {
                    linkedHashMap.put(name3, new FieldInfo(name3, (Method) null, field));
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        JSONType jSONType = (JSONType) cls2.getAnnotation(JSONType.class);
        if (jSONType != null && (strArr = jSONType.orders()) != null && strArr.length == linkedHashMap.size()) {
            int length2 = strArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length2) {
                    z2 = true;
                    break;
                } else if (!linkedHashMap.containsKey(strArr[i2])) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        z2 = false;
        if (z2) {
            for (String str3 : strArr) {
                arrayList.add((FieldInfo) linkedHashMap.get(str3));
            }
        } else {
            for (FieldInfo add : linkedHashMap.values()) {
                arrayList.add(add);
            }
            if (z) {
                Collections.sort(arrayList);
            }
        }
        return arrayList;
    }

    public static JSONField a(Class<?> cls, Method method) {
        boolean z;
        JSONField jSONField;
        for (Class methods : cls.getInterfaces()) {
            for (Method method2 : methods.getMethods()) {
                if (method2.getName().equals(method.getName()) && method2.getParameterTypes().length == method.getParameterTypes().length) {
                    int i = 0;
                    while (true) {
                        if (i >= method2.getParameterTypes().length) {
                            z = true;
                            break;
                        } else if (!method2.getParameterTypes()[i].equals(method.getParameterTypes()[i])) {
                            z = false;
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (z && (jSONField = (JSONField) method2.getAnnotation(JSONField.class)) != null) {
                        return jSONField;
                    }
                }
            }
        }
        return null;
    }

    private static boolean a(Class<?> cls, String str) {
        JSONType jSONType = (JSONType) cls.getAnnotation(JSONType.class);
        if (!(jSONType == null || jSONType.ignores() == null)) {
            for (String equalsIgnoreCase : jSONType.ignores()) {
                if (str.equalsIgnoreCase(equalsIgnoreCase)) {
                    return true;
                }
            }
        }
        return (cls.getSuperclass() == Object.class || cls.getSuperclass() == null || !a((Class<?>) cls.getSuperclass(), str)) ? false : true;
    }

    public static Class<?> a(Type type) {
        if (type.getClass() == Class.class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return a(((ParameterizedType) type).getRawType());
        }
        return Object.class;
    }
}
