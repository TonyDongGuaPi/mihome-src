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
import java.util.List;
import okio.ByteString;

public final class ExtentionsData extends Message<ExtentionsData, Builder> {
    public static final ProtoAdapter<ExtentionsData> ADAPTER = new ProtoAdapter_ExtentionsData();
    public static final String DEFAULT_ADAPT_DESC = "";
    public static final String DEFAULT_BIGTAP_SWITCH = "";
    public static final String DEFAULT_CARTTTL = "";
    public static final String DEFAULT_CROWDFUNDING_ENDTIME = "";
    public static final String DEFAULT_DEALER = "";
    public static final String DEFAULT_GOODS_DEALER = "";
    public static final String DEFAULT_GOODS_LIMIT_BUY = "";
    public static final String DEFAULT_HAS_BATTERY = "";
    public static final String DEFAULT_IS_ALONE_BUY = "";
    public static final String DEFAULT_IS_CHECKOUT_BARGAIN = "";
    public static final String DEFAULT_IS_PROMOTION = "";
    public static final String DEFAULT_ORDERTTL = "";
    public static final String DEFAULT_PUSH_TIME = "";
    public static final String DEFAULT_SELF_GET = "";
    public static final String DEFAULT_URL_PRODUCT = "";
    public static final String DEFAULT_URL_SPECIFIC = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", label = WireField.Label.REPEATED, tag = 1)
    public final List<String> adapt;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 13)
    public final String adapt_desc;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String bigtap_switch;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String cartTTL;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 16)
    public final String crowdfunding_endtime;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 17)
    public final String dealer;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 15)
    public final String goods_dealer;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String goods_limit_buy;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String has_battery;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final String is_alone_buy;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String is_checkout_bargain;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 14)
    public final String is_promotion;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String orderTTL;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 12)
    public final String push_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String self_get;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 11)
    public final String url_product;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 10)
    public final String url_specific;

    public ExtentionsData(List<String> list, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16) {
        this(list, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, ByteString.EMPTY);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExtentionsData(List<String> list, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, ByteString byteString) {
        super(ADAPTER, byteString);
        List<String> list2 = list;
        this.adapt = Internal.immutableCopyOf("adapt", list);
        this.self_get = str;
        this.orderTTL = str2;
        this.is_checkout_bargain = str3;
        this.goods_limit_buy = str4;
        this.has_battery = str5;
        this.bigtap_switch = str6;
        this.cartTTL = str7;
        this.is_alone_buy = str8;
        this.url_specific = str9;
        this.url_product = str10;
        this.push_time = str11;
        this.adapt_desc = str12;
        this.is_promotion = str13;
        this.goods_dealer = str14;
        this.crowdfunding_endtime = str15;
        this.dealer = str16;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.adapt = Internal.copyOf("adapt", this.adapt);
        builder.self_get = this.self_get;
        builder.orderTTL = this.orderTTL;
        builder.is_checkout_bargain = this.is_checkout_bargain;
        builder.goods_limit_buy = this.goods_limit_buy;
        builder.has_battery = this.has_battery;
        builder.bigtap_switch = this.bigtap_switch;
        builder.cartTTL = this.cartTTL;
        builder.is_alone_buy = this.is_alone_buy;
        builder.url_specific = this.url_specific;
        builder.url_product = this.url_product;
        builder.push_time = this.push_time;
        builder.adapt_desc = this.adapt_desc;
        builder.is_promotion = this.is_promotion;
        builder.goods_dealer = this.goods_dealer;
        builder.crowdfunding_endtime = this.crowdfunding_endtime;
        builder.dealer = this.dealer;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExtentionsData)) {
            return false;
        }
        ExtentionsData extentionsData = (ExtentionsData) obj;
        if (!Internal.equals(unknownFields(), extentionsData.unknownFields()) || !Internal.equals(this.adapt, extentionsData.adapt) || !Internal.equals(this.self_get, extentionsData.self_get) || !Internal.equals(this.orderTTL, extentionsData.orderTTL) || !Internal.equals(this.is_checkout_bargain, extentionsData.is_checkout_bargain) || !Internal.equals(this.goods_limit_buy, extentionsData.goods_limit_buy) || !Internal.equals(this.has_battery, extentionsData.has_battery) || !Internal.equals(this.bigtap_switch, extentionsData.bigtap_switch) || !Internal.equals(this.cartTTL, extentionsData.cartTTL) || !Internal.equals(this.is_alone_buy, extentionsData.is_alone_buy) || !Internal.equals(this.url_specific, extentionsData.url_specific) || !Internal.equals(this.url_product, extentionsData.url_product) || !Internal.equals(this.push_time, extentionsData.push_time) || !Internal.equals(this.adapt_desc, extentionsData.adapt_desc) || !Internal.equals(this.is_promotion, extentionsData.is_promotion) || !Internal.equals(this.goods_dealer, extentionsData.goods_dealer) || !Internal.equals(this.crowdfunding_endtime, extentionsData.crowdfunding_endtime) || !Internal.equals(this.dealer, extentionsData.dealer)) {
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
        int hashCode = ((((((((((((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.adapt != null ? this.adapt.hashCode() : 1)) * 37) + (this.self_get != null ? this.self_get.hashCode() : 0)) * 37) + (this.orderTTL != null ? this.orderTTL.hashCode() : 0)) * 37) + (this.is_checkout_bargain != null ? this.is_checkout_bargain.hashCode() : 0)) * 37) + (this.goods_limit_buy != null ? this.goods_limit_buy.hashCode() : 0)) * 37) + (this.has_battery != null ? this.has_battery.hashCode() : 0)) * 37) + (this.bigtap_switch != null ? this.bigtap_switch.hashCode() : 0)) * 37) + (this.cartTTL != null ? this.cartTTL.hashCode() : 0)) * 37) + (this.is_alone_buy != null ? this.is_alone_buy.hashCode() : 0)) * 37) + (this.url_specific != null ? this.url_specific.hashCode() : 0)) * 37) + (this.url_product != null ? this.url_product.hashCode() : 0)) * 37) + (this.push_time != null ? this.push_time.hashCode() : 0)) * 37) + (this.adapt_desc != null ? this.adapt_desc.hashCode() : 0)) * 37) + (this.is_promotion != null ? this.is_promotion.hashCode() : 0)) * 37) + (this.goods_dealer != null ? this.goods_dealer.hashCode() : 0)) * 37) + (this.crowdfunding_endtime != null ? this.crowdfunding_endtime.hashCode() : 0)) * 37;
        if (this.dealer != null) {
            i2 = this.dealer.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.adapt != null) {
            sb.append(", adapt=");
            sb.append(this.adapt);
        }
        if (this.self_get != null) {
            sb.append(", self_get=");
            sb.append(this.self_get);
        }
        if (this.orderTTL != null) {
            sb.append(", orderTTL=");
            sb.append(this.orderTTL);
        }
        if (this.is_checkout_bargain != null) {
            sb.append(", is_checkout_bargain=");
            sb.append(this.is_checkout_bargain);
        }
        if (this.goods_limit_buy != null) {
            sb.append(", goods_limit_buy=");
            sb.append(this.goods_limit_buy);
        }
        if (this.has_battery != null) {
            sb.append(", has_battery=");
            sb.append(this.has_battery);
        }
        if (this.bigtap_switch != null) {
            sb.append(", bigtap_switch=");
            sb.append(this.bigtap_switch);
        }
        if (this.cartTTL != null) {
            sb.append(", cartTTL=");
            sb.append(this.cartTTL);
        }
        if (this.is_alone_buy != null) {
            sb.append(", is_alone_buy=");
            sb.append(this.is_alone_buy);
        }
        if (this.url_specific != null) {
            sb.append(", url_specific=");
            sb.append(this.url_specific);
        }
        if (this.url_product != null) {
            sb.append(", url_product=");
            sb.append(this.url_product);
        }
        if (this.push_time != null) {
            sb.append(", push_time=");
            sb.append(this.push_time);
        }
        if (this.adapt_desc != null) {
            sb.append(", adapt_desc=");
            sb.append(this.adapt_desc);
        }
        if (this.is_promotion != null) {
            sb.append(", is_promotion=");
            sb.append(this.is_promotion);
        }
        if (this.goods_dealer != null) {
            sb.append(", goods_dealer=");
            sb.append(this.goods_dealer);
        }
        if (this.crowdfunding_endtime != null) {
            sb.append(", crowdfunding_endtime=");
            sb.append(this.crowdfunding_endtime);
        }
        if (this.dealer != null) {
            sb.append(", dealer=");
            sb.append(this.dealer);
        }
        StringBuilder replace = sb.replace(0, 2, "ExtentionsData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<ExtentionsData, Builder> {
        public List<String> adapt = Internal.newMutableList();
        public String adapt_desc;
        public String bigtap_switch;
        public String cartTTL;
        public String crowdfunding_endtime;
        public String dealer;
        public String goods_dealer;
        public String goods_limit_buy;
        public String has_battery;
        public String is_alone_buy;
        public String is_checkout_bargain;
        public String is_promotion;
        public String orderTTL;
        public String push_time;
        public String self_get;
        public String url_product;
        public String url_specific;

        public Builder adapt(List<String> list) {
            Internal.checkElementsNotNull(list);
            this.adapt = list;
            return this;
        }

        public Builder self_get(String str) {
            this.self_get = str;
            return this;
        }

        public Builder orderTTL(String str) {
            this.orderTTL = str;
            return this;
        }

        public Builder is_checkout_bargain(String str) {
            this.is_checkout_bargain = str;
            return this;
        }

        public Builder goods_limit_buy(String str) {
            this.goods_limit_buy = str;
            return this;
        }

        public Builder has_battery(String str) {
            this.has_battery = str;
            return this;
        }

        public Builder bigtap_switch(String str) {
            this.bigtap_switch = str;
            return this;
        }

        public Builder cartTTL(String str) {
            this.cartTTL = str;
            return this;
        }

        public Builder is_alone_buy(String str) {
            this.is_alone_buy = str;
            return this;
        }

        public Builder url_specific(String str) {
            this.url_specific = str;
            return this;
        }

        public Builder url_product(String str) {
            this.url_product = str;
            return this;
        }

        public Builder push_time(String str) {
            this.push_time = str;
            return this;
        }

        public Builder adapt_desc(String str) {
            this.adapt_desc = str;
            return this;
        }

        public Builder is_promotion(String str) {
            this.is_promotion = str;
            return this;
        }

        public Builder goods_dealer(String str) {
            this.goods_dealer = str;
            return this;
        }

        public Builder crowdfunding_endtime(String str) {
            this.crowdfunding_endtime = str;
            return this;
        }

        public Builder dealer(String str) {
            this.dealer = str;
            return this;
        }

        public ExtentionsData build() {
            return new ExtentionsData(this.adapt, this.self_get, this.orderTTL, this.is_checkout_bargain, this.goods_limit_buy, this.has_battery, this.bigtap_switch, this.cartTTL, this.is_alone_buy, this.url_specific, this.url_product, this.push_time, this.adapt_desc, this.is_promotion, this.goods_dealer, this.crowdfunding_endtime, this.dealer, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_ExtentionsData extends ProtoAdapter<ExtentionsData> {
        ProtoAdapter_ExtentionsData() {
            super(FieldEncoding.LENGTH_DELIMITED, ExtentionsData.class);
        }

        public int encodedSize(ExtentionsData extentionsData) {
            int i = 0;
            int encodedSizeWithTag = ProtoAdapter.STRING.asRepeated().encodedSizeWithTag(1, extentionsData.adapt) + (extentionsData.self_get != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, extentionsData.self_get) : 0) + (extentionsData.orderTTL != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, extentionsData.orderTTL) : 0) + (extentionsData.is_checkout_bargain != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, extentionsData.is_checkout_bargain) : 0) + (extentionsData.goods_limit_buy != null ? ProtoAdapter.STRING.encodedSizeWithTag(5, extentionsData.goods_limit_buy) : 0) + (extentionsData.has_battery != null ? ProtoAdapter.STRING.encodedSizeWithTag(6, extentionsData.has_battery) : 0) + (extentionsData.bigtap_switch != null ? ProtoAdapter.STRING.encodedSizeWithTag(7, extentionsData.bigtap_switch) : 0) + (extentionsData.cartTTL != null ? ProtoAdapter.STRING.encodedSizeWithTag(8, extentionsData.cartTTL) : 0) + (extentionsData.is_alone_buy != null ? ProtoAdapter.STRING.encodedSizeWithTag(9, extentionsData.is_alone_buy) : 0) + (extentionsData.url_specific != null ? ProtoAdapter.STRING.encodedSizeWithTag(10, extentionsData.url_specific) : 0) + (extentionsData.url_product != null ? ProtoAdapter.STRING.encodedSizeWithTag(11, extentionsData.url_product) : 0) + (extentionsData.push_time != null ? ProtoAdapter.STRING.encodedSizeWithTag(12, extentionsData.push_time) : 0) + (extentionsData.adapt_desc != null ? ProtoAdapter.STRING.encodedSizeWithTag(13, extentionsData.adapt_desc) : 0) + (extentionsData.is_promotion != null ? ProtoAdapter.STRING.encodedSizeWithTag(14, extentionsData.is_promotion) : 0) + (extentionsData.goods_dealer != null ? ProtoAdapter.STRING.encodedSizeWithTag(15, extentionsData.goods_dealer) : 0) + (extentionsData.crowdfunding_endtime != null ? ProtoAdapter.STRING.encodedSizeWithTag(16, extentionsData.crowdfunding_endtime) : 0);
            if (extentionsData.dealer != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(17, extentionsData.dealer);
            }
            return encodedSizeWithTag + i + extentionsData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, ExtentionsData extentionsData) throws IOException {
            if (extentionsData.adapt != null) {
                ProtoAdapter.STRING.asRepeated().encodeWithTag(protoWriter, 1, extentionsData.adapt);
            }
            if (extentionsData.self_get != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, extentionsData.self_get);
            }
            if (extentionsData.orderTTL != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, extentionsData.orderTTL);
            }
            if (extentionsData.is_checkout_bargain != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, extentionsData.is_checkout_bargain);
            }
            if (extentionsData.goods_limit_buy != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, extentionsData.goods_limit_buy);
            }
            if (extentionsData.has_battery != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, extentionsData.has_battery);
            }
            if (extentionsData.bigtap_switch != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, extentionsData.bigtap_switch);
            }
            if (extentionsData.cartTTL != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, extentionsData.cartTTL);
            }
            if (extentionsData.is_alone_buy != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 9, extentionsData.is_alone_buy);
            }
            if (extentionsData.url_specific != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 10, extentionsData.url_specific);
            }
            if (extentionsData.url_product != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 11, extentionsData.url_product);
            }
            if (extentionsData.push_time != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 12, extentionsData.push_time);
            }
            if (extentionsData.adapt_desc != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 13, extentionsData.adapt_desc);
            }
            if (extentionsData.is_promotion != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 14, extentionsData.is_promotion);
            }
            if (extentionsData.goods_dealer != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 15, extentionsData.goods_dealer);
            }
            if (extentionsData.crowdfunding_endtime != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 16, extentionsData.crowdfunding_endtime);
            }
            if (extentionsData.dealer != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 17, extentionsData.dealer);
            }
            protoWriter.writeBytes(extentionsData.unknownFields());
        }

        public ExtentionsData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.adapt.add(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.self_get(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.orderTTL(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.is_checkout_bargain(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 5:
                            builder.goods_limit_buy(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 6:
                            builder.has_battery(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 7:
                            builder.bigtap_switch(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 8:
                            builder.cartTTL(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 9:
                            builder.is_alone_buy(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 10:
                            builder.url_specific(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 11:
                            builder.url_product(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 12:
                            builder.push_time(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 13:
                            builder.adapt_desc(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 14:
                            builder.is_promotion(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 15:
                            builder.goods_dealer(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 16:
                            builder.crowdfunding_endtime(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 17:
                            builder.dealer(ProtoAdapter.STRING.decode(protoReader));
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

        public ExtentionsData redact(ExtentionsData extentionsData) {
            Builder newBuilder = extentionsData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
