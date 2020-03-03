package com.xiaomi.jr.hybrid;

import java.util.HashMap;
import java.util.Map;

public class FeatureManager {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, String> f1439a = new HashMap();
    private Map<String, HybridFeature> b = new HashMap();

    public static void a(Map<String, String> map) {
        f1439a = map;
    }

    public static void a(String str, Class<?> cls) {
        f1439a.put(str, cls.getName());
    }

    public Map<String, HybridFeature> a() {
        return this.b;
    }

    public HybridFeature a(String str) throws HybridException {
        HybridFeature hybridFeature = this.b.get(str);
        if (hybridFeature != null) {
            return hybridFeature;
        }
        if (f1439a.containsKey(str)) {
            HybridFeature b2 = b(str);
            this.b.put(str, b2);
            return b2;
        }
        throw new HybridException(204, "feature not declared: " + str);
    }

    private HybridFeature b(String str) throws HybridException {
        try {
            return (HybridFeature) Class.forName(f1439a.get(str)).newInstance();
        } catch (ClassNotFoundException unused) {
            throw new HybridException(204, "feature not found: " + str);
        } catch (InstantiationException unused2) {
            throw new HybridException(204, "feature cannot be instantiated: " + str);
        } catch (IllegalAccessException unused3) {
            throw new HybridException(204, "feature cannot be accessed: " + str);
        } catch (Exception e) {
            throw new HybridException(204, "exception in feature creation: " + e.getMessage());
        }
    }
}
