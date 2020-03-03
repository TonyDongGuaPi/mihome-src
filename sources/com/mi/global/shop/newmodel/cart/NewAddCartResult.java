package com.mi.global.shop.newmodel.cart;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewAddCartResult extends BaseResult {
    @SerializedName("data")
    public NewAddCartData data;

    public static NewAddCartResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewAddCartResult decode(ProtoReader protoReader) throws IOException {
        NewAddCartResult newAddCartResult = new NewAddCartResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newAddCartResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newAddCartResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newAddCartResult.data = NewAddCartData.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newAddCartResult;
            }
        }
    }
}
