package cn.jiajixin.nuwa.util;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Array;

public class DexUtils {
    public static void a(String str, String str2) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        DexClassLoader dexClassLoader = new DexClassLoader(str, str2, str, a());
        Object a2 = a(a(b(dexClassLoader)), a(b(a())));
        Object b = b(a());
        ReflectionUtils.a(b, b.getClass(), "dexElements", a2);
    }

    private static PathClassLoader a() {
        return (PathClassLoader) DexUtils.class.getClassLoader();
    }

    private static Object a(Object obj) throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
        return ReflectionUtils.a(obj, obj.getClass(), "dexElements");
    }

    private static Object b(Object obj) throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        return ReflectionUtils.a(obj, Class.forName("dalvik.system.BaseDexClassLoader"), "pathList");
    }

    private static Object a(Object obj, Object obj2) {
        Class<?> componentType = obj.getClass().getComponentType();
        int length = Array.getLength(obj);
        int length2 = Array.getLength(obj2) + length;
        Object newInstance = Array.newInstance(componentType, length2);
        for (int i = 0; i < length2; i++) {
            if (i < length) {
                Array.set(newInstance, i, Array.get(obj, i));
            } else {
                Array.set(newInstance, i, Array.get(obj2, i - length));
            }
        }
        return newInstance;
    }
}
