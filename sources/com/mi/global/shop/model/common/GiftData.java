package com.mi.global.shop.model.common;

import com.mi.global.shop.model.Tags;
import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.util.List;
import okio.ByteString;

public final class GiftData extends Message<GiftData, Builder> {
    public static final ProtoAdapter<GiftData> ADAPTER = new ProtoAdapter_GiftData();
    public static final String DEFAULT_ACTID = "";
    public static final String DEFAULT_ACTIVITY_NAME = "";
    public static final String DEFAULT_COMMODITY_ID = "";
    public static final String DEFAULT_IMAGE_URL = "";
    public static final String DEFAULT_ITEMID = "";
    public static final Integer DEFAULT_NUM = 0;
    public static final String DEFAULT_PRODUCT_NAME = "";
    public static final String DEFAULT_SALEPRICE = "";
    public static final String DEFAULT_SALEPRICE_TXT = "";
    public static final String DEFAULT_SUBTOTAL = "";
    public static final String DEFAULT_SUBTOTAL_TXT = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 53)
    public final String actId;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 56)
    public final String activity_name;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 12)
    public final String commodity_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 27)
    public final String image_url;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String itemId;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 5)
    public final Integer num;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 23)
    public final String product_name;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 18)
    public final String salePrice;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 57)
    public final String salePrice_txt;
    @WireField(adapter = "com.mi.global.shop.model.common.SelecInfoData#ADAPTER", label = WireField.Label.REPEATED, tag = 52)
    public final List<SelecInfoData> selecInfo;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 19)
    public final String subtotal;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 58)
    public final String subtotal_txt;

    public GiftData(String str, Integer num2, String str2, String str3, String str4, String str5, String str6, List<SelecInfoData> list, String str7, String str8, String str9, String str10) {
        this(str, num2, str2, str3, str4, str5, str6, list, str7, str8, str9, str10, ByteString.EMPTY);
    }

    public GiftData(String str, Integer num2, String str2, String str3, String str4, String str5, String str6, List<SelecInfoData> list, String str7, String str8, String str9, String str10, ByteString byteString) {
        super(ADAPTER, byteString);
        this.itemId = str;
        this.num = num2;
        this.commodity_id = str2;
        this.salePrice = str3;
        this.subtotal = str4;
        this.product_name = str5;
        this.image_url = str6;
        this.selecInfo = Internal.immutableCopyOf(Tags.ShoppingSupply.SELECT_INFO, list);
        this.actId = str7;
        this.activity_name = str8;
        this.salePrice_txt = str9;
        this.subtotal_txt = str10;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.itemId = this.itemId;
        builder.num = this.num;
        builder.commodity_id = this.commodity_id;
        builder.salePrice = this.salePrice;
        builder.subtotal = this.subtotal;
        builder.product_name = this.product_name;
        builder.image_url = this.image_url;
        builder.selecInfo = Internal.copyOf(Tags.ShoppingSupply.SELECT_INFO, this.selecInfo);
        builder.actId = this.actId;
        builder.activity_name = this.activity_name;
        builder.salePrice_txt = this.salePrice_txt;
        builder.subtotal_txt = this.subtotal_txt;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GiftData)) {
            return false;
        }
        GiftData giftData = (GiftData) obj;
        if (!Internal.equals(unknownFields(), giftData.unknownFields()) || !Internal.equals(this.itemId, giftData.itemId) || !Internal.equals(this.num, giftData.num) || !Internal.equals(this.commodity_id, giftData.commodity_id) || !Internal.equals(this.salePrice, giftData.salePrice) || !Internal.equals(this.subtotal, giftData.subtotal) || !Internal.equals(this.product_name, giftData.product_name) || !Internal.equals(this.image_url, giftData.image_url) || !Internal.equals(this.selecInfo, giftData.selecInfo) || !Internal.equals(this.actId, giftData.actId) || !Internal.equals(this.activity_name, giftData.activity_name) || !Internal.equals(this.salePrice_txt, giftData.salePrice_txt) || !Internal.equals(this.subtotal_txt, giftData.subtotal_txt)) {
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
        int hashCode = ((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.itemId != null ? this.itemId.hashCode() : 0)) * 37) + (this.num != null ? this.num.hashCode() : 0)) * 37) + (this.commodity_id != null ? this.commodity_id.hashCode() : 0)) * 37) + (this.salePrice != null ? this.salePrice.hashCode() : 0)) * 37) + (this.subtotal != null ? this.subtotal.hashCode() : 0)) * 37) + (this.product_name != null ? this.product_name.hashCode() : 0)) * 37) + (this.image_url != null ? this.image_url.hashCode() : 0)) * 37) + (this.selecInfo != null ? this.selecInfo.hashCode() : 1)) * 37) + (this.actId != null ? this.actId.hashCode() : 0)) * 37) + (this.activity_name != null ? this.activity_name.hashCode() : 0)) * 37) + (this.salePrice_txt != null ? this.salePrice_txt.hashCode() : 0)) * 37;
        if (this.subtotal_txt != null) {
            i2 = this.subtotal_txt.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.itemId != null) {
            sb.append(", itemId=");
            sb.append(this.itemId);
        }
        if (this.num != null) {
            sb.append(", num=");
            sb.append(this.num);
        }
        if (this.commodity_id != null) {
            sb.append(", commodity_id=");
            sb.append(this.commodity_id);
        }
        if (this.salePrice != null) {
            sb.append(", salePrice=");
            sb.append(this.salePrice);
        }
        if (this.subtotal != null) {
            sb.append(", subtotal=");
            sb.append(this.subtotal);
        }
        if (this.product_name != null) {
            sb.append(", product_name=");
            sb.append(this.product_name);
        }
        if (this.image_url != null) {
            sb.append(", image_url=");
            sb.append(this.image_url);
        }
        if (this.selecInfo != null) {
            sb.append(", selecInfo=");
            sb.append(this.selecInfo);
        }
        if (this.actId != null) {
            sb.append(", actId=");
            sb.append(this.actId);
        }
        if (this.activity_name != null) {
            sb.append(", activity_name=");
            sb.append(this.activity_name);
        }
        if (this.salePrice_txt != null) {
            sb.append(", salePrice_txt=");
            sb.append(this.salePrice_txt);
        }
        if (this.subtotal_txt != null) {
            sb.append(", subtotal_txt=");
            sb.append(this.subtotal_txt);
        }
        StringBuilder replace = sb.replace(0, 2, "GiftData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<GiftData, Builder> {
        public String actId;
        public String activity_name;
        public String commodity_id;
        public String image_url;
        public String itemId;
        public Integer num;
        public String product_name;
        public String salePrice;
        public String salePrice_txt;
        public List<SelecInfoData> selecInfo = Internal.newMutableList();
        public String subtotal;
        public String subtotal_txt;

        public Builder itemId(String str) {
            this.itemId = str;
            return this;
        }

        public Builder num(Integer num2) {
            this.num = num2;
            return this;
        }

        public Builder commodity_id(String str) {
            this.commodity_id = str;
            return this;
        }

        public Builder salePrice(String str) {
            this.salePrice = str;
            return this;
        }

        public Builder subtotal(String str) {
            this.subtotal = str;
            return this;
        }

        public Builder product_name(String str) {
            this.product_name = str;
            return this;
        }

        public Builder image_url(String str) {
            this.image_url = str;
            return this;
        }

        public Builder selecInfo(List<SelecInfoData> list) {
            Internal.checkElementsNotNull(list);
            this.selecInfo = list;
            return this;
        }

        public Builder actId(String str) {
            this.actId = str;
            return this;
        }

        public Builder activity_name(String str) {
            this.activity_name = str;
            return this;
        }

        public Builder salePrice_txt(String str) {
            this.salePrice_txt = str;
            return this;
        }

        public Builder subtotal_txt(String str) {
            this.subtotal_txt = str;
            return this;
        }

        public GiftData build() {
            return new GiftData(this.itemId, this.num, this.commodity_id, this.salePrice, this.subtotal, this.product_name, this.image_url, this.selecInfo, this.actId, this.activity_name, this.salePrice_txt, this.subtotal_txt, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_GiftData extends ProtoAdapter<GiftData> {
        ProtoAdapter_GiftData() {
            super(FieldEncoding.LENGTH_DELIMITED, GiftData.class);
        }

        public int encodedSize(GiftData giftData) {
            int i = 0;
            int encodedSizeWithTag = (giftData.itemId != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, giftData.itemId) : 0) + (giftData.num != null ? ProtoAdapter.INT32.encodedSizeWithTag(5, giftData.num) : 0) + (giftData.commodity_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(12, giftData.commodity_id) : 0) + (giftData.salePrice != null ? ProtoAdapter.STRING.encodedSizeWithTag(18, giftData.salePrice) : 0) + (giftData.subtotal != null ? ProtoAdapter.STRING.encodedSizeWithTag(19, giftData.subtotal) : 0) + (giftData.product_name != null ? ProtoAdapter.STRING.encodedSizeWithTag(23, giftData.product_name) : 0) + (giftData.image_url != null ? ProtoAdapter.STRING.encodedSizeWithTag(27, giftData.image_url) : 0) + SelecInfoData.ADAPTER.asRepeated().encodedSizeWithTag(52, giftData.selecInfo) + (giftData.actId != null ? ProtoAdapter.STRING.encodedSizeWithTag(53, giftData.actId) : 0) + (giftData.activity_name != null ? ProtoAdapter.STRING.encodedSizeWithTag(56, giftData.activity_name) : 0) + (giftData.salePrice_txt != null ? ProtoAdapter.STRING.encodedSizeWithTag(57, giftData.salePrice_txt) : 0);
            if (giftData.subtotal_txt != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(58, giftData.subtotal_txt);
            }
            return encodedSizeWithTag + i + giftData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, GiftData giftData) throws IOException {
            if (giftData.itemId != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, giftData.itemId);
            }
            if (giftData.num != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 5, giftData.num);
            }
            if (giftData.commodity_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 12, giftData.commodity_id);
            }
            if (giftData.salePrice != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 18, giftData.salePrice);
            }
            if (giftData.subtotal != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 19, giftData.subtotal);
            }
            if (giftData.product_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 23, giftData.product_name);
            }
            if (giftData.image_url != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 27, giftData.image_url);
            }
            if (giftData.selecInfo != null) {
                SelecInfoData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 52, giftData.selecInfo);
            }
            if (giftData.actId != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 53, giftData.actId);
            }
            if (giftData.activity_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 56, giftData.activity_name);
            }
            if (giftData.salePrice_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 57, giftData.salePrice_txt);
            }
            if (giftData.subtotal_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 58, giftData.subtotal_txt);
            }
            protoWriter.writeBytes(giftData.unknownFields());
        }

        public GiftData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.itemId(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 5:
                            builder.num(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 12:
                            builder.commodity_id(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 18:
                            builder.salePrice(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 19:
                            builder.subtotal(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 23:
                            builder.product_name(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 27:
                            builder.image_url(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 52:
                            builder.selecInfo.add(SelecInfoData.ADAPTER.decode(protoReader));
                            break;
                        case 53:
                            builder.actId(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 56:
                            builder.activity_name(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 57:
                            builder.salePrice_txt(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 58:
                            builder.subtotal_txt(ProtoAdapter.STRING.decode(protoReader));
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

        public GiftData redact(GiftData giftData) {
            Builder newBuilder = giftData.newBuilder();
            Internal.redactElements(newBuilder.selecInfo, SelecInfoData.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
