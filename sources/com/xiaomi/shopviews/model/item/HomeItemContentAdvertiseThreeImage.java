package com.xiaomi.shopviews.model.item;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class HomeItemContentAdvertiseThreeImage extends HomeItemContentBase {
    @SerializedName("items")

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<Item> f13174a = new ArrayList<>();

    public static HomeItemContentAdvertiseThreeImage a(byte[] bArr) throws IOException {
        return a(new ProtoReader(new Buffer().write(bArr)));
    }

    public static HomeItemContentAdvertiseThreeImage a(ProtoReader protoReader) throws IOException {
        HomeItemContentAdvertiseThreeImage homeItemContentAdvertiseThreeImage = new HomeItemContentAdvertiseThreeImage();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return homeItemContentAdvertiseThreeImage;
            } else if (nextTag != 1) {
                protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
            } else {
                homeItemContentAdvertiseThreeImage.f13174a.add(Item.a(protoReader));
            }
        }
    }

    public static class Item {
        @SerializedName("thumb")

        /* renamed from: a  reason: collision with root package name */
        public String f13175a;
        @SerializedName("url")
        public String b;
        @SerializedName("productName")
        public String c;
        @SerializedName("title")
        public String d;
        @SerializedName("newPrice")
        public String e;
        @SerializedName("oldPrice")
        public String f;

        public Item() {
        }

        public Item(String str, String str2) {
            this.f13175a = str;
            this.c = str2;
        }

        public Item(String str, String str2, String str3) {
            this.f13175a = str;
            this.b = str2;
            this.c = str3;
        }

        public Item(String str, String str2, String str3, String str4, String str5, String str6) {
            this.f13175a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
            this.f = str6;
        }

        public static Item a(ProtoReader protoReader) throws IOException {
            Item item = new Item();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            item.f13175a = ProtoAdapter.STRING.decode(protoReader);
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
