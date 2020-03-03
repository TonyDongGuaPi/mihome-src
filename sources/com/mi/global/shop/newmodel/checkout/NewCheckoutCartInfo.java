package com.mi.global.shop.newmodel.checkout;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class NewCheckoutCartInfo {
    @SerializedName("activityDiscountMoney")
    public String activityDiscountMoney;
    @SerializedName("items")
    public ArrayList<NewCheckoutCartItem> items = new ArrayList<>();
    @SerializedName("productMoney")
    public String productMoney;
    @SerializedName("productMoney_txt")
    public String productMoney_txt;

    public static NewCheckoutCartInfo decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewCheckoutCartInfo decode(ProtoReader protoReader) throws IOException {
        NewCheckoutCartInfo newCheckoutCartInfo = new NewCheckoutCartInfo();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newCheckoutCartInfo.items.add(NewCheckoutCartItem.decode(protoReader));
                        break;
                    case 2:
                        newCheckoutCartInfo.productMoney = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newCheckoutCartInfo.activityDiscountMoney = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        newCheckoutCartInfo.productMoney_txt = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newCheckoutCartInfo;
            }
        }
    }
}
