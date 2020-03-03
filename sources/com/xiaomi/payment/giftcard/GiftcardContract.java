package com.xiaomi.payment.giftcard;

import com.mibi.common.base.IHandleError;
import com.mibi.common.base.IHandleProgress;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.IView;
import com.xiaomi.payment.task.rxjava.RxGiftcardTask;
import java.util.ArrayList;

public class GiftcardContract {

    /* renamed from: a  reason: collision with root package name */
    public static final int f12299a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final String d = "giftcard_type";

    interface GiftcardTabView extends IHandleError, IHandleProgress, IView {
        void a(boolean z, ArrayList<RxGiftcardTask.Result.GiftcardItem> arrayList);

        void a_(String str);

        void b_(String str);
    }

    interface Presenter extends IPresenter {
        void a(boolean z);
    }
}
