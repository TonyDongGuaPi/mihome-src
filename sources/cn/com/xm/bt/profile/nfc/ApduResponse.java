package cn.com.xm.bt.profile.nfc;

import android.os.Parcel;
import android.os.Parcelable;
import cn.com.xm.bt.c.c;
import com.taobao.weex.el.parse.Operators;

public class ApduResponse implements Parcelable {
    public static final Parcelable.Creator<ApduResponse> CREATOR = new Parcelable.Creator<ApduResponse>() {
        /* renamed from: a */
        public ApduResponse createFromParcel(Parcel parcel) {
            return new ApduResponse(parcel);
        }

        /* renamed from: a */
        public ApduResponse[] newArray(int i) {
            return new ApduResponse[i];
        }
    };
    private final byte[] data;
    private final int len;

    public int describeContents() {
        return 0;
    }

    ApduResponse(byte[] bArr, int i) {
        this.data = bArr;
        this.len = i;
    }

    private ApduResponse(Parcel parcel) {
        this.data = parcel.createByteArray();
        this.len = parcel.readInt();
    }

    public byte[] getData() {
        return this.data;
    }

    public int getLen() {
        return this.len;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.data);
        parcel.writeInt(this.len);
    }

    public String toString() {
        return Operators.BLOCK_START_STR + "data=" + c.a(this.data) + ", len=" + this.len + Operators.BLOCK_END;
    }
}
