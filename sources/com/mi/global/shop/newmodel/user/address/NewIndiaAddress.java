package com.mi.global.shop.newmodel.user.address;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewIndiaAddress implements Parcelable {
    public static final Parcelable.Creator<NewIndiaAddress> CREATOR = new Parcelable.Creator<NewIndiaAddress>() {
        public NewIndiaAddress createFromParcel(Parcel parcel) {
            return new NewIndiaAddress(parcel);
        }

        public NewIndiaAddress[] newArray(int i) {
            return new NewIndiaAddress[i];
        }
    };
    @SerializedName("addr")
    public String addr;
    @SerializedName("city")
    public String city;
    @SerializedName("landmark")
    public String landmark;

    public int describeContents() {
        return 0;
    }

    public static NewIndiaAddress decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewIndiaAddress decode(ProtoReader protoReader) throws IOException {
        NewIndiaAddress newIndiaAddress = new NewIndiaAddress();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newIndiaAddress.addr = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newIndiaAddress.landmark = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newIndiaAddress.city = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newIndiaAddress;
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.addr);
        parcel.writeString(this.landmark);
        parcel.writeString(this.city);
    }

    public NewIndiaAddress() {
    }

    protected NewIndiaAddress(Parcel parcel) {
        this.addr = parcel.readString();
        this.landmark = parcel.readString();
        this.city = parcel.readString();
    }
}
