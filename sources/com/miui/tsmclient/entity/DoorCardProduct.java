package com.miui.tsmclient.entity;

import com.google.gson.annotations.SerializedName;

public class DoorCardProduct {
    private static final String URL_OPEN_DOOR_CARD = "tsmclient://card?action=issue_mifare&type=%1d&product_id=%2s";
    private boolean isSelected;
    @SerializedName("cardType")
    private int mCardType;
    @SerializedName("fingerFlag")
    private int mFingerFlag;
    @SerializedName("logo")
    private String mLogo;
    @SerializedName("productId")
    private String mProductId;
    @SerializedName("productName")
    private String mProductName;

    public String getProductId() {
        return this.mProductId;
    }

    public String getProductName() {
        return this.mProductName;
    }

    public String getLogo() {
        return this.mLogo;
    }

    public int getCardType() {
        return this.mCardType;
    }

    public boolean needFinger() {
        return this.mFingerFlag == 1;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public String getOpenCardUrl() {
        return String.format(URL_OPEN_DOOR_CARD, new Object[]{Integer.valueOf(this.mCardType), this.mProductId});
    }
}
