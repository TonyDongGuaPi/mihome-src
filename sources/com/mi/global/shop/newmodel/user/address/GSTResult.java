package com.mi.global.shop.newmodel.user.address;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class GSTResult extends BaseResult {
    @SerializedName("data")
    public GSTData data;

    public static GSTResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static GSTResult decode(ProtoReader protoReader) throws IOException {
        GSTResult gSTResult = new GSTResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        gSTResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        gSTResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        gSTResult.data = GSTData.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return gSTResult;
            }
        }
    }
}
