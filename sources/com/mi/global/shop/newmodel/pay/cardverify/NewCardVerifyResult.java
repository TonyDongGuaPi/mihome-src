package com.mi.global.shop.newmodel.pay.cardverify;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewCardVerifyResult extends BaseResult {
    @SerializedName("data")
    public NewCardVerifyData data;

    public static NewCardVerifyResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewCardVerifyResult decode(ProtoReader protoReader) throws IOException {
        NewCardVerifyResult newCardVerifyResult = new NewCardVerifyResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newCardVerifyResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newCardVerifyResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newCardVerifyResult.data = NewCardVerifyData.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newCardVerifyResult;
            }
        }
    }
}
