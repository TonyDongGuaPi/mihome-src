package com.xiaomi.shopviews.model.item;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class HomeItemContentAdvertiseHeadImage extends HomeItemContentBase {
    @SerializedName("item")

    /* renamed from: a  reason: collision with root package name */
    public Item f13172a = new Item();

    public static HomeItemContentAdvertiseHeadImage a(byte[] bArr) throws IOException {
        return a(new ProtoReader(new Buffer().write(bArr)));
    }

    public static HomeItemContentAdvertiseHeadImage a(ProtoReader protoReader) throws IOException {
        HomeItemContentAdvertiseHeadImage homeItemContentAdvertiseHeadImage = new HomeItemContentAdvertiseHeadImage();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return homeItemContentAdvertiseHeadImage;
            } else if (nextTag != 1) {
                protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
            } else {
                homeItemContentAdvertiseHeadImage.f13172a = Item.a(protoReader);
            }
        }
    }

    public static class Item {
        @SerializedName("thumb")

        /* renamed from: a  reason: collision with root package name */
        public String f13173a;
        @SerializedName("url")
        public String b;
        @SerializedName("productName")
        public String c;

        public Item() {
        }

        public Item(String str, String str2) {
            this.f13173a = str;
            this.c = str2;
        }

        public Item(String str, String str2, String str3) {
            this.f13173a = str;
            this.b = str2;
            this.c = str3;
        }

        public static Item a(ProtoReader protoReader) throws IOException {
            Item item = new Item();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            item.f13173a = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 2:
                            item.b = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 3:
                            item.c = ProtoAdapter.STRING.decode(protoReader);
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
