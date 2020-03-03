package com.xiaomi.payment.recharge;

import com.xiaomi.payment.data.EntryData;
import java.io.Serializable;

public class RechargeMethod implements Serializable {
    public String mChannel = null;
    public String mContentHint = null;
    public EntryData mContentHintEntryData = null;
    public String mDiscountDesc = null;
    public double mDiscountRate;
    public String mName = null;
    public String mTitle = null;
}
