package com.xiaomi.payment.channel.presenter;

import android.os.Bundle;
import com.mibi.common.base.Presenter;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.recharge.PrepaidCardRechargeMethod;
import com.xiaomi.payment.task.rxjava.RxPrepaidTask;
import com.xiaomi.payment.ui.contract.PrepaidCardChannelContract;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class PrepaidCardChannelPresenter extends Presenter<PrepaidCardChannelContract.View> implements PrepaidCardChannelContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    public static final int f12213a = 1;
    private static final String b = "VoucherChannelPresenter";
    private Long c;
    /* access modifiers changed from: private */
    public Long d;
    private RxPrepaidTask e;
    private String f;
    private String g;
    private String h;

    public PrepaidCardChannelPresenter() {
        super(PrepaidCardChannelContract.View.class);
    }

    /* access modifiers changed from: protected */
    public void b(Bundle bundle) {
        super.b(bundle);
        Bundle n_ = n_();
        this.f = n_.getString("processId");
        this.g = ((PrepaidCardRechargeMethod) n_().getSerializable(MibiConstants.cE)).mChannel;
        this.h = n_.getString("carrier");
        this.d = Long.valueOf(n_.getLong(MibiConstants.dd, 0));
        this.c = Long.valueOf(n_.getLong(MibiConstants.f12224de, 0));
    }

    public void a(String str, String str2) {
        Assert.assertNotNull(str);
        Assert.assertNotNull(str2);
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) this.f);
        sortedParameter.a("channel", (Object) this.g);
        sortedParameter.a("carrier", (Object) this.h);
        sortedParameter.a(MibiConstants.dD, (Object) str);
        sortedParameter.a(MibiConstants.dE, (Object) str2);
        sortedParameter.a(MibiConstants.dq, (Object) this.c);
        this.e = new RxPrepaidTask(e(), f());
        this.e.a(sortedParameter);
        Observable.create(this.e).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
            public void call() {
                ((PrepaidCardChannelContract.View) PrepaidCardChannelPresenter.this.l()).a(1, true);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new RxBaseErrorHandleTaskListener<RxPrepaidTask.Result>(e()) {
            /* access modifiers changed from: protected */
            public void a(RxPrepaidTask.Result result) {
                ((PrepaidCardChannelContract.View) PrepaidCardChannelPresenter.this.l()).a(PrepaidCardChannelPresenter.this.d.longValue());
            }

            /* access modifiers changed from: protected */
            public void a(int i, String str, Throwable th) {
                ((PrepaidCardChannelContract.View) PrepaidCardChannelPresenter.this.l()).a(i, str, th);
            }
        });
    }
}
