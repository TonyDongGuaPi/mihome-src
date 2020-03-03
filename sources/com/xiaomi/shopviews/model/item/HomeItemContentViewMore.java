package com.xiaomi.shopviews.model.item;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class HomeItemContentViewMore extends HomeItemContentBase {
    @SerializedName("item")

    /* renamed from: a  reason: collision with root package name */
    public Item f13211a = new Item();

    public static HomeItemContentViewMore a(byte[] bArr) throws IOException {
        return a(new ProtoReader(new Buffer().write(bArr)));
    }

    public static HomeItemContentViewMore a(ProtoReader protoReader) throws IOException {
        HomeItemContentViewMore homeItemContentViewMore = new HomeItemContentViewMore();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return homeItemContentViewMore;
            } else if (nextTag != 1) {
                protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
            } else {
                homeItemContentViewMore.f13211a = Item.a(protoReader);
            }
        }
    }

    public static class Item {
        @SerializedName("text")

        /* renamed from: a  reason: collision with root package name */
        public String f13212a;
        @SerializedName("url")
        public String b;

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
                            item.f13212a = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 2:
                            item.b = ProtoAdapter.STRING.decode(protoReader);
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
