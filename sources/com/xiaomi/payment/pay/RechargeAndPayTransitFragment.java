package com.xiaomi.payment.pay;

import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.base.BaseActivity;
import com.mibi.common.base.TaskListener;
import com.mibi.common.data.MemoryStorage;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.ui.TranslucentActivity;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.recharge.Recharge;
import com.xiaomi.payment.recharge.RechargeManager;
import com.xiaomi.payment.recharge.RechargeMethod;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.task.UploadAnalyticsTask;
import com.xiaomi.payment.ui.RechargeMethodEntryActivity;

public class RechargeAndPayTransitFragment extends BaseProcessFragment {
    private RechargeType v;
    private long w;

    public void c(Bundle bundle) {
        super.c(bundle);
        d(false);
        if (bundle == null) {
            N();
        }
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.v = (RechargeType) bundle.getSerializable(MibiConstants.cG);
    }

    /* access modifiers changed from: protected */
    public void a(MemoryStorage memoryStorage) {
        super.a(memoryStorage);
        if (!TextUtils.isEmpty(this.t)) {
            this.w = memoryStorage.f(this.t, "price");
        }
    }

    private void N() {
        RechargeType rechargeType = this.v;
        RechargeMethod rechargeMethod = rechargeType.mRechargeMethods.get(0);
        Recharge a2 = RechargeManager.a().a(rechargeMethod.mChannel);
        Bundle arguments = getArguments();
        arguments.putString("processId", this.t);
        arguments.putSerializable(MibiConstants.cE, rechargeMethod);
        arguments.putSerializable(MibiConstants.cR, a2.a(true));
        if (a2.c()) {
            arguments.putLong(MibiConstants.dd, this.w);
            a(arguments, (String) null, (Class<? extends BaseActivity>) TranslucentActivity.class);
        } else {
            arguments.putString(MibiConstants.fQ, rechargeType.mCurrencyUnit);
            a(arguments, (String) null, (Class<? extends BaseActivity>) RechargeMethodEntryActivity.class);
        }
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("eventType", (Object) MibiConstants.cQ);
        sortedParameter.a("payType", (Object) this.v.mType);
        UploadAnalyticsTask uploadAnalyticsTask = new UploadAnalyticsTask(getActivity(), this.b);
        uploadAnalyticsTask.c(sortedParameter);
        q().b(uploadAnalyticsTask, (TaskListener) null);
    }

    /* access modifiers changed from: protected */
    public void a(Bundle bundle, String str, Class<? extends BaseActivity> cls) {
        Class cls2 = (Class) bundle.getSerializable(MibiConstants.cR);
        if (cls2 != null) {
            a(cls2, bundle, 0, str, cls);
        }
    }

    public void a(int i, int i2, Bundle bundle) {
        super.a(i, i2, bundle);
        if (i == 0 || i2 == 0 || i2 == 1005) {
            b(i2, bundle);
        }
        E();
    }
}
