package com.mi.global.shop.newmodel.orderlist;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewOrderListResult extends BaseResult {
    @SerializedName("data")
    public NewOrderListData data;

    public static NewOrderListResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewOrderListResult decode(ProtoReader protoReader) throws IOException {
        NewOrderListResult newOrderListResult = new NewOrderListResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return newOrderListResult;
            } else if (nextTag != 5) {
                switch (nextTag) {
                    case 1:
                        newOrderListResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newOrderListResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                newOrderListResult.data = NewOrderListData.decode(protoReader);
            }
        }
    }
}
