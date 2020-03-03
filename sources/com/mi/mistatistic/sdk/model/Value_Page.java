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

public final class Value_Page extends Message<Value_Page, Builder> {
    public static final ProtoAdapter<Value_Page> ADAPTER = new ProtoAdapter_Value_Page();
    public static final Long DEFAULT_END_TIME = 0L;
    public static final String DEFAULT_PAGE_ID = "";
    public static final String DEFAULT_PAGE_REF = "";
    public static final String DEFAULT_SESSION_ID = "";
    public static final Long DEFAULT_START_TIME = 0L;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 4)
    public final Long end_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String page_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String page_ref;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String session_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 3)
    public final Long start_time;

    public Value_Page(String str, Long l, Long l2, String str2, String str3) {
        this(str, l, l2, str2, str3, ByteString.EMPTY);
    }

    public Value_Page(String str, Long l, Long l2, String str2, String str3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.session_id = str;
        this.start_time = l;
        this.end_time = l2;
        this.page_id = str2;
        this.page_ref = str3;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.f7364a = this.session_id;
        builder.b = this.start_time;
        builder.c = this.end_time;
        builder.d = this.page_id;
        builder.e = this.page_ref;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value_Page)) {
            return false;
        }
        Value_Page value_Page = (Value_Page) obj;
        if (!Internal.equals(unknownFields(), value_Page.unknownFields()) || !Internal.equals(this.session_id, value_Page.session_id) || !Internal.equals(this.start_time, value_Page.start_time) || !Internal.equals(this.end_time, value_Page.end_time) || !Internal.equals(this.page_id, value_Page.page_id) || !Internal.equals(this.page_ref, value_Page.page_ref)) {
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
        int hashCode = ((((((((unknownFields().hashCode() * 37) + (this.session_id != null ? this.session_id.hashCode() : 0)) * 37) + (this.start_time != null ? this.start_time.hashCode() : 0)) * 37) + (this.end_time != null ? this.end_time.hashCode() : 0)) * 37) + (this.page_id != null ? this.page_id.hashCode() : 0)) * 37;
        if (this.page_ref != null) {
            i2 = this.page_ref.hashCode();
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
        if (this.page_id != null) {
            sb.append(", page_id=");
            sb.append(this.page_id);
        }
        if (this.page_ref != null) {
            sb.append(", page_ref=");
            sb.append(this.page_ref);
        }
        StringBuilder replace = sb.replace(0, 2, "Value_Page{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Value_Page, Builder> {

        /* renamed from: a  reason: collision with root package name */
        public String f7364a;
        public Long b;
        public Long c;
        public String d;
        public String e;

        public Builder a(String str) {
            this.f7364a = str;
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

        public Builder c(String str) {
            this.e = str;
            return this;
        }

        /* renamed from: a */
        public Value_Page build() {
            return new Value_Page(this.f7364a, this.b, this.c, this.d, this.e, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Value_Page extends ProtoAdapter<Value_Page> {
        ProtoAdapter_Value_Page() {
            super(FieldEncoding.LENGTH_DELIMITED, Value_Page.class);
        }

        /* renamed from: a */
        public int encodedSize(Value_Page value_Page) {
            int i = 0;
            int encodedSizeWithTag = (value_Page.session_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, value_Page.session_id) : 0) + (value_Page.start_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(3, value_Page.start_time) : 0) + (value_Page.end_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(4, value_Page.end_time) : 0) + (value_Page.page_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(5, value_Page.page_id) : 0);
            if (value_Page.page_ref != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(6, value_Page.page_ref);
            }
            return encodedSizeWithTag + i + value_Page.unknownFields().size();
        }

        /* renamed from: a */
        public void encode(ProtoWriter protoWriter, Value_Page value_Page) throws IOException {
            if (value_Page.session_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, value_Page.session_id);
            }
            if (value_Page.start_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 3, value_Page.start_time);
            }
            if (value_Page.end_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 4, value_Page.end_time);
            }
            if (value_Page.page_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, value_Page.page_id);
            }
            if (value_Page.page_ref != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, value_Page.page_ref);
            }
            protoWriter.writeBytes(value_Page.unknownFields());
        }

        /* renamed from: a */
        public Value_Page decode(ProtoReader protoReader) throws IOException {
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
                        case 6:
                            builder.c(ProtoAdapter.STRING.decode(protoReader));
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
        public Value_Page redact(Value_Page value_Page) {
            Builder newBuilder = value_Page.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
