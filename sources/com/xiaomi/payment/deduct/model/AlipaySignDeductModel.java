package com.xiaomi.payment.deduct.model;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.mibi.common.data.MarketUtils;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.DeductManager;
import com.xiaomi.payment.deduct.contract.DoSignDeductContract;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxDoAlipaySignDeductTask;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AlipaySignDeductModel extends SignDeductModel {
    private static final String b = "AlipaySignDeductModel";

    public AlipaySignDeductModel(Session session, String str) {
        super(session, str);
    }

    public void d() {
        Log.d(b, "startDeduct called");
        if (!MarketUtils.a(l_(), "com.eg.android.AlipayGphone")) {
            g();
        } else {
            h();
        }
    }

    private void g() {
        f().a((DoSignDeductContract.Function<Fragment>) new DoSignDeductContract.Function<Fragment>() {
            public void a(Fragment fragment) {
                Activity activity = fragment.getActivity();
                MarketUtils.a(activity, activity.getString(R.string.mibi_paytool_app_not_installed, new Object[]{activity.getResources().getString(R.string.mibi_paytool_alipay)}), "com.eg.android.AlipayGphone", new MarketUtils.InstallPromtListener() {
                    public void a() {
                        AlipaySignDeductModel.this.f().a(300, "go market for alipay install");
                    }

                    public void b() {
                        AlipaySignDeductModel.this.f().a(202, "Alipay install is canceled");
                    }
                });
            }
        });
    }

    private void h() {
        if (c().m().a(e(), SignDeductModel.f12268a, false)) {
            i();
        } else {
            j();
        }
    }

    private void i() {
        Bundle bundle = new Bundle();
        bundle.putString("processId", e());
        bundle.putString(MibiConstants.gA, DeductManager.CHANNELS.ALIPAY.getChannel());
        f().a(bundle);
        c().m().a(e(), SignDeductModel.f12268a);
    }

    private void j() {
        RxDoAlipaySignDeductTask rxDoAlipaySignDeductTask = new RxDoAlipaySignDeductTask(l_(), c());
        AlipaySignDeductTaskListener alipaySignDeductTaskListener = new AlipaySignDeductTaskListener(l_());
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) e());
        rxDoAlipaySignDeductTask.a(sortedParameter);
        Observable.create(rxDoAlipaySignDeductTask).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(alipaySignDeductTaskListener);
    }

    private class AlipaySignDeductTaskListener extends RxBaseErrorHandleTaskListener<RxDoAlipaySignDeductTask.Result> {
        private AlipaySignDeductTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            AlipaySignDeductModel.this.f().a(201, str);
        }

        /* access modifiers changed from: protected */
        public void a(RxDoAlipaySignDeductTask.Result result) {
            AlipaySignDeductModel.this.a(result);
        }
    }

    /* access modifiers changed from: private */
    public void a(RxDoAlipaySignDeductTask.Result result) {
        if (!TextUtils.isEmpty(result.mUrl)) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(result.mUrl));
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            l_().startActivity(intent);
            c().m().a(e(), SignDeductModel.f12268a, (Object) true);
            return;
        }
        i();
    }
}
