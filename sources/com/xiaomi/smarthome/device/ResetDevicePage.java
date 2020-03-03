package com.xiaomi.smarthome.device;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.taobao.weex.common.Constants;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.qrcode.QrCodeParser;
import com.xiaomi.qrcode.ScanBarcodeActivity;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.bluetooth.connect.single.BleBindActivityV2;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.permission.PermissionHelper;
import com.xiaomi.smarthome.framework.webview.SmartHomeWebView;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.CircleProgressBar;
import com.xiaomi.smarthome.operation.js_sdk.OperationCommonWebViewActivity;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.wificonfig.RescanWifiActivity;
import com.yanzhenjie.permission.Action;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResetDevicePage extends BaseActivity {
    public static final String EXTRA_MODEL = "model";
    public static final String EXTRA_RECORD = "plugin_record";

    /* renamed from: a  reason: collision with root package name */
    private static final String f14931a = "ResetDevicePage";
    private static final int b = 11;
    private static final int c = 12;
    private static final long d = 2000;
    private static final int e = 263;
    private static final int f = 264;
    /* access modifiers changed from: private */
    public String g;
    /* access modifiers changed from: private */
    public Button h;
    private SmartHomeWebView i;
    /* access modifiers changed from: private */
    public PluginRecord j;
    /* access modifiers changed from: private */
    public CheckBox k;
    private TextView l;
    private TextView m;
    /* access modifiers changed from: private */
    public List<Device> n;
    /* access modifiers changed from: private */
    public int o;
    /* access modifiers changed from: private */
    public Runnable p;
    private View q;
    /* access modifiers changed from: private */
    public View r;
    /* access modifiers changed from: private */
    public List<Device> s;
    /* access modifiers changed from: private */
    public CircleProgressBar t;
    /* access modifiers changed from: private */
    public TextView u;
    /* access modifiers changed from: private */
    public String v;
    /* access modifiers changed from: private */
    public boolean w;

    /* access modifiers changed from: private */
    public static /* synthetic */ String b(String str) throws JSONException {
        return str;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String c(String str) throws JSONException {
        return str;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String d(String str) throws JSONException {
        return str;
    }

    public static void showActivity(Activity activity, PluginRecord pluginRecord, int i2, Bundle bundle) {
        Intent intent = new Intent(activity, ResetDevicePage.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.putExtra("model", pluginRecord.o());
        intent.putExtra("plugin_record", pluginRecord);
        activity.startActivityForResult(intent, i2);
    }

    public void onBackPressed() {
        Device b2;
        PluginRecord d2;
        if (this.j.c().t() == Device.PID_SUBDEVICE && !((this.s != null && this.s.size() != 0) || (b2 = SmartHomeDeviceManager.a().b(this.v)) == null || (d2 = CoreApi.a().d(b2.model)) == null)) {
            a(d2, new QrCodeParser(getIntent().getStringExtra(ScanBarcodeActivity.SCAN_RESULT_NBIOT)), 0, (Callback<String>) null);
        }
        Intent intent = new Intent();
        intent.putExtra(Constants.Event.FINISH, false);
        setResult(-1, intent);
        finish();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"CheckResult"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.choose_device_failed_page);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ResetDevicePage.this.onBackPressed();
            }
        });
        this.h = (Button) findViewById(R.id.next_btn);
        this.l = (TextView) findViewById(R.id.module_a_3_right_tv_text);
        this.i = (SmartHomeWebView) findViewById(R.id.kuailian_reset_web_view);
        this.k = (CheckBox) findViewById(R.id.check_box_confirm);
        this.q = findViewById(R.id.flError);
        this.r = findViewById(R.id.flCountDown);
        this.t = (CircleProgressBar) findViewById(R.id.ccv);
        this.u = (TextView) findViewById(R.id.tvTime);
        TextView textView = (TextView) findViewById(R.id.tips);
        this.l.setText(R.string.scan_barcode);
        this.m = (TextView) findViewById(R.id.device_detail);
        Intent intent = getIntent();
        if (intent != null) {
            this.g = intent.getStringExtra("model");
            this.j = (PluginRecord) intent.getParcelableExtra("plugin_record");
            this.v = getIntent().getStringExtra("gateway_did");
            if (this.g == null && this.j != null) {
                this.g = this.j.o();
            }
        }
        this.k.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                STAT.d.x(ResetDevicePage.this.g);
                ResetDevicePage.this.h.setEnabled(z);
                if (z) {
                    STAT.d.aL(ResetDevicePage.this.g);
                }
            }
        });
        findViewById(R.id.check_box_root).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ResetDevicePage.this.k.setChecked(!ResetDevicePage.this.k.isChecked());
            }
        });
        this.i.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }
        });
        this.l.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ResetDevicePage.this.getContext(), ScanBarcodeActivity.class);
                intent.putExtra("from", 200);
                intent.putExtra("desc", ResetDevicePage.this.getString(R.string.msg_default_status3));
                intent.putExtra("pid", String.valueOf(ResetDevicePage.this.j.c().d()));
                ResetDevicePage.this.startActivityForResult(intent, 11);
            }
        });
        if (this.g != null) {
            if (CoreApi.a().c(this.g)) {
                ((TextView) findViewById(R.id.module_a_3_return_title)).setText(CoreApi.a().d(this.g).p());
            }
            if (this.j == null) {
                this.j = CoreApi.a().d(this.g);
            }
            if (this.j == null) {
                finish();
                return;
            }
            if (this.j.c().t() == Device.PID_SUBDEVICE) {
                this.k.setVisibility(8);
                a(new QrCodeParser(getIntent().getStringExtra(ScanBarcodeActivity.SCAN_RESULT_NBIOT)));
                textView.setVisibility(8);
            } else {
                this.h.setEnabled(this.k.isChecked());
            }
            try {
                b();
                a();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            final int e3 = this.j.c().e();
            if (e3 == 5) {
                textView.setText(getString(R.string.confirm_operation));
                this.h.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        STAT.d.aM(ResetDevicePage.this.g);
                        PermissionHelper.a(ResetDevicePage.this, true, new Action() {
                            public void onAction(List<String> list) {
                                STAT.d.y(ResetDevicePage.this.g);
                                Intent intent = new Intent(ResetDevicePage.this.getContext(), SmartConfigMainActivity.class);
                                intent.putExtra("strategy_id", 5);
                                intent.putExtra("model", ResetDevicePage.this.g);
                                if (ResetDevicePage.this.getIntent() != null && ResetDevicePage.this.getIntent().hasExtra(BleBindActivityV2.KEY_QRCODE_OOB)) {
                                    intent.putExtra(BleBindActivityV2.KEY_QRCODE_OOB, ResetDevicePage.this.getIntent().getStringExtra(BleBindActivityV2.KEY_QRCODE_OOB));
                                }
                                ResetDevicePage.this.startActivityForResult(intent, 100);
                            }
                        });
                    }
                });
            } else {
                if (e3 == 4) {
                    textView.setText(getString(R.string.smart_config_xiaofang_checkbox_text));
                } else {
                    textView.setText(getString(R.string.confirm_operation));
                }
                this.h.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        STAT.d.y(ResetDevicePage.this.g);
                        STAT.d.aM(ResetDevicePage.this.g);
                        if (ResetDevicePage.this.j.c().t() == Device.PID_BLE_MESH) {
                            Intent intent = new Intent(ResetDevicePage.this.getContext(), ScanChooseBluetoothDevice.class);
                            intent.putExtra("model", ResetDevicePage.this.j.o());
                            intent.putExtra("deviceName", ResetDevicePage.this.j.p());
                            ResetDevicePage.this.startActivityForResult(intent, 100);
                        } else if (e3 == 15) {
                            ResetDevicePage.this.c();
                        } else if (ResetDevicePage.this.j.c().t() == Device.PID_SUBDEVICE) {
                            ResetDevicePage.this.d();
                        } else {
                            Intent intent2 = new Intent(ResetDevicePage.this, RescanWifiActivity.class);
                            MobclickAgent.a((Context) ResetDevicePage.this, ResetDevicePage.this.j.o(), "ap_manual_connect");
                            intent2.putExtra("model", ResetDevicePage.this.j.o());
                            if (ResetDevicePage.this.getIntent() != null) {
                                intent2.putExtra(SmartConfigDataProvider.N, ResetDevicePage.this.getIntent().getBooleanExtra(SmartConfigDataProvider.N, false));
                            }
                            if (ResetDevicePage.this.getIntent() != null && ResetDevicePage.this.getIntent().hasExtra(BleBindActivityV2.KEY_QRCODE_OOB)) {
                                intent2.putExtra(BleBindActivityV2.KEY_QRCODE_OOB, ResetDevicePage.this.getIntent().getStringExtra(BleBindActivityV2.KEY_QRCODE_OOB));
                            }
                            ResetDevicePage.this.startActivityForResult(intent2, 100);
                        }
                    }
                });
            }
            BaikeApi.a(this.g).subscribe(new Consumer() {
                public final void accept(Object obj) {
                    ResetDevicePage.this.e((String) obj);
                }
            });
            STAT.c.d(this.g);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e(String str) throws Exception {
        this.m.setVisibility(0);
        this.m.setOnClickListener(new View.OnClickListener(str) {
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                ResetDevicePage.this.a(this.f$1, view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(String str, View view) {
        OperationCommonWebViewActivity.start(getContext(), str, (String) null);
        STAT.d.bn(this.g);
    }

    private void a() {
        this.l.setVisibility(8);
    }

    private void b() {
        String str;
        String str2;
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        String c2 = this.j.c().c();
        if (CoreApi.a().D()) {
            ServerBean F = CoreApi.a().F();
            StringBuilder sb = new StringBuilder();
            sb.append("https://");
            if (F != null) {
                str2 = F.f1530a + ".";
            } else {
                str2 = "";
            }
            sb.append(str2);
            sb.append("home.mi.com/views/deviceReset.html?model=");
            sb.append(c2);
            sb.append("&locale=");
            sb.append(LocaleUtil.a(I));
            str = sb.toString();
        } else {
            str = "https://home.mi.com/views/deviceReset.html?model=" + c2 + "&locale=" + LocaleUtil.a(I);
        }
        this.i.loadUrl(str);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null && intent.getStringExtra("model") != null) {
            this.k.setChecked(false);
            this.g = intent.getStringExtra("model");
            this.j = CoreApi.a().d(this.g);
            if (this.j == null) {
                finish();
                return;
            }
            ((TextView) findViewById(R.id.module_a_3_return_title)).setText(this.j.p());
            b();
            this.h.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    STAT.d.aM(ResetDevicePage.this.g);
                    int e = ResetDevicePage.this.j.c().e();
                    if (ResetDevicePage.this.j.c().t() == Device.PID_BLE_MESH) {
                        Intent intent = new Intent(ResetDevicePage.this.getContext(), ScanChooseBluetoothDevice.class);
                        intent.putExtra("model", ResetDevicePage.this.j.o());
                        intent.putExtra("deviceName", ResetDevicePage.this.j.p());
                        ResetDevicePage.this.startActivityForResult(intent, 100);
                    } else if (e == 15) {
                        ResetDevicePage.this.c();
                    } else if (ResetDevicePage.this.j.c().t() == Device.PID_SUBDEVICE) {
                        ResetDevicePage.this.d();
                    } else {
                        PermissionHelper.a(ResetDevicePage.this, true, new Action() {
                            public void onAction(List<String> list) {
                                Intent intent = new Intent(ResetDevicePage.this.getContext(), SmartConfigMainActivity.class);
                                intent.putExtra("strategy_id", 5);
                                intent.putExtra("model", ResetDevicePage.this.g);
                                if (ResetDevicePage.this.getIntent() != null && ResetDevicePage.this.getIntent().hasExtra(BleBindActivityV2.KEY_QRCODE_OOB)) {
                                    intent.putExtra(BleBindActivityV2.KEY_QRCODE_OOB, ResetDevicePage.this.getIntent().getStringExtra(BleBindActivityV2.KEY_QRCODE_OOB));
                                }
                                ResetDevicePage.this.startActivityForResult(intent, 100);
                            }
                        });
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (TextUtils.isEmpty(getIntent().getStringExtra(ScanBarcodeActivity.SCAN_RESULT_NBIOT))) {
            PermissionHelper.a(this, true, new Action() {
                public void onAction(List<String> list) {
                    Intent intent = new Intent(ResetDevicePage.this.getContext(), ScanBarcodeActivity.class);
                    intent.putExtra("from", 200);
                    intent.putExtra("desc", ResetDevicePage.this.getString(R.string.msg_default_status3));
                    intent.putExtra("pid", String.valueOf(ResetDevicePage.this.j.c().d()));
                    ResetDevicePage.this.startActivityForResult(intent, 12);
                }
            });
            return;
        }
        Intent intent = new Intent(getContext(), SmartConfigMainActivity.class);
        String stringExtra = getIntent().getStringExtra(ScanBarcodeActivity.SCAN_RESULT_NBIOT);
        intent.putExtra(SmartConfigDataProvider.D, stringExtra);
        LogUtilGrey.a(f14931a, "resultDevice SC_NBIOT qrCode:" + stringExtra);
        if (TextUtils.isEmpty(stringExtra)) {
            LogUtilGrey.a(f14931a, "qr code is null can`t show connect step");
            return;
        }
        intent.putExtra("strategy_id", 12);
        startActivityForResult(intent, 100);
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.s == null || this.s.size() == 0) {
            a(new QrCodeParser(getIntent().getStringExtra(ScanBarcodeActivity.SCAN_RESULT_NBIOT)));
        } else if (isValid()) {
            Intent intent = new Intent(getContext(), SmartConfigMainActivity.class);
            String stringExtra = getIntent().getStringExtra(ScanBarcodeActivity.SCAN_RESULT_NBIOT);
            LogUtilGrey.a(f14931a, "resultDevice SC_NBIOT qrCode:" + stringExtra + " model:" + this.g + " gateway_did:" + this.v);
            if (TextUtils.isEmpty(this.v)) {
                LogUtilGrey.a(f14931a, "gateway did is null can`t show connect step");
                return;
            }
            intent.putExtra("did", this.s.get(0).did);
            intent.putExtra(SmartConfigDataProvider.D, stringExtra);
            intent.putExtra("strategy_id", 14);
            intent.putExtra("gateway_did", this.v);
            intent.putExtra("model", this.g);
            startActivityForResult(intent, 100);
            onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i2 == 11) {
            if (i3 == -1) {
                getIntent().putExtras(intent);
                a();
                a(new QrCodeParser(getIntent().getStringExtra(ScanBarcodeActivity.SCAN_RESULT_NBIOT)));
            }
        } else if (i2 != 12) {
            boolean booleanExtra = intent != null ? intent.getBooleanExtra(Constants.Event.FINISH, true) : true;
            if (i3 == 0) {
                booleanExtra = false;
            }
            if (booleanExtra) {
                Intent intent2 = new Intent();
                intent2.putExtra(Constants.Event.FINISH, true);
                if (intent != null) {
                    intent2.putExtras(intent);
                }
                setResult(-1, intent2);
                finish();
            }
        } else if (i3 == -2) {
            getIntent().putExtras(intent);
            c();
        }
    }

    private void a(final QrCodeParser qrCodeParser) {
        Device b2 = SmartHomeDeviceManager.a().b(this.v);
        if (b2 == null) {
            LogUtilGrey.a(f14931a, "device is null did:" + this.v);
            return;
        }
        final PluginRecord d2 = CoreApi.a().d(b2.model);
        if (d2 == null) {
            LogUtilGrey.a(f14931a, "pluginrecord is null model:" + b2.model + " did:" + this.v);
            return;
        }
        final int i2 = d2.c().t(8) ? 60000 : 30000;
        this.h.setEnabled(false);
        this.q.setVisibility(8);
        this.r.setVisibility(0);
        this.t.setMaxProgress((float) i2);
        this.t.setColor(0, -13452608, -657931);
        this.u.setTextColor(-13452608);
        int a2 = DisplayUtils.a(1.0f);
        this.t.setWidth(a2, a2);
        if (this.p != null) {
            this.t.removeCallbacks(this.p);
        }
        final long currentTimeMillis = System.currentTimeMillis();
        CircleProgressBar circleProgressBar = this.t;
        AnonymousClass10 r6 = new Runnable() {

            /* renamed from: a  reason: collision with root package name */
            ForegroundColorSpan f14933a = new ForegroundColorSpan(0);
            RelativeSizeSpan b = new RelativeSizeSpan(2.5f);

            public void run() {
                if (ResetDevicePage.this.isValid()) {
                    long currentTimeMillis = (((long) i2) - System.currentTimeMillis()) + currentTimeMillis;
                    if (currentTimeMillis > 500) {
                        ResetDevicePage.this.t.setProgress((float) currentTimeMillis);
                        SpannableString spannableString = new SpannableString("s" + (currentTimeMillis / 1000) + "s");
                        int length = spannableString.length();
                        spannableString.setSpan(this.f14933a, 0, 1, 17);
                        spannableString.setSpan(this.b, 1, length - 1, 17);
                        ResetDevicePage.this.u.setText(spannableString);
                        ResetDevicePage.this.t.postDelayed(this, 100);
                        return;
                    }
                    boolean unused = ResetDevicePage.this.w = true;
                    if (ResetDevicePage.this.f().hasMessages(264)) {
                        ResetDevicePage.this.f().removeMessages(264);
                        ResetDevicePage.this.a(ResetDevicePage.this.v);
                    }
                }
            }
        };
        this.p = r6;
        circleProgressBar.post(r6);
        DeviceApi.getInstance().getSubDevice(this, new String[]{this.v}, new AsyncCallback<List<Device>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<Device> list) {
                List unused = ResetDevicePage.this.n = list;
                LogUtilGrey.a(ResetDevicePage.f14931a, "requestGatewayStart qrCodeParser:" + qrCodeParser);
                ResetDevicePage.this.a(d2, qrCodeParser, (i2 / 1000) + -1, new Callback<String>() {
                    /* renamed from: a */
                    public void onSuccess(String str) {
                        LogUtil.c(ResetDevicePage.f14931a, "requestGatewayStart scanSubdevice success" + str);
                        int unused = ResetDevicePage.this.o = 0;
                        boolean unused2 = ResetDevicePage.this.w = false;
                        ResetDevicePage.this.a(ResetDevicePage.this.v);
                    }

                    public void onFailure(int i, String str) {
                        ResetDevicePage.this.e();
                        LogUtilGrey.a(ResetDevicePage.f14931a, "requestGatewayStart scanSubdevice onFailure:" + i + " errorInfo:" + str);
                    }
                });
            }

            public void onFailure(Error error) {
                ResetDevicePage.this.e();
                LogUtilGrey.a(ResetDevicePage.f14931a, "requestGatewayStart onFailure getSubDevice:" + error);
            }
        });
    }

    /* access modifiers changed from: private */
    public void e() {
        int i2 = this.o;
        this.o = i2 + 1;
        if (i2 < 3) {
            f().removeMessages(263);
            f().sendEmptyMessageDelayed(263, 1000);
            return;
        }
        this.o = 0;
        triggerTimeoutNow();
    }

    /* access modifiers changed from: private */
    public void a(PluginRecord pluginRecord, QrCodeParser qrCodeParser, int i2, Callback<String> callback) {
        try {
            if (!pluginRecord.c().t(8)) {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(i2);
                LogUtilGrey.a(f14931a, "scanSubdevice start_zigbee_join");
                XmPluginHostApi.instance().callMethodFromCloud(this.v, "start_zigbee_join", jSONArray, callback, $$Lambda$ResetDevicePage$lYT42R8BUvDPX6dO8mhxu7dZqg8.INSTANCE);
            } else if (i2 == 0) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("code", -1);
                LogUtilGrey.a(f14931a, "scanSubdevice miIO.zb_end_provision");
                XmPluginHostApi.instance().callMethodFromCloud(this.v, "miIO.zb_end_provision", jSONObject, callback, $$Lambda$ResetDevicePage$cf2vlKxXzoBZUVFOz2CBNCSVpbY.INSTANCE);
            } else {
                JSONObject jSONObject2 = new JSONObject();
                PluginDeviceInfo c2 = this.j.c();
                jSONObject2.put("duration", i2);
                jSONObject2.put("model", this.j.o());
                jSONObject2.put("pid", c2.d());
                jSONObject2.put("reserved", "");
                if (qrCodeParser.b() == QrCodeParser.QrCodeType.ZIGBEE_30) {
                    jSONObject2.put("method", 2);
                    jSONObject2.put("mac", qrCodeParser.e());
                    jSONObject2.put("install_code", qrCodeParser.f());
                    jSONObject2.put("dev_type", 1);
                } else {
                    int i3 = 0;
                    jSONObject2.put("method", 0);
                    if (c2.e() == 14) {
                        i3 = 1;
                    }
                    jSONObject2.put("dev_type", i3);
                }
                LogUtilGrey.a(f14931a, "scanSubdevice miIO.zb_start_provision");
                XmPluginHostApi.instance().callMethodFromCloud(this.v, "miIO.zb_start_provision", jSONObject2, callback, $$Lambda$ResetDevicePage$RnlMs50lbLltbGYIV2rQfatxWIQ.INSTANCE);
            }
        } catch (Exception e2) {
            LogUtilGrey.a(f14931a, "requestGatewayStart:" + Log.getStackTraceString(e2));
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        final long currentTimeMillis = System.currentTimeMillis();
        DeviceApi.getInstance().getSubDevice(this, new String[]{str}, new AsyncCallback<List<Device>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<Device> list) {
                if (list != null) {
                    ArrayList arrayList = new ArrayList(list);
                    if (ResetDevicePage.this.n != null) {
                        for (Device device : ResetDevicePage.this.n) {
                            int size = list.size() - 1;
                            while (true) {
                                if (size < 0) {
                                    break;
                                }
                                Device device2 = list.get(size);
                                if (device != null && device2 != null && TextUtils.equals(device.did, device2.did)) {
                                    list.remove(size);
                                    break;
                                }
                                size--;
                            }
                        }
                    }
                    if (list.size() > 0) {
                        String[] strArr = new String[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            Device device3 = list.get(i);
                            if (device3 != null) {
                                strArr[i] = device3.did;
                            }
                        }
                        DeviceApi.getInstance().getDeviceDetail(SHApplication.getAppContext(), strArr, new AsyncCallback<List<Device>, Error>() {
                            /* renamed from: a */
                            public void onSuccess(List<Device> list) {
                                if (list.size() > 0) {
                                    for (int i = 0; i < list.size(); i++) {
                                        Device device = list.get(i);
                                        Device b = SmartHomeDeviceManager.a().b(device.did);
                                        if (b != null) {
                                            SmartHomeDeviceManager.a().c(b);
                                        }
                                        SmartHomeDeviceManager.a().b(device);
                                        DeviceFinder.a().c(device.did);
                                    }
                                    List unused = ResetDevicePage.this.s = list;
                                    ResetDevicePage.this.t.removeCallbacks(ResetDevicePage.this.p);
                                    ResetDevicePage.this.h.setText(R.string.next_button);
                                    ResetDevicePage.this.h.setEnabled(true);
                                    ResetDevicePage.this.r.setVisibility(8);
                                    ResetDevicePage.this.d();
                                    return;
                                }
                                ResetDevicePage.this.a(2000 - (System.currentTimeMillis() - currentTimeMillis));
                            }

                            public void onFailure(Error error) {
                                ResetDevicePage.this.a(2000 - (System.currentTimeMillis() - currentTimeMillis));
                                BluetoothLog.c(String.format("onFailure %s", new Object[]{error}));
                            }
                        });
                        return;
                    }
                    List unused = ResetDevicePage.this.n = arrayList;
                    ResetDevicePage.this.a(2000 - (System.currentTimeMillis() - currentTimeMillis));
                    return;
                }
                ResetDevicePage.this.a(2000 - (System.currentTimeMillis() - currentTimeMillis));
            }

            public void onFailure(Error error) {
                ResetDevicePage.this.a(2000 - (System.currentTimeMillis() - currentTimeMillis));
                LogUtilGrey.a(ResetDevicePage.f14931a, "checkNewDevice getSubDevice:" + error);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(long j2) {
        if (this.w) {
            triggerTimeoutNow();
            return;
        }
        f().removeMessages(264);
        f().sendEmptyMessageDelayed(264, Math.max(0, j2));
    }

    /* access modifiers changed from: protected */
    public void triggerTimeoutNow() {
        this.q.setVisibility(0);
        this.t.removeCallbacks(this.p);
        this.h.setText(R.string.common_retry);
        this.h.setEnabled(true);
        this.r.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public Handler f() {
        return this.mHandler;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 263:
                a(new QrCodeParser(getIntent().getStringExtra(ScanBarcodeActivity.SCAN_RESULT_NBIOT)));
                return;
            case 264:
                a(this.v);
                return;
            default:
                super.handleMessage(message);
                return;
        }
    }
}
