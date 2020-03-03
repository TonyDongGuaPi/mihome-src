package com.xiaomi.smarthome.mibrain.anothernamesetting;

import android.arch.lifecycle.MutableLiveData;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.mibrain.roomsetting.BaseViewModel;
import java.util.ArrayList;
import java.util.List;

public class AnotherNameDevListViewModel extends BaseViewModel<List<GroupBean>> {
    private String b = AnotherNameDevListViewModel.class.getSimpleName();

    public class GroupBean {

        /* renamed from: a  reason: collision with root package name */
        String f10635a;
        String b;
        List<String> c = new ArrayList();

        public GroupBean(String str, String str2, List<String> list) {
            this.f10635a = str;
            this.b = str2;
            this.c.clear();
            this.c.addAll(list);
        }
    }

    public MutableLiveData<List<GroupBean>> a() {
        return this.f10663a;
    }

    public void b() {
        AnotherNameManager.a().a(new AsyncCallback<List<String>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<String> list) {
                AnotherNameDevListViewModel.this.f10663a.postValue(AnotherNameDevListViewModel.this.a(list));
            }

            public void onFailure(Error error) {
                AnotherNameDevListViewModel.this.f10663a.postValue(new ArrayList());
            }

            /* renamed from: b */
            public void onCache(List<String> list) {
                super.onCache(list);
            }
        }, true);
    }

    /* access modifiers changed from: private */
    public List<GroupBean> a(List<String> list) {
        List<Home> f = HomeManager.a().f();
        ArrayList arrayList = new ArrayList();
        for (Home next : f) {
            if (next.p()) {
                List<String> a2 = HomeManager.a().a(next.j(), new boolean[0]);
                if (!a2.isEmpty()) {
                    ArrayList arrayList2 = new ArrayList();
                    for (String next2 : a2) {
                        Device b2 = SmartHomeDeviceManager.a().b(next2);
                        if (b2 != null && list.contains(b2.model)) {
                            arrayList2.add(next2);
                        }
                    }
                    if (!arrayList2.isEmpty()) {
                        arrayList.add(new GroupBean(next.k(), next.j(), arrayList2));
                    }
                }
            }
        }
        return arrayList;
    }
}
