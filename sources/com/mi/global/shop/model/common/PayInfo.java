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

public final class PayInfo extends Message<PayInfo, Builder> {
    public static final ProtoAdapter<PayInfo> ADAPTER = new ProtoAdapter_PayInfo();
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.common.PayList#ADAPTER", tag = 1)
    public final PayList payList;
    @WireField(adapter = "com.mi.global.shop.model.common.PayParam#ADAPTER", tag = 2)
    public final PayParam payParam;

    public PayInfo(PayList payList2, PayParam payParam2) {
        this(payList2, payParam2, ByteString.EMPTY);
    }

    public PayInfo(PayList payList2, PayParam payParam2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.payList = payList2;
        this.payParam = payParam2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.payList = this.payList;
        builder.payParam = this.payParam;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PayInfo)) {
            return false;
        }
        PayInfo payInfo = (PayInfo) obj;
        if (!Internal.equals(unknownFields(), payInfo.unknownFields()) || !Internal.equals(this.payList, payInfo.payList) || !Internal.equals(this.payParam, payInfo.payParam)) {
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
        int hashCode = ((unknownFields().hashCode() * 37) + (this.payList != null ? this.payList.hashCode() : 0)) * 37;
        if (this.payParam != null) {
            i2 = this.payParam.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.payList != null) {
            sb.append(", payList=");
            sb.append(this.payList);
        }
        if (this.payParam != null) {
            sb.append(", payParam=");
            sb.append(this.payParam);
        }
        StringBuilder replace = sb.replace(0, 2, "PayInfo{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<PayInfo, Builder> {
        public PayList payList;
        public PayParam payParam;

        public Builder payList(PayList payList2) {
            this.payList = payList2;
            return this;
        }

        public Builder payParam(PayParam payParam2) {
            this.payParam = payParam2;
            return this;
        }

        public PayInfo build() {
            return new PayInfo(this.payList, this.payParam, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_PayInfo extends ProtoAdapter<PayInfo> {
        ProtoAdapter_PayInfo() {
            super(FieldEncoding.LENGTH_DELIMITED, PayInfo.class);
        }

        public int encodedSize(PayInfo payInfo) {
            int i = 0;
            int encodedSizeWithTag = payInfo.payList != null ? PayList.ADAPTER.encodedSizeWithTag(1, payInfo.payList) : 0;
            if (payInfo.payParam != null) {
                i = PayParam.ADAPTER.encodedSizeWithTag(2, payInfo.payParam);
            }
            return encodedSizeWithTag + i + payInfo.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, PayInfo payInfo) throws IOException {
            if (payInfo.payList != null) {
                PayList.ADAPTER.encodeWithTag(protoWriter, 1, payInfo.payList);
            }
            if (payInfo.payParam != null) {
                PayParam.ADAPTER.encodeWithTag(protoWriter, 2, payInfo.payParam);
            }
            protoWriter.writeBytes(payInfo.unknownFields());
        }

        public PayInfo decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.payList(PayList.ADAPTER.decode(protoReader));
                            break;
                        case 2:
                            builder.payParam(PayParam.ADAPTER.decode(protoReader));
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

        public PayInfo redact(PayInfo payInfo) {
            Builder newBuilder = payInfo.newBuilder();
            if (newBuilder.payList != null) {
                newBuilder.payList = PayList.ADAPTER.redact(newBuilder.payList);
            }
            if (newBuilder.payParam != null) {
                newBuilder.payParam = PayParam.ADAPTER.redact(newBuilder.payParam);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
