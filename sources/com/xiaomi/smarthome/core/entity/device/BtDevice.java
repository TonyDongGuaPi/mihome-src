package com.xiaomi.smarthome.core.entity.device;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.DeviceType;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import java.util.List;

public class BtDevice extends Device {
    public static final Parcelable.Creator<BtDevice> CREATOR = new Parcelable.Creator<BtDevice>() {
        /* renamed from: a */
        public BtDevice createFromParcel(Parcel parcel) {
            return new BtDevice(parcel);
        }

        /* renamed from: a */
        public BtDevice[] newArray(int i) {
            return new BtDevice[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f13971a = 0;
    public static final int b = 1;
    public static final int c = 2;
    protected MiotBleAdvPacket d;
    private byte[] e;
    private int f;

    public int f() {
        return 6;
    }

    public BtDevice(String str) {
        d(str);
        a(str);
        c(6);
    }

    public BtDevice(Device device) {
        d(device.n());
        a(device.k());
        c(6);
        c(device.m());
        e(device.s());
        b(device.l());
        d(device.S());
        m(device.D());
        d(device.p());
        l(device.C());
        n(device.E());
        v(device.R());
    }

    public BtDevice(BluetoothSearchResult bluetoothSearchResult) {
        c(bluetoothSearchResult.k());
        d(bluetoothSearchResult.g());
        a(bluetoothSearchResult.g());
        f(bluetoothSearchResult.h());
        a(bluetoothSearchResult.i());
        c(6);
        a(bluetoothSearchResult.j());
    }

    public void a(BtDevice btDevice) {
        c(btDevice.m());
        a(btDevice.k());
        f(btDevice.r());
        a(btDevice.c());
        a(btDevice.a());
    }

    public int a() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public MiotBleAdvPacket b() {
        return this.d;
    }

    public void a(MiotBleAdvPacket miotBleAdvPacket) {
        this.d = miotBleAdvPacket;
    }

    public BtDevice(Parcel parcel) {
        super(parcel);
        this.e = parcel.createByteArray();
        this.f = parcel.readInt();
        this.d = (MiotBleAdvPacket) parcel.readParcelable(MiotBleAdvPacket.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeByteArray(this.e);
        parcel.writeInt(this.f);
        parcel.writeParcelable(this.d, 0);
    }

    public byte[] c() {
        return this.e;
    }

    public void a(byte[] bArr) {
        this.e = bArr;
    }

    public boolean d() {
        return DeviceType.b(l());
    }

    public boolean e() {
        return n().equals(k()) || BluetoothCache.n(n()) != 2;
    }

    public void g() {
        b(1);
    }

    public void b(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
                BluetoothCache.d(n(), i);
                return;
            default:
                BluetoothLog.e("invalid boundStatus " + i);
                return;
        }
    }

    public String toString() {
        return "BtDevice{name=" + m() + ", mac=" + n() + ", model=" + l() + ", did=" + k() + ", prop=" + E() + Operators.BLOCK_END;
    }

    public static String a(List<Device> list) {
        StringBuilder sb = new StringBuilder();
        if (!ListUtils.a(list)) {
            for (Device device : list) {
                sb.append(String.format("\n>>> %s", new Object[]{device.toString()}));
            }
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BtDevice)) {
            return false;
        }
        return n().equals(((BtDevice) obj).n());
    }

    public int hashCode() {
        return n().hashCode();
    }

    public boolean a(Device device) {
        return equals(device);
    }
}
