package com.mi.global.shop.newmodel.cart;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class NewCartActivity {
    @SerializedName("coupons")
    public ArrayList<NewCartCouponItem> coupons = new ArrayList<>();
    @SerializedName("gift")
    public ArrayList<NewCartGiftItem> gift = new ArrayList<>();
    @SerializedName("postFree")
    public NewCartPostFree postFree;
    @SerializedName("reduction")
    public ArrayList<NewCartReductionItem> reduction = new ArrayList<>();

    public static NewCartActivity decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewCartActivity decode(ProtoReader protoReader) throws IOException {
        NewCartActivity newCartActivity = new NewCartActivity();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newCartActivity.gift.add(NewCartGiftItem.decode(protoReader));
                        break;
                    case 2:
                        newCartActivity.reduction.add(NewCartReductionItem.decode(protoReader));
                        break;
                    case 3:
                        newCartActivity.coupons.add(NewCartCouponItem.decode(protoReader));
                        break;
                    case 4:
                        newCartActivity.postFree = NewCartPostFree.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newCartActivity;
            }
        }
    }
}
