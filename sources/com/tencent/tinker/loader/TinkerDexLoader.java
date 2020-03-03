package com.tencent.tinker.loader;

import android.annotation.TargetApi;
import android.content.Intent;
import android.util.Log;
import com.tencent.tinker.loader.TinkerDexOptimizer;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareDexDiffPatchInfo;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class TinkerDexLoader {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9226a = "Tinker.TinkerDexLoader";
    private static final String b = "assets/dex_meta.txt";
    private static final String c = "dex";
    private static final String d = "odex";
    private static final String e = "interpet";
    private static final ArrayList<ShareDexDiffPatchInfo> f = new ArrayList<>();
    private static HashSet<ShareDexDiffPatchInfo> g = new HashSet<>();
    private static boolean h = ShareTinkerInternals.a();

    private TinkerDexLoader() {
    }

    @TargetApi(14)
    public static boolean a(TinkerApplication tinkerApplication, String str, String str2, Intent intent, boolean z, boolean z2) {
        File file;
        TinkerApplication tinkerApplication2;
        String str3 = str;
        Intent intent2 = intent;
        if (!f.isEmpty() || !g.isEmpty()) {
            BaseDexClassLoader baseDexClassLoader = (BaseDexClassLoader) TinkerDexLoader.class.getClassLoader();
            if (baseDexClassLoader != null) {
                Log.i(f9226a, "classloader: " + baseDexClassLoader.toString());
                String str4 = str3 + "/" + "dex" + "/";
                ArrayList arrayList = new ArrayList();
                Iterator<ShareDexDiffPatchInfo> it = f.iterator();
                while (it.hasNext()) {
                    ShareDexDiffPatchInfo next = it.next();
                    if (!b(next)) {
                        File file2 = new File(str4 + next.j);
                        if (tinkerApplication.isTinkerLoadVerifyFlag()) {
                            long currentTimeMillis = System.currentTimeMillis();
                            if (!SharePatchFileUtil.b(file2, a(next))) {
                                ShareIntentUtil.a(intent2, -13);
                                intent2.putExtra(ShareIntentUtil.e, file2.getAbsolutePath());
                                return false;
                            }
                            Log.i(f9226a, "verify dex file:" + file2.getPath() + " md5, use time: " + (System.currentTimeMillis() - currentTimeMillis));
                        }
                        arrayList.add(file2);
                    }
                }
                if (h && !g.isEmpty()) {
                    File file3 = new File(str4 + ShareConstants.B);
                    long currentTimeMillis2 = System.currentTimeMillis();
                    if (tinkerApplication.isTinkerLoadVerifyFlag()) {
                        Iterator<ShareDexDiffPatchInfo> it2 = g.iterator();
                        while (it2.hasNext()) {
                            ShareDexDiffPatchInfo next2 = it2.next();
                            if (!SharePatchFileUtil.a(file3, next2.f1358a, next2.c)) {
                                ShareIntentUtil.a(intent2, -13);
                                intent2.putExtra(ShareIntentUtil.e, file3.getAbsolutePath());
                                return false;
                            }
                        }
                    }
                    Log.i(f9226a, "verify dex file:" + file3.getPath() + " md5, use time: " + (System.currentTimeMillis() - currentTimeMillis2));
                    arrayList.add(file3);
                }
                File file4 = new File(str3 + "/" + str2);
                if (z) {
                    final boolean[] zArr = {true};
                    final Throwable[] thArr = new Throwable[1];
                    try {
                        String e2 = ShareTinkerInternals.e();
                        a(str);
                        Log.w(f9226a, "systemOTA, try parallel oat dexes, targetISA:" + e2);
                        file = new File(str3 + "/" + "interpet");
                        TinkerDexOptimizer.a(tinkerApplication, arrayList, file, true, e2, new TinkerDexOptimizer.ResultCallback() {

                            /* renamed from: a  reason: collision with root package name */
                            long f9227a;

                            public void a(File file, File file2) {
                                this.f9227a = System.currentTimeMillis();
                                Log.i(TinkerDexLoader.f9226a, "start to optimize dex:" + file.getPath());
                            }

                            public void a(File file, File file2, File file3) {
                                Log.i(TinkerDexLoader.f9226a, "success to optimize dex " + file.getPath() + ", use time " + (System.currentTimeMillis() - this.f9227a));
                            }

                            public void a(File file, File file2, Throwable th) {
                                zArr[0] = false;
                                thArr[0] = th;
                                Log.i(TinkerDexLoader.f9226a, "fail to optimize dex " + file.getPath() + ", use time " + (System.currentTimeMillis() - this.f9227a));
                            }
                        });
                        if (!zArr[0]) {
                            Log.e(f9226a, "parallel oat dexes failed");
                            intent2.putExtra(ShareIntentUtil.q, thArr[0]);
                            ShareIntentUtil.a(intent2, -16);
                            return false;
                        }
                        tinkerApplication2 = tinkerApplication;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        Log.i(f9226a, "getCurrentInstructionSet fail:" + th2);
                        a(str);
                        intent2.putExtra(ShareIntentUtil.q, th2);
                        ShareIntentUtil.a(intent2, -15);
                        return false;
                    }
                } else {
                    tinkerApplication2 = tinkerApplication;
                    file = file4;
                }
                try {
                    SystemClassLoaderAdder.a(tinkerApplication2, baseDexClassLoader, file, arrayList, z2);
                    return true;
                } catch (Throwable th3) {
                    Log.e(f9226a, "install dexes failed");
                    intent2.putExtra(ShareIntentUtil.l, th3);
                    ShareIntentUtil.a(intent2, -14);
                    return false;
                }
            } else {
                Log.e(f9226a, "classloader is null");
                ShareIntentUtil.a(intent2, -12);
                return false;
            }
        } else {
            Log.w(f9226a, "there is no dex to load");
            return true;
        }
    }

    public static boolean a(String str, ShareSecurityCheck shareSecurityCheck, String str2, Intent intent) {
        String str3 = shareSecurityCheck.a().get("assets/dex_meta.txt");
        if (str3 == null) {
            return true;
        }
        f.clear();
        g.clear();
        ArrayList arrayList = new ArrayList();
        ShareDexDiffPatchInfo.a(str3, arrayList);
        if (arrayList.isEmpty()) {
            return true;
        }
        HashMap hashMap = new HashMap();
        ShareDexDiffPatchInfo shareDexDiffPatchInfo = null;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ShareDexDiffPatchInfo shareDexDiffPatchInfo2 = (ShareDexDiffPatchInfo) it.next();
            if (!b(shareDexDiffPatchInfo2)) {
                if (!ShareDexDiffPatchInfo.a(shareDexDiffPatchInfo2)) {
                    intent.putExtra(ShareIntentUtil.m, -3);
                    ShareIntentUtil.a(intent, -8);
                    return false;
                } else if (h && shareDexDiffPatchInfo2.f1358a.startsWith(ShareConstants.A)) {
                    shareDexDiffPatchInfo = shareDexDiffPatchInfo2;
                } else if (!h || !ShareConstants.D.matcher(shareDexDiffPatchInfo2.j).matches()) {
                    hashMap.put(shareDexDiffPatchInfo2.j, a(shareDexDiffPatchInfo2));
                    f.add(shareDexDiffPatchInfo2);
                } else {
                    g.add(shareDexDiffPatchInfo2);
                }
            }
        }
        if (h && (shareDexDiffPatchInfo != null || !g.isEmpty())) {
            if (shareDexDiffPatchInfo != null) {
                g.add(ShareTinkerInternals.a(shareDexDiffPatchInfo, g.size() + 1));
            }
            hashMap.put(ShareConstants.B, "");
        }
        String str4 = str + "/" + "dex" + "/";
        File file = new File(str4);
        if (!file.exists() || !file.isDirectory()) {
            ShareIntentUtil.a(intent, -9);
            return false;
        }
        File file2 = new File(str + "/" + str2 + "/");
        for (String str5 : hashMap.keySet()) {
            File file3 = new File(str4 + str5);
            if (!SharePatchFileUtil.a(file3)) {
                intent.putExtra(ShareIntentUtil.f, file3.getAbsolutePath());
                ShareIntentUtil.a(intent, -10);
                return false;
            }
            File file4 = new File(SharePatchFileUtil.b(file3, file2));
            if (!SharePatchFileUtil.a(file4) && !SharePatchFileUtil.b(file4)) {
                intent.putExtra(ShareIntentUtil.f, file4.getAbsolutePath());
                ShareIntentUtil.a(intent, -11);
                return false;
            }
        }
        intent.putExtra(ShareIntentUtil.g, hashMap);
        return true;
    }

    private static String a(ShareDexDiffPatchInfo shareDexDiffPatchInfo) {
        return h ? shareDexDiffPatchInfo.c : shareDexDiffPatchInfo.b;
    }

    private static void a(String str) {
        SharePatchFileUtil.f(str + "/" + "odex" + "/");
        if (ShareTinkerInternals.d()) {
            SharePatchFileUtil.f(str + "/" + "dex" + "/" + ShareConstants.t + "/");
        }
    }

    private static boolean b(ShareDexDiffPatchInfo shareDexDiffPatchInfo) {
        if (!h && shareDexDiffPatchInfo.b.equals("0")) {
            return true;
        }
        return false;
    }
}
