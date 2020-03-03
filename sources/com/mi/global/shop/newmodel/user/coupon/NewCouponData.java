package com.mi.global.shop.newmodel.user.coupon;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class NewCouponData {
    @SerializedName("coupons")
    public ArrayList<NewCouponItem> coupons = new ArrayList<>();

    public static NewCouponData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewCouponData decode(ProtoReader protoReader) throws IOException {
        NewCouponData newCouponData = new NewCouponData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return newCouponData;
            } else if (nextTag != 1) {
                protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
            } else {
                newCouponData.coupons.add(NewCouponItem.decode(protoReader));
            }
        }
    }
}
