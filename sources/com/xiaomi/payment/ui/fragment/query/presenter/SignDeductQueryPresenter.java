package com.xiaomi.payment.ui.fragment.query.presenter;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.base.Presenter;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxSignDeductQueryTask;
import com.xiaomi.payment.ui.fragment.query.AutoQuerier;
import com.xiaomi.payment.ui.fragment.query.contract.SignDeductQueryContract;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SignDeductQueryPresenter extends Presenter<SignDeductQueryContract.View> implements SignDeductQueryContract.Presenter {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f12515a = {0, 1, 1, 2, 3, 5, 7};
    private String b;
    /* access modifiers changed from: private */
    public String c;
    /* access modifiers changed from: private */
    public AutoQuerier d;
    private RxSignDeductQueryTask e;
    /* access modifiers changed from: private */
    public AutoQuerier.AutoQuerierCallback f = new AutoQuerier.AutoQuerierCallback() {
        public void a(long j) {
        }

        public void b(long j) {
        }

        public void a() {
            SignDeductQueryPresenter.this.o();
        }
    };

    public SignDeductQueryPresenter() {
        super(SignDeductQueryContract.View.class);
    }

    /* access modifiers changed from: protected */
    public void b(Bundle bundle) {
        super.b(bundle);
        this.b = n_().getString("processId");
        this.c = n_().getString(MibiConstants.gA);
        this.e = new RxSignDeductQueryTask(e(), f());
        o();
    }

    /* access modifiers changed from: private */
    public void o() {
        if (this.b == null || this.c == null) {
            throw new IllegalArgumentException("mProcessId or mDeductChannel should not be null here");
        }
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) this.b);
        sortedParameter.a(MibiConstants.gA, (Object) this.c);
        this.e.a(sortedParameter);
        Observable.create(this.e).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new SignDeductQueryTaskListener(e()));
    }

    private class SignDeductQueryTaskListener extends RxBaseErrorHandleTaskListener<RxSignDeductQueryTask.Result> {
        public SignDeductQueryTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            ((SignDeductQueryContract.View) SignDeductQueryPresenter.this.l()).a(str, (Bundle) null);
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x002c  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x00a8  */
        /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(com.xiaomi.payment.task.rxjava.RxSignDeductQueryTask.Result r6) {
            /*
                r5 = this;
                java.lang.String r0 = r6.mStatus
                int r1 = r0.hashCode()
                r2 = -1149187101(0xffffffffbb80cbe3, float:-0.003930555)
                r3 = 1
                if (r1 == r2) goto L_0x001c
                r2 = 2150174(0x20cf1e, float:3.013036E-39)
                if (r1 == r2) goto L_0x0012
                goto L_0x0026
            L_0x0012:
                java.lang.String r1 = "FAIL"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x0026
                r0 = 1
                goto L_0x0027
            L_0x001c:
                java.lang.String r1 = "SUCCESS"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x0026
                r0 = 0
                goto L_0x0027
            L_0x0026:
                r0 = -1
            L_0x0027:
                switch(r0) {
                    case 0: goto L_0x00a8;
                    case 1: goto L_0x002c;
                    default: goto L_0x002a;
                }
            L_0x002a:
                goto L_0x011a
            L_0x002c:
                com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter r0 = com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter.this
                com.xiaomi.payment.ui.fragment.query.AutoQuerier r0 = r0.d
                if (r0 != 0) goto L_0x0048
                com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter r0 = com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter.this
                com.xiaomi.payment.ui.fragment.query.AutoQuerier r1 = new com.xiaomi.payment.ui.fragment.query.AutoQuerier
                int[] r2 = com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter.f12515a
                com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter r4 = com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter.this
                com.xiaomi.payment.ui.fragment.query.AutoQuerier$AutoQuerierCallback r4 = r4.f
                r1.<init>(r2, r4)
                com.xiaomi.payment.ui.fragment.query.AutoQuerier unused = r0.d = r1
            L_0x0048:
                com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter r0 = com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter.this
                com.xiaomi.payment.ui.fragment.query.AutoQuerier r0 = r0.d
                boolean r0 = r0.a()
                if (r0 == 0) goto L_0x005f
                com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter r6 = com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter.this
                com.xiaomi.payment.ui.fragment.query.AutoQuerier r6 = r6.d
                r6.c()
                goto L_0x011a
            L_0x005f:
                android.os.Bundle r0 = new android.os.Bundle
                r0.<init>()
                java.lang.String r1 = "isDeduct"
                r0.putBoolean(r1, r3)
                java.lang.String r1 = "marketDeductId"
                java.lang.String r2 = r6.mMarketDeductId
                r0.putString(r1, r2)
                java.lang.String r1 = "orderId"
                java.lang.String r2 = r6.mOrderId
                r0.putString(r1, r2)
                java.lang.String r1 = "chargeStatus"
                java.lang.String r2 = r6.mChargeStatus
                r0.putString(r1, r2)
                java.lang.String r1 = "payStatus"
                int r2 = r6.mPayStatus
                r0.putInt(r1, r2)
                java.lang.String r1 = "deductSignStatus"
                int r6 = r6.mDeductSignStatus
                r0.putInt(r1, r6)
                com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter r6 = com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter.this
                com.mibi.common.base.IView r6 = r6.l()
                com.xiaomi.payment.ui.fragment.query.contract.SignDeductQueryContract$View r6 = (com.xiaomi.payment.ui.fragment.query.contract.SignDeductQueryContract.View) r6
                com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter r1 = com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter.this
                android.content.Context r1 = r1.e()
                android.content.res.Resources r1 = r1.getResources()
                int r2 = com.xiaomi.payment.platform.R.string.mibi_deduct_query_error
                java.lang.String r1 = r1.getString(r2)
                r6.a(r1, r0)
                goto L_0x011a
            L_0x00a8:
                android.os.Bundle r0 = new android.os.Bundle
                r0.<init>()
                com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter r1 = com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter.this
                java.lang.String r1 = r1.c
                com.xiaomi.payment.deduct.DeductManager$CHANNELS r2 = com.xiaomi.payment.deduct.DeductManager.CHANNELS.MIPAY
                java.lang.String r2 = r2.getChannel()
                boolean r1 = android.text.TextUtils.equals(r1, r2)
                if (r1 == 0) goto L_0x00d5
                java.lang.String r1 = "deductName"
                com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter r2 = com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter.this
                android.content.Context r2 = r2.e()
                android.content.res.Resources r2 = r2.getResources()
                int r4 = com.xiaomi.payment.platform.R.string.mibi_paytool_mipay
                java.lang.String r2 = r2.getString(r4)
                r0.putString(r1, r2)
                goto L_0x00fc
            L_0x00d5:
                com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter r1 = com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter.this
                java.lang.String r1 = r1.c
                com.xiaomi.payment.deduct.DeductManager$CHANNELS r2 = com.xiaomi.payment.deduct.DeductManager.CHANNELS.ALIPAY
                java.lang.String r2 = r2.getChannel()
                boolean r1 = android.text.TextUtils.equals(r1, r2)
                if (r1 == 0) goto L_0x00fc
                java.lang.String r1 = "deductName"
                com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter r2 = com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter.this
                android.content.Context r2 = r2.e()
                android.content.res.Resources r2 = r2.getResources()
                int r4 = com.xiaomi.payment.platform.R.string.mibi_paytool_alipay
                java.lang.String r2 = r2.getString(r4)
                r0.putString(r1, r2)
            L_0x00fc:
                java.lang.String r1 = "isDeduct"
                r0.putBoolean(r1, r3)
                java.lang.String r1 = "marketDeductId"
                java.lang.String r2 = r6.mMarketDeductId
                r0.putString(r1, r2)
                java.lang.String r1 = "orderId"
                java.lang.String r6 = r6.mOrderId
                r0.putString(r1, r6)
                com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter r6 = com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter.this
                com.mibi.common.base.IView r6 = r6.l()
                com.xiaomi.payment.ui.fragment.query.contract.SignDeductQueryContract$View r6 = (com.xiaomi.payment.ui.fragment.query.contract.SignDeductQueryContract.View) r6
                r6.e(r0)
            L_0x011a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter.SignDeductQueryTaskListener.a(com.xiaomi.payment.task.rxjava.RxSignDeductQueryTask$Result):void");
        }
    }
}
