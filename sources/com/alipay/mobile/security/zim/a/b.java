package com.alipay.mobile.security.zim.a;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.zim.api.ZIMFacade;
import com.taobao.weex.el.parse.Operators;

public class b implements Application.ActivityLifecycleCallbacks {
    private static b b;

    /* renamed from: a  reason: collision with root package name */
    protected Context f1044a;

    private b(Context context) {
        this.f1044a = context.getApplicationContext();
    }

    public static b a(Application application) {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    b bVar = new b(application);
                    BioLog.w(ZIMFacade.TAG, "application.registerActivityLifecycleCallbacks(ZimActivityLifecycleCallbacks)");
                    application.registerActivityLifecycleCallbacks(bVar);
                    b = bVar;
                }
            }
        }
        return b;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        BioLog.d(ZIMFacade.TAG, activity.getClass().getSimpleName() + ".onActivityCreated(activity=" + activity + ", savedInstanceState=" + bundle + Operators.BRACKET_END_STR);
    }

    public void onActivityStarted(Activity activity) {
        BioLog.d(ZIMFacade.TAG, activity.getClass().getSimpleName() + ".onActivityStarted(activity=" + activity + Operators.BRACKET_END_STR);
    }

    public void onActivityResumed(Activity activity) {
        BioLog.d(ZIMFacade.TAG, activity.getClass().getSimpleName() + ".onActivityResumed(activity=" + activity + Operators.BRACKET_END_STR);
    }

    public void onActivityPaused(Activity activity) {
        BioLog.d(ZIMFacade.TAG, activity.getClass().getSimpleName() + ".onActivityPaused(activity=" + activity + Operators.BRACKET_END_STR);
    }

    public void onActivityStopped(Activity activity) {
        BioLog.d(ZIMFacade.TAG, activity.getClass().getSimpleName() + ".onActivityStopped(activity=" + activity + Operators.BRACKET_END_STR);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        BioLog.d(ZIMFacade.TAG, activity.getClass().getSimpleName() + ".onActivitySaveInstanceState(activity=" + activity + ", outState=" + bundle + Operators.BRACKET_END_STR);
    }

    public void onActivityDestroyed(Activity activity) {
        BioLog.d(ZIMFacade.TAG, activity.getClass().getSimpleName() + ".onActivityDestroyed(activity=" + activity + Operators.BRACKET_END_STR);
    }
}
