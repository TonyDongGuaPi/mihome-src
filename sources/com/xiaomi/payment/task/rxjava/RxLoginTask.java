package com.xiaomi.payment.task.rxjava;

import android.accounts.AccountsException;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import com.mibi.common.account.AccountRegistry;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.data.Utils;
import com.mibi.common.exception.AccountException;
import com.mibi.common.exception.ConnectionException;
import com.mibi.common.exception.NotConnectedException;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.rxjava.RxTask;
import java.io.IOException;

public class RxLoginTask extends RxTask<Result> {

    /* renamed from: a  reason: collision with root package name */
    private Activity f12425a;
    private SortedParameter b;

    public static class Result {

        /* renamed from: a  reason: collision with root package name */
        public static final int f12426a = 0;
        public static final int b = 1;
        public static final int c = 2;
        public String d;
        public int e;
    }

    public RxLoginTask(Activity activity) {
        super(Result.class);
        this.f12425a = activity;
    }

    public void a(SortedParameter sortedParameter) {
        if (sortedParameter == null) {
            this.b = new SortedParameter();
        }
        this.b = sortedParameter;
    }

    /* access modifiers changed from: protected */
    public void a(Result result) throws PaymentException {
        if (Utils.a((Context) this.f12425a)) {
            String f = this.b.f("userName");
            String f2 = this.b.f("password");
            try {
                result.d = AccountRegistry.d().getStsUrl(this.f12425a, f, f2, CommonConstants.C);
                result.e = 0;
            } catch (OperationCanceledException unused) {
                result.e = 2;
            } catch (AuthenticatorException e) {
                result.e = 1;
                throw new AccountException((Throwable) e);
            } catch (IOException e2) {
                result.e = 2;
                throw new AccountException((Throwable) new ConnectionException(e2));
            } catch (AccountsException e3) {
                result.e = 1;
                throw new AccountException((Throwable) e3);
            }
        } else {
            throw new NotConnectedException();
        }
    }
}
