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

public final class onlinepayCouponInfoData extends Message<onlinepayCouponInfoData, Builder> {
    public static final ProtoAdapter<onlinepayCouponInfoData> ADAPTER = new ProtoAdapter_onlinepayCouponInfoData();
    public static final String DEFAULT_MENTION = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String mention;

    public onlinepayCouponInfoData(String str) {
        this(str, ByteString.EMPTY);
    }

    public onlinepayCouponInfoData(String str, ByteString byteString) {
        super(ADAPTER, byteString);
        this.mention = str;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.mention = this.mention;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof onlinepayCouponInfoData)) {
            return false;
        }
        onlinepayCouponInfoData onlinepaycouponinfodata = (onlinepayCouponInfoData) obj;
        if (!Internal.equals(unknownFields(), onlinepaycouponinfodata.unknownFields()) || !Internal.equals(this.mention, onlinepaycouponinfodata.mention)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = (unknownFields().hashCode() * 37) + (this.mention != null ? this.mention.hashCode() : 0);
        this.hashCode = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.mention != null) {
            sb.append(", mention=");
            sb.append(this.mention);
        }
        StringBuilder replace = sb.replace(0, 2, "onlinepayCouponInfoData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<onlinepayCouponInfoData, Builder> {
        public String mention;

        public Builder mention(String str) {
            this.mention = str;
            return this;
        }

        public onlinepayCouponInfoData build() {
            return new onlinepayCouponInfoData(this.mention, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_onlinepayCouponInfoData extends ProtoAdapter<onlinepayCouponInfoData> {
        ProtoAdapter_onlinepayCouponInfoData() {
            super(FieldEncoding.LENGTH_DELIMITED, onlinepayCouponInfoData.class);
        }

        public int encodedSize(onlinepayCouponInfoData onlinepaycouponinfodata) {
            return (onlinepaycouponinfodata.mention != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, onlinepaycouponinfodata.mention) : 0) + onlinepaycouponinfodata.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, onlinepayCouponInfoData onlinepaycouponinfodata) throws IOException {
            if (onlinepaycouponinfodata.mention != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, onlinepaycouponinfodata.mention);
            }
            protoWriter.writeBytes(onlinepaycouponinfodata.unknownFields());
        }

        public onlinepayCouponInfoData decode(ProtoReader protoReader) throws IOException {
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
                    builder.mention(ProtoAdapter.STRING.decode(protoReader));
                }
            }
        }

        public onlinepayCouponInfoData redact(onlinepayCouponInfoData onlinepaycouponinfodata) {
            Builder newBuilder = onlinepaycouponinfodata.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
