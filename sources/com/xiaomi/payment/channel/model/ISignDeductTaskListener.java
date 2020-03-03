package com.xiaomi.payment.channel.model;

import android.app.Fragment;
import com.xiaomi.payment.channel.contract.PaytoolContract;

public interface ISignDeductTaskListener {
    void a();

    void a(int i, String str, Throwable th);

    void a(PaytoolContract.Function<Fragment> function);

    void b();

    void c();
}
