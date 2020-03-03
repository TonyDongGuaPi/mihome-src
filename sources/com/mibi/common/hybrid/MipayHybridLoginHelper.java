package com.mibi.common.hybrid;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.mibi.common.account.AccountLoader;
import com.mibi.common.account.AccountRegistry;
import com.mibi.common.account.AccountToken;
import com.mibi.common.account.AccountUtils;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.mibi.common.rxjava.RxTask;
import java.lang.ref.WeakReference;
import miuipub.hybrid.HybridView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class MipayHybridLoginHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7577a = "MibiHybrid";
    private int b = 0;
    private boolean c;

    private boolean a(String str) {
        return false;
    }

    MipayHybridLoginHelper() {
    }

    public void a(HybridView hybridView, String str, String str2, String str3) {
        Uri parse = Uri.parse(str3);
        parse.getQueryParameter("callback");
        parse.getQueryParameter("sid");
        this.c = AccountUtils.a(hybridView.getContext().getApplicationContext());
        if (this.c) {
            AccountRegistry.a().getAuthToken(AccountUtils.b(hybridView.getContext()), "weblogin:" + str3, (Bundle) null, (Activity) null, (AccountManagerCallback<Bundle>) new AccountCallback(hybridView), (Handler) null);
        }
        if (this.b <= 0) {
        }
    }

    private static class AccountCallback implements AccountManagerCallback<Bundle> {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<HybridView> f7578a;

        public AccountCallback(HybridView hybridView) {
            this.f7578a = new WeakReference<>(hybridView);
        }

        public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
            HybridView hybridView;
            try {
                String string = accountManagerFuture.getResult().getString("authtoken");
                if (!TextUtils.isEmpty(string) && (hybridView = (HybridView) this.f7578a.get()) != null) {
                    hybridView.loadUrl(string);
                }
            } catch (Exception e) {
                Log.d("MibiHybrid", "handle callback failed", e);
            }
        }
    }

    private class GuestAccountTaskListener extends RxBaseErrorHandleTaskListener<LoadGuestAccountTask.Result> {
        private Context b;
        private String c;
        private String d;
        private WeakReference<HybridView> e;

        /* access modifiers changed from: protected */
        public void a(LoadGuestAccountTask.Result result) {
        }

        GuestAccountTaskListener(Context context, HybridView hybridView, String str, String str2) {
            super(context);
            this.b = context.getApplicationContext();
            this.c = str;
            this.d = str2;
            this.e = new WeakReference<>(hybridView);
        }
    }

    private Observable<LoadGuestAccountTask.Result> a(Context context, String str) {
        return Observable.create(new LoadGuestAccountTask(context, str)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    private static class LoadGuestAccountTask extends RxTask<Result> {

        /* renamed from: a  reason: collision with root package name */
        private Context f7580a;
        private String b;

        public static class Result {

            /* renamed from: a  reason: collision with root package name */
            AccountToken f7581a;
        }

        LoadGuestAccountTask(Context context, String str) {
            super(Result.class);
            this.f7580a = context;
            this.b = str;
        }

        /* access modifiers changed from: protected */
        public void a(Result result) throws PaymentException {
            AccountLoader a2 = AccountUtils.a();
            a2.c(this.f7580a);
            result.f7581a = a2.b();
        }
    }
}
