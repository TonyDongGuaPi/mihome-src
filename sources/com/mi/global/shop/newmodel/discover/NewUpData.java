package com.mi.global.shop.newmodel.discover;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewUpData {
    @SerializedName("num")
    public long num;
    @SerializedName("res")
    public boolean res;

    public static NewUpData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewUpData decode(ProtoReader protoReader) throws IOException {
        NewUpData newUpData = new NewUpData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newUpData.res = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 2:
                        newUpData.num = ProtoAdapter.INT64.decode(protoReader).longValue();
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newUpData;
            }
        }
    }
}
