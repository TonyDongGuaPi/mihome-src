package com.xiaomi.payment.homepage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.IView;
import com.mibi.common.base.StepFragment;
import com.xiaomi.payment.data.EntryData;
import com.xiaomi.payment.task.rxjava.RxHomePageGridTask;

public class MiliCenterContract {

    /* renamed from: a  reason: collision with root package name */
    public static int f12310a = 0;
    public static int b = 1;
    public static int c = 2;
    public static int d = 3;

    interface Presenter extends IPresenter {
        void B_();

        void a(long j);

        void a(Activity activity);

        void a(EntryData entryData);

        void a(boolean z);

        void a(boolean z, boolean z2);

        void g();

        void h();

        void i();

        void j();

        Intent k();
    }

    interface View extends IView {
        void a(int i, String str);

        void a(long j);

        void a(long j, int i);

        void a(EntryData entryData);

        void a(RxHomePageGridTask.Result result);

        void a(Class<? extends StepFragment> cls, Bundle bundle);

        void a_(Intent intent);

        void a_(boolean z);

        void b(EntryData entryData);

        void b_(int i);

        void b_(boolean z);

        void c();

        void c(boolean z);

        void c_(String str);

        void d_(String str);

        void j();

        void p_();

        void q_();

        void r_();

        void s_();

        void t_();

        void u_();

        void v_();

        void w_();

        void x_();

        void y_();
    }
}
