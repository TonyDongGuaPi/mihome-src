package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;

public class Reflection {

    /* renamed from: a  reason: collision with root package name */
    static final String f2830a = " (Kotlin reflection is not available)";
    private static final ReflectionFactory b;
    private static final KClass[] c = new KClass[0];

    static {
        ReflectionFactory reflectionFactory = null;
        try {
            reflectionFactory = (ReflectionFactory) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (reflectionFactory == null) {
            reflectionFactory = new ReflectionFactory();
        }
        b = reflectionFactory;
    }

    public static KClass a(Class cls) {
        return b.a(cls);
    }

    public static KClass a(Class cls, String str) {
        return b.a(cls, str);
    }

    public static KDeclarationContainer b(Class cls, String str) {
        return b.b(cls, str);
    }

    public static KClass b(Class cls) {
        return b.b(cls);
    }

    public static KClass c(Class cls, String str) {
        return b.c(cls, str);
    }

    public static KClass[] a(Class[] clsArr) {
        int length = clsArr.length;
        if (length == 0) {
            return c;
        }
        KClass[] kClassArr = new KClass[length];
        for (int i = 0; i < length; i++) {
            kClassArr[i] = b(clsArr[i]);
        }
        return kClassArr;
    }

    @SinceKotlin(version = "1.1")
    public static String a(Lambda lambda) {
        return b.a(lambda);
    }

    @SinceKotlin(version = "1.3")
    public static String a(FunctionBase functionBase) {
        return b.a(functionBase);
    }

    public static KFunction a(FunctionReference functionReference) {
        return b.a(functionReference);
    }

    public static KProperty0 a(PropertyReference0 propertyReference0) {
        return b.a(propertyReference0);
    }

    public static KMutableProperty0 a(MutablePropertyReference0 mutablePropertyReference0) {
        return b.a(mutablePropertyReference0);
    }

    public static KProperty1 a(PropertyReference1 propertyReference1) {
        return b.a(propertyReference1);
    }

    public static KMutableProperty1 a(MutablePropertyReference1 mutablePropertyReference1) {
        return b.a(mutablePropertyReference1);
    }

    public static KProperty2 a(PropertyReference2 propertyReference2) {
        return b.a(propertyReference2);
    }

    public static KMutableProperty2 a(MutablePropertyReference2 mutablePropertyReference2) {
        return b.a(mutablePropertyReference2);
    }
}
