package org.apache.commons.lang;

import com.taobao.weex.ui.component.list.template.TemplateDom;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.lang.exception.CloneFailedException;
import org.apache.commons.lang.reflect.MethodUtils;

public class ObjectUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final Null f3370a = new Null();

    public static Object a(Object obj, Object obj2) {
        return obj != null ? obj : obj2;
    }

    public static boolean b(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    public static boolean c(Object obj, Object obj2) {
        return !b(obj, obj2);
    }

    public static int a(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public static String b(Object obj) {
        if (obj == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        a(stringBuffer, obj);
        return stringBuffer.toString();
    }

    public static void a(StringBuffer stringBuffer, Object obj) {
        if (obj != null) {
            stringBuffer.append(obj.getClass().getName());
            stringBuffer.append(TemplateDom.SEPARATOR);
            stringBuffer.append(Integer.toHexString(System.identityHashCode(obj)));
            return;
        }
        throw new NullPointerException("Cannot get the toString of a null identity");
    }

    public static StringBuffer b(StringBuffer stringBuffer, Object obj) {
        if (obj == null) {
            return null;
        }
        if (stringBuffer == null) {
            stringBuffer = new StringBuffer();
        }
        stringBuffer.append(obj.getClass().getName());
        stringBuffer.append(TemplateDom.SEPARATOR);
        stringBuffer.append(Integer.toHexString(System.identityHashCode(obj)));
        return stringBuffer;
    }

    public static String c(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static String a(Object obj, String str) {
        return obj == null ? str : obj.toString();
    }

    public static Object a(Comparable comparable, Comparable comparable2) {
        return a(comparable, comparable2, true) <= 0 ? comparable : comparable2;
    }

    public static Object b(Comparable comparable, Comparable comparable2) {
        return a(comparable, comparable2, false) >= 0 ? comparable : comparable2;
    }

    public static int c(Comparable comparable, Comparable comparable2) {
        return a(comparable, comparable2, false);
    }

    public static int a(Comparable comparable, Comparable comparable2, boolean z) {
        if (comparable == comparable2) {
            return 0;
        }
        if (comparable == null) {
            return z ? 1 : -1;
        }
        if (comparable2 == null) {
            return z ? -1 : 1;
        }
        return comparable.compareTo(comparable2);
    }

    public static Object d(Object obj) {
        Object obj2;
        if (!(obj instanceof Cloneable)) {
            return null;
        }
        if (obj.getClass().isArray()) {
            Class<?> componentType = obj.getClass().getComponentType();
            if (componentType.isPrimitive()) {
                int length = Array.getLength(obj);
                obj2 = Array.newInstance(componentType, length);
                while (true) {
                    int i = length - 1;
                    if (length <= 0) {
                        break;
                    }
                    Array.set(obj2, i, Array.get(obj, i));
                    length = i;
                }
            } else {
                return ((Object[]) obj).clone();
            }
        } else {
            try {
                obj2 = MethodUtils.a(obj, "clone", (Object[]) null);
            } catch (NoSuchMethodException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Cloneable type ");
                stringBuffer.append(obj.getClass().getName());
                stringBuffer.append(" has no clone method");
                throw new CloneFailedException(stringBuffer.toString(), e);
            } catch (IllegalAccessException e2) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Cannot clone Cloneable type ");
                stringBuffer2.append(obj.getClass().getName());
                throw new CloneFailedException(stringBuffer2.toString(), e2);
            } catch (InvocationTargetException e3) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Exception cloning Cloneable type ");
                stringBuffer3.append(obj.getClass().getName());
                throw new CloneFailedException(stringBuffer3.toString(), e3.getTargetException());
            }
        }
        return obj2;
    }

    public static Object e(Object obj) {
        Object d = d(obj);
        return d == null ? obj : d;
    }

    public static class Null implements Serializable {
        private static final long serialVersionUID = 7092611880189329093L;

        Null() {
        }

        private Object readResolve() {
            return ObjectUtils.f3370a;
        }
    }
}
