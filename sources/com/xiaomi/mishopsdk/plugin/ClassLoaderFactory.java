package com.xiaomi.mishopsdk.plugin;

import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.util.AndroidUtil;
import com.xiaomi.mishopsdk.util.FileUtil;
import com.xiaomi.mishopsdk.util.Log;
import com.xiaomi.mobilestats.StatService;
import com.xiaomi.shop2.util.DeviceUtil;
import dalvik.system.DexClassLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ClassLoaderFactory {
    private static final String TAG = "ClassLoaderFactory";
    private static String sCurrentInstructionSet;
    private static boolean sFactoryValid;
    private static String sFastDexPath;
    /* access modifiers changed from: private */
    public static String sOptimizedDexPath;
    /* access modifiers changed from: private */
    public static ClassLoader sParentClassLoader;
    private static HashMap<String, Boolean> sPluginOptValid;
    private static PluginOptimizedQueue sPluginOptimizedQueue;
    private static String sSystemFingerPrint;

    public static void initialize() {
        sParentClassLoader = ShopApp.instance.getClassLoader();
        File dir = ShopApp.instance.getDir(PluginSyncManager.PATH_DEXPLUGIN, 0);
        dir.mkdirs();
        sOptimizedDexPath = dir.getAbsolutePath();
        if (Build.VERSION.SDK_INT >= 23) {
            sSystemFingerPrint = Build.FINGERPRINT;
            if (!TextUtils.isEmpty(sSystemFingerPrint)) {
                sCurrentInstructionSet = DeviceUtil.getCurrentInstructionSet();
                if (!TextUtils.isEmpty(sCurrentInstructionSet)) {
                    File dir2 = ShopApp.instance.getDir(PluginSyncManager.PATH_FASTPLUGIN, 0);
                    dir2.mkdirs();
                    sFastDexPath = dir2.getAbsolutePath();
                    sPluginOptimizedQueue = new PluginOptimizedQueue();
                    sPluginOptValid = new HashMap<>();
                    sFactoryValid = true;
                }
            }
        }
    }

    public static ClassLoader createPluginClassLoader(String str, String str2) {
        if (!sFactoryValid) {
            if (Log.isDebug()) {
                Log.e(TAG, "ClassLoaderFactory can not work, process=%s, dexPath=%s, Build.VERSION.SDK_INT=%s, sSystemFingerPrint=%s, sCurrentInstructionSet=%s.", DeviceUtil.getProcessName(ShopApp.instance, Process.myPid()), str, Integer.valueOf(Build.VERSION.SDK_INT), sSystemFingerPrint, sCurrentInstructionSet);
            }
            return new DexClassLoader(str, sOptimizedDexPath, str2, sParentClassLoader);
        }
        File file = new File(str);
        if (!file.isFile()) {
            Log.e(TAG, "createPluginClassLoader dexPath is not a file, exit");
            return null;
        }
        String pluginFileName = getPluginFileName(file);
        if (isOptimizedOatFileValid(pluginFileName)) {
            DexClassLoader dexClassLoader = new DexClassLoader(str, sOptimizedDexPath, str2, sParentClassLoader);
            deleteFastDexFile(pluginFileName);
            Log.d(TAG, "createPluginClassLoader, use OptimizedOat:%s", (Object) pluginFileName);
            return dexClassLoader;
        }
        interpretDex2Oat(str, getFastDexFilePath(pluginFileName));
        DexClassLoader dexClassLoader2 = new DexClassLoader(str, sFastDexPath, str2, sParentClassLoader);
        sPluginOptimizedQueue.addPluginOptimizedTask(pluginFileName, str, str2);
        Log.d(TAG, "createPluginClassLoader, use FastOat:%s", (Object) pluginFileName);
        return dexClassLoader2;
    }

    private static boolean isOptimizedOatFileValid(String str) {
        if (sPluginOptValid.containsKey(str)) {
            return sPluginOptValid.get(str).booleanValue();
        }
        boolean z = false;
        if (isOptimizedOatFileExist(str)) {
            String curDexParentFingerPrint = getCurDexParentFingerPrint(str);
            if (!TextUtils.isEmpty(curDexParentFingerPrint) && curDexParentFingerPrint.equals(sSystemFingerPrint)) {
                z = true;
            }
        }
        sPluginOptValid.put(str, Boolean.valueOf(z));
        return z;
    }

    private static String getPluginFileName(File file) {
        String name = file.getName();
        return name.substring(0, name.lastIndexOf("."));
    }

    private static String getCurDexParentFingerPrint(String str) {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            fileInputStream = new FileInputStream(new File(getRecordFilePath(str)));
            try {
                inputStreamReader = new InputStreamReader(fileInputStream);
            } catch (Exception e) {
                e = e;
                inputStreamReader = null;
                bufferedReader = null;
                try {
                    Log.e(TAG, "getCurDexParentFingerPrint %s failed.", str, e);
                    FileUtil.closeQuietly(bufferedReader);
                    FileUtil.closeQuietly(inputStreamReader);
                    FileUtil.closeQuietly(fileInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    FileUtil.closeQuietly(bufferedReader2);
                    FileUtil.closeQuietly(inputStreamReader);
                    FileUtil.closeQuietly(fileInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                inputStreamReader = null;
                FileUtil.closeQuietly(bufferedReader2);
                FileUtil.closeQuietly(inputStreamReader);
                FileUtil.closeQuietly(fileInputStream);
                throw th;
            }
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
            } catch (Exception e2) {
                e = e2;
                bufferedReader = null;
                Log.e(TAG, "getCurDexParentFingerPrint %s failed.", str, e);
                FileUtil.closeQuietly(bufferedReader);
                FileUtil.closeQuietly(inputStreamReader);
                FileUtil.closeQuietly(fileInputStream);
                return null;
            } catch (Throwable th3) {
                th = th3;
                FileUtil.closeQuietly(bufferedReader2);
                FileUtil.closeQuietly(inputStreamReader);
                FileUtil.closeQuietly(fileInputStream);
                throw th;
            }
            try {
                String readLine = bufferedReader.readLine();
                FileUtil.closeQuietly(bufferedReader);
                FileUtil.closeQuietly(inputStreamReader);
                FileUtil.closeQuietly(fileInputStream);
                return readLine;
            } catch (Exception e3) {
                e = e3;
                Log.e(TAG, "getCurDexParentFingerPrint %s failed.", str, e);
                FileUtil.closeQuietly(bufferedReader);
                FileUtil.closeQuietly(inputStreamReader);
                FileUtil.closeQuietly(fileInputStream);
                return null;
            }
        } catch (Exception e4) {
            e = e4;
            inputStreamReader = null;
            fileInputStream = null;
            bufferedReader = null;
            Log.e(TAG, "getCurDexParentFingerPrint %s failed.", str, e);
            FileUtil.closeQuietly(bufferedReader);
            FileUtil.closeQuietly(inputStreamReader);
            FileUtil.closeQuietly(fileInputStream);
            return null;
        } catch (Throwable th4) {
            th = th4;
            inputStreamReader = null;
            fileInputStream = null;
            FileUtil.closeQuietly(bufferedReader2);
            FileUtil.closeQuietly(inputStreamReader);
            FileUtil.closeQuietly(fileInputStream);
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public static boolean saveCurDexParentOptTime(String str) {
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(new File(getRecordFilePath(str)));
            try {
                byte[] bytes = sSystemFingerPrint.getBytes();
                fileOutputStream2.write(bytes, 0, bytes.length);
                fileOutputStream2.flush();
                FileUtil.closeQuietly(fileOutputStream2);
                return true;
            } catch (Exception e) {
                e = e;
                fileOutputStream = fileOutputStream2;
                try {
                    Log.e(TAG, "saveCurDexParentOptTime %s failed.", str, e);
                    FileUtil.closeQuietly(fileOutputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    FileUtil.closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                FileUtil.closeQuietly(fileOutputStream);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            Log.e(TAG, "saveCurDexParentOptTime %s failed.", str, e);
            FileUtil.closeQuietly(fileOutputStream);
            return false;
        }
    }

    private static String getRecordFilePath(String str) {
        return sFastDexPath + File.separator + str + ".cfg";
    }

    private static String getFastDexFilePath(String str) {
        return sFastDexPath + File.separator + str + ShareConstants.w;
    }

    /* access modifiers changed from: private */
    public static String getOptimizedDexFilePath(String str) {
        return sOptimizedDexPath + File.separator + str + ShareConstants.w;
    }

    private static boolean isOptimizedOatFileExist(String str) {
        return new File(getOptimizedDexFilePath(str)).isFile();
    }

    private static void deleteFastDexFile(String str) {
        File file = new File(getFastDexFilePath(str));
        if (file.isFile()) {
            file.delete();
        }
    }

    private static void interpretDex2Oat(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("dex2oat");
        if (Build.VERSION.SDK_INT >= 24) {
            arrayList.add("--runtime-arg");
            arrayList.add("-classpath");
            arrayList.add("--runtime-arg");
            arrayList.add(a.b);
        }
        arrayList.add("--dex-file=" + str);
        arrayList.add("--oat-file=" + str2);
        arrayList.add("--instruction-set=" + sCurrentInstructionSet);
        if (Build.VERSION.SDK_INT > 25) {
            arrayList.add("--compiler-filter=quicken");
        } else {
            arrayList.add("--compiler-filter=interpret-only");
        }
        ProcessBuilder processBuilder = new ProcessBuilder(arrayList);
        processBuilder.redirectErrorStream(true);
        try {
            int waitFor = processBuilder.start().waitFor();
            if (waitFor != 0) {
                throw new IOException("interpretDex2Oat works unsuccessfully, exit code: " + waitFor);
            }
        } catch (Exception e) {
            StatService.onError(ShopApp.instance, e, new HashMap<String, String>() {
                {
                    put("type", "interpretDex2Oat");
                }
            });
            sFactoryValid = false;
        }
    }

    /* access modifiers changed from: private */
    public static void speedDex2Oat(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("dex2oat");
        arrayList.add("--dex-file=" + str);
        arrayList.add("--oat-file=" + str2);
        arrayList.add("--instruction-set=" + sCurrentInstructionSet);
        ProcessBuilder processBuilder = new ProcessBuilder(arrayList);
        processBuilder.redirectErrorStream(true);
        try {
            int waitFor = processBuilder.start().waitFor();
            if (waitFor != 0) {
                throw new IOException("speedDex2Oat works unsuccessfully, exit code: " + waitFor);
            }
        } catch (Exception e) {
            StatService.onError(ShopApp.instance, e, new HashMap<String, String>() {
                {
                    put("type", "speedDex2Oat");
                }
            });
            sFactoryValid = false;
        }
    }

    private static class PluginOptimizedQueue {
        private HashSet<String> mOptimizedSet = new HashSet<>();

        public synchronized void addPluginOptimizedTask(final String str, final String str2, final String str3) {
            if (!this.mOptimizedSet.contains(str)) {
                this.mOptimizedSet.add(str);
                AndroidUtil.sPluginQueue.postRunnable(new Runnable() {
                    public void run() {
                        if (Build.VERSION.SDK_INT <= 23) {
                            ClassLoaderFactory.speedDex2Oat(str2, ClassLoaderFactory.getOptimizedDexFilePath(str));
                        } else {
                            new DexClassLoader(str2, ClassLoaderFactory.sOptimizedDexPath, str3, ClassLoaderFactory.sParentClassLoader);
                        }
                        boolean unused = ClassLoaderFactory.saveCurDexParentOptTime(str);
                        Log.d(ClassLoaderFactory.TAG, "PluginOptimizedQueue, general opt oat:%s", (Object) str);
                    }
                });
            }
        }
    }
}
