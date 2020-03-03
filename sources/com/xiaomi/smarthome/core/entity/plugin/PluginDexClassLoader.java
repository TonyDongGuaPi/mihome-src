package com.xiaomi.smarthome.core.entity.plugin;

import dalvik.system.DexClassLoader;

public class PluginDexClassLoader extends DexClassLoader {

    /* renamed from: a  reason: collision with root package name */
    private ClassLoader f13988a;
    private ClassLoader b;

    public PluginDexClassLoader(String str, String str2, String str3, ClassLoader classLoader, ClassLoader classLoader2) {
        super(str, str2, str3, classLoader);
        this.b = classLoader;
        this.f13988a = classLoader2;
    }

    public Class<?> loadClass(String str) throws ClassNotFoundException {
        Class<?> findLoadedClass = findLoadedClass(str);
        if (findLoadedClass != null) {
            return findLoadedClass;
        }
        try {
            findLoadedClass = super.loadClass(str, false);
        } catch (ClassNotFoundException unused) {
        }
        if (findLoadedClass != null) {
            return findLoadedClass;
        }
        try {
            return this.f13988a.loadClass(str);
        } catch (ClassNotFoundException e) {
            throw e;
        }
    }

    public String findLibrary(String str) {
        return super.findLibrary(str);
    }
}
