package com.mi.global.shop.newmodel.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewCartProperty implements Parcelable {
    public static final Parcelable.Creator<NewCartProperty> CREATOR = new Parcelable.Creator<NewCartProperty>() {
        public NewCartProperty createFromParcel(Parcel parcel) {
            return new NewCartProperty(parcel);
        }

        public NewCartProperty[] newArray(int i) {
            return new NewCartProperty[i];
        }
    };
    @SerializedName("actDate")
    public String actDate;
    @SerializedName("insurance")
    public NewCartInsurance insurance;
    @SerializedName("parent_itemId")
    public String parent_itemId;
    @SerializedName("source")
    public String source;

    public int describeContents() {
        return 0;
    }

    public static NewCartProperty decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewCartProperty decode(ProtoReader protoReader) throws IOException {
        NewCartProperty newCartProperty = new NewCartProperty();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newCartProperty.parent_itemId = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newCartProperty.source = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newCartProperty.actDate = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        newCartProperty.insurance = NewCartInsurance.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newCartProperty;
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.parent_itemId);
        parcel.writeString(this.source);
        parcel.writeString(this.actDate);
        parcel.writeParcelable(this.insurance, i);
    }

    public NewCartProperty() {
    }

    protected NewCartProperty(Parcel parcel) {
        this.parent_itemId = parcel.readString();
        this.source = parcel.readString();
        this.actDate = parcel.readString();
        this.insurance = (NewCartInsurance) parcel.readParcelable(NewCartInsurance.class.getClassLoader());
    }
}
