package com.xiaomi.payment.pay.contract;

import android.app.Activity;
import android.os.Bundle;
import com.mibi.common.base.IHandleError;
import com.mibi.common.base.IHandleProgress;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.IView;

public class PaymentCheckPasswordContract {

    public interface Presenter extends IPresenter {
        void a(Activity activity, String str, String str2);
    }

    public interface View extends IHandleError, IHandleProgress, IView {
        void N();

        void O();

        void Q();

        void R();

        void S();

        void a(int i, String str);

        void e(Bundle bundle);
    }
}
