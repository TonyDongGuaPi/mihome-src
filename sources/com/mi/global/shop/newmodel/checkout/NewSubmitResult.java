package com.mi.global.shop.newmodel.checkout;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewSubmitResult extends BaseResult {
    @SerializedName("data")
    public NewSubmitData data;

    public static NewSubmitResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewSubmitResult decode(ProtoReader protoReader) throws IOException {
        NewSubmitResult newSubmitResult = new NewSubmitResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newSubmitResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newSubmitResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newSubmitResult.data = NewSubmitData.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newSubmitResult;
            }
        }
    }
}
