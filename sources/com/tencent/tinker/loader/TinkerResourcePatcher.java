package com.tencent.tinker.loader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Log;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class TinkerResourcePatcher {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9233a = "Tinker.ResourcePatcher";
    private static final String b = "only_use_to_test_tinker_resource.txt";
    private static Collection<WeakReference<Resources>> c;
    private static Object d;
    private static AssetManager e;
    private static Method f;
    private static Method g;
    private static Field h;
    private static Field i;
    private static Field j;
    private static Field k;
    private static Field l;
    private static Field m;
    private static Field n;

    TinkerResourcePatcher() {
    }

    public static void a(Context context) throws Throwable {
        Class<?> cls;
        Class<?> cls2 = Class.forName("android.app.ActivityThread");
        d = ShareReflectUtil.a(context, cls2);
        try {
            cls = Class.forName("android.app.LoadedApk");
        } catch (ClassNotFoundException unused) {
            cls = Class.forName("android.app.ActivityThread$PackageInfo");
        }
        j = ShareReflectUtil.a(cls, "mResDir");
        k = ShareReflectUtil.a(cls2, "mPackages");
        if (Build.VERSION.SDK_INT < 27) {
            l = ShareReflectUtil.a(cls2, "mResourcePackages");
        }
        AssetManager assets = context.getAssets();
        f = ShareReflectUtil.a((Object) assets, "addAssetPath", (Class<?>[]) new Class[]{String.class});
        try {
            n = ShareReflectUtil.a((Object) assets, "mStringBlocks");
            g = ShareReflectUtil.a((Object) assets, "ensureStringBlocks", (Class<?>[]) new Class[0]);
        } catch (Throwable unused2) {
        }
        e = (AssetManager) ShareReflectUtil.a((Object) assets, (Class<?>[]) new Class[0]).newInstance(new Object[0]);
        if (Build.VERSION.SDK_INT >= 19) {
            Class<?> cls3 = Class.forName("android.app.ResourcesManager");
            Object invoke = ShareReflectUtil.a(cls3, "getInstance", (Class<?>[]) new Class[0]).invoke((Object) null, new Object[0]);
            try {
                c = ((ArrayMap) ShareReflectUtil.a(cls3, "mActiveResources").get(invoke)).values();
            } catch (NoSuchFieldException unused3) {
                c = (Collection) ShareReflectUtil.a(cls3, "mResourceReferences").get(invoke);
            }
        } else {
            c = ((HashMap) ShareReflectUtil.a(cls2, "mActiveResources").get(d)).values();
        }
        if (c != null) {
            Resources resources = context.getResources();
            if (Build.VERSION.SDK_INT >= 24) {
                try {
                    i = ShareReflectUtil.a((Object) resources, "mResourcesImpl");
                } catch (Throwable unused4) {
                    h = ShareReflectUtil.a((Object) resources, "mAssets");
                }
            } else {
                h = ShareReflectUtil.a((Object) resources, "mAssets");
            }
            try {
                m = ShareReflectUtil.a((Class<?>) ApplicationInfo.class, "publicSourceDir");
            } catch (NoSuchFieldException unused5) {
            }
        } else {
            throw new IllegalStateException("resource references is null");
        }
    }

    public static void a(Context context, String str) throws Throwable {
        if (str != null) {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            for (Field field : Build.VERSION.SDK_INT < 27 ? new Field[]{k, l} : new Field[]{k}) {
                for (Map.Entry value : ((Map) field.get(d)).entrySet()) {
                    Object obj = ((WeakReference) value.getValue()).get();
                    if (obj != null) {
                        if (applicationInfo.sourceDir.equals((String) j.get(obj))) {
                            j.set(obj, str);
                        }
                    }
                }
            }
            if (((Integer) f.invoke(e, new Object[]{str})).intValue() != 0) {
                if (!(n == null || g == null)) {
                    n.set(e, (Object) null);
                    g.invoke(e, new Object[0]);
                }
                for (WeakReference<Resources> weakReference : c) {
                    Resources resources = (Resources) weakReference.get();
                    if (resources != null) {
                        try {
                            h.set(resources, e);
                        } catch (Throwable unused) {
                            Object obj2 = i.get(resources);
                            ShareReflectUtil.a(obj2, "mAssets").set(obj2, e);
                        }
                        a(resources);
                        resources.updateConfiguration(resources.getConfiguration(), resources.getDisplayMetrics());
                    }
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    try {
                        if (m != null) {
                            m.set(context.getApplicationInfo(), str);
                        }
                    } catch (Throwable unused2) {
                    }
                }
                if (!b(context)) {
                    throw new TinkerRuntimeException(ShareConstants.F);
                }
                return;
            }
            throw new IllegalStateException("Could not create new AssetManager");
        }
    }

    private static void a(Resources resources) {
        Log.w(f9233a, "try to clear typedArray cache!");
        try {
            Object obj = ShareReflectUtil.a((Class<?>) Resources.class, "mTypedArrayPool").get(resources);
            do {
            } while (ShareReflectUtil.a(obj, "acquire", (Class<?>[]) new Class[0]).invoke(obj, new Object[0]) != null);
        } catch (Throwable th) {
            Log.e(f9233a, "clearPreloadTypedArrayIssue failed, ignore error: " + th);
        }
    }

    private static boolean b(Context context) {
        try {
            SharePatchFileUtil.a((Object) context.getAssets().open(b));
            Log.i(f9233a, "checkResUpdate success, found test resource assets file only_use_to_test_tinker_resource.txt");
            return true;
        } catch (Throwable th) {
            SharePatchFileUtil.a((Object) null);
            throw th;
        }
    }
}
