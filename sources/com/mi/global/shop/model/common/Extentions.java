package com.mi.global.shop.model.common;

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

public final class Extentions extends Message<Extentions, Builder> {
    public static final ProtoAdapter<Extentions> ADAPTER = new ProtoAdapter_Extentions();
    public static final String DEFAULT_GOODS_DEALER = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String goods_dealer;

    public Extentions(String str) {
        this(str, ByteString.EMPTY);
    }

    public Extentions(String str, ByteString byteString) {
        super(ADAPTER, byteString);
        this.goods_dealer = str;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.goods_dealer = this.goods_dealer;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Extentions)) {
            return false;
        }
        Extentions extentions = (Extentions) obj;
        if (!Internal.equals(unknownFields(), extentions.unknownFields()) || !Internal.equals(this.goods_dealer, extentions.goods_dealer)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = (unknownFields().hashCode() * 37) + (this.goods_dealer != null ? this.goods_dealer.hashCode() : 0);
        this.hashCode = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.goods_dealer != null) {
            sb.append(", goods_dealer=");
            sb.append(this.goods_dealer);
        }
        StringBuilder replace = sb.replace(0, 2, "Extentions{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Extentions, Builder> {
        public String goods_dealer;

        public Builder goods_dealer(String str) {
            this.goods_dealer = str;
            return this;
        }

        public Extentions build() {
            return new Extentions(this.goods_dealer, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Extentions extends ProtoAdapter<Extentions> {
        ProtoAdapter_Extentions() {
            super(FieldEncoding.LENGTH_DELIMITED, Extentions.class);
        }

        public int encodedSize(Extentions extentions) {
            return (extentions.goods_dealer != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, extentions.goods_dealer) : 0) + extentions.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, Extentions extentions) throws IOException {
            if (extentions.goods_dealer != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, extentions.goods_dealer);
            }
            protoWriter.writeBytes(extentions.unknownFields());
        }

        public Extentions decode(ProtoReader protoReader) throws IOException {
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
                    builder.goods_dealer(ProtoAdapter.STRING.decode(protoReader));
                }
            }
        }

        public Extentions redact(Extentions extentions) {
            Builder newBuilder = extentions.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
