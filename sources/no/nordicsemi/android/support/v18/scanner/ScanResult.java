package no.nordicsemi.android.support.v18.scanner;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.taobao.weex.el.parse.Operators;

public final class ScanResult implements Parcelable {
    public static final Parcelable.Creator<ScanResult> CREATOR = new Parcelable.Creator<ScanResult>() {
        /* renamed from: a */
        public ScanResult createFromParcel(Parcel parcel) {
            return new ScanResult(parcel);
        }

        /* renamed from: a */
        public ScanResult[] newArray(int i) {
            return new ScanResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f3185a = 0;
    public static final int b = 2;
    public static final int c = 0;
    public static final int d = 255;
    public static final int e = 127;
    public static final int f = 0;
    static final int g = 16;
    static final int h = 1;
    @NonNull
    private BluetoothDevice i;
    @Nullable
    private ScanRecord j;
    private int k;
    private long l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;

    public int describeContents() {
        return 0;
    }

    public ScanResult(@NonNull BluetoothDevice bluetoothDevice, @Nullable ScanRecord scanRecord, int i2, long j2) {
        this.i = bluetoothDevice;
        this.j = scanRecord;
        this.k = i2;
        this.l = j2;
        this.m = 17;
        this.n = 1;
        this.o = 0;
        this.p = 255;
        this.q = 127;
        this.r = 0;
    }

    public ScanResult(@NonNull BluetoothDevice bluetoothDevice, int i2, int i3, int i4, int i5, int i6, int i7, int i8, @Nullable ScanRecord scanRecord, long j2) {
        this.i = bluetoothDevice;
        this.m = i2;
        this.n = i3;
        this.o = i4;
        this.p = i5;
        this.q = i6;
        this.k = i7;
        this.r = i8;
        this.j = scanRecord;
        this.l = j2;
    }

    private ScanResult(Parcel parcel) {
        a(parcel);
    }

    public void writeToParcel(Parcel parcel, int i2) {
        this.i.writeToParcel(parcel, i2);
        if (this.j != null) {
            parcel.writeInt(1);
            parcel.writeByteArray(this.j.g());
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.k);
        parcel.writeLong(this.l);
        parcel.writeInt(this.m);
        parcel.writeInt(this.n);
        parcel.writeInt(this.o);
        parcel.writeInt(this.p);
        parcel.writeInt(this.q);
        parcel.writeInt(this.r);
    }

    private void a(Parcel parcel) {
        this.i = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(parcel);
        if (parcel.readInt() == 1) {
            this.j = ScanRecord.a(parcel.createByteArray());
        }
        this.k = parcel.readInt();
        this.l = parcel.readLong();
        this.m = parcel.readInt();
        this.n = parcel.readInt();
        this.o = parcel.readInt();
        this.p = parcel.readInt();
        this.q = parcel.readInt();
        this.r = parcel.readInt();
    }

    @NonNull
    public BluetoothDevice a() {
        return this.i;
    }

    @Nullable
    public ScanRecord b() {
        return this.j;
    }

    public int c() {
        return this.k;
    }

    public long d() {
        return this.l;
    }

    public boolean e() {
        return (this.m & 16) != 0;
    }

    public boolean f() {
        return (this.m & 1) != 0;
    }

    public int g() {
        return (this.m >> 5) & 3;
    }

    public int h() {
        return this.n;
    }

    public int i() {
        return this.o;
    }

    public int j() {
        return this.p;
    }

    public int k() {
        return this.q;
    }

    public int l() {
        return this.r;
    }

    public int hashCode() {
        return Objects.a(this.i, Integer.valueOf(this.k), this.j, Long.valueOf(this.l), Integer.valueOf(this.m), Integer.valueOf(this.n), Integer.valueOf(this.o), Integer.valueOf(this.p), Integer.valueOf(this.q), Integer.valueOf(this.r));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ScanResult scanResult = (ScanResult) obj;
        if (Objects.b(this.i, scanResult.i) && this.k == scanResult.k && Objects.b(this.j, scanResult.j) && this.l == scanResult.l && this.m == scanResult.m && this.n == scanResult.n && this.o == scanResult.o && this.p == scanResult.p && this.q == scanResult.q && this.r == scanResult.r) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "ScanResult{device=" + this.i + ", scanRecord=" + Objects.a((Object) this.j) + ", rssi=" + this.k + ", timestampNanos=" + this.l + ", eventType=" + this.m + ", primaryPhy=" + this.n + ", secondaryPhy=" + this.o + ", advertisingSid=" + this.p + ", txPower=" + this.q + ", periodicAdvertisingInterval=" + this.r + Operators.BLOCK_END;
    }
}
