package com.xiaomi.smarthome.smartconfig.step;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;
import com.alipay.sdk.packet.e;
import com.coloros.mcssdk.c.a;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.miio.MiioLocalAPI;
import com.xiaomi.miio.MiioLocalRpcResponse;
import com.xiaomi.qrcode.ScanBarcodeActivity;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.ECCPointConvert;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.SecurityChipUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.api.CameraApi;
import com.xiaomi.smarthome.device.bluetooth.connect.single.BleBindActivityV2;
import com.xiaomi.smarthome.device.bluetooth.ui.CommonBindView;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
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
import com.xiaomi.smarthome.library.common.statcode.ApErrorCode;
import com.xiaomi.smarthome.library.crypto.HexUtil;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.ApSecureQRStep;
import com.xiaomi.smarthome.smartconfig.step.ConfigStep;
import com.xiaomi.smarthome.smartconfig.step.ECCurve;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.wificonfig.WifiDeviceFinder;
import com.xiaomi.smarthome.wificonfig.WifiSettingUtils;
import com.xiaomi.stat.d;
import java.security.interfaces.ECPublicKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApSecureConfigStep extends ConfigStep {

    /* renamed from: a  reason: collision with root package name */
    private static final int f22431a = 1000;
    private static final int ap = 3;
    private static final String aq = "ApSecureConfigStep";
    private static final boolean b = true;
    private static final int c = 0;
    private static final int d = 1;
    private static final int e = 2;
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 3;
    public static final int i = 4;
    protected static final int j = 5000;
    private ConfigStep.DeviceFinderCallback aA = new DeviceFinder.DeviceFinderCallback2() {
        public void a() {
        }

        public void a(List<Device> list) {
            ApSecureConfigStep.this.ak = false;
            boolean unused = ApSecureConfigStep.this.az = true;
            ApSecureConfigStep.this.a(3);
        }

        public void a(int i) {
            if (i == -6) {
                ApSecureConfigStep.this.a(SmartConfigStep.Step.STEP_BIND_BY_OTHER_ERROR);
            }
        }

        public void b(List<Device> list) {
            ApSecureConfigStep.this.ak = false;
            boolean unused = ApSecureConfigStep.this.az = true;
            ApSecureConfigStep.this.a(3);
        }
    };
    private ScanResult ar;
    private ArrayList<ConfigStep.ConfigTime> as = new ArrayList<>();
    /* access modifiers changed from: private */
    public int at;
    private boolean au = false;
    /* access modifiers changed from: private */
    public String av;
    /* access modifiers changed from: private */
    public MiioLocalAPI.Cancelable aw;
    /* access modifiers changed from: private */
    public boolean ax;
    /* access modifiers changed from: private */
    public ApSecureQRStep ay = null;
    /* access modifiers changed from: private */
    public boolean az = false;
    protected long k;
    protected String l;

    ApSecureConfigStep() {
        if (DeviceFinder.a().h() != null) {
            this.k = Long.valueOf(DeviceFinder.a().h()).longValue();
        }
    }

    public void a(Context context) {
        super.a(context);
        STAT.c.h(this.aj);
    }

    public ArrayList<ConfigStep.ConfigTime> O_() {
        this.as.clear();
        ConfigStep.ConfigTime configTime = new ConfigStep.ConfigTime();
        configTime.f22586a = 0;
        configTime.c = 0;
        configTime.b = 30000;
        this.as.add(configTime);
        ConfigStep.ConfigTime configTime2 = new ConfigStep.ConfigTime();
        configTime2.f22586a = 1;
        configTime2.b = 20000;
        this.as.add(configTime2);
        ConfigStep.ConfigTime configTime3 = new ConfigStep.ConfigTime();
        configTime3.f22586a = 2;
        configTime3.b = 20000;
        this.as.add(configTime3);
        ConfigStep.ConfigTime configTime4 = new ConfigStep.ConfigTime();
        configTime4.f22586a = 3;
        configTime4.b = 50000;
        if (DeviceFinder.a().e()) {
            long f2 = DeviceFinder.a().f();
            long currentTimeMillis = System.currentTimeMillis();
            double d2 = (double) ((((currentTimeMillis - f2) * 50) / 50000) + 50);
            Double.isNaN(d2);
            configTime4.c = (int) (d2 * 0.9d);
            configTime4.b = (f2 + 50000) - currentTimeMillis;
            this.at = 3;
        }
        this.au = ((Boolean) SmartConfigDataProvider.a().a(SmartConfigDataProvider.y, false)).booleanValue();
        if (this.au) {
            SmartConfigDataProvider.a().b(SmartConfigDataProvider.y, false);
            this.at = 3;
            configTime4.b = 0;
        }
        this.as.add(configTime4);
        return this.as;
    }

    public SmartConfigStep.Step b(int i2) {
        this.ax = true;
        this.am = true;
        switch (i2) {
            case 0:
                MobclickAgent.a(this.af, MiStatType.CLICK.getValue(), "connect_ap_connect_timeout");
                if (!this.al && this.ak) {
                    this.al = true;
                    STAT.c.a(this.k, this.aj, 1101, this.an);
                    STAT.c.d(this.aj, 1);
                }
                StatHelper.d("connect_ap_connect_timeout");
                U_().removeMessages(123);
                return SmartConfigStep.Step.STEP_CONNECT_AP_ERROR;
            case 1:
            case 2:
                q();
                if (!this.al && this.ak) {
                    this.al = true;
                    STAT.c.a(this.k, this.aj, (int) ApErrorCode.b, this.an);
                    STAT.c.d(this.aj, 2);
                }
                PluginRecord d2 = CoreApi.a().d(DeviceFactory.a(l()));
                if (d2 == null || d2.c().C() == 0) {
                    a(false, 0, (SmartConfigStep.Step) null);
                } else {
                    MobclickAgent.a(this.af, DeviceFactory.a(l()), "ap_connect_transfer_failure2");
                    a(true, (int) R.string.connect_fail_tips, SmartConfigStep.Step.STEP_SEND_ROUTER_INFO_FINAL_ERROR);
                }
                return null;
            case 3:
                w();
                q();
                MobclickAgent.a(this.af, MiStatType.CLICK.getValue(), "connect_get_device_timeout");
                if (!this.al && this.ak && NetworkUtils.c() && x()) {
                    this.al = true;
                    STAT.c.a(this.k, this.aj, 1201, this.an);
                    STAT.c.d(this.aj, 3);
                }
                StatHelper.d("connect_get_device_timeout");
                DeviceFinder.a().b();
                PluginRecord d3 = CoreApi.a().d(DeviceFactory.a(l()));
                if (d3 == null || d3.c().C() == 0) {
                    a(false, 0, (SmartConfigStep.Step) null);
                    return null;
                }
                MobclickAgent.a(this.af, DeviceFactory.a(l()), "ap_connect_server_failure2");
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
        switch (this.at) {
            case 0:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.kuailian_phone_connect_device_success, (int) R.string.kuailian_phone_connect_device_des);
                this.mCommonBindView.addNextStep((int) R.string.kuailian_phone_sendmessage_device, (int) R.string.kuailian_phone_connect_device_des);
                this.mCommonBindView.stopProgressAnimation();
                this.mCommonBindView.startProgressAnimation(2);
                return;
            case 1:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.kuailian_phone_sendmessage_device, (int) R.string.kuailian_phone_connect_device_des);
                this.mCommonBindView.startProgressAnimation(2);
                return;
            case 2:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.kuailian_phone_sendmessage_success, (int) R.string.kuailian_phone_connect_device_des);
                Resources resources = this.mCommonBindView.getResources();
                this.mCommonBindView.addNextStep(resources.getString(R.string.kuailian_device_connect_wifi), resources.getString(R.string.make_device_near_router));
                this.mCommonBindView.stopProgressAnimation();
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
        switch (this.at) {
            case 0:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.kuailian_phone_connect_device, (int) R.string.kuailian_phone_connect_device_des);
                this.mCommonBindView.stopProgressAnimation();
                this.mCommonBindView.startProgressAnimation(1);
                return;
            case 1:
            case 2:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.kuailian_phone_sendmessage_device, (int) R.string.kuailian_phone_connect_device_des);
                this.mCommonBindView.stopProgressAnimation();
                this.mCommonBindView.startProgressAnimation(2);
                return;
            case 3:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, this.mCommonBindView.getResources().getString(R.string.kuailian_device_connect_wifi), (int) R.string.make_device_near_router);
                this.mCommonBindView.stopProgressAnimation();
                this.mCommonBindView.startProgressAnimation(3);
                return;
            default:
                return;
        }
    }

    private void q() {
        switch (this.at) {
            case 0:
                a((int) R.string.kuailian_phone_connect_device_fail, (int) R.string.kuailian_phone_connect_device_fail_desc, (int) R.drawable.common_bind_app_connect_device_failed);
                return;
            case 1:
            case 2:
                a((int) R.string.kuailian_phone_sendmessage_fail, (int) R.string.kuailian_phone_connect_device_fail_desc, (int) R.drawable.common_bind_app_connect_device_failed);
                return;
            case 3:
                if (!NetworkUtils.c()) {
                    b((int) R.string.kuailian_device_connect_wifi_fail, (int) R.string.phone_wifi_error, (int) R.drawable.common_bind_app_connect_network_failed);
                    return;
                } else {
                    b((int) R.string.kuailian_device_connect_wifi_fail, (int) R.string.make_device_near_router, (int) R.drawable.common_bind_device_connect_network_failed);
                    return;
                }
            default:
                return;
        }
    }

    public void k() {
        this.k = 0;
        this.l = null;
        this.at = 0;
    }

    public int b() {
        switch (this.at) {
            case 0:
                if (l() != null) {
                    return 0;
                }
                d_(false);
                return -1;
            case 1:
                if (l() == null) {
                    return 1;
                }
                String str = l().SSID;
                WifiInfo connectionInfo = this.w.getConnectionInfo();
                if (connectionInfo != null && WifiSettingUtils.a(str, connectionInfo.getSSID())) {
                    return 1;
                }
                k();
                return 0;
            case 2:
                return 2;
            default:
                return this.at;
        }
    }

    /* access modifiers changed from: protected */
    public ScanResult l() {
        if (this.ar == null) {
            this.ar = (ScanResult) SmartConfigDataProvider.a().a(SmartConfigDataProvider.h);
        }
        return this.ar;
    }

    public SmartConfigStep.Step f() {
        return SmartConfigStep.Step.STEP_AP_CONFIG_STEP;
    }

    public void a(Message message) {
        PluginRecord d2;
        if (U_() != null) {
            U_().removeMessages(message.what);
        }
        int i2 = message.what;
        if (i2 == 101) {
            LogUtilGrey.a(aq, "handleMessage:  NETWORK_STATE_CHANGED " + l());
            b(true);
        } else if (i2 != 112) {
            if (i2 == 115) {
                o();
                p();
            } else if (i2 == 123) {
                LogUtilGrey.a(aq, "handleMessage:  MSG_RECONNECT_DEVICE_AP " + l());
                String i3 = DeviceFactory.i(l().SSID);
                String str = "";
                if (!(i3 == null || (d2 = CoreApi.a().d(i3)) == null || TextUtils.isEmpty(d2.c().u()))) {
                    str = d2.c().u();
                }
                WifiManager wifiManager = this.w;
                String str2 = l().SSID;
                if (DeviceFactory.d(l()) == DeviceFactory.AP_TYPE.AP_MIDEA || DeviceFactory.d(l()) == DeviceFactory.AP_TYPE.AP_MIDEA_AC) {
                    str = "12345678";
                }
                WifiSettingUtils.a(wifiManager, str2, str, l().BSSID, l().capabilities);
            } else if (i2 != 132) {
                switch (i2) {
                    case 128:
                        s();
                        return;
                    case 129:
                        E();
                        return;
                    case 130:
                        F();
                        return;
                    default:
                        super.a(message);
                        return;
                }
            } else {
                H();
            }
        } else if (this.at == 2) {
            a(2);
            this.at = 3;
            o();
            I();
        }
    }

    public void c(int i2) {
        boolean z;
        boolean z2 = true;
        switch (i2) {
            case 0:
                k();
                List<ScanResult> scanResults = this.w.getScanResults();
                if (scanResults != null) {
                    Iterator<ScanResult> it = scanResults.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (WifiSettingUtils.a(it.next().SSID, this.ar.SSID)) {
                                z = true;
                            }
                        }
                    }
                    if (!z || !this.ak) {
                        z2 = false;
                    }
                    this.ak = z2;
                    CameraApi.getInstance().getBindKey(this.af, new AsyncCallback<String, Error>() {
                        /* renamed from: a */
                        public void onSuccess(String str) {
                            BluetoothMyLogger.d("BleComboStep getBindKey:" + str);
                            String unused = ApSecureConfigStep.this.av = str;
                            ApSecureConfigStep.this.b(false);
                        }

                        public void onFailure(Error error) {
                            BluetoothMyLogger.d("BleComboStep getBindKey failed");
                            String unused = ApSecureConfigStep.this.av = "";
                            ApSecureConfigStep.this.b(false);
                        }
                    });
                    MobclickAgent.a(this.af, DeviceFactory.a(l()), "ap_connect_device");
                    return;
                }
                z = false;
                z2 = false;
                this.ak = z2;
                CameraApi.getInstance().getBindKey(this.af, new AsyncCallback<String, Error>() {
                    /* renamed from: a */
                    public void onSuccess(String str) {
                        BluetoothMyLogger.d("BleComboStep getBindKey:" + str);
                        String unused = ApSecureConfigStep.this.av = str;
                        ApSecureConfigStep.this.b(false);
                    }

                    public void onFailure(Error error) {
                        BluetoothMyLogger.d("BleComboStep getBindKey failed");
                        String unused = ApSecureConfigStep.this.av = "";
                        ApSecureConfigStep.this.b(false);
                    }
                });
                MobclickAgent.a(this.af, DeviceFactory.a(l()), "ap_connect_device");
                return;
            case 1:
            case 2:
                P_();
                this.at = 1;
                if (U_() == null || (this.k != 0 && !TextUtils.isEmpty(this.l))) {
                    t();
                } else {
                    U_().sendEmptyMessage(128);
                }
                MobclickAgent.a(this.af, DeviceFactory.a(l()), "ap_connect_transfering");
                return;
            case 3:
                U_().sendEmptyMessage(112);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void b(boolean z) {
        U_().removeMessages(123);
        if (l() != null && !this.ah && this.at != 3 && !this.ax) {
            WifiInfo connectionInfo = this.w.getConnectionInfo();
            if (connectionInfo == null || !WifiSettingUtils.a(connectionInfo.getSSID(), l().SSID)) {
                LogUtilGrey.a(aq, "checkConnectDeviceAp info:" + connectionInfo);
                U_().sendEmptyMessageDelayed(123, z ? 5000 : 0);
            } else if (U_() != null && connectionInfo.getSupplicantState() == SupplicantState.COMPLETED) {
                if (P_()) {
                    U_().postDelayed(new Runnable() {
                        public void run() {
                            if (ApSecureConfigStep.this.at == 0) {
                                ApSecureConfigStep.this.a(0);
                                int unused = ApSecureConfigStep.this.at = 1;
                                if (ApSecureConfigStep.this.U_() == null) {
                                    return;
                                }
                                if (ApSecureConfigStep.this.k == 0 || TextUtils.isEmpty(ApSecureConfigStep.this.l)) {
                                    MobclickAgent.a(ApSecureConfigStep.this.af, DeviceFactory.a(ApSecureConfigStep.this.l()), "ap_connect_transfering");
                                    ApSecureConfigStep.this.U_().sendEmptyMessage(128);
                                }
                            }
                        }
                    }, 1000);
                } else {
                    U_().postDelayed(new Runnable(z) {
                        private final /* synthetic */ boolean f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            ApSecureConfigStep.this.b(this.f$1);
                        }
                    }, 1000);
                }
            }
        }
    }

    public boolean P_() {
        LogUtilGrey.a(aq, "startConnectionAsso mCurrentIndex:" + this.at);
        WifiDeviceFinder.m.clear();
        if (!ApiHelper.e) {
            return true;
        }
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
        if (network != null) {
            return this.x.bindProcessToNetwork(network);
        }
        Log.e("ERROR", "Get Network ERROR");
        return false;
    }

    private JSONObject r() {
        JSONObject jSONObject = new JSONObject();
        try {
            String b2 = SmartConfigDataProvider.a().b();
            String d2 = SmartConfigDataProvider.a().d();
            jSONObject.put("bind_key", this.av);
            jSONObject.put("config_type", this.u.type);
            String e2 = SelectServerUtils.e();
            if (!TextUtils.isEmpty(e2)) {
                jSONObject.put("country_domain", e2);
            }
            jSONObject.put(SmartConfigDataProvider.s, (Integer) SmartConfigDataProvider.a().a(SmartConfigDataProvider.s, 28800));
            if (d2 == null) {
                d2 = "";
            }
            jSONObject.put("passwd", d2);
            jSONObject.put(DeviceTagInterface.e, b2);
            jSONObject.put(d.o, SmartConfigDataProvider.a().a(SmartConfigDataProvider.t, ""));
            jSONObject.put("uid", Long.valueOf(CoreApi.a().s()));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(d.p, SelectServerUtils.d());
            jSONObject.put("wifi_config", jSONObject2);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    /* access modifiers changed from: private */
    public void s() {
        if (!CoreApi.a().q()) {
            d_(true);
            return;
        }
        LogUtilGrey.a(aq, "getToken, getGatewayAddr= " + n());
        if (this.aw == null && !this.ax) {
            this.aw = MiioLocalAPI.a(n(), (MiioLocalRpcResponse) new MiioLocalRpcResponse.MiioLocalRpcResponseSimple() {
                public void a(JSONObject jSONObject) {
                    MiioLocalAPI.Cancelable unused = ApSecureConfigStep.this.aw = null;
                    LogUtilGrey.a(ApSecureConfigStep.aq, "getToken onSuccess:" + jSONObject);
                    if (ApSecureConfigStep.this.k == 0 || TextUtils.isEmpty(ApSecureConfigStep.this.l)) {
                        ApSecureConfigStep.this.k = jSONObject.optLong("did");
                        ApSecureConfigStep.this.l = jSONObject.optString("token");
                        JSONObject jSONObject2 = new JSONObject();
                        try {
                            jSONObject2.put("id", ((PluginHostApi) PluginHostApi.instance()).generateNonce());
                            jSONObject2.put("method", "miIO.info");
                            jSONObject2.put("params", new JSONObject());
                            MiioLocalAPI.a(ApSecureConfigStep.this.n(), jSONObject2.toString(), ApSecureConfigStep.this.k, ApSecureConfigStep.this.l, (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
                                public void onResponse(String str) {
                                    JSONObject a2 = ApConfigStep.a(str);
                                    if (a2 != null) {
                                        ApSecureConfigStep.this.an = a2.optString("fw_ver");
                                    }
                                    LogUtilGrey.a(ApSecureConfigStep.aq, "async_rpc info version: " + ApSecureConfigStep.this.an + ", data = " + str);
                                    ApSecureConfigStep.this.t();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void a(int i, String str, Throwable th) {
                    MiioLocalAPI.Cancelable unused = ApSecureConfigStep.this.aw = null;
                    StringBuilder sb = new StringBuilder();
                    sb.append("getToken onFail:");
                    sb.append(str == null ? Log.getStackTraceString(th) : str.toString());
                    LogUtilGrey.a(ApSecureConfigStep.aq, sb.toString());
                    if (!ApSecureConfigStep.this.ag && !ApSecureConfigStep.this.ah) {
                        ApSecureConfigStep.this.s();
                    }
                }
            }, 3);
        }
    }

    /* access modifiers changed from: private */
    public void t() {
        SmartConfigDataProvider.a().b(SmartConfigDataProvider.J, Long.valueOf(this.k));
        SmartConfigDataProvider.a().b(SmartConfigDataProvider.G, this.l);
        DeviceFinder.a().a(String.valueOf(this.k), this.l);
        U_().sendEmptyMessage(130);
    }

    private void E() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", ((PluginHostApi) PluginHostApi.instance()).generateNonce());
            jSONObject.put("method", "miIO.info");
            MiioLocalAPI.a(n(), jSONObject.toString(), this.k, this.l, (MiioLocalRpcResponse) new MiioLocalRpcResponse.MiioLocalRpcResponseSimple() {
                public void a(JSONObject jSONObject) {
                    if (!ApSecureConfigStep.this.ax) {
                        LogUtilGrey.a(ApSecureConfigStep.aq, "checkSupportECDH onSuccess:" + jSONObject);
                        String optString = jSONObject.optString("miio_ver");
                        if (!TextUtils.isEmpty(optString)) {
                            String[] split = optString.split("\\.");
                            if (split.length < 3) {
                                return;
                            }
                            if (Integer.valueOf(split[2]).intValue() >= 2 || Integer.valueOf(split[1]).intValue() > 0 || Integer.valueOf(split[0]).intValue() > 0) {
                                ApSecureConfigStep.this.U_().sendEmptyMessage(130);
                                return;
                            }
                            return;
                        }
                        ApSecureConfigStep.this.U_().sendEmptyMessageDelayed(132, 1000);
                    }
                }

                public void a(int i, String str, Throwable th) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("checkSupportECDH onFail:");
                    if (str == null) {
                        str = Log.getStackTraceString(th);
                    }
                    sb.append(str);
                    LogUtilGrey.a(ApSecureConfigStep.aq, sb.toString());
                    if (!ApSecureConfigStep.this.ag && !ApSecureConfigStep.this.ah && ApSecureConfigStep.this.U_() != null) {
                        ApSecureConfigStep.this.U_().sendEmptyMessageDelayed(132, 1000);
                    }
                }
            });
        } catch (Exception e2) {
            LogUtilGrey.a(aq, "checkSupportECDH onFail:" + Log.getStackTraceString(e2));
            U_().sendEmptyMessageDelayed(132, 1000);
        }
    }

    private void F() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", ((PluginHostApi) PluginHostApi.instance()).generateNonce());
            jSONObject.put("method", APSecurePinStep.f22386a);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", 1);
            jSONObject.put("params", jSONObject2);
            String jSONObject3 = jSONObject.toString();
            LogUtilGrey.a(aq, "chooseECDH0:" + jSONObject3);
            MiioLocalAPI.a(n(), jSONObject3, this.k, this.l, (MiioLocalRpcResponse) new MiioLocalRpcResponse.MiioLocalRpcResponseSimple() {
                public void a(JSONObject jSONObject) {
                    int[] iArr;
                    int[] iArr2;
                    if (!ApSecureConfigStep.this.ax) {
                        LogUtilGrey.a(ApSecureConfigStep.aq, "chooseECDH0 result:" + jSONObject);
                        JSONObject optJSONObject = jSONObject.optJSONObject("ecdh");
                        JSONArray optJSONArray = optJSONObject.optJSONArray("curve_suites");
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("sign_suites");
                        ECCurve eCCurve = null;
                        int i = 0;
                        while (i < optJSONArray.length() && (eCCurve = ECCurve.search(optJSONArray.optInt(i))) == null) {
                            i++;
                        }
                        int i2 = 0;
                        for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                            i2 = optJSONArray2.optInt(i3);
                            if (ECCurve.SignType.index(i2) != null) {
                                break;
                            }
                        }
                        if (eCCurve == null || i2 == 0) {
                            Toast.makeText(SHApplication.getAppContext(), R.string.ble_new_auth_step_failed, 0).show();
                            return;
                        }
                        JSONObject optJSONObject2 = jSONObject.optJSONObject("oob");
                        if (optJSONObject2 != null) {
                            JSONArray optJSONArray3 = optJSONObject2.optJSONArray("modes");
                            JSONArray optJSONArray4 = optJSONObject2.optJSONArray("extents");
                            if (optJSONArray3 == null || optJSONArray3.length() <= 0) {
                                iArr = new int[]{optJSONObject2.optInt("modes")};
                            } else {
                                iArr = new int[optJSONArray3.length()];
                                for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                                    iArr[i4] = optJSONArray3.optInt(i4);
                                }
                            }
                            if (optJSONArray4 == null || optJSONArray4.length() <= 0) {
                                iArr2 = new int[]{optJSONObject2.optInt("extents")};
                            } else {
                                iArr2 = new int[optJSONArray4.length()];
                                for (int i5 = 0; i5 < optJSONArray4.length(); i5++) {
                                    iArr2[i5] = optJSONArray4.optInt(i5);
                                }
                            }
                            ApSecureConfigStep.this.a(eCCurve, i2, iArr, iArr2);
                            return;
                        }
                        ApSecureConfigStep.this.a(eCCurve, i2, (int[]) null, (int[]) null);
                    }
                }

                public void a(int i, String str, Throwable th) {
                    if (!ApSecureConfigStep.this.ag && !ApSecureConfigStep.this.ah && !ApSecureConfigStep.this.ax) {
                        ApSecureConfigStep.this.G();
                    }
                }
            });
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void G() {
        U_().sendEmptyMessageDelayed(130, 1000);
    }

    /* access modifiers changed from: private */
    public void a(ECCurve eCCurve, int i2, int[] iArr, int[] iArr2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", ((PluginHostApi) PluginHostApi.instance()).generateNonce());
            jSONObject.put("method", APSecurePinStep.f22386a);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", 2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("curve_suite", eCCurve.getIndex());
            jSONObject3.put("sign_suite", i2);
            jSONObject3.put(e.m, Base64.encodeToString(ECCPointConvert.b((ECPublicKey) eCCurve.generateKeyPair().getPublic()), 2));
            jSONObject2.put("ecdh", jSONObject3);
            jSONObject.put("params", jSONObject2);
            String jSONObject4 = jSONObject.toString();
            LogUtilGrey.a(aq, "chooseECDH:" + jSONObject4);
            final ECCurve eCCurve2 = eCCurve;
            final int i3 = i2;
            final int[] iArr3 = iArr;
            final int[] iArr4 = iArr2;
            MiioLocalAPI.a(n(), jSONObject4, this.k, this.l, (MiioLocalRpcResponse) new MiioLocalRpcResponse.MiioLocalRpcResponseSimple() {
                public void a(final JSONObject jSONObject) {
                    SHApplication.getGlobalHandler().post(new Runnable() {
                        public void run() {
                            if (!ApSecureConfigStep.this.ax) {
                                LogUtilGrey.a(ApSecureConfigStep.aq, "chooseECDH result:" + jSONObject);
                                JSONObject optJSONObject = jSONObject.optJSONObject("ecdh");
                                String str = "";
                                String str2 = "";
                                if (optJSONObject != null) {
                                    str = optJSONObject.optString(e.m);
                                    str2 = optJSONObject.optString("sign");
                                }
                                final byte[] verify = eCCurve2.verify(ECCurve.SignType.index(i3), ApSecureConfigStep.this.l, Base64.decode(str, 2), Base64.decode(str2, 2));
                                if (verify == null) {
                                    ApSecureConfigStep.this.G();
                                } else if (iArr3 == null || iArr3.length <= 0 || iArr4 == null || iArr4.length <= 0) {
                                    ApSecureConfigStep.this.a(verify, (String) null);
                                } else {
                                    int i = 0;
                                    int i2 = 0;
                                    boolean z = false;
                                    boolean z2 = false;
                                    while (i < iArr3.length && i < iArr4.length) {
                                        if (iArr3[i] == 2) {
                                            i2 = iArr4[i];
                                            z2 = true;
                                        } else if (iArr3[i] == 3) {
                                            z = true;
                                        }
                                        i++;
                                    }
                                    SmartConfigDataProvider.a().b("sign", verify);
                                    SmartConfigDataProvider.a().b("length", Integer.valueOf(i2));
                                    int i3 = iArr3[0];
                                    if (z) {
                                        i3 = 3;
                                    } else if (z2) {
                                        i3 = 2;
                                    }
                                    switch (i3) {
                                        case 2:
                                            ApSecureConfigStep.this.c(SmartConfigStep.Step.STEP_APSECURE_PIN_STEP);
                                            return;
                                        case 3:
                                            String str3 = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.U);
                                            if (TextUtils.isEmpty(str3)) {
                                                ApSecureConfigStep.this.U_().removeMessages(110);
                                                Intent intent = new Intent();
                                                intent.setClass(ApSecureConfigStep.this.af, ScanBarcodeActivity.class);
                                                intent.putExtra("from", 200);
                                                intent.putExtra(ScanBarcodeActivity.SHOW_ADD_MANULLY, z2);
                                                ((Activity) ApSecureConfigStep.this.af).startActivityForResult(intent, 1000);
                                                return;
                                            }
                                            if (ApSecureConfigStep.this.ay != null) {
                                                ApSecureConfigStep.this.ay.a();
                                            }
                                            ApSecureQRStep unused = ApSecureConfigStep.this.ay = new ApSecureQRStep(ApSecureConfigStep.this.k, ApSecureConfigStep.this.l, verify, new ApSecureQRStep.ApSecureQRCallback() {
                                                public void a(final String str) {
                                                    ApSecureConfigStep.this.U_().post(new Runnable() {
                                                        public void run() {
                                                            ApSecureConfigStep.this.a(verify, str);
                                                        }
                                                    });
                                                }

                                                public void a() {
                                                    ApSecureConfigStep.this.U_().post(new Runnable() {
                                                        public void run() {
                                                            ApSecureConfigStep.this.p();
                                                            ApSecureConfigStep.this.u();
                                                            int unused = ApSecureConfigStep.this.at = 1;
                                                        }
                                                    });
                                                }
                                            });
                                            ApSecureConfigStep.this.ay.a(str3);
                                            return;
                                        default:
                                            return;
                                    }
                                }
                            }
                        }
                    });
                }

                public void a(int i, String str, Throwable th) {
                    if (!ApSecureConfigStep.this.ag && !ApSecureConfigStep.this.ah && !ApSecureConfigStep.this.ax) {
                        ApSecureConfigStep.this.G();
                    }
                }
            }, 5000);
        } catch (Exception e2) {
            LogUtilGrey.a(aq, Log.getStackTraceString(e2));
        }
    }

    public void c() {
        this.ax = false;
        String str = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.l);
        byte[] bArr = (byte[]) SmartConfigDataProvider.a().a("sign");
        LogUtilGrey.a(aq, "onResumeStep mCurrentIndex:" + this.at + " iv:" + str + " shareKey:" + bArr);
        if (this.at != 1 || bArr == null) {
            super.c();
            return;
        }
        SmartConfigDataProvider.a().b("sign");
        if (TextUtils.isEmpty(str)) {
            p();
            u();
            this.at = 1;
            return;
        }
        a(bArr, str);
    }

    private void H() {
        if (!this.ax) {
            a(1);
            this.at = 2;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", ((PluginHostApi) PluginHostApi.instance()).generateNonce());
                jSONObject.put("method", "miIO.config_router");
                jSONObject.put("params", r());
            } catch (JSONException e2) {
                LogUtilGrey.a(aq, "makeNormalWifiJson error:" + Log.getStackTraceString(e2));
            }
            String jSONObject2 = jSONObject.toString();
            LogUtilGrey.a(aq, "Enter Normal wifi config:" + jSONObject2);
            MiioLocalAPI.a(n(), jSONObject2, this.k, this.l, (MiioLocalRpcResponse) new MiioLocalRpcResponse.MiioLocalRpcResponseSimple() {
                public void a(JSONObject jSONObject) {
                    LogUtilGrey.a(ApSecureConfigStep.aq, "sendWifi, onSuccess");
                    if (ApSecureConfigStep.this.U_() != null) {
                        ApSecureConfigStep.this.U_().sendEmptyMessageDelayed(112, 1000);
                    }
                }

                public void a(int i, String str, Throwable th) {
                    LogUtilGrey.a(ApSecureConfigStep.aq, "sendWifi, onFail:" + str);
                    if (!ApSecureConfigStep.this.ag && !ApSecureConfigStep.this.ah && !ApSecureConfigStep.this.ax) {
                        ApSecureConfigStep.this.u();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(byte[] bArr, String str) {
        if (!this.ax) {
            a(1);
            this.at = 2;
            LogUtilGrey.a(aq, "sharekey after decode = " + HexUtil.a(bArr));
            JSONObject jSONObject = new JSONObject();
            try {
                byte[] bArr2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                if (!TextUtils.isEmpty(str)) {
                    bArr2 = Base64.decode(str, 2);
                }
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, a.b);
                Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
                instance.init(1, secretKeySpec, new IvParameterSpec(bArr2));
                byte[] doFinal = instance.doFinal(r().toString().getBytes());
                jSONObject.put("id", ((PluginHostApi) PluginHostApi.instance()).generateNonce());
                jSONObject.put("method", "miIO.config_router_safe");
                JSONObject jSONObject2 = new JSONObject();
                String encodeToString = Base64.encodeToString(doFinal, 2);
                jSONObject2.put("data", encodeToString);
                LogUtilGrey.a(aq, "sendECDHWifi, datastr= " + encodeToString);
                String encodeToString2 = Base64.encodeToString(SecurityChipUtil.a(bArr, doFinal), 2);
                jSONObject2.put("sign", encodeToString2);
                LogUtilGrey.a(aq, "sendECDHWifi, signStr= " + encodeToString2);
                jSONObject.put("params", jSONObject2);
                MiioLocalAPI.a(n(), jSONObject.toString(), this.k, this.l, (MiioLocalRpcResponse) new MiioLocalRpcResponse.MiioLocalRpcResponseSimple() {
                    public void a(JSONObject jSONObject) {
                        LogUtilGrey.a(ApSecureConfigStep.aq, "sendECDHWifi, onSuccess");
                        if (ApSecureConfigStep.this.U_() != null) {
                            ApSecureConfigStep.this.U_().sendEmptyMessageDelayed(112, 1000);
                        }
                    }

                    public void a(int i, String str, Throwable th) {
                        LogUtilGrey.a(ApSecureConfigStep.aq, "sendECDHWifi, onFail:" + str);
                        if (!ApSecureConfigStep.this.ag && !ApSecureConfigStep.this.ah && !ApSecureConfigStep.this.ax) {
                            ApSecureConfigStep.this.u();
                        }
                    }
                }, 5000);
            } catch (Exception e2) {
                LogUtilGrey.a(aq, Log.getStackTraceString(e2));
            }
        }
    }

    private boolean I() {
        if (DeviceFinder.a().e()) {
            DeviceFinder.a().a(this.aA);
            return true;
        } else if (l() == null) {
            return false;
        } else {
            this.au = false;
            DeviceFinder.a().a(this.aA, l(), this.k == 0 ? null : String.valueOf(this.k), this.l, this.av);
            return false;
        }
    }

    public void e() {
        super.e();
        if (this.aw != null) {
            this.aw.a();
        }
        if (this.ay != null) {
            this.ay.a();
            this.ay = null;
        }
        DeviceFinder.a().c();
    }

    public void o() {
        U_().removeMessages(112);
        if (ApiHelper.e) {
            Log.e("WifiSettingUap", "Bind Network to NULL");
            this.x.bindProcessToNetwork((Network) null);
        }
    }

    public void p() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", ((PluginHostApi) PluginHostApi.instance()).generateNonce());
            jSONObject.put("method", "miIO.stop_diag_mode");
            jSONObject.put("params", "");
        } catch (JSONException unused) {
        }
        MiioLocalAPI.a(n(), jSONObject.toString(), this.k, this.l, (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
            public void onResponse(String str) {
            }
        });
    }

    public boolean a() {
        try {
            new MLAlertDialog.Builder(this.af).a((CharSequence) this.af.getString(R.string.stop_connect_device_title)).b((CharSequence) this.af.getString(R.string.stop_connect_device_message)).a((CharSequence) this.af.getString(R.string.confirm), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (ApSecureConfigStep.this.af != null) {
                        WifiManager wifiManager = (WifiManager) ApSecureConfigStep.this.af.getApplicationContext().getSystemService("wifi");
                        WifiInfo wifiInfo = null;
                        if (wifiManager != null) {
                            wifiInfo = wifiManager.getConnectionInfo();
                        }
                        if (!(ApSecureConfigStep.this.l() == null || wifiInfo == null || !ApSecureConfigStep.this.l().BSSID.equalsIgnoreCase(wifiManager.getConnectionInfo().getBSSID()))) {
                            WifiSettingUtils.b(ApSecureConfigStep.this.w, ApSecureConfigStep.this.l().SSID);
                        }
                        if (DeviceFinder.a().e()) {
                            DeviceFinder.a().b();
                        }
                        ApSecureConfigStep.this.d_(false);
                        if (!ApSecureConfigStep.this.al && ApSecureConfigStep.this.ak && !ApSecureConfigStep.this.am) {
                            if (ApSecureConfigStep.this.at == 0 || (ApSecureConfigStep.this.at == 3 && ApSecureConfigStep.this.x() && NetworkUtils.c())) {
                                ApSecureConfigStep.this.al = true;
                                STAT.c.a(ApSecureConfigStep.this.k, ApSecureConfigStep.this.aj, ApErrorCode.a(ApSecureConfigStep.this.at), ApSecureConfigStep.this.an);
                            }
                            STAT.c.d(ApSecureConfigStep.this.aj, 4);
                        }
                        STAT.c.d(ApSecureConfigStep.this.aj, ApSecureConfigStep.this.ai);
                    }
                }
            }).b((CharSequence) this.af.getString(R.string.cancel), (DialogInterface.OnClickListener) null).a(this.af.getResources().getColor(R.color.std_dialog_button_red), -1).d();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public void a(int i2, int i3, Intent intent) {
        if (i2 != 1000) {
            return;
        }
        if (i3 == -1) {
            String stringExtra = intent.getStringExtra(BleBindActivityV2.KEY_QRCODE_OOB);
            if (TextUtils.isEmpty(stringExtra)) {
                p();
                D();
                return;
            }
            U_().sendEmptyMessageDelayed(110, 20000);
            final byte[] bArr = (byte[]) SmartConfigDataProvider.a().a("sign");
            if (this.ay != null) {
                this.ay.a();
            }
            this.ay = new ApSecureQRStep(this.k, this.l, bArr, new ApSecureQRStep.ApSecureQRCallback() {
                public void a(final String str) {
                    ApSecureConfigStep.this.U_().post(new Runnable() {
                        public void run() {
                            ApSecureConfigStep.this.a(bArr, str);
                        }
                    });
                }

                public void a() {
                    ApSecureConfigStep.this.U_().post(new Runnable() {
                        public void run() {
                            ApSecureConfigStep.this.p();
                            ApSecureConfigStep.this.u();
                            int unused = ApSecureConfigStep.this.at = 1;
                        }
                    });
                }
            });
            this.ay.a(stringExtra);
        } else if (i3 == -3) {
            c(SmartConfigStep.Step.STEP_APSECURE_PIN_STEP);
        } else {
            p();
            D();
        }
    }
}
