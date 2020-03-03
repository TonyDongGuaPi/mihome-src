package org.apache.commons.lang.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

public class ExceptionUtils {

    /* renamed from: a  reason: collision with root package name */
    static final String f3386a = " [wrapped] ";
    static Class b;
    private static final Object c = new Object();
    private static String[] d = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"};
    private static final Method e;
    private static final Method f;

    static {
        Method method;
        Class cls;
        Class cls2;
        Class cls3;
        Method method2 = null;
        try {
            if (b == null) {
                cls3 = e("java.lang.Throwable");
                b = cls3;
            } else {
                cls3 = b;
            }
            method = cls3.getMethod("getCause", (Class[]) null);
        } catch (Exception unused) {
            method = null;
        }
        e = method;
        try {
            if (b == null) {
                cls = e("java.lang.Throwable");
                b = cls;
            } else {
                cls = b;
            }
            Class[] clsArr = new Class[1];
            if (b == null) {
                cls2 = e("java.lang.Throwable");
                b = cls2;
            } else {
                cls2 = b;
            }
            clsArr[0] = cls2;
            method2 = cls.getMethod("initCause", clsArr);
        } catch (Exception unused2) {
        }
        f = method2;
    }

    static Class e(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError(e2.getMessage());
        }
    }

    public static void a(String str) {
        if (StringUtils.b(str) && !c(str)) {
            ArrayList b2 = b();
            if (b2.add(str)) {
                synchronized (c) {
                    d = a((List) b2);
                }
            }
        }
    }

    public static void b(String str) {
        if (StringUtils.b(str)) {
            ArrayList b2 = b();
            if (b2.remove(str)) {
                synchronized (c) {
                    d = a((List) b2);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0020 A[Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0035 }] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0029 A[Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0035 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.Throwable r7, java.lang.Throwable r8) {
        /*
            if (r7 == 0) goto L_0x0036
            r0 = 1
            java.lang.Object[] r1 = new java.lang.Object[r0]
            r2 = 0
            r1[r2] = r8
            java.lang.reflect.Method r8 = f
            if (r8 == 0) goto L_0x0013
            java.lang.reflect.Method r8 = f     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x0013 }
            r8.invoke(r7, r1)     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x0013 }
            r8 = 1
            goto L_0x0014
        L_0x0013:
            r8 = 0
        L_0x0014:
            java.lang.Class r3 = r7.getClass()     // Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0035 }
            java.lang.String r4 = "setCause"
            java.lang.Class[] r5 = new java.lang.Class[r0]     // Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0035 }
            java.lang.Class r6 = b     // Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0035 }
            if (r6 != 0) goto L_0x0029
            java.lang.String r6 = "java.lang.Throwable"
            java.lang.Class r6 = e((java.lang.String) r6)     // Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0035 }
            b = r6     // Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0035 }
            goto L_0x002b
        L_0x0029:
            java.lang.Class r6 = b     // Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0035 }
        L_0x002b:
            r5[r2] = r6     // Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0035 }
            java.lang.reflect.Method r2 = r3.getMethod(r4, r5)     // Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0035 }
            r2.invoke(r7, r1)     // Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0035 }
            r8 = 1
        L_0x0035:
            return r8
        L_0x0036:
            org.apache.commons.lang.NullArgumentException r7 = new org.apache.commons.lang.NullArgumentException
            java.lang.String r8 = "target"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.exception.ExceptionUtils.a(java.lang.Throwable, java.lang.Throwable):boolean");
    }

    private static String[] a(List list) {
        return (String[]) list.toArray(new String[list.size()]);
    }

    private static ArrayList b() {
        ArrayList arrayList;
        synchronized (c) {
            arrayList = new ArrayList(Arrays.asList(d));
        }
        return arrayList;
    }

    public static boolean c(String str) {
        boolean z;
        synchronized (c) {
            z = ArrayUtils.a((Object[]) d, (Object) str) >= 0;
        }
        return z;
    }

    public static Throwable a(Throwable th) {
        Throwable a2;
        synchronized (c) {
            a2 = a(th, d);
        }
        return a2;
    }

    public static Throwable a(Throwable th, String[] strArr) {
        if (th == null) {
            return null;
        }
        Throwable o = o(th);
        if (o != null) {
            return o;
        }
        if (strArr == null) {
            synchronized (c) {
                strArr = d;
            }
        }
        int i = 0;
        while (i < strArr.length && ((r2 = strArr[i]) == null || (o = a(th, r2)) == null)) {
            i++;
        }
        return o == null ? b(th, "detail") : o;
    }

    public static Throwable b(Throwable th) {
        List f2 = f(th);
        if (f2.size() < 2) {
            return null;
        }
        return (Throwable) f2.get(f2.size() - 1);
    }

    private static Throwable o(Throwable th) {
        if (th instanceof Nestable) {
            return ((Nestable) th).getCause();
        }
        if (th instanceof SQLException) {
            return ((SQLException) th).getNextException();
        }
        if (th instanceof InvocationTargetException) {
            return ((InvocationTargetException) th).getTargetException();
        }
        return null;
    }

    private static Throwable a(Throwable th, String str) {
        Method method;
        Class cls;
        try {
            method = th.getClass().getMethod(str, (Class[]) null);
        } catch (NoSuchMethodException | SecurityException unused) {
            method = null;
        }
        if (method != null) {
            if (b == null) {
                cls = e("java.lang.Throwable");
                b = cls;
            } else {
                cls = b;
            }
            if (cls.isAssignableFrom(method.getReturnType())) {
                try {
                    return (Throwable) method.invoke(th, ArrayUtils.f3354a);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
                }
            }
        }
        return null;
    }

    private static Throwable b(Throwable th, String str) {
        Field field;
        Class cls;
        try {
            field = th.getClass().getField(str);
        } catch (NoSuchFieldException | SecurityException unused) {
            field = null;
        }
        if (field != null) {
            if (b == null) {
                cls = e("java.lang.Throwable");
                b = cls;
            } else {
                cls = b;
            }
            if (cls.isAssignableFrom(field.getType())) {
                try {
                    return (Throwable) field.get(th);
                } catch (IllegalAccessException | IllegalArgumentException unused2) {
                }
            }
        }
        return null;
    }

    public static boolean a() {
        return e != null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0058, code lost:
        if (r7.getField("detail") == null) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x005a, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean c(java.lang.Throwable r7) {
        /*
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r7 instanceof org.apache.commons.lang.exception.Nestable
            r2 = 1
            if (r1 == 0) goto L_0x000a
            return r2
        L_0x000a:
            boolean r1 = r7 instanceof java.sql.SQLException
            if (r1 == 0) goto L_0x000f
            return r2
        L_0x000f:
            boolean r1 = r7 instanceof java.lang.reflect.InvocationTargetException
            if (r1 == 0) goto L_0x0014
            return r2
        L_0x0014:
            boolean r1 = a()
            if (r1 == 0) goto L_0x001b
            return r2
        L_0x001b:
            java.lang.Class r7 = r7.getClass()
            java.lang.Object r1 = c
            monitor-enter(r1)
            java.lang.String[] r3 = d     // Catch:{ all -> 0x005c }
            int r3 = r3.length     // Catch:{ all -> 0x005c }
            r4 = 0
        L_0x0026:
            if (r4 >= r3) goto L_0x0051
            java.lang.String[] r5 = d     // Catch:{ NoSuchMethodException | SecurityException -> 0x004e }
            r5 = r5[r4]     // Catch:{ NoSuchMethodException | SecurityException -> 0x004e }
            r6 = 0
            java.lang.reflect.Method r5 = r7.getMethod(r5, r6)     // Catch:{ NoSuchMethodException | SecurityException -> 0x004e }
            if (r5 == 0) goto L_0x004e
            java.lang.Class r6 = b     // Catch:{ NoSuchMethodException | SecurityException -> 0x004e }
            if (r6 != 0) goto L_0x0040
            java.lang.String r6 = "java.lang.Throwable"
            java.lang.Class r6 = e((java.lang.String) r6)     // Catch:{ NoSuchMethodException | SecurityException -> 0x004e }
            b = r6     // Catch:{ NoSuchMethodException | SecurityException -> 0x004e }
            goto L_0x0042
        L_0x0040:
            java.lang.Class r6 = b     // Catch:{ NoSuchMethodException | SecurityException -> 0x004e }
        L_0x0042:
            java.lang.Class r5 = r5.getReturnType()     // Catch:{ NoSuchMethodException | SecurityException -> 0x004e }
            boolean r5 = r6.isAssignableFrom(r5)     // Catch:{ NoSuchMethodException | SecurityException -> 0x004e }
            if (r5 == 0) goto L_0x004e
            monitor-exit(r1)     // Catch:{ all -> 0x005c }
            return r2
        L_0x004e:
            int r4 = r4 + 1
            goto L_0x0026
        L_0x0051:
            monitor-exit(r1)     // Catch:{ all -> 0x005c }
            java.lang.String r1 = "detail"
            java.lang.reflect.Field r7 = r7.getField(r1)     // Catch:{ NoSuchFieldException | SecurityException -> 0x005b }
            if (r7 == 0) goto L_0x005b
            return r2
        L_0x005b:
            return r0
        L_0x005c:
            r7 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x005c }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.exception.ExceptionUtils.c(java.lang.Throwable):boolean");
    }

    public static int d(Throwable th) {
        return f(th).size();
    }

    public static Throwable[] e(Throwable th) {
        List f2 = f(th);
        return (Throwable[]) f2.toArray(new Throwable[f2.size()]);
    }

    public static List f(Throwable th) {
        ArrayList arrayList = new ArrayList();
        while (th != null && !arrayList.contains(th)) {
            arrayList.add(th);
            th = a(th);
        }
        return arrayList;
    }

    public static int a(Throwable th, Class cls) {
        return a(th, cls, 0, false);
    }

    public static int a(Throwable th, Class cls, int i) {
        return a(th, cls, i, false);
    }

    public static int b(Throwable th, Class cls) {
        return a(th, cls, 0, true);
    }

    public static int b(Throwable th, Class cls, int i) {
        return a(th, cls, i, true);
    }

    private static int a(Throwable th, Class cls, int i, boolean z) {
        if (th == null || cls == null) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        Throwable[] e2 = e(th);
        if (i >= e2.length) {
            return -1;
        }
        if (z) {
            while (i < e2.length) {
                if (cls.isAssignableFrom(e2[i].getClass())) {
                    return i;
                }
                i++;
            }
        } else {
            while (i < e2.length) {
                if (cls.equals(e2[i].getClass())) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    public static void g(Throwable th) {
        a(th, System.err);
    }

    public static void a(Throwable th, PrintStream printStream) {
        if (th != null) {
            if (printStream != null) {
                String[] h = h(th);
                for (String println : h) {
                    printStream.println(println);
                }
                printStream.flush();
                return;
            }
            throw new IllegalArgumentException("The PrintStream must not be null");
        }
    }

    public static void a(Throwable th, PrintWriter printWriter) {
        if (th != null) {
            if (printWriter != null) {
                String[] h = h(th);
                for (String println : h) {
                    printWriter.println(println);
                }
                printWriter.flush();
                return;
            }
            throw new IllegalArgumentException("The PrintWriter must not be null");
        }
    }

    public static String[] h(Throwable th) {
        List list;
        if (th == null) {
            return ArrayUtils.c;
        }
        Throwable[] e2 = e(th);
        int length = e2.length;
        ArrayList arrayList = new ArrayList();
        int i = length - 1;
        List l = l(e2[i]);
        while (true) {
            length--;
            if (length < 0) {
                return (String[]) arrayList.toArray(new String[0]);
            }
            if (length != 0) {
                list = l(e2[length - 1]);
                a(l, list);
            } else {
                list = l;
            }
            if (length == i) {
                arrayList.add(e2[length].toString());
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(f3386a);
                stringBuffer.append(e2[length].toString());
                arrayList.add(stringBuffer.toString());
            }
            for (int i2 = 0; i2 < l.size(); i2++) {
                arrayList.add(l.get(i2));
            }
            l = list;
        }
    }

    public static void a(List list, List list2) {
        if (list == null || list2 == null) {
            throw new IllegalArgumentException("The List must not be null");
        }
        int size = list.size() - 1;
        int size2 = list2.size() - 1;
        while (size >= 0 && size2 >= 0) {
            if (((String) list.get(size)).equals((String) list2.get(size2))) {
                list.remove(size);
            }
            size--;
            size2--;
        }
    }

    public static String i(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);
        Throwable[] e2 = e(th);
        for (int i = 0; i < e2.length; i++) {
            e2[i].printStackTrace(printWriter);
            if (c(e2[i])) {
                break;
            }
        }
        return stringWriter.getBuffer().toString();
    }

    public static String j(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter, true));
        return stringWriter.getBuffer().toString();
    }

    public static String[] k(Throwable th) {
        if (th == null) {
            return ArrayUtils.c;
        }
        return d(j(th));
    }

    static String[] d(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, SystemUtils.F);
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        return a((List) arrayList);
    }

    static List l(Throwable th) {
        StringTokenizer stringTokenizer = new StringTokenizer(j(th), SystemUtils.F);
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            int indexOf = nextToken.indexOf("at");
            if (indexOf != -1 && nextToken.substring(0, indexOf).trim().length() == 0) {
                z = true;
                arrayList.add(nextToken);
            } else if (z) {
                break;
            }
        }
        return arrayList;
    }

    public static String m(Throwable th) {
        if (th == null) {
            return "";
        }
        String a2 = ClassUtils.a((Object) th, (String) null);
        String message = th.getMessage();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(a2);
        stringBuffer.append(": ");
        stringBuffer.append(StringUtils.P(message));
        return stringBuffer.toString();
    }

    public static String n(Throwable th) {
        Throwable b2 = b(th);
        if (b2 != null) {
            th = b2;
        }
        return m(th);
    }
}
