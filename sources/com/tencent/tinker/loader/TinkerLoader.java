package com.tencent.tinker.loader;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.hotplug.ComponentHotplug;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;

public class TinkerLoader extends AbstractTinkerLoader {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1354a = "Tinker.TinkerLoader";
    private SharePatchInfo b;

    public Intent a(TinkerApplication tinkerApplication) {
        Log.d(f1354a, "tryLoad test test");
        Intent intent = new Intent();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        a(tinkerApplication, intent);
        ShareIntentUtil.a(intent, SystemClock.elapsedRealtime() - elapsedRealtime);
        return intent;
    }

    private void a(TinkerApplication tinkerApplication, Intent intent) {
        ShareSecurityCheck shareSecurityCheck;
        boolean z;
        File file;
        File file2;
        TinkerApplication tinkerApplication2 = tinkerApplication;
        Intent intent2 = intent;
        int tinkerFlags = tinkerApplication.getTinkerFlags();
        if (!ShareTinkerInternals.f(tinkerFlags)) {
            Log.w(f1354a, "tryLoadPatchFiles: tinker is disable, just return");
            ShareIntentUtil.a(intent2, -1);
        } else if (ShareTinkerInternals.f((Context) tinkerApplication)) {
            Log.w(f1354a, "tryLoadPatchFiles: we don't load patch with :patch process itself, just return");
            ShareIntentUtil.a(intent2, -1);
        } else {
            File a2 = SharePatchFileUtil.a((Context) tinkerApplication);
            if (a2 == null) {
                Log.w(f1354a, "tryLoadPatchFiles:getPatchDirectory == null");
                ShareIntentUtil.a(intent2, -2);
                return;
            }
            String absolutePath = a2.getAbsolutePath();
            if (!a2.exists()) {
                Log.w(f1354a, "tryLoadPatchFiles:patch dir not exist:" + absolutePath);
                ShareIntentUtil.a(intent2, -2);
                return;
            }
            File a3 = SharePatchFileUtil.a(absolutePath);
            if (!a3.exists()) {
                Log.w(f1354a, "tryLoadPatchFiles:patch info not exist:" + a3.getAbsolutePath());
                ShareIntentUtil.a(intent2, -3);
                return;
            }
            File b2 = SharePatchFileUtil.b(absolutePath);
            this.b = SharePatchInfo.a(a3, b2);
            if (this.b == null) {
                ShareIntentUtil.a(intent2, -4);
                return;
            }
            boolean z2 = this.b.k;
            intent2.putExtra(ShareIntentUtil.d, z2);
            String str = this.b.i;
            String str2 = this.b.j;
            String str3 = this.b.n;
            if (str == null || str2 == null || str3 == null) {
                Log.w(f1354a, "tryLoadPatchFiles:onPatchInfoCorrupted");
                ShareIntentUtil.a(intent2, -4);
                return;
            }
            boolean e = ShareTinkerInternals.e((Context) tinkerApplication);
            boolean z3 = this.b.l;
            if (e && z3) {
                Log.w(f1354a, "found clean patch mark and we are in main process, delete patch file now.");
                String c = SharePatchFileUtil.c(str2);
                if (c != null) {
                    SharePatchFileUtil.f(absolutePath + "/" + c);
                    if (str.equals(str2)) {
                        str = "";
                    }
                    this.b.i = str;
                    this.b.j = str;
                    this.b.l = false;
                    SharePatchInfo.a(a3, this.b, b2);
                    ShareTinkerInternals.h(tinkerApplication);
                    ShareIntentUtil.a(intent2, -2);
                    return;
                }
            }
            intent2.putExtra(ShareIntentUtil.b, str);
            intent2.putExtra(ShareIntentUtil.c, str2);
            boolean z4 = !str.equals(str2);
            boolean equals = str3.equals(ShareConstants.v);
            String a4 = ShareTinkerInternals.a((Context) tinkerApplication2, str3);
            intent2.putExtra(ShareIntentUtil.p, a4);
            if (z4 && e) {
                str = str2;
            }
            if (ShareTinkerInternals.b(str)) {
                Log.w(f1354a, "tryLoadPatchFiles:version is blank, wait main process to restart");
                ShareIntentUtil.a(intent2, -5);
                return;
            }
            String c2 = SharePatchFileUtil.c(str);
            if (c2 == null) {
                Log.w(f1354a, "tryLoadPatchFiles:patchName is null");
                ShareIntentUtil.a(intent2, -6);
                return;
            }
            String str4 = absolutePath + "/" + c2;
            File file3 = new File(str4);
            if (!file3.exists()) {
                Log.w(f1354a, "tryLoadPatchFiles:onPatchVersionDirectoryNotFound");
                ShareIntentUtil.a(intent2, -6);
                return;
            }
            String d = SharePatchFileUtil.d(str);
            File file4 = d != null ? new File(file3.getAbsolutePath(), d) : null;
            if (!SharePatchFileUtil.a(file4)) {
                Log.w(f1354a, "tryLoadPatchFiles:onPatchVersionFileNotFound");
                ShareIntentUtil.a(intent2, -7);
                return;
            }
            ShareSecurityCheck shareSecurityCheck2 = new ShareSecurityCheck(tinkerApplication2);
            int a5 = ShareTinkerInternals.a(tinkerApplication2, tinkerFlags, file4, shareSecurityCheck2);
            if (a5 != 0) {
                Log.w(f1354a, "tryLoadPatchFiles:checkTinkerPackage");
                intent2.putExtra(ShareIntentUtil.m, a5);
                ShareIntentUtil.a(intent2, -8);
                return;
            }
            intent2.putExtra(ShareIntentUtil.n, shareSecurityCheck2.b());
            boolean a6 = ShareTinkerInternals.a(tinkerFlags);
            boolean c3 = ShareTinkerInternals.c();
            if (c3 || !a6 || TinkerDexLoader.a(str4, shareSecurityCheck2, a4, intent2)) {
                boolean d2 = ShareTinkerInternals.d(tinkerFlags);
                if (c3 && d2 && !TinkerArkHotLoader.a(str4, shareSecurityCheck2, intent2)) {
                    Log.w(f1354a, "tryLoadPatchFiles:dex check fail");
                } else if (!ShareTinkerInternals.b(tinkerFlags) || TinkerSoLoader.a(str4, shareSecurityCheck2, intent2)) {
                    boolean c4 = ShareTinkerInternals.c(tinkerFlags);
                    File file5 = a3;
                    StringBuilder sb = new StringBuilder();
                    File file6 = b2;
                    sb.append("tryLoadPatchFiles:isEnabledForResource:");
                    sb.append(c4);
                    Log.w(f1354a, sb.toString());
                    if (!c4 || TinkerResourceLoader.a(tinkerApplication2, str4, shareSecurityCheck2, intent2)) {
                        boolean z5 = ShareTinkerInternals.a() && ShareTinkerInternals.a(this.b.m) && Build.VERSION.SDK_INT >= 21 && !ShareTinkerInternals.d();
                        intent2.putExtra(ShareIntentUtil.o, z5);
                        if (e) {
                            if (z4) {
                                this.b.i = str;
                            }
                            if (equals) {
                                this.b.n = a4;
                                Log.i(f1354a, "tryLoadPatchFiles:oatModeChanged, try to delete interpret optimize files");
                                SharePatchFileUtil.f(str4 + "/" + ShareConstants.u);
                            }
                        }
                        if (!b(tinkerApplication)) {
                            intent2.putExtra(ShareIntentUtil.l, new TinkerRuntimeException("checkSafeModeCount fail"));
                            ShareIntentUtil.a(intent2, -25);
                            Log.w(f1354a, "tryLoadPatchFiles:checkSafeModeCount fail");
                            return;
                        }
                        if (c3 || !a6) {
                            z = c4;
                            shareSecurityCheck = shareSecurityCheck2;
                            file2 = file5;
                            file = file6;
                        } else {
                            String str5 = a4;
                            z = c4;
                            shareSecurityCheck = shareSecurityCheck2;
                            boolean a7 = TinkerDexLoader.a(tinkerApplication, str4, str5, intent, z5, z2);
                            if (z5) {
                                this.b.m = Build.FINGERPRINT;
                                this.b.n = a7 ? ShareConstants.u : "odex";
                                file2 = file5;
                                file = file6;
                                if (!SharePatchInfo.a(file2, this.b, file)) {
                                    ShareIntentUtil.a(intent2, -19);
                                    Log.w(f1354a, "tryLoadPatchFiles:onReWritePatchInfoCorrupted");
                                    return;
                                }
                                intent2.putExtra(ShareIntentUtil.p, this.b.n);
                            } else {
                                file2 = file5;
                                file = file6;
                            }
                            if (!a7) {
                                Log.w(f1354a, "tryLoadPatchFiles:onPatchLoadDexesFail");
                                return;
                            }
                        }
                        if (c3 && d2 && !TinkerArkHotLoader.a(tinkerApplication2, str4, intent2)) {
                            Log.w(f1354a, "tryLoadPatchFiles:onPatchLoadArkApkFail");
                        } else if (!z || TinkerResourceLoader.a(tinkerApplication2, str4, intent2)) {
                            if ((a6 || d2) && z) {
                                ComponentHotplug.a(tinkerApplication2, shareSecurityCheck);
                            }
                            if (e && z4) {
                                if (!SharePatchInfo.a(file2, this.b, file)) {
                                    ShareIntentUtil.a(intent2, -19);
                                    Log.w(f1354a, "tryLoadPatchFiles:onReWritePatchInfoCorrupted");
                                    return;
                                }
                                ShareTinkerInternals.h(tinkerApplication);
                            }
                            ShareIntentUtil.a(intent2, 0);
                            Log.i(f1354a, "tryLoadPatchFiles: load end, ok!");
                        } else {
                            Log.w(f1354a, "tryLoadPatchFiles:onPatchLoadResourcesFail");
                        }
                    } else {
                        Log.w(f1354a, "tryLoadPatchFiles:resource check fail");
                    }
                } else {
                    Log.w(f1354a, "tryLoadPatchFiles:native lib check fail");
                }
            } else {
                Log.w(f1354a, "tryLoadPatchFiles:dex check fail");
            }
        }
    }

    private boolean b(TinkerApplication tinkerApplication) {
        int d = ShareTinkerInternals.d((Context) tinkerApplication);
        if (d >= 2) {
            ShareTinkerInternals.a((Context) tinkerApplication, 0);
            return false;
        }
        tinkerApplication.setUseSafeMode(true);
        ShareTinkerInternals.a((Context) tinkerApplication, d + 1);
        return true;
    }
}
