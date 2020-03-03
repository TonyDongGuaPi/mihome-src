package com.xiaomi.smarthome.library.bluetooth.search;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;

public class BluetoothSearchResult implements Parcelable {
    public static final Parcelable.Creator<BluetoothSearchResult> CREATOR = new Parcelable.Creator<BluetoothSearchResult>() {
        /* renamed from: a */
        public BluetoothSearchResult createFromParcel(Parcel parcel) {
            return new BluetoothSearchResult(parcel);
        }

        /* renamed from: a */
        public BluetoothSearchResult[] newArray(int i) {
            return new BluetoothSearchResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private BluetoothDevice f18537a;
    private int b;
    private byte[] c;
    private int d;
    private String e;

    private String c(int i) {
        switch (i) {
            case 10:
                return "BOND_NONE";
            case 11:
                return "BOND_BONDING";
            case 12:
                return "BOND_BONDED";
            default:
                return "unknown";
        }
    }

    public int describeContents() {
        return 0;
    }

    public BluetoothSearchResult() {
    }

    public BluetoothSearchResult(BluetoothDevice bluetoothDevice) {
        this.f18537a = bluetoothDevice;
    }

    public BluetoothSearchResult(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        this.f18537a = bluetoothDevice;
        this.b = i;
        this.c = bArr;
    }

    public void a() {
        this.d = 2;
    }

    public boolean b() {
        return this.d == 2;
    }

    public void c() {
        this.d = 1;
    }

    public boolean d() {
        return this.d == 1;
    }

    public void e() {
        if (this.f18537a != null) {
            try {
                this.e = this.f18537a.getName();
            } catch (Exception e2) {
                e2.printStackTrace();
                this.e = "";
            }
        } else {
            this.e = "";
        }
    }

    public BluetoothDevice f() {
        return this.f18537a;
    }

    public void a(BluetoothDevice bluetoothDevice) {
        this.f18537a = bluetoothDevice;
    }

    public String g() {
        return this.f18537a != null ? this.f18537a.getAddress() : "";
    }

    public int h() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public byte[] i() {
        return this.c;
    }

    public void a(byte[] bArr) {
        this.c = bArr;
    }

    public int j() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public String k() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name = " + this.e);
        sb.append(", mac = " + this.f18537a.getAddress());
        return sb.toString();
    }

    private String d(int i) {
        switch (i) {
            case 0:
                return "disconnected";
            case 1:
                return "connecting";
            case 2:
                return "connected";
            case 3:
                return "disconnecting";
            default:
                return String.format("unknonw %d", new Object[]{Integer.valueOf(i)});
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f18537a, 0);
        parcel.writeInt(this.b);
        parcel.writeByteArray(this.c);
        parcel.writeInt(this.d);
        parcel.writeString(this.e);
    }

    public BluetoothSearchResult(Parcel parcel) {
        this.f18537a = (BluetoothDevice) parcel.readParcelable(BluetoothDevice.class.getClassLoader());
        this.b = parcel.readInt();
        this.c = parcel.createByteArray();
        this.d = parcel.readInt();
        this.e = parcel.readString();
    }
}
