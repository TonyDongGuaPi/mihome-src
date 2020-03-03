package com.xiaomi.payment.channel.model;

import android.app.Activity;
import android.os.Bundle;
import com.mibi.common.data.SortedParameter;

public interface IMSGOrderModel {
    void a(int i, int i2, Bundle bundle);

    void a(SortedParameter sortedParameter, Activity activity, IMSGOrderListener iMSGOrderListener);
}
