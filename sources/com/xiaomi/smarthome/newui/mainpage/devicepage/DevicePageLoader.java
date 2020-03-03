package com.xiaomi.smarthome.newui.mainpage.devicepage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.homeroom.DeviceListAssemble;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.util.ParcelStoreHelper;
import com.xiaomi.smarthome.newui.DropMenuStateHelper;
import com.xiaomi.smarthome.newui.mainpage.devicepage.model.MainPageDeviceModel;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.List;

public class DevicePageLoader {

    /* renamed from: a  reason: collision with root package name */
    public static ParcelStoreHelper<MainPageDeviceModel> f20681a = new ParcelStoreHelper<>("main", "main_page_device_cache_", MainPageDeviceModel.class);
    private static final String b = "MVI-DevicePageLoader";
    /* access modifiers changed from: private */
    public PublishSubject<List<MainPageDeviceModel>> c = PublishSubject.create();
    private BroadcastReceiver d = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), HomeManager.S)) {
                LogUtilGrey.a(DevicePageLoader.b, "ACTION_FORCE_UPDATE_COMPLETED");
                DevicePageLoader.this.d();
            }
        }
    };

    /* access modifiers changed from: private */
    public static /* synthetic */ void b(ObservableEmitter observableEmitter) throws Exception {
    }

    public DevicePageLoader() {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.d, new IntentFilter(HomeManager.S));
    }

    public Observable<List<MainPageDeviceModel>> a() {
        LogUtilGrey.a(b, "updateRemoteDevices");
        HomeManager.a().b(false);
        return this.c;
    }

    public Observable<List<MainPageDeviceModel>> b() {
        LogUtilGrey.a(b, "loadDevices");
        return Observable.create($$Lambda$DevicePageLoader$QGqJG0TzFr9LsjZrsxX0Ht9asv4.INSTANCE);
    }

    public Observable<List<MainPageDeviceModel>> c() {
        LogUtilGrey.a(b, "getDevicesByPageBean");
        return Observable.create($$Lambda$DevicePageLoader$cT8NzAIIJu2t3LwEidJubHXAsFU.INSTANCE).subscribeOn(Schedulers.io()).onErrorReturn($$Lambda$DevicePageLoader$yE0LRA4AZla1wvg5FUweshz94A.INSTANCE);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(ObservableEmitter observableEmitter) throws Exception {
        if (!observableEmitter.isDisposed()) {
            LogUtilGrey.a(b, "convertDevicesToModel start execute");
            observableEmitter.onNext(c(DeviceListAssemble.f17943a.a(DropMenuStateHelper.a().b())));
            observableEmitter.onComplete();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List a(Throwable th) throws Exception {
        return new ArrayList();
    }

    public void d() {
        SHApplication.getThreadExecutor().submit(new Runnable() {
            public void run() {
                LogUtilGrey.a(DevicePageLoader.b, "saveDeviceModelToCache");
                List a2 = DevicePageLoader.c(DeviceListAssemble.f17943a.a(DropMenuStateHelper.a().b()));
                DevicePageLoader.this.c.onNext(a2);
                DevicePageLoader.f20681a.a(CoreApi.a().s(), a2);
            }
        });
    }

    public Observable<List<MainPageDeviceModel>> e() {
        LogUtilGrey.a(b, "loadCache");
        return Observable.create(new ObservableOnSubscribe<List<MainPageDeviceModel>>() {
            public void subscribe(ObservableEmitter<List<MainPageDeviceModel>> observableEmitter) throws Exception {
                if (!observableEmitter.isDisposed()) {
                    String s = CoreApi.a().s();
                    LogUtilGrey.a(DevicePageLoader.b, "loadCache start uid=" + s);
                    if (TextUtils.isEmpty(s)) {
                        observableEmitter.onComplete();
                        return;
                    }
                    List<MainPageDeviceModel> b = DevicePageLoader.f20681a.b(s);
                    LogUtilGrey.a(DevicePageLoader.b, "loadCache loaded");
                    if (b == null || b.isEmpty()) {
                        observableEmitter.onComplete();
                        return;
                    }
                    observableEmitter.onNext(b);
                    observableEmitter.onComplete();
                }
            }
        });
    }

    public List<MainPageDeviceModel> f() {
        String s = CoreApi.a().s();
        LogUtilGrey.a(b, "getCachedData start uid=" + s);
        Integer num = null;
        if (TextUtils.isEmpty(s)) {
            return null;
        }
        List<MainPageDeviceModel> b2 = f20681a.b(s);
        b(b2);
        StringBuilder sb = new StringBuilder();
        sb.append("getCachedData loaded ");
        if (b2 != null) {
            num = Integer.valueOf(b2.size());
        }
        sb.append(num);
        LogUtilGrey.a(b, sb.toString());
        return b2;
    }

    private void b(List<MainPageDeviceModel> list) {
        if (list != null && !list.isEmpty()) {
            if (GlobalSetting.q || GlobalSetting.u) {
                StringBuilder sb = new StringBuilder();
                for (MainPageDeviceModel next : list) {
                    if (next != null) {
                        sb.append(next.d());
                        sb.append(",");
                    }
                }
                LogUtilGrey.a(b, "logCacheModelData:" + sb.toString());
            }
        }
    }

    public List<MainPageDeviceModel> g() {
        return f20681a.b(CoreApi.a().s());
    }

    /* access modifiers changed from: private */
    public static List<MainPageDeviceModel> c(List<Device> list) {
        LogUtilGrey.a(b, "convertDevicesToModel");
        ArrayList arrayList = new ArrayList();
        if (list == null || list.isEmpty()) {
            return arrayList;
        }
        for (int i = 0; i < list.size(); i++) {
            MainPageDeviceModel a2 = MainPageDeviceModel.a(list.get(i));
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }
}
