package com.xiaomi.miot.store.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.miot.store.common.MiotStoreConstant;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.youpin.log.LogUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11424a = "Utils";

    public static boolean a() {
        return Build.MODEL.toLowerCase().equals("mi pad 2");
    }

    @Nullable
    public static String a(Context context, String str) {
        BufferedReader bufferedReader;
        if (context == null) {
            return null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(MiotStoreConstant.i, 0);
        String string = sharedPreferences.getString(MiotStoreConstant.j, (String) null);
        String string2 = sharedPreferences.getString(MiotStoreConstant.p, (String) null);
        if (!TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string) && string2.contains(string) && string2.contains(JSMethod.NOT_SET)) {
            String[] split = string2.split(JSMethod.NOT_SET);
            String str2 = split.length >= 2 ? split[0] : null;
            if (str2 != null && str2.length() > 0) {
                return str2;
            }
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists() || file.length() < 0) {
            return null;
        }
        try {
            Pattern compile = Pattern.compile("MakeTime=\"([\\d]*)\"");
            bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (readLine.contains("MakeTime=")) {
                        Matcher matcher = compile.matcher(readLine);
                        if (matcher.find() && matcher.groupCount() == 1) {
                            String group = matcher.group(1);
                            if (group != null) {
                                sharedPreferences.edit().putString(MiotStoreConstant.p, group + JSMethod.NOT_SET + string).apply();
                            }
                            IOUtils.a((Closeable) bufferedReader);
                            return group;
                        }
                    }
                } catch (Exception e) {
                    e = e;
                    try {
                        e.printStackTrace();
                        IOUtils.a((Closeable) bufferedReader);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        IOUtils.a((Closeable) bufferedReader);
                        throw th;
                    }
                }
            }
        } catch (Exception e2) {
            e = e2;
            bufferedReader = null;
            e.printStackTrace();
            IOUtils.a((Closeable) bufferedReader);
            return null;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
            IOUtils.a((Closeable) bufferedReader);
            throw th;
        }
        IOUtils.a((Closeable) bufferedReader);
        return null;
    }

    public static boolean a(Context context) {
        if (Build.VERSION.SDK_INT >= 16 && !a()) {
            return true;
        }
        LogUtils.i("RA_JNI", "React native not Support!");
        return false;
    }

    public static boolean a(File file) {
        File[] listFiles;
        if (file.exists() && (listFiles = file.listFiles()) != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    a(listFiles[i]);
                } else {
                    listFiles[i].delete();
                }
            }
        }
        return file.delete();
    }

    public static boolean a(File file, String str) {
        File[] listFiles;
        if (!file.exists() || (listFiles = file.listFiles()) == null) {
            return true;
        }
        for (int i = 0; i < listFiles.length; i++) {
            if (TextUtils.isEmpty(str) || !listFiles[i].getName().startsWith(str)) {
                if (listFiles[i].isDirectory()) {
                    a(listFiles[i]);
                } else {
                    listFiles[i].delete();
                }
            }
        }
        return true;
    }

    public static boolean a(AssetManager assetManager, String str, String str2) {
        try {
            String[] list = assetManager.list(str);
            new File(str2).mkdirs();
            boolean z = true;
            for (String str3 : list) {
                if (str3 != ".") {
                    if (str3 != "..") {
                        if (str3.contains(".")) {
                            z &= b(assetManager, str + File.separator + str3, str2 + File.separator + str3);
                        } else {
                            z &= a(assetManager, str + File.separator + str3, str2 + File.separator + str3);
                        }
                    }
                }
            }
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: type inference failed for: r2v7, types: [java.io.OutputStream, java.io.Closeable, java.io.FileOutputStream] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean b(android.content.res.AssetManager r1, java.lang.String r2, java.lang.String r3) {
        /*
            r0 = 0
            java.io.InputStream r1 = r1.open(r2)     // Catch:{ Exception -> 0x002d, all -> 0x002a }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0026, all -> 0x0024 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0026, all -> 0x0024 }
            r2.createNewFile()     // Catch:{ Exception -> 0x0026, all -> 0x0024 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0026, all -> 0x0024 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0026, all -> 0x0024 }
            com.xiaomi.miot.store.utils.IOUtils.a((java.io.InputStream) r1, (java.io.OutputStream) r2)     // Catch:{ Exception -> 0x0022, all -> 0x0020 }
            r2.flush()     // Catch:{ Exception -> 0x0022, all -> 0x0020 }
            r3 = 1
            com.xiaomi.miot.store.utils.IOUtils.a((java.io.Closeable) r1)
            com.xiaomi.miot.store.utils.IOUtils.a((java.io.Closeable) r2)
            return r3
        L_0x0020:
            r3 = move-exception
            goto L_0x003c
        L_0x0022:
            r3 = move-exception
            goto L_0x0028
        L_0x0024:
            r3 = move-exception
            goto L_0x003d
        L_0x0026:
            r3 = move-exception
            r2 = r0
        L_0x0028:
            r0 = r1
            goto L_0x002f
        L_0x002a:
            r3 = move-exception
            r1 = r0
            goto L_0x003d
        L_0x002d:
            r3 = move-exception
            r2 = r0
        L_0x002f:
            r3.printStackTrace()     // Catch:{ all -> 0x003a }
            r1 = 0
            com.xiaomi.miot.store.utils.IOUtils.a((java.io.Closeable) r0)
            com.xiaomi.miot.store.utils.IOUtils.a((java.io.Closeable) r2)
            return r1
        L_0x003a:
            r3 = move-exception
            r1 = r0
        L_0x003c:
            r0 = r2
        L_0x003d:
            com.xiaomi.miot.store.utils.IOUtils.a((java.io.Closeable) r1)
            com.xiaomi.miot.store.utils.IOUtils.a((java.io.Closeable) r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miot.store.utils.Utils.b(android.content.res.AssetManager, java.lang.String, java.lang.String):boolean");
    }

    public static void a(boolean z, Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                if (z) {
                    attributes.flags |= Constants.CALLIGRAPHY_TAG_PRICE;
                } else {
                    attributes.flags &= -67108865;
                }
                window.setAttributes(attributes);
                b(z, activity);
            } catch (Exception unused) {
            }
        }
    }

    public static void b(boolean z, Activity activity) {
        try {
            Class<?> cls = activity.getWindow().getClass();
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
            Window window = activity.getWindow();
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(z ? i : 0);
            objArr[1] = Integer.valueOf(i);
            method.invoke(window, objArr);
        } catch (Exception unused) {
        }
    }

    public static boolean b(File file, String str) {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream2;
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            return false;
        }
        FileInputStream fileInputStream2 = null;
        try {
            FileInputStream fileInputStream3 = new FileInputStream(file);
            try {
                fileOutputStream2 = new FileOutputStream(str);
            } catch (Exception e) {
                e = e;
                fileInputStream = fileInputStream3;
                fileOutputStream = null;
                fileInputStream2 = fileInputStream;
                try {
                    LogUtils.e("Copy file error", str);
                    e.printStackTrace();
                    IOUtils.a((Closeable) fileInputStream2);
                    IOUtils.a((Closeable) fileOutputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    IOUtils.a((Closeable) fileInputStream2);
                    IOUtils.a((Closeable) fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                FileInputStream fileInputStream4 = fileInputStream3;
                fileOutputStream = null;
                fileInputStream2 = fileInputStream4;
                IOUtils.a((Closeable) fileInputStream2);
                IOUtils.a((Closeable) fileOutputStream);
                throw th;
            }
            try {
                IOUtils.a((InputStream) fileInputStream3, (OutputStream) fileOutputStream2);
                fileOutputStream2.flush();
                IOUtils.a((Closeable) fileInputStream3);
                IOUtils.a((Closeable) fileOutputStream2);
                return true;
            } catch (Exception e2) {
                fileInputStream = fileInputStream3;
                fileOutputStream = fileOutputStream2;
                e = e2;
                fileInputStream2 = fileInputStream;
                LogUtils.e("Copy file error", str);
                e.printStackTrace();
                IOUtils.a((Closeable) fileInputStream2);
                IOUtils.a((Closeable) fileOutputStream);
                return false;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream2 = fileInputStream3;
                fileOutputStream = fileOutputStream2;
                IOUtils.a((Closeable) fileInputStream2);
                IOUtils.a((Closeable) fileOutputStream);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream = null;
            LogUtils.e("Copy file error", str);
            e.printStackTrace();
            IOUtils.a((Closeable) fileInputStream2);
            IOUtils.a((Closeable) fileOutputStream);
            return false;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
            IOUtils.a((Closeable) fileInputStream2);
            IOUtils.a((Closeable) fileOutputStream);
            throw th;
        }
    }

    public static boolean a(String str, String str2) {
        File file = new File(str);
        if (!file.exists() || !file.isDirectory() || !file.canRead()) {
            return false;
        }
        File file2 = new File(str2);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        if (!str2.endsWith(File.separator)) {
            str2 = str2 + File.separator;
        }
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        try {
            String[] list = file.list();
            for (int i = 0; i < list.length; i++) {
                File file3 = new File(str + list[i]);
                if (file3.isFile()) {
                    b(file3, str2 + file3.getName().toString());
                }
                if (file3.isDirectory()) {
                    a(str + list[i], str2 + list[i]);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void b(File file) {
        if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                file.delete();
                return;
            }
            for (File b : listFiles) {
                b(b);
            }
            file.delete();
        }
    }

    public static void a(Activity activity) {
        InputMethodManager inputMethodManager;
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus != null && (inputMethodManager = (InputMethodManager) activity.getSystemService("input_method")) != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }
}
