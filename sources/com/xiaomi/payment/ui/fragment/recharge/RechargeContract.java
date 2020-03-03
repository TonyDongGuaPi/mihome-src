package com.xiaomi.payment.ui.fragment.recharge;

import android.os.Bundle;
import com.mibi.common.base.BaseActivity;
import com.mibi.common.base.IHandleError;
import com.mibi.common.base.IHandleProgress;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.IView;
import com.xiaomi.payment.recharge.RechargeType;
import java.util.ArrayList;

public interface RechargeContract {

    public interface Presenter extends IPresenter {
        void a(RechargeType rechargeType);

        void b(RechargeType rechargeType);

        void g();

        void h();
    }

    public interface View extends IHandleError, IHandleProgress, IView {
        void a();

        void a(int i, String str);

        void a(Bundle bundle, Class<? extends BaseActivity> cls);

        void a(String str, ArrayList<RechargeType> arrayList);

        void a_(int i, Bundle bundle);
    }
}
