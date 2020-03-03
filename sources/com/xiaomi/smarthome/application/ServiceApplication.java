package com.xiaomi.smarthome.application;

import android.content.Context;
import android.os.Handler;
import com.xiaomi.smarthome.AppStateNotifier;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;

public abstract class ServiceApplication extends CommonApplication {
    protected static AppStateNotifier sNotifier;
    MessageHandlerThread sGlobalWorkerThread;

    public static Handler getGlobalWorkerHandler() {
        return sGlobalWorkerHandler;
    }

    public void onCreate() {
        super.onCreate();
        if (this.sGlobalWorkerThread == null) {
            this.sGlobalWorkerThread = new MessageHandlerThread("GlobalWorker");
            this.sGlobalWorkerThread.start();
            sGlobalWorkerHandler = new Handler(this.sGlobalWorkerThread.getLooper());
        }
    }

    public static Context getAppContext() {
        return (Context) sInstance.a();
    }

    public static AppStateNotifier getStateNotifier() {
        if (sNotifier == null) {
            synchronized (ServiceApplication.class) {
                if (sNotifier == null) {
                    sNotifier = new AppStateNotifier();
                }
            }
        }
        return sNotifier;
    }
}
