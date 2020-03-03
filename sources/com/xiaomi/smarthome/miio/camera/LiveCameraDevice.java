package com.xiaomi.smarthome.miio.camera;

import com.xiaomi.smarthome.device.Device;
import org.json.JSONObject;

public class LiveCameraDevice extends Device {
    public static String LIVE_CAMERA_MODEL = "yunyi.camera.v1";
    public String mAddressDes;
    public String mDevDes;
    public String mThumbNailUrl;
    public String mVideoPlayUrl;

    public static LiveCameraDevice parse(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        LiveCameraDevice liveCameraDevice = new LiveCameraDevice();
        liveCameraDevice.model = LIVE_CAMERA_MODEL;
        liveCameraDevice.did = jSONObject.optString("dev_uid");
        liveCameraDevice.name = jSONObject.optString("dev_name");
        liveCameraDevice.latitude = jSONObject.optDouble("dev_latitude");
        liveCameraDevice.longitude = jSONObject.optDouble("dev_longtude");
        liveCameraDevice.mThumbNailUrl = jSONObject.optString("video_thumbnail_url");
        liveCameraDevice.mAddressDes = jSONObject.optString("dev_location");
        liveCameraDevice.mVideoPlayUrl = jSONObject.optString("video_play_url");
        liveCameraDevice.mDevDes = jSONObject.optString("dev_desc");
        return liveCameraDevice;
    }
}
