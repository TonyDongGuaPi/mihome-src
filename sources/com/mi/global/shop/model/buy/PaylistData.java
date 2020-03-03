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

public final class PaylistData extends Message<PaylistData, Builder> {
    public static final ProtoAdapter<PaylistData> ADAPTER = new ProtoAdapter_PaylistData();
    public static final String DEFAULT_BRIEF = "";
    public static final Boolean DEFAULT_CHECKED = false;
    public static final String DEFAULT_PAY_ID = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String brief;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 3)
    public final Boolean checked;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String pay_id;

    public PaylistData(String str, String str2, Boolean bool) {
        this(str, str2, bool, ByteString.EMPTY);
    }

    public PaylistData(String str, String str2, Boolean bool, ByteString byteString) {
        super(ADAPTER, byteString);
        this.brief = str;
        this.pay_id = str2;
        this.checked = bool;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.brief = this.brief;
        builder.pay_id = this.pay_id;
        builder.checked = this.checked;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PaylistData)) {
            return false;
        }
        PaylistData paylistData = (PaylistData) obj;
        if (!Internal.equals(unknownFields(), paylistData.unknownFields()) || !Internal.equals(this.brief, paylistData.brief) || !Internal.equals(this.pay_id, paylistData.pay_id) || !Internal.equals(this.checked, paylistData.checked)) {
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
        int hashCode = ((((unknownFields().hashCode() * 37) + (this.brief != null ? this.brief.hashCode() : 0)) * 37) + (this.pay_id != null ? this.pay_id.hashCode() : 0)) * 37;
        if (this.checked != null) {
            i2 = this.checked.hashCode();
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
        StringBuilder replace = sb.replace(0, 2, "PaylistData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<PaylistData, Builder> {
        public String brief;
        public Boolean checked;
        public String pay_id;

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

        public PaylistData build() {
            return new PaylistData(this.brief, this.pay_id, this.checked, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_PaylistData extends ProtoAdapter<PaylistData> {
        ProtoAdapter_PaylistData() {
            super(FieldEncoding.LENGTH_DELIMITED, PaylistData.class);
        }

        public int encodedSize(PaylistData paylistData) {
            int i = 0;
            int encodedSizeWithTag = (paylistData.brief != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, paylistData.brief) : 0) + (paylistData.pay_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, paylistData.pay_id) : 0);
            if (paylistData.checked != null) {
                i = ProtoAdapter.BOOL.encodedSizeWithTag(3, paylistData.checked);
            }
            return encodedSizeWithTag + i + paylistData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, PaylistData paylistData) throws IOException {
            if (paylistData.brief != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, paylistData.brief);
            }
            if (paylistData.pay_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, paylistData.pay_id);
            }
            if (paylistData.checked != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 3, paylistData.checked);
            }
            protoWriter.writeBytes(paylistData.unknownFields());
        }

        public PaylistData decode(ProtoReader protoReader) throws IOException {
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

        public PaylistData redact(PaylistData paylistData) {
            Builder newBuilder = paylistData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
