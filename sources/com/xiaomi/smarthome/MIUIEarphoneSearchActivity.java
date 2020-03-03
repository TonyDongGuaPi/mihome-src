package com.xiaomi.smarthome;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.bluetooth.XmBluetoothDevice;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleDispatcher;
import com.xiaomi.smarthome.device.bluetooth.MiioBtSearchResponse;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.location.LocationPermissionDialogHelper;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.library.common.widget.nestscroll.UIUtils;
import java.util.ArrayList;
import java.util.List;

public class MIUIEarphoneSearchActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13377a = "MIUIEarphoneLog";
    private static final int b = 18;
    private static final int c = 4097;
    private static final int d = 4098;
    private static final int e = 4099;
    private static final int f = 4100;
    private static final int g = 4101;
    private static final int h = 4102;
    private static final int i = 4103;
    private static final int j = 4104;
    private static final int k = 1;
    private static final int l = 2;
    private static final double m = 1.5d;
    private static final String n = "pref_earphone_pair_name";
    private TextView A;
    private int B;
    private int C;
    private int D;
    private double E;
    private boolean F;
    private boolean G = true;
    /* access modifiers changed from: private */
    public String o;
    /* access modifiers changed from: private */
    public String p;
    /* access modifiers changed from: private */
    public BleDevice q;
    private SharedPreferences r;
    private Intent s;
    private View t;
    private View u;
    private View v;
    private ImageView w;
    private ImageView x;
    private ImageView y;
    private TextView z;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BluetoothMyLogger.e("MIUIEarphoneLog onCreate");
        this.r = getSharedPreferences(n, 0);
        a(getIntent());
    }

    /* access modifiers changed from: private */
    public synchronized void a() {
        if (this.v == null) {
            setContentView(R.layout.miui_earphone_search_activity);
            findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MIUIEarphoneSearchActivity.this.finish();
                }
            });
            ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.miui_earphone_more_setting);
            this.t = findViewById(R.id.title_bar);
            this.u = findViewById(R.id.container);
            this.v = findViewById(R.id.miui_earphone_layout);
            this.w = (ImageView) findViewById(R.id.miui_earphone_icon);
            this.x = (ImageView) findViewById(R.id.miui_earphone_exception_icon);
            this.y = (ImageView) findViewById(R.id.search_icon);
            this.z = (TextView) findViewById(R.id.search_status);
            this.A = (TextView) findViewById(R.id.retry_button);
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        BluetoothMyLogger.e("MIUIEarphoneLog onNewIntent");
        if (intent != null) {
            a(intent);
        }
    }

    private void a(Intent intent) {
        this.s = intent;
        StartupCheckList.a((StartupCheckList.CheckListCallback) new StartupCheckList.CheckListCallback() {
            public void a() {
            }

            public void c() {
            }

            public void b() {
                MIUIEarphoneSearchActivity.this.finish();
            }

            public void d() {
                MIUIEarphoneSearchActivity.this.finish();
            }

            public void e() {
                MIUIEarphoneSearchActivity.this.a();
                MIUIEarphoneSearchActivity.this.mHandler.removeMessages(4100);
                MIUIEarphoneSearchActivity.this.mHandler.sendEmptyMessageDelayed(4100, 100);
            }
        });
        HomeKeyManager.a().a(false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0022, code lost:
        r4 = com.xiaomi.smarthome.core.server.ReflectUtils.a(r0.getClass().getName(), (java.lang.Object) r0, "getTwsPlusPeerAddress", new java.lang.Object[0]);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.lang.String r4) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L_0x0009
            java.lang.String r4 = ""
            return r4
        L_0x0009:
            r0 = 0
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 18
            if (r1 < r2) goto L_0x0020
            java.lang.String r0 = "bluetooth"
            java.lang.Object r0 = r3.getSystemService(r0)
            android.bluetooth.BluetoothManager r0 = (android.bluetooth.BluetoothManager) r0
            android.bluetooth.BluetoothAdapter r0 = r0.getAdapter()
            android.bluetooth.BluetoothDevice r0 = r0.getRemoteDevice(r4)
        L_0x0020:
            if (r0 == 0) goto L_0x003a
            java.lang.Class r4 = r0.getClass()
            java.lang.String r4 = r4.getName()
            java.lang.String r1 = "getTwsPlusPeerAddress"
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Object r4 = com.xiaomi.smarthome.core.server.ReflectUtils.a((java.lang.String) r4, (java.lang.Object) r0, (java.lang.String) r1, (java.lang.Object[]) r2)
            if (r4 == 0) goto L_0x003a
            java.lang.String r4 = r4.toString()
            return r4
        L_0x003a:
            java.lang.String r4 = ""
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.MIUIEarphoneSearchActivity.a(java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: private */
    public void b() {
        if (!TextUtils.isEmpty(this.o) && !TextUtils.isEmpty(this.p)) {
            SharedPreferences.Editor edit = this.r.edit();
            edit.putString(this.o, this.p);
            edit.apply();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        BluetoothMyLogger.e("MIUIEarphoneLog parseIntent");
        if (this.s == null) {
            BluetoothMyLogger.e("MIUIEarphoneLog input intent is null");
            finish();
            return;
        }
        String stringExtra = this.s.getStringExtra("ble_address");
        String stringExtra2 = this.s.getStringExtra("classic_address");
        int intExtra = this.s.getIntExtra("rssi", 0);
        byte[] byteArrayExtra = this.s.getByteArrayExtra("scan_record");
        BluetoothMyLogger.e("MIUIEarphoneLog bleAddress = " + stringExtra + ", classicAddress = " + BluetoothMyLogger.a(stringExtra2) + ", rssi = " + intExtra);
        if (byteArrayExtra != null && byteArrayExtra.length > 0) {
            BluetoothMyLogger.e("MIUIEarphoneLog scanRecord = " + ByteUtils.d(byteArrayExtra));
        }
        if (TextUtils.isEmpty(stringExtra) || byteArrayExtra == null || byteArrayExtra.length < 18 || !(byteArrayExtra[0] == 88 || byteArrayExtra[0] == 77)) {
            BluetoothMyLogger.c("MIUIEarphoneLog scan record format error");
            finish();
            return;
        }
        byte b2 = byteArrayExtra[3] & 1;
        this.G = (byteArrayExtra[3] & 2) == 2;
        if (b2 == 1) {
            this.o = stringExtra.toUpperCase();
            String a2 = a(this.o);
            BluetoothMyLogger.e("MIUIEarphoneLog right peer address: " + BluetoothMyLogger.a(a2));
            if (TextUtils.isEmpty(a2)) {
                String a3 = a(byteArrayExtra);
                if (TextUtils.isEmpty(a3) || TextUtils.equals(this.o, a3)) {
                    this.p = this.r.getString(this.o, "");
                } else {
                    this.p = a3.toUpperCase();
                    b();
                }
            } else {
                this.p = a2.toUpperCase();
                b();
            }
        } else {
            this.p = stringExtra.toUpperCase();
            String a4 = a(this.p);
            BluetoothMyLogger.e("MIUIEarphoneLog left peer address: " + BluetoothMyLogger.a(a4));
            if (TextUtils.isEmpty(a4)) {
                String a5 = a(byteArrayExtra);
                if (!TextUtils.isEmpty(a5) && !TextUtils.equals(this.p, a5)) {
                    this.o = a5.toUpperCase();
                    b();
                }
            } else {
                this.o = a4.toUpperCase();
                b();
            }
        }
        this.t.setVisibility(0);
        this.u.setVisibility(0);
        if (this.G) {
            this.w.setBackgroundResource(R.drawable.miui_earphone_normal_black_bg);
        } else {
            this.w.setBackgroundResource(R.drawable.miui_earphone_normal_white_bg);
        }
        this.x.setVisibility(8);
        this.z.setText(R.string.miui_earphone_scanning);
        this.A.setVisibility(8);
        this.F = true;
        j();
        d();
    }

    private void d() {
        BluetoothMyLogger.e("MIUIEarphoneLog checkSmartHomeEnvironment");
        CoreApi.a().a((Context) this, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                BluetoothMyLogger.e("MIUIEarphoneLog core ready");
                if (CoreApi.a().q()) {
                    BluetoothMyLogger.e("MIUIEarphoneLog login success");
                    if (CoreApi.a().D()) {
                        MIUIEarphoneSearchActivity.this.l();
                    } else {
                        MIUIEarphoneSearchActivity.this.e();
                    }
                } else {
                    BluetoothMyLogger.e("MIUIEarphoneLog login failed");
                    LoginApi.a().a((Context) MIUIEarphoneSearchActivity.this, 5, (LoginApi.LoginCallback) new LoginApi.LoginCallback() {
                        public void a() {
                            MIUIEarphoneSearchActivity.this.mHandler.removeMessages(4101);
                            MIUIEarphoneSearchActivity.this.mHandler.sendEmptyMessageDelayed(4101, 100);
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void e() {
        BluetoothMyLogger.e("MIUIEarphoneLog checkDeviceReady");
        SmartHomeDeviceManager.a().a((SmartHomeDeviceManager.IsDeviceReadyCallback) new SmartHomeDeviceManager.IsDeviceReadyCallback() {
            public void onDeviceReady(List<Device> list) {
                BluetoothMyLogger.e("MIUIEarphoneLog device ready");
                if (TextUtils.isEmpty(MIUIEarphoneSearchActivity.this.p)) {
                    MIUIEarphoneSearchActivity.this.f();
                } else {
                    MIUIEarphoneSearchActivity.this.g();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void f() {
        BluetoothMyLogger.e("MIUIEarphoneLog searchRightEarphoneMac");
        LocationPermissionDialogHelper.a(this, true, R.string.location_disable_hint);
        XmBluetoothManager.getInstance().stopScan();
        this.mHandler.sendEmptyMessageDelayed(4098, 4000);
        XmBluetoothManager.getInstance().startScan(3000, 1, new XmBluetoothManager.BluetoothSearchResponse() {
            public void onSearchStarted() {
                BluetoothMyLogger.e("MIUIEarphoneLog search right mac started");
            }

            public void onDeviceFounded(XmBluetoothDevice xmBluetoothDevice) {
                BLEScanRecord a2;
                if (TextUtils.isEmpty(MIUIEarphoneSearchActivity.this.p) && (a2 = BLEScanRecord.a(xmBluetoothDevice.scanRecord)) != null && a2.c() != null && a2.c().size() > 0) {
                    boolean z = false;
                    byte[] valueAt = a2.c().valueAt(0);
                    if (valueAt != null && valueAt.length >= 18 && valueAt[0] == 88 && valueAt[1] == 77 && valueAt[2] == 8) {
                        if (valueAt[3] == 1) {
                            z = true;
                        }
                        String access$600 = MIUIEarphoneSearchActivity.this.a(valueAt);
                        if (!z && MIUIEarphoneSearchActivity.this.o.equalsIgnoreCase(access$600)) {
                            BluetoothMyLogger.e("MIUIEarphoneLog right device mac found");
                            String unused = MIUIEarphoneSearchActivity.this.p = xmBluetoothDevice.getAddress().toUpperCase();
                            XmBluetoothManager.getInstance().stopScan();
                            MIUIEarphoneSearchActivity.this.b();
                            MIUIEarphoneSearchActivity.this.g();
                        }
                    }
                }
            }

            public void onSearchStopped() {
                BluetoothMyLogger.e("MIUIEarphoneLog search right mac stopped");
                MIUIEarphoneSearchActivity.this.mHandler.removeMessages(4098);
                if (TextUtils.isEmpty(MIUIEarphoneSearchActivity.this.p)) {
                    MIUIEarphoneSearchActivity.this.i();
                }
            }

            public void onSearchCanceled() {
                BluetoothMyLogger.e("MIUIEarphoneLog search right mac canceled");
                MIUIEarphoneSearchActivity.this.mHandler.removeMessages(4098);
                if (TextUtils.isEmpty(MIUIEarphoneSearchActivity.this.p)) {
                    MIUIEarphoneSearchActivity.this.i();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public String a(byte[] bArr) {
        if (bArr == null || bArr.length < 18) {
            return "";
        }
        return (String.format("%02x", new Object[]{Byte.valueOf(bArr[12])}) + ":" + String.format("%02x", new Object[]{Byte.valueOf(bArr[11])}) + ":" + String.format("%02x", new Object[]{Byte.valueOf(bArr[13])}) + ":" + String.format("%02x", new Object[]{Byte.valueOf(bArr[16])}) + ":" + String.format("%02x", new Object[]{Byte.valueOf(bArr[15])}) + ":" + String.format("%02x", new Object[]{Byte.valueOf(bArr[14])})).toUpperCase();
    }

    /* access modifiers changed from: private */
    public void g() {
        BluetoothMyLogger.e("MIUIEarphoneLog searchRightEarphoneDevice");
        Device f2 = SmartHomeDeviceManager.a().f(this.p);
        if (f2 != null) {
            a(this, f2, true);
        } else if (!BLEDeviceManager.e()) {
            BLEDeviceManager.f();
            this.mHandler.sendEmptyMessageDelayed(4097, 1000);
        } else if (!LocationPermissionDialogHelper.a(this, true, R.string.location_disable_hint)) {
            i();
        } else {
            this.mHandler.sendEmptyMessageDelayed(4099, 5000);
            BLEDeviceManager.a(new SearchRequest.Builder().a(4000, 1).a(), (MiioBtSearchResponse) new MiioBtSearchResponse() {
                public void a() {
                    BluetoothMyLogger.e("MIUIEarphoneLog search right device started");
                }

                public void a(BleDevice bleDevice) {
                    if (MIUIEarphoneSearchActivity.this.q == null && MIUIEarphoneSearchActivity.this.p.equalsIgnoreCase(bleDevice.mac)) {
                        BluetoothMyLogger.e("MIUIEarphoneLog right device found");
                        BleDevice unused = MIUIEarphoneSearchActivity.this.q = bleDevice;
                        BLEDeviceManager.f();
                        BLEDeviceManager.c();
                        BleDispatcher.b(MIUIEarphoneSearchActivity.this, BLEDeviceManager.a(bleDevice.model), (Intent) null, (ArrayList<String>) null);
                        MIUIEarphoneSearchActivity.this.finish();
                    }
                }

                public void b() {
                    BluetoothMyLogger.e("MIUIEarphoneLog search right device stopped");
                    MIUIEarphoneSearchActivity.this.mHandler.removeMessages(4099);
                    if (MIUIEarphoneSearchActivity.this.q == null) {
                        MIUIEarphoneSearchActivity.this.i();
                    }
                }

                public void c() {
                    BluetoothMyLogger.e("MIUIEarphoneLog search right device canceled");
                    MIUIEarphoneSearchActivity.this.mHandler.removeMessages(4099);
                    if (MIUIEarphoneSearchActivity.this.q == null) {
                        MIUIEarphoneSearchActivity.this.i();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public boolean h() {
        if ((Build.VERSION.SDK_INT < 17 || !isDestroyed()) && !isFinishing()) {
            return true;
        }
        return false;
    }

    private void a(final Activity activity, Device device, final boolean z2) {
        BluetoothMyLogger.e("MIUIEarphoneLog openDevicePage");
        PluginRecord d2 = CoreApi.a().d(device.model);
        if (d2 == null) {
            BluetoothMyLogger.e("MIUIEarphoneLog openDevicePage plugin record is null");
            return;
        }
        if (this.t != null) {
            this.F = false;
            this.t.setVisibility(8);
            this.u.setVisibility(8);
            this.y.setVisibility(4);
        }
        XQProgressDialog.a(activity, "", activity.getString(R.string.loading), true, true, new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                activity.finish();
            }
        }).show();
        Intent intent = new Intent();
        final XQProgressHorizontalDialog b2 = XQProgressHorizontalDialog.b(activity, activity.getString(R.string.plugin_downloading) + d2.p() + activity.getString(R.string.plugin));
        PluginApi.getInstance().sendMessage(activity, d2, 1, intent, device.newDeviceStat(), (RunningProcess) null, false, new PluginApi.SendMessageCallback() {
            public void onDownloadStart(final PluginRecord pluginRecord, final PluginDownloadTask pluginDownloadTask) {
                if (z2 && MIUIEarphoneSearchActivity.this.h() && b2 != null) {
                    b2.a(true);
                    b2.c();
                    b2.setCancelable(true);
                    b2.show();
                    b2.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialogInterface) {
                            CoreApi.a().a(pluginRecord.o(), pluginDownloadTask, (CoreApi.CancelPluginDownloadCallback) null);
                        }
                    });
                }
            }

            public void onDownloadProgress(PluginRecord pluginRecord, float f) {
                if (z2 && MIUIEarphoneSearchActivity.this.h() && b2 != null && b2.isShowing()) {
                    b2.a(100, (int) (f * 100.0f));
                }
            }

            public void onDownloadSuccess(PluginRecord pluginRecord) {
                if (z2 && MIUIEarphoneSearchActivity.this.h() && b2 != null && b2.isShowing()) {
                    b2.dismiss();
                }
            }

            public void onDownloadFailure(PluginError pluginError) {
                if (z2 && MIUIEarphoneSearchActivity.this.h() && b2 != null && b2.isShowing()) {
                    b2.dismiss();
                }
            }

            public void onDownloadCancel() {
                if (z2 && MIUIEarphoneSearchActivity.this.h() && b2 != null && b2.isShowing()) {
                    b2.dismiss();
                }
            }

            public void onSendSuccess(Bundle bundle) {
                activity.finish();
            }

            public void onSendCancel() {
                activity.finish();
            }

            public void onSendFailure(Error error) {
                activity.finish();
            }
        });
    }

    /* access modifiers changed from: private */
    public void i() {
        this.F = false;
        this.mHandler.removeMessages(4104);
        this.x.setVisibility(0);
        this.y.setVisibility(4);
        this.z.setText(R.string.miui_earphone_scan_failed);
        this.A.setVisibility(0);
        this.A.setText(R.string.miui_earphone_try_again);
        this.A.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MIUIEarphoneSearchActivity.this.c();
            }
        });
        Message message = new Message();
        message.what = 4104;
        message.arg1 = 2;
        message.arg2 = 1;
        this.mHandler.sendMessageDelayed(message, 500);
    }

    private void j() {
        if (this.F) {
            if (this.v.getWidth() == 0) {
                this.mHandler.sendEmptyMessageDelayed(4103, 100);
                return;
            }
            this.y.setVisibility(0);
            int bottom = this.v.getBottom();
            this.B = this.v.getRight() - (this.v.getWidth() / 2);
            this.C = bottom - (this.v.getHeight() / 2);
            this.D = UIUtils.a(30);
            this.E = 270.0d;
            this.mHandler.sendEmptyMessage(4102);
        }
    }

    private void k() {
        if (this.F && this.y.getVisibility() == 0 && h()) {
            if (this.E > 359.0d) {
                this.E = 0.0d;
            }
            double d2 = (double) this.B;
            double d3 = (double) this.D;
            double cos = Math.cos((this.E * 3.14d) / 180.0d);
            Double.isNaN(d3);
            Double.isNaN(d2);
            double d4 = d2 + (d3 * cos);
            double d5 = (double) this.C;
            double d6 = (double) this.D;
            double sin = Math.sin((this.E * 3.14d) / 180.0d);
            Double.isNaN(d6);
            Double.isNaN(d5);
            double d7 = d5 + (d6 * sin);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.y.getLayoutParams();
            double left = (double) this.v.getLeft();
            Double.isNaN(left);
            layoutParams.leftMargin = (int) (d4 - left);
            double top = (double) this.v.getTop();
            Double.isNaN(top);
            layoutParams.topMargin = (int) (d7 - top);
            this.y.setLayoutParams(layoutParams);
            this.y.invalidate();
            this.E += m;
            this.mHandler.sendEmptyMessageDelayed(4102, 10);
        }
    }

    /* access modifiers changed from: private */
    public void l() {
        this.F = false;
        this.y.setVisibility(4);
        this.x.setVisibility(8);
        this.z.setText(R.string.miui_earphone_unsupport_server);
        this.A.setVisibility(0);
        this.A.setText(R.string.i_know);
        this.A.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MIUIEarphoneSearchActivity.this.finish();
            }
        });
    }

    private void a(int i2, int i3) {
        if (!this.F) {
            if (i3 < 5 || i2 != 2) {
                Message message = new Message();
                message.what = 4104;
                if (i2 == 1) {
                    this.x.setVisibility(0);
                    message.arg1 = 2;
                } else {
                    this.x.setVisibility(8);
                    message.arg1 = 1;
                }
                message.arg2 = i3 + 1;
                this.mHandler.sendMessageDelayed(message, 500);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        BluetoothMyLogger.e("MIUIEarphoneLog onDestroy");
        this.F = false;
        this.mHandler.removeCallbacksAndMessages((Object) null);
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 4097:
                g();
                return;
            case 4098:
                BluetoothMyLogger.e("MIUIEarphoneLog start scan timeout");
                XmBluetoothManager.getInstance().stopScan();
                i();
                return;
            case 4099:
                BluetoothMyLogger.e("MIUIEarphoneLog search device timeout");
                BLEDeviceManager.f();
                i();
                return;
            case 4100:
                c();
                return;
            case 4101:
                if (!CoreApi.a().q()) {
                    BluetoothMyLogger.e("MIUIEarphoneLog login callback failed");
                    startActivity(new Intent(this, SmartHomeMainActivity.class));
                    finish();
                    return;
                } else if (CoreApi.a().D()) {
                    l();
                    return;
                } else {
                    e();
                    return;
                }
            case 4102:
                k();
                return;
            case 4103:
                j();
                return;
            case 4104:
                a(message.arg1, message.arg2);
                return;
            default:
                return;
        }
    }
}
