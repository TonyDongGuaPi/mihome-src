package com.xiaomi.smarthome.device;

import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.ServiceApplication;
import com.xiaomi.smarthome.device.DeviceSearch;
import com.xiaomi.smarthome.device.api.DevicelibApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MiTVMiWIFIDeviceSearch extends MiioDeviceSearchBase {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14893a = 2;

    public int g() {
        return 2;
    }

    /* access modifiers changed from: protected */
    public void a(Collection<? extends Device> collection, DeviceSearch.REMOTESTATE remotestate) {
        if (remotestate == DeviceSearch.REMOTESTATE.REMOTE_SUCCESS) {
            ArrayList arrayList = new ArrayList();
            ArrayList<MiioDeviceV2> arrayList2 = new ArrayList<>();
            for (Device device : collection) {
                if (device instanceof MiTVDevice) {
                    if (device.isOwner()) {
                        device.userId = CoreApi.a().s();
                        arrayList.add((MiioDeviceV2) device);
                    } else {
                        arrayList2.add((MiioDeviceV2) device);
                    }
                    ((MiTVDevice) device).m = false;
                } else if (device instanceof MiioDeviceV2) {
                    if (device.isBinded()) {
                        device.userId = CoreApi.a().s();
                        arrayList.add((MiioDeviceV2) device);
                    } else {
                        arrayList2.add((MiioDeviceV2) device);
                    }
                }
            }
            MitvDeviceManager.b().a();
            for (MiioDeviceV2 miioDeviceV2 : arrayList2) {
                if (!(miioDeviceV2 instanceof MiTVDevice)) {
                    arrayList.add(miioDeviceV2);
                } else if (!MitvDeviceManager.b().a(miioDeviceV2.did)) {
                    arrayList.add(miioDeviceV2);
                } else {
                    MitvDeviceManager.b().a((MiTVDevice) miioDeviceV2);
                }
            }
            a((List<MiioDeviceV2>) arrayList);
        } else if (remotestate == DeviceSearch.REMOTESTATE.REMOTE_FAILED) {
            a((List<MiioDeviceV2>) null);
        } else if (remotestate == DeviceSearch.REMOTESTATE.REMOTE_NOT_LOGIN) {
            a((List<MiioDeviceV2>) null);
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        ServiceApplication.getThreadExecutor().submit(new Runnable() {
            public void run() {
                if (WifiUtil.a(CommonApplication.getAppContext()) != null) {
                    int i = 0;
                    while (i < MiTVMiWIFIDeviceSearch.this.b.size()) {
                        try {
                            MiioDeviceV2 miioDeviceV2 = (MiioDeviceV2) MiTVMiWIFIDeviceSearch.this.b.get(i);
                            if (miioDeviceV2 instanceof MiTVDevice) {
                                ((MiTVDevice) miioDeviceV2).b();
                            }
                            i++;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            }
        });
    }

    private void i() {
        if (!SmartHomeDeviceHelper.a().h()) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (i < this.b.size()) {
                try {
                    MiioDeviceV2 miioDeviceV2 = (MiioDeviceV2) this.b.get(i);
                    if (miioDeviceV2 instanceof RouterDevice) {
                        arrayList.add(miioDeviceV2.did);
                    }
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            if (arrayList.size() > 0) {
                DevicelibApi.getDeviceExtraInfo(CommonApplication.getAppContext(), arrayList, new AsyncCallback<JSONObject, Error>() {
                    public void onFailure(Error error) {
                    }

                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        try {
                            JSONArray jSONArray = jSONObject.getJSONArray("result");
                            for (int i = 0; i < jSONArray.length(); i++) {
                                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                                Device b = SmartHomeDeviceManager.a().b(jSONObject2.getString("did"));
                                if (b != null && (b instanceof RouterDevice)) {
                                    JSONObject jSONObject3 = jSONObject2.getJSONObject("value");
                                    ((RouterDevice) b).i = jSONObject3.getInt("online_device_count");
                                    ((RouterDevice) b).h = jSONObject3.getInt("downloading_count");
                                    ((RouterDevice) b).name = jSONObject3.getString("router_name");
                                    ((RouterDevice) b).e = jSONObject3.optInt("wanRX");
                                    b.notifyStateChanged();
                                    SmartHomeDeviceManager.a().a(b);
                                }
                            }
                        } catch (JSONException unused) {
                        }
                    }
                });
            }
        }
    }
}
