package com.tencent.tinker.loader;

import android.content.Context;
import android.os.Process;
import android.util.Log;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread;

public class TinkerUncaughtHandler implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1355a = "Tinker.UncaughtHandler";
    private final File b;
    private final Context c;
    private final Thread.UncaughtExceptionHandler d = Thread.getDefaultUncaughtExceptionHandler();

    public TinkerUncaughtHandler(Context context) {
        this.c = context;
        this.b = SharePatchFileUtil.c(context);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        PrintWriter printWriter;
        IOException e;
        Log.e(f1355a, "TinkerUncaughtHandler catch exception:" + Log.getStackTraceString(th));
        this.d.uncaughtException(thread, th);
        if (this.b != null && (Thread.getDefaultUncaughtExceptionHandler() instanceof TinkerUncaughtHandler)) {
            File parentFile = this.b.getParentFile();
            if (parentFile.exists() || parentFile.mkdirs()) {
                try {
                    printWriter = new PrintWriter(new FileWriter(this.b, false));
                    try {
                        printWriter.println("process:" + ShareTinkerInternals.i(this.c));
                        printWriter.println(ShareTinkerInternals.a(th));
                    } catch (IOException e2) {
                        e = e2;
                    }
                } catch (IOException e3) {
                    printWriter = null;
                    e = e3;
                    try {
                        Log.e(f1355a, "print crash file error:" + Log.getStackTraceString(e));
                        SharePatchFileUtil.a((Object) printWriter);
                        Process.killProcess(Process.myPid());
                        return;
                    } catch (Throwable th2) {
                        th = th2;
                        SharePatchFileUtil.a((Object) printWriter);
                        throw th;
                    }
                } catch (Throwable th3) {
                    printWriter = null;
                    th = th3;
                    SharePatchFileUtil.a((Object) printWriter);
                    throw th;
                }
                SharePatchFileUtil.a((Object) printWriter);
                Process.killProcess(Process.myPid());
                return;
            }
            Log.e(f1355a, "print crash file error: create directory fail!");
        }
    }
}
