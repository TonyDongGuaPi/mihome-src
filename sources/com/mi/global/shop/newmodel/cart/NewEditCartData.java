package com.mi.global.shop.newmodel.cart;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class NewEditCartData {
    @SerializedName("CartActivityDiscountMoney")
    public String CartActivityDiscountMoney;
    @SerializedName("CartOrderMoney")
    public String CartOrderMoney;
    @SerializedName("CartPostFreeBalance")
    public float CartPostFreeBalance;
    @SerializedName("CartProductMoney")
    public String CartProductMoney;
    @SerializedName("activitys")
    public NewCartActivity activitys;
    @SerializedName("bargains")
    public ArrayList<NewCartBargainItem> bargains = new ArrayList<>();
    @SerializedName("itemcount")
    public int itemcount;
    @SerializedName("items")
    public ArrayList<NewCartItem> items = new ArrayList<>();
    @SerializedName("totalWithoutGift")
    public int totalWithoutGift;

    public static NewEditCartData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewEditCartData decode(ProtoReader protoReader) throws IOException {
        NewEditCartData newEditCartData = new NewEditCartData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newEditCartData.CartOrderMoney = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newEditCartData.CartProductMoney = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newEditCartData.CartActivityDiscountMoney = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        newEditCartData.CartPostFreeBalance = ProtoAdapter.FLOAT.decode(protoReader).floatValue();
                        break;
                    case 5:
                        newEditCartData.bargains.add(NewCartBargainItem.decode(protoReader));
                        break;
                    case 6:
                        newEditCartData.totalWithoutGift = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 7:
                        newEditCartData.activitys = NewCartActivity.decode(protoReader);
                        break;
                    case 8:
                        newEditCartData.items.add(NewCartItem.decode(protoReader));
                        break;
                    case 9:
                        newEditCartData.itemcount = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newEditCartData;
            }
        }
    }
}
