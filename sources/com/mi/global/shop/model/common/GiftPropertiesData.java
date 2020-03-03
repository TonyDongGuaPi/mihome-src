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

public final class GiftPropertiesData extends Message<GiftPropertiesData, Builder> {
    public static final ProtoAdapter<GiftPropertiesData> ADAPTER = new ProtoAdapter_GiftPropertiesData();
    public static final String DEFAULT_ACTID = "";
    public static final String DEFAULT_BENEFITID = "";
    public static final String DEFAULT_PARENT_ITEMID = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String actId;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String benefitId;
    @WireField(adapter = "com.mi.global.shop.model.common.InsuranceData#ADAPTER", tag = 4)
    public final InsuranceData insurance;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String parent_itemId;

    public GiftPropertiesData(String str, String str2, String str3, InsuranceData insuranceData) {
        this(str, str2, str3, insuranceData, ByteString.EMPTY);
    }

    public GiftPropertiesData(String str, String str2, String str3, InsuranceData insuranceData, ByteString byteString) {
        super(ADAPTER, byteString);
        this.actId = str;
        this.benefitId = str2;
        this.parent_itemId = str3;
        this.insurance = insuranceData;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.actId = this.actId;
        builder.benefitId = this.benefitId;
        builder.parent_itemId = this.parent_itemId;
        builder.insurance = this.insurance;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GiftPropertiesData)) {
            return false;
        }
        GiftPropertiesData giftPropertiesData = (GiftPropertiesData) obj;
        if (!Internal.equals(unknownFields(), giftPropertiesData.unknownFields()) || !Internal.equals(this.actId, giftPropertiesData.actId) || !Internal.equals(this.benefitId, giftPropertiesData.benefitId) || !Internal.equals(this.parent_itemId, giftPropertiesData.parent_itemId) || !Internal.equals(this.insurance, giftPropertiesData.insurance)) {
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
        int hashCode = ((((((unknownFields().hashCode() * 37) + (this.actId != null ? this.actId.hashCode() : 0)) * 37) + (this.benefitId != null ? this.benefitId.hashCode() : 0)) * 37) + (this.parent_itemId != null ? this.parent_itemId.hashCode() : 0)) * 37;
        if (this.insurance != null) {
            i2 = this.insurance.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.actId != null) {
            sb.append(", actId=");
            sb.append(this.actId);
        }
        if (this.benefitId != null) {
            sb.append(", benefitId=");
            sb.append(this.benefitId);
        }
        if (this.parent_itemId != null) {
            sb.append(", parent_itemId=");
            sb.append(this.parent_itemId);
        }
        if (this.insurance != null) {
            sb.append(", insurance=");
            sb.append(this.insurance);
        }
        StringBuilder replace = sb.replace(0, 2, "GiftPropertiesData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<GiftPropertiesData, Builder> {
        public String actId;
        public String benefitId;
        public InsuranceData insurance;
        public String parent_itemId;

        public Builder actId(String str) {
            this.actId = str;
            return this;
        }

        public Builder benefitId(String str) {
            this.benefitId = str;
            return this;
        }

        public Builder parent_itemId(String str) {
            this.parent_itemId = str;
            return this;
        }

        public Builder insurance(InsuranceData insuranceData) {
            this.insurance = insuranceData;
            return this;
        }

        public GiftPropertiesData build() {
            return new GiftPropertiesData(this.actId, this.benefitId, this.parent_itemId, this.insurance, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_GiftPropertiesData extends ProtoAdapter<GiftPropertiesData> {
        ProtoAdapter_GiftPropertiesData() {
            super(FieldEncoding.LENGTH_DELIMITED, GiftPropertiesData.class);
        }

        public int encodedSize(GiftPropertiesData giftPropertiesData) {
            int i = 0;
            int encodedSizeWithTag = (giftPropertiesData.actId != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, giftPropertiesData.actId) : 0) + (giftPropertiesData.benefitId != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, giftPropertiesData.benefitId) : 0) + (giftPropertiesData.parent_itemId != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, giftPropertiesData.parent_itemId) : 0);
            if (giftPropertiesData.insurance != null) {
                i = InsuranceData.ADAPTER.encodedSizeWithTag(4, giftPropertiesData.insurance);
            }
            return encodedSizeWithTag + i + giftPropertiesData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, GiftPropertiesData giftPropertiesData) throws IOException {
            if (giftPropertiesData.actId != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, giftPropertiesData.actId);
            }
            if (giftPropertiesData.benefitId != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, giftPropertiesData.benefitId);
            }
            if (giftPropertiesData.parent_itemId != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, giftPropertiesData.parent_itemId);
            }
            if (giftPropertiesData.insurance != null) {
                InsuranceData.ADAPTER.encodeWithTag(protoWriter, 4, giftPropertiesData.insurance);
            }
            protoWriter.writeBytes(giftPropertiesData.unknownFields());
        }

        public GiftPropertiesData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.actId(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.benefitId(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.parent_itemId(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.insurance(InsuranceData.ADAPTER.decode(protoReader));
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

        public GiftPropertiesData redact(GiftPropertiesData giftPropertiesData) {
            Builder newBuilder = giftPropertiesData.newBuilder();
            if (newBuilder.insurance != null) {
                newBuilder.insurance = InsuranceData.ADAPTER.redact(newBuilder.insurance);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
