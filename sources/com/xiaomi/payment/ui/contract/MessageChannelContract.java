package com.xiaomi.payment.ui.contract;

import android.app.Activity;
import android.os.Bundle;
import com.mibi.common.base.IHandleError;
import com.mibi.common.base.IHandleProgress;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.IView;

public class MessageChannelContract {

    public interface Presenter extends IPresenter {
        void a(Activity activity, long j);

        String[] n();
    }

    public interface View extends IHandleError, IHandleProgress, IView {
        void N();

        void e(Bundle bundle);
    }
}
