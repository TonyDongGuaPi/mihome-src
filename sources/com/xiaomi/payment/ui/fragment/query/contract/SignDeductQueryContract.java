package com.xiaomi.payment.ui.fragment.query.contract;

import android.os.Bundle;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.IView;

public class SignDeductQueryContract {

    public interface Presenter extends IPresenter {
    }

    public interface View extends IView {
        void a(String str, Bundle bundle);

        void e(Bundle bundle);
    }
}
