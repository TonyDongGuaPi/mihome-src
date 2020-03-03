package com.xiaomi.payment.ui.fragment.recharge;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.mibi.common.base.BaseActivity;
import com.mibi.common.base.Presenter;
import com.mibi.common.data.MemoryStorage;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.data.Utils;
import com.mibi.common.decorator.AutoSave;
import com.mibi.common.ui.TranslucentActivity;
import com.xiaomi.payment.data.AnalyticsConstants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.Recharge;
import com.xiaomi.payment.recharge.RechargeManager;
import com.xiaomi.payment.recharge.RechargeMethod;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.task.rxjava.RxRechargeTypeTask;
import com.xiaomi.payment.task.rxjava.RxStartProcessTask;
import com.xiaomi.payment.ui.RechargeMethodEntryActivity;
import com.xiaomi.payment.ui.fragment.recharge.RechargeContract;
import com.xiaomi.payment.ui.model.RechargeTypeModel;
import com.xiaomi.payment.ui.model.StartProcessModel;
import com.xiaomi.payment.ui.model.UploadAnalyticsModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RechargePresenter extends Presenter<RechargeContract.View> implements AutoSave, RechargeContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12521a = "RechargePresenter";
    private static final int b = 2;
    private StartProcessModel c;
    private RechargeTypeModel d;
    private UploadAnalyticsModel e;
    @AutoSave.AutoSavable
    private String f;
    /* access modifiers changed from: private */
    @AutoSave.AutoSavable
    public long g;
    /* access modifiers changed from: private */
    @AutoSave.AutoSavable
    public String h;
    /* access modifiers changed from: private */
    @AutoSave.AutoSavable
    public boolean i;
    /* access modifiers changed from: private */
    public ArrayList<RechargeType> j = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<RechargeType> k = new ArrayList<>();
    private StartProcessModel.OnProcessStartListener l = new StartProcessModel.OnProcessStartListener() {
        public void a(int i, String str, Throwable th) {
            ((RechargeContract.View) RechargePresenter.this.l()).a(0, false);
            ((RechargeContract.View) RechargePresenter.this.l()).a(i, str, th);
        }

        public void a(RxStartProcessTask.Result result) {
            String unused = RechargePresenter.this.h = result.f12434a;
            RechargePresenter.this.n();
        }

        public void a(int i, String str) {
            ((RechargeContract.View) RechargePresenter.this.l()).a(i, str);
        }
    };
    private RechargeTypeModel.OnRechargeTypeListener m = new RechargeTypeModel.OnRechargeTypeListener() {
        public void a(int i, String str, Throwable th) {
            ((RechargeContract.View) RechargePresenter.this.l()).a(0, false);
            ((RechargeContract.View) RechargePresenter.this.l()).a(i, str, th);
        }

        public void a(RxRechargeTypeTask.Result result) {
            ((RechargeContract.View) RechargePresenter.this.l()).a(0, false);
            RechargePresenter.this.o();
            RechargePresenter.this.a(result);
            if (result.mShowAll || !RechargePresenter.this.i || result.mRechargeTypes.size() <= 3 || RechargePresenter.this.g > 0) {
                ((RechargeContract.View) RechargePresenter.this.l()).a(RechargePresenter.this.h, (ArrayList<RechargeType>) RechargePresenter.this.j);
            } else {
                ((RechargeContract.View) RechargePresenter.this.l()).a(RechargePresenter.this.h, (ArrayList<RechargeType>) RechargePresenter.this.k);
            }
        }
    };

    public RechargePresenter() {
        super(RechargeContract.View.class);
    }

    /* access modifiers changed from: protected */
    public void b(Bundle bundle) {
        this.c = new StartProcessModel(f());
        this.d = new RechargeTypeModel(f());
        this.g = n_().getLong("rechargeAmount", 0);
        ((RechargeContract.View) l()).a(0, true);
    }

    public void g() {
        if (this.g > 0) {
            this.f = RechargeManager.b(e());
        } else {
            this.f = RechargeManager.a(e());
        }
        this.c.a(this.l);
    }

    public void h() {
        ((RechargeContract.View) l()).a(this.h, this.j);
    }

    public void a(RechargeType rechargeType) {
        boolean z = false;
        RechargeMethod rechargeMethod = rechargeType.mRechargeMethods.get(0);
        Recharge a2 = RechargeManager.a().a(rechargeMethod.mChannel);
        Bundle bundle = new Bundle();
        bundle.putString("processId", this.h);
        bundle.putSerializable(MibiConstants.cE, rechargeMethod);
        if (this.g > 0) {
            z = true;
        }
        bundle.putSerializable(MibiConstants.cR, a2.a(z));
        if (!a2.c() || this.g <= 0) {
            bundle.putString(MibiConstants.fQ, rechargeType.mCurrencyUnit);
            ((RechargeContract.View) l()).a(bundle, (Class<? extends BaseActivity>) RechargeMethodEntryActivity.class);
            return;
        }
        bundle.putLong(MibiConstants.dd, this.g);
        ((RechargeContract.View) l()).a(bundle, (Class<? extends BaseActivity>) TranslucentActivity.class);
    }

    public void b(RechargeType rechargeType) {
        if (this.e == null) {
            this.e = new UploadAnalyticsModel(f());
        }
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("eventType", (Object) MibiConstants.cO);
        sortedParameter.a(MibiConstants.cL, (Object) rechargeType.mType);
        this.e.a(sortedParameter);
        String str = (String) f().m().c("miref", (String) null);
        if (!TextUtils.isEmpty(str)) {
            HashMap hashMap = new HashMap();
            hashMap.put("miref", str);
            hashMap.put(AnalyticsConstants.bH, "recharge_choosed_" + rechargeType.mType);
            MistatisticUtils.a(AnalyticsConstants.bE, AnalyticsConstants.bF, (Map<String, String>) hashMap);
        }
    }

    /* access modifiers changed from: private */
    public void n() {
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) this.h);
        sortedParameter.a(MibiConstants.dq, (Object) Long.valueOf(this.g));
        sortedParameter.a(MibiConstants.gp, (Object) this.f);
        if (TextUtils.isEmpty(this.h)) {
            Log.e(f12521a, "mProcessId is null");
        }
        this.d.a(sortedParameter, this.m);
    }

    /* access modifiers changed from: private */
    public void a(RxRechargeTypeTask.Result result) {
        this.k.clear();
        this.j.clear();
        Iterator<RechargeType> it = result.mRechargeTypes.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            RechargeType next = it.next();
            if (next.mFavorite && i2 < 2) {
                i2++;
                this.k.add(next);
            }
            this.j.add(next);
        }
        if (!Utils.b() && !this.k.isEmpty()) {
            RechargeType rechargeType = new RechargeType();
            rechargeType.mFavorite = true;
            rechargeType.mTitle = e().getResources().getString(R.string.mibi_recharge_grid_item_more);
            rechargeType.mType = "more";
            rechargeType.mLocalIconRes = R.drawable.mibi_ic_dialog_more_recharge;
            this.k.add(rechargeType);
            this.i = true;
        }
    }

    /* access modifiers changed from: private */
    public void o() {
        if (!TextUtils.isEmpty(this.h)) {
            MemoryStorage m2 = f().m();
            m2.a(this.h, MibiConstants.cZ, (Object) false);
            if (this.g > 0) {
                m2.a(this.h, "price", (Object) Long.valueOf(this.g));
                return;
            }
            return;
        }
        throw new IllegalArgumentException("processId should not be null after recharge started");
    }

    public void a(int i2, int i3, Bundle bundle) {
        Log.d(f12521a, "RechargePresenter handleResult requestCode =" + i2 + ",  resultCode =" + i3);
        super.a(i2, i3, bundle);
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        if (1000 == i3) {
            bundle2.putInt("errcode", -1);
        } else {
            bundle2.putInt("errcode", 0);
        }
        ((RechargeContract.View) l()).a_(i3, bundle2);
        if (i3 == 1000 || i3 == 1002) {
            ((RechargeContract.View) l()).a();
        }
    }
}
