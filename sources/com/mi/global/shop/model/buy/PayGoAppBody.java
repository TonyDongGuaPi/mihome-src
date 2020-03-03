package com.mi.global.shop.model.buy;

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

public final class PayGoAppBody extends Message<PayGoAppBody, Builder> {
    public static final ProtoAdapter<PayGoAppBody> ADAPTER = new ProtoAdapter_PayGoAppBody();
    public static final String DEFAULT_DATA = "";
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String Data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String Errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer Errno;

    public PayGoAppBody(Integer num, String str, String str2) {
        this(num, str, str2, ByteString.EMPTY);
    }

    public PayGoAppBody(Integer num, String str, String str2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.Errno = num;
        this.Errmsg = str;
        this.Data = str2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.Errno = this.Errno;
        builder.Errmsg = this.Errmsg;
        builder.Data = this.Data;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PayGoAppBody)) {
            return false;
        }
        PayGoAppBody payGoAppBody = (PayGoAppBody) obj;
        if (!Internal.equals(unknownFields(), payGoAppBody.unknownFields()) || !Internal.equals(this.Errno, payGoAppBody.Errno) || !Internal.equals(this.Errmsg, payGoAppBody.Errmsg) || !Internal.equals(this.Data, payGoAppBody.Data)) {
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
        int hashCode = ((((unknownFields().hashCode() * 37) + (this.Errno != null ? this.Errno.hashCode() : 0)) * 37) + (this.Errmsg != null ? this.Errmsg.hashCode() : 0)) * 37;
        if (this.Data != null) {
            i2 = this.Data.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.Errno != null) {
            sb.append(", Errno=");
            sb.append(this.Errno);
        }
        if (this.Errmsg != null) {
            sb.append(", Errmsg=");
            sb.append(this.Errmsg);
        }
        if (this.Data != null) {
            sb.append(", Data=");
            sb.append(this.Data);
        }
        StringBuilder replace = sb.replace(0, 2, "PayGoAppBody{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<PayGoAppBody, Builder> {
        public String Data;
        public String Errmsg;
        public Integer Errno;

        public Builder Errno(Integer num) {
            this.Errno = num;
            return this;
        }

        public Builder Errmsg(String str) {
            this.Errmsg = str;
            return this;
        }

        public Builder Data(String str) {
            this.Data = str;
            return this;
        }

        public PayGoAppBody build() {
            return new PayGoAppBody(this.Errno, this.Errmsg, this.Data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_PayGoAppBody extends ProtoAdapter<PayGoAppBody> {
        ProtoAdapter_PayGoAppBody() {
            super(FieldEncoding.LENGTH_DELIMITED, PayGoAppBody.class);
        }

        public int encodedSize(PayGoAppBody payGoAppBody) {
            int i = 0;
            int encodedSizeWithTag = (payGoAppBody.Errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, payGoAppBody.Errno) : 0) + (payGoAppBody.Errmsg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, payGoAppBody.Errmsg) : 0);
            if (payGoAppBody.Data != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(3, payGoAppBody.Data);
            }
            return encodedSizeWithTag + i + payGoAppBody.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, PayGoAppBody payGoAppBody) throws IOException {
            if (payGoAppBody.Errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, payGoAppBody.Errno);
            }
            if (payGoAppBody.Errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, payGoAppBody.Errmsg);
            }
            if (payGoAppBody.Data != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, payGoAppBody.Data);
            }
            protoWriter.writeBytes(payGoAppBody.unknownFields());
        }

        public PayGoAppBody decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.Errno(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 2:
                            builder.Errmsg(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.Data(ProtoAdapter.STRING.decode(protoReader));
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

        public PayGoAppBody redact(PayGoAppBody payGoAppBody) {
            Builder newBuilder = payGoAppBody.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
