package com.xiaomi.payment.deduct.contract;

import android.app.Fragment;
import android.os.Bundle;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.IView;

public class DoDeductContract {

    public interface Function<T> {
        void a(T t);
    }

    public interface Presenter extends IPresenter {
        void g();
    }

    public interface View extends IView {
        void a(int i, String str, Bundle bundle);

        void a(Function<Fragment> function);
    }
}
