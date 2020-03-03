package com.xiaomi.mimc.protobuf;

import com.xiaomi.mimc.protobuf.GeneratedMessageLite;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExtensionRegistryLite {

    /* renamed from: a  reason: collision with root package name */
    static final String f11305a = "com.xiaomi.mimc.Extension";
    static final ExtensionRegistryLite b = new ExtensionRegistryLite(true);
    private static volatile boolean c = false;
    private static final Class<?> d = a();
    private final Map<ObjectIntPair, GeneratedMessageLite.GeneratedExtension<?, ?>> e;

    static Class<?> a() {
        try {
            return Class.forName(f11305a);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static boolean b() {
        return c;
    }

    public static void a(boolean z) {
        c = z;
    }

    public static ExtensionRegistryLite c() {
        return ExtensionRegistryFactory.b();
    }

    public static ExtensionRegistryLite d() {
        return ExtensionRegistryFactory.c();
    }

    public ExtensionRegistryLite e() {
        return new ExtensionRegistryLite(this);
    }

    public <ContainingType extends MessageLite> GeneratedMessageLite.GeneratedExtension<ContainingType, ?> a(ContainingType containingtype, int i) {
        return this.e.get(new ObjectIntPair(containingtype, i));
    }

    public final void a(GeneratedMessageLite.GeneratedExtension<?, ?> generatedExtension) {
        this.e.put(new ObjectIntPair(generatedExtension.g(), generatedExtension.a()), generatedExtension);
    }

    public final void a(ExtensionLite<?, ?> extensionLite) {
        if (GeneratedMessageLite.GeneratedExtension.class.isAssignableFrom(extensionLite.getClass())) {
            a((GeneratedMessageLite.GeneratedExtension<?, ?>) (GeneratedMessageLite.GeneratedExtension) extensionLite);
        }
        if (ExtensionRegistryFactory.a(this)) {
            try {
                getClass().getMethod("add", new Class[]{d}).invoke(this, new Object[]{extensionLite});
            } catch (Exception e2) {
                throw new IllegalArgumentException(String.format("Could not invoke ExtensionRegistry#add for %s", new Object[]{extensionLite}), e2);
            }
        }
    }

    ExtensionRegistryLite() {
        this.e = new HashMap();
    }

    ExtensionRegistryLite(ExtensionRegistryLite extensionRegistryLite) {
        if (extensionRegistryLite == b) {
            this.e = Collections.emptyMap();
        } else {
            this.e = Collections.unmodifiableMap(extensionRegistryLite.e);
        }
    }

    ExtensionRegistryLite(boolean z) {
        this.e = Collections.emptyMap();
    }

    private static final class ObjectIntPair {

        /* renamed from: a  reason: collision with root package name */
        private final Object f11306a;
        private final int b;

        ObjectIntPair(Object obj, int i) {
            this.f11306a = obj;
            this.b = i;
        }

        public int hashCode() {
            return (System.identityHashCode(this.f11306a) * 65535) + this.b;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ObjectIntPair)) {
                return false;
            }
            ObjectIntPair objectIntPair = (ObjectIntPair) obj;
            if (this.f11306a == objectIntPair.f11306a && this.b == objectIntPair.b) {
                return true;
            }
            return false;
        }
    }
}
