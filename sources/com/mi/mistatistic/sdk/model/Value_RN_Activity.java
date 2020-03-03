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

public final class Value_RN_Activity extends Message<Value_RN_Activity, Builder> {
    public static final ProtoAdapter<Value_RN_Activity> ADAPTER = new ProtoAdapter_Value_RN_Activity();
    public static final String DEFAULT_BUNDLE_NAME = "";
    public static final String DEFAULT_BUNDLE_USING_VERSION = "";
    public static final Long DEFAULT_COMPONENTDIDMOUNT_TIME = 0L;
    public static final Long DEFAULT_COMPONENTWILLMOUNT_TIME = 0L;
    public static final Long DEFAULT_CONSTRUCTOR_TIME = 0L;
    public static final Long DEFAULT_INIT_TIME = 0L;
    public static final Long DEFAULT_ONCREATE_TIME = 0L;
    public static final Long DEFAULT_ONRESUME_TIME = 0L;
    public static final Long DEFAULT_ONSTART_TIME = 0L;
    public static final String DEFAULT_RN_INFO = "";
    public static final String DEFAULT_RN_VERSION = "";
    public static final String DEFAULT_SESSION_ID = "";
    public static final Long DEFAULT_TIMESTAMP = 0L;
    public static final Long DEFAULT_TOTAL_LOAD_TIME = 0L;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String bundle_name;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String bundle_using_version;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 10)
    public final Long componentDidMount_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 8)
    public final Long componentWillMount_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 7)
    public final Long constructor_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 13)
    public final Long init_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 4)
    public final Long onCreate_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 6)
    public final Long onResume_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 5)
    public final Long onStart_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", label = WireField.Label.REPEATED, tag = 9)
    public final List<Long> render_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 15)
    public final String rn_info;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String rn_version;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 11)
    public final String session_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 12)
    public final Long timestamp;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 14)
    public final Long total_load_time;

    public Value_RN_Activity(String str, String str2, String str3, Long l, Long l2, Long l3, Long l4, Long l5, List<Long> list, Long l6, String str4, Long l7, Long l8, Long l9, String str5) {
        this(str, str2, str3, l, l2, l3, l4, l5, list, l6, str4, l7, l8, l9, str5, ByteString.EMPTY);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Value_RN_Activity(String str, String str2, String str3, Long l, Long l2, Long l3, Long l4, Long l5, List<Long> list, Long l6, String str4, Long l7, Long l8, Long l9, String str5, ByteString byteString) {
        super(ADAPTER, byteString);
        this.bundle_name = str;
        this.bundle_using_version = str2;
        this.rn_version = str3;
        this.onCreate_time = l;
        this.onStart_time = l2;
        this.onResume_time = l3;
        this.constructor_time = l4;
        this.componentWillMount_time = l5;
        List<Long> list2 = list;
        this.render_time = Internal.immutableCopyOf("render_time", list);
        this.componentDidMount_time = l6;
        this.session_id = str4;
        this.timestamp = l7;
        this.init_time = l8;
        this.total_load_time = l9;
        this.rn_info = str5;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.f7365a = this.bundle_name;
        builder.b = this.bundle_using_version;
        builder.c = this.rn_version;
        builder.d = this.onCreate_time;
        builder.e = this.onStart_time;
        builder.f = this.onResume_time;
        builder.g = this.constructor_time;
        builder.h = this.componentWillMount_time;
        builder.i = Internal.copyOf("render_time", this.render_time);
        builder.j = this.componentDidMount_time;
        builder.k = this.session_id;
        builder.l = this.timestamp;
        builder.m = this.init_time;
        builder.n = this.total_load_time;
        builder.o = this.rn_info;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value_RN_Activity)) {
            return false;
        }
        Value_RN_Activity value_RN_Activity = (Value_RN_Activity) obj;
        if (!Internal.equals(unknownFields(), value_RN_Activity.unknownFields()) || !Internal.equals(this.bundle_name, value_RN_Activity.bundle_name) || !Internal.equals(this.bundle_using_version, value_RN_Activity.bundle_using_version) || !Internal.equals(this.rn_version, value_RN_Activity.rn_version) || !Internal.equals(this.onCreate_time, value_RN_Activity.onCreate_time) || !Internal.equals(this.onStart_time, value_RN_Activity.onStart_time) || !Internal.equals(this.onResume_time, value_RN_Activity.onResume_time) || !Internal.equals(this.constructor_time, value_RN_Activity.constructor_time) || !Internal.equals(this.componentWillMount_time, value_RN_Activity.componentWillMount_time) || !Internal.equals(this.render_time, value_RN_Activity.render_time) || !Internal.equals(this.componentDidMount_time, value_RN_Activity.componentDidMount_time) || !Internal.equals(this.session_id, value_RN_Activity.session_id) || !Internal.equals(this.timestamp, value_RN_Activity.timestamp) || !Internal.equals(this.init_time, value_RN_Activity.init_time) || !Internal.equals(this.total_load_time, value_RN_Activity.total_load_time) || !Internal.equals(this.rn_info, value_RN_Activity.rn_info)) {
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
        int hashCode = ((((((((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.bundle_name != null ? this.bundle_name.hashCode() : 0)) * 37) + (this.bundle_using_version != null ? this.bundle_using_version.hashCode() : 0)) * 37) + (this.rn_version != null ? this.rn_version.hashCode() : 0)) * 37) + (this.onCreate_time != null ? this.onCreate_time.hashCode() : 0)) * 37) + (this.onStart_time != null ? this.onStart_time.hashCode() : 0)) * 37) + (this.onResume_time != null ? this.onResume_time.hashCode() : 0)) * 37) + (this.constructor_time != null ? this.constructor_time.hashCode() : 0)) * 37) + (this.componentWillMount_time != null ? this.componentWillMount_time.hashCode() : 0)) * 37) + (this.render_time != null ? this.render_time.hashCode() : 1)) * 37) + (this.componentDidMount_time != null ? this.componentDidMount_time.hashCode() : 0)) * 37) + (this.session_id != null ? this.session_id.hashCode() : 0)) * 37) + (this.timestamp != null ? this.timestamp.hashCode() : 0)) * 37) + (this.init_time != null ? this.init_time.hashCode() : 0)) * 37) + (this.total_load_time != null ? this.total_load_time.hashCode() : 0)) * 37;
        if (this.rn_info != null) {
            i2 = this.rn_info.hashCode();
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
        if (this.onCreate_time != null) {
            sb.append(", onCreate_time=");
            sb.append(this.onCreate_time);
        }
        if (this.onStart_time != null) {
            sb.append(", onStart_time=");
            sb.append(this.onStart_time);
        }
        if (this.onResume_time != null) {
            sb.append(", onResume_time=");
            sb.append(this.onResume_time);
        }
        if (this.constructor_time != null) {
            sb.append(", constructor_time=");
            sb.append(this.constructor_time);
        }
        if (this.componentWillMount_time != null) {
            sb.append(", componentWillMount_time=");
            sb.append(this.componentWillMount_time);
        }
        if (this.render_time != null) {
            sb.append(", render_time=");
            sb.append(this.render_time);
        }
        if (this.componentDidMount_time != null) {
            sb.append(", componentDidMount_time=");
            sb.append(this.componentDidMount_time);
        }
        if (this.session_id != null) {
            sb.append(", session_id=");
            sb.append(this.session_id);
        }
        if (this.timestamp != null) {
            sb.append(", timestamp=");
            sb.append(this.timestamp);
        }
        if (this.init_time != null) {
            sb.append(", init_time=");
            sb.append(this.init_time);
        }
        if (this.total_load_time != null) {
            sb.append(", total_load_time=");
            sb.append(this.total_load_time);
        }
        if (this.rn_info != null) {
            sb.append(", rn_info=");
            sb.append(this.rn_info);
        }
        StringBuilder replace = sb.replace(0, 2, "Value_RN_Activity{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Value_RN_Activity, Builder> {

        /* renamed from: a  reason: collision with root package name */
        public String f7365a;
        public String b;
        public String c;
        public Long d;
        public Long e;
        public Long f;
        public Long g;
        public Long h;
        public List<Long> i = Internal.newMutableList();
        public Long j;
        public String k;
        public Long l;
        public Long m;
        public Long n;
        public String o;

        public Builder a(String str) {
            this.f7365a = str;
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

        public Builder a(Long l2) {
            this.d = l2;
            return this;
        }

        public Builder b(Long l2) {
            this.e = l2;
            return this;
        }

        public Builder c(Long l2) {
            this.f = l2;
            return this;
        }

        public Builder d(Long l2) {
            this.g = l2;
            return this;
        }

        public Builder e(Long l2) {
            this.h = l2;
            return this;
        }

        public Builder a(List<Long> list) {
            Internal.checkElementsNotNull(list);
            this.i = list;
            return this;
        }

        public Builder f(Long l2) {
            this.j = l2;
            return this;
        }

        public Builder d(String str) {
            this.k = str;
            return this;
        }

        public Builder g(Long l2) {
            this.l = l2;
            return this;
        }

        public Builder h(Long l2) {
            this.m = l2;
            return this;
        }

        public Builder i(Long l2) {
            this.n = l2;
            return this;
        }

        public Builder e(String str) {
            this.o = str;
            return this;
        }

        /* renamed from: a */
        public Value_RN_Activity build() {
            return new Value_RN_Activity(this.f7365a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Value_RN_Activity extends ProtoAdapter<Value_RN_Activity> {
        ProtoAdapter_Value_RN_Activity() {
            super(FieldEncoding.LENGTH_DELIMITED, Value_RN_Activity.class);
        }

        /* renamed from: a */
        public int encodedSize(Value_RN_Activity value_RN_Activity) {
            int i = 0;
            int encodedSizeWithTag = (value_RN_Activity.bundle_name != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, value_RN_Activity.bundle_name) : 0) + (value_RN_Activity.bundle_using_version != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, value_RN_Activity.bundle_using_version) : 0) + (value_RN_Activity.rn_version != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, value_RN_Activity.rn_version) : 0) + (value_RN_Activity.onCreate_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(4, value_RN_Activity.onCreate_time) : 0) + (value_RN_Activity.onStart_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(5, value_RN_Activity.onStart_time) : 0) + (value_RN_Activity.onResume_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(6, value_RN_Activity.onResume_time) : 0) + (value_RN_Activity.constructor_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(7, value_RN_Activity.constructor_time) : 0) + (value_RN_Activity.componentWillMount_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(8, value_RN_Activity.componentWillMount_time) : 0) + ProtoAdapter.INT64.asRepeated().encodedSizeWithTag(9, value_RN_Activity.render_time) + (value_RN_Activity.componentDidMount_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(10, value_RN_Activity.componentDidMount_time) : 0) + (value_RN_Activity.session_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(11, value_RN_Activity.session_id) : 0) + (value_RN_Activity.timestamp != null ? ProtoAdapter.INT64.encodedSizeWithTag(12, value_RN_Activity.timestamp) : 0) + (value_RN_Activity.init_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(13, value_RN_Activity.init_time) : 0) + (value_RN_Activity.total_load_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(14, value_RN_Activity.total_load_time) : 0);
            if (value_RN_Activity.rn_info != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(15, value_RN_Activity.rn_info);
            }
            return encodedSizeWithTag + i + value_RN_Activity.unknownFields().size();
        }

        /* renamed from: a */
        public void encode(ProtoWriter protoWriter, Value_RN_Activity value_RN_Activity) throws IOException {
            if (value_RN_Activity.bundle_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, value_RN_Activity.bundle_name);
            }
            if (value_RN_Activity.bundle_using_version != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, value_RN_Activity.bundle_using_version);
            }
            if (value_RN_Activity.rn_version != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, value_RN_Activity.rn_version);
            }
            if (value_RN_Activity.onCreate_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 4, value_RN_Activity.onCreate_time);
            }
            if (value_RN_Activity.onStart_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 5, value_RN_Activity.onStart_time);
            }
            if (value_RN_Activity.onResume_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 6, value_RN_Activity.onResume_time);
            }
            if (value_RN_Activity.constructor_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 7, value_RN_Activity.constructor_time);
            }
            if (value_RN_Activity.componentWillMount_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 8, value_RN_Activity.componentWillMount_time);
            }
            if (value_RN_Activity.render_time != null) {
                ProtoAdapter.INT64.asRepeated().encodeWithTag(protoWriter, 9, value_RN_Activity.render_time);
            }
            if (value_RN_Activity.componentDidMount_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 10, value_RN_Activity.componentDidMount_time);
            }
            if (value_RN_Activity.session_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 11, value_RN_Activity.session_id);
            }
            if (value_RN_Activity.timestamp != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 12, value_RN_Activity.timestamp);
            }
            if (value_RN_Activity.init_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 13, value_RN_Activity.init_time);
            }
            if (value_RN_Activity.total_load_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 14, value_RN_Activity.total_load_time);
            }
            if (value_RN_Activity.rn_info != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 15, value_RN_Activity.rn_info);
            }
            protoWriter.writeBytes(value_RN_Activity.unknownFields());
        }

        /* renamed from: a */
        public Value_RN_Activity decode(ProtoReader protoReader) throws IOException {
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
                            builder.c(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 7:
                            builder.d(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 8:
                            builder.e(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 9:
                            builder.i.add(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 10:
                            builder.f(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 11:
                            builder.d(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 12:
                            builder.g(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 13:
                            builder.h(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 14:
                            builder.i(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 15:
                            builder.e(ProtoAdapter.STRING.decode(protoReader));
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
        public Value_RN_Activity redact(Value_RN_Activity value_RN_Activity) {
            Builder newBuilder = value_RN_Activity.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
