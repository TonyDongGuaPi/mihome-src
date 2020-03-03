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

public final class EditCartData extends Message<EditCartData, Builder> {
    public static final ProtoAdapter<EditCartData> ADAPTER = new ProtoAdapter_EditCartData();
    public static final String DEFAULT_CARTACTIVITYDISCOUNTMONEY = "";
    public static final String DEFAULT_CARTORDERMONEY = "";
    public static final Float DEFAULT_CARTPOSTFREEBALANCE = Float.valueOf(0.0f);
    public static final String DEFAULT_CARTPRODUCTMONEY = "";
    public static final Integer DEFAULT_ITEMCOUNT = 0;
    public static final Integer DEFAULT_TOTALWITHOUTGIFT = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String CartActivityDiscountMoney;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String CartOrderMoney;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 4)
    public final Float CartPostFreeBalance;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String CartProductMoney;
    @WireField(adapter = "com.mi.global.shop.model.common.ActivitysData#ADAPTER", tag = 7)
    public final ActivitysData activitys;
    @WireField(adapter = "com.mi.global.shop.model.common.BargainsData#ADAPTER", label = WireField.Label.REPEATED, tag = 5)
    public final List<BargainsData> bargains;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 9)
    public final Integer itemcount;
    @WireField(adapter = "com.mi.global.shop.model.common.ItemsData#ADAPTER", label = WireField.Label.REPEATED, tag = 8)
    public final List<ItemsData> items;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 6)
    public final Integer totalWithoutGift;

    public EditCartData(String str, String str2, String str3, Float f, List<BargainsData> list, Integer num, ActivitysData activitysData, List<ItemsData> list2, Integer num2) {
        this(str, str2, str3, f, list, num, activitysData, list2, num2, ByteString.EMPTY);
    }

    public EditCartData(String str, String str2, String str3, Float f, List<BargainsData> list, Integer num, ActivitysData activitysData, List<ItemsData> list2, Integer num2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.CartOrderMoney = str;
        this.CartProductMoney = str2;
        this.CartActivityDiscountMoney = str3;
        this.CartPostFreeBalance = f;
        this.bargains = Internal.immutableCopyOf("bargains", list);
        this.totalWithoutGift = num;
        this.activitys = activitysData;
        this.items = Internal.immutableCopyOf("items", list2);
        this.itemcount = num2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.CartOrderMoney = this.CartOrderMoney;
        builder.CartProductMoney = this.CartProductMoney;
        builder.CartActivityDiscountMoney = this.CartActivityDiscountMoney;
        builder.CartPostFreeBalance = this.CartPostFreeBalance;
        builder.bargains = Internal.copyOf("bargains", this.bargains);
        builder.totalWithoutGift = this.totalWithoutGift;
        builder.activitys = this.activitys;
        builder.items = Internal.copyOf("items", this.items);
        builder.itemcount = this.itemcount;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EditCartData)) {
            return false;
        }
        EditCartData editCartData = (EditCartData) obj;
        if (!Internal.equals(unknownFields(), editCartData.unknownFields()) || !Internal.equals(this.CartOrderMoney, editCartData.CartOrderMoney) || !Internal.equals(this.CartProductMoney, editCartData.CartProductMoney) || !Internal.equals(this.CartActivityDiscountMoney, editCartData.CartActivityDiscountMoney) || !Internal.equals(this.CartPostFreeBalance, editCartData.CartPostFreeBalance) || !Internal.equals(this.bargains, editCartData.bargains) || !Internal.equals(this.totalWithoutGift, editCartData.totalWithoutGift) || !Internal.equals(this.activitys, editCartData.activitys) || !Internal.equals(this.items, editCartData.items) || !Internal.equals(this.itemcount, editCartData.itemcount)) {
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
        int i3 = 1;
        int hashCode = ((((((((((((((unknownFields().hashCode() * 37) + (this.CartOrderMoney != null ? this.CartOrderMoney.hashCode() : 0)) * 37) + (this.CartProductMoney != null ? this.CartProductMoney.hashCode() : 0)) * 37) + (this.CartActivityDiscountMoney != null ? this.CartActivityDiscountMoney.hashCode() : 0)) * 37) + (this.CartPostFreeBalance != null ? this.CartPostFreeBalance.hashCode() : 0)) * 37) + (this.bargains != null ? this.bargains.hashCode() : 1)) * 37) + (this.totalWithoutGift != null ? this.totalWithoutGift.hashCode() : 0)) * 37) + (this.activitys != null ? this.activitys.hashCode() : 0)) * 37;
        if (this.items != null) {
            i3 = this.items.hashCode();
        }
        int i4 = (hashCode + i3) * 37;
        if (this.itemcount != null) {
            i2 = this.itemcount.hashCode();
        }
        int i5 = i4 + i2;
        this.hashCode = i5;
        return i5;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.CartOrderMoney != null) {
            sb.append(", CartOrderMoney=");
            sb.append(this.CartOrderMoney);
        }
        if (this.CartProductMoney != null) {
            sb.append(", CartProductMoney=");
            sb.append(this.CartProductMoney);
        }
        if (this.CartActivityDiscountMoney != null) {
            sb.append(", CartActivityDiscountMoney=");
            sb.append(this.CartActivityDiscountMoney);
        }
        if (this.CartPostFreeBalance != null) {
            sb.append(", CartPostFreeBalance=");
            sb.append(this.CartPostFreeBalance);
        }
        if (this.bargains != null) {
            sb.append(", bargains=");
            sb.append(this.bargains);
        }
        if (this.totalWithoutGift != null) {
            sb.append(", totalWithoutGift=");
            sb.append(this.totalWithoutGift);
        }
        if (this.activitys != null) {
            sb.append(", activitys=");
            sb.append(this.activitys);
        }
        if (this.items != null) {
            sb.append(", items=");
            sb.append(this.items);
        }
        if (this.itemcount != null) {
            sb.append(", itemcount=");
            sb.append(this.itemcount);
        }
        StringBuilder replace = sb.replace(0, 2, "EditCartData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<EditCartData, Builder> {
        public String CartActivityDiscountMoney;
        public String CartOrderMoney;
        public Float CartPostFreeBalance;
        public String CartProductMoney;
        public ActivitysData activitys;
        public List<BargainsData> bargains = Internal.newMutableList();
        public Integer itemcount;
        public List<ItemsData> items = Internal.newMutableList();
        public Integer totalWithoutGift;

        public Builder CartOrderMoney(String str) {
            this.CartOrderMoney = str;
            return this;
        }

        public Builder CartProductMoney(String str) {
            this.CartProductMoney = str;
            return this;
        }

        public Builder CartActivityDiscountMoney(String str) {
            this.CartActivityDiscountMoney = str;
            return this;
        }

        public Builder CartPostFreeBalance(Float f) {
            this.CartPostFreeBalance = f;
            return this;
        }

        public Builder bargains(List<BargainsData> list) {
            Internal.checkElementsNotNull(list);
            this.bargains = list;
            return this;
        }

        public Builder totalWithoutGift(Integer num) {
            this.totalWithoutGift = num;
            return this;
        }

        public Builder activitys(ActivitysData activitysData) {
            this.activitys = activitysData;
            return this;
        }

        public Builder items(List<ItemsData> list) {
            Internal.checkElementsNotNull(list);
            this.items = list;
            return this;
        }

        public Builder itemcount(Integer num) {
            this.itemcount = num;
            return this;
        }

        public EditCartData build() {
            return new EditCartData(this.CartOrderMoney, this.CartProductMoney, this.CartActivityDiscountMoney, this.CartPostFreeBalance, this.bargains, this.totalWithoutGift, this.activitys, this.items, this.itemcount, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_EditCartData extends ProtoAdapter<EditCartData> {
        ProtoAdapter_EditCartData() {
            super(FieldEncoding.LENGTH_DELIMITED, EditCartData.class);
        }

        public int encodedSize(EditCartData editCartData) {
            int i = 0;
            int encodedSizeWithTag = (editCartData.CartOrderMoney != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, editCartData.CartOrderMoney) : 0) + (editCartData.CartProductMoney != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, editCartData.CartProductMoney) : 0) + (editCartData.CartActivityDiscountMoney != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, editCartData.CartActivityDiscountMoney) : 0) + (editCartData.CartPostFreeBalance != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(4, editCartData.CartPostFreeBalance) : 0) + BargainsData.ADAPTER.asRepeated().encodedSizeWithTag(5, editCartData.bargains) + (editCartData.totalWithoutGift != null ? ProtoAdapter.INT32.encodedSizeWithTag(6, editCartData.totalWithoutGift) : 0) + (editCartData.activitys != null ? ActivitysData.ADAPTER.encodedSizeWithTag(7, editCartData.activitys) : 0) + ItemsData.ADAPTER.asRepeated().encodedSizeWithTag(8, editCartData.items);
            if (editCartData.itemcount != null) {
                i = ProtoAdapter.INT32.encodedSizeWithTag(9, editCartData.itemcount);
            }
            return encodedSizeWithTag + i + editCartData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, EditCartData editCartData) throws IOException {
            if (editCartData.CartOrderMoney != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, editCartData.CartOrderMoney);
            }
            if (editCartData.CartProductMoney != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, editCartData.CartProductMoney);
            }
            if (editCartData.CartActivityDiscountMoney != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, editCartData.CartActivityDiscountMoney);
            }
            if (editCartData.CartPostFreeBalance != null) {
                ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 4, editCartData.CartPostFreeBalance);
            }
            if (editCartData.bargains != null) {
                BargainsData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 5, editCartData.bargains);
            }
            if (editCartData.totalWithoutGift != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 6, editCartData.totalWithoutGift);
            }
            if (editCartData.activitys != null) {
                ActivitysData.ADAPTER.encodeWithTag(protoWriter, 7, editCartData.activitys);
            }
            if (editCartData.items != null) {
                ItemsData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 8, editCartData.items);
            }
            if (editCartData.itemcount != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 9, editCartData.itemcount);
            }
            protoWriter.writeBytes(editCartData.unknownFields());
        }

        public EditCartData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.CartOrderMoney(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.CartProductMoney(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.CartActivityDiscountMoney(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.CartPostFreeBalance(ProtoAdapter.FLOAT.decode(protoReader));
                            break;
                        case 5:
                            builder.bargains.add(BargainsData.ADAPTER.decode(protoReader));
                            break;
                        case 6:
                            builder.totalWithoutGift(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 7:
                            builder.activitys(ActivitysData.ADAPTER.decode(protoReader));
                            break;
                        case 8:
                            builder.items.add(ItemsData.ADAPTER.decode(protoReader));
                            break;
                        case 9:
                            builder.itemcount(ProtoAdapter.INT32.decode(protoReader));
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

        public EditCartData redact(EditCartData editCartData) {
            Builder newBuilder = editCartData.newBuilder();
            Internal.redactElements(newBuilder.bargains, BargainsData.ADAPTER);
            if (newBuilder.activitys != null) {
                newBuilder.activitys = ActivitysData.ADAPTER.redact(newBuilder.activitys);
            }
            Internal.redactElements(newBuilder.items, ItemsData.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
