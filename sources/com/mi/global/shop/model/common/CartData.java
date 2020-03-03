package com.mi.global.shop.model.common;

import com.mi.global.shop.model.basestruct.PageMessage;
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

public final class CartData extends Message<CartData, Builder> {
    public static final ProtoAdapter<CartData> ADAPTER = new ProtoAdapter_CartData();
    public static final String DEFAULT_ACTDISCOUNTMIN = "";
    public static final String DEFAULT_ACTIVITYDISCOUNTMONEY = "";
    public static final String DEFAULT_ACTIVITYDISCOUNTMONEY_TXT = "";
    public static final String DEFAULT_ORDERMONEY = "";
    public static final String DEFAULT_ORDERMONEY_TXT = "";
    public static final Boolean DEFAULT_POSTFREE = false;
    public static final Float DEFAULT_POSTFREEBALANCE = Float.valueOf(0.0f);
    public static final String DEFAULT_POSTFREEBALANCE_TXT = "";
    public static final String DEFAULT_PRODUCTMONEY = "";
    public static final String DEFAULT_PRODUCTMONEY_TXT = "";
    public static final Integer DEFAULT_TOTAL = 0;
    public static final String DEFAULT_TOTALPRICE = "";
    public static final Integer DEFAULT_TOTALWITHOUTGIFT = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 12)
    public final String ActDiscountMin;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final String activityDiscountMoney;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 29)
    public final String activityDiscountMoney_txt;
    @WireField(adapter = "com.mi.global.shop.model.common.ActivitysData#ADAPTER", tag = 18)
    public final ActivitysData activitys;
    @WireField(adapter = "com.mi.global.shop.model.common.AppConfigData#ADAPTER", tag = 19)
    public final AppConfigData appConfig;
    @WireField(adapter = "com.mi.global.shop.model.common.BargainsData#ADAPTER", label = WireField.Label.REPEATED, tag = 20)
    public final List<BargainsData> bargains;
    @WireField(adapter = "com.mi.global.shop.model.common.GatherorderInfoData#ADAPTER", tag = 24)
    public final GatherorderInfoData gatherorder_info;
    @WireField(adapter = "com.mi.global.shop.model.common.ItemsData#ADAPTER", label = WireField.Label.REPEATED, tag = 2)
    public final List<ItemsData> items;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 10)
    public final String orderMoney;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 28)
    public final String orderMoney_txt;
    @WireField(adapter = "com.mi.global.shop.model.basestruct.PageMessage#ADAPTER", tag = 31)
    public final PageMessage pagemsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 3)
    public final Boolean postFree;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 23)
    public final Float postFreeBalance;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 30)
    public final String postFreeBalance_txt;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String productMoney;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 27)
    public final String productMoney_txt;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 14)
    public final Integer total;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 15)
    public final Integer totalWithoutGift;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 16)
    public final String totalprice;

    public CartData(List<ItemsData> list, Boolean bool, String str, String str2, String str3, String str4, Integer num, Integer num2, String str5, ActivitysData activitysData, AppConfigData appConfigData, List<BargainsData> list2, Float f, GatherorderInfoData gatherorderInfoData, String str6, String str7, String str8, String str9, PageMessage pageMessage) {
        this(list, bool, str, str2, str3, str4, num, num2, str5, activitysData, appConfigData, list2, f, gatherorderInfoData, str6, str7, str8, str9, pageMessage, ByteString.EMPTY);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CartData(List<ItemsData> list, Boolean bool, String str, String str2, String str3, String str4, Integer num, Integer num2, String str5, ActivitysData activitysData, AppConfigData appConfigData, List<BargainsData> list2, Float f, GatherorderInfoData gatherorderInfoData, String str6, String str7, String str8, String str9, PageMessage pageMessage, ByteString byteString) {
        super(ADAPTER, byteString);
        List<ItemsData> list3 = list;
        this.items = Internal.immutableCopyOf("items", list);
        this.postFree = bool;
        this.productMoney = str;
        this.activityDiscountMoney = str2;
        this.orderMoney = str3;
        this.ActDiscountMin = str4;
        this.total = num;
        this.totalWithoutGift = num2;
        this.totalprice = str5;
        this.activitys = activitysData;
        this.appConfig = appConfigData;
        List<BargainsData> list4 = list2;
        this.bargains = Internal.immutableCopyOf("bargains", list2);
        this.postFreeBalance = f;
        this.gatherorder_info = gatherorderInfoData;
        this.productMoney_txt = str6;
        this.orderMoney_txt = str7;
        this.activityDiscountMoney_txt = str8;
        this.postFreeBalance_txt = str9;
        this.pagemsg = pageMessage;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.items = Internal.copyOf("items", this.items);
        builder.postFree = this.postFree;
        builder.productMoney = this.productMoney;
        builder.activityDiscountMoney = this.activityDiscountMoney;
        builder.orderMoney = this.orderMoney;
        builder.ActDiscountMin = this.ActDiscountMin;
        builder.total = this.total;
        builder.totalWithoutGift = this.totalWithoutGift;
        builder.totalprice = this.totalprice;
        builder.activitys = this.activitys;
        builder.appConfig = this.appConfig;
        builder.bargains = Internal.copyOf("bargains", this.bargains);
        builder.postFreeBalance = this.postFreeBalance;
        builder.gatherorder_info = this.gatherorder_info;
        builder.productMoney_txt = this.productMoney_txt;
        builder.orderMoney_txt = this.orderMoney_txt;
        builder.activityDiscountMoney_txt = this.activityDiscountMoney_txt;
        builder.postFreeBalance_txt = this.postFreeBalance_txt;
        builder.pagemsg = this.pagemsg;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CartData)) {
            return false;
        }
        CartData cartData = (CartData) obj;
        if (!Internal.equals(unknownFields(), cartData.unknownFields()) || !Internal.equals(this.items, cartData.items) || !Internal.equals(this.postFree, cartData.postFree) || !Internal.equals(this.productMoney, cartData.productMoney) || !Internal.equals(this.activityDiscountMoney, cartData.activityDiscountMoney) || !Internal.equals(this.orderMoney, cartData.orderMoney) || !Internal.equals(this.ActDiscountMin, cartData.ActDiscountMin) || !Internal.equals(this.total, cartData.total) || !Internal.equals(this.totalWithoutGift, cartData.totalWithoutGift) || !Internal.equals(this.totalprice, cartData.totalprice) || !Internal.equals(this.activitys, cartData.activitys) || !Internal.equals(this.appConfig, cartData.appConfig) || !Internal.equals(this.bargains, cartData.bargains) || !Internal.equals(this.postFreeBalance, cartData.postFreeBalance) || !Internal.equals(this.gatherorder_info, cartData.gatherorder_info) || !Internal.equals(this.productMoney_txt, cartData.productMoney_txt) || !Internal.equals(this.orderMoney_txt, cartData.orderMoney_txt) || !Internal.equals(this.activityDiscountMoney_txt, cartData.activityDiscountMoney_txt) || !Internal.equals(this.postFreeBalance_txt, cartData.postFreeBalance_txt) || !Internal.equals(this.pagemsg, cartData.pagemsg)) {
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
        int i3 = 0;
        int hashCode = ((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.items != null ? this.items.hashCode() : 1)) * 37) + (this.postFree != null ? this.postFree.hashCode() : 0)) * 37) + (this.productMoney != null ? this.productMoney.hashCode() : 0)) * 37) + (this.activityDiscountMoney != null ? this.activityDiscountMoney.hashCode() : 0)) * 37) + (this.orderMoney != null ? this.orderMoney.hashCode() : 0)) * 37) + (this.ActDiscountMin != null ? this.ActDiscountMin.hashCode() : 0)) * 37) + (this.total != null ? this.total.hashCode() : 0)) * 37) + (this.totalWithoutGift != null ? this.totalWithoutGift.hashCode() : 0)) * 37) + (this.totalprice != null ? this.totalprice.hashCode() : 0)) * 37) + (this.activitys != null ? this.activitys.hashCode() : 0)) * 37) + (this.appConfig != null ? this.appConfig.hashCode() : 0)) * 37;
        if (this.bargains != null) {
            i2 = this.bargains.hashCode();
        }
        int hashCode2 = (((((((((((((hashCode + i2) * 37) + (this.postFreeBalance != null ? this.postFreeBalance.hashCode() : 0)) * 37) + (this.gatherorder_info != null ? this.gatherorder_info.hashCode() : 0)) * 37) + (this.productMoney_txt != null ? this.productMoney_txt.hashCode() : 0)) * 37) + (this.orderMoney_txt != null ? this.orderMoney_txt.hashCode() : 0)) * 37) + (this.activityDiscountMoney_txt != null ? this.activityDiscountMoney_txt.hashCode() : 0)) * 37) + (this.postFreeBalance_txt != null ? this.postFreeBalance_txt.hashCode() : 0)) * 37;
        if (this.pagemsg != null) {
            i3 = this.pagemsg.hashCode();
        }
        int i4 = hashCode2 + i3;
        this.hashCode = i4;
        return i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.items != null) {
            sb.append(", items=");
            sb.append(this.items);
        }
        if (this.postFree != null) {
            sb.append(", postFree=");
            sb.append(this.postFree);
        }
        if (this.productMoney != null) {
            sb.append(", productMoney=");
            sb.append(this.productMoney);
        }
        if (this.activityDiscountMoney != null) {
            sb.append(", activityDiscountMoney=");
            sb.append(this.activityDiscountMoney);
        }
        if (this.orderMoney != null) {
            sb.append(", orderMoney=");
            sb.append(this.orderMoney);
        }
        if (this.ActDiscountMin != null) {
            sb.append(", ActDiscountMin=");
            sb.append(this.ActDiscountMin);
        }
        if (this.total != null) {
            sb.append(", total=");
            sb.append(this.total);
        }
        if (this.totalWithoutGift != null) {
            sb.append(", totalWithoutGift=");
            sb.append(this.totalWithoutGift);
        }
        if (this.totalprice != null) {
            sb.append(", totalprice=");
            sb.append(this.totalprice);
        }
        if (this.activitys != null) {
            sb.append(", activitys=");
            sb.append(this.activitys);
        }
        if (this.appConfig != null) {
            sb.append(", appConfig=");
            sb.append(this.appConfig);
        }
        if (this.bargains != null) {
            sb.append(", bargains=");
            sb.append(this.bargains);
        }
        if (this.postFreeBalance != null) {
            sb.append(", postFreeBalance=");
            sb.append(this.postFreeBalance);
        }
        if (this.gatherorder_info != null) {
            sb.append(", gatherorder_info=");
            sb.append(this.gatherorder_info);
        }
        if (this.productMoney_txt != null) {
            sb.append(", productMoney_txt=");
            sb.append(this.productMoney_txt);
        }
        if (this.orderMoney_txt != null) {
            sb.append(", orderMoney_txt=");
            sb.append(this.orderMoney_txt);
        }
        if (this.activityDiscountMoney_txt != null) {
            sb.append(", activityDiscountMoney_txt=");
            sb.append(this.activityDiscountMoney_txt);
        }
        if (this.postFreeBalance_txt != null) {
            sb.append(", postFreeBalance_txt=");
            sb.append(this.postFreeBalance_txt);
        }
        if (this.pagemsg != null) {
            sb.append(", pagemsg=");
            sb.append(this.pagemsg);
        }
        StringBuilder replace = sb.replace(0, 2, "CartData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<CartData, Builder> {
        public String ActDiscountMin;
        public String activityDiscountMoney;
        public String activityDiscountMoney_txt;
        public ActivitysData activitys;
        public AppConfigData appConfig;
        public List<BargainsData> bargains = Internal.newMutableList();
        public GatherorderInfoData gatherorder_info;
        public List<ItemsData> items = Internal.newMutableList();
        public String orderMoney;
        public String orderMoney_txt;
        public PageMessage pagemsg;
        public Boolean postFree;
        public Float postFreeBalance;
        public String postFreeBalance_txt;
        public String productMoney;
        public String productMoney_txt;
        public Integer total;
        public Integer totalWithoutGift;
        public String totalprice;

        public Builder items(List<ItemsData> list) {
            Internal.checkElementsNotNull(list);
            this.items = list;
            return this;
        }

        public Builder postFree(Boolean bool) {
            this.postFree = bool;
            return this;
        }

        public Builder productMoney(String str) {
            this.productMoney = str;
            return this;
        }

        public Builder activityDiscountMoney(String str) {
            this.activityDiscountMoney = str;
            return this;
        }

        public Builder orderMoney(String str) {
            this.orderMoney = str;
            return this;
        }

        public Builder ActDiscountMin(String str) {
            this.ActDiscountMin = str;
            return this;
        }

        public Builder total(Integer num) {
            this.total = num;
            return this;
        }

        public Builder totalWithoutGift(Integer num) {
            this.totalWithoutGift = num;
            return this;
        }

        public Builder totalprice(String str) {
            this.totalprice = str;
            return this;
        }

        public Builder activitys(ActivitysData activitysData) {
            this.activitys = activitysData;
            return this;
        }

        public Builder appConfig(AppConfigData appConfigData) {
            this.appConfig = appConfigData;
            return this;
        }

        public Builder bargains(List<BargainsData> list) {
            Internal.checkElementsNotNull(list);
            this.bargains = list;
            return this;
        }

        public Builder postFreeBalance(Float f) {
            this.postFreeBalance = f;
            return this;
        }

        public Builder gatherorder_info(GatherorderInfoData gatherorderInfoData) {
            this.gatherorder_info = gatherorderInfoData;
            return this;
        }

        public Builder productMoney_txt(String str) {
            this.productMoney_txt = str;
            return this;
        }

        public Builder orderMoney_txt(String str) {
            this.orderMoney_txt = str;
            return this;
        }

        public Builder activityDiscountMoney_txt(String str) {
            this.activityDiscountMoney_txt = str;
            return this;
        }

        public Builder postFreeBalance_txt(String str) {
            this.postFreeBalance_txt = str;
            return this;
        }

        public Builder pagemsg(PageMessage pageMessage) {
            this.pagemsg = pageMessage;
            return this;
        }

        public CartData build() {
            return new CartData(this.items, this.postFree, this.productMoney, this.activityDiscountMoney, this.orderMoney, this.ActDiscountMin, this.total, this.totalWithoutGift, this.totalprice, this.activitys, this.appConfig, this.bargains, this.postFreeBalance, this.gatherorder_info, this.productMoney_txt, this.orderMoney_txt, this.activityDiscountMoney_txt, this.postFreeBalance_txt, this.pagemsg, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_CartData extends ProtoAdapter<CartData> {
        ProtoAdapter_CartData() {
            super(FieldEncoding.LENGTH_DELIMITED, CartData.class);
        }

        public int encodedSize(CartData cartData) {
            int i = 0;
            int encodedSizeWithTag = ItemsData.ADAPTER.asRepeated().encodedSizeWithTag(2, cartData.items) + (cartData.postFree != null ? ProtoAdapter.BOOL.encodedSizeWithTag(3, cartData.postFree) : 0) + (cartData.productMoney != null ? ProtoAdapter.STRING.encodedSizeWithTag(6, cartData.productMoney) : 0) + (cartData.activityDiscountMoney != null ? ProtoAdapter.STRING.encodedSizeWithTag(9, cartData.activityDiscountMoney) : 0) + (cartData.orderMoney != null ? ProtoAdapter.STRING.encodedSizeWithTag(10, cartData.orderMoney) : 0) + (cartData.ActDiscountMin != null ? ProtoAdapter.STRING.encodedSizeWithTag(12, cartData.ActDiscountMin) : 0) + (cartData.total != null ? ProtoAdapter.INT32.encodedSizeWithTag(14, cartData.total) : 0) + (cartData.totalWithoutGift != null ? ProtoAdapter.INT32.encodedSizeWithTag(15, cartData.totalWithoutGift) : 0) + (cartData.totalprice != null ? ProtoAdapter.STRING.encodedSizeWithTag(16, cartData.totalprice) : 0) + (cartData.activitys != null ? ActivitysData.ADAPTER.encodedSizeWithTag(18, cartData.activitys) : 0) + (cartData.appConfig != null ? AppConfigData.ADAPTER.encodedSizeWithTag(19, cartData.appConfig) : 0) + BargainsData.ADAPTER.asRepeated().encodedSizeWithTag(20, cartData.bargains) + (cartData.postFreeBalance != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(23, cartData.postFreeBalance) : 0) + (cartData.gatherorder_info != null ? GatherorderInfoData.ADAPTER.encodedSizeWithTag(24, cartData.gatherorder_info) : 0) + (cartData.productMoney_txt != null ? ProtoAdapter.STRING.encodedSizeWithTag(27, cartData.productMoney_txt) : 0) + (cartData.orderMoney_txt != null ? ProtoAdapter.STRING.encodedSizeWithTag(28, cartData.orderMoney_txt) : 0) + (cartData.activityDiscountMoney_txt != null ? ProtoAdapter.STRING.encodedSizeWithTag(29, cartData.activityDiscountMoney_txt) : 0) + (cartData.postFreeBalance_txt != null ? ProtoAdapter.STRING.encodedSizeWithTag(30, cartData.postFreeBalance_txt) : 0);
            if (cartData.pagemsg != null) {
                i = PageMessage.ADAPTER.encodedSizeWithTag(31, cartData.pagemsg);
            }
            return encodedSizeWithTag + i + cartData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, CartData cartData) throws IOException {
            if (cartData.items != null) {
                ItemsData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 2, cartData.items);
            }
            if (cartData.postFree != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 3, cartData.postFree);
            }
            if (cartData.productMoney != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, cartData.productMoney);
            }
            if (cartData.activityDiscountMoney != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 9, cartData.activityDiscountMoney);
            }
            if (cartData.orderMoney != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 10, cartData.orderMoney);
            }
            if (cartData.ActDiscountMin != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 12, cartData.ActDiscountMin);
            }
            if (cartData.total != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 14, cartData.total);
            }
            if (cartData.totalWithoutGift != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 15, cartData.totalWithoutGift);
            }
            if (cartData.totalprice != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 16, cartData.totalprice);
            }
            if (cartData.activitys != null) {
                ActivitysData.ADAPTER.encodeWithTag(protoWriter, 18, cartData.activitys);
            }
            if (cartData.appConfig != null) {
                AppConfigData.ADAPTER.encodeWithTag(protoWriter, 19, cartData.appConfig);
            }
            if (cartData.bargains != null) {
                BargainsData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 20, cartData.bargains);
            }
            if (cartData.postFreeBalance != null) {
                ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 23, cartData.postFreeBalance);
            }
            if (cartData.gatherorder_info != null) {
                GatherorderInfoData.ADAPTER.encodeWithTag(protoWriter, 24, cartData.gatherorder_info);
            }
            if (cartData.productMoney_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 27, cartData.productMoney_txt);
            }
            if (cartData.orderMoney_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 28, cartData.orderMoney_txt);
            }
            if (cartData.activityDiscountMoney_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 29, cartData.activityDiscountMoney_txt);
            }
            if (cartData.postFreeBalance_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 30, cartData.postFreeBalance_txt);
            }
            if (cartData.pagemsg != null) {
                PageMessage.ADAPTER.encodeWithTag(protoWriter, 31, cartData.pagemsg);
            }
            protoWriter.writeBytes(cartData.unknownFields());
        }

        public CartData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 2:
                            builder.items.add(ItemsData.ADAPTER.decode(protoReader));
                            break;
                        case 3:
                            builder.postFree(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 6:
                            builder.productMoney(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 9:
                            builder.activityDiscountMoney(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 10:
                            builder.orderMoney(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 12:
                            builder.ActDiscountMin(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 14:
                            builder.total(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 15:
                            builder.totalWithoutGift(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 16:
                            builder.totalprice(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 18:
                            builder.activitys(ActivitysData.ADAPTER.decode(protoReader));
                            break;
                        case 19:
                            builder.appConfig(AppConfigData.ADAPTER.decode(protoReader));
                            break;
                        case 20:
                            builder.bargains.add(BargainsData.ADAPTER.decode(protoReader));
                            break;
                        case 23:
                            builder.postFreeBalance(ProtoAdapter.FLOAT.decode(protoReader));
                            break;
                        case 24:
                            builder.gatherorder_info(GatherorderInfoData.ADAPTER.decode(protoReader));
                            break;
                        case 27:
                            builder.productMoney_txt(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 28:
                            builder.orderMoney_txt(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 29:
                            builder.activityDiscountMoney_txt(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 30:
                            builder.postFreeBalance_txt(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 31:
                            builder.pagemsg(PageMessage.ADAPTER.decode(protoReader));
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

        public CartData redact(CartData cartData) {
            Builder newBuilder = cartData.newBuilder();
            Internal.redactElements(newBuilder.items, ItemsData.ADAPTER);
            if (newBuilder.activitys != null) {
                newBuilder.activitys = ActivitysData.ADAPTER.redact(newBuilder.activitys);
            }
            if (newBuilder.appConfig != null) {
                newBuilder.appConfig = AppConfigData.ADAPTER.redact(newBuilder.appConfig);
            }
            Internal.redactElements(newBuilder.bargains, BargainsData.ADAPTER);
            if (newBuilder.gatherorder_info != null) {
                newBuilder.gatherorder_info = GatherorderInfoData.ADAPTER.redact(newBuilder.gatherorder_info);
            }
            if (newBuilder.pagemsg != null) {
                newBuilder.pagemsg = PageMessage.ADAPTER.redact(newBuilder.pagemsg);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
