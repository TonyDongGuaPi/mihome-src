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

public final class PropertiesData extends Message<PropertiesData, Builder> {
    public static final ProtoAdapter<PropertiesData> ADAPTER = new ProtoAdapter_PropertiesData();
    public static final String DEFAULT_ACTDATE = "";
    public static final String DEFAULT_PARENT_ITEMID = "";
    public static final String DEFAULT_SOURCE = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String actDate;
    @WireField(adapter = "com.mi.global.shop.model.common.InsuranceData#ADAPTER", tag = 4)
    public final InsuranceData insurance;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String parent_itemId;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String source;

    public PropertiesData(String str, String str2, String str3, InsuranceData insuranceData) {
        this(str, str2, str3, insuranceData, ByteString.EMPTY);
    }

    public PropertiesData(String str, String str2, String str3, InsuranceData insuranceData, ByteString byteString) {
        super(ADAPTER, byteString);
        this.parent_itemId = str;
        this.source = str2;
        this.actDate = str3;
        this.insurance = insuranceData;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.parent_itemId = this.parent_itemId;
        builder.source = this.source;
        builder.actDate = this.actDate;
        builder.insurance = this.insurance;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PropertiesData)) {
            return false;
        }
        PropertiesData propertiesData = (PropertiesData) obj;
        if (!Internal.equals(unknownFields(), propertiesData.unknownFields()) || !Internal.equals(this.parent_itemId, propertiesData.parent_itemId) || !Internal.equals(this.source, propertiesData.source) || !Internal.equals(this.actDate, propertiesData.actDate) || !Internal.equals(this.insurance, propertiesData.insurance)) {
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
        int hashCode = ((((((unknownFields().hashCode() * 37) + (this.parent_itemId != null ? this.parent_itemId.hashCode() : 0)) * 37) + (this.source != null ? this.source.hashCode() : 0)) * 37) + (this.actDate != null ? this.actDate.hashCode() : 0)) * 37;
        if (this.insurance != null) {
            i2 = this.insurance.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.parent_itemId != null) {
            sb.append(", parent_itemId=");
            sb.append(this.parent_itemId);
        }
        if (this.source != null) {
            sb.append(", source=");
            sb.append(this.source);
        }
        if (this.actDate != null) {
            sb.append(", actDate=");
            sb.append(this.actDate);
        }
        if (this.insurance != null) {
            sb.append(", insurance=");
            sb.append(this.insurance);
        }
        StringBuilder replace = sb.replace(0, 2, "PropertiesData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<PropertiesData, Builder> {
        public String actDate;
        public InsuranceData insurance;
        public String parent_itemId;
        public String source;

        public Builder parent_itemId(String str) {
            this.parent_itemId = str;
            return this;
        }

        public Builder source(String str) {
            this.source = str;
            return this;
        }

        public Builder actDate(String str) {
            this.actDate = str;
            return this;
        }

        public Builder insurance(InsuranceData insuranceData) {
            this.insurance = insuranceData;
            return this;
        }

        public PropertiesData build() {
            return new PropertiesData(this.parent_itemId, this.source, this.actDate, this.insurance, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_PropertiesData extends ProtoAdapter<PropertiesData> {
        ProtoAdapter_PropertiesData() {
            super(FieldEncoding.LENGTH_DELIMITED, PropertiesData.class);
        }

        public int encodedSize(PropertiesData propertiesData) {
            int i = 0;
            int encodedSizeWithTag = (propertiesData.parent_itemId != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, propertiesData.parent_itemId) : 0) + (propertiesData.source != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, propertiesData.source) : 0) + (propertiesData.actDate != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, propertiesData.actDate) : 0);
            if (propertiesData.insurance != null) {
                i = InsuranceData.ADAPTER.encodedSizeWithTag(4, propertiesData.insurance);
            }
            return encodedSizeWithTag + i + propertiesData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, PropertiesData propertiesData) throws IOException {
            if (propertiesData.parent_itemId != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, propertiesData.parent_itemId);
            }
            if (propertiesData.source != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, propertiesData.source);
            }
            if (propertiesData.actDate != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, propertiesData.actDate);
            }
            if (propertiesData.insurance != null) {
                InsuranceData.ADAPTER.encodeWithTag(protoWriter, 4, propertiesData.insurance);
            }
            protoWriter.writeBytes(propertiesData.unknownFields());
        }

        public PropertiesData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.parent_itemId(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.source(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.actDate(ProtoAdapter.STRING.decode(protoReader));
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

        public PropertiesData redact(PropertiesData propertiesData) {
            Builder newBuilder = propertiesData.newBuilder();
            if (newBuilder.insurance != null) {
                newBuilder.insurance = InsuranceData.ADAPTER.redact(newBuilder.insurance);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
