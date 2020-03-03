package com.xiaomi.infrared.utils;

import com.xiaomi.infrared.InifraredContants;
import com.xiaomi.infrared.bean.IRMatchingDeviceTypeData;
import com.xiaomi.infrared.bean.IRType;
import com.xiaomi.smarthome.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class IRDataUtil {

    /* renamed from: a  reason: collision with root package name */
    public static Set<String> f10262a = null;
    public static Random b = null;
    static final String c = "0123456789";

    public static String a() {
        if (f10262a == null) {
            f10262a = new HashSet();
        }
        return a(c, 15, f10262a);
    }

    public static String a(String str, int i, Set<String> set) {
        String str2;
        if (b == null) {
            b = new Random();
        }
        do {
            str2 = "";
            for (int i2 = 0; i2 < i; i2++) {
                int abs = Math.abs(b.nextInt()) % str.length();
                str2 = str2 + str.charAt(abs);
            }
        } while (set.contains(str2));
        set.add(str2);
        return str2;
    }

    public static List<IRMatchingDeviceTypeData> b() {
        ArrayList arrayList = new ArrayList();
        for (int value = IRType.STB.value(); value <= IRType.WATER_HEATER.value(); value++) {
            arrayList.add(a(value));
        }
        return arrayList;
    }

    public static List<IRMatchingDeviceTypeData> c() {
        ArrayList arrayList = new ArrayList();
        for (int value = IRType.WATER_HEATER.value(); value >= IRType.Unknown.value(); value--) {
            arrayList.add(a(value));
        }
        return arrayList;
    }

    public static List<IRMatchingDeviceTypeData> a(String[] strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String valueOfModel : strArr) {
            arrayList.add(a(IRType.valueOfModel(valueOfModel).value()));
        }
        return arrayList;
    }

    public static IRMatchingDeviceTypeData a(int i) {
        String str;
        int i2;
        int i3;
        if (i == IRType.STB.value()) {
            str = InifraredContants.h;
            i3 = R.string.ir_device_type_set_top_box_text;
            i2 = R.drawable.ir_device_type_set_top_box_normal;
        } else if (i == IRType.TV.value()) {
            str = InifraredContants.e;
            i3 = R.string.ir_device_type_tv_text;
            i2 = R.drawable.ir_device_type_tv_normal;
        } else if (i == IRType.BOX.value()) {
            str = InifraredContants.d;
            i3 = R.string.ir_device_type_box_text;
            i2 = R.drawable.ir_device_type_box_normal;
        } else if (i == IRType.DVD.value()) {
            str = InifraredContants.k;
            i3 = R.string.ir_device_type_dvd_text;
            i2 = R.drawable.ir_device_type_dvd_normal;
        } else if (i == IRType.AC.value()) {
            str = InifraredContants.f;
            i3 = R.string.ir_device_type_air_conditioner_text;
            i2 = R.drawable.ir_device_type_air_conditioner_normal;
        } else if (i == IRType.PRO.value()) {
            str = InifraredContants.l;
            i3 = R.string.ir_device_type_projector_text;
            i2 = R.drawable.ir_device_type_projector_normal;
        } else if (i == IRType.PA.value()) {
            str = InifraredContants.o;
            i3 = R.string.ir_device_type_amplifier;
            i2 = R.drawable.ir_device_type_amplifier_normal;
        } else if (i == IRType.FAN.value()) {
            str = InifraredContants.i;
            i3 = R.string.ir_device_type_fan_text;
            i2 = R.drawable.ir_device_type_fan_normal;
        } else if (i == IRType.SLR.value()) {
            str = InifraredContants.p;
            i3 = R.string.ir_device_type_camera;
            i2 = R.drawable.ir_device_type_camera_normal;
        } else if (i == IRType.LIGHT.value()) {
            str = InifraredContants.j;
            i3 = R.string.ir_device_type_lamp;
            i2 = R.drawable.ir_lamp_normal;
        } else if (i == IRType.AIR_CLEANER.value()) {
            str = InifraredContants.n;
            i3 = R.string.ir_device_type_purifier_text;
            i2 = R.drawable.ir_device_type_purifier_normal;
        } else if (i == IRType.WATER_HEATER.value()) {
            str = InifraredContants.m;
            i3 = R.string.ir_device_type_water_heater;
            i2 = R.drawable.ir_device_type_tv_wther_heater_normal;
        } else {
            str = InifraredContants.q;
            i3 = R.string.ir_device_type_unknown;
            i2 = R.drawable.ir_device_type_unknown_normal;
        }
        return new IRMatchingDeviceTypeData(str, i3, i2);
    }

    public static String a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            String a2 = CommUtil.a(jSONObject, "key");
            String a3 = CommUtil.a(jSONObject, "code");
            if (str.equals(a2)) {
                return a3;
            }
            return "";
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
