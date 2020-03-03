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

public final class Value_Startup extends Message<Value_Startup, Builder> {
    public static final ProtoAdapter<Value_Startup> ADAPTER = new ProtoAdapter_Value_Startup();
    public static final String DEFAULT_NETWORK = "";
    public static final String DEFAULT_RESOLUTION = "";
    public static final String DEFAULT_SESSION_ID = "";
    public static final Long DEFAULT_START_TIME = 0L;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String network;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String resolution;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String session_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 7)
    public final Long start_time;

    public Value_Startup(String str, String str2, String str3, Long l) {
        this(str, str2, str3, l, ByteString.EMPTY);
    }

    public Value_Startup(String str, String str2, String str3, Long l, ByteString byteString) {
        super(ADAPTER, byteString);
        this.session_id = str;
        this.network = str2;
        this.resolution = str3;
        this.start_time = l;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.f7369a = this.session_id;
        builder.b = this.network;
        builder.c = this.resolution;
        builder.d = this.start_time;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value_Startup)) {
            return false;
        }
        Value_Startup value_Startup = (Value_Startup) obj;
        if (!Internal.equals(unknownFields(), value_Startup.unknownFields()) || !Internal.equals(this.session_id, value_Startup.session_id) || !Internal.equals(this.network, value_Startup.network) || !Internal.equals(this.resolution, value_Startup.resolution) || !Internal.equals(this.start_time, value_Startup.start_time)) {
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
        int hashCode = ((((((unknownFields().hashCode() * 37) + (this.session_id != null ? this.session_id.hashCode() : 0)) * 37) + (this.network != null ? this.network.hashCode() : 0)) * 37) + (this.resolution != null ? this.resolution.hashCode() : 0)) * 37;
        if (this.start_time != null) {
            i2 = this.start_time.hashCode();
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
        if (this.network != null) {
            sb.append(", network=");
            sb.append(this.network);
        }
        if (this.resolution != null) {
            sb.append(", resolution=");
            sb.append(this.resolution);
        }
        if (this.start_time != null) {
            sb.append(", start_time=");
            sb.append(this.start_time);
        }
        StringBuilder replace = sb.replace(0, 2, "Value_Startup{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Value_Startup, Builder> {

        /* renamed from: a  reason: collision with root package name */
        public String f7369a;
        public String b;
        public String c;
        public Long d;

        public Builder a(String str) {
            this.f7369a = str;
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

        public Builder a(Long l) {
            this.d = l;
            return this;
        }

        /* renamed from: a */
        public Value_Startup build() {
            return new Value_Startup(this.f7369a, this.b, this.c, this.d, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Value_Startup extends ProtoAdapter<Value_Startup> {
        ProtoAdapter_Value_Startup() {
            super(FieldEncoding.LENGTH_DELIMITED, Value_Startup.class);
        }

        /* renamed from: a */
        public int encodedSize(Value_Startup value_Startup) {
            int i = 0;
            int encodedSizeWithTag = (value_Startup.session_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, value_Startup.session_id) : 0) + (value_Startup.network != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, value_Startup.network) : 0) + (value_Startup.resolution != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, value_Startup.resolution) : 0);
            if (value_Startup.start_time != null) {
                i = ProtoAdapter.INT64.encodedSizeWithTag(7, value_Startup.start_time);
            }
            return encodedSizeWithTag + i + value_Startup.unknownFields().size();
        }

        /* renamed from: a */
        public void encode(ProtoWriter protoWriter, Value_Startup value_Startup) throws IOException {
            if (value_Startup.session_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, value_Startup.session_id);
            }
            if (value_Startup.network != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, value_Startup.network);
            }
            if (value_Startup.resolution != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, value_Startup.resolution);
            }
            if (value_Startup.start_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 7, value_Startup.start_time);
            }
            protoWriter.writeBytes(value_Startup.unknownFields());
        }

        /* renamed from: a */
        public Value_Startup decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag == -1) {
                    protoReader.endMessage(beginMessage);
                    return builder.build();
                } else if (nextTag != 7) {
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
                    builder.a(ProtoAdapter.INT64.decode(protoReader));
                }
            }
        }

        /* renamed from: b */
        public Value_Startup redact(Value_Startup value_Startup) {
            Builder newBuilder = value_Startup.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
