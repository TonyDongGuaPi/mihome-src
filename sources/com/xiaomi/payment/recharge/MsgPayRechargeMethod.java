package com.xiaomi.payment.recharge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MsgPayRechargeMethod extends RechargeMethod {
    public ArrayList<Long> mDenominationMibiList = new ArrayList<>();
    public ArrayList<MessagePayDominationInfo> mInfos = new ArrayList<>();
    public int mMibi;
    public int mMoney;
    public Map<Long, String> mPayIdMap = new HashMap();

    public static class MessagePayDominationInfo implements Serializable {
        public long mChargeFee;
        public long mMibiAmount;
        public String mPayId;
    }
}
