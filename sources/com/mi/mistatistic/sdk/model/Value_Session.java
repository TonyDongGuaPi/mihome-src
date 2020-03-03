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

public final class Value_Session extends Message<Value_Session, Builder> {
    public static final ProtoAdapter<Value_Session> ADAPTER = new ProtoAdapter_Value_Session();
    public static final Long DEFAULT_END_TIME = 0L;
    public static final String DEFAULT_NETWORK = "";
    public static final String DEFAULT_SESSION_ID = "";
    public static final Long DEFAULT_START_TIME = 0L;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 4)
    public final Long end_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String network;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String session_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 3)
    public final Long start_time;

    public Value_Session(String str, Long l, Long l2, String str2) {
        this(str, l, l2, str2, ByteString.EMPTY);
    }

    public Value_Session(String str, Long l, Long l2, String str2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.session_id = str;
        this.start_time = l;
        this.end_time = l2;
        this.network = str2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.f7368a = this.session_id;
        builder.b = this.start_time;
        builder.c = this.end_time;
        builder.d = this.network;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value_Session)) {
            return false;
        }
        Value_Session value_Session = (Value_Session) obj;
        if (!Internal.equals(unknownFields(), value_Session.unknownFields()) || !Internal.equals(this.session_id, value_Session.session_id) || !Internal.equals(this.start_time, value_Session.start_time) || !Internal.equals(this.end_time, value_Session.end_time) || !Internal.equals(this.network, value_Session.network)) {
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
        int hashCode = ((((((unknownFields().hashCode() * 37) + (this.session_id != null ? this.session_id.hashCode() : 0)) * 37) + (this.start_time != null ? this.start_time.hashCode() : 0)) * 37) + (this.end_time != null ? this.end_time.hashCode() : 0)) * 37;
        if (this.network != null) {
            i2 = this.network.hashCode();
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
        if (this.start_time != null) {
            sb.append(", start_time=");
            sb.append(this.start_time);
        }
        if (this.end_time != null) {
            sb.append(", end_time=");
            sb.append(this.end_time);
        }
        if (this.network != null) {
            sb.append(", network=");
            sb.append(this.network);
        }
        StringBuilder replace = sb.replace(0, 2, "Value_Session{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Value_Session, Builder> {

        /* renamed from: a  reason: collision with root package name */
        public String f7368a;
        public Long b;
        public Long c;
        public String d;

        public Builder a(String str) {
            this.f7368a = str;
            return this;
        }

        public Builder a(Long l) {
            this.b = l;
            return this;
        }

        public Builder b(Long l) {
            this.c = l;
            return this;
        }

        public Builder b(String str) {
            this.d = str;
            return this;
        }

        /* renamed from: a */
        public Value_Session build() {
            return new Value_Session(this.f7368a, this.b, this.c, this.d, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Value_Session extends ProtoAdapter<Value_Session> {
        ProtoAdapter_Value_Session() {
            super(FieldEncoding.LENGTH_DELIMITED, Value_Session.class);
        }

        /* renamed from: a */
        public int encodedSize(Value_Session value_Session) {
            int i = 0;
            int encodedSizeWithTag = (value_Session.session_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, value_Session.session_id) : 0) + (value_Session.start_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(3, value_Session.start_time) : 0) + (value_Session.end_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(4, value_Session.end_time) : 0);
            if (value_Session.network != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(5, value_Session.network);
            }
            return encodedSizeWithTag + i + value_Session.unknownFields().size();
        }

        /* renamed from: a */
        public void encode(ProtoWriter protoWriter, Value_Session value_Session) throws IOException {
            if (value_Session.session_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, value_Session.session_id);
            }
            if (value_Session.start_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 3, value_Session.start_time);
            }
            if (value_Session.end_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 4, value_Session.end_time);
            }
            if (value_Session.network != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, value_Session.network);
            }
            protoWriter.writeBytes(value_Session.unknownFields());
        }

        /* renamed from: a */
        public Value_Session decode(ProtoReader protoReader) throws IOException {
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
                            builder.b(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 5:
                            builder.b(ProtoAdapter.STRING.decode(protoReader));
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
        public Value_Session redact(Value_Session value_Session) {
            Builder newBuilder = value_Session.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}