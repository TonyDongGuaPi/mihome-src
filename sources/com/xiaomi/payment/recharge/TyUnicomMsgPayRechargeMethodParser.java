package com.xiaomi.payment.recharge;

public class TyUnicomMsgPayRechargeMethodParser extends MsgPayRechargeMethodParser {
    public RechargeMethod a() {
        return new TyUnicomMsgPayRechargeMethod();
    }
}
