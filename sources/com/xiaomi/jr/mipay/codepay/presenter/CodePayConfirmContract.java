package com.xiaomi.jr.mipay.codepay.presenter;

import com.xiaomi.jr.mipay.codepay.data.PayType;
import com.xiaomi.jr.mipay.codepay.validate.FooterViewType;
import java.util.List;

public class CodePayConfirmContract {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10897a = 1001;
    public static final int b = 1002;

    public interface Presenter {
        PayType a();

        void a(String str);

        List<PayType> b();
    }

    public interface View {
        void a(int i, String str);

        void a(PayType payType);

        void a(FooterViewType footerViewType);

        void a(String str);

        void a(String str, long j);

        void a(String str, String str2);

        void a(boolean z);

        void a(boolean z, String str, String str2);
    }
}
