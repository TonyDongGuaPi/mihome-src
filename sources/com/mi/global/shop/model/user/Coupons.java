package com.mi.global.shop.model.user;

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

public final class Coupons extends Message<Coupons, Builder> {
    public static final ProtoAdapter<Coupons> ADAPTER = new ProtoAdapter_Coupons();
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.user.CouponData#ADAPTER", label = WireField.Label.REPEATED, tag = 1)
    public final List<CouponData> coupons;

    public Coupons(List<CouponData> list) {
        this(list, ByteString.EMPTY);
    }

    public Coupons(List<CouponData> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.coupons = Internal.immutableCopyOf(Tags.ShoppingCartList.COUPONS, list);
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.coupons = Internal.copyOf(Tags.ShoppingCartList.COUPONS, this.coupons);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Coupons)) {
            return false;
        }
        Coupons coupons2 = (Coupons) obj;
        if (!Internal.equals(unknownFields(), coupons2.unknownFields()) || !Internal.equals(this.coupons, coupons2.coupons)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = (unknownFields().hashCode() * 37) + (this.coupons != null ? this.coupons.hashCode() : 1);
        this.hashCode = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.coupons != null) {
            sb.append(", coupons=");
            sb.append(this.coupons);
        }
        StringBuilder replace = sb.replace(0, 2, "Coupons{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Coupons, Builder> {
        public List<CouponData> coupons = Internal.newMutableList();

        public Builder coupons(List<CouponData> list) {
            Internal.checkElementsNotNull(list);
            this.coupons = list;
            return this;
        }

        public Coupons build() {
            return new Coupons(this.coupons, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Coupons extends ProtoAdapter<Coupons> {
        ProtoAdapter_Coupons() {
            super(FieldEncoding.LENGTH_DELIMITED, Coupons.class);
        }

        public int encodedSize(Coupons coupons) {
            return CouponData.ADAPTER.asRepeated().encodedSizeWithTag(1, coupons.coupons) + coupons.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, Coupons coupons) throws IOException {
            if (coupons.coupons != null) {
                CouponData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 1, coupons.coupons);
            }
            protoWriter.writeBytes(coupons.unknownFields());
        }

        public Coupons decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag == -1) {
                    protoReader.endMessage(beginMessage);
                    return builder.build();
                } else if (nextTag != 1) {
                    FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                    builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                } else {
                    builder.coupons.add(CouponData.ADAPTER.decode(protoReader));
                }
            }
        }

        public Coupons redact(Coupons coupons) {
            Builder newBuilder = coupons.newBuilder();
            Internal.redactElements(newBuilder.coupons, CouponData.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
