package com.xiaomi.payment.ui.contract;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import com.mibi.common.base.IHandleError;
import com.mibi.common.base.IHandleProgress;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.IView;

public class MessageOrderContract {

    /* renamed from: a  reason: collision with root package name */
    public static final int f12470a = 1000;

    public interface Presenter extends IPresenter {
        void a(Activity activity, Fragment fragment);

        String[] n();
    }

    public interface View extends IHandleError, IHandleProgress, IView {
        void N();

        void e(Bundle bundle);
    }
}
