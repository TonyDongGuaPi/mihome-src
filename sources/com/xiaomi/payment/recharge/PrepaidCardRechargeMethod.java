package com.xiaomi.payment.recharge;

import java.io.Serializable;
import java.util.ArrayList;

public class PrepaidCardRechargeMethod extends RechargeMethod {
    public ArrayList<CarrierInfo> mCarrierInfos = new ArrayList<>();

    public static class CardPwdLen implements Serializable {
        public int mCardLen;
        public int mPwdLen;
    }

    public static class CarrierInfo implements Serializable, Comparable<CarrierInfo> {
        public ArrayList<CardPwdLen> mCardPwdLens = new ArrayList<>();
        public boolean mIsDefault;
        public ArrayList<Long> mMoneyValues = new ArrayList<>();
        public String mName;
        public int mOrder;
        public String mTitle;

        public int compareTo(CarrierInfo carrierInfo) {
            if (this.mOrder == carrierInfo.mOrder) {
                return 0;
            }
            return this.mOrder < carrierInfo.mOrder ? -1 : 1;
        }
    }
}
