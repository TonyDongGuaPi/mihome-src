package com.xiaomi.payment.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mibi.common.data.ArrayAdapter;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.ui.item.PayListItem;

public class DeductListAdapter extends ArrayAdapter<RechargeType> {
    public DeductListAdapter(Context context) {
        super(context);
    }

    public View a(Context context, int i, RechargeType rechargeType, ViewGroup viewGroup) {
        PayListItem payListItem = (PayListItem) LayoutInflater.from(this.f7498a).inflate(R.layout.mibi_pay_list_item, viewGroup, false);
        payListItem.bind();
        return payListItem;
    }

    public void a(View view, int i, RechargeType rechargeType) {
        if (rechargeType != null) {
            ((PayListItem) view).rebind(rechargeType);
            return;
        }
        throw new IllegalStateException("RechargeType data is null at this position " + i);
    }
}
