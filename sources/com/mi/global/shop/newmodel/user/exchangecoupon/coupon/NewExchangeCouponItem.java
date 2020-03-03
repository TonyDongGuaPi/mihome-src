package com.mi.global.shop.newmodel.user.exchangecoupon.coupon;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewExchangeCouponItem {
    @SerializedName("add_time")
    public int add_time;
    @SerializedName("amount")
    public String amount;
    @SerializedName("brand")
    public String brand;
    @SerializedName("end_time")
    public int end_time;
    @SerializedName("id")
    public String id;
    @SerializedName("imei")
    public String imei;
    @SerializedName("model")
    public String model;
    @SerializedName("start_time")
    public int start_time;
    @SerializedName("status")
    public String status;

    public static NewExchangeCouponItem decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewExchangeCouponItem decode(ProtoReader protoReader) throws IOException {
        NewExchangeCouponItem newExchangeCouponItem = new NewExchangeCouponItem();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newExchangeCouponItem.id = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newExchangeCouponItem.brand = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newExchangeCouponItem.model = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        newExchangeCouponItem.amount = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 5:
                        newExchangeCouponItem.start_time = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 6:
                        newExchangeCouponItem.end_time = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 7:
                        newExchangeCouponItem.imei = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 8:
                        newExchangeCouponItem.status = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 9:
                        newExchangeCouponItem.add_time = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newExchangeCouponItem;
            }
        }
    }
}
