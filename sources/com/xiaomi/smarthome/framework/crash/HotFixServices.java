package com.xiaomi.smarthome.framework.crash;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.HotFixManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;

public class HotFixServices extends Service {
    Handler mMainHandler = new Handler();

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        this.mMainHandler.postDelayed(new Runnable() {
            public void run() {
                HotFixServices.this.updatePatch();
            }
        }, 3000);
        return 2;
    }

    public void updatePatch() {
        CoreApi.a().a(CommonApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                HotFixManager.a(CommonApplication.getAppContext(), (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        Log.d("HotFixServices", "hotfix suscess");
                        HotFixServices.this.existServices();
                    }

                    public void onFailure(Error error) {
                        Log.d("HotFixServices", "hotfix failure:" + error.b());
                        HotFixServices.this.existServices();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void existServices() {
        this.mMainHandler.post(new Runnable() {
            public void run() {
                Log.d("HotFixServices", "stopSelf");
                HotFixServices.this.stopSelf();
                System.exit(0);
            }
        });
    }
}
