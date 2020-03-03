package com.mi.global.shop.buy;

import android.content.Context;
import android.view.View;
import com.mi.global.shop.R;
import com.mi.global.shop.buy.payu.PayU;
import com.mi.global.shop.buy.payu.PayUtil;
import com.payu.sdk.Constants;
import com.payu.sdk.Params;

public class CardViewWraper extends AbstractCardViewWraper {
    /* access modifiers changed from: protected */
    public void c() {
    }

    public CardViewWraper(Context context, View view) {
        super(context, view, Cardfragment.f6798a, false);
        if (!PayU.av) {
            view.findViewById(R.id.store_card_container).setVisibility(8);
        }
        view.findViewById(R.id.ll_xiaomi_emi).setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void d() {
        Params params = new Params();
        String obj = this.g.getText().toString();
        if (obj.length() < 3) {
            obj = "PayU " + obj;
        }
        params.put(PayU.l, this.h.getText().toString().replace(" ", ""));
        params.put(PayU.m, String.valueOf(this.d));
        params.put(PayU.n, String.valueOf(this.c));
        params.put(PayU.o, obj);
        params.put(PayU.p, this.f.getText().toString());
        if (this.i.isChecked()) {
            params.put("store_card", "1");
        }
        PayUtil.a(((ConfirmActivity) this.f6776a).getconfirmOrder().f6881a, Constants.PAY_BANK_PAYU, "card", (ConfirmActivity) this.f6776a, PayU.PaymentMode.CC, params, this.h.getText().toString().replace(" ", ""), "", "", "", "");
    }
}
