package com.mibi.common.rxjava;

import android.content.Context;
import com.mibi.common.exception.rxjava.AccountChangedExceptionHandler;
import com.mibi.common.exception.rxjava.AccountExceptionHandler;
import com.mibi.common.exception.rxjava.AccountThrottingExceptionHandler;
import com.mibi.common.exception.rxjava.CertificateDateNotValidExceptionHandler;
import com.mibi.common.exception.rxjava.ConnectionExceptionHandler;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import com.mibi.common.exception.rxjava.IllegalDeviceExceptionHandler;
import com.mibi.common.exception.rxjava.NetworkExceptionHandler;
import com.mibi.common.exception.rxjava.NotConnectedExceptionHandler;
import com.mibi.common.exception.rxjava.PasswordErrorExceptionHandler;
import com.mibi.common.exception.rxjava.PaymentExceptionHandler;
import com.mibi.common.exception.rxjava.ResultExceptionHandler;
import com.mibi.common.exception.rxjava.ServerErrorCodeExceptionHandler;
import com.mibi.common.exception.rxjava.ServiceExceptionHandler;
import com.mibi.common.exception.rxjava.ServiceTokenExpiredExceptionHandler;
import rx.functions.Action1;

public abstract class RxBaseErrorHandler implements Action1<Throwable> {

    /* renamed from: a  reason: collision with root package name */
    private Context f7586a;
    private ExceptionDispatcher b = new ExceptionDispatcher();

    /* access modifiers changed from: protected */
    public abstract void a(int i, String str, Throwable th);

    public RxBaseErrorHandler(Context context) {
        this.f7586a = context;
        this.b.a((ExceptionDispatcher.ExceptionHandler) new AccountChangedExceptionHandler(this.f7586a)).a((ExceptionDispatcher.ExceptionHandler) new AccountThrottingExceptionHandler(this.f7586a)).a((ExceptionDispatcher.ExceptionHandler) new AccountExceptionHandler(this.f7586a)).a((ExceptionDispatcher.ExceptionHandler) new CertificateDateNotValidExceptionHandler(this.f7586a)).a((ExceptionDispatcher.ExceptionHandler) new ConnectionExceptionHandler()).a((ExceptionDispatcher.ExceptionHandler) new NotConnectedExceptionHandler(this.f7586a)).a((ExceptionDispatcher.ExceptionHandler) new NetworkExceptionHandler(this.f7586a)).a((ExceptionDispatcher.ExceptionHandler) new IllegalDeviceExceptionHandler(this.f7586a)).a((ExceptionDispatcher.ExceptionHandler) new PasswordErrorExceptionHandler(this.f7586a)).a((ExceptionDispatcher.ExceptionHandler) new ResultExceptionHandler(this.f7586a)).a((ExceptionDispatcher.ExceptionHandler) new ServiceExceptionHandler(this.f7586a)).a((ExceptionDispatcher.ExceptionHandler) new ServiceTokenExpiredExceptionHandler(this.f7586a)).a((ExceptionDispatcher.ExceptionHandler) new ServerErrorCodeExceptionHandler(this.f7586a)).a((ExceptionDispatcher.ExceptionHandler) new PaymentExceptionHandler(this.f7586a) {
            /* access modifiers changed from: protected */
            public void a(int i, String str, Throwable th) {
                RxBaseErrorHandler.this.a(i, str, th);
            }
        });
    }

    /* renamed from: a */
    public void call(Throwable th) {
        try {
            if (!this.b.a(th)) {
                throw new IllegalStateException("error not handled", th);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("exception occurs in onError", e);
        }
    }

    public ExceptionDispatcher a() {
        return this.b;
    }
}
