package com.miui.tsmclient.pay;

import android.text.TextUtils;

public enum PayType {
    Mipay(com.mipay.ucashier.data.PayType.PAY_NAME_MIPAY),
    UCashier("UCASHIER"),
    EntryPay("ENTRYPAY"),
    UPInAppPay("UPINAPPPAY");
    
    private String mPayType;

    private PayType(String str) {
        this.mPayType = str;
    }

    public String toString() {
        return this.mPayType;
    }

    public static PayType getInstance(String str) {
        for (PayType payType : values()) {
            if (TextUtils.equals(payType.mPayType, str)) {
                return payType;
            }
        }
        return EntryPay;
    }
}
