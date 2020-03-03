package com.mi.global.shop.newmodel.user.address;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewSimpleRegionItem implements Parcelable {
    public static final Parcelable.Creator<NewSimpleRegionItem> CREATOR = new Parcelable.Creator<NewSimpleRegionItem>() {
        public NewSimpleRegionItem createFromParcel(Parcel parcel) {
            return new NewSimpleRegionItem(parcel);
        }

        public NewSimpleRegionItem[] newArray(int i) {
            return new NewSimpleRegionItem[i];
        }
    };
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;

    public int describeContents() {
        return 0;
    }

    public static NewSimpleRegionItem decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewSimpleRegionItem decode(ProtoReader protoReader) throws IOException {
        NewSimpleRegionItem newSimpleRegionItem = new NewSimpleRegionItem();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newSimpleRegionItem.id = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newSimpleRegionItem.name = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newSimpleRegionItem;
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.name);
    }

    public NewSimpleRegionItem() {
    }

    protected NewSimpleRegionItem(Parcel parcel) {
        this.id = parcel.readString();
        this.name = parcel.readString();
    }
}
