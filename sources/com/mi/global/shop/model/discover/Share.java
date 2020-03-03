package com.mi.global.shop.model.discover;

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

public final class Share extends Message<Share, Builder> {
    public static final ProtoAdapter<Share> ADAPTER = new ProtoAdapter_Share();
    public static final String DEFAULT_TEXT = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String text;

    public Share(String str) {
        this(str, ByteString.EMPTY);
    }

    public Share(String str, ByteString byteString) {
        super(ADAPTER, byteString);
        this.text = str;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.text = this.text;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Share)) {
            return false;
        }
        Share share = (Share) obj;
        if (!Internal.equals(unknownFields(), share.unknownFields()) || !Internal.equals(this.text, share.text)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = (unknownFields().hashCode() * 37) + (this.text != null ? this.text.hashCode() : 0);
        this.hashCode = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.text != null) {
            sb.append(", text=");
            sb.append(this.text);
        }
        StringBuilder replace = sb.replace(0, 2, "Share{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Share, Builder> {
        public String text;

        public Builder text(String str) {
            this.text = str;
            return this;
        }

        public Share build() {
            return new Share(this.text, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Share extends ProtoAdapter<Share> {
        ProtoAdapter_Share() {
            super(FieldEncoding.LENGTH_DELIMITED, Share.class);
        }

        public int encodedSize(Share share) {
            return (share.text != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, share.text) : 0) + share.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, Share share) throws IOException {
            if (share.text != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, share.text);
            }
            protoWriter.writeBytes(share.unknownFields());
        }

        public Share decode(ProtoReader protoReader) throws IOException {
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
                    builder.text(ProtoAdapter.STRING.decode(protoReader));
                }
            }
        }

        public Share redact(Share share) {
            Builder newBuilder = share.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
