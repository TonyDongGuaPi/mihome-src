package com.xiaomi.shopviews.model.item;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class HomeItemContentVideo extends HomeItemContentBase {
    @SerializedName("item")

    /* renamed from: a  reason: collision with root package name */
    public Item f13209a = new Item();

    public static HomeItemContentVideo a(byte[] bArr) throws IOException {
        return a(new ProtoReader(new Buffer().write(bArr)));
    }

    public static HomeItemContentVideo a(ProtoReader protoReader) throws IOException {
        HomeItemContentVideo homeItemContentVideo = new HomeItemContentVideo();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return homeItemContentVideo;
            } else if (nextTag != 1) {
                protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
            } else {
                homeItemContentVideo.f13209a = Item.a(protoReader);
            }
        }
    }

    public static class Item {
        @SerializedName("videoUrl")

        /* renamed from: a  reason: collision with root package name */
        public String f13210a;
        @SerializedName("title")
        public String b;
        @SerializedName("content")
        public String c;
        @SerializedName("coverUrl")
        public String d;

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
                            item.f13210a = ProtoAdapter.STRING.decode(protoReader);
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
