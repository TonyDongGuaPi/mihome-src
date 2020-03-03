package org.xutils.common.util;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class ParameterizedTypeUtil {
    private ParameterizedTypeUtil() {
    }

    public static Type a(Type type, Class<?> cls, int i) {
        TypeVariable[] typeVariableArr;
        Class<?> cls2;
        Type[] typeArr = null;
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class<?> cls3 = (Class) parameterizedType.getRawType();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            typeVariableArr = cls3.getTypeParameters();
            Class<?> cls4 = cls3;
            typeArr = actualTypeArguments;
            cls2 = cls4;
        } else {
            cls2 = (Class) type;
            typeVariableArr = null;
        }
        if (cls != cls2) {
            Type[] genericInterfaces = cls2.getGenericInterfaces();
            if (genericInterfaces != null) {
                for (Type type2 : genericInterfaces) {
                    if ((type2 instanceof ParameterizedType) && cls.isAssignableFrom((Class) ((ParameterizedType) type2).getRawType())) {
                        try {
                            return a(a(type2, cls, i), (TypeVariable<?>[]) typeVariableArr, typeArr);
                        } catch (Throwable unused) {
                        }
                    }
                }
            }
            Class<? super Object> superclass = cls2.getSuperclass();
            if (superclass != null && cls.isAssignableFrom(superclass)) {
                return a(a(cls2.getGenericSuperclass(), cls, i), (TypeVariable<?>[]) typeVariableArr, typeArr);
            }
            throw new IllegalArgumentException("FindGenericType:" + type + ", declaredClass: " + cls + ", index: " + i);
        } else if (typeArr != null) {
            return typeArr[i];
        } else {
            return Object.class;
        }
    }

    private static Type a(Type type, TypeVariable<?>[] typeVariableArr, Type[] typeArr) {
        if (type instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type;
            String name = typeVariable.getName();
            if (typeArr != null) {
                for (int i = 0; i < typeVariableArr.length; i++) {
                    if (name.equals(typeVariableArr[i].getName())) {
                        return typeArr[i];
                    }
                }
            }
            return typeVariable;
        }
        if (type instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            if (genericComponentType instanceof Class) {
                return Array.newInstance((Class) genericComponentType, 0).getClass();
            }
        }
        return type;
    }
}
