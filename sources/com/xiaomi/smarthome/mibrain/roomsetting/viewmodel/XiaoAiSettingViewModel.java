package com.xiaomi.smarthome.mibrain.roomsetting.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.mibrain.roomsetting.BaseViewModel;
import com.xiaomi.smarthome.mibrain.roomsetting.XiaoAiRoomSettingManager;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class XiaoAiSettingViewModel extends BaseViewModel<XiaoAiSettingBean> {
    public MutableLiveData<XiaoAiSettingBean> a() {
        return this.f10663a;
    }

    public static class XiaoAiSettingBean {

        /* renamed from: a  reason: collision with root package name */
        public int f10705a;
        public List<String> b;
        public LinkedHashMap<String, LinkedHashMap<String, List<String>>> c;

        public XiaoAiSettingBean(int i, List<String> list) {
            this.f10705a = i;
            if (list == null || list.isEmpty()) {
                this.b = list;
            }
            ArrayList arrayList = new ArrayList();
            if (list != null) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    String str = list.get(i2);
                    if (!TextUtils.isEmpty(str) && SmartHomeDeviceManager.a().b(str) != null) {
                        arrayList.add(str);
                    }
                }
            }
            this.b = arrayList;
            this.c = HomeManager.HomeRoomSortUtil.a((List<String>) arrayList);
        }
    }

    public void b() {
        XiaoAiRoomSettingManager.a().a((AsyncCallback<List<String>, Error>) new AsyncCallback<List<String>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<String> list) {
                XiaoAiSettingViewModel.this.f10663a.postValue(new XiaoAiSettingBean(ErrorCode.SUCCESS.getCode(), list));
            }

            public void onFailure(Error error) {
                XiaoAiSettingViewModel.this.f10663a.postValue(new XiaoAiSettingBean(error.a(), (List<String>) null));
            }

            /* renamed from: b */
            public void onCache(List<String> list) {
                if (list != null) {
                    XiaoAiSettingViewModel.this.f10663a.postValue(new XiaoAiSettingBean(ErrorCode.SUCCESS_CACHE.getCode(), list));
                }
            }
        }, true);
    }
}
