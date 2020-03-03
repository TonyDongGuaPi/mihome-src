package com.xiaomi.smarthome.device.bluetooth;

import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.device.BtDevice;
import com.xiaomi.smarthome.core.server.internal.bluetooth.IBluetoothService;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.connect.BleGattProfile;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import java.util.List;

public class BleCacheUtils {
    public static void a(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(IBluetoothService.V, str2);
        CoreApi.a().b(str, 1, bundle);
    }

    public static String a(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 1, bundle);
        return bundle.getString("extra.result", "");
    }

    public static String b(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 17, bundle);
        return bundle.getString("extra.result", "");
    }

    public static void b(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(IBluetoothService.V, str2);
        CoreApi.a().b(str, 16, bundle);
    }

    public static String c(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 16, bundle);
        return bundle.getString("extra.result", "");
    }

    public static void c(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(IBluetoothService.V, str2);
        CoreApi.a().b(str, 13, bundle);
    }

    public static String d(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 13, bundle);
        return bundle.getString("extra.result", "");
    }

    public static void d(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(IBluetoothService.V, str2);
        CoreApi.a().b(str, 2, bundle);
    }

    public static String e(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 2, bundle);
        return bundle.getString("extra.result", "");
    }

    public static int f(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 12, bundle);
        return bundle.getInt("extra.result", 0);
    }

    public static int g(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 14, bundle);
        return bundle.getInt("extra.result", -60);
    }

    public static int h(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 11, bundle);
        return bundle.getInt("extra.result", 0);
    }

    public static void e(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(IBluetoothService.V, str2);
        CoreApi.a().b(str, 3, bundle);
    }

    public static String i(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 3, bundle);
        return bundle.getString("extra.result", "");
    }

    public static void f(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(IBluetoothService.V, str2);
        CoreApi.a().b(str, 4, bundle);
    }

    public static String j(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 4, bundle);
        return bundle.getString("extra.result", "");
    }

    public static void g(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(IBluetoothService.V, str2);
        CoreApi.a().b(str, 10, bundle);
    }

    public static String k(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 10, bundle);
        return bundle.getString("extra.result", "");
    }

    public static void a(String str, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray(IBluetoothService.V, bArr);
        CoreApi.a().b(str, 8, bundle);
    }

    public static byte[] l(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 8, bundle);
        return bundle.getByteArray("extra.result");
    }

    public static void a(String str, BleGattProfile bleGattProfile) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(IBluetoothService.V, bleGattProfile);
        CoreApi.a().b(str, 9, bundle);
    }

    public static BleGattProfile m(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 9, bundle);
        return (BleGattProfile) bundle.getParcelable("extra.result");
    }

    public static void a(String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(IBluetoothService.V, i);
        CoreApi.a().b(str, 5, bundle);
    }

    public static int n(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 5, bundle);
        return bundle.getInt("extra.result", 0);
    }

    public static void b(String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(IBluetoothService.V, i);
        CoreApi.a().b(str, 15, bundle);
    }

    public static int o(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 15, bundle);
        return bundle.getInt("extra.result", 0);
    }

    public static String p(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 6, bundle);
        return bundle.getString("extra.result", "");
    }

    public static byte[] q(String str) {
        String p = p(str);
        return TextUtils.isEmpty(p) ? ByteUtils.b : ByteUtils.a(p);
    }

    public static void h(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            BluetoothMyLogger.c(String.format("setPropToken mac null", new Object[0]));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(IBluetoothService.V, str2);
        CoreApi.a().b(str, 6, bundle);
    }

    public static String r(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 19, bundle);
        return bundle.getString("extra.result", "");
    }

    public static void i(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            BluetoothMyLogger.c(String.format("setPropLtmk mac null", new Object[0]));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(IBluetoothService.V, str2);
        CoreApi.a().b(str, 19, bundle);
    }

    public static String s(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 21, bundle);
        return bundle.getString("extra.result", "");
    }

    public static void j(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            BluetoothMyLogger.c(String.format("setPropPincode mac null", new Object[0]));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(IBluetoothService.V, str2);
        CoreApi.a().b(str, 21, bundle);
    }

    public static int t(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 22, bundle);
        return bundle.getInt("extra.result", 0);
    }

    public static void c(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            BluetoothMyLogger.c(String.format("setPropLtmkEncryptType mac null", new Object[0]));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(IBluetoothService.V, i);
        CoreApi.a().b(str, 22, bundle);
    }

    public static boolean u(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 23, bundle);
        return bundle.getBoolean("extra.result", true);
    }

    public static void a(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            BluetoothMyLogger.c(String.format("setPropShowPincode mac null", new Object[0]));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean(IBluetoothService.V, z);
        CoreApi.a().b(str, 23, bundle);
    }

    public static String v(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 20, bundle);
        return bundle.getString("extra.result", "");
    }

    public static void b(String str, byte[] bArr) {
        if (!ByteUtils.e(bArr)) {
            h(str, ByteUtils.d(bArr));
        }
    }

    public static String w(String str) {
        Bundle bundle = new Bundle();
        CoreApi.a().a(str, 24, bundle);
        return bundle.getString("extra.result", "");
    }

    public static void x(String str) {
        h(str, "");
        i(str, "");
        j(str, "");
        c(str, 0);
        a(str, true);
    }

    public static String k(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(IBluetoothService.T, str2);
        CoreApi.a().a(str, 7, bundle);
        return bundle.getString("extra.result", "");
    }

    public static void a(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString(IBluetoothService.T, str2);
        bundle.putString(IBluetoothService.V, str3);
        CoreApi.a().b(str, 7, bundle);
    }

    public static int a(String str, String str2, int i) {
        String k = k(str, str2);
        if (!TextUtils.isEmpty(k)) {
            try {
                return Integer.parseInt(k);
            } catch (Exception e) {
                BluetoothLog.b((Throwable) e);
            }
        }
        return i;
    }

    public static void b(String str, String str2, int i) {
        a(str, str2, String.valueOf(i));
    }

    public static boolean a(String str, String str2, boolean z) {
        String k = k(str, str2);
        if (!TextUtils.isEmpty(k)) {
            try {
                return Boolean.parseBoolean(k);
            } catch (Exception e) {
                BluetoothLog.b((Throwable) e);
            }
        }
        return z;
    }

    public static void b(String str, String str2, boolean z) {
        a(str, str2, String.valueOf(z));
    }

    public static void l(String str, String str2) {
        a(str, str2, "");
    }

    public static List<String> a() {
        return ListUtils.a();
    }

    public static List<BleDevice> a(int i) {
        Bundle bundle = new Bundle();
        CoreApi.a().a((String) null, i, bundle);
        bundle.setClassLoader(BtDevice.class.getClassLoader());
        return BleObjectTranslator.a((List<BtDevice>) bundle.getParcelableArrayList(IBluetoothService.ac));
    }

    public static List<BleDevice> b() {
        return a(100);
    }

    public static List<BleDevice> c() {
        return a(101);
    }

    public static List<BleDevice> d() {
        return a(102);
    }
}
