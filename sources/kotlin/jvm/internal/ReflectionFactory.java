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

public class ReflectionFactory {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2831a = "kotlin.jvm.functions.";

    public KFunction a(FunctionReference functionReference) {
        return functionReference;
    }

    public KMutableProperty0 a(MutablePropertyReference0 mutablePropertyReference0) {
        return mutablePropertyReference0;
    }

    public KMutableProperty1 a(MutablePropertyReference1 mutablePropertyReference1) {
        return mutablePropertyReference1;
    }

    public KMutableProperty2 a(MutablePropertyReference2 mutablePropertyReference2) {
        return mutablePropertyReference2;
    }

    public KProperty0 a(PropertyReference0 propertyReference0) {
        return propertyReference0;
    }

    public KProperty1 a(PropertyReference1 propertyReference1) {
        return propertyReference1;
    }

    public KProperty2 a(PropertyReference2 propertyReference2) {
        return propertyReference2;
    }

    public KClass a(Class cls) {
        return new ClassReference(cls);
    }

    public KClass a(Class cls, String str) {
        return new ClassReference(cls);
    }

    public KDeclarationContainer b(Class cls, String str) {
        return new PackageReference(cls, str);
    }

    public KClass b(Class cls) {
        return new ClassReference(cls);
    }

    public KClass c(Class cls, String str) {
        return new ClassReference(cls);
    }

    @SinceKotlin(version = "1.1")
    public String a(Lambda lambda) {
        return a((FunctionBase) lambda);
    }

    @SinceKotlin(version = "1.3")
    public String a(FunctionBase functionBase) {
        String obj = functionBase.getClass().getGenericInterfaces()[0].toString();
        return obj.startsWith(f2831a) ? obj.substring(f2831a.length()) : obj;
    }
}
