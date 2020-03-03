package com.xiaomi.smarthome.library.common.util;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import com.xiaomi.smarthome.library.common.ThreadPool;

@SuppressLint({"NewApi"})
public abstract class AsyncTaskUtils {
    public static <Params, Progress, Result> void a(AsyncTask<Params, Progress, Result> asyncTask, Params... paramsArr) {
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTask.executeOnExecutor(ThreadPool.f18561a, paramsArr);
        } else {
            asyncTask.execute(paramsArr);
        }
    }
}
