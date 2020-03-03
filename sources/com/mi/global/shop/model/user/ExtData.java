package com.mi.global.shop.model.user;

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

public final class ExtData extends Message<ExtData, Builder> {
    public static final ProtoAdapter<ExtData> ADAPTER = new ProtoAdapter_ExtData();
    public static final Integer DEFAULT_SEND = 0;
    public static final Integer DEFAULT_UNPAID = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 2)
    public final Integer send;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer unpaid;

    public ExtData(Integer num, Integer num2) {
        this(num, num2, ByteString.EMPTY);
    }

    public ExtData(Integer num, Integer num2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.unpaid = num;
        this.send = num2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.unpaid = this.unpaid;
        builder.send = this.send;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExtData)) {
            return false;
        }
        ExtData extData = (ExtData) obj;
        if (!Internal.equals(unknownFields(), extData.unknownFields()) || !Internal.equals(this.unpaid, extData.unpaid) || !Internal.equals(this.send, extData.send)) {
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
        int hashCode = ((unknownFields().hashCode() * 37) + (this.unpaid != null ? this.unpaid.hashCode() : 0)) * 37;
        if (this.send != null) {
            i2 = this.send.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.unpaid != null) {
            sb.append(", unpaid=");
            sb.append(this.unpaid);
        }
        if (this.send != null) {
            sb.append(", send=");
            sb.append(this.send);
        }
        StringBuilder replace = sb.replace(0, 2, "ExtData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<ExtData, Builder> {
        public Integer send;
        public Integer unpaid;

        public Builder unpaid(Integer num) {
            this.unpaid = num;
            return this;
        }

        public Builder send(Integer num) {
            this.send = num;
            return this;
        }

        public ExtData build() {
            return new ExtData(this.unpaid, this.send, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_ExtData extends ProtoAdapter<ExtData> {
        ProtoAdapter_ExtData() {
            super(FieldEncoding.LENGTH_DELIMITED, ExtData.class);
        }

        public int encodedSize(ExtData extData) {
            int i = 0;
            int encodedSizeWithTag = extData.unpaid != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, extData.unpaid) : 0;
            if (extData.send != null) {
                i = ProtoAdapter.INT32.encodedSizeWithTag(2, extData.send);
            }
            return encodedSizeWithTag + i + extData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, ExtData extData) throws IOException {
            if (extData.unpaid != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, extData.unpaid);
            }
            if (extData.send != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 2, extData.send);
            }
            protoWriter.writeBytes(extData.unknownFields());
        }

        public ExtData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.unpaid(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 2:
                            builder.send(ProtoAdapter.INT32.decode(protoReader));
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

        public ExtData redact(ExtData extData) {
            Builder newBuilder = extData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
