package com.mibi.common.account;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.exception.AccountChangedException;
import com.mibi.common.exception.AccountException;
import com.mibi.common.exception.AccountThrottingException;
import com.mibi.common.exception.ConnectionException;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

class MiAccountLoader implements AccountLoader {
    public static final Parcelable.Creator<AccountLoader> CREATOR = new Parcelable.Creator<AccountLoader>() {
        /* renamed from: a */
        public AccountLoader createFromParcel(Parcel parcel) {
            return new MiAccountLoader((Account) parcel.readParcelable((ClassLoader) null));
        }

        /* renamed from: a */
        public AccountLoader[] newArray(int i) {
            return new MiAccountLoader[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private static final String f7446a = "MiAccountLoader";
    private static final int b = 30000;
    private static final AccountToken c = new AccountToken("", "", "", "");
    private final Account d;
    private AccountToken e;

    public int describeContents() {
        return 0;
    }

    MiAccountLoader(Account account) {
        this.d = account;
    }

    public String a() {
        return this.d.name;
    }

    public synchronized AccountToken b() {
        return this.e;
    }

    public boolean a(Context context) {
        return !TextUtils.equals(AccountUtils.c(context), a());
    }

    public void b(Context context) throws PaymentException {
        if (!a(context)) {
            e(context);
            return;
        }
        throw new AccountChangedException();
    }

    public void c(Context context) throws PaymentException {
        if (!a(context)) {
            d(context);
            e(context);
            return;
        }
        throw new AccountChangedException();
    }

    private void d(Context context) {
        String d2 = this.e.d();
        if (!TextUtils.isEmpty(d2)) {
            AccountRegistry.a().invalidateAuthToken("com.xiaomi", d2);
        }
    }

    private synchronized void e(Context context) throws PaymentException {
        AccountManagerFuture<Bundle> authToken = AccountRegistry.a().getAuthToken(this.d, CommonConstants.C, (Bundle) null, true, (AccountManagerCallback<Bundle>) null, (Handler) null);
        try {
            Bundle result = authToken.getResult(30000, TimeUnit.MILLISECONDS);
            if (!authToken.isDone() || result == null) {
                throw new AccountException();
            }
            String string = result.getString("authAccount");
            String string2 = result.getString("authtoken");
            if (TextUtils.isEmpty(string)) {
                throw new AccountException((Throwable) new ResultException("uid is empty"));
            } else if (TextUtils.isEmpty(string2)) {
                Intent intent = (Intent) result.getParcelable("intent");
                if (intent == null) {
                    int i = result.getInt("errorCode");
                    String string3 = result.getString("errorMessage");
                    throw new AccountException((Throwable) new ResultException("authtoken is empty dueto error " + i + ": " + string3));
                } else if (!(context instanceof Activity)) {
                    throw new AccountThrottingException();
                } else {
                    context.startActivity(intent);
                    throw new AccountThrottingException();
                }
            } else if (TextUtils.equals(string, this.d.name)) {
                ExtendedAuthToken a2 = ExtendedAuthToken.a(string2);
                if (a2 == null) {
                    throw new AccountException((Throwable) new ResultException("Cannot parse ext token"));
                } else if (TextUtils.isEmpty(a2.f7443a) || TextUtils.isEmpty(a2.b)) {
                    throw new AccountException((Throwable) new ResultException("serviceToken or security is empty"));
                } else {
                    this.e = new AccountToken(string, string2, a2.f7443a, a2.b);
                }
            } else {
                throw new AccountChangedException();
            }
        } catch (ActivityNotFoundException e2) {
            Log.e(f7446a, "loadAccountInfo error", e2);
            throw new AccountThrottingException();
        } catch (OperationCanceledException e3) {
            throw new AccountException((Throwable) e3);
        } catch (AuthenticatorException e4) {
            throw new AccountException((Throwable) e4);
        } catch (IOException e5) {
            throw new AccountException((Throwable) new ConnectionException(e5));
        } catch (Throwable unused) {
            throw new AccountThrottingException();
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.d, i);
    }
}
