package com.xiaomi.smarthome.smartconfig.step;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.ApDeviceManager;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.bluetooth.ui.CommonBindView;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.ApiHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.CheckStatusHandlerTask;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.ConfigStep;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.wificonfig.WifiSettingUtils;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;
import org.json.JSONException;
import org.json.JSONObject;

public class ApDeviceConfigStep extends ConfigStep {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22413a = "ApDevice";
    private static final JoinPoint.StaticPart aq = null;
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final int l = 5000;
    /* access modifiers changed from: private */
    public CheckStatusHandlerTask ap;
    /* access modifiers changed from: private */
    public ScanResult f;
    /* access modifiers changed from: private */
    public String g;
    /* access modifiers changed from: private */
    public String h;
    /* access modifiers changed from: private */
    public String i;
    /* access modifiers changed from: private */
    public int j;
    /* access modifiers changed from: private */
    public OkHttpClient k;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return ApDeviceConfigStep.a((ApDeviceConfigStep) objArr2[0], (OkHttpClient.Builder) objArr2[1], (JoinPoint) objArr2[2]);
        }
    }

    static {
        s();
    }

    private static void s() {
        Factory factory = new Factory("ApDeviceConfigStep.java", ApDeviceConfigStep.class);
        aq = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 86);
    }

    public SmartConfigStep.Step f() {
        return null;
    }

    ApDeviceConfigStep() {
        OkHttpClient.Builder writeTimeout = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS);
        JoinPoint a2 = Factory.a(aq, (Object) this, (Object) writeTimeout);
        this.k = (OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{this, writeTimeout, a2}).linkClosureAndJoinPoint(4112));
        if (this.f == null) {
            this.f = (ScanResult) SmartConfigDataProvider.a().a(SmartConfigDataProvider.h);
            if (this.f == null) {
                return;
            }
        }
        MobclickAgent.a(this.af, DeviceFactory.c(this.f), "start connection");
        long longValue = ((Long) SmartConfigDataProvider.a().a(SmartConfigDataProvider.F)).longValue();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "input_password_duration");
            jSONObject.put("duration", System.currentTimeMillis() - longValue);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        CoreApi.a().a(StatType.PLUGIN, "mihome", "Task", jSONObject.toString(), (String) null, false);
    }

    static final OkHttpClient a(ApDeviceConfigStep apDeviceConfigStep, OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }

    public ArrayList<ConfigStep.ConfigTime> O_() {
        ArrayList<ConfigStep.ConfigTime> arrayList = new ArrayList<>();
        ConfigStep.ConfigTime configTime = new ConfigStep.ConfigTime();
        configTime.f22586a = 0;
        configTime.b = 30000;
        arrayList.add(configTime);
        ConfigStep.ConfigTime configTime2 = new ConfigStep.ConfigTime();
        configTime2.f22586a = 1;
        configTime2.b = 30000;
        arrayList.add(configTime2);
        ConfigStep.ConfigTime configTime3 = new ConfigStep.ConfigTime();
        configTime3.f22586a = 2;
        configTime3.b = 25000;
        arrayList.add(configTime3);
        ConfigStep.ConfigTime configTime4 = new ConfigStep.ConfigTime();
        configTime4.f22586a = 3;
        configTime4.b = 30000;
        arrayList.add(configTime4);
        return arrayList;
    }

    public SmartConfigStep.Step b(int i2) {
        if (this.ap != null) {
            this.ap.b();
        }
        switch (i2) {
            case 0:
                MobclickAgent.a(this.af, DeviceFactory.c(this.f), "download_plugin_error");
                r();
                a(false, 0, (SmartConfigStep.Step) null);
                return null;
            case 1:
                r();
                if (TextUtils.isEmpty(this.h)) {
                    MobclickAgent.a(this.af, DeviceFactory.c(this.f), "get_did_failed");
                    a(false, 0, (SmartConfigStep.Step) null);
                    return null;
                }
                MobclickAgent.a(this.af, DeviceFactory.c(this.f), "connect_ap_failed");
                a(true, (int) R.string.switch_router_manually, SmartConfigStep.Step.STEP_CONNECT_AP_ERROR);
                return null;
            case 2:
                MobclickAgent.a(this.af, DeviceFactory.c(this.f), "confirm_error");
                r();
                a(false, 0, (SmartConfigStep.Step) null);
                return null;
            case 3:
                MobclickAgent.a(this.af, DeviceFactory.c(this.f), "connect_cloud_error");
                r();
                a(false, 0, (SmartConfigStep.Step) null);
                return null;
            default:
                return SmartConfigStep.Step.STEP_FIND_DEVICE_FAILED;
        }
    }

    public void k() {
        this.j = 0;
    }

    public int b() {
        return this.j;
    }

    public void a(Message message) {
        int i2 = message.what;
        if (i2 == 101) {
            NetworkInfo networkInfo = (NetworkInfo) message.obj;
            NetworkInfo.DetailedState detailedState = networkInfo.getDetailedState();
            WifiInfo connectionInfo = this.w.getConnectionInfo();
            if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID()) && !connectionInfo.getSSID().contains("<unknown ssid>") && detailedState == NetworkInfo.DetailedState.CONNECTED && networkInfo.isConnected() && WifiSettingUtils.a(connectionInfo.getSSID(), ((ScanResult) SmartConfigDataProvider.a().a(SmartConfigDataProvider.h)).SSID) && !this.ah) {
                if (this.j == 1) {
                    a(this.j);
                    c(2);
                }
                U_().removeMessages(123);
            }
        } else if (i2 != 126) {
            super.a(message);
        } else {
            p();
        }
    }

    public void c(int i2) {
        if (!this.ag) {
            this.j = i2;
            switch (i2) {
                case 0:
                    T_();
                    return;
                case 1:
                    m();
                    return;
                case 2:
                    if (TextUtils.isEmpty(this.h)) {
                        m();
                        return;
                    } else {
                        o();
                        return;
                    }
                case 3:
                    p();
                    return;
                default:
                    return;
            }
        }
    }

    public void h() {
        this.mCommonBindView.addNextStep((int) R.string.kuailian_init_plugin, (int) R.string.keep_phone_wifi_connect);
        this.mCommonBindView.startProgressAnimation(0);
    }

    public void i() {
        switch (this.j) {
            case 0:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.kuailian_init_plugin_success, (int) R.string.keep_phone_wifi_connect);
                this.mCommonBindView.addNextStep((int) R.string.kuailian_phone_connect_device, (int) R.string.kuailian_phone_connect_device_des);
                this.mCommonBindView.startProgressAnimation(1);
                return;
            case 1:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.kuailian_phone_connect_device_success, (int) R.string.kuailian_phone_connect_device_des);
                this.mCommonBindView.addNextStep((int) R.string.kuailian_phone_sendmessage_device, (int) R.string.kuailian_phone_connect_device_des);
                this.mCommonBindView.startProgressAnimation(2);
                return;
            case 2:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.kuailian_phone_sendmessage_success, (int) R.string.kuailian_phone_connect_device_des);
                this.mCommonBindView.addNextStep((int) R.string.kuailian_device_connect_wifi, (int) R.string.make_device_near_router);
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
        switch (this.j) {
            case 0:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.kuailian_init_plugin, (int) R.string.keep_phone_wifi_connect);
                this.mCommonBindView.startProgressAnimation(0);
                break;
            case 1:
                break;
            case 2:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.kuailian_phone_sendmessage_device, (int) R.string.kuailian_phone_connect_device_des);
                this.mCommonBindView.startProgressAnimation(2);
                return;
            case 3:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.kuailian_device_connect_wifi, (int) R.string.make_device_near_router);
                this.mCommonBindView.startProgressAnimation(3);
                return;
            default:
                return;
        }
        this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.kuailian_phone_connect_device, (int) R.string.kuailian_phone_connect_device_des);
        this.mCommonBindView.startProgressAnimation(1);
    }

    private void r() {
        switch (this.j) {
            case 0:
                a((int) R.string.kuailian_init_plugin_timeout, (int) R.string.phone_wifi_error, (int) R.drawable.common_bind_app_connect_network_failed);
                return;
            case 1:
                a((int) R.string.kuailian_phone_connect_device_fail, (int) R.string.kuailian_phone_connect_device_fail_desc, (int) R.drawable.common_bind_app_connect_device_failed);
                return;
            case 2:
                a((int) R.string.kuailian_phone_sendmessage_fail, (int) R.string.kuailian_phone_connect_device_fail_desc, (int) R.drawable.common_bind_app_connect_device_failed);
                return;
            case 3:
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

    /* access modifiers changed from: package-private */
    public void T_() {
        PluginRecord d2 = CoreApi.a().d(DeviceFactory.c(this.f));
        if (d2 == null) {
            a(this.j);
            c(1);
        } else if (d2.k()) {
            a(this.j);
            c(1);
        } else {
            CoreApi.a().a(d2.o(), (CoreApi.DownloadPluginCallback) new CoreApi.DownloadPluginCallback() {
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
                    ApDeviceConfigStep.this.a(ApDeviceConfigStep.this.j);
                    ApDeviceConfigStep.this.c(1);
                }

                public void onFailure(PluginError pluginError) {
                    ApDeviceConfigStep.this.u();
                }

                public void onCancel() {
                    ApDeviceConfigStep.this.u();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void m() {
        this.i = q();
        if (this.f == null) {
            this.f = (ScanResult) SmartConfigDataProvider.a().a(SmartConfigDataProvider.h);
            if (this.f == null) {
                return;
            }
        }
        int i2 = 100;
        if (this.w.getConnectionInfo() != null && !TextUtils.isEmpty(this.w.getConnectionInfo().getSSID()) && WifiSettingUtils.a(this.f.SSID, this.w.getConnectionInfo().getSSID())) {
            WifiSettingUtils.b(this.w, this.f.SSID);
            i2 = 3000;
        }
        this.g = SmartConfigDataProvider.a().d();
        if (TextUtils.isEmpty(this.h)) {
            U_().postDelayed(new Runnable() {
                public void run() {
                    DeviceApi.getInstance().generateDid(SHApplication.getAppContext(), ApDeviceConfigStep.this.f.BSSID, ApDeviceConfigStep.this.i, DeviceFactory.c(ApDeviceConfigStep.this.f), new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            String unused = ApDeviceConfigStep.this.h = jSONObject.optString("did");
                            if (!TextUtils.isEmpty(ApDeviceConfigStep.this.h)) {
                                WifiSettingUtils.a(ApDeviceConfigStep.this.w, ApDeviceConfigStep.this.f.SSID, ApDeviceConfigStep.this.g, ApDeviceConfigStep.this.f.BSSID, ApDeviceConfigStep.this.f.capabilities);
                            }
                        }

                        public void onFailure(Error error) {
                            MobclickAgent.a(ApDeviceConfigStep.this.af, DeviceFactory.c(ApDeviceConfigStep.this.f), "get token from server error");
                        }
                    });
                }
            }, (long) i2);
        } else {
            WifiSettingUtils.a(this.w, this.f.SSID, this.g, this.f.BSSID, this.f.capabilities);
        }
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
        return a((long) ((WifiManager) this.af.getApplicationContext().getSystemService("wifi")).getDhcpInfo().gateway);
    }

    /* access modifiers changed from: package-private */
    @SuppressLint({"StaticFieldLeak"})
    public void o() {
        this.j = 2;
        if (ApiHelper.e) {
            Network network = null;
            Network[] allNetworks = this.x.getAllNetworks();
            int i2 = 0;
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
        AsyncTaskUtils.a(new AsyncTask<Void, Void, Boolean>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Boolean doInBackground(Void... voidArr) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ApDeviceConfigStep.this.ag) {
                    return false;
                }
                try {
                    Response execute = ApDeviceConfigStep.this.k.newCall(new Request.Builder().url(ConnectionHelper.HTTP_PREFIX + ApDeviceConfigStep.this.n() + "/cgi-bin/Config.cgi?action=set&property=UserConfirm&value=" + 10).build()).execute();
                    if (execute.isSuccessful()) {
                        execute.body().string();
                        if (!execute.message().equals("OK")) {
                            MobclickAgent.a(ApDeviceConfigStep.this.af, DeviceFactory.c(ApDeviceConfigStep.this.f), "get error found in getting device did and token");
                            return false;
                        } else if (ApDeviceConfigStep.this.ag) {
                            return false;
                        } else {
                            try {
                                Response execute2 = ApDeviceConfigStep.this.k.newCall(new Request.Builder().url(ConnectionHelper.HTTP_PREFIX + ApDeviceConfigStep.this.n() + "/cgi-bin/Config.cgi?action=get&property=DidToken").build()).execute();
                                if (execute2.isSuccessful()) {
                                    String string = execute2.body().string();
                                    if (execute2.message().equals("OK")) {
                                        String[] split = string.split("\n");
                                        if (split.length > 0) {
                                            String str = split[split.length - 1];
                                            if (str.startsWith("DidToken=")) {
                                                String[] split2 = str.substring("DidToken=".length()).split("\\+");
                                                if (split2.length == 2 && !TextUtils.isEmpty(split2[0]) && !split2[0].equals("null")) {
                                                    String unused = ApDeviceConfigStep.this.h = split2[0];
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!TextUtils.isEmpty(ApDeviceConfigStep.this.h) && ApDeviceConfigStep.this.h.equals("null")) {
                                    return false;
                                }
                                if (ApDeviceConfigStep.this.ag) {
                                    return false;
                                }
                                try {
                                    Response execute3 = ApDeviceConfigStep.this.k.newCall(new Request.Builder().url(ConnectionHelper.HTTP_PREFIX + ApDeviceConfigStep.this.n() + "/cgi-bin/Config.cgi?action=set&property=DidToken&value=" + ApDeviceConfigStep.this.h + "+" + ApDeviceConfigStep.this.i).build()).execute();
                                    if (execute3.isSuccessful()) {
                                        String message = execute3.message();
                                        String string2 = execute3.body().string();
                                        if (message.equals("OK")) {
                                            if (!string2.startsWith("709")) {
                                                return true;
                                            }
                                        }
                                        MobclickAgent.a(ApDeviceConfigStep.this.af, DeviceFactory.c(ApDeviceConfigStep.this.f), "set error found in setting device did and token");
                                        return false;
                                    }
                                    MobclickAgent.a(ApDeviceConfigStep.this.af, DeviceFactory.c(ApDeviceConfigStep.this.f), "connect error found in setting device did and token");
                                    return false;
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            } catch (IOException e3) {
                                e3.printStackTrace();
                                return false;
                            }
                        }
                    } else {
                        MobclickAgent.a(ApDeviceConfigStep.this.af, DeviceFactory.c(ApDeviceConfigStep.this.f), "connect error found in getting device did and token");
                        return false;
                    }
                } catch (IOException e4) {
                    e4.printStackTrace();
                    return false;
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Boolean bool) {
                if (ApiHelper.e) {
                    ApDeviceConfigStep.this.x.bindProcessToNetwork((Network) null);
                }
                if (bool.booleanValue()) {
                    ApDeviceConfigStep.this.a(ApDeviceConfigStep.this.j);
                    int unused = ApDeviceConfigStep.this.j = 3;
                    WifiSettingUtils.b(ApDeviceConfigStep.this.w, ApDeviceConfigStep.this.f.SSID);
                    ApDeviceConfigStep.this.U_().sendEmptyMessage(126);
                }
            }
        }, new Void[0]);
    }

    /* access modifiers changed from: package-private */
    public void p() {
        if (this.ap != null) {
            this.ap.b();
        }
        this.ap = new CheckStatusHandlerTask(false);
        this.ap.a((CheckStatusHandlerTask.MyRunnable) new CheckStatusHandlerTask.MyRunnable() {
            public void a(Handler handler) {
                DeviceApi.getInstance().lapBindDevice(SHApplication.getAppContext(), ApDeviceConfigStep.this.f.BSSID, ApDeviceConfigStep.this.i, DeviceFactory.c(ApDeviceConfigStep.this.f), ApDeviceConfigStep.this.h, ApDeviceConfigStep.this.g, new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        ApDeviceManager.a().a(ApDeviceConfigStep.this.f);
                        ApDeviceConfigStep.this.ap.b();
                        ApDeviceConfigStep.this.a(ApDeviceConfigStep.this.h);
                    }

                    public void onFailure(Error error) {
                        MobclickAgent.a(ApDeviceConfigStep.this.af, DeviceFactory.c(ApDeviceConfigStep.this.f), "set did and token to server error");
                    }
                });
                ApDeviceConfigStep.this.ap.c();
            }
        }, 5000);
        this.ap.a();
    }

    /* access modifiers changed from: package-private */
    public void a(final String str) {
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
                        b2.isNew = true;
                    }
                    SmartHomeDeviceManager.a().b(b2);
                    DeviceFinder.a().c(str);
                    SmartConfigDataProvider.a().b(SmartConfigDataProvider.w, b2);
                    ApDeviceConfigStep.this.a(3);
                    MobclickAgent.a(ApDeviceConfigStep.this.af, DeviceFactory.c(ApDeviceConfigStep.this.f), "connection success");
                    long longValue = ((Long) SmartConfigDataProvider.a().a(SmartConfigDataProvider.F)).longValue();
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("name", "total_duration");
                        jSONObject.put("duration", System.currentTimeMillis() - longValue);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    CoreApi.a().a(StatType.PLUGIN, "mihome", "Task", jSONObject.toString(), (String) null, false);
                }
            }

            public void onFailure(Error error) {
                ApDeviceConfigStep.this.a(3);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public String q() {
        Random random = new Random(System.currentTimeMillis());
        String str = "";
        for (int i2 = 0; i2 < 32; i2++) {
            str = str + "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length()));
        }
        return str;
    }

    public boolean a() {
        try {
            new MLAlertDialog.Builder(this.af).a((CharSequence) this.af.getString(R.string.stop_connect_device_title)).b((CharSequence) this.af.getString(R.string.stop_connect_device_message)).a((CharSequence) this.af.getString(R.string.confirm), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    WifiManager wifiManager = (WifiManager) ApDeviceConfigStep.this.af.getApplicationContext().getSystemService("wifi");
                    WifiInfo connectionInfo = wifiManager != null ? wifiManager.getConnectionInfo() : null;
                    if (!(ApDeviceConfigStep.this.f == null || connectionInfo == null || !ApDeviceConfigStep.this.f.BSSID.equalsIgnoreCase(wifiManager.getConnectionInfo().getBSSID()))) {
                        WifiSettingUtils.b(ApDeviceConfigStep.this.w, ApDeviceConfigStep.this.f.SSID);
                    }
                    if (DeviceFinder.a().e()) {
                        DeviceFinder.a().b();
                    }
                    ApDeviceConfigStep.this.d_(false);
                }
            }).b((CharSequence) this.af.getString(R.string.cancel), (DialogInterface.OnClickListener) null).a(this.af.getResources().getColor(R.color.std_dialog_button_red), -1).d();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }
}
