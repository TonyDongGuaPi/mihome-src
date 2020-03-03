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

public final class Value_View_Show extends Message<Value_View_Show, Builder> {
    public static final ProtoAdapter<Value_View_Show> ADAPTER = new ProtoAdapter_Value_View_Show();
    public static final String DEFAULT_LABEL = "";
    public static final String DEFAULT_PAGE_ID = "";
    public static final Long DEFAULT_PAGE_SHOW_TIME = 0L;
    public static final String DEFAULT_SESSION_ID = "";
    public static final String DEFAULT_VIEW_ID = "";
    public static final Long DEFAULT_VIEW_LEAVE_TIME = 0L;
    public static final Long DEFAULT_VIEW_SHOW_TIME = 0L;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String label;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String page_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 5)
    public final Long page_show_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String session_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String view_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 3)
    public final Long view_leave_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 4)
    public final Long view_show_time;

    public Value_View_Show(String str, Long l, Long l2, Long l3, String str2, String str3, String str4) {
        this(str, l, l2, l3, str2, str3, str4, ByteString.EMPTY);
    }

    public Value_View_Show(String str, Long l, Long l2, Long l3, String str2, String str3, String str4, ByteString byteString) {
        super(ADAPTER, byteString);
        this.session_id = str;
        this.view_leave_time = l;
        this.view_show_time = l2;
        this.page_show_time = l3;
        this.view_id = str2;
        this.label = str3;
        this.page_id = str4;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.f7371a = this.session_id;
        builder.b = this.view_leave_time;
        builder.c = this.view_show_time;
        builder.d = this.page_show_time;
        builder.e = this.view_id;
        builder.f = this.label;
        builder.g = this.page_id;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value_View_Show)) {
            return false;
        }
        Value_View_Show value_View_Show = (Value_View_Show) obj;
        if (!Internal.equals(unknownFields(), value_View_Show.unknownFields()) || !Internal.equals(this.session_id, value_View_Show.session_id) || !Internal.equals(this.view_leave_time, value_View_Show.view_leave_time) || !Internal.equals(this.view_show_time, value_View_Show.view_show_time) || !Internal.equals(this.page_show_time, value_View_Show.page_show_time) || !Internal.equals(this.view_id, value_View_Show.view_id) || !Internal.equals(this.label, value_View_Show.label) || !Internal.equals(this.page_id, value_View_Show.page_id)) {
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
        int hashCode = ((((((((((((unknownFields().hashCode() * 37) + (this.session_id != null ? this.session_id.hashCode() : 0)) * 37) + (this.view_leave_time != null ? this.view_leave_time.hashCode() : 0)) * 37) + (this.view_show_time != null ? this.view_show_time.hashCode() : 0)) * 37) + (this.page_show_time != null ? this.page_show_time.hashCode() : 0)) * 37) + (this.view_id != null ? this.view_id.hashCode() : 0)) * 37) + (this.label != null ? this.label.hashCode() : 0)) * 37;
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
        if (this.view_leave_time != null) {
            sb.append(", view_leave_time=");
            sb.append(this.view_leave_time);
        }
        if (this.view_show_time != null) {
            sb.append(", view_show_time=");
            sb.append(this.view_show_time);
        }
        if (this.page_show_time != null) {
            sb.append(", page_show_time=");
            sb.append(this.page_show_time);
        }
        if (this.view_id != null) {
            sb.append(", view_id=");
            sb.append(this.view_id);
        }
        if (this.label != null) {
            sb.append(", label=");
            sb.append(this.label);
        }
        if (this.page_id != null) {
            sb.append(", page_id=");
            sb.append(this.page_id);
        }
        StringBuilder replace = sb.replace(0, 2, "Value_View_Show{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Value_View_Show, Builder> {

        /* renamed from: a  reason: collision with root package name */
        public String f7371a;
        public Long b;
        public Long c;
        public Long d;
        public String e;
        public String f;
        public String g;

        public Builder a(String str) {
            this.f7371a = str;
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

        public Builder c(Long l) {
            this.d = l;
            return this;
        }

        public Builder b(String str) {
            this.e = str;
            return this;
        }

        public Builder c(String str) {
            this.f = str;
            return this;
        }

        public Builder d(String str) {
            this.g = str;
            return this;
        }

        /* renamed from: a */
        public Value_View_Show build() {
            return new Value_View_Show(this.f7371a, this.b, this.c, this.d, this.e, this.f, this.g, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Value_View_Show extends ProtoAdapter<Value_View_Show> {
        ProtoAdapter_Value_View_Show() {
            super(FieldEncoding.LENGTH_DELIMITED, Value_View_Show.class);
        }

        /* renamed from: a */
        public int encodedSize(Value_View_Show value_View_Show) {
            int i = 0;
            int encodedSizeWithTag = (value_View_Show.session_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, value_View_Show.session_id) : 0) + (value_View_Show.view_leave_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(3, value_View_Show.view_leave_time) : 0) + (value_View_Show.view_show_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(4, value_View_Show.view_show_time) : 0) + (value_View_Show.page_show_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(5, value_View_Show.page_show_time) : 0) + (value_View_Show.view_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(6, value_View_Show.view_id) : 0) + (value_View_Show.label != null ? ProtoAdapter.STRING.encodedSizeWithTag(7, value_View_Show.label) : 0);
            if (value_View_Show.page_id != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(8, value_View_Show.page_id);
            }
            return encodedSizeWithTag + i + value_View_Show.unknownFields().size();
        }

        /* renamed from: a */
        public void encode(ProtoWriter protoWriter, Value_View_Show value_View_Show) throws IOException {
            if (value_View_Show.session_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, value_View_Show.session_id);
            }
            if (value_View_Show.view_leave_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 3, value_View_Show.view_leave_time);
            }
            if (value_View_Show.view_show_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 4, value_View_Show.view_show_time);
            }
            if (value_View_Show.page_show_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 5, value_View_Show.page_show_time);
            }
            if (value_View_Show.view_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, value_View_Show.view_id);
            }
            if (value_View_Show.label != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, value_View_Show.label);
            }
            if (value_View_Show.page_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, value_View_Show.page_id);
            }
            protoWriter.writeBytes(value_View_Show.unknownFields());
        }

        /* renamed from: a */
        public Value_View_Show decode(ProtoReader protoReader) throws IOException {
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
                            builder.c(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 6:
                            builder.b(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 7:
                            builder.c(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 8:
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
        public Value_View_Show redact(Value_View_Show value_View_Show) {
            Builder newBuilder = value_View_Show.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
