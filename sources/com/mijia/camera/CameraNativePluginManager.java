package com.mijia.camera;

import com.xiaomi.smarthome.plugin.DeviceConstant;

public class CameraNativePluginManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7860a = "is_v4";

    private static final class InstanceHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final CameraNativePluginManager f7861a = new CameraNativePluginManager();

        private InstanceHolder() {
        }
    }

    private CameraNativePluginManager() {
    }

    public static CameraNativePluginManager a() {
        return InstanceHolder.f7861a;
    }

    public boolean a(String str) {
        return "mijia.camera.v3".equals(str) || "chuangmi.camera.ipc009".equals(str) || "chuangmi.camera.ipc019".equals(str) || DeviceConstant.CHUANGMI_CAMERA_021.equals(str) || DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equals(str);
    }

    public int b(String str) {
        return ("chuangmi.camera.ipc009".equals(str) || "chuangmi.camera.ipc019".equals(str) || DeviceConstant.CHUANGMI_CAMERA_021.equals(str) || DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equals(str)) ? 0 : 1;
    }
}
