package com.xiaomi.payment.deduct.model;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.mibi.common.data.MarketUtils;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.DeductManager;
import com.xiaomi.payment.deduct.contract.DoDeductContract;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxDoAlipayDeductTask;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AlipayDeductModel extends DeductModel {
    public AlipayDeductModel(Session session, String str) {
        super(session, str);
    }

    public void d() {
        if (!MarketUtils.a(l_(), "com.eg.android.AlipayGphone")) {
            g();
        } else {
            h();
        }
    }

    private void g() {
        f().a((DoDeductContract.Function<Fragment>) new DoDeductContract.Function<Fragment>() {
            public void a(Fragment fragment) {
                Activity activity = fragment.getActivity();
                MarketUtils.a(activity, activity.getString(R.string.mibi_paytool_app_not_installed, new Object[]{activity.getResources().getString(R.string.mibi_paytool_alipay)}), "com.eg.android.AlipayGphone", new MarketUtils.InstallPromtListener() {
                    public void a() {
                        AlipayDeductModel.this.f().a(300, "go market for alipay install");
                    }

                    public void b() {
                        AlipayDeductModel.this.f().a(202, "Alipay install is canceled");
                    }
                });
            }
        });
    }

    private void h() {
        if (c().m().a(e(), DeductModel.f12262a, false)) {
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
        c().m().a(e(), DeductModel.f12262a);
    }

    private void j() {
        RxDoAlipayDeductTask rxDoAlipayDeductTask = new RxDoAlipayDeductTask(l_(), c());
        AlipayDeductTaskListener alipayDeductTaskListener = new AlipayDeductTaskListener(l_());
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) e());
        rxDoAlipayDeductTask.a(sortedParameter);
        Observable.create(rxDoAlipayDeductTask).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(alipayDeductTaskListener);
    }

    private class AlipayDeductTaskListener extends RxBaseErrorHandleTaskListener<RxDoAlipayDeductTask.Result> {
        private AlipayDeductTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            AlipayDeductModel.this.f().a(201, str);
        }

        /* access modifiers changed from: protected */
        public void a(RxDoAlipayDeductTask.Result result) {
            AlipayDeductModel.this.b(result.mUrl);
        }
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            l_().startActivity(intent);
            c().m().a(e(), DeductModel.f12262a, (Object) true);
            return;
        }
        i();
    }
}
