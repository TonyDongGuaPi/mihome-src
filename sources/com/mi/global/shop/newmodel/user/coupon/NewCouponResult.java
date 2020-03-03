package com.mi.global.shop.newmodel.user.coupon;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewCouponResult extends BaseResult {
    @SerializedName("data")
    public NewCouponData data;

    public static NewCouponResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewCouponResult decode(ProtoReader protoReader) throws IOException {
        NewCouponResult newCouponResult = new NewCouponResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return newCouponResult;
            } else if (nextTag != 5) {
                switch (nextTag) {
                    case 1:
                        newCouponResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newCouponResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                newCouponResult.data = NewCouponData.decode(protoReader);
            }
        }
    }
}
