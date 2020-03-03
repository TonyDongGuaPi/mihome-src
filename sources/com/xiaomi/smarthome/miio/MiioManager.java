package com.xiaomi.smarthome.miio;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DevicelibApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.location.SHLocationManager;
import com.xiaomi.smarthome.framework.statistic.StatUtil;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MiioManager {

    /* renamed from: a  reason: collision with root package name */
    private static final int f11574a = 40;
    private static MiioManager c;
    /* access modifiers changed from: private */
    public Context b = CommonApplication.getAppContext();

    public static MiioManager a() {
        if (c == null) {
            c = new MiioManager();
        }
        return c;
    }

    private MiioManager() {
    }

    public Context b() {
        return this.b;
    }

    private InetAddress c() throws IOException {
        DhcpInfo dhcpInfo = ((WifiManager) this.b.getSystemService("wifi")).getDhcpInfo();
        int i = (dhcpInfo.netmask ^ -1) | (dhcpInfo.ipAddress & dhcpInfo.netmask);
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) ((i >> (i2 * 8)) & 255);
        }
        return InetAddress.getByAddress(bArr);
    }

    public void a(final Device device, final AsyncResponseCallback<Void> asyncResponseCallback) {
        DevicelibApi.bindDevice(CommonApplication.getAppContext(), device.did, device.token, device.pid, new AsyncCallback<Integer, Error>() {
            /* renamed from: a */
            public void onSuccess(Integer num) {
                if (num.intValue() == 1) {
                    device.setOwner(true);
                    device.userId = CoreApi.a().s();
                    asyncResponseCallback.a(null);
                    return;
                }
                asyncResponseCallback.a(num.intValue());
            }

            public void onFailure(Error error) {
                asyncResponseCallback.a(error.a());
            }
        });
    }

    public void a(final Device device, final AsyncCallback<Integer, Error> asyncCallback) {
        DevicelibApi.bindDevice(CommonApplication.getAppContext(), device.did, device.token, device.pid, new AsyncCallback<Integer, Error>() {
            /* renamed from: a */
            public void onSuccess(Integer num) {
                if (num.intValue() == 1) {
                    device.setOwner(true);
                    device.userId = CoreApi.a().s();
                }
                SmartHomeDeviceManager.a().b(device);
                asyncCallback.onSuccess(num);
            }

            public void onFailure(Error error) {
                asyncCallback.onFailure(error);
            }
        });
    }

    public void a(String str, int i, final String str2, final AsyncResponseCallback<Void> asyncResponseCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("pid", i);
            jSONObject.put("method", "remove_device");
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(str2);
            jSONObject.put("param", jSONArray);
            jSONObject.put("sid", str2);
            DevicelibApi.delSubDevice(CommonApplication.getAppContext(), jSONObject, new AsyncResponseCallback<Void>() {
                public void a(int i) {
                    asyncResponseCallback.a(i);
                }

                public void a(int i, Object obj) {
                    asyncResponseCallback.a(i);
                }

                public void a(Void voidR) {
                    Iterator<Device> it = SmartHomeDeviceManager.a().d().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Device next = it.next();
                        if (next.did.equalsIgnoreCase(str2)) {
                            SmartHomeDeviceManager.a().c(next);
                            break;
                        }
                    }
                    asyncResponseCallback.a(voidR);
                }
            });
        } catch (JSONException unused) {
            asyncResponseCallback.a(ErrorCode.INVALID.getCode());
        }
    }

    public void a(final String str, int i, final AsyncResponseCallback<Void> asyncResponseCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("pid", i);
            if (str.startsWith("yunyi.")) {
                MobclickAgent.a(this.b, StatUtil.b, "unbind");
            }
            DevicelibApi.delDevice(CommonApplication.getAppContext(), jSONObject, new AsyncResponseCallback<Void>() {
                public void a(int i) {
                    asyncResponseCallback.a(i);
                }

                public void a(int i, Object obj) {
                    asyncResponseCallback.a(i);
                }

                public void a(Void voidR) {
                    Iterator<Device> it = SmartHomeDeviceManager.a().d().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Device next = it.next();
                        if (next.did.equalsIgnoreCase(str)) {
                            String a2 = WifiUtil.a(CommonApplication.getAppContext());
                            if (next.location == Device.Location.REMOTE || TextUtils.isEmpty(a2)) {
                                SmartHomeDeviceManager.a().c(next);
                            } else {
                                next.setOwner(false);
                                next.userId = a2;
                                SmartHomeDeviceManager.a().b(next);
                            }
                        }
                    }
                    asyncResponseCallback.a(voidR);
                }
            });
        } catch (JSONException unused) {
            asyncResponseCallback.a(ErrorCode.INVALID.getCode());
        }
    }

    public void a(Device device, String str, AsyncCallback<Void, Error> asyncCallback) {
        if (device.did.startsWith("yunyi.")) {
            MobclickAgent.a(this.b, StatUtil.b, "modify_name");
        }
        String trim = str.trim();
        if (trim.length() > 40) {
            trim = trim.substring(0, 40);
        }
        CoreApi.a().a(device.did, trim, asyncCallback);
    }

    public void a(Device device) {
        if (ServerCompact.c(CoreApi.a().F())) {
            AsyncTaskUtils.a(new GetGpsInfoTask(), device);
        }
    }

    private class GetGpsInfoTask extends AsyncTask<Object, Device, Void> {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Void voidR) {
        }

        private GetGpsInfoTask() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Object... objArr) {
            MiioManager.this.b(objArr[0]);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void b(final Device device) {
        SHLocationManager.a().a((SHLocationManager.LocationCallback) new SHLocationManager.LocationCallback() {
            public void onSucceed(String str, final Location location) {
                CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
                    public void run() {
                        Location location = location;
                        if (location != null && device != null && device.did != null) {
                            Address address = null;
                            try {
                                List<Address> fromLocation = new Geocoder(MiioManager.this.b).getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                if (fromLocation != null && fromLocation.size() > 0) {
                                    address = fromLocation.get(0);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            String str = "";
                            String str2 = "";
                            String str3 = "";
                            String str4 = "";
                            String str5 = "";
                            if (address != null) {
                                str = address.getAdminArea();
                                str2 = address.getCountryCode();
                                str3 = address.getLocality();
                                str4 = address.getThoroughfare();
                                str5 = address.getSubLocality();
                            }
                            DevicelibApi.reportGPSInfo(CommonApplication.getAppContext(), device.did, location.getLongitude(), location.getLatitude(), str, str2, str3, str4, str5, (AsyncCallback<Void, Error>) null);
                        }
                    }
                });
            }
        });
    }
}
