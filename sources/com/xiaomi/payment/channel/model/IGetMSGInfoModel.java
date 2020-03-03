package com.xiaomi.payment.channel.model;

import android.app.Activity;
import com.mibi.common.data.SortedParameter;

public interface IGetMSGInfoModel {
    void a(SortedParameter sortedParameter, Activity activity, IGetMSGInfoListener iGetMSGInfoListener);

    String[] d();
}
