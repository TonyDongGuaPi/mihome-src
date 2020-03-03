package com.mi.global.shop.buy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mi.global.shop.R;
import com.mi.global.shop.buy.cod.CODViewHelper;
import com.mi.log.LogUtil;

public class CODfragment extends MiFragment {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6797a = "cash_on_delivery";
    private static final String c = "CODfragment";
    private View b;
    private CODViewHelper d;

    public void onCreate(Bundle bundle) {
        LogUtil.b(c, "onCreate");
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.b(c, "onCreateView");
        if (this.b == null) {
            this.b = layoutInflater.inflate(R.layout.shop_buy_confirm_payment_cod, viewGroup, false);
            this.d = new CODViewHelper((ConfirmActivity) getActivity(), this.b, ((ConfirmActivity) getActivity()).getOrderPaymentInfo());
            this.d.a();
            this.d.b();
        } else {
            ViewGroup viewGroup2 = (ViewGroup) this.b.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.b);
                LogUtil.b(c, "onCreateView remove from parent");
            }
        }
        return this.b;
    }

    public void onResume() {
        LogUtil.b(c, "onResume");
        if (this.d != null) {
            this.d.d();
        }
        super.onResume();
    }

    public void a() {
        if (this.d != null) {
            this.d.a(((ConfirmActivity) getActivity()).getOrderPaymentInfo());
        }
    }
}
