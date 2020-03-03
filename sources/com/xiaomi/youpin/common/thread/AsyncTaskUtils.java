package com.xiaomi.youpin.common.thread;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

@SuppressLint({"NewApi"})
public abstract class AsyncTaskUtils {
    public static <Params, Progress, Result> void a(AsyncTask<Params, Progress, Result> asyncTask, Params... paramsArr) {
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTask.executeOnExecutor(ThreadPool.f23245a, paramsArr);
        } else {
            asyncTask.execute(paramsArr);
        }
    }
}
