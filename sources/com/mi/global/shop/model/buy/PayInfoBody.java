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

public final class PayInfoBody extends Message<PayInfoBody, Builder> {
    public static final ProtoAdapter<PayInfoBody> ADAPTER = new ProtoAdapter_PayInfoBody();
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.buy.PayInfoData#ADAPTER", tag = 3)
    public final PayInfoData Data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String Errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer Errno;

    public PayInfoBody(Integer num, String str, PayInfoData payInfoData) {
        this(num, str, payInfoData, ByteString.EMPTY);
    }

    public PayInfoBody(Integer num, String str, PayInfoData payInfoData, ByteString byteString) {
        super(ADAPTER, byteString);
        this.Errno = num;
        this.Errmsg = str;
        this.Data = payInfoData;
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
        if (!(obj instanceof PayInfoBody)) {
            return false;
        }
        PayInfoBody payInfoBody = (PayInfoBody) obj;
        if (!Internal.equals(unknownFields(), payInfoBody.unknownFields()) || !Internal.equals(this.Errno, payInfoBody.Errno) || !Internal.equals(this.Errmsg, payInfoBody.Errmsg) || !Internal.equals(this.Data, payInfoBody.Data)) {
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
        StringBuilder replace = sb.replace(0, 2, "PayInfoBody{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<PayInfoBody, Builder> {
        public PayInfoData Data;
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

        public Builder Data(PayInfoData payInfoData) {
            this.Data = payInfoData;
            return this;
        }

        public PayInfoBody build() {
            return new PayInfoBody(this.Errno, this.Errmsg, this.Data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_PayInfoBody extends ProtoAdapter<PayInfoBody> {
        ProtoAdapter_PayInfoBody() {
            super(FieldEncoding.LENGTH_DELIMITED, PayInfoBody.class);
        }

        public int encodedSize(PayInfoBody payInfoBody) {
            int i = 0;
            int encodedSizeWithTag = (payInfoBody.Errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, payInfoBody.Errno) : 0) + (payInfoBody.Errmsg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, payInfoBody.Errmsg) : 0);
            if (payInfoBody.Data != null) {
                i = PayInfoData.ADAPTER.encodedSizeWithTag(3, payInfoBody.Data);
            }
            return encodedSizeWithTag + i + payInfoBody.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, PayInfoBody payInfoBody) throws IOException {
            if (payInfoBody.Errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, payInfoBody.Errno);
            }
            if (payInfoBody.Errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, payInfoBody.Errmsg);
            }
            if (payInfoBody.Data != null) {
                PayInfoData.ADAPTER.encodeWithTag(protoWriter, 3, payInfoBody.Data);
            }
            protoWriter.writeBytes(payInfoBody.unknownFields());
        }

        public PayInfoBody decode(ProtoReader protoReader) throws IOException {
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
                            builder.Data(PayInfoData.ADAPTER.decode(protoReader));
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

        public PayInfoBody redact(PayInfoBody payInfoBody) {
            Builder newBuilder = payInfoBody.newBuilder();
            if (newBuilder.Data != null) {
                newBuilder.Data = PayInfoData.ADAPTER.redact(newBuilder.Data);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
