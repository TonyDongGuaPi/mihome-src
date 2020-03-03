package com.xiaomi.smarthome.newui.mainpage.devicepage;

import android.os.Looper;
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.login.logic.LoginManager;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.newui.DropMenuStateHelper;
import com.xiaomi.smarthome.newui.mainpage.devicepage.model.DeviceMainPartialChanges;
import com.xiaomi.smarthome.newui.mainpage.devicepage.model.MainPageDeviceModel;
import com.xiaomi.smarthome.newui.mainpage.devicepage.model.MainPageViewState;
import com.xiaomi.smarthome.newui.mainpage.devicepage.view.DevicePageView;
import com.xiaomi.smarthome.newui.widget.topnavi.PageBean;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DevicePagePresenter extends MviBasePresenter<DevicePageView, MainPageViewState> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20685a = "MVI-DevicePagePresenter";
    private DevicePageLoader b = new DevicePageLoader();

    /* access modifiers changed from: protected */
    public void d() {
        MainPageViewState mainPageViewState;
        LogUtilGrey.a(f20685a, "bindIntents");
        if (GlobalSetting.q) {
            LogUtil.f(f20685a, "bindIntents:");
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    DevicePagePresenter.this.d();
                }
            });
            return;
        }
        Observable<R> doOnNext = Observable.merge(a($$Lambda$DevicePagePresenter$RxqxRml7RQnTRfgzhTRzjgnBK_o.INSTANCE).flatMap(new Function() {
            public final Object apply(Object obj) {
                return DevicePagePresenter.this.c((Boolean) obj);
            }
        }).doOnNext($$Lambda$DevicePagePresenter$qgpxQFxVdCzQ2ULvEXlxEq8OYY.INSTANCE), LoginManager.a().b().filter($$Lambda$DevicePagePresenter$hJmeLCSDR_NxBvwaFoserF41qY.INSTANCE).map($$Lambda$DevicePagePresenter$MgsSEJTXdYBeCAtSzakPO_y6AYU.INSTANCE).subscribeOn(AndroidSchedulers.mainThread()).doOnNext($$Lambda$DevicePagePresenter$AqE_K3jNkRh0cu4F1R05_kAwgj4.INSTANCE), a($$Lambda$DevicePagePresenter$GRmSWseSAuBRCkzr91ii8Dl3zNY.INSTANCE).throttleFirst(1000, TimeUnit.MILLISECONDS).doOnNext(new Consumer() {
            public final void accept(Object obj) {
                DevicePagePresenter.this.b((Boolean) obj);
            }
        }).flatMap(new Function() {
            public final Object apply(Object obj) {
                return DevicePagePresenter.this.a((Boolean) obj);
            }
        }).map($$Lambda$DevicePagePresenter$APFTqk8so5B_eb7hw9hdQGfspmY.INSTANCE).subscribeOn(AndroidSchedulers.mainThread()).doOnNext($$Lambda$DevicePagePresenter$M9y2XXMjle5eP8fOhqnvP7u4RQ.INSTANCE), DropMenuStateHelper.a().c().flatMap(new Function() {
            public final Object apply(Object obj) {
                return DevicePagePresenter.this.a((PageBean) obj);
            }
        }).map($$Lambda$DevicePagePresenter$LJKWFFZyquQXOAc4bJcKgAhjFw.INSTANCE).subscribeOn(AndroidSchedulers.mainThread()).doOnNext($$Lambda$DevicePagePresenter$bxjixSTl88K3k3599lhccrryc2s.INSTANCE)).observeOn(Schedulers.trampoline()).doOnNext($$Lambda$DevicePagePresenter$QM0eofYkArW_MzFed8zHjBcNG8o.INSTANCE);
        List<MainPageDeviceModel> f = this.b.f();
        if (f == null) {
            mainPageViewState = new MainPageViewState.Builder().a(true).a();
        } else {
            mainPageViewState = a((MainPageViewState) null, new DeviceMainPartialChanges.PageLoaded(f));
        }
        a(doOnNext.scan(mainPageViewState, new BiFunction() {
            public final Object apply(Object obj, Object obj2) {
                return DevicePagePresenter.this.a((MainPageViewState) obj, (DeviceMainPartialChanges) obj2);
            }
        }).distinctUntilChanged().throttleFirst(1000, TimeUnit.MILLISECONDS), $$Lambda$LjKQQFt6YAm9RLKOaO4_GBP_6A.INSTANCE);
        LogUtilGrey.a(f20685a, "bindIntents end");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ObservableSource c(Boolean bool) throws Exception {
        return this.b.a().map($$Lambda$DevicePagePresenter$j4i3FKak2Ka2zKZWW6V3i5Bly3A.INSTANCE).startWith(new DeviceMainPartialChanges.PageLoading()).onErrorReturn($$Lambda$DevicePagePresenter$_uogVTVy19AT0jn_AYh7m4yTStU.INSTANCE).subscribeOn(Schedulers.io());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DeviceMainPartialChanges c(List list) throws Exception {
        return new DeviceMainPartialChanges.PageLoaded(list);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DeviceMainPartialChanges a(Throwable th) throws Exception {
        return new DeviceMainPartialChanges.PageLoaded(new ArrayList());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(Boolean bool) throws Exception {
        this.b.d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ObservableSource a(Boolean bool) throws Exception {
        return this.b.c();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DeviceMainPartialChanges b(List list) throws Exception {
        return new DeviceMainPartialChanges.PageLoaded(list);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ObservableSource a(PageBean pageBean) throws Exception {
        return this.b.c();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DeviceMainPartialChanges a(List list) throws Exception {
        return new DeviceMainPartialChanges.PageLoaded(list);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean b(Integer num) throws Exception {
        return num.intValue() == 3;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DeviceMainPartialChanges a(Integer num) throws Exception {
        return new DeviceMainPartialChanges.PageLoaded((List<MainPageDeviceModel>) null);
    }

    /* access modifiers changed from: private */
    public MainPageViewState a(MainPageViewState mainPageViewState, DeviceMainPartialChanges deviceMainPartialChanges) {
        LogUtilGrey.a(f20685a, "viewStateReducer");
        return (MainPageViewState) deviceMainPartialChanges.a(mainPageViewState);
    }
}
