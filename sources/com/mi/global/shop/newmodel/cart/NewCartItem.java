package com.mi.global.shop.newmodel.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewCartItem implements Parcelable {
    public static final Parcelable.Creator<NewCartItem> CREATOR = new Parcelable.Creator<NewCartItem>() {
        public NewCartItem createFromParcel(Parcel parcel) {
            return new NewCartItem(parcel);
        }

        public NewCartItem[] newArray(int i) {
            return new NewCartItem[i];
        }
    };
    @SerializedName("buy_limit")
    public int buy_limit;
    @SerializedName("cartTTL")
    public long cartTTL;
    @SerializedName("commodity_id")
    public String commodity_id;
    @SerializedName("elementsGoods")
    public String elementsGoods;
    @SerializedName("getType")
    public String getType;
    @SerializedName("goodsId")
    public String goodsId;
    @SerializedName("goods_dealer")
    public String goods_dealer;
    @SerializedName("image_url")
    public String image_url;
    @SerializedName("isInsurance")
    public boolean isInsurance;
    @SerializedName("is_batched")
    public boolean is_batched;
    @SerializedName("is_cos")
    public boolean is_cos;
    @SerializedName("is_on_sale")
    public boolean is_on_sale;
    @SerializedName("itemId")
    public String itemId;
    @SerializedName("item_timeout")
    public boolean item_timeout;
    @SerializedName("item_type_name")
    public String item_type_name;
    @SerializedName("jump_url")
    public String jump_url;
    @SerializedName("marketPrice_txt")
    public String marketPrice_txt;
    @SerializedName("market_price")
    public String market_price;
    @SerializedName("num")
    public int num;
    @SerializedName("price")
    public String price;
    @SerializedName("product_brief")
    public String product_brief;
    @SerializedName("product_id")
    public String product_id;
    @SerializedName("product_name")
    public String product_name;
    @SerializedName("properties")
    public NewCartProperty properties;
    @SerializedName("salePrice")
    public float salePrice;
    @SerializedName("salePrice_txt")
    public String salePrice_txt;
    @SerializedName("showType")
    public String showType;
    @SerializedName("show_stock")
    public boolean show_stock;
    @SerializedName("sku")
    public String sku;
    @SerializedName("style_value")
    public String style_value;
    @SerializedName("subtotal")
    public float subtotal;
    @SerializedName("subtotal_txt")
    public String subtotal_txt;
    public boolean timeout;

    public int describeContents() {
        return 0;
    }

    public static NewCartItem decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewCartItem decode(ProtoReader protoReader) throws IOException {
        NewCartItem newCartItem = new NewCartItem();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newCartItem.itemId = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newCartItem.goodsId = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        newCartItem.getType = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 5:
                        newCartItem.num = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 7:
                        newCartItem.properties = NewCartProperty.decode(protoReader);
                        break;
                    case 12:
                        newCartItem.salePrice = ProtoAdapter.FLOAT.decode(protoReader).floatValue();
                        break;
                    case 13:
                        newCartItem.commodity_id = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 14:
                        newCartItem.sku = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 18:
                        newCartItem.goods_dealer = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 19:
                        newCartItem.subtotal = ProtoAdapter.FLOAT.decode(protoReader).floatValue();
                        break;
                    case 22:
                        newCartItem.is_cos = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 23:
                        newCartItem.product_name = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 25:
                        newCartItem.isInsurance = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 26:
                        newCartItem.product_id = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 27:
                        newCartItem.image_url = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 29:
                        newCartItem.is_on_sale = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 32:
                        newCartItem.is_batched = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 34:
                        newCartItem.buy_limit = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 35:
                        newCartItem.product_brief = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 36:
                        newCartItem.price = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 37:
                        newCartItem.market_price = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 42:
                        newCartItem.cartTTL = ProtoAdapter.INT64.decode(protoReader).longValue();
                        break;
                    case 44:
                        newCartItem.show_stock = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 45:
                        newCartItem.style_value = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 47:
                        newCartItem.showType = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 50:
                        newCartItem.elementsGoods = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 51:
                        newCartItem.item_timeout = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 52:
                        newCartItem.item_type_name = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 53:
                        newCartItem.salePrice_txt = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 54:
                        newCartItem.subtotal_txt = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 55:
                        newCartItem.marketPrice_txt = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 56:
                        newCartItem.jump_url = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newCartItem;
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.timeout ? (byte) 1 : 0);
        parcel.writeString(this.itemId);
        parcel.writeString(this.goodsId);
        parcel.writeString(this.getType);
        parcel.writeInt(this.num);
        parcel.writeParcelable(this.properties, i);
        parcel.writeFloat(this.salePrice);
        parcel.writeString(this.commodity_id);
        parcel.writeString(this.sku);
        parcel.writeString(this.goods_dealer);
        parcel.writeFloat(this.subtotal);
        parcel.writeByte(this.is_cos ? (byte) 1 : 0);
        parcel.writeString(this.product_name);
        parcel.writeByte(this.isInsurance ? (byte) 1 : 0);
        parcel.writeString(this.product_id);
        parcel.writeString(this.image_url);
        parcel.writeByte(this.is_batched ? (byte) 1 : 0);
        parcel.writeByte(this.is_on_sale ? (byte) 1 : 0);
        parcel.writeInt(this.buy_limit);
        parcel.writeString(this.product_brief);
        parcel.writeString(this.price);
        parcel.writeString(this.market_price);
        parcel.writeLong(this.cartTTL);
        parcel.writeByte(this.show_stock ? (byte) 1 : 0);
        parcel.writeString(this.style_value);
        parcel.writeString(this.showType);
        parcel.writeString(this.elementsGoods);
        parcel.writeByte(this.item_timeout ? (byte) 1 : 0);
        parcel.writeString(this.item_type_name);
        parcel.writeString(this.salePrice_txt);
        parcel.writeString(this.subtotal_txt);
        parcel.writeString(this.marketPrice_txt);
    }

    public NewCartItem() {
    }

    protected NewCartItem(Parcel parcel) {
        boolean z = false;
        this.timeout = parcel.readByte() != 0;
        this.itemId = parcel.readString();
        this.goodsId = parcel.readString();
        this.getType = parcel.readString();
        this.num = parcel.readInt();
        this.properties = (NewCartProperty) parcel.readParcelable(NewCartProperty.class.getClassLoader());
        this.salePrice = parcel.readFloat();
        this.commodity_id = parcel.readString();
        this.sku = parcel.readString();
        this.goods_dealer = parcel.readString();
        this.subtotal = parcel.readFloat();
        this.is_cos = parcel.readByte() != 0;
        this.product_name = parcel.readString();
        this.isInsurance = parcel.readByte() != 0;
        this.product_id = parcel.readString();
        this.image_url = parcel.readString();
        this.is_batched = parcel.readByte() != 0;
        this.is_on_sale = parcel.readByte() != 0;
        this.buy_limit = parcel.readInt();
        this.product_brief = parcel.readString();
        this.price = parcel.readString();
        this.market_price = parcel.readString();
        this.cartTTL = parcel.readLong();
        this.show_stock = parcel.readByte() != 0;
        this.style_value = parcel.readString();
        this.showType = parcel.readString();
        this.elementsGoods = parcel.readString();
        this.item_timeout = parcel.readByte() != 0 ? true : z;
        this.item_type_name = parcel.readString();
        this.salePrice_txt = parcel.readString();
        this.subtotal_txt = parcel.readString();
        this.marketPrice_txt = parcel.readString();
    }
}
