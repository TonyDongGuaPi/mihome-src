package com.xiaomi.mimc.protobuf;

final class ExtensionRegistryFactory {

    /* renamed from: a  reason: collision with root package name */
    static final String f11304a = "com.xiaomi.mimc.ExtensionRegistry";
    static final Class<?> b = a();

    ExtensionRegistryFactory() {
    }

    static Class<?> a() {
        try {
            return Class.forName(f11304a);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static ExtensionRegistryLite b() {
        if (b != null) {
            try {
                return a("newInstance");
            } catch (Exception unused) {
            }
        }
        return new ExtensionRegistryLite();
    }

    public static ExtensionRegistryLite c() {
        if (b != null) {
            try {
                return a("getEmptyRegistry");
            } catch (Exception unused) {
            }
        }
        return ExtensionRegistryLite.b;
    }

    static boolean a(ExtensionRegistryLite extensionRegistryLite) {
        return b != null && b.isAssignableFrom(extensionRegistryLite.getClass());
    }

    private static final ExtensionRegistryLite a(String str) throws Exception {
        return (ExtensionRegistryLite) b.getMethod(str, new Class[0]).invoke((Object) null, new Object[0]);
    }
}
