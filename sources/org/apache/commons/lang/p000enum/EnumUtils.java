package org.apache.commons.lang.p000enum;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: org.apache.commons.lang.enum.EnumUtils  reason: invalid package */
public class EnumUtils {
    public static Enum a(Class cls, String str) {
        return Enum.getEnum(cls, str);
    }

    public static ValuedEnum a(Class cls, int i) {
        return (ValuedEnum) ValuedEnum.getEnum(cls, i);
    }

    public static Map a(Class cls) {
        return Enum.getEnumMap(cls);
    }

    public static List b(Class cls) {
        return Enum.getEnumList(cls);
    }

    public static Iterator c(Class cls) {
        return Enum.getEnumList(cls).iterator();
    }
}
