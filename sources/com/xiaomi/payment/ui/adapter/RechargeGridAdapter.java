package com.xiaomi.payment.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mibi.common.data.ArrayAdapter;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.ui.item.RechargeGridItem;

public class RechargeGridAdapter extends ArrayAdapter<RechargeType> {
    public RechargeGridAdapter(Context context) {
        super(context);
    }

    public View a(Context context, int i, RechargeType rechargeType, ViewGroup viewGroup) {
        RechargeGridItem rechargeGridItem = (RechargeGridItem) LayoutInflater.from(this.f7498a).inflate(R.layout.mibi_recharge_grid_item, viewGroup, false);
        rechargeGridItem.bind();
        return rechargeGridItem;
    }

    public void a(View view, int i, RechargeType rechargeType) {
        if (rechargeType != null) {
            ((RechargeGridItem) view).rebind(rechargeType);
            return;
        }
        throw new IllegalStateException("RechargeType data is null at this position " + i);
    }
}
