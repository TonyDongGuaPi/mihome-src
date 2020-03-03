package com.alipay.zoloz.toyger.util;

import android.os.Build;
import com.alipay.android.phone.a.a.a;
import com.alipay.mobile.security.bio.utils.BioLog;
import java.lang.reflect.Field;

public class EnvCheck {
    private static final int ANDROID_VERSION_4_3 = 18;

    private boolean isLowOS() {
        return Build.VERSION.SDK != null && Integer.parseInt(Build.VERSION.SDK) < 18;
    }

    public EnvErrorType check() {
        EnvErrorType envErrorType = EnvErrorType.ENV_ERROR_INVALID;
        if (isLowOS()) {
            return EnvErrorType.ENV_ERROR_LOW_OS;
        }
        if (!a.f813a.equals(getCamera())) {
            return envErrorType;
        }
        if (!"armeabi-v7a".equals(Build.CPU_ABI)) {
            return EnvErrorType.ENV_ERROR_UNSUPPORTED_CPU;
        }
        return com.alipay.zoloz.hardware.camera.a.a.b() == -1 ? EnvErrorType.ENV_ERROR_NO_FRONT_CAMERA : envErrorType;
    }

    public static String getCamera() {
        try {
            Field field = a.class.getField("a");
            field.setAccessible(true);
            return (String) field.get(a.class);
        } catch (NoSuchFieldException e) {
            BioLog.w((Throwable) e);
            return a.f813a;
        } catch (IllegalAccessException e2) {
            BioLog.w((Throwable) e2);
            return a.f813a;
        }
    }
}
