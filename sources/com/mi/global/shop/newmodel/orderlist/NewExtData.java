package com.mi.global.shop.newmodel.orderlist;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewExtData {
    @SerializedName("send")
    public int send;
    @SerializedName("unpaid")
    public int unpaid;

    public static NewExtData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewExtData decode(ProtoReader protoReader) throws IOException {
        NewExtData newExtData = new NewExtData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newExtData.unpaid = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newExtData.send = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newExtData;
            }
        }
    }
}
