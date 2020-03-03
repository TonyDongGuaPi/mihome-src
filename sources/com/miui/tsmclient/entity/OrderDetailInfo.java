package com.miui.tsmclient.entity;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.miui.tsmclient.pay.OrderInfo;
import java.util.List;

public class OrderDetailInfo {
    private static final int FLAG_REFUND = 2;
    private static final int FLAG_REFUND_SUCCESS = 8;
    private static final int FLAG_RETRY = 4;
    private static final int FLAG_SUCCESS = 1;
    @SerializedName("cardName")
    private String mCardType;
    @SerializedName("hint")
    private String mHint;
    @SerializedName("orderFlow")
    private List<OrderFlow> mOrderFlows;
    @SerializedName("orderId")
    private String mOrderId;
    @SerializedName("refundDesc")
    private String mRefundDesc;
    @SerializedName("status")
    private int mStatus;
    @SerializedName("statusDesc")
    private String mStatusDesc;
    @SerializedName("time")
    private String mTime;
    @SerializedName("unfinishedOrder")
    private OrderInfo mUnfinishedOrder;

    public static class OrderFlow {
        @SerializedName("amount")
        private int mAmount;
        @SerializedName("desc")
        private String mDesc;
        @SerializedName("leftTitle")
        private String mKey;
        @SerializedName("leftContent")
        private String mSubKey;
        @SerializedName("rightContent")
        private String mSubValue;
        @SerializedName("time")
        private String mTime;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("rightTitle")
        private String mValue;

        public String getTitle() {
            return this.mTitle;
        }

        public String getDesc() {
            return this.mDesc;
        }

        public String getTime() {
            return this.mTime;
        }

        public int getAmount() {
            return this.mAmount;
        }

        public String getKey() {
            return this.mKey;
        }

        public String getSubKey() {
            return this.mSubKey;
        }

        public String getValue() {
            return this.mValue;
        }

        public String getSubValue() {
            return this.mSubValue;
        }

        public boolean shouldShowNewOrderFlow() {
            return !TextUtils.isEmpty(this.mKey) || !TextUtils.isEmpty(this.mSubKey) || !TextUtils.isEmpty(this.mValue) || !TextUtils.isEmpty(this.mSubValue);
        }
    }

    public String getCardType() {
        return this.mCardType;
    }

    public String getHint() {
        return this.mHint;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public String getOrderId() {
        return this.mOrderId;
    }

    public String getStatusDesc() {
        return this.mStatusDesc;
    }

    public String getRefundDesc() {
        return this.mRefundDesc;
    }

    public String getTime() {
        return this.mTime;
    }

    public OrderInfo getUnfinishedOrder() {
        return this.mUnfinishedOrder;
    }

    public List<OrderFlow> getOrderFlows() {
        return this.mOrderFlows;
    }

    public boolean isSuccess() {
        return (this.mStatus & 1) == 1 || isRefundSuccess();
    }

    public boolean canRefund() {
        return (this.mStatus & 2) == 2;
    }

    public boolean canRetry() {
        return (this.mStatus & 4) == 4;
    }

    public boolean isRefundSuccess() {
        return (this.mStatus & 8) == 8;
    }

    public int getRechargeAmount() {
        if (isRechargeOrder()) {
            return this.mUnfinishedOrder.getRechargeAmount();
        }
        return 0;
    }

    public boolean isRechargeOrder() {
        if (this.mUnfinishedOrder != null) {
            return this.mUnfinishedOrder.isRechargeOrder();
        }
        return false;
    }

    public boolean isIssueOrWithdrawOrder() {
        return this.mUnfinishedOrder != null && this.mUnfinishedOrder.isIssueOrWithdrawOrder();
    }

    public boolean isIssuable(CardInfo cardInfo) {
        return cardInfo != null && !cardInfo.mHasIssue && isIssueOrWithdrawOrder();
    }
}
