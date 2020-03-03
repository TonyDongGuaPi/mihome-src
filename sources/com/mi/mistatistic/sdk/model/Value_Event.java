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
import java.util.List;
import okio.ByteString;

public final class Value_Event extends Message<Value_Event, Builder> {
    public static final ProtoAdapter<Value_Event> ADAPTER = new ProtoAdapter_Value_Event();
    public static final String DEFAULT_EVENT_ID = "";
    public static final String DEFAULT_LABEL = "";
    public static final String DEFAULT_PAGE_ID = "";
    public static final String DEFAULT_SESSION_ID = "";
    public static final Long DEFAULT_TIMESTAMP = 0L;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "appstat.EventExtraData#ADAPTER", label = WireField.Label.REPEATED, tag = 6)
    public final List<EventExtraData> data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String event_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String label;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String page_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String session_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 3)
    public final Long timestamp;

    public Value_Event(String str, Long l, String str2, String str3, List<EventExtraData> list, String str4) {
        this(str, l, str2, str3, list, str4, ByteString.EMPTY);
    }

    public Value_Event(String str, Long l, String str2, String str3, List<EventExtraData> list, String str4, ByteString byteString) {
        super(ADAPTER, byteString);
        this.session_id = str;
        this.timestamp = l;
        this.event_id = str2;
        this.label = str3;
        this.data = Internal.immutableCopyOf("data", list);
        this.page_id = str4;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.f7363a = this.session_id;
        builder.b = this.timestamp;
        builder.c = this.event_id;
        builder.d = this.label;
        builder.e = Internal.copyOf("data", this.data);
        builder.f = this.page_id;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value_Event)) {
            return false;
        }
        Value_Event value_Event = (Value_Event) obj;
        if (!Internal.equals(unknownFields(), value_Event.unknownFields()) || !Internal.equals(this.session_id, value_Event.session_id) || !Internal.equals(this.timestamp, value_Event.timestamp) || !Internal.equals(this.event_id, value_Event.event_id) || !Internal.equals(this.label, value_Event.label) || !Internal.equals(this.data, value_Event.data) || !Internal.equals(this.page_id, value_Event.page_id)) {
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
        int hashCode = ((((((((((unknownFields().hashCode() * 37) + (this.session_id != null ? this.session_id.hashCode() : 0)) * 37) + (this.timestamp != null ? this.timestamp.hashCode() : 0)) * 37) + (this.event_id != null ? this.event_id.hashCode() : 0)) * 37) + (this.label != null ? this.label.hashCode() : 0)) * 37) + (this.data != null ? this.data.hashCode() : 1)) * 37;
        if (this.page_id != null) {
            i2 = this.page_id.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.session_id != null) {
            sb.append(", session_id=");
            sb.append(this.session_id);
        }
        if (this.timestamp != null) {
            sb.append(", timestamp=");
            sb.append(this.timestamp);
        }
        if (this.event_id != null) {
            sb.append(", event_id=");
            sb.append(this.event_id);
        }
        if (this.label != null) {
            sb.append(", label=");
            sb.append(this.label);
        }
        if (this.data != null) {
            sb.append(", data=");
            sb.append(this.data);
        }
        if (this.page_id != null) {
            sb.append(", page_id=");
            sb.append(this.page_id);
        }
        StringBuilder replace = sb.replace(0, 2, "Value_Event{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Value_Event, Builder> {

        /* renamed from: a  reason: collision with root package name */
        public String f7363a;
        public Long b;
        public String c;
        public String d;
        public List<EventExtraData> e = Internal.newMutableList();
        public String f;

        public Builder a(String str) {
            this.f7363a = str;
            return this;
        }

        public Builder a(Long l) {
            this.b = l;
            return this;
        }

        public Builder b(String str) {
            this.c = str;
            return this;
        }

        public Builder c(String str) {
            this.d = str;
            return this;
        }

        public Builder a(List<EventExtraData> list) {
            Internal.checkElementsNotNull(list);
            this.e = list;
            return this;
        }

        public Builder d(String str) {
            this.f = str;
            return this;
        }

        /* renamed from: a */
        public Value_Event build() {
            return new Value_Event(this.f7363a, this.b, this.c, this.d, this.e, this.f, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Value_Event extends ProtoAdapter<Value_Event> {
        ProtoAdapter_Value_Event() {
            super(FieldEncoding.LENGTH_DELIMITED, Value_Event.class);
        }

        /* renamed from: a */
        public int encodedSize(Value_Event value_Event) {
            int i = 0;
            int encodedSizeWithTag = (value_Event.session_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, value_Event.session_id) : 0) + (value_Event.timestamp != null ? ProtoAdapter.INT64.encodedSizeWithTag(3, value_Event.timestamp) : 0) + (value_Event.event_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, value_Event.event_id) : 0) + (value_Event.label != null ? ProtoAdapter.STRING.encodedSizeWithTag(5, value_Event.label) : 0) + EventExtraData.ADAPTER.asRepeated().encodedSizeWithTag(6, value_Event.data);
            if (value_Event.page_id != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(7, value_Event.page_id);
            }
            return encodedSizeWithTag + i + value_Event.unknownFields().size();
        }

        /* renamed from: a */
        public void encode(ProtoWriter protoWriter, Value_Event value_Event) throws IOException {
            if (value_Event.session_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, value_Event.session_id);
            }
            if (value_Event.timestamp != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 3, value_Event.timestamp);
            }
            if (value_Event.event_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, value_Event.event_id);
            }
            if (value_Event.label != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, value_Event.label);
            }
            if (value_Event.data != null) {
                EventExtraData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 6, value_Event.data);
            }
            if (value_Event.page_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, value_Event.page_id);
            }
            protoWriter.writeBytes(value_Event.unknownFields());
        }

        /* renamed from: a */
        public Value_Event decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag == -1) {
                    protoReader.endMessage(beginMessage);
                    return builder.build();
                } else if (nextTag != 1) {
                    switch (nextTag) {
                        case 3:
                            builder.a(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 4:
                            builder.b(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 5:
                            builder.c(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 6:
                            builder.e.add(EventExtraData.ADAPTER.decode(protoReader));
                            break;
                        case 7:
                            builder.d(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        default:
                            FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                            builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                            break;
                    }
                } else {
                    builder.a(ProtoAdapter.STRING.decode(protoReader));
                }
            }
        }

        /* renamed from: b */
        public Value_Event redact(Value_Event value_Event) {
            Builder newBuilder = value_Event.newBuilder();
            Internal.redactElements(newBuilder.e, EventExtraData.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
