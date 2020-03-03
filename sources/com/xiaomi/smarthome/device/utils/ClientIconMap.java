package com.xiaomi.smarthome.device.utils;

import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthomedevice.R;
import java.util.HashMap;

public class ClientIconMap {

    /* renamed from: a  reason: collision with root package name */
    private static HashMap<String, Integer[]> f15423a = new HashMap<>();
    private static HashMap<String, Integer[]> b = new HashMap<>();
    private static HashMap<String, Integer[]> c = new HashMap<>();
    private static HashMap<String, Integer> d = new HashMap<>();

    static {
        f15423a.put("xiaomi.ir.v1", new Integer[]{Integer.valueOf(R.drawable.device_list_remote_control_normal), Integer.valueOf(R.drawable.lock_list_remote_control_normal)});
        f15423a.put("xiaomi.curtain.v1", new Integer[]{Integer.valueOf(R.drawable.device_list_intelligent_curtain), Integer.valueOf(R.drawable.lock_list_curtain_normal)});
        f15423a.put("xiaomi.ble.v1", new Integer[]{Integer.valueOf(R.drawable.intelligent_ble), Integer.valueOf(R.drawable.lock_list_bracelet_normal)});
        f15423a.put("xiaomi.myphone.v1", new Integer[]{Integer.valueOf(R.drawable.device_list_phone), Integer.valueOf(R.drawable.lock_list_phone_normal)});
        f15423a.put("yeelink.light.rgb1", new Integer[]{Integer.valueOf(R.drawable.device_list_yeelight_real), Integer.valueOf(R.drawable.lock_list_yeelight_normal)});
        f15423a.put("yeelight.rgb.v1", new Integer[]{Integer.valueOf(R.drawable.device_list_yeelight_real), Integer.valueOf(R.drawable.lock_list_yeelight_normal)});
        f15423a.put("xiaomi.phone_ir.t1", new Integer[]{Integer.valueOf(R.drawable.device_list_remote_control_normal), Integer.valueOf(R.drawable.lock_list_remote_control_normal)});
        f15423a.put("xiaomi.phone_ir.v1", new Integer[]{Integer.valueOf(R.drawable.device_list_remote_control_normal), Integer.valueOf(R.drawable.lock_list_remote_control_normal)});
        f15423a.put("xiaomi.mikey.v1", new Integer[]{Integer.valueOf(R.drawable.device_list_mikey_normal), Integer.valueOf(R.drawable.lock_list_mikey_normal)});
        b.put("xiaomi.ir.v1", new Integer[]{Integer.valueOf(R.drawable.device_list_remote_control_normal), Integer.valueOf(R.drawable.lock_list_remote_control_disabled)});
        b.put("xiaomi.curtain.v1", new Integer[]{Integer.valueOf(R.drawable.device_list_intelligent_curtain), Integer.valueOf(R.drawable.lock_list_curtain_normal)});
        b.put("xiaomi.ble.v1", new Integer[]{Integer.valueOf(R.drawable.intelligent_ble), Integer.valueOf(R.drawable.lock_list_bracelet_normal)});
        b.put("xiaomi.myphone.v1", new Integer[]{Integer.valueOf(R.drawable.device_list_phone), Integer.valueOf(R.drawable.lock_list_phone_normal)});
        b.put("yeelink.light.rgb1", new Integer[]{Integer.valueOf(R.drawable.device_list_yeelight_real), Integer.valueOf(R.drawable.lock_list_yeelight_pressed)});
        b.put("yeelight.rgb.v1", new Integer[]{Integer.valueOf(R.drawable.device_list_yeelight_real), Integer.valueOf(R.drawable.lock_list_yeelight_pressed)});
        b.put("xiaomi.phone_ir.t1", new Integer[]{Integer.valueOf(R.drawable.device_list_remote_control_normal), Integer.valueOf(R.drawable.lock_list_remote_control_disabled)});
        b.put("xiaomi.phone_ir.v1", new Integer[]{Integer.valueOf(R.drawable.device_list_remote_control_normal), Integer.valueOf(R.drawable.lock_list_remote_control_disabled)});
        b.put("xiaomi.mikey.v1", new Integer[]{Integer.valueOf(R.drawable.device_list_mikey_closed), Integer.valueOf(R.drawable.lock_list_mikey_closed)});
        c.put("xiaomi.ir.v1", new Integer[]{Integer.valueOf(R.drawable.device_list_remote_control_normal), Integer.valueOf(R.drawable.lock_list_remote_control_disabled)});
        c.put("xiaomi.curtain.v1", new Integer[]{Integer.valueOf(R.drawable.device_list_intelligent_curtain), Integer.valueOf(R.drawable.lock_list_curtain_normal)});
        c.put("xiaomi.ble.v1", new Integer[]{Integer.valueOf(R.drawable.miband_offline), Integer.valueOf(R.drawable.lock_list_bracelet_off)});
        c.put("yeelink.light.rgb1", new Integer[]{Integer.valueOf(R.drawable.device_list_yeelight_real), Integer.valueOf(R.drawable.lock_list_yeelight_pressed)});
        c.put("yeelight.rgb.v1", new Integer[]{Integer.valueOf(R.drawable.device_list_yeelight_real), Integer.valueOf(R.drawable.lock_list_yeelight_pressed)});
        c.put("xiaomi.phone_ir.t1", new Integer[]{Integer.valueOf(R.drawable.device_list_remote_control_normal), Integer.valueOf(R.drawable.lock_list_remote_control_disabled)});
        c.put("xiaomi.phone_ir.v1", new Integer[]{Integer.valueOf(R.drawable.device_list_remote_control_normal), Integer.valueOf(R.drawable.lock_list_remote_control_disabled)});
        c.put("xiaomi.mikey.v1", new Integer[]{Integer.valueOf(R.drawable.device_list_mikey_offline), Integer.valueOf(R.drawable.device_list_mikey_offline)});
        d.put("ir.others", Integer.valueOf(R.drawable.ir_others));
        d.put("ir.stb", Integer.valueOf(R.drawable.ir_stb));
        d.put("ir.tv", Integer.valueOf(R.drawable.ir_tv));
        d.put("ir.dvd", Integer.valueOf(R.drawable.ir_dvd));
        d.put("ir.projector", Integer.valueOf(R.drawable.ir_projector));
        d.put("ir.fan", Integer.valueOf(R.drawable.ir_fan));
        d.put("ir.air", Integer.valueOf(R.drawable.ir_air));
        d.put("ir.purifier", Integer.valueOf(R.drawable.ir_purifier));
        d.put("ir.box", Integer.valueOf(R.drawable.ir_box));
        d.put("ir.micontroller", Integer.valueOf(R.drawable.ir_mi_controller));
        d.put("ir.camera", Integer.valueOf(R.drawable.ir_camera));
        d.put("ir.amplifier", Integer.valueOf(R.drawable.ir_amplifier));
    }

    public static int a(String str) {
        Integer[] numArr;
        if (str == null || (numArr = f15423a.get(str)) == null || numArr.length <= 0) {
            return 0;
        }
        return numArr[0].intValue();
    }

    public static int a(Device device) {
        Integer[] numArr;
        if (device == null) {
            return 0;
        }
        String str = device.model;
        if (device.isOnline && device.isOpen()) {
            numArr = f15423a.get(str);
        } else if (!device.isOnline) {
            numArr = c.get(str);
        } else {
            numArr = b.get(str);
        }
        if (numArr == null || numArr.length <= 0) {
            return 0;
        }
        if (numArr.length < 2) {
            return numArr[0].intValue();
        }
        return numArr[1].intValue();
    }

    public static int b(String str) {
        if (str == null || str.isEmpty() || d.get(str) == null) {
            return 0;
        }
        return d.get(str).intValue();
    }
}
