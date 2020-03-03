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

public final class ShipmentlistData extends Message<ShipmentlistData, Builder> {
    public static final ProtoAdapter<ShipmentlistData> ADAPTER = new ProtoAdapter_ShipmentlistData();
    public static final String DEFAULT_AMOUNT = "";
    public static final String DEFAULT_BASIC_AMOUNT = "";
    public static final String DEFAULT_BRIEF = "";
    public static final Boolean DEFAULT_CHECKED = false;
    public static final String DEFAULT_SHIPMENT_ID = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String amount;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String basic_amount;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String brief;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 5)
    public final Boolean checked;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String shipment_id;

    public ShipmentlistData(String str, String str2, String str3, String str4, Boolean bool) {
        this(str, str2, str3, str4, bool, ByteString.EMPTY);
    }

    public ShipmentlistData(String str, String str2, String str3, String str4, Boolean bool, ByteString byteString) {
        super(ADAPTER, byteString);
        this.shipment_id = str;
        this.brief = str2;
        this.amount = str3;
        this.basic_amount = str4;
        this.checked = bool;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.shipment_id = this.shipment_id;
        builder.brief = this.brief;
        builder.amount = this.amount;
        builder.basic_amount = this.basic_amount;
        builder.checked = this.checked;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ShipmentlistData)) {
            return false;
        }
        ShipmentlistData shipmentlistData = (ShipmentlistData) obj;
        if (!Internal.equals(unknownFields(), shipmentlistData.unknownFields()) || !Internal.equals(this.shipment_id, shipmentlistData.shipment_id) || !Internal.equals(this.brief, shipmentlistData.brief) || !Internal.equals(this.amount, shipmentlistData.amount) || !Internal.equals(this.basic_amount, shipmentlistData.basic_amount) || !Internal.equals(this.checked, shipmentlistData.checked)) {
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
        int hashCode = ((((((((unknownFields().hashCode() * 37) + (this.shipment_id != null ? this.shipment_id.hashCode() : 0)) * 37) + (this.brief != null ? this.brief.hashCode() : 0)) * 37) + (this.amount != null ? this.amount.hashCode() : 0)) * 37) + (this.basic_amount != null ? this.basic_amount.hashCode() : 0)) * 37;
        if (this.checked != null) {
            i2 = this.checked.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.shipment_id != null) {
            sb.append(", shipment_id=");
            sb.append(this.shipment_id);
        }
        if (this.brief != null) {
            sb.append(", brief=");
            sb.append(this.brief);
        }
        if (this.amount != null) {
            sb.append(", amount=");
            sb.append(this.amount);
        }
        if (this.basic_amount != null) {
            sb.append(", basic_amount=");
            sb.append(this.basic_amount);
        }
        if (this.checked != null) {
            sb.append(", checked=");
            sb.append(this.checked);
        }
        StringBuilder replace = sb.replace(0, 2, "ShipmentlistData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<ShipmentlistData, Builder> {
        public String amount;
        public String basic_amount;
        public String brief;
        public Boolean checked;
        public String shipment_id;

        public Builder shipment_id(String str) {
            this.shipment_id = str;
            return this;
        }

        public Builder brief(String str) {
            this.brief = str;
            return this;
        }

        public Builder amount(String str) {
            this.amount = str;
            return this;
        }

        public Builder basic_amount(String str) {
            this.basic_amount = str;
            return this;
        }

        public Builder checked(Boolean bool) {
            this.checked = bool;
            return this;
        }

        public ShipmentlistData build() {
            return new ShipmentlistData(this.shipment_id, this.brief, this.amount, this.basic_amount, this.checked, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_ShipmentlistData extends ProtoAdapter<ShipmentlistData> {
        ProtoAdapter_ShipmentlistData() {
            super(FieldEncoding.LENGTH_DELIMITED, ShipmentlistData.class);
        }

        public int encodedSize(ShipmentlistData shipmentlistData) {
            int i = 0;
            int encodedSizeWithTag = (shipmentlistData.shipment_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, shipmentlistData.shipment_id) : 0) + (shipmentlistData.brief != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, shipmentlistData.brief) : 0) + (shipmentlistData.amount != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, shipmentlistData.amount) : 0) + (shipmentlistData.basic_amount != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, shipmentlistData.basic_amount) : 0);
            if (shipmentlistData.checked != null) {
                i = ProtoAdapter.BOOL.encodedSizeWithTag(5, shipmentlistData.checked);
            }
            return encodedSizeWithTag + i + shipmentlistData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, ShipmentlistData shipmentlistData) throws IOException {
            if (shipmentlistData.shipment_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, shipmentlistData.shipment_id);
            }
            if (shipmentlistData.brief != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, shipmentlistData.brief);
            }
            if (shipmentlistData.amount != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, shipmentlistData.amount);
            }
            if (shipmentlistData.basic_amount != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, shipmentlistData.basic_amount);
            }
            if (shipmentlistData.checked != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 5, shipmentlistData.checked);
            }
            protoWriter.writeBytes(shipmentlistData.unknownFields());
        }

        public ShipmentlistData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.shipment_id(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.brief(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.amount(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.basic_amount(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 5:
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

        public ShipmentlistData redact(ShipmentlistData shipmentlistData) {
            Builder newBuilder = shipmentlistData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
