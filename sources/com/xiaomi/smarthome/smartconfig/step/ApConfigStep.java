package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.miio.MiioLocalAPI;
import com.xiaomi.miio.MiioLocalRpcResponse;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.api.CameraApi;
import com.xiaomi.smarthome.device.bluetooth.ui.CommonBindView;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.statistic.MiStatType;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.international.SelectServerUtils;
import com.xiaomi.smarthome.library.common.ApiHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.library.common.statcode.ApErrorCode;
import com.xiaomi.smarthome.newui.utils.NumberUtils;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.ConfigStep;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.wificonfig.WifiDeviceFinder;
import com.xiaomi.smarthome.wificonfig.WifiSettingUtils;
import com.xiaomi.stat.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApConfigStep extends ConfigStep {
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final String f = "ApConfigStep";

    /* renamed from: a  reason: collision with root package name */
    boolean f22396a = false;
    private int ap = 0;
    private boolean aq = false;
    /* access modifiers changed from: private */
    public long ar = -1;
    /* access modifiers changed from: private */
    public long[] as = new long[4];
    private long[] at = new long[4];
    private MiioLocalAPI.Cancelable au;
    /* access modifiers changed from: private */
    public String av;
    /* access modifiers changed from: private */
    public boolean aw = false;
    private ConfigStep.DeviceFinderCallback ax = new DeviceFinder.DeviceFinderCallback2() {
        public void a(List<Device> list) {
            boolean unused = ApConfigStep.this.aw = true;
            ApConfigStep.this.ak = false;
            ApConfigStep.this.a(3);
            WifiDeviceFinder.a(ApConfigStep.this.ao);
        }

        public void a() {
            WifiDeviceFinder.a(ApConfigStep.this.ao);
        }

        public void a(int i) {
            if (i == -6) {
                ApConfigStep.this.a(SmartConfigStep.Step.STEP_BIND_BY_OTHER_ERROR);
                if (!ApConfigStep.this.al && ApConfigStep.this.ak) {
                    Log.e(ApConfigStep.f, "DeviceFinder already bind!");
                    STAT.c.d(ApConfigStep.this.aj, 3);
                }
            }
            WifiDeviceFinder.a(ApConfigStep.this.ao);
        }

        public void b(List<Device> list) {
            ApConfigStep.this.ak = false;
            boolean unused = ApConfigStep.this.aw = true;
            ApConfigStep.this.a(3);
            WifiDeviceFinder.a(ApConfigStep.this.ao);
        }
    };
    private ArrayList<ConfigStep.ConfigTime> g = new ArrayList<>();
    /* access modifiers changed from: private */
    public int h;
    /* access modifiers changed from: private */
    public long i;
    /* access modifiers changed from: private */
    public String j;
    private int k;
    private int l = 0;

    static /* synthetic */ int b(ApConfigStep apConfigStep) {
        int i2 = apConfigStep.l;
        apConfigStep.l = i2 + 1;
        return i2;
    }

    ApConfigStep() {
        if (DeviceFinder.a().h() != null) {
            this.i = NumberUtils.a((Object) DeviceFinder.a().h(), 0);
        }
        this.ap = ((Integer) SmartConfigDataProvider.a().a(SmartConfigDataProvider.x, 0)).intValue();
    }

    public void a(Context context) {
        super.a(context);
        STAT.c.h(this.aj);
    }

    public ArrayList<ConfigStep.ConfigTime> O_() {
        this.g.clear();
        ConfigStep.ConfigTime configTime = new ConfigStep.ConfigTime();
        configTime.f22586a = 0;
        configTime.b = 30000;
        this.g.add(configTime);
        ConfigStep.ConfigTime configTime2 = new ConfigStep.ConfigTime();
        configTime2.f22586a = 1;
        configTime2.b = 20000;
        this.g.add(configTime2);
        ConfigStep.ConfigTime configTime3 = new ConfigStep.ConfigTime();
        configTime3.f22586a = 3;
        configTime3.b = 50000;
        if (DeviceFinder.a().e()) {
            long f2 = DeviceFinder.a().f();
            long currentTimeMillis = System.currentTimeMillis();
            double d2 = (double) ((((currentTimeMillis - f2) * 50) / 50000) + 50);
            Double.isNaN(d2);
            configTime3.c = (int) (d2 * 0.9d);
            configTime3.b = (f2 + 50000) - currentTimeMillis;
            this.h = 3;
        }
        this.aq = ((Boolean) SmartConfigDataProvider.a().a(SmartConfigDataProvider.y, false)).booleanValue();
        if (this.aq) {
            SmartConfigDataProvider.a().b(SmartConfigDataProvider.y, false);
            this.h = 3;
            configTime3.b = 0;
        }
        this.g.add(configTime3);
        return this.g;
    }

    public SmartConfigStep.Step b(int i2) {
        this.am = true;
        WifiDeviceFinder.a(this.ao);
        switch (i2) {
            case 0:
                MobclickAgent.a(this.af, MiStatType.CLICK.getValue(), "connect_ap_connect_timeout");
                if (!this.al && this.ak) {
                    this.al = true;
                    STAT.c.a(this.i, this.aj, 1101, this.an);
                    STAT.c.d(this.aj, 1);
                }
                StatHelper.d("connect_ap_connect_timeout");
                U_().removeMessages(123);
                return SmartConfigStep.Step.STEP_CONNECT_AP_ERROR;
            case 1:
                this.f22396a = true;
                return null;
            case 2:
                MobclickAgent.a(this.af, MiStatType.CLICK.getValue(), "connect_mobile_network_timeout");
                StatHelper.d("connect_mobile_network_timeout");
                return null;
            case 3:
                w();
                q();
                MobclickAgent.a(this.af, MiStatType.CLICK.getValue(), "connect_get_device_timeout");
                if (!this.al && this.ak && NetworkUtils.c() && x()) {
                    this.al = true;
                    STAT.c.a(this.i, this.aj, 1201, this.an);
                    STAT.c.d(this.aj, 3);
                }
                StatHelper.d("connect_get_device_timeout");
                DeviceFinder.a().b();
                PluginRecord d2 = CoreApi.a().d(DeviceFactory.a(this.ao));
                if (d2 == null || d2.c().C() == 0) {
                    a(false, 0, (SmartConfigStep.Step) null);
                    return null;
                }
                MobclickAgent.a(this.af, DeviceFactory.a(this.ao), "ap_connect_server_failure2");
                a(true, (int) R.string.connect_fail_tips, SmartConfigStep.Step.STEP_FIND_DEVICE_FAILED_ERROR);
                return null;
            default:
                return null;
        }
    }

    public void h() {
        this.mCommonBindView.addNextStep((int) R.string.kuailian_phone_connect_device, (int) R.string.kuailian_phone_connect_device_des);
        this.mCommonBindView.startProgressAnimation(1);
    }

    public void i() {
        switch (this.h) {
            case 0:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.kuailian_phone_connect_device_success, (int) R.string.kuailian_phone_connect_device_des);
                this.mCommonBindView.addNextStep((int) R.string.kuailian_phone_sendmessage_device, (int) R.string.kuailian_phone_connect_device_des);
                this.mCommonBindView.startProgressAnimation(2);
                return;
            case 1:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.kuailian_phone_sendmessage_success, (int) R.string.kuailian_phone_connect_device_des);
                Resources resources = this.mCommonBindView.getResources();
                this.mCommonBindView.addNextStep(resources.getString(R.string.kuailian_device_connect_wifi), resources.getString(R.string.make_device_near_router));
                this.mCommonBindView.startProgressAnimation(3);
                return;
            case 3:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.kuailian_device_connect_wifi_success, (int) R.string.make_device_near_router);
                return;
            default:
                return;
        }
    }

    public void j() {
        int i2 = this.h;
        if (i2 != 3) {
            switch (i2) {
                case 0:
                    this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.kuailian_phone_connect_device, (int) R.string.kuailian_phone_connect_device_des);
                    this.mCommonBindView.startProgressAnimation(1);
                    return;
                case 1:
                    this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.kuailian_phone_sendmessage_device, (int) R.string.kuailian_phone_connect_device_des);
                    this.mCommonBindView.startProgressAnimation(2);
                    return;
                default:
                    return;
            }
        } else {
            this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, this.mCommonBindView.getResources().getString(R.string.kuailian_device_connect_wifi), (int) R.string.make_device_near_router);
            this.mCommonBindView.startProgressAnimation(3);
        }
    }

    private void q() {
        int i2 = this.h;
        if (i2 != 3) {
            switch (i2) {
                case 0:
                    a((int) R.string.kuailian_phone_connect_device_fail, (int) R.string.kuailian_phone_connect_device_fail_desc, (int) R.drawable.common_bind_app_connect_device_failed);
                    return;
                case 1:
                    a((int) R.string.kuailian_phone_sendmessage_fail, (int) R.string.kuailian_phone_connect_device_fail_desc, (int) R.drawable.common_bind_app_connect_device_failed);
                    return;
                default:
                    return;
            }
        } else if (!NetworkUtils.c()) {
            b((int) R.string.kuailian_device_connect_wifi_fail, (int) R.string.phone_wifi_error, (int) R.drawable.common_bind_app_connect_network_failed);
        } else {
            b((int) R.string.kuailian_device_connect_wifi_fail, (int) R.string.make_device_near_router, (int) R.drawable.common_bind_device_connect_network_failed);
        }
    }

    public void k() {
        this.h = 0;
        this.ar = System.currentTimeMillis();
        Arrays.a(this.as, 0);
        this.as[this.h] = System.currentTimeMillis();
    }

    public int b() {
        switch (this.h) {
            case 0:
                return 0;
            case 1:
                String str = ((ScanResult) SmartConfigDataProvider.a().a(SmartConfigDataProvider.h)).SSID;
                WifiInfo connectionInfo = this.w.getConnectionInfo();
                if (connectionInfo == null || !WifiSettingUtils.a(str, connectionInfo.getSSID())) {
                    this.h = 0;
                    return 0;
                } else if (((Boolean) SmartConfigDataProvider.a().a(SmartConfigDataProvider.v, false)).booleanValue()) {
                    return 3;
                } else {
                    return 1;
                }
            case 2:
                String b2 = SmartConfigDataProvider.a().b();
                WifiInfo connectionInfo2 = this.w.getConnectionInfo();
                if (connectionInfo2 == null || !connectionInfo2.getSSID().equals(b2)) {
                    return 2;
                }
                return 3;
            default:
                return this.h;
        }
    }

    public SmartConfigStep.Step f() {
        return SmartConfigStep.Step.STEP_AP_CONFIG_STEP;
    }

    public void a(Message message) {
        ScanResult scanResult;
        int i2 = message.what;
        if (i2 != 101) {
            if (i2 == 112) {
                if (U_() != null) {
                    U_().removeMessages(112);
                    U_().removeMessages(114);
                }
                if (this.h == 1) {
                    a(1);
                    this.h = 3;
                    o();
                    S_();
                }
            } else if (i2 != 123) {
                switch (i2) {
                    case 114:
                        if (this.f22396a) {
                            q();
                            if (!this.al && this.ak) {
                                this.al = true;
                                STAT.c.a(this.i, this.aj, (int) ApErrorCode.b, this.an);
                                STAT.c.d(this.aj, 2);
                            }
                            PluginRecord d2 = CoreApi.a().d(DeviceFactory.a(this.ao));
                            if (d2 == null || d2.c().C() == 0) {
                                a(false, 0, (SmartConfigStep.Step) null);
                            } else {
                                MobclickAgent.a(this.af, DeviceFactory.a(this.ao), "ap_connect_transfer_failure2");
                                a(true, (int) R.string.connect_fail_tips, SmartConfigStep.Step.STEP_SEND_ROUTER_INFO_FINAL_ERROR);
                            }
                            this.f22396a = false;
                            return;
                        } else if (this.h == 1) {
                            R_();
                            return;
                        } else {
                            return;
                        }
                    case 115:
                        o();
                        p();
                        return;
                    default:
                        super.a(message);
                        return;
                }
            } else {
                Q_();
            }
        } else if (this.v) {
            NetworkInfo networkInfo = (NetworkInfo) message.obj;
            NetworkInfo.DetailedState detailedState = networkInfo.getDetailedState();
            WifiInfo connectionInfo = this.w.getConnectionInfo();
            StringBuilder sb = new StringBuilder();
            sb.append(detailedState.toString());
            sb.append(", ");
            sb.append(connectionInfo);
            sb.append(", ");
            sb.append(networkInfo.getExtraInfo() != null ? networkInfo.getExtraInfo() : "");
            sb.append(", ");
            sb.append(networkInfo.getReason() != null ? networkInfo.getReason() : "");
            LogUtilGrey.a(f, sb.toString());
            if (this.h == 1 && networkInfo.getState() == NetworkInfo.State.DISCONNECTED && !this.ah) {
                ScanResult scanResult2 = (ScanResult) SmartConfigDataProvider.a().a(SmartConfigDataProvider.h);
                if (connectionInfo == null || scanResult2 == null || !WifiSettingUtils.a(connectionInfo.getSSID(), scanResult2.SSID)) {
                    U_().sendEmptyMessage(112);
                    return;
                }
            }
            if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID()) && !connectionInfo.getSSID().contains("<unknown ssid>") && detailedState == NetworkInfo.DetailedState.CONNECTED && networkInfo.isConnected() && connectionInfo.getSupplicantState() == SupplicantState.COMPLETED && (scanResult = (ScanResult) SmartConfigDataProvider.a().a(SmartConfigDataProvider.h)) != null && WifiSettingUtils.a(connectionInfo.getSSID(), scanResult.SSID) && this.h == 0 && !this.ah) {
                a(this.h);
                U_().removeMessages(123);
                R_();
                this.h = 1;
                this.l++;
                MobclickAgent.a(this.af, DeviceFactory.a(this.ao), "ap_connect_transfering");
            }
        }
    }

    /* renamed from: c */
    public void e(int i2) {
        boolean z;
        boolean z2 = false;
        if (l() == null) {
            this.ak = false;
            Handler U_ = U_();
            if (U_ != null && this.y) {
                U_.postDelayed(new Runnable(i2) {
                    private final /* synthetic */ int f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        ApConfigStep.this.e(this.f$1);
                    }
                }, 2000);
                return;
            }
            return;
        }
        List<ScanResult> scanResults = this.w.getScanResults();
        if (scanResults != null) {
            Iterator<ScanResult> it = scanResults.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (WifiSettingUtils.a(it.next().SSID, this.ao.SSID)) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        z = false;
        if (z && this.ak) {
            z2 = true;
        }
        this.ak = z2;
        switch (i2) {
            case 0:
                long currentTimeMillis = System.currentTimeMillis();
                if (this.ar <= 0) {
                    this.ar = currentTimeMillis;
                }
                if (this.as[i2] == 0) {
                    this.as[i2] = currentTimeMillis;
                }
                CameraApi.getInstance().getBindKey(this.af, new AsyncCallback<String, Error>() {
                    /* renamed from: a */
                    public void onSuccess(String str) {
                        BluetoothMyLogger.d("ApConfigStep getBindKey:" + str);
                        String unused = ApConfigStep.this.av = str;
                        ApConfigStep.this.Q_();
                    }

                    public void onFailure(Error error) {
                        BluetoothMyLogger.d("ApConfigStep getBindKey failed");
                        String unused = ApConfigStep.this.av = "";
                        ApConfigStep.this.Q_();
                    }
                });
                MobclickAgent.a(this.af, DeviceFactory.a(this.ao), "ap_connect_device");
                return;
            case 1:
                R_();
                MobclickAgent.a(this.af, DeviceFactory.a(this.ao), "ap_connect_transfering");
                this.l++;
                return;
            case 2:
                MobclickAgent.a(this.af, DeviceFactory.a(this.ao), "ap_connect_server");
                o();
                return;
            case 3:
                o();
                S_();
                return;
            default:
                return;
        }
    }

    public void a(int i2) {
        d(i2);
        super.a(i2);
    }

    private void d(int i2) {
        STAT.c.a(this.aj, this.i, ApErrorCode.a(i2), (int) ((System.currentTimeMillis() - this.at[i2]) / 1000));
        this.at[this.h] = 0;
    }

    public void Q_() {
        PluginRecord d2;
        LogUtilGrey.a(f, "startConnectToDeviceAp:" + this.ao);
        if (this.ao != null) {
            String i2 = DeviceFactory.i(this.ao.SSID);
            String str = "";
            if (!(i2 == null || (d2 = CoreApi.a().d(i2)) == null || TextUtils.isEmpty(d2.c().u()))) {
                str = d2.c().u();
            }
            this.h = 0;
            String c2 = WifiUtil.c(this.af);
            this.at[this.h] = System.currentTimeMillis();
            WifiInfo connectionInfo = this.w.getConnectionInfo();
            if (TextUtils.isEmpty(c2) || !c2.equals(this.ao.SSID) || connectionInfo == null || connectionInfo.getSupplicantState() != SupplicantState.COMPLETED) {
                WifiManager wifiManager = this.w;
                String str2 = this.ao.SSID;
                if (DeviceFactory.d(this.ao) == DeviceFactory.AP_TYPE.AP_MIDEA || DeviceFactory.d(this.ao) == DeviceFactory.AP_TYPE.AP_MIDEA_AC) {
                    str = "12345678";
                }
                WifiSettingUtils.a(wifiManager, str2, str, this.ao.BSSID, this.ao.capabilities);
                U_().sendEmptyMessageDelayed(123, 15000);
            } else if (U_() != null) {
                U_().post(new Runnable() {
                    public void run() {
                        if (ApConfigStep.this.h != 1) {
                            ApConfigStep.this.a(ApConfigStep.this.h);
                            ApConfigStep.this.R_();
                            ApConfigStep.b(ApConfigStep.this);
                        }
                    }
                });
            }
        }
    }

    public void R_() {
        LogUtilGrey.a(f, "startConnectionAsso:");
        WifiDeviceFinder.m.clear();
        this.k = (int) (System.currentTimeMillis() / 1000);
        this.h = 1;
        if (this.as[this.h] == 0) {
            this.as[this.h] = System.currentTimeMillis();
        }
        this.at[this.h] = System.currentTimeMillis();
        if (ApiHelper.e) {
            Network network = null;
            Network[] allNetworks = this.x.getAllNetworks();
            int i2 = 0;
            while (true) {
                try {
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
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (network == null) {
                Log.e("ERROR", "Get Network ERROR");
            }
            this.x.bindProcessToNetwork(network);
        }
        a(SmartConfigDataProvider.a().b(), SmartConfigDataProvider.a().d());
    }

    public void a(String str, String str2) {
        if (!CoreApi.a().q()) {
            d_(true);
            return;
        }
        final JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.k);
            jSONObject.put("method", "miIO.config_router");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(DeviceTagInterface.e, str);
            if (str2 == null) {
                str2 = "";
            }
            jSONObject2.put("passwd", str2);
            jSONObject2.put("uid", Long.valueOf(CoreApi.a().s()));
            jSONObject2.put("bind_key", this.av);
            jSONObject2.put("config_type", this.u.type);
            String e2 = SelectServerUtils.e();
            if (!TextUtils.isEmpty(e2)) {
                jSONObject2.put("country_domain", e2);
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(d.p, SelectServerUtils.d());
            jSONObject2.put("wifi_config", jSONObject3);
            jSONObject2.put(SmartConfigDataProvider.s, (Integer) SmartConfigDataProvider.a().a(SmartConfigDataProvider.s, 28800));
            jSONObject2.put(d.o, SmartConfigDataProvider.a().a(SmartConfigDataProvider.t, ""));
            jSONObject.put("params", jSONObject2);
        } catch (JSONException e3) {
            LogUtilGrey.a(f, "setMiioRouter json error:" + Log.getStackTraceString(e3));
        }
        if (this.au != null) {
            this.au.a();
        }
        this.au = MiioLocalAPI.a(n(), (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
            public void onResponse(final String str) {
                LogUtilGrey.a(ApConfigStep.f, "RPC response: " + str);
                if (ApConfigStep.this.U_() != null) {
                    ApConfigStep.this.U_().post(new Runnable() {
                        public void run() {
                            JSONObject a2 = ApConfigStep.a(str);
                            if (a2 != null) {
                                long unused = ApConfigStep.this.i = Long.valueOf(a2.optString("did")).longValue();
                                String unused2 = ApConfigStep.this.j = a2.optString("token");
                                DeviceFinder.a().a(String.valueOf(ApConfigStep.this.i), ApConfigStep.this.j);
                                JSONObject jSONObject = new JSONObject();
                                try {
                                    jSONObject.put("id", ((PluginHostApi) PluginHostApi.instance()).generateNonce());
                                    jSONObject.put("method", "miIO.info");
                                    jSONObject.put("params", new JSONObject());
                                    MiioLocalAPI.a(ApConfigStep.this.n(), jSONObject.toString(), ApConfigStep.this.i, ApConfigStep.this.j, (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
                                        public void onResponse(String str) {
                                            JSONObject a2 = ApConfigStep.a(str);
                                            if (a2 != null) {
                                                ApConfigStep.this.an = a2.optString("fw_ver");
                                            }
                                            LogUtilGrey.a(ApConfigStep.f, "async_rpc info version: " + ApConfigStep.this.an + ", data = " + str);
                                            String jSONObject = jSONObject.toString();
                                            LogUtilGrey.a(ApConfigStep.f, "write to device data: " + jSONObject + " result:" + str);
                                            MiioLocalAPI.a(ApConfigStep.this.n(), jSONObject, ApConfigStep.this.i, ApConfigStep.this.j, (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
                                                public void onResponse(String str) {
                                                    LogUtilGrey.a(ApConfigStep.f, "async_rpc device return data: " + str);
                                                    if (ApConfigStep.a(str) != null) {
                                                        ApConfigStep.this.p();
                                                        WifiDeviceFinder.a(ApConfigStep.this.ao);
                                                    } else if (ApConfigStep.this.U_() != null) {
                                                        ApConfigStep.this.U_().sendEmptyMessageDelayed(114, 1000);
                                                        return;
                                                    }
                                                    if (ApConfigStep.this.U_() != null) {
                                                        ApConfigStep.this.U_().sendEmptyMessageDelayed(112, 1000);
                                                    }
                                                }
                                            });
                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else if (ApConfigStep.this.U_() != null) {
                                ApConfigStep.this.U_().sendEmptyMessageDelayed(114, 1000);
                            }
                        }
                    });
                }
            }
        }, 5);
    }

    /* access modifiers changed from: protected */
    public boolean S_() {
        long currentTimeMillis = System.currentTimeMillis();
        this.h = 3;
        if (this.as[this.h] == 0) {
            this.as[this.h] = currentTimeMillis;
        }
        this.at[this.h] = currentTimeMillis;
        if (DeviceFinder.a().e()) {
            DeviceFinder.a().a(this.ax);
            return true;
        } else if (this.ao == null) {
            return false;
        } else {
            this.aq = false;
            DeviceFinder.a().a(this.ax, this.ao, this.i == 0 ? null : String.valueOf(this.i), this.j, this.av);
            this.ap++;
            return false;
        }
    }

    public void e() {
        super.e();
        if (this.au != null) {
            this.au.a();
        }
        DeviceFinder.a().c();
    }

    public void o() {
        U_().removeMessages(112);
        U_().removeMessages(114);
        if (ApiHelper.e) {
            Log.e("WifiSettingUap", "Bind Network to NULL");
            this.x.bindProcessToNetwork((Network) null);
        }
        if (!DeviceFinder.a().e()) {
            WifiSettingUtils.a(this.w, SmartConfigDataProvider.a().b(), SmartConfigDataProvider.a().d(), (String) null, SmartConfigDataProvider.a().e());
        }
    }

    public static JSONObject a(String str) {
        JSONArray optJSONArray;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (AnonymousClass7.f22406a[ErrorCode.valueof(jSONObject.optInt("code")).ordinal()] != 1) {
                return null;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("result");
            if (optJSONObject == null && (optJSONArray = jSONObject.optJSONArray("result")) != null) {
                optJSONObject = new JSONObject();
                optJSONObject.put("result", optJSONArray);
            }
            return optJSONObject == null ? jSONObject : optJSONObject;
        } catch (JSONException e2) {
            LogUtilGrey.a(f, "parseRpcResponse: " + Log.getStackTraceString(e2));
            return null;
        }
    }

    /* renamed from: com.xiaomi.smarthome.smartconfig.step.ApConfigStep$7  reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f22406a = new int[ErrorCode.values().length];

        static {
            try {
                f22406a[ErrorCode.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public void p() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.k);
            jSONObject.put("method", "miIO.stop_diag_mode");
            jSONObject.put("params", "");
        } catch (JSONException unused) {
        }
        MiioLocalAPI.a(n(), jSONObject.toString(), this.i, this.j, (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
            public void onResponse(String str) {
            }
        });
    }

    public boolean a() {
        try {
            new MLAlertDialog.Builder(this.af).a((CharSequence) this.af.getString(R.string.stop_connect_device_title)).b((CharSequence) this.af.getString(R.string.stop_connect_device_message)).a((CharSequence) this.af.getString(R.string.confirm), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (ApConfigStep.this.af != null) {
                        WifiManager wifiManager = (WifiManager) ApConfigStep.this.af.getApplicationContext().getSystemService("wifi");
                        WifiInfo wifiInfo = null;
                        if (wifiManager != null) {
                            wifiInfo = wifiManager.getConnectionInfo();
                        }
                        if (!(ApConfigStep.this.ao == null || wifiInfo == null || !ApConfigStep.this.ao.BSSID.equalsIgnoreCase(wifiManager.getConnectionInfo().getBSSID()))) {
                            WifiSettingUtils.b(ApConfigStep.this.w, ApConfigStep.this.ao.SSID);
                        }
                        if (DeviceFinder.a().e()) {
                            DeviceFinder.a().b();
                        }
                        ApConfigStep.this.d_(false);
                        STAT.c.d(ApConfigStep.this.aj, ApConfigStep.this.ai);
                        int a2 = ApErrorCode.a(ApConfigStep.this.h);
                        long currentTimeMillis = System.currentTimeMillis();
                        int i2 = (int) ((currentTimeMillis - ApConfigStep.this.as[ApConfigStep.this.h]) / 1000);
                        int f = (int) ((currentTimeMillis - ApConfigStep.this.ar) / 1000);
                        if (!ApErrorCode.a(i2, ApConfigStep.this.h)) {
                            ApConfigStep.this.ak = false;
                        } else if (!ApConfigStep.this.al && ApConfigStep.this.ak && !ApConfigStep.this.am) {
                            if (ApConfigStep.this.h != 3 || (ApConfigStep.this.x() && NetworkUtils.c())) {
                                ApConfigStep.this.al = true;
                                STAT.c.a(ApConfigStep.this.i, ApConfigStep.this.aj, a2, i2, f, ApConfigStep.this.an);
                            }
                        }
                    }
                }
            }).b((CharSequence) this.af.getString(R.string.cancel), (DialogInterface.OnClickListener) null).a(this.af.getResources().getColor(R.color.std_dialog_button_red), -1).d();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }
}
