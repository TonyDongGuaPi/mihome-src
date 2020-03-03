package com.xiaomi.jr.http.encoding;

import com.taobao.weex.el.parse.Operators;
import java.util.Collection;

public final class Preconditions {
    private Preconditions() {
    }

    public static void a(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void a(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void a(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(a(str, objArr));
        }
    }

    public static void b(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void b(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static void b(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalStateException(a(str, objArr));
        }
    }

    public static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static <T> T a(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static <T> T a(T t, String str, Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(a(str, objArr));
    }

    public static <T extends Iterable<?>> T a(T t) {
        if (!b((Iterable<?>) t)) {
            return t;
        }
        throw new NullPointerException();
    }

    public static <T extends Iterable<?>> T a(T t, Object obj) {
        if (!b((Iterable<?>) t)) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static <T extends Iterable<?>> T a(T t, String str, Object... objArr) {
        if (!b((Iterable<?>) t)) {
            return t;
        }
        throw new NullPointerException(a(str, objArr));
    }

    private static boolean b(Iterable<?> iterable) {
        if (iterable == null) {
            return true;
        }
        if (iterable instanceof Collection) {
            try {
                return ((Collection) iterable).contains((Object) null);
            } catch (NullPointerException unused) {
                return false;
            }
        } else {
            for (Object obj : iterable) {
                if (obj == null) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void a(int i, int i2) {
        a(i, i2, "index");
    }

    public static void a(int i, int i2, String str) {
        a(i2 >= 0, "negative size: %s", Integer.valueOf(i2));
        if (i < 0) {
            throw new IndexOutOfBoundsException(a("%s (%s) must not be negative", str, Integer.valueOf(i)));
        } else if (i >= i2) {
            throw new IndexOutOfBoundsException(a("%s (%s) must be less than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    public static void b(int i, int i2) {
        b(i, i2, "index");
    }

    public static void b(int i, int i2, String str) {
        a(i2 >= 0, "negative size: %s", Integer.valueOf(i2));
        if (i < 0) {
            throw new IndexOutOfBoundsException(a("%s (%s) must not be negative", str, Integer.valueOf(i)));
        } else if (i > i2) {
            throw new IndexOutOfBoundsException(a("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    public static void a(int i, int i2, int i3) {
        b(i, i3, "start index");
        b(i2, i3, "end index");
        if (i2 < i) {
            throw new IndexOutOfBoundsException(a("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i)));
        }
    }

    static String a(String str, Object... objArr) {
        int indexOf;
        StringBuilder sb = new StringBuilder(str.length() + (objArr.length * 16));
        int i = 0;
        int i2 = 0;
        while (i < objArr.length && (indexOf = str.indexOf("%s", i2)) != -1) {
            sb.append(str.substring(i2, indexOf));
            sb.append(objArr[i]);
            int i3 = i + 1;
            i2 = indexOf + 2;
            i = i3;
        }
        sb.append(str.substring(i2));
        if (i < objArr.length) {
            sb.append(" [");
            sb.append(objArr[i]);
            for (int i4 = i + 1; i4 < objArr.length; i4++) {
                sb.append(", ");
                sb.append(objArr[i4]);
            }
            sb.append(Operators.ARRAY_END_STR);
        }
        return sb.toString();
    }
}
