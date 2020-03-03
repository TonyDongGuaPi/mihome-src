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

public final class PayWalletBody extends Message<PayWalletBody, Builder> {
    public static final ProtoAdapter<PayWalletBody> ADAPTER = new ProtoAdapter_PayWalletBody();
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.buy.PayWalletData#ADAPTER", tag = 3)
    public final PayWalletData Data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String Errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer Errno;

    public PayWalletBody(Integer num, String str, PayWalletData payWalletData) {
        this(num, str, payWalletData, ByteString.EMPTY);
    }

    public PayWalletBody(Integer num, String str, PayWalletData payWalletData, ByteString byteString) {
        super(ADAPTER, byteString);
        this.Errno = num;
        this.Errmsg = str;
        this.Data = payWalletData;
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
        if (!(obj instanceof PayWalletBody)) {
            return false;
        }
        PayWalletBody payWalletBody = (PayWalletBody) obj;
        if (!Internal.equals(unknownFields(), payWalletBody.unknownFields()) || !Internal.equals(this.Errno, payWalletBody.Errno) || !Internal.equals(this.Errmsg, payWalletBody.Errmsg) || !Internal.equals(this.Data, payWalletBody.Data)) {
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
        StringBuilder replace = sb.replace(0, 2, "PayWalletBody{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<PayWalletBody, Builder> {
        public PayWalletData Data;
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

        public Builder Data(PayWalletData payWalletData) {
            this.Data = payWalletData;
            return this;
        }

        public PayWalletBody build() {
            return new PayWalletBody(this.Errno, this.Errmsg, this.Data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_PayWalletBody extends ProtoAdapter<PayWalletBody> {
        ProtoAdapter_PayWalletBody() {
            super(FieldEncoding.LENGTH_DELIMITED, PayWalletBody.class);
        }

        public int encodedSize(PayWalletBody payWalletBody) {
            int i = 0;
            int encodedSizeWithTag = (payWalletBody.Errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, payWalletBody.Errno) : 0) + (payWalletBody.Errmsg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, payWalletBody.Errmsg) : 0);
            if (payWalletBody.Data != null) {
                i = PayWalletData.ADAPTER.encodedSizeWithTag(3, payWalletBody.Data);
            }
            return encodedSizeWithTag + i + payWalletBody.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, PayWalletBody payWalletBody) throws IOException {
            if (payWalletBody.Errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, payWalletBody.Errno);
            }
            if (payWalletBody.Errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, payWalletBody.Errmsg);
            }
            if (payWalletBody.Data != null) {
                PayWalletData.ADAPTER.encodeWithTag(protoWriter, 3, payWalletBody.Data);
            }
            protoWriter.writeBytes(payWalletBody.unknownFields());
        }

        public PayWalletBody decode(ProtoReader protoReader) throws IOException {
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
                            builder.Data(PayWalletData.ADAPTER.decode(protoReader));
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

        public PayWalletBody redact(PayWalletBody payWalletBody) {
            Builder newBuilder = payWalletBody.newBuilder();
            if (newBuilder.Data != null) {
                newBuilder.Data = PayWalletData.ADAPTER.redact(newBuilder.Data);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
