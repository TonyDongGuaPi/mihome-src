package org.apache.commons.lang.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.lang.ArrayUtils;

public class ReflectionToStringBuilder extends ToStringBuilder {

    /* renamed from: a  reason: collision with root package name */
    private boolean f3379a = false;
    private boolean b = false;
    private String[] c;
    private Class d = null;

    public static String a(Object obj) {
        return a(obj, (ToStringStyle) null, false, false, (Class) null);
    }

    public static String a(Object obj, ToStringStyle toStringStyle) {
        return a(obj, toStringStyle, false, false, (Class) null);
    }

    public static String a(Object obj, ToStringStyle toStringStyle, boolean z) {
        return a(obj, toStringStyle, z, false, (Class) null);
    }

    public static String a(Object obj, ToStringStyle toStringStyle, boolean z, boolean z2) {
        return a(obj, toStringStyle, z, z2, (Class) null);
    }

    public static String a(Object obj, ToStringStyle toStringStyle, boolean z, boolean z2, Class cls) {
        return new ReflectionToStringBuilder(obj, toStringStyle, (StringBuffer) null, cls, z, z2).toString();
    }

    public static String a(Object obj, ToStringStyle toStringStyle, boolean z, Class cls) {
        return new ReflectionToStringBuilder(obj, toStringStyle, (StringBuffer) null, cls, z).toString();
    }

    public static String a(Object obj, String str) {
        return a(obj, new String[]{str});
    }

    public static String a(Object obj, Collection collection) {
        return a(obj, a(collection));
    }

    static String[] a(Collection collection) {
        if (collection == null) {
            return ArrayUtils.c;
        }
        return a(collection.toArray());
    }

    static String[] a(Object[] objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            if (obj != null) {
                arrayList.add(obj.toString());
            }
        }
        return (String[]) arrayList.toArray(ArrayUtils.c);
    }

    public static String a(Object obj, String[] strArr) {
        return new ReflectionToStringBuilder(obj).a(strArr).toString();
    }

    public ReflectionToStringBuilder(Object obj) {
        super(obj);
    }

    public ReflectionToStringBuilder(Object obj, ToStringStyle toStringStyle) {
        super(obj, toStringStyle);
    }

    public ReflectionToStringBuilder(Object obj, ToStringStyle toStringStyle, StringBuffer stringBuffer) {
        super(obj, toStringStyle, stringBuffer);
    }

    public ReflectionToStringBuilder(Object obj, ToStringStyle toStringStyle, StringBuffer stringBuffer, Class cls, boolean z) {
        super(obj, toStringStyle, stringBuffer);
        b(cls);
        b(z);
    }

    public ReflectionToStringBuilder(Object obj, ToStringStyle toStringStyle, StringBuffer stringBuffer, Class cls, boolean z, boolean z2) {
        super(obj, toStringStyle, stringBuffer);
        b(cls);
        b(z);
        a(z2);
    }

    /* access modifiers changed from: protected */
    public boolean a(Field field) {
        if (field.getName().indexOf(36) != -1) {
            return false;
        }
        if (Modifier.isTransient(field.getModifiers()) && !d()) {
            return false;
        }
        if (Modifier.isStatic(field.getModifiers()) && !c()) {
            return false;
        }
        if (a() == null || Arrays.binarySearch(a(), field.getName()) < 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void a(Class cls) {
        if (cls.isArray()) {
            b(f());
            return;
        }
        Field[] declaredFields = cls.getDeclaredFields();
        AccessibleObject.setAccessible(declaredFields, true);
        for (Field field : declaredFields) {
            String name = field.getName();
            if (a(field)) {
                try {
                    a(name, b(field));
                } catch (IllegalAccessException e) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unexpected IllegalAccessException: ");
                    stringBuffer.append(e.getMessage());
                    throw new InternalError(stringBuffer.toString());
                }
            }
        }
    }

    public String[] a() {
        return this.c;
    }

    public Class b() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public Object b(Field field) throws IllegalArgumentException, IllegalAccessException {
        return field.get(f());
    }

    public boolean c() {
        return this.f3379a;
    }

    public boolean d() {
        return this.b;
    }

    public ToStringBuilder b(Object obj) {
        h().reflectionAppendArrayDetail(g(), (String) null, obj);
        return this;
    }

    public void a(boolean z) {
        this.f3379a = z;
    }

    public void b(boolean z) {
        this.b = z;
    }

    public ReflectionToStringBuilder a(String[] strArr) {
        if (strArr == null) {
            this.c = null;
        } else {
            this.c = a((Object[]) strArr);
            Arrays.sort(this.c);
        }
        return this;
    }

    public void b(Class cls) {
        Object f;
        if (cls == null || (f = f()) == null || cls.isInstance(f)) {
            this.d = cls;
            return;
        }
        throw new IllegalArgumentException("Specified class is not a superclass of the object");
    }

    public String toString() {
        if (f() == null) {
            return h().getNullText();
        }
        Class cls = f().getClass();
        a(cls);
        while (cls.getSuperclass() != null && cls != b()) {
            cls = cls.getSuperclass();
            a(cls);
        }
        return super.toString();
    }
}
