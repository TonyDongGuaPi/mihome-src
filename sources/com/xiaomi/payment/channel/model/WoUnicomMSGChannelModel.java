package com.xiaomi.payment.channel.model;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxWoUnicomMSGPayTask;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class WoUnicomMSGChannelModel extends Model implements IGetMSGInfoModel {

    /* renamed from: a  reason: collision with root package name */
    public static final int f12204a = 0;
    private final String[] b = {"android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private RxWoUnicomMSGPayTask c;
    /* access modifiers changed from: private */
    public IGetMSGInfoListener d;

    public WoUnicomMSGChannelModel(Session session) {
        super(session);
        if (this.c == null) {
            this.c = new RxWoUnicomMSGPayTask(l_(), c());
        }
    }

    public String[] d() {
        return this.b;
    }

    public void a(SortedParameter sortedParameter, Activity activity, IGetMSGInfoListener iGetMSGInfoListener) {
        Assert.assertNotNull(sortedParameter);
        Assert.assertNotNull(activity);
        Assert.assertNotNull(iGetMSGInfoListener);
        this.d = iGetMSGInfoListener;
        this.c.a(sortedParameter);
        Observable.create(this.c).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
            public void call() {
                WoUnicomMSGChannelModel.this.d.a(0, true);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new WoUnicomMSGTaskListener(l_()));
    }

    private class WoUnicomMSGTaskListener extends RxBaseErrorHandleTaskListener<RxWoUnicomMSGPayTask.Result> {
        public WoUnicomMSGTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            WoUnicomMSGChannelModel.this.d.a(0, false);
            WoUnicomMSGChannelModel.this.d.a(i, str, th);
        }

        /* access modifiers changed from: protected */
        public void a(RxWoUnicomMSGPayTask.Result result) {
            WoUnicomMSGChannelModel.this.d.a(0, false);
            Bundle bundle = new Bundle();
            bundle.putCharSequence(MibiConstants.dr, result.c);
            bundle.putParcelable(MibiConstants.dj, result);
            bundle.putBoolean(MibiConstants.hj, true);
            WoUnicomMSGChannelModel.this.d.a(bundle);
        }
    }
}
