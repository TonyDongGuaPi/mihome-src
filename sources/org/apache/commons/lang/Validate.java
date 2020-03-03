package org.apache.commons.lang;

import java.util.Collection;
import java.util.Map;

public class Validate {
    public static void a(boolean z, String str, Object obj) {
        if (!z) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append(obj);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    public static void a(boolean z, String str, long j) {
        if (!z) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append(j);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    public static void a(boolean z, String str, double d) {
        if (!z) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append(d);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    public static void a(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void a(boolean z) {
        if (!z) {
            throw new IllegalArgumentException("The validated expression is false");
        }
    }

    public static void a(Object obj) {
        a(obj, "The validated object is null");
    }

    public static void a(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void a(Object[] objArr, String str) {
        if (objArr == null || objArr.length == 0) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void a(Object[] objArr) {
        a(objArr, "The validated array is empty");
    }

    public static void a(Collection collection, String str) {
        if (collection == null || collection.size() == 0) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void a(Collection collection) {
        a(collection, "The validated collection is empty");
    }

    public static void a(Map map, String str) {
        if (map == null || map.size() == 0) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void a(Map map) {
        a(map, "The validated map is empty");
    }

    public static void a(String str, String str2) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException(str2);
        }
    }

    public static void a(String str) {
        a(str, "The validated string is empty");
    }

    public static void b(Object[] objArr, String str) {
        a((Object) objArr);
        int i = 0;
        while (i < objArr.length) {
            if (objArr[i] != null) {
                i++;
            } else {
                throw new IllegalArgumentException(str);
            }
        }
    }

    public static void b(Object[] objArr) {
        a((Object) objArr);
        int i = 0;
        while (i < objArr.length) {
            if (objArr[i] != null) {
                i++;
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("The validated array contains null element at index: ");
                stringBuffer.append(i);
                throw new IllegalArgumentException(stringBuffer.toString());
            }
        }
    }

    public static void b(Collection collection, String str) {
        a((Object) collection);
        for (Object obj : collection) {
            if (obj == null) {
                throw new IllegalArgumentException(str);
            }
        }
    }

    public static void b(Collection collection) {
        a((Object) collection);
        int i = 0;
        for (Object obj : collection) {
            if (obj != null) {
                i++;
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("The validated collection contains null element at index: ");
                stringBuffer.append(i);
                throw new IllegalArgumentException(stringBuffer.toString());
            }
        }
    }

    public static void a(Collection collection, Class cls, String str) {
        a((Object) collection);
        a((Object) cls);
        for (Object isInstance : collection) {
            if (!cls.isInstance(isInstance)) {
                throw new IllegalArgumentException(str);
            }
        }
    }

    public static void a(Collection collection, Class cls) {
        a((Object) collection);
        a((Object) cls);
        int i = 0;
        for (Object isInstance : collection) {
            if (cls.isInstance(isInstance)) {
                i++;
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("The validated collection contains an element not of type ");
                stringBuffer.append(cls.getName());
                stringBuffer.append(" at index: ");
                stringBuffer.append(i);
                throw new IllegalArgumentException(stringBuffer.toString());
            }
        }
    }
}
