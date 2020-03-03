package com.xiaomi.payment.deduct;

import com.xiaomi.payment.recharge.BaseRechargeMethodParser;
import com.xiaomi.payment.recharge.RechargeMethod;

public class WxpayDeductMethodParaser extends BaseRechargeMethodParser {
    public RechargeMethod a() {
        return new WxpayDeductMethod();
    }
}
