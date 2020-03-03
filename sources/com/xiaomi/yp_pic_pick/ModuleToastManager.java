package com.xiaomi.yp_pic_pick;

import android.app.Activity;
import android.content.Context;
import com.xiaomiyoupin.toast.YPDToast;

public class ModuleToastManager {

    private static class Holder {

        /* renamed from: a  reason: collision with root package name */
        public static final ModuleToastManager f19486a = new ModuleToastManager();

        private Holder() {
        }
    }

    public static ModuleToastManager a() {
        return Holder.f19486a;
    }

    public void a(int i) {
        YPDToast.getInstance().dismiss(i);
    }

    public int a(Activity activity, String str) {
        return YPDToast.getInstance().toast((Context) activity, str);
    }

    public int b(Activity activity, String str) {
        return YPDToast.getInstance().toast(activity, str, 2);
    }
}
