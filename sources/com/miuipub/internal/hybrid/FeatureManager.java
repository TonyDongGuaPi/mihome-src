package com.miuipub.internal.hybrid;

import java.util.HashMap;
import java.util.Map;
import miuipub.hybrid.HybridFeature;

public class FeatureManager {

    /* renamed from: a  reason: collision with root package name */
    private Map<String, HybridFeature> f8255a = new HashMap();
    private Config b;
    private ClassLoader c;

    public FeatureManager(Config config, ClassLoader classLoader) {
        this.b = config;
        this.c = classLoader;
    }

    private HybridFeature b(String str) throws HybridException {
        try {
            return (HybridFeature) this.c.loadClass(str).newInstance();
        } catch (ClassNotFoundException unused) {
            throw new HybridException(204, "feature not found: " + str);
        } catch (InstantiationException unused2) {
            throw new HybridException(204, "feature cannot be instantiated: " + str);
        } catch (IllegalAccessException unused3) {
            throw new HybridException(204, "feature cannot be accessed: " + str);
        }
    }

    public HybridFeature a(String str) throws HybridException {
        HybridFeature hybridFeature = this.f8255a.get(str);
        if (hybridFeature != null) {
            return hybridFeature;
        }
        Feature c2 = this.b.c(str);
        if (c2 != null) {
            HybridFeature b2 = b(str);
            b2.a(c2.b());
            this.f8255a.put(str, b2);
            return b2;
        }
        throw new HybridException(204, "feature not declared: " + str);
    }
}
