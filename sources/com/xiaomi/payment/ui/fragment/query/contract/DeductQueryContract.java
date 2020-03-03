package com.xiaomi.payment.ui.fragment.query.contract;

import android.os.Bundle;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.IView;

public class DeductQueryContract {

    public interface Presenter extends IPresenter {
    }

    public interface View extends IView {
        void e(Bundle bundle);

        void e(String str);
    }
}
