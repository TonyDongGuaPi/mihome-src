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

public final class PackInfo extends Message<PackInfo, Builder> {
    public static final ProtoAdapter<PackInfo> ADAPTER = new ProtoAdapter_PackInfo();
    public static final String DEFAULT_URL = "";
    public static final Integer DEFAULT_VERSION = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String url;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer version;

    public PackInfo(Integer num, String str) {
        this(num, str, ByteString.EMPTY);
    }

    public PackInfo(Integer num, String str, ByteString byteString) {
        super(ADAPTER, byteString);
        this.version = num;
        this.url = str;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.version = this.version;
        builder.url = this.url;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PackInfo)) {
            return false;
        }
        PackInfo packInfo = (PackInfo) obj;
        if (!Internal.equals(unknownFields(), packInfo.unknownFields()) || !Internal.equals(this.version, packInfo.version) || !Internal.equals(this.url, packInfo.url)) {
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
        int hashCode = ((unknownFields().hashCode() * 37) + (this.version != null ? this.version.hashCode() : 0)) * 37;
        if (this.url != null) {
            i2 = this.url.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.version != null) {
            sb.append(", version=");
            sb.append(this.version);
        }
        if (this.url != null) {
            sb.append(", url=");
            sb.append(this.url);
        }
        StringBuilder replace = sb.replace(0, 2, "PackInfo{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<PackInfo, Builder> {
        public String url;
        public Integer version;

        public Builder version(Integer num) {
            this.version = num;
            return this;
        }

        public Builder url(String str) {
            this.url = str;
            return this;
        }

        public PackInfo build() {
            return new PackInfo(this.version, this.url, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_PackInfo extends ProtoAdapter<PackInfo> {
        ProtoAdapter_PackInfo() {
            super(FieldEncoding.LENGTH_DELIMITED, PackInfo.class);
        }

        public int encodedSize(PackInfo packInfo) {
            int i = 0;
            int encodedSizeWithTag = packInfo.version != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, packInfo.version) : 0;
            if (packInfo.url != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(2, packInfo.url);
            }
            return encodedSizeWithTag + i + packInfo.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, PackInfo packInfo) throws IOException {
            if (packInfo.version != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, packInfo.version);
            }
            if (packInfo.url != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, packInfo.url);
            }
            protoWriter.writeBytes(packInfo.unknownFields());
        }

        public PackInfo decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.version(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 2:
                            builder.url(ProtoAdapter.STRING.decode(protoReader));
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

        public PackInfo redact(PackInfo packInfo) {
            Builder newBuilder = packInfo.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
