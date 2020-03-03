package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.miio.MiioLocalAPI;
import com.xiaomi.miio.MiioLocalRpcResponse;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.CameraApi;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.bluetooth.ui.CommonBindView;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.statistic.MiStatType;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.ApiHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.ConfigStep;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.wificonfig.WifiDeviceFinder;
import com.xiaomi.smarthome.wificonfig.WifiSettingUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BindKeyStep extends ConfigStep {
    private static final int au = 263;
    private static final int b = 0;
    private static final int c = 50000;
    private static final int d = 1;
    private static final int e = 50000;
    private static final int f = 2;
    private static final int g = 30000;

    /* renamed from: a  reason: collision with root package name */
    boolean f22455a = false;
    private int ap;
    /* access modifiers changed from: private */
    public String aq;
    private int ar = 0;
    private int as = 0;
    /* access modifiers changed from: private */
    public int at;
    /* access modifiers changed from: private */
    public long av;
    private long aw;
    private volatile boolean ax;
    private Handler ay;
    private MiioLocalAPI.Cancelable az;
    /* access modifiers changed from: private */
    public ScanResult h;
    private ArrayList<ConfigStep.ConfigTime> i = new ArrayList<>();
    /* access modifiers changed from: private */
    public int j;
    /* access modifiers changed from: private */
    public long k;
    /* access modifiers changed from: private */
    public String l;

    public ArrayList<ConfigStep.ConfigTime> O_() {
        this.i.clear();
        ConfigStep.ConfigTime configTime = new ConfigStep.ConfigTime();
        configTime.f22586a = 0;
        configTime.b = 50000;
        this.i.add(configTime);
        ConfigStep.ConfigTime configTime2 = new ConfigStep.ConfigTime();
        configTime2.f22586a = 1;
        configTime2.b = 50000;
        this.i.add(configTime2);
        ConfigStep.ConfigTime configTime3 = new ConfigStep.ConfigTime();
        configTime3.f22586a = 2;
        configTime3.b = 30000;
        this.i.add(configTime3);
        return this.i;
    }

    public SmartConfigStep.Step b(int i2) {
        switch (i2) {
            case 0:
                BluetoothLog.c(String.format("onStageTimeOut CONNECT_TO_AP_INDEX", new Object[0]));
                MobclickAgent.a(this.af, MiStatType.CLICK.getValue(), "connect_ap_connect_timeout");
                StatHelper.d("connect_ap_connect_timeout");
                U_().removeMessages(123);
                U_().removeMessages(263);
                H();
                a(true, (int) R.string.switch_router_manually, SmartConfigStep.Step.STEP_CONNECT_AP_ERROR);
                return null;
            case 1:
                BluetoothLog.c(String.format("onStageTimeOut SEND_SSID_AND_PASSWD_INDEX", new Object[0]));
                this.f22455a = true;
                return null;
            case 2:
                BluetoothLog.c(String.format("onStageTimeOut GET_NEW_DEVICE_INDEX", new Object[0]));
                MobclickAgent.a(this.af, MiStatType.CLICK.getValue(), "connect_get_device_timeout");
                StatHelper.d("connect_get_device_timeout");
                DeviceFinder.a().b();
                H();
                if (U_() != null) {
                    U_().removeMessages(122);
                }
                PluginRecord d2 = CoreApi.a().d(DeviceFactory.a(this.h));
                if (d2 != null) {
                    Log.e("AoConfigStep", "" + d2.c().C());
                }
                if (d2 == null || d2.c().C() == 0) {
                    a(true, 0, (SmartConfigStep.Step) null);
                    return null;
                }
                a(true, (int) R.string.connect_fail_tips, SmartConfigStep.Step.STEP_FIND_DEVICE_FAILED_ERROR);
                return null;
            default:
                return null;
        }
    }

    public Handler U_() {
        Handler U_ = super.U_();
        return U_ != null ? U_ : this.ay;
    }

    public void k() {
        this.j = 0;
    }

    public int b() {
        BluetoothLog.c(String.format("getCurrentStageIndex bindKey = %s", new Object[]{this.aq}));
        if (TextUtils.isEmpty(this.aq)) {
            return 0;
        }
        return this.j;
    }

    public SmartConfigStep.Step f() {
        BluetoothLog.c(String.format("getStep called", new Object[0]));
        return SmartConfigStep.Step.STEP_AP_CONFIG_STEP;
    }

    public void a(Message message) {
        switch (message.what) {
            case 101:
                BluetoothLog.c(String.format("handleMessage NETWORK_STATE_CHANGED", new Object[0]));
                NetworkInfo networkInfo = (NetworkInfo) message.obj;
                NetworkInfo.DetailedState detailedState = networkInfo.getDetailedState();
                WifiInfo connectionInfo = this.w.getConnectionInfo();
                if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID()) && !connectionInfo.getSSID().contains("<unknown ssid>")) {
                    BluetoothLog.c(String.format(">>> wifiInfo = %s, state = %s", new Object[]{connectionInfo, detailedState}));
                    if (detailedState == NetworkInfo.DetailedState.CONNECTED && networkInfo.isConnected() && WifiSettingUtils.a(connectionInfo.getSSID(), ((ScanResult) SmartConfigDataProvider.a().a(SmartConfigDataProvider.h)).SSID) && this.j == 0 && !this.ah) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(detailedState.toString());
                        sb.append(", ");
                        sb.append(connectionInfo.getSSID() != null ? connectionInfo.getSSID() : "");
                        sb.append(", ");
                        sb.append(networkInfo.getExtraInfo() != null ? networkInfo.getExtraInfo() : "");
                        sb.append(", ");
                        sb.append(networkInfo.getReason() != null ? networkInfo.getReason() : "");
                        Log.e("WifiStateConfig", sb.toString());
                        U_().removeMessages(123);
                        if (!this.ax) {
                            t();
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            case 112:
                BluetoothLog.c(String.format("handleMessage MSG_UPDATE_DEVICE_STATE", new Object[0]));
                if (U_() != null) {
                    U_().removeMessages(112);
                    U_().removeMessages(114);
                }
                if (this.j == 1) {
                    a(1);
                    this.j = 2;
                    r();
                    return;
                }
                return;
            case 114:
                BluetoothLog.c(String.format("handleMessage MSG_SEND_DEVICE_MSG", new Object[0]));
                if (this.f22455a) {
                    H();
                    PluginRecord d2 = CoreApi.a().d(DeviceFactory.a(this.h));
                    if (d2 == null || d2.c().C() == 0) {
                        a(false, 0, (SmartConfigStep.Step) null);
                        this.f22455a = false;
                        return;
                    }
                    a(true, (int) R.string.connect_fail_tips, SmartConfigStep.Step.STEP_SEND_ROUTER_INFO_FINAL_ERROR);
                    return;
                }
                p();
                return;
            case 115:
                BluetoothLog.c(String.format("handleMessage MSG_DISCONNECT_TIME_OUT", new Object[0]));
                r();
                return;
            case 122:
                BluetoothLog.c(String.format("handleMessage MSG_UPDATE_CHECKOUT", new Object[0]));
                s();
                return;
            case 123:
                BluetoothLog.c(String.format("handleMessage MSG_RECONNECT_DEVICE_AP", new Object[0]));
                o();
                return;
            case 263:
                this.at++;
                E();
                return;
            default:
                super.a(message);
                return;
        }
    }

    public void a(Context context) {
        super.a(context);
        this.ay = new Handler(Looper.getMainLooper());
    }

    public String a(long j2) {
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

    public String n() {
        return a((long) ((WifiManager) this.af.getSystemService("wifi")).getDhcpInfo().gateway);
    }

    public void c(int i2) {
        this.h = (ScanResult) SmartConfigDataProvider.a().a(SmartConfigDataProvider.h);
        switch (i2) {
            case 0:
                BluetoothLog.c(String.format("startConnection CONNECT_TO_AP_INDEX", new Object[0]));
                o();
                return;
            case 1:
                BluetoothLog.c(String.format("startConnection SEND_SSID_AND_PASSWD_INDEX", new Object[0]));
                this.av = System.currentTimeMillis();
                p();
                return;
            case 2:
                BluetoothLog.c(String.format("startConnection GET_NEW_DEVICE_INDEX", new Object[0]));
                r();
                return;
            default:
                return;
        }
    }

    public void o() {
        BluetoothLog.c(String.format("startConnectToDeviceAp", new Object[0]));
        this.j = 0;
        this.ax = false;
        this.at = 0;
        String c2 = WifiUtil.c(this.af);
        WifiInfo connectionInfo = this.w.getConnectionInfo();
        if (TextUtils.isEmpty(c2) || !c2.equals(this.h.SSID) || connectionInfo == null || connectionInfo.getSupplicantState() != SupplicantState.COMPLETED) {
            WifiSettingUtils.a(this.w, this.h.SSID, "", this.h.BSSID, this.h.capabilities);
            U_().sendEmptyMessageDelayed(123, 15000);
            return;
        }
        BluetoothLog.c(String.format(">>>@ info = %s, ssid = %s", new Object[]{connectionInfo, c2}));
        t();
    }

    private void t() {
        BluetoothLog.c(String.format("onConnectToApSuccess", new Object[0]));
        this.ax = true;
        if (U_() != null) {
            U_().postDelayed(new Runnable() {
                public void run() {
                    if (BindKeyStep.this.j != 1) {
                        BindKeyStep.this.E();
                    }
                }
            }, 1000);
        }
    }

    /* access modifiers changed from: private */
    public void E() {
        BluetoothLog.c(String.format("getBindKey, retrys = %d", new Object[]{Integer.valueOf(this.at)}));
        CameraApi.getInstance().getBindKey(this.af, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                BluetoothLog.c(String.format("onSuccess " + str, new Object[0]));
                int unused = BindKeyStep.this.at = 0;
                BindKeyStep.this.a(BindKeyStep.this.j);
                int unused2 = BindKeyStep.this.j = 1;
                String unused3 = BindKeyStep.this.aq = str;
                BindKeyStep.this.c(BindKeyStep.this.j);
            }

            public void onFailure(Error error) {
                BluetoothLog.c(String.format("onFailure " + error, new Object[0]));
                if (BindKeyStep.this.at < 3) {
                    BindKeyStep.this.U_().sendEmptyMessageDelayed(263, 5000);
                } else {
                    BindKeyStep.this.u();
                }
            }
        });
    }

    public void p() {
        int i2 = 0;
        BluetoothLog.c(String.format("startConnectionAsso ", new Object[0]));
        WifiDeviceFinder.m.clear();
        this.ap = (int) (System.currentTimeMillis() / 1000);
        this.j = 1;
        if (ApiHelper.e) {
            Network network = null;
            Network[] allNetworks = this.x.getAllNetworks();
            while (true) {
                if (i2 < allNetworks.length) {
                    NetworkInfo networkInfo = this.x.getNetworkInfo(allNetworks[i2]);
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
                Log.e("ERROR", "Get Network ERROR");
            }
            this.x.bindProcessToNetwork(network);
        }
        q();
        this.j = 1;
    }

    /* access modifiers changed from: private */
    public void F() {
        String str = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.d);
        String d2 = SmartConfigDataProvider.a().d();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.ap);
            jSONObject.put("method", "set_bind_key");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("bind_key", this.aq);
            jSONObject2.put(DeviceTagInterface.e, str);
            jSONObject2.put("pswd", d2);
            jSONObject.put("params", jSONObject2);
        } catch (JSONException unused) {
        }
        MiioLocalAPI.a(n(), jSONObject.toString(), this.k, this.l, (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
            public void onResponse(String str) {
                BluetoothLog.c(String.format("async_rpc onResponse: %s", new Object[]{str}));
                BluetoothLog.c(String.format(">>> obj = %s", new Object[]{BindKeyStep.this.b(str)}));
                if (BindKeyStep.this.U_() != null) {
                    long currentTimeMillis = (50000 - (System.currentTimeMillis() - BindKeyStep.this.av)) - 1000;
                    if (currentTimeMillis < 0) {
                        currentTimeMillis = 0;
                    }
                    BluetoothLog.c(String.format("Delay %ds", new Object[]{Long.valueOf(currentTimeMillis / 1000)}));
                    BindKeyStep.this.U_().removeMessages(114);
                    BindKeyStep.this.U_().sendEmptyMessageDelayed(112, currentTimeMillis);
                }
            }
        });
    }

    public void q() {
        if (!CoreApi.a().q()) {
            d_(true);
        } else if (!TextUtils.isEmpty(this.l)) {
            F();
        } else {
            if (this.az != null) {
                this.az.a();
            }
            this.az = MiioLocalAPI.a(n(), (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
                public void onResponse(final String str) {
                    BluetoothLog.c(String.format("async_get_token onResponse: %s", new Object[]{str}));
                    if (BindKeyStep.this.U_() != null) {
                        BindKeyStep.this.U_().post(new Runnable() {
                            public void run() {
                                JSONObject b2 = BindKeyStep.this.b(str);
                                BluetoothLog.c(String.format(">>> obj = %s", new Object[]{b2}));
                                if (b2 != null) {
                                    long unused = BindKeyStep.this.k = Long.valueOf(b2.optString("did")).longValue();
                                    String unused2 = BindKeyStep.this.l = b2.optString("token");
                                    DeviceFinder.a().a(String.valueOf(BindKeyStep.this.k), BindKeyStep.this.l);
                                    BindKeyStep.this.F();
                                } else if (BindKeyStep.this.U_() != null) {
                                    BindKeyStep.this.U_().sendEmptyMessageDelayed(114, 1000);
                                }
                            }
                        });
                    }
                }
            }, 5);
        }
    }

    public void e() {
        super.e();
        if (this.az != null) {
            this.az.a();
        }
        this.ay.removeCallbacksAndMessages((Object) null);
    }

    public void r() {
        BluetoothLog.c(String.format("onStopConnection", new Object[0]));
        U_().removeMessages(112);
        U_().removeMessages(114);
        if (ApiHelper.e) {
            Log.e("WifiSettingUap", "Bind Network to NULL");
            this.x.bindProcessToNetwork((Network) null);
        }
        this.aw = System.currentTimeMillis();
        s();
    }

    /* access modifiers changed from: package-private */
    public void s() {
        BluetoothLog.c(String.format("checkBindKey (%s)", new Object[]{this.aq}));
        CameraApi.getInstance().checkBindKey(this.af, this.aq, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                BluetoothLog.c(String.format("checkBindKey onSuccess " + jSONObject, new Object[0]));
                int optInt = jSONObject.optInt("ret");
                if (optInt == 0) {
                    int optInt2 = jSONObject.optInt("check_after");
                    if (optInt2 > 0) {
                        BindKeyStep.this.U_().removeMessages(122);
                        BindKeyStep.this.U_().sendEmptyMessageDelayed(122, (long) (optInt2 * 1000));
                        return;
                    }
                    BindKeyStep.this.U_().removeMessages(122);
                    BindKeyStep.this.U_().sendEmptyMessageDelayed(122, 2000);
                } else if (optInt == 1) {
                    BindKeyStep.this.a(jSONObject.optString("bind_did"));
                } else if (optInt == -2 || optInt == -3) {
                    String unused = BindKeyStep.this.aq = "";
                }
            }

            public void onFailure(Error error) {
                BluetoothLog.c(String.format("checkBindKey onFailure " + error, new Object[0]));
                if (BindKeyStep.this.U_() != null) {
                    BindKeyStep.this.U_().removeMessages(122);
                    BindKeyStep.this.U_().sendEmptyMessageDelayed(122, 2000);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void G() {
        System.currentTimeMillis();
        long j2 = this.aw;
        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
            public void run() {
                BluetoothLog.c(String.format("onGetDeviceInfoOver", new Object[0]));
                if (WifiDeviceFinder.d().e() == null) {
                    WifiDeviceFinder.d().a(SHApplication.getAppContext());
                    WifiDeviceFinder.d().a();
                }
                WifiDeviceFinder.a(BindKeyStep.this.h);
                BindKeyStep.this.a(BindKeyStep.this.j);
            }
        }, 0);
    }

    /* access modifiers changed from: package-private */
    public void a(final String str) {
        BluetoothLog.c(String.format("getDeviceInfo did = %s", new Object[]{str}));
        DeviceApi.getInstance().getDeviceDetail(SHApplication.getAppContext(), new String[]{str}, new AsyncCallback<List<Device>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<Device> list) {
                if (list.size() > 0) {
                    Device b2 = SmartHomeDeviceManager.a().b(str);
                    if (b2 != null) {
                        SmartHomeDeviceManager.a().c(b2);
                    }
                    for (Device next : list) {
                        if (str.equals(next.did)) {
                            b2 = next;
                        }
                    }
                    if (b2 != null) {
                        BluetoothLog.c(String.format("onSuccess did = %s, mac = %s, name = %s", new Object[]{b2.did, b2.mac, b2.name}));
                    }
                    SmartHomeDeviceManager.a().b(b2);
                    DeviceFinder.a().c(str);
                    SmartConfigDataProvider.a().b(SmartConfigDataProvider.w, b2);
                    BindKeyStep.this.G();
                }
            }

            public void onFailure(Error error) {
                BluetoothLog.b(String.format("onFailure %s", new Object[]{error}));
                BindKeyStep.this.G();
            }
        });
    }

    public JSONObject b(String str) {
        JSONArray optJSONArray;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (AnonymousClass9.f22465a[ErrorCode.valueof(jSONObject.optInt("code")).ordinal()] != 1) {
                return null;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("result");
            if (optJSONObject == null && (optJSONArray = jSONObject.optJSONArray("result")) != null) {
                optJSONObject = new JSONObject();
                optJSONObject.put("result", optJSONArray);
            }
            return optJSONObject == null ? jSONObject : optJSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* renamed from: com.xiaomi.smarthome.smartconfig.step.BindKeyStep$9  reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f22465a = new int[ErrorCode.values().length];

        static {
            try {
                f22465a[ErrorCode.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public void h() {
        this.mCommonBindView.addNextStep((int) R.string.kuailian_phone_connect_device, (int) R.string.kuailian_phone_connect_device_des);
        this.mCommonBindView.startProgressAnimation(1);
    }

    public void i() {
        switch (this.j) {
            case 0:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.kuailian_phone_connect_device_success, (int) R.string.kuailian_phone_connect_device_des);
                this.mCommonBindView.addNextStep((int) R.string.kuailian_phone_sendmessage_device, (int) R.string.kuailian_phone_connect_device_des);
                this.mCommonBindView.startProgressAnimation(2);
                return;
            case 1:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.kuailian_phone_sendmessage_success, (int) R.string.kuailian_phone_connect_device_des);
                this.mCommonBindView.addNextStep((int) R.string.kuailian_device_connect_wifi, (int) R.string.make_device_near_router);
                this.mCommonBindView.startProgressAnimation(3);
                return;
            case 2:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.kuailian_device_connect_wifi_success, (int) R.string.make_device_near_router);
                return;
            default:
                return;
        }
    }

    public void j() {
        switch (this.j) {
            case 0:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.kuailian_phone_connect_device, (int) R.string.kuailian_phone_connect_device_des);
                this.mCommonBindView.startProgressAnimation(1);
                return;
            case 1:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.kuailian_phone_sendmessage_device, (int) R.string.kuailian_phone_connect_device_des);
                this.mCommonBindView.startProgressAnimation(2);
                return;
            case 2:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.kuailian_device_connect_wifi, (int) R.string.make_device_near_router);
                this.mCommonBindView.startProgressAnimation(3);
                return;
            default:
                return;
        }
    }

    private void H() {
        switch (this.j) {
            case 0:
                a((int) R.string.kuailian_phone_connect_device_fail, (int) R.string.kuailian_phone_connect_device_fail_desc, (int) R.drawable.common_bind_app_connect_device_failed);
                return;
            case 1:
                a((int) R.string.kuailian_phone_sendmessage_fail, (int) R.string.kuailian_phone_connect_device_fail_desc, (int) R.drawable.common_bind_app_connect_device_failed);
                return;
            case 2:
                if (!NetworkUtils.c()) {
                    a((int) R.string.kuailian_device_connect_wifi_fail, (int) R.string.phone_wifi_error, (int) R.drawable.common_bind_app_connect_network_failed);
                    return;
                } else {
                    a((int) R.string.kuailian_device_connect_wifi_fail, (int) R.string.make_device_near_router, (int) R.drawable.common_bind_device_connect_network_failed);
                    return;
                }
            default:
                return;
        }
    }

    public boolean a() {
        try {
            new MLAlertDialog.Builder(this.af).a((CharSequence) this.af.getString(R.string.stop_connect_device_title)).b((CharSequence) this.af.getString(R.string.stop_connect_device_message)).a((CharSequence) this.af.getString(R.string.confirm), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    WifiManager wifiManager = (WifiManager) BindKeyStep.this.af.getApplicationContext().getSystemService("wifi");
                    WifiInfo connectionInfo = wifiManager != null ? wifiManager.getConnectionInfo() : null;
                    if (!(BindKeyStep.this.h == null || connectionInfo == null || !BindKeyStep.this.h.BSSID.equalsIgnoreCase(wifiManager.getConnectionInfo().getBSSID()))) {
                        WifiSettingUtils.b(BindKeyStep.this.w, BindKeyStep.this.h.SSID);
                    }
                    if (DeviceFinder.a().e()) {
                        DeviceFinder.a().b();
                    }
                    BindKeyStep.this.d_(false);
                }
            }).b((CharSequence) this.af.getString(R.string.cancel), (DialogInterface.OnClickListener) null).a(this.af.getResources().getColor(R.color.std_dialog_button_red), -1).d();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }
}
