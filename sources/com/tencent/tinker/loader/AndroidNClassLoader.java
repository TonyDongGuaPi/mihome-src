package com.tencent.tinker.loader;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

@TargetApi(14)
class AndroidNClassLoader extends PathClassLoader {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9221a = "Tinker.NClassLoader";
    private static Object b = null;
    private static String c = "";
    private final ClassLoader d;
    private String e = "";

    private AndroidNClassLoader(String str, ClassLoader classLoader, Application application) {
        super(str, classLoader.getParent());
        this.d = classLoader;
        String name = application.getClass().getName();
        if (name != null && !name.equals("android.app.Application")) {
            this.e = name;
        }
        c = application.getPackageName();
    }

    private AndroidNClassLoader(String str, ClassLoader classLoader) {
        super(str, classLoader);
        this.d = classLoader;
    }

    private static Object a(Object obj, ClassLoader classLoader, boolean z) throws Exception {
        Object obj2 = obj;
        Constructor<?> a2 = ShareReflectUtil.a(obj2, (Class<?>[]) new Class[]{ClassLoader.class, String.class, String.class, File.class});
        if (z) {
            return a2.newInstance(new Object[]{classLoader, "", null, null});
        }
        Object[] objArr = (Object[]) ShareReflectUtil.a(obj2, "dexElements").get(obj2);
        List<File> list = (List) ShareReflectUtil.a(obj2, "nativeLibraryDirectories").get(obj2);
        StringBuilder sb = new StringBuilder();
        Field a3 = ShareReflectUtil.a(objArr.getClass().getComponentType(), "dexFile");
        boolean z2 = true;
        for (Object obj3 : objArr) {
            DexFile dexFile = (DexFile) a3.get(obj3);
            if (!(dexFile == null || dexFile.getName() == null)) {
                if (dexFile.getName().contains("/" + c)) {
                    if (z2) {
                        z2 = false;
                    } else {
                        sb.append(File.pathSeparator);
                    }
                    sb.append(dexFile.getName());
                }
            }
        }
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        boolean z3 = true;
        for (File file : list) {
            if (file != null) {
                if (z3) {
                    z3 = false;
                } else {
                    sb3.append(File.pathSeparator);
                }
                sb3.append(file.getAbsolutePath());
            }
        }
        return a2.newInstance(new Object[]{classLoader, sb2, sb3.toString(), null});
    }

    private static AndroidNClassLoader b(BaseDexClassLoader baseDexClassLoader, Application application) throws Exception {
        AndroidNClassLoader androidNClassLoader = new AndroidNClassLoader("", baseDexClassLoader, application);
        Field a2 = ShareReflectUtil.a((Object) baseDexClassLoader, "pathList");
        Object obj = a2.get(baseDexClassLoader);
        a2.set(androidNClassLoader, a(obj, androidNClassLoader, false));
        ShareReflectUtil.a(obj, "definingContext").set(obj, androidNClassLoader);
        b = obj;
        return androidNClassLoader;
    }

    private static void a(Application application, ClassLoader classLoader) throws Exception {
        Context context = (Context) ShareReflectUtil.a((Object) application, "mBase").get(application);
        Object obj = ShareReflectUtil.a((Object) context, "mPackageInfo").get(context);
        ShareReflectUtil.a(obj, "mClassLoader").set(obj, classLoader);
        if (Build.VERSION.SDK_INT < 27) {
            Resources resources = application.getResources();
            ShareReflectUtil.a((Object) resources, "mClassLoader").set(resources, classLoader);
            Object obj2 = ShareReflectUtil.a((Object) resources, "mDrawableInflater").get(resources);
            if (obj2 != null) {
                ShareReflectUtil.a(obj2, "mClassLoader").set(obj2, classLoader);
            }
        }
        Thread.currentThread().setContextClassLoader(classLoader);
    }

    public static void a(Context context, String str) {
        new AndroidNClassLoader(str, Context.class.getClassLoader());
    }

    public static AndroidNClassLoader a(BaseDexClassLoader baseDexClassLoader, Application application) throws Exception {
        AndroidNClassLoader b2 = b(baseDexClassLoader, application);
        a(application, (ClassLoader) b2);
        return b2;
    }

    public Class<?> findClass(String str) throws ClassNotFoundException {
        if (this.e != null && this.e.equals(str)) {
            return this.d.loadClass(str);
        }
        if (str != null && str.startsWith("com.tencent.tinker.loader.") && !str.equals(SystemClassLoaderAdder.f9223a)) {
            return this.d.loadClass(str);
        }
        if (str != null && (str.startsWith("org.apache.commons.codec.") || str.startsWith("org.apache.commons.logging.") || str.startsWith("org.apache.http."))) {
            return this.d.loadClass(str);
        }
        try {
            return super.findClass(str);
        } catch (ClassNotFoundException unused) {
            return this.d.loadClass(str);
        }
    }

    public String findLibrary(String str) {
        return super.findLibrary(str);
    }
}
