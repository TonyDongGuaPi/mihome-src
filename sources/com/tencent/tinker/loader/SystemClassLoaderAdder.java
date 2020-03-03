package com.tencent.tinker.loader;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.zip.ZipFile;

public class SystemClassLoaderAdder {

    /* renamed from: a  reason: collision with root package name */
    public static final String f9223a = "com.tencent.tinker.loader.TinkerTestDexLoad";
    public static final String b = "isPatch";
    private static final String c = "Tinker.ClassLoaderAdder";
    private static int d;

    @SuppressLint({"NewApi"})
    public static void a(Application application, BaseDexClassLoader baseDexClassLoader, File file, List<File> list, boolean z) throws Throwable {
        Log.i(c, "installDexes dexOptDir: " + file.getAbsolutePath() + ", dex size:" + list.size());
        if (!list.isEmpty()) {
            List<File> a2 = a(list);
            if (Build.VERSION.SDK_INT >= 24 && !z) {
                baseDexClassLoader = AndroidNClassLoader.a(baseDexClassLoader, application);
            }
            if (Build.VERSION.SDK_INT >= 23) {
                V23.b(baseDexClassLoader, a2, file);
            } else if (Build.VERSION.SDK_INT >= 19) {
                V19.b(baseDexClassLoader, a2, file);
            } else if (Build.VERSION.SDK_INT >= 14) {
                V14.b(baseDexClassLoader, a2, file);
            } else {
                V4.b(baseDexClassLoader, a2, file);
            }
            d = a2.size();
            Log.i(c, "after loaded classloader: " + baseDexClassLoader + ", dex size:" + d);
            if (!b(baseDexClassLoader)) {
                a((ClassLoader) baseDexClassLoader);
                throw new TinkerRuntimeException(ShareConstants.E);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static void a(PathClassLoader pathClassLoader, List<File> list) throws Throwable {
        if (!list.isEmpty()) {
            List<File> a2 = a(list);
            ArkHot.b(pathClassLoader, a2);
            d = a2.size();
            Log.i(c, "after loaded classloader: " + pathClassLoader + ", dex size:" + d);
            b(pathClassLoader);
        }
    }

    public static void a(ClassLoader classLoader) throws Throwable {
        if (d > 0) {
            if (Build.VERSION.SDK_INT >= 14) {
                ShareReflectUtil.a(ShareReflectUtil.a((Object) classLoader, "pathList").get(classLoader), "dexElements", d);
                return;
            }
            ShareReflectUtil.a((Object) classLoader, "mPaths", d);
            ShareReflectUtil.a((Object) classLoader, "mFiles", d);
            ShareReflectUtil.a((Object) classLoader, "mZips", d);
            try {
                ShareReflectUtil.a((Object) classLoader, "mDexs", d);
            } catch (Exception unused) {
            }
        }
    }

    private static boolean b(ClassLoader classLoader) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        boolean booleanValue = ((Boolean) ShareReflectUtil.a(Class.forName(f9223a, true, classLoader), b).get((Object) null)).booleanValue();
        Log.w(c, "checkDexInstall result:" + booleanValue);
        return booleanValue;
    }

    private static List<File> a(List<File> list) {
        ArrayList<File> arrayList = new ArrayList<>(list);
        final HashMap hashMap = new HashMap();
        for (File name : arrayList) {
            String name2 = name.getName();
            hashMap.put(name2, Boolean.valueOf(ShareConstants.D.matcher(name2).matches()));
        }
        Collections.sort(arrayList, new Comparator<File>() {
            /* renamed from: a */
            public int compare(File file, File file2) {
                if (file == null && file2 == null) {
                    return 0;
                }
                if (file == null) {
                    return -1;
                }
                if (file2 == null) {
                    return 1;
                }
                String name = file.getName();
                String name2 = file2.getName();
                if (name.equals(name2)) {
                    return 0;
                }
                if (name.startsWith(ShareConstants.A)) {
                    return 1;
                }
                if (name2.startsWith(ShareConstants.A)) {
                    return -1;
                }
                boolean booleanValue = ((Boolean) hashMap.get(name)).booleanValue();
                boolean booleanValue2 = ((Boolean) hashMap.get(name2)).booleanValue();
                if (booleanValue && booleanValue2) {
                    int indexOf = name.indexOf(46);
                    int indexOf2 = name2.indexOf(46);
                    int parseInt = indexOf > 7 ? Integer.parseInt(name.substring(7, indexOf)) : 1;
                    int parseInt2 = indexOf2 > 7 ? Integer.parseInt(name2.substring(7, indexOf2)) : 1;
                    if (parseInt == parseInt2) {
                        return 0;
                    }
                    return parseInt < parseInt2 ? -1 : 1;
                } else if (booleanValue) {
                    return -1;
                } else {
                    if (booleanValue2) {
                        return 1;
                    }
                    return name.compareTo(name2);
                }
            }
        });
        return arrayList;
    }

    private static final class ArkHot {
        private ArkHot() {
        }

        /* access modifiers changed from: private */
        public static void b(ClassLoader classLoader, List<File> list) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException, ClassNotFoundException, SecurityException {
            Class<?> loadClass = ClassLoader.getSystemClassLoader().getParent().loadClass("com.huawei.ark.classloader.ExtendedClassLoaderHelper");
            for (File canonicalPath : list) {
                String canonicalPath2 = canonicalPath.getCanonicalPath();
                Method declaredMethod = loadClass.getDeclaredMethod("applyPatch", new Class[]{ClassLoader.class, String.class});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke((Object) null, new Object[]{classLoader, canonicalPath2});
                Log.i(SystemClassLoaderAdder.c, "ArkHot install path = " + canonicalPath2);
            }
        }
    }

    private static final class V23 {
        private V23() {
        }

        /* access modifiers changed from: private */
        public static void b(ClassLoader classLoader, List<File> list, File file) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException {
            Object obj = ShareReflectUtil.a((Object) classLoader, "pathList").get(classLoader);
            ArrayList arrayList = new ArrayList();
            ShareReflectUtil.a(obj, "dexElements", a(obj, new ArrayList(list), file, arrayList));
            if (arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                if (it.hasNext()) {
                    IOException iOException = (IOException) it.next();
                    Log.w(SystemClassLoaderAdder.c, "Exception in makePathElement", iOException);
                    throw iOException;
                }
            }
        }

        private static Object[] a(Object obj, ArrayList<File> arrayList, File file, ArrayList<IOException> arrayList2) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            Method method;
            try {
                method = ShareReflectUtil.a(obj, "makePathElements", (Class<?>[]) new Class[]{List.class, File.class, List.class});
            } catch (NoSuchMethodException unused) {
                Log.e(SystemClassLoaderAdder.c, "NoSuchMethodException: makePathElements(List,File,List) failure");
                try {
                    method = ShareReflectUtil.a(obj, "makePathElements", (Class<?>[]) new Class[]{ArrayList.class, File.class, ArrayList.class});
                } catch (NoSuchMethodException unused2) {
                    Log.e(SystemClassLoaderAdder.c, "NoSuchMethodException: makeDexElements(ArrayList,File,ArrayList) failure");
                    try {
                        Log.e(SystemClassLoaderAdder.c, "NoSuchMethodException: try use v19 instead");
                        return V19.b(obj, arrayList, file, arrayList2);
                    } catch (NoSuchMethodException e) {
                        Log.e(SystemClassLoaderAdder.c, "NoSuchMethodException: makeDexElements(List,File,List) failure");
                        throw e;
                    }
                }
            }
            return (Object[]) method.invoke(obj, new Object[]{arrayList, file, arrayList2});
        }
    }

    private static final class V19 {
        private V19() {
        }

        /* access modifiers changed from: private */
        public static void b(ClassLoader classLoader, List<File> list, File file) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException {
            Object obj = ShareReflectUtil.a((Object) classLoader, "pathList").get(classLoader);
            ArrayList arrayList = new ArrayList();
            ShareReflectUtil.a(obj, "dexElements", b(obj, new ArrayList(list), file, arrayList));
            if (arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                if (it.hasNext()) {
                    IOException iOException = (IOException) it.next();
                    Log.w(SystemClassLoaderAdder.c, "Exception in makeDexElement", iOException);
                    throw iOException;
                }
            }
        }

        /* access modifiers changed from: private */
        public static Object[] b(Object obj, ArrayList<File> arrayList, File file, ArrayList<IOException> arrayList2) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            Method method;
            try {
                method = ShareReflectUtil.a(obj, "makeDexElements", (Class<?>[]) new Class[]{ArrayList.class, File.class, ArrayList.class});
            } catch (NoSuchMethodException unused) {
                Log.e(SystemClassLoaderAdder.c, "NoSuchMethodException: makeDexElements(ArrayList,File,ArrayList) failure");
                try {
                    method = ShareReflectUtil.a(obj, "makeDexElements", (Class<?>[]) new Class[]{List.class, File.class, List.class});
                } catch (NoSuchMethodException e) {
                    Log.e(SystemClassLoaderAdder.c, "NoSuchMethodException: makeDexElements(List,File,List) failure");
                    throw e;
                }
            }
            return (Object[]) method.invoke(obj, new Object[]{arrayList, file, arrayList2});
        }
    }

    private static final class V14 {
        private V14() {
        }

        /* access modifiers changed from: private */
        public static void b(ClassLoader classLoader, List<File> list, File file) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
            Object obj = ShareReflectUtil.a((Object) classLoader, "pathList").get(classLoader);
            ShareReflectUtil.a(obj, "dexElements", a(obj, (ArrayList<File>) new ArrayList(list), file));
        }

        private static Object[] a(Object obj, ArrayList<File> arrayList, File file) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            return (Object[]) ShareReflectUtil.a(obj, "makeDexElements", (Class<?>[]) new Class[]{ArrayList.class, File.class}).invoke(obj, new Object[]{arrayList, file});
        }
    }

    private static final class V4 {
        private V4() {
        }

        /* access modifiers changed from: private */
        public static void b(ClassLoader classLoader, List<File> list, File file) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, IOException {
            int size = list.size();
            Field a2 = ShareReflectUtil.a((Object) classLoader, "path");
            StringBuilder sb = new StringBuilder((String) a2.get(classLoader));
            String[] strArr = new String[size];
            File[] fileArr = new File[size];
            ZipFile[] zipFileArr = new ZipFile[size];
            DexFile[] dexFileArr = new DexFile[size];
            ListIterator<File> listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                File next = listIterator.next();
                String absolutePath = next.getAbsolutePath();
                sb.append(Operators.CONDITION_IF_MIDDLE);
                sb.append(absolutePath);
                int previousIndex = listIterator.previousIndex();
                strArr[previousIndex] = absolutePath;
                fileArr[previousIndex] = next;
                zipFileArr[previousIndex] = new ZipFile(next);
                dexFileArr[previousIndex] = DexFile.loadDex(absolutePath, SharePatchFileUtil.b(next, file), 0);
            }
            a2.set(classLoader, sb.toString());
            ShareReflectUtil.a((Object) classLoader, "mPaths", (Object[]) strArr);
            ShareReflectUtil.a((Object) classLoader, "mFiles", (Object[]) fileArr);
            ShareReflectUtil.a((Object) classLoader, "mZips", (Object[]) zipFileArr);
            try {
                ShareReflectUtil.a((Object) classLoader, "mDexs", (Object[]) dexFileArr);
            } catch (Exception unused) {
            }
        }
    }
}
