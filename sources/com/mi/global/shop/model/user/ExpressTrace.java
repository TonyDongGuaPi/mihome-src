package com.mi.global.shop.model.user;

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

public final class ExpressTrace extends Message<ExpressTrace, Builder> {
    public static final ProtoAdapter<ExpressTrace> ADAPTER = new ProtoAdapter_ExpressTrace();
    public static final String DEFAULT_CITY = "";
    public static final String DEFAULT_TIME = "";
    public static final String DEFAULT_TRACK = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String city;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String track;

    public ExpressTrace(String str, String str2, String str3) {
        this(str, str2, str3, ByteString.EMPTY);
    }

    public ExpressTrace(String str, String str2, String str3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.time = str;
        this.city = str2;
        this.track = str3;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.time = this.time;
        builder.city = this.city;
        builder.track = this.track;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExpressTrace)) {
            return false;
        }
        ExpressTrace expressTrace = (ExpressTrace) obj;
        if (!Internal.equals(unknownFields(), expressTrace.unknownFields()) || !Internal.equals(this.time, expressTrace.time) || !Internal.equals(this.city, expressTrace.city) || !Internal.equals(this.track, expressTrace.track)) {
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
        int hashCode = ((((unknownFields().hashCode() * 37) + (this.time != null ? this.time.hashCode() : 0)) * 37) + (this.city != null ? this.city.hashCode() : 0)) * 37;
        if (this.track != null) {
            i2 = this.track.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.time != null) {
            sb.append(", time=");
            sb.append(this.time);
        }
        if (this.city != null) {
            sb.append(", city=");
            sb.append(this.city);
        }
        if (this.track != null) {
            sb.append(", track=");
            sb.append(this.track);
        }
        StringBuilder replace = sb.replace(0, 2, "ExpressTrace{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<ExpressTrace, Builder> {
        public String city;
        public String time;
        public String track;

        public Builder time(String str) {
            this.time = str;
            return this;
        }

        public Builder city(String str) {
            this.city = str;
            return this;
        }

        public Builder track(String str) {
            this.track = str;
            return this;
        }

        public ExpressTrace build() {
            return new ExpressTrace(this.time, this.city, this.track, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_ExpressTrace extends ProtoAdapter<ExpressTrace> {
        ProtoAdapter_ExpressTrace() {
            super(FieldEncoding.LENGTH_DELIMITED, ExpressTrace.class);
        }

        public int encodedSize(ExpressTrace expressTrace) {
            int i = 0;
            int encodedSizeWithTag = (expressTrace.time != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, expressTrace.time) : 0) + (expressTrace.city != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, expressTrace.city) : 0);
            if (expressTrace.track != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(3, expressTrace.track);
            }
            return encodedSizeWithTag + i + expressTrace.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, ExpressTrace expressTrace) throws IOException {
            if (expressTrace.time != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, expressTrace.time);
            }
            if (expressTrace.city != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, expressTrace.city);
            }
            if (expressTrace.track != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, expressTrace.track);
            }
            protoWriter.writeBytes(expressTrace.unknownFields());
        }

        public ExpressTrace decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.time(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.city(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.track(ProtoAdapter.STRING.decode(protoReader));
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

        public ExpressTrace redact(ExpressTrace expressTrace) {
            Builder newBuilder = expressTrace.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
