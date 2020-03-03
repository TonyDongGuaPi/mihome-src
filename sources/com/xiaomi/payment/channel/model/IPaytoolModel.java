package com.xiaomi.payment.channel.model;

import android.os.Bundle;
import com.mibi.common.data.SortedParameter;

public interface IPaytoolModel {
    void a(int i, int i2, Bundle bundle);

    void a(Bundle bundle);

    void a(SortedParameter sortedParameter, IPaytoolTaskListener iPaytoolTaskListener);

    void a(IPaytoolTaskListener iPaytoolTaskListener);

    void b(Bundle bundle);

    String[] d();

    void e();

    void f();
}
