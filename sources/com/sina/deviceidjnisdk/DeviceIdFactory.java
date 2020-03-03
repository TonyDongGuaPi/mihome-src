package com.sina.deviceidjnisdk;

import android.content.Context;
import android.text.TextUtils;

public class DeviceIdFactory {

    /* renamed from: a  reason: collision with root package name */
    private static volatile IDeviceId f8815a;

    public static native String calculateM(Context context, String str, String str2);

    private static native String getIValueNative(Context context, String str);

    private static native IDeviceId getInstanceNative(Context context, int i);

    static {
        System.loadLibrary("weibosdkcore");
    }

    private DeviceIdFactory() {
    }

    public static synchronized IDeviceId a(Context context) {
        IDeviceId iDeviceId;
        synchronized (DeviceIdFactory.class) {
            if (f8815a == null) {
                f8815a = new DeviceId(context);
            }
            iDeviceId = f8815a;
        }
        return iDeviceId;
    }

    public static synchronized String b(Context context) {
        synchronized (DeviceIdFactory.class) {
            try {
                String a2 = DeviceInfo.a(context);
                if (TextUtils.isEmpty(a2)) {
                    a2 = DeviceInfo.b(context);
                }
                if (TextUtils.isEmpty(a2)) {
                    a2 = "000000000000000";
                }
                if (!TextUtils.isEmpty(a2)) {
                    String iValueNative = getIValueNative(context, a2);
                    return iValueNative;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
