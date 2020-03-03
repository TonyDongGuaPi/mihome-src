package com.xiaomi.payment.channel.presenter;

import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.base.Presenter;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxVoucherTask;
import com.xiaomi.payment.ui.contract.VoucherContract;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class VoucherChannelPresenter extends Presenter<VoucherContract.View> implements VoucherContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    public static final int f12216a = 1;
    private static final String b = "VoucherChannelPresenter";
    private RxVoucherTask c;
    private String d;

    public VoucherChannelPresenter() {
        super(VoucherContract.View.class);
    }

    /* access modifiers changed from: protected */
    public void b(Bundle bundle) {
        super.b(bundle);
        this.d = n_().getString("processId");
    }

    public void a(String str) {
        Assert.assertNotNull(str);
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) this.d);
        sortedParameter.a(MibiConstants.ek, (Object) str);
        this.c = new RxVoucherTask(e(), f());
        this.c.a(sortedParameter);
        Observable.create(this.c).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
            public void call() {
                ((VoucherContract.View) VoucherChannelPresenter.this.l()).a(1, true);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new RxBaseErrorHandleTaskListener<RxVoucherTask.Result>(e()) {
            /* access modifiers changed from: protected */
            public void a(RxVoucherTask.Result result) {
                if (!TextUtils.isEmpty(result.b)) {
                    ((VoucherContract.View) VoucherChannelPresenter.this.l()).a(result.c);
                }
            }

            /* access modifiers changed from: protected */
            public void a(int i, String str, Throwable th) {
                ((VoucherContract.View) VoucherChannelPresenter.this.l()).a(i, str, th);
            }
        });
    }
}
