package com.xiaomi.payment.deduct.contract;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.IView;
import com.xiaomi.payment.recharge.RechargeType;
import java.util.ArrayList;

public class DeductTypeListContract {

    /* renamed from: a  reason: collision with root package name */
    public static final int f12250a = 100;
    public static final int b = 200;

    public interface Presenter extends IPresenter {
        void a(RechargeType rechargeType);

        String m_();
    }

    public interface View extends IView {
        void a(int i, String str);

        void a(Context context, ArrayList<RechargeType> arrayList);

        void d(String str);

        void e(Bundle bundle);
    }
}
