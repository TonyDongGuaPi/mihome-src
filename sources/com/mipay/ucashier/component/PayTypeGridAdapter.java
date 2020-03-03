package com.mipay.ucashier.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mipay.common.data.a;
import com.mipay.ucashier.R;
import com.mipay.ucashier.data.PayType;

public class PayTypeGridAdapter extends a<PayType> {
    public PayTypeGridAdapter(Context context) {
        super(context);
    }

    public void bindView(View view, int i, PayType payType) {
        ((PayTypeGridItem) view).setItemData(payType);
    }

    public View newView(Context context, int i, PayType payType, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.ucashier_pay_type_grid_item, viewGroup, false);
    }
}
