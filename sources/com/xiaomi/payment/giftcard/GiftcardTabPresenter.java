package com.xiaomi.payment.giftcard;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.base.Presenter;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import com.mibi.common.exception.rxjava.NetworkExceptionHandler;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.giftcard.GiftcardContract;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxGiftcardTask;
import java.util.ArrayList;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

class GiftcardTabPresenter extends Presenter<GiftcardContract.GiftcardTabView> implements GiftcardContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    public static final int f12305a = 0;
    private RxGiftcardTask b;
    private int c;

    public GiftcardTabPresenter(int i) {
        super(GiftcardContract.GiftcardTabView.class);
        this.c = i;
    }

    public void b(Bundle bundle) {
        if (this.b == null) {
            this.b = new RxGiftcardTask(e(), f());
            this.b.a(this.c);
        }
    }

    public void a(boolean z) {
        if (z) {
            n();
        } else {
            o();
        }
    }

    private void n() {
        this.b.d();
        this.b.a(this.c);
        GiftcardListTaskListener giftcardListTaskListener = new GiftcardListTaskListener(e());
        giftcardListTaskListener.a().a((ExceptionDispatcher.ExceptionHandler) new GiftcardNetworkExceptionHandler(e()));
        giftcardListTaskListener.a(true);
        Observable.create(this.b).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
            public void call() {
                ((GiftcardContract.GiftcardTabView) GiftcardTabPresenter.this.l()).a(0, true);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(giftcardListTaskListener);
    }

    private void o() {
        this.b.e();
        this.b.a(this.c);
        GiftcardListTaskListener giftcardListTaskListener = new GiftcardListTaskListener(e());
        giftcardListTaskListener.a().a((ExceptionDispatcher.ExceptionHandler) new GiftcardNetworkExceptionHandler(e()));
        giftcardListTaskListener.a(false);
        Observable.create(this.b).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
            public void call() {
                ((GiftcardContract.GiftcardTabView) GiftcardTabPresenter.this.l()).a(0, true);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(giftcardListTaskListener);
    }

    private class GiftcardListTaskListener extends RxBaseErrorHandleTaskListener<RxGiftcardTask.Result> {
        private boolean b;

        public GiftcardListTaskListener(Context context) {
            super(context);
        }

        public void a(boolean z) {
            this.b = z;
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            ((GiftcardContract.GiftcardTabView) GiftcardTabPresenter.this.l()).a(0, false);
            ((GiftcardContract.GiftcardTabView) GiftcardTabPresenter.this.l()).a(i, str, th);
        }

        /* access modifiers changed from: protected */
        public void a(RxGiftcardTask.Result result) {
            ((GiftcardContract.GiftcardTabView) GiftcardTabPresenter.this.l()).a(0, false);
            ArrayList<RxGiftcardTask.Result.GiftcardItem> arrayList = result.f12420a;
            if (arrayList == null || arrayList.isEmpty()) {
                if (this.b) {
                    ((GiftcardContract.GiftcardTabView) GiftcardTabPresenter.this.l()).a_(GiftcardTabPresenter.this.p());
                } else {
                    ((GiftcardContract.GiftcardTabView) GiftcardTabPresenter.this.l()).a(false, arrayList);
                }
            } else if (this.b) {
                ((GiftcardContract.GiftcardTabView) GiftcardTabPresenter.this.l()).a(true, arrayList);
            } else {
                ((GiftcardContract.GiftcardTabView) GiftcardTabPresenter.this.l()).a(false, arrayList);
            }
        }
    }

    /* access modifiers changed from: private */
    public String p() {
        switch (this.c) {
            case 1:
                return e().getString(R.string.mibi_giftcard_available);
            case 2:
                return e().getString(R.string.mibi_giftcard_unavailable);
            case 3:
                return e().getString(R.string.mibi_giftcard_used);
            default:
                throw new IllegalStateException("gift card type error , type = " + this.c);
        }
    }

    private class GiftcardNetworkExceptionHandler extends NetworkExceptionHandler {
        public GiftcardNetworkExceptionHandler(Context context) {
            super(context);
        }

        public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
            if (!bundle.containsKey("errDesc")) {
                bundle.putString("errDesc", GiftcardTabPresenter.this.e().getString(R.string.mibi_error_network_summary));
            }
            ((GiftcardContract.GiftcardTabView) GiftcardTabPresenter.this.l()).a(0, false);
            ((GiftcardContract.GiftcardTabView) GiftcardTabPresenter.this.l()).b_(bundle.getString("errDesc"));
            return true;
        }
    }
}
