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
import java.util.List;
import okio.ByteString;

public final class GatherorderInfoData extends Message<GatherorderInfoData, Builder> {
    public static final ProtoAdapter<GatherorderInfoData> ADAPTER = new ProtoAdapter_GatherorderInfoData();
    public static final String DEFAULT_BALANCE_PRICE = "";
    public static final Boolean DEFAULT_SHOW_LIST = false;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String balance_price;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", label = WireField.Label.REPEATED, tag = 3)
    public final List<String> goods_list;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 2)
    public final Boolean show_list;

    public GatherorderInfoData(String str, Boolean bool, List<String> list) {
        this(str, bool, list, ByteString.EMPTY);
    }

    public GatherorderInfoData(String str, Boolean bool, List<String> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.balance_price = str;
        this.show_list = bool;
        this.goods_list = Internal.immutableCopyOf("goods_list", list);
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.balance_price = this.balance_price;
        builder.show_list = this.show_list;
        builder.goods_list = Internal.copyOf("goods_list", this.goods_list);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GatherorderInfoData)) {
            return false;
        }
        GatherorderInfoData gatherorderInfoData = (GatherorderInfoData) obj;
        if (!Internal.equals(unknownFields(), gatherorderInfoData.unknownFields()) || !Internal.equals(this.balance_price, gatherorderInfoData.balance_price) || !Internal.equals(this.show_list, gatherorderInfoData.show_list) || !Internal.equals(this.goods_list, gatherorderInfoData.goods_list)) {
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
        int hashCode = ((unknownFields().hashCode() * 37) + (this.balance_price != null ? this.balance_price.hashCode() : 0)) * 37;
        if (this.show_list != null) {
            i2 = this.show_list.hashCode();
        }
        int hashCode2 = ((hashCode + i2) * 37) + (this.goods_list != null ? this.goods_list.hashCode() : 1);
        this.hashCode = hashCode2;
        return hashCode2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.balance_price != null) {
            sb.append(", balance_price=");
            sb.append(this.balance_price);
        }
        if (this.show_list != null) {
            sb.append(", show_list=");
            sb.append(this.show_list);
        }
        if (this.goods_list != null) {
            sb.append(", goods_list=");
            sb.append(this.goods_list);
        }
        StringBuilder replace = sb.replace(0, 2, "GatherorderInfoData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<GatherorderInfoData, Builder> {
        public String balance_price;
        public List<String> goods_list = Internal.newMutableList();
        public Boolean show_list;

        public Builder balance_price(String str) {
            this.balance_price = str;
            return this;
        }

        public Builder show_list(Boolean bool) {
            this.show_list = bool;
            return this;
        }

        public Builder goods_list(List<String> list) {
            Internal.checkElementsNotNull(list);
            this.goods_list = list;
            return this;
        }

        public GatherorderInfoData build() {
            return new GatherorderInfoData(this.balance_price, this.show_list, this.goods_list, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_GatherorderInfoData extends ProtoAdapter<GatherorderInfoData> {
        ProtoAdapter_GatherorderInfoData() {
            super(FieldEncoding.LENGTH_DELIMITED, GatherorderInfoData.class);
        }

        public int encodedSize(GatherorderInfoData gatherorderInfoData) {
            int i = 0;
            int encodedSizeWithTag = gatherorderInfoData.balance_price != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, gatherorderInfoData.balance_price) : 0;
            if (gatherorderInfoData.show_list != null) {
                i = ProtoAdapter.BOOL.encodedSizeWithTag(2, gatherorderInfoData.show_list);
            }
            return encodedSizeWithTag + i + ProtoAdapter.STRING.asRepeated().encodedSizeWithTag(3, gatherorderInfoData.goods_list) + gatherorderInfoData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, GatherorderInfoData gatherorderInfoData) throws IOException {
            if (gatherorderInfoData.balance_price != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, gatherorderInfoData.balance_price);
            }
            if (gatherorderInfoData.show_list != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 2, gatherorderInfoData.show_list);
            }
            if (gatherorderInfoData.goods_list != null) {
                ProtoAdapter.STRING.asRepeated().encodeWithTag(protoWriter, 3, gatherorderInfoData.goods_list);
            }
            protoWriter.writeBytes(gatherorderInfoData.unknownFields());
        }

        public GatherorderInfoData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.balance_price(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.show_list(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 3:
                            builder.goods_list.add(ProtoAdapter.STRING.decode(protoReader));
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

        public GatherorderInfoData redact(GatherorderInfoData gatherorderInfoData) {
            Builder newBuilder = gatherorderInfoData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
