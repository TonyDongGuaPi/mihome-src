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

public final class CookieInfo extends Message<CookieInfo, Builder> {
    public static final ProtoAdapter<CookieInfo> ADAPTER = new ProtoAdapter_CookieInfo();
    public static final String DEFAULT_DOMAIN = "";
    public static final String DEFAULT_KEY = "";
    public static final String DEFAULT_PATH = "";
    public static final String DEFAULT_VALUE = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String domain;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String key;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String path;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String value;

    public CookieInfo(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, ByteString.EMPTY);
    }

    public CookieInfo(String str, String str2, String str3, String str4, ByteString byteString) {
        super(ADAPTER, byteString);
        this.key = str;
        this.value = str2;
        this.domain = str3;
        this.path = str4;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.key = this.key;
        builder.value = this.value;
        builder.domain = this.domain;
        builder.path = this.path;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CookieInfo)) {
            return false;
        }
        CookieInfo cookieInfo = (CookieInfo) obj;
        if (!Internal.equals(unknownFields(), cookieInfo.unknownFields()) || !Internal.equals(this.key, cookieInfo.key) || !Internal.equals(this.value, cookieInfo.value) || !Internal.equals(this.domain, cookieInfo.domain) || !Internal.equals(this.path, cookieInfo.path)) {
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
        int hashCode = ((((((unknownFields().hashCode() * 37) + (this.key != null ? this.key.hashCode() : 0)) * 37) + (this.value != null ? this.value.hashCode() : 0)) * 37) + (this.domain != null ? this.domain.hashCode() : 0)) * 37;
        if (this.path != null) {
            i2 = this.path.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.key != null) {
            sb.append(", key=");
            sb.append(this.key);
        }
        if (this.value != null) {
            sb.append(", value=");
            sb.append(this.value);
        }
        if (this.domain != null) {
            sb.append(", domain=");
            sb.append(this.domain);
        }
        if (this.path != null) {
            sb.append(", path=");
            sb.append(this.path);
        }
        StringBuilder replace = sb.replace(0, 2, "CookieInfo{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<CookieInfo, Builder> {
        public String domain;
        public String key;
        public String path;
        public String value;

        public Builder key(String str) {
            this.key = str;
            return this;
        }

        public Builder value(String str) {
            this.value = str;
            return this;
        }

        public Builder domain(String str) {
            this.domain = str;
            return this;
        }

        public Builder path(String str) {
            this.path = str;
            return this;
        }

        public CookieInfo build() {
            return new CookieInfo(this.key, this.value, this.domain, this.path, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_CookieInfo extends ProtoAdapter<CookieInfo> {
        ProtoAdapter_CookieInfo() {
            super(FieldEncoding.LENGTH_DELIMITED, CookieInfo.class);
        }

        public int encodedSize(CookieInfo cookieInfo) {
            int i = 0;
            int encodedSizeWithTag = (cookieInfo.key != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, cookieInfo.key) : 0) + (cookieInfo.value != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, cookieInfo.value) : 0) + (cookieInfo.domain != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, cookieInfo.domain) : 0);
            if (cookieInfo.path != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(4, cookieInfo.path);
            }
            return encodedSizeWithTag + i + cookieInfo.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, CookieInfo cookieInfo) throws IOException {
            if (cookieInfo.key != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, cookieInfo.key);
            }
            if (cookieInfo.value != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, cookieInfo.value);
            }
            if (cookieInfo.domain != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, cookieInfo.domain);
            }
            if (cookieInfo.path != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, cookieInfo.path);
            }
            protoWriter.writeBytes(cookieInfo.unknownFields());
        }

        public CookieInfo decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.key(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.value(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.domain(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.path(ProtoAdapter.STRING.decode(protoReader));
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

        public CookieInfo redact(CookieInfo cookieInfo) {
            Builder newBuilder = cookieInfo.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
