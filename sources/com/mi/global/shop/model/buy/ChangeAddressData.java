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

public final class ChangeAddressData extends Message<ChangeAddressData, Builder> {
    public static final ProtoAdapter<ChangeAddressData> ADAPTER = new ProtoAdapter_ChangeAddressData();
    public static final String DEFAULT_CODTEXT = "";
    public static final Boolean DEFAULT_ISCOS = false;
    public static final String DEFAULT_PRODUCTTEXT = "";
    public static final Boolean DEFAULT_VALID = false;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String codtext;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 4)
    public final Boolean isCos;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String producttext;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 1)
    public final Boolean valid;

    public ChangeAddressData(Boolean bool, String str, String str2, Boolean bool2) {
        this(bool, str, str2, bool2, ByteString.EMPTY);
    }

    public ChangeAddressData(Boolean bool, String str, String str2, Boolean bool2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.valid = bool;
        this.codtext = str;
        this.producttext = str2;
        this.isCos = bool2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.valid = this.valid;
        builder.codtext = this.codtext;
        builder.producttext = this.producttext;
        builder.isCos = this.isCos;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ChangeAddressData)) {
            return false;
        }
        ChangeAddressData changeAddressData = (ChangeAddressData) obj;
        if (!Internal.equals(unknownFields(), changeAddressData.unknownFields()) || !Internal.equals(this.valid, changeAddressData.valid) || !Internal.equals(this.codtext, changeAddressData.codtext) || !Internal.equals(this.producttext, changeAddressData.producttext) || !Internal.equals(this.isCos, changeAddressData.isCos)) {
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
        int hashCode = ((((((unknownFields().hashCode() * 37) + (this.valid != null ? this.valid.hashCode() : 0)) * 37) + (this.codtext != null ? this.codtext.hashCode() : 0)) * 37) + (this.producttext != null ? this.producttext.hashCode() : 0)) * 37;
        if (this.isCos != null) {
            i2 = this.isCos.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.valid != null) {
            sb.append(", valid=");
            sb.append(this.valid);
        }
        if (this.codtext != null) {
            sb.append(", codtext=");
            sb.append(this.codtext);
        }
        if (this.producttext != null) {
            sb.append(", producttext=");
            sb.append(this.producttext);
        }
        if (this.isCos != null) {
            sb.append(", isCos=");
            sb.append(this.isCos);
        }
        StringBuilder replace = sb.replace(0, 2, "ChangeAddressData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<ChangeAddressData, Builder> {
        public String codtext;
        public Boolean isCos;
        public String producttext;
        public Boolean valid;

        public Builder valid(Boolean bool) {
            this.valid = bool;
            return this;
        }

        public Builder codtext(String str) {
            this.codtext = str;
            return this;
        }

        public Builder producttext(String str) {
            this.producttext = str;
            return this;
        }

        public Builder isCos(Boolean bool) {
            this.isCos = bool;
            return this;
        }

        public ChangeAddressData build() {
            return new ChangeAddressData(this.valid, this.codtext, this.producttext, this.isCos, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_ChangeAddressData extends ProtoAdapter<ChangeAddressData> {
        ProtoAdapter_ChangeAddressData() {
            super(FieldEncoding.LENGTH_DELIMITED, ChangeAddressData.class);
        }

        public int encodedSize(ChangeAddressData changeAddressData) {
            int i = 0;
            int encodedSizeWithTag = (changeAddressData.valid != null ? ProtoAdapter.BOOL.encodedSizeWithTag(1, changeAddressData.valid) : 0) + (changeAddressData.codtext != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, changeAddressData.codtext) : 0) + (changeAddressData.producttext != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, changeAddressData.producttext) : 0);
            if (changeAddressData.isCos != null) {
                i = ProtoAdapter.BOOL.encodedSizeWithTag(4, changeAddressData.isCos);
            }
            return encodedSizeWithTag + i + changeAddressData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, ChangeAddressData changeAddressData) throws IOException {
            if (changeAddressData.valid != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 1, changeAddressData.valid);
            }
            if (changeAddressData.codtext != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, changeAddressData.codtext);
            }
            if (changeAddressData.producttext != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, changeAddressData.producttext);
            }
            if (changeAddressData.isCos != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 4, changeAddressData.isCos);
            }
            protoWriter.writeBytes(changeAddressData.unknownFields());
        }

        public ChangeAddressData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.valid(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 2:
                            builder.codtext(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.producttext(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.isCos(ProtoAdapter.BOOL.decode(protoReader));
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

        public ChangeAddressData redact(ChangeAddressData changeAddressData) {
            Builder newBuilder = changeAddressData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
