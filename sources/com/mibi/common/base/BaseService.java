package com.mibi.common.base;

import android.content.Intent;
import android.os.IBinder;
import com.mipay.core.runtime.BundleService;

public class BaseService extends BundleService {

    /* renamed from: a  reason: collision with root package name */
    private final TaskHolder f7454a = new TaskHolder();

    public IBinder onBind(Intent intent) {
        return null;
    }

    public TaskManager getTaskManager() {
        return this.f7454a;
    }

    public void onCreate() {
        super.onCreate();
        this.f7454a.c();
    }

    public void onDestroy() {
        super.onDestroy();
        this.f7454a.g();
    }
}
