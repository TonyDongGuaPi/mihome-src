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

public final class CouponsData extends Message<CouponsData, Builder> {
    public static final ProtoAdapter<CouponsData> ADAPTER = new ProtoAdapter_CouponsData();
    public static final String DEFAULT_ACTIVITY_NAME = "";
    public static final String DEFAULT_ACTNAME = "";
    public static final Integer DEFAULT_COUPON_NUM = 0;
    public static final String DEFAULT_COUPON_TYPE_NAME = "";
    public static final String DEFAULT_PARENT_ITEMID = "";
    public static final Integer DEFAULT_SEL_STATUS = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String actName;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String activity_name;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 3)
    public final Integer coupon_num;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String coupon_type_name;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String parent_itemId;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 5)
    public final Integer sel_status;

    public CouponsData(String str, String str2, Integer num, String str3, Integer num2, String str4) {
        this(str, str2, num, str3, num2, str4, ByteString.EMPTY);
    }

    public CouponsData(String str, String str2, Integer num, String str3, Integer num2, String str4, ByteString byteString) {
        super(ADAPTER, byteString);
        this.actName = str;
        this.coupon_type_name = str2;
        this.coupon_num = num;
        this.parent_itemId = str3;
        this.sel_status = num2;
        this.activity_name = str4;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.actName = this.actName;
        builder.coupon_type_name = this.coupon_type_name;
        builder.coupon_num = this.coupon_num;
        builder.parent_itemId = this.parent_itemId;
        builder.sel_status = this.sel_status;
        builder.activity_name = this.activity_name;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CouponsData)) {
            return false;
        }
        CouponsData couponsData = (CouponsData) obj;
        if (!Internal.equals(unknownFields(), couponsData.unknownFields()) || !Internal.equals(this.actName, couponsData.actName) || !Internal.equals(this.coupon_type_name, couponsData.coupon_type_name) || !Internal.equals(this.coupon_num, couponsData.coupon_num) || !Internal.equals(this.parent_itemId, couponsData.parent_itemId) || !Internal.equals(this.sel_status, couponsData.sel_status) || !Internal.equals(this.activity_name, couponsData.activity_name)) {
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
        int hashCode = ((((((((((unknownFields().hashCode() * 37) + (this.actName != null ? this.actName.hashCode() : 0)) * 37) + (this.coupon_type_name != null ? this.coupon_type_name.hashCode() : 0)) * 37) + (this.coupon_num != null ? this.coupon_num.hashCode() : 0)) * 37) + (this.parent_itemId != null ? this.parent_itemId.hashCode() : 0)) * 37) + (this.sel_status != null ? this.sel_status.hashCode() : 0)) * 37;
        if (this.activity_name != null) {
            i2 = this.activity_name.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.actName != null) {
            sb.append(", actName=");
            sb.append(this.actName);
        }
        if (this.coupon_type_name != null) {
            sb.append(", coupon_type_name=");
            sb.append(this.coupon_type_name);
        }
        if (this.coupon_num != null) {
            sb.append(", coupon_num=");
            sb.append(this.coupon_num);
        }
        if (this.parent_itemId != null) {
            sb.append(", parent_itemId=");
            sb.append(this.parent_itemId);
        }
        if (this.sel_status != null) {
            sb.append(", sel_status=");
            sb.append(this.sel_status);
        }
        if (this.activity_name != null) {
            sb.append(", activity_name=");
            sb.append(this.activity_name);
        }
        StringBuilder replace = sb.replace(0, 2, "CouponsData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<CouponsData, Builder> {
        public String actName;
        public String activity_name;
        public Integer coupon_num;
        public String coupon_type_name;
        public String parent_itemId;
        public Integer sel_status;

        public Builder actName(String str) {
            this.actName = str;
            return this;
        }

        public Builder coupon_type_name(String str) {
            this.coupon_type_name = str;
            return this;
        }

        public Builder coupon_num(Integer num) {
            this.coupon_num = num;
            return this;
        }

        public Builder parent_itemId(String str) {
            this.parent_itemId = str;
            return this;
        }

        public Builder sel_status(Integer num) {
            this.sel_status = num;
            return this;
        }

        public Builder activity_name(String str) {
            this.activity_name = str;
            return this;
        }

        public CouponsData build() {
            return new CouponsData(this.actName, this.coupon_type_name, this.coupon_num, this.parent_itemId, this.sel_status, this.activity_name, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_CouponsData extends ProtoAdapter<CouponsData> {
        ProtoAdapter_CouponsData() {
            super(FieldEncoding.LENGTH_DELIMITED, CouponsData.class);
        }

        public int encodedSize(CouponsData couponsData) {
            int i = 0;
            int encodedSizeWithTag = (couponsData.actName != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, couponsData.actName) : 0) + (couponsData.coupon_type_name != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, couponsData.coupon_type_name) : 0) + (couponsData.coupon_num != null ? ProtoAdapter.INT32.encodedSizeWithTag(3, couponsData.coupon_num) : 0) + (couponsData.parent_itemId != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, couponsData.parent_itemId) : 0) + (couponsData.sel_status != null ? ProtoAdapter.INT32.encodedSizeWithTag(5, couponsData.sel_status) : 0);
            if (couponsData.activity_name != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(6, couponsData.activity_name);
            }
            return encodedSizeWithTag + i + couponsData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, CouponsData couponsData) throws IOException {
            if (couponsData.actName != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, couponsData.actName);
            }
            if (couponsData.coupon_type_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, couponsData.coupon_type_name);
            }
            if (couponsData.coupon_num != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 3, couponsData.coupon_num);
            }
            if (couponsData.parent_itemId != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, couponsData.parent_itemId);
            }
            if (couponsData.sel_status != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 5, couponsData.sel_status);
            }
            if (couponsData.activity_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, couponsData.activity_name);
            }
            protoWriter.writeBytes(couponsData.unknownFields());
        }

        public CouponsData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.actName(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.coupon_type_name(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.coupon_num(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 4:
                            builder.parent_itemId(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 5:
                            builder.sel_status(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 6:
                            builder.activity_name(ProtoAdapter.STRING.decode(protoReader));
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

        public CouponsData redact(CouponsData couponsData) {
            Builder newBuilder = couponsData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
