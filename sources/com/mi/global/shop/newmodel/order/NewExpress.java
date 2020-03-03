package com.mi.global.shop.newmodel.order;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewExpress implements Parcelable {
    public static final Parcelable.Creator<NewExpress> CREATOR = new Parcelable.Creator<NewExpress>() {
        public NewExpress createFromParcel(Parcel parcel) {
            return new NewExpress(parcel);
        }

        public NewExpress[] newArray(int i) {
            return new NewExpress[i];
        }
    };
    @SerializedName("express_id")
    public String express_id;
    @SerializedName("express_name")
    public String express_name;
    @SerializedName("express_sn")
    public String express_sn;

    public int describeContents() {
        return 0;
    }

    public static NewExpress decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewExpress decode(ProtoReader protoReader) throws IOException {
        NewExpress newExpress = new NewExpress();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newExpress.express_id = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newExpress.express_sn = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newExpress.express_name = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newExpress;
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.express_id);
        parcel.writeString(this.express_sn);
        parcel.writeString(this.express_name);
    }

    public NewExpress() {
    }

    protected NewExpress(Parcel parcel) {
        this.express_id = parcel.readString();
        this.express_sn = parcel.readString();
        this.express_name = parcel.readString();
    }
}
