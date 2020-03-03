package com.xiaomi.payment.deduct.presenter;

import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.base.Presenter;
import com.mibi.common.data.PrivacyManager;
import com.mibi.common.data.Session;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.data.MibiPrivacyUtils;
import com.xiaomi.payment.deduct.DeductManager;
import com.xiaomi.payment.deduct.contract.CheckDeductOrderContract;
import com.xiaomi.payment.deduct.model.CheckDeductOrderModel;
import com.xiaomi.payment.deduct.model.RequestDeductTypeListModel;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxCheckDeductTask;
import com.xiaomi.payment.task.rxjava.RxDeductTypeTask;
import com.xiaomi.payment.task.rxjava.RxStartProcessTask;
import com.xiaomi.payment.ui.model.StartProcessModel;

public class CheckDeductOrderPresenter extends Presenter<CheckDeductOrderContract.View> implements CheckDeductOrderContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    private StartProcessModel f12276a;
    private CheckDeductOrderModel b;
    private RequestDeductTypeListModel c;
    /* access modifiers changed from: private */
    public String d;
    private String e;
    private String f;

    public CheckDeductOrderPresenter() {
        super(CheckDeductOrderContract.View.class);
    }

    /* access modifiers changed from: protected */
    public void b(Bundle bundle) {
        super.b(bundle);
        this.f12276a = new StartProcessModel(f());
        this.b = new CheckDeductOrderModel(f());
        this.c = new RequestDeductTypeListModel(f());
        this.f = n_().getString("deductSignOrder");
        this.e = DeductManager.a(e());
        if (bundle != null) {
            return;
        }
        if (TextUtils.isEmpty(this.e)) {
            ((CheckDeductOrderContract.View) l()).a(201, e().getResources().getString(R.string.mibi_error_no_available_channels));
            return;
        }
        n();
    }

    private void n() {
        this.f12276a.a((StartProcessModel.OnProcessStartListener) new StartProcessModel.OnProcessStartListener() {
            public void a(int i, String str, Throwable th) {
                ((CheckDeductOrderContract.View) CheckDeductOrderPresenter.this.l()).a(201, str);
            }

            public void a(RxStartProcessTask.Result result) {
                String unused = CheckDeductOrderPresenter.this.d = result.f12434a;
                Session f = CheckDeductOrderPresenter.this.f();
                boolean z = CheckDeductOrderPresenter.this.n_().getBoolean("payment_skip_view", false);
                String string = CheckDeductOrderPresenter.this.n_().getString("deduct_channel");
                f.m().a(CheckDeductOrderPresenter.this.d, "payment_skip_view", (Object) Boolean.valueOf(z));
                f.m().a(CheckDeductOrderPresenter.this.d, "deduct_channel", (Object) string);
                PrivacyManager.a(CheckDeductOrderPresenter.this.f(), "105", MibiPrivacyUtils.d);
                CheckDeductOrderPresenter.this.o();
            }

            public void a(int i, String str) {
                ((CheckDeductOrderContract.View) CheckDeductOrderPresenter.this.l()).b(201, str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void o() {
        this.b.a(this.d, this.f, new CheckDeductOrderModel.OnCheckDeductOrderListener() {
            public void a(int i, String str, Throwable th) {
                ((CheckDeductOrderContract.View) CheckDeductOrderPresenter.this.l()).a(201, str);
            }

            public void a(RxCheckDeductTask.Result result) {
                CheckDeductOrderPresenter.this.p();
            }
        });
    }

    /* access modifiers changed from: private */
    public void p() {
        this.c.a(this.d, this.e, new RequestDeductTypeListModel.OnRequestDeductTypeListListener() {
            public void a(int i, String str, Throwable th) {
                ((CheckDeductOrderContract.View) CheckDeductOrderPresenter.this.l()).a(201, str);
            }

            public void a(RxDeductTypeTask.Result result) {
                if (result.mRechargeTypes == null || result.mRechargeTypes.size() == 0) {
                    ((CheckDeductOrderContract.View) CheckDeductOrderPresenter.this.l()).a(201, "no available channels");
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable(MibiConstants.gy, result.mRechargeTypes);
                bundle.putString("processId", CheckDeductOrderPresenter.this.d);
                if (CheckDeductOrderPresenter.this.f().m().a(CheckDeductOrderPresenter.this.d, "payment_skip_view", false)) {
                    ((CheckDeductOrderContract.View) CheckDeductOrderPresenter.this.l()).f(bundle);
                } else {
                    ((CheckDeductOrderContract.View) CheckDeductOrderPresenter.this.l()).e(bundle);
                }
            }
        });
    }

    public void a(int i, int i2, Bundle bundle) {
        super.a(i, i2, bundle);
        if (i == 1) {
            String str = null;
            if (bundle != null) {
                str = bundle.getString(MibiConstants.db);
            }
            ((CheckDeductOrderContract.View) l()).a(i2, str);
        }
    }
}
