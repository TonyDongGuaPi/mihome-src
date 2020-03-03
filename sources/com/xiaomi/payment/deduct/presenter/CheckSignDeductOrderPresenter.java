package com.xiaomi.payment.deduct.presenter;

import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.base.Presenter;
import com.mibi.common.data.PrivacyManager;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.data.MibiPrivacyUtils;
import com.xiaomi.payment.deduct.DeductManager;
import com.xiaomi.payment.deduct.contract.CheckSignDeductOrderContract;
import com.xiaomi.payment.deduct.model.CheckSignDeductOrderModel;
import com.xiaomi.payment.deduct.model.RequestDeductTypeListModel;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.task.rxjava.RxCheckSignDeductTask;
import com.xiaomi.payment.task.rxjava.RxDeductTypeTask;
import com.xiaomi.payment.task.rxjava.RxStartProcessTask;
import com.xiaomi.payment.ui.model.StartProcessModel;
import java.util.List;

public class CheckSignDeductOrderPresenter extends Presenter<CheckSignDeductOrderContract.View> implements CheckSignDeductOrderContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    private StartProcessModel f12280a;
    private CheckSignDeductOrderModel b;
    private RequestDeductTypeListModel c;
    /* access modifiers changed from: private */
    public String d;
    private String e;
    private String f;
    /* access modifiers changed from: private */
    public String g;

    public CheckSignDeductOrderPresenter() {
        super(CheckSignDeductOrderContract.View.class);
    }

    /* access modifiers changed from: protected */
    public void b(Bundle bundle) {
        super.b(bundle);
        this.f12280a = new StartProcessModel(f());
        this.b = new CheckSignDeductOrderModel(f());
        this.c = new RequestDeductTypeListModel(f());
        this.f = n_().getString("deductSignOrder");
        this.g = n_().getString("sign_deduct_channel");
        this.e = DeductManager.a(e());
        if (bundle != null) {
            return;
        }
        if (TextUtils.isEmpty(this.e)) {
            ((CheckSignDeductOrderContract.View) l()).a(204, e().getResources().getString(R.string.mibi_error_no_available_channels));
            return;
        }
        n();
    }

    private void n() {
        this.f12280a.a((StartProcessModel.OnProcessStartListener) new StartProcessModel.OnProcessStartListener() {
            public void a(int i, String str, Throwable th) {
                ((CheckSignDeductOrderContract.View) CheckSignDeductOrderPresenter.this.l()).a(204, str);
            }

            public void a(RxStartProcessTask.Result result) {
                String unused = CheckSignDeductOrderPresenter.this.d = result.f12434a;
                PrivacyManager.a(CheckSignDeductOrderPresenter.this.f(), "105", MibiPrivacyUtils.d);
                CheckSignDeductOrderPresenter.this.o();
            }

            public void a(int i, String str) {
                ((CheckSignDeductOrderContract.View) CheckSignDeductOrderPresenter.this.l()).b(204, str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void o() {
        this.b.a(this.d, this.f, new CheckSignDeductOrderModel.OnCheckSignDeductOrderListener() {
            public void a(int i, String str, Throwable th) {
                ((CheckSignDeductOrderContract.View) CheckSignDeductOrderPresenter.this.l()).a(204, str);
            }

            public void a(RxCheckSignDeductTask.Result result) {
                CheckSignDeductOrderPresenter.this.p();
            }
        });
    }

    /* access modifiers changed from: private */
    public void p() {
        this.c.a(this.d, this.e, new RequestDeductTypeListModel.OnRequestDeductTypeListListener() {
            public void a(int i, String str, Throwable th) {
                ((CheckSignDeductOrderContract.View) CheckSignDeductOrderPresenter.this.l()).a(204, str);
            }

            public void a(RxDeductTypeTask.Result result) {
                if (result.mRechargeTypes == null || result.mRechargeTypes.size() == 0) {
                    ((CheckSignDeductOrderContract.View) CheckSignDeductOrderPresenter.this.l()).a(204, "no available channels");
                }
                if (TextUtils.isEmpty(CheckSignDeductOrderPresenter.this.g) || !CheckSignDeductOrderPresenter.this.a(CheckSignDeductOrderPresenter.this.g, (List<RechargeType>) result.mRechargeTypes)) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(MibiConstants.gy, result.mRechargeTypes);
                    bundle.putString("processId", CheckSignDeductOrderPresenter.this.d);
                    ((CheckSignDeductOrderContract.View) CheckSignDeductOrderPresenter.this.l()).e(bundle);
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("processId", CheckSignDeductOrderPresenter.this.d);
                bundle2.putString(MibiConstants.gA, CheckSignDeductOrderPresenter.this.g);
                ((CheckSignDeductOrderContract.View) CheckSignDeductOrderPresenter.this.l()).f(bundle2);
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean a(String str, List<RechargeType> list) {
        for (RechargeType rechargeType : list) {
            if (str.equals(rechargeType.mRechargeMethods.get(0).mChannel)) {
                return true;
            }
        }
        return false;
    }

    public void a(int i, int i2, Bundle bundle) {
        super.a(i, i2, bundle);
        if (i == 1) {
            String str = null;
            if (bundle != null) {
                str = bundle.getString(MibiConstants.db);
            }
            ((CheckSignDeductOrderContract.View) l()).a(i2, str);
        }
    }
}
