package com.xiaomi.payment.deduct.model;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.mibi.common.data.MarketUtils;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xiaomi.payment.channel.model.ISignDeductModel;
import com.xiaomi.payment.channel.model.WXpayModel;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.DeductManager;
import com.xiaomi.payment.deduct.contract.DoSignDeductContract;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxDoWxpaySignDeductTask;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WxpaySignDeductModel extends SignDeductModel implements ISignDeductModel {
    private static final String b = "WxpaySignDeductModel";
    private static final int c = 0;
    private static final int d = -1;
    private static final int e = -2;
    private IWXAPI f = WXAPIFactory.createWXAPI(l_().getApplicationContext(), (String) null);
    private ExecutorService g;

    public WxpaySignDeductModel(Session session, String str) {
        super(session, str);
    }

    public void d() {
        if (!this.f.isWXAppInstalled()) {
            h();
        } else {
            i();
        }
    }

    private void h() {
        f().a((DoSignDeductContract.Function<Fragment>) new DoSignDeductContract.Function<Fragment>() {
            public void a(Fragment fragment) {
                Activity activity = fragment.getActivity();
                MarketUtils.a(activity, activity.getString(R.string.mibi_paytool_app_not_installed, new Object[]{activity.getResources().getString(R.string.mibi_paytool_weixin)}), "com.tencent.mm", new MarketUtils.InstallPromtListener() {
                    public void a() {
                        WxpaySignDeductModel.this.f().a(300, "go market for wxpay install");
                    }

                    public void b() {
                        WxpaySignDeductModel.this.f().a(205, "Wxpay install is canceled");
                    }
                });
            }
        });
    }

    private void i() {
        if (c().m().a(e(), SignDeductModel.f12268a, false)) {
            g();
        } else {
            j();
        }
    }

    private void j() {
        RxDoWxpaySignDeductTask rxDoWxpaySignDeductTask = new RxDoWxpaySignDeductTask(l_(), c());
        WxpaySignDeductTaskListener wxpaySignDeductTaskListener = new WxpaySignDeductTaskListener(l_());
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) e());
        rxDoWxpaySignDeductTask.a(sortedParameter);
        Observable.create(rxDoWxpaySignDeductTask).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(wxpaySignDeductTaskListener);
    }

    /* access modifiers changed from: private */
    public void a(RxDoWxpaySignDeductTask.Result result) {
        if (this.f.isWXAppInstalled()) {
            this.g = Executors.newSingleThreadExecutor();
            this.g.execute(new WxRequestRunnable(this, result));
            return;
        }
        h();
    }

    public void g() {
        Bundle bundle = new Bundle();
        bundle.putString("processId", e());
        bundle.putString(MibiConstants.gA, DeductManager.CHANNELS.WXPAY.getChannel());
        f().a(bundle);
        c().m().a(e(), SignDeductModel.f12268a);
    }

    public void k_() {
        if (TextUtils.isEmpty(e())) {
            Log.e(b, "mProcessId is null");
            return;
        }
        String a2 = c().m().a(e(), MibiConstants.fs, "");
        if (!TextUtils.isEmpty(a2) && WXpayModel.f12197a.containsKey(a2)) {
            a(WXpayModel.f12197a.get(a2).intValue());
        }
    }

    public void b() {
        if (this.g != null && !this.g.isShutdown()) {
            this.g.shutdownNow();
            this.g = null;
        }
    }

    private void a(int i) {
        c().m().a(e(), MibiConstants.fs);
        switch (i) {
            case -2:
                f().a(205, (String) null);
                return;
            case -1:
                String string = l_().getResources().getString(R.string.mibi_paytool_weixin);
                String string2 = l_().getResources().getString(R.string.mibi_paytool_error_msp, new Object[]{string});
                f().a(204, string2 + " ; code : " + i);
                return;
            case 0:
                WXpayModel.f12197a.clear();
                g();
                return;
            default:
                f().a(204, "WXPay failed errorCode : " + i);
                return;
        }
    }

    private class WxpaySignDeductTaskListener extends RxBaseErrorHandleTaskListener<RxDoWxpaySignDeductTask.Result> {
        private WxpaySignDeductTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            WxpaySignDeductModel.this.f().a(204, str);
        }

        /* access modifiers changed from: protected */
        public void a(RxDoWxpaySignDeductTask.Result result) {
            WxpaySignDeductModel.this.a(result);
        }
    }

    private static class WxRequestRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private IWXAPI f12274a;
        private RxDoWxpaySignDeductTask.Result b;
        private WeakReference<WxpaySignDeductModel> c;

        public WxRequestRunnable(WxpaySignDeductModel wxpaySignDeductModel, RxDoWxpaySignDeductTask.Result result) {
            this.c = new WeakReference<>(wxpaySignDeductModel);
            this.b = result;
        }

        public void run() {
            WxpaySignDeductModel wxpaySignDeductModel = (WxpaySignDeductModel) this.c.get();
            if (wxpaySignDeductModel != null) {
                this.f12274a = WXAPIFactory.createWXAPI(wxpaySignDeductModel.l_().getApplicationContext(), (String) null);
                a(wxpaySignDeductModel, this.b);
            }
        }

        private void a(WxpaySignDeductModel wxpaySignDeductModel, RxDoWxpaySignDeductTask.Result result) {
            PayReq payReq = new PayReq();
            payReq.appId = result.mAppId;
            payReq.partnerId = result.mPartnerId;
            payReq.prepayId = result.mPrepayId;
            payReq.packageValue = result.mPackageValue;
            payReq.nonceStr = result.mNonceStr;
            payReq.timeStamp = result.mTimeStamp;
            payReq.sign = result.mSign;
            this.f12274a.registerApp(result.mAppId);
            boolean sendReq = this.f12274a.sendReq(payReq);
            wxpaySignDeductModel.c().m().a(wxpaySignDeductModel.e(), SignDeductModel.f12268a, (Object) true);
            Log.d(WxpaySignDeductModel.b, "WeiXin sendSuccess = " + sendReq);
            if (sendReq) {
                wxpaySignDeductModel.c().m().a(wxpaySignDeductModel.e(), MibiConstants.fs, (Object) result.mPrepayId);
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(wxpaySignDeductModel.l_()).edit();
                edit.putString("appid", result.mAppId);
                edit.apply();
            }
        }
    }
}
