package com.xiaomi.smarthome.messagecenter.mvi;

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.xiaomi.smarthome.messagecenter.mvi.view.MessageCenterView;
import io.reactivex.Observable;

/* renamed from: com.xiaomi.smarthome.messagecenter.mvi.-$$Lambda$MsgListPresenter$_RLA4mwfa-fmP2yszF0qt6XZEQM  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MsgListPresenter$_RLA4mwfafmP2yszF0qt6XZEQM implements MviBasePresenter.ViewIntentBinder {
    public static final /* synthetic */ $$Lambda$MsgListPresenter$_RLA4mwfafmP2yszF0qt6XZEQM INSTANCE = new $$Lambda$MsgListPresenter$_RLA4mwfafmP2yszF0qt6XZEQM();

    private /* synthetic */ $$Lambda$MsgListPresenter$_RLA4mwfafmP2yszF0qt6XZEQM() {
    }

    public final Observable bind(MvpView mvpView) {
        return ((MessageCenterView) mvpView).b();
    }
}
