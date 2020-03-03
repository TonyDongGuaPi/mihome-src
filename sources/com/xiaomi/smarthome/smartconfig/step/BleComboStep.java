package com.xiaomi.smarthome.smartconfig.step;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.miui.whetstone.WhetstoneResult;
import com.miui.whetstone.WhetstoneResultBinder;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.miio.MiioLocalAPI;
import com.xiaomi.miio.MiioLocalRpcResponse;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.KuailianManager;
import com.xiaomi.smarthome.device.api.CameraApi;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BluetoothManager;
import com.xiaomi.smarthome.device.bluetooth.connect.combo.BleComboConnector;
import com.xiaomi.smarthome.device.bluetooth.connect.combo.ComboConnectResponse;
import com.xiaomi.smarthome.device.bluetooth.connect.combo.ComboRestoreResponse;
import com.xiaomi.smarthome.device.bluetooth.connect.combo.IBleComboConnector;
import com.xiaomi.smarthome.device.bluetooth.ui.CommonBindView;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
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
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.ApiHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.library.common.statcode.ComboErrorCode;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.ConfigStep;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.wificonfig.WifiDeviceFinder;
import com.xiaomi.smarthome.wificonfig.WifiSettingUtils;
import com.xiaomi.stat.d;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BleComboStep extends ConfigStep implements ComboConnectResponse {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f22473a = false;
    private static final String b = "hannto.printer.honey";
    private static final int c = 0;
    private static final int d = 1;
    private static final int e = 2;
    private static final int f = 62000;
    private static final int g = 90000;
    private static final int h = 50000;
    private static final int i = 60000;
    private static final int j = 60000;
    private static final String k = "BleComboStep";
    /* access modifiers changed from: private */
    public long aA = -1;
    private long[] aB = new long[4];
    /* access modifiers changed from: private */
    public long[] aC = new long[4];
    /* access modifiers changed from: private */
    public boolean aD = false;
    /* access modifiers changed from: private */
    public boolean aE = false;
    private BroadcastReceiver aF = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                String stringExtra = intent.getStringExtra("key_device_address");
                int intExtra = intent.getIntExtra("key_connect_status", 5);
                if (TextUtils.equals(stringExtra, BleComboStep.this.av)) {
                    BluetoothMyLogger.d("BleComboStep bluetooth connect status = " + intExtra);
                }
                if ("com.xiaomi.smarthome.bluetooth.connect_status_changed".equalsIgnoreCase(action) && TextUtils.equals(stringExtra, BleComboStep.this.av) && intExtra == 32) {
                    BluetoothMyLogger.d("BleComboStep disconnect mac = " + BluetoothMyLogger.a(BleComboStep.this.av));
                    boolean unused = BleComboStep.this.l = false;
                    boolean unused2 = BleComboStep.this.aE = true;
                    BleComboStep.this.U_().removeMessages(127);
                    BleComboStep.this.b(0);
                    BLEDeviceManager.a(BleComboStep.this.aj, BleComboStep.this.av);
                    WifiDeviceFinder.a(BleComboStep.this.ao);
                }
            }
        }
    };
    private ConfigStep.DeviceFinderCallback aG = new DeviceFinder.DeviceFinderCallback2() {
        public void a(int i) {
            if (i == -6) {
                BleComboStep.this.a_(SmartConfigStep.Step.STEP_BIND_BY_OTHER_ERROR);
            }
            WifiDeviceFinder.a(BleComboStep.this.ao);
        }

        public void a(List<Device> list) {
            BluetoothMyLogger.f("BleComboStep DeviceFinderCallback.onDeviceConnectionSuccess");
            int unused = BleComboStep.this.ap = 2;
            BleComboStep.this.ak = false;
            BleComboStep.this.a(2);
            WifiDeviceFinder.a(BleComboStep.this.ao);
        }

        public void a() {
            BluetoothMyLogger.f("BleComboStep DeviceFinderCallback.onDeviceConnectionFailure");
            WifiDeviceFinder.a(BleComboStep.this.ao);
        }

        public void b(List<Device> list) {
            BluetoothMyLogger.f("BleComboStep DeviceFinderCallback.onDeviceConnectionBind");
            int unused = BleComboStep.this.ap = 2;
            BleComboStep.this.ak = false;
            BleComboStep.this.a(2);
            WifiDeviceFinder.a(BleComboStep.this.ao);
        }
    };
    /* access modifiers changed from: private */
    public int ap;
    /* access modifiers changed from: private */
    public long aq;
    /* access modifiers changed from: private */
    public String ar;
    private int as = 0;
    /* access modifiers changed from: private */
    public String at = "";
    /* access modifiers changed from: private */
    public IBleComboConnector au;
    /* access modifiers changed from: private */
    public String av;
    /* access modifiers changed from: private */
    public int aw;
    private Handler ax;
    private MiioLocalAPI.Cancelable ay;
    private boolean az = true;
    /* access modifiers changed from: private */
    public boolean l = true;

    public SmartConfigStep.Step f() {
        return null;
    }

    static /* synthetic */ int f(BleComboStep bleComboStep) {
        int i2 = bleComboStep.as;
        bleComboStep.as = i2 + 1;
        return i2;
    }

    public void a(Context context) {
        this.ax = new Handler(Looper.getMainLooper());
        SmartConfigDataProvider.a().b(SmartConfigDataProvider.Q, 4);
        super.a(context);
        STAT.c.h(this.aj);
        if (!BluetoothUtils.b()) {
            this.ak = false;
        }
    }

    /* access modifiers changed from: private */
    public void b(long j2) {
        if (U_() != null) {
            U_().sendEmptyMessageDelayed(112, j2);
        }
    }

    public void a(int i2) {
        d(i2);
        super.a(i2);
    }

    private void d(int i2) {
        STAT.c.a(this.aj, this.aq, ComboErrorCode.a(i2), (int) ((System.currentTimeMillis() - this.aB[i2]) / 1000));
        this.aB[this.ap] = 0;
    }

    public ArrayList<ConfigStep.ConfigTime> O_() {
        BluetoothMyLogger.f("BleComboStep.getAllConfigStages");
        ArrayList<ConfigStep.ConfigTime> arrayList = new ArrayList<>();
        ConfigStep.ConfigTime configTime = new ConfigStep.ConfigTime();
        configTime.f22586a = 0;
        configTime.b = 90000;
        arrayList.add(configTime);
        ConfigStep.ConfigTime configTime2 = new ConfigStep.ConfigTime();
        configTime2.f22586a = 1;
        configTime2.b = 60000;
        arrayList.add(configTime2);
        ConfigStep.ConfigTime configTime3 = new ConfigStep.ConfigTime();
        configTime3.f22586a = 2;
        configTime3.b = 60000;
        arrayList.add(configTime3);
        return arrayList;
    }

    public void a(Message message) {
        int i2 = message.what;
        if (i2 == 101) {
            BluetoothMyLogger.f("BleComboStep.handleMessage NETWORK_STATE_CHANGED");
            if (this.v && !this.l) {
                NetworkInfo networkInfo = (NetworkInfo) message.obj;
                NetworkInfo.DetailedState detailedState = networkInfo.getDetailedState();
                WifiInfo connectionInfo = this.w.getConnectionInfo();
                this.ao = l();
                if (this.ap == 1 && networkInfo.getState() == NetworkInfo.State.DISCONNECTED && !this.ah) {
                    if (connectionInfo == null || this.ao == null || !WifiSettingUtils.a(connectionInfo.getSSID(), this.ao.SSID)) {
                        U_().sendEmptyMessage(112);
                        return;
                    }
                    LogUtilGrey.a(k, "DISCONNECTED ScanResult:" + this.ao.SSID + " wifiInfo:" + connectionInfo);
                }
                if (connectionInfo == null || TextUtils.isEmpty(connectionInfo.getSSID()) || connectionInfo.getSSID().contains("<unknown ssid>")) {
                    LogUtilGrey.a(k, "error unknown wifiInfo:" + connectionInfo);
                } else if (detailedState != NetworkInfo.DetailedState.CONNECTED || !networkInfo.isConnected()) {
                    LogUtilGrey.a(k, "error otherstate state:" + detailedState);
                } else if (this.ao == null || !WifiSettingUtils.a(connectionInfo.getSSID(), this.ao.SSID) || this.ap != 0 || this.ah) {
                    LogUtilGrey.a(k, "CONNECTED but wifiInfo:" + connectionInfo + " ScanResult:" + this.ao + " mCurrentIndex:" + this.ap + " mIsPaused:" + this.ah);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(detailedState.toString());
                    sb.append(", ");
                    sb.append(connectionInfo.getSSID() != null ? connectionInfo.getSSID() : "");
                    sb.append(", ");
                    sb.append(networkInfo.getExtraInfo() != null ? networkInfo.getExtraInfo() : "");
                    sb.append(", ");
                    sb.append(networkInfo.getReason() != null ? networkInfo.getReason() : "");
                    LogUtilGrey.a("WifiStateConfig", sb.toString());
                    a(this.ap);
                    U_().removeMessages(123);
                    r();
                    this.ap = 1;
                    this.as++;
                }
            }
        } else if (i2 == 112) {
            BluetoothMyLogger.f("BleComboStep.handleMessage MSG_UPDATE_DEVICE_STATE");
            if (U_() != null) {
                U_().removeMessages(112);
                U_().removeMessages(114);
            }
            this.l = false;
            if (this.ap == 1) {
                s();
                a(1);
                this.ap = 2;
                t();
            }
        } else if (i2 == 123) {
            BluetoothMyLogger.f("BleComboStep.handleMessage MSG_RECONNECT_DEVICE_AP");
            q();
            this.l = false;
        } else if (i2 == 125) {
            BluetoothMyLogger.f("BleComboStep.handleMessage MSG_CONNECT_BLE_TIME_OUT");
            this.l = false;
            Log.i("stopScan", "BComboS stop");
            CoreApi.a().W();
            try {
                SHApplication.getAppContext().unregisterReceiver(this.aF);
            } catch (Exception unused) {
            }
            this.au.d();
            if (G()) {
                if (!this.al && this.ak) {
                    this.al = true;
                    STAT.c.a(this.aq, this.aj, ComboErrorCode.d, 1, this.ao == null ? "" : this.ao.SSID, this.av, this.an);
                }
                STAT.c.d(this.aj, 1);
                a_(SmartConfigStep.Step.STEP_BLE_CONNECT_ERROR);
                return;
            }
            q();
        } else if (i2 != 127) {
            switch (i2) {
                case 114:
                    BluetoothMyLogger.f("BleComboStep.handleMessage MSG_SEND_DEVICE_MSG");
                    if (this.ap == 1) {
                        r();
                        return;
                    }
                    return;
                case 115:
                    BluetoothMyLogger.f("BleComboStep.handleMessage MSG_DISCONNECT_TIME_OUT");
                    s();
                    return;
                default:
                    super.a(message);
                    return;
            }
        } else {
            BluetoothMyLogger.f("BleComboStep.handleMessage MSG_BLE_NOTIFY_TIME_OUT, current notifyStatus = " + this.aw);
            U_().removeMessages(127);
            this.l = false;
            try {
                SHApplication.getAppContext().unregisterReceiver(this.aF);
            } catch (Exception unused2) {
            }
            this.au.d();
            b(0);
        }
    }

    public void k() {
        BluetoothMyLogger.f("BleComboStep resetCurrentStageIndex");
        this.ap = 0;
        this.l = true;
        this.aA = System.currentTimeMillis();
        Arrays.fill(this.aC, 0);
        this.aC[this.ap] = System.currentTimeMillis();
    }

    public int b() {
        switch (this.ap) {
            case 0:
                BluetoothMyLogger.f("BleComboStep.getCurrentStageIndex mCurrentIndex = CONNECT_INDEX, mUseBleConfig = " + this.l);
                return this.l ? 0 : 0;
            case 1:
                BluetoothMyLogger.f("BleComboStep.getCurrentStageIndex mCurrentIndex = SEND_SSID_AND_PASSWD_INDEX, mUseBleConfig = " + this.l);
                if (!this.l) {
                    String str = this.ao.SSID;
                    WifiInfo connectionInfo = this.w.getConnectionInfo();
                    if (connectionInfo != null && WifiSettingUtils.a(str, connectionInfo.getSSID())) {
                        return ((Boolean) SmartConfigDataProvider.a().a(SmartConfigDataProvider.v, false)).booleanValue() ? 2 : 1;
                    }
                    this.ap = 0;
                    return 0;
                }
                break;
        }
        return this.ap;
    }

    public SmartConfigStep.Step b(int i2) {
        this.am = true;
        switch (i2) {
            case 0:
                BluetoothMyLogger.f("BleComboStep.onStageTimeOut CONNECT_INDEX, mUseBleConfig = " + this.l);
                if (!this.al && this.ak) {
                    this.al = true;
                    STAT.c.a(this.aq, this.aj, 2101, this.l ? 1 : 0, this.an);
                }
                if (this.l) {
                    return SmartConfigStep.Step.STEP_CONNECT_AP_ERROR;
                }
                MobclickAgent.a(this.af, MiStatType.CLICK.getValue(), "connect_ap_connect_timeout");
                StatHelper.d("connect_ap_connect_timeout");
                U_().removeMessages(123);
                WifiDeviceFinder.a(this.ao);
                STAT.c.d(this.aj, 1);
                return SmartConfigStep.Step.STEP_CONNECT_AP_ERROR;
            case 1:
                BluetoothMyLogger.f("BleComboStep.onStageTimeOut SEND_SSID_AND_PASSWD_INDEX, mUseBleConfig = " + this.l);
                if (!this.al && this.ak) {
                    this.al = true;
                    STAT.c.a(this.aq, this.aj, (int) ComboErrorCode.b, this.l ? 1 : 0, this.an);
                }
                if (!this.l) {
                    if (this.as == 1) {
                        return SmartConfigStep.Step.STEP_SEND_ROUTER_INFO_ERROR;
                    }
                    PluginRecord d2 = CoreApi.a().d(DeviceFactory.a(this.ao));
                    if (d2 != null && d2.c().C() != 0) {
                        return SmartConfigStep.Step.STEP_SEND_ROUTER_INFO_FINAL_ERROR;
                    }
                    WifiDeviceFinder.a(this.ao);
                    return SmartConfigStep.Step.STEP_SEND_ROUTER_INFO_ERROR;
                } else if (this.aw == 0) {
                    return SmartConfigStep.Step.STEP_BLE_SEND_ERROR;
                } else {
                    if (this.aw == 1) {
                        BluetoothMyLogger.f(">>> current notifyStatus == NOTIFY_CONNECTING_ROUTER");
                        return SmartConfigStep.Step.STEP_BLE_SEND_ERROR;
                    } else if (this.aw == 2) {
                        BluetoothMyLogger.f(">>> current notifyStatus == NOTIFY_CONNECTING_ROUTER");
                        return SmartConfigStep.Step.STEP_BLE_SEND_ERROR;
                    } else if (this.aw == 5) {
                        BluetoothMyLogger.f(">>> current notifyStatus == NOTIFY_AUTHENTICATION_ERROR");
                        E();
                        return SmartConfigStep.Step.STEP_BLE_PWD_ERROR;
                    } else if (this.aw == 4) {
                        BluetoothMyLogger.f(">>> current notifyStatus == NOTIFY_UNKNONW_ERROR");
                        return SmartConfigStep.Step.STEP_BLE_SEND_ERROR;
                    } else if (this.aw == 3) {
                        return SmartConfigStep.Step.STEP_BLE_SEND_ERROR;
                    } else {
                        BluetoothMyLogger.f(String.format(">>> Unknown notifyStatus = %d", new Object[]{Integer.valueOf(this.aw)}));
                        return null;
                    }
                }
            case 2:
                BluetoothMyLogger.f("BleComboStep.onStageTimeOut GET_NEW_DEVICE_INDEX，mUseBleConfig = " + this.l);
                w();
                if (!this.al && this.ak && NetworkUtils.c() && (this.l || x())) {
                    this.al = true;
                    STAT.c.a(this.aq, this.aj, ComboErrorCode.a(this.aD, this.aE, this.aw), this.aw == 3 ? 2 : this.aD, this.ao == null ? "" : this.ao.SSID, this.av, this.an);
                }
                if (this.l) {
                    return null;
                }
                MobclickAgent.a(this.af, MiStatType.CLICK.getValue(), "connect_get_device_timeout");
                StatHelper.d("connect_get_device_timeout");
                DeviceFinder.a().b();
                PluginRecord d3 = CoreApi.a().d(DeviceFactory.a(this.ao));
                if (d3 != null) {
                    Log.e("AoConfigStep", "" + d3.c().C());
                }
                if (d3 != null && d3.c().C() != 0) {
                    return SmartConfigStep.Step.STEP_FIND_DEVICE_FAILED_ERROR;
                }
                WifiDeviceFinder.a(this.ao);
                return SmartConfigStep.Step.STEP_FIND_DEVICE_FAILED;
            default:
                return null;
        }
    }

    public void h() {
        this.mCommonBindView.addNextStep((int) R.string.ble_combo_connect_device_step_loading, (int) R.string.ble_combo_connect_title_loading);
        this.mCommonBindView.startProgressAnimation(1);
    }

    public void i() {
        switch (this.ap) {
            case 0:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.ble_combo_connect_device_step_success, (int) R.string.ble_combo_connect_title_loading);
                this.mCommonBindView.addNextStep((int) R.string.ble_combo_transfer_device_step_loading, (int) R.string.ble_combo_connect_title_loading);
                this.mCommonBindView.startProgressAnimation(2);
                return;
            case 1:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.ble_combo_transfer_device_step_success, (int) R.string.ble_combo_connect_title_loading);
                Resources resources = this.mCommonBindView.getResources();
                CommonBindView commonBindView = this.mCommonBindView;
                StringBuilder sb = new StringBuilder();
                sb.append(resources.getString(R.string.ble_combo_device_connect_network_step_loading));
                sb.append(resources.getString(this.az ? R.string.kuailian_connect_ble : R.string.kuailian_connect_ap));
                commonBindView.addNextStep(sb.toString(), resources.getString(R.string.ble_combo_network_title_loading));
                this.mCommonBindView.startProgressAnimation(3);
                return;
            case 2:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.ble_combo_device_connect_network_step_success, (int) R.string.ble_combo_network_title_loading);
                return;
            default:
                return;
        }
    }

    public void j() {
        switch (this.ap) {
            case 0:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.ble_combo_connect_device_step_loading, (int) R.string.ble_combo_connect_title_loading);
                this.mCommonBindView.startProgressAnimation(1);
                return;
            case 1:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.ble_combo_transfer_device_step_loading, (int) R.string.ble_combo_connect_title_loading);
                this.mCommonBindView.startProgressAnimation(2);
                return;
            case 2:
                Resources resources = this.mCommonBindView.getResources();
                CommonBindView commonBindView = this.mCommonBindView;
                CommonBindView.StepStatus stepStatus = CommonBindView.StepStatus.LOADING;
                StringBuilder sb = new StringBuilder();
                sb.append(resources.getString(R.string.ble_combo_device_connect_network_step_loading));
                sb.append(resources.getString(this.az ? R.string.kuailian_connect_ble : R.string.kuailian_connect_ap));
                commonBindView.updateCurrentStep(stepStatus, sb.toString(), R.string.ble_combo_network_title_loading, false);
                this.mCommonBindView.startProgressAnimation(3);
                return;
            default:
                return;
        }
    }

    private void E() {
        this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.ble_combo_transfer_device_step_success, (int) R.string.ble_combo_connect_title_loading);
        Resources resources = this.mCommonBindView.getResources();
        CommonBindView commonBindView = this.mCommonBindView;
        StringBuilder sb = new StringBuilder();
        sb.append(resources.getString(R.string.ble_combo_device_connect_network_step_loading));
        sb.append(resources.getString(this.az ? R.string.kuailian_connect_ble : R.string.kuailian_connect_ap));
        commonBindView.addNextStep(sb.toString(), resources.getString(R.string.ble_combo_network_title_loading));
    }

    /* renamed from: c */
    public void f(int i2) {
        if (l() == null) {
            Handler U_ = U_();
            LogUtilGrey.a(k, "startConnection mScanResult is null");
            if (U_ != null && this.y) {
                U_.postDelayed(new Runnable(i2) {
                    private final /* synthetic */ int f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        BleComboStep.this.f(this.f$1);
                    }
                }, 2000);
                return;
            }
            return;
        }
        switch (i2) {
            case 0:
                BluetoothMyLogger.f("BleComboStep.startConnection CONNECT_INDEX, mUseBleConfig = " + this.l);
                long currentTimeMillis = System.currentTimeMillis();
                if (this.aA <= 0) {
                    this.aA = currentTimeMillis;
                }
                if (this.aC[i2] == 0) {
                    this.aC[i2] = currentTimeMillis;
                }
                a((AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
                    /* renamed from: a */
                    public void onSuccess(String str) {
                        LogUtilGrey.a(BleComboStep.k, "getBindKey " + BleComboStep.this.at);
                        String unused = BleComboStep.this.at = str;
                        if (BleComboStep.this.at == null) {
                            String unused2 = BleComboStep.this.at = "";
                        }
                        if (BleComboStep.this.l) {
                            BleComboStep.this.o();
                        } else {
                            BleComboStep.this.q();
                        }
                    }

                    public void onFailure(Error error) {
                        BluetoothMyLogger.d("BleComboStep getBindKey failed");
                        String unused = BleComboStep.this.at = "";
                        if (BleComboStep.this.l) {
                            BleComboStep.this.o();
                        } else {
                            BleComboStep.this.q();
                        }
                    }
                });
                return;
            case 1:
                BluetoothMyLogger.f("BleComboStep.startConnection SEND_SSID_AND_PASSWD_INDEX, mUseBleConfig = " + this.l);
                if (this.l) {
                    BluetoothMyLogger.d(">>> current mNotifyStatus = " + e(this.aw));
                    if (this.aw == 0) {
                        p();
                        return;
                    } else if (this.aw == 4) {
                        F();
                        return;
                    } else if (this.aw == 3) {
                        this.l = true;
                        U_().sendEmptyMessageDelayed(127, 50000);
                        d_(this.aw);
                        return;
                    } else if (BluetoothUtils.c(this.av)) {
                        this.l = true;
                        U_().sendEmptyMessageDelayed(127, 50000);
                        d_(this.aw);
                        return;
                    } else {
                        this.l = false;
                        U_().removeMessages(127);
                        b(0);
                        return;
                    }
                } else {
                    r();
                    this.as++;
                    return;
                }
            case 2:
                BluetoothMyLogger.f("BleComboStep.startConnection GET_NEW_DEVICE_INDEX");
                BluetoothMyLogger.d(">>> current mNotifyStatus = " + e(this.aw));
                s();
                t();
                return;
            default:
                return;
        }
    }

    private void a(final AsyncCallback<String, Error> asyncCallback) {
        CameraApi.getInstance().getBindKey(this.af, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                asyncCallback.onSuccess(str);
            }

            public void onFailure(Error error) {
                asyncCallback.onFailure(error);
            }
        });
    }

    private void F() {
        BluetoothMyLogger.d("BleComboStep.retryComboConnect");
        this.aw = 0;
        this.l = true;
        this.au.c();
        U_().removeMessages(125);
        U_().removeMessages(127);
        this.au.a();
        U_().sendEmptyMessageDelayed(127, 50000);
    }

    public Handler U_() {
        Handler U_ = super.U_();
        return U_ != null ? U_ : this.ax;
    }

    public void o() {
        BluetoothMyLogger.f("BleComboStep.startConnectToBle");
        this.az = true;
        this.ap = 0;
        this.aB[this.ap] = System.currentTimeMillis();
        if (this.aC[this.ap] == 0) {
            this.aC[this.ap] = System.currentTimeMillis();
        }
        if (this.au == null) {
            this.au = new BleComboConnector(this);
        }
        if (this.ao != null) {
            this.au.a(this.ao);
        } else {
            d_(true);
        }
        U_().sendEmptyMessageDelayed(125, 62000);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0041, code lost:
        if (com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothApi.a(r0.d().f14276a.l) == false) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r12) {
        /*
            r11 = this;
            java.lang.String r0 = "BleComboStep.onSearchComboAddress, mac = %s"
            r1 = 1
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = com.xiaomi.smarthome.frame.log.BluetoothMyLogger.a(r12)
            r4 = 0
            r2[r4] = r3
            java.lang.String r0 = java.lang.String.format(r0, r2)
            com.xiaomi.smarthome.frame.log.BluetoothMyLogger.f(r0)
            boolean r0 = android.text.TextUtils.isEmpty(r12)
            if (r0 != 0) goto L_0x007f
            com.xiaomi.smarthome.device.BleDevice r0 = com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager.d((java.lang.String) r12)
            if (r0 == 0) goto L_0x0044
            com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket r2 = r0.d()
            if (r2 == 0) goto L_0x0044
            com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket r2 = r0.d()
            com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket$FrameControl r2 = r2.f14276a
            if (r2 == 0) goto L_0x0044
            com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket r2 = r0.d()
            com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket$FrameControl r2 = r2.f14276a
            int r2 = r2.k
            com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket r0 = r0.d()
            com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket$FrameControl r0 = r0.f14276a
            int r0 = r0.l
            boolean r0 = com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothApi.a((int) r0)
            if (r0 != 0) goto L_0x0045
            goto L_0x0046
        L_0x0044:
            r2 = 0
        L_0x0045:
            r4 = 1
        L_0x0046:
            if (r2 != 0) goto L_0x005b
            if (r4 == 0) goto L_0x005b
            com.xiaomi.smarthome.framework.statistic.StatHelper.s()
            r11.av = r12
            com.xiaomi.smarthome.device.bluetooth.connect.combo.IBleComboConnector r12 = r11.au
            java.lang.String r0 = r11.at
            com.xiaomi.smarthome.smartconfig.step.ConfigStep$ConnectType r1 = r11.u
            java.lang.String r1 = r1.type
            r12.a(r0, r1)
            goto L_0x00c9
        L_0x005b:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "don't support authMode = "
            r12.append(r0)
            r12.append(r2)
            java.lang.String r12 = r12.toString()
            com.xiaomi.smarthome.frame.log.BluetoothMyLogger.f(r12)
            android.os.Handler r12 = com.xiaomi.smarthome.application.CommonApplication.getGlobalHandler()
            com.xiaomi.smarthome.smartconfig.step.BleComboStep$5 r0 = new com.xiaomi.smarthome.smartconfig.step.BleComboStep$5
            r0.<init>()
            r12.post(r0)
            r11.d_(r1)
            goto L_0x00c9
        L_0x007f:
            com.xiaomi.smarthome.framework.statistic.StatHelper.r()
            boolean r12 = r11.G()
            if (r12 == 0) goto L_0x00af
            boolean r12 = r11.al
            if (r12 != 0) goto L_0x00af
            boolean r12 = r11.ak
            if (r12 == 0) goto L_0x00af
            r11.al = r1
            com.xiaomi.smarthome.stat.StatPagev2 r2 = com.xiaomi.smarthome.stat.STAT.c
            long r3 = r11.aq
            java.lang.String r5 = r11.aj
            r6 = 2701(0xa8d, float:3.785E-42)
            r7 = 1
            android.net.wifi.ScanResult r12 = r11.ao
            if (r12 != 0) goto L_0x00a3
            java.lang.String r12 = ""
        L_0x00a1:
            r8 = r12
            goto L_0x00a8
        L_0x00a3:
            android.net.wifi.ScanResult r12 = r11.ao
            java.lang.String r12 = r12.SSID
            goto L_0x00a1
        L_0x00a8:
            java.lang.String r9 = r11.av
            java.lang.String r10 = r11.an
            r2.a(r3, r5, r6, r7, r8, r9, r10)
        L_0x00af:
            android.os.Handler r12 = r11.U_()
            r0 = 125(0x7d, float:1.75E-43)
            boolean r12 = r12.hasMessages(r0)
            if (r12 == 0) goto L_0x00c9
            android.os.Handler r12 = r11.U_()
            r12.removeMessages(r0)
            android.os.Handler r12 = r11.U_()
            r12.sendEmptyMessage(r0)
        L_0x00c9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.smartconfig.step.BleComboStep.a(java.lang.String):void");
    }

    public void a(int i2, String str) {
        BluetoothMyLogger.f("BleComboStep.onSendSSIDAndPassWd code = " + i2 + ", firmwareVersion = " + str);
        this.an = str;
        if (i2 == 0) {
            StatHelper.t();
            if (this.l && !this.ag) {
                this.aD = true;
                SHApplication.getAppContext().registerReceiver(this.aF, new IntentFilter("com.xiaomi.smarthome.bluetooth.connect_status_changed"));
                U_().removeMessages(125);
                this.au.a();
                a(this.ap);
                this.aB[1] = System.currentTimeMillis();
                f(1);
                if (this.aC[1] == 0) {
                    this.aC[1] = System.currentTimeMillis();
                }
                U_().sendEmptyMessageDelayed(127, 50000);
                return;
            }
            return;
        }
        StatHelper.u();
        if (U_().hasMessages(125)) {
            U_().removeMessages(125);
            if (G() && !this.al && this.ak) {
                int b2 = ComboErrorCode.b(i2);
                this.al = true;
                STAT.c.a(this.aq, this.aj, b2, 1, this.ao == null ? "" : this.ao.SSID, this.av, this.an);
            }
            if (i2 == -6) {
                BluetoothManager.getInstance().disconnect(this.av);
                U_().sendEmptyMessageDelayed(125, 2000);
                return;
            }
            U_().sendEmptyMessage(125);
        }
    }

    public void p() {
        this.ap = 1;
    }

    private String e(int i2) {
        switch (i2) {
            case 0:
                return "NOTIFY_START";
            case 1:
                return "connecting router";
            case 2:
                return "router connected";
            case 3:
                return "server connected";
            case 4:
                return "unknown error";
            case 5:
                return "pwd error";
            default:
                return "unknown " + i2;
        }
    }

    public void d_(int i2) {
        this.aw = i2;
        BluetoothMyLogger.d("onNotifyStatus " + e(i2));
        if (this.l && U_().hasMessages(127)) {
            switch (i2) {
                case 3:
                    try {
                        SHApplication.getAppContext().unregisterReceiver(this.aF);
                    } catch (Exception unused) {
                    }
                    this.au.d();
                    U_().removeMessages(127);
                    b(0);
                    StatHelper.x();
                    return;
                case 4:
                case 5:
                    try {
                        SHApplication.getAppContext().unregisterReceiver(this.aF);
                    } catch (Exception unused2) {
                    }
                    U_().removeMessages(127);
                    u();
                    return;
                default:
                    return;
            }
        }
    }

    public void e() {
        super.e();
        if (this.ay != null) {
            this.ay.a();
        }
        try {
            SHApplication.getAppContext().unregisterReceiver(this.aF);
        } catch (Exception unused) {
        }
        this.ax.removeCallbacksAndMessages((Object) null);
        Log.i("stopScan", "BComboS finish stop");
        BluetoothHelper.b();
        boolean booleanValue = ((Boolean) SmartConfigDataProvider.a().a(SmartConfigDataProvider.E, false)).booleanValue();
        BluetoothMyLogger.d(String.format("wifi restore ? -> %b", new Object[]{Boolean.valueOf(booleanValue)}));
        if (this.au == null) {
            return;
        }
        if (booleanValue) {
            this.au.a((ComboRestoreResponse) new ComboRestoreResponse() {
                public void a(int i) {
                    BleComboStep.this.au.d();
                }
            });
        } else {
            this.au.d();
        }
    }

    public void q() {
        PluginRecord d2;
        BluetoothMyLogger.f("BleComboStep.startConnectToDeviceAp");
        this.az = false;
        String i2 = DeviceFactory.i(this.ao.SSID);
        String str = "";
        if (!(i2 == null || (d2 = CoreApi.a().d(i2)) == null || TextUtils.isEmpty(d2.c().u()))) {
            str = d2.c().u();
        }
        this.ap = 0;
        this.aB[this.ap] = System.currentTimeMillis();
        String c2 = WifiUtil.c(this.af);
        WifiInfo connectionInfo = this.w.getConnectionInfo();
        if (TextUtils.isEmpty(c2) || !c2.equals(this.ao.SSID) || connectionInfo == null || connectionInfo.getSupplicantState() != SupplicantState.COMPLETED) {
            DeviceFactory.AP_TYPE d3 = DeviceFactory.d(this.ao);
            if (d3 == DeviceFactory.AP_TYPE.AP_MIDEA || d3 == DeviceFactory.AP_TYPE.AP_MIDEA_AC) {
                str = "12345678";
            }
            WifiSettingUtils.a(this.w, this.ao.SSID, str, this.ao.BSSID, this.ao.capabilities);
            U_().sendEmptyMessageDelayed(123, 15000);
        } else if (U_() != null) {
            U_().post(new Runnable() {
                public void run() {
                    if (BleComboStep.this.ap != 1) {
                        BleComboStep.this.r();
                        BleComboStep.this.a(BleComboStep.this.ap);
                        int unused = BleComboStep.this.ap = 1;
                        BleComboStep.f(BleComboStep.this);
                    }
                }
            });
        }
    }

    public void r() {
        Network network;
        BluetoothMyLogger.f("BleComboStep.startConnectionAsso");
        WifiDeviceFinder.m.clear();
        this.ap = 1;
        if (this.aC[this.ap] == 0) {
            this.aC[this.ap] = System.currentTimeMillis();
        }
        this.aB[this.ap] = System.currentTimeMillis();
        if (ApiHelper.e) {
            Network[] allNetworks = this.x.getAllNetworks();
            int i2 = 0;
            while (true) {
                if (i2 >= allNetworks.length) {
                    network = null;
                    break;
                }
                NetworkInfo networkInfo = this.x.getNetworkInfo(allNetworks[i2]);
                if (networkInfo != null && networkInfo.getType() == 1) {
                    network = allNetworks[i2];
                    break;
                }
                i2++;
            }
            if (network == null) {
                Log.e("ERROR", "Get Network ERROR");
            }
            this.x.bindProcessToNetwork(network);
        }
        a(SmartConfigDataProvider.a().b(), SmartConfigDataProvider.a().d(), (int) (System.currentTimeMillis() / 1000), (Class) SmartConfigDataProvider.a().a(SmartConfigDataProvider.p, (Object) null));
        this.ap = 1;
    }

    public void a(String str, String str2, int i2, final Class cls) {
        if (!CoreApi.a().q()) {
            d_(true);
            return;
        }
        final JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", i2);
            jSONObject.put("method", "miIO.config_router");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(DeviceTagInterface.e, str);
            if (str2 == null) {
                str2 = "";
            }
            jSONObject2.put("passwd", str2);
            jSONObject2.put("uid", Long.valueOf(CoreApi.a().s()));
            jSONObject2.put("bind_key", this.at);
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
            LogUtilGrey.a(k, "setMiioRouter json error:" + Log.getStackTraceString(e3));
        }
        if (this.ay != null) {
            this.ay.a();
        }
        this.ay = MiioLocalAPI.a(n(), (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
            public void onResponse(final String str) {
                LogUtilGrey.a(BleComboStep.k, "RPC response: " + str);
                if (BleComboStep.this.U_() != null) {
                    BleComboStep.this.U_().post(new Runnable() {
                        public void run() {
                            JSONObject b2 = BleComboStep.this.b(str);
                            if (b2 != null) {
                                long unused = BleComboStep.this.aq = Long.valueOf(b2.optString("did")).longValue();
                                String unused2 = BleComboStep.this.ar = b2.optString("token");
                                KuailianManager.a().a(String.valueOf(BleComboStep.this.aq), BleComboStep.this.ar);
                                JSONObject jSONObject = new JSONObject();
                                try {
                                    jSONObject.put("id", ((PluginHostApi) PluginHostApi.instance()).generateNonce());
                                    jSONObject.put("method", "miIO.info");
                                    jSONObject.put("params", new JSONObject());
                                    MiioLocalAPI.a(BleComboStep.this.n(), jSONObject.toString(), BleComboStep.this.aq, BleComboStep.this.ar, (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
                                        public void onResponse(String str) {
                                            JSONObject a2 = ApConfigStep.a(str);
                                            if (a2 != null) {
                                                String optString = a2.optString("fw_ver");
                                                if (!TextUtils.isEmpty(optString)) {
                                                    BleComboStep.this.an = optString;
                                                }
                                            }
                                            LogUtilGrey.a(BleComboStep.k, "async_rpc info version: " + BleComboStep.this.an + ", data = " + str);
                                            String jSONObject = jSONObject.toString();
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("write to device data: ");
                                            sb.append(jSONObject);
                                            LogUtilGrey.a(BleComboStep.k, sb.toString());
                                            if (cls != null) {
                                                try {
                                                    cls.getDeclaredMethod("wifiSmartConfigUapAsync", new Class[]{String.class, String.class, Long.TYPE, String.class, IBinder.class}).invoke((Object) null, new Object[]{BleComboStep.this.n(), jSONObject, Long.valueOf(BleComboStep.this.aq), BleComboStep.this.ar, new WhetstoneResultBinder() {
                                                        public void onResult(WhetstoneResult whetstoneResult) {
                                                            LogUtilGrey.a(BleComboStep.k, "wifiSmartConfigUapAsync device return data: " + whetstoneResult);
                                                            String a2 = WifiSettingUtils.a(whetstoneResult);
                                                            if (!TextUtils.isEmpty(a2) && BleComboStep.this.b(a2) != null) {
                                                                WifiDeviceFinder.a(BleComboStep.this.ao);
                                                            }
                                                            BleComboStep.this.b(200);
                                                        }
                                                    }});
                                                } catch (NoSuchMethodException e) {
                                                    LogUtilGrey.a(BleComboStep.k, "wifiSmartConfigUapAsync NoSuchMethodException: " + Log.getStackTraceString(e));
                                                } catch (InvocationTargetException e2) {
                                                    LogUtilGrey.a(BleComboStep.k, "wifiSmartConfigUapAsync InvocationTargetException: " + Log.getStackTraceString(e2));
                                                } catch (IllegalAccessException e3) {
                                                    LogUtilGrey.a(BleComboStep.k, "wifiSmartConfigUapAsync IllegalAccessException: " + Log.getStackTraceString(e3));
                                                }
                                            } else {
                                                MiioLocalAPI.a(BleComboStep.this.n(), jSONObject, BleComboStep.this.aq, BleComboStep.this.ar, (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
                                                    public void onResponse(String str) {
                                                        LogUtilGrey.a(BleComboStep.k, "async_rpc device return data: " + str);
                                                        if (BleComboStep.this.b(str) != null) {
                                                            WifiDeviceFinder.a(BleComboStep.this.ao);
                                                        } else if (BleComboStep.this.U_() != null) {
                                                            BleComboStep.this.U_().sendEmptyMessageDelayed(114, 1000);
                                                        }
                                                        if (BleComboStep.this.U_() != null) {
                                                            BleComboStep.this.b(1000);
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else if (BleComboStep.this.U_() != null) {
                                BleComboStep.this.U_().sendEmptyMessageDelayed(114, 1000);
                            }
                        }
                    });
                }
            }
        }, 5);
    }

    private boolean G() {
        return TextUtils.equals(DeviceFactory.i(this.ao.SSID), "hannto.printer.honey");
    }

    public void s() {
        BluetoothMyLogger.f("BleComboStep.onStopConnection");
        U_().removeMessages(112);
        U_().removeMessages(114);
        if (ApiHelper.e) {
            Log.e("WifiSettingUap", "Bind Network to NULL");
            this.x.bindProcessToNetwork((Network) null);
        }
        String b2 = SmartConfigDataProvider.a().b();
        String d2 = SmartConfigDataProvider.a().d();
        String e2 = SmartConfigDataProvider.a().e();
        if (!G()) {
            String c2 = WifiUtil.c(this.af);
            if (!this.az || TextUtils.equals(c2, this.ao.SSID)) {
                WifiSettingUtils.a(this.w, b2, d2, (String) null, e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void t() {
        BluetoothMyLogger.f("BleComboStep.scanNewDevice mDid = " + this.aq);
        this.ap = 2;
        if (this.aC[this.ap] == 0) {
            this.aC[this.ap] = System.currentTimeMillis();
        }
        this.aB[this.ap] = System.currentTimeMillis();
        if (G()) {
            String g2 = DeviceFactory.g(this.ao.SSID);
            if (!(this.av == null || this.av.length() != 17 || g2 == null || g2.length() != 4 || this.ao == null)) {
                char[] charArray = this.av.toCharArray();
                charArray[12] = g2.charAt(0);
                charArray[13] = g2.charAt(1);
                charArray[15] = g2.charAt(2);
                charArray[16] = g2.charAt(3);
                String replaceAll = Arrays.toString(charArray).replaceAll("[\\[\\]\\s,]", "");
                BluetoothMyLogger.e("BleComboStep.scanNewDevice change deviceMac: " + BluetoothMyLogger.a(replaceAll));
                this.ao.BSSID = replaceAll;
            }
        }
        DeviceFinder.a().a(this.aG, this.ao, this.aq == 0 ? null : String.valueOf(this.aq), this.ar, this.at);
    }

    public JSONObject b(String str) {
        JSONArray optJSONArray;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (AnonymousClass10.f22475a[ErrorCode.valueof(jSONObject.optInt("code")).ordinal()] != 1) {
                return null;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("result");
            if (optJSONObject == null && (optJSONArray = jSONObject.optJSONArray("result")) != null) {
                optJSONObject = new JSONObject();
                optJSONObject.put("result", optJSONArray);
            }
            return optJSONObject == null ? jSONObject : optJSONObject;
        } catch (JSONException e2) {
            LogUtilGrey.a(k, "parseRpcResponse error: " + Log.getStackTraceString(e2));
            return null;
        }
    }

    /* renamed from: com.xiaomi.smarthome.smartconfig.step.BleComboStep$10  reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f22475a = new int[ErrorCode.values().length];

        static {
            try {
                f22475a[ErrorCode.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public boolean a() {
        try {
            new MLAlertDialog.Builder(this.af).a((CharSequence) this.af.getString(R.string.stop_connect_device_title)).b((CharSequence) this.af.getString(R.string.stop_connect_device_message)).a((CharSequence) this.af.getString(R.string.confirm), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    WifiManager wifiManager = (WifiManager) BleComboStep.this.af.getApplicationContext().getSystemService("wifi");
                    WifiInfo connectionInfo = wifiManager != null ? wifiManager.getConnectionInfo() : null;
                    if (!(BleComboStep.this.ao == null || connectionInfo == null || !BleComboStep.this.ao.BSSID.equalsIgnoreCase(wifiManager.getConnectionInfo().getBSSID()))) {
                        WifiSettingUtils.b(BleComboStep.this.w, BleComboStep.this.ao.SSID);
                    }
                    if (DeviceFinder.a().e()) {
                        DeviceFinder.a().b();
                    }
                    BleComboStep.this.d_(false);
                    int a2 = ComboErrorCode.a(BleComboStep.this.ap);
                    long currentTimeMillis = System.currentTimeMillis();
                    int i2 = (int) ((currentTimeMillis - BleComboStep.this.aC[BleComboStep.this.ap]) / 1000);
                    int j = (int) ((currentTimeMillis - BleComboStep.this.aA) / 1000);
                    if (!ComboErrorCode.a(i2, BleComboStep.this.ap)) {
                        BleComboStep.this.ak = false;
                        return;
                    }
                    if (BleComboStep.this.ap == 2 && BleComboStep.this.aD) {
                        if (BleComboStep.this.aw == 0 && !BleComboStep.this.aE) {
                            return;
                        }
                        if (!(BleComboStep.this.aw == 0 || BleComboStep.this.aw == 3)) {
                            return;
                        }
                    }
                    if (!BleComboStep.this.al && BleComboStep.this.ak && !BleComboStep.this.am) {
                        if (BleComboStep.this.l || BleComboStep.this.ap != 2 || (BleComboStep.this.x() && NetworkUtils.c())) {
                            BleComboStep.this.al = true;
                            STAT.c.a(BleComboStep.this.aq, BleComboStep.this.aj, a2, i2, j, BleComboStep.this.an);
                            STAT.c.d(BleComboStep.this.aj, 4);
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
