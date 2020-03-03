package com.xiaomi.jr.mipay.codepay.presenter;

import android.os.Bundle;
import com.xiaomi.jr.mipay.codepay.data.CodePayConfirmParams;
import com.xiaomi.jr.mipay.codepay.data.PayType;
import java.util.List;

public interface CodePayContract {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10900a = 1001;
    public static final int b = 1003;
    public static final int c = 1004;

    public interface Presenter {
        void a();

        void a(int i);

        void a(int i, Bundle bundle);

        void a(boolean z);

        void b();

        void b(int i, Bundle bundle);

        PayType c();

        List<PayType> e();
    }

    public interface View {
        void a();

        void a(int i, String str, Throwable th);

        void a(int i, boolean z);

        void a(PayType payType);

        void a(String str, CodePayConfirmParams codePayConfirmParams);

        void a(boolean z);

        void b();

        void b(boolean z);

        void c(boolean z);
    }
}
