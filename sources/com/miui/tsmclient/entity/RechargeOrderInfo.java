package com.miui.tsmclient.entity;

import com.google.gson.annotations.SerializedName;

public class RechargeOrderInfo {
    private static final int FLAG_REFUND = 2;
    private static final int FLAG_REFUND_SUCCESS = 8;
    private static final int FLAG_RETRY = 4;
    private static final int FLAG_SUCCESS = 1;
    @SerializedName("amount")
    private int mAmount;
    @SerializedName("orderId")
    private String mOrderId;
    @SerializedName("richStatus")
    private int mRichStatus;
    @SerializedName("statusDesc")
    private String mStatusDesc;
    @SerializedName("time")
    private String mTime;
    @SerializedName("title")
    private String mTitle;

    public String getTitle() {
        return this.mTitle;
    }

    public String getOrderId() {
        return this.mOrderId;
    }

    public int getAmount() {
        return this.mAmount;
    }

    public String getStatusDesc() {
        return this.mStatusDesc;
    }

    public String getTime() {
        return this.mTime;
    }

    public boolean isSuccess() {
        return (this.mRichStatus & 1) == 1 || isRefundSuccess();
    }

    public boolean isRefundable() {
        return (this.mRichStatus & 2) == 2;
    }

    public boolean isRetriable() {
        return (this.mRichStatus & 4) == 4;
    }

    public boolean isRefundSuccess() {
        return (this.mRichStatus & 8) == 8;
    }
}
