package com.mi.global.shop.newmodel.order;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewAddrSimple implements Parcelable {
    public static final Parcelable.Creator<NewAddrSimple> CREATOR = new Parcelable.Creator<NewAddrSimple>() {
        public NewAddrSimple createFromParcel(Parcel parcel) {
            return new NewAddrSimple(parcel);
        }

        public NewAddrSimple[] newArray(int i) {
            return new NewAddrSimple[i];
        }
    };
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;

    public int describeContents() {
        return 0;
    }

    public static NewAddrSimple decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewAddrSimple decode(ProtoReader protoReader) throws IOException {
        NewAddrSimple newAddrSimple = new NewAddrSimple();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newAddrSimple.id = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newAddrSimple.name = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newAddrSimple;
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.name);
    }

    public NewAddrSimple() {
    }

    protected NewAddrSimple(Parcel parcel) {
        this.id = parcel.readString();
        this.name = parcel.readString();
    }
}
