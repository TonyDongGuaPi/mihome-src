package com.xiaomi.shopviews.model.item;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class HomeItemContentProductShow3 extends HomeItemContentBase {
    @SerializedName("items")

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<Item> f13195a = new ArrayList<>();

    public static HomeItemContentProductShow3 a(byte[] bArr) throws IOException {
        return a(new ProtoReader(new Buffer().write(bArr)));
    }

    public static HomeItemContentProductShow3 a(ProtoReader protoReader) throws IOException {
        HomeItemContentProductShow3 homeItemContentProductShow3 = new HomeItemContentProductShow3();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return homeItemContentProductShow3;
            } else if (nextTag != 1) {
                protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
            } else {
                homeItemContentProductShow3.f13195a.add(Item.a(protoReader));
            }
        }
    }

    public static class Item {
        @SerializedName("productName")

        /* renamed from: a  reason: collision with root package name */
        public String f13196a;
        @SerializedName("imageUrl")
        public String b;
        @SerializedName("salePrice")
        public String c;
        @SerializedName("marketPrice")
        public String d;
        @SerializedName("productId")
        public String e;
        @SerializedName("commodityId")
        public String f;
        @SerializedName("jumpUrl")
        public String g;
        @SerializedName("goodsOrder")
        public int h;

        public static Item a(byte[] bArr) throws IOException {
            return a(new ProtoReader(new Buffer().write(bArr)));
        }

        public static Item a(ProtoReader protoReader) throws IOException {
            Item item = new Item();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            item.f13196a = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 2:
                            item.b = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 3:
                            item.c = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 4:
                            item.d = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 5:
                            item.e = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 6:
                            item.f = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 7:
                            item.g = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 8:
                            item.h = ProtoAdapter.INT32.decode(protoReader).intValue();
                            break;
                        default:
                            protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                            break;
                    }
                } else {
                    protoReader.endMessage(beginMessage);
                    return item;
                }
            }
        }
    }
}
