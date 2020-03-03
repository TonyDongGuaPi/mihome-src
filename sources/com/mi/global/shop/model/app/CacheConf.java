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

public final class CacheConf extends Message<CacheConf, Builder> {
    public static final ProtoAdapter<CacheConf> ADAPTER = new ProtoAdapter_CacheConf();
    public static final Boolean DEFAULT_CSS = false;
    public static final Boolean DEFAULT_ENABLE = false;
    public static final Boolean DEFAULT_HTML = false;
    public static final Boolean DEFAULT_JS = false;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 3)
    public final Boolean css;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 1)
    public final Boolean enable;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 4)
    public final Boolean html;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 2)
    public final Boolean js;

    public CacheConf(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4) {
        this(bool, bool2, bool3, bool4, ByteString.EMPTY);
    }

    public CacheConf(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, ByteString byteString) {
        super(ADAPTER, byteString);
        this.enable = bool;
        this.js = bool2;
        this.css = bool3;
        this.html = bool4;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.enable = this.enable;
        builder.js = this.js;
        builder.css = this.css;
        builder.html = this.html;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CacheConf)) {
            return false;
        }
        CacheConf cacheConf = (CacheConf) obj;
        if (!Internal.equals(unknownFields(), cacheConf.unknownFields()) || !Internal.equals(this.enable, cacheConf.enable) || !Internal.equals(this.js, cacheConf.js) || !Internal.equals(this.css, cacheConf.css) || !Internal.equals(this.html, cacheConf.html)) {
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
        int hashCode = ((((((unknownFields().hashCode() * 37) + (this.enable != null ? this.enable.hashCode() : 0)) * 37) + (this.js != null ? this.js.hashCode() : 0)) * 37) + (this.css != null ? this.css.hashCode() : 0)) * 37;
        if (this.html != null) {
            i2 = this.html.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.enable != null) {
            sb.append(", enable=");
            sb.append(this.enable);
        }
        if (this.js != null) {
            sb.append(", js=");
            sb.append(this.js);
        }
        if (this.css != null) {
            sb.append(", css=");
            sb.append(this.css);
        }
        if (this.html != null) {
            sb.append(", html=");
            sb.append(this.html);
        }
        StringBuilder replace = sb.replace(0, 2, "CacheConf{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<CacheConf, Builder> {
        public Boolean css;
        public Boolean enable;
        public Boolean html;
        public Boolean js;

        public Builder enable(Boolean bool) {
            this.enable = bool;
            return this;
        }

        public Builder js(Boolean bool) {
            this.js = bool;
            return this;
        }

        public Builder css(Boolean bool) {
            this.css = bool;
            return this;
        }

        public Builder html(Boolean bool) {
            this.html = bool;
            return this;
        }

        public CacheConf build() {
            return new CacheConf(this.enable, this.js, this.css, this.html, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_CacheConf extends ProtoAdapter<CacheConf> {
        ProtoAdapter_CacheConf() {
            super(FieldEncoding.LENGTH_DELIMITED, CacheConf.class);
        }

        public int encodedSize(CacheConf cacheConf) {
            int i = 0;
            int encodedSizeWithTag = (cacheConf.enable != null ? ProtoAdapter.BOOL.encodedSizeWithTag(1, cacheConf.enable) : 0) + (cacheConf.js != null ? ProtoAdapter.BOOL.encodedSizeWithTag(2, cacheConf.js) : 0) + (cacheConf.css != null ? ProtoAdapter.BOOL.encodedSizeWithTag(3, cacheConf.css) : 0);
            if (cacheConf.html != null) {
                i = ProtoAdapter.BOOL.encodedSizeWithTag(4, cacheConf.html);
            }
            return encodedSizeWithTag + i + cacheConf.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, CacheConf cacheConf) throws IOException {
            if (cacheConf.enable != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 1, cacheConf.enable);
            }
            if (cacheConf.js != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 2, cacheConf.js);
            }
            if (cacheConf.css != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 3, cacheConf.css);
            }
            if (cacheConf.html != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 4, cacheConf.html);
            }
            protoWriter.writeBytes(cacheConf.unknownFields());
        }

        public CacheConf decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.enable(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 2:
                            builder.js(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 3:
                            builder.css(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 4:
                            builder.html(ProtoAdapter.BOOL.decode(protoReader));
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

        public CacheConf redact(CacheConf cacheConf) {
            Builder newBuilder = cacheConf.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
