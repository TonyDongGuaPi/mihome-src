package com.xiaomi.payment.recharge;

public class WoUnicomMsgPayRechargeMethodParser extends MsgPayRechargeMethodParser {
    public RechargeMethod a() {
        return new WoUnicomMsgPayRechargeMethod();
    }
}
