package com.xiaomi.payment.pay.model;

import android.os.Bundle;
import com.mibi.common.data.SortedParameter;

public interface IOrderCheckModel {

    public interface OnCheckAuthListener {
        void a(int i, String str, Bundle bundle);

        void a(Bundle bundle);
    }

    void a(SortedParameter sortedParameter, OnCheckAuthListener onCheckAuthListener);
}
