package com.xiaomi.payment.channel.contract;

import android.app.Fragment;
import android.os.Bundle;
import com.mibi.common.base.IHandleError;
import com.mibi.common.base.IHandleProgress;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.IView;

public class PaytoolContract {

    public interface Function<Parameter> {
        void a(Parameter parameter);
    }

    public interface Presenter extends IPresenter {
        void a(long j);

        long b(long j);

        String[] i_();

        void j_();
    }

    public interface View extends IHandleError, IHandleProgress, IView {
        void N();

        void O();

        void Q();

        void a(long j);

        void a(Bundle bundle, int i);

        void a(Function<Fragment> function);
    }
}
