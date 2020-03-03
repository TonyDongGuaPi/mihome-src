package com.xiaomi.payment.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mibi.common.data.CheckableArrayAdapter;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.PrepaidCardRechargeMethod;
import com.xiaomi.payment.ui.component.CarrierGridViewItem;

public class CarrierGridAdapter extends CheckableArrayAdapter<PrepaidCardRechargeMethod.CarrierInfo> {
    private LayoutInflater d;

    public CarrierGridAdapter(Context context) {
        super(context);
        this.d = LayoutInflater.from(context);
    }

    public View a(Context context, int i, PrepaidCardRechargeMethod.CarrierInfo carrierInfo, ViewGroup viewGroup) {
        return (CarrierGridViewItem) this.d.inflate(R.layout.mibi_carrier_grid_item, viewGroup, false);
    }

    public void a(View view, int i, PrepaidCardRechargeMethod.CarrierInfo carrierInfo, boolean z) {
        if (carrierInfo != null) {
            CarrierGridViewItem carrierGridViewItem = (CarrierGridViewItem) view;
            carrierGridViewItem.setCarrierInfo(carrierInfo.mName, carrierInfo.mTitle);
            carrierGridViewItem.setChecked(z);
            return;
        }
        throw new IllegalStateException("CarrierInfo data is null at this position " + i);
    }
}
