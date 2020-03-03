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

public final class Value_RN_Download extends Message<Value_RN_Download, Builder> {
    public static final ProtoAdapter<Value_RN_Download> ADAPTER = new ProtoAdapter_Value_RN_Download();
    public static final String DEFAULT_BUNDLE_DOWNLOAD_VERSION = "";
    public static final String DEFAULT_BUNDLE_NAME = "";
    public static final String DEFAULT_BUNDLE_USING_VERSION = "";
    public static final Integer DEFAULT_CODE = 0;
    public static final Long DEFAULT_DOWNLOAD_TIME = 0L;
    public static final String DEFAULT_ERR_MESSAGE = "";
    public static final Long DEFAULT_MD5_TIME = 0L;
    public static final Long DEFAULT_PATCH_TIME = 0L;
    public static final String DEFAULT_RN_VERSION = "";
    public static final String DEFAULT_SESSION_ID = "";
    public static final Long DEFAULT_TIMESTAMP = 0L;
    public static final Long DEFAULT_UNZIP_TIME = 0L;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String bundle_download_version;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String bundle_name;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String bundle_using_version;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 11)
    public final Integer code;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 5)
    public final Long download_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 12)
    public final String err_message;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 8)
    public final Long md5_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 7)
    public final Long patch_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String rn_version;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final String session_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 10)
    public final Long timestamp;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 6)
    public final Long unzip_time;

    public Value_RN_Download(String str, String str2, String str3, String str4, Long l, Long l2, Long l3, Long l4, String str5, Long l5, Integer num, String str6) {
        this(str, str2, str3, str4, l, l2, l3, l4, str5, l5, num, str6, ByteString.EMPTY);
    }

    public Value_RN_Download(String str, String str2, String str3, String str4, Long l, Long l2, Long l3, Long l4, String str5, Long l5, Integer num, String str6, ByteString byteString) {
        super(ADAPTER, byteString);
        this.bundle_name = str;
        this.bundle_download_version = str2;
        this.bundle_using_version = str3;
        this.rn_version = str4;
        this.download_time = l;
        this.unzip_time = l2;
        this.patch_time = l3;
        this.md5_time = l4;
        this.session_id = str5;
        this.timestamp = l5;
        this.code = num;
        this.err_message = str6;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.f7366a = this.bundle_name;
        builder.b = this.bundle_download_version;
        builder.c = this.bundle_using_version;
        builder.d = this.rn_version;
        builder.e = this.download_time;
        builder.f = this.unzip_time;
        builder.g = this.patch_time;
        builder.h = this.md5_time;
        builder.i = this.session_id;
        builder.j = this.timestamp;
        builder.k = this.code;
        builder.l = this.err_message;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value_RN_Download)) {
            return false;
        }
        Value_RN_Download value_RN_Download = (Value_RN_Download) obj;
        if (!Internal.equals(unknownFields(), value_RN_Download.unknownFields()) || !Internal.equals(this.bundle_name, value_RN_Download.bundle_name) || !Internal.equals(this.bundle_download_version, value_RN_Download.bundle_download_version) || !Internal.equals(this.bundle_using_version, value_RN_Download.bundle_using_version) || !Internal.equals(this.rn_version, value_RN_Download.rn_version) || !Internal.equals(this.download_time, value_RN_Download.download_time) || !Internal.equals(this.unzip_time, value_RN_Download.unzip_time) || !Internal.equals(this.patch_time, value_RN_Download.patch_time) || !Internal.equals(this.md5_time, value_RN_Download.md5_time) || !Internal.equals(this.session_id, value_RN_Download.session_id) || !Internal.equals(this.timestamp, value_RN_Download.timestamp) || !Internal.equals(this.code, value_RN_Download.code) || !Internal.equals(this.err_message, value_RN_Download.err_message)) {
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
        int hashCode = ((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.bundle_name != null ? this.bundle_name.hashCode() : 0)) * 37) + (this.bundle_download_version != null ? this.bundle_download_version.hashCode() : 0)) * 37) + (this.bundle_using_version != null ? this.bundle_using_version.hashCode() : 0)) * 37) + (this.rn_version != null ? this.rn_version.hashCode() : 0)) * 37) + (this.download_time != null ? this.download_time.hashCode() : 0)) * 37) + (this.unzip_time != null ? this.unzip_time.hashCode() : 0)) * 37) + (this.patch_time != null ? this.patch_time.hashCode() : 0)) * 37) + (this.md5_time != null ? this.md5_time.hashCode() : 0)) * 37) + (this.session_id != null ? this.session_id.hashCode() : 0)) * 37) + (this.timestamp != null ? this.timestamp.hashCode() : 0)) * 37) + (this.code != null ? this.code.hashCode() : 0)) * 37;
        if (this.err_message != null) {
            i2 = this.err_message.hashCode();
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
        if (this.bundle_download_version != null) {
            sb.append(", bundle_download_version=");
            sb.append(this.bundle_download_version);
        }
        if (this.bundle_using_version != null) {
            sb.append(", bundle_using_version=");
            sb.append(this.bundle_using_version);
        }
        if (this.rn_version != null) {
            sb.append(", rn_version=");
            sb.append(this.rn_version);
        }
        if (this.download_time != null) {
            sb.append(", download_time=");
            sb.append(this.download_time);
        }
        if (this.unzip_time != null) {
            sb.append(", unzip_time=");
            sb.append(this.unzip_time);
        }
        if (this.patch_time != null) {
            sb.append(", patch_time=");
            sb.append(this.patch_time);
        }
        if (this.md5_time != null) {
            sb.append(", md5_time=");
            sb.append(this.md5_time);
        }
        if (this.session_id != null) {
            sb.append(", session_id=");
            sb.append(this.session_id);
        }
        if (this.timestamp != null) {
            sb.append(", timestamp=");
            sb.append(this.timestamp);
        }
        if (this.code != null) {
            sb.append(", code=");
            sb.append(this.code);
        }
        if (this.err_message != null) {
            sb.append(", err_message=");
            sb.append(this.err_message);
        }
        StringBuilder replace = sb.replace(0, 2, "Value_RN_Download{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Value_RN_Download, Builder> {

        /* renamed from: a  reason: collision with root package name */
        public String f7366a;
        public String b;
        public String c;
        public String d;
        public Long e;
        public Long f;
        public Long g;
        public Long h;
        public String i;
        public Long j;
        public Integer k;
        public String l;

        public Builder a(String str) {
            this.f7366a = str;
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

        public Builder a(Long l2) {
            this.e = l2;
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

        public Builder e(String str) {
            this.i = str;
            return this;
        }

        public Builder e(Long l2) {
            this.j = l2;
            return this;
        }

        public Builder a(Integer num) {
            this.k = num;
            return this;
        }

        public Builder f(String str) {
            this.l = str;
            return this;
        }

        /* renamed from: a */
        public Value_RN_Download build() {
            return new Value_RN_Download(this.f7366a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Value_RN_Download extends ProtoAdapter<Value_RN_Download> {
        ProtoAdapter_Value_RN_Download() {
            super(FieldEncoding.LENGTH_DELIMITED, Value_RN_Download.class);
        }

        /* renamed from: a */
        public int encodedSize(Value_RN_Download value_RN_Download) {
            int i = 0;
            int encodedSizeWithTag = (value_RN_Download.bundle_name != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, value_RN_Download.bundle_name) : 0) + (value_RN_Download.bundle_download_version != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, value_RN_Download.bundle_download_version) : 0) + (value_RN_Download.bundle_using_version != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, value_RN_Download.bundle_using_version) : 0) + (value_RN_Download.rn_version != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, value_RN_Download.rn_version) : 0) + (value_RN_Download.download_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(5, value_RN_Download.download_time) : 0) + (value_RN_Download.unzip_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(6, value_RN_Download.unzip_time) : 0) + (value_RN_Download.patch_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(7, value_RN_Download.patch_time) : 0) + (value_RN_Download.md5_time != null ? ProtoAdapter.INT64.encodedSizeWithTag(8, value_RN_Download.md5_time) : 0) + (value_RN_Download.session_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(9, value_RN_Download.session_id) : 0) + (value_RN_Download.timestamp != null ? ProtoAdapter.INT64.encodedSizeWithTag(10, value_RN_Download.timestamp) : 0) + (value_RN_Download.code != null ? ProtoAdapter.INT32.encodedSizeWithTag(11, value_RN_Download.code) : 0);
            if (value_RN_Download.err_message != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(12, value_RN_Download.err_message);
            }
            return encodedSizeWithTag + i + value_RN_Download.unknownFields().size();
        }

        /* renamed from: a */
        public void encode(ProtoWriter protoWriter, Value_RN_Download value_RN_Download) throws IOException {
            if (value_RN_Download.bundle_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, value_RN_Download.bundle_name);
            }
            if (value_RN_Download.bundle_download_version != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, value_RN_Download.bundle_download_version);
            }
            if (value_RN_Download.bundle_using_version != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, value_RN_Download.bundle_using_version);
            }
            if (value_RN_Download.rn_version != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, value_RN_Download.rn_version);
            }
            if (value_RN_Download.download_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 5, value_RN_Download.download_time);
            }
            if (value_RN_Download.unzip_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 6, value_RN_Download.unzip_time);
            }
            if (value_RN_Download.patch_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 7, value_RN_Download.patch_time);
            }
            if (value_RN_Download.md5_time != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 8, value_RN_Download.md5_time);
            }
            if (value_RN_Download.session_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 9, value_RN_Download.session_id);
            }
            if (value_RN_Download.timestamp != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 10, value_RN_Download.timestamp);
            }
            if (value_RN_Download.code != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 11, value_RN_Download.code);
            }
            if (value_RN_Download.err_message != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 12, value_RN_Download.err_message);
            }
            protoWriter.writeBytes(value_RN_Download.unknownFields());
        }

        /* renamed from: a */
        public Value_RN_Download decode(ProtoReader protoReader) throws IOException {
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
                            builder.a(ProtoAdapter.INT64.decode(protoReader));
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
                            builder.e(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 10:
                            builder.e(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 11:
                            builder.a(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 12:
                            builder.f(ProtoAdapter.STRING.decode(protoReader));
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
        public Value_RN_Download redact(Value_RN_Download value_RN_Download) {
            Builder newBuilder = value_RN_Download.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
