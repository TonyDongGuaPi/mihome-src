package com.xiaomi.payment.deduct.presenter;

import android.os.Bundle;
import android.util.Log;
import com.mibi.common.base.Presenter;
import com.mibi.common.data.SortedParameter;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.DeductManager;
import com.xiaomi.payment.deduct.contract.DeductTypeListContract;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.ui.model.UploadAnalyticsModel;
import java.util.ArrayList;

public class DeductTypeListPresenter extends Presenter<DeductTypeListContract.View> implements DeductTypeListContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    private UploadAnalyticsModel f12284a;
    private String b;
    private ArrayList<RechargeType> c = new ArrayList<>();

    public DeductTypeListPresenter() {
        super(DeductTypeListContract.View.class);
    }

    /* access modifiers changed from: protected */
    public void b(Bundle bundle) {
        super.b(bundle);
        this.f12284a = new UploadAnalyticsModel(f());
        this.b = n_().getString("processId");
        this.c = (ArrayList) n_().getSerializable(MibiConstants.gy);
        if (this.c == null || this.c.isEmpty()) {
            ((DeductTypeListContract.View) l()).a(201, "mPayTypes should not be empty here");
        }
        ((DeductTypeListContract.View) l()).a(e(), this.c);
    }

    public void a(RechargeType rechargeType) {
        Bundle n_ = n_();
        n_.putString("processId", this.b);
        n_.putString(MibiConstants.gA, DeductManager.a().a(rechargeType.mRechargeMethods.get(0).mChannel).a());
        ((DeductTypeListContract.View) l()).e(n_);
        b(rechargeType);
    }

    public String m_() {
        return this.b;
    }

    private void b(RechargeType rechargeType) {
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("eventType", (Object) MibiConstants.cP);
        sortedParameter.a(MibiConstants.cM, (Object) rechargeType.mType);
        this.f12284a.a(sortedParameter);
    }

    public void a(int i, int i2, Bundle bundle) {
        super.a(i, i2, bundle);
        String obj = toString();
        Log.v(obj, this + ".doFragmentResult, requestCode = " + i + ",resultCode = " + i2);
        if (i == 100) {
            if (i2 == 200) {
                ((DeductTypeListContract.View) l()).d(bundle.getString(MibiConstants.gA));
            } else if (i2 == 201) {
                ((DeductTypeListContract.View) l()).a(i2, bundle.getString(MibiConstants.db));
            } else if (i2 == 300) {
                Bundle n_ = n_();
                n_.putString("processId", this.b);
                n_.putString(MibiConstants.gA, DeductManager.CHANNELS.ALIPAY.getChannel());
                ((DeductTypeListContract.View) l()).e(n_);
            } else if (i2 == 202 && f().m().a(this.b, "payment_skip_view", false)) {
                ((DeductTypeListContract.View) l()).a(i2, "uninstalled");
            }
        } else if (i == 200) {
            String str = null;
            if (bundle != null) {
                str = bundle.getString(MibiConstants.db);
            }
            ((DeductTypeListContract.View) l()).a(i2, str);
        }
    }
}
