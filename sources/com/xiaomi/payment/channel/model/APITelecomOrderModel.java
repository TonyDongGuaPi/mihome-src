package com.xiaomi.payment.channel.model;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxSendSMSTask;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class APITelecomOrderModel extends Model implements IMSGOrderModel {

    /* renamed from: a  reason: collision with root package name */
    public static final int f12173a = 0;
    private static final String b = "APITelecomOrderModel";
    private static final String c = "com.xiaomi.payment.telecom";
    private String d;
    private String e;
    private RxSendSMSTask<RxSendSMSTask.Result> f;
    /* access modifiers changed from: private */
    public IMSGOrderListener g;

    public void a(int i, int i2, Bundle bundle) {
    }

    public APITelecomOrderModel(Session session) {
        super(session);
    }

    public void a(SortedParameter sortedParameter, Activity activity, IMSGOrderListener iMSGOrderListener) {
        this.d = sortedParameter.f(MibiConstants.fo);
        this.e = sortedParameter.f(MibiConstants.fp);
        this.g = iMSGOrderListener;
        if (this.f == null) {
            this.f = new RxSendSMSTask(l_(), this.e, this.d, c, RxSendSMSTask.Result.class);
        }
        Observable.create(this.f).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
            public void call() {
                APITelecomOrderModel.this.g.a(0, true);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new SendMSGTaskListener(l_()));
    }

    private class SendMSGTaskListener extends RxBaseErrorHandleTaskListener<RxSendSMSTask.Result> {
        public SendMSGTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            APITelecomOrderModel.this.g.a(0, false);
            APITelecomOrderModel.this.g.a(i, str, th);
        }

        /* access modifiers changed from: protected */
        public void a(RxSendSMSTask.Result result) {
            APITelecomOrderModel.this.g.a(0, false);
            APITelecomOrderModel.this.a(result);
        }
    }

    /* access modifiers changed from: private */
    public void a(RxSendSMSTask.Result result) {
        switch (result.f12433a) {
            case -1:
                this.g.a(new Bundle());
                return;
            case 101:
                this.g.a(11, l_().getResources().getString(R.string.mibi_msg_send_no_sim_error), (Throwable) null);
                return;
            case 102:
                this.g.a(11, l_().getResources().getString(R.string.mibi_msg_send_no_net_error), (Throwable) null);
                return;
            case 104:
            case 105:
                this.g.a(2, l_().getResources().getString(R.string.mibi_msg_send_parameter_error), (Throwable) null);
                return;
            default:
                this.g.a(11, l_().getResources().getString(R.string.mibi_msg_send_send_fail_error), (Throwable) null);
                return;
        }
    }
}
