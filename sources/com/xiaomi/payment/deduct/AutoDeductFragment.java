package com.xiaomi.payment.deduct;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.StepFragment;
import com.mibi.common.decorator.AutoSave;
import com.mibi.common.ui.TranslucentActivity;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.contract.DeductTypeListContract;
import com.xiaomi.payment.deduct.presenter.DeductTypeListPresenter;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.ui.fragment.query.DeductQueryFragment;
import java.util.ArrayList;
import java.util.Iterator;

public class AutoDeductFragment extends BaseFragment implements AutoSave, DeductTypeListContract.View {
    private static final String t = "AutoDeductFragment";

    public IPresenter onCreatePresenter() {
        return new DeductTypeListPresenter();
    }

    public void a(Bundle bundle) {
        super.a(bundle);
        d(false);
    }

    public void a(Context context, ArrayList<RechargeType> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            Log.e(t, "data is empty");
            a(201, "data is empty");
            return;
        }
        String str = (String) p().m().c(getArguments().getString("processId"), "deduct_channel");
        RechargeType rechargeType = null;
        Iterator<RechargeType> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            RechargeType next = it.next();
            if (TextUtils.equals(next.mType, str)) {
                rechargeType = next;
                break;
            }
        }
        if (rechargeType == null) {
            Log.e(t, "recharge type is empty");
            a(201, "recharge type is empty");
            return;
        }
        ((DeductTypeListContract.Presenter) H_()).a(rechargeType);
    }

    public void a(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(MibiConstants.db, str);
        b(i, bundle);
        E();
    }

    public void e(Bundle bundle) {
        a(DoDeductFragment.class, bundle, 100, (String) null, TranslucentActivity.class);
    }

    public void d(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("processId", ((DeductTypeListContract.Presenter) H_()).m_());
        bundle.putString(MibiConstants.gA, str);
        a((Class<? extends StepFragment>) DeductQueryFragment.class, bundle, 200, (String) null);
    }
}
