package com.xiaomi.loan.sdk;

import android.app.Application;
import android.content.Context;
import com.xiaomi.jr.appbase.app.MiFiAppController;
import com.xiaomi.jr.appbase.app.MiFiAppControllerImpl;
import com.xiaomi.jr.appbase.app.MiFiAppDelegate;
import com.xiaomi.jr.appbase.app.MiFiAppLifecycle;
import com.xiaomi.jr.appbase.app.MiFiAppLifecycleImpl;

public class MiFiApplication extends Application {
    private Application mApplication;

    public MiFiApplication() {
        this.mApplication = this;
    }

    public MiFiApplication(Application application) {
        this.mApplication = application;
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        if (MiFiAppLifecycle.a() == null) {
            MiFiAppLifecycle.a(new MiFiAppLifecycleImpl(this.mApplication));
        }
        MiFiAppLifecycle.a().a();
    }

    public void onCreate() {
        super.onCreate();
        MiFiAppDelegate.a((Context) this.mApplication);
        MiFiAppController.a(new MiFiAppControllerImpl(this.mApplication));
        MiFiAppLifecycle.a().c();
        MiFiDeeplinkConfig.a((Context) this.mApplication);
        MiFiAppLifecycle.a().b();
    }

    public void onTerminate() {
        MiFiAppLifecycle.a().e();
        super.onTerminate();
    }
}
