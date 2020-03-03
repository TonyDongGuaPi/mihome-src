package com.xiaomi.shop2.model;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.ArrayList;

public class BaseUserCentralCounterInfo {
    @JSONField(name = "plugin_info")
    public ArrayList<PluginCounter> mCountPluginInfo;
    @JSONField(name = "coupon_count")
    public int mCouponCount;
    @JSONField(name = "unreceive_count")
    public int mDeliveryCount;
    @JSONField(name = "feedback_count")
    public int mFeedbackCount;
    @JSONField(name = "invite_count")
    public int mInviteCount;
    @JSONField(name = "msg_count")
    public int mMsgCount;
    @JSONField(name = "payment_count")
    public int mPaymentCount;
    @JSONField(name = "repair_count")
    public int mRepairCount;
    @JSONField(name = "total_count")
    public int mTotalCount;
    @JSONField(name = "uncomment_count")
    public int mUncommentCount;
}
