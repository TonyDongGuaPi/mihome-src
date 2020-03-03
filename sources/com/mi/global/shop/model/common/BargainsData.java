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

public final class BargainsData extends Message<BargainsData, Builder> {
    public static final ProtoAdapter<BargainsData> ADAPTER = new ProtoAdapter_BargainsData();
    public static final String DEFAULT_ACTID = "";
    public static final String DEFAULT_BARGAINGOODSID = "";
    public static final String DEFAULT_BARGAINITEMID = "";
    public static final String DEFAULT_BARGAINPRICE_TXT = "";
    public static final String DEFAULT_BARGAIN_NAME = "";
    public static final Boolean DEFAULT_CHECKED = false;
    public static final String DEFAULT_IMAGE_URL = "";
    public static final String DEFAULT_JSON_DATA = "";
    public static final String DEFAULT_PRODUCT_ID = "";
    public static final String DEFAULT_SALEPRICE = "";
    public static final Boolean DEFAULT_SELECTABLE = false;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String actId;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 42)
    public final String bargainGoodsId;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 43)
    public final String bargainItemId;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 41)
    public final String bargainPrice_txt;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 39)
    public final String bargain_name;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 40)
    public final Boolean checked;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", label = WireField.Label.REPEATED, tag = 1)
    public final List<String> goodsId;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 14)
    public final String image_url;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 44)
    public final String json_data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 11)
    public final String product_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String salePrice;
    @WireField(adapter = "com.mi.global.shop.model.common.SelecInfoData#ADAPTER", label = WireField.Label.REPEATED, tag = 38)
    public final List<SelecInfoData> selecInfo;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 37)
    public final Boolean selectable;

    public BargainsData(List<String> list, String str, String str2, String str3, String str4, Boolean bool, List<SelecInfoData> list2, String str5, Boolean bool2, String str6, String str7, String str8, String str9) {
        this(list, str, str2, str3, str4, bool, list2, str5, bool2, str6, str7, str8, str9, ByteString.EMPTY);
    }

    public BargainsData(List<String> list, String str, String str2, String str3, String str4, Boolean bool, List<SelecInfoData> list2, String str5, Boolean bool2, String str6, String str7, String str8, String str9, ByteString byteString) {
        super(ADAPTER, byteString);
        this.goodsId = Internal.immutableCopyOf("goodsId", list);
        this.salePrice = str;
        this.actId = str2;
        this.product_id = str3;
        this.image_url = str4;
        this.selectable = bool;
        this.selecInfo = Internal.immutableCopyOf(Tags.ShoppingSupply.SELECT_INFO, list2);
        this.bargain_name = str5;
        this.checked = bool2;
        this.bargainPrice_txt = str6;
        this.bargainGoodsId = str7;
        this.bargainItemId = str8;
        this.json_data = str9;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.goodsId = Internal.copyOf("goodsId", this.goodsId);
        builder.salePrice = this.salePrice;
        builder.actId = this.actId;
        builder.product_id = this.product_id;
        builder.image_url = this.image_url;
        builder.selectable = this.selectable;
        builder.selecInfo = Internal.copyOf(Tags.ShoppingSupply.SELECT_INFO, this.selecInfo);
        builder.bargain_name = this.bargain_name;
        builder.checked = this.checked;
        builder.bargainPrice_txt = this.bargainPrice_txt;
        builder.bargainGoodsId = this.bargainGoodsId;
        builder.bargainItemId = this.bargainItemId;
        builder.json_data = this.json_data;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BargainsData)) {
            return false;
        }
        BargainsData bargainsData = (BargainsData) obj;
        if (!Internal.equals(unknownFields(), bargainsData.unknownFields()) || !Internal.equals(this.goodsId, bargainsData.goodsId) || !Internal.equals(this.salePrice, bargainsData.salePrice) || !Internal.equals(this.actId, bargainsData.actId) || !Internal.equals(this.product_id, bargainsData.product_id) || !Internal.equals(this.image_url, bargainsData.image_url) || !Internal.equals(this.selectable, bargainsData.selectable) || !Internal.equals(this.selecInfo, bargainsData.selecInfo) || !Internal.equals(this.bargain_name, bargainsData.bargain_name) || !Internal.equals(this.checked, bargainsData.checked) || !Internal.equals(this.bargainPrice_txt, bargainsData.bargainPrice_txt) || !Internal.equals(this.bargainGoodsId, bargainsData.bargainGoodsId) || !Internal.equals(this.bargainItemId, bargainsData.bargainItemId) || !Internal.equals(this.json_data, bargainsData.json_data)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int i2 = 1;
        int i3 = 0;
        int hashCode = ((((((((((((unknownFields().hashCode() * 37) + (this.goodsId != null ? this.goodsId.hashCode() : 1)) * 37) + (this.salePrice != null ? this.salePrice.hashCode() : 0)) * 37) + (this.actId != null ? this.actId.hashCode() : 0)) * 37) + (this.product_id != null ? this.product_id.hashCode() : 0)) * 37) + (this.image_url != null ? this.image_url.hashCode() : 0)) * 37) + (this.selectable != null ? this.selectable.hashCode() : 0)) * 37;
        if (this.selecInfo != null) {
            i2 = this.selecInfo.hashCode();
        }
        int hashCode2 = (((((((((((hashCode + i2) * 37) + (this.bargain_name != null ? this.bargain_name.hashCode() : 0)) * 37) + (this.checked != null ? this.checked.hashCode() : 0)) * 37) + (this.bargainPrice_txt != null ? this.bargainPrice_txt.hashCode() : 0)) * 37) + (this.bargainGoodsId != null ? this.bargainGoodsId.hashCode() : 0)) * 37) + (this.bargainItemId != null ? this.bargainItemId.hashCode() : 0)) * 37;
        if (this.json_data != null) {
            i3 = this.json_data.hashCode();
        }
        int i4 = hashCode2 + i3;
        this.hashCode = i4;
        return i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.goodsId != null) {
            sb.append(", goodsId=");
            sb.append(this.goodsId);
        }
        if (this.salePrice != null) {
            sb.append(", salePrice=");
            sb.append(this.salePrice);
        }
        if (this.actId != null) {
            sb.append(", actId=");
            sb.append(this.actId);
        }
        if (this.product_id != null) {
            sb.append(", product_id=");
            sb.append(this.product_id);
        }
        if (this.image_url != null) {
            sb.append(", image_url=");
            sb.append(this.image_url);
        }
        if (this.selectable != null) {
            sb.append(", selectable=");
            sb.append(this.selectable);
        }
        if (this.selecInfo != null) {
            sb.append(", selecInfo=");
            sb.append(this.selecInfo);
        }
        if (this.bargain_name != null) {
            sb.append(", bargain_name=");
            sb.append(this.bargain_name);
        }
        if (this.checked != null) {
            sb.append(", checked=");
            sb.append(this.checked);
        }
        if (this.bargainPrice_txt != null) {
            sb.append(", bargainPrice_txt=");
            sb.append(this.bargainPrice_txt);
        }
        if (this.bargainGoodsId != null) {
            sb.append(", bargainGoodsId=");
            sb.append(this.bargainGoodsId);
        }
        if (this.bargainItemId != null) {
            sb.append(", bargainItemId=");
            sb.append(this.bargainItemId);
        }
        if (this.json_data != null) {
            sb.append(", json_data=");
            sb.append(this.json_data);
        }
        StringBuilder replace = sb.replace(0, 2, "BargainsData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<BargainsData, Builder> {
        public String actId;
        public String bargainGoodsId;
        public String bargainItemId;
        public String bargainPrice_txt;
        public String bargain_name;
        public Boolean checked;
        public List<String> goodsId = Internal.newMutableList();
        public String image_url;
        public String json_data;
        public String product_id;
        public String salePrice;
        public List<SelecInfoData> selecInfo = Internal.newMutableList();
        public Boolean selectable;

        public Builder goodsId(List<String> list) {
            Internal.checkElementsNotNull(list);
            this.goodsId = list;
            return this;
        }

        public Builder salePrice(String str) {
            this.salePrice = str;
            return this;
        }

        public Builder actId(String str) {
            this.actId = str;
            return this;
        }

        public Builder product_id(String str) {
            this.product_id = str;
            return this;
        }

        public Builder image_url(String str) {
            this.image_url = str;
            return this;
        }

        public Builder selectable(Boolean bool) {
            this.selectable = bool;
            return this;
        }

        public Builder selecInfo(List<SelecInfoData> list) {
            Internal.checkElementsNotNull(list);
            this.selecInfo = list;
            return this;
        }

        public Builder bargain_name(String str) {
            this.bargain_name = str;
            return this;
        }

        public Builder checked(Boolean bool) {
            this.checked = bool;
            return this;
        }

        public Builder bargainPrice_txt(String str) {
            this.bargainPrice_txt = str;
            return this;
        }

        public Builder bargainGoodsId(String str) {
            this.bargainGoodsId = str;
            return this;
        }

        public Builder bargainItemId(String str) {
            this.bargainItemId = str;
            return this;
        }

        public Builder json_data(String str) {
            this.json_data = str;
            return this;
        }

        public BargainsData build() {
            return new BargainsData(this.goodsId, this.salePrice, this.actId, this.product_id, this.image_url, this.selectable, this.selecInfo, this.bargain_name, this.checked, this.bargainPrice_txt, this.bargainGoodsId, this.bargainItemId, this.json_data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_BargainsData extends ProtoAdapter<BargainsData> {
        ProtoAdapter_BargainsData() {
            super(FieldEncoding.LENGTH_DELIMITED, BargainsData.class);
        }

        public int encodedSize(BargainsData bargainsData) {
            int i = 0;
            int encodedSizeWithTag = ProtoAdapter.STRING.asRepeated().encodedSizeWithTag(1, bargainsData.goodsId) + (bargainsData.salePrice != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, bargainsData.salePrice) : 0) + (bargainsData.actId != null ? ProtoAdapter.STRING.encodedSizeWithTag(5, bargainsData.actId) : 0) + (bargainsData.product_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(11, bargainsData.product_id) : 0) + (bargainsData.image_url != null ? ProtoAdapter.STRING.encodedSizeWithTag(14, bargainsData.image_url) : 0) + (bargainsData.selectable != null ? ProtoAdapter.BOOL.encodedSizeWithTag(37, bargainsData.selectable) : 0) + SelecInfoData.ADAPTER.asRepeated().encodedSizeWithTag(38, bargainsData.selecInfo) + (bargainsData.bargain_name != null ? ProtoAdapter.STRING.encodedSizeWithTag(39, bargainsData.bargain_name) : 0) + (bargainsData.checked != null ? ProtoAdapter.BOOL.encodedSizeWithTag(40, bargainsData.checked) : 0) + (bargainsData.bargainPrice_txt != null ? ProtoAdapter.STRING.encodedSizeWithTag(41, bargainsData.bargainPrice_txt) : 0) + (bargainsData.bargainGoodsId != null ? ProtoAdapter.STRING.encodedSizeWithTag(42, bargainsData.bargainGoodsId) : 0) + (bargainsData.bargainItemId != null ? ProtoAdapter.STRING.encodedSizeWithTag(43, bargainsData.bargainItemId) : 0);
            if (bargainsData.json_data != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(44, bargainsData.json_data);
            }
            return encodedSizeWithTag + i + bargainsData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, BargainsData bargainsData) throws IOException {
            if (bargainsData.goodsId != null) {
                ProtoAdapter.STRING.asRepeated().encodeWithTag(protoWriter, 1, bargainsData.goodsId);
            }
            if (bargainsData.salePrice != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, bargainsData.salePrice);
            }
            if (bargainsData.actId != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, bargainsData.actId);
            }
            if (bargainsData.product_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 11, bargainsData.product_id);
            }
            if (bargainsData.image_url != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 14, bargainsData.image_url);
            }
            if (bargainsData.selectable != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 37, bargainsData.selectable);
            }
            if (bargainsData.selecInfo != null) {
                SelecInfoData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 38, bargainsData.selecInfo);
            }
            if (bargainsData.bargain_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 39, bargainsData.bargain_name);
            }
            if (bargainsData.checked != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 40, bargainsData.checked);
            }
            if (bargainsData.bargainPrice_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 41, bargainsData.bargainPrice_txt);
            }
            if (bargainsData.bargainGoodsId != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 42, bargainsData.bargainGoodsId);
            }
            if (bargainsData.bargainItemId != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 43, bargainsData.bargainItemId);
            }
            if (bargainsData.json_data != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 44, bargainsData.json_data);
            }
            protoWriter.writeBytes(bargainsData.unknownFields());
        }

        public BargainsData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag == -1) {
                    protoReader.endMessage(beginMessage);
                    return builder.build();
                } else if (nextTag == 5) {
                    builder.actId(ProtoAdapter.STRING.decode(protoReader));
                } else if (nextTag == 11) {
                    builder.product_id(ProtoAdapter.STRING.decode(protoReader));
                } else if (nextTag != 14) {
                    switch (nextTag) {
                        case 1:
                            builder.goodsId.add(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.salePrice(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        default:
                            switch (nextTag) {
                                case 37:
                                    builder.selectable(ProtoAdapter.BOOL.decode(protoReader));
                                    break;
                                case 38:
                                    builder.selecInfo.add(SelecInfoData.ADAPTER.decode(protoReader));
                                    break;
                                case 39:
                                    builder.bargain_name(ProtoAdapter.STRING.decode(protoReader));
                                    break;
                                case 40:
                                    builder.checked(ProtoAdapter.BOOL.decode(protoReader));
                                    break;
                                case 41:
                                    builder.bargainPrice_txt(ProtoAdapter.STRING.decode(protoReader));
                                    break;
                                case 42:
                                    builder.bargainGoodsId(ProtoAdapter.STRING.decode(protoReader));
                                    break;
                                case 43:
                                    builder.bargainItemId(ProtoAdapter.STRING.decode(protoReader));
                                    break;
                                case 44:
                                    builder.json_data(ProtoAdapter.STRING.decode(protoReader));
                                    break;
                                default:
                                    FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                                    builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                                    break;
                            }
                    }
                } else {
                    builder.image_url(ProtoAdapter.STRING.decode(protoReader));
                }
            }
        }

        public BargainsData redact(BargainsData bargainsData) {
            Builder newBuilder = bargainsData.newBuilder();
            Internal.redactElements(newBuilder.selecInfo, SelecInfoData.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
