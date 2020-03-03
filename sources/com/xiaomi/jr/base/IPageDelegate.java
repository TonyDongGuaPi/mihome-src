package com.xiaomi.jr.base;

import android.support.v4.app.Fragment;
import com.xiaomi.jr.pagereload.pagereload.IPageReloader;

public interface IPageDelegate {

    /* renamed from: com.xiaomi.jr.base.IPageDelegate$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$a(IPageDelegate iPageDelegate, boolean z) {
        }

        public static boolean $default$a(IPageDelegate iPageDelegate) {
            return false;
        }

        public static void $default$reload(IPageDelegate iPageDelegate, Fragment fragment) {
        }
    }

    void a(boolean z);

    boolean a();

    IAppDelegate getAppDelegate();

    IPageReloader getPageReloader();

    void reload(Fragment fragment);
}
