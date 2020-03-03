package com.xiaomi.shopviews.model.item;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class HomeItemContentVirtualView extends HomeItemContentBase {
    @SerializedName("item")

    /* renamed from: a  reason: collision with root package name */
    public Item f13213a = new Item();

    public static HomeItemContentVirtualView a(byte[] bArr) throws IOException {
        return a(new ProtoReader(new Buffer().write(bArr)));
    }

    public static HomeItemContentVirtualView a(ProtoReader protoReader) throws IOException {
        HomeItemContentVirtualView homeItemContentVirtualView = new HomeItemContentVirtualView();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return homeItemContentVirtualView;
            } else if (nextTag != 1) {
                protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
            } else {
                homeItemContentVirtualView.f13213a = Item.a(protoReader);
            }
        }
    }

    public static class Item {
        @SerializedName("name")

        /* renamed from: a  reason: collision with root package name */
        public String f13214a;

        public static Item a(byte[] bArr) throws IOException {
            return a(new ProtoReader(new Buffer().write(bArr)));
        }

        public static Item a(ProtoReader protoReader) throws IOException {
            Item item = new Item();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag == -1) {
                    protoReader.endMessage(beginMessage);
                    return item;
                } else if (nextTag != 1) {
                    protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                } else {
                    item.f13214a = ProtoAdapter.STRING.decode(protoReader);
                }
            }
        }
    }
}
