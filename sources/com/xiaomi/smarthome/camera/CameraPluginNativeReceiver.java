package com.xiaomi.smarthome.camera;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.alipay.mobile.common.logging.api.ProcessInfo;
import com.google.android.exoplayer2.C;
import com.mijia.camera.MijiaCameraDevice;
import com.mijia.camera.lite.CameraFrameManager;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.camera.activity.CameraPlayer2ExActivity;
import com.xiaomi.smarthome.camera.activity.CameraV3UpgradePlayerActivity;
import com.xiaomi.smarthome.camera.activity.alarm2.AlarmVideoNormalPlayerActivity;
import com.xiaomi.smarthome.camera.activity.alarm2.AlarmVideoPlayerActivity;
import com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerActivity;
import com.xiaomi.smarthome.camera.activity.nas.NASInfoActivity;
import com.xiaomi.smarthome.camera.v4.activity.CameraPlayerActivity;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.plugin.DeviceConstant;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraPluginNativeReceiver extends BroadcastReceiver {
    public static final String ACTION_DESTROY_REQUEST_CAMERA_FRAME = "com.xiaomi.smarthome.camera.DESTROY_REQUEST_CAMERA_FRAME";
    public static final String ACTION_INIT_CAMERA_FRAME_SENDER = "com.xiaomi.smarthome.camera.INIT_CAMERA_FRAME_SENDER";
    public static final String ACTION_LAUNCH = "com.xiaomi.smarthome.camera.LANUCH";
    public static final String ACTION_PUSH_MSG = "com.xiaomi.smarthome.camera.PUSH_MSG";
    public static final String ACTION_STAR_REQUEST_CAMERA_FRAME = "com.xiaomi.smarthome.camera.STAR_REQUEST_CAMERA_FRAME";
    public static final String ACTION_STOP_REQUEST_CAMERA_FRAME = "com.xiaomi.smarthome.camera.STOP_REQUEST_CAMERA_FRAME";
    private static final String TAG = "CameraPluginNativeReceiver";

    public void onReceive(Context context, Intent intent) {
        intent.getStringExtra("extra_device_did");
        String stringExtra = intent.getStringExtra("extra_device_model");
        if ("mijia.camera.v3".equals(stringExtra)) {
            handleV3Message(context, intent);
        } else if ("chuangmi.camera.ipc009".equals(stringExtra) || "chuangmi.camera.ipc019".equals(stringExtra) || DeviceConstant.CHUANGMI_CAMERA_021.equals(stringExtra)) {
            handleV4Message(context, intent, stringExtra);
        } else if (DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equals(stringExtra)) {
            handleV3UpdateMessage(context, intent);
        }
    }

    private void handleV3Message(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("extra_device_did");
        DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(stringExtra);
        if (deviceByDid != null) {
            String action = intent.getAction();
            char c = 65535;
            boolean z = true;
            switch (action.hashCode()) {
                case -1867628834:
                    if (action.equals("com.xiaomi.smarthome.camera.LANUCH")) {
                        c = 0;
                        break;
                    }
                    break;
                case -411486983:
                    if (action.equals("com.xiaomi.smarthome.camera.PUSH_MSG")) {
                        c = 5;
                        break;
                    }
                    break;
                case -87835003:
                    if (action.equals("com.xiaomi.smarthome.camera.DESTROY_REQUEST_CAMERA_FRAME")) {
                        c = 4;
                        break;
                    }
                    break;
                case 794585267:
                    if (action.equals("com.xiaomi.smarthome.camera.STAR_REQUEST_CAMERA_FRAME")) {
                        c = 2;
                        break;
                    }
                    break;
                case 822564099:
                    if (action.equals("com.xiaomi.smarthome.camera.STOP_REQUEST_CAMERA_FRAME")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1303636175:
                    if (action.equals("com.xiaomi.smarthome.camera.INIT_CAMERA_FRAME_SENDER")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    XmPluginHostApi.instance().closeCameraFloatingWindow(stringExtra);
                    Intent intent2 = new Intent(context, CameraPlayer2ExActivity.class);
                    intent2.putExtra("extra_device_did", stringExtra);
                    intent2.addFlags(C.ENCODING_PCM_MU_LAW);
                    context.startActivity(intent2);
                    return;
                case 1:
                    CameraFrameManager.a().b((XmPluginPackage) null, deviceByDid);
                    return;
                case 2:
                    CameraFrameManager.a().a((XmPluginPackage) null, deviceByDid, intent);
                    return;
                case 3:
                    CameraFrameManager.a().a((XmPluginPackage) null, deviceByDid);
                    return;
                case 4:
                    CameraFrameManager.a().c((XmPluginPackage) null, deviceByDid);
                    return;
                case 5:
                    if (intent != null) {
                        String stringExtra2 = intent.getStringExtra("type");
                        if (!TextUtils.isEmpty(stringExtra2)) {
                            if ("DevicePush".equals(stringExtra2)) {
                                String stringExtra3 = intent.getStringExtra("data");
                                MijiaCameraDevice.a(deviceByDid).g().a(intent);
                                SDKLog.e(Tag.f7948a, "DevicePush " + stringExtra3);
                                return;
                            } else if ("ScenePush".equals(stringExtra2)) {
                                String stringExtra4 = intent.getStringExtra("event");
                                String stringExtra5 = intent.getStringExtra("extra");
                                long longExtra = intent.getLongExtra("time", 0);
                                intent.putExtra("extra_device_did", stringExtra);
                                LogUtil.a(TAG, "ScenePush extra = " + stringExtra5);
                                if (stringExtra4.equals("motion") && longExtra > 0) {
                                    intent.putExtra(ProcessInfo.ALIAS_PUSH, true);
                                    intent.putExtra("check", true);
                                    intent.setClass(context, LocalAlarmPlayerActivity.class);
                                    intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                                    context.startActivity(intent);
                                    return;
                                } else if (stringExtra4.equals("bind_fail_need_app_unbind")) {
                                    MijiaCameraDevice.a(deviceByDid);
                                    intent.putExtra("fail_unbind", true);
                                    intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                                    intent.setClass(context, CameraPlayer2ExActivity.class);
                                    context.startActivity(intent);
                                    return;
                                } else if (stringExtra4.equals("nas_error")) {
                                    intent.setClass(context, NASInfoActivity.class);
                                    intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                                    context.startActivity(intent);
                                    return;
                                } else if ("smart_camera_motion".equals(stringExtra4)) {
                                    intent.putExtra("check", true);
                                    if (!TextUtils.isEmpty(stringExtra5)) {
                                        try {
                                            JSONObject jSONObject = new JSONObject(stringExtra5);
                                            long optLong = jSONObject.optLong("createTime");
                                            String optString = jSONObject.optString("fileId");
                                            int optInt = jSONObject.optInt("offset");
                                            boolean optBoolean = jSONObject.optBoolean("isAlarm", true);
                                            try {
                                                double optDouble = jSONObject.optDouble("startDuration");
                                                intent.putExtra("fileId", optString);
                                                intent.putExtra("createTime", optLong);
                                                intent.putExtra("isAlarm", optBoolean);
                                                intent.putExtra("offset", optInt);
                                                intent.putExtra("startDuration", optDouble);
                                            } catch (JSONException unused) {
                                            }
                                            z = optBoolean;
                                        } catch (JSONException unused2) {
                                        }
                                    }
                                    if (z) {
                                        intent.setClass(context, AlarmVideoNormalPlayerActivity.class);
                                    } else {
                                        intent.setClass(context, AlarmVideoPlayerActivity.class);
                                    }
                                    intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                                    context.startActivity(intent);
                                    return;
                                } else {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }

    private void handleV3UpdateMessage(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("extra_device_did");
        DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(stringExtra);
        if (deviceByDid != null) {
            String action = intent.getAction();
            char c = 65535;
            boolean z = true;
            switch (action.hashCode()) {
                case -1867628834:
                    if (action.equals("com.xiaomi.smarthome.camera.LANUCH")) {
                        c = 0;
                        break;
                    }
                    break;
                case -411486983:
                    if (action.equals("com.xiaomi.smarthome.camera.PUSH_MSG")) {
                        c = 5;
                        break;
                    }
                    break;
                case -87835003:
                    if (action.equals("com.xiaomi.smarthome.camera.DESTROY_REQUEST_CAMERA_FRAME")) {
                        c = 4;
                        break;
                    }
                    break;
                case 794585267:
                    if (action.equals("com.xiaomi.smarthome.camera.STAR_REQUEST_CAMERA_FRAME")) {
                        c = 2;
                        break;
                    }
                    break;
                case 822564099:
                    if (action.equals("com.xiaomi.smarthome.camera.STOP_REQUEST_CAMERA_FRAME")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1303636175:
                    if (action.equals("com.xiaomi.smarthome.camera.INIT_CAMERA_FRAME_SENDER")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    XmPluginHostApi.instance().closeCameraFloatingWindow(stringExtra);
                    Intent intent2 = new Intent();
                    intent2.putExtra("extra_device_did", stringExtra);
                    intent2.putExtra("is_v4", false);
                    intent2.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent2.setClass(context, CameraV3UpgradePlayerActivity.class);
                    context.startActivity(intent2);
                    return;
                case 1:
                    CameraFrameManager.a().b((XmPluginPackage) null, deviceByDid);
                    return;
                case 2:
                    CameraFrameManager.a().a((XmPluginPackage) null, deviceByDid, intent);
                    return;
                case 3:
                    CameraFrameManager.a().a((XmPluginPackage) null, deviceByDid);
                    return;
                case 4:
                    CameraFrameManager.a().c((XmPluginPackage) null, deviceByDid);
                    return;
                case 5:
                    if (intent != null) {
                        String stringExtra2 = intent.getStringExtra("type");
                        if (!TextUtils.isEmpty(stringExtra2)) {
                            if ("DevicePush".equals(stringExtra2)) {
                                String stringExtra3 = intent.getStringExtra("data");
                                MijiaCameraDevice.a(deviceByDid).g().a(intent);
                                SDKLog.e(Tag.f7948a, "DevicePush " + stringExtra3);
                                return;
                            } else if ("ScenePush".equals(stringExtra2)) {
                                String stringExtra4 = intent.getStringExtra("event");
                                String stringExtra5 = intent.getStringExtra("extra");
                                long longExtra = intent.getLongExtra("time", 0);
                                intent.putExtra("extra_device_did", stringExtra);
                                LogUtil.a(TAG, "ScenePush extra = " + stringExtra5);
                                if (stringExtra4.equals("motion") && longExtra > 0) {
                                    intent.putExtra(ProcessInfo.ALIAS_PUSH, true);
                                    intent.putExtra("check", true);
                                    intent.setClass(context, LocalAlarmPlayerActivity.class);
                                    intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                                    context.startActivity(intent);
                                    return;
                                } else if (stringExtra4.equals("bind_fail_need_app_unbind")) {
                                    intent.putExtra("fail_unbind", true);
                                    intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                                    String str = deviceByDid.extrainfo;
                                    intent.setClass(context, CameraV3UpgradePlayerActivity.class);
                                    context.startActivity(intent);
                                    return;
                                } else if (stringExtra4.equals("nas_error")) {
                                    intent.setClass(context, NASInfoActivity.class);
                                    intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                                    context.startActivity(intent);
                                    return;
                                } else if ("smart_camera_motion".equals(stringExtra4)) {
                                    intent.putExtra("check", true);
                                    if (!TextUtils.isEmpty(stringExtra5)) {
                                        try {
                                            JSONObject jSONObject = new JSONObject(stringExtra5);
                                            long optLong = jSONObject.optLong("createTime");
                                            String optString = jSONObject.optString("fileId");
                                            int optInt = jSONObject.optInt("offset");
                                            boolean optBoolean = jSONObject.optBoolean("isAlarm", true);
                                            try {
                                                double optDouble = jSONObject.optDouble("startDuration");
                                                intent.putExtra("fileId", optString);
                                                intent.putExtra("createTime", optLong);
                                                intent.putExtra("isAlarm", optBoolean);
                                                intent.putExtra("offset", optInt);
                                                intent.putExtra("startDuration", optDouble);
                                            } catch (JSONException unused) {
                                            }
                                            z = optBoolean;
                                        } catch (JSONException unused2) {
                                        }
                                    }
                                    if (z) {
                                        intent.setClass(context, AlarmVideoNormalPlayerActivity.class);
                                    } else {
                                        intent.setClass(context, AlarmVideoPlayerActivity.class);
                                    }
                                    intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                                    context.startActivity(intent);
                                    return;
                                } else {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }

    private void handleV4Message(Context context, Intent intent, String str) {
        String stringExtra = intent.getStringExtra("extra_device_did");
        DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(stringExtra);
        if (deviceByDid != null) {
            String action = intent.getAction();
            char c = 65535;
            boolean z = true;
            switch (action.hashCode()) {
                case -1867628834:
                    if (action.equals("com.xiaomi.smarthome.camera.LANUCH")) {
                        c = 0;
                        break;
                    }
                    break;
                case -411486983:
                    if (action.equals("com.xiaomi.smarthome.camera.PUSH_MSG")) {
                        c = 5;
                        break;
                    }
                    break;
                case -87835003:
                    if (action.equals("com.xiaomi.smarthome.camera.DESTROY_REQUEST_CAMERA_FRAME")) {
                        c = 4;
                        break;
                    }
                    break;
                case 794585267:
                    if (action.equals("com.xiaomi.smarthome.camera.STAR_REQUEST_CAMERA_FRAME")) {
                        c = 2;
                        break;
                    }
                    break;
                case 822564099:
                    if (action.equals("com.xiaomi.smarthome.camera.STOP_REQUEST_CAMERA_FRAME")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1303636175:
                    if (action.equals("com.xiaomi.smarthome.camera.INIT_CAMERA_FRAME_SENDER")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    XmPluginHostApi.instance().closeCameraFloatingWindow(stringExtra);
                    Intent intent2 = new Intent();
                    intent2.putExtra("extra_device_did", stringExtra);
                    intent2.putExtra("is_v4", true);
                    intent2.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent2.setClass(context, CameraPlayerActivity.class);
                    context.startActivity(intent2);
                    return;
                case 1:
                    CameraFrameManager.a().b((XmPluginPackage) null, deviceByDid);
                    return;
                case 2:
                    CameraFrameManager.a().a((XmPluginPackage) null, deviceByDid, intent);
                    return;
                case 3:
                    CameraFrameManager.a().a((XmPluginPackage) null, deviceByDid);
                    return;
                case 4:
                    CameraFrameManager.a().c((XmPluginPackage) null, deviceByDid);
                    return;
                case 5:
                    if (intent != null) {
                        String stringExtra2 = intent.getStringExtra("type");
                        if (!TextUtils.isEmpty(stringExtra2)) {
                            if ("DevicePush".equals(stringExtra2)) {
                                String stringExtra3 = intent.getStringExtra("data");
                                MijiaCameraDevice.a(deviceByDid).g().a(intent);
                                SDKLog.e(Tag.f7948a, "DevicePush " + stringExtra3);
                                return;
                            } else if ("ScenePush".equals(stringExtra2)) {
                                String stringExtra4 = intent.getStringExtra("event");
                                String stringExtra5 = intent.getStringExtra("extra");
                                SDKLog.b("MessageReceiver", "ScenePush " + stringExtra4 + " " + stringExtra5);
                                long longExtra = intent.getLongExtra("time", 0);
                                SDKLog.e(Tag.f7948a, "ScenePush :" + stringExtra4 + "  " + stringExtra5);
                                intent.putExtra("extra_device_did", stringExtra);
                                intent.putExtra("is_v4", true);
                                if (stringExtra4.equals("motion") && longExtra > 0) {
                                    XmPluginHostApi.instance().closeCameraFloatingWindow(stringExtra);
                                    intent.putExtra(ProcessInfo.ALIAS_PUSH, true);
                                    intent.putExtra("check", true);
                                    intent.putExtra("is_v4", true);
                                    intent.setClass(context, LocalAlarmPlayerActivity.class);
                                    intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                                    context.startActivity(intent);
                                    return;
                                } else if (stringExtra4.equals("bind_fail_need_app_unbind")) {
                                    intent.putExtra("fail_unbind", true);
                                    intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                                    intent.putExtra("is_v4", true);
                                    intent.setClass(context, CameraPlayerActivity.class);
                                    context.startActivity(intent);
                                    return;
                                } else if (stringExtra4.equals("nas_error")) {
                                    intent.putExtra("is_v4", true);
                                    intent.setClass(context, NASInfoActivity.class);
                                    intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                                    context.startActivity(intent);
                                    return;
                                } else if ("smart_camera_motion".equals(stringExtra4)) {
                                    XmPluginHostApi.instance().closeCameraFloatingWindow(stringExtra);
                                    intent.putExtra("check", true);
                                    if (!TextUtils.isEmpty(stringExtra5)) {
                                        try {
                                            JSONObject jSONObject = new JSONObject(stringExtra5);
                                            long optLong = jSONObject.optLong("createTime");
                                            String optString = jSONObject.optString("fileId");
                                            int optInt = jSONObject.optInt("offset");
                                            boolean optBoolean = jSONObject.optBoolean("isAlarm", true);
                                            try {
                                                double optDouble = jSONObject.optDouble("startDuration");
                                                intent.putExtra("fileId", optString);
                                                intent.putExtra("createTime", optLong);
                                                intent.putExtra("isAlarm", optBoolean);
                                                intent.putExtra("offset", optInt);
                                                intent.putExtra("startDuration", optDouble);
                                            } catch (JSONException unused) {
                                            }
                                            z = optBoolean;
                                        } catch (JSONException unused2) {
                                        }
                                    }
                                    if (z) {
                                        intent.setClass(context, AlarmVideoNormalPlayerActivity.class);
                                    } else {
                                        intent.setClass(context, AlarmVideoPlayerActivity.class);
                                    }
                                    intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                                    context.startActivity(intent);
                                    return;
                                } else {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }
}
