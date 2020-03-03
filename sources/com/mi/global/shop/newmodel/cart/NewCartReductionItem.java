package com.mi.global.shop.newmodel.cart;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewCartReductionItem {
    @SerializedName("actName")
    public String actName;
    @SerializedName("activity_name")
    public String activity_name;
    @SerializedName("reduceMoney")
    public String reduceMoney;
    @SerializedName("reduceMoneySingle")
    public String reduceMoneySingle;
    @SerializedName("reduceMoneySingle_txt")
    public String reduceMoneySingle_txt;
    @SerializedName("reduceMoney_txt")
    public String reduceMoney_txt;
    @SerializedName("times")
    public int times;

    public static NewCartReductionItem decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewCartReductionItem decode(ProtoReader protoReader) throws IOException {
        NewCartReductionItem newCartReductionItem = new NewCartReductionItem();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newCartReductionItem.actName = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newCartReductionItem.reduceMoneySingle = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newCartReductionItem.times = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 4:
                        newCartReductionItem.reduceMoney = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 7:
                        newCartReductionItem.activity_name = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 8:
                        newCartReductionItem.reduceMoneySingle_txt = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 9:
                        newCartReductionItem.reduceMoney_txt = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newCartReductionItem;
            }
        }
    }
}
