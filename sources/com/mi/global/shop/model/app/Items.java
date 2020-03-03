package com.mi.global.shop.model.app;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import okio.ByteString;

public final class Items extends Message<Items, Builder> {
    public static final ProtoAdapter<Items> ADAPTER = new ProtoAdapter_Items();
    public static final String DEFAULT_DESC = "";
    public static final String DEFAULT_FULL_PRICE = "";
    public static final String DEFAULT_HEIGHT = "";
    public static final String DEFAULT_ICON_CONTENT = "";
    public static final String DEFAULT_ICON_IMG = "";
    public static final String DEFAULT_ICON_TYPE = "";
    public static final String DEFAULT_PRODUCT_PRICE = "";
    public static final String DEFAULT_TARGET = "";
    public static final String DEFAULT_THUMB = "";
    public static final String DEFAULT_TITLE = "";
    public static final String DEFAULT_T_FULL_PRICE = "";
    public static final String DEFAULT_T_PRODUCT_PRICE = "";
    public static final String DEFAULT_URL = "";
    public static final String DEFAULT_VIEWID = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String desc;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String full_price;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String height;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 12)
    public final String icon_content;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 13)
    public final String icon_img;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 11)
    public final String icon_type;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String product_price;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final String t_full_price;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String t_product_price;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 10)
    public final String target;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String thumb;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String title;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String url;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 14)
    public final String viewId;

    public Items(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
        this(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, ByteString.EMPTY);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Items(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, ByteString byteString) {
        super(ADAPTER, byteString);
        this.title = str;
        this.url = str2;
        this.thumb = str3;
        this.product_price = str4;
        this.desc = str5;
        this.t_product_price = str6;
        this.height = str7;
        this.full_price = str8;
        this.t_full_price = str9;
        this.target = str10;
        this.icon_type = str11;
        this.icon_content = str12;
        this.icon_img = str13;
        this.viewId = str14;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.title = this.title;
        builder.url = this.url;
        builder.thumb = this.thumb;
        builder.product_price = this.product_price;
        builder.desc = this.desc;
        builder.t_product_price = this.t_product_price;
        builder.height = this.height;
        builder.full_price = this.full_price;
        builder.t_full_price = this.t_full_price;
        builder.target = this.target;
        builder.icon_type = this.icon_type;
        builder.icon_content = this.icon_content;
        builder.icon_img = this.icon_img;
        builder.viewId = this.viewId;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Items)) {
            return false;
        }
        Items items = (Items) obj;
        if (!Internal.equals(unknownFields(), items.unknownFields()) || !Internal.equals(this.title, items.title) || !Internal.equals(this.url, items.url) || !Internal.equals(this.thumb, items.thumb) || !Internal.equals(this.product_price, items.product_price) || !Internal.equals(this.desc, items.desc) || !Internal.equals(this.t_product_price, items.t_product_price) || !Internal.equals(this.height, items.height) || !Internal.equals(this.full_price, items.full_price) || !Internal.equals(this.t_full_price, items.t_full_price) || !Internal.equals(this.target, items.target) || !Internal.equals(this.icon_type, items.icon_type) || !Internal.equals(this.icon_content, items.icon_content) || !Internal.equals(this.icon_img, items.icon_img) || !Internal.equals(this.viewId, items.viewId)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int i2 = 0;
        int hashCode = ((((((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.title != null ? this.title.hashCode() : 0)) * 37) + (this.url != null ? this.url.hashCode() : 0)) * 37) + (this.thumb != null ? this.thumb.hashCode() : 0)) * 37) + (this.product_price != null ? this.product_price.hashCode() : 0)) * 37) + (this.desc != null ? this.desc.hashCode() : 0)) * 37) + (this.t_product_price != null ? this.t_product_price.hashCode() : 0)) * 37) + (this.height != null ? this.height.hashCode() : 0)) * 37) + (this.full_price != null ? this.full_price.hashCode() : 0)) * 37) + (this.t_full_price != null ? this.t_full_price.hashCode() : 0)) * 37) + (this.target != null ? this.target.hashCode() : 0)) * 37) + (this.icon_type != null ? this.icon_type.hashCode() : 0)) * 37) + (this.icon_content != null ? this.icon_content.hashCode() : 0)) * 37) + (this.icon_img != null ? this.icon_img.hashCode() : 0)) * 37;
        if (this.viewId != null) {
            i2 = this.viewId.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.title != null) {
            sb.append(", title=");
            sb.append(this.title);
        }
        if (this.url != null) {
            sb.append(", url=");
            sb.append(this.url);
        }
        if (this.thumb != null) {
            sb.append(", thumb=");
            sb.append(this.thumb);
        }
        if (this.product_price != null) {
            sb.append(", product_price=");
            sb.append(this.product_price);
        }
        if (this.desc != null) {
            sb.append(", desc=");
            sb.append(this.desc);
        }
        if (this.t_product_price != null) {
            sb.append(", t_product_price=");
            sb.append(this.t_product_price);
        }
        if (this.height != null) {
            sb.append(", height=");
            sb.append(this.height);
        }
        if (this.full_price != null) {
            sb.append(", full_price=");
            sb.append(this.full_price);
        }
        if (this.t_full_price != null) {
            sb.append(", t_full_price=");
            sb.append(this.t_full_price);
        }
        if (this.target != null) {
            sb.append(", target=");
            sb.append(this.target);
        }
        if (this.icon_type != null) {
            sb.append(", icon_type=");
            sb.append(this.icon_type);
        }
        if (this.icon_content != null) {
            sb.append(", icon_content=");
            sb.append(this.icon_content);
        }
        if (this.icon_img != null) {
            sb.append(", icon_img=");
            sb.append(this.icon_img);
        }
        if (this.viewId != null) {
            sb.append(", viewId=");
            sb.append(this.viewId);
        }
        StringBuilder replace = sb.replace(0, 2, "Items{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Items, Builder> {
        public String desc;
        public String full_price;
        public String height;
        public String icon_content;
        public String icon_img;
        public String icon_type;
        public String product_price;
        public String t_full_price;
        public String t_product_price;
        public String target;
        public String thumb;
        public String title;
        public String url;
        public String viewId;

        public Builder title(String str) {
            this.title = str;
            return this;
        }

        public Builder url(String str) {
            this.url = str;
            return this;
        }

        public Builder thumb(String str) {
            this.thumb = str;
            return this;
        }

        public Builder product_price(String str) {
            this.product_price = str;
            return this;
        }

        public Builder desc(String str) {
            this.desc = str;
            return this;
        }

        public Builder t_product_price(String str) {
            this.t_product_price = str;
            return this;
        }

        public Builder height(String str) {
            this.height = str;
            return this;
        }

        public Builder full_price(String str) {
            this.full_price = str;
            return this;
        }

        public Builder t_full_price(String str) {
            this.t_full_price = str;
            return this;
        }

        public Builder target(String str) {
            this.target = str;
            return this;
        }

        public Builder icon_type(String str) {
            this.icon_type = str;
            return this;
        }

        public Builder icon_content(String str) {
            this.icon_content = str;
            return this;
        }

        public Builder icon_img(String str) {
            this.icon_img = str;
            return this;
        }

        public Builder viewId(String str) {
            this.viewId = str;
            return this;
        }

        public Items build() {
            return new Items(this.title, this.url, this.thumb, this.product_price, this.desc, this.t_product_price, this.height, this.full_price, this.t_full_price, this.target, this.icon_type, this.icon_content, this.icon_img, this.viewId, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Items extends ProtoAdapter<Items> {
        ProtoAdapter_Items() {
            super(FieldEncoding.LENGTH_DELIMITED, Items.class);
        }

        public int encodedSize(Items items) {
            int i = 0;
            int encodedSizeWithTag = (items.title != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, items.title) : 0) + (items.url != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, items.url) : 0) + (items.thumb != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, items.thumb) : 0) + (items.product_price != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, items.product_price) : 0) + (items.desc != null ? ProtoAdapter.STRING.encodedSizeWithTag(5, items.desc) : 0) + (items.t_product_price != null ? ProtoAdapter.STRING.encodedSizeWithTag(6, items.t_product_price) : 0) + (items.height != null ? ProtoAdapter.STRING.encodedSizeWithTag(7, items.height) : 0) + (items.full_price != null ? ProtoAdapter.STRING.encodedSizeWithTag(8, items.full_price) : 0) + (items.t_full_price != null ? ProtoAdapter.STRING.encodedSizeWithTag(9, items.t_full_price) : 0) + (items.target != null ? ProtoAdapter.STRING.encodedSizeWithTag(10, items.target) : 0) + (items.icon_type != null ? ProtoAdapter.STRING.encodedSizeWithTag(11, items.icon_type) : 0) + (items.icon_content != null ? ProtoAdapter.STRING.encodedSizeWithTag(12, items.icon_content) : 0) + (items.icon_img != null ? ProtoAdapter.STRING.encodedSizeWithTag(13, items.icon_img) : 0);
            if (items.viewId != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(14, items.viewId);
            }
            return encodedSizeWithTag + i + items.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, Items items) throws IOException {
            if (items.title != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, items.title);
            }
            if (items.url != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, items.url);
            }
            if (items.thumb != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, items.thumb);
            }
            if (items.product_price != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, items.product_price);
            }
            if (items.desc != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, items.desc);
            }
            if (items.t_product_price != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, items.t_product_price);
            }
            if (items.height != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, items.height);
            }
            if (items.full_price != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, items.full_price);
            }
            if (items.t_full_price != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 9, items.t_full_price);
            }
            if (items.target != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 10, items.target);
            }
            if (items.icon_type != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 11, items.icon_type);
            }
            if (items.icon_content != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 12, items.icon_content);
            }
            if (items.icon_img != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 13, items.icon_img);
            }
            if (items.viewId != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 14, items.viewId);
            }
            protoWriter.writeBytes(items.unknownFields());
        }

        public Items decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.title(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.url(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.thumb(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.product_price(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 5:
                            builder.desc(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 6:
                            builder.t_product_price(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 7:
                            builder.height(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 8:
                            builder.full_price(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 9:
                            builder.t_full_price(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 10:
                            builder.target(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 11:
                            builder.icon_type(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 12:
                            builder.icon_content(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 13:
                            builder.icon_img(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 14:
                            builder.viewId(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        default:
                            FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                            builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                            break;
                    }
                } else {
                    protoReader.endMessage(beginMessage);
                    return builder.build();
                }
            }
        }

        public Items redact(Items items) {
            Builder newBuilder = items.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
