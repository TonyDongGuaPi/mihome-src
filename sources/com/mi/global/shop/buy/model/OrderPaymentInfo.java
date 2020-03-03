package com.mi.global.shop.buy.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.payment.data.MibiConstants;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderPaymentInfo implements Parcelable {
    public static final Parcelable.Creator<OrderPaymentInfo> CREATOR = new Parcelable.Creator<OrderPaymentInfo>() {
        /* renamed from: a */
        public OrderPaymentInfo createFromParcel(Parcel parcel) {
            return new OrderPaymentInfo(parcel);
        }

        /* renamed from: a */
        public OrderPaymentInfo[] newArray(int i) {
            return new OrderPaymentInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final String f6888a = "orderPaymentInfo";
    public static final int r = 1;
    public static final int s = 2;
    public static final int t = 0;
    public static final int u = 3;
    public static final String v = "needverify";
    public static final String w = "noverify";
    public static final String x = "notsupport";
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public ArrayList<OrderItem> k;
    public String l;
    public boolean m = true;
    public boolean n = true;
    public String o = null;
    public int p = 0;
    public int q = 0;

    public int describeContents() {
        return 0;
    }

    public OrderPaymentInfo(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has(MibiConstants.hk)) {
            try {
                b(jSONObject.getJSONObject(MibiConstants.hk));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        if (jSONObject != null && jSONObject.has("support")) {
            try {
                a(jSONObject.getJSONObject("support"));
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
    }

    public void a(JSONObject jSONObject) {
        try {
            if (jSONObject.has("can_cod") && jSONObject.getString("can_cod").equals("0")) {
                this.m = false;
            }
            if (jSONObject.has("can_onlinepay") && jSONObject.getString("can_onlinepay").equals("0")) {
                this.n = false;
            }
            if (jSONObject.has("codstatus")) {
                this.o = jSONObject.getString("codstatus");
                if (this.o.equals(v)) {
                    this.p = 1;
                } else if (this.o.equals(w)) {
                    this.p = 0;
                } else if (this.o.equals(x)) {
                    this.p = 3;
                }
            }
            if (!this.m) {
                this.p = 3;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x00a2 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x00a3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(org.json.JSONObject r7) {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r6.k = r0
            if (r7 != 0) goto L_0x000a
            return
        L_0x000a:
            r0 = 0
            java.lang.String r1 = "orderItems"
            java.lang.Object r1 = r7.get(r1)     // Catch:{ Exception -> 0x0084 }
            org.json.JSONArray r1 = (org.json.JSONArray) r1     // Catch:{ Exception -> 0x0084 }
            java.lang.String r0 = "order_id"
            java.lang.Object r0 = r7.get(r0)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0082 }
            r6.b = r0     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = "consignee"
            java.lang.Object r0 = r7.get(r0)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0082 }
            r6.e = r0     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = "address"
            java.lang.Object r0 = r7.get(r0)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0082 }
            java.lang.String r2 = "[-addr-]"
            java.lang.String r3 = ","
            java.lang.String r0 = r0.replace(r2, r3)     // Catch:{ Exception -> 0x0082 }
            r6.f = r0     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = "tel"
            java.lang.Object r0 = r7.get(r0)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0082 }
            r6.c = r0     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = r6.c     // Catch:{ Exception -> 0x0082 }
            r6.d = r0     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = "goods_amount"
            java.lang.Object r0 = r7.get(r0)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0082 }
            r6.i = r0     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = "reduce_price"
            java.lang.Object r0 = r7.get(r0)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0082 }
            r6.g = r0     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = "shipment_expense"
            java.lang.Object r0 = r7.get(r0)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0082 }
            r6.h = r0     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = "remaining_time"
            java.lang.Object r7 = r7.get(r0)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0082 }
            r6.l = r7     // Catch:{ Exception -> 0x0082 }
            goto L_0x00a0
        L_0x0082:
            r7 = move-exception
            goto L_0x0086
        L_0x0084:
            r7 = move-exception
            r1 = r0
        L_0x0086:
            java.lang.String r0 = "orderPaymentInfo"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "OrderInfo Parse Exception:"
            r2.append(r3)
            java.lang.String r7 = r7.toString()
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            com.mi.log.LogUtil.b(r0, r7)
        L_0x00a0:
            if (r1 != 0) goto L_0x00a3
            return
        L_0x00a3:
            r7 = 0
        L_0x00a4:
            int r0 = r1.length()     // Catch:{ Exception -> 0x012c }
            if (r7 >= r0) goto L_0x0147
            java.lang.Object r0 = r1.get(r7)     // Catch:{ Exception -> 0x012c }
            org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ Exception -> 0x012c }
            com.mi.global.shop.buy.model.OrderItem r2 = new com.mi.global.shop.buy.model.OrderItem     // Catch:{ Exception -> 0x012c }
            r2.<init>()     // Catch:{ Exception -> 0x012c }
            java.lang.String r3 = "product_count"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ Exception -> 0x012c }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x012c }
            r2.b = r3     // Catch:{ Exception -> 0x012c }
            java.lang.String r3 = "orderPaymentInfo"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x012c }
            r4.<init>()     // Catch:{ Exception -> 0x012c }
            java.lang.String r5 = "product_count is: "
            r4.append(r5)     // Catch:{ Exception -> 0x012c }
            java.lang.String r5 = r2.b     // Catch:{ Exception -> 0x012c }
            r4.append(r5)     // Catch:{ Exception -> 0x012c }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x012c }
            com.mi.log.LogUtil.b(r3, r4)     // Catch:{ Exception -> 0x012c }
            java.lang.String r3 = "product_name"
            java.lang.Object r3 = r0.get(r3)     // Catch:{ Exception -> 0x012c }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x012c }
            r2.f6887a = r3     // Catch:{ Exception -> 0x012c }
            java.lang.String r3 = "orderPaymentInfo"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x012c }
            r4.<init>()     // Catch:{ Exception -> 0x012c }
            java.lang.String r5 = "product_name is: "
            r4.append(r5)     // Catch:{ Exception -> 0x012c }
            java.lang.String r5 = r2.f6887a     // Catch:{ Exception -> 0x012c }
            r4.append(r5)     // Catch:{ Exception -> 0x012c }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x012c }
            com.mi.log.LogUtil.b(r3, r4)     // Catch:{ Exception -> 0x012c }
            java.lang.String r3 = "subtotal"
            java.lang.Object r0 = r0.get(r3)     // Catch:{ Exception -> 0x012c }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x012c }
            r2.c = r0     // Catch:{ Exception -> 0x012c }
            java.lang.String r0 = "orderPaymentInfo"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x012c }
            r3.<init>()     // Catch:{ Exception -> 0x012c }
            java.lang.String r4 = "subtotal is: "
            r3.append(r4)     // Catch:{ Exception -> 0x012c }
            java.lang.String r4 = r2.c     // Catch:{ Exception -> 0x012c }
            r3.append(r4)     // Catch:{ Exception -> 0x012c }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x012c }
            com.mi.log.LogUtil.b(r0, r3)     // Catch:{ Exception -> 0x012c }
            java.util.ArrayList<com.mi.global.shop.buy.model.OrderItem> r0 = r6.k     // Catch:{ Exception -> 0x012c }
            r0.add(r2)     // Catch:{ Exception -> 0x012c }
            int r7 = r7 + 1
            goto L_0x00a4
        L_0x012c:
            r7 = move-exception
            java.lang.String r0 = "orderPaymentInfo"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "OrderInfo Parse Exception:"
            r1.append(r2)
            java.lang.String r7 = r7.toString()
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            com.mi.log.LogUtil.b(r0, r7)
        L_0x0147:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.buy.model.OrderPaymentInfo.b(org.json.JSONObject):void");
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeList(this.k);
        parcel.writeString(this.l);
        parcel.writeByte(this.m ? (byte) 1 : 0);
        parcel.writeByte(this.n ? (byte) 1 : 0);
        parcel.writeString(this.o);
        parcel.writeInt(this.p);
        parcel.writeInt(this.q);
    }

    protected OrderPaymentInfo(Parcel parcel) {
        boolean z = true;
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = new ArrayList<>();
        parcel.readList(this.k, OrderItem.class.getClassLoader());
        this.l = parcel.readString();
        this.m = parcel.readByte() != 0;
        this.n = parcel.readByte() == 0 ? false : z;
        this.o = parcel.readString();
        this.p = parcel.readInt();
        this.q = parcel.readInt();
    }
}
