package com.xiaomi.payment.channel.model;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import com.xiaomi.payment.channel.contract.PaytoolContract;

public interface IPaytoolTaskListener {
    void a();

    void a(int i, String str, Throwable th);

    void a(long j, long j2);

    void a(Activity activity, Bundle bundle);

    void a(PaytoolContract.Function<Fragment> function);

    void b();
}
