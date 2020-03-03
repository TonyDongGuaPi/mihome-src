package com.xiaomi.shopviews.model.item;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class HomeItemContentAdvertiseGallery extends HomeItemContentBase {
    @SerializedName("items")

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<Item> f13170a = new ArrayList<>();
    @SerializedName("height")
    public int b = 200;
    @SerializedName("isShowTxt")
    public boolean c = false;

    public HomeItemContentAdvertiseGallery(int i, boolean z) {
        this.b = i;
        this.c = z;
    }

    public HomeItemContentAdvertiseGallery() {
    }

    public static HomeItemContentAdvertiseGallery a(byte[] bArr) throws IOException {
        return a(new ProtoReader(new Buffer().write(bArr)));
    }

    public static HomeItemContentAdvertiseGallery a(ProtoReader protoReader) throws IOException {
        HomeItemContentAdvertiseGallery homeItemContentAdvertiseGallery = new HomeItemContentAdvertiseGallery();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        homeItemContentAdvertiseGallery.f13170a.add(Item.a(protoReader));
                        break;
                    case 2:
                        homeItemContentAdvertiseGallery.b = protoReader.readFixed32();
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return homeItemContentAdvertiseGallery;
            }
        }
    }

    public static class Item {
        @SerializedName("thumb")

        /* renamed from: a  reason: collision with root package name */
        public String f13171a;
        @SerializedName("url")
        public String b;
        @SerializedName("viewId")
        public String c;

        public Item() {
        }

        public Item(String str, String str2) {
            this.f13171a = str;
            this.c = str2;
        }

        public Item(String str, String str2, String str3) {
            this.f13171a = str;
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
                            item.f13171a = ProtoAdapter.STRING.decode(protoReader);
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
