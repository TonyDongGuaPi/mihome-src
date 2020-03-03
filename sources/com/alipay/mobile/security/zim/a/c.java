package com.alipay.mobile.security.zim.a;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.zim.api.ZIMFacade;
import com.taobao.weex.el.parse.Operators;

public class c implements ComponentCallbacks2 {
    private static c b;

    /* renamed from: a  reason: collision with root package name */
    protected Context f1045a;

    private c(Context context) {
        this.f1045a = context.getApplicationContext();
    }

    public static c a(Application application) {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    c cVar = new c(application);
                    BioLog.w(ZIMFacade.TAG, "application.registerComponentCallbacks(ZimComponentCallbacks)");
                    application.registerComponentCallbacks(cVar);
                    b = cVar;
                }
            }
        }
        return b;
    }

    public void onTrimMemory(int i) {
        BioLog.d(ZIMFacade.TAG, "onTrimMemory(level=" + i + Operators.BRACKET_END_STR);
    }

    public void onConfigurationChanged(Configuration configuration) {
        BioLog.d(ZIMFacade.TAG, "onConfigurationChanged(newConfig=" + configuration + Operators.BRACKET_END_STR);
    }

    public void onLowMemory() {
        BioLog.d(ZIMFacade.TAG, "onLowMemory()");
    }
}
