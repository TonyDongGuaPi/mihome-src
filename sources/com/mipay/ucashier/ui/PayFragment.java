package com.mipay.ucashier.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.mipay.common.base.s;
import com.mipay.common.data.h;
import com.mipay.ucashier.R;
import com.mipay.ucashier.data.PayType;
import com.mipay.ucashier.pay.IPayEntry;
import com.mipay.ucashier.pay.PayEntryFactory;
import com.mipay.ucashier.task.BaseUCashierTaskAdapter;
import com.mipay.ucashier.task.PayTradeTask;

public class PayFragment extends BaseUCashierFragment {
    a g;
    IPayEntry h;
    /* access modifiers changed from: private */
    public String i;
    /* access modifiers changed from: private */
    public String j;
    /* access modifiers changed from: private */
    public PayType k;

    private class a extends BaseUCashierTaskAdapter<PayTradeTask, Void, PayTradeTask.Result> {
        public a(Context context, s sVar) {
            super(context, sVar, new PayTradeTask(context));
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void c(PayTradeTask.Result result) {
            PayFragment.this.h.pay(PayFragment.this, result.mPayInfo);
        }

        /* access modifiers changed from: protected */
        public void a(String str, int i, PayTradeTask.Result result) {
            PayFragment.this.a(i, str);
            PayFragment.this.finish("trade", false);
        }

        /* access modifiers changed from: protected */
        public h c() {
            h hVar = new h();
            hVar.a("tradeId", (Object) PayFragment.this.i);
            hVar.a("payType", (Object) Integer.valueOf(PayFragment.this.k.mPayType));
            hVar.a("deviceId", (Object) PayFragment.this.j);
            return hVar;
        }

        /* access modifiers changed from: protected */
        public void d() {
            super.d();
            PayFragment.this.a(R.string.ucashier_handle_loading);
        }

        /* access modifiers changed from: protected */
        public boolean e() {
            PayFragment.this.e();
            return super.e();
        }
    }

    /* access modifiers changed from: protected */
    public void a(Bundle bundle) {
        super.a(bundle);
        this.i = bundle.getString("tradeId");
        if (!TextUtils.isEmpty(this.i)) {
            this.j = bundle.getString("deviceId");
            if (!TextUtils.isEmpty(this.j)) {
                this.k = (PayType) bundle.getSerializable("payType");
                if (this.k == null) {
                    throw new IllegalArgumentException("payType is null");
                }
                return;
            }
            throw new IllegalArgumentException("deviceId is null");
        }
        throw new IllegalArgumentException("tradeId is null");
    }

    public void doActivityCreated(Bundle bundle) {
        super.doActivityCreated(bundle);
        this.h = PayEntryFactory.get(this.k);
        this.g = new a(getActivity(), getTaskManager());
        this.g.start();
    }

    public void doActivityResult(int i2, int i3, Intent intent) {
        super.doActivityResult(i2, i3, intent);
        this.h.handleActivityResult(i2, i3, intent);
    }
}
