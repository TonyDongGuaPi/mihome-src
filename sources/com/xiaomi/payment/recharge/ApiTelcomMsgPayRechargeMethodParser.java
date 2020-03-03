package com.xiaomi.payment.recharge;

public class ApiTelcomMsgPayRechargeMethodParser extends MsgPayRechargeMethodParser {
    public RechargeMethod a() {
        return new ApiTelcomMsgPayRechargeMethod();
    }
}
