package com.mi.mistatistic.sdk.model;

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

public final class EventExtraData extends Message<EventExtraData, Builder> {
    public static final ProtoAdapter<EventExtraData> ADAPTER = new ProtoAdapter_EventExtraData();
    public static final String DEFAULT_KEY = "";
    public static final String DEFAULT_TYPE = "";
    public static final String DEFAULT_VALUE = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String key;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String type;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String value;

    public EventExtraData(String str, String str2, String str3) {
        this(str, str2, str3, ByteString.EMPTY);
    }

    public EventExtraData(String str, String str2, String str3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.key = str;
        this.value = str2;
        this.type = str3;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.f7359a = this.key;
        builder.b = this.value;
        builder.c = this.type;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventExtraData)) {
            return false;
        }
        EventExtraData eventExtraData = (EventExtraData) obj;
        if (!Internal.equals(unknownFields(), eventExtraData.unknownFields()) || !Internal.equals(this.key, eventExtraData.key) || !Internal.equals(this.value, eventExtraData.value) || !Internal.equals(this.type, eventExtraData.type)) {
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
        int hashCode = ((((unknownFields().hashCode() * 37) + (this.key != null ? this.key.hashCode() : 0)) * 37) + (this.value != null ? this.value.hashCode() : 0)) * 37;
        if (this.type != null) {
            i2 = this.type.hashCode();
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
        if (this.type != null) {
            sb.append(", type=");
            sb.append(this.type);
        }
        StringBuilder replace = sb.replace(0, 2, "EventExtraData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<EventExtraData, Builder> {

        /* renamed from: a  reason: collision with root package name */
        public String f7359a;
        public String b;
        public String c;

        public Builder a(String str) {
            this.f7359a = str;
            return this;
        }

        public Builder b(String str) {
            this.b = str;
            return this;
        }

        public Builder c(String str) {
            this.c = str;
            return this;
        }

        /* renamed from: a */
        public EventExtraData build() {
            return new EventExtraData(this.f7359a, this.b, this.c, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_EventExtraData extends ProtoAdapter<EventExtraData> {
        ProtoAdapter_EventExtraData() {
            super(FieldEncoding.LENGTH_DELIMITED, EventExtraData.class);
        }

        /* renamed from: a */
        public int encodedSize(EventExtraData eventExtraData) {
            int i = 0;
            int encodedSizeWithTag = (eventExtraData.key != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, eventExtraData.key) : 0) + (eventExtraData.value != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, eventExtraData.value) : 0);
            if (eventExtraData.type != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(3, eventExtraData.type);
            }
            return encodedSizeWithTag + i + eventExtraData.unknownFields().size();
        }

        /* renamed from: a */
        public void encode(ProtoWriter protoWriter, EventExtraData eventExtraData) throws IOException {
            if (eventExtraData.key != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, eventExtraData.key);
            }
            if (eventExtraData.value != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, eventExtraData.value);
            }
            if (eventExtraData.type != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, eventExtraData.type);
            }
            protoWriter.writeBytes(eventExtraData.unknownFields());
        }

        /* renamed from: a */
        public EventExtraData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.a(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.b(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.c(ProtoAdapter.STRING.decode(protoReader));
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

        /* renamed from: b */
        public EventExtraData redact(EventExtraData eventExtraData) {
            Builder newBuilder = eventExtraData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
