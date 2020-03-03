package com.mipay.core.internal;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;

public class ApplicationWrapper extends Application {
    public ApplicationWrapper(Application application) {
        attachBaseContext(application);
    }

    public Context getApplicationContext() {
        Context applicationContext = super.getApplicationContext();
        return applicationContext == null ? this : applicationContext;
    }

    public void registerComponentCallbacks(ComponentCallbacks componentCallbacks) {
        ((Application) getBaseContext()).registerComponentCallbacks(componentCallbacks);
    }

    public void unregisterComponentCallbacks(ComponentCallbacks componentCallbacks) {
        ((Application) getBaseContext()).unregisterComponentCallbacks(componentCallbacks);
    }

    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        ((Application) getBaseContext()).registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        ((Application) getBaseContext()).unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public void registerOnProvideAssistDataListener(Application.OnProvideAssistDataListener onProvideAssistDataListener) {
        ((Application) getBaseContext()).registerOnProvideAssistDataListener(onProvideAssistDataListener);
    }

    public void unregisterOnProvideAssistDataListener(Application.OnProvideAssistDataListener onProvideAssistDataListener) {
        ((Application) getBaseContext()).unregisterOnProvideAssistDataListener(onProvideAssistDataListener);
    }
}
