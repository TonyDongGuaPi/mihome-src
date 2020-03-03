package com.xiaomi.smarthome.app.startup;

import android.content.Context;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingConst;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;

public class CTAHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f13727a = "action_disclaim_local_broadcast_complete";
    public static final String b = "param_key";
    public static final int c = 1;
    public static final int d = 2;

    public interface DisclaimCallback {
        void a();

        void b();
    }

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        return SharePrefsManager.b(context.getSharedPreferences(GlobalDynamicSettingManager.b, 0), GlobalDynamicSettingConst.b, true);
    }

    public static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        return SharePrefsManager.b(context.getSharedPreferences(GlobalDynamicSettingManager.b, 0), GlobalDynamicSettingConst.c, false);
    }
}
