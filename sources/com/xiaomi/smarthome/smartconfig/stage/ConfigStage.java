package com.xiaomi.smarthome.smartconfig.stage;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.text.TextUtils;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.bluetooth.ComboDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.wificonfig.WifiSettingNormal;
import java.util.HashMap;
import java.util.List;

public class ConfigStage {

    /* renamed from: a  reason: collision with root package name */
    public static final int f22384a = 2;
    public static final int b = 3;
    public static final int c = 4;
    public static final int d = 5;
    public static final int e = 6;
    @Deprecated
    public static final int f = 7;
    public static final int g = 8;
    public static final int h = 9;
    public static final int i = 10;
    public static final int j = 11;
    public static final int k = 12;
    public static final int l = 13;
    public static final int m = 14;
    public static final int n = 16;
    public static final int o = 15;
    private static HashMap<Integer, Stage> p = new HashMap<>();

    static {
        Stage stage = new Stage();
        stage.f22385a = true;
        stage.b = true;
        stage.d = SmartConfigStep.Step.STEP_AP_CONFIG_STEP;
        p.put(2, stage);
        Stage stage2 = new Stage();
        stage2.f22385a = true;
        stage2.b = false;
        stage2.d = SmartConfigStep.Step.STEP_QR_CONFIG;
        p.put(3, stage2);
        Stage stage3 = new Stage();
        stage3.f22385a = false;
        stage3.b = false;
        stage3.c = SmartConfigStep.Step.STEP_PREPARE_SCAN_QR;
        stage3.d = SmartConfigStep.Step.STEP_SCAN_QR;
        p.put(5, stage3);
        Stage stage4 = new Stage();
        stage4.f22385a = true;
        stage4.b = false;
        stage4.d = SmartConfigStep.Step.STEP_BLE_COMBO_CONFIG;
        p.put(4, stage4);
        Stage stage5 = new Stage();
        stage5.f22385a = false;
        stage5.b = false;
        stage5.c = SmartConfigStep.Step.STEP_AP_DEVICE_CONFIG_PASSWD_CHOOSER;
        stage5.d = SmartConfigStep.Step.STEP_AP_DEVICE_CONFIG_STEP;
        p.put(6, stage5);
        Stage stage6 = new Stage();
        stage6.f22385a = false;
        stage6.b = false;
        stage6.c = SmartConfigStep.Step.STEP_XIAOFANG_RESET;
        stage6.d = SmartConfigStep.Step.STEP_QR_CONFIG;
        p.put(7, stage6);
        Stage stage7 = new Stage();
        stage7.f22385a = false;
        stage7.b = false;
        stage7.c = SmartConfigStep.Step.STEP_AP_INPUT_SSID_PASSWD_CONFIG;
        stage7.d = SmartConfigStep.Step.STEP_BIND_KEY;
        p.put(8, stage7);
        Stage stage8 = new Stage();
        stage8.f22385a = false;
        stage8.b = false;
        stage8.c = SmartConfigStep.Step.STEP_CAMERA_RESET;
        stage8.d = SmartConfigStep.Step.STEP_QR_CAMERA;
        p.put(9, stage8);
        Stage stage9 = new Stage();
        stage9.f22385a = false;
        stage9.b = false;
        stage9.c = null;
        stage9.d = SmartConfigStep.Step.STEP_BIND_DEVICE_FROM_THIRD_APP;
        p.put(10, stage9);
        Stage stage10 = new Stage();
        stage10.f22385a = false;
        stage10.b = false;
        stage10.c = SmartConfigStep.Step.STEP_CAMERA_RESET;
        stage10.d = SmartConfigStep.Step.STEP_SW_CAMERA;
        p.put(11, stage10);
        Stage stage11 = new Stage();
        stage11.f22385a = false;
        stage11.b = false;
        stage11.d = SmartConfigStep.Step.STEP_NBIOT;
        p.put(12, stage11);
        Stage stage12 = new Stage();
        stage12.f22385a = true;
        stage12.b = false;
        stage12.d = SmartConfigStep.Step.STEP_COMBO_BLE_WAY_CONFIG;
        p.put(13, stage12);
        Stage stage13 = new Stage();
        stage13.f22385a = false;
        stage13.b = false;
        stage13.d = SmartConfigStep.Step.STEP_GATEWAY_SUBDEVICE;
        p.put(14, stage13);
        Stage stage14 = new Stage();
        stage14.f22385a = false;
        stage14.b = false;
        stage14.d = SmartConfigStep.Step.STEP_PUSH_NEWDEVICE;
        p.put(16, stage14);
        Stage stage15 = new Stage();
        stage15.f22385a = true;
        stage15.b = true;
        stage15.d = SmartConfigStep.Step.STEP_APSECURE_CONFIG_STEP;
        p.put(15, stage15);
    }

    public static boolean a(int i2) {
        return p.get(Integer.valueOf(i2)).f22385a;
    }

    public static SmartConfigStep.Step b(int i2) {
        if (p.get(Integer.valueOf(i2)) == null) {
            return null;
        }
        return p.get(Integer.valueOf(i2)).c;
    }

    public static SmartConfigStep.Step c(int i2) {
        if (p.get(Integer.valueOf(i2)) == null) {
            return null;
        }
        return p.get(Integer.valueOf(i2)).d;
    }

    public static boolean d(int i2) {
        return p.get(Integer.valueOf(i2)).b;
    }

    public static Intent a(Context context, ScanResult scanResult, String str, String str2, String str3) {
        PluginRecord d2;
        Intent intent;
        if (context == null || (d2 = CoreApi.a().d(str)) == null) {
            return null;
        }
        if (d2.v() > SystemApi.a().e(context)) {
            Toast.makeText(context, context.getString(R.string.device_not_support_now), 0).show();
            return null;
        } else if (!d2.c().a()) {
            Toast.makeText(context, context.getString(R.string.device_not_support_now), 0).show();
            return null;
        } else {
            if (scanResult == null) {
                intent = new Intent(context, WifiSettingNormal.class);
            } else if (DeviceFactory.d(scanResult) == DeviceFactory.AP_TYPE.AP_MIIO) {
                intent = new Intent(context, WifiSettingNormal.class);
            } else if (DeviceFactory.b(scanResult) == DeviceFactory.AP_TYPE.AP_MIDEVICE) {
                intent = new Intent(context, SmartConfigMainActivity.class);
                intent.putExtra("strategy_id", 6);
            } else if (d2.c().e() == 4) {
                List<Integer> i2 = d2.c().i();
                Intent intent2 = new Intent(context, SmartConfigMainActivity.class);
                if (i2 != null && i2.contains(1) && BluetoothUtils.a()) {
                    intent2.putExtra("strategy_id", 4);
                    ComboDeviceManager.a(scanResult);
                } else if (i2 == null || !i2.contains(0)) {
                    intent2.putExtra("strategy_id", 9);
                    intent2.putExtra("use_reset_page", false);
                } else {
                    intent2.putExtra("strategy_id", 2);
                }
                intent = intent2;
            } else if (d2.c().e() == 12) {
                intent = new Intent(context, SmartConfigMainActivity.class);
                intent.putExtra("strategy_id", 11);
                intent.putExtra("use_reset_page", false);
            } else if (DeviceFactory.d(scanResult) == DeviceFactory.AP_TYPE.AP_MIBAP) {
                intent = new Intent(context, SmartConfigMainActivity.class);
                if (BluetoothUtils.a()) {
                    intent.putExtra("strategy_id", 4);
                    ComboDeviceManager.a(scanResult);
                } else {
                    intent.putExtra("strategy_id", 2);
                }
            } else {
                intent = new Intent(context, SmartConfigMainActivity.class);
                String a2 = DeviceFactory.a(scanResult);
                if (a2.equalsIgnoreCase("xiaomi.plc.v1")) {
                    intent.putExtra("strategy_id", 8);
                } else if (d2.c().e() == 16 || "chuangmi.camera.ipc020".equals(str)) {
                    intent.putExtra("strategy_id", 15);
                } else {
                    intent.putExtra("strategy_id", 2);
                }
                intent.putExtra("model", a2);
            }
            intent.putExtra("model", str);
            if (!TextUtils.isEmpty(str2)) {
                intent.putExtra("bssid", str2);
                intent.putExtra("password", str3);
            }
            if (scanResult != null) {
                intent.putExtra("scanResult", scanResult);
            }
            return intent;
        }
    }
}
