package com.xiaomi.payment.pay.contract;

import android.os.Bundle;
import com.mibi.common.base.IHandleError;
import com.mibi.common.base.IHandleProgress;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.IView;

public class CheckPayOrderContract {

    public interface Presenter extends IPresenter {
        void g();
    }

    public interface View extends IHandleError, IHandleProgress, IView {
        void a(int i, String str);

        void a(int i, String str, Bundle bundle);

        void a(String str, Bundle bundle);
    }
}
