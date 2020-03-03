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

public final class Value_View_Click extends Message<Value_View_Click, Builder> {
    public static final ProtoAdapter<Value_View_Click> ADAPTER = new ProtoAdapter_Value_View_Click();
    public static final String DEFAULT_LABEL = "";
    public static final String DEFAULT_PAGE_ID = "";
    public static final String DEFAULT_SESSION_ID = "";
    public static final Long DEFAULT_TIMESTAMP = 0L;
    public static final String DEFAULT_VIEW_ID = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String label;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String page_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String session_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 3)
    public final Long timestamp;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String view_id;

    public Value_View_Click(String str, Long l, String str2, String str3, String str4) {
        this(str, l, str2, str3, str4, ByteString.EMPTY);
    }

    public Value_View_Click(String str, Long l, String str2, String str3, String str4, ByteString byteString) {
        super(ADAPTER, byteString);
        this.session_id = str;
        this.timestamp = l;
        this.view_id = str2;
        this.label = str3;
        this.page_id = str4;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.f7370a = this.session_id;
        builder.b = this.timestamp;
        builder.c = this.view_id;
        builder.d = this.label;
        builder.e = this.page_id;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value_View_Click)) {
            return false;
        }
        Value_View_Click value_View_Click = (Value_View_Click) obj;
        if (!Internal.equals(unknownFields(), value_View_Click.unknownFields()) || !Internal.equals(this.session_id, value_View_Click.session_id) || !Internal.equals(this.timestamp, value_View_Click.timestamp) || !Internal.equals(this.view_id, value_View_Click.view_id) || !Internal.equals(this.label, value_View_Click.label) || !Internal.equals(this.page_id, value_View_Click.page_id)) {
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
        int hashCode = ((((((((unknownFields().hashCode() * 37) + (this.session_id != null ? this.session_id.hashCode() : 0)) * 37) + (this.timestamp != null ? this.timestamp.hashCode() : 0)) * 37) + (this.view_id != null ? this.view_id.hashCode() : 0)) * 37) + (this.label != null ? this.label.hashCode() : 0)) * 37;
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
        StringBuilder replace = sb.replace(0, 2, "Value_View_Click{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Value_View_Click, Builder> {

        /* renamed from: a  reason: collision with root package name */
        public String f7370a;
        public Long b;
        public String c;
        public String d;
        public String e;

        public Builder a(String str) {
            this.f7370a = str;
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

        public Builder d(String str) {
            this.e = str;
            return this;
        }

        /* renamed from: a */
        public Value_View_Click build() {
            return new Value_View_Click(this.f7370a, this.b, this.c, this.d, this.e, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Value_View_Click extends ProtoAdapter<Value_View_Click> {
        ProtoAdapter_Value_View_Click() {
            super(FieldEncoding.LENGTH_DELIMITED, Value_View_Click.class);
        }

        /* renamed from: a */
        public int encodedSize(Value_View_Click value_View_Click) {
            int i = 0;
            int encodedSizeWithTag = (value_View_Click.session_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, value_View_Click.session_id) : 0) + (value_View_Click.timestamp != null ? ProtoAdapter.INT64.encodedSizeWithTag(3, value_View_Click.timestamp) : 0) + (value_View_Click.view_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, value_View_Click.view_id) : 0) + (value_View_Click.label != null ? ProtoAdapter.STRING.encodedSizeWithTag(5, value_View_Click.label) : 0);
            if (value_View_Click.page_id != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(6, value_View_Click.page_id);
            }
            return encodedSizeWithTag + i + value_View_Click.unknownFields().size();
        }

        /* renamed from: a */
        public void encode(ProtoWriter protoWriter, Value_View_Click value_View_Click) throws IOException {
            if (value_View_Click.session_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, value_View_Click.session_id);
            }
            if (value_View_Click.timestamp != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 3, value_View_Click.timestamp);
            }
            if (value_View_Click.view_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, value_View_Click.view_id);
            }
            if (value_View_Click.label != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, value_View_Click.label);
            }
            if (value_View_Click.page_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, value_View_Click.page_id);
            }
            protoWriter.writeBytes(value_View_Click.unknownFields());
        }

        /* renamed from: a */
        public Value_View_Click decode(ProtoReader protoReader) throws IOException {
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
        public Value_View_Click redact(Value_View_Click value_View_Click) {
            Builder newBuilder = value_View_Click.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
