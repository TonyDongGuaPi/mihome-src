package com.xiaomi.pluginhost;

import android.content.Context;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.xiaomi.plugin.PackageRawInfo;
import com.xiaomi.plugin.XmPluginPackage;
import java.io.File;

public class PluginSettings {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f12601a = true;
    public static final int b = 1;
    public static final long c = 1;
    public static final long d = 100;
    public static IExceptionHandler e;
    public static Context f;

    public interface IExceptionHandler {
        void a(XmPluginPackage xmPluginPackage, Throwable th);
    }

    public static boolean a(long j) {
        return j > 0;
    }

    public static boolean b(long j) {
        return j > 0;
    }

    public static boolean c(long j) {
        return 1 <= j && j <= 100;
    }

    public static boolean d(long j) {
        return j > 0;
    }

    public static String a(Context context) {
        File dir = context.getDir(ShareConstants.q, 0);
        return dir.getAbsolutePath() + File.separator + "plugin";
    }

    public static String a(Context context, PackageRawInfo packageRawInfo) {
        File dir = context.getDir(ShareConstants.q, 0);
        return dir.getAbsolutePath() + File.separator + "plugin" + File.separator + packageRawInfo.mPackageName + File.separator + packageRawInfo.mVersion;
    }

    public static String b(Context context) {
        return context.getFilesDir().getPath() + File.separator + "plugin" + File.separator + "install" + File.separator + "libs";
    }

    public static String c(Context context) {
        return context.getFilesDir().getPath() + File.separator + "plugin" + File.separator + "install" + File.separator + ShareConstants.o;
    }

    public static String a(Context context, long j, long j2) {
        return c(context) + File.separator + j + File.separator + j2;
    }
}
