package com.mi.global.shop.newmodel.cart;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class NewCartGatherOrderInfo {
    @SerializedName("balance_price")
    public String balance_price;
    @SerializedName("goods_list")
    public ArrayList<String> goods_list = new ArrayList<>();
    @SerializedName("show_list")
    public boolean show_list;

    public static NewCartGatherOrderInfo decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewCartGatherOrderInfo decode(ProtoReader protoReader) throws IOException {
        NewCartGatherOrderInfo newCartGatherOrderInfo = new NewCartGatherOrderInfo();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newCartGatherOrderInfo.balance_price = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newCartGatherOrderInfo.show_list = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 3:
                        newCartGatherOrderInfo.goods_list.add(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newCartGatherOrderInfo;
            }
        }
    }
}
