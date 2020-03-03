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

public final class NotifyMessage extends Message<NotifyMessage, Builder> {
    public static final ProtoAdapter<NotifyMessage> ADAPTER = new ProtoAdapter_NotifyMessage();
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.user.MessageContent#ADAPTER", tag = 5)
    public final MessageContent data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer errno;

    public NotifyMessage(Integer num, String str, MessageContent messageContent) {
        this(num, str, messageContent, ByteString.EMPTY);
    }

    public NotifyMessage(Integer num, String str, MessageContent messageContent, ByteString byteString) {
        super(ADAPTER, byteString);
        this.errno = num;
        this.errmsg = str;
        this.data = messageContent;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.errno = this.errno;
        builder.errmsg = this.errmsg;
        builder.data = this.data;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NotifyMessage)) {
            return false;
        }
        NotifyMessage notifyMessage = (NotifyMessage) obj;
        if (!Internal.equals(unknownFields(), notifyMessage.unknownFields()) || !Internal.equals(this.errno, notifyMessage.errno) || !Internal.equals(this.errmsg, notifyMessage.errmsg) || !Internal.equals(this.data, notifyMessage.data)) {
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
        int hashCode = ((((unknownFields().hashCode() * 37) + (this.errno != null ? this.errno.hashCode() : 0)) * 37) + (this.errmsg != null ? this.errmsg.hashCode() : 0)) * 37;
        if (this.data != null) {
            i2 = this.data.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.errno != null) {
            sb.append(", errno=");
            sb.append(this.errno);
        }
        if (this.errmsg != null) {
            sb.append(", errmsg=");
            sb.append(this.errmsg);
        }
        if (this.data != null) {
            sb.append(", data=");
            sb.append(this.data);
        }
        StringBuilder replace = sb.replace(0, 2, "NotifyMessage{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<NotifyMessage, Builder> {
        public MessageContent data;
        public String errmsg;
        public Integer errno;

        public Builder errno(Integer num) {
            this.errno = num;
            return this;
        }

        public Builder errmsg(String str) {
            this.errmsg = str;
            return this;
        }

        public Builder data(MessageContent messageContent) {
            this.data = messageContent;
            return this;
        }

        public NotifyMessage build() {
            return new NotifyMessage(this.errno, this.errmsg, this.data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_NotifyMessage extends ProtoAdapter<NotifyMessage> {
        ProtoAdapter_NotifyMessage() {
            super(FieldEncoding.LENGTH_DELIMITED, NotifyMessage.class);
        }

        public int encodedSize(NotifyMessage notifyMessage) {
            int i = 0;
            int encodedSizeWithTag = (notifyMessage.errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, notifyMessage.errno) : 0) + (notifyMessage.errmsg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, notifyMessage.errmsg) : 0);
            if (notifyMessage.data != null) {
                i = MessageContent.ADAPTER.encodedSizeWithTag(5, notifyMessage.data);
            }
            return encodedSizeWithTag + i + notifyMessage.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, NotifyMessage notifyMessage) throws IOException {
            if (notifyMessage.errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, notifyMessage.errno);
            }
            if (notifyMessage.errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, notifyMessage.errmsg);
            }
            if (notifyMessage.data != null) {
                MessageContent.ADAPTER.encodeWithTag(protoWriter, 5, notifyMessage.data);
            }
            protoWriter.writeBytes(notifyMessage.unknownFields());
        }

        public NotifyMessage decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag == -1) {
                    protoReader.endMessage(beginMessage);
                    return builder.build();
                } else if (nextTag != 5) {
                    switch (nextTag) {
                        case 1:
                            builder.errno(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 2:
                            builder.errmsg(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        default:
                            FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                            builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                            break;
                    }
                } else {
                    builder.data(MessageContent.ADAPTER.decode(protoReader));
                }
            }
        }

        public NotifyMessage redact(NotifyMessage notifyMessage) {
            Builder newBuilder = notifyMessage.newBuilder();
            if (newBuilder.data != null) {
                newBuilder.data = MessageContent.ADAPTER.redact(newBuilder.data);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
