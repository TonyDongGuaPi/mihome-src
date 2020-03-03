package com.mi.global.shop.buy.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Html;
import com.mi.global.shop.model.Tags;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BuyOrderInfo implements Parcelable {
    public static final Parcelable.Creator<BuyOrderInfo> CREATOR = new Parcelable.Creator<BuyOrderInfo>() {
        /* renamed from: a */
        public BuyOrderInfo createFromParcel(Parcel parcel) {
            return new BuyOrderInfo(parcel);
        }

        /* renamed from: a */
        public BuyOrderInfo[] newArray(int i) {
            return new BuyOrderInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f6881a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public ArrayList<BuyOrderItem> l;

    public int describeContents() {
        return 0;
    }

    public BuyOrderInfo() {
    }

    public BuyOrderInfo(JSONObject jSONObject) throws JSONException {
        this.l = new ArrayList<>();
        if (jSONObject != null) {
            JSONArray jSONArray = (JSONArray) jSONObject.get("orderItems");
            this.f6881a = jSONObject.get("order_id").toString();
            this.c = jSONObject.get("consignee").toString();
            if (jSONObject.has("zipcode")) {
                this.i = jSONObject.get("zipcode").toString();
            }
            String[] split = Html.fromHtml(jSONObject.get("address").toString()).toString().split("\\[\\-addr\\-\\]");
            if (split.length >= 3) {
                this.d = split[1] + " " + split[2] + " " + split[0] + " / " + this.i;
            } else if (split.length == 2) {
                this.d = split[1] + " " + split[0] + " / " + this.i;
            } else {
                this.d = split[0] + " / " + this.i;
            }
            this.b = jSONObject.get("tel").toString();
            this.g = jSONObject.get(Tags.Order.FEE).toString();
            this.e = jSONObject.get(Tags.Order.REDUCE_PRICE).toString();
            this.f = jSONObject.get(Tags.Order.SHIPMENT_EXPRENSE).toString();
            this.j = jSONObject.get("remaining_time").toString();
            JSONObject optJSONObject = jSONObject.optJSONObject("exchange_coupon");
            if (optJSONObject != null) {
                this.k = optJSONObject.getString("amount");
            }
            if (jSONArray != null) {
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                    BuyOrderItem buyOrderItem = new BuyOrderItem();
                    buyOrderItem.b = jSONObject2.get("product_count").toString();
                    buyOrderItem.f6882a = jSONObject2.get("product_name").toString();
                    buyOrderItem.e = jSONObject2.get("product_id").toString();
                    buyOrderItem.g = jSONObject2.get("goods_id").toString();
                    buyOrderItem.d = jSONObject2.get("price").toString();
                    buyOrderItem.c = jSONObject2.get("subtotal").toString();
                    buyOrderItem.f = jSONObject2.get("product_count").toString();
                    this.l.add(buyOrderItem);
                }
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f6881a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeList(this.l);
    }

    protected BuyOrderInfo(Parcel parcel) {
        this.f6881a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = new ArrayList<>();
        parcel.readList(this.l, BuyOrderItem.class.getClassLoader());
    }
}
