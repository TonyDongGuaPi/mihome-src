package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.miui.tsmclient.entity.ActionToken;
import com.miui.tsmclient.entity.FeeInfo;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.util.DeviceUtils;
import com.miui.tsmclient.util.EnvironmentConfig;
import com.miui.tsmclient.util.LogUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PayableCardInfo extends CardInfo implements Parcelable {
    public static final Parcelable.Creator<PayableCardInfo> CREATOR = new Parcelable.Creator<PayableCardInfo>() {
        public PayableCardInfo createFromParcel(Parcel parcel) {
            PayableCardInfo payableCardInfo = new PayableCardInfo((String) null);
            payableCardInfo.readFromParcel(parcel);
            return payableCardInfo;
        }

        public PayableCardInfo[] newArray(int i) {
            return new PayableCardInfo[i];
        }
    };
    private static final String KEY_CONFIG_ID = "configId";
    private static final String KEY_CUSTOM_INFO = "customInfo";
    private static final String KEY_FEES = "fees";
    private static final int TRANSFER_OUT_BALANCE_AMOUNT_DEFAULT = 0;
    protected Map<FeeInfo.ActionType, List<FeeInfo>> mActionType2FeeInfoListMap = new HashMap();
    protected List<FeeInfo> mActiveFeeInfos;
    private long mCustomConfigId;
    private CustomFeeInfo mCustomFeeInfo;
    private CustomInfo mCustomInfo;
    private String mPhoneNumber;
    public List<OrderInfo> mUnfinishOrderInfos = new CopyOnWriteArrayList();
    private TransferOutOrderInfo mUnfinishTransferOutInfo;

    public boolean isTransCard() {
        return true;
    }

    public TransferOutOrderInfo getUnfinishTransferOutInfo() {
        return this.mUnfinishTransferOutInfo;
    }

    public void setUnfinishTransferOutInfo(TransferOutOrderInfo transferOutOrderInfo) {
        this.mUnfinishTransferOutInfo = transferOutOrderInfo;
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.mPhoneNumber = str;
    }

    public CustomFeeInfo getCustomFeeInfo() {
        return this.mCustomFeeInfo;
    }

    public PayableCardInfo(String str) {
        super(str);
        this.mCardGroupId = CardGroupType.TRANSCARD.getId();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        if (this.mActionType2FeeInfoListMap == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(this.mActionType2FeeInfoListMap.size());
            for (FeeInfo.ActionType next : this.mActionType2FeeInfoListMap.keySet()) {
                parcel.writeString(next.name());
                parcel.writeList(this.mActionType2FeeInfoListMap.get(next));
            }
        }
        parcel.writeList(this.mUnfinishOrderInfos);
        parcel.writeString(this.mPhoneNumber);
        parcel.writeParcelable(this.mCustomFeeInfo, i);
        parcel.writeParcelable(this.mCustomInfo, i);
        parcel.writeLong(this.mCustomConfigId);
        if (supportTransferOutOrderInfoParcel()) {
            parcel.writeParcelable(this.mUnfinishTransferOutInfo, i);
        }
    }

    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            this.mActionType2FeeInfoListMap.put(FeeInfo.ActionType.valueOf(parcel.readString()), parcel.readArrayList(FeeInfo.class.getClassLoader()));
        }
        this.mUnfinishOrderInfos = new CopyOnWriteArrayList();
        this.mUnfinishOrderInfos.addAll(parcel.readArrayList(OrderInfo.class.getClassLoader()));
        this.mPhoneNumber = parcel.readString();
        this.mCustomFeeInfo = (CustomFeeInfo) parcel.readParcelable(CustomFeeInfo.class.getClassLoader());
        this.mCustomInfo = (CustomInfo) parcel.readParcelable(CustomInfo.class.getClassLoader());
        this.mCustomConfigId = parcel.readLong();
        if (supportTransferOutOrderInfoParcel()) {
            this.mUnfinishTransferOutInfo = (TransferOutOrderInfo) parcel.readParcelable(TransferOutOrderInfo.class.getClassLoader());
        }
    }

    public JSONObject serialize() {
        JSONObject serialize = super.serialize();
        if (serialize != null) {
            try {
                if (this.mActionType2FeeInfoListMap != null) {
                    JSONArray jSONArray = new JSONArray();
                    for (FeeInfo.ActionType actionType : this.mActionType2FeeInfoListMap.keySet()) {
                        for (FeeInfo serialize2 : this.mActionType2FeeInfoListMap.get(actionType)) {
                            jSONArray.put(serialize2.serialize());
                        }
                    }
                    serialize.put(KEY_FEES, jSONArray);
                    if (this.mCustomInfo != null) {
                        serialize.put(KEY_CUSTOM_INFO, this.mCustomInfo.serialize());
                    }
                }
            } catch (JSONException e) {
                LogUtils.e("serialize fee info list failed.", e);
            }
        }
        if (serialize != null) {
            serialize.put("configId", this.mCustomConfigId);
        }
        return serialize;
    }

    public CardInfo parse(JSONObject jSONObject) {
        super.parse(jSONObject);
        if (jSONObject != null && jSONObject.has(KEY_FEES)) {
            try {
                if (this.mActionType2FeeInfoListMap != null) {
                    this.mActionType2FeeInfoListMap.clear();
                }
                JSONArray jSONArray = jSONObject.getJSONArray(KEY_FEES);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    FeeInfo feeInfo = new FeeInfo();
                    feeInfo.parse(jSONObject2);
                    if (this.mActionType2FeeInfoListMap.get(feeInfo.mActionType) == null) {
                        this.mActionType2FeeInfoListMap.put(feeInfo.mActionType, new ArrayList());
                    }
                    this.mActionType2FeeInfoListMap.get(feeInfo.mActionType).add(feeInfo);
                }
            } catch (JSONException e) {
                LogUtils.e("parse fee info list failed. parse object:" + jSONObject.toString(), e);
            }
        }
        if (jSONObject != null && jSONObject.has(KEY_CUSTOM_INFO)) {
            try {
                JSONObject jSONObject3 = jSONObject.getJSONObject(KEY_CUSTOM_INFO);
                this.mCustomInfo = new CustomInfo();
                this.mCustomInfo.parse(jSONObject3);
                this.mCustomFeeInfo = new CustomFeeInfo(this.mCustomInfo);
            } catch (JSONException e2) {
                LogUtils.e("parse customInfo info list failed. parse object:" + jSONObject.toString(), e2);
            }
        }
        if (jSONObject != null && jSONObject.has("configId")) {
            this.mCustomConfigId = jSONObject.optLong("configId");
        }
        return this;
    }

    public boolean showBalance() {
        return this.mCardBalance != -999;
    }

    public List<FeeInfo> getActiveFeeInfoList() {
        if (this.mHasIssue) {
            this.mActiveFeeInfos = this.mActionType2FeeInfoListMap.get(FeeInfo.ActionType.recharge);
        } else {
            this.mActiveFeeInfos = this.mActionType2FeeInfoListMap.get(FeeInfo.ActionType.issue);
            if (this.mActiveFeeInfos == null) {
                this.mActiveFeeInfos = this.mActionType2FeeInfoListMap.get(FeeInfo.ActionType.issueAndRecharge);
            }
        }
        if (this.mActiveFeeInfos != null && this.mCustomFeeInfo != null && this.mCustomFeeInfo.isValid() && !this.mActiveFeeInfos.contains(this.mCustomFeeInfo)) {
            this.mActiveFeeInfos.add(this.mCustomFeeInfo);
        }
        return this.mActiveFeeInfos;
    }

    public FeeInfo getWithdrawFeeInfo() {
        List<FeeInfo> list;
        if (!canTransferIn() || (list = this.mActionType2FeeInfoListMap.get(FeeInfo.ActionType.withdraw)) == null || list.isEmpty()) {
            return null;
        }
        for (FeeInfo feeInfo : list) {
            if (feeInfo.mActionType == FeeInfo.ActionType.withdraw) {
                return feeInfo;
            }
        }
        return null;
    }

    public boolean canTransferIn() {
        if (this.mHasIssue || !this.mIsReadSECorrectly) {
            return false;
        }
        return hasTransferInOrder();
    }

    public boolean hasTransferInOrder() {
        OrderInfo orderInfo;
        if (this.mUnfinishOrderInfos == null || this.mUnfinishOrderInfos.isEmpty() || (orderInfo = this.mUnfinishOrderInfos.get(0)) == null || orderInfo.mActionTokens == null) {
            return false;
        }
        for (ActionToken actionToken : orderInfo.mActionTokens) {
            if (actionToken.mType == ActionToken.TokenType.withdraw) {
                return true;
            }
        }
        return false;
    }

    public boolean hasIssueOrder() {
        if (this.mUnfinishOrderInfos == null) {
            return false;
        }
        for (OrderInfo isIssueOrder : this.mUnfinishOrderInfos) {
            if (isIssueOrder.isIssueOrder()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasRechargeOrder() {
        if (this.mUnfinishOrderInfos == null) {
            return false;
        }
        for (OrderInfo isRechargeOrder : this.mUnfinishOrderInfos) {
            if (isRechargeOrder.isRechargeOrder()) {
                return true;
            }
        }
        return false;
    }

    public OrderInfo getTransferInOrder() {
        if (canTransferIn()) {
            return this.mUnfinishOrderInfos.get(0);
        }
        return null;
    }

    public int getTransferOutBalance() {
        List<ActionToken> list;
        if (!canTransferIn() || (list = this.mUnfinishOrderInfos.get(0).mActionTokens) == null || list.isEmpty()) {
            return 0;
        }
        for (ActionToken next : list) {
            if (next.mType == ActionToken.TokenType.withdraw) {
                return next.mRechargeAmount;
            }
        }
        return 0;
    }

    public PayableCardInfo updateOrderInfo(OrderInfo orderInfo) {
        if (orderInfo != null && orderInfo.isPaid()) {
            if (this.mUnfinishOrderInfos != null) {
                this.mUnfinishOrderInfos.clear();
            } else {
                this.mUnfinishOrderInfos = new CopyOnWriteArrayList();
            }
            this.mUnfinishOrderInfos.add(orderInfo);
        }
        return this;
    }

    public boolean hasUnfinishedOrder() {
        return (this.mUnfinishOrderInfos != null && !this.mUnfinishOrderInfos.isEmpty()) || this.mUnfinishTransferOutInfo != null;
    }

    public OrderInfo getRechargeOrder() {
        List<OrderInfo> list = this.mUnfinishOrderInfos;
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (OrderInfo next : list) {
            if (next.isRechargeOrder()) {
                return next;
            }
        }
        return null;
    }

    public int hashCode() {
        return this.mCardType.hashCode();
    }

    public void updateCustomFeeInfo(CustomFeeInfo customFeeInfo) {
        int indexOf;
        if (customFeeInfo != null && this.mActiveFeeInfos != null && (indexOf = this.mActiveFeeInfos.indexOf(customFeeInfo)) >= 0) {
            this.mActiveFeeInfos.set(indexOf, customFeeInfo);
        }
    }

    public FeeInfo getFeeInfo(int i) {
        if (getActiveFeeInfoList() == null || getActiveFeeInfoList().size() <= i) {
            return null;
        }
        return getActiveFeeInfoList().get(i);
    }

    public long getCustomConfigId() {
        return this.mCustomConfigId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CardInfo)) {
            return false;
        }
        CardInfo cardInfo = (CardInfo) obj;
        if (!TextUtils.equals(this.mCardType, cardInfo.mCardType) || !TextUtils.equals(this.mCardDevice, cardInfo.mCardDevice)) {
            return false;
        }
        return true;
    }

    private boolean supportTransferOutOrderInfoParcel() {
        if (EnvironmentConfig.getContext() != null && DeviceUtils.getAppVersionCode(EnvironmentConfig.getContext(), "com.miui.tsmclient") >= 47) {
            return true;
        }
        return false;
    }
}
