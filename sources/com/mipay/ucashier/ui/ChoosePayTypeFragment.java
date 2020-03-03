package com.mipay.ucashier.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.mipay.ucashier.R;
import com.mipay.ucashier.component.PayTypeGridAdapter;
import com.mipay.ucashier.data.PayType;
import com.mipay.ucashier.data.UCashierConstants;
import java.util.ArrayList;

public class ChoosePayTypeFragment extends BaseUCashierFragment {
    private ArrayList<PayType> g;
    /* access modifiers changed from: private */
    public String h;
    /* access modifiers changed from: private */
    public String i;
    private GridView j;
    private PayTypeGridAdapter k;
    private AdapterView.OnItemClickListener l = new c(this);

    /* access modifiers changed from: protected */
    public void a(Bundle bundle) {
        super.a(bundle);
        this.g = (ArrayList) bundle.getSerializable(UCashierConstants.KEY_PAY_TYPES);
        if (this.g != null) {
            this.h = bundle.getString("tradeId");
            if (!TextUtils.isEmpty(this.h)) {
                this.i = bundle.getString("deviceId");
                if (TextUtils.isEmpty(this.i)) {
                    throw new IllegalArgumentException("deviceId is null");
                }
                return;
            }
            throw new IllegalArgumentException("tradeId is null");
        }
        throw new IllegalArgumentException("payTypes is null");
    }

    public void doActivityCreated(Bundle bundle) {
        super.doActivityCreated(bundle);
        this.k = new PayTypeGridAdapter(getActivity());
        this.k.updateData(this.g);
        this.j.setAdapter(this.k);
        this.j.setOnItemClickListener(this.l);
    }

    public View doInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.ucashier_choose_pay_type, viewGroup, false);
        this.j = (GridView) inflate.findViewById(R.id.grid_view);
        return inflate;
    }
}
