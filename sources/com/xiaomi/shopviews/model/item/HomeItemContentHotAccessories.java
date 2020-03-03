package com.xiaomi.shopviews.model.item;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class HomeItemContentHotAccessories extends HomeItemContentBase {
    @SerializedName("items")

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<Item> f13191a = new ArrayList<>();

    public static HomeItemContentHotAccessories a(byte[] bArr) throws IOException {
        return a(new ProtoReader(new Buffer().write(bArr)));
    }

    public static HomeItemContentHotAccessories a(ProtoReader protoReader) throws IOException {
        HomeItemContentHotAccessories homeItemContentHotAccessories = new HomeItemContentHotAccessories();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return homeItemContentHotAccessories;
            } else if (nextTag != 1) {
                protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
            } else {
                homeItemContentHotAccessories.f13191a.add(Item.a(protoReader));
            }
        }
    }

    public static class Item {
        @SerializedName("title")

        /* renamed from: a  reason: collision with root package name */
        public String f13192a;
        @SerializedName("url")
        public String b;
        @SerializedName("thumb")
        public String c;
        @SerializedName("product_price")
        public String d;
        @SerializedName("desc")
        public String e;
        @SerializedName("t_product_price")
        public String f;
        @SerializedName("height")
        public String g;
        @SerializedName("full_price")
        public String h;
        @SerializedName("t_full_price")
        public String i;
        @SerializedName("target")
        public String j;
        @SerializedName("icon_type")
        public String k;
        @SerializedName("icon_content")
        public String l;
        @SerializedName("icon_img")
        public String m;
        @SerializedName("viewId")
        public String n;
        @SerializedName("product_tag")
        public String o;

        public Item() {
        }

        public Item(String str, String str2, String str3) {
            this.f13192a = str;
            this.c = str2;
            this.d = str3;
        }

        public Item(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15) {
            this.f13192a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
            this.f = str6;
            this.g = str7;
            this.h = str8;
            this.i = str9;
            this.j = str10;
            this.k = str11;
            this.l = str12;
            this.m = str13;
            this.n = str14;
            this.o = str15;
        }

        public String a() {
            if (TextUtils.isEmpty(this.c)) {
                return this.c;
            }
            if (this.c.startsWith("http:") || this.c.startsWith("https:")) {
                return this.c;
            }
            return "http:" + this.c;
        }

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
                            item.f13192a = ProtoAdapter.STRING.decode(protoReader);
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
                            item.h = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 9:
                            item.i = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 10:
                            item.j = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 11:
                            item.k = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 12:
                            item.l = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 13:
                            item.m = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 14:
                            item.n = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 15:
                            item.o = ProtoAdapter.STRING.decode(protoReader);
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
