package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.util.SparseIntArray;

@RestrictTo({RestrictTo.Scope.LIBRARY})
class VersionedParcelParcel extends VersionedParcel {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f487a = false;
    private static final String b = "VersionedParcelParcel";
    private final SparseIntArray c;
    private final Parcel d;
    private final int e;
    private final int f;
    private final String g;
    private int h;
    private int i;

    VersionedParcelParcel(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "");
    }

    VersionedParcelParcel(Parcel parcel, int i2, int i3, String str) {
        this.c = new SparseIntArray();
        this.h = -1;
        this.i = 0;
        this.d = parcel;
        this.e = i2;
        this.f = i3;
        this.i = this.e;
        this.g = str;
    }

    private int d(int i2) {
        while (this.i < this.f) {
            this.d.setDataPosition(this.i);
            int readInt = this.d.readInt();
            int readInt2 = this.d.readInt();
            this.i += readInt;
            if (readInt2 == i2) {
                return this.d.dataPosition();
            }
        }
        return -1;
    }

    public boolean b(int i2) {
        int d2 = d(i2);
        if (d2 == -1) {
            return false;
        }
        this.d.setDataPosition(d2);
        return true;
    }

    public void c(int i2) {
        b();
        this.h = i2;
        this.c.put(i2, this.d.dataPosition());
        a(0);
        a(i2);
    }

    public void b() {
        if (this.h >= 0) {
            int i2 = this.c.get(this.h);
            int dataPosition = this.d.dataPosition();
            this.d.setDataPosition(i2);
            this.d.writeInt(dataPosition - i2);
            this.d.setDataPosition(dataPosition);
        }
    }

    /* access modifiers changed from: protected */
    public VersionedParcel c() {
        Parcel parcel = this.d;
        int dataPosition = this.d.dataPosition();
        int i2 = this.i == this.e ? this.f : this.i;
        return new VersionedParcelParcel(parcel, dataPosition, i2, this.g + "  ");
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            this.d.writeInt(bArr.length);
            this.d.writeByteArray(bArr);
            return;
        }
        this.d.writeInt(-1);
    }

    public void a(byte[] bArr, int i2, int i3) {
        if (bArr != null) {
            this.d.writeInt(bArr.length);
            this.d.writeByteArray(bArr, i2, i3);
            return;
        }
        this.d.writeInt(-1);
    }

    public void a(int i2) {
        this.d.writeInt(i2);
    }

    public void a(long j) {
        this.d.writeLong(j);
    }

    public void a(float f2) {
        this.d.writeFloat(f2);
    }

    public void a(double d2) {
        this.d.writeDouble(d2);
    }

    public void a(String str) {
        this.d.writeString(str);
    }

    public void a(IBinder iBinder) {
        this.d.writeStrongBinder(iBinder);
    }

    public void a(Parcelable parcelable) {
        this.d.writeParcelable(parcelable, 0);
    }

    public void a(boolean z) {
        this.d.writeInt(z ? 1 : 0);
    }

    public void a(IInterface iInterface) {
        this.d.writeStrongInterface(iInterface);
    }

    public void a(Bundle bundle) {
        this.d.writeBundle(bundle);
    }

    public int d() {
        return this.d.readInt();
    }

    public long e() {
        return this.d.readLong();
    }

    public float f() {
        return this.d.readFloat();
    }

    public double g() {
        return this.d.readDouble();
    }

    public String h() {
        return this.d.readString();
    }

    public IBinder i() {
        return this.d.readStrongBinder();
    }

    public byte[] j() {
        int readInt = this.d.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        this.d.readByteArray(bArr);
        return bArr;
    }

    public <T extends Parcelable> T k() {
        return this.d.readParcelable(getClass().getClassLoader());
    }

    public Bundle l() {
        return this.d.readBundle(getClass().getClassLoader());
    }

    public boolean m() {
        return this.d.readInt() != 0;
    }
}
