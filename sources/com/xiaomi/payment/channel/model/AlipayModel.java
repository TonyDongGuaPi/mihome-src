package com.xiaomi.payment.channel.model;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.app.PayTask;
import com.mibi.common.base.Model;
import com.mibi.common.data.MemoryStorage;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import com.mibi.common.exception.rxjava.ServerErrorCodeExceptionHandler;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.channel.contract.PaytoolContract;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxAlipayTask;
import java.lang.ref.WeakReference;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AlipayModel extends Model implements IPaytoolModel {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12176a = "AlipayModel";
    private static final long c = 1;
    private final String[] b = {"android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public long e;
    private RxAlipayTask f;
    /* access modifiers changed from: private */
    public IPaytoolTaskListener g;

    public void a(int i, int i2, Bundle bundle) {
    }

    public AlipayModel(Session session) {
        super(session);
        if (this.f == null) {
            this.f = new RxAlipayTask(l_(), c());
        }
    }

    public String[] d() {
        return this.b;
    }

    public void a(IPaytoolTaskListener iPaytoolTaskListener) {
        this.g = iPaytoolTaskListener;
    }

    public void a(SortedParameter sortedParameter, IPaytoolTaskListener iPaytoolTaskListener) {
        Assert.assertNotNull(sortedParameter);
        Assert.assertNotNull(iPaytoolTaskListener);
        this.d = sortedParameter.f("processId");
        this.g = iPaytoolTaskListener;
        this.e = sortedParameter.d(MibiConstants.dd);
        this.f.a(sortedParameter);
        AlipayTaskListener alipayTaskListener = new AlipayTaskListener(l_());
        alipayTaskListener.a().a((ExceptionDispatcher.ExceptionHandler) new ServerErrorCodeExceptionHandler(l_()) {
            /* access modifiers changed from: protected */
            public boolean e() {
                AlipayModel.this.g.a();
                return true;
            }
        });
        Observable.create(this.f).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(alipayTaskListener);
    }

    private class AlipayTaskListener extends RxBaseErrorHandleTaskListener<RxAlipayTask.Result> {
        private AlipayTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            AlipayModel.this.g.a(i, str, th);
        }

        /* access modifiers changed from: protected */
        public void a(RxAlipayTask.Result result) {
            if (!AlipayModel.this.a(result)) {
                AlipayModel.this.g.a(6, AlipayModel.this.l_().getResources().getString(R.string.mibi_error_server_summary), (Throwable) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean a(RxAlipayTask.Result result) {
        if (TextUtils.isEmpty(result.f12417a)) {
            Log.e(f12176a, "result is null");
            return false;
        }
        final String str = result.f12417a;
        this.g.a(new PaytoolContract.Function<Fragment>() {
            public void a(Fragment fragment) {
                Assert.assertNotNull(fragment);
                new Thread(new AlipayRunnable(fragment.getActivity(), AlipayModel.this.c().m(), AlipayModel.this.e, AlipayModel.this.e * 1, AlipayModel.this.d, str, AlipayModel.this.g)).start();
            }
        });
        return true;
    }

    private static class AlipayRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private long f12179a;
        private long b;
        private MemoryStorage c;
        private String d;
        private String e;
        private WeakReference<Activity> f;
        private WeakReference<IPaytoolTaskListener> g;

        private AlipayRunnable(Activity activity, MemoryStorage memoryStorage, long j, long j2, String str, String str2, IPaytoolTaskListener iPaytoolTaskListener) {
            this.f12179a = j;
            this.b = j2;
            this.d = str2;
            this.f = new WeakReference<>(activity);
            this.e = str;
            this.c = memoryStorage;
            this.g = new WeakReference<>(iPaytoolTaskListener);
        }

        public void run() {
            Activity activity = (Activity) this.f.get();
            if (activity != null) {
                String string = activity.getResources().getString(R.string.mibi_paytool_error_msp, new Object[]{activity.getResources().getString(R.string.mibi_paytool_alipay)});
                PayTask payTask = new PayTask(activity);
                Log.d(AlipayModel.f12176a, "start calling alipay sdk");
                String pay = payTask.pay(this.d, true);
                Log.d(AlipayModel.f12176a, "finish calling alipay sdk");
                this.c.a(this.e, MibiConstants.dd, (Object) Long.valueOf(this.f12179a));
                this.c.a(this.e, MibiConstants.f12224de, (Object) Long.valueOf(this.f12179a * 1));
                AlipaySchedule.a(this.e, pay, this.f12179a, this.b, string);
                IPaytoolTaskListener iPaytoolTaskListener = (IPaytoolTaskListener) this.g.get();
                if (!activity.isDestroyed() && iPaytoolTaskListener != null) {
                    AlipaySchedule.a(this.e, iPaytoolTaskListener);
                }
            }
        }
    }

    public void e() {
        if (TextUtils.isEmpty(this.d)) {
            Log.e(f12176a, "mProcessId is null");
        } else if (this.g == null) {
            Log.e(f12176a, "mDoAlipayListener is null");
        } else {
            AlipaySchedule.a(this.d, this.g);
        }
    }

    public void a(Bundle bundle) {
        if (this.d != null) {
            bundle.putLong(MibiConstants.dd, this.e);
        }
    }

    public void b(Bundle bundle) {
        this.d = bundle.getString("processId", "");
        if (this.d != null) {
            this.e = bundle.getLong(MibiConstants.dd, 0);
            c().m().a(this.d, MibiConstants.dd, (Object) Long.valueOf(this.e));
            c().m().a(this.d, MibiConstants.f12224de, (Object) Long.valueOf(this.e * 1));
        }
    }

    public void f() {
        AlipaySchedule.a();
    }
}
