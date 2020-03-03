package com.xiaomi.smarthome.smartconfig.router;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.jr.mipay.common.MipayConstants;
import com.xiaomi.smarthome.OpenExternalBrowserCompat;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.bluetooth.connect.count.BoostCallback;
import com.xiaomi.smarthome.device.bluetooth.connect.count.ProgressCounter;
import com.xiaomi.smarthome.device.bluetooth.connect.count.ProgressCounterImpl;
import com.xiaomi.smarthome.device.bluetooth.connect.count.ProgressNotifier;
import com.xiaomi.smarthome.device.bluetooth.ui.CommonBindView;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.initdevice.InitDeviceRoomActivity;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.routerconfig.RouterDeviceApi;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class RouterConfigActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22341a = "RouterConfigActivity";
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 5;
    private static final int g = 6;
    private static final int h = 30000;
    private static final int i = 15000;
    private static final int j = 1000;
    private static final int k = 4097;
    private static final int l = 60;
    private static final int m = 99;
    private static final int n = 101;
    /* access modifiers changed from: private */
    public static final Map<String, String> z = new HashMap();
    /* access modifiers changed from: private */
    public int A = 0;
    /* access modifiers changed from: private */
    public Runnable B = new Runnable() {
        public void run() {
            if (!RouterConfigActivity.this.mIsPaused) {
                RouterConfigActivity.this.f();
            }
        }
    };
    /* access modifiers changed from: private */
    public final BoostCallback C = new BoostCallback() {
        public void a() {
            RouterConfigActivity.this.l();
            RouterConfigActivity.this.n();
        }
    };
    @BindView(2131430969)
    ImageView mBackImage;
    @BindView(2131427850)
    View mBindingPage;
    @BindView(2131428341)
    TextView mChooseRouterButton;
    @BindView(2131428340)
    TextView mChooseRouterButton1;
    @BindView(2131428342)
    View mChooseRouterItem;
    @BindView(2131428343)
    View mChooseRouterPage;
    @BindView(2131428484)
    CommonBindView mCommonBindView;
    @BindView(2131429528)
    TextView mHasBoundRouterButton;
    @BindView(2131429529)
    SimpleDraweeView mHasBoundRouterImage;
    @BindView(2131429530)
    TextView mHasBoundRouterName;
    @BindView(2131429531)
    View mHasBoundRouterPage;
    @BindView(2131429860)
    EditText mInputPwdEditText;
    @BindView(2131432301)
    TextView mSetRouterButton;
    @BindView(2131432604)
    TextView mStartBindButton;
    @BindView(2131430975)
    TextView mTitleView;
    @BindView(2131433610)
    SimpleDraweeView mUninitRouterImage;
    @BindView(2131433611)
    TextView mUninitRouterName;
    @BindView(2131433612)
    View mUninitRouterPage;
    @BindView(2131433619)
    SimpleDraweeView mUnsupportRouterImage;
    @BindView(2131433620)
    View mUnsupportRouterPage;
    @BindView(2131433901)
    SimpleDraweeView mWaitBindRouterImage;
    @BindView(2131433902)
    TextView mWaitBindRouterNameView;
    @BindView(2131433903)
    View mWaitBindRouterPage;
    /* access modifiers changed from: private */
    public int o = -1;
    /* access modifiers changed from: private */
    public String p = "";
    /* access modifiers changed from: private */
    public String q = "";
    /* access modifiers changed from: private */
    public String r = "";
    /* access modifiers changed from: private */
    public String s;
    /* access modifiers changed from: private */
    public String t;
    /* access modifiers changed from: private */
    public String u;
    /* access modifiers changed from: private */
    public String v;
    private PluginRecord w;
    /* access modifiers changed from: private */
    public ProgressCounter x;
    private final ProgressNotifier y = new ProgressNotifier() {
        public void a(int i) {
            int i2;
            if (i >= 0 && i <= 100) {
                if (i <= 60) {
                    i2 = (i * 100) / 60;
                } else {
                    i2 = i <= 99 ? ((i - 60) * 100) / 39 : i;
                }
                RouterConfigActivity.this.mCommonBindView.setProgress(i2);
            }
            if (i == 100) {
                RouterConfigActivity.this.t();
            }
        }
    };

    static /* synthetic */ int access$2408(RouterConfigActivity routerConfigActivity) {
        int i2 = routerConfigActivity.A;
        routerConfigActivity.A = i2 + 1;
        return i2;
    }

    static {
        z.put("xiaomi.router.r3600", "R3600");
    }

    public static boolean isSupportRouterConfig(String str) {
        return z.containsKey(str);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_router_config);
        ButterKnife.bind((Activity) this);
        a();
        this.x = new ProgressCounterImpl(this.y);
        this.s = getIntent().getStringExtra("model");
        if (TextUtils.isEmpty(this.s)) {
            Toast.makeText(this, "model is empty", 1).show();
            finish();
            return;
        }
        this.w = CoreApi.a().d(this.s);
        if (this.w == null) {
            Toast.makeText(this, "plugin record is empty", 1).show();
            finish();
        }
    }

    private void a() {
        this.mBackImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RouterConfigActivity.this.finish();
            }
        });
        this.mChooseRouterItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RouterConfigActivity.this.b();
            }
        });
        this.mChooseRouterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RouterConfigActivity.this.b();
            }
        });
        this.mChooseRouterButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RouterConfigActivity.this.b();
            }
        });
        this.mHasBoundRouterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RouterConfigActivity.this.b();
            }
        });
        this.mSetRouterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    OpenExternalBrowserCompat.a(RouterConfigActivity.this, "http://miwifi.com");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.mInputPwdEditText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    RouterConfigActivity.this.mStartBindButton.setEnabled(true);
                    RouterConfigActivity.this.mStartBindButton.setBackgroundResource(R.drawable.router_config_button_shape);
                    return;
                }
                RouterConfigActivity.this.mStartBindButton.setEnabled(false);
                RouterConfigActivity.this.mStartBindButton.setBackgroundResource(R.drawable.router_config_button_disable_shape);
            }
        });
        this.mStartBindButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RouterConfigActivity.this.mStartBindButton.setClickable(false);
                CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
                    public void run() {
                        RouterDeviceApi.a(RouterConfigActivity.this.getApplicationContext(), RouterConfigActivity.this.p, RouterConfigActivity.this.mInputPwdEditText.getText().toString(), (Callback<String>) new Callback<String>() {
                            /* renamed from: a */
                            public void onSuccess(String str) {
                                try {
                                    String string = new JSONObject(str).getString("token");
                                    if (!TextUtils.isEmpty(string)) {
                                        RouterDeviceApi.a(RouterConfigActivity.this.p, string, new Callback<String>() {
                                            /* renamed from: a */
                                            public void onSuccess(String str) {
                                                try {
                                                    String string = new JSONObject(str).getString("info");
                                                    if (!TextUtils.isEmpty(string)) {
                                                        String unused = RouterConfigActivity.this.v = string;
                                                        RouterConfigActivity.this.mHandler.post(new Runnable() {
                                                            public void run() {
                                                                RouterConfigActivity.this.mStartBindButton.setClickable(true);
                                                                RouterConfigActivity.this.d();
                                                            }
                                                        });
                                                        return;
                                                    }
                                                    RouterConfigActivity.this.c();
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                    RouterConfigActivity.this.c();
                                                }
                                            }

                                            public void onFailure(int i, String str) {
                                                Log.d(RouterConfigActivity.f22341a, "getRouterIdentify failed, error = " + i + ", errorInfo = " + str);
                                                RouterConfigActivity.this.c();
                                            }
                                        });
                                    } else {
                                        RouterConfigActivity.this.c();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    RouterConfigActivity.this.c();
                                }
                            }

                            public void onFailure(int i, String str) {
                                Log.d(RouterConfigActivity.f22341a, "getRouterToken failed, error = " + i + ", errorInfo = " + str);
                                RouterConfigActivity.this.c();
                            }
                        });
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        Intent intent = new Intent();
        intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");
        try {
            startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        this.mHandler.post(new Runnable() {
            public void run() {
                RouterConfigActivity.this.mStartBindButton.setClickable(true);
                Toast.makeText(RouterConfigActivity.this, R.string.router_admin_password_failed, 1).show();
                RouterConfigActivity.this.mInputPwdEditText.setText("");
                RouterConfigActivity.this.d(RouterConfigActivity.this.q);
            }
        });
    }

    /* access modifiers changed from: private */
    public void d() {
        j();
        if (TextUtils.isEmpty(this.r)) {
            RouterDeviceApi.b(getApplicationContext(), this.q, this.v, new AsyncCallback<String, Error>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        String string = jSONObject.getString("code");
                        final String str2 = "miwifi." + jSONObject.getString("id");
                        if (TextUtils.equals(string, "0")) {
                            String unused = RouterConfigActivity.this.r = str2;
                            CommonApplication.getGlobalWorkerHandler().postDelayed(new Runnable() {
                                public void run() {
                                    RouterConfigActivity.this.a(str2);
                                }
                            }, 3000);
                            return;
                        }
                        RouterConfigActivity.this.m();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        RouterConfigActivity.this.m();
                    }
                }

                public void onFailure(Error error) {
                    Log.d(RouterConfigActivity.f22341a, "startBindRouter failed: " + error);
                    RouterConfigActivity.this.m();
                }
            });
        } else {
            CommonApplication.getGlobalWorkerHandler().postDelayed(new Runnable() {
                public void run() {
                    RouterConfigActivity.this.a(RouterConfigActivity.this.r);
                }
            }, 3000);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        DeviceApi.getInstance().getNewDevice(SHApplication.getAppContext(), str, true, (String) null, (String) null, (String) null, (String) null, new AsyncCallback<List<Device>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<Device> list) {
                if (list != null && list.size() > 0) {
                    String unused = RouterConfigActivity.this.t = list.get(0).did;
                    String unused2 = RouterConfigActivity.this.u = list.get(0).mac;
                    SmartHomeDeviceManager.a().b(list.get(0));
                }
                RouterConfigActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        RouterConfigActivity.this.x.a(99, 15000, (BoostCallback) null);
                        RouterConfigActivity.this.mHandler.sendEmptyMessageDelayed(4097, 15000);
                        RouterConfigActivity.this.mHandler.postDelayed(new Runnable() {
                            public void run() {
                                RouterConfigActivity.this.e();
                            }
                        }, 2000);
                    }
                });
            }

            public void onFailure(Error error) {
                Log.d(RouterConfigActivity.f22341a, "getNewDevice failed: " + error);
                RouterConfigActivity.this.m();
            }
        });
    }

    /* access modifiers changed from: private */
    public void e() {
        BluetoothMyLogger.d(String.format("RouterConfigActivity Start load plugin", new Object[0]));
        PluginRecord d2 = CoreApi.a().d(this.s);
        if (d2.k() || d2.l()) {
            this.mHandler.removeMessages(4097);
            o();
            this.x.a(101, 1000, (BoostCallback) null);
            return;
        }
        CoreApi.a().a(this.s, (CoreApi.DownloadPluginCallback) new CoreApi.DownloadPluginCallback() {
            public void onDownInfoSuccess(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
            }

            public void onProgress(PluginRecord pluginRecord, float f) {
            }

            public void onStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
            }

            public void onStartAlready(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
            }

            public void onSuccess(PluginRecord pluginRecord) {
                if (!RouterConfigActivity.this.isFinishing()) {
                    BluetoothMyLogger.f("RouterConfigActivity Plugin Download onSuccess");
                    RouterConfigActivity.this.mHandler.removeMessages(4097);
                    RouterConfigActivity.this.o();
                    RouterConfigActivity.this.x.a(101, 1000, (BoostCallback) null);
                }
            }

            public void onFailure(PluginError pluginError) {
                if (!RouterConfigActivity.this.isFinishing()) {
                    RouterConfigActivity.this.mHandler.removeMessages(4097);
                    BluetoothMyLogger.f("Plugin Download onFailure");
                    RouterConfigActivity.this.x.a(0);
                    RouterConfigActivity.this.p();
                }
            }

            public void onCancel() {
                if (!RouterConfigActivity.this.isFinishing()) {
                    RouterConfigActivity.this.mHandler.removeMessages(4097);
                    BluetoothMyLogger.f("Plugin Download onCancel");
                    RouterConfigActivity.this.x.a(0);
                    RouterConfigActivity.this.p();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.A = 0;
        if (this.w != null && this.o != 6) {
            f();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mHandler.removeCallbacks(this.B);
    }

    public void handleMessage(Message message) {
        if (message.what == 4097) {
            q();
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        if (!WifiUtil.h(this) || TextUtils.isEmpty(RouterDeviceApi.a(this))) {
            h();
        } else {
            CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
                public void run() {
                    String a2 = RouterDeviceApi.a(RouterConfigActivity.this.getApplicationContext());
                    String unused = RouterConfigActivity.this.p = a2;
                    RouterDeviceApi.a(a2, new Callback<String>() {
                        /* renamed from: a */
                        public void onSuccess(String str) {
                            Log.d(RouterConfigActivity.f22341a, "isRouterInited success");
                            try {
                                JSONObject jSONObject = new JSONObject(str);
                                int i = jSONObject.getInt("inited");
                                String string = jSONObject.getString("id");
                                String string2 = jSONObject.getString("routerId");
                                final String string3 = jSONObject.getString("routername");
                                String optString = jSONObject.optString(MipayConstants.S);
                                if (TextUtils.equals(optString, (CharSequence) RouterConfigActivity.z.get(RouterConfigActivity.this.s))) {
                                    String unused = RouterConfigActivity.this.q = string3;
                                    if (i == 1) {
                                        RouterDeviceApi.a(RouterConfigActivity.this.getApplicationContext(), string, string2, (AsyncCallback<Boolean, Error>) new AsyncCallback<Boolean, Error>() {
                                            /* renamed from: a */
                                            public void onSuccess(Boolean bool) {
                                                if (bool.booleanValue()) {
                                                    RouterConfigActivity.this.c(string3);
                                                } else {
                                                    RouterConfigActivity.this.d(string3);
                                                }
                                            }

                                            public void onFailure(Error error) {
                                                Log.d(RouterConfigActivity.f22341a, "isRouterBound failed: " + error);
                                                RouterConfigActivity.this.i();
                                                if (!RouterConfigActivity.this.mIsPaused && RouterConfigActivity.this.A < 2) {
                                                    RouterConfigActivity.access$2408(RouterConfigActivity.this);
                                                    RouterConfigActivity.this.mHandler.removeCallbacks(RouterConfigActivity.this.B);
                                                    RouterConfigActivity.this.mHandler.postDelayed(RouterConfigActivity.this.B, 1000);
                                                }
                                            }
                                        });
                                    } else {
                                        RouterConfigActivity.this.b(string3);
                                    }
                                } else {
                                    Log.d(RouterConfigActivity.f22341a, "don't support hardware: " + optString);
                                    RouterConfigActivity.this.i();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                RouterConfigActivity.this.i();
                            }
                        }

                        public void onFailure(int i, String str) {
                            Log.d(RouterConfigActivity.f22341a, "isRouterInited failed, error = " + i + ", errorInfo = " + str);
                            RouterConfigActivity.this.i();
                            if (!RouterConfigActivity.this.mIsPaused && RouterConfigActivity.this.A < 2) {
                                RouterConfigActivity.access$2408(RouterConfigActivity.this);
                                RouterConfigActivity.this.mHandler.removeCallbacks(RouterConfigActivity.this.B);
                                RouterConfigActivity.this.mHandler.postDelayed(RouterConfigActivity.this.B, 1000);
                            }
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        this.mChooseRouterPage.setVisibility(8);
        this.mUnsupportRouterPage.setVisibility(8);
        this.mUninitRouterPage.setVisibility(8);
        this.mHasBoundRouterPage.setVisibility(8);
        this.mWaitBindRouterPage.setVisibility(8);
        this.mBindingPage.setVisibility(8);
    }

    private void h() {
        this.mHandler.post(new Runnable() {
            public void run() {
                int unused = RouterConfigActivity.this.o = 1;
                RouterConfigActivity.this.g();
                RouterConfigActivity.this.mTitleView.setText(R.string.device_choose_wifi);
                RouterConfigActivity.this.mChooseRouterPage.setVisibility(0);
                RouterConfigActivity.this.mInputPwdEditText.setText("");
            }
        });
    }

    /* access modifiers changed from: private */
    public void i() {
        this.mHandler.post(new Runnable() {
            public void run() {
                int unused = RouterConfigActivity.this.o = 2;
                RouterConfigActivity.this.g();
                RouterConfigActivity.this.mTitleView.setText(R.string.choose_other_wifi_title);
                RouterConfigActivity.this.mUnsupportRouterPage.setVisibility(0);
                DeviceFactory.b(RouterConfigActivity.this.s, RouterConfigActivity.this.mUnsupportRouterImage);
                RouterConfigActivity.this.mInputPwdEditText.setText("");
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(final String str) {
        this.mHandler.post(new Runnable() {
            public void run() {
                int unused = RouterConfigActivity.this.o = 3;
                RouterConfigActivity.this.g();
                RouterConfigActivity.this.mTitleView.setText(R.string.set_router_title);
                RouterConfigActivity.this.mUninitRouterPage.setVisibility(0);
                DeviceFactory.b(RouterConfigActivity.this.s, RouterConfigActivity.this.mUninitRouterImage);
                RouterConfigActivity.this.mUninitRouterName.setText(RouterConfigActivity.this.getString(R.string.uninit_router_tips, new Object[]{str}));
                RouterConfigActivity.this.mInputPwdEditText.setText("");
            }
        });
    }

    /* access modifiers changed from: private */
    public void c(final String str) {
        this.mHandler.post(new Runnable() {
            public void run() {
                int unused = RouterConfigActivity.this.o = 4;
                RouterConfigActivity.this.g();
                RouterConfigActivity.this.mTitleView.setText(R.string.choose_other_wifi_title);
                RouterConfigActivity.this.mHasBoundRouterPage.setVisibility(0);
                DeviceFactory.b(RouterConfigActivity.this.s, RouterConfigActivity.this.mHasBoundRouterImage);
                RouterConfigActivity.this.mHasBoundRouterName.setText(RouterConfigActivity.this.getString(R.string.has_bound_router_tip, new Object[]{str}));
                RouterConfigActivity.this.mInputPwdEditText.setText("");
            }
        });
    }

    /* access modifiers changed from: private */
    public void d(final String str) {
        this.mHandler.post(new Runnable() {
            public void run() {
                int unused = RouterConfigActivity.this.o = 5;
                RouterConfigActivity.this.g();
                RouterConfigActivity.this.mTitleView.setText(R.string.choose_other_wifi_title);
                RouterConfigActivity.this.mWaitBindRouterPage.setVisibility(0);
                RouterConfigActivity.this.mStartBindButton.setClickable(true);
                DeviceFactory.b(RouterConfigActivity.this.s, RouterConfigActivity.this.mWaitBindRouterImage);
                RouterConfigActivity.this.mWaitBindRouterNameView.setText(RouterConfigActivity.this.getString(R.string.has_connected_router_tip, new Object[]{str}));
            }
        });
    }

    private void j() {
        this.mHandler.post(new Runnable() {
            public void run() {
                int unused = RouterConfigActivity.this.o = 6;
                RouterConfigActivity.this.g();
                RouterConfigActivity.this.mTitleView.setText(R.string.kuailian_connect_device);
                RouterConfigActivity.this.mBindingPage.setVisibility(0);
                RouterConfigActivity.this.x.a(0);
                RouterConfigActivity.this.mCommonBindView.resetUi();
                RouterConfigActivity.this.k();
                RouterConfigActivity.this.x.a(60, 30000, RouterConfigActivity.this.C);
            }
        });
    }

    /* access modifiers changed from: private */
    public void k() {
        this.mHandler.post(new Runnable() {
            public void run() {
                Resources resources = RouterConfigActivity.this.mCommonBindView.getResources();
                RouterConfigActivity.this.mCommonBindView.addNextStep(resources.getString(R.string.ble_new_bind_step_loading), resources.getString(R.string.ble_new_network_loading_title));
                RouterConfigActivity.this.mCommonBindView.startProgressAnimation(3);
            }
        });
    }

    /* access modifiers changed from: private */
    public void l() {
        this.mHandler.post(new Runnable() {
            public void run() {
                RouterConfigActivity.this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.ble_new_bind_step_success, (int) R.string.ble_new_connect_loading_title);
            }
        });
    }

    /* access modifiers changed from: private */
    public void m() {
        this.mHandler.post(new Runnable() {
            public void run() {
                RouterConfigActivity.this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.FAILED, RouterConfigActivity.this.getResources().getText(R.string.ble_new_bind_step_failed), R.string.ble_new_network_failed_title, true);
                RouterConfigActivity.this.mCommonBindView.setBindFailed(R.drawable.common_bind_app_connect_network_failed);
                RouterConfigActivity.this.r();
            }
        });
    }

    /* access modifiers changed from: private */
    public void n() {
        this.mHandler.post(new Runnable() {
            public void run() {
                RouterConfigActivity.this.mCommonBindView.addNextStep((int) R.string.ble_new_download_plugin_step_loading, (int) R.string.ble_new_network_loading_title);
                RouterConfigActivity.this.mCommonBindView.startProgressAnimation(4);
            }
        });
    }

    /* access modifiers changed from: private */
    public void o() {
        this.mHandler.post(new Runnable() {
            public void run() {
                RouterConfigActivity.this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.ble_new_download_plugin_step_success, (int) R.string.ble_new_add_device_success_title);
            }
        });
    }

    /* access modifiers changed from: private */
    public void p() {
        this.mHandler.post(new Runnable() {
            public void run() {
                RouterConfigActivity.this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.FAILED, RouterConfigActivity.this.getResources().getText(R.string.ble_new_download_plugin_step_failed).toString(), (int) R.string.ble_new_network_failed_title);
                RouterConfigActivity.this.s();
            }
        });
    }

    private void q() {
        this.mHandler.post(new Runnable() {
            public void run() {
                RouterConfigActivity.this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, RouterConfigActivity.this.getResources().getText(R.string.ble_new_download_plugin_step_timeout).toString(), (int) R.string.ble_new_network_failed_title);
                RouterConfigActivity.this.s();
            }
        });
    }

    /* access modifiers changed from: private */
    public void r() {
        this.mCommonBindView.setCommonBtnVisibility(0);
        this.mCommonBindView.setCommonBtnText(getString(R.string.retry));
        this.mCommonBindView.setCommonBtnListener(new View.OnClickListener() {
            public void onClick(View view) {
                RouterConfigActivity.this.d();
            }
        });
    }

    /* access modifiers changed from: private */
    public void s() {
        this.mCommonBindView.setCommonBtnVisibility(0);
        this.mCommonBindView.setCommonBtnListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(RouterConfigActivity.this, InitDeviceRoomActivity.class);
                intent.putExtra("device_id", RouterConfigActivity.this.t);
                intent.putExtra("device_mac", RouterConfigActivity.this.u);
                RouterConfigActivity.this.startActivity(intent);
                RouterConfigActivity.this.finish();
            }
        });
        this.mCommonBindView.setBindSuccess();
    }

    /* access modifiers changed from: private */
    public void t() {
        s();
    }
}
