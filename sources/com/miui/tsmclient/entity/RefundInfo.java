package com.miui.tsmclient.entity;

import com.google.gson.annotations.SerializedName;

public class RefundInfo {
    @SerializedName("applyRefundAmount")
    private int mApplyRefundAmount;
    @SerializedName("responseCode")
    private String mResponseCode;
    @SerializedName("responseDesc")
    private String mResponseDesc;
    @SerializedName("success")
    private boolean mSuccess;

    public boolean isSuccess() {
        return this.mSuccess;
    }

    public String getResponseCode() {
        return this.mResponseCode;
    }

    public String getResponseDesc() {
        return this.mResponseDesc;
    }

    public int getApplyRefundAmount() {
        return this.mApplyRefundAmount;
    }
}
