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

public final class PaygoBody extends Message<PaygoBody, Builder> {
    public static final ProtoAdapter<PaygoBody> ADAPTER = new ProtoAdapter_PaygoBody();
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.buy.PaygoData#ADAPTER", tag = 3)
    public final PaygoData Data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String Errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer Errno;

    public PaygoBody(Integer num, String str, PaygoData paygoData) {
        this(num, str, paygoData, ByteString.EMPTY);
    }

    public PaygoBody(Integer num, String str, PaygoData paygoData, ByteString byteString) {
        super(ADAPTER, byteString);
        this.Errno = num;
        this.Errmsg = str;
        this.Data = paygoData;
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
        if (!(obj instanceof PaygoBody)) {
            return false;
        }
        PaygoBody paygoBody = (PaygoBody) obj;
        if (!Internal.equals(unknownFields(), paygoBody.unknownFields()) || !Internal.equals(this.Errno, paygoBody.Errno) || !Internal.equals(this.Errmsg, paygoBody.Errmsg) || !Internal.equals(this.Data, paygoBody.Data)) {
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
        StringBuilder replace = sb.replace(0, 2, "PaygoBody{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<PaygoBody, Builder> {
        public PaygoData Data;
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

        public Builder Data(PaygoData paygoData) {
            this.Data = paygoData;
            return this;
        }

        public PaygoBody build() {
            return new PaygoBody(this.Errno, this.Errmsg, this.Data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_PaygoBody extends ProtoAdapter<PaygoBody> {
        ProtoAdapter_PaygoBody() {
            super(FieldEncoding.LENGTH_DELIMITED, PaygoBody.class);
        }

        public int encodedSize(PaygoBody paygoBody) {
            int i = 0;
            int encodedSizeWithTag = (paygoBody.Errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, paygoBody.Errno) : 0) + (paygoBody.Errmsg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, paygoBody.Errmsg) : 0);
            if (paygoBody.Data != null) {
                i = PaygoData.ADAPTER.encodedSizeWithTag(3, paygoBody.Data);
            }
            return encodedSizeWithTag + i + paygoBody.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, PaygoBody paygoBody) throws IOException {
            if (paygoBody.Errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, paygoBody.Errno);
            }
            if (paygoBody.Errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, paygoBody.Errmsg);
            }
            if (paygoBody.Data != null) {
                PaygoData.ADAPTER.encodeWithTag(protoWriter, 3, paygoBody.Data);
            }
            protoWriter.writeBytes(paygoBody.unknownFields());
        }

        public PaygoBody decode(ProtoReader protoReader) throws IOException {
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
                            builder.Data(PaygoData.ADAPTER.decode(protoReader));
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

        public PaygoBody redact(PaygoBody paygoBody) {
            Builder newBuilder = paygoBody.newBuilder();
            if (newBuilder.Data != null) {
                newBuilder.Data = PaygoData.ADAPTER.redact(newBuilder.Data);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
