package com.xiaomi.smarthome.newui.mainpage.devicepage.view;

import com.xiaomi.smarthome.newui.mainpage.devicepage.model.MainPageViewState;
import com.xiaomi.smarthome.ui.base.BaseSHMvpView;
import com.xiaomi.smarthome.ui.base.EditModeView;
import io.reactivex.Observable;

public interface DevicePageView extends BaseSHMvpView<MainPageViewState>, EditModeView<MainPageViewState> {

    /* renamed from: com.xiaomi.smarthome.newui.mainpage.devicepage.view.DevicePageView$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
    }

    Observable<Boolean> dataSetChanged();

    Observable<Boolean> pullToRefresh();

    void render(MainPageViewState mainPageViewState);
}
