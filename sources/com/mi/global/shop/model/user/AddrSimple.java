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

public final class AddrSimple extends Message<AddrSimple, Builder> {
    public static final ProtoAdapter<AddrSimple> ADAPTER = new ProtoAdapter_AddrSimple();
    public static final String DEFAULT_ID = "";
    public static final String DEFAULT_NAME = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String name;

    public AddrSimple(String str, String str2) {
        this(str, str2, ByteString.EMPTY);
    }

    public AddrSimple(String str, String str2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.id = str;
        this.name = str2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.id = this.id;
        builder.name = this.name;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AddrSimple)) {
            return false;
        }
        AddrSimple addrSimple = (AddrSimple) obj;
        if (!Internal.equals(unknownFields(), addrSimple.unknownFields()) || !Internal.equals(this.id, addrSimple.id) || !Internal.equals(this.name, addrSimple.name)) {
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
        int hashCode = ((unknownFields().hashCode() * 37) + (this.id != null ? this.id.hashCode() : 0)) * 37;
        if (this.name != null) {
            i2 = this.name.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.id != null) {
            sb.append(", id=");
            sb.append(this.id);
        }
        if (this.name != null) {
            sb.append(", name=");
            sb.append(this.name);
        }
        StringBuilder replace = sb.replace(0, 2, "AddrSimple{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<AddrSimple, Builder> {
        public String id;
        public String name;

        public Builder id(String str) {
            this.id = str;
            return this;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public AddrSimple build() {
            return new AddrSimple(this.id, this.name, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_AddrSimple extends ProtoAdapter<AddrSimple> {
        ProtoAdapter_AddrSimple() {
            super(FieldEncoding.LENGTH_DELIMITED, AddrSimple.class);
        }

        public int encodedSize(AddrSimple addrSimple) {
            int i = 0;
            int encodedSizeWithTag = addrSimple.id != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, addrSimple.id) : 0;
            if (addrSimple.name != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(2, addrSimple.name);
            }
            return encodedSizeWithTag + i + addrSimple.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, AddrSimple addrSimple) throws IOException {
            if (addrSimple.id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, addrSimple.id);
            }
            if (addrSimple.name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, addrSimple.name);
            }
            protoWriter.writeBytes(addrSimple.unknownFields());
        }

        public AddrSimple decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.id(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.name(ProtoAdapter.STRING.decode(protoReader));
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

        public AddrSimple redact(AddrSimple addrSimple) {
            Builder newBuilder = addrSimple.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
