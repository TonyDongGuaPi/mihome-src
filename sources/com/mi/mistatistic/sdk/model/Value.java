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

public final class Value extends Message<Value, Builder> {
    public static final ProtoAdapter<Value> ADAPTER = new ProtoAdapter_Value();
    private static final long serialVersionUID = 0;
    @WireField(adapter = "appstat.Value_Event#ADAPTER", tag = 1)
    public final Value_Event val_event;
    @WireField(adapter = "appstat.Value_Page#ADAPTER", tag = 2)
    public final Value_Page val_page;
    @WireField(adapter = "appstat.Value_RN_Download#ADAPTER", tag = 7)
    public final Value_RN_Download val_rn_download;
    @WireField(adapter = "appstat.Value_Session#ADAPTER", tag = 6)
    public final Value_Session val_session;
    @WireField(adapter = "appstat.Value_Startup#ADAPTER", tag = 3)
    public final Value_Startup val_startup;
    @WireField(adapter = "appstat.Value_View_Click#ADAPTER", tag = 5)
    public final Value_View_Click val_view_click;
    @WireField(adapter = "appstat.Value_View_Show#ADAPTER", tag = 4)
    public final Value_View_Show val_view_show;
    @WireField(adapter = "appstat.Value_RN_Activity#ADAPTER", tag = 9)
    public final Value_RN_Activity value_rn_activity;
    @WireField(adapter = "appstat.Value_RN_LoadBundle#ADAPTER", tag = 8)
    public final Value_RN_LoadBundle value_rn_loadBundle;

    public Value(Value_Event value_Event, Value_Page value_Page, Value_Startup value_Startup, Value_View_Show value_View_Show, Value_View_Click value_View_Click, Value_Session value_Session, Value_RN_Download value_RN_Download, Value_RN_LoadBundle value_RN_LoadBundle, Value_RN_Activity value_RN_Activity) {
        this(value_Event, value_Page, value_Startup, value_View_Show, value_View_Click, value_Session, value_RN_Download, value_RN_LoadBundle, value_RN_Activity, ByteString.EMPTY);
    }

    public Value(Value_Event value_Event, Value_Page value_Page, Value_Startup value_Startup, Value_View_Show value_View_Show, Value_View_Click value_View_Click, Value_Session value_Session, Value_RN_Download value_RN_Download, Value_RN_LoadBundle value_RN_LoadBundle, Value_RN_Activity value_RN_Activity, ByteString byteString) {
        super(ADAPTER, byteString);
        if (Internal.countNonNull(value_Event, value_Page, value_Startup, value_View_Show, value_View_Click, value_Session, value_RN_Download, value_RN_LoadBundle, value_RN_Activity) <= 1) {
            this.val_event = value_Event;
            this.val_page = value_Page;
            this.val_startup = value_Startup;
            this.val_view_show = value_View_Show;
            this.val_view_click = value_View_Click;
            this.val_session = value_Session;
            this.val_rn_download = value_RN_Download;
            this.value_rn_loadBundle = value_RN_LoadBundle;
            this.value_rn_activity = value_RN_Activity;
            return;
        }
        throw new IllegalArgumentException("at most one of val_event, val_page, val_startup, val_view_show, val_view_click, val_session, val_rn_download, value_rn_loadBundle, value_rn_activity may be non-null");
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.f7362a = this.val_event;
        builder.b = this.val_page;
        builder.c = this.val_startup;
        builder.d = this.val_view_show;
        builder.e = this.val_view_click;
        builder.f = this.val_session;
        builder.g = this.val_rn_download;
        builder.h = this.value_rn_loadBundle;
        builder.i = this.value_rn_activity;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value)) {
            return false;
        }
        Value value = (Value) obj;
        if (!Internal.equals(unknownFields(), value.unknownFields()) || !Internal.equals(this.val_event, value.val_event) || !Internal.equals(this.val_page, value.val_page) || !Internal.equals(this.val_startup, value.val_startup) || !Internal.equals(this.val_view_show, value.val_view_show) || !Internal.equals(this.val_view_click, value.val_view_click) || !Internal.equals(this.val_session, value.val_session) || !Internal.equals(this.val_rn_download, value.val_rn_download) || !Internal.equals(this.value_rn_loadBundle, value.value_rn_loadBundle) || !Internal.equals(this.value_rn_activity, value.value_rn_activity)) {
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
        int hashCode = ((((((((((((((((unknownFields().hashCode() * 37) + (this.val_event != null ? this.val_event.hashCode() : 0)) * 37) + (this.val_page != null ? this.val_page.hashCode() : 0)) * 37) + (this.val_startup != null ? this.val_startup.hashCode() : 0)) * 37) + (this.val_view_show != null ? this.val_view_show.hashCode() : 0)) * 37) + (this.val_view_click != null ? this.val_view_click.hashCode() : 0)) * 37) + (this.val_session != null ? this.val_session.hashCode() : 0)) * 37) + (this.val_rn_download != null ? this.val_rn_download.hashCode() : 0)) * 37) + (this.value_rn_loadBundle != null ? this.value_rn_loadBundle.hashCode() : 0)) * 37;
        if (this.value_rn_activity != null) {
            i2 = this.value_rn_activity.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.val_event != null) {
            sb.append(", val_event=");
            sb.append(this.val_event);
        }
        if (this.val_page != null) {
            sb.append(", val_page=");
            sb.append(this.val_page);
        }
        if (this.val_startup != null) {
            sb.append(", val_startup=");
            sb.append(this.val_startup);
        }
        if (this.val_view_show != null) {
            sb.append(", val_view_show=");
            sb.append(this.val_view_show);
        }
        if (this.val_view_click != null) {
            sb.append(", val_view_click=");
            sb.append(this.val_view_click);
        }
        if (this.val_session != null) {
            sb.append(", val_session=");
            sb.append(this.val_session);
        }
        if (this.val_rn_download != null) {
            sb.append(", val_rn_download=");
            sb.append(this.val_rn_download);
        }
        if (this.value_rn_loadBundle != null) {
            sb.append(", value_rn_loadBundle=");
            sb.append(this.value_rn_loadBundle);
        }
        if (this.value_rn_activity != null) {
            sb.append(", value_rn_activity=");
            sb.append(this.value_rn_activity);
        }
        StringBuilder replace = sb.replace(0, 2, "Value{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Value, Builder> {

        /* renamed from: a  reason: collision with root package name */
        public Value_Event f7362a;
        public Value_Page b;
        public Value_Startup c;
        public Value_View_Show d;
        public Value_View_Click e;
        public Value_Session f;
        public Value_RN_Download g;
        public Value_RN_LoadBundle h;
        public Value_RN_Activity i;

        public Builder a(Value_Event value_Event) {
            this.f7362a = value_Event;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
            this.i = null;
            return this;
        }

        public Builder a(Value_Page value_Page) {
            this.b = value_Page;
            this.f7362a = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
            this.i = null;
            return this;
        }

        public Builder a(Value_Startup value_Startup) {
            this.c = value_Startup;
            this.f7362a = null;
            this.b = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
            this.i = null;
            return this;
        }

        public Builder a(Value_View_Show value_View_Show) {
            this.d = value_View_Show;
            this.f7362a = null;
            this.b = null;
            this.c = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
            this.i = null;
            return this;
        }

        public Builder a(Value_View_Click value_View_Click) {
            this.e = value_View_Click;
            this.f7362a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.f = null;
            this.g = null;
            this.h = null;
            this.i = null;
            return this;
        }

        public Builder a(Value_Session value_Session) {
            this.f = value_Session;
            this.f7362a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.g = null;
            this.h = null;
            this.i = null;
            return this;
        }

        public Builder a(Value_RN_Download value_RN_Download) {
            this.g = value_RN_Download;
            this.f7362a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.h = null;
            this.i = null;
            return this;
        }

        public Builder a(Value_RN_LoadBundle value_RN_LoadBundle) {
            this.h = value_RN_LoadBundle;
            this.f7362a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.i = null;
            return this;
        }

        public Builder a(Value_RN_Activity value_RN_Activity) {
            this.i = value_RN_Activity;
            this.f7362a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
            return this;
        }

        /* renamed from: a */
        public Value build() {
            return new Value(this.f7362a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Value extends ProtoAdapter<Value> {
        ProtoAdapter_Value() {
            super(FieldEncoding.LENGTH_DELIMITED, Value.class);
        }

        /* renamed from: a */
        public int encodedSize(Value value) {
            int i = 0;
            int encodedSizeWithTag = (value.val_event != null ? Value_Event.ADAPTER.encodedSizeWithTag(1, value.val_event) : 0) + (value.val_page != null ? Value_Page.ADAPTER.encodedSizeWithTag(2, value.val_page) : 0) + (value.val_startup != null ? Value_Startup.ADAPTER.encodedSizeWithTag(3, value.val_startup) : 0) + (value.val_view_show != null ? Value_View_Show.ADAPTER.encodedSizeWithTag(4, value.val_view_show) : 0) + (value.val_view_click != null ? Value_View_Click.ADAPTER.encodedSizeWithTag(5, value.val_view_click) : 0) + (value.val_session != null ? Value_Session.ADAPTER.encodedSizeWithTag(6, value.val_session) : 0) + (value.val_rn_download != null ? Value_RN_Download.ADAPTER.encodedSizeWithTag(7, value.val_rn_download) : 0) + (value.value_rn_loadBundle != null ? Value_RN_LoadBundle.ADAPTER.encodedSizeWithTag(8, value.value_rn_loadBundle) : 0);
            if (value.value_rn_activity != null) {
                i = Value_RN_Activity.ADAPTER.encodedSizeWithTag(9, value.value_rn_activity);
            }
            return encodedSizeWithTag + i + value.unknownFields().size();
        }

        /* renamed from: a */
        public void encode(ProtoWriter protoWriter, Value value) throws IOException {
            if (value.val_event != null) {
                Value_Event.ADAPTER.encodeWithTag(protoWriter, 1, value.val_event);
            }
            if (value.val_page != null) {
                Value_Page.ADAPTER.encodeWithTag(protoWriter, 2, value.val_page);
            }
            if (value.val_startup != null) {
                Value_Startup.ADAPTER.encodeWithTag(protoWriter, 3, value.val_startup);
            }
            if (value.val_view_show != null) {
                Value_View_Show.ADAPTER.encodeWithTag(protoWriter, 4, value.val_view_show);
            }
            if (value.val_view_click != null) {
                Value_View_Click.ADAPTER.encodeWithTag(protoWriter, 5, value.val_view_click);
            }
            if (value.val_session != null) {
                Value_Session.ADAPTER.encodeWithTag(protoWriter, 6, value.val_session);
            }
            if (value.val_rn_download != null) {
                Value_RN_Download.ADAPTER.encodeWithTag(protoWriter, 7, value.val_rn_download);
            }
            if (value.value_rn_loadBundle != null) {
                Value_RN_LoadBundle.ADAPTER.encodeWithTag(protoWriter, 8, value.value_rn_loadBundle);
            }
            if (value.value_rn_activity != null) {
                Value_RN_Activity.ADAPTER.encodeWithTag(protoWriter, 9, value.value_rn_activity);
            }
            protoWriter.writeBytes(value.unknownFields());
        }

        /* renamed from: a */
        public Value decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.a(Value_Event.ADAPTER.decode(protoReader));
                            break;
                        case 2:
                            builder.a(Value_Page.ADAPTER.decode(protoReader));
                            break;
                        case 3:
                            builder.a(Value_Startup.ADAPTER.decode(protoReader));
                            break;
                        case 4:
                            builder.a(Value_View_Show.ADAPTER.decode(protoReader));
                            break;
                        case 5:
                            builder.a(Value_View_Click.ADAPTER.decode(protoReader));
                            break;
                        case 6:
                            builder.a(Value_Session.ADAPTER.decode(protoReader));
                            break;
                        case 7:
                            builder.a(Value_RN_Download.ADAPTER.decode(protoReader));
                            break;
                        case 8:
                            builder.a(Value_RN_LoadBundle.ADAPTER.decode(protoReader));
                            break;
                        case 9:
                            builder.a(Value_RN_Activity.ADAPTER.decode(protoReader));
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
        public Value redact(Value value) {
            Builder newBuilder = value.newBuilder();
            if (newBuilder.f7362a != null) {
                newBuilder.f7362a = Value_Event.ADAPTER.redact(newBuilder.f7362a);
            }
            if (newBuilder.b != null) {
                newBuilder.b = Value_Page.ADAPTER.redact(newBuilder.b);
            }
            if (newBuilder.c != null) {
                newBuilder.c = Value_Startup.ADAPTER.redact(newBuilder.c);
            }
            if (newBuilder.d != null) {
                newBuilder.d = Value_View_Show.ADAPTER.redact(newBuilder.d);
            }
            if (newBuilder.e != null) {
                newBuilder.e = Value_View_Click.ADAPTER.redact(newBuilder.e);
            }
            if (newBuilder.f != null) {
                newBuilder.f = Value_Session.ADAPTER.redact(newBuilder.f);
            }
            if (newBuilder.g != null) {
                newBuilder.g = Value_RN_Download.ADAPTER.redact(newBuilder.g);
            }
            if (newBuilder.h != null) {
                newBuilder.h = Value_RN_LoadBundle.ADAPTER.redact(newBuilder.h);
            }
            if (newBuilder.i != null) {
                newBuilder.i = Value_RN_Activity.ADAPTER.redact(newBuilder.i);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
