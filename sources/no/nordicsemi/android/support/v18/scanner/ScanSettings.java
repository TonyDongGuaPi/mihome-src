package no.nordicsemi.android.support.v18.scanner;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public final class ScanSettings implements Parcelable {
    public static final Parcelable.Creator<ScanSettings> CREATOR = new Parcelable.Creator<ScanSettings>() {
        /* renamed from: a */
        public ScanSettings[] newArray(int i) {
            return new ScanSettings[i];
        }

        /* renamed from: a */
        public ScanSettings createFromParcel(Parcel parcel) {
            return new ScanSettings(parcel);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final long f3186a = 10000;
    public static final long b = 10000;
    public static final int c = -1;
    public static final int d = 0;
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 1;
    public static final int h = 2;
    public static final int i = 4;
    public static final int j = 1;
    public static final int k = 2;
    public static final int l = 3;
    public static final int m = 1;
    public static final int n = 2;
    public static final int o = 255;
    private long A;
    private boolean B;
    private int C;
    private final long p;
    private final long q;
    private int r;
    private int s;
    private long t;
    private int u;
    private int v;
    private boolean w;
    private boolean x;
    private boolean y;
    private long z;

    public int describeContents() {
        return 0;
    }

    public int a() {
        return this.r;
    }

    public int b() {
        return this.s;
    }

    public int c() {
        return this.u;
    }

    public int d() {
        return this.v;
    }

    public boolean e() {
        return this.w;
    }

    public boolean f() {
        return this.x;
    }

    public boolean g() {
        return this.y;
    }

    /* access modifiers changed from: package-private */
    public void h() {
        this.y = false;
    }

    public long i() {
        return this.z;
    }

    public long j() {
        return this.A;
    }

    public boolean k() {
        return this.B;
    }

    public int l() {
        return this.C;
    }

    public long m() {
        return this.t;
    }

    private ScanSettings(int i2, int i3, long j2, int i4, int i5, boolean z2, int i6, boolean z3, boolean z4, boolean z5, long j3, long j4, long j5, long j6) {
        this.r = i2;
        this.s = i3;
        this.t = j2;
        this.v = i5;
        this.u = i4;
        this.B = z2;
        this.C = i6;
        this.w = z3;
        this.x = z4;
        this.y = z5;
        this.z = 1000000 * j3;
        this.A = j4;
        this.p = j5;
        this.q = j6;
    }

    private ScanSettings(Parcel parcel) {
        this.r = parcel.readInt();
        this.s = parcel.readInt();
        this.t = parcel.readLong();
        this.u = parcel.readInt();
        this.v = parcel.readInt();
        boolean z2 = false;
        this.B = parcel.readInt() != 0;
        this.C = parcel.readInt();
        this.w = parcel.readInt() == 1;
        this.x = parcel.readInt() == 1 ? true : z2;
        this.p = parcel.readLong();
        this.q = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.r);
        parcel.writeInt(this.s);
        parcel.writeLong(this.t);
        parcel.writeInt(this.u);
        parcel.writeInt(this.v);
        parcel.writeInt(this.B ? 1 : 0);
        parcel.writeInt(this.C);
        parcel.writeInt(this.w ? 1 : 0);
        parcel.writeInt(this.x ? 1 : 0);
        parcel.writeLong(this.p);
        parcel.writeLong(this.q);
    }

    public boolean n() {
        return this.q > 0 && this.p > 0;
    }

    public long o() {
        return this.q;
    }

    public long p() {
        return this.p;
    }

    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        private int f3187a = 0;
        private int b = 1;
        private long c = 0;
        private int d = 1;
        private int e = 3;
        private boolean f = true;
        private int g = 255;
        private boolean h = true;
        private boolean i = true;
        private boolean j = true;
        private long k = 10000;
        private long l = 10000;
        private long m = 0;
        private long n = 0;

        private boolean f(int i2) {
            return i2 == 1 || i2 == 2 || i2 == 4 || i2 == 6;
        }

        @NonNull
        public Builder a(int i2) {
            if (i2 < -1 || i2 > 2) {
                throw new IllegalArgumentException("invalid scan mode " + i2);
            }
            this.f3187a = i2;
            return this;
        }

        @NonNull
        public Builder b(int i2) {
            if (f(i2)) {
                this.b = i2;
                return this;
            }
            throw new IllegalArgumentException("invalid callback type - " + i2);
        }

        @NonNull
        public Builder a(long j2) {
            if (j2 >= 0) {
                this.c = j2;
                return this;
            }
            throw new IllegalArgumentException("reportDelay must be > 0");
        }

        @NonNull
        public Builder c(int i2) {
            if (i2 < 1 || i2 > 3) {
                throw new IllegalArgumentException("invalid numOfMatches " + i2);
            }
            this.e = i2;
            return this;
        }

        @NonNull
        public Builder d(int i2) {
            if (i2 < 1 || i2 > 2) {
                throw new IllegalArgumentException("invalid matchMode " + i2);
            }
            this.d = i2;
            return this;
        }

        @NonNull
        public Builder a(boolean z) {
            this.f = z;
            return this;
        }

        @NonNull
        public Builder e(int i2) {
            this.g = i2;
            return this;
        }

        @NonNull
        public Builder b(boolean z) {
            this.h = z;
            return this;
        }

        @NonNull
        public Builder c(boolean z) {
            this.i = z;
            return this;
        }

        @NonNull
        public Builder d(boolean z) {
            this.j = z;
            return this;
        }

        @NonNull
        public Builder a(long j2, long j3) {
            if (j2 <= 0 || j3 <= 0) {
                throw new IllegalArgumentException("maxDeviceAgeMillis and taskIntervalMillis must be > 0");
            }
            this.k = j2;
            this.l = j3;
            return this;
        }

        @NonNull
        public Builder b(long j2, long j3) {
            if (j2 <= 0 || j3 <= 0) {
                throw new IllegalArgumentException("scanInterval and restInterval must be > 0");
            }
            this.n = j2;
            this.m = j3;
            return this;
        }

        @NonNull
        public ScanSettings a() {
            if (this.m == 0 && this.n == 0) {
                b();
            }
            return new ScanSettings(this.f3187a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.n, this.m);
        }

        private void b() {
            switch (this.f3187a) {
                case 1:
                    this.n = 2000;
                    this.m = 3000;
                    return;
                case 2:
                    this.n = 0;
                    this.m = 0;
                    return;
                default:
                    this.n = 500;
                    this.m = 4500;
                    return;
            }
        }
    }
}
