package com.xiaomi.payment.channel.model;

import android.os.Bundle;

public interface IGetMSGInfoListener {
    void a(int i, String str, Throwable th);

    void a(int i, boolean z);

    void a(Bundle bundle);
}
