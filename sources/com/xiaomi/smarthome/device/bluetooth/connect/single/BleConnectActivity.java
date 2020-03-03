package com.xiaomi.smarthome.device.bluetooth.connect.single;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bluetooth.Response;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectHandler;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.BluetoothStateHelper;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.device.bluetooth.BluetoothReporter;
import com.xiaomi.smarthome.device.bluetooth.connect.count.BoostCallback;
import com.xiaomi.smarthome.device.bluetooth.connect.count.ProgressCounter;
import com.xiaomi.smarthome.device.bluetooth.connect.count.ProgressCounterImpl;
import com.xiaomi.smarthome.device.bluetooth.connect.count.ProgressNotifier;
import com.xiaomi.smarthome.device.bluetooth.connect.single.BleDeviceFinder;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.device.renderer.DevicePluginOpener;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.IBleSecureConnectResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.widget.PieProgressBar;
import java.util.Locale;

public class BleConnectActivity extends BaseActivity {
    private static final int L = 50000;

    /* renamed from: a  reason: collision with root package name */
    private static final String f15190a = "BleConnectActivity";
    private static final int b = 60;
    private static final int c = 80;
    private static final int d = 99;
    private static final int e = 101;
    private static final int f = 30000;
    private static final int g = 5000;
    private static final int h = 10000;
    private static final int i = 1000;
    private static final int j = 10;
    private static final int k = 11;
    private static final int l = 12;
    private static final int m = 20;
    private static final int n = 21;
    private static final int o = 22;
    private static final int p = 23;
    private static final int q = 24;
    private static final int r = 25;
    private static final int s = 26;
    private static final int t = 44;
    /* access modifiers changed from: private */
    public TextView A;
    /* access modifiers changed from: private */
    public int B;
    /* access modifiers changed from: private */
    public Device C;
    /* access modifiers changed from: private */
    public ProgressCounter D;
    /* access modifiers changed from: private */
    public ISecureConnectHandler E;
    private MLAlertDialog F;
    /* access modifiers changed from: private */
    public BleConnectOptions G;
    /* access modifiers changed from: private */
    public boolean H = false;
    /* access modifiers changed from: private */
    public boolean I = false;
    /* access modifiers changed from: private */
    public int J = -9999;
    /* access modifiers changed from: private */
    public boolean K = false;
    /* access modifiers changed from: private */
    public int M;
    /* access modifiers changed from: private */
    public long N = 0;
    /* access modifiers changed from: private */
    public Runnable O = new Runnable() {
        public void run() {
            if (BluetoothUtils.f(BleConnectActivity.this.C.mac) && BleConnectActivity.this.K) {
                BleConnectActivity.this.D.a(0);
                BleConnectActivity.this.c(44);
            } else if (System.currentTimeMillis() - BleConnectActivity.this.N < 50000) {
                BleConnectActivity.this.mHandler.postDelayed(BleConnectActivity.this.O, 1000);
            } else {
                BleConnectActivity.this.D.a(0);
                BleConnectActivity.this.c(11);
            }
        }
    };
    private BleDeviceFinder P = new BleDeviceFinder();
    private BleDeviceFinder.BleDeviceFinderCallback Q = new BleDeviceFinder.BleDeviceFinderCallback() {
        public void a() {
            if (BleConnectActivity.this.isValid()) {
                boolean unused = BleConnectActivity.this.I = true;
                BluetoothMyLogger.e("BleConnectActivity DeviceFinder onFound");
                BleConnectActivity.this.d();
            }
        }

        public void b() {
            if (BleConnectActivity.this.isValid()) {
                boolean unused = BleConnectActivity.this.I = false;
                BluetoothMyLogger.e("BleConnectActivity DeviceFinder onTimeout");
                BleConnectActivity.this.d();
            }
        }
    };
    private BroadcastReceiver R = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.equals(action, "com.xiaomi.smarthome.bluetooth.connect_status_changed")) {
                String stringExtra = intent.getStringExtra("key_device_address");
                int intExtra = intent.getIntExtra("key_connect_status", 0);
                if (!TextUtils.equals(stringExtra, BleConnectActivity.this.C.mac)) {
                    return;
                }
                if (intExtra == 16 || intExtra == 32) {
                    BluetoothMyLogger.d(String.format("BleBindActivityV2 bluetooth status change: %s, %s", new Object[]{BluetoothMyLogger.a(stringExtra), String.valueOf(intExtra)}));
                    boolean unused = BleConnectActivity.this.K = false;
                }
            } else if (TextUtils.equals(action, "action.online.status.changed")) {
                String stringExtra2 = intent.getStringExtra("extra_mac");
                int intExtra2 = intent.getIntExtra("extra_online_status", 0);
                if (TextUtils.equals(stringExtra2, BleConnectActivity.this.C.mac) && intExtra2 == 80) {
                    BluetoothMyLogger.d(String.format("BleBindActivityV2 bluetooth login success: %s", new Object[]{BluetoothMyLogger.a(stringExtra2)}));
                    boolean unused2 = BleConnectActivity.this.K = true;
                }
            }
        }
    };
    private final ProgressNotifier S = new ProgressNotifier() {
        public void a(int i) {
            if (i >= 0 && i <= 100) {
                BleConnectActivity.this.A.setText(String.format("%d%%", new Object[]{Integer.valueOf(i)}));
                BleConnectActivity.this.x.setPercent((float) i);
            }
            if (i == 100) {
                BleConnectActivity.this.c(44);
            }
        }
    };
    private final BoostCallback T = new BoostCallback() {
        public void a() {
            BleConnectActivity.this.c(20);
        }
    };
    /* access modifiers changed from: private */
    public IBleSecureConnectResponse U = new IBleSecureConnectResponse() {
        public void a(int i, Bundle bundle) {
            BluetoothMyLogger.d(String.format("BleBindActivity onConnectResponse: code = %d", new Object[]{Integer.valueOf(i)}));
            int unused = BleConnectActivity.this.J = i;
            BleConnectActivity.this.a(i, bundle);
            if (i != 0 && i != -2) {
                BluetoothInternationLogUtil.a("BleConnectActivity onConnectResponse " + BleConnectActivity.this.a(i));
            }
        }

        public void b(int i, Bundle bundle) {
            BluetoothMyLogger.d(String.format("BleBindActivity onAuthResponse: code = %d", new Object[]{Integer.valueOf(i)}));
            int unused = BleConnectActivity.this.J = i;
            BleConnectActivity.this.b(i, bundle);
            if (i != 0 && i != -2) {
                BluetoothInternationLogUtil.a("BleConnectActivity onAuthResponse " + BleConnectActivity.this.a(i));
            }
        }

        public void c(int i, Bundle bundle) {
            BluetoothMyLogger.d(String.format("BleBindActivity onBindResponse: code = %d", new Object[]{Integer.valueOf(i)}));
            int unused = BleConnectActivity.this.J = i;
            BleConnectActivity.this.c(i, bundle);
            if (i != 0 && i != -2) {
                BluetoothInternationLogUtil.a("BleConnectActivity onBindResponse " + BleConnectActivity.this.a(i));
            }
        }

        public void d(int i, Bundle bundle) {
            BluetoothMyLogger.d(String.format("BleBindActivity onLastResponse: code = %d", new Object[]{Integer.valueOf(i)}));
            int unused = BleConnectActivity.this.J = i;
            if (i == -12) {
                BleConnectActivity.this.c(11);
            } else if (i == -13) {
                long unused2 = BleConnectActivity.this.N = System.currentTimeMillis();
                BleConnectActivity.this.mHandler.removeCallbacks(BleConnectActivity.this.O);
                BleConnectActivity.this.mHandler.postDelayed(BleConnectActivity.this.O, 1000);
                BleConnectActivity.this.D.a(80, 50000, (BoostCallback) null);
                BleConnectActivity.this.c(10);
            } else if (i == 0) {
            } else {
                if (BleConnectActivity.this.B == 10) {
                    BleConnectActivity.this.c(11);
                } else if (BleConnectActivity.this.B == 20) {
                    BleConnectActivity.this.c(21);
                }
            }
        }
    };
    private final BoostCallback V = new BoostCallback() {
        public void a() {
            BleConnectActivity.this.c(44);
        }
    };
    private final View.OnClickListener W = new View.OnClickListener() {
        public void onClick(View view) {
            BleConnectActivity.this.y.setEnabled(false);
            BleConnectActivity.this.z.setEnabled(false);
            BleConnectActivity.this.j();
            BleConnectActivity.this.n();
        }
    };
    private final View.OnClickListener X = new View.OnClickListener() {
        public void onClick(View view) {
            BleConnectActivity.this.k();
        }
    };
    private EditText u;
    private TextView v;
    private ImageView w;
    /* access modifiers changed from: private */
    public PieProgressBar x;
    /* access modifiers changed from: private */
    public Button y;
    /* access modifiers changed from: private */
    public Button z;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!a()) {
            finish();
            return;
        }
        this.G = new BleConnectOptions.Builder().a(1).c(31000).b(2).d(15000).a();
        BLEDeviceManager.f();
        this.D = new ProgressCounterImpl(this.S);
        b();
        if (BluetoothUtils.f(this.C.mac)) {
            BluetoothMyLogger.d("BleConnectActivity device has ConnectedOrBonded: " + BluetoothMyLogger.a(this.C.mac));
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xiaomi.smarthome.bluetooth.connect_status_changed");
        intentFilter.addAction("action.online.status.changed");
        BluetoothUtils.a(this.R, intentFilter);
        c();
    }

    private boolean a() {
        this.C = SmartHomeDeviceManager.a().b(getIntent().getStringExtra("extra_did"));
        return this.C != null;
    }

    private void b() {
        setContentView(R.layout.activity_ble_connect);
        this.u = (EditText) findViewById(R.id.title);
        this.w = (ImageView) findViewById(R.id.icon);
        this.x = (PieProgressBar) findViewById(R.id.progress_bar);
        this.v = (TextView) findViewById(R.id.subtitle);
        this.y = (Button) findViewById(R.id.left_btn);
        this.z = (Button) findViewById(R.id.right_btn);
        this.A = (TextView) findViewById(R.id.progress_text);
        this.y.setOnClickListener(this.W);
        this.z.setOnClickListener(this.X);
    }

    /* access modifiers changed from: private */
    public void c() {
        this.D.a(0);
        this.D.a(60, 30000, this.T);
        c(10);
        this.P.a(this.C.mac, this.Q);
    }

    /* access modifiers changed from: private */
    public void d() {
        if (!this.C.isOwner() && !SmartHomeDeviceManager.a().i(this.C.mac)) {
            this.C.setOwner(true);
            this.C.ownerId = "";
            this.C.ownerName = "";
            BleCacheUtils.g(this.C.mac, "");
            BleCacheUtils.b(this.C.mac, "");
            BleCacheUtils.b(this.C.mac, 0);
        }
        if (e()) {
            BluetoothMyLogger.d("BleConnectActivity Start bleMeshConnect");
            this.M = 4;
            BluetoothHelper.a(this.C.mac, this.C.did, this.C.token, this.G, this.U);
        } else if (!f()) {
            BluetoothMyLogger.d("BleConnectActivity Start Normal Connect");
            BleDevice d2 = BLEDeviceManager.d(this.C.mac);
            if (d2 == null || d2.d() == null || d2.d().f14276a == null) {
                a((Response.BleConnectResponse) new Response.BleConnectResponse() {
                    /* renamed from: a */
                    public void onResponse(int i, Bundle bundle) {
                        if (BleConnectActivity.this.isValid()) {
                            if (i == 0) {
                                XmBluetoothManager.getInstance().isBleCharacterExist(BleConnectActivity.this.C.mac, BluetoothConstants.i, BluetoothConstants.V, new Response.BleResponse<Void>() {
                                    /* renamed from: a */
                                    public void onResponse(int i, Void voidR) {
                                        if (i == 0) {
                                            BluetoothMyLogger.d("Start bleStandardAuthConnect after read character");
                                            int unused = BleConnectActivity.this.M = 2;
                                            ISecureConnectHandler unused2 = BleConnectActivity.this.E = BluetoothHelper.e(BleConnectActivity.this.C.mac, BleConnectActivity.this.G, BleConnectActivity.this.U);
                                            return;
                                        }
                                        BluetoothMyLogger.d("Start secureConnect after read character");
                                        int unused3 = BleConnectActivity.this.M = 1;
                                        ISecureConnectHandler unused4 = BleConnectActivity.this.E = BluetoothHelper.a(BleConnectActivity.this.C.mac, BleConnectActivity.this.G, BleConnectActivity.this.U);
                                    }
                                });
                                return;
                            }
                            BluetoothMyLogger.d("connect failed when read device character: " + i);
                            int unused = BleConnectActivity.this.J = i;
                            BleConnectActivity.this.D.a(0);
                            BleConnectActivity.this.c(11);
                        }
                    }
                }, 1);
            } else if (d2.d().f14276a.k == 2) {
                BluetoothMyLogger.d("BleConnectActivity Start bleStandardAuthConnect");
                this.M = 2;
                this.E = BluetoothHelper.e(this.C.mac, this.G, this.U);
            } else {
                BluetoothMyLogger.d("BleConnectActivity Start secureConnect");
                this.M = 1;
                this.E = BluetoothHelper.a(this.C.mac, this.G, this.U);
            }
        } else if (this.C.isOwner()) {
            BluetoothMyLogger.d("BleConnectActivity Start securityChipConnect");
            this.M = 3;
            this.E = BluetoothHelper.c(this.C.mac, this.G, this.U);
        } else {
            BluetoothMyLogger.d("BleConnectActivity Start securityChipSharedDeviceConnect");
            this.M = 3;
            this.E = BluetoothHelper.b(this.C.mac, this.G, this.U);
        }
    }

    /* access modifiers changed from: private */
    public void a(final Response.BleConnectResponse bleConnectResponse, final int i2) {
        if (isValid()) {
            XmBluetoothManager.getInstance().connect(this.C.mac, new Response.BleConnectResponse() {
                /* renamed from: a */
                public void onResponse(int i, Bundle bundle) {
                    if (i == 0) {
                        bleConnectResponse.onResponse(i, bundle);
                    } else if (i2 > 0) {
                        BleConnectActivity.this.mHandler.postDelayed(new Runnable() {
                            public void run() {
                                BleConnectActivity.this.a(bleConnectResponse, i2 - 1);
                            }
                        }, 2000);
                    } else {
                        bleConnectResponse.onResponse(i, bundle);
                    }
                }
            });
        }
    }

    private boolean e() {
        PluginRecord d2 = CoreApi.a().d(this.C.model);
        if (d2 != null && d2.c().t() == Device.PID_BLE_MESH) {
            return true;
        }
        BleDevice d3 = BLEDeviceManager.d(this.C.mac);
        MiotBleAdvPacket miotBleAdvPacket = null;
        if (d3 != null) {
            miotBleAdvPacket = d3.d();
        }
        if (d3 == null || miotBleAdvPacket == null || miotBleAdvPacket.f14276a == null || !miotBleAdvPacket.f14276a.e) {
            return false;
        }
        return true;
    }

    private boolean f() {
        PluginRecord d2 = CoreApi.a().d(this.C.model);
        if (d2 != null && d2.c() != null && d2.c().I() == 1) {
            return true;
        }
        BleDevice d3 = BLEDeviceManager.d(this.C.mac);
        MiotBleAdvPacket miotBleAdvPacket = null;
        if (d3 != null) {
            miotBleAdvPacket = d3.d();
        }
        if (miotBleAdvPacket == null || miotBleAdvPacket.f14276a == null || miotBleAdvPacket.f14276a.k != 1) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public String a(int i2) {
        String str;
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("error code = " + i2);
            sb.append(", isScanned = " + this.I);
            int i3 = 0;
            BleDevice d2 = BLEDeviceManager.d(this.C.mac);
            if (d2 != null) {
                i3 = d2.rssi;
            }
            sb.append(", rssi = " + i3);
            sb.append(", model = " + this.C.model);
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
                                n();
                                return;
                            case -1:
                                break;
                            case 0:
                                this.D.a(80, 5000, (BoostCallback) null);
                                BluetoothReporter.b(this.C.mac);
                                return;
                            default:
                                this.D.a(0);
                                c(11);
                                return;
                        }
                }
            }
            this.D.a(0);
            c(11);
            BluetoothReporter.a(this.C.mac, bundle);
            return;
        }
        this.D.a(0);
        c(12);
        BluetoothReporter.a(this.C.mac);
    }

    /* access modifiers changed from: private */
    public void b(int i2, Bundle bundle) {
        BluetoothMyLogger.d(String.format("BleBind Step 2/3 response: code = %s", new Object[]{XmBluetoothManager.Code.toString(i2)}));
        switch (i2) {
            case -55:
            case -32:
            case -28:
            case -27:
            case -7:
            case -6:
            case -5:
            case -1:
                this.D.a(0);
                c(21);
                BluetoothReporter.b(this.C.mac, bundle);
                return;
            case -17:
                this.D.a(0);
                c(25);
                BluetoothReporter.b(this.C.mac, bundle);
                return;
            case -16:
                this.D.a(0);
                c(24);
                BluetoothReporter.b(this.C.mac, bundle);
                return;
            case -14:
                this.D.a(0);
                c(26);
                BluetoothReporter.e(this.C.mac);
                return;
            case -10:
                this.D.a(0);
                c(23);
                BluetoothReporter.c(this.C.mac, bundle);
                return;
            case -2:
                n();
                return;
            case 0:
                this.D.a(99, 10000, this.V);
                BluetoothReporter.c(this.C.mac);
                return;
            default:
                this.D.a(0);
                c(21);
                return;
        }
    }

    /* access modifiers changed from: private */
    public void c(int i2, Bundle bundle) {
        BluetoothMyLogger.d(String.format("BleBind Step 3/3 response: code = %s", new Object[]{XmBluetoothManager.Code.toString(i2)}));
        if (!(i2 == -30 || i2 == -26)) {
            if (i2 != -14) {
                switch (i2) {
                    case -2:
                        n();
                        return;
                    case -1:
                        break;
                    case 0:
                        this.D.a(101, 1000, (BoostCallback) null);
                        BluetoothReporter.f(this.C.mac);
                        return;
                    default:
                        this.D.a(0);
                        c(21);
                        return;
                }
            } else {
                this.D.a(0);
                c(26);
                BluetoothReporter.e(this.C.mac);
                return;
            }
        }
        this.D.a(0);
        c(21);
        BluetoothReporter.d(this.C.mac);
    }

    private String b(int i2) {
        return getResources().getText(i2) + " (" + this.J + Operators.BRACKET_END_STR;
    }

    private int g() {
        switch (this.M) {
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

    private int h() {
        switch (this.M) {
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
        this.B = i2;
        if (i2 != 44) {
            switch (i2) {
                case 10:
                    a(true);
                    b(false);
                    this.u.setText(R.string.ble_new_connect_step_loading);
                    this.v.setText(R.string.ble_new_connect_loading_title);
                    return;
                case 11:
                case 12:
                    a(R.string.ble_new_connect_step_failed, R.string.ble_new_connect_failed_title, true);
                    return;
                default:
                    switch (i2) {
                        case 20:
                            this.u.setText(g());
                            this.v.setText(R.string.ble_new_connect_loading_title);
                            return;
                        case 21:
                        case 22:
                            a(h(), R.string.ble_new_connect_failed_title, true);
                            return;
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                            this.u.setGravity(3);
                            a(R.string.ble_new_connect_has_bound_rich_text, 0, false);
                            return;
                        default:
                            return;
                    }
            }
        } else {
            i();
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    if (!BleConnectActivity.this.H) {
                        boolean unused = BleConnectActivity.this.H = true;
                        DevicePluginOpener.a(SHApplication.getAppContext(), BleConnectActivity.this.C, new Intent());
                    }
                }
            }, 1000);
        }
    }

    private void a(int i2, int i3, boolean z2) {
        a(false);
        this.w.setImageResource(R.drawable.kuailian_failed_icon);
        if (i2 == 0) {
            this.u.setText("");
        } else {
            this.u.setText(b(i2));
        }
        if (i3 == 0) {
            this.v.setText("");
        } else {
            this.v.setText(i3);
        }
        if (z2) {
            b(true);
        }
    }

    private void i() {
        a(false);
        this.w.setImageResource(R.drawable.kuailian_success_icon);
        this.u.setText(R.string.connecting_success);
        this.v.setText("");
        this.y.setVisibility(8);
        this.z.setVisibility(8);
    }

    private void a(boolean z2) {
        if (z2) {
            this.w.setVisibility(8);
            this.A.setVisibility(0);
            this.x.setVisibility(0);
            return;
        }
        this.w.setVisibility(0);
        this.A.setVisibility(8);
        this.x.setVisibility(8);
    }

    private void b(boolean z2) {
        if (z2) {
            this.z.setVisibility(0);
            this.y.setBackgroundResource(R.drawable.common_btn_left);
            return;
        }
        this.z.setVisibility(8);
        this.y.setBackgroundResource(R.drawable.common_button);
    }

    /* access modifiers changed from: private */
    public void j() {
        BluetoothMyLogger.d(String.format("cancelDeviceBind", new Object[0]));
        if (this.E != null) {
            try {
                this.E.cancel();
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            this.E = null;
        }
        this.D.b();
    }

    /* access modifiers changed from: private */
    public void k() {
        l();
        if (BluetoothUtils.b()) {
            BluetoothStateHelper.b(new BleResponse() {
                public void a(int i, Object obj) {
                    BluetoothStateHelper.a((BleResponse) new BleResponse() {
                        public void a(int i, Object obj) {
                            BleConnectActivity.this.m();
                            BleConnectActivity.this.c();
                        }
                    });
                }
            });
        } else {
            BluetoothStateHelper.a((BleResponse) new BleResponse() {
                public void a(int i, Object obj) {
                    BleConnectActivity.this.m();
                    BleConnectActivity.this.c();
                }
            });
        }
    }

    private void l() {
        if (this.F == null) {
            this.F = new XQProgressDialog(this);
            this.F.setMessage(getString(R.string.reopening_bluetooth));
            this.F.setCancelable(false);
        }
        if (!this.F.isShowing()) {
            this.F.show();
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        if (this.F != null && this.F.isShowing()) {
            this.F.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void n() {
        BluetoothMyLogger.d(String.format("BleBindActivity quitBindingActivity", new Object[0]));
        XmBluetoothManager.getInstance().disconnect(this.C.mac);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                BluetoothHelper.b(BleConnectActivity.this.C.mac);
            }
        }, 1000);
    }

    public void onBackPressed() {
        if (this.B == 44) {
            super.onBackPressed();
        } else if (this.y.getVisibility() == 0 || this.y.isEnabled()) {
            this.y.performClick();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.P.a();
        this.mHandler.removeCallbacksAndMessages((Object) null);
        if (this.D != null) {
            this.D.b();
        }
        if (this.F != null && this.F.isShowing()) {
            this.F.dismiss();
        }
        BluetoothUtils.a(this.R);
    }
}
