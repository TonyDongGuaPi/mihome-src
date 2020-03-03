package cn.com.xm.bt.profile.nfc;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;

public class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() {
        /* renamed from: a */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        /* renamed from: a */
        public a[] newArray(int i) {
            return new a[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private byte[] f585a;
    private int b;
    private boolean c;

    public int describeContents() {
        return 0;
    }

    public a(byte[] bArr, int i, boolean z) {
        this.f585a = bArr;
        this.b = i;
        this.c = z;
    }

    private a(Parcel parcel) {
        this.f585a = parcel.createByteArray();
        this.b = parcel.readInt();
        this.c = parcel.readByte() != 0;
    }

    public byte[] a() {
        return this.f585a;
    }

    public int b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public String toString() {
        return "ApduRequest{data=" + Arrays.toString(this.f585a) + ", dataLen=" + this.b + ", encrypt=" + this.c + Operators.BLOCK_END;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.f585a);
        parcel.writeInt(this.b);
        parcel.writeByte(this.c ? (byte) 1 : 0);
    }
}
