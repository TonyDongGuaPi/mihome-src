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

public final class ItemsData extends Message<ItemsData, Builder> {
    public static final ProtoAdapter<ItemsData> ADAPTER = new ProtoAdapter_ItemsData();
    public static final Integer DEFAULT_BUY_LIMIT = 0;
    public static final Long DEFAULT_CARTTTL = 0L;
    public static final String DEFAULT_COMMODITY_ID = "";
    public static final String DEFAULT_ELEMENTSGOODS = "";
    public static final String DEFAULT_GETTYPE = "";
    public static final String DEFAULT_GOODSID = "";
    public static final String DEFAULT_GOODS_DEALER = "";
    public static final String DEFAULT_IMAGE_URL = "";
    public static final Boolean DEFAULT_ISINSURANCE = false;
    public static final Boolean DEFAULT_IS_BATCHED = false;
    public static final Boolean DEFAULT_IS_COS = false;
    public static final Boolean DEFAULT_IS_ON_SALE = false;
    public static final String DEFAULT_ITEMID = "";
    public static final Boolean DEFAULT_ITEM_TIMEOUT = false;
    public static final String DEFAULT_ITEM_TYPE_NAME = "";
    public static final String DEFAULT_MARKET_PRICE = "";
    public static final String DEFAULT_MARKET_PRICE_TXT = "";
    public static final Integer DEFAULT_NUM = 0;
    public static final String DEFAULT_PRICE = "";
    public static final String DEFAULT_PRODUCT_BRIEF = "";
    public static final String DEFAULT_PRODUCT_ID = "";
    public static final String DEFAULT_PRODUCT_NAME = "";
    public static final Float DEFAULT_SALEPRICE = Float.valueOf(0.0f);
    public static final String DEFAULT_SALEPRICE_TXT = "";
    public static final String DEFAULT_SHOWTYPE = "";
    public static final Boolean DEFAULT_SHOW_STOCK = false;
    public static final String DEFAULT_SKU = "";
    public static final String DEFAULT_STYLE_VALUE = "";
    public static final Float DEFAULT_SUBTOTAL = Float.valueOf(0.0f);
    public static final String DEFAULT_SUBTOTAL_TXT = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 34)
    public final Integer buy_limit;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 42)
    public final Long cartTTL;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 13)
    public final String commodity_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 50)
    public final String elementsGoods;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String getType;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String goodsId;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 18)
    public final String goods_dealer;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 27)
    public final String image_url;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 25)
    public final Boolean isInsurance;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 32)
    public final Boolean is_batched;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 22)
    public final Boolean is_cos;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 29)
    public final Boolean is_on_sale;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String itemId;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 51)
    public final Boolean item_timeout;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 52)
    public final String item_type_name;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 37)
    public final String market_price;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 55)
    public final String market_price_txt;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 5)
    public final Integer num;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 36)
    public final String price;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 35)
    public final String product_brief;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 26)
    public final String product_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 23)
    public final String product_name;
    @WireField(adapter = "com.mi.global.shop.model.common.PropertiesData#ADAPTER", tag = 7)
    public final PropertiesData properties;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 12)
    public final Float salePrice;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 53)
    public final String salePrice_txt;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 47)
    public final String showType;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 44)
    public final Boolean show_stock;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 14)
    public final String sku;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 45)
    public final String style_value;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 19)
    public final Float subtotal;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 54)
    public final String subtotal_txt;

    public ItemsData(String str, String str2, String str3, Integer num2, PropertiesData propertiesData, Float f, String str4, String str5, String str6, Float f2, Boolean bool, String str7, Boolean bool2, String str8, String str9, Boolean bool3, Boolean bool4, Integer num3, String str10, String str11, String str12, Long l, Boolean bool5, String str13, String str14, String str15, Boolean bool6, String str16, String str17, String str18, String str19) {
        this(str, str2, str3, num2, propertiesData, f, str4, str5, str6, f2, bool, str7, bool2, str8, str9, bool3, bool4, num3, str10, str11, str12, l, bool5, str13, str14, str15, bool6, str16, str17, str18, str19, ByteString.EMPTY);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ItemsData(String str, String str2, String str3, Integer num2, PropertiesData propertiesData, Float f, String str4, String str5, String str6, Float f2, Boolean bool, String str7, Boolean bool2, String str8, String str9, Boolean bool3, Boolean bool4, Integer num3, String str10, String str11, String str12, Long l, Boolean bool5, String str13, String str14, String str15, Boolean bool6, String str16, String str17, String str18, String str19, ByteString byteString) {
        super(ADAPTER, byteString);
        this.itemId = str;
        this.goodsId = str2;
        this.getType = str3;
        this.num = num2;
        this.properties = propertiesData;
        this.salePrice = f;
        this.commodity_id = str4;
        this.sku = str5;
        this.goods_dealer = str6;
        this.subtotal = f2;
        this.is_cos = bool;
        this.product_name = str7;
        this.isInsurance = bool2;
        this.product_id = str8;
        this.image_url = str9;
        this.is_on_sale = bool3;
        this.is_batched = bool4;
        this.buy_limit = num3;
        this.product_brief = str10;
        this.price = str11;
        this.market_price = str12;
        this.cartTTL = l;
        this.show_stock = bool5;
        this.style_value = str13;
        this.showType = str14;
        this.elementsGoods = str15;
        this.item_timeout = bool6;
        this.item_type_name = str16;
        this.salePrice_txt = str17;
        this.subtotal_txt = str18;
        this.market_price_txt = str19;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.itemId = this.itemId;
        builder.goodsId = this.goodsId;
        builder.getType = this.getType;
        builder.num = this.num;
        builder.properties = this.properties;
        builder.salePrice = this.salePrice;
        builder.commodity_id = this.commodity_id;
        builder.sku = this.sku;
        builder.goods_dealer = this.goods_dealer;
        builder.subtotal = this.subtotal;
        builder.is_cos = this.is_cos;
        builder.product_name = this.product_name;
        builder.isInsurance = this.isInsurance;
        builder.product_id = this.product_id;
        builder.image_url = this.image_url;
        builder.is_on_sale = this.is_on_sale;
        builder.is_batched = this.is_batched;
        builder.buy_limit = this.buy_limit;
        builder.product_brief = this.product_brief;
        builder.price = this.price;
        builder.market_price = this.market_price;
        builder.cartTTL = this.cartTTL;
        builder.show_stock = this.show_stock;
        builder.style_value = this.style_value;
        builder.showType = this.showType;
        builder.elementsGoods = this.elementsGoods;
        builder.item_timeout = this.item_timeout;
        builder.item_type_name = this.item_type_name;
        builder.salePrice_txt = this.salePrice_txt;
        builder.subtotal_txt = this.subtotal_txt;
        builder.market_price_txt = this.market_price_txt;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ItemsData)) {
            return false;
        }
        ItemsData itemsData = (ItemsData) obj;
        if (!Internal.equals(unknownFields(), itemsData.unknownFields()) || !Internal.equals(this.itemId, itemsData.itemId) || !Internal.equals(this.goodsId, itemsData.goodsId) || !Internal.equals(this.getType, itemsData.getType) || !Internal.equals(this.num, itemsData.num) || !Internal.equals(this.properties, itemsData.properties) || !Internal.equals(this.salePrice, itemsData.salePrice) || !Internal.equals(this.commodity_id, itemsData.commodity_id) || !Internal.equals(this.sku, itemsData.sku) || !Internal.equals(this.goods_dealer, itemsData.goods_dealer) || !Internal.equals(this.subtotal, itemsData.subtotal) || !Internal.equals(this.is_cos, itemsData.is_cos) || !Internal.equals(this.product_name, itemsData.product_name) || !Internal.equals(this.isInsurance, itemsData.isInsurance) || !Internal.equals(this.product_id, itemsData.product_id) || !Internal.equals(this.image_url, itemsData.image_url) || !Internal.equals(this.is_on_sale, itemsData.is_on_sale) || !Internal.equals(this.is_batched, itemsData.is_batched) || !Internal.equals(this.buy_limit, itemsData.buy_limit) || !Internal.equals(this.product_brief, itemsData.product_brief) || !Internal.equals(this.price, itemsData.price) || !Internal.equals(this.market_price, itemsData.market_price) || !Internal.equals(this.cartTTL, itemsData.cartTTL) || !Internal.equals(this.show_stock, itemsData.show_stock) || !Internal.equals(this.style_value, itemsData.style_value) || !Internal.equals(this.showType, itemsData.showType) || !Internal.equals(this.elementsGoods, itemsData.elementsGoods) || !Internal.equals(this.item_timeout, itemsData.item_timeout) || !Internal.equals(this.item_type_name, itemsData.item_type_name) || !Internal.equals(this.salePrice_txt, itemsData.salePrice_txt) || !Internal.equals(this.subtotal_txt, itemsData.subtotal_txt) || !Internal.equals(this.market_price_txt, itemsData.market_price_txt)) {
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
        int hashCode = ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.itemId != null ? this.itemId.hashCode() : 0)) * 37) + (this.goodsId != null ? this.goodsId.hashCode() : 0)) * 37) + (this.getType != null ? this.getType.hashCode() : 0)) * 37) + (this.num != null ? this.num.hashCode() : 0)) * 37) + (this.properties != null ? this.properties.hashCode() : 0)) * 37) + (this.salePrice != null ? this.salePrice.hashCode() : 0)) * 37) + (this.commodity_id != null ? this.commodity_id.hashCode() : 0)) * 37) + (this.sku != null ? this.sku.hashCode() : 0)) * 37) + (this.goods_dealer != null ? this.goods_dealer.hashCode() : 0)) * 37) + (this.subtotal != null ? this.subtotal.hashCode() : 0)) * 37) + (this.is_cos != null ? this.is_cos.hashCode() : 0)) * 37) + (this.product_name != null ? this.product_name.hashCode() : 0)) * 37) + (this.isInsurance != null ? this.isInsurance.hashCode() : 0)) * 37) + (this.product_id != null ? this.product_id.hashCode() : 0)) * 37) + (this.image_url != null ? this.image_url.hashCode() : 0)) * 37) + (this.is_on_sale != null ? this.is_on_sale.hashCode() : 0)) * 37) + (this.is_batched != null ? this.is_batched.hashCode() : 0)) * 37) + (this.buy_limit != null ? this.buy_limit.hashCode() : 0)) * 37) + (this.product_brief != null ? this.product_brief.hashCode() : 0)) * 37) + (this.price != null ? this.price.hashCode() : 0)) * 37) + (this.market_price != null ? this.market_price.hashCode() : 0)) * 37) + (this.cartTTL != null ? this.cartTTL.hashCode() : 0)) * 37) + (this.show_stock != null ? this.show_stock.hashCode() : 0)) * 37) + (this.style_value != null ? this.style_value.hashCode() : 0)) * 37) + (this.showType != null ? this.showType.hashCode() : 0)) * 37) + (this.elementsGoods != null ? this.elementsGoods.hashCode() : 0)) * 37) + (this.item_timeout != null ? this.item_timeout.hashCode() : 0)) * 37) + (this.item_type_name != null ? this.item_type_name.hashCode() : 0)) * 37) + (this.salePrice_txt != null ? this.salePrice_txt.hashCode() : 0)) * 37) + (this.subtotal_txt != null ? this.subtotal_txt.hashCode() : 0)) * 37;
        if (this.market_price_txt != null) {
            i2 = this.market_price_txt.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.itemId != null) {
            sb.append(", itemId=");
            sb.append(this.itemId);
        }
        if (this.goodsId != null) {
            sb.append(", goodsId=");
            sb.append(this.goodsId);
        }
        if (this.getType != null) {
            sb.append(", getType=");
            sb.append(this.getType);
        }
        if (this.num != null) {
            sb.append(", num=");
            sb.append(this.num);
        }
        if (this.properties != null) {
            sb.append(", properties=");
            sb.append(this.properties);
        }
        if (this.salePrice != null) {
            sb.append(", salePrice=");
            sb.append(this.salePrice);
        }
        if (this.commodity_id != null) {
            sb.append(", commodity_id=");
            sb.append(this.commodity_id);
        }
        if (this.sku != null) {
            sb.append(", sku=");
            sb.append(this.sku);
        }
        if (this.goods_dealer != null) {
            sb.append(", goods_dealer=");
            sb.append(this.goods_dealer);
        }
        if (this.subtotal != null) {
            sb.append(", subtotal=");
            sb.append(this.subtotal);
        }
        if (this.is_cos != null) {
            sb.append(", is_cos=");
            sb.append(this.is_cos);
        }
        if (this.product_name != null) {
            sb.append(", product_name=");
            sb.append(this.product_name);
        }
        if (this.isInsurance != null) {
            sb.append(", isInsurance=");
            sb.append(this.isInsurance);
        }
        if (this.product_id != null) {
            sb.append(", product_id=");
            sb.append(this.product_id);
        }
        if (this.image_url != null) {
            sb.append(", image_url=");
            sb.append(this.image_url);
        }
        if (this.is_on_sale != null) {
            sb.append(", is_on_sale=");
            sb.append(this.is_on_sale);
        }
        if (this.is_batched != null) {
            sb.append(", is_batched=");
            sb.append(this.is_batched);
        }
        if (this.buy_limit != null) {
            sb.append(", buy_limit=");
            sb.append(this.buy_limit);
        }
        if (this.product_brief != null) {
            sb.append(", product_brief=");
            sb.append(this.product_brief);
        }
        if (this.price != null) {
            sb.append(", price=");
            sb.append(this.price);
        }
        if (this.market_price != null) {
            sb.append(", market_price=");
            sb.append(this.market_price);
        }
        if (this.cartTTL != null) {
            sb.append(", cartTTL=");
            sb.append(this.cartTTL);
        }
        if (this.show_stock != null) {
            sb.append(", show_stock=");
            sb.append(this.show_stock);
        }
        if (this.style_value != null) {
            sb.append(", style_value=");
            sb.append(this.style_value);
        }
        if (this.showType != null) {
            sb.append(", showType=");
            sb.append(this.showType);
        }
        if (this.elementsGoods != null) {
            sb.append(", elementsGoods=");
            sb.append(this.elementsGoods);
        }
        if (this.item_timeout != null) {
            sb.append(", item_timeout=");
            sb.append(this.item_timeout);
        }
        if (this.item_type_name != null) {
            sb.append(", item_type_name=");
            sb.append(this.item_type_name);
        }
        if (this.salePrice_txt != null) {
            sb.append(", salePrice_txt=");
            sb.append(this.salePrice_txt);
        }
        if (this.subtotal_txt != null) {
            sb.append(", subtotal_txt=");
            sb.append(this.subtotal_txt);
        }
        if (this.market_price_txt != null) {
            sb.append(", market_price_txt=");
            sb.append(this.market_price_txt);
        }
        StringBuilder replace = sb.replace(0, 2, "ItemsData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<ItemsData, Builder> {
        public Integer buy_limit;
        public Long cartTTL;
        public String commodity_id;
        public String elementsGoods;
        public String getType;
        public String goodsId;
        public String goods_dealer;
        public String image_url;
        public Boolean isInsurance;
        public Boolean is_batched;
        public Boolean is_cos;
        public Boolean is_on_sale;
        public String itemId;
        public Boolean item_timeout;
        public String item_type_name;
        public String market_price;
        public String market_price_txt;
        public Integer num;
        public String price;
        public String product_brief;
        public String product_id;
        public String product_name;
        public PropertiesData properties;
        public Float salePrice;
        public String salePrice_txt;
        public String showType;
        public Boolean show_stock;
        public String sku;
        public String style_value;
        public Float subtotal;
        public String subtotal_txt;

        public Builder itemId(String str) {
            this.itemId = str;
            return this;
        }

        public Builder goodsId(String str) {
            this.goodsId = str;
            return this;
        }

        public Builder getType(String str) {
            this.getType = str;
            return this;
        }

        public Builder num(Integer num2) {
            this.num = num2;
            return this;
        }

        public Builder properties(PropertiesData propertiesData) {
            this.properties = propertiesData;
            return this;
        }

        public Builder salePrice(Float f) {
            this.salePrice = f;
            return this;
        }

        public Builder commodity_id(String str) {
            this.commodity_id = str;
            return this;
        }

        public Builder sku(String str) {
            this.sku = str;
            return this;
        }

        public Builder goods_dealer(String str) {
            this.goods_dealer = str;
            return this;
        }

        public Builder subtotal(Float f) {
            this.subtotal = f;
            return this;
        }

        public Builder is_cos(Boolean bool) {
            this.is_cos = bool;
            return this;
        }

        public Builder product_name(String str) {
            this.product_name = str;
            return this;
        }

        public Builder isInsurance(Boolean bool) {
            this.isInsurance = bool;
            return this;
        }

        public Builder product_id(String str) {
            this.product_id = str;
            return this;
        }

        public Builder image_url(String str) {
            this.image_url = str;
            return this;
        }

        public Builder is_on_sale(Boolean bool) {
            this.is_on_sale = bool;
            return this;
        }

        public Builder is_batched(Boolean bool) {
            this.is_batched = bool;
            return this;
        }

        public Builder buy_limit(Integer num2) {
            this.buy_limit = num2;
            return this;
        }

        public Builder product_brief(String str) {
            this.product_brief = str;
            return this;
        }

        public Builder price(String str) {
            this.price = str;
            return this;
        }

        public Builder market_price(String str) {
            this.market_price = str;
            return this;
        }

        public Builder cartTTL(Long l) {
            this.cartTTL = l;
            return this;
        }

        public Builder show_stock(Boolean bool) {
            this.show_stock = bool;
            return this;
        }

        public Builder style_value(String str) {
            this.style_value = str;
            return this;
        }

        public Builder showType(String str) {
            this.showType = str;
            return this;
        }

        public Builder elementsGoods(String str) {
            this.elementsGoods = str;
            return this;
        }

        public Builder item_timeout(Boolean bool) {
            this.item_timeout = bool;
            return this;
        }

        public Builder item_type_name(String str) {
            this.item_type_name = str;
            return this;
        }

        public Builder salePrice_txt(String str) {
            this.salePrice_txt = str;
            return this;
        }

        public Builder subtotal_txt(String str) {
            this.subtotal_txt = str;
            return this;
        }

        public Builder market_price_txt(String str) {
            this.market_price_txt = str;
            return this;
        }

        public ItemsData build() {
            return new ItemsData(this.itemId, this.goodsId, this.getType, this.num, this.properties, this.salePrice, this.commodity_id, this.sku, this.goods_dealer, this.subtotal, this.is_cos, this.product_name, this.isInsurance, this.product_id, this.image_url, this.is_on_sale, this.is_batched, this.buy_limit, this.product_brief, this.price, this.market_price, this.cartTTL, this.show_stock, this.style_value, this.showType, this.elementsGoods, this.item_timeout, this.item_type_name, this.salePrice_txt, this.subtotal_txt, this.market_price_txt, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_ItemsData extends ProtoAdapter<ItemsData> {
        ProtoAdapter_ItemsData() {
            super(FieldEncoding.LENGTH_DELIMITED, ItemsData.class);
        }

        public int encodedSize(ItemsData itemsData) {
            int i = 0;
            int encodedSizeWithTag = (itemsData.itemId != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, itemsData.itemId) : 0) + (itemsData.goodsId != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, itemsData.goodsId) : 0) + (itemsData.getType != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, itemsData.getType) : 0) + (itemsData.num != null ? ProtoAdapter.INT32.encodedSizeWithTag(5, itemsData.num) : 0) + (itemsData.properties != null ? PropertiesData.ADAPTER.encodedSizeWithTag(7, itemsData.properties) : 0) + (itemsData.salePrice != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(12, itemsData.salePrice) : 0) + (itemsData.commodity_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(13, itemsData.commodity_id) : 0) + (itemsData.sku != null ? ProtoAdapter.STRING.encodedSizeWithTag(14, itemsData.sku) : 0) + (itemsData.goods_dealer != null ? ProtoAdapter.STRING.encodedSizeWithTag(18, itemsData.goods_dealer) : 0) + (itemsData.subtotal != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(19, itemsData.subtotal) : 0) + (itemsData.is_cos != null ? ProtoAdapter.BOOL.encodedSizeWithTag(22, itemsData.is_cos) : 0) + (itemsData.product_name != null ? ProtoAdapter.STRING.encodedSizeWithTag(23, itemsData.product_name) : 0) + (itemsData.isInsurance != null ? ProtoAdapter.BOOL.encodedSizeWithTag(25, itemsData.isInsurance) : 0) + (itemsData.product_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(26, itemsData.product_id) : 0) + (itemsData.image_url != null ? ProtoAdapter.STRING.encodedSizeWithTag(27, itemsData.image_url) : 0) + (itemsData.is_on_sale != null ? ProtoAdapter.BOOL.encodedSizeWithTag(29, itemsData.is_on_sale) : 0) + (itemsData.is_batched != null ? ProtoAdapter.BOOL.encodedSizeWithTag(32, itemsData.is_batched) : 0) + (itemsData.buy_limit != null ? ProtoAdapter.INT32.encodedSizeWithTag(34, itemsData.buy_limit) : 0) + (itemsData.product_brief != null ? ProtoAdapter.STRING.encodedSizeWithTag(35, itemsData.product_brief) : 0) + (itemsData.price != null ? ProtoAdapter.STRING.encodedSizeWithTag(36, itemsData.price) : 0) + (itemsData.market_price != null ? ProtoAdapter.STRING.encodedSizeWithTag(37, itemsData.market_price) : 0) + (itemsData.cartTTL != null ? ProtoAdapter.INT64.encodedSizeWithTag(42, itemsData.cartTTL) : 0) + (itemsData.show_stock != null ? ProtoAdapter.BOOL.encodedSizeWithTag(44, itemsData.show_stock) : 0) + (itemsData.style_value != null ? ProtoAdapter.STRING.encodedSizeWithTag(45, itemsData.style_value) : 0) + (itemsData.showType != null ? ProtoAdapter.STRING.encodedSizeWithTag(47, itemsData.showType) : 0) + (itemsData.elementsGoods != null ? ProtoAdapter.STRING.encodedSizeWithTag(50, itemsData.elementsGoods) : 0) + (itemsData.item_timeout != null ? ProtoAdapter.BOOL.encodedSizeWithTag(51, itemsData.item_timeout) : 0) + (itemsData.item_type_name != null ? ProtoAdapter.STRING.encodedSizeWithTag(52, itemsData.item_type_name) : 0) + (itemsData.salePrice_txt != null ? ProtoAdapter.STRING.encodedSizeWithTag(53, itemsData.salePrice_txt) : 0) + (itemsData.subtotal_txt != null ? ProtoAdapter.STRING.encodedSizeWithTag(54, itemsData.subtotal_txt) : 0);
            if (itemsData.market_price_txt != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(55, itemsData.market_price_txt);
            }
            return encodedSizeWithTag + i + itemsData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, ItemsData itemsData) throws IOException {
            if (itemsData.itemId != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, itemsData.itemId);
            }
            if (itemsData.goodsId != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, itemsData.goodsId);
            }
            if (itemsData.getType != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, itemsData.getType);
            }
            if (itemsData.num != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 5, itemsData.num);
            }
            if (itemsData.properties != null) {
                PropertiesData.ADAPTER.encodeWithTag(protoWriter, 7, itemsData.properties);
            }
            if (itemsData.salePrice != null) {
                ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 12, itemsData.salePrice);
            }
            if (itemsData.commodity_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 13, itemsData.commodity_id);
            }
            if (itemsData.sku != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 14, itemsData.sku);
            }
            if (itemsData.goods_dealer != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 18, itemsData.goods_dealer);
            }
            if (itemsData.subtotal != null) {
                ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 19, itemsData.subtotal);
            }
            if (itemsData.is_cos != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 22, itemsData.is_cos);
            }
            if (itemsData.product_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 23, itemsData.product_name);
            }
            if (itemsData.isInsurance != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 25, itemsData.isInsurance);
            }
            if (itemsData.product_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 26, itemsData.product_id);
            }
            if (itemsData.image_url != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 27, itemsData.image_url);
            }
            if (itemsData.is_on_sale != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 29, itemsData.is_on_sale);
            }
            if (itemsData.is_batched != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 32, itemsData.is_batched);
            }
            if (itemsData.buy_limit != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 34, itemsData.buy_limit);
            }
            if (itemsData.product_brief != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 35, itemsData.product_brief);
            }
            if (itemsData.price != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 36, itemsData.price);
            }
            if (itemsData.market_price != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 37, itemsData.market_price);
            }
            if (itemsData.cartTTL != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 42, itemsData.cartTTL);
            }
            if (itemsData.show_stock != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 44, itemsData.show_stock);
            }
            if (itemsData.style_value != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 45, itemsData.style_value);
            }
            if (itemsData.showType != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 47, itemsData.showType);
            }
            if (itemsData.elementsGoods != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 50, itemsData.elementsGoods);
            }
            if (itemsData.item_timeout != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 51, itemsData.item_timeout);
            }
            if (itemsData.item_type_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 52, itemsData.item_type_name);
            }
            if (itemsData.salePrice_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 53, itemsData.salePrice_txt);
            }
            if (itemsData.subtotal_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 54, itemsData.subtotal_txt);
            }
            if (itemsData.market_price_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 55, itemsData.market_price_txt);
            }
            protoWriter.writeBytes(itemsData.unknownFields());
        }

        public ItemsData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.itemId(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.goodsId(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.getType(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 5:
                            builder.num(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 7:
                            builder.properties(PropertiesData.ADAPTER.decode(protoReader));
                            break;
                        case 12:
                            builder.salePrice(ProtoAdapter.FLOAT.decode(protoReader));
                            break;
                        case 13:
                            builder.commodity_id(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 14:
                            builder.sku(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 18:
                            builder.goods_dealer(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 19:
                            builder.subtotal(ProtoAdapter.FLOAT.decode(protoReader));
                            break;
                        case 22:
                            builder.is_cos(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 23:
                            builder.product_name(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 25:
                            builder.isInsurance(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 26:
                            builder.product_id(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 27:
                            builder.image_url(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 29:
                            builder.is_on_sale(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 32:
                            builder.is_batched(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 34:
                            builder.buy_limit(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 35:
                            builder.product_brief(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 36:
                            builder.price(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 37:
                            builder.market_price(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 42:
                            builder.cartTTL(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 44:
                            builder.show_stock(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 45:
                            builder.style_value(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 47:
                            builder.showType(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 50:
                            builder.elementsGoods(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 51:
                            builder.item_timeout(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 52:
                            builder.item_type_name(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 53:
                            builder.salePrice_txt(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 54:
                            builder.subtotal_txt(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 55:
                            builder.market_price_txt(ProtoAdapter.STRING.decode(protoReader));
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

        public ItemsData redact(ItemsData itemsData) {
            Builder newBuilder = itemsData.newBuilder();
            if (newBuilder.properties != null) {
                newBuilder.properties = PropertiesData.ADAPTER.redact(newBuilder.properties);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
