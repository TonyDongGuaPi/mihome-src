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

public final class BombBox extends Message<BombBox, Builder> {
    public static final ProtoAdapter<BombBox> ADAPTER = new ProtoAdapter_BombBox();
    public static final String DEFAULT_URL = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String url;

    public BombBox(String str) {
        this(str, ByteString.EMPTY);
    }

    public BombBox(String str, ByteString byteString) {
        super(ADAPTER, byteString);
        this.url = str;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.url = this.url;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BombBox)) {
            return false;
        }
        BombBox bombBox = (BombBox) obj;
        if (!Internal.equals(unknownFields(), bombBox.unknownFields()) || !Internal.equals(this.url, bombBox.url)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = (unknownFields().hashCode() * 37) + (this.url != null ? this.url.hashCode() : 0);
        this.hashCode = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.url != null) {
            sb.append(", url=");
            sb.append(this.url);
        }
        StringBuilder replace = sb.replace(0, 2, "BombBox{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<BombBox, Builder> {
        public String url;

        public Builder url(String str) {
            this.url = str;
            return this;
        }

        public BombBox build() {
            return new BombBox(this.url, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_BombBox extends ProtoAdapter<BombBox> {
        ProtoAdapter_BombBox() {
            super(FieldEncoding.LENGTH_DELIMITED, BombBox.class);
        }

        public int encodedSize(BombBox bombBox) {
            return (bombBox.url != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, bombBox.url) : 0) + bombBox.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, BombBox bombBox) throws IOException {
            if (bombBox.url != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, bombBox.url);
            }
            protoWriter.writeBytes(bombBox.unknownFields());
        }

        public BombBox decode(ProtoReader protoReader) throws IOException {
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
                    builder.url(ProtoAdapter.STRING.decode(protoReader));
                }
            }
        }

        public BombBox redact(BombBox bombBox) {
            Builder newBuilder = bombBox.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
