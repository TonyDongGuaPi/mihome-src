package com.xiaomi.smarthome.newui.mainpage.devicepage;

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.xiaomi.smarthome.newui.mainpage.devicepage.model.MainPageViewState;
import com.xiaomi.smarthome.newui.mainpage.devicepage.view.DevicePageView;

/* renamed from: com.xiaomi.smarthome.newui.mainpage.devicepage.-$$Lambda$LjKQQFt6YAm9RLKOaO4_GBP_6-A  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$LjKQQFt6YAm9RLKOaO4_GBP_6A implements MviBasePresenter.ViewStateConsumer {
    public static final /* synthetic */ $$Lambda$LjKQQFt6YAm9RLKOaO4_GBP_6A INSTANCE = new $$Lambda$LjKQQFt6YAm9RLKOaO4_GBP_6A();

    private /* synthetic */ $$Lambda$LjKQQFt6YAm9RLKOaO4_GBP_6A() {
    }

    public final void accept(MvpView mvpView, Object obj) {
        ((DevicePageView) mvpView).render((MainPageViewState) obj);
    }
}
