package com.xiaomi.push;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

public class dq implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a  reason: collision with root package name */
    private String f12699a = "";
    private String b;
    private Context c;

    public dq(Context context, String str) {
        this.c = context;
        this.f12699a = str;
    }

    private void a(String str) {
        hw hwVar = new hw();
        hwVar.a(str);
        hwVar.a(System.currentTimeMillis());
        hwVar.a(hq.ActivityActiveTimeStamp);
        ef.a(this.c, hwVar);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
        String localClassName = activity.getLocalClassName();
        if (!TextUtils.isEmpty(this.f12699a) && !TextUtils.isEmpty(localClassName)) {
            this.b = "";
            if (TextUtils.isEmpty(this.b) || TextUtils.equals(this.b, localClassName)) {
                a(this.c.getPackageName() + "|" + localClassName + ":" + this.f12699a + "," + String.valueOf(System.currentTimeMillis() / 1000));
                this.f12699a = "";
                this.b = "";
                return;
            }
            this.f12699a = "";
        }
    }

    public void onActivityResumed(Activity activity) {
        if (TextUtils.isEmpty(this.b)) {
            this.b = activity.getLocalClassName();
        }
        this.f12699a = String.valueOf(System.currentTimeMillis() / 1000);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }
}
