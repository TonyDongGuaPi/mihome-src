package kotlin.jvm.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Function;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableCollection;
import kotlin.jvm.internal.markers.KMutableIterable;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableListIterator;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.jvm.internal.markers.KMutableSet;

public class TypeIntrinsics {
    private static <T extends Throwable> T a(T t) {
        return Intrinsics.a(t, TypeIntrinsics.class.getName());
    }

    public static void a(Object obj, String str) {
        String name = obj == null ? "null" : obj.getClass().getName();
        a(name + " cannot be cast to " + str);
    }

    public static void a(String str) {
        throw a(new ClassCastException(str));
    }

    public static ClassCastException a(ClassCastException classCastException) {
        throw ((ClassCastException) a(classCastException));
    }

    public static boolean a(Object obj) {
        return (obj instanceof Iterator) && (!(obj instanceof KMappedMarker) || (obj instanceof KMutableIterator));
    }

    public static Iterator b(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableIterator)) {
            a(obj, "kotlin.collections.MutableIterator");
        }
        return c(obj);
    }

    public static Iterator b(Object obj, String str) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableIterator)) {
            a(str);
        }
        return c(obj);
    }

    public static Iterator c(Object obj) {
        try {
            return (Iterator) obj;
        } catch (ClassCastException e) {
            throw a(e);
        }
    }

    public static boolean d(Object obj) {
        return (obj instanceof ListIterator) && (!(obj instanceof KMappedMarker) || (obj instanceof KMutableListIterator));
    }

    public static ListIterator e(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableListIterator)) {
            a(obj, "kotlin.collections.MutableListIterator");
        }
        return f(obj);
    }

    public static ListIterator c(Object obj, String str) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableListIterator)) {
            a(str);
        }
        return f(obj);
    }

    public static ListIterator f(Object obj) {
        try {
            return (ListIterator) obj;
        } catch (ClassCastException e) {
            throw a(e);
        }
    }

    public static boolean g(Object obj) {
        return (obj instanceof Iterable) && (!(obj instanceof KMappedMarker) || (obj instanceof KMutableIterable));
    }

    public static Iterable h(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableIterable)) {
            a(obj, "kotlin.collections.MutableIterable");
        }
        return i(obj);
    }

    public static Iterable d(Object obj, String str) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableIterable)) {
            a(str);
        }
        return i(obj);
    }

    public static Iterable i(Object obj) {
        try {
            return (Iterable) obj;
        } catch (ClassCastException e) {
            throw a(e);
        }
    }

    public static boolean j(Object obj) {
        return (obj instanceof Collection) && (!(obj instanceof KMappedMarker) || (obj instanceof KMutableCollection));
    }

    public static Collection k(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableCollection)) {
            a(obj, "kotlin.collections.MutableCollection");
        }
        return l(obj);
    }

    public static Collection e(Object obj, String str) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableCollection)) {
            a(str);
        }
        return l(obj);
    }

    public static Collection l(Object obj) {
        try {
            return (Collection) obj;
        } catch (ClassCastException e) {
            throw a(e);
        }
    }

    public static boolean m(Object obj) {
        return (obj instanceof List) && (!(obj instanceof KMappedMarker) || (obj instanceof KMutableList));
    }

    public static List n(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableList)) {
            a(obj, "kotlin.collections.MutableList");
        }
        return o(obj);
    }

    public static List f(Object obj, String str) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableList)) {
            a(str);
        }
        return o(obj);
    }

    public static List o(Object obj) {
        try {
            return (List) obj;
        } catch (ClassCastException e) {
            throw a(e);
        }
    }

    public static boolean p(Object obj) {
        return (obj instanceof Set) && (!(obj instanceof KMappedMarker) || (obj instanceof KMutableSet));
    }

    public static Set q(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableSet)) {
            a(obj, "kotlin.collections.MutableSet");
        }
        return r(obj);
    }

    public static Set g(Object obj, String str) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableSet)) {
            a(str);
        }
        return r(obj);
    }

    public static Set r(Object obj) {
        try {
            return (Set) obj;
        } catch (ClassCastException e) {
            throw a(e);
        }
    }

    public static boolean s(Object obj) {
        return (obj instanceof Map) && (!(obj instanceof KMappedMarker) || (obj instanceof KMutableMap));
    }

    public static Map t(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableMap)) {
            a(obj, "kotlin.collections.MutableMap");
        }
        return u(obj);
    }

    public static Map h(Object obj, String str) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableMap)) {
            a(str);
        }
        return u(obj);
    }

    public static Map u(Object obj) {
        try {
            return (Map) obj;
        } catch (ClassCastException e) {
            throw a(e);
        }
    }

    public static boolean v(Object obj) {
        return (obj instanceof Map.Entry) && (!(obj instanceof KMappedMarker) || (obj instanceof KMutableMap.Entry));
    }

    public static Map.Entry w(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableMap.Entry)) {
            a(obj, "kotlin.collections.MutableMap.MutableEntry");
        }
        return x(obj);
    }

    public static Map.Entry i(Object obj, String str) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableMap.Entry)) {
            a(str);
        }
        return x(obj);
    }

    public static Map.Entry x(Object obj) {
        try {
            return (Map.Entry) obj;
        } catch (ClassCastException e) {
            throw a(e);
        }
    }

    public static int y(Object obj) {
        if (obj instanceof FunctionBase) {
            return ((FunctionBase) obj).getArity();
        }
        if (obj instanceof Function0) {
            return 0;
        }
        if (obj instanceof Function1) {
            return 1;
        }
        if (obj instanceof Function2) {
            return 2;
        }
        if (obj instanceof Function3) {
            return 3;
        }
        if (obj instanceof Function4) {
            return 4;
        }
        if (obj instanceof Function5) {
            return 5;
        }
        if (obj instanceof Function6) {
            return 6;
        }
        if (obj instanceof Function7) {
            return 7;
        }
        if (obj instanceof Function8) {
            return 8;
        }
        if (obj instanceof Function9) {
            return 9;
        }
        if (obj instanceof Function10) {
            return 10;
        }
        if (obj instanceof Function11) {
            return 11;
        }
        if (obj instanceof Function12) {
            return 12;
        }
        if (obj instanceof Function13) {
            return 13;
        }
        if (obj instanceof Function14) {
            return 14;
        }
        if (obj instanceof Function15) {
            return 15;
        }
        if (obj instanceof Function16) {
            return 16;
        }
        if (obj instanceof Function17) {
            return 17;
        }
        if (obj instanceof Function18) {
            return 18;
        }
        if (obj instanceof Function19) {
            return 19;
        }
        if (obj instanceof Function20) {
            return 20;
        }
        if (obj instanceof Function21) {
            return 21;
        }
        return obj instanceof Function22 ? 22 : -1;
    }

    public static boolean a(Object obj, int i) {
        return (obj instanceof Function) && y(obj) == i;
    }

    public static Object b(Object obj, int i) {
        if (obj != null && !a(obj, i)) {
            a(obj, "kotlin.jvm.functions.Function" + i);
        }
        return obj;
    }

    public static Object a(Object obj, int i, String str) {
        if (obj != null && !a(obj, i)) {
            a(str);
        }
        return obj;
    }
}
