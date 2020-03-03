package com.xiaomi.payment.ui.fragment.query.contract;

import android.os.Bundle;
import com.mibi.common.base.IHandleError;
import com.mibi.common.base.IHandleProgress;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.IView;

public class QueryContract {

    public interface Presenter extends IPresenter {
        void a(int[] iArr);
    }

    public interface View extends IHandleError, IHandleProgress, IView {
        void N();

        void O();

        void Q();

        void a(long j);

        void a(long j, String str, int i);

        void b(long j);

        void e(Bundle bundle);

        void f(Bundle bundle);

        void g(Bundle bundle);
    }
}
