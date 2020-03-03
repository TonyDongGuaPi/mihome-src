package com.xiaomi.payment.pay.contract;

import android.os.Bundle;
import com.mibi.common.base.IHandleError;
import com.mibi.common.base.IHandleProgress;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.IView;

public class PaymentOrderInfoContract {

    public interface Presenter extends IPresenter {
        void a(boolean z, boolean z2, int i, boolean z3);
    }

    public interface View extends IHandleError, IHandleProgress, IView {
        void N();

        void O();

        void Q();

        void a(int i, String str, Bundle bundle);

        void e(Bundle bundle);

        void f(Bundle bundle);

        void g(Bundle bundle);

        void h(Bundle bundle);
    }
}
