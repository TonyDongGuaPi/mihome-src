package com.xiaomi.smarthome.device;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.bluetooth.XmBluetoothDevice;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.core.entity.device.BtDevice;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.DeviceType;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.device.bluetooth.BleDeviceGroup;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthomedevice.R;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BleDevice extends Device {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14751a = "bluetooth";
    public static final Comparator<BleDevice> d = new Comparator<BleDevice>() {
        /* renamed from: a */
        public int compare(BleDevice bleDevice, BleDevice bleDevice2) {
            return BleCacheUtils.g(bleDevice2.mac) - BleCacheUtils.g(bleDevice.mac);
        }
    };
    protected MiotBleAdvPacket b;
    public boolean c;
    private byte[] e;

    public boolean hasShortcut() {
        return true;
    }

    public boolean isNoneOperatableDevice() {
        return false;
    }

    public boolean isOnlineAdvance() {
        return true;
    }

    protected BleDevice() {
        this.pid = Device.PID_BLUETOOTH;
    }

    public static BleDevice a(String str) {
        return BleDevicePool.a().d(str);
    }

    public static BleDevice a(BtDevice btDevice) {
        BleDevice a2 = a(btDevice.n());
        if (a2 != null) {
            a2.rssi = btDevice.r();
            a2.e = btDevice.c();
            a2.b = btDevice.b();
        }
        return a2;
    }

    public static BleDevice a(XmBluetoothDevice xmBluetoothDevice) {
        BleDevice a2 = a(xmBluetoothDevice.getAddress());
        if (a2 != null) {
            a2.name = xmBluetoothDevice.device.getName();
            a2.e = xmBluetoothDevice.scanRecord;
            a2.rssi = xmBluetoothDevice.rssi;
        }
        return a2;
    }

    public XmBluetoothDevice a() {
        XmBluetoothDevice xmBluetoothDevice = new XmBluetoothDevice();
        xmBluetoothDevice.device = BluetoothUtils.b(this.mac);
        xmBluetoothDevice.rssi = this.rssi;
        xmBluetoothDevice.scanRecord = this.e;
        return xmBluetoothDevice;
    }

    public BluetoothDevice b() {
        return BluetoothUtils.b(this.mac);
    }

    public byte[] c() {
        return this.e;
    }

    public MiotBleAdvPacket d() {
        return this.b;
    }

    public boolean e() {
        return !f();
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.did = str;
        }
    }

    public void a(boolean z) {
        this.isNew = z;
    }

    public void b(boolean z) {
        a(z);
        BLEDeviceManager.a(this.mac, z);
    }

    public boolean f() {
        return !TextUtils.isEmpty(this.did) && !TextUtils.equals(this.did, this.mac);
    }

    public String g() {
        return BleCacheUtils.i(this.mac);
    }

    public boolean h() {
        return DeviceType.b(this.model);
    }

    public boolean i() {
        if (!h()) {
            return false;
        }
        if (!k()) {
            b(true);
        }
        BleCacheUtils.a(this.mac, 1);
        return true;
    }

    public boolean j() {
        return f() && BleCacheUtils.n(this.mac) == 2;
    }

    public boolean k() {
        return h() && BleCacheUtils.n(this.mac) == 1;
    }

    public void l() {
        XmBluetoothManager.getInstance().disconnect(this.mac);
    }

    public boolean m() {
        return DeviceType.a(this.model) == 2;
    }

    public boolean n() {
        return this.c;
    }

    public boolean o() {
        return DeviceType.a(this.model) == 1;
    }

    public CharSequence getStatusDescription(Context context) {
        return this.mac;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name = " + XMStringUtils.i(this.name));
        sb.append(", did = " + XMStringUtils.i(this.did));
        sb.append(", mac = " + XMStringUtils.i(this.mac));
        sb.append(", model = " + XMStringUtils.i(this.model));
        sb.append(", permit = " + this.permitLevel);
        sb.append(", rssi = " + this.rssi);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof BleDevice) && super.equals(obj)) {
            return this.mac.equals(((BleDevice) obj).mac);
        }
        return false;
    }

    public int hashCode() {
        return (super.hashCode() * 31) + this.mac.hashCode();
    }

    public static String a(List<BleDevice> list) {
        StringBuilder sb = new StringBuilder();
        if (!ListUtils.a(list)) {
            for (BleDevice bleDevice : list) {
                sb.append(bleDevice.toString() + ", ");
            }
        }
        return sb.toString();
    }

    public boolean p() {
        return BluetoothUtils.c(this.mac);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
        r5 = r5.split(":");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(com.xiaomi.smarthome.device.Device r5) {
        /*
            java.lang.String r0 = ""
            if (r5 == 0) goto L_0x002b
            java.lang.String r5 = r5.mac
            boolean r1 = android.text.TextUtils.isEmpty(r5)
            if (r1 != 0) goto L_0x002b
            java.lang.String r1 = ":"
            java.lang.String[] r5 = r5.split(r1)
            int r1 = r5.length
            r2 = 2
            if (r1 < r2) goto L_0x002b
            java.lang.String r0 = "%s%s"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            int r4 = r1 + -2
            r4 = r5[r4]
            r2[r3] = r4
            r3 = 1
            int r1 = r1 - r3
            r5 = r5[r1]
            r2[r3] = r5
            java.lang.String r0 = java.lang.String.format(r0, r2)
        L_0x002b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.BleDevice.a(com.xiaomi.smarthome.device.Device):java.lang.String");
    }

    public static void b(List<BleDevice> list) {
        if (!ListUtils.a(list)) {
            Collections.sort(list, d);
        }
    }

    public String q() {
        if (this instanceof BleDeviceGroup) {
            return s();
        }
        return String.format("%s(%s)", new Object[]{getName(), a((Device) this)});
    }

    public int r() {
        if (!(this instanceof BleDeviceGroup) || "xiaomi.ble.v1".equalsIgnoreCase(this.model)) {
            return 1;
        }
        return ((BleDeviceGroup) this).r();
    }

    public String s() {
        return c(this.model);
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (DeviceUtils.d(str)) {
            return XMStringUtils.a(CommonApplication.getAppContext(), R.string.mi_key_title);
        }
        if ("yeelink.light.ble1".equalsIgnoreCase(str)) {
            return XMStringUtils.a(CommonApplication.getAppContext(), R.string.yeelight_name);
        }
        if ("zimi.powerbank.v1".equalsIgnoreCase(str)) {
            return XMStringUtils.a(CommonApplication.getAppContext(), R.string.zimi_power_name);
        }
        if ("xiaomi.ble.v1".equalsIgnoreCase(str)) {
            return XMStringUtils.a(CommonApplication.getAppContext(), R.string.xiaomi_bracelet);
        }
        PluginRecord d2 = CoreApi.a().d(str);
        if (d2 != null) {
            return d2.p();
        }
        BluetoothLog.b(String.format("getDefaultName: there is no plugin record for model %s", new Object[]{str}));
        return "";
    }

    public CharSequence getName() {
        if (TextUtils.isEmpty(this.name) || "null".equalsIgnoreCase(this.name)) {
            return s();
        }
        return this.name;
    }

    public String t() {
        return f(this.model);
    }

    public static PluginDeviceInfo d(String str) {
        PluginRecord d2 = CoreApi.a().d(str);
        if (d2 != null) {
            return d2.c();
        }
        return null;
    }

    public int u() {
        PluginDeviceInfo d2 = d(this.model);
        if (d2 != null) {
            return d2.A();
        }
        return 0;
    }

    public static int e(String str) {
        PluginDeviceInfo d2 = d(str);
        if (d2 == null) {
            return 0;
        }
        try {
            return Integer.parseInt(d2.B());
        } catch (Exception e2) {
            BluetoothLog.a((Throwable) e2);
            return 0;
        }
    }

    public static String f(String str) {
        PluginDeviceInfo d2 = d(str);
        return d2 != null ? d2.o() : "";
    }

    public int v() {
        return g(this.model);
    }

    public static int g(String str) {
        PluginDeviceInfo d2 = d(str);
        if (d2 != null) {
            return d2.d();
        }
        return 0;
    }

    public boolean canBeShared() {
        if ("soocare.toothbrush.x3".equalsIgnoreCase(this.model) || "hmpace.watch.v1".equalsIgnoreCase(this.model) || DeviceType.a(this.model) != 2 || !j()) {
            return false;
        }
        return super.canBeShared();
    }
}
