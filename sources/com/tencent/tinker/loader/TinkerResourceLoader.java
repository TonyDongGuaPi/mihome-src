package com.tencent.tinker.loader;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareResPatchInfo;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import java.io.File;

public class TinkerResourceLoader {

    /* renamed from: a  reason: collision with root package name */
    protected static final String f9232a = "assets/res_meta.txt";
    protected static final String b = "resources.apk";
    protected static final String c = "res";
    private static final String d = "Tinker.ResourceLoader";
    private static ShareResPatchInfo e = new ShareResPatchInfo();

    private TinkerResourceLoader() {
    }

    public static boolean a(TinkerApplication tinkerApplication, String str, Intent intent) {
        if (e == null || e.b == null) {
            return true;
        }
        String str2 = str + "/" + "res" + "/" + "resources.apk";
        File file = new File(str2);
        long currentTimeMillis = System.currentTimeMillis();
        if (tinkerApplication.isTinkerLoadVerifyFlag()) {
            if (!SharePatchFileUtil.c(file, e.b)) {
                Log.e(d, "Failed to load resource file, path: " + file.getPath() + ", expect md5: " + e.b);
                ShareIntentUtil.a(intent, -24);
                return false;
            }
            Log.i(d, "verify resource file:" + file.getPath() + " md5, use time: " + (System.currentTimeMillis() - currentTimeMillis));
        }
        try {
            TinkerResourcePatcher.a(tinkerApplication, str2);
            Log.i(d, "monkeyPatchExistingResources resource file:" + str2 + ", use time: " + (System.currentTimeMillis() - currentTimeMillis));
            return true;
        } catch (Throwable unused) {
            Log.e(d, "uninstallPatchDex failed", th);
        }
        intent.putExtra(ShareIntentUtil.l, th);
        ShareIntentUtil.a(intent, -23);
        return false;
    }

    public static boolean a(Context context, String str, ShareSecurityCheck shareSecurityCheck, Intent intent) {
        String str2 = shareSecurityCheck.a().get("assets/res_meta.txt");
        if (str2 == null) {
            return true;
        }
        ShareResPatchInfo.b(str2, e);
        if (e.b == null) {
            return true;
        }
        if (!ShareResPatchInfo.a(e)) {
            intent.putExtra(ShareIntentUtil.m, -8);
            ShareIntentUtil.a(intent, -8);
            return false;
        }
        String str3 = str + "/" + "res" + "/";
        File file = new File(str3);
        if (!file.exists() || !file.isDirectory()) {
            ShareIntentUtil.a(intent, -21);
            return false;
        }
        if (!SharePatchFileUtil.a(new File(str3 + "resources.apk"))) {
            ShareIntentUtil.a(intent, -22);
            return false;
        }
        try {
            TinkerResourcePatcher.a(context);
            return true;
        } catch (Throwable th) {
            Log.e(d, "resource hook check failed.", th);
            intent.putExtra(ShareIntentUtil.l, th);
            ShareIntentUtil.a(intent, -23);
            return false;
        }
    }
}
