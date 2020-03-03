package com.xiaomi.smarthome.mibrain.roomsetting;

import android.text.TextUtils;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.mibrain.roomsetting.model.XiaoAiRoomItem;
import com.xiaomi.smarthome.mibrain.roomsetting.model.XiaoAiVoiceCategory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class XiaoAiRoomSettingManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10674a = "XiaoAiRoomSettingManager";
    private static volatile XiaoAiRoomSettingManager b;
    private List<String> c = new ArrayList();
    /* access modifiers changed from: private */
    public Map<String, List<XiaoAiVoiceCategory>> d = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<String, List<XiaoAiRoomItem>> e = new ConcurrentHashMap();

    /* access modifiers changed from: private */
    public void b() {
    }

    private XiaoAiRoomSettingManager() {
    }

    public static XiaoAiRoomSettingManager a() {
        if (b == null) {
            synchronized (XiaoAiRoomSettingManager.class) {
                if (b == null) {
                    b = new XiaoAiRoomSettingManager();
                }
            }
        }
        return b;
    }

    public List<String> a(AsyncCallback<List<String>, Error> asyncCallback, boolean z) {
        if (asyncCallback == null) {
            return this.c;
        }
        if (!this.c.isEmpty()) {
            asyncCallback.onCache(this.c);
        }
        if (z) {
            a(asyncCallback);
        }
        return this.c;
    }

    public void a(final Room room, final AsyncCallback<List<XiaoAiRoomItem>, Error> asyncCallback) {
        if (room != null) {
            RemoteFamilyApi.a().g(room.d(), (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    XiaoAiRoomItem a2;
                    if (jSONObject == null) {
                        try {
                            if (asyncCallback != null) {
                                asyncCallback.onFailure(new Error(-1, "response is null or result is null"));
                                return;
                            }
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    JSONArray optJSONArray = jSONObject.optJSONArray("list");
                    if (optJSONArray != null) {
                        List list = (List) XiaoAiRoomSettingManager.this.e.get(XiaoAiRoomSettingManager.c(room));
                        if (list == null) {
                            list = new ArrayList();
                            XiaoAiRoomSettingManager.this.e.put(XiaoAiRoomSettingManager.c(room), list);
                        }
                        list.clear();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                            if (!(optJSONObject == null || (a2 = XiaoAiRoomItem.a(optJSONObject)) == null || TextUtils.isEmpty(a2.b()))) {
                                list.add(a2);
                            }
                        }
                        XiaoAiRoomSettingManager.this.b();
                        if (asyncCallback != null) {
                            asyncCallback.onSuccess(list);
                        }
                    } else if (asyncCallback != null) {
                        asyncCallback.onFailure(new Error(-1, "result cannot cast to JSONArray"));
                    }
                }

                public void onFailure(Error error) {
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(error);
                    }
                }
            });
        } else if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(-1, "room and did cannot be empty"));
        }
    }

    public void a(Room room, String str, AsyncCallback<List<XiaoAiVoiceCategory>, Error> asyncCallback) {
        if (room != null && !TextUtils.isEmpty(str)) {
            a(room.d(), str, asyncCallback);
            List list = this.d.get(c(room));
            if (asyncCallback != null && list != null) {
                asyncCallback.onCache(list);
            }
        } else if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(-1, "room and did cannot be empty"));
        }
    }

    public List<XiaoAiVoiceCategory> a(Room room) {
        if (room == null) {
            return null;
        }
        return this.d.get(c(room));
    }

    /* access modifiers changed from: private */
    public static String c(Room room) {
        if (room == null) {
            return "";
        }
        return room.f() + JSMethod.NOT_SET + room.d();
    }

    public void a(AsyncCallback<List<String>, Error> asyncCallback) {
        b(asyncCallback);
    }

    private void a(final String str, String str2, final AsyncCallback<List<XiaoAiVoiceCategory>, Error> asyncCallback) {
        RemoteFamilyApi.a().c(str, str2, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null) {
                    try {
                        if (asyncCallback != null) {
                            asyncCallback.onFailure(new Error(-1, "response is null or result is null"));
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("result");
                if (optJSONArray != null) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            arrayList.add(XiaoAiVoiceCategory.a(optJSONObject));
                        }
                    }
                    Room i2 = HomeManager.a().i(str);
                    if (i2 != null) {
                        XiaoAiRoomSettingManager.this.d.put(XiaoAiRoomSettingManager.c(i2), arrayList);
                    }
                    XiaoAiRoomSettingManager.this.b();
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(arrayList);
                    }
                } else if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(-1, "result cannot cast to JSONArray"));
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    private void b(AsyncCallback<List<String>, Error> asyncCallback) {
        List<Device> d2 = SmartHomeDeviceManager.a().d();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < d2.size(); i++) {
            Device device = d2.get(i);
            if (device != null && 2 == device.voiceCtrl) {
                arrayList.add(device.did);
            }
        }
        if (asyncCallback != null) {
            asyncCallback.onSuccess(arrayList);
        }
    }

    public XiaoAiVoiceCategory a(String str, String str2) {
        List list;
        if (this.d == null || this.d.isEmpty()) {
            return null;
        }
        try {
            Room i = HomeManager.a().i(str);
            if (!(i == null || (list = this.d.get(c(i))) == null)) {
                if (!list.isEmpty()) {
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        XiaoAiVoiceCategory xiaoAiVoiceCategory = (XiaoAiVoiceCategory) list.get(i2);
                        if (xiaoAiVoiceCategory != null) {
                            if (TextUtils.equals(str2, xiaoAiVoiceCategory.a())) {
                                return xiaoAiVoiceCategory;
                            }
                        }
                    }
                    return null;
                }
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void a(List<String> list, final XiaoAiRoomItem xiaoAiRoomItem, final AsyncCallback<Void, Error> asyncCallback) {
        RemoteFamilyApi.a().a(xiaoAiRoomItem.a(), xiaoAiRoomItem.b(), xiaoAiRoomItem.a(a(xiaoAiRoomItem.a(), xiaoAiRoomItem.b())), list, xiaoAiRoomItem.c(), (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                XiaoAiRoomSettingManager.this.a(HomeManager.a().i(xiaoAiRoomItem.a()), (AsyncCallback<List<XiaoAiRoomItem>, Error>) null);
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(null);
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }
}
