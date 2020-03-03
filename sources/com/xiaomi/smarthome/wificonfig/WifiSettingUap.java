package com.xiaomi.smarthome.wificonfig;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import com.miui.whetstone.WhetstoneResult;
import com.miui.whetstone.WhetstoneResultBinder;
import com.xiaomi.miio.MiioLocalAPI;
import com.xiaomi.miio.MiioLocalRpcResponse;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.KuailianManager;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.framework.api.RouterRemoteApi;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.wifiaccount.WifiAccountManager;
import com.xiaomi.smarthome.library.common.ApiHelper;
import com.xiaomi.smarthome.miio.WifiAccount;
import com.xiaomi.smarthome.wificonfig.BaseWifiSetting;
import com.xiaomi.smarthome.wificonfig.WifiSettingUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class WifiSettingUap extends BaseWifiSetting {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22954a = "WifiSettingUap";
    private static final String h = "NOTCONNECTED";
    private static final String i = "CONNECTING";
    private static final String j = "CONNECTED";
    private static final String k = "ONLINE";
    private int b;
    /* access modifiers changed from: private */
    public long c;
    /* access modifiers changed from: private */
    public String d;
    private int e;
    private int f;
    private int g;
    private String l;
    private String m;
    private String n;
    private ScanResult o;
    private int p;
    private ConnectivityManager.NetworkCallback q;

    public void startConnection() {
        PluginRecord d2;
        ScanResult scanResult;
        if (this.mRouterInfo == null) {
            if (this.mChooseUnconnWifi) {
                scanResult = ((WifiSettingUtils.KuailianScanResult) this.mUnconnectResult.get(this.mPosition)).f22964a;
            } else {
                scanResult = ((WifiSettingUtils.KuailianScanResult) this.mScanResult.get(this.mPosition)).f22964a;
            }
            this.o = scanResult;
        }
        WifiInfo connectionInfo = ((WifiManager) getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo != null) {
            this.m = connectionInfo.getSSID();
            this.n = connectionInfo.getBSSID();
            if (this.mScanResult != null) {
                for (WifiSettingUtils.KuailianScanResult kuailianScanResult : this.mScanResult) {
                    if (WifiSettingUtils.a(this.m, kuailianScanResult.f22964a.SSID) && !TextUtils.isEmpty(kuailianScanResult.f22964a.BSSID)) {
                        this.n = kuailianScanResult.f22964a.BSSID;
                    }
                }
            }
            this.mStartTime = System.currentTimeMillis();
            String i2 = DeviceFactory.i(this.mKuailianScanResult.SSID);
            String str = "";
            if (!(i2 == null || (d2 = CoreApi.a().d(i2)) == null || TextUtils.isEmpty(d2.c().u()))) {
                str = d2.c().u();
            }
            ScanResult scanResult2 = this.mKuailianScanResult;
            if (DeviceFactory.d(this.mKuailianScanResult) == DeviceFactory.AP_TYPE.AP_MIDEA || DeviceFactory.d(this.mKuailianScanResult) == DeviceFactory.AP_TYPE.AP_MIDEA_AC) {
                str = "12345678";
            }
            connectToWifi(scanResult2, str);
            this.l = h;
            this.mStartTime = System.currentTimeMillis();
            this.p = 0;
        }
    }

    public void onHandleMessage(Message message) {
        PluginRecord d2;
        int i2 = message.what;
        if (i2 != 101) {
            if (i2 != 112) {
                switch (i2) {
                    case 114:
                        startConnectionAsso();
                        return;
                    case 115:
                        onStopConnection();
                        stopConnection();
                        return;
                    default:
                        return;
                }
            } else {
                this.l = j;
                this.mHandler.removeMessages(112);
                this.mHandler.removeMessages(114);
                onStopConnection();
                onDeviceConnSuccess();
            }
        } else if (this.mState != BaseWifiSetting.State.DEVICE_CONNECTING) {
            BaseWifiSetting.State state = this.mState;
            BaseWifiSetting.State state2 = BaseWifiSetting.State.DEVICE_SEARCHING;
        } else if (WifiSettingUtils.a(this.mWifiManager.getConnectionInfo().getSSID(), this.mKuailianScanResult.SSID)) {
            startConnectionAsso();
        } else {
            String str = "";
            String i3 = DeviceFactory.i(this.mKuailianScanResult.SSID);
            if (!(i3 == null || (d2 = CoreApi.a().d(i3)) == null || TextUtils.isEmpty(d2.c().u()))) {
                str = d2.c().u();
            }
            ScanResult scanResult = this.mKuailianScanResult;
            if (DeviceFactory.d(this.mKuailianScanResult) == DeviceFactory.AP_TYPE.AP_MIDEA || DeviceFactory.d(this.mKuailianScanResult) == DeviceFactory.AP_TYPE.AP_MIDEA_AC) {
                str = "12345678";
            }
            connectToWifi(scanResult, str);
            this.mHandler.removeMessages(103);
        }
    }

    public String long2Ip(long j2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf((int) (j2 & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j2 >> 8) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j2 >> 16) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j2 >> 24) & 255)));
        return stringBuffer.toString();
    }

    public String getGatewayAddr() {
        return long2Ip((long) ((WifiManager) getSystemService("wifi")).getDhcpInfo().gateway);
    }

    public void startConnectionAsso() {
        if (this.mState == BaseWifiSetting.State.DEVICE_CONNECTING) {
            WifiDeviceFinder.m.clear();
            this.b = (int) (System.currentTimeMillis() / 1000);
            this.mState = BaseWifiSetting.State.DEVICE_SEARCHING;
            Network network = null;
            if (this.o == null) {
                Iterator<RouterRemoteApi.WifiInfo> it = this.mRouterInfo.f16425a.iterator();
                RouterRemoteApi.WifiInfo wifiInfo = null;
                while (it.hasNext()) {
                    RouterRemoteApi.WifiInfo next = it.next();
                    if (next.f16426a > 0 && next.f16426a < 20) {
                        wifiInfo = next;
                    }
                }
                String str = wifiInfo.c;
            } else {
                String str2 = this.o.SSID;
            }
            if (ApiHelper.e) {
                Network[] allNetworks = this.mConnectivityManager.getAllNetworks();
                int i2 = 0;
                while (true) {
                    if (i2 < allNetworks.length) {
                        NetworkInfo networkInfo = this.mConnectivityManager.getNetworkInfo(allNetworks[i2]);
                        if (networkInfo != null && networkInfo.getType() == 1) {
                            network = allNetworks[i2];
                            break;
                        }
                        i2++;
                    } else {
                        break;
                    }
                }
                if (network == null) {
                    LogUtilGrey.a("ERROR", "Get Network ERROR");
                }
                this.mConnectivityManager.bindProcessToNetwork(network);
            }
            WifiAccount.WifiItem a2 = WifiAccountManager.a().a(this.n);
            if (a2 == null && !this.mIsMiui) {
                LogUtilGrey.a(f22954a, "item == null");
            } else if (a2 == null) {
                setMiioRouter(this.o.SSID, "");
            } else {
                setMiioRouter(a2.c, a2.d);
            }
        }
    }

    public void setMiioRouter(String str, String str2) {
        String str3;
        this.p++;
        if (this.p > 2) {
            this.mHandler.sendEmptyMessage(112);
            return;
        }
        final JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.b);
            jSONObject.put("method", "miIO.config_router");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(DeviceTagInterface.e, str);
            if (!useMiuiKuailian()) {
                if (str2 == null) {
                    str2 = "";
                }
                jSONObject2.put("passwd", str2);
            }
            jSONObject2.put("uid", Long.valueOf(CoreApi.a().s()));
            if (CoreApi.a().D()) {
                ServerBean F = CoreApi.a().F();
                if (F == null) {
                    str3 = "";
                } else {
                    str3 = F.f1530a;
                }
                jSONObject2.put("country_domain", str3);
            }
            jSONObject.put("params", jSONObject2);
        } catch (JSONException unused) {
        }
        LogUtilGrey.a(f22954a, "start Send");
        MiioLocalAPI.a(getGatewayAddr(), (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
            public void onResponse(final String str) {
                WifiSettingUap.this.mHandler.post(new Runnable() {
                    public void run() {
                        JSONObject parseRpcResponse = WifiSettingUap.this.parseRpcResponse(str);
                        if (parseRpcResponse == null) {
                            if (WifiSettingUap.this.mState == BaseWifiSetting.State.DEVICE_SEARCHING) {
                                WifiSettingUap.this.mState = BaseWifiSetting.State.DEVICE_CONNECTING;
                                WifiSettingUap.this.mHandler.sendEmptyMessageDelayed(114, 1000);
                            } else {
                                return;
                            }
                        }
                        if (WifiSettingUap.this.mState == BaseWifiSetting.State.DEVICE_SEARCHING) {
                            long unused = WifiSettingUap.this.c = Long.valueOf(parseRpcResponse.optString("did")).longValue();
                            String unused2 = WifiSettingUap.this.d = parseRpcResponse.optString("token");
                            KuailianManager.a().a(String.valueOf(WifiSettingUap.this.c), WifiSettingUap.this.d);
                            LogUtilGrey.a(WifiSettingUap.f22954a, "async_get_token:" + parseRpcResponse);
                            if (WifiSettingUap.this.useMiuiKuailian()) {
                                try {
                                    WifiSettingUap.this.mWhetstoneClass.getDeclaredMethod("wifiSmartConfigUapAsync", new Class[]{String.class, String.class, Long.TYPE, String.class, IBinder.class}).invoke((Object) null, new Object[]{WifiSettingUap.this.getGatewayAddr(), jSONObject.toString(), Long.valueOf(WifiSettingUap.this.c), WifiSettingUap.this.d, new WhetstoneResultBinder() {
                                        public void onResult(WhetstoneResult whetstoneResult) {
                                            if (WifiSettingUap.this.mState == BaseWifiSetting.State.DEVICE_SEARCHING) {
                                                WifiSettingUap.this.mHandler.sendEmptyMessageDelayed(112, 200);
                                                LogUtilGrey.a(WifiSettingUap.f22954a, "Send Success");
                                            }
                                        }
                                    }});
                                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused3) {
                                }
                            } else {
                                MiioLocalAPI.a(WifiSettingUap.this.getGatewayAddr(), jSONObject.toString(), WifiSettingUap.this.c, WifiSettingUap.this.d, (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
                                    public void onResponse(String str) {
                                        if (WifiSettingUap.this.parseRpcResponse(str) == null) {
                                            if (WifiSettingUap.this.mState == BaseWifiSetting.State.DEVICE_SEARCHING) {
                                                WifiSettingUap.this.mState = BaseWifiSetting.State.DEVICE_CONNECTING;
                                                WifiSettingUap.this.mHandler.sendEmptyMessageDelayed(114, 1000);
                                            } else {
                                                return;
                                            }
                                        }
                                        if (WifiSettingUap.this.mState == BaseWifiSetting.State.DEVICE_SEARCHING) {
                                            WifiSettingUap.this.mHandler.sendEmptyMessageDelayed(112, 1000);
                                        }
                                    }
                                });
                            }
                        }
                    }
                });
            }
        }, 9);
    }

    public void onDeviceConnSuccess() {
        this.mHandler.post(new Runnable() {
            public void run() {
                KuailianManager.a().a(WifiSettingUap.this, true, WifiSettingUap.this.mKuailianScanResult, WifiSettingUap.this.c == 0 ? null : String.valueOf(WifiSettingUap.this.c));
            }
        });
    }

    public JSONObject parseRpcResponse(String str) {
        JSONArray optJSONArray;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (AnonymousClass4.f22960a[ErrorCode.valueof(jSONObject.optInt("code")).ordinal()] != 1) {
                return null;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("result");
            if (optJSONObject != null || (optJSONArray = jSONObject.optJSONArray("result")) == null) {
                return optJSONObject;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("result", optJSONArray);
            return jSONObject2;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* renamed from: com.xiaomi.smarthome.wificonfig.WifiSettingUap$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f22960a = new int[ErrorCode.values().length];

        static {
            try {
                f22960a[ErrorCode.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public void onStopConnection() {
        this.mHandler.removeMessages(112);
        this.mHandler.removeMessages(114);
        RouterRemoteApi.WifiInfo wifiInfo = null;
        if (ApiHelper.e) {
            LogUtilGrey.a(f22954a, "Bind Network to NULL");
            this.mConnectivityManager.bindProcessToNetwork((Network) null);
        }
        if (this.o != null) {
            connectToWifi(this.o, "");
        } else if (this.mRouterInfo != null) {
            Iterator<RouterRemoteApi.WifiInfo> it = this.mRouterInfo.f16425a.iterator();
            while (it.hasNext()) {
                RouterRemoteApi.WifiInfo next = it.next();
                if (next.f16426a > 0 && next.f16426a < 20) {
                    wifiInfo = next;
                }
            }
            connectXiaomiRouter(wifiInfo.c, wifiInfo.e, wifiInfo.g, false);
        }
    }

    public void stopConnection() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.b);
            jSONObject.put("method", "miIO.stop_diag_mode");
            jSONObject.put("params", "");
        } catch (JSONException unused) {
        }
        MiioLocalAPI.a(getGatewayAddr(), jSONObject.toString(), this.c, this.d, (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
            public void onResponse(String str) {
            }
        });
    }

    public String getConnStatusText() {
        if (this.l.equals(h)) {
            return getString(R.string.kuailian_uap_state_1);
        }
        if (this.l.equals(i)) {
            return getString(R.string.kuailian_uap_state_2);
        }
        return getString(R.string.kuailian_uap_state_3);
    }
}
