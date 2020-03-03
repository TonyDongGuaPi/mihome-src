package no.nordicsemi.android.support.v18.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class ScanFilter implements Parcelable {
    public static final Parcelable.Creator<ScanFilter> CREATOR = new Parcelable.Creator<ScanFilter>() {
        /* renamed from: a */
        public ScanFilter[] newArray(int i) {
            return new ScanFilter[i];
        }

        /* renamed from: a */
        public ScanFilter createFromParcel(Parcel parcel) {
            Builder builder = new Builder();
            if (parcel.readInt() == 1) {
                builder.a(parcel.readString());
            }
            if (parcel.readInt() == 1) {
                builder.b(parcel.readString());
            }
            if (parcel.readInt() == 1) {
                ParcelUuid parcelUuid = (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader());
                builder.a(parcelUuid);
                if (parcel.readInt() == 1) {
                    builder.a(parcelUuid, (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader()));
                }
            }
            if (parcel.readInt() == 1) {
                ParcelUuid parcelUuid2 = (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader());
                if (parcel.readInt() == 1) {
                    byte[] bArr = new byte[parcel.readInt()];
                    parcel.readByteArray(bArr);
                    if (parcel.readInt() == 0) {
                        builder.a(parcelUuid2, bArr);
                    } else {
                        byte[] bArr2 = new byte[parcel.readInt()];
                        parcel.readByteArray(bArr2);
                        builder.a(parcelUuid2, bArr, bArr2);
                    }
                }
            }
            int readInt = parcel.readInt();
            if (parcel.readInt() == 1) {
                byte[] bArr3 = new byte[parcel.readInt()];
                parcel.readByteArray(bArr3);
                if (parcel.readInt() == 0) {
                    builder.a(readInt, bArr3);
                } else {
                    byte[] bArr4 = new byte[parcel.readInt()];
                    parcel.readByteArray(bArr4);
                    builder.a(readInt, bArr3, bArr4);
                }
            }
            return builder.a();
        }
    };
    private static final ScanFilter k = new Builder().a();
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private final String f3182a;
    @Nullable
    private final String b;
    @Nullable
    private final ParcelUuid c;
    @Nullable
    private final ParcelUuid d;
    @Nullable
    private final ParcelUuid e;
    @Nullable
    private final byte[] f;
    @Nullable
    private final byte[] g;
    private final int h;
    @Nullable
    private final byte[] i;
    @Nullable
    private final byte[] j;

    public int describeContents() {
        return 0;
    }

    private ScanFilter(@Nullable String str, @Nullable String str2, @Nullable ParcelUuid parcelUuid, @Nullable ParcelUuid parcelUuid2, @Nullable ParcelUuid parcelUuid3, @Nullable byte[] bArr, @Nullable byte[] bArr2, int i2, @Nullable byte[] bArr3, @Nullable byte[] bArr4) {
        this.f3182a = str;
        this.c = parcelUuid;
        this.d = parcelUuid2;
        this.b = str2;
        this.e = parcelUuid3;
        this.f = bArr;
        this.g = bArr2;
        this.h = i2;
        this.i = bArr3;
        this.j = bArr4;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        int i3 = 1;
        parcel.writeInt(this.f3182a == null ? 0 : 1);
        if (this.f3182a != null) {
            parcel.writeString(this.f3182a);
        }
        parcel.writeInt(this.b == null ? 0 : 1);
        if (this.b != null) {
            parcel.writeString(this.b);
        }
        parcel.writeInt(this.c == null ? 0 : 1);
        if (this.c != null) {
            parcel.writeParcelable(this.c, i2);
            parcel.writeInt(this.d == null ? 0 : 1);
            if (this.d != null) {
                parcel.writeParcelable(this.d, i2);
            }
        }
        parcel.writeInt(this.e == null ? 0 : 1);
        if (this.e != null) {
            parcel.writeParcelable(this.e, i2);
            parcel.writeInt(this.f == null ? 0 : 1);
            if (this.f != null) {
                parcel.writeInt(this.f.length);
                parcel.writeByteArray(this.f);
                parcel.writeInt(this.g == null ? 0 : 1);
                if (this.g != null) {
                    parcel.writeInt(this.g.length);
                    parcel.writeByteArray(this.g);
                }
            }
        }
        parcel.writeInt(this.h);
        parcel.writeInt(this.i == null ? 0 : 1);
        if (this.i != null) {
            parcel.writeInt(this.i.length);
            parcel.writeByteArray(this.i);
            if (this.j == null) {
                i3 = 0;
            }
            parcel.writeInt(i3);
            if (this.j != null) {
                parcel.writeInt(this.j.length);
                parcel.writeByteArray(this.j);
            }
        }
    }

    @Nullable
    public String a() {
        return this.f3182a;
    }

    @Nullable
    public ParcelUuid b() {
        return this.c;
    }

    @Nullable
    public ParcelUuid c() {
        return this.d;
    }

    @Nullable
    public String d() {
        return this.b;
    }

    @Nullable
    public byte[] e() {
        return this.f;
    }

    @Nullable
    public byte[] f() {
        return this.g;
    }

    @Nullable
    public ParcelUuid g() {
        return this.e;
    }

    public int h() {
        return this.h;
    }

    @Nullable
    public byte[] i() {
        return this.i;
    }

    @Nullable
    public byte[] j() {
        return this.j;
    }

    public boolean a(@Nullable ScanResult scanResult) {
        if (scanResult == null) {
            return false;
        }
        BluetoothDevice a2 = scanResult.a();
        if (this.b != null && !this.b.equals(a2.getAddress())) {
            return false;
        }
        ScanRecord b2 = scanResult.b();
        if (b2 == null && (this.f3182a != null || this.c != null || this.i != null || this.f != null)) {
            return false;
        }
        if (this.f3182a != null && !this.f3182a.equals(b2.f())) {
            return false;
        }
        if (this.c != null && !a(this.c, this.d, b2.b())) {
            return false;
        }
        if (this.e != null && b2 != null && !a(this.f, this.g, b2.a(this.e))) {
            return false;
        }
        if (this.h < 0 || b2 == null || a(this.i, this.j, b2.a(this.h))) {
            return true;
        }
        return false;
    }

    private static boolean a(@Nullable ParcelUuid parcelUuid, @Nullable ParcelUuid parcelUuid2, @Nullable List<ParcelUuid> list) {
        UUID uuid;
        if (parcelUuid == null) {
            return true;
        }
        if (list == null) {
            return false;
        }
        for (ParcelUuid next : list) {
            if (parcelUuid2 == null) {
                uuid = null;
            } else {
                uuid = parcelUuid2.getUuid();
            }
            if (a(parcelUuid.getUuid(), uuid, next.getUuid())) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(@NonNull UUID uuid, @Nullable UUID uuid2, @NonNull UUID uuid3) {
        if (uuid2 == null) {
            return uuid.equals(uuid3);
        }
        if ((uuid.getLeastSignificantBits() & uuid2.getLeastSignificantBits()) != (uuid3.getLeastSignificantBits() & uuid2.getLeastSignificantBits())) {
            return false;
        }
        if ((uuid.getMostSignificantBits() & uuid2.getMostSignificantBits()) == (uuid2.getMostSignificantBits() & uuid3.getMostSignificantBits())) {
            return true;
        }
        return false;
    }

    private boolean a(@Nullable byte[] bArr, @Nullable byte[] bArr2, @Nullable byte[] bArr3) {
        if (bArr == null) {
            return bArr3 != null;
        }
        if (bArr3 == null || bArr3.length < bArr.length) {
            return false;
        }
        if (bArr2 == null) {
            for (int i2 = 0; i2 < bArr.length; i2++) {
                if (bArr3[i2] != bArr[i2]) {
                    return false;
                }
            }
            return true;
        }
        for (int i3 = 0; i3 < bArr.length; i3++) {
            if ((bArr2[i3] & bArr3[i3]) != (bArr2[i3] & bArr[i3])) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return "BluetoothLeScanFilter [deviceName=" + this.f3182a + ", deviceAddress=" + this.b + ", mUuid=" + this.c + ", uuidMask=" + this.d + ", serviceDataUuid=" + Objects.a((Object) this.e) + ", serviceData=" + Arrays.toString(this.f) + ", serviceDataMask=" + Arrays.toString(this.g) + ", manufacturerId=" + this.h + ", manufacturerData=" + Arrays.toString(this.i) + ", manufacturerDataMask=" + Arrays.toString(this.j) + Operators.ARRAY_END_STR;
    }

    public int hashCode() {
        return Objects.a(this.f3182a, this.b, Integer.valueOf(this.h), Integer.valueOf(Arrays.hashCode(this.i)), Integer.valueOf(Arrays.hashCode(this.j)), this.e, Integer.valueOf(Arrays.hashCode(this.f)), Integer.valueOf(Arrays.hashCode(this.g)), this.c, this.d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ScanFilter scanFilter = (ScanFilter) obj;
        if (!Objects.b(this.f3182a, scanFilter.f3182a) || !Objects.b(this.b, scanFilter.b) || this.h != scanFilter.h || !Objects.a(this.i, scanFilter.i) || !Objects.a(this.j, scanFilter.j) || !Objects.b(this.e, scanFilter.e) || !Objects.a(this.f, scanFilter.f) || !Objects.a(this.g, scanFilter.g) || !Objects.b(this.c, scanFilter.c) || !Objects.b(this.d, scanFilter.d)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean k() {
        return k.equals(this);
    }

    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        private String f3183a;
        private String b;
        private ParcelUuid c;
        private ParcelUuid d;
        private ParcelUuid e;
        private byte[] f;
        private byte[] g;
        private int h = -1;
        private byte[] i;
        private byte[] j;

        public Builder a(@Nullable String str) {
            this.f3183a = str;
            return this;
        }

        public Builder b(@Nullable String str) {
            if (str == null || BluetoothAdapter.checkBluetoothAddress(str)) {
                this.b = str;
                return this;
            }
            throw new IllegalArgumentException("invalid device address " + str);
        }

        public Builder a(@Nullable ParcelUuid parcelUuid) {
            this.c = parcelUuid;
            this.d = null;
            return this;
        }

        public Builder a(@Nullable ParcelUuid parcelUuid, @Nullable ParcelUuid parcelUuid2) {
            if (parcelUuid2 == null || parcelUuid != null) {
                this.c = parcelUuid;
                this.d = parcelUuid2;
                return this;
            }
            throw new IllegalArgumentException("uuid is null while uuidMask is not null!");
        }

        public Builder a(@Nullable ParcelUuid parcelUuid, @Nullable byte[] bArr) {
            if (parcelUuid == null && bArr != null) {
                throw new IllegalArgumentException("serviceDataUuid is null!");
            } else if (parcelUuid == null || bArr != null) {
                this.e = parcelUuid;
                this.f = bArr;
                this.g = null;
                return this;
            } else {
                throw new IllegalArgumentException("serviceData is null!");
            }
        }

        public Builder a(@Nullable ParcelUuid parcelUuid, @Nullable byte[] bArr, @Nullable byte[] bArr2) {
            if (parcelUuid != null || bArr == null) {
                if (bArr2 != null) {
                    if (bArr == null) {
                        throw new IllegalArgumentException("serviceData is null while serviceDataMask is not null");
                    } else if (bArr.length != bArr2.length) {
                        throw new IllegalArgumentException("size mismatch for service data and service data mask");
                    }
                }
                this.e = parcelUuid;
                this.f = bArr;
                this.g = bArr2;
                return this;
            }
            throw new IllegalArgumentException("serviceDataUuid is null");
        }

        public Builder a(int i2, @Nullable byte[] bArr) {
            if (bArr == null || i2 >= 0) {
                this.h = i2;
                this.i = bArr;
                this.j = null;
                return this;
            }
            throw new IllegalArgumentException("invalid manufacture id");
        }

        public Builder a(int i2, @Nullable byte[] bArr, @Nullable byte[] bArr2) {
            if (bArr == null || i2 >= 0) {
                if (bArr2 != null) {
                    if (bArr == null) {
                        throw new IllegalArgumentException("manufacturerData is null while manufacturerDataMask is not null");
                    } else if (bArr.length != bArr2.length) {
                        throw new IllegalArgumentException("size mismatch for manufacturerData and manufacturerDataMask");
                    }
                }
                this.h = i2;
                this.i = bArr;
                this.j = bArr2;
                return this;
            }
            throw new IllegalArgumentException("invalid manufacture id");
        }

        public ScanFilter a() {
            return new ScanFilter(this.f3183a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j);
        }
    }
}
