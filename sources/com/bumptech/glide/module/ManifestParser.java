package com.bumptech.glide.module;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public final class ManifestParser {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5047a = "ManifestParser";
    private static final String b = "GlideModule";
    private final Context c;

    public ManifestParser(Context context) {
        this.c = context;
    }

    public List<GlideModule> a() {
        if (Log.isLoggable(f5047a, 3)) {
            Log.d(f5047a, "Loading Glide modules");
        }
        ArrayList arrayList = new ArrayList();
        try {
            ApplicationInfo applicationInfo = this.c.getPackageManager().getApplicationInfo(this.c.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                if (Log.isLoggable(f5047a, 3)) {
                    Log.d(f5047a, "Got null app info metadata");
                }
                return arrayList;
            }
            if (Log.isLoggable(f5047a, 2)) {
                Log.v(f5047a, "Got app info metadata: " + applicationInfo.metaData);
            }
            for (String str : applicationInfo.metaData.keySet()) {
                if (b.equals(applicationInfo.metaData.get(str))) {
                    arrayList.add(a(str));
                    if (Log.isLoggable(f5047a, 3)) {
                        Log.d(f5047a, "Loaded Glide module: " + str);
                    }
                }
            }
            if (Log.isLoggable(f5047a, 3)) {
                Log.d(f5047a, "Finished loading Glide modules");
            }
            return arrayList;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Unable to find metadata to parse GlideModules", e);
        }
    }

    private static GlideModule a(String str) {
        try {
            Class<?> cls = Class.forName(str);
            GlideModule glideModule = null;
            try {
                glideModule = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (InstantiationException e) {
                a(cls, e);
            } catch (IllegalAccessException e2) {
                a(cls, e2);
            } catch (NoSuchMethodException e3) {
                a(cls, e3);
            } catch (InvocationTargetException e4) {
                a(cls, e4);
            }
            if (glideModule instanceof GlideModule) {
                return glideModule;
            }
            throw new RuntimeException("Expected instanceof GlideModule, but found: " + glideModule);
        } catch (ClassNotFoundException e5) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e5);
        }
    }

    private static void a(Class<?> cls, Exception exc) {
        throw new RuntimeException("Unable to instantiate GlideModule implementation for " + cls, exc);
    }
}
