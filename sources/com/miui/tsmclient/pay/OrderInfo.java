package com.miui.tsmclient.pay;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import com.miui.tsmclient.entity.ActionToken;
import com.miui.tsmclient.entity.ObjectParser;
import com.miui.tsmclient.util.LogUtils;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderInfo implements Parcelable, ObjectParser<OrderInfo> {
    public static final Parcelable.Creator<OrderInfo> CREATOR = new Parcelable.Creator<OrderInfo>() {
        public OrderInfo createFromParcel(Parcel parcel) {
            return new OrderInfo(parcel);
        }

        public OrderInfo[] newArray(int i) {
            return new OrderInfo[i];
        }
    };
    public static final String KEY_ACTIONTOKEN = "actionToken";
    public static final String KEY_DESC = "statusDesc";
    public static final String KEY_NEED_PAY = "needPay";
    public static final String KEY_ORDERID = "orderId";
    public static final String KEY_ORDERSTATUS = "orderStatus";
    public static final String KEY_ORIGIN_COUNT = "originCount";
    public static final String KEY_PAYFEE = "payFee";
    public static final String KEY_PAYSTRING = "payString";
    public static final String KEY_PAYTIME = "payTime";
    public static final String KEY_REST_COUNT = "restCount";
    public static final String KEY_SINGLE_COST = "singleCost";
    @SerializedName("actionToken")
    public List<ActionToken> mActionTokens;
    @SerializedName("cardName")
    public String mCardType;
    public String mCityId;
    public boolean mNeedPay;
    @SerializedName("orderId")
    public String mOrderId;
    @SerializedName("orderStatus")
    public OrderStatus mOrderStatus;
    public int mOriginCount;
    public String mPayExtra;
    @SerializedName("payFee")
    public int mPayFee;
    @SerializedName("payTime")
    public long mPayTime;
    public PayType mPayType;
    public int mRestCount;
    public int mSingleCost;
    @SerializedName("statusDesc")
    private String mStatusDesc;

    public int describeContents() {
        return 0;
    }

    public enum OrderStatus {
        finish(0),
        waitingpay(1),
        paid(2),
        payFailed(3);
        
        private int mId;

        private OrderStatus(int i) {
            this.mId = i;
        }

        public static OrderStatus newInstance(int i) {
            for (OrderStatus orderStatus : values()) {
                if (orderStatus.mId == i) {
                    return orderStatus;
                }
            }
            return null;
        }
    }

    public static class OrderStatusDeserializer implements JsonDeserializer<OrderStatus> {
        public OrderStatus deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return OrderStatus.newInstance(jsonElement.getAsInt());
        }
    }

    private OrderInfo(Parcel parcel) {
        this.mPayType = PayType.Mipay;
        readFromParcel(parcel);
    }

    public OrderInfo() {
        this.mPayType = PayType.Mipay;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mOrderId);
        parcel.writeString(this.mOrderStatus == null ? "" : this.mOrderStatus.name());
        parcel.writeList(this.mActionTokens);
        parcel.writeString(this.mPayExtra);
        parcel.writeString(this.mPayType == null ? "" : this.mPayType.name());
        parcel.writeLong(this.mPayTime);
        parcel.writeInt(this.mPayFee);
        parcel.writeString(this.mCardType);
        parcel.writeString(this.mCityId);
        parcel.writeInt(this.mSingleCost);
        parcel.writeInt(this.mOriginCount);
        parcel.writeInt(this.mRestCount);
        parcel.writeByte(this.mNeedPay ? (byte) 1 : 0);
        parcel.writeString(this.mStatusDesc);
    }

    public void readFromParcel(Parcel parcel) {
        this.mOrderId = parcel.readString();
        String readString = parcel.readString();
        if (!TextUtils.isEmpty(readString)) {
            this.mOrderStatus = OrderStatus.valueOf(readString);
        }
        this.mActionTokens = parcel.readArrayList(ActionToken.class.getClassLoader());
        this.mPayExtra = parcel.readString();
        String readString2 = parcel.readString();
        if (!TextUtils.isEmpty(readString2)) {
            this.mPayType = PayType.valueOf(readString2);
        }
        this.mPayTime = parcel.readLong();
        this.mPayFee = parcel.readInt();
        this.mCardType = parcel.readString();
        this.mCityId = parcel.readString();
        this.mSingleCost = parcel.readInt();
        this.mOriginCount = parcel.readInt();
        this.mRestCount = parcel.readInt();
        this.mNeedPay = parcel.readByte() != 0;
        this.mStatusDesc = parcel.readString();
    }

    public OrderInfo parse(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.mOrderId = jSONObject.optString("orderId");
            if (jSONObject.has(KEY_ORDERSTATUS)) {
                this.mOrderStatus = OrderStatus.newInstance(jSONObject.optInt(KEY_ORDERSTATUS));
            }
            this.mPayExtra = jSONObject.optString(KEY_PAYSTRING);
            this.mPayTime = jSONObject.optLong("payTime");
            this.mPayFee = jSONObject.optInt("payFee");
            this.mCardType = jSONObject.optString("cardName");
            this.mCityId = jSONObject.optString("cityId");
            this.mSingleCost = jSONObject.optInt(KEY_SINGLE_COST);
            this.mOriginCount = jSONObject.optInt(KEY_ORIGIN_COUNT);
            this.mRestCount = jSONObject.optInt(KEY_REST_COUNT);
            this.mNeedPay = jSONObject.optBoolean(KEY_NEED_PAY);
            this.mStatusDesc = jSONObject.optString("statusDesc");
            JSONArray optJSONArray = jSONObject.optJSONArray("actionToken");
            if (optJSONArray != null) {
                this.mActionTokens = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    try {
                        this.mActionTokens.add(new ActionToken().parse(optJSONArray.getJSONObject(i)));
                    } catch (JSONException e) {
                        LogUtils.e("parse action token failed.", e);
                    }
                }
            }
        }
        return this;
    }

    public boolean isPaid() {
        return this.mOrderStatus == OrderStatus.paid;
    }

    public boolean isPayFailed() {
        return this.mOrderStatus == OrderStatus.payFailed;
    }

    public boolean isRechargeOrder() {
        if (this.mActionTokens == null) {
            return false;
        }
        for (ActionToken isRechargeType : this.mActionTokens) {
            if (isRechargeType.isRechargeType()) {
                return true;
            }
        }
        return false;
    }

    public boolean isIssueOrder() {
        if (this.mActionTokens == null) {
            return false;
        }
        for (ActionToken isIssueType : this.mActionTokens) {
            if (isIssueType.isIssueType()) {
                return true;
            }
        }
        return false;
    }

    public boolean isIssueOrWithdrawOrder() {
        if (this.mActionTokens == null) {
            return false;
        }
        for (ActionToken shouldAutoIssueOrWithdraw : this.mActionTokens) {
            if (shouldAutoIssueOrWithdraw.shouldAutoIssueOrWithdraw()) {
                return true;
            }
        }
        return false;
    }

    public ActionToken getIssueOrWithdrawOrderToken() {
        if (this.mActionTokens == null) {
            return null;
        }
        for (ActionToken next : this.mActionTokens) {
            if (next.shouldAutoIssueOrWithdraw()) {
                return next;
            }
        }
        return null;
    }

    public int getRechargeAmount() {
        if (this.mActionTokens == null) {
            return 0;
        }
        for (ActionToken next : this.mActionTokens) {
            if (next.isRechargeType()) {
                return next.mRechargeAmount;
            }
        }
        return 0;
    }

    public String getStatusDesc() {
        return this.mStatusDesc;
    }
}
