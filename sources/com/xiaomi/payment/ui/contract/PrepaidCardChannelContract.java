package com.xiaomi.payment.ui.contract;

import com.mibi.common.base.IHandleError;
import com.mibi.common.base.IHandleProgress;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.IView;

public class PrepaidCardChannelContract {

    public interface Presenter extends IPresenter {
        void a(String str, String str2);
    }

    public interface View extends IHandleError, IHandleProgress, IView {
        void a(long j);
    }
}
