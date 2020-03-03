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

public final class GetIndexData extends Message<GetIndexData, Builder> {
    public static final ProtoAdapter<GetIndexData> ADAPTER = new ProtoAdapter_GetIndexData();
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.user.MessageContent#ADAPTER", tag = 1)
    public final MessageContent message;

    public GetIndexData(MessageContent messageContent) {
        this(messageContent, ByteString.EMPTY);
    }

    public GetIndexData(MessageContent messageContent, ByteString byteString) {
        super(ADAPTER, byteString);
        this.message = messageContent;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.message = this.message;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetIndexData)) {
            return false;
        }
        GetIndexData getIndexData = (GetIndexData) obj;
        if (!Internal.equals(unknownFields(), getIndexData.unknownFields()) || !Internal.equals(this.message, getIndexData.message)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = (unknownFields().hashCode() * 37) + (this.message != null ? this.message.hashCode() : 0);
        this.hashCode = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.message != null) {
            sb.append(", message=");
            sb.append(this.message);
        }
        StringBuilder replace = sb.replace(0, 2, "GetIndexData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<GetIndexData, Builder> {
        public MessageContent message;

        public Builder message(MessageContent messageContent) {
            this.message = messageContent;
            return this;
        }

        public GetIndexData build() {
            return new GetIndexData(this.message, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_GetIndexData extends ProtoAdapter<GetIndexData> {
        ProtoAdapter_GetIndexData() {
            super(FieldEncoding.LENGTH_DELIMITED, GetIndexData.class);
        }

        public int encodedSize(GetIndexData getIndexData) {
            return (getIndexData.message != null ? MessageContent.ADAPTER.encodedSizeWithTag(1, getIndexData.message) : 0) + getIndexData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, GetIndexData getIndexData) throws IOException {
            if (getIndexData.message != null) {
                MessageContent.ADAPTER.encodeWithTag(protoWriter, 1, getIndexData.message);
            }
            protoWriter.writeBytes(getIndexData.unknownFields());
        }

        public GetIndexData decode(ProtoReader protoReader) throws IOException {
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
                    builder.message(MessageContent.ADAPTER.decode(protoReader));
                }
            }
        }

        public GetIndexData redact(GetIndexData getIndexData) {
            Builder newBuilder = getIndexData.newBuilder();
            if (newBuilder.message != null) {
                newBuilder.message = MessageContent.ADAPTER.redact(newBuilder.message);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
