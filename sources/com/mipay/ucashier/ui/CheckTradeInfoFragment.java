package com.mipay.ucashier.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.mipay.common.data.i;
import com.mipay.ucashier.R;
import com.mipay.ucashier.data.PayType;
import com.mipay.ucashier.data.UCashierConstants;
import java.util.ArrayList;

public class CheckTradeInfoFragment extends BaseUCashierFragment {
    /* access modifiers changed from: private */
    public ArrayList<PayType> g;
    /* access modifiers changed from: private */
    public String h;
    /* access modifiers changed from: private */
    public String i;
    /* access modifiers changed from: private */
    public PayType j;
    private String k;
    private Long l;
    private TextView m;
    private TextView n;
    private ViewGroup o;
    private TextView p;
    private ImageView q;
    private TextView r;
    private Button s;
    private View.OnClickListener t = new a(this);
    private View.OnClickListener u = new b(this);

    /* access modifiers changed from: protected */
    public void a(Bundle bundle) {
        super.a(bundle);
        this.j = (PayType) bundle.getSerializable(UCashierConstants.KEY_LAST_PAY_TYPE);
        if (this.j != null) {
            this.g = (ArrayList) bundle.getSerializable(UCashierConstants.KEY_PAY_TYPES);
            if (this.g != null) {
                this.h = bundle.getString("tradeId");
                if (!TextUtils.isEmpty(this.h)) {
                    this.i = bundle.getString("deviceId");
                    if (!TextUtils.isEmpty(this.i)) {
                        this.k = bundle.getString(UCashierConstants.KEY_PRODUCT_NAME);
                        this.l = Long.valueOf(bundle.getLong(UCashierConstants.KEY_TOTAL_FEE));
                        return;
                    }
                    throw new IllegalArgumentException("deviceId is null");
                }
                throw new IllegalArgumentException("tradeId is null");
            }
            throw new IllegalArgumentException("payTypes is null");
        }
        throw new IllegalArgumentException("lastPayType is null");
    }

    public void doActivityCreated(Bundle bundle) {
        super.doActivityCreated(bundle);
        this.m.setText(this.k);
        this.n.setText(getString(R.string.ucashier_trade_price, new Object[]{i.a(this.l.longValue())}));
        this.p.setText(this.j.mPayTitle);
        if (!TextUtils.isEmpty(this.j.mPayTip)) {
            this.r.setText(this.j.mPayTip);
            this.r.setVisibility(0);
        }
        if (this.g.size() > 1) {
            this.q.setVisibility(0);
            this.o.setOnClickListener(this.t);
        }
        this.s.setOnClickListener(this.u);
    }

    public View doInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.ucashier_check_trade_info, viewGroup, false);
        this.m = (TextView) inflate.findViewById(R.id.trade_title);
        this.n = (TextView) inflate.findViewById(R.id.trade_price);
        this.o = (ViewGroup) inflate.findViewById(R.id.pay_method_container);
        this.p = (TextView) inflate.findViewById(R.id.pay_method);
        this.q = (ImageView) inflate.findViewById(R.id.arrow);
        this.r = (TextView) inflate.findViewById(R.id.trade_tip);
        this.s = (Button) inflate.findViewById(R.id.pay_button);
        return inflate;
    }
}
