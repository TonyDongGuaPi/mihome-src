package com.mi.global.shop.newmodel.user.address;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewRegionItem implements Parcelable {
    public static final Parcelable.Creator<NewRegionItem> CREATOR = new Parcelable.Creator<NewRegionItem>() {
        public NewRegionItem createFromParcel(Parcel parcel) {
            return new NewRegionItem(parcel);
        }

        public NewRegionItem[] newArray(int i) {
            return new NewRegionItem[i];
        }
    };
    @SerializedName("can_cod")
    public int can_cod;
    @SerializedName("region_id")
    public String region_id;
    @SerializedName("region_name")
    public String region_name;
    @SerializedName("zipcode")
    public String zipcode;

    public int describeContents() {
        return 0;
    }

    public static NewRegionItem decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewRegionItem decode(ProtoReader protoReader) throws IOException {
        NewRegionItem newRegionItem = new NewRegionItem();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newRegionItem.region_id = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newRegionItem.region_name = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newRegionItem.zipcode = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        newRegionItem.can_cod = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newRegionItem;
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.region_id);
        parcel.writeString(this.region_name);
        parcel.writeString(this.zipcode);
        parcel.writeInt(this.can_cod);
    }

    public NewRegionItem() {
    }

    protected NewRegionItem(Parcel parcel) {
        this.region_id = parcel.readString();
        this.region_name = parcel.readString();
        this.zipcode = parcel.readString();
        this.can_cod = parcel.readInt();
    }
}
