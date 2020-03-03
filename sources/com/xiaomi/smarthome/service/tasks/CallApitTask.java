package com.xiaomi.smarthome.service.tasks;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.mi.global.shop.model.Tags;
import com.xiaomi.miot.DeviceDescInfo;
import com.xiaomi.miot.DeviceInfo;
import com.xiaomi.miot.service.ICallback;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bluetooth.XmBluetoothRecord;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CallApitTask implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22058a = "result";
    private static final String b = "error_msg";
    private final String c;
    /* access modifiers changed from: private */
    public final ICallback d;
    /* access modifiers changed from: private */
    public final boolean e;
    private String f;

    /* access modifiers changed from: private */
    public static /* synthetic */ JSONObject a(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    public CallApitTask(ICallback iCallback, boolean z, String str, String str2) {
        this.d = iCallback;
        this.c = str;
        this.f = str2;
        this.e = z;
    }

    public void run() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", this.f));
        CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b(this.c).b((List<KeyValuePair>) arrayList).a(), $$Lambda$CallApitTask$QCiipg3Y2OI6qR2DK9qDS0wiQcc.INSTANCE, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null) {
                    jSONObject = new JSONObject();
                }
                try {
                    ArrayList arrayList = new ArrayList();
                    CallApitTask.this.a(CallApitTask.this.e, arrayList, jSONObject.optJSONArray("description_list"), 2);
                    CallApitTask.this.a(CallApitTask.this.e, arrayList, jSONObject.optJSONArray("consumable_items"), 1);
                    Bundle bundle = new Bundle();
                    Log.i("MiuiService", "CallApitTask onSuccess:" + jSONObject);
                    bundle.putString(LoginTask.f22078a, CoreApi.a().s());
                    bundle.putParcelableArrayList("result", arrayList);
                    if (CallApitTask.this.d != null) {
                        CallApitTask.this.d.onSuccess(bundle);
                    }
                } catch (Throwable th) {
                    Log.e("MiuiService", Log.getStackTraceString(th));
                }
            }

            public void onFailure(Error error) {
                Log.i("MiuiService", "CallApitTask onfail");
                Bundle bundle = new Bundle();
                bundle.setClassLoader(DeviceInfo.class.getClassLoader());
                if (error != null) {
                    bundle.putInt("error_code", error.a());
                    bundle.putString(CallApitTask.b, error.b() + error.c());
                }
                try {
                    if (CallApitTask.this.d != null) {
                        CallApitTask.this.d.onFailure(bundle);
                    }
                } catch (RemoteException e) {
                    Log.e("MiuiService", Log.getStackTraceString(e));
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(boolean z, ArrayList<DeviceDescInfo> arrayList, JSONArray jSONArray, int i) {
        String str;
        String str2;
        if (jSONArray != null) {
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    Device b2 = SmartHomeDeviceManager.a().b(optJSONObject.optString("did"));
                    if (b2 == null || (z && DeviceCategory.fromPid(b2.pid) != DeviceCategory.Bluetooth && !b2.isOnline)) {
                        Log.e("MiuiService", "setItem device:" + b2 + " size:" + SmartHomeDeviceManager.a().f().size());
                    } else {
                        PluginRecord d2 = CoreApi.a().d(b2.model);
                        String str3 = "";
                        if (d2 != null) {
                            str3 = d2.t();
                        }
                        JSONArray optJSONArray = optJSONObject.optJSONArray(Tags.MiPhoneDetails.DETAILS);
                        if (!(optJSONArray == null || optJSONArray.length() == 0)) {
                            if (i == 2) {
                                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i3);
                                    DeviceDescInfo deviceDescInfo = new DeviceDescInfo();
                                    deviceDescInfo.c = i;
                                    deviceDescInfo.h = optJSONObject2.optString("description");
                                    deviceDescInfo.i = optJSONObject2.optString(XmBluetoothRecord.TYPE_PROP);
                                    deviceDescInfo.e = b2.name;
                                    deviceDescInfo.g = b2.model;
                                    deviceDescInfo.f = str3;
                                    deviceDescInfo.d = b2.did;
                                    deviceDescInfo.j = optJSONObject2.optLong("timestamp");
                                    arrayList.add(deviceDescInfo);
                                }
                            } else {
                                if (optJSONArray.length() == 1) {
                                    str = optJSONArray.optJSONObject(0).optString("description");
                                    str2 = optJSONArray.optJSONObject(0).optString(XmBluetoothRecord.TYPE_PROP);
                                } else {
                                    StringBuilder sb = new StringBuilder();
                                    StringBuilder sb2 = new StringBuilder();
                                    for (int i4 = 0; i4 < optJSONArray.length(); i4++) {
                                        JSONObject optJSONObject3 = optJSONArray.optJSONObject(i4);
                                        sb.append(optJSONObject3.optString("description"));
                                        sb.append(",");
                                        sb2.append(optJSONObject3.optString(XmBluetoothRecord.TYPE_PROP));
                                        sb2.append(",");
                                    }
                                    str = sb.deleteCharAt(sb.length() - 1).toString();
                                    str2 = sb2.deleteCharAt(sb2.length() - 1).toString();
                                }
                                DeviceDescInfo deviceDescInfo2 = new DeviceDescInfo();
                                deviceDescInfo2.c = i;
                                deviceDescInfo2.h = str;
                                deviceDescInfo2.i = str2;
                                deviceDescInfo2.e = b2.name;
                                deviceDescInfo2.g = b2.model;
                                deviceDescInfo2.f = str3;
                                deviceDescInfo2.d = b2.did;
                                arrayList.add(deviceDescInfo2);
                            }
                        }
                    }
                }
            }
        }
    }
}
