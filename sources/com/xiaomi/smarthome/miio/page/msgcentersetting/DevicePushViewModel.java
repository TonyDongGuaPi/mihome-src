package com.xiaomi.smarthome.miio.page.msgcentersetting;

import android.arch.lifecycle.MutableLiveData;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.mibrain.roomsetting.BaseViewModel;
import com.xiaomi.smarthome.miio.page.msgcentersetting.model.DeviceMsgSettingChild;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class DevicePushViewModel extends BaseViewModel<List<DeviceMsgSettingChild>> {
    public static String b = "DevicePushViewModel";
    /* access modifiers changed from: private */
    public MutableLiveData<Integer> c = new MutableLiveData<>();

    public MutableLiveData<List<DeviceMsgSettingChild>> a() {
        return this.f10663a;
    }

    public MutableLiveData<Integer> b() {
        return this.c;
    }

    public void a(String str, final boolean z) {
        final Home j = HomeManager.a().j(str);
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.addAll(MultiHomeDeviceManager.a().f());
        } else if (!(j == null || j.i() == null)) {
            arrayList.addAll(j.i());
        }
        RemoteFamilyApi.a().a(z ? null : j, "", (List<String>) arrayList, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                String str = DevicePushViewModel.b;
                LogUtilGrey.a(str, "getMiPushDeviceSwitch: " + jSONObject.toString());
                ArrayList arrayList = new ArrayList();
                try {
                    DevicePushViewModel.this.c.setValue(Integer.valueOf(jSONObject.optInt("switch")));
                    JSONArray optJSONArray = jSONObject.optJSONArray("device_setting");
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        DeviceMsgSettingChild deviceMsgSettingChild = new DeviceMsgSettingChild();
                        deviceMsgSettingChild.f = optJSONObject.optString("did");
                        deviceMsgSettingChild.g = optJSONObject.optString("name");
                        deviceMsgSettingChild.h = optJSONObject.optString("roomid");
                        deviceMsgSettingChild.i = optJSONObject.optString("model");
                        deviceMsgSettingChild.j = Integer.valueOf(optJSONObject.optInt("status"));
                        if (!(z || j == null || j.d() == null)) {
                            List<Room> d = j.d();
                            Room i2 = HomeManager.a().i(deviceMsgSettingChild.h);
                            if (i2 != null) {
                                deviceMsgSettingChild.l = d.indexOf(i2);
                                deviceMsgSettingChild.m = i2.h().indexOf(deviceMsgSettingChild.f);
                            }
                        }
                        arrayList.add(deviceMsgSettingChild);
                    }
                    Collections.sort(arrayList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DevicePushViewModel.this.f10663a.postValue(arrayList);
            }

            public void onFailure(Error error) {
                DevicePushViewModel.this.f10663a.postValue(new ArrayList());
                String str = DevicePushViewModel.b;
                LogUtilGrey.a(str, "getMiPushDeviceSwitch: " + error.toString());
            }
        }, (JSONObject) null);
    }
}
