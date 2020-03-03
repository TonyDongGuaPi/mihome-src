package com.tencent.tinker.loader;

import android.annotation.TargetApi;
import android.content.Intent;
import android.util.Log;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareArkHotDiffPatchInfo;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class TinkerArkHotLoader {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9225a = "Tinker.TinkerArkHotLoader";
    private static final String b = "assets/arkHot_meta.txt";
    private static final String c = "arkHot";
    private static HashSet<ShareArkHotDiffPatchInfo> d = new HashSet<>();
    private static boolean e = ShareTinkerInternals.c();

    private TinkerArkHotLoader() {
    }

    @TargetApi(14)
    public static boolean a(TinkerApplication tinkerApplication, String str, Intent intent) {
        if (d.isEmpty()) {
            Log.w(f9225a, "there is no apk to load");
            return true;
        }
        PathClassLoader pathClassLoader = (PathClassLoader) TinkerArkHotLoader.class.getClassLoader();
        if (pathClassLoader != null) {
            Log.i(f9225a, "classloader: " + pathClassLoader.toString());
            String str2 = str + "/" + "arkHot" + "/";
            ArrayList arrayList = new ArrayList();
            if (e && !d.isEmpty()) {
                arrayList.add(new File(str2 + ShareConstants.C));
            }
            try {
                SystemClassLoaderAdder.a(pathClassLoader, arrayList);
                return true;
            } catch (Throwable th) {
                Log.e(f9225a, "install dexes failed");
                intent.putExtra(ShareIntentUtil.l, th);
                ShareIntentUtil.a(intent, -14);
                return false;
            }
        } else {
            Log.e(f9225a, "classloader is null");
            ShareIntentUtil.a(intent, -12);
            return false;
        }
    }

    public static boolean a(String str, ShareSecurityCheck shareSecurityCheck, Intent intent) {
        String str2 = shareSecurityCheck.a().get("assets/arkHot_meta.txt");
        if (str2 == null) {
            return true;
        }
        d.clear();
        ArrayList arrayList = new ArrayList();
        ShareArkHotDiffPatchInfo.a(str2, arrayList);
        if (arrayList.isEmpty()) {
            return true;
        }
        HashMap hashMap = new HashMap(1);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ShareArkHotDiffPatchInfo shareArkHotDiffPatchInfo = (ShareArkHotDiffPatchInfo) it.next();
            if (!ShareArkHotDiffPatchInfo.a(shareArkHotDiffPatchInfo)) {
                intent.putExtra(ShareIntentUtil.m, -3);
                ShareIntentUtil.a(intent, -8);
                return false;
            } else if (e && ShareConstants.C.equals(shareArkHotDiffPatchInfo.b)) {
                d.add(shareArkHotDiffPatchInfo);
            }
        }
        if (e && !d.isEmpty()) {
            hashMap.put(ShareConstants.C, "");
        }
        String str3 = str + "/" + "arkHot" + "/";
        File file = new File(str3);
        if (!file.exists() || !file.isDirectory()) {
            ShareIntentUtil.a(intent, -9);
            return false;
        }
        for (String str4 : hashMap.keySet()) {
            File file2 = new File(str3 + str4);
            if (!SharePatchFileUtil.a(file2)) {
                try {
                    intent.putExtra(ShareIntentUtil.f, file2.getCanonicalPath());
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                ShareIntentUtil.a(intent, -10);
                return false;
            }
        }
        intent.putExtra(ShareIntentUtil.g, hashMap);
        return true;
    }
}
