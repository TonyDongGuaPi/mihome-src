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

public final class Item extends Message<Item, Builder> {
    public static final ProtoAdapter<Item> ADAPTER = new ProtoAdapter_Item();
    public static final Long DEFAULT_CONNECT_COST = 0L;
    public static final String DEFAULT_DNS = "";
    public static final Long DEFAULT_DNS_COST = 0L;
    public static final String DEFAULT_EXCEPTION = "";
    public static final String DEFAULT_HOST = "";
    public static final Integer DEFAULT_HTTP_STATUS = 0;
    public static final Long DEFAULT_INPUT_COST = 0L;
    public static final String DEFAULT_LOG_ID = "";
    public static final String DEFAULT_NET = "";
    public static final Long DEFAULT_OUTPUT_COST = 0L;
    public static final Long DEFAULT_PACKAGE_SIZE = 0L;
    public static final Long DEFAULT_TIME_COST = 0L;
    public static final Long DEFAULT_TS_CONNECTED = 0L;
    public static final Long DEFAULT_TS_DISCONNECTED = 0L;
    public static final Long DEFAULT_TS_GET_IP = 0L;
    public static final Long DEFAULT_TS_GOT_IP = 0L;
    public static final Long DEFAULT_TS_INPUT_CLOSED = 0L;
    public static final Long DEFAULT_TS_OUTPUT_CLOSED = 0L;
    public static final Long DEFAULT_TS_RECORD = 0L;
    public static final Long DEFAULT_TS_TRY_CONNECT = 0L;
    public static final String DEFAULT_URL = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 18)
    public final Long connect_cost;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 14)
    public final String dns;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 19)
    public final Long dns_cost;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 15)
    public final String exception;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 17)
    public final String host;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer http_status;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 20)
    public final Long input_cost;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String log_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)

    /* renamed from: net  reason: collision with root package name */
    public final String f1347net;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 21)
    public final Long output_cost;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 4)
    public final Long package_size;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 6)
    public final Long time_cost;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 8)
    public final Long ts_connected;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 11)
    public final Long ts_disconnected;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 12)
    public final Long ts_get_ip;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 13)
    public final Long ts_got_ip;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 9)
    public final Long ts_input_closed;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 10)
    public final Long ts_output_closed;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 16)
    public final Long ts_record;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 7)
    public final Long ts_try_connect;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String url;

    public Item(Integer num, String str, String str2, Long l, String str3, Long l2, Long l3, Long l4, Long l5, Long l6, Long l7, Long l8, Long l9, String str4, String str5, Long l10, String str6, Long l11, Long l12, Long l13, Long l14) {
        this(num, str, str2, l, str3, l2, l3, l4, l5, l6, l7, l8, l9, str4, str5, l10, str6, l11, l12, l13, l14, ByteString.EMPTY);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Item(Integer num, String str, String str2, Long l, String str3, Long l2, Long l3, Long l4, Long l5, Long l6, Long l7, Long l8, Long l9, String str4, String str5, Long l10, String str6, Long l11, Long l12, Long l13, Long l14, ByteString byteString) {
        super(ADAPTER, byteString);
        this.http_status = num;
        this.f1347net = str;
        this.url = str2;
        this.package_size = l;
        this.log_id = str3;
        this.time_cost = l2;
        this.ts_try_connect = l3;
        this.ts_connected = l4;
        this.ts_input_closed = l5;
        this.ts_output_closed = l6;
        this.ts_disconnected = l7;
        this.ts_get_ip = l8;
        this.ts_got_ip = l9;
        this.dns = str4;
        this.exception = str5;
        this.ts_record = l10;
        this.host = str6;
        this.connect_cost = l11;
        this.dns_cost = l12;
        this.input_cost = l13;
        this.output_cost = l14;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.f7360a = this.http_status;
        builder.b = this.f1347net;
        builder.c = this.url;
        builder.d = this.package_size;
        builder.e = this.log_id;
        builder.f = this.time_cost;
        builder.g = this.ts_try_connect;
        builder.h = this.ts_connected;
        builder.i = this.ts_input_closed;
        builder.j = this.ts_output_closed;
        builder.k = this.ts_disconnected;
        builder.l = this.ts_get_ip;
        builder.m = this.ts_got_ip;
        builder.n = this.dns;
        builder.o = this.exception;
        builder.p = this.ts_record;
        builder.q = this.host;
        builder.r = this.connect_cost;
        builder.s = this.dns_cost;
        builder.t = this.input_cost;
        builder.u = this.output_cost;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Item)) {
            return false;
        }
        Item item = (Item) obj;
        if (!Internal.equals(unknownFields(), item.unknownFields()) || !Internal.equals(this.http_status, item.http_status) || !Internal.equals(this.f1347net, item.f1347net) || !Internal.equals(this.url, item.url) || !Internal.equals(this.package_size, item.package_size) || !Internal.equals(this.log_id, item.log_id) || !Internal.equals(this.time_cost, item.time_cost) || !Internal.equals(this.ts_try_connect, item.ts_try_connect) || !Internal.equals(this.ts_connected, item.ts_connected) || !Internal.equals(this.ts_input_closed, item.ts_input_closed) || !Internal.equals(this.ts_output_closed, item.ts_output_closed) || !Internal.equals(this.ts_disconnected, item.ts_disconnected) || !Internal.equals(this.ts_get_ip, item.ts_get_ip) || !Internal.equals(this.ts_got_ip, item.ts_got_ip) || !Internal.equals(this.dns, item.dns) || !Internal.equals(this.exception, item.exception) || !Internal.equals(this.ts_record, item.ts_record) || !Internal.equals(this.host, item.host) || !Internal.equals(this.connect_cost, item.connect_cost) || !Internal.equals(this.dns_cost, item.dns_cost) || !Internal.equals(this.input_cost, item.input_cost) || !Internal.equals(this.output_cost, item.output_cost)) {
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
        int hashCode = ((((((((((((((((((((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.http_status != null ? this.http_status.hashCode() : 0)) * 37) + (this.f1347net != null ? this.f1347net.hashCode() : 0)) * 37) + (this.url != null ? this.url.hashCode() : 0)) * 37) + (this.package_size != null ? this.package_size.hashCode() : 0)) * 37) + (this.log_id != null ? this.log_id.hashCode() : 0)) * 37) + (this.time_cost != null ? this.time_cost.hashCode() : 0)) * 37) + (this.ts_try_connect != null ? this.ts_try_connect.hashCode() : 0)) * 37) + (this.ts_connected != null ? this.ts_connected.hashCode() : 0)) * 37) + (this.ts_input_closed != null ? this.ts_input_closed.hashCode() : 0)) * 37) + (this.ts_output_closed != null ? this.ts_output_closed.hashCode() : 0)) * 37) + (this.ts_disconnected != null ? this.ts_disconnected.hashCode() : 0)) * 37) + (this.ts_get_ip != null ? this.ts_get_ip.hashCode() : 0)) * 37) + (this.ts_got_ip != null ? this.ts_got_ip.hashCode() : 0)) * 37) + (this.dns != null ? this.dns.hashCode() : 0)) * 37) + (this.exception != null ? this.exception.hashCode() : 0)) * 37) + (this.ts_record != null ? this.ts_record.hashCode() : 0)) * 37) + (this.host != null ? this.host.hashCode() : 0)) * 37) + (this.connect_cost != null ? this.connect_cost.hashCode() : 0)) * 37) + (this.dns_cost != null ? this.dns_cost.hashCode() : 0)) * 37) + (this.input_cost != null ? this.input_cost.hashCode() : 0)) * 37;
        if (this.output_cost != null) {
            i2 = this.output_cost.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.http_status != null) {
            sb.append(", http_status=");
            sb.append(this.http_status);
        }
        if (this.f1347net != null) {
            sb.append(", net=");
            sb.append(this.f1347net);
        }
        if (this.url != null) {
            sb.append(", url=");
            sb.append(this.url);
        }
        if (this.package_size != null) {
            sb.append(", package_size=");
            sb.append(this.package_size);
        }
        if (this.log_id != null) {
            sb.append(", log_id=");
            sb.append(this.log_id);
        }
        if (this.time_cost != null) {
            sb.append(", time_cost=");
            sb.append(this.time_cost);
        }
        if (this.ts_try_connect != null) {
            sb.append(", ts_try_connect=");
            sb.append(this.ts_try_connect);
        }
        if (this.ts_connected != null) {
            sb.append(", ts_connected=");
            sb.append(this.ts_connected);
        }
        if (this.ts_input_closed != null) {
            sb.append(", ts_input_closed=");
            sb.append(this.ts_input_closed);
        }
        if (this.ts_output_closed != null) {
            sb.append(", ts_output_closed=");
            sb.append(this.ts_output_closed);
        }
        if (this.ts_disconnected != null) {
            sb.append(", ts_disconnected=");
            sb.append(this.ts_disconnected);
        }
        if (this.ts_get_ip != null) {
            sb.append(", ts_get_ip=");
            sb.append(this.ts_get_ip);
        }
        if (this.ts_got_ip != null) {
            sb.append(", ts_got_ip=");
            sb.append(this.ts_got_ip);
        }
        if (this.dns != null) {
            sb.append(", dns=");
            sb.append(this.dns);
        }
        if (this.exception != null) {
            sb.append(", exception=");
            sb.append(this.exception);
        }
        if (this.ts_record != null) {
            sb.append(", ts_record=");
            sb.append(this.ts_record);
        }
        if (this.host != null) {
            sb.append(", host=");
            sb.append(this.host);
        }
        if (this.connect_cost != null) {
            sb.append(", connect_cost=");
            sb.append(this.connect_cost);
        }
        if (this.dns_cost != null) {
            sb.append(", dns_cost=");
            sb.append(this.dns_cost);
        }
        if (this.input_cost != null) {
            sb.append(", input_cost=");
            sb.append(this.input_cost);
        }
        if (this.output_cost != null) {
            sb.append(", output_cost=");
            sb.append(this.output_cost);
        }
        StringBuilder replace = sb.replace(0, 2, "Item{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Item, Builder> {

        /* renamed from: a  reason: collision with root package name */
        public Integer f7360a;
        public String b;
        public String c;
        public Long d;
        public String e;
        public Long f;
        public Long g;
        public Long h;
        public Long i;
        public Long j;
        public Long k;
        public Long l;
        public Long m;
        public String n;
        public String o;
        public Long p;
        public String q;
        public Long r;
        public Long s;
        public Long t;
        public Long u;

        public Builder a(Integer num) {
            this.f7360a = num;
            return this;
        }

        public Builder a(String str) {
            this.b = str;
            return this;
        }

        public Builder b(String str) {
            this.c = str;
            return this;
        }

        public Builder a(Long l2) {
            this.d = l2;
            return this;
        }

        public Builder c(String str) {
            this.e = str;
            return this;
        }

        public Builder b(Long l2) {
            this.f = l2;
            return this;
        }

        public Builder c(Long l2) {
            this.g = l2;
            return this;
        }

        public Builder d(Long l2) {
            this.h = l2;
            return this;
        }

        public Builder e(Long l2) {
            this.i = l2;
            return this;
        }

        public Builder f(Long l2) {
            this.j = l2;
            return this;
        }

        public Builder g(Long l2) {
            this.k = l2;
            return this;
        }

        public Builder h(Long l2) {
            this.l = l2;
            return this;
        }

        public Builder i(Long l2) {
            this.m = l2;
            return this;
        }

        public Builder d(String str) {
            this.n = str;
            return this;
        }

        public Builder e(String str) {
            this.o = str;
            return this;
        }

        public Builder j(Long l2) {
            this.p = l2;
            return this;
        }

        public Builder f(String str) {
            this.q = str;
            return this;
        }

        public Builder k(Long l2) {
            this.r = l2;
            return this;
        }

        public Builder l(Long l2) {
            this.s = l2;
            return this;
        }

        public Builder m(Long l2) {
            this.t = l2;
            return this;
        }

        public Builder n(Long l2) {
            this.u = l2;
            return this;
        }

        /* renamed from: a */
        public Item build() {
            return new Item(this.f7360a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Item extends ProtoAdapter<Item> {
        ProtoAdapter_Item() {
            super(FieldEncoding.LENGTH_DELIMITED, Item.class);
        }

        /* renamed from: a */
        public int encodedSize(Item item) {
            int i = 0;
            int encodedSizeWithTag = (item.http_status != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, item.http_status) : 0) + (item.f1347net != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, item.f1347net) : 0) + (item.url != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, item.url) : 0) + (item.package_size != null ? ProtoAdapter.INT64.encodedSizeWithTag(4, item.package_size) : 0) + (item.log_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(5, item.log_id) : 0) + (item.time_cost != null ? ProtoAdapter.INT64.encodedSizeWithTag(6, item.time_cost) : 0) + (item.ts_try_connect != null ? ProtoAdapter.INT64.encodedSizeWithTag(7, item.ts_try_connect) : 0) + (item.ts_connected != null ? ProtoAdapter.INT64.encodedSizeWithTag(8, item.ts_connected) : 0) + (item.ts_input_closed != null ? ProtoAdapter.INT64.encodedSizeWithTag(9, item.ts_input_closed) : 0) + (item.ts_output_closed != null ? ProtoAdapter.INT64.encodedSizeWithTag(10, item.ts_output_closed) : 0) + (item.ts_disconnected != null ? ProtoAdapter.INT64.encodedSizeWithTag(11, item.ts_disconnected) : 0) + (item.ts_get_ip != null ? ProtoAdapter.INT64.encodedSizeWithTag(12, item.ts_get_ip) : 0) + (item.ts_got_ip != null ? ProtoAdapter.INT64.encodedSizeWithTag(13, item.ts_got_ip) : 0) + (item.dns != null ? ProtoAdapter.STRING.encodedSizeWithTag(14, item.dns) : 0) + (item.exception != null ? ProtoAdapter.STRING.encodedSizeWithTag(15, item.exception) : 0) + (item.ts_record != null ? ProtoAdapter.INT64.encodedSizeWithTag(16, item.ts_record) : 0) + (item.host != null ? ProtoAdapter.STRING.encodedSizeWithTag(17, item.host) : 0) + (item.connect_cost != null ? ProtoAdapter.INT64.encodedSizeWithTag(18, item.connect_cost) : 0) + (item.dns_cost != null ? ProtoAdapter.INT64.encodedSizeWithTag(19, item.dns_cost) : 0) + (item.input_cost != null ? ProtoAdapter.INT64.encodedSizeWithTag(20, item.input_cost) : 0);
            if (item.output_cost != null) {
                i = ProtoAdapter.INT64.encodedSizeWithTag(21, item.output_cost);
            }
            return encodedSizeWithTag + i + item.unknownFields().size();
        }

        /* renamed from: a */
        public void encode(ProtoWriter protoWriter, Item item) throws IOException {
            if (item.http_status != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, item.http_status);
            }
            if (item.f1347net != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, item.f1347net);
            }
            if (item.url != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, item.url);
            }
            if (item.package_size != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 4, item.package_size);
            }
            if (item.log_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, item.log_id);
            }
            if (item.time_cost != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 6, item.time_cost);
            }
            if (item.ts_try_connect != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 7, item.ts_try_connect);
            }
            if (item.ts_connected != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 8, item.ts_connected);
            }
            if (item.ts_input_closed != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 9, item.ts_input_closed);
            }
            if (item.ts_output_closed != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 10, item.ts_output_closed);
            }
            if (item.ts_disconnected != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 11, item.ts_disconnected);
            }
            if (item.ts_get_ip != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 12, item.ts_get_ip);
            }
            if (item.ts_got_ip != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 13, item.ts_got_ip);
            }
            if (item.dns != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 14, item.dns);
            }
            if (item.exception != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 15, item.exception);
            }
            if (item.ts_record != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 16, item.ts_record);
            }
            if (item.host != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 17, item.host);
            }
            if (item.connect_cost != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 18, item.connect_cost);
            }
            if (item.dns_cost != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 19, item.dns_cost);
            }
            if (item.input_cost != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 20, item.input_cost);
            }
            if (item.output_cost != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 21, item.output_cost);
            }
            protoWriter.writeBytes(item.unknownFields());
        }

        /* renamed from: a */
        public Item decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.a(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 2:
                            builder.a(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.b(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.a(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 5:
                            builder.c(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 6:
                            builder.b(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 7:
                            builder.c(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 8:
                            builder.d(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 9:
                            builder.e(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 10:
                            builder.f(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 11:
                            builder.g(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 12:
                            builder.h(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 13:
                            builder.i(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 14:
                            builder.d(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 15:
                            builder.e(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 16:
                            builder.j(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 17:
                            builder.f(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 18:
                            builder.k(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 19:
                            builder.l(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 20:
                            builder.m(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 21:
                            builder.n(ProtoAdapter.INT64.decode(protoReader));
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
        public Item redact(Item item) {
            Builder newBuilder = item.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
