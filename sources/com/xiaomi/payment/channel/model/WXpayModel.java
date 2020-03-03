package com.xiaomi.payment.channel.model;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.mibi.common.base.Model;
import com.mibi.common.data.MarketUtils;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import com.mibi.common.exception.rxjava.ServerErrorCodeExceptionHandler;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xiaomi.payment.channel.contract.PaytoolContract;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxWXpayTask;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WXpayModel extends Model implements IPaytoolModel {

    /* renamed from: a  reason: collision with root package name */
    public static Map<String, Integer> f12197a = new HashMap();
    private static final String b = "WXpayModel";
    private static final int d = 0;
    private static final int e = -1;
    private static final int f = -2;
    private static final long g = 1;
    private final String[] c = {"android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    /* access modifiers changed from: private */
    public String h;
    /* access modifiers changed from: private */
    public String i;
    /* access modifiers changed from: private */
    public long j;
    private RxWXpayTask k;
    /* access modifiers changed from: private */
    public IPaytoolTaskListener l;
    /* access modifiers changed from: private */
    public ExecutorService m;

    public void a(int i2, int i3, Bundle bundle) {
    }

    public WXpayModel(Session session) {
        super(session);
        if (this.k == null) {
            this.k = new RxWXpayTask(l_(), c());
        }
    }

    public String[] d() {
        return this.c;
    }

    public void a(IPaytoolTaskListener iPaytoolTaskListener) {
        this.l = iPaytoolTaskListener;
    }

    public void a(final SortedParameter sortedParameter, IPaytoolTaskListener iPaytoolTaskListener) {
        Assert.assertNotNull(sortedParameter);
        this.l = iPaytoolTaskListener;
        this.i = sortedParameter.f("processId");
        this.j = sortedParameter.d(MibiConstants.dd);
        this.l.a(new PaytoolContract.Function<Fragment>() {
            public void a(Fragment fragment) {
                if (WXpayModel.this.a((Context) fragment.getActivity())) {
                    WXpayModel.this.a(sortedParameter);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean a(Context context) {
        Assert.assertNotNull(context);
        if (MarketUtils.a(context, "com.tencent.mm")) {
            return true;
        }
        MarketUtils.a(context, context.getResources().getString(R.string.mibi_paytool_app_not_installed, new Object[]{context.getResources().getString(R.string.mibi_paytool_weixin)}), "com.tencent.mm", new MarketUtils.InstallPromtListener() {
            public void a() {
                WXpayModel.this.l.b();
            }

            public void b() {
                WXpayModel.this.l.b();
            }
        });
        return false;
    }

    /* access modifiers changed from: private */
    public void a(SortedParameter sortedParameter) {
        this.k.a(sortedParameter);
        WXpayTaskListener wXpayTaskListener = new WXpayTaskListener(l_());
        wXpayTaskListener.a().a((ExceptionDispatcher.ExceptionHandler) new ServerErrorCodeExceptionHandler(l_()) {
            /* access modifiers changed from: protected */
            public boolean e() {
                WXpayModel.this.l.a();
                return true;
            }
        });
        Observable.create(this.k).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(wXpayTaskListener);
    }

    private class WXpayTaskListener extends RxBaseErrorHandleTaskListener<RxWXpayTask.Result> {
        public WXpayTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            WXpayModel.this.l.a(i, str, th);
        }

        /* access modifiers changed from: protected */
        public void a(final RxWXpayTask.Result result) {
            WXpayModel.this.l.a(new PaytoolContract.Function<Fragment>() {
                public void a(Fragment fragment) {
                    String unused = WXpayModel.this.h = result.e;
                    ExecutorService unused2 = WXpayModel.this.m = Executors.newSingleThreadExecutor();
                    WXpayModel.this.m.execute(new WxRequestRunnable(WXpayModel.this, result, WXpayModel.this.j, WXpayModel.this.i));
                }
            });
        }
    }

    private static class WxRequestRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private IWXAPI f12203a;
        private RxWXpayTask.Result b;
        private WeakReference<WXpayModel> c;
        private long d;
        private String e;

        public WxRequestRunnable(WXpayModel wXpayModel, RxWXpayTask.Result result, long j, String str) {
            this.c = new WeakReference<>(wXpayModel);
            this.b = result;
            this.d = j;
            this.e = str;
        }

        public void run() {
            WXpayModel wXpayModel = (WXpayModel) this.c.get();
            if (wXpayModel != null) {
                this.f12203a = WXAPIFactory.createWXAPI(wXpayModel.l_().getApplicationContext(), (String) null);
                a(wXpayModel, this.b, this.d);
            }
        }

        private void a(WXpayModel wXpayModel, RxWXpayTask.Result result, long j) {
            PayReq payReq = new PayReq();
            payReq.appId = result.f12439a;
            payReq.partnerId = result.d;
            payReq.prepayId = result.e;
            payReq.packageValue = result.f;
            payReq.nonceStr = result.g;
            payReq.timeStamp = result.h;
            payReq.sign = result.i;
            this.f12203a.registerApp(result.f12439a);
            boolean sendReq = this.f12203a.sendReq(payReq);
            if (sendReq) {
                wXpayModel.c().m().a(this.e, MibiConstants.dd, (Object) Long.valueOf(j));
                wXpayModel.c().m().a(this.e, MibiConstants.fs, (Object) result.e);
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(wXpayModel.l_()).edit();
                edit.putString("appid", result.f12439a);
                edit.apply();
            }
            Log.d(WXpayModel.b, "WeiXin sendSuccess = " + sendReq);
        }
    }

    private void a(int i2) {
        c().m().a(this.i, MibiConstants.fs);
        switch (i2) {
            case -2:
                this.l.a(0, (String) null, (Throwable) null);
                return;
            case -1:
                String string = l_().getResources().getString(R.string.mibi_paytool_weixin);
                this.l.a(11, l_().getResources().getString(R.string.mibi_paytool_error_msp, new Object[]{string}), (Throwable) null);
                return;
            case 0:
                f12197a.clear();
                long f2 = c().m().f(this.i, MibiConstants.dd);
                if (f2 > 0) {
                    this.l.a(f2, 1 * f2);
                    return;
                }
                throw new IllegalArgumentException("denominationMibi should be greter than 0 when return from weixin pay");
            default:
                return;
        }
    }

    public void e() {
        if (TextUtils.isEmpty(this.i)) {
            Log.e(b, "mProcessId is null");
            return;
        }
        String a2 = c().m().a(this.i, MibiConstants.fs, "");
        if (!TextUtils.isEmpty(a2) && f12197a.containsKey(a2)) {
            int intValue = f12197a.get(a2).intValue();
            a(intValue);
            Log.i(b, "mPrepayId != null && contain mPrepayId, resultCode = " + intValue + "  mPrepayId" + this.h + " to handlePayResult()");
        }
    }

    public void a(Bundle bundle) {
        if (this.i != null) {
            bundle.putString(MibiConstants.fs, c().m().a(this.i, MibiConstants.fs, ""));
            bundle.putLong(MibiConstants.dd, this.j);
        }
    }

    public void b(Bundle bundle) {
        this.i = bundle.getString("processId", "");
        if (this.i != null) {
            c().m().a(this.i, MibiConstants.fs, (Object) bundle.getString(MibiConstants.fs, ""));
            bundle.putLong(MibiConstants.dd, this.j);
        }
    }

    public void f() {
        if (this.m != null && !this.m.isShutdown()) {
            this.m.shutdownNow();
            this.m = null;
        }
    }
}
