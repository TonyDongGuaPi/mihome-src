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
import okio.ByteString;

public final class OrderItemsData extends Message<OrderItemsData, Builder> {
    public static final ProtoAdapter<OrderItemsData> ADAPTER = new ProtoAdapter_OrderItemsData();
    public static final String DEFAULT_CART_PRICE = "";
    public static final String DEFAULT_GOODS_ID = "";
    public static final String DEFAULT_IMAGE_URL = "";
    public static final String DEFAULT_PRICE = "";
    public static final String DEFAULT_PRODUCT_COUNT = "";
    public static final String DEFAULT_PRODUCT_ID = "";
    public static final String DEFAULT_PRODUCT_NAME = "";
    public static final String DEFAULT_SUBTOTAL = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 10)
    public final String cart_price;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String goods_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String image_url;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 11)
    public final String price;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final String product_count;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String product_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String product_name;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 12)
    public final String subtotal;

    public OrderItemsData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this(str, str2, str3, str4, str5, str6, str7, str8, ByteString.EMPTY);
    }

    public OrderItemsData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, ByteString byteString) {
        super(ADAPTER, byteString);
        this.product_id = str;
        this.goods_id = str2;
        this.product_name = str3;
        this.image_url = str4;
        this.product_count = str5;
        this.cart_price = str6;
        this.price = str7;
        this.subtotal = str8;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.product_id = this.product_id;
        builder.goods_id = this.goods_id;
        builder.product_name = this.product_name;
        builder.image_url = this.image_url;
        builder.product_count = this.product_count;
        builder.cart_price = this.cart_price;
        builder.price = this.price;
        builder.subtotal = this.subtotal;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OrderItemsData)) {
            return false;
        }
        OrderItemsData orderItemsData = (OrderItemsData) obj;
        if (!Internal.equals(unknownFields(), orderItemsData.unknownFields()) || !Internal.equals(this.product_id, orderItemsData.product_id) || !Internal.equals(this.goods_id, orderItemsData.goods_id) || !Internal.equals(this.product_name, orderItemsData.product_name) || !Internal.equals(this.image_url, orderItemsData.image_url) || !Internal.equals(this.product_count, orderItemsData.product_count) || !Internal.equals(this.cart_price, orderItemsData.cart_price) || !Internal.equals(this.price, orderItemsData.price) || !Internal.equals(this.subtotal, orderItemsData.subtotal)) {
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
        int hashCode = ((((((((((((((unknownFields().hashCode() * 37) + (this.product_id != null ? this.product_id.hashCode() : 0)) * 37) + (this.goods_id != null ? this.goods_id.hashCode() : 0)) * 37) + (this.product_name != null ? this.product_name.hashCode() : 0)) * 37) + (this.image_url != null ? this.image_url.hashCode() : 0)) * 37) + (this.product_count != null ? this.product_count.hashCode() : 0)) * 37) + (this.cart_price != null ? this.cart_price.hashCode() : 0)) * 37) + (this.price != null ? this.price.hashCode() : 0)) * 37;
        if (this.subtotal != null) {
            i2 = this.subtotal.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.product_id != null) {
            sb.append(", product_id=");
            sb.append(this.product_id);
        }
        if (this.goods_id != null) {
            sb.append(", goods_id=");
            sb.append(this.goods_id);
        }
        if (this.product_name != null) {
            sb.append(", product_name=");
            sb.append(this.product_name);
        }
        if (this.image_url != null) {
            sb.append(", image_url=");
            sb.append(this.image_url);
        }
        if (this.product_count != null) {
            sb.append(", product_count=");
            sb.append(this.product_count);
        }
        if (this.cart_price != null) {
            sb.append(", cart_price=");
            sb.append(this.cart_price);
        }
        if (this.price != null) {
            sb.append(", price=");
            sb.append(this.price);
        }
        if (this.subtotal != null) {
            sb.append(", subtotal=");
            sb.append(this.subtotal);
        }
        StringBuilder replace = sb.replace(0, 2, "OrderItemsData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<OrderItemsData, Builder> {
        public String cart_price;
        public String goods_id;
        public String image_url;
        public String price;
        public String product_count;
        public String product_id;
        public String product_name;
        public String subtotal;

        public Builder product_id(String str) {
            this.product_id = str;
            return this;
        }

        public Builder goods_id(String str) {
            this.goods_id = str;
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

        public Builder product_count(String str) {
            this.product_count = str;
            return this;
        }

        public Builder cart_price(String str) {
            this.cart_price = str;
            return this;
        }

        public Builder price(String str) {
            this.price = str;
            return this;
        }

        public Builder subtotal(String str) {
            this.subtotal = str;
            return this;
        }

        public OrderItemsData build() {
            return new OrderItemsData(this.product_id, this.goods_id, this.product_name, this.image_url, this.product_count, this.cart_price, this.price, this.subtotal, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_OrderItemsData extends ProtoAdapter<OrderItemsData> {
        ProtoAdapter_OrderItemsData() {
            super(FieldEncoding.LENGTH_DELIMITED, OrderItemsData.class);
        }

        public int encodedSize(OrderItemsData orderItemsData) {
            int i = 0;
            int encodedSizeWithTag = (orderItemsData.product_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, orderItemsData.product_id) : 0) + (orderItemsData.goods_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, orderItemsData.goods_id) : 0) + (orderItemsData.product_name != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, orderItemsData.product_name) : 0) + (orderItemsData.image_url != null ? ProtoAdapter.STRING.encodedSizeWithTag(5, orderItemsData.image_url) : 0) + (orderItemsData.product_count != null ? ProtoAdapter.STRING.encodedSizeWithTag(9, orderItemsData.product_count) : 0) + (orderItemsData.cart_price != null ? ProtoAdapter.STRING.encodedSizeWithTag(10, orderItemsData.cart_price) : 0) + (orderItemsData.price != null ? ProtoAdapter.STRING.encodedSizeWithTag(11, orderItemsData.price) : 0);
            if (orderItemsData.subtotal != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(12, orderItemsData.subtotal);
            }
            return encodedSizeWithTag + i + orderItemsData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, OrderItemsData orderItemsData) throws IOException {
            if (orderItemsData.product_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, orderItemsData.product_id);
            }
            if (orderItemsData.goods_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, orderItemsData.goods_id);
            }
            if (orderItemsData.product_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, orderItemsData.product_name);
            }
            if (orderItemsData.image_url != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, orderItemsData.image_url);
            }
            if (orderItemsData.product_count != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 9, orderItemsData.product_count);
            }
            if (orderItemsData.cart_price != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 10, orderItemsData.cart_price);
            }
            if (orderItemsData.price != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 11, orderItemsData.price);
            }
            if (orderItemsData.subtotal != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 12, orderItemsData.subtotal);
            }
            protoWriter.writeBytes(orderItemsData.unknownFields());
        }

        public OrderItemsData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.product_id(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.goods_id(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.product_name(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 5:
                            builder.image_url(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 9:
                            builder.product_count(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 10:
                            builder.cart_price(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 11:
                            builder.price(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 12:
                            builder.subtotal(ProtoAdapter.STRING.decode(protoReader));
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

        public OrderItemsData redact(OrderItemsData orderItemsData) {
            Builder newBuilder = orderItemsData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
