package com.mi.global.shop.newmodel.checkout;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewSubmitData {
    @SerializedName("errors")
    public String errors;
    @SerializedName("is_zero_order")
    public boolean is_zero_order;
    @SerializedName("link")
    public String link;
    @SerializedName("order_id")
    public String order_id;

    public static NewSubmitData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewSubmitData decode(ProtoReader protoReader) throws IOException {
        NewSubmitData newSubmitData = new NewSubmitData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newSubmitData.order_id = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newSubmitData.is_zero_order = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 3:
                        newSubmitData.link = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        newSubmitData.errors = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newSubmitData;
            }
        }
    }
}
