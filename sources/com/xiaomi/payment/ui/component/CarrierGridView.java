package com.xiaomi.payment.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import com.mibi.common.component.CommonGridView;
import com.xiaomi.payment.recharge.PrepaidCardRechargeMethod;
import com.xiaomi.payment.ui.adapter.CarrierGridAdapter;

public class CarrierGridView extends CommonGridView<PrepaidCardRechargeMethod.CarrierInfo> {
    public CarrierGridView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CarrierGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setAdapter((ListAdapter) new CarrierGridAdapter(context));
    }
}
