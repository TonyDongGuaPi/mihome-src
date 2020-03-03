package com.mi.global.shop.model.address;

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

public final class CheckZipcodeData extends Message<CheckZipcodeData, Builder> {
    public static final ProtoAdapter<CheckZipcodeData> ADAPTER = new ProtoAdapter_CheckZipcodeData();
    public static final Integer DEFAULT_CAN_COD = 0;
    public static final Integer DEFAULT_CAN_DELIVERY = 0;
    public static final String DEFAULT_CITYS = "";
    public static final Integer DEFAULT_ENABLE = 0;
    public static final String DEFAULT_ERRORS = "";
    public static final String DEFAULT_LIMIT = "";
    public static final String DEFAULT_LIMIT_COD = "";
    public static final String DEFAULT_PARENT_ID = "";
    public static final String DEFAULT_REGION_ID = "";
    public static final String DEFAULT_REGION_NAME = "";
    public static final String DEFAULT_WAREHOUSE = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 4)
    public final Integer can_cod;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 5)
    public final Integer can_delivery;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 10)
    public final String citys;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 6)
    public final Integer enable;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 11)
    public final String errors;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String limit;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final String limit_cod;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String parent_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String region_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String region_name;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 12)
    public final String warehouse;

    public CheckZipcodeData(String str, String str2, String str3, Integer num, Integer num2, Integer num3, String str4, String str5, String str6, String str7, String str8) {
        this(str, str2, str3, num, num2, num3, str4, str5, str6, str7, str8, ByteString.EMPTY);
    }

    public CheckZipcodeData(String str, String str2, String str3, Integer num, Integer num2, Integer num3, String str4, String str5, String str6, String str7, String str8, ByteString byteString) {
        super(ADAPTER, byteString);
        this.region_id = str;
        this.parent_id = str2;
        this.region_name = str3;
        this.can_cod = num;
        this.can_delivery = num2;
        this.enable = num3;
        this.limit = str4;
        this.limit_cod = str5;
        this.citys = str6;
        this.errors = str7;
        this.warehouse = str8;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.region_id = this.region_id;
        builder.parent_id = this.parent_id;
        builder.region_name = this.region_name;
        builder.can_cod = this.can_cod;
        builder.can_delivery = this.can_delivery;
        builder.enable = this.enable;
        builder.limit = this.limit;
        builder.limit_cod = this.limit_cod;
        builder.citys = this.citys;
        builder.errors = this.errors;
        builder.warehouse = this.warehouse;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CheckZipcodeData)) {
            return false;
        }
        CheckZipcodeData checkZipcodeData = (CheckZipcodeData) obj;
        if (!Internal.equals(unknownFields(), checkZipcodeData.unknownFields()) || !Internal.equals(this.region_id, checkZipcodeData.region_id) || !Internal.equals(this.parent_id, checkZipcodeData.parent_id) || !Internal.equals(this.region_name, checkZipcodeData.region_name) || !Internal.equals(this.can_cod, checkZipcodeData.can_cod) || !Internal.equals(this.can_delivery, checkZipcodeData.can_delivery) || !Internal.equals(this.enable, checkZipcodeData.enable) || !Internal.equals(this.limit, checkZipcodeData.limit) || !Internal.equals(this.limit_cod, checkZipcodeData.limit_cod) || !Internal.equals(this.citys, checkZipcodeData.citys) || !Internal.equals(this.errors, checkZipcodeData.errors) || !Internal.equals(this.warehouse, checkZipcodeData.warehouse)) {
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
        int hashCode = ((((((((((((((((((((unknownFields().hashCode() * 37) + (this.region_id != null ? this.region_id.hashCode() : 0)) * 37) + (this.parent_id != null ? this.parent_id.hashCode() : 0)) * 37) + (this.region_name != null ? this.region_name.hashCode() : 0)) * 37) + (this.can_cod != null ? this.can_cod.hashCode() : 0)) * 37) + (this.can_delivery != null ? this.can_delivery.hashCode() : 0)) * 37) + (this.enable != null ? this.enable.hashCode() : 0)) * 37) + (this.limit != null ? this.limit.hashCode() : 0)) * 37) + (this.limit_cod != null ? this.limit_cod.hashCode() : 0)) * 37) + (this.citys != null ? this.citys.hashCode() : 0)) * 37) + (this.errors != null ? this.errors.hashCode() : 0)) * 37;
        if (this.warehouse != null) {
            i2 = this.warehouse.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.region_id != null) {
            sb.append(", region_id=");
            sb.append(this.region_id);
        }
        if (this.parent_id != null) {
            sb.append(", parent_id=");
            sb.append(this.parent_id);
        }
        if (this.region_name != null) {
            sb.append(", region_name=");
            sb.append(this.region_name);
        }
        if (this.can_cod != null) {
            sb.append(", can_cod=");
            sb.append(this.can_cod);
        }
        if (this.can_delivery != null) {
            sb.append(", can_delivery=");
            sb.append(this.can_delivery);
        }
        if (this.enable != null) {
            sb.append(", enable=");
            sb.append(this.enable);
        }
        if (this.limit != null) {
            sb.append(", limit=");
            sb.append(this.limit);
        }
        if (this.limit_cod != null) {
            sb.append(", limit_cod=");
            sb.append(this.limit_cod);
        }
        if (this.citys != null) {
            sb.append(", citys=");
            sb.append(this.citys);
        }
        if (this.errors != null) {
            sb.append(", errors=");
            sb.append(this.errors);
        }
        if (this.warehouse != null) {
            sb.append(", warehouse=");
            sb.append(this.warehouse);
        }
        StringBuilder replace = sb.replace(0, 2, "CheckZipcodeData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<CheckZipcodeData, Builder> {
        public Integer can_cod;
        public Integer can_delivery;
        public String citys;
        public Integer enable;
        public String errors;
        public String limit;
        public String limit_cod;
        public String parent_id;
        public String region_id;
        public String region_name;
        public String warehouse;

        public Builder region_id(String str) {
            this.region_id = str;
            return this;
        }

        public Builder parent_id(String str) {
            this.parent_id = str;
            return this;
        }

        public Builder region_name(String str) {
            this.region_name = str;
            return this;
        }

        public Builder can_cod(Integer num) {
            this.can_cod = num;
            return this;
        }

        public Builder can_delivery(Integer num) {
            this.can_delivery = num;
            return this;
        }

        public Builder enable(Integer num) {
            this.enable = num;
            return this;
        }

        public Builder limit(String str) {
            this.limit = str;
            return this;
        }

        public Builder limit_cod(String str) {
            this.limit_cod = str;
            return this;
        }

        public Builder citys(String str) {
            this.citys = str;
            return this;
        }

        public Builder errors(String str) {
            this.errors = str;
            return this;
        }

        public Builder warehouse(String str) {
            this.warehouse = str;
            return this;
        }

        public CheckZipcodeData build() {
            return new CheckZipcodeData(this.region_id, this.parent_id, this.region_name, this.can_cod, this.can_delivery, this.enable, this.limit, this.limit_cod, this.citys, this.errors, this.warehouse, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_CheckZipcodeData extends ProtoAdapter<CheckZipcodeData> {
        ProtoAdapter_CheckZipcodeData() {
            super(FieldEncoding.LENGTH_DELIMITED, CheckZipcodeData.class);
        }

        public int encodedSize(CheckZipcodeData checkZipcodeData) {
            int i = 0;
            int encodedSizeWithTag = (checkZipcodeData.region_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, checkZipcodeData.region_id) : 0) + (checkZipcodeData.parent_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, checkZipcodeData.parent_id) : 0) + (checkZipcodeData.region_name != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, checkZipcodeData.region_name) : 0) + (checkZipcodeData.can_cod != null ? ProtoAdapter.INT32.encodedSizeWithTag(4, checkZipcodeData.can_cod) : 0) + (checkZipcodeData.can_delivery != null ? ProtoAdapter.INT32.encodedSizeWithTag(5, checkZipcodeData.can_delivery) : 0) + (checkZipcodeData.enable != null ? ProtoAdapter.INT32.encodedSizeWithTag(6, checkZipcodeData.enable) : 0) + (checkZipcodeData.limit != null ? ProtoAdapter.STRING.encodedSizeWithTag(8, checkZipcodeData.limit) : 0) + (checkZipcodeData.limit_cod != null ? ProtoAdapter.STRING.encodedSizeWithTag(9, checkZipcodeData.limit_cod) : 0) + (checkZipcodeData.citys != null ? ProtoAdapter.STRING.encodedSizeWithTag(10, checkZipcodeData.citys) : 0) + (checkZipcodeData.errors != null ? ProtoAdapter.STRING.encodedSizeWithTag(11, checkZipcodeData.errors) : 0);
            if (checkZipcodeData.warehouse != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(12, checkZipcodeData.warehouse);
            }
            return encodedSizeWithTag + i + checkZipcodeData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, CheckZipcodeData checkZipcodeData) throws IOException {
            if (checkZipcodeData.region_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, checkZipcodeData.region_id);
            }
            if (checkZipcodeData.parent_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, checkZipcodeData.parent_id);
            }
            if (checkZipcodeData.region_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, checkZipcodeData.region_name);
            }
            if (checkZipcodeData.can_cod != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 4, checkZipcodeData.can_cod);
            }
            if (checkZipcodeData.can_delivery != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 5, checkZipcodeData.can_delivery);
            }
            if (checkZipcodeData.enable != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 6, checkZipcodeData.enable);
            }
            if (checkZipcodeData.limit != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, checkZipcodeData.limit);
            }
            if (checkZipcodeData.limit_cod != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 9, checkZipcodeData.limit_cod);
            }
            if (checkZipcodeData.citys != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 10, checkZipcodeData.citys);
            }
            if (checkZipcodeData.errors != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 11, checkZipcodeData.errors);
            }
            if (checkZipcodeData.warehouse != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 12, checkZipcodeData.warehouse);
            }
            protoWriter.writeBytes(checkZipcodeData.unknownFields());
        }

        public CheckZipcodeData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.region_id(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.parent_id(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.region_name(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.can_cod(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 5:
                            builder.can_delivery(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 6:
                            builder.enable(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 8:
                            builder.limit(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 9:
                            builder.limit_cod(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 10:
                            builder.citys(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 11:
                            builder.errors(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 12:
                            builder.warehouse(ProtoAdapter.STRING.decode(protoReader));
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

        public CheckZipcodeData redact(CheckZipcodeData checkZipcodeData) {
            Builder newBuilder = checkZipcodeData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
