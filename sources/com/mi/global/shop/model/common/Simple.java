package com.mi.global.shop.model.common;

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

public final class Simple extends Message<Simple, Builder> {
    public static final ProtoAdapter<Simple> ADAPTER = new ProtoAdapter_Simple();
    public static final String DEFAULT_DATA = "";
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer errno;

    public Simple(Integer num, String str, String str2) {
        this(num, str, str2, ByteString.EMPTY);
    }

    public Simple(Integer num, String str, String str2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.errno = num;
        this.errmsg = str;
        this.data = str2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.errno = this.errno;
        builder.errmsg = this.errmsg;
        builder.data = this.data;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Simple)) {
            return false;
        }
        Simple simple = (Simple) obj;
        if (!Internal.equals(unknownFields(), simple.unknownFields()) || !Internal.equals(this.errno, simple.errno) || !Internal.equals(this.errmsg, simple.errmsg) || !Internal.equals(this.data, simple.data)) {
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
        int hashCode = ((((unknownFields().hashCode() * 37) + (this.errno != null ? this.errno.hashCode() : 0)) * 37) + (this.errmsg != null ? this.errmsg.hashCode() : 0)) * 37;
        if (this.data != null) {
            i2 = this.data.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.errno != null) {
            sb.append(", errno=");
            sb.append(this.errno);
        }
        if (this.errmsg != null) {
            sb.append(", errmsg=");
            sb.append(this.errmsg);
        }
        if (this.data != null) {
            sb.append(", data=");
            sb.append(this.data);
        }
        StringBuilder replace = sb.replace(0, 2, "Simple{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Simple, Builder> {
        public String data;
        public String errmsg;
        public Integer errno;

        public Builder errno(Integer num) {
            this.errno = num;
            return this;
        }

        public Builder errmsg(String str) {
            this.errmsg = str;
            return this;
        }

        public Builder data(String str) {
            this.data = str;
            return this;
        }

        public Simple build() {
            return new Simple(this.errno, this.errmsg, this.data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Simple extends ProtoAdapter<Simple> {
        ProtoAdapter_Simple() {
            super(FieldEncoding.LENGTH_DELIMITED, Simple.class);
        }

        public int encodedSize(Simple simple) {
            int i = 0;
            int encodedSizeWithTag = (simple.errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, simple.errno) : 0) + (simple.errmsg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, simple.errmsg) : 0);
            if (simple.data != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(3, simple.data);
            }
            return encodedSizeWithTag + i + simple.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, Simple simple) throws IOException {
            if (simple.errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, simple.errno);
            }
            if (simple.errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, simple.errmsg);
            }
            if (simple.data != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, simple.data);
            }
            protoWriter.writeBytes(simple.unknownFields());
        }

        public Simple decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.errno(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 2:
                            builder.errmsg(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.data(ProtoAdapter.STRING.decode(protoReader));
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

        public Simple redact(Simple simple) {
            Builder newBuilder = simple.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
