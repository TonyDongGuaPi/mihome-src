package com.xiaomi.smarthome.newui.mainpage.devicepage;

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.xiaomi.smarthome.newui.mainpage.devicepage.view.DevicePageView;
import io.reactivex.Observable;

/* renamed from: com.xiaomi.smarthome.newui.mainpage.devicepage.-$$Lambda$DevicePagePresenter$GRmSWseSAuBRCkzr91ii8Dl3zNY  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$DevicePagePresenter$GRmSWseSAuBRCkzr91ii8Dl3zNY implements MviBasePresenter.ViewIntentBinder {
    public static final /* synthetic */ $$Lambda$DevicePagePresenter$GRmSWseSAuBRCkzr91ii8Dl3zNY INSTANCE = new $$Lambda$DevicePagePresenter$GRmSWseSAuBRCkzr91ii8Dl3zNY();

    private /* synthetic */ $$Lambda$DevicePagePresenter$GRmSWseSAuBRCkzr91ii8Dl3zNY() {
    }

    public final Observable bind(MvpView mvpView) {
        return ((DevicePageView) mvpView).dataSetChanged();
    }
}
