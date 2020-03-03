package com.xiaomi.payment.deduct.model;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.data.MarketUtils;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.tencent.mm.opensdk.modelbiz.OpenWebview;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.DeductManager;
import com.xiaomi.payment.deduct.contract.DoDeductContract;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxDoWxpayDeductTask;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WxpayDeductModel extends DeductModel {
    private IWXAPI b = WXAPIFactory.createWXAPI(l_().getApplicationContext(), (String) null);

    public WxpayDeductModel(Session session, String str) {
        super(session, str);
    }

    public void d() {
        if (!this.b.isWXAppInstalled()) {
            g();
        } else {
            h();
        }
    }

    private void g() {
        f().a((DoDeductContract.Function<Fragment>) new DoDeductContract.Function<Fragment>() {
            public void a(Fragment fragment) {
                Activity activity = fragment.getActivity();
                MarketUtils.a(activity, activity.getString(R.string.mibi_paytool_app_not_installed, new Object[]{activity.getResources().getString(R.string.mibi_paytool_weixin)}), "com.tencent.mm", new MarketUtils.InstallPromtListener() {
                    public void a() {
                        WxpayDeductModel.this.f().a(300, "go market for wxpay install");
                    }

                    public void b() {
                        WxpayDeductModel.this.f().a(202, "Wxpay install is canceled");
                    }
                });
            }
        });
    }

    private void h() {
        if (c().m().a(e(), DeductModel.f12262a, false)) {
            j();
        } else {
            i();
        }
    }

    private void i() {
        RxDoWxpayDeductTask rxDoWxpayDeductTask = new RxDoWxpayDeductTask(l_(), c());
        WxpayDeductTaskListener wxpayDeductTaskListener = new WxpayDeductTaskListener(l_());
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) e());
        rxDoWxpayDeductTask.a(sortedParameter);
        Observable.create(rxDoWxpayDeductTask).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(wxpayDeductTaskListener);
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            OpenWebview.Req req = new OpenWebview.Req();
            req.url = str;
            this.b = WXAPIFactory.createWXAPI(l_().getApplicationContext(), (String) null);
            this.b.registerApp(str2);
            this.b.sendReq(req);
            c().m().a(e(), DeductModel.f12262a, (Object) true);
            return;
        }
        j();
    }

    private void j() {
        Bundle bundle = new Bundle();
        bundle.putString("processId", e());
        bundle.putString(MibiConstants.gA, DeductManager.CHANNELS.WXPAY.getChannel());
        f().a(bundle);
        c().m().a(e(), DeductModel.f12262a);
    }

    private class WxpayDeductTaskListener extends RxBaseErrorHandleTaskListener<RxDoWxpayDeductTask.Result> {
        private WxpayDeductTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            WxpayDeductModel.this.f().a(201, str);
        }

        /* access modifiers changed from: protected */
        public void a(RxDoWxpayDeductTask.Result result) {
            WxpayDeductModel.this.a(result.mUrl, result.mAppId);
        }
    }
}
