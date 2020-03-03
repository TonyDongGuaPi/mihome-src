package com.mibi.common.base;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.data.Session;

public interface IPresenter {
    void a();

    void a(int i, int i2, Bundle bundle);

    void a(Context context, Session session, Bundle bundle, Bundle bundle2);

    void a(Bundle bundle);

    void a(IView iView);

    void b();

    void b(IView iView);

    void c();

    String d();

    Context e();

    Session f();
}
