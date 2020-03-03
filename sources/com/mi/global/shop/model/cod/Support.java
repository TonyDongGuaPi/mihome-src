package com.mi.global.shop.model.cod;

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

public final class Support extends Message<Support, Builder> {
    public static final ProtoAdapter<Support> ADAPTER = new ProtoAdapter_Support();
    public static final String DEFAULT_CODSTATUS = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String codstatus;

    public Support(String str) {
        this(str, ByteString.EMPTY);
    }

    public Support(String str, ByteString byteString) {
        super(ADAPTER, byteString);
        this.codstatus = str;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.codstatus = this.codstatus;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Support)) {
            return false;
        }
        Support support = (Support) obj;
        if (!Internal.equals(unknownFields(), support.unknownFields()) || !Internal.equals(this.codstatus, support.codstatus)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = (unknownFields().hashCode() * 37) + (this.codstatus != null ? this.codstatus.hashCode() : 0);
        this.hashCode = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.codstatus != null) {
            sb.append(", codstatus=");
            sb.append(this.codstatus);
        }
        StringBuilder replace = sb.replace(0, 2, "Support{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Support, Builder> {
        public String codstatus;

        public Builder codstatus(String str) {
            this.codstatus = str;
            return this;
        }

        public Support build() {
            return new Support(this.codstatus, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Support extends ProtoAdapter<Support> {
        ProtoAdapter_Support() {
            super(FieldEncoding.LENGTH_DELIMITED, Support.class);
        }

        public int encodedSize(Support support) {
            return (support.codstatus != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, support.codstatus) : 0) + support.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, Support support) throws IOException {
            if (support.codstatus != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, support.codstatus);
            }
            protoWriter.writeBytes(support.unknownFields());
        }

        public Support decode(ProtoReader protoReader) throws IOException {
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
                    builder.codstatus(ProtoAdapter.STRING.decode(protoReader));
                }
            }
        }

        public Support redact(Support support) {
            Builder newBuilder = support.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
