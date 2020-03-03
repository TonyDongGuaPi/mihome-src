package com.mi.global.shop.model.buy;

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

public final class CartInfoData extends Message<CartInfoData, Builder> {
    public static final ProtoAdapter<CartInfoData> ADAPTER = new ProtoAdapter_CartInfoData();
    public static final String DEFAULT_ACTIVITYDISCOUNTMONEY = "";
    public static final String DEFAULT_PRODUCTMONEY = "";
    public static final String DEFAULT_PRODUCTMONEY_TXT = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String activityDiscountMoney;
    @WireField(adapter = "com.mi.global.shop.model.buy.ItemsData#ADAPTER", label = WireField.Label.REPEATED, tag = 1)
    public final List<ItemsData> items;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String productMoney;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String productMoney_txt;

    public CartInfoData(List<ItemsData> list, String str, String str2, String str3) {
        this(list, str, str2, str3, ByteString.EMPTY);
    }

    public CartInfoData(List<ItemsData> list, String str, String str2, String str3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.items = Internal.immutableCopyOf("items", list);
        this.productMoney = str;
        this.activityDiscountMoney = str2;
        this.productMoney_txt = str3;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.items = Internal.copyOf("items", this.items);
        builder.productMoney = this.productMoney;
        builder.activityDiscountMoney = this.activityDiscountMoney;
        builder.productMoney_txt = this.productMoney_txt;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CartInfoData)) {
            return false;
        }
        CartInfoData cartInfoData = (CartInfoData) obj;
        if (!Internal.equals(unknownFields(), cartInfoData.unknownFields()) || !Internal.equals(this.items, cartInfoData.items) || !Internal.equals(this.productMoney, cartInfoData.productMoney) || !Internal.equals(this.activityDiscountMoney, cartInfoData.activityDiscountMoney) || !Internal.equals(this.productMoney_txt, cartInfoData.productMoney_txt)) {
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
        int hashCode = ((((((unknownFields().hashCode() * 37) + (this.items != null ? this.items.hashCode() : 1)) * 37) + (this.productMoney != null ? this.productMoney.hashCode() : 0)) * 37) + (this.activityDiscountMoney != null ? this.activityDiscountMoney.hashCode() : 0)) * 37;
        if (this.productMoney_txt != null) {
            i2 = this.productMoney_txt.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.items != null) {
            sb.append(", items=");
            sb.append(this.items);
        }
        if (this.productMoney != null) {
            sb.append(", productMoney=");
            sb.append(this.productMoney);
        }
        if (this.activityDiscountMoney != null) {
            sb.append(", activityDiscountMoney=");
            sb.append(this.activityDiscountMoney);
        }
        if (this.productMoney_txt != null) {
            sb.append(", productMoney_txt=");
            sb.append(this.productMoney_txt);
        }
        StringBuilder replace = sb.replace(0, 2, "CartInfoData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<CartInfoData, Builder> {
        public String activityDiscountMoney;
        public List<ItemsData> items = Internal.newMutableList();
        public String productMoney;
        public String productMoney_txt;

        public Builder items(List<ItemsData> list) {
            Internal.checkElementsNotNull(list);
            this.items = list;
            return this;
        }

        public Builder productMoney(String str) {
            this.productMoney = str;
            return this;
        }

        public Builder activityDiscountMoney(String str) {
            this.activityDiscountMoney = str;
            return this;
        }

        public Builder productMoney_txt(String str) {
            this.productMoney_txt = str;
            return this;
        }

        public CartInfoData build() {
            return new CartInfoData(this.items, this.productMoney, this.activityDiscountMoney, this.productMoney_txt, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_CartInfoData extends ProtoAdapter<CartInfoData> {
        ProtoAdapter_CartInfoData() {
            super(FieldEncoding.LENGTH_DELIMITED, CartInfoData.class);
        }

        public int encodedSize(CartInfoData cartInfoData) {
            int i = 0;
            int encodedSizeWithTag = ItemsData.ADAPTER.asRepeated().encodedSizeWithTag(1, cartInfoData.items) + (cartInfoData.productMoney != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, cartInfoData.productMoney) : 0) + (cartInfoData.activityDiscountMoney != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, cartInfoData.activityDiscountMoney) : 0);
            if (cartInfoData.productMoney_txt != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(4, cartInfoData.productMoney_txt);
            }
            return encodedSizeWithTag + i + cartInfoData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, CartInfoData cartInfoData) throws IOException {
            if (cartInfoData.items != null) {
                ItemsData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 1, cartInfoData.items);
            }
            if (cartInfoData.productMoney != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, cartInfoData.productMoney);
            }
            if (cartInfoData.activityDiscountMoney != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, cartInfoData.activityDiscountMoney);
            }
            if (cartInfoData.productMoney_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, cartInfoData.productMoney_txt);
            }
            protoWriter.writeBytes(cartInfoData.unknownFields());
        }

        public CartInfoData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.items.add(ItemsData.ADAPTER.decode(protoReader));
                            break;
                        case 2:
                            builder.productMoney(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.activityDiscountMoney(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.productMoney_txt(ProtoAdapter.STRING.decode(protoReader));
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

        public CartInfoData redact(CartInfoData cartInfoData) {
            Builder newBuilder = cartInfoData.newBuilder();
            Internal.redactElements(newBuilder.items, ItemsData.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
