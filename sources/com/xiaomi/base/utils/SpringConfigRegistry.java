package com.xiaomi.base.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SpringConfigRegistry {

    /* renamed from: a  reason: collision with root package name */
    private static final SpringConfigRegistry f10044a = new SpringConfigRegistry(true);
    private final Map<SpringConfig, String> b = new HashMap();

    SpringConfigRegistry(boolean z) {
        if (z) {
            a(SpringConfig.f10043a, "default config");
        }
    }

    public static SpringConfigRegistry a() {
        return f10044a;
    }

    public boolean a(SpringConfig springConfig, String str) {
        if (springConfig == null) {
            throw new IllegalArgumentException("springConfig is required");
        } else if (str == null) {
            throw new IllegalArgumentException("configName is required");
        } else if (this.b.containsKey(springConfig)) {
            return false;
        } else {
            this.b.put(springConfig, str);
            return true;
        }
    }

    public Map<SpringConfig, String> b() {
        return Collections.unmodifiableMap(this.b);
    }

    public void c() {
        this.b.clear();
    }

    public boolean a(SpringConfig springConfig) {
        if (springConfig != null) {
            return this.b.remove(springConfig) != null;
        }
        throw new IllegalArgumentException("springConfig is required");
    }
}
