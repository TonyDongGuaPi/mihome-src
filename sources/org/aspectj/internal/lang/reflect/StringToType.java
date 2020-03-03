package org.aspectj.internal.lang.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.StringTokenizer;
import org.aspectj.lang.reflect.AjTypeSystem;

public class StringToType {
    public static Type[] a(String str, Class cls) throws ClassNotFoundException {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        Type[] typeArr = new Type[stringTokenizer.countTokens()];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            typeArr[i] = b(stringTokenizer.nextToken().trim(), cls);
            i++;
        }
        return typeArr;
    }

    public static Type b(String str, Class cls) throws ClassNotFoundException {
        try {
            if (str.indexOf("<") == -1) {
                return AjTypeSystem.a(Class.forName(str, false, cls.getClassLoader()));
            }
            return c(str, cls);
        } catch (ClassNotFoundException unused) {
            TypeVariable[] typeParameters = cls.getTypeParameters();
            for (int i = 0; i < typeParameters.length; i++) {
                if (typeParameters[i].getName().equals(str)) {
                    return typeParameters[i];
                }
            }
            throw new ClassNotFoundException(str);
        }
    }

    private static Type c(String str, Class cls) throws ClassNotFoundException {
        int indexOf = str.indexOf(60);
        final Class<?> cls2 = Class.forName(str.substring(0, indexOf), false, cls.getClassLoader());
        final Type[] a2 = a(str.substring(indexOf + 1, str.lastIndexOf(62)), cls);
        return new ParameterizedType() {
            public Type[] getActualTypeArguments() {
                return a2;
            }

            public Type getRawType() {
                return cls2;
            }

            public Type getOwnerType() {
                return cls2.getEnclosingClass();
            }
        };
    }
}
