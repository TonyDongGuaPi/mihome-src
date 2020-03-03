package com.xiaomi.mimc.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class JSONObject {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f11200a = new Null();
    private final Map<String, Object> b;

    private static final class Null {
        /* access modifiers changed from: protected */
        public final Object clone() {
            return this;
        }

        public boolean equals(Object obj) {
            return obj == null || obj == this;
        }

        public int hashCode() {
            return 0;
        }

        public String toString() {
            return "null";
        }

        private Null() {
        }
    }

    public JSONObject() {
        this.b = new HashMap();
    }

    public JSONObject(JSONObject jSONObject, String[] strArr) {
        this(strArr.length);
        for (int i = 0; i < strArr.length; i++) {
            try {
                d(strArr[i], jSONObject.p(strArr[i]));
            } catch (Exception unused) {
            }
        }
    }

    public JSONObject(JSONTokener jSONTokener) throws JSONException {
        this();
        if (jSONTokener.e() == '{') {
            while (true) {
                char e = jSONTokener.e();
                if (e == 0) {
                    throw jSONTokener.b("A JSONObject text must end with '}'");
                } else if (e != '}') {
                    jSONTokener.a();
                    String obj = jSONTokener.f().toString();
                    if (jSONTokener.e() == ':') {
                        if (obj != null) {
                            if (p(obj) == null) {
                                Object f = jSONTokener.f();
                                if (f != null) {
                                    c(obj, f);
                                }
                            } else {
                                throw jSONTokener.b("Duplicate key \"" + obj + "\"");
                            }
                        }
                        char e2 = jSONTokener.e();
                        if (e2 == ',' || e2 == ';') {
                            if (jSONTokener.e() != '}') {
                                jSONTokener.a();
                            } else {
                                return;
                            }
                        } else if (e2 != '}') {
                            throw jSONTokener.b("Expected a ',' or '}'");
                        } else {
                            return;
                        }
                    } else {
                        throw jSONTokener.b("Expected a ':' after a key");
                    }
                } else {
                    return;
                }
            }
        } else {
            throw jSONTokener.b("A JSONObject text must begin with '{'");
        }
    }

    public JSONObject(Map<?, ?> map) {
        if (map == null) {
            this.b = new HashMap();
            return;
        }
        this.b = new HashMap(map.size());
        for (Map.Entry next : map.entrySet()) {
            if (next.getKey() != null) {
                Object value = next.getValue();
                if (value != null) {
                    this.b.put(String.valueOf(next.getKey()), e(value));
                }
            } else {
                throw new NullPointerException("Null key.");
            }
        }
    }

    public JSONObject(Object obj) {
        this();
        f(obj);
    }

    public JSONObject(Object obj, String[] strArr) {
        this(strArr.length);
        Class<?> cls = obj.getClass();
        for (String str : strArr) {
            try {
                e(str, cls.getField(str).get(obj));
            } catch (Exception unused) {
            }
        }
    }

    public JSONObject(String str) throws JSONException {
        this(new JSONTokener(str));
    }

    public JSONObject(String str, Locale locale) throws JSONException {
        this();
        ResourceBundle bundle = ResourceBundle.getBundle(str, locale, Thread.currentThread().getContextClassLoader());
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String nextElement = keys.nextElement();
            if (nextElement != null) {
                String str2 = nextElement;
                String[] split = str2.split("\\.");
                int length = split.length - 1;
                JSONObject jSONObject = this;
                for (int i = 0; i < length; i++) {
                    String str3 = split[i];
                    JSONObject v = jSONObject.v(str3);
                    if (v == null) {
                        v = new JSONObject();
                        jSONObject.c(str3, v);
                    }
                    jSONObject = v;
                }
                jSONObject.c(split[length], bundle.getString(str2));
            }
        }
    }

    protected JSONObject(int i) {
        this.b = new HashMap(i);
    }

    public JSONObject a(String str, Object obj) throws JSONException {
        c(obj);
        Object p = p(str);
        if (p == null) {
            if (obj instanceof JSONArray) {
                obj = new JSONArray().a(obj);
            }
            c(str, obj);
        } else if (p instanceof JSONArray) {
            ((JSONArray) p).a(obj);
        } else {
            c(str, new JSONArray().a(p).a(obj));
        }
        return this;
    }

    public JSONObject b(String str, Object obj) throws JSONException {
        c(obj);
        Object p = p(str);
        if (p == null) {
            c(str, new JSONArray().a(obj));
        } else if (p instanceof JSONArray) {
            c(str, ((JSONArray) p).a(obj));
        } else {
            throw new JSONException("JSONObject[" + str + "] is not a JSONArray.");
        }
        return this;
    }

    public static String a(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            return "null";
        }
        String d2 = Double.toString(d);
        if (d2.indexOf(46) <= 0 || d2.indexOf(101) >= 0 || d2.indexOf(69) >= 0) {
            return d2;
        }
        while (d2.endsWith("0")) {
            d2 = d2.substring(0, d2.length() - 1);
        }
        return d2.endsWith(".") ? d2.substring(0, d2.length() - 1) : d2;
    }

    public Object a(String str) throws JSONException {
        if (str != null) {
            Object p = p(str);
            if (p != null) {
                return p;
            }
            throw new JSONException("JSONObject[" + B(str) + "] not found.");
        }
        throw new JSONException("Null key.");
    }

    public <E extends Enum<E>> E a(Class<E> cls, String str) throws JSONException {
        E b2 = b(cls, str);
        if (b2 != null) {
            return b2;
        }
        throw new JSONException("JSONObject[" + B(str) + "] is not an enum of type " + B(cls.getSimpleName()) + ".");
    }

    public boolean b(String str) throws JSONException {
        Object a2 = a(str);
        if (a2.equals(Boolean.FALSE)) {
            return false;
        }
        boolean z = a2 instanceof String;
        if (z && ((String) a2).equalsIgnoreCase("false")) {
            return false;
        }
        if (a2.equals(Boolean.TRUE)) {
            return true;
        }
        if (z && ((String) a2).equalsIgnoreCase("true")) {
            return true;
        }
        throw new JSONException("JSONObject[" + B(str) + "] is not a Boolean.");
    }

    public BigInteger c(String str) throws JSONException {
        try {
            return new BigInteger(a(str).toString());
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + B(str) + "] could not be converted to BigInteger.", e);
        }
    }

    public BigDecimal d(String str) throws JSONException {
        Object a2 = a(str);
        if (a2 instanceof BigDecimal) {
            return (BigDecimal) a2;
        }
        try {
            return new BigDecimal(a2.toString());
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + B(str) + "] could not be converted to BigDecimal.", e);
        }
    }

    public double e(String str) throws JSONException {
        Object a2 = a(str);
        try {
            if (a2 instanceof Number) {
                return ((Number) a2).doubleValue();
            }
            return Double.parseDouble(a2.toString());
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + B(str) + "] is not a number.", e);
        }
    }

    public float f(String str) throws JSONException {
        Object a2 = a(str);
        try {
            if (a2 instanceof Number) {
                return ((Number) a2).floatValue();
            }
            return Float.parseFloat(a2.toString());
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + B(str) + "] is not a number.", e);
        }
    }

    public Number g(String str) throws JSONException {
        Object a2 = a(str);
        try {
            if (a2 instanceof Number) {
                return (Number) a2;
            }
            return E(a2.toString());
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + B(str) + "] is not a number.", e);
        }
    }

    public int h(String str) throws JSONException {
        Object a2 = a(str);
        try {
            if (a2 instanceof Number) {
                return ((Number) a2).intValue();
            }
            return Integer.parseInt((String) a2);
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + B(str) + "] is not an int.", e);
        }
    }

    public JSONArray i(String str) throws JSONException {
        Object a2 = a(str);
        if (a2 instanceof JSONArray) {
            return (JSONArray) a2;
        }
        throw new JSONException("JSONObject[" + B(str) + "] is not a JSONArray.");
    }

    public JSONObject j(String str) throws JSONException {
        Object a2 = a(str);
        if (a2 instanceof JSONObject) {
            return (JSONObject) a2;
        }
        throw new JSONException("JSONObject[" + B(str) + "] is not a JSONObject.");
    }

    public long k(String str) throws JSONException {
        Object a2 = a(str);
        try {
            if (a2 instanceof Number) {
                return ((Number) a2).longValue();
            }
            return Long.parseLong((String) a2);
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + B(str) + "] is not a long.", e);
        }
    }

    public static String[] a(JSONObject jSONObject) {
        if (jSONObject.e()) {
            return null;
        }
        return (String[]) jSONObject.b().toArray(new String[jSONObject.d()]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0004, code lost:
        r4 = r4.getClass().getFields();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String[] a(java.lang.Object r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.Class r4 = r4.getClass()
            java.lang.reflect.Field[] r4 = r4.getFields()
            int r1 = r4.length
            if (r1 != 0) goto L_0x0010
            return r0
        L_0x0010:
            java.lang.String[] r0 = new java.lang.String[r1]
            r2 = 0
        L_0x0013:
            if (r2 >= r1) goto L_0x0020
            r3 = r4[r2]
            java.lang.String r3 = r3.getName()
            r0[r2] = r3
            int r2 = r2 + 1
            goto L_0x0013
        L_0x0020:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.json.JSONObject.a(java.lang.Object):java.lang.String[]");
    }

    public String l(String str) throws JSONException {
        Object a2 = a(str);
        if (a2 instanceof String) {
            return (String) a2;
        }
        throw new JSONException("JSONObject[" + B(str) + "] not a string.");
    }

    public boolean m(String str) {
        return this.b.containsKey(str);
    }

    public JSONObject n(String str) throws JSONException {
        Object p = p(str);
        if (p == null) {
            b(str, 1);
        } else if (p instanceof BigInteger) {
            c(str, ((BigInteger) p).add(BigInteger.ONE));
        } else if (p instanceof BigDecimal) {
            c(str, ((BigDecimal) p).add(BigDecimal.ONE));
        } else if (p instanceof Integer) {
            b(str, ((Integer) p).intValue() + 1);
        } else if (p instanceof Long) {
            b(str, ((Long) p).longValue() + 1);
        } else if (p instanceof Double) {
            b(str, ((Double) p).doubleValue() + 1.0d);
        } else if (p instanceof Float) {
            b(str, ((Float) p).floatValue() + 1.0f);
        } else {
            throw new JSONException("Unable to increment [" + B(str) + "].");
        }
        return this;
    }

    public boolean o(String str) {
        return f11200a.equals(p(str));
    }

    public Iterator<String> a() {
        return b().iterator();
    }

    public Set<String> b() {
        return this.b.keySet();
    }

    /* access modifiers changed from: protected */
    public Set<Map.Entry<String, Object>> c() {
        return this.b.entrySet();
    }

    public int d() {
        return this.b.size();
    }

    public boolean e() {
        return this.b.isEmpty();
    }

    public JSONArray f() {
        if (this.b.isEmpty()) {
            return null;
        }
        return new JSONArray((Collection<?>) this.b.keySet());
    }

    public static String a(Number number) throws JSONException {
        if (number != null) {
            c((Object) number);
            String obj = number.toString();
            if (obj.indexOf(46) <= 0 || obj.indexOf(101) >= 0 || obj.indexOf(69) >= 0) {
                return obj;
            }
            while (obj.endsWith("0")) {
                obj = obj.substring(0, obj.length() - 1);
            }
            return obj.endsWith(".") ? obj.substring(0, obj.length() - 1) : obj;
        }
        throw new JSONException("Null pointer");
    }

    public Object p(String str) {
        if (str == null) {
            return null;
        }
        return this.b.get(str);
    }

    public <E extends Enum<E>> E b(Class<E> cls, String str) {
        return a(cls, str, (Enum) null);
    }

    public <E extends Enum<E>> E a(Class<E> cls, String str, E e) {
        try {
            E p = p(str);
            if (f11200a.equals(p)) {
                return e;
            }
            if (cls.isAssignableFrom(p.getClass())) {
                return (Enum) p;
            }
            return Enum.valueOf(cls, p.toString());
        } catch (IllegalArgumentException unused) {
            return e;
        } catch (NullPointerException unused2) {
            return e;
        }
    }

    public boolean q(String str) {
        return a(str, false);
    }

    public boolean a(String str, boolean z) {
        Object p = p(str);
        if (f11200a.equals(p)) {
            return z;
        }
        if (p instanceof Boolean) {
            return ((Boolean) p).booleanValue();
        }
        try {
            return b(str);
        } catch (Exception unused) {
            return z;
        }
    }

    public BigDecimal a(String str, BigDecimal bigDecimal) {
        Object p = p(str);
        if (f11200a.equals(p)) {
            return bigDecimal;
        }
        if (p instanceof BigDecimal) {
            return (BigDecimal) p;
        }
        if (p instanceof BigInteger) {
            return new BigDecimal((BigInteger) p);
        }
        if ((p instanceof Double) || (p instanceof Float)) {
            return new BigDecimal(((Number) p).doubleValue());
        }
        if ((p instanceof Long) || (p instanceof Integer) || (p instanceof Short) || (p instanceof Byte)) {
            return new BigDecimal(((Number) p).longValue());
        }
        try {
            return new BigDecimal(p.toString());
        } catch (Exception unused) {
            return bigDecimal;
        }
    }

    public BigInteger a(String str, BigInteger bigInteger) {
        Object p = p(str);
        if (f11200a.equals(p)) {
            return bigInteger;
        }
        if (p instanceof BigInteger) {
            return (BigInteger) p;
        }
        if (p instanceof BigDecimal) {
            return ((BigDecimal) p).toBigInteger();
        }
        if ((p instanceof Double) || (p instanceof Float)) {
            return new BigDecimal(((Number) p).doubleValue()).toBigInteger();
        }
        if ((p instanceof Long) || (p instanceof Integer) || (p instanceof Short) || (p instanceof Byte)) {
            return BigInteger.valueOf(((Number) p).longValue());
        }
        try {
            String obj = p.toString();
            if (D(obj)) {
                return new BigDecimal(obj).toBigInteger();
            }
            return new BigInteger(obj);
        } catch (Exception unused) {
            return bigInteger;
        }
    }

    public double r(String str) {
        return a(str, Double.NaN);
    }

    public double a(String str, double d) {
        Object p = p(str);
        if (f11200a.equals(p)) {
            return d;
        }
        if (p instanceof Number) {
            return ((Number) p).doubleValue();
        }
        if (!(p instanceof String)) {
            return d;
        }
        try {
            return Double.parseDouble((String) p);
        } catch (Exception unused) {
            return d;
        }
    }

    public float s(String str) {
        return a(str, Float.NaN);
    }

    public float a(String str, float f) {
        Object p = p(str);
        if (f11200a.equals(p)) {
            return f;
        }
        if (p instanceof Number) {
            return ((Number) p).floatValue();
        }
        if (!(p instanceof String)) {
            return f;
        }
        try {
            return Float.parseFloat((String) p);
        } catch (Exception unused) {
            return f;
        }
    }

    public int t(String str) {
        return a(str, 0);
    }

    public int a(String str, int i) {
        Object p = p(str);
        if (f11200a.equals(p)) {
            return i;
        }
        if (p instanceof Number) {
            return ((Number) p).intValue();
        }
        if (!(p instanceof String)) {
            return i;
        }
        try {
            return new BigDecimal((String) p).intValue();
        } catch (Exception unused) {
            return i;
        }
    }

    public JSONArray u(String str) {
        Object p = p(str);
        if (p instanceof JSONArray) {
            return (JSONArray) p;
        }
        return null;
    }

    public JSONObject v(String str) {
        Object p = p(str);
        if (p instanceof JSONObject) {
            return (JSONObject) p;
        }
        return null;
    }

    public long w(String str) {
        return a(str, 0);
    }

    public long a(String str, long j) {
        Object p = p(str);
        if (f11200a.equals(p)) {
            return j;
        }
        if (p instanceof Number) {
            return ((Number) p).longValue();
        }
        if (!(p instanceof String)) {
            return j;
        }
        try {
            return new BigDecimal((String) p).longValue();
        } catch (Exception unused) {
            return j;
        }
    }

    public Number x(String str) {
        return a(str, (Number) null);
    }

    public Number a(String str, Number number) {
        Object p = p(str);
        if (f11200a.equals(p)) {
            return number;
        }
        if (p instanceof Number) {
            return (Number) p;
        }
        if (!(p instanceof String)) {
            return number;
        }
        try {
            return E((String) p);
        } catch (Exception unused) {
            return number;
        }
    }

    public String y(String str) {
        return a(str, "");
    }

    public String a(String str, String str2) {
        Object p = p(str);
        return f11200a.equals(p) ? str2 : p.toString();
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[ExcHandler: IllegalAccessException | IllegalArgumentException | InvocationTargetException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:30:0x006f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void f(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Class r0 = r9.getClass()
            java.lang.ClassLoader r1 = r0.getClassLoader()
            r2 = 0
            if (r1 == 0) goto L_0x000d
            r1 = 1
            goto L_0x000e
        L_0x000d:
            r1 = 0
        L_0x000e:
            if (r1 == 0) goto L_0x0015
            java.lang.reflect.Method[] r0 = r0.getMethods()
            goto L_0x0019
        L_0x0015:
            java.lang.reflect.Method[] r0 = r0.getDeclaredMethods()
        L_0x0019:
            int r1 = r0.length
            r3 = 0
        L_0x001b:
            if (r3 >= r1) goto L_0x0077
            r4 = r0[r3]
            int r5 = r4.getModifiers()
            boolean r6 = java.lang.reflect.Modifier.isPublic(r5)
            if (r6 == 0) goto L_0x0074
            boolean r5 = java.lang.reflect.Modifier.isStatic(r5)
            if (r5 != 0) goto L_0x0074
            java.lang.Class[] r5 = r4.getParameterTypes()
            int r5 = r5.length
            if (r5 != 0) goto L_0x0074
            boolean r5 = r4.isBridge()
            if (r5 != 0) goto L_0x0074
            java.lang.Class r5 = r4.getReturnType()
            java.lang.Class r6 = java.lang.Void.TYPE
            if (r5 == r6) goto L_0x0074
            java.lang.String r5 = r4.getName()
            boolean r5 = r8.G(r5)
            if (r5 == 0) goto L_0x0074
            java.lang.String r5 = r8.a((java.lang.reflect.Method) r4)
            if (r5 == 0) goto L_0x0074
            boolean r6 = r5.isEmpty()
            if (r6 != 0) goto L_0x0074
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{  }
            java.lang.Object r4 = r4.invoke(r9, r6)     // Catch:{  }
            if (r4 == 0) goto L_0x0074
            java.util.Map<java.lang.String, java.lang.Object> r6 = r8.b     // Catch:{  }
            java.lang.Object r7 = e((java.lang.Object) r4)     // Catch:{  }
            r6.put(r5, r7)     // Catch:{  }
            boolean r5 = r4 instanceof java.io.Closeable     // Catch:{  }
            if (r5 == 0) goto L_0x0074
            java.io.Closeable r4 = (java.io.Closeable) r4     // Catch:{ IllegalAccessException | IllegalArgumentException | InvocationTargetException -> 0x0074, IllegalAccessException | IllegalArgumentException | InvocationTargetException -> 0x0074, IllegalAccessException | IllegalArgumentException | InvocationTargetException -> 0x0074 }
            r4.close()     // Catch:{ IllegalAccessException | IllegalArgumentException | InvocationTargetException -> 0x0074, IllegalAccessException | IllegalArgumentException | InvocationTargetException -> 0x0074, IllegalAccessException | IllegalArgumentException | InvocationTargetException -> 0x0074 }
        L_0x0074:
            int r3 = r3 + 1
            goto L_0x001b
        L_0x0077:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.json.JSONObject.f(java.lang.Object):void");
    }

    private boolean G(String str) {
        return !"getClass".equals(str) && !"getDeclaringClass".equals(str);
    }

    private String a(Method method) {
        String str;
        int b2;
        int b3 = b(method, (Class<? extends Annotation>) JSONPropertyIgnore.class);
        if (b3 > 0 && ((b2 = b(method, (Class<? extends Annotation>) JSONPropertyName.class)) < 0 || b3 <= b2)) {
            return null;
        }
        JSONPropertyName jSONPropertyName = (JSONPropertyName) a(method, JSONPropertyName.class);
        if (jSONPropertyName != null && jSONPropertyName.value() != null && !jSONPropertyName.value().isEmpty()) {
            return jSONPropertyName.value();
        }
        String name = method.getName();
        if (name.startsWith("get") && name.length() > 3) {
            str = name.substring(3);
        } else if (!name.startsWith("is") || name.length() <= 2) {
            return null;
        } else {
            str = name.substring(2);
        }
        if (Character.isLowerCase(str.charAt(0))) {
            return null;
        }
        if (str.length() == 1) {
            return str.toLowerCase(Locale.ROOT);
        }
        if (Character.isUpperCase(str.charAt(1))) {
            return str;
        }
        return str.substring(0, 1).toLowerCase(Locale.ROOT) + str.substring(1);
    }

    private static <A extends Annotation> A a(Method method, Class<A> cls) {
        if (method == null || cls == null) {
            return null;
        }
        if (method.isAnnotationPresent(cls)) {
            return method.getAnnotation(cls);
        }
        Class<?> declaringClass = method.getDeclaringClass();
        if (declaringClass.getSuperclass() == null) {
            return null;
        }
        Class[] interfaces = declaringClass.getInterfaces();
        int length = interfaces.length;
        int i = 0;
        while (i < length) {
            try {
                return a(interfaces[i].getMethod(method.getName(), method.getParameterTypes()), cls);
            } catch (NoSuchMethodException | SecurityException unused) {
                i++;
            }
        }
        try {
            return a(declaringClass.getSuperclass().getMethod(method.getName(), method.getParameterTypes()), cls);
        } catch (SecurityException unused2) {
            return null;
        } catch (NoSuchMethodException unused3) {
            return null;
        }
    }

    private static int b(Method method, Class<? extends Annotation> cls) {
        if (method == null || cls == null) {
            return -1;
        }
        if (method.isAnnotationPresent(cls)) {
            return 1;
        }
        Class<?> declaringClass = method.getDeclaringClass();
        if (declaringClass.getSuperclass() == null) {
            return -1;
        }
        Class[] interfaces = declaringClass.getInterfaces();
        int length = interfaces.length;
        int i = 0;
        while (i < length) {
            try {
                int b2 = b(interfaces[i].getMethod(method.getName(), method.getParameterTypes()), cls);
                if (b2 > 0) {
                    return b2 + 1;
                }
                i++;
            } catch (NoSuchMethodException | SecurityException unused) {
            }
        }
        try {
            int b3 = b(declaringClass.getSuperclass().getMethod(method.getName(), method.getParameterTypes()), cls);
            if (b3 > 0) {
                return b3 + 1;
            }
            return -1;
        } catch (SecurityException unused2) {
            return -1;
        } catch (NoSuchMethodException unused3) {
            return -1;
        }
    }

    public JSONObject b(String str, boolean z) throws JSONException {
        return c(str, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public JSONObject a(String str, Collection<?> collection) throws JSONException {
        return c(str, new JSONArray(collection));
    }

    public JSONObject b(String str, double d) throws JSONException {
        return c(str, Double.valueOf(d));
    }

    public JSONObject b(String str, float f) throws JSONException {
        return c(str, Float.valueOf(f));
    }

    public JSONObject b(String str, int i) throws JSONException {
        return c(str, Integer.valueOf(i));
    }

    public JSONObject b(String str, long j) throws JSONException {
        return c(str, Long.valueOf(j));
    }

    public JSONObject a(String str, Map<?, ?> map) throws JSONException {
        return c(str, new JSONObject(map));
    }

    public JSONObject c(String str, Object obj) throws JSONException {
        if (str != null) {
            if (obj != null) {
                c(obj);
                this.b.put(str, obj);
            } else {
                C(str);
            }
            return this;
        }
        throw new NullPointerException("Null key.");
    }

    public JSONObject d(String str, Object obj) throws JSONException {
        if (str == null || obj == null) {
            return this;
        }
        if (p(str) == null) {
            return c(str, obj);
        }
        throw new JSONException("Duplicate key \"" + str + "\"");
    }

    public JSONObject e(String str, Object obj) throws JSONException {
        return (str == null || obj == null) ? this : c(str, obj);
    }

    public Object z(String str) {
        return a(new JSONPointer(str));
    }

    public Object a(JSONPointer jSONPointer) {
        return jSONPointer.a((Object) this);
    }

    public Object A(String str) {
        return b(new JSONPointer(str));
    }

    public Object b(JSONPointer jSONPointer) {
        try {
            return jSONPointer.a((Object) this);
        } catch (JSONPointerException unused) {
            return null;
        }
    }

    public static String B(String str) {
        String obj;
        StringWriter stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            try {
                obj = a(str, (Writer) stringWriter).toString();
            } catch (IOException unused) {
                return "";
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }

    public static Writer a(String str, Writer writer) throws IOException {
        if (str == null || str.isEmpty()) {
            writer.write("\"\"");
            return writer;
        }
        int length = str.length();
        writer.write(34);
        int i = 0;
        char c = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt != '\"') {
                if (charAt != '/') {
                    if (charAt != '\\') {
                        switch (charAt) {
                            case 8:
                                writer.write("\\b");
                                break;
                            case 9:
                                writer.write("\\t");
                                break;
                            case 10:
                                writer.write("\\n");
                                break;
                            default:
                                switch (charAt) {
                                    case 12:
                                        writer.write("\\f");
                                        break;
                                    case 13:
                                        writer.write("\\r");
                                        break;
                                    default:
                                        if (charAt >= ' ' && ((charAt < 128 || charAt >= 160) && (charAt < 8192 || charAt >= 8448))) {
                                            writer.write(charAt);
                                            break;
                                        } else {
                                            writer.write("\\u");
                                            String hexString = Integer.toHexString(charAt);
                                            writer.write("0000", 0, 4 - hexString.length());
                                            writer.write(hexString);
                                            break;
                                        }
                                }
                        }
                    }
                } else {
                    if (c == '<') {
                        writer.write(92);
                    }
                    writer.write(charAt);
                }
                i++;
                c = charAt;
            }
            writer.write(92);
            writer.write(charAt);
            i++;
            c = charAt;
        }
        writer.write(34);
        return writer;
    }

    public Object C(String str) {
        return this.b.remove(str);
    }

    public boolean b(Object obj) {
        try {
            if (!(obj instanceof JSONObject) || !b().equals(((JSONObject) obj).b())) {
                return false;
            }
            for (Map.Entry next : c()) {
                Object value = next.getValue();
                Object a2 = ((JSONObject) obj).a((String) next.getKey());
                if (value != a2) {
                    if (value == null) {
                        return false;
                    }
                    if (value instanceof JSONObject) {
                        if (!((JSONObject) value).b(a2)) {
                            return false;
                        }
                    } else if (value instanceof JSONArray) {
                        if (!((JSONArray) value).b(a2)) {
                            return false;
                        }
                    } else if (!value.equals(a2)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    protected static boolean D(String str) {
        return str.indexOf(46) > -1 || str.indexOf(101) > -1 || str.indexOf(69) > -1 || "-0".equals(str);
    }

    protected static Number E(String str) throws NumberFormatException {
        char charAt = str.charAt(0);
        if ((charAt < '0' || charAt > '9') && charAt != '-') {
            throw new NumberFormatException("val [" + str + "] is not a valid number.");
        } else if (!D(str)) {
            BigInteger bigInteger = new BigInteger(str);
            if (bigInteger.bitLength() <= 31) {
                return Integer.valueOf(bigInteger.intValue());
            }
            return bigInteger.bitLength() <= 63 ? Long.valueOf(bigInteger.longValue()) : bigInteger;
        } else if (str.length() > 14) {
            return new BigDecimal(str);
        } else {
            Double valueOf = Double.valueOf(str);
            if (valueOf.isInfinite() || valueOf.isNaN()) {
                return new BigDecimal(str);
            }
            return valueOf;
        }
    }

    public static Object F(String str) {
        if (str.equals("")) {
            return str;
        }
        if (str.equalsIgnoreCase("true")) {
            return Boolean.TRUE;
        }
        if (str.equalsIgnoreCase("false")) {
            return Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("null")) {
            return f11200a;
        }
        char charAt = str.charAt(0);
        if ((charAt >= '0' && charAt <= '9') || charAt == '-') {
            try {
                if (D(str)) {
                    Double valueOf = Double.valueOf(str);
                    if (valueOf.isInfinite() || valueOf.isNaN()) {
                        return str;
                    }
                    return valueOf;
                }
                Long valueOf2 = Long.valueOf(str);
                if (str.equals(valueOf2.toString())) {
                    return valueOf2.longValue() == ((long) valueOf2.intValue()) ? Integer.valueOf(valueOf2.intValue()) : valueOf2;
                }
            } catch (Exception unused) {
            }
        }
        return str;
    }

    public static void c(Object obj) throws JSONException {
        if (obj == null) {
            return;
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            if (d.isInfinite() || d.isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        } else if (obj instanceof Float) {
            Float f = (Float) obj;
            if (f.isInfinite() || f.isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        }
    }

    public JSONArray a(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || jSONArray.c()) {
            return null;
        }
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.a(); i++) {
            jSONArray2.a(p(jSONArray.l(i)));
        }
        return jSONArray2;
    }

    public String toString() {
        try {
            return a(0);
        } catch (Exception unused) {
            return null;
        }
    }

    public String a(int i) throws JSONException {
        String obj;
        StringWriter stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            obj = a((Writer) stringWriter, i, 0).toString();
        }
        return obj;
    }

    public static String d(Object obj) throws JSONException {
        return JSONWriter.a(obj);
    }

    public static Object e(Object obj) {
        if (obj == null) {
            try {
                return f11200a;
            } catch (Exception unused) {
                return null;
            }
        } else {
            if (!(obj instanceof JSONObject) && !(obj instanceof JSONArray) && !f11200a.equals(obj) && !(obj instanceof JSONString) && !(obj instanceof Byte) && !(obj instanceof Character) && !(obj instanceof Short) && !(obj instanceof Integer) && !(obj instanceof Long) && !(obj instanceof Boolean) && !(obj instanceof Float) && !(obj instanceof Double) && !(obj instanceof String) && !(obj instanceof BigInteger) && !(obj instanceof BigDecimal)) {
                if (!(obj instanceof Enum)) {
                    if (obj instanceof Collection) {
                        return new JSONArray((Collection<?>) (Collection) obj);
                    }
                    if (obj.getClass().isArray()) {
                        return new JSONArray(obj);
                    }
                    if (obj instanceof Map) {
                        return new JSONObject((Map<?, ?>) (Map) obj);
                    }
                    Package packageR = obj.getClass().getPackage();
                    String name = packageR != null ? packageR.getName() : "";
                    if (!name.startsWith("java.") && !name.startsWith("javax.")) {
                        if (obj.getClass().getClassLoader() != null) {
                            return new JSONObject(obj);
                        }
                    }
                    return obj.toString();
                }
            }
            return obj;
        }
    }

    public Writer a(Writer writer) throws JSONException {
        return a(writer, 0, 0);
    }

    static final Writer a(Writer writer, Object obj, int i, int i2) throws JSONException, IOException {
        if (obj == null || obj.equals((Object) null)) {
            writer.write("null");
        } else if (obj instanceof JSONString) {
            try {
                String a2 = ((JSONString) obj).a();
                writer.write(a2 != null ? a2.toString() : B(obj.toString()));
            } catch (Exception e) {
                throw new JSONException((Throwable) e);
            }
        } else if (obj instanceof Number) {
            String a3 = a((Number) obj);
            try {
                new BigDecimal(a3);
                writer.write(a3);
            } catch (NumberFormatException unused) {
                a(a3, writer);
            }
        } else if (obj instanceof Boolean) {
            writer.write(obj.toString());
        } else if (obj instanceof Enum) {
            writer.write(B(((Enum) obj).name()));
        } else if (obj instanceof JSONObject) {
            ((JSONObject) obj).a(writer, i, i2);
        } else if (obj instanceof JSONArray) {
            ((JSONArray) obj).a(writer, i, i2);
        } else if (obj instanceof Map) {
            new JSONObject((Map<?, ?>) (Map) obj).a(writer, i, i2);
        } else if (obj instanceof Collection) {
            new JSONArray((Collection<?>) (Collection) obj).a(writer, i, i2);
        } else if (obj.getClass().isArray()) {
            new JSONArray(obj).a(writer, i, i2);
        } else {
            a(obj.toString(), writer);
        }
        return writer;
    }

    static final void a(Writer writer, int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            writer.write(32);
        }
    }

    public Writer a(Writer writer, int i, int i2) throws JSONException {
        String str;
        String str2;
        boolean z = false;
        try {
            int d = d();
            writer.write(123);
            if (d == 1) {
                Map.Entry next = c().iterator().next();
                str2 = (String) next.getKey();
                writer.write(B(str2));
                writer.write(58);
                if (i > 0) {
                    writer.write(32);
                }
                a(writer, next.getValue(), i, i2);
            } else if (d != 0) {
                int i3 = i2 + i;
                for (Map.Entry next2 : c()) {
                    if (z) {
                        writer.write(44);
                    }
                    if (i > 0) {
                        writer.write(10);
                    }
                    a(writer, i3);
                    str = (String) next2.getKey();
                    writer.write(B(str));
                    writer.write(58);
                    if (i > 0) {
                        writer.write(32);
                    }
                    a(writer, next2.getValue(), i, i3);
                    z = true;
                }
                if (i > 0) {
                    writer.write(10);
                }
                a(writer, i2);
            }
            writer.write(125);
            return writer;
        } catch (Exception e) {
            throw new JSONException("Unable to write JSONObject value for key: " + str, e);
        } catch (Exception e2) {
            throw new JSONException("Unable to write JSONObject value for key: " + str2, e2);
        } catch (IOException e3) {
            throw new JSONException((Throwable) e3);
        }
    }

    public Map<String, Object> g() {
        Object obj;
        HashMap hashMap = new HashMap();
        for (Map.Entry next : c()) {
            if (next.getValue() == null || f11200a.equals(next.getValue())) {
                obj = null;
            } else if (next.getValue() instanceof JSONObject) {
                obj = ((JSONObject) next.getValue()).g();
            } else {
                obj = next.getValue() instanceof JSONArray ? ((JSONArray) next.getValue()).b() : next.getValue();
            }
            hashMap.put(next.getKey(), obj);
        }
        return hashMap;
    }
}
