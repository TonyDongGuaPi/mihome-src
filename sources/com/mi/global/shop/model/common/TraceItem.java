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

public final class TraceItem extends Message<TraceItem, Builder> {
    public static final ProtoAdapter<TraceItem> ADAPTER = new ProtoAdapter_TraceItem();
    public static final String DEFAULT_TEXT = "";
    public static final String DEFAULT_TIME = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String text;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String time;

    public TraceItem(String str, String str2) {
        this(str, str2, ByteString.EMPTY);
    }

    public TraceItem(String str, String str2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.text = str;
        this.time = str2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.text = this.text;
        builder.time = this.time;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TraceItem)) {
            return false;
        }
        TraceItem traceItem = (TraceItem) obj;
        if (!Internal.equals(unknownFields(), traceItem.unknownFields()) || !Internal.equals(this.text, traceItem.text) || !Internal.equals(this.time, traceItem.time)) {
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
        int hashCode = ((unknownFields().hashCode() * 37) + (this.text != null ? this.text.hashCode() : 0)) * 37;
        if (this.time != null) {
            i2 = this.time.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.text != null) {
            sb.append(", text=");
            sb.append(this.text);
        }
        if (this.time != null) {
            sb.append(", time=");
            sb.append(this.time);
        }
        StringBuilder replace = sb.replace(0, 2, "TraceItem{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<TraceItem, Builder> {
        public String text;
        public String time;

        public Builder text(String str) {
            this.text = str;
            return this;
        }

        public Builder time(String str) {
            this.time = str;
            return this;
        }

        public TraceItem build() {
            return new TraceItem(this.text, this.time, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_TraceItem extends ProtoAdapter<TraceItem> {
        ProtoAdapter_TraceItem() {
            super(FieldEncoding.LENGTH_DELIMITED, TraceItem.class);
        }

        public int encodedSize(TraceItem traceItem) {
            int i = 0;
            int encodedSizeWithTag = traceItem.text != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, traceItem.text) : 0;
            if (traceItem.time != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(2, traceItem.time);
            }
            return encodedSizeWithTag + i + traceItem.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, TraceItem traceItem) throws IOException {
            if (traceItem.text != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, traceItem.text);
            }
            if (traceItem.time != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, traceItem.time);
            }
            protoWriter.writeBytes(traceItem.unknownFields());
        }

        public TraceItem decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.text(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.time(ProtoAdapter.STRING.decode(protoReader));
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

        public TraceItem redact(TraceItem traceItem) {
            Builder newBuilder = traceItem.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
