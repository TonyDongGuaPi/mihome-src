package com.xiaomi.jr.mipay.codepay.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.jr.mipay.codepay.R;
import com.xiaomi.jr.mipay.codepay.data.CodePayConfirmParams;
import com.xiaomi.jr.mipay.codepay.data.DoPayResult;
import com.xiaomi.jr.mipay.codepay.data.PayType;
import com.xiaomi.jr.mipay.codepay.data.TradeResult;
import com.xiaomi.jr.mipay.codepay.presenter.CodePayConfirmContract;
import com.xiaomi.jr.mipay.codepay.presenter.CodePayTrader;
import com.xiaomi.jr.mipay.codepay.ui.BaseFragment;
import com.xiaomi.jr.mipay.codepay.ui.CodePayConfirmFragment;
import com.xiaomi.jr.mipay.codepay.util.CodePayConstants;
import com.xiaomi.jr.mipay.codepay.util.CodePayUtils;
import com.xiaomi.jr.mipay.codepay.validate.FooterViewType;
import com.xiaomi.jr.mipay.codepay.validate.ValidateType;
import java.util.List;

public class CodePayConfirmPresenter extends Presenter implements CodePayConfirmContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10898a = "CodePayConfirmPresenter";
    private CodePayConfirmParams b;
    private PayType d;
    /* access modifiers changed from: private */
    public int e;
    /* access modifiers changed from: private */
    public String f;
    private String g;
    private CodePayTrader h = new CodePayTrader();
    /* access modifiers changed from: private */
    public CodePayConfirmContract.View i;
    private CodePayTrader.TradeCallback j = new CodePayTrader.TradeCallback() {
        public void a(int i, String str, DoPayResult doPayResult) {
            if (((CodePayConfirmFragment) CodePayConfirmPresenter.this.i).isAdded()) {
                if (i == 3000004) {
                    int unused = CodePayConfirmPresenter.this.e = 1;
                    CodePayConfirmPresenter.this.c().a(ValidateType.b(1));
                } else if (i == 2010003) {
                    CodePayConfirmPresenter.this.c().a(doPayResult.b, doPayResult.c, doPayResult.d);
                } else if (i == 3000002) {
                    CodePayConfirmPresenter.this.c().a(CodePayConfirmPresenter.this.f, doPayResult.e);
                } else {
                    a(i, str);
                }
                a();
            }
        }

        public void a(int i, String str, TradeResult tradeResult) {
            if (((CodePayConfirmFragment) CodePayConfirmPresenter.this.i).isAdded()) {
                if (i != 200 || !TextUtils.equals(tradeResult.e, "TRADE_SUCCESS")) {
                    a(i, str);
                } else {
                    CodePayUtils.a((CodePayConfirmFragment) CodePayConfirmPresenter.this.c(), tradeResult.e());
                }
                a();
            }
        }

        private void a(int i, String str) {
            CodePayConfirmPresenter.this.c().a(i, str);
        }

        private void a() {
            CodePayConfirmPresenter.this.c().a(true);
            CodePayConfirmPresenter.this.c().a(ValidateType.b(CodePayConfirmPresenter.this.e));
        }
    };

    public CodePayConfirmPresenter(CodePayConfirmContract.View view) {
        this.i = view;
    }

    public CodePayConfirmContract.View c() {
        return this.i;
    }

    public void d() {
        this.c = ((CodePayConfirmFragment) this.i).getActivity().getApplicationContext();
        Bundle arguments = ((CodePayConfirmFragment) this.i).getArguments();
        this.b = (CodePayConfirmParams) arguments.getSerializable("params");
        this.g = this.b.mTradeId;
        this.f = arguments.getString("processId");
        this.d = this.b.getCurPayType();
        int i2 = this.b.mValidateType;
        if (i2 != 4) {
            i2 = 1;
        }
        this.e = i2;
        if (TextUtils.isEmpty(this.f) || TextUtils.isEmpty(this.g)) {
            throw new IllegalArgumentException("argument is illegal");
        }
        e();
    }

    private void e() {
        String string = this.c.getString(ValidateType.a(this.e));
        if (TextUtils.isEmpty(string)) {
            string = this.c.getString(R.string.jr_mipay_counter_confirm_title);
        }
        c().a(string);
        c().a(this.b.mTradeSummary, this.b.mTradeAmount);
        c().a(this.d);
        c().a(ValidateType.b(this.e));
    }

    public void a(int i2, int i3, Intent intent) {
        super.a(i2, i3, intent);
        if (i3 == -1) {
            if (i2 == 1001) {
                PayType payType = (PayType) intent.getSerializableExtra(CodePayConstants.k);
                if (payType != null) {
                    this.d = payType;
                    c().a(this.d);
                }
            } else if (i2 == 1002) {
                ((BaseFragment) c()).d();
            }
        }
    }

    public void a(String str) {
        this.h.a(this.f, this.b.mAuthCode, this.d.mPayTypeId, this.g, this.e, str, false, this.j);
        c().a(false);
        c().a(FooterViewType.LOADING);
    }

    public PayType a() {
        return this.d;
    }

    public List<PayType> b() {
        return this.b.mSupportPayTypeList;
    }
}
