package com.xiaomi.payment.pay.autoPay;

import android.os.Bundle;
import com.mibi.common.base.IHandleError;
import com.mibi.common.base.IHandleProgress;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.IView;
import com.xiaomi.payment.recharge.RechargeType;

public class AutoPayContract {

    public interface Presenter extends IPresenter {
        void a(RechargeType rechargeType);

        void g();

        void h();
    }

    public interface View extends IHandleError, IHandleProgress, IView {
        void F_();

        void G_();

        void a(int i, String str, Bundle bundle);

        void a_(Bundle bundle);

        void b(Bundle bundle);

        void c();

        void e(Bundle bundle);

        void f(Bundle bundle);

        void g(Bundle bundle);
    }
}
