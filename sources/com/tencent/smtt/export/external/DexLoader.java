package com.tencent.smtt.export.external;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import dalvik.system.DexClassLoader;
import dalvik.system.VMStack;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class DexLoader {
    private static final String JAVACORE_PACKAGE_PREFIX = "org.chromium";
    private static final String TAG = "DexLoader";
    private static final String TBS_FUSION_DEX = "tbs_jars_fusion_dex";
    private static final String TBS_WEBVIEW_DEX = "webview_dex";
    static boolean mCanUseDexLoaderProviderService = true;
    private static boolean mUseSpeedyClassLoader = false;
    private static boolean mUseTbsCorePrivateClassLoader = false;
    private DexClassLoader mClassLoader;

    private static class TbsCorePrivateClassLoader extends DexClassLoader {
        public TbsCorePrivateClassLoader(String str, String str2, String str3, ClassLoader classLoader) {
            super(str, str2, str3, classLoader);
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
            r0 = getParent();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Class<?> loadClass(java.lang.String r2, boolean r3) {
            /*
                r1 = this;
                if (r2 == 0) goto L_0x0022
                java.lang.String r0 = "org.chromium"
                boolean r0 = r2.startsWith(r0)
                if (r0 == 0) goto L_0x0022
                java.lang.Class r3 = r1.findLoadedClass(r2)
                if (r3 != 0) goto L_0x0021
                java.lang.Class r0 = r1.findClass(r2)     // Catch:{ ClassNotFoundException -> 0x0015 }
                r3 = r0
            L_0x0015:
                if (r3 != 0) goto L_0x0021
                java.lang.ClassLoader r0 = r1.getParent()
                if (r0 == 0) goto L_0x0021
                java.lang.Class r3 = r0.loadClass(r2)
            L_0x0021:
                return r3
            L_0x0022:
                java.lang.Class r2 = super.loadClass(r2, r3)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.export.external.DexLoader.TbsCorePrivateClassLoader.loadClass(java.lang.String, boolean):java.lang.Class");
        }
    }

    public DexLoader(Context context, String str, String str2) {
        this(context, new String[]{str}, str2);
    }

    public DexLoader(Context context, String[] strArr, String str) {
        this((String) null, context, strArr, str);
    }

    public DexLoader(Context context, String[] strArr, String str, DexLoader dexLoader) {
        DexClassLoader classLoader = dexLoader.getClassLoader();
        for (String createDexClassLoader : strArr) {
            classLoader = createDexClassLoader(createDexClassLoader, str, context.getApplicationInfo().nativeLibraryDir, classLoader, context);
            this.mClassLoader = classLoader;
        }
    }

    public DexLoader(Context context, String[] strArr, String str, String str2) {
        ClassLoader classLoader = context.getClassLoader();
        String str3 = context.getApplicationInfo().nativeLibraryDir;
        if (!TextUtils.isEmpty(str2)) {
            str3 = str3 + File.pathSeparator + str2;
        }
        int i = 0;
        DexClassLoader dexClassLoader = classLoader;
        while (i < strArr.length) {
            DexClassLoader createDexClassLoader = createDexClassLoader(strArr[i], str, str3, dexClassLoader, context);
            this.mClassLoader = createDexClassLoader;
            i++;
            dexClassLoader = createDexClassLoader;
        }
    }

    public DexLoader(String str, Context context, String[] strArr, String str2) {
        this(str, context, strArr, str2, (Map<String, Object>) null);
    }

    public DexLoader(String str, Context context, String[] strArr, String str2, Map<String, Object> map) {
        initTbsSettings(map);
        ClassLoader callingClassLoader = VMStack.getCallingClassLoader();
        int i = 0;
        DexClassLoader classLoader = callingClassLoader == null ? context.getClassLoader() : callingClassLoader;
        while (i < strArr.length) {
            DexClassLoader createDexClassLoader = createDexClassLoader(strArr[i], str2, str, classLoader, context);
            this.mClassLoader = createDexClassLoader;
            i++;
            classLoader = createDexClassLoader;
        }
    }

    private DexClassLoader createDexClassLoader(String str, String str2, String str3, ClassLoader classLoader, Context context) {
        if (shouldUseTbsCorePrivateClassLoader(str)) {
            return new TbsCorePrivateClassLoader(str, str2, str3, classLoader);
        }
        if (Build.VERSION.SDK_INT < 21 || Build.VERSION.SDK_INT > 25 || !mUseSpeedyClassLoader) {
            return new DexClassLoader(str, str2, str3, classLoader);
        }
        try {
            return DexClassLoaderProvider.createDexClassLoader(str, str2, str3, classLoader, context);
        } catch (Throwable th) {
            Log.e("dexloader", "createDexClassLoader exception: " + th);
            return new DexClassLoader(str, str2, str3, classLoader);
        }
    }

    public static void initTbsSettings(Map<String, Object> map) {
        if (map != null) {
            try {
                Object obj = map.get(TbsCoreSettings.TBS_SETTINGS_USE_PRIVATE_CLASSLOADER);
                if (obj instanceof Boolean) {
                    mUseTbsCorePrivateClassLoader = ((Boolean) obj).booleanValue();
                }
                Object obj2 = map.get(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER);
                if (obj2 instanceof Boolean) {
                    mUseSpeedyClassLoader = ((Boolean) obj2).booleanValue();
                }
                Object obj3 = map.get(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE);
                if (obj3 instanceof Boolean) {
                    mCanUseDexLoaderProviderService = ((Boolean) obj3).booleanValue();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private boolean shouldUseTbsCorePrivateClassLoader(String str) {
        if (!mUseTbsCorePrivateClassLoader) {
            return false;
        }
        return str.contains(TBS_FUSION_DEX) || str.contains(TBS_WEBVIEW_DEX);
    }

    public DexClassLoader getClassLoader() {
        return this.mClassLoader;
    }

    public Object getStaticField(String str, String str2) {
        try {
            Field field = this.mClassLoader.loadClass(str).getField(str2);
            field.setAccessible(true);
            return field.get((Object) null);
        } catch (Throwable th) {
            String simpleName = getClass().getSimpleName();
            Log.e(simpleName, "'" + str + "' get field '" + str2 + "' failed", th);
            return null;
        }
    }

    public Object invokeMethod(Object obj, String str, String str2, Class<?>[] clsArr, Object... objArr) {
        try {
            Method method = this.mClassLoader.loadClass(str).getMethod(str2, clsArr);
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        } catch (Throwable th) {
            String simpleName = getClass().getSimpleName();
            Log.e(simpleName, "'" + str + "' invoke method '" + str2 + "' failed", th);
            return null;
        }
    }

    public Object invokeStaticMethod(String str, String str2, Class<?>[] clsArr, Object... objArr) {
        try {
            Method method = this.mClassLoader.loadClass(str).getMethod(str2, clsArr);
            method.setAccessible(true);
            return method.invoke((Object) null, objArr);
        } catch (Throwable th) {
            if (str2 == null || !str2.equalsIgnoreCase("initTesRuntimeEnvironment")) {
                String simpleName = getClass().getSimpleName();
                Log.e(simpleName, "'" + str + "' invoke static method '" + str2 + "' failed", th);
                return null;
            }
            String simpleName2 = getClass().getSimpleName();
            Log.e(simpleName2, "'" + str + "' invoke static method '" + str2 + "' failed", th);
            return th;
        }
    }

    public Class<?> loadClass(String str) {
        try {
            return this.mClassLoader.loadClass(str);
        } catch (Throwable th) {
            String simpleName = getClass().getSimpleName();
            Log.e(simpleName, "loadClass '" + str + "' failed", th);
            return null;
        }
    }

    public Object newInstance(String str) {
        try {
            return this.mClassLoader.loadClass(str).newInstance();
        } catch (Throwable th) {
            String simpleName = getClass().getSimpleName();
            Log.e(simpleName, "create " + str + " instance failed", th);
            return null;
        }
    }

    public Object newInstance(String str, Class<?>[] clsArr, Object... objArr) {
        try {
            return this.mClassLoader.loadClass(str).getConstructor(clsArr).newInstance(objArr);
        } catch (Throwable th) {
            if ("com.tencent.smtt.webkit.adapter.X5WebViewAdapter".equalsIgnoreCase(str)) {
                String simpleName = getClass().getSimpleName();
                Log.e(simpleName, "'newInstance " + str + " failed", th);
                return th;
            }
            String simpleName2 = getClass().getSimpleName();
            Log.e(simpleName2, "create '" + str + "' instance failed", th);
            return null;
        }
    }

    public void setStaticField(String str, String str2, Object obj) {
        try {
            Field field = this.mClassLoader.loadClass(str).getField(str2);
            field.setAccessible(true);
            field.set((Object) null, obj);
        } catch (Throwable th) {
            String simpleName = getClass().getSimpleName();
            Log.e(simpleName, "'" + str + "' set field '" + str2 + "' failed", th);
        }
    }
}
