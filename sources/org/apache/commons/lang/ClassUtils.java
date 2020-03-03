package org.apache.commons.lang;

import com.adobe.xmp.XMPConst;
import com.alipay.sdk.util.i;
import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import miuipub.reflect.Field;
import org.apache.commons.lang.text.StrBuilder;

public class ClassUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final char f3360a = '.';
    public static final String b = String.valueOf('.');
    public static final char c = '$';
    public static final String d = String.valueOf('$');
    static Class e;
    static Class f;
    static Class g;
    static Class h;
    static Class i;
    static Class j;
    static Class k;
    static Class l;
    static Class m;
    private static final Map n = new HashMap();
    private static final Map o = new HashMap();
    private static final Map p = new HashMap();
    private static final Map q = new HashMap();

    static {
        Class cls;
        Class cls2;
        Class cls3;
        Class cls4;
        Class cls5;
        Class cls6;
        Class cls7;
        Class cls8;
        Map map = n;
        Class cls9 = Boolean.TYPE;
        if (e == null) {
            cls = f("java.lang.Boolean");
            e = cls;
        } else {
            cls = e;
        }
        map.put(cls9, cls);
        Map map2 = n;
        Class cls10 = Byte.TYPE;
        if (f == null) {
            cls2 = f("java.lang.Byte");
            f = cls2;
        } else {
            cls2 = f;
        }
        map2.put(cls10, cls2);
        Map map3 = n;
        Class cls11 = Character.TYPE;
        if (g == null) {
            cls3 = f("java.lang.Character");
            g = cls3;
        } else {
            cls3 = g;
        }
        map3.put(cls11, cls3);
        Map map4 = n;
        Class cls12 = Short.TYPE;
        if (h == null) {
            cls4 = f("java.lang.Short");
            h = cls4;
        } else {
            cls4 = h;
        }
        map4.put(cls12, cls4);
        Map map5 = n;
        Class cls13 = Integer.TYPE;
        if (i == null) {
            cls5 = f("java.lang.Integer");
            i = cls5;
        } else {
            cls5 = i;
        }
        map5.put(cls13, cls5);
        Map map6 = n;
        Class cls14 = Long.TYPE;
        if (j == null) {
            cls6 = f("java.lang.Long");
            j = cls6;
        } else {
            cls6 = j;
        }
        map6.put(cls14, cls6);
        Map map7 = n;
        Class cls15 = Double.TYPE;
        if (k == null) {
            cls7 = f("java.lang.Double");
            k = cls7;
        } else {
            cls7 = k;
        }
        map7.put(cls15, cls7);
        Map map8 = n;
        Class cls16 = Float.TYPE;
        if (l == null) {
            cls8 = f("java.lang.Float");
            l = cls8;
        } else {
            cls8 = l;
        }
        map8.put(cls16, cls8);
        n.put(Void.TYPE, Void.TYPE);
        for (Class cls17 : n.keySet()) {
            Class cls18 = (Class) n.get(cls17);
            if (!cls17.equals(cls18)) {
                o.put(cls18, cls17);
            }
        }
        a("int", Field.e);
        a("boolean", Field.f3009a);
        a("float", Field.g);
        a("long", Field.f);
        a("short", "S");
        a("byte", Field.b);
        a("double", Field.h);
        a("char", Field.c);
    }

    static Class f(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError(e2.getMessage());
        }
    }

    private static void a(String str, String str2) {
        p.put(str, str2);
        q.put(str2, str);
    }

    public static String a(Object obj, String str) {
        return obj == null ? str : a((Class) obj.getClass());
    }

    public static String a(Class cls) {
        return cls == null ? "" : a(cls.getName());
    }

    public static String a(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        StrBuilder strBuilder = new StrBuilder();
        int i2 = 0;
        if (str.startsWith(Operators.ARRAY_START_STR)) {
            while (str.charAt(0) == '[') {
                str = str.substring(1);
                strBuilder.c(XMPConst.ai);
            }
            if (str.charAt(0) == 'L' && str.charAt(str.length() - 1) == ';') {
                str = str.substring(1, str.length() - 1);
            }
        }
        if (q.containsKey(str)) {
            str = (String) q.get(str);
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf != -1) {
            i2 = lastIndexOf + 1;
        }
        int indexOf = str.indexOf(36, i2);
        String substring = str.substring(lastIndexOf + 1);
        if (indexOf != -1) {
            substring = substring.replace('$', '.');
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(substring);
        stringBuffer.append(strBuilder);
        return stringBuffer.toString();
    }

    public static String b(Object obj, String str) {
        return obj == null ? str : b((Class) obj.getClass());
    }

    public static String b(Class cls) {
        return cls == null ? "" : b(cls.getName());
    }

    public static String b(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        while (str.charAt(0) == '[') {
            str = str.substring(1);
        }
        if (str.charAt(0) == 'L' && str.charAt(str.length() - 1) == ';') {
            str = str.substring(1);
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return "";
        }
        return str.substring(0, lastIndexOf);
    }

    public static List c(Class cls) {
        if (cls == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Class superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            arrayList.add(superclass);
        }
        return arrayList;
    }

    public static List d(Class cls) {
        if (cls == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        a(cls, (List) arrayList);
        return arrayList;
    }

    private static void a(Class cls, List list) {
        while (cls != null) {
            Class[] interfaces = cls.getInterfaces();
            for (int i2 = 0; i2 < interfaces.length; i2++) {
                if (!list.contains(interfaces[i2])) {
                    list.add(interfaces[i2]);
                    a(interfaces[i2], list);
                }
            }
            cls = cls.getSuperclass();
        }
    }

    public static List a(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(Class.forName((String) it.next()));
            } catch (Exception unused) {
                arrayList.add((Object) null);
            }
        }
        return arrayList;
    }

    public static List b(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Class cls = (Class) it.next();
            if (cls == null) {
                arrayList.add((Object) null);
            } else {
                arrayList.add(cls.getName());
            }
        }
        return arrayList;
    }

    public static boolean a(Class[] clsArr, Class[] clsArr2) {
        return a(clsArr, clsArr2, false);
    }

    public static boolean a(Class[] clsArr, Class[] clsArr2, boolean z) {
        if (!ArrayUtils.a((Object[]) clsArr, (Object[]) clsArr2)) {
            return false;
        }
        if (clsArr == null) {
            clsArr = ArrayUtils.b;
        }
        if (clsArr2 == null) {
            clsArr2 = ArrayUtils.b;
        }
        for (int i2 = 0; i2 < clsArr.length; i2++) {
            if (!a(clsArr[i2], clsArr2[i2], z)) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(Class cls, Class cls2) {
        return a(cls, cls2, false);
    }

    public static boolean a(Class cls, Class cls2, boolean z) {
        if (cls2 == null) {
            return false;
        }
        if (cls == null) {
            return !cls2.isPrimitive();
        }
        if (z) {
            if (cls.isPrimitive() && !cls2.isPrimitive() && (cls = e(cls)) == null) {
                return false;
            }
            if (cls2.isPrimitive() && !cls.isPrimitive() && (cls = f(cls)) == null) {
                return false;
            }
        }
        if (cls.equals(cls2)) {
            return true;
        }
        if (!cls.isPrimitive()) {
            return cls2.isAssignableFrom(cls);
        }
        if (!cls2.isPrimitive()) {
            return false;
        }
        if (Integer.TYPE.equals(cls)) {
            if (Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2)) {
                return true;
            }
            return false;
        } else if (Long.TYPE.equals(cls)) {
            if (Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2)) {
                return true;
            }
            return false;
        } else if (Boolean.TYPE.equals(cls) || Double.TYPE.equals(cls)) {
            return false;
        } else {
            if (Float.TYPE.equals(cls)) {
                return Double.TYPE.equals(cls2);
            }
            if (Character.TYPE.equals(cls)) {
                if (Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2)) {
                    return true;
                }
                return false;
            } else if (Short.TYPE.equals(cls)) {
                if (Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2)) {
                    return true;
                }
                return false;
            } else if (!Byte.TYPE.equals(cls)) {
                return false;
            } else {
                if (Short.TYPE.equals(cls2) || Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2)) {
                    return true;
                }
                return false;
            }
        }
    }

    public static Class e(Class cls) {
        return (cls == null || !cls.isPrimitive()) ? cls : (Class) n.get(cls);
    }

    public static Class[] a(Class[] clsArr) {
        if (clsArr == null) {
            return null;
        }
        if (clsArr.length == 0) {
            return clsArr;
        }
        Class[] clsArr2 = new Class[clsArr.length];
        for (int i2 = 0; i2 < clsArr.length; i2++) {
            clsArr2[i2] = e(clsArr[i2]);
        }
        return clsArr2;
    }

    public static Class f(Class cls) {
        return (Class) o.get(cls);
    }

    public static Class[] b(Class[] clsArr) {
        if (clsArr == null) {
            return null;
        }
        if (clsArr.length == 0) {
            return clsArr;
        }
        Class[] clsArr2 = new Class[clsArr.length];
        for (int i2 = 0; i2 < clsArr.length; i2++) {
            clsArr2[i2] = f(clsArr[i2]);
        }
        return clsArr2;
    }

    public static boolean g(Class cls) {
        return cls != null && cls.getName().indexOf(36) >= 0;
    }

    public static Class a(ClassLoader classLoader, String str, boolean z) throws ClassNotFoundException {
        try {
            if (!p.containsKey(str)) {
                return Class.forName(g(str), z, classLoader);
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(Operators.ARRAY_START_STR);
            stringBuffer.append(p.get(str));
            return Class.forName(stringBuffer.toString(), z, classLoader).getComponentType();
        } catch (ClassNotFoundException e2) {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf != -1) {
                try {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(str.substring(0, lastIndexOf));
                    stringBuffer2.append('$');
                    stringBuffer2.append(str.substring(lastIndexOf + 1));
                    return a(classLoader, stringBuffer2.toString(), z);
                } catch (ClassNotFoundException unused) {
                    throw e2;
                }
            }
            throw e2;
        }
    }

    public static Class a(ClassLoader classLoader, String str) throws ClassNotFoundException {
        return a(classLoader, str, true);
    }

    public static Class c(String str) throws ClassNotFoundException {
        return a(str, true);
    }

    public static Class a(String str, boolean z) throws ClassNotFoundException {
        Class cls;
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader == null) {
            if (m == null) {
                cls = f("org.apache.commons.lang.ClassUtils");
                m = cls;
            } else {
                cls = m;
            }
            contextClassLoader = cls.getClassLoader();
        }
        return a(contextClassLoader, str, z);
    }

    public static Method a(Class cls, String str, Class[] clsArr) throws SecurityException, NoSuchMethodException {
        Method method = cls.getMethod(str, clsArr);
        if (Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
            return method;
        }
        ArrayList<Class> arrayList = new ArrayList<>();
        arrayList.addAll(d(cls));
        arrayList.addAll(c(cls));
        for (Class cls2 : arrayList) {
            if (Modifier.isPublic(cls2.getModifiers())) {
                try {
                    Method method2 = cls2.getMethod(str, clsArr);
                    if (Modifier.isPublic(method2.getDeclaringClass().getModifiers())) {
                        return method2;
                    }
                } catch (NoSuchMethodException unused) {
                    continue;
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Can't find a public method for ");
        stringBuffer.append(str);
        stringBuffer.append(" ");
        stringBuffer.append(ArrayUtils.a((Object) clsArr));
        throw new NoSuchMethodException(stringBuffer.toString());
    }

    private static String g(String str) {
        String q2 = StringUtils.q(str);
        if (q2 == null) {
            throw new NullArgumentException("className");
        } else if (!q2.endsWith(XMPConst.ai)) {
            return q2;
        } else {
            StrBuilder strBuilder = new StrBuilder();
            while (q2.endsWith(XMPConst.ai)) {
                q2 = q2.substring(0, q2.length() - 2);
                strBuilder.c(Operators.ARRAY_START_STR);
            }
            String str2 = (String) p.get(q2);
            if (str2 != null) {
                strBuilder.c(str2);
            } else {
                strBuilder.c("L").c(q2).c(i.b);
            }
            return strBuilder.toString();
        }
    }

    public static Class[] a(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        if (objArr.length == 0) {
            return ArrayUtils.b;
        }
        Class[] clsArr = new Class[objArr.length];
        for (int i2 = 0; i2 < objArr.length; i2++) {
            clsArr[i2] = objArr[i2] == null ? null : objArr[i2].getClass();
        }
        return clsArr;
    }

    public static String c(Object obj, String str) {
        return obj == null ? str : d(obj.getClass().getName());
    }

    public static String h(Class cls) {
        return cls == null ? "" : d(cls.getName());
    }

    public static String d(String str) {
        return a(h(str));
    }

    public static String d(Object obj, String str) {
        return obj == null ? str : e(obj.getClass().getName());
    }

    public static String i(Class cls) {
        return cls == null ? "" : e(cls.getName());
    }

    public static String e(String str) {
        return b(h(str));
    }

    private static String h(String str) {
        String q2 = StringUtils.q(str);
        if (q2 == null) {
            return null;
        }
        int i2 = 0;
        while (q2.startsWith(Operators.ARRAY_START_STR)) {
            i2++;
            q2 = q2.substring(1);
        }
        if (i2 < 1) {
            return q2;
        }
        if (q2.startsWith("L")) {
            q2 = q2.substring(1, q2.endsWith(i.b) ? q2.length() - 1 : q2.length());
        } else if (q2.length() > 0) {
            q2 = (String) q.get(q2.substring(0, 1));
        }
        StrBuilder strBuilder = new StrBuilder(q2);
        for (int i3 = 0; i3 < i2; i3++) {
            strBuilder.c(XMPConst.ai);
        }
        return strBuilder.toString();
    }
}
