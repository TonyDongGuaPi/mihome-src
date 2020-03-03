package com.xiaomi.smarthome.device.bluetooth.connect.single;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.qrcode.ScanBarcodeActivity;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bluetooth.Response;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothApi;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityLauncher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectHandler;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.BluetoothStateHelper;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.device.bluetooth.BluetoothReporter;
import com.xiaomi.smarthome.device.bluetooth.connect.count.BoostCallback;
import com.xiaomi.smarthome.device.bluetooth.connect.count.ProgressCounter;
import com.xiaomi.smarthome.device.bluetooth.connect.count.ProgressCounterImpl;
import com.xiaomi.smarthome.device.bluetooth.connect.count.ProgressNotifier;
import com.xiaomi.smarthome.device.bluetooth.connect.single.BleBindPairView;
import com.xiaomi.smarthome.device.bluetooth.connect.single.BleDeviceFinder;
import com.xiaomi.smarthome.device.bluetooth.ui.BleMatchActivity;
import com.xiaomi.smarthome.device.bluetooth.ui.CommonBindView;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.permission.PermissionHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.initdevice.InitDeviceRoomActivity;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.IBleSecureConnectResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.statcode.BleErrorCode;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.smartconfig.DeviceBindStatis;
import com.xiaomi.smarthome.stat.STAT;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.SettingService;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONObject;

public class BleBindActivityV2 extends BaseActivity {
    private static final int A = 27;
    private static final int B = 28;
    private static final int C = 29;
    private static final int D = 30;
    private static final int E = 33;
    private static final int F = 34;
    private static final int G = 35;
    private static final int H = 36;
    private static final int I = 40;
    private static final int J = 41;
    private static final int K = 42;
    public static final String KEY_QRCODE_OOB = "key_qrcode_oob";
    private static final int L = 43;
    private static final int M = 44;
    public static final int MESH_BLE_AUTH = 4;
    private static final int N = 8193;
    public static final int NORMAL_BLE_AUTH = 1;
    public static final int SECURITY_CHIP_BLE_AUTH = 3;
    public static final int STANDARD_BLE_AUTH = 2;

    /* renamed from: a  reason: collision with root package name */
    private static final String f15140a = "BleBindActivityV2";
    private static final int b = 1;
    private static final int c = 3000;
    private static final int d = 4000;
    private static final int e = 5000;
    private static final int f = 60;
    private static final int g = 80;
    private static final int h = 90;
    private static final int i = 99;
    private static final int j = 101;
    private static final int k = 30000;
    private static final int l = 5000;
    private static final int m = 10000;
    private static final int n = 15000;
    private static final int o = 1000;
    private static final int p = 10;
    private static final int q = 11;
    private static final int r = 12;
    private static final int s = 13;
    private static final int t = 20;
    private static final int u = 21;
    private static final int v = 22;
    private static final int w = 23;
    private static final int x = 24;
    private static final int y = 25;
    private static final int z = 26;
    private View O;
    /* access modifiers changed from: private */
    public CommonBindView P;
    private String Q;
    /* access modifiers changed from: private */
    public int R;
    /* access modifiers changed from: private */
    public BleDevice S;
    /* access modifiers changed from: private */
    public ProgressCounter T;
    /* access modifiers changed from: private */
    public ISecureConnectHandler U;
    private MLAlertDialog V;
    private Bundle W = new Bundle();
    /* access modifiers changed from: private */
    public BleConnectOptions X;
    /* access modifiers changed from: private */
    public BleBindPairView Y;
    /* access modifiers changed from: private */
    public PopupWindow Z;
    /* access modifiers changed from: private */
    public int aa = -9999;
    private long ab = System.currentTimeMillis();
    private boolean ac = false;
    /* access modifiers changed from: private */
    public boolean ad = false;
    /* access modifiers changed from: private */
    public int ae;
    private BleDeviceFinder af = new BleDeviceFinder();
    /* access modifiers changed from: private */
    public boolean ag = false;
    /* access modifiers changed from: private */
    public boolean ah = false;
    /* access modifiers changed from: private */
    public IBleSecureConnectResponse ai = new IBleSecureConnectResponse() {
        public void a(int i, Bundle bundle) {
            BluetoothMyLogger.d(String.format("BleBindActivity onConnectResponse: code = %d", new Object[]{Integer.valueOf(i)}));
            int unused = BleBindActivityV2.this.aa = i;
            BleBindActivityV2.this.a(i, bundle);
        }

        public void b(int i, Bundle bundle) {
            BluetoothMyLogger.d(String.format("BleBindActivity onAuthResponse: code = %d", new Object[]{Integer.valueOf(i)}));
            int unused = BleBindActivityV2.this.aa = i;
            BleBindActivityV2.this.b(i, bundle);
        }

        public void c(int i, Bundle bundle) {
            BluetoothMyLogger.d(String.format("BleBindActivity onBindResponse: code = %d", new Object[]{Integer.valueOf(i)}));
            int unused = BleBindActivityV2.this.aa = i;
            BleBindActivityV2.this.c(i, bundle);
        }

        public void d(int i, Bundle bundle) {
            BluetoothMyLogger.d(String.format("BleBindActivity onLastResponse: code = %d", new Object[]{Integer.valueOf(i)}));
            if (i == 0) {
                STAT.i.a(BleBindActivityV2.this.S.model, bundle == null ? "" : bundle.getString(BleSecurityLauncher.f14301a));
                if (BleBindActivityV2.this.i()) {
                    BLEDeviceManager.b((Device) BleBindActivityV2.this.S);
                }
            } else if (BleErrorCode.a(i) && BleBindActivityV2.this.ag && !BleBindActivityV2.this.ah && NetworkUtils.c()) {
                boolean unused = BleBindActivityV2.this.ah = true;
                String str = "";
                if (bundle != null) {
                    str = bundle.getString("key_version");
                }
                STAT.i.a(BleBindActivityV2.this.S.model, bundle == null ? "" : bundle.getString(BleSecurityLauncher.f14301a), i, str, "");
            }
            int unused2 = BleBindActivityV2.this.aa = i;
            if (i == -12 || i == -13) {
                BleBindActivityV2.this.c(11);
            } else if (i != 0) {
                BleBindActivityV2.this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        if (BleBindActivityV2.this.R == 10) {
                            BleBindActivityV2.this.c(11);
                        } else if (BleBindActivityV2.this.R == 20) {
                            BleBindActivityV2.this.c(21);
                        } else if (BleBindActivityV2.this.R == 33) {
                            BleBindActivityV2.this.c(34);
                        }
                    }
                }, 2500);
            }
        }
    };
    private final ProgressNotifier aj = new ProgressNotifier() {
        public void a(int i) {
            int i2;
            if (i >= 0 && i <= 100) {
                if (i <= 60) {
                    i2 = (i * 100) / 60;
                } else if (i <= 80) {
                    i2 = ((i - 60) * 100) / 20;
                } else if (i <= 90) {
                    i2 = ((i - 80) * 100) / 10;
                } else {
                    i2 = i <= 99 ? ((i - 90) * 100) / 9 : i;
                }
                BleBindActivityV2.this.P.setProgress(i2);
            }
            if (i == 100) {
                BleBindActivityV2.this.c(44);
            }
        }
    };
    private PopupWindow.OnDismissListener ak = new PopupWindow.OnDismissListener() {
        public void onDismiss() {
            BleBindPairView unused = BleBindActivityV2.this.Y = null;
            PopupWindow unused2 = BleBindActivityV2.this.Z = null;
        }
    };
    private BleBindPairView.OnPairCallback al = new BleBindPairView.OnPairCallback() {
        public void a() {
            BleBindActivityV2.this.d();
            BleBindActivityV2.this.u();
            BleBindActivityV2.this.B();
        }

        public void b() {
            BleBindActivityV2.this.d();
            BleBindActivityV2.this.a(true);
        }
    };
    private final BoostCallback am = new BoostCallback() {
        public void a() {
            BleBindActivityV2.this.c(13);
            BleBindActivityV2.this.c(20);
        }
    };
    private BleDeviceFinder.BleDeviceFinderCallback an = new BleDeviceFinder.BleDeviceFinderCallback() {
        public void a() {
            if (BleBindActivityV2.this.isValid()) {
                boolean unused = BleBindActivityV2.this.ag = true;
                BluetoothMyLogger.e("BleBindActivityV2 DeviceFinder onFound");
                BleBindActivityV2.this.f();
            }
        }

        public void b() {
            if (BleBindActivityV2.this.isValid()) {
                boolean unused = BleBindActivityV2.this.ag = false;
                BluetoothMyLogger.e("BleBindActivityV2 DeviceFinder onTimeout");
                BleBindActivityV2.this.f();
            }
        }
    };
    private final BoostCallback ao = new BoostCallback() {
        public void a() {
            BleBindActivityV2.this.c(30);
            BleBindActivityV2.this.c(33);
        }
    };
    private final BoostCallback ap = new BoostCallback() {
        public void a() {
            BleBindActivityV2.this.c(36);
            BleBindActivityV2.this.c(40);
        }
    };
    private final View.OnClickListener aq = new View.OnClickListener() {
        public void onClick(View view) {
            STAT.d.bc(BleBindActivityV2.this.S.model);
            BleBindActivityV2.this.v();
        }
    };
    private final View.OnClickListener ar = new View.OnClickListener() {
        public void onClick(View view) {
            BleBindActivityV2.this.finish();
        }
    };
    private final View.OnClickListener as = new View.OnClickListener() {
        public void onClick(View view) {
            BleBindActivityV2.this.P.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.ble_new_download_plugin_step_loading, (int) R.string.ble_new_network_loading_title);
            BleBindActivityV2.this.P.startProgressAnimation(4);
            BleBindActivityV2.this.T.a(90);
            BleBindActivityV2.this.T.a(99, 15000, (BoostCallback) null);
            BleBindActivityV2.this.mHandler.sendEmptyMessageDelayed(8193, 15000);
            BleBindActivityV2.this.g();
        }
    };
    private final View.OnClickListener at = new View.OnClickListener() {
        public void onClick(View view) {
            STAT.d.bd(BleBindActivityV2.this.S.model);
            DeviceApi.getInstance().getDeviceDetail(SHApplication.getAppContext(), new String[]{BleBindActivityV2.this.S.did}, new AsyncCallback<List<Device>, Error>() {
                /* renamed from: a */
                public void onSuccess(List<Device> list) {
                    if (list != null && list.size() > 0 && TextUtils.equals(BleBindActivityV2.this.S.did, list.get(0).did)) {
                        BleBindActivityV2.this.S.name = list.get(0).name;
                        BleCacheUtils.a(BleBindActivityV2.this.S.mac, list.get(0).name);
                    }
                    BleBindActivityV2.this.t();
                }

                public void onFailure(Error error) {
                    BleBindActivityV2.this.t();
                }
            });
        }
    };
    private View.OnClickListener au = new View.OnClickListener() {
        public void onClick(View view) {
            if (BleBindActivityV2.this.R == 10 || BleBindActivityV2.this.R == 20 || BleBindActivityV2.this.R == 33) {
                new MLAlertDialog.Builder(BleBindActivityV2.this).a((int) R.string.ble_new_cancel_dialog_title).b((int) R.string.ble_new_cancel_dialog_message).a((int) R.string.ble_new_cancel_dialog_ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BleBindActivityV2.this.u();
                        BleBindActivityV2.this.B();
                    }
                }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b().show();
                return;
            }
            if (BleBindActivityV2.this.R == 44) {
                XmBluetoothManager.getInstance().disconnect(BleBindActivityV2.this.S.mac, 15000);
            }
            BleBindActivityV2.this.finish();
        }
    };

    private interface RequestCameraPermissionCallback {
        void a();

        void b();

        void c();

        void d();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!y()) {
            finish();
            return;
        }
        this.X = new BleConnectOptions.Builder().a(1).c(31000).b(2).d(15000).a();
        BLEDeviceManager.f();
        this.T = new ProgressCounterImpl(this.aj);
        e();
        if (BluetoothUtils.f(this.S.mac)) {
            BluetoothMyLogger.d("BleBindActivityV2 device has ConnectedOrBonded: " + BluetoothMyLogger.a(this.S.mac));
        }
        if (!a()) {
            this.T.a(0);
            c(29);
            return;
        }
        if (h()) {
            String stringExtra = getIntent().getStringExtra(KEY_QRCODE_OOB);
            if (!TextUtils.isEmpty(stringExtra)) {
                this.X.a(stringExtra);
                a(true);
            } else {
                a((Activity) this, (RequestCameraPermissionCallback) new RequestCameraPermissionCallback() {
                    public void d() {
                    }

                    public void a() {
                        BleBindActivityV2.this.c();
                    }

                    public void b() {
                        BleBindActivityV2.this.finish();
                    }

                    public void c() {
                        ToastUtil.a((CharSequence) BleBindActivityV2.this.getResources().getString(R.string.permission_failure));
                        BleBindActivityV2.this.finish();
                    }
                });
            }
        } else {
            a(true);
        }
        STAT.c.m(this.S.model);
    }

    private boolean a() {
        BleDevice d2;
        MiotBleAdvPacket d3;
        MiotBleAdvPacket d4 = this.S.d();
        boolean z2 = d4 == null || d4.f14276a == null || BluetoothApi.a(d4.f14276a.l);
        if (!z2 || (d2 = BLEDeviceManager.d(this.S.mac)) == null || (d3 = d2.d()) == null || d3.f14276a == null || BluetoothApi.a(d3.f14276a.l)) {
            return z2;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void b() {
        Intent intent = new Intent();
        intent.setClass(this, BleSecurePinActivity.class);
        intent.putExtra(BleSecurePinActivity.KEY_MAC, this.S.mac);
        startActivityForResult(intent, 5000);
    }

    /* access modifiers changed from: private */
    public void c() {
        Intent intent = new Intent();
        intent.setClass(this, ScanBarcodeActivity.class);
        intent.putExtra("from", 200);
        startActivityForResult(intent, 3000);
    }

    private void a(String str) {
        this.Y = new BleBindPairView(this);
        this.Y.setPairCallback(this.al);
        this.Y.setPaircode(str);
        this.Z = new PopupWindow(this.Y, -1, -1, true);
        this.Z.setAnimationStyle(R.style.BleBindPairWindowAnim);
        this.Z.setFocusable(false);
        this.Z.setOutsideTouchable(false);
        this.Z.setOnDismissListener(this.ak);
        this.Z.showAtLocation(this.O, 0, 0, 0);
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.Z != null && this.Z.isShowing()) {
            this.Z.dismiss();
        }
    }

    private void e() {
        setContentView(R.layout.activity_ble_bind_v2);
        this.O = findViewById(R.id.title_bar);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.press_to_add_device1);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(this.au);
        this.P = (CommonBindView) findViewById(R.id.common_bind_view);
    }

    /* access modifiers changed from: private */
    public void a(boolean z2) {
        this.P.resetUi();
        this.T.a(0);
        this.T.a(60, 30000, this.am);
        c(10);
        if (z2 || this.S.d() == null) {
            this.af.a(this.S.mac, this.an);
        } else {
            f();
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        DeviceBindStatis.a((Context) this, this.S.model, this.S.mac);
        if (!this.S.isOwner() && !SmartHomeDeviceManager.a().i(this.S.mac)) {
            this.S.setOwner(true);
            this.S.ownerId = "";
            this.S.ownerName = "";
            BleCacheUtils.g(this.S.mac, "");
            BleCacheUtils.b(this.S.mac, "");
            BleCacheUtils.b(this.S.mac, 0);
        }
        if (i()) {
            BluetoothMyLogger.d("Start bleMeshBind, rssi = " + this.S.rssi);
            this.ae = 4;
            BluetoothHelper.d(this.S.mac, this.X, this.ai);
        } else if (!j()) {
            BluetoothMyLogger.d("Start Normal Bind, rssi = " + this.S.rssi);
            BleDevice d2 = BLEDeviceManager.d(this.S.mac);
            if (d2 == null || d2.d() == null || d2.d().f14276a == null) {
                BluetoothMyLogger.d("get auth type, rssi = " + this.S.rssi);
                a((Response.BleConnectResponse) new Response.BleConnectResponse() {
                    /* renamed from: a */
                    public void onResponse(int i, Bundle bundle) {
                        if (BleBindActivityV2.this.isValid()) {
                            if (i == 0) {
                                XmBluetoothManager.getInstance().isBleCharacterExist(BleBindActivityV2.this.S.mac, BluetoothConstants.i, BluetoothConstants.V, new Response.BleResponse<Void>() {
                                    /* renamed from: a */
                                    public void onResponse(int i, Void voidR) {
                                        if (i == 0) {
                                            BluetoothMyLogger.d("Start bleStandardAuthBind after read character");
                                            int unused = BleBindActivityV2.this.ae = 2;
                                            ISecureConnectHandler unused2 = BleBindActivityV2.this.U = BluetoothHelper.f(BleBindActivityV2.this.S.mac, BleBindActivityV2.this.X, BleBindActivityV2.this.ai);
                                            return;
                                        }
                                        BluetoothMyLogger.d("Start secureConnect after read character");
                                        int unused3 = BleBindActivityV2.this.ae = 1;
                                        ISecureConnectHandler unused4 = BleBindActivityV2.this.U = BluetoothHelper.a(BleBindActivityV2.this.S.mac, BleBindActivityV2.this.X, BleBindActivityV2.this.ai);
                                    }
                                });
                                return;
                            }
                            BluetoothInternationLogUtil.a("BleBindActivityV2 connect failed when read device character: " + BleBindActivityV2.this.a(i));
                            BluetoothMyLogger.d("connect failed when read device character: " + i);
                            int unused = BleBindActivityV2.this.aa = i;
                            BleBindActivityV2.this.T.a(0);
                            BleBindActivityV2.this.c(11);
                        }
                    }
                }, 1);
            } else if (d2.d().f14276a.k == 2) {
                BluetoothMyLogger.d("Start bleStandardAuthBind");
                this.ae = 2;
                this.U = BluetoothHelper.f(this.S.mac, this.X, this.ai);
            } else {
                BluetoothMyLogger.d("Start secureConnect");
                this.ae = 1;
                this.U = BluetoothHelper.a(this.S.mac, this.X, this.ai);
            }
        } else if (this.ad) {
            BluetoothMyLogger.d("Start securityChipConnect, rssi = " + this.S.rssi);
            this.ae = 3;
            this.U = BluetoothHelper.c(this.S.mac, this.X, this.ai);
        } else {
            BluetoothMyLogger.d("get lock version after connect, rssi = " + this.S.rssi);
            a((Response.BleConnectResponse) new Response.BleConnectResponse() {
                /* renamed from: a */
                public void onResponse(int i, Bundle bundle) {
                    if (BleBindActivityV2.this.isValid()) {
                        if (i == 0) {
                            XmBluetoothManager.getInstance().getBleMeshFirmwareVersion(BleBindActivityV2.this.S.mac, new Response.BleReadFirmwareVersionResponse() {
                                /* renamed from: a */
                                public void onResponse(int i, String str) {
                                    if (i != 0 || TextUtils.isEmpty(str)) {
                                        BluetoothInternationLogUtil.a("BleBindActivityV2 read lock version failed: " + BleBindActivityV2.this.a(i));
                                        BluetoothMyLogger.d("read lock version failed: " + i);
                                        int unused = BleBindActivityV2.this.aa = i;
                                        BleBindActivityV2.this.T.a(0);
                                        BleBindActivityV2.this.c(11);
                                        BleBindActivityV2.this.S.l();
                                        return;
                                    }
                                    boolean unused2 = BleBindActivityV2.this.ad = true;
                                    if (LtmkEncryptUtil.a(str)) {
                                        BleBindActivityV2.this.T.a(0);
                                        BleBindActivityV2.this.b();
                                        return;
                                    }
                                    BluetoothMyLogger.d("Start securityChipConnect, rssi = " + BleBindActivityV2.this.S.rssi);
                                    int unused3 = BleBindActivityV2.this.ae = 3;
                                    ISecureConnectHandler unused4 = BleBindActivityV2.this.U = BluetoothHelper.c(BleBindActivityV2.this.S.mac, BleBindActivityV2.this.X, BleBindActivityV2.this.ai);
                                }
                            });
                            return;
                        }
                        BluetoothInternationLogUtil.a("BleBindActivityV2 connect failed when read lock version: " + BleBindActivityV2.this.a(i));
                        BluetoothMyLogger.d("connect failed when read lock version: " + i);
                        int unused = BleBindActivityV2.this.aa = i;
                        BleBindActivityV2.this.T.a(0);
                        BleBindActivityV2.this.c(11);
                    }
                }
            }, 1);
        }
    }

    /* access modifiers changed from: private */
    public void a(final Response.BleConnectResponse bleConnectResponse, final int i2) {
        if (isValid()) {
            XmBluetoothManager.getInstance().connect(this.S.mac, new Response.BleConnectResponse() {
                /* renamed from: a */
                public void onResponse(int i, Bundle bundle) {
                    if (i == 0) {
                        bleConnectResponse.onResponse(i, bundle);
                    } else if (i2 > 0) {
                        BleBindActivityV2.this.mHandler.postDelayed(new Runnable() {
                            public void run() {
                                BleBindActivityV2.this.a(bleConnectResponse, i2 - 1);
                            }
                        }, 2000);
                    } else {
                        bleConnectResponse.onResponse(i, bundle);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public String a(int i2) {
        String str;
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("error code = " + i2);
            sb.append(", isScanned = " + this.ag);
            int i3 = 0;
            BleDevice d2 = BLEDeviceManager.d(this.S.mac);
            if (d2 != null) {
                i3 = d2.rssi;
            }
            sb.append(", rssi = " + i3);
            sb.append(", model = " + this.S.model);
            sb.append(", defaultLocale = " + Locale.getDefault());
            Locale I2 = CoreApi.a().I();
            if (I2 == null) {
                I2 = Locale.getDefault();
            }
            sb.append(", chooseLocale = " + I2);
            ServerBean a2 = ServerCompact.a(CommonApplication.getAppContext());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(", server = ");
            if (a2 == null) {
                str = null;
            } else {
                str = a2.f1530a + ":" + a2.b + ":" + a2.c;
            }
            sb2.append(str);
            sb.append(sb2.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public void a(int i2, Bundle bundle) {
        BluetoothMyLogger.d(String.format("BleBind Step 1/3 response: code = %s", new Object[]{XmBluetoothManager.Code.toString(i2)}));
        if (i2 != -55) {
            if (i2 != -32) {
                switch (i2) {
                    case -7:
                        break;
                    case -6:
                    case -5:
                        break;
                    default:
                        switch (i2) {
                            case -2:
                                B();
                                return;
                            case -1:
                                break;
                            case 0:
                                this.T.a(80, 5000, this.ao);
                                BluetoothReporter.b(this.S.mac);
                                return;
                            default:
                                this.T.a(0);
                                c(11);
                                return;
                        }
                }
            }
            this.T.a(0);
            c(11);
            BluetoothReporter.a(this.S.mac, bundle);
            return;
        }
        this.T.a(0);
        c(12);
        BluetoothReporter.a(this.S.mac);
    }

    /* access modifiers changed from: private */
    public void b(int i2, final Bundle bundle) {
        BluetoothMyLogger.d(String.format("BleBind Step 2/3 response: code = %s", new Object[]{XmBluetoothManager.Code.toString(i2)}));
        if (bundle != null) {
            this.W.putAll(bundle);
        }
        switch (i2) {
            case -55:
            case -32:
            case -28:
            case -27:
            case -7:
            case -6:
            case -5:
            case -1:
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        BleBindActivityV2.this.T.a(0);
                        BleBindActivityV2.this.c(21);
                        BluetoothReporter.b(BleBindActivityV2.this.S.mac, bundle);
                    }
                }, 2000);
                return;
            case -37:
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        BleBindActivityV2.this.T.a(0);
                        BleBindActivityV2.this.c(29);
                        BluetoothReporter.b(BleBindActivityV2.this.S.mac, bundle);
                    }
                }, 2000);
                return;
            case -35:
                this.T.a(0);
                c(28);
                return;
            case -34:
                this.T.a(0);
                c(27);
                return;
            case -33:
                String string = bundle.getString(BluetoothConstants.D);
                if (!TextUtils.isEmpty(string)) {
                    a(string);
                    return;
                }
                return;
            case -17:
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        BleBindActivityV2.this.T.a(0);
                        BleBindActivityV2.this.c(25);
                        BluetoothReporter.b(BleBindActivityV2.this.S.mac, bundle);
                    }
                }, 2000);
                return;
            case -16:
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        BleBindActivityV2.this.T.a(0);
                        BleBindActivityV2.this.c(24);
                        BluetoothReporter.b(BleBindActivityV2.this.S.mac, bundle);
                    }
                }, 2000);
                return;
            case -14:
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        BleBindActivityV2.this.T.a(0);
                        BleBindActivityV2.this.c(26);
                        BluetoothReporter.e(BleBindActivityV2.this.S.mac);
                    }
                }, 2000);
                return;
            case -10:
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        BleBindActivityV2.this.T.a(0);
                        BleBindActivityV2.this.c(23);
                        BluetoothReporter.c(BleBindActivityV2.this.S.mac, bundle);
                    }
                }, 2000);
                return;
            case -2:
                B();
                return;
            case 0:
                this.T.a(90, 10000, this.ap);
                BluetoothReporter.c(this.S.mac);
                return;
            default:
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        BleBindActivityV2.this.T.a(0);
                        BleBindActivityV2.this.c(21);
                        BluetoothReporter.b(BleBindActivityV2.this.S.mac, bundle);
                    }
                }, 2000);
                return;
        }
    }

    /* access modifiers changed from: private */
    public void c(int i2, Bundle bundle) {
        BluetoothMyLogger.d(String.format("BleBind Step 3/3 response: code = %s", new Object[]{XmBluetoothManager.Code.toString(i2)}));
        if (bundle != null) {
            this.W.putAll(bundle);
        }
        if (this.R != 36) {
            if (!(i2 == -30 || i2 == -26)) {
                if (i2 != -14) {
                    switch (i2) {
                        case -2:
                            B();
                            return;
                        case -1:
                            break;
                        case 0:
                            this.T.a(99, 15000, (BoostCallback) null);
                            this.mHandler.sendEmptyMessageDelayed(8193, 15000);
                            this.mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    BleBindActivityV2.this.k();
                                    BleBindActivityV2.this.g();
                                    BluetoothReporter.f(BleBindActivityV2.this.S.mac);
                                    RemoteFamilyApi.a().a(BleBindActivityV2.this.S.did, TimeZone.getDefault().getID(), (JSONObject) null, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                                        /* renamed from: a */
                                        public void onSuccess(JSONObject jSONObject) {
                                            Log.d("timezone", "upload successfully!");
                                        }

                                        public void onFailure(Error error) {
                                            Log.d("timezone", "upload failed! Error = " + error.toString());
                                        }
                                    });
                                }
                            }, 2000);
                            return;
                        default:
                            this.mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    BleBindActivityV2.this.T.a(0);
                                    BleBindActivityV2.this.c(34);
                                }
                            }, 2000);
                            return;
                    }
                } else {
                    this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            BleBindActivityV2.this.T.a(0);
                            BleBindActivityV2.this.c(35);
                            BluetoothReporter.e(BleBindActivityV2.this.S.mac);
                        }
                    }, 2000);
                    return;
                }
            }
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    BleBindActivityV2.this.T.a(0);
                    BleBindActivityV2.this.c(34);
                    BluetoothReporter.d(BleBindActivityV2.this.S.mac);
                }
            }, 2000);
        }
    }

    private String b(int i2) {
        return getResources().getText(i2) + " (" + this.aa + Operators.BRACKET_END_STR;
    }

    /* access modifiers changed from: private */
    public void g() {
        BluetoothMyLogger.d(String.format("Start load plugin", new Object[0]));
        PluginRecord d2 = CoreApi.a().d(this.S.model);
        if (d2.k() || d2.l()) {
            this.mHandler.removeMessages(8193);
            c(43);
            this.T.a(101, 1000, (BoostCallback) null);
            return;
        }
        CoreApi.a().a(this.S.model, (CoreApi.DownloadPluginCallback) new CoreApi.DownloadPluginCallback() {
            private long b = 0;

            public void onDownInfoSuccess(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
            }

            public void onProgress(PluginRecord pluginRecord, float f) {
            }

            public void onStartAlready(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
            }

            public void onStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                this.b = System.currentTimeMillis();
            }

            public void onSuccess(PluginRecord pluginRecord) {
                if (this.b > 0 && pluginRecord != null) {
                    STAT.i.a(System.currentTimeMillis() - this.b, pluginRecord.o());
                }
                if (!BleBindActivityV2.this.isFinishing()) {
                    BleBindActivityV2.this.mHandler.removeMessages(8193);
                    BluetoothMyLogger.f("Plugin Download onSuccess");
                    BleBindActivityV2.this.c(43);
                    BleBindActivityV2.this.T.a(101, 1000, (BoostCallback) null);
                }
            }

            public void onFailure(PluginError pluginError) {
                if (!BleBindActivityV2.this.isFinishing()) {
                    BleBindActivityV2.this.mHandler.removeMessages(8193);
                    BluetoothMyLogger.f("Plugin Download onFailure");
                    BleBindActivityV2.this.T.a(0);
                    BleBindActivityV2.this.c(41);
                    BluetoothReporter.g(BleBindActivityV2.this.S.mac);
                }
            }

            public void onCancel() {
                if (!BleBindActivityV2.this.isFinishing()) {
                    BleBindActivityV2.this.mHandler.removeMessages(8193);
                    BluetoothMyLogger.f("Plugin Download onCancel");
                    BleBindActivityV2.this.T.a(0);
                    BleBindActivityV2.this.c(41);
                    BluetoothReporter.g(BleBindActivityV2.this.S.mac);
                }
            }
        });
    }

    private boolean h() {
        MiotBleAdvPacket d2 = this.S.d();
        return (d2 == null || d2.g == null || d2.g.l != 8) ? false : true;
    }

    /* access modifiers changed from: private */
    public boolean i() {
        PluginRecord d2 = CoreApi.a().d(this.S.model);
        if (d2 != null && d2.c().t() == Device.PID_BLE_MESH) {
            return true;
        }
        BleDevice d3 = BLEDeviceManager.d(this.S.mac);
        MiotBleAdvPacket d4 = this.S.d();
        MiotBleAdvPacket miotBleAdvPacket = null;
        if (d3 != null) {
            miotBleAdvPacket = d3.d();
        }
        if (d4 != null && d4.f14276a != null && d4.f14276a.e) {
            return true;
        }
        if (d3 == null || miotBleAdvPacket == null || miotBleAdvPacket.f14276a == null || !miotBleAdvPacket.f14276a.e) {
            return false;
        }
        return true;
    }

    private boolean j() {
        PluginRecord d2 = CoreApi.a().d(this.S.model);
        if (d2 != null && d2.c() != null && d2.c().I() == 1) {
            return true;
        }
        BleDevice d3 = BLEDeviceManager.d(this.S.mac);
        MiotBleAdvPacket d4 = this.S.d();
        MiotBleAdvPacket miotBleAdvPacket = null;
        if (d3 != null) {
            miotBleAdvPacket = d3.d();
        }
        if (d4 != null && d4.f14276a != null && d4.f14276a.k == 1) {
            return true;
        }
        if (miotBleAdvPacket == null || miotBleAdvPacket.f14276a == null || miotBleAdvPacket.f14276a.k != 1) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void k() {
        this.S.isOnline = true;
        this.S.b(BleCacheUtils.e(this.S.mac));
        this.S.version = this.W.getString("key_version");
        SmartHomeDeviceManager.a().b((Device) this.S);
        a((Device) this.S);
        HomeManager.I = this.S;
        HomeManager a2 = HomeManager.a();
        a2.a((String) null, HomeManager.a().e());
        a2.a((Device) this.S, true);
        a2.b(this.S.did, true);
    }

    private void a(Device device) {
        Home m2;
        if (device != null && (m2 = HomeManager.a().m()) != null) {
            HomeManager.a().a(m2, (Room) null, device, (HomeManager.IHomeOperationCallback) null);
        }
    }

    private int l() {
        switch (this.ae) {
            case 1:
                return R.string.ble_new_normal_auth_step_loading;
            case 2:
                return R.string.ble_new_standard_auth_step_loading;
            case 3:
                return R.string.ble_new_auth_step_loading;
            case 4:
                return R.string.ble_new_mesh_auth_step_loading;
            default:
                return R.string.ble_new_auth_step_loading;
        }
    }

    private int m() {
        switch (this.ae) {
            case 1:
                return R.string.ble_new_normal_auth_step_success;
            case 2:
                return R.string.ble_new_standard_auth_step_success;
            case 3:
                return R.string.ble_new_auth_step_success;
            case 4:
                return R.string.ble_new_mesh_auth_step_success;
            default:
                return R.string.ble_new_auth_step_success;
        }
    }

    private int n() {
        switch (this.ae) {
            case 1:
                return R.string.ble_new_normal_auth_step_failed;
            case 2:
                return R.string.ble_new_standard_auth_step_failed;
            case 3:
                return R.string.ble_new_auth_step_failed;
            case 4:
                return R.string.ble_new_mesh_auth_step_failed;
            default:
                return R.string.ble_new_auth_step_failed;
        }
    }

    /* access modifiers changed from: private */
    public void c(int i2) {
        if (!isFinishing()) {
            this.R = i2;
            if (!isFinishing()) {
                switch (i2) {
                    case 10:
                        this.P.addNextStep((int) R.string.ble_new_connect_step_loading, (int) R.string.ble_new_connect_loading_title);
                        this.P.startProgressAnimation(1);
                        return;
                    case 11:
                    case 12:
                        STAT.c.h(this.S.model, this.aa);
                        a(b(R.string.ble_new_connect_step_failed), R.string.ble_new_connect_loading_title, R.drawable.common_bind_app_connect_device_failed);
                        p();
                        return;
                    case 13:
                        this.P.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.ble_new_connect_step_success, (int) R.string.ble_new_connect_loading_title);
                        return;
                    default:
                        switch (i2) {
                            case 20:
                                this.P.addNextStep(l(), (int) R.string.ble_new_connect_loading_title);
                                this.P.startProgressAnimation(2);
                                return;
                            case 21:
                            case 22:
                                STAT.c.h(this.S.model, this.aa);
                                d();
                                a(b(n()), R.string.ble_new_connect_failed_title, R.drawable.common_bind_app_connect_device_failed);
                                p();
                                return;
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                                STAT.c.h(this.S.model, this.aa);
                                d();
                                this.P.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, m(), (int) R.string.ble_new_connect_loading_title);
                                Resources resources = this.P.getResources();
                                this.P.addNextStep(resources.getString(R.string.ble_new_bind_step_loading), resources.getString(R.string.ble_new_network_loading_title));
                                a(o(), R.string.ble_new_device_has_been_bind, R.drawable.common_bind_device_has_binded_failed);
                                BleCacheUtils.x(this.S.mac);
                                q();
                                return;
                            case 27:
                                STAT.c.h(this.S.model, this.aa);
                                a(b(R.string.ble_auth_pair_code_failed), R.string.ble_new_connect_failed_title, R.drawable.common_bind_app_connect_device_failed);
                                p();
                                if (this.Y != null) {
                                    this.Y.onFailed();
                                    return;
                                }
                                return;
                            case 28:
                                STAT.c.h(this.S.model, this.aa);
                                a(b(R.string.ble_auth_pair_code_expired), R.string.ble_new_connect_failed_title, R.drawable.common_bind_app_connect_device_failed);
                                p();
                                return;
                            case 29:
                                STAT.c.h(this.S.model, this.aa);
                                a(b(n()), R.string.device_not_support_now, R.drawable.common_bind_app_connect_device_failed);
                                q();
                                return;
                            case 30:
                                d();
                                this.P.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, m(), (int) R.string.ble_new_connect_loading_title);
                                return;
                            default:
                                switch (i2) {
                                    case 33:
                                        Resources resources2 = this.P.getResources();
                                        this.P.addNextStep(resources2.getString(R.string.ble_new_bind_step_loading), resources2.getString(R.string.ble_new_network_loading_title));
                                        this.P.startProgressAnimation(3);
                                        return;
                                    case 34:
                                        STAT.c.h(this.S.model, this.aa);
                                        a(o(), R.string.ble_new_network_failed_title, R.drawable.common_bind_app_connect_network_failed);
                                        p();
                                        return;
                                    case 35:
                                        STAT.c.h(this.S.model, this.aa);
                                        a(o(), R.string.ble_new_device_has_been_bind, R.drawable.common_bind_device_has_binded_failed);
                                        q();
                                        return;
                                    case 36:
                                        this.P.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.ble_new_bind_step_success, (int) R.string.ble_new_connect_loading_title);
                                        return;
                                    default:
                                        switch (i2) {
                                            case 40:
                                                this.P.addNextStep((int) R.string.ble_new_download_plugin_step_loading, (int) R.string.ble_new_network_loading_title);
                                                this.P.startProgressAnimation(4);
                                                return;
                                            case 41:
                                                STAT.c.h(this.S.model, 1);
                                                a(getResources().getText(R.string.ble_new_download_plugin_step_failed).toString(), R.string.ble_new_network_failed_title, R.drawable.common_bind_app_connect_network_failed);
                                                s();
                                                return;
                                            case 42:
                                                this.P.updateCurrentStep(CommonBindView.StepStatus.LOADING, getResources().getText(R.string.ble_new_download_plugin_step_timeout).toString(), (int) R.string.ble_new_network_failed_title);
                                                r();
                                                return;
                                            case 43:
                                                this.P.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.ble_new_download_plugin_step_success, (int) R.string.ble_new_add_device_success_title);
                                                return;
                                            case 44:
                                                r();
                                                return;
                                            default:
                                                return;
                                        }
                                }
                        }
                }
            }
        }
    }

    private String o() {
        String string = this.W.getString(BluetoothConstants.ae);
        if (TextUtils.isEmpty(string)) {
            return getResources().getText(R.string.ble_new_bind_step_failed) + " (" + this.aa + Operators.BRACKET_END_STR;
        }
        return getResources().getText(R.string.ble_new_bind_step_failed) + " (" + this.aa + ", did = " + string + Operators.BRACKET_END_STR;
    }

    private void a(String str, int i2, int i3) {
        this.P.updateCurrentStep(CommonBindView.StepStatus.FAILED, str, i2, true);
        this.P.setBindFailed(i3);
    }

    private void p() {
        this.P.setCommonBtnVisibility(0);
        this.P.setCommonBtnText(getString(R.string.ble_new_reopen_and_retry));
        this.P.setCommonBtnListener(this.aq);
    }

    private void q() {
        this.P.setCommonBtnVisibility(0);
        this.P.setCommonBtnText(getString(R.string.exit));
        this.P.setCommonBtnListener(this.ar);
    }

    private void r() {
        this.ac = true;
        STAT.d.a(this.S.model, System.currentTimeMillis() - this.ab);
        STAT.c.n(this.S.model);
        DeviceBindStatis.b((Context) this, this.S.model, this.S.mac);
        this.P.setCommonBtnVisibility(0);
        this.P.setCommonBtnListener(this.at);
        this.P.setBindSuccess();
    }

    private void s() {
        this.P.setCommonBtnVisibility(0);
        this.P.setCommonBtnText(getString(R.string.retry));
        this.P.setCommonBtnListener(this.as);
    }

    /* access modifiers changed from: private */
    public void t() {
        Intent intent = new Intent(this, InitDeviceRoomActivity.class);
        intent.putExtra("device_id", this.S.did);
        intent.putExtra("device_mac", this.S.mac);
        startActivity(intent);
        finish();
    }

    /* access modifiers changed from: private */
    public void u() {
        BluetoothMyLogger.d(String.format("cancelDeviceBind", new Object[0]));
        if (this.U != null) {
            try {
                this.U.cancel();
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            this.U = null;
        }
        this.T.a(0);
    }

    /* access modifiers changed from: private */
    public void v() {
        w();
        if (BluetoothUtils.b()) {
            BluetoothStateHelper.b(new BleResponse() {
                public void a(int i, Object obj) {
                    BluetoothStateHelper.a((BleResponse) new BleResponse() {
                        public void a(int i, Object obj) {
                            BleBindActivityV2.this.x();
                            BleBindActivityV2.this.a(true);
                        }
                    });
                }
            });
        } else {
            BluetoothStateHelper.a((BleResponse) new BleResponse() {
                public void a(int i, Object obj) {
                    BleBindActivityV2.this.x();
                    BleBindActivityV2.this.a(true);
                }
            });
        }
    }

    private void w() {
        if (this.V == null) {
            this.V = new XQProgressDialog(this);
            this.V.setMessage(getString(R.string.reopening_bluetooth));
            this.V.setCancelable(false);
        }
        if (!this.V.isShowing()) {
            this.V.show();
        }
    }

    /* access modifiers changed from: private */
    public void x() {
        if (this.V != null && this.V.isShowing()) {
            this.V.dismiss();
        }
    }

    private boolean y() {
        Intent intent = getIntent();
        this.Q = intent.getStringExtra(com.xiaomi.smarthome.device.bluetooth.utils.BluetoothConstants.i);
        if (z()) {
            this.S = BleMatchActivity.mMatchedDevice;
        } else if (A()) {
            this.S = (BleDevice) SmartHomeDeviceManager.a().b(intent.getStringExtra("extra_did"));
        }
        return this.S != null;
    }

    private boolean z() {
        return com.xiaomi.smarthome.device.bluetooth.utils.BluetoothConstants.m.equalsIgnoreCase(this.Q);
    }

    private boolean A() {
        return com.xiaomi.smarthome.device.bluetooth.utils.BluetoothConstants.n.equalsIgnoreCase(this.Q);
    }

    /* access modifiers changed from: private */
    public void B() {
        BluetoothMyLogger.d(String.format("BleBindActivity quitBindingActivity", new Object[0]));
        this.S.l();
        finish();
    }

    public void handleMessage(Message message) {
        if (message.what == 8193) {
            c(42);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (BleBindActivityV2.this.S != null) {
                    BluetoothHelper.b(BleBindActivityV2.this.S.mac);
                }
            }
        }, 1000);
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (this.Z == null || this.Y == null || !this.Z.isShowing()) {
            return super.onKeyDown(i2, keyEvent);
        }
        this.Y.onBack();
        return false;
    }

    public void onBackPressed() {
        this.au.onClick((View) null);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (!(this.ac || this.S == null || this.aa == -14 || this.aa == -17 || this.aa == -34 || this.aa == -35 || this.aa == -2 || this.aa == -32 || this.aa == -5 || this.aa == -55 || !this.ag || !NetworkUtils.c())) {
            STAT.i.b(this.S.model, System.currentTimeMillis() - this.ab);
        }
        this.af.a();
        this.mHandler.removeCallbacksAndMessages((Object) null);
        if (this.Z != null && this.Z.isShowing()) {
            this.Z.dismiss();
        }
        this.Y = null;
        this.Z = null;
        if (this.T != null) {
            this.T.b();
        }
        if (this.V != null && this.V.isShowing()) {
            this.V.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i2 == 3000) {
            String str = null;
            if (i3 == -1) {
                str = intent.getStringExtra(KEY_QRCODE_OOB);
            }
            if (!TextUtils.isEmpty(str)) {
                this.X.a(str);
                a(false);
                return;
            }
            finish();
        } else if (i2 == 4000) {
            if (PermissionHelper.a(Permission.Group.b)) {
                c();
                return;
            }
            ToastUtil.a((CharSequence) getResources().getString(R.string.permission_failure));
            finish();
        } else if (i2 != 5000) {
        } else {
            if (i3 == -1) {
                a(false);
                return;
            }
            this.S.l();
            finish();
        }
    }

    private static boolean a(Activity activity, RequestCameraPermissionCallback requestCameraPermissionCallback) {
        if (PermissionHelper.a(Permission.Group.b)) {
            requestCameraPermissionCallback.a();
            return true;
        }
        a(activity, (Rationale) null, activity.getApplication().getResources().getString(R.string.permission_successfully), activity.getApplication().getResources().getString(R.string.permission_failure), true, activity.getApplication().getResources().getString(R.string.tips), activity.getApplication().getResources().getString(R.string.permission_tips_camera_msg), requestCameraPermissionCallback, Permission.Group.b);
        return false;
    }

    private static void a(Activity activity, Rationale rationale, String str, String str2, boolean z2, String str3, String str4, final RequestCameraPermissionCallback requestCameraPermissionCallback, String... strArr) {
        final WeakReference weakReference = new WeakReference(activity);
        final boolean z3 = z2;
        final String str5 = str2;
        final String str6 = str3;
        final String str7 = str4;
        final RequestCameraPermissionCallback requestCameraPermissionCallback2 = requestCameraPermissionCallback;
        AndPermission.b((Activity) weakReference.get()).a(strArr).a(rationale).a((Action) new Action() {
            public void onAction(List<String> list) {
                requestCameraPermissionCallback.a();
            }
        }).b(new Action() {
            public void onAction(@NonNull List<String> list) {
                if (!z3) {
                    ToastUtil.a((CharSequence) str5);
                    return;
                }
                Activity activity = (Activity) weakReference.get();
                if (activity != null && !activity.isFinishing() && Build.VERSION.SDK_INT >= 17 && !activity.isDestroyed()) {
                    if (AndPermission.a(activity, list)) {
                        BleBindActivityV2.a(activity, str6, str7, list, requestCameraPermissionCallback2);
                        return;
                    }
                    ToastUtil.a((CharSequence) str5);
                    requestCameraPermissionCallback2.b();
                }
            }
        }).a();
    }

    /* access modifiers changed from: private */
    public static void a(Activity activity, String str, String str2, List<String> list, final RequestCameraPermissionCallback requestCameraPermissionCallback) {
        String format = String.format(str2, new Object[]{TextUtils.join("\n", Permission.a((Context) activity, list))});
        final SettingService a2 = AndPermission.a(activity);
        new MLAlertDialog.Builder(activity).a(false).a((CharSequence) str).b((CharSequence) format).a((int) R.string.setting, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                a2.a(4000);
                requestCameraPermissionCallback.d();
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                a2.c();
                requestCameraPermissionCallback.c();
            }
        }).b().show();
    }
}
