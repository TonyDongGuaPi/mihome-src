package com.xiaomi.smarthome.miio.camera.match;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import java.util.HashMap;

public class CameraInfoManager {
    public static final String P2P_STATUS_REFRESH_BROADCAST = "p2p_status_refresh_broadcast";
    static final String UPDATE_URL_V1 = "http://115.29.106.70/vmanager/upgrade?version=%s&uid=%s&sname=familymonitor";
    static final String UPDATE_URL_V2 = "http://115.29.106.70/vmanager/upgrade?version=%s&uid=%s&sname=familymonitor-p2p";
    private static CameraInfoManager __INSTANCE__;
    HashMap<String, CameraInfo> cameraInfoMap = new HashMap<>();

    private CameraInfoManager() {
    }

    public static synchronized CameraInfoManager instance() {
        CameraInfoManager cameraInfoManager;
        synchronized (CameraInfoManager.class) {
            if (__INSTANCE__ == null) {
                __INSTANCE__ = new CameraInfoManager();
            }
            cameraInfoManager = __INSTANCE__;
        }
        return cameraInfoManager;
    }

    public CameraInfo getCameraInfo(CameraDevice cameraDevice) {
        String str = cameraDevice.did;
        CameraInfo cameraInfo = this.cameraInfoMap.get(str);
        if (cameraInfo != null) {
            return cameraInfo;
        }
        CameraInfo cameraInfo2 = new CameraInfo(str);
        this.cameraInfoMap.put(str, cameraInfo2);
        return cameraInfo2;
    }

    public static void broadcastStatusChanged() {
        Intent intent = new Intent(P2P_STATUS_REFRESH_BROADCAST);
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(XmPluginHostApi.instance().context());
        if (instance != null) {
            instance.sendBroadcast(intent);
        }
    }
}
