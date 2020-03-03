package com.mipay.ucashier.pay;

import android.text.TextUtils;
import com.mipay.ucashier.data.PayType;
import com.mipay.ucashier.pay.alipay.AlipayEntry;
import com.mipay.ucashier.pay.mipay.MipayEntry;

public class PayEntryFactory {
    public static IPayEntry get(PayType payType) {
        if (TextUtils.equals(payType.mPayName, PayType.PAY_NAME_MIPAY)) {
            return new MipayEntry();
        }
        if (TextUtils.equals(payType.mPayName, PayType.PAY_NAME_ALIPAY)) {
            return new AlipayEntry();
        }
        throw new IllegalArgumentException("unknown pay type: " + payType.mPayName);
    }
}
