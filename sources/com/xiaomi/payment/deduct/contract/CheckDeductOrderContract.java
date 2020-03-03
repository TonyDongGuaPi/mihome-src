package com.xiaomi.payment.deduct.contract;

import android.os.Bundle;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.IView;

public class CheckDeductOrderContract {

    /* renamed from: a  reason: collision with root package name */
    public static final int f12248a = 1;

    public interface Presenter extends IPresenter {
    }

    public interface View extends IView {
        void a(int i, String str);

        void b(int i, String str);

        void e(Bundle bundle);

        void f(Bundle bundle);
    }
}
