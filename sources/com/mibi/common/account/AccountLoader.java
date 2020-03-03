package com.mibi.common.account;

import android.content.Context;
import android.os.Parcelable;
import com.mibi.common.exception.PaymentException;

public interface AccountLoader extends Parcelable {
    String a();

    boolean a(Context context);

    AccountToken b();

    void b(Context context) throws PaymentException;

    void c(Context context) throws PaymentException;
}
