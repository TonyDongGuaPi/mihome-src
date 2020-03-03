package com.xiaomi.payment.channel.model;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxAPITelecomMSGPayTask;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class APITelecomMSGChannelModel extends Model implements IGetMSGInfoModel {

    /* renamed from: a  reason: collision with root package name */
    public static final int f12170a = 0;
    private static final String b = "APITelecomMSGChannelModel";
    private final String[] c = {"android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private RxAPITelecomMSGPayTask d;
    /* access modifiers changed from: private */
    public IGetMSGInfoListener e;

    public APITelecomMSGChannelModel(Session session) {
        super(session);
        if (this.d == null) {
            this.d = new RxAPITelecomMSGPayTask(l_(), c());
        }
    }

    public String[] d() {
        return this.c;
    }

    public void a(SortedParameter sortedParameter, Activity activity, IGetMSGInfoListener iGetMSGInfoListener) {
        Assert.assertNotNull(sortedParameter);
        Assert.assertNotNull(iGetMSGInfoListener);
        this.e = iGetMSGInfoListener;
        this.d.a(sortedParameter);
        Observable.create(this.d).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
            public void call() {
                APITelecomMSGChannelModel.this.e.a(0, true);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new APITelecomMSGTaskListener(l_()));
    }

    private class APITelecomMSGTaskListener extends RxBaseErrorHandleTaskListener<RxAPITelecomMSGPayTask.Result> {
        public APITelecomMSGTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            APITelecomMSGChannelModel.this.e.a(0, false);
            APITelecomMSGChannelModel.this.e.a(i, str, th);
        }

        /* access modifiers changed from: protected */
        public void a(RxAPITelecomMSGPayTask.Result result) {
            APITelecomMSGChannelModel.this.e.a(0, false);
            Bundle bundle = new Bundle();
            bundle.putCharSequence(MibiConstants.dr, result.c);
            bundle.putCharSequence(MibiConstants.fo, result.f12416a);
            bundle.putCharSequence(MibiConstants.fp, result.b);
            bundle.putBoolean(MibiConstants.hj, true);
            APITelecomMSGChannelModel.this.e.a(bundle);
        }
    }
}
