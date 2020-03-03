package com.mi.global.shop.newmodel.expresstrack;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewExpressInfoResult extends BaseResult {
    @SerializedName("data")
    public NewExpressInfoData data;

    public static NewExpressInfoResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewExpressInfoResult decode(ProtoReader protoReader) throws IOException {
        NewExpressInfoResult newExpressInfoResult = new NewExpressInfoResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return newExpressInfoResult;
            } else if (nextTag != 5) {
                switch (nextTag) {
                    case 1:
                        newExpressInfoResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newExpressInfoResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                newExpressInfoResult.data = NewExpressInfoData.decode(protoReader);
            }
        }
    }
}
