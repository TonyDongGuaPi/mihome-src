package a.a.a;

import android.os.Parcel;
import android.os.Parcelable;

public class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    public final String f370a;
    public final String b;
    public final boolean c;
    public final boolean d;

    public class a implements Parcelable.Creator<b> {
        /* renamed from: a */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        /* renamed from: a */
        public b[] newArray(int i) {
            return new b[i];
        }
    }

    public b(Parcel parcel) {
        this.f370a = parcel.readString();
        this.b = parcel.readString();
        boolean z = false;
        this.c = parcel.readByte() != 0;
        this.d = parcel.readByte() != 0 ? true : z;
    }

    public b(String str, Object obj, boolean z) {
        this(str, String.valueOf(obj), z, false);
    }

    public b(String str, Object obj, boolean z, boolean z2) {
        this.f370a = str;
        this.b = String.valueOf(obj);
        this.c = z;
        this.d = z2;
    }

    public String a() {
        return this.f370a;
    }

    public String b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return this.b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f370a);
        parcel.writeString(this.b);
        parcel.writeByte(this.c ? (byte) 1 : 0);
        parcel.writeByte(this.d ? (byte) 1 : 0);
    }
}
