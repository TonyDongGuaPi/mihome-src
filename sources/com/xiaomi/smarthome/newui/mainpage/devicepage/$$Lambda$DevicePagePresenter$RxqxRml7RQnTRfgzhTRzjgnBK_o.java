package com.xiaomi.smarthome.newui.mainpage.devicepage;

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.xiaomi.smarthome.newui.mainpage.devicepage.view.DevicePageView;
import io.reactivex.Observable;

/* renamed from: com.xiaomi.smarthome.newui.mainpage.devicepage.-$$Lambda$DevicePagePresenter$RxqxRml7RQnTRfgzhTRzjgnBK_o  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$DevicePagePresenter$RxqxRml7RQnTRfgzhTRzjgnBK_o implements MviBasePresenter.ViewIntentBinder {
    public static final /* synthetic */ $$Lambda$DevicePagePresenter$RxqxRml7RQnTRfgzhTRzjgnBK_o INSTANCE = new $$Lambda$DevicePagePresenter$RxqxRml7RQnTRfgzhTRzjgnBK_o();

    private /* synthetic */ $$Lambda$DevicePagePresenter$RxqxRml7RQnTRfgzhTRzjgnBK_o() {
    }

    public final Observable bind(MvpView mvpView) {
        return ((DevicePageView) mvpView).pullToRefresh();
    }
}
