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

public final class Stat extends Message<Stat, Builder> {
    public static final ProtoAdapter<Stat> ADAPTER = new ProtoAdapter_Stat();
    public static final String DEFAULT_APP_KEY = "";
    public static final String DEFAULT_APP_VERSION = "";
    public static final String DEFAULT_CHANNEL = "";
    public static final String DEFAULT_CLIENT_IP = "";
    public static final Long DEFAULT_COLLECT_TIME = 0L;
    public static final String DEFAULT_DEVICE_ID = "";
    public static final String DEFAULT_DEVICE_NAME = "";
    public static final String DEFAULT_IMEI = "";
    public static final String DEFAULT_INSTALL_FROM = "";
    public static final String DEFAULT_INTERVAL = "";
    public static final String DEFAULT_LANGUAGE = "";
    public static final String DEFAULT_MAC = "";
    public static final String DEFAULT_MIUI_VERSION = "";
    public static final String DEFAULT_OS_VERSION = "";
    public static final String DEFAULT_POLICY = "";
    public static final Long DEFAULT_REPORT_TIME = 0L;
    public static final String DEFAULT_SDK_VERSION = "";
    public static final String DEFAULT_SIGN = "";
    public static final Integer DEFAULT_SIZE = 0;
    public static final String DEFAULT_START_FROM = "";
    public static final String DEFAULT_USER_ID = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String app_key;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String app_version;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String channel;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 17)
    public final String client_ip;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 20)
    public final Long collect_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String device_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String device_name;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 10)
    public final String imei;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 21)
    public final String install_from;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 11)
    public final String interval;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 14)
    public final String language;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final String mac;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String miui_version;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String os_version;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 12)
    public final String policy;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 18)
    public final Long report_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String sdk_version;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 13)
    public final String sign;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 15)
    public final Integer size;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 22)
    public final String start_from;
    @WireField(adapter = "appstat.Value#ADAPTER", label = WireField.Label.REPEATED, tag = 16)
    public final List<Value> stat_values;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 19)
    public final String user_id;

    public Stat(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, Integer num, List<Value> list, String str15, Long l, String str16, Long l2, String str17, String str18) {
        this(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, num, list, str15, l, str16, l2, str17, str18, ByteString.EMPTY);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Stat(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, Integer num, List<Value> list, String str15, Long l, String str16, Long l2, String str17, String str18, ByteString byteString) {
        super(ADAPTER, byteString);
        this.app_key = str;
        this.miui_version = str2;
        this.os_version = str3;
        this.sdk_version = str4;
        this.app_version = str5;
        this.channel = str6;
        this.device_id = str7;
        this.device_name = str8;
        this.mac = str9;
        this.imei = str10;
        this.interval = str11;
        this.policy = str12;
        this.sign = str13;
        this.language = str14;
        this.size = num;
        this.stat_values = Internal.immutableCopyOf("stat_values", list);
        this.client_ip = str15;
        this.report_time = l;
        this.user_id = str16;
        this.collect_time = l2;
        this.install_from = str17;
        this.start_from = str18;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.f7361a = this.app_key;
        builder.b = this.miui_version;
        builder.c = this.os_version;
        builder.d = this.sdk_version;
        builder.e = this.app_version;
        builder.f = this.channel;
        builder.g = this.device_id;
        builder.h = this.device_name;
        builder.i = this.mac;
        builder.j = this.imei;
        builder.k = this.interval;
        builder.l = this.policy;
        builder.m = this.sign;
        builder.n = this.language;
        builder.o = this.size;
        builder.p = Internal.copyOf("stat_values", this.stat_values);
        builder.q = this.client_ip;
        builder.r = this.report_time;
        builder.s = this.user_id;
        builder.t = this.collect_time;
        builder.u = this.install_from;
        builder.v = this.start_from;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Stat)) {
            return false;
        }
        Stat stat = (Stat) obj;
        if (!Internal.equals(unknownFields(), stat.unknownFields()) || !Internal.equals(this.app_key, stat.app_key) || !Internal.equals(this.miui_version, stat.miui_version) || !Internal.equals(this.os_version, stat.os_version) || !Internal.equals(this.sdk_version, stat.sdk_version) || !Internal.equals(this.app_version, stat.app_version) || !Internal.equals(this.channel, stat.channel) || !Internal.equals(this.device_id, stat.device_id) || !Internal.equals(this.device_name, stat.device_name) || !Internal.equals(this.mac, stat.mac) || !Internal.equals(this.imei, stat.imei) || !Internal.equals(this.interval, stat.interval) || !Internal.equals(this.policy, stat.policy) || !Internal.equals(this.sign, stat.sign) || !Internal.equals(this.language, stat.language) || !Internal.equals(this.size, stat.size) || !Internal.equals(this.stat_values, stat.stat_values) || !Internal.equals(this.client_ip, stat.client_ip) || !Internal.equals(this.report_time, stat.report_time) || !Internal.equals(this.user_id, stat.user_id) || !Internal.equals(this.collect_time, stat.collect_time) || !Internal.equals(this.install_from, stat.install_from) || !Internal.equals(this.start_from, stat.start_from)) {
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
        int hashCode = ((((((((((((((((((((((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.app_key != null ? this.app_key.hashCode() : 0)) * 37) + (this.miui_version != null ? this.miui_version.hashCode() : 0)) * 37) + (this.os_version != null ? this.os_version.hashCode() : 0)) * 37) + (this.sdk_version != null ? this.sdk_version.hashCode() : 0)) * 37) + (this.app_version != null ? this.app_version.hashCode() : 0)) * 37) + (this.channel != null ? this.channel.hashCode() : 0)) * 37) + (this.device_id != null ? this.device_id.hashCode() : 0)) * 37) + (this.device_name != null ? this.device_name.hashCode() : 0)) * 37) + (this.mac != null ? this.mac.hashCode() : 0)) * 37) + (this.imei != null ? this.imei.hashCode() : 0)) * 37) + (this.interval != null ? this.interval.hashCode() : 0)) * 37) + (this.policy != null ? this.policy.hashCode() : 0)) * 37) + (this.sign != null ? this.sign.hashCode() : 0)) * 37) + (this.language != null ? this.language.hashCode() : 0)) * 37) + (this.size != null ? this.size.hashCode() : 0)) * 37) + (this.stat_values != null ? this.stat_values.hashCode() : 1)) * 37) + (this.client_ip != null ? this.client_ip.hashCode() : 0)) * 37) + (this.report_time != null ? this.report_time.hashCode() : 0)) * 37) + (this.user_id != null ? this.user_id.hashCode() : 0)) * 37) + (this.collect_time != null ? this.collect_time.hashCode() : 0)) * 37) + (this.install_from != null ? this.install_from.hashCode() : 0)) * 37;
        if (this.start_from != null) {
            i2 = this.start_from.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.app_key != null) {
            sb.append(", app_key=");
            sb.append(this.app_key);
        }
        if (this.miui_version != null) {
            sb.append(", miui_version=");
            sb.append(this.miui_version);
        }
        if (this.os_version != null) {
            sb.append(", os_version=");
            sb.append(this.os_version);
        }
        if (this.sdk_version != null) {
            sb.append(", sdk_version=");
            sb.append(this.sdk_version);
        }
        if (this.app_version != null) {
            sb.append(", app_version=");
            sb.append(this.app_version);
        }
        if (this.channel != null) {
            sb.append(", channel=");
            sb.append(this.channel);
        }
        if (this.device_id != null) {
            sb.append(", device_id=");
            sb.append(this.device_id);
        }
        if (this.device_name != null) {
            sb.append(", device_name=");
            sb.append(this.device_name);
        }
        if (this.mac != null) {
            sb.append(", mac=");
            sb.append(this.mac);
        }
        if (this.imei != null) {
            sb.append(", imei=");
            sb.append(this.imei);
        }
        if (this.interval != null) {
            sb.append(", interval=");
            sb.append(this.interval);
        }
        if (this.policy != null) {
            sb.append(", policy=");
            sb.append(this.policy);
        }
        if (this.sign != null) {
            sb.append(", sign=");
            sb.append(this.sign);
        }
        if (this.language != null) {
            sb.append(", language=");
            sb.append(this.language);
        }
        if (this.size != null) {
            sb.append(", size=");
            sb.append(this.size);
        }
        if (this.stat_values != null) {
            sb.append(", stat_values=");
            sb.append(this.stat_values);
        }
        if (this.client_ip != null) {
            sb.append(", client_ip=");
            sb.append(this.client_ip);
        }
        if (this.report_time != null) {
            sb.append(", report_time=");
            sb.append(this.report_time);
        }
        if (this.user_id != null) {
            sb.append(", user_id=");
            sb.append(this.user_id);
        }
        if (this.collect_time != null) {
            sb.append(", collect_time=");
            sb.append(this.collect_time);
        }
        if (this.install_from != null) {
            sb.append(", install_from=");
            sb.append(this.install_from);
        }
        if (this.start_from != null) {
            sb.append(", start_from=");
            sb.append(this.start_from);
        }
        StringBuilder replace = sb.replace(0, 2, "Stat{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Stat, Builder> {

        /* renamed from: a  reason: collision with root package name */
        public String f7361a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public String k;
        public String l;
        public String m;
        public String n;
        public Integer o;
        public List<Value> p = Internal.newMutableList();
        public String q;
        public Long r;
        public String s;
        public Long t;
        public String u;
        public String v;

        public Builder a(String str) {
            this.f7361a = str;
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

        public Builder d(String str) {
            this.d = str;
            return this;
        }

        public Builder e(String str) {
            this.e = str;
            return this;
        }

        public Builder f(String str) {
            this.f = str;
            return this;
        }

        public Builder g(String str) {
            this.g = str;
            return this;
        }

        public Builder h(String str) {
            this.h = str;
            return this;
        }

        public Builder i(String str) {
            this.i = str;
            return this;
        }

        public Builder j(String str) {
            this.j = str;
            return this;
        }

        public Builder k(String str) {
            this.k = str;
            return this;
        }

        public Builder l(String str) {
            this.l = str;
            return this;
        }

        public Builder m(String str) {
            this.m = str;
            return this;
        }

        public Builder n(String str) {
            this.n = str;
            return this;
        }

        public Builder a(Integer num) {
            this.o = num;
            return this;
        }

        public Builder a(List<Value> list) {
            Internal.checkElementsNotNull(list);
            this.p = list;
            return this;
        }

        public Builder o(String str) {
            this.q = str;
            return this;
        }

        public Builder a(Long l2) {
            this.r = l2;
            return this;
        }

        public Builder p(String str) {
            this.s = str;
            return this;
        }

        public Builder b(Long l2) {
            this.t = l2;
            return this;
        }

        public Builder q(String str) {
            this.u = str;
            return this;
        }

        public Builder r(String str) {
            this.v = str;
            return this;
        }

        /* renamed from: a */
        public Stat build() {
            return new Stat(this.f7361a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Stat extends ProtoAdapter<Stat> {
        ProtoAdapter_Stat() {
            super(FieldEncoding.LENGTH_DELIMITED, Stat.class);
        }

        /* renamed from: a */
        public int encodedSize(Stat stat) {
            int i = 0;
            int encodedSizeWithTag = (stat.app_key != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, stat.app_key) : 0) + (stat.miui_version != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, stat.miui_version) : 0) + (stat.os_version != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, stat.os_version) : 0) + (stat.sdk_version != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, stat.sdk_version) : 0) + (stat.app_version != null ? ProtoAdapter.STRING.encodedSizeWithTag(5, stat.app_version) : 0) + (stat.channel != null ? ProtoAdapter.STRING.encodedSizeWithTag(6, stat.channel) : 0) + (stat.device_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(7, stat.device_id) : 0) + (stat.device_name != null ? ProtoAdapter.STRING.encodedSizeWithTag(8, stat.device_name) : 0) + (stat.mac != null ? ProtoAdapter.STRING.encodedSizeWithTag(9, stat.mac) : 0) + (stat.imei != null ? ProtoAdapter.STRING.encodedSizeWithTag(10, stat.imei) : 0) + (stat.interval != null ? ProtoAdapter.STRING.encodedSizeWithTag(11, stat.interval) : 0) + (stat.policy != null ? ProtoAdapter.STRING.encodedSizeWithTag(12, stat.policy) : 0) + (stat.sign != null ? ProtoAdapter.STRING.encodedSizeWithTag(13, stat.sign) : 0) + (stat.language != null ? ProtoAdapter.STRING.encodedSizeWithTag(14, stat.language) : 0) + (stat.size != null ? ProtoAdapter.INT32.encodedSizeWithTag(15, stat.size) : 0) + Value.ADAPTER.asRepeated().encodedSizeWithTag(16, stat.stat_values) + (stat.client_ip != null ? ProtoAdapter.STRING.encodedSizeWithTag(17, stat.client_ip) : 0) + (stat.report_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(18, stat.report_time) : 0) + (stat.user_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(19, stat.user_id) : 0) + (stat.collect_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(20, stat.collect_time) : 0) + (stat.install_from != null ? ProtoAdapter.STRING.encodedSizeWithTag(21, stat.install_from) : 0);
            if (stat.start_from != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(22, stat.start_from);
            }
            return encodedSizeWithTag + i + stat.unknownFields().size();
        }

        /* renamed from: a */
        public void encode(ProtoWriter protoWriter, Stat stat) throws IOException {
            if (stat.app_key != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, stat.app_key);
            }
            if (stat.miui_version != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, stat.miui_version);
            }
            if (stat.os_version != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, stat.os_version);
            }
            if (stat.sdk_version != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, stat.sdk_version);
            }
            if (stat.app_version != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, stat.app_version);
            }
            if (stat.channel != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, stat.channel);
            }
            if (stat.device_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, stat.device_id);
            }
            if (stat.device_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, stat.device_name);
            }
            if (stat.mac != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 9, stat.mac);
            }
            if (stat.imei != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 10, stat.imei);
            }
            if (stat.interval != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 11, stat.interval);
            }
            if (stat.policy != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 12, stat.policy);
            }
            if (stat.sign != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 13, stat.sign);
            }
            if (stat.language != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 14, stat.language);
            }
            if (stat.size != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 15, stat.size);
            }
            if (stat.stat_values != null) {
                Value.ADAPTER.asRepeated().encodeWithTag(protoWriter, 16, stat.stat_values);
            }
            if (stat.client_ip != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 17, stat.client_ip);
            }
            if (stat.report_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 18, stat.report_time);
            }
            if (stat.user_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 19, stat.user_id);
            }
            if (stat.collect_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 20, stat.collect_time);
            }
            if (stat.install_from != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 21, stat.install_from);
            }
            if (stat.start_from != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 22, stat.start_from);
            }
            protoWriter.writeBytes(stat.unknownFields());
        }

        /* renamed from: a */
        public Stat decode(ProtoReader protoReader) throws IOException {
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
                            builder.d(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 5:
                            builder.e(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 6:
                            builder.f(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 7:
                            builder.g(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 8:
                            builder.h(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 9:
                            builder.i(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 10:
                            builder.j(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 11:
                            builder.k(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 12:
                            builder.l(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 13:
                            builder.m(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 14:
                            builder.n(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 15:
                            builder.a(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 16:
                            builder.p.add(Value.ADAPTER.decode(protoReader));
                            break;
                        case 17:
                            builder.o(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 18:
                            builder.a(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 19:
                            builder.p(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 20:
                            builder.b(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 21:
                            builder.q(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 22:
                            builder.r(ProtoAdapter.STRING.decode(protoReader));
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
        public Stat redact(Stat stat) {
            Builder newBuilder = stat.newBuilder();
            Internal.redactElements(newBuilder.p, Value.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
