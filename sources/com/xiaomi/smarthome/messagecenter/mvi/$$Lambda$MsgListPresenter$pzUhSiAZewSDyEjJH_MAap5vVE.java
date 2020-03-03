package com.xiaomi.smarthome.messagecenter.mvi;

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.xiaomi.smarthome.messagecenter.mvi.view.MessageCenterView;
import io.reactivex.Observable;

/* renamed from: com.xiaomi.smarthome.messagecenter.mvi.-$$Lambda$MsgListPresenter$pzUhSi-AZewSDyEjJH_MAap5vVE  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MsgListPresenter$pzUhSiAZewSDyEjJH_MAap5vVE implements MviBasePresenter.ViewIntentBinder {
    public static final /* synthetic */ $$Lambda$MsgListPresenter$pzUhSiAZewSDyEjJH_MAap5vVE INSTANCE = new $$Lambda$MsgListPresenter$pzUhSiAZewSDyEjJH_MAap5vVE();

    private /* synthetic */ $$Lambda$MsgListPresenter$pzUhSiAZewSDyEjJH_MAap5vVE() {
    }

    public final Observable bind(MvpView mvpView) {
        return ((MessageCenterView) mvpView).a();
    }
}
