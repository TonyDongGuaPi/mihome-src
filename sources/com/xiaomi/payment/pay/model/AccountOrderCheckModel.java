package com.xiaomi.payment.pay.model;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.data.Utils;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import com.mibi.common.exception.rxjava.ServerErrorCodeExceptionHandler;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.pay.PaymentOrderInfoFragment;
import com.xiaomi.payment.pay.autoPay.AutoPayFragment;
import com.xiaomi.payment.pay.model.IOrderCheckModel;
import com.xiaomi.payment.pay.model.UploadOrderGiftcardModel;
import com.xiaomi.payment.recharge.RechargeManager;
import com.xiaomi.payment.task.rxjava.RxCheckPaymentTask;
import com.xiaomi.payment.task.rxjava.RxRechargeTypeTask;
import com.xiaomi.payment.ui.PadFixedWidthActivity;
import com.xiaomi.payment.ui.PhonePaymentCommonActivity;
import com.xiaomi.payment.ui.model.RechargeTypeModel;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AccountOrderCheckModel extends Model implements IOrderCheckModel {

    /* renamed from: a  reason: collision with root package name */
    private RxCheckPaymentTask f12369a;
    /* access modifiers changed from: private */
    public IOrderCheckModel.OnCheckAuthListener b;
    /* access modifiers changed from: private */
    public String c;
    private RechargeTypeModel d;
    private String e;
    private boolean f;
    /* access modifiers changed from: private */
    public boolean g;
    /* access modifiers changed from: private */
    public boolean h;

    public AccountOrderCheckModel(Session session) {
        super(session);
        if (this.f12369a == null) {
            this.f12369a = new RxCheckPaymentTask(l_(), c());
        }
    }

    public void a(SortedParameter sortedParameter, IOrderCheckModel.OnCheckAuthListener onCheckAuthListener) {
        Assert.assertNotNull(sortedParameter);
        Assert.assertNotNull(onCheckAuthListener);
        this.b = onCheckAuthListener;
        this.c = sortedParameter.f("processId");
        this.e = sortedParameter.f(MibiConstants.cX);
        this.f = sortedParameter.a(MibiConstants.eD, true);
        this.g = sortedParameter.a(MibiConstants.eE, true);
        this.h = sortedParameter.a(MibiConstants.eH, true);
        this.f12369a.a(sortedParameter);
        CheckPaymentTaskListener checkPaymentTaskListener = new CheckPaymentTaskListener(l_());
        checkPaymentTaskListener.a().a((ExceptionDispatcher.ExceptionHandler) new CheckPaymentServerErrorCodeExceptionHandler(l_()));
        Observable.create(this.f12369a).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(checkPaymentTaskListener);
    }

    private class CheckPaymentTaskListener extends RxBaseErrorHandleTaskListener<RxCheckPaymentTask.Result> {
        public CheckPaymentTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            AccountOrderCheckModel.this.b.a(i, str, new Bundle());
        }

        /* access modifiers changed from: protected */
        public void a(final RxCheckPaymentTask.Result result) {
            if (result.mResult != null) {
                Bundle bundle = new Bundle();
                bundle.putString("payment_payment_result", result.mResult);
                AccountOrderCheckModel.this.b.a(result.mResultErrorCode, result.mResultErrorDesc, bundle);
                return;
            }
            UploadOrderGiftcardModel uploadOrderGiftcardModel = new UploadOrderGiftcardModel(AccountOrderCheckModel.this.c());
            SortedParameter sortedParameter = new SortedParameter();
            sortedParameter.a("processId", (Object) AccountOrderCheckModel.this.c);
            sortedParameter.a(MibiConstants.eE, (Object) Boolean.valueOf(AccountOrderCheckModel.this.g));
            sortedParameter.a(MibiConstants.eH, (Object) Boolean.valueOf(AccountOrderCheckModel.this.h));
            uploadOrderGiftcardModel.a(sortedParameter, new UploadOrderGiftcardModel.OnUploadOrderGiftcardListener() {
                public void a() {
                }

                public void a(int i, String str) {
                    AccountOrderCheckModel.this.b.a(i, str, new Bundle());
                }

                public void b() {
                    AccountOrderCheckModel.this.a(result);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(RxCheckPaymentTask.Result result) {
        long j;
        final Bundle bundle = new Bundle();
        bundle.putSerializable(MibiConstants.cW, result);
        bundle.putString("processId", this.c);
        bundle.putString(MibiConstants.cX, this.e);
        bundle.putSerializable("fragment", TextUtils.isEmpty(this.e) ? PaymentOrderInfoFragment.class : AutoPayFragment.class);
        bundle.putSerializable("activity", Utils.b() ? PadFixedWidthActivity.class : PhonePaymentCommonActivity.class);
        bundle.putLong("price", result.mOrderPrice);
        if (d()) {
            bundle.putLong(MibiConstants.dQ, result.mOrderPrice);
            j = a(this.f, this.g, this.h, result);
        } else {
            j = result.mOrderPrice - result.getTotalBalance();
        }
        if (j > 0) {
            bundle.putLong(MibiConstants.dh, j);
            SortedParameter sortedParameter = new SortedParameter();
            sortedParameter.a("processId", (Object) this.c);
            sortedParameter.a(MibiConstants.dq, (Object) Long.valueOf(j));
            sortedParameter.a(MibiConstants.gp, (Object) RechargeManager.a(l_()));
            this.d = new RechargeTypeModel(c());
            this.d.a(sortedParameter, new RechargeTypeModel.OnRechargeTypeListener() {
                public void a(int i, String str, Throwable th) {
                    AccountOrderCheckModel.this.b.a(i, str, new Bundle());
                }

                public void a(RxRechargeTypeTask.Result result) {
                    bundle.putSerializable(MibiConstants.cF, result);
                    AccountOrderCheckModel.this.b.a(bundle);
                }
            });
            return;
        }
        this.b.a(bundle);
    }

    private class CheckPaymentServerErrorCodeExceptionHandler extends ServerErrorCodeExceptionHandler {
        public CheckPaymentServerErrorCodeExceptionHandler(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public boolean a(int i, String str, Object obj) {
            int i2;
            if (i == 1986) {
                i2 = 7;
            } else if (i != 1991) {
                return false;
            } else {
                i2 = 13;
            }
            Bundle bundle = null;
            RxCheckPaymentTask.Result result = (RxCheckPaymentTask.Result) obj;
            if (result.mResult != null) {
                bundle = new Bundle();
                bundle.putString("payment_payment_result", result.mResult);
            }
            AccountOrderCheckModel.this.b.a(i2, str, bundle);
            return true;
        }
    }

    private boolean d() {
        return !TextUtils.isEmpty(this.e);
    }

    private static long a(boolean z, boolean z2, boolean z3, RxCheckPaymentTask.Result result) {
        long j = z ? result.mBalance + 0 : 0;
        if (z2) {
            j += result.mGiftcardValue;
        }
        if (z3) {
            j += result.mPartnerGiftcardValue;
        }
        return Math.max(result.mOrderPrice - j, 0);
    }
}
