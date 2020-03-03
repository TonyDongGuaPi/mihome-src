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

public final class PaymentData extends Message<PaymentData, Builder> {
    public static final ProtoAdapter<PaymentData> ADAPTER = new ProtoAdapter_PaymentData();
    public static final String DEFAULT_BRIEF = "";
    public static final Boolean DEFAULT_CHECKED = false;
    public static final String DEFAULT_PAY_ID = "";
    public static final String DEFAULT_TPIS = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String brief;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 3)
    public final Boolean checked;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String pay_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String tpis;

    public PaymentData(String str, String str2, Boolean bool, String str3) {
        this(str, str2, bool, str3, ByteString.EMPTY);
    }

    public PaymentData(String str, String str2, Boolean bool, String str3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.brief = str;
        this.pay_id = str2;
        this.checked = bool;
        this.tpis = str3;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.brief = this.brief;
        builder.pay_id = this.pay_id;
        builder.checked = this.checked;
        builder.tpis = this.tpis;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PaymentData)) {
            return false;
        }
        PaymentData paymentData = (PaymentData) obj;
        if (!Internal.equals(unknownFields(), paymentData.unknownFields()) || !Internal.equals(this.brief, paymentData.brief) || !Internal.equals(this.pay_id, paymentData.pay_id) || !Internal.equals(this.checked, paymentData.checked) || !Internal.equals(this.tpis, paymentData.tpis)) {
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
        int hashCode = ((((((unknownFields().hashCode() * 37) + (this.brief != null ? this.brief.hashCode() : 0)) * 37) + (this.pay_id != null ? this.pay_id.hashCode() : 0)) * 37) + (this.checked != null ? this.checked.hashCode() : 0)) * 37;
        if (this.tpis != null) {
            i2 = this.tpis.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.brief != null) {
            sb.append(", brief=");
            sb.append(this.brief);
        }
        if (this.pay_id != null) {
            sb.append(", pay_id=");
            sb.append(this.pay_id);
        }
        if (this.checked != null) {
            sb.append(", checked=");
            sb.append(this.checked);
        }
        if (this.tpis != null) {
            sb.append(", tpis=");
            sb.append(this.tpis);
        }
        StringBuilder replace = sb.replace(0, 2, "PaymentData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<PaymentData, Builder> {
        public String brief;
        public Boolean checked;
        public String pay_id;
        public String tpis;

        public Builder brief(String str) {
            this.brief = str;
            return this;
        }

        public Builder pay_id(String str) {
            this.pay_id = str;
            return this;
        }

        public Builder checked(Boolean bool) {
            this.checked = bool;
            return this;
        }

        public Builder tpis(String str) {
            this.tpis = str;
            return this;
        }

        public PaymentData build() {
            return new PaymentData(this.brief, this.pay_id, this.checked, this.tpis, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_PaymentData extends ProtoAdapter<PaymentData> {
        ProtoAdapter_PaymentData() {
            super(FieldEncoding.LENGTH_DELIMITED, PaymentData.class);
        }

        public int encodedSize(PaymentData paymentData) {
            int i = 0;
            int encodedSizeWithTag = (paymentData.brief != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, paymentData.brief) : 0) + (paymentData.pay_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, paymentData.pay_id) : 0) + (paymentData.checked != null ? ProtoAdapter.BOOL.encodedSizeWithTag(3, paymentData.checked) : 0);
            if (paymentData.tpis != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(4, paymentData.tpis);
            }
            return encodedSizeWithTag + i + paymentData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, PaymentData paymentData) throws IOException {
            if (paymentData.brief != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, paymentData.brief);
            }
            if (paymentData.pay_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, paymentData.pay_id);
            }
            if (paymentData.checked != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 3, paymentData.checked);
            }
            if (paymentData.tpis != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, paymentData.tpis);
            }
            protoWriter.writeBytes(paymentData.unknownFields());
        }

        public PaymentData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.brief(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.pay_id(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.checked(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 4:
                            builder.tpis(ProtoAdapter.STRING.decode(protoReader));
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

        public PaymentData redact(PaymentData paymentData) {
            Builder newBuilder = paymentData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
