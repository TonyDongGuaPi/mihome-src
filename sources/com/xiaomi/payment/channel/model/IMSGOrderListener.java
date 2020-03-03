package com.xiaomi.payment.channel.model;

import android.os.Bundle;

public interface IMSGOrderListener {
    void a(int i, String str, Throwable th);

    void a(int i, boolean z);

    void a(Bundle bundle);
}
