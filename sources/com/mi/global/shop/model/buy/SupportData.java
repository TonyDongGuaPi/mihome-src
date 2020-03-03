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

public final class SupportData extends Message<SupportData, Builder> {
    public static final ProtoAdapter<SupportData> ADAPTER = new ProtoAdapter_SupportData();
    public static final Integer DEFAULT_CAN_COD = 0;
    public static final Integer DEFAULT_CAN_ONLINEPAY = 0;
    public static final String DEFAULT_CODSTATUS = "";
    public static final String DEFAULT_COD_MESSAGE = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer can_cod;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 2)
    public final Integer can_onlinepay;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String cod_message;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String codstatus;

    public SupportData(Integer num, Integer num2, String str, String str2) {
        this(num, num2, str, str2, ByteString.EMPTY);
    }

    public SupportData(Integer num, Integer num2, String str, String str2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.can_cod = num;
        this.can_onlinepay = num2;
        this.codstatus = str;
        this.cod_message = str2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.can_cod = this.can_cod;
        builder.can_onlinepay = this.can_onlinepay;
        builder.codstatus = this.codstatus;
        builder.cod_message = this.cod_message;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SupportData)) {
            return false;
        }
        SupportData supportData = (SupportData) obj;
        if (!Internal.equals(unknownFields(), supportData.unknownFields()) || !Internal.equals(this.can_cod, supportData.can_cod) || !Internal.equals(this.can_onlinepay, supportData.can_onlinepay) || !Internal.equals(this.codstatus, supportData.codstatus) || !Internal.equals(this.cod_message, supportData.cod_message)) {
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
        int hashCode = ((((((unknownFields().hashCode() * 37) + (this.can_cod != null ? this.can_cod.hashCode() : 0)) * 37) + (this.can_onlinepay != null ? this.can_onlinepay.hashCode() : 0)) * 37) + (this.codstatus != null ? this.codstatus.hashCode() : 0)) * 37;
        if (this.cod_message != null) {
            i2 = this.cod_message.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.can_cod != null) {
            sb.append(", can_cod=");
            sb.append(this.can_cod);
        }
        if (this.can_onlinepay != null) {
            sb.append(", can_onlinepay=");
            sb.append(this.can_onlinepay);
        }
        if (this.codstatus != null) {
            sb.append(", codstatus=");
            sb.append(this.codstatus);
        }
        if (this.cod_message != null) {
            sb.append(", cod_message=");
            sb.append(this.cod_message);
        }
        StringBuilder replace = sb.replace(0, 2, "SupportData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<SupportData, Builder> {
        public Integer can_cod;
        public Integer can_onlinepay;
        public String cod_message;
        public String codstatus;

        public Builder can_cod(Integer num) {
            this.can_cod = num;
            return this;
        }

        public Builder can_onlinepay(Integer num) {
            this.can_onlinepay = num;
            return this;
        }

        public Builder codstatus(String str) {
            this.codstatus = str;
            return this;
        }

        public Builder cod_message(String str) {
            this.cod_message = str;
            return this;
        }

        public SupportData build() {
            return new SupportData(this.can_cod, this.can_onlinepay, this.codstatus, this.cod_message, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_SupportData extends ProtoAdapter<SupportData> {
        ProtoAdapter_SupportData() {
            super(FieldEncoding.LENGTH_DELIMITED, SupportData.class);
        }

        public int encodedSize(SupportData supportData) {
            int i = 0;
            int encodedSizeWithTag = (supportData.can_cod != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, supportData.can_cod) : 0) + (supportData.can_onlinepay != null ? ProtoAdapter.INT32.encodedSizeWithTag(2, supportData.can_onlinepay) : 0) + (supportData.codstatus != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, supportData.codstatus) : 0);
            if (supportData.cod_message != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(4, supportData.cod_message);
            }
            return encodedSizeWithTag + i + supportData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, SupportData supportData) throws IOException {
            if (supportData.can_cod != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, supportData.can_cod);
            }
            if (supportData.can_onlinepay != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 2, supportData.can_onlinepay);
            }
            if (supportData.codstatus != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, supportData.codstatus);
            }
            if (supportData.cod_message != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, supportData.cod_message);
            }
            protoWriter.writeBytes(supportData.unknownFields());
        }

        public SupportData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.can_cod(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 2:
                            builder.can_onlinepay(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 3:
                            builder.codstatus(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.cod_message(ProtoAdapter.STRING.decode(protoReader));
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

        public SupportData redact(SupportData supportData) {
            Builder newBuilder = supportData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
