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

public final class MessageContent extends Message<MessageContent, Builder> {
    public static final ProtoAdapter<MessageContent> ADAPTER = new ProtoAdapter_MessageContent();
    public static final String DEFAULT_CONTENT = "";
    public static final String DEFAULT_TYPE = "";
    public static final String DEFAULT_URL = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String content;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String type;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String url;

    public MessageContent(String str, String str2, String str3) {
        this(str, str2, str3, ByteString.EMPTY);
    }

    public MessageContent(String str, String str2, String str3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.type = str;
        this.content = str2;
        this.url = str3;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.type = this.type;
        builder.content = this.content;
        builder.url = this.url;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MessageContent)) {
            return false;
        }
        MessageContent messageContent = (MessageContent) obj;
        if (!Internal.equals(unknownFields(), messageContent.unknownFields()) || !Internal.equals(this.type, messageContent.type) || !Internal.equals(this.content, messageContent.content) || !Internal.equals(this.url, messageContent.url)) {
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
        int hashCode = ((((unknownFields().hashCode() * 37) + (this.type != null ? this.type.hashCode() : 0)) * 37) + (this.content != null ? this.content.hashCode() : 0)) * 37;
        if (this.url != null) {
            i2 = this.url.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.type != null) {
            sb.append(", type=");
            sb.append(this.type);
        }
        if (this.content != null) {
            sb.append(", content=");
            sb.append(this.content);
        }
        if (this.url != null) {
            sb.append(", url=");
            sb.append(this.url);
        }
        StringBuilder replace = sb.replace(0, 2, "MessageContent{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<MessageContent, Builder> {
        public String content;
        public String type;
        public String url;

        public Builder type(String str) {
            this.type = str;
            return this;
        }

        public Builder content(String str) {
            this.content = str;
            return this;
        }

        public Builder url(String str) {
            this.url = str;
            return this;
        }

        public MessageContent build() {
            return new MessageContent(this.type, this.content, this.url, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_MessageContent extends ProtoAdapter<MessageContent> {
        ProtoAdapter_MessageContent() {
            super(FieldEncoding.LENGTH_DELIMITED, MessageContent.class);
        }

        public int encodedSize(MessageContent messageContent) {
            int i = 0;
            int encodedSizeWithTag = (messageContent.type != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, messageContent.type) : 0) + (messageContent.content != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, messageContent.content) : 0);
            if (messageContent.url != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(3, messageContent.url);
            }
            return encodedSizeWithTag + i + messageContent.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, MessageContent messageContent) throws IOException {
            if (messageContent.type != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, messageContent.type);
            }
            if (messageContent.content != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, messageContent.content);
            }
            if (messageContent.url != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, messageContent.url);
            }
            protoWriter.writeBytes(messageContent.unknownFields());
        }

        public MessageContent decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.type(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.content(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
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

        public MessageContent redact(MessageContent messageContent) {
            Builder newBuilder = messageContent.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
