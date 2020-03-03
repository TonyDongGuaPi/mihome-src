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

public final class RefreshUserInfoData extends Message<RefreshUserInfoData, Builder> {
    public static final ProtoAdapter<RefreshUserInfoData> ADAPTER = new ProtoAdapter_RefreshUserInfoData();
    public static final Integer DEFAULT_NOT_COMMENT_ITEM_COUNT = 0;
    public static final Integer DEFAULT_NOT_PAY_ORDER_COUNT = 0;
    public static final Integer DEFAULT_NOT_USED_COUPON_COUNT = 0;
    public static final Integer DEFAULT_RETURNS_COUNT = 0;
    public static final Integer DEFAULT_SHIP_COUNT = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 5)
    public final Integer not_comment_item_count;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer not_pay_order_count;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 2)
    public final Integer not_used_coupon_count;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 4)
    public final Integer returns_count;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 3)
    public final Integer ship_count;

    public RefreshUserInfoData(Integer num, Integer num2, Integer num3, Integer num4, Integer num5) {
        this(num, num2, num3, num4, num5, ByteString.EMPTY);
    }

    public RefreshUserInfoData(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, ByteString byteString) {
        super(ADAPTER, byteString);
        this.not_pay_order_count = num;
        this.not_used_coupon_count = num2;
        this.ship_count = num3;
        this.returns_count = num4;
        this.not_comment_item_count = num5;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.not_pay_order_count = this.not_pay_order_count;
        builder.not_used_coupon_count = this.not_used_coupon_count;
        builder.ship_count = this.ship_count;
        builder.returns_count = this.returns_count;
        builder.not_comment_item_count = this.not_comment_item_count;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RefreshUserInfoData)) {
            return false;
        }
        RefreshUserInfoData refreshUserInfoData = (RefreshUserInfoData) obj;
        if (!Internal.equals(unknownFields(), refreshUserInfoData.unknownFields()) || !Internal.equals(this.not_pay_order_count, refreshUserInfoData.not_pay_order_count) || !Internal.equals(this.not_used_coupon_count, refreshUserInfoData.not_used_coupon_count) || !Internal.equals(this.ship_count, refreshUserInfoData.ship_count) || !Internal.equals(this.returns_count, refreshUserInfoData.returns_count) || !Internal.equals(this.not_comment_item_count, refreshUserInfoData.not_comment_item_count)) {
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
        int hashCode = ((((((((unknownFields().hashCode() * 37) + (this.not_pay_order_count != null ? this.not_pay_order_count.hashCode() : 0)) * 37) + (this.not_used_coupon_count != null ? this.not_used_coupon_count.hashCode() : 0)) * 37) + (this.ship_count != null ? this.ship_count.hashCode() : 0)) * 37) + (this.returns_count != null ? this.returns_count.hashCode() : 0)) * 37;
        if (this.not_comment_item_count != null) {
            i2 = this.not_comment_item_count.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.not_pay_order_count != null) {
            sb.append(", not_pay_order_count=");
            sb.append(this.not_pay_order_count);
        }
        if (this.not_used_coupon_count != null) {
            sb.append(", not_used_coupon_count=");
            sb.append(this.not_used_coupon_count);
        }
        if (this.ship_count != null) {
            sb.append(", ship_count=");
            sb.append(this.ship_count);
        }
        if (this.returns_count != null) {
            sb.append(", returns_count=");
            sb.append(this.returns_count);
        }
        if (this.not_comment_item_count != null) {
            sb.append(", not_comment_item_count=");
            sb.append(this.not_comment_item_count);
        }
        StringBuilder replace = sb.replace(0, 2, "RefreshUserInfoData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<RefreshUserInfoData, Builder> {
        public Integer not_comment_item_count;
        public Integer not_pay_order_count;
        public Integer not_used_coupon_count;
        public Integer returns_count;
        public Integer ship_count;

        public Builder not_pay_order_count(Integer num) {
            this.not_pay_order_count = num;
            return this;
        }

        public Builder not_used_coupon_count(Integer num) {
            this.not_used_coupon_count = num;
            return this;
        }

        public Builder ship_count(Integer num) {
            this.ship_count = num;
            return this;
        }

        public Builder returns_count(Integer num) {
            this.returns_count = num;
            return this;
        }

        public Builder not_comment_item_count(Integer num) {
            this.not_comment_item_count = num;
            return this;
        }

        public RefreshUserInfoData build() {
            return new RefreshUserInfoData(this.not_pay_order_count, this.not_used_coupon_count, this.ship_count, this.returns_count, this.not_comment_item_count, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_RefreshUserInfoData extends ProtoAdapter<RefreshUserInfoData> {
        ProtoAdapter_RefreshUserInfoData() {
            super(FieldEncoding.LENGTH_DELIMITED, RefreshUserInfoData.class);
        }

        public int encodedSize(RefreshUserInfoData refreshUserInfoData) {
            int i = 0;
            int encodedSizeWithTag = (refreshUserInfoData.not_pay_order_count != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, refreshUserInfoData.not_pay_order_count) : 0) + (refreshUserInfoData.not_used_coupon_count != null ? ProtoAdapter.INT32.encodedSizeWithTag(2, refreshUserInfoData.not_used_coupon_count) : 0) + (refreshUserInfoData.ship_count != null ? ProtoAdapter.INT32.encodedSizeWithTag(3, refreshUserInfoData.ship_count) : 0) + (refreshUserInfoData.returns_count != null ? ProtoAdapter.INT32.encodedSizeWithTag(4, refreshUserInfoData.returns_count) : 0);
            if (refreshUserInfoData.not_comment_item_count != null) {
                i = ProtoAdapter.INT32.encodedSizeWithTag(5, refreshUserInfoData.not_comment_item_count);
            }
            return encodedSizeWithTag + i + refreshUserInfoData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, RefreshUserInfoData refreshUserInfoData) throws IOException {
            if (refreshUserInfoData.not_pay_order_count != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, refreshUserInfoData.not_pay_order_count);
            }
            if (refreshUserInfoData.not_used_coupon_count != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 2, refreshUserInfoData.not_used_coupon_count);
            }
            if (refreshUserInfoData.ship_count != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 3, refreshUserInfoData.ship_count);
            }
            if (refreshUserInfoData.returns_count != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 4, refreshUserInfoData.returns_count);
            }
            if (refreshUserInfoData.not_comment_item_count != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 5, refreshUserInfoData.not_comment_item_count);
            }
            protoWriter.writeBytes(refreshUserInfoData.unknownFields());
        }

        public RefreshUserInfoData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.not_pay_order_count(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 2:
                            builder.not_used_coupon_count(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 3:
                            builder.ship_count(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 4:
                            builder.returns_count(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 5:
                            builder.not_comment_item_count(ProtoAdapter.INT32.decode(protoReader));
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

        public RefreshUserInfoData redact(RefreshUserInfoData refreshUserInfoData) {
            Builder newBuilder = refreshUserInfoData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
