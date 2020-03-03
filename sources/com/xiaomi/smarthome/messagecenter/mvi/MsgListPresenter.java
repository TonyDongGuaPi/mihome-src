package com.xiaomi.smarthome.messagecenter.mvi;

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;
import com.xiaomi.smarthome.messagecenter.mvi.loaders.MessageListLoader;
import com.xiaomi.smarthome.messagecenter.mvi.view.MessageCenterView;
import com.xiaomi.smarthome.messagecenter.mvi.view.PartialChanges;
import com.xiaomi.smarthome.messagecenter.ui.MessageCenterViewState;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class MsgListPresenter extends MviBasePresenter<MessageCenterView, MessageCenterViewState> {

    /* renamed from: a  reason: collision with root package name */
    MessageListLoader f10480a;

    public MsgListPresenter(MessageListLoader messageListLoader) {
    }

    public MsgListPresenter() {
    }

    /* access modifiers changed from: protected */
    public void d() {
        Observable.merge(a($$Lambda$MsgListPresenter$pzUhSiAZewSDyEjJH_MAap5vVE.INSTANCE).flatMap(new Function() {
            public final Object apply(Object obj) {
                return MsgListPresenter.this.b((Boolean) obj);
            }
        }), a($$Lambda$MsgListPresenter$_RLA4mwfafmP2yszF0qt6XZEQM.INSTANCE).flatMap(new Function() {
            public final Object apply(Object obj) {
                return MsgListPresenter.this.a((Boolean) obj);
            }
        })).observeOn(AndroidSchedulers.mainThread());
        new MessageCenterViewState.Builder((MessageCenterViewState) null).a();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ObservableSource b(Boolean bool) throws Exception {
        return this.f10480a.a().map($$Lambda$MsgListPresenter$7nMKLf3iuhJ9mRNiwU89XTG0A.INSTANCE).startWith(new PartialChanges.FirstPageLoading()).onErrorReturn($$Lambda$MsgListPresenter$zoZDVfDF0zd877vsSAkbWElYoGE.INSTANCE).subscribeOn(Schedulers.io());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ PartialChanges b(List list) throws Exception {
        return new PartialChanges.FirstPageLoaded(list);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ PartialChanges b(Throwable th) throws Exception {
        return new PartialChanges.FirstPageLoading();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ObservableSource a(Boolean bool) throws Exception {
        return this.f10480a.b().map($$Lambda$MsgListPresenter$ocdZ6T40uDLcgdnjYFyFfWl9zAQ.INSTANCE).startWith(new PartialChanges.NextPageLoading()).onErrorReturn($$Lambda$MsgListPresenter$mVsh5J0zkwAbVG3A1Eg8zC1bf8.INSTANCE).subscribeOn(Schedulers.io());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ PartialChanges a(List list) throws Exception {
        return new PartialChanges.NextPageLoaded(list);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ PartialChanges a(Throwable th) throws Exception {
        return new PartialChanges.NextPageError();
    }
}
