package com.xiaomi.smarthome.smartconfig.step;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.Network;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.CameraApi;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BluetoothManager;
import com.xiaomi.smarthome.device.bluetooth.connect.combo.BaseBleComboConnector;
import com.xiaomi.smarthome.device.bluetooth.connect.combo.BleComboConnector;
import com.xiaomi.smarthome.device.bluetooth.connect.combo.ComboConnectResponse;
import com.xiaomi.smarthome.device.bluetooth.connect.combo.ComboRestoreResponse;
import com.xiaomi.smarthome.device.bluetooth.ui.CommonBindView;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.framework.statistic.MiStatType;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.ApiHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.statcode.ComboErrorCode;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.ConfigStep;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;

public class BleComboBleWayStep extends ConfigStep implements ComboConnectResponse {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f22466a = false;
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 62000;
    private static final int f = 90000;
    private static final int g = 50000;
    private static final int h = 60000;
    private static final int i = 60000;
    private int ap = 0;
    /* access modifiers changed from: private */
    public String aq = "";
    /* access modifiers changed from: private */
    public String ar;
    private String as;
    /* access modifiers changed from: private */
    public BaseBleComboConnector at;
    /* access modifiers changed from: private */
    public String au;
    /* access modifiers changed from: private */
    public int av;
    private Handler aw;
    /* access modifiers changed from: private */
    public boolean ax = false;
    private BroadcastReceiver ay = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                String stringExtra = intent.getStringExtra("key_device_address");
                int intExtra = intent.getIntExtra("key_connect_status", 5);
                if (TextUtils.equals(stringExtra, BleComboBleWayStep.this.au)) {
                    BluetoothMyLogger.d("BleComboBleWayStep bluetooth connect status = " + intExtra);
                }
                if ("com.xiaomi.smarthome.bluetooth.connect_status_changed".equalsIgnoreCase(action) && TextUtils.equals(stringExtra, BleComboBleWayStep.this.au) && intExtra == 32) {
                    BluetoothMyLogger.d("BleComboBleWayStep disconnect mac = " + BluetoothMyLogger.a(BleComboBleWayStep.this.au));
                    boolean unused = BleComboBleWayStep.this.ax = true;
                    BleComboBleWayStep.this.U_().removeMessages(127);
                    BleComboBleWayStep.this.b(0);
                    BLEDeviceManager.a(BleComboBleWayStep.this.l, BleComboBleWayStep.this.au);
                }
            }
        }
    };
    private ConfigStep.DeviceFinderCallback az = new DeviceFinder.DeviceFinderCallback2() {
        public void a(int i) {
            if (i == -6) {
                BleComboBleWayStep.this.a_(SmartConfigStep.Step.STEP_BIND_BY_OTHER_ERROR);
                if (!BleComboBleWayStep.this.al) {
                    BluetoothMyLogger.c("BleComboBleWayStep.DeviceFinder already bind!");
                    STAT.c.d(BleComboBleWayStep.this.l, 3);
                }
            }
            BLEDeviceManager.a(BleComboBleWayStep.this.l, BleComboBleWayStep.this.ar);
        }

        public void a(List<Device> list) {
            BluetoothMyLogger.f("BleComboBleWayStep DeviceFinderCallback.onDeviceConnectionSuccess");
            int unused = BleComboBleWayStep.this.k = 2;
            BleComboBleWayStep.this.ak = false;
            BleComboBleWayStep.this.a(2);
            BLEDeviceManager.a(BleComboBleWayStep.this.l, BleComboBleWayStep.this.ar);
        }

        public void a() {
            BluetoothMyLogger.f("BleComboBleWayStep DeviceFinderCallback.onDeviceConnectionFailure");
            BLEDeviceManager.a(BleComboBleWayStep.this.l, BleComboBleWayStep.this.ar);
        }

        public void b(List<Device> list) {
            BluetoothMyLogger.f("BleComboBleWayStep DeviceFinderCallback.onDeviceConnectionBind");
            int unused = BleComboBleWayStep.this.k = 2;
            BleComboBleWayStep.this.ak = false;
            BleComboBleWayStep.this.a(2);
            BLEDeviceManager.a(BleComboBleWayStep.this.l, BleComboBleWayStep.this.ar);
        }
    };
    /* access modifiers changed from: private */
    public boolean j = true;
    /* access modifiers changed from: private */
    public int k;
    /* access modifiers changed from: private */
    public String l;

    public SmartConfigStep.Step f() {
        return null;
    }

    public String b(String str) {
        if (TextUtils.isEmpty(this.as) || this.as.length() != 4) {
            return null;
        }
        try {
            return str.substring(0, 12) + this.as.substring(0, 2) + ":" + this.as.substring(2, 4);
        } catch (Exception unused) {
            return "";
        }
    }

    public void a(Context context) {
        this.aw = new Handler(Looper.getMainLooper());
        this.l = (String) SmartConfigDataProvider.a().a("device_model");
        this.ar = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.O);
        this.as = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.P);
        BluetoothMyLogger.d("BleComboBleWayStep model = " + this.l + ", combokey = " + this.as);
        SmartConfigDataProvider.a().b(SmartConfigDataProvider.Q, 13);
        super.a(context);
        STAT.c.h(this.l);
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

    public ArrayList<ConfigStep.ConfigTime> O_() {
        BluetoothMyLogger.f("BleComboBleWayStep.getAllConfigStages");
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
        if (i2 == 112) {
            BluetoothMyLogger.f("BleComboBleWayStep.handleMessage MSG_UPDATE_DEVICE_STATE");
            if (U_() != null) {
                U_().removeMessages(112);
                U_().removeMessages(114);
            }
            if (this.k == 1) {
                q();
                r();
                a(1);
                this.k = 2;
            }
        } else if (i2 == 115) {
            BluetoothMyLogger.f("BleComboBleWayStep.handleMessage MSG_DISCONNECT_TIME_OUT");
            q();
        } else if (i2 == 125) {
            BluetoothMyLogger.f("BleComboBleWayStep.handleMessage MSG_CONNECT_BLE_TIME_OUT");
            CoreApi.a().W();
            try {
                SHApplication.getAppContext().unregisterReceiver(this.ay);
            } catch (Exception unused) {
            }
            this.at.d();
            BLEDeviceManager.a(this.l, this.ar);
            STAT.c.d(this.l, 1);
            if (!this.al && this.ak) {
                this.al = true;
                STAT.c.a(0, this.l, ComboErrorCode.d, 1, "", this.ar, this.an);
            }
            a_(SmartConfigStep.Step.STEP_BLE_CONNECT_ERROR);
        } else if (i2 != 127) {
            super.a(message);
        } else {
            BluetoothMyLogger.f("BleComboBleWayStep.handleMessage MSG_BLE_NOTIFY_TIME_OUT, current notifyStatus = " + this.av);
            U_().removeMessages(127);
            try {
                SHApplication.getAppContext().unregisterReceiver(this.ay);
            } catch (Exception unused2) {
            }
            this.at.d();
            b(0);
        }
    }

    public void k() {
        this.k = 0;
        this.j = true;
    }

    public int b() {
        if (this.k != 0) {
            return this.k;
        }
        BluetoothMyLogger.f("BleComboBleWayStep.getCurrentStageIndex mCurrentIndex = CONNECT_INDEX, mUseBleConfig = " + this.j);
        return this.j ? 0 : 0;
    }

    public SmartConfigStep.Step b(int i2) {
        this.am = true;
        BLEDeviceManager.a(this.l, this.ar);
        switch (i2) {
            case 0:
                BluetoothMyLogger.f("BleComboBleWayStep.onStageTimeOut CONNECT_INDEX, mUseBleConfig = " + this.j);
                if (!this.al && this.ak) {
                    this.al = true;
                    STAT.c.a(0, this.l, 2101, this.j ? 1 : 0, this.an);
                    STAT.c.d(this.l, 1);
                }
                if (this.j) {
                    return SmartConfigStep.Step.STEP_CONNECT_AP_ERROR;
                }
                MobclickAgent.a(this.af, MiStatType.CLICK.getValue(), "connect_ap_connect_timeout");
                StatHelper.d("connect_ap_connect_timeout");
                U_().removeMessages(123);
                return SmartConfigStep.Step.STEP_CONNECT_AP_ERROR;
            case 1:
                BluetoothMyLogger.f("BleComboBleWayStep.onStageTimeOut SEND_SSID_AND_PASSWD_INDEX, mUseBleConfig = " + this.j);
                if (!this.al && this.ak) {
                    this.al = true;
                    STAT.c.a(0, this.l, (int) ComboErrorCode.b, this.j ? 1 : 0, this.an);
                    STAT.c.d(this.l, 2);
                }
                if (!this.j) {
                    if (this.ap == 1) {
                        return SmartConfigStep.Step.STEP_SEND_ROUTER_INFO_ERROR;
                    }
                    PluginRecord d2 = CoreApi.a().d(this.l);
                    if (d2 == null || d2.c().C() == 0) {
                        return SmartConfigStep.Step.STEP_SEND_ROUTER_INFO_ERROR;
                    }
                    return SmartConfigStep.Step.STEP_SEND_ROUTER_INFO_FINAL_ERROR;
                } else if (this.av == 0) {
                    return SmartConfigStep.Step.STEP_BLE_SEND_ERROR;
                } else {
                    if (this.av == 1) {
                        BluetoothMyLogger.f(">>> current notifyStatus == NOTIFY_CONNECTING_ROUTER");
                        return SmartConfigStep.Step.STEP_BLE_SEND_ERROR;
                    } else if (this.av == 2) {
                        BluetoothMyLogger.f(">>> current notifyStatus == NOTIFY_CONNECTING_ROUTER");
                        return SmartConfigStep.Step.STEP_BLE_SEND_ERROR;
                    } else if (this.av == 5) {
                        BluetoothMyLogger.f(">>> current notifyStatus == NOTIFY_AUTHENTICATION_ERROR");
                        s();
                        return SmartConfigStep.Step.STEP_BLE_PWD_ERROR;
                    } else if (this.av == 4) {
                        BluetoothMyLogger.f(">>> current notifyStatus == NOTIFY_UNKNONW_ERROR");
                        return SmartConfigStep.Step.STEP_BLE_SEND_ERROR;
                    } else if (this.av == 3) {
                        return SmartConfigStep.Step.STEP_BLE_SEND_ERROR;
                    } else {
                        BluetoothMyLogger.f(String.format(">>> Unknown notifyStatus = %d", new Object[]{Integer.valueOf(this.av)}));
                        return null;
                    }
                }
            case 2:
                BluetoothMyLogger.f("BleComboBleWayStep.onStageTimeOut GET_NEW_DEVICE_INDEX，mUseBleConfig = " + this.j);
                w();
                if (!this.al && this.ak && NetworkUtils.c()) {
                    this.al = true;
                    STAT.c.a(0, this.l, ComboErrorCode.a(true, this.ax, this.av), this.av == 3 ? 2 : 1, "", this.ar, this.an);
                    STAT.c.d(this.l, 3);
                }
                if (this.j) {
                    return SmartConfigStep.Step.STEP_FIND_DEVICE_FAILED;
                }
                MobclickAgent.a(this.af, MiStatType.CLICK.getValue(), "connect_get_device_timeout");
                StatHelper.d("connect_get_device_timeout");
                DeviceFinder.a().b();
                PluginRecord d3 = CoreApi.a().d(this.l);
                if (d3 != null) {
                    Log.e("AoConfigStep", "" + d3.c().C());
                }
                if (d3 == null || d3.c().C() == 0) {
                    return SmartConfigStep.Step.STEP_FIND_DEVICE_FAILED;
                }
                return SmartConfigStep.Step.STEP_FIND_DEVICE_FAILED_ERROR;
            default:
                return null;
        }
    }

    public void h() {
        this.mCommonBindView.addNextStep((int) R.string.ble_combo_connect_device_step_loading, (int) R.string.ble_combo_connect_title_loading);
        this.mCommonBindView.startProgressAnimation(1);
    }

    public void i() {
        switch (this.k) {
            case 0:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.ble_combo_connect_device_step_success, (int) R.string.ble_combo_connect_title_loading);
                this.mCommonBindView.addNextStep((int) R.string.ble_combo_transfer_device_step_loading, (int) R.string.ble_combo_connect_title_loading);
                this.mCommonBindView.startProgressAnimation(2);
                return;
            case 1:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.ble_combo_transfer_device_step_success, (int) R.string.ble_combo_connect_title_loading);
                Resources resources = this.mCommonBindView.getResources();
                CommonBindView commonBindView = this.mCommonBindView;
                commonBindView.addNextStep(resources.getString(R.string.ble_combo_device_connect_network_step_loading) + resources.getString(R.string.kuailian_connect_ble), resources.getString(R.string.ble_combo_network_title_loading));
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
        switch (this.k) {
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
                commonBindView.updateCurrentStep(stepStatus, resources.getString(R.string.ble_combo_device_connect_network_step_loading) + resources.getString(R.string.kuailian_connect_ble), R.string.ble_combo_network_title_loading, false);
                this.mCommonBindView.startProgressAnimation(3);
                return;
            default:
                return;
        }
    }

    private void s() {
        this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.ble_combo_transfer_device_step_success, (int) R.string.ble_combo_connect_title_loading);
        Resources resources = this.mCommonBindView.getResources();
        CommonBindView commonBindView = this.mCommonBindView;
        commonBindView.addNextStep(resources.getString(R.string.ble_combo_device_connect_network_step_loading) + resources.getString(R.string.kuailian_connect_ble), resources.getString(R.string.ble_combo_network_title_loading));
    }

    public void c(int i2) {
        switch (i2) {
            case 0:
                BluetoothMyLogger.f("BleComboBleWayStep.startConnection CONNECT_INDEX, mUseBleConfig = " + this.j);
                a((AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
                    /* renamed from: a */
                    public void onSuccess(String str) {
                        String unused = BleComboBleWayStep.this.aq = str;
                        if (BleComboBleWayStep.this.aq == null) {
                            String unused2 = BleComboBleWayStep.this.aq = "";
                        }
                        if (BleComboBleWayStep.this.j) {
                            BleComboBleWayStep.this.o();
                        }
                    }

                    public void onFailure(Error error) {
                        BluetoothMyLogger.d("BleComboBleWayStep getBindKey failed");
                        String unused = BleComboBleWayStep.this.aq = "";
                        if (BleComboBleWayStep.this.j) {
                            BleComboBleWayStep.this.o();
                        }
                    }
                });
                return;
            case 1:
                BluetoothMyLogger.f("BleComboBleWayStep.startConnection SEND_SSID_AND_PASSWD_INDEX, mUseBleConfig = " + this.j);
                if (this.j) {
                    BluetoothMyLogger.d("BleComboBleWayStep >>> current mNotifyStatus = " + d(this.av));
                    if (this.av == 0) {
                        p();
                        return;
                    } else if (this.av == 4) {
                        t();
                        return;
                    } else if (this.av == 3) {
                        this.j = true;
                        U_().sendEmptyMessageDelayed(127, 50000);
                        d_(this.av);
                        return;
                    } else if (BluetoothUtils.c(this.au)) {
                        this.j = true;
                        U_().sendEmptyMessageDelayed(127, 50000);
                        d_(this.av);
                        return;
                    } else {
                        U_().removeMessages(127);
                        b(0);
                        return;
                    }
                } else {
                    return;
                }
            case 2:
                BluetoothMyLogger.f("BleComboBleWayStep.startConnection GET_NEW_DEVICE_INDEX");
                BluetoothMyLogger.d(">>> current mNotifyStatus = " + d(this.av));
                q();
                r();
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

    private void t() {
        BluetoothMyLogger.d("BleComboBleWayStep.retryComboConnect");
        this.av = 0;
        this.j = true;
        this.at.c();
        U_().removeMessages(125);
        U_().removeMessages(127);
        this.at.a();
        U_().sendEmptyMessageDelayed(127, 50000);
    }

    public Handler U_() {
        Handler U_ = super.U_();
        return U_ != null ? U_ : this.aw;
    }

    public void o() {
        BluetoothMyLogger.f("BleComboBleWayStep.startConnectToBle");
        this.k = 0;
        if (this.at == null) {
            this.at = new BleComboConnector(this);
        }
        if (this.ar != null) {
            this.at.a(this.ar);
        } else {
            d_(true);
        }
        U_().sendEmptyMessageDelayed(125, 62000);
    }

    public void a(String str) {
        BluetoothMyLogger.f(String.format("BleComboBleWayStep.onSearchComboAddress, mac = %s", new Object[]{BluetoothMyLogger.a(str)}));
        if (!TextUtils.isEmpty(str)) {
            StatHelper.s();
            this.au = str;
            this.at.a(this.aq, this.u.type);
            return;
        }
        StatHelper.r();
        if (!this.al && this.ak) {
            this.al = true;
            STAT.c.a(0, this.l, ComboErrorCode.e, 1, "", this.ar, this.an);
        }
        if (U_().hasMessages(125)) {
            U_().removeMessages(125);
            U_().sendEmptyMessage(125);
        }
    }

    public void a(int i2, String str) {
        BluetoothMyLogger.f("BleComboBleWayStep.onSendSSIDAndPassWd code = " + i2 + ", firmwareVersion = " + str);
        this.an = str;
        if (i2 == 0) {
            StatHelper.t();
            if (this.j && !this.ag) {
                SHApplication.getAppContext().registerReceiver(this.ay, new IntentFilter("com.xiaomi.smarthome.bluetooth.connect_status_changed"));
                U_().removeMessages(125);
                this.at.a();
                a(this.k);
                c(1);
                U_().sendEmptyMessageDelayed(127, 50000);
                return;
            }
            return;
        }
        StatHelper.u();
        if (U_().hasMessages(125)) {
            U_().removeMessages(125);
            if (!this.al && this.ak) {
                int b2 = ComboErrorCode.b(i2);
                this.al = true;
                STAT.c.a(0, this.l, b2, 1, "", this.ar, this.an);
            }
            if (i2 == -6) {
                BluetoothManager.getInstance().disconnect(this.ar);
                U_().sendEmptyMessageDelayed(125, 2000);
                return;
            }
            U_().sendEmptyMessage(125);
        }
    }

    public void p() {
        this.k = 1;
    }

    private String d(int i2) {
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
        this.av = i2;
        BluetoothMyLogger.d("onNotifyStatus " + d(i2));
        if (this.j && U_().hasMessages(127)) {
            switch (i2) {
                case 3:
                    try {
                        SHApplication.getAppContext().unregisterReceiver(this.ay);
                    } catch (Exception unused) {
                    }
                    this.at.d();
                    U_().removeMessages(127);
                    b(0);
                    StatHelper.x();
                    return;
                case 4:
                case 5:
                    try {
                        SHApplication.getAppContext().unregisterReceiver(this.ay);
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
        try {
            SHApplication.getAppContext().unregisterReceiver(this.ay);
        } catch (Exception unused) {
        }
        this.aw.removeCallbacksAndMessages((Object) null);
        BluetoothHelper.b();
        boolean booleanValue = ((Boolean) SmartConfigDataProvider.a().a(SmartConfigDataProvider.E, false)).booleanValue();
        BluetoothMyLogger.d(String.format("wifi restore ? -> %b", new Object[]{Boolean.valueOf(booleanValue)}));
        if (this.at == null) {
            return;
        }
        if (booleanValue) {
            this.at.a((ComboRestoreResponse) new ComboRestoreResponse() {
                public void a(int i) {
                    BleComboBleWayStep.this.at.d();
                }
            });
        } else {
            this.at.d();
        }
    }

    public void q() {
        BluetoothMyLogger.f("BleComboBleWayStep.onStopConnection");
        U_().removeMessages(112);
        U_().removeMessages(114);
        if (ApiHelper.e) {
            Log.e("WifiSettingUap", "Bind Network to NULL");
            this.x.bindProcessToNetwork((Network) null);
        }
    }

    /* access modifiers changed from: protected */
    public void r() {
        String b2 = b(this.ar);
        BluetoothMyLogger.f("BleComboBleWayStep.scanNewDevice bssid = " + b2);
        DeviceFinder.a().a(this.az, b2, (String) null, (String) null, this.aq);
    }

    public boolean a() {
        try {
            new MLAlertDialog.Builder(this.af).a((CharSequence) this.af.getString(R.string.stop_connect_device_title)).b((CharSequence) this.af.getString(R.string.stop_connect_device_message)).a((CharSequence) this.af.getString(R.string.confirm), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    WifiManager wifiManager = (WifiManager) BleComboBleWayStep.this.af.getApplicationContext().getSystemService("wifi");
                    if (wifiManager != null) {
                        wifiManager.getConnectionInfo();
                    }
                    if (DeviceFinder.a().e()) {
                        DeviceFinder.a().b();
                    }
                    BleComboBleWayStep.this.d_(false);
                    if (BleComboBleWayStep.this.k == 2) {
                        if (BleComboBleWayStep.this.av == 0 && !BleComboBleWayStep.this.ax) {
                            return;
                        }
                        if (!(BleComboBleWayStep.this.av == 0 || BleComboBleWayStep.this.av == 3)) {
                            return;
                        }
                    }
                    if (!BleComboBleWayStep.this.al && BleComboBleWayStep.this.ak && !BleComboBleWayStep.this.am) {
                        BleComboBleWayStep.this.al = true;
                        STAT.c.a(0, BleComboBleWayStep.this.l, ComboErrorCode.a(BleComboBleWayStep.this.k), BleComboBleWayStep.this.an);
                        STAT.c.d(BleComboBleWayStep.this.l, 4);
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
