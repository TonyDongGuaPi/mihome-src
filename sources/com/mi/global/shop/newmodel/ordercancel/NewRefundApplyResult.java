package com.mi.global.shop.newmodel.ordercancel;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewRefundApplyResult extends BaseResult {
    @SerializedName("data")
    public NewRefundApplyData data;

    public static NewRefundApplyResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewRefundApplyResult decode(ProtoReader protoReader) throws IOException {
        NewRefundApplyResult newRefundApplyResult = new NewRefundApplyResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return newRefundApplyResult;
            } else if (nextTag != 5) {
                switch (nextTag) {
                    case 1:
                        newRefundApplyResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newRefundApplyResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                newRefundApplyResult.data = NewRefundApplyData.decode(protoReader);
            }
        }
    }
}
