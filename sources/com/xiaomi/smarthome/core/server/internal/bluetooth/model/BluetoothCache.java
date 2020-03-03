package com.xiaomi.smarthome.core.server.internal.bluetooth.model;

import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.device.BtDevice;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BtConstants;
import com.xiaomi.smarthome.core.server.internal.bluetooth.TokenEncryption;
import com.xiaomi.smarthome.core.server.internal.bluetooth.decorator.BluetoothDeviceDecorator;
import com.xiaomi.smarthome.core.server.internal.bluetooth.decorator.BluetoothDevicePermitLevelDecorator;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BleDevicePropCache;
import com.xiaomi.smarthome.library.bluetooth.connect.BleGattProfile;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BluetoothCache {
    public static final int A = 102;
    private static Map<String, String> B = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    public static final int f14259a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 6;
    public static final int g = 7;
    public static final int h = 8;
    public static final int i = 9;
    public static final int j = 10;
    public static final int k = 11;
    public static final int l = 12;
    public static final int m = 13;
    public static final int n = 14;
    public static final int o = 15;
    public static final int p = 16;
    public static final int q = 17;
    public static final int r = 18;
    public static final int s = 19;
    public static final int t = 20;
    public static final int u = 21;
    public static final int v = 22;
    public static final int w = 23;
    public static final int x = 24;
    public static final int y = 100;
    public static final int z = 101;

    public static void a() {
        BleDevicePropCache.a().b();
    }

    public static void a(Device device) {
        String n2 = device.n();
        a(n2, device.m());
        i(n2, device.s());
        e(n2, device.k());
        d(n2, 2);
        m(n2, device.R());
    }

    public static void a(String str, String str2) {
        BleDevicePropCache.a().a(str, str2);
    }

    public static String a(String str) {
        return BleDevicePropCache.a().a(str);
    }

    public static void b(String str, String str2) {
        BleDevicePropCache.a().c(str, XMStringUtils.i(str2));
    }

    public static String b(String str) {
        return BleDevicePropCache.a().c(str);
    }

    public static void c(String str, String str2) {
        BleDevicePropCache.a().d(str, str2);
    }

    public static String c(String str) {
        return BleDevicePropCache.a().d(str);
    }

    public static void d(String str, String str2) {
        BleDevicePropCache.a().f(str, XMStringUtils.i(str2));
    }

    public static String d(String str) {
        return BleDevicePropCache.a().f(str);
    }

    public static void a(String str, int i2) {
        BleDevicePropCache.a().c(str, i2);
    }

    public static int e(String str) {
        return BleDevicePropCache.a().k(str);
    }

    public static void e(String str, String str2) {
        BleDevicePropCache.a().e(str, str2);
    }

    public static String f(String str) {
        return BleDevicePropCache.a().e(str);
    }

    public static void b(String str, int i2) {
        BleDevicePropCache.a().a(str, i2);
    }

    public static int g(String str) {
        return BleDevicePropCache.a().i(str);
    }

    public static void f(String str, String str2) {
        BleDevicePropCache.a().g(str, str2);
    }

    public static void c(String str, int i2) {
        BleDevicePropCache.a().b(str, i2);
    }

    public static int h(String str) {
        return BleDevicePropCache.a().j(str);
    }

    public static String i(String str) {
        return BleDevicePropCache.a().g(str);
    }

    public static void g(String str, String str2) {
        BleDevicePropCache.a().h(str, str2);
    }

    public static String j(String str) {
        return BleDevicePropCache.a().h(str);
    }

    public static void h(String str, String str2) {
        BleDevicePropCache.a().b(str, str2);
    }

    public static String k(String str) {
        return BleDevicePropCache.a().b(str);
    }

    public static void d(String str, int i2) {
        BleDevicePropCache.a().d(str, i2);
    }

    public static void a(String str, byte[] bArr) {
        BleDevicePropCache.a().a(str, bArr);
    }

    public static byte[] l(String str) {
        return BleDevicePropCache.a().u(str);
    }

    public static void e(String str, int i2) {
        BleDevicePropCache.a().f(str, i2);
    }

    public static int m(String str) {
        return BleDevicePropCache.a().v(str);
    }

    public static int n(String str) {
        return BleDevicePropCache.a().l(str);
    }

    public static String o(String str) {
        return TokenEncryption.a().b(BleDevicePropCache.a().m(str));
    }

    public static byte[] p(String str) {
        String o2 = o(str);
        return TextUtils.isEmpty(o2) ? ByteUtils.b : ByteUtils.a(o2);
    }

    public static void i(String str, String str2) {
        BleDevicePropCache.a().i(str, TokenEncryption.a().a(str2));
    }

    public static void b(String str, byte[] bArr) {
        i(str, ByteUtils.d(bArr));
    }

    public static String q(String str) {
        return TokenEncryption.a().b(BleDevicePropCache.a().n(str));
    }

    public static byte[] r(String str) {
        String q2 = q(str);
        return TextUtils.isEmpty(q2) ? ByteUtils.b : ByteUtils.a(q2);
    }

    public static void j(String str, String str2) {
        BleDevicePropCache.a().j(str, TokenEncryption.a().a(str2));
    }

    public static void c(String str, byte[] bArr) {
        j(str, ByteUtils.d(bArr));
    }

    public static String s(String str) {
        return TokenEncryption.a().b(BleDevicePropCache.a().o(str));
    }

    public static void k(String str, String str2) {
        BleDevicePropCache.a().k(str, TokenEncryption.a().a(str2));
    }

    public static int t(String str) {
        return BleDevicePropCache.a().p(str);
    }

    public static void f(String str, int i2) {
        BleDevicePropCache.a().e(str, i2);
    }

    public static boolean u(String str) {
        return BleDevicePropCache.a().q(str);
    }

    public static void a(String str, boolean z2) {
        BleDevicePropCache.a().a(str, z2);
    }

    public static String v(String str) {
        return TokenEncryption.a().b(BleDevicePropCache.a().r(str));
    }

    public static byte[] w(String str) {
        String v2 = v(str);
        return TextUtils.isEmpty(v2) ? ByteUtils.b : ByteUtils.a(v2);
    }

    public static void l(String str, String str2) {
        BleDevicePropCache.a().l(str, TokenEncryption.a().a(str2));
    }

    public static void d(String str, byte[] bArr) {
        l(str, ByteUtils.d(bArr));
    }

    public static String x(String str) {
        return TokenEncryption.a().b(BleDevicePropCache.a().s(str));
    }

    public static void m(String str, String str2) {
        BleDevicePropCache.a().m(str, TokenEncryption.a().a(str2));
    }

    public static String y(String str) {
        return TokenEncryption.a().b(BleDevicePropCache.a().t(str));
    }

    public static void n(String str, String str2) {
        BleDevicePropCache.a().n(str, TokenEncryption.a().a(str2));
    }

    public static BleGattProfile z(String str) {
        return BleDevicePropCache.a().w(str);
    }

    public static void a(String str, BleGattProfile bleGattProfile) {
        BleDevicePropCache.a().a(str, bleGattProfile);
    }

    public static String o(String str, String str2) {
        return BleDevicePropCache.a().o(str, str2);
    }

    public static void a(String str, String str2, String str3) {
        BleDevicePropCache.a().a(str, str2, str3);
    }

    public static void a(String str, String str2, boolean z2) {
        BleDevicePropCache.a().b(str, str2, z2);
    }

    public static boolean b(String str, String str2, boolean z2) {
        return BleDevicePropCache.a().a(str, str2, z2);
    }

    public static ArrayList<BtDevice> b() {
        final ArrayList<BtDevice> arrayList = new ArrayList<>();
        BleDevicePropCache.a().a((BleDevicePropCache.IPropTraverse) new BleDevicePropCache.IPropTraverse() {
            public boolean a(String str, BleDeviceProp bleDeviceProp) {
                if (bleDeviceProp.getBoundStatus() != 1 || "xiaomi.ble.v1".equalsIgnoreCase(bleDeviceProp.getModel())) {
                    return false;
                }
                BtDevice btDevice = new BtDevice(str);
                BluetoothDeviceDecorator.a().a(btDevice);
                BluetoothDevicePermitLevelDecorator.a().a(btDevice);
                arrayList.add(btDevice);
                return false;
            }
        });
        return arrayList;
    }

    public static ArrayList<BtDevice> c() {
        final ArrayList<BtDevice> arrayList = new ArrayList<>();
        BleDevicePropCache.a().a((BleDevicePropCache.IPropTraverse) new BleDevicePropCache.IPropTraverse() {
            public boolean a(String str, BleDeviceProp bleDeviceProp) {
                if (bleDeviceProp.getExtra(BtConstants.f14140a, false)) {
                    BtDevice btDevice = new BtDevice(str);
                    BluetoothDeviceDecorator.a().a(btDevice);
                    arrayList.add(btDevice);
                }
                return false;
            }
        });
        return arrayList;
    }

    public static ArrayList<BtDevice> d() {
        final ArrayList<BtDevice> arrayList = new ArrayList<>();
        BleDevicePropCache.a().a((BleDevicePropCache.IPropTraverse) new BleDevicePropCache.IPropTraverse() {
            public boolean a(String str, BleDeviceProp bleDeviceProp) {
                if (bleDeviceProp.getBoundStatus() != 2) {
                    return false;
                }
                BtDevice btDevice = new BtDevice(str);
                BluetoothDeviceDecorator.a().a(btDevice);
                arrayList.add(btDevice);
                return false;
            }
        });
        return arrayList;
    }

    public static List<BtDevice> e() {
        final ArrayList arrayList = new ArrayList();
        BleDevicePropCache.a().a((BleDevicePropCache.IPropTraverse) new BleDevicePropCache.IPropTraverse() {
            public boolean a(String str, BleDeviceProp bleDeviceProp) {
                if (bleDeviceProp.getExtra(BtConstants.c, false)) {
                    BtDevice btDevice = new BtDevice(str);
                    BluetoothDeviceDecorator.a().a(btDevice);
                    arrayList.add(btDevice);
                }
                return false;
            }
        });
        return arrayList;
    }

    public static void p(String str, String str2) {
        B.put(str, str2);
    }

    public static String A(String str) {
        return B.get(str);
    }
}
