package com.xiaomi.smarthome.framework.page;

import android.app.Activity;
import com.xiaomi.smarthome.FrescoInitial;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.baseui.PageHostApi;
import com.xiaomi.smarthome.frame.process.ProcessUtil;

public class PageHostApiImpl extends PageHostApi {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f1540a = new Object();
    private static PageHostApiImpl b;

    private PageHostApiImpl() {
    }

    public static PageHostApiImpl a() {
        if (b == null) {
            synchronized (f1540a) {
                if (b == null) {
                    b = new PageHostApiImpl();
                }
            }
        }
        return b;
    }

    public void a(Activity activity) {
        if (ProcessUtil.b(SHApplication.getAppContext())) {
            FrescoInitial.a(false);
        }
    }

    public void b(Activity activity) {
        if (ProcessUtil.b(SHApplication.getAppContext())) {
            FrescoInitial.a();
        }
    }
}
