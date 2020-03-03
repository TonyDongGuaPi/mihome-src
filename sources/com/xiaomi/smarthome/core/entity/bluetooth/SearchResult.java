package com.xiaomi.smarthome.core.entity.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;

public class SearchResult implements Parcelable {
    public static final Parcelable.Creator<SearchResult> CREATOR = new Parcelable.Creator<SearchResult>() {
        /* renamed from: a */
        public SearchResult createFromParcel(Parcel parcel) {
            return new SearchResult(parcel);
        }

        /* renamed from: a */
        public SearchResult[] newArray(int i) {
            return new SearchResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public BluetoothDevice f13969a;
    public int b;
    public byte[] c;
    public int d;
    public String e;

    public int describeContents() {
        return 0;
    }

    public SearchResult() {
    }

    public SearchResult(BluetoothSearchResult bluetoothSearchResult) {
        this.f13969a = bluetoothSearchResult.f();
        this.b = bluetoothSearchResult.h();
        this.c = bluetoothSearchResult.i();
        this.d = bluetoothSearchResult.j();
        this.e = bluetoothSearchResult.k();
    }

    public SearchResult(BluetoothDevice bluetoothDevice, int i) {
        this.f13969a = bluetoothDevice;
        this.d = i;
    }

    public SearchResult(BluetoothDevice bluetoothDevice, int i, byte[] bArr, int i2) {
        this.f13969a = bluetoothDevice;
        this.b = i;
        this.c = bArr;
        this.d = i2;
    }

    public String a() {
        if (this.f13969a != null) {
            return this.f13969a.getAddress();
        }
        return null;
    }

    public BluetoothDevice b() {
        return this.f13969a;
    }

    public void a(BluetoothDevice bluetoothDevice) {
        this.f13969a = bluetoothDevice;
    }

    public int c() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public byte[] d() {
        return this.c;
    }

    public void a(byte[] bArr) {
        this.c = bArr;
    }

    public int e() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public String f() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name = " + this.e);
        sb.append(", mac = " + this.f13969a.getAddress());
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f13969a, 0);
        parcel.writeInt(this.b);
        parcel.writeByteArray(this.c);
        parcel.writeInt(this.d);
        parcel.writeString(this.e);
    }

    public SearchResult(Parcel parcel) {
        this.f13969a = (BluetoothDevice) parcel.readParcelable(BluetoothDevice.class.getClassLoader());
        this.b = parcel.readInt();
        this.c = parcel.createByteArray();
        this.d = parcel.readInt();
        this.e = parcel.readString();
    }
}
