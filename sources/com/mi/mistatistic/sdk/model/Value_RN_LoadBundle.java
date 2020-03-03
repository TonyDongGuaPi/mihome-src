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

public final class Value_RN_LoadBundle extends Message<Value_RN_LoadBundle, Builder> {
    public static final ProtoAdapter<Value_RN_LoadBundle> ADAPTER = new ProtoAdapter_Value_RN_LoadBundle();
    public static final String DEFAULT_BUNDLE_NAME = "";
    public static final String DEFAULT_BUNDLE_USING_VERSION = "";
    public static final Long DEFAULT_INSTANCE_TIME = 0L;
    public static final String DEFAULT_RN_VERSION = "";
    public static final String DEFAULT_SESSION_ID = "";
    public static final Long DEFAULT_TIMESTAMP = 0L;
    public static final Long DEFAULT_VIEW_TIME = 0L;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String bundle_name;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String bundle_using_version;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 4)
    public final Long instance_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String rn_version;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String session_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 7)
    public final Long timestamp;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 5)
    public final Long view_time;

    public Value_RN_LoadBundle(String str, String str2, String str3, Long l, Long l2, String str4, Long l3) {
        this(str, str2, str3, l, l2, str4, l3, ByteString.EMPTY);
    }

    public Value_RN_LoadBundle(String str, String str2, String str3, Long l, Long l2, String str4, Long l3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.bundle_name = str;
        this.bundle_using_version = str2;
        this.rn_version = str3;
        this.instance_time = l;
        this.view_time = l2;
        this.session_id = str4;
        this.timestamp = l3;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.f7367a = this.bundle_name;
        builder.b = this.bundle_using_version;
        builder.c = this.rn_version;
        builder.d = this.instance_time;
        builder.e = this.view_time;
        builder.f = this.session_id;
        builder.g = this.timestamp;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value_RN_LoadBundle)) {
            return false;
        }
        Value_RN_LoadBundle value_RN_LoadBundle = (Value_RN_LoadBundle) obj;
        if (!Internal.equals(unknownFields(), value_RN_LoadBundle.unknownFields()) || !Internal.equals(this.bundle_name, value_RN_LoadBundle.bundle_name) || !Internal.equals(this.bundle_using_version, value_RN_LoadBundle.bundle_using_version) || !Internal.equals(this.rn_version, value_RN_LoadBundle.rn_version) || !Internal.equals(this.instance_time, value_RN_LoadBundle.instance_time) || !Internal.equals(this.view_time, value_RN_LoadBundle.view_time) || !Internal.equals(this.session_id, value_RN_LoadBundle.session_id) || !Internal.equals(this.timestamp, value_RN_LoadBundle.timestamp)) {
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
        int hashCode = ((((((((((((unknownFields().hashCode() * 37) + (this.bundle_name != null ? this.bundle_name.hashCode() : 0)) * 37) + (this.bundle_using_version != null ? this.bundle_using_version.hashCode() : 0)) * 37) + (this.rn_version != null ? this.rn_version.hashCode() : 0)) * 37) + (this.instance_time != null ? this.instance_time.hashCode() : 0)) * 37) + (this.view_time != null ? this.view_time.hashCode() : 0)) * 37) + (this.session_id != null ? this.session_id.hashCode() : 0)) * 37;
        if (this.timestamp != null) {
            i2 = this.timestamp.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.bundle_name != null) {
            sb.append(", bundle_name=");
            sb.append(this.bundle_name);
        }
        if (this.bundle_using_version != null) {
            sb.append(", bundle_using_version=");
            sb.append(this.bundle_using_version);
        }
        if (this.rn_version != null) {
            sb.append(", rn_version=");
            sb.append(this.rn_version);
        }
        if (this.instance_time != null) {
            sb.append(", instance_time=");
            sb.append(this.instance_time);
        }
        if (this.view_time != null) {
            sb.append(", view_time=");
            sb.append(this.view_time);
        }
        if (this.session_id != null) {
            sb.append(", session_id=");
            sb.append(this.session_id);
        }
        if (this.timestamp != null) {
            sb.append(", timestamp=");
            sb.append(this.timestamp);
        }
        StringBuilder replace = sb.replace(0, 2, "Value_RN_LoadBundle{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Value_RN_LoadBundle, Builder> {

        /* renamed from: a  reason: collision with root package name */
        public String f7367a;
        public String b;
        public String c;
        public Long d;
        public Long e;
        public String f;
        public Long g;

        public Builder a(String str) {
            this.f7367a = str;
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

        public Builder b(Long l) {
            this.e = l;
            return this;
        }

        public Builder d(String str) {
            this.f = str;
            return this;
        }

        public Builder c(Long l) {
            this.g = l;
            return this;
        }

        /* renamed from: a */
        public Value_RN_LoadBundle build() {
            return new Value_RN_LoadBundle(this.f7367a, this.b, this.c, this.d, this.e, this.f, this.g, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Value_RN_LoadBundle extends ProtoAdapter<Value_RN_LoadBundle> {
        ProtoAdapter_Value_RN_LoadBundle() {
            super(FieldEncoding.LENGTH_DELIMITED, Value_RN_LoadBundle.class);
        }

        /* renamed from: a */
        public int encodedSize(Value_RN_LoadBundle value_RN_LoadBundle) {
            int i = 0;
            int encodedSizeWithTag = (value_RN_LoadBundle.bundle_name != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, value_RN_LoadBundle.bundle_name) : 0) + (value_RN_LoadBundle.bundle_using_version != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, value_RN_LoadBundle.bundle_using_version) : 0) + (value_RN_LoadBundle.rn_version != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, value_RN_LoadBundle.rn_version) : 0) + (value_RN_LoadBundle.instance_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(4, value_RN_LoadBundle.instance_time) : 0) + (value_RN_LoadBundle.view_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(5, value_RN_LoadBundle.view_time) : 0) + (value_RN_LoadBundle.session_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(6, value_RN_LoadBundle.session_id) : 0);
            if (value_RN_LoadBundle.timestamp != null) {
                i = ProtoAdapter.INT64.encodedSizeWithTag(7, value_RN_LoadBundle.timestamp);
            }
            return encodedSizeWithTag + i + value_RN_LoadBundle.unknownFields().size();
        }

        /* renamed from: a */
        public void encode(ProtoWriter protoWriter, Value_RN_LoadBundle value_RN_LoadBundle) throws IOException {
            if (value_RN_LoadBundle.bundle_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, value_RN_LoadBundle.bundle_name);
            }
            if (value_RN_LoadBundle.bundle_using_version != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, value_RN_LoadBundle.bundle_using_version);
            }
            if (value_RN_LoadBundle.rn_version != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, value_RN_LoadBundle.rn_version);
            }
            if (value_RN_LoadBundle.instance_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 4, value_RN_LoadBundle.instance_time);
            }
            if (value_RN_LoadBundle.view_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 5, value_RN_LoadBundle.view_time);
            }
            if (value_RN_LoadBundle.session_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, value_RN_LoadBundle.session_id);
            }
            if (value_RN_LoadBundle.timestamp != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 7, value_RN_LoadBundle.timestamp);
            }
            protoWriter.writeBytes(value_RN_LoadBundle.unknownFields());
        }

        /* renamed from: a */
        public Value_RN_LoadBundle decode(ProtoReader protoReader) throws IOException {
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
                        case 4:
                            builder.a(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 5:
                            builder.b(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 6:
                            builder.d(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 7:
                            builder.c(ProtoAdapter.INT64.decode(protoReader));
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
        public Value_RN_LoadBundle redact(Value_RN_LoadBundle value_RN_LoadBundle) {
            Builder newBuilder = value_RN_LoadBundle.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
