package com.xiaomi.shopviews.model.item;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class HomeItemContentGallery extends HomeItemContentBase {
    @SerializedName("items")

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<Item> f13189a = new ArrayList<>();

    public static HomeItemContentGallery a(byte[] bArr) throws IOException {
        return a(new ProtoReader(new Buffer().write(bArr)));
    }

    public static HomeItemContentGallery a(ProtoReader protoReader) throws IOException {
        HomeItemContentGallery homeItemContentGallery = new HomeItemContentGallery();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return homeItemContentGallery;
            } else if (nextTag != 1) {
                protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
            } else {
                homeItemContentGallery.f13189a.add(Item.a(protoReader));
            }
        }
    }

    public static class Item {
        @SerializedName("thumb")

        /* renamed from: a  reason: collision with root package name */
        public String f13190a;
        @SerializedName("url")
        public String b;
        @SerializedName("viewId")
        public String c;

        public Item() {
        }

        public Item(String str, String str2) {
            this.f13190a = str;
            this.c = str2;
        }

        public Item(String str, String str2, String str3) {
            this.f13190a = str;
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
                            item.f13190a = ProtoAdapter.STRING.decode(protoReader);
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
