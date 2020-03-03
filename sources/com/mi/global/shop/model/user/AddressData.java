package com.mi.global.shop.model.user;

import com.mi.global.shop.model.common.RegionData;
import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.util.List;
import okio.ByteString;

public final class AddressData extends Message<AddressData, Builder> {
    public static final ProtoAdapter<AddressData> ADAPTER = new ProtoAdapter_AddressData();
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.common.AddressData#ADAPTER", label = WireField.Label.REPEATED, tag = 1)
    public final List<com.mi.global.shop.model.common.AddressData> list;
    @WireField(adapter = "com.mi.global.shop.model.common.RegionData#ADAPTER", label = WireField.Label.REPEATED, tag = 2)
    public final List<RegionData> region;

    public AddressData(List<com.mi.global.shop.model.common.AddressData> list2, List<RegionData> list3) {
        this(list2, list3, ByteString.EMPTY);
    }

    public AddressData(List<com.mi.global.shop.model.common.AddressData> list2, List<RegionData> list3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.list = Internal.immutableCopyOf("list", list2);
        this.region = Internal.immutableCopyOf("region", list3);
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.list = Internal.copyOf("list", this.list);
        builder.region = Internal.copyOf("region", this.region);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AddressData)) {
            return false;
        }
        AddressData addressData = (AddressData) obj;
        if (!Internal.equals(unknownFields(), addressData.unknownFields()) || !Internal.equals(this.list, addressData.list) || !Internal.equals(this.region, addressData.region)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int i2 = 1;
        int hashCode = ((unknownFields().hashCode() * 37) + (this.list != null ? this.list.hashCode() : 1)) * 37;
        if (this.region != null) {
            i2 = this.region.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.list != null) {
            sb.append(", list=");
            sb.append(this.list);
        }
        if (this.region != null) {
            sb.append(", region=");
            sb.append(this.region);
        }
        StringBuilder replace = sb.replace(0, 2, "AddressData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<AddressData, Builder> {
        public List<com.mi.global.shop.model.common.AddressData> list = Internal.newMutableList();
        public List<RegionData> region = Internal.newMutableList();

        public Builder list(List<com.mi.global.shop.model.common.AddressData> list2) {
            Internal.checkElementsNotNull(list2);
            this.list = list2;
            return this;
        }

        public Builder region(List<RegionData> list2) {
            Internal.checkElementsNotNull(list2);
            this.region = list2;
            return this;
        }

        public AddressData build() {
            return new AddressData(this.list, this.region, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_AddressData extends ProtoAdapter<AddressData> {
        ProtoAdapter_AddressData() {
            super(FieldEncoding.LENGTH_DELIMITED, AddressData.class);
        }

        public int encodedSize(AddressData addressData) {
            return com.mi.global.shop.model.common.AddressData.ADAPTER.asRepeated().encodedSizeWithTag(1, addressData.list) + RegionData.ADAPTER.asRepeated().encodedSizeWithTag(2, addressData.region) + addressData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, AddressData addressData) throws IOException {
            if (addressData.list != null) {
                com.mi.global.shop.model.common.AddressData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 1, addressData.list);
            }
            if (addressData.region != null) {
                RegionData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 2, addressData.region);
            }
            protoWriter.writeBytes(addressData.unknownFields());
        }

        public AddressData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.list.add(com.mi.global.shop.model.common.AddressData.ADAPTER.decode(protoReader));
                            break;
                        case 2:
                            builder.region.add(RegionData.ADAPTER.decode(protoReader));
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

        public AddressData redact(AddressData addressData) {
            Builder newBuilder = addressData.newBuilder();
            Internal.redactElements(newBuilder.list, com.mi.global.shop.model.common.AddressData.ADAPTER);
            Internal.redactElements(newBuilder.region, RegionData.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
