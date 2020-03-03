package com.xiaomi.smarthome.smartconfig.step;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.BindView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.exoplayer2.C;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.SHConfig;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.bluetooth.ui.CommonBindView;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.framework.statistic.MiStatType;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.framework.wifiaccount.WifiAccountManager;
import com.xiaomi.smarthome.homeroom.CommonUseDeviceDataManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.initdevice.InitDeviceRoomActivity;
import com.xiaomi.smarthome.homeroom.model.GridViewData;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.service.tasks.GetDeviceTask;
import com.xiaomi.smarthome.service.tasks.LoginTask;
import com.xiaomi.smarthome.smartconfig.DeviceBindStatis;
import com.xiaomi.smarthome.smartconfig.DevicePushBindManager;
import com.xiaomi.smarthome.smartconfig.PushBindEntity;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.wificonfig.WifiSettingUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import miui.os.SystemProperties;
import org.json.JSONObject;

public abstract class ConfigStep extends SmartConfigStep {

    /* renamed from: a  reason: collision with root package name */
    private static final long f22572a = 5000;
    private static final long b = 1000;
    private static final int c = 4097;
    public static final int m = 1;
    public static final int n = 2;
    public static final int o = 3;
    public static final int p = 4;
    public static final int q = 100;
    public static final int r = 200;
    public static final int s = 300;
    public static final String t = "donnot_show_again";
    private Queue<ConfigTime> d = new LinkedList();
    private Queue<ConfigPercent> e = new LinkedList();
    private RunningPercent f;
    private List<RunningPercent> g = new CopyOnWriteArrayList();
    @BindView(2131428484)
    CommonBindView mCommonBindView;
    @BindView(2131430969)
    View mReturnBtn;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131430975)
    TextView mTitlebarTv;
    public ConnectType u = ConnectType.APP;
    boolean v = true;
    protected WifiManager w;
    protected ConnectivityManager x;
    protected boolean y;

    public static class ConfigPercent {

        /* renamed from: a  reason: collision with root package name */
        int f22585a;
        int b;
    }

    public static class ConfigTime {

        /* renamed from: a  reason: collision with root package name */
        int f22586a;
        long b;
        int c = -1;
    }

    public interface DeviceFinderCallback {
        void a();

        void a(List<Device> list);

        void b(List<Device> list);
    }

    public static class RunningPercent {

        /* renamed from: a  reason: collision with root package name */
        ConfigPercent f22587a;
        int b;
        long c;
        boolean d = false;
    }

    public abstract ArrayList<ConfigTime> O_();

    public abstract int b();

    public abstract SmartConfigStep.Step b(int i);

    public void c(int i) {
    }

    public void h() {
    }

    public void i() {
    }

    public void j() {
    }

    public abstract void k();

    public enum ConnectType {
        APP("app"),
        MIUI("miui"),
        SPEAKER("speaker"),
        MINET("minet");
        
        String type;

        private ConnectType(String str) {
            this.type = str;
        }
    }

    public void a(int i) {
        LogUtil.c("ConfigStep", "onCurrentIndexSuccess index" + i);
        if (this.e.size() == 0) {
            q();
        } else if (!this.ag) {
            i();
            U_().sendEmptyMessage(111);
            U_().removeMessages(110);
        }
    }

    public void a(Message message) {
        int i;
        int i2 = message.what;
        if (i2 == 118) {
            U_().removeMessages(4097);
            this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.kuailian_init_plugin_timeout, (int) R.string.phone_wifi_error);
            q();
        } else if (i2 != 4097) {
            switch (i2) {
                case 110:
                    this.y = false;
                    SmartConfigStep.Step b2 = b(this.d.element().f22586a);
                    if (b2 != null) {
                        a_(b2);
                        return;
                    }
                    return;
                case 111:
                    if (!this.e.isEmpty()) {
                        this.e.poll();
                        this.d.poll();
                    }
                    LogUtil.c("ConfigStep", "MSG_CONFIG_STAGE_SUCCESS mConfigStepList:" + this.d.size());
                    if (this.e.size() == 0) {
                        String str = (String) SmartConfigDataProvider.a().a("device_model");
                        ScanResult l = l();
                        if (str == null) {
                            str = DeviceFactory.a(l);
                        }
                        PushBindEntity pushBindEntity = (PushBindEntity) SmartConfigDataProvider.a().a(SmartConfigDataProvider.r);
                        Device device = (Device) SmartConfigDataProvider.a().a(SmartConfigDataProvider.w);
                        if (pushBindEntity != null) {
                            DevicePushBindManager.instance.bind(pushBindEntity.d);
                        }
                        if (l != null) {
                            DevicePushBindManager.instance.bind(l.BSSID);
                        }
                        if (device != null) {
                            DevicePushBindManager.instance.bind(device.bssid);
                        }
                        if (str == null) {
                            q();
                            return;
                        } else if (((Integer) SmartConfigDataProvider.a().a(SmartConfigDataProvider.ab, -1)).intValue() == 6) {
                            q();
                            return;
                        } else {
                            this.mCommonBindView.addNextStep((int) R.string.kuailian_init_plugin, (int) R.string.keep_phone_wifi_connect);
                            c(str);
                            return;
                        }
                    } else {
                        ConfigTime peek = this.d.peek();
                        if (peek.c != -1) {
                            i = peek.c;
                        } else {
                            i = this.e.peek().f22585a;
                        }
                        long j = peek.b;
                        a(i, this.e.peek(), j);
                        if (j > 0) {
                            U_().sendEmptyMessageDelayed(110, j);
                            return;
                        }
                        return;
                    }
                default:
                    return;
            }
        } else if (this.f == null || this.f.c == 0 || this.f.f22587a == null) {
            this.mCommonBindView.setProgress(0);
        } else {
            if (this.f.d) {
                this.f.b += 2;
            } else {
                this.f.b++;
            }
            if (this.f.b > this.f.f22587a.b) {
                this.f.b = this.f.f22587a.b;
            }
            int i3 = ((this.f.b - this.f.f22587a.f22585a) * 100) / (this.f.f22587a.b - this.f.f22587a.f22585a);
            if (i3 < 0) {
                i3 = 0;
            }
            if (i3 >= 100) {
                i3 = 100;
            }
            this.mCommonBindView.setProgress(i3);
            if (this.g.size() <= 0) {
                U_().sendEmptyMessageDelayed(4097, this.f.c / ((long) (this.f.f22587a.b - this.f.f22587a.f22585a)));
            } else if (this.f.b >= this.f.f22587a.b) {
                this.f = this.g.remove(0);
                U_().sendEmptyMessage(4097);
            } else {
                this.f.c = 1000;
                this.f.d = true;
                U_().sendEmptyMessageDelayed(4097, this.f.c / ((long) (this.f.f22587a.b - this.f.f22587a.f22585a)));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void u() {
        this.y = false;
        U_().removeMessages(110);
        a_(b(this.d.element().f22586a));
    }

    /* access modifiers changed from: protected */
    public void a_(SmartConfigStep.Step step) {
        if (step != null) {
            switch (step) {
                case STEP_BLE_CONNECT_ERROR:
                    a((int) R.string.ble_new_connect_step_failed, (int) R.string.ble_new_connect_loading_title, (int) R.drawable.common_bind_app_connect_device_failed);
                    a(false, 0, (SmartConfigStep.Step) null);
                    k();
                    return;
                case STEP_CONNECT_AP_ERROR:
                    c(SmartConfigStep.Step.STEP_CONNECT_AP_ERROR);
                    k();
                    return;
                case STEP_SEND_ROUTER_INFO_ERROR:
                    a((int) R.string.kuailian_phone_sendmessage_fail, (int) R.string.kuailian_phone_connect_device_fail_desc, (int) R.drawable.common_bind_app_connect_device_failed);
                    a(false, 0, (SmartConfigStep.Step) null);
                    k();
                    return;
                case STEP_SEND_ROUTER_INFO_FINAL_ERROR:
                    a((int) R.string.kuailian_phone_sendmessage_fail, (int) R.string.kuailian_phone_connect_device_fail_desc, (int) R.drawable.common_bind_app_connect_device_failed);
                    a(true, (int) R.string.connect_fail_tips, SmartConfigStep.Step.STEP_SEND_ROUTER_INFO_FINAL_ERROR);
                    k();
                    return;
                case STEP_BLE_SEND_ERROR:
                    a((int) R.string.ble_combo_transfer_device_step_failed, (int) R.string.ble_combo_connect_title_error, (int) R.drawable.common_bind_app_connect_device_failed);
                    a(false, 0, (SmartConfigStep.Step) null);
                    k();
                    return;
                case STEP_BLE_PWD_ERROR:
                    b((int) R.string.ble_combo_device_connect_network_step_failed, (int) R.string.ble_combo_password_title_error, (int) R.drawable.common_bind_app_connect_device_failed);
                    a(false, 0, (SmartConfigStep.Step) null);
                    k();
                    return;
                case STEP_FIND_DEVICE_FAILED:
                    b((int) R.string.ble_combo_device_connect_network_step_failed, (int) R.string.ble_combo_device_title_error, (int) R.drawable.common_bind_device_connect_network_failed);
                    a(false, 0, (SmartConfigStep.Step) null);
                    return;
                case STEP_FIND_DEVICE_FAILED_ERROR:
                    b((int) R.string.ble_combo_device_connect_network_step_failed, (int) R.string.ble_combo_device_title_error, (int) R.drawable.common_bind_device_connect_network_failed);
                    a(true, (int) R.string.connect_fail_tips, SmartConfigStep.Step.STEP_FIND_DEVICE_FAILED_ERROR);
                    return;
                case STEP_BIND_BY_OTHER_ERROR:
                    a((int) R.string.ble_new_bind_step_failed, (int) R.string.ble_new_device_has_been_bind, (int) R.drawable.common_bind_device_has_binded_failed);
                    b(false, 0, (SmartConfigStep.Step) null);
                    return;
                default:
                    c(step);
                    return;
            }
        }
    }

    public void a(Context context) {
        if (this.af != null) {
            a(context, R.layout.smart_config_start_config_ui);
            TitleBarUtil.a(this.mTitleBar);
            y();
            STAT.c.d(this.aj, 0);
            this.mTitleBar.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.common_title_bar_bg));
            TitleBarUtil.a((Activity) context);
            this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ConfigStep.this.a();
                }
            });
            this.mTitlebarTv.setText(this.af.getString(R.string.kuailian_connect_device));
            this.w = (WifiManager) this.af.getApplicationContext().getSystemService("wifi");
            this.x = (ConnectivityManager) this.af.getSystemService("connectivity");
            HomeManager.a();
            o();
            p();
        }
    }

    private void o() {
        if (((Boolean) SmartConfigDataProvider.a().a(SmartConfigDataProvider.N, false)).booleanValue()) {
            this.u = ConnectType.MIUI;
        } else {
            this.u = ConnectType.APP;
        }
    }

    public void c() {
        p();
    }

    private void p() {
        int i = 0;
        this.y = false;
        this.d.clear();
        this.e.clear();
        this.g.clear();
        ArrayList<ConfigTime> O_ = O_();
        this.d.addAll(O_);
        long j = 0;
        for (ConfigTime configTime : O_) {
            j += Math.max(configTime.b, 0);
        }
        ConfigPercent configPercent = null;
        int i2 = 0;
        for (ConfigTime configTime2 : O_) {
            i2 = (int) (((long) i2) + Math.max(configTime2.b, 0));
            ConfigPercent configPercent2 = new ConfigPercent();
            if (configPercent == null) {
                configPercent2.f22585a = 0;
            } else {
                configPercent2.f22585a = configPercent.b;
            }
            configPercent2.b = (int) (((long) (i2 * 90)) / j);
            if (configPercent2.f22585a == configPercent2.b) {
                configPercent2.b++;
            }
            this.e.add(configPercent2);
            configPercent = configPercent2;
        }
        int b2 = b();
        if (b2 != -1) {
            while (!this.d.isEmpty() && this.d.peek().f22586a != b2) {
                this.d.poll();
                i = this.e.poll().b;
            }
            ConfigTime peek = this.d.peek();
            if (peek.c != -1) {
                i = peek.c;
            }
            if (b2 == 0) {
                this.mCommonBindView.resetUi();
                h();
            } else {
                this.mCommonBindView.restoreUi();
                j();
            }
            c(b2);
            long j2 = peek.b;
            a(i, this.e.peek(), j2);
            if (j2 > 0) {
                U_().sendEmptyMessageDelayed(110, j2);
            }
        }
    }

    private void a(int i, ConfigPercent configPercent, long j) {
        RunningPercent runningPercent = new RunningPercent();
        runningPercent.f22587a = configPercent;
        runningPercent.b = i;
        runningPercent.c = j;
        if (this.f == null || this.f.b >= this.f.f22587a.b) {
            this.f = runningPercent;
            U_().sendEmptyMessage(4097);
            return;
        }
        U_().removeMessages(4097);
        this.g.add(runningPercent);
        this.f.c = 1000;
        this.f.d = true;
        U_().sendEmptyMessage(4097);
    }

    public void d() {
        U_().removeMessages(110);
        U_().removeMessages(111);
        U_().removeMessages(118);
        U_().removeMessages(4097);
    }

    public void e() {
        U_().removeMessages(110);
        U_().removeMessages(111);
        U_().removeMessages(118);
        U_().removeMessages(4097);
    }

    /* access modifiers changed from: protected */
    public void c(String str) {
        LogUtil.c("ConfigStep", "startDownloadPlugin model");
        if (this.af != null) {
            PluginRecord d2 = CoreApi.a().d(str);
            final ScanResult l = l();
            if (d2 == null) {
                if (l != null) {
                    MobclickAgent.a(this.af, DeviceFactory.a(l), "ap_connect_success");
                }
                q();
                return;
            }
            this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.kuailian_init_plugin, (int) R.string.keep_phone_wifi_connect);
            this.mCommonBindView.startProgressAnimation(4);
            if (d2.k()) {
                if (l != null) {
                    MobclickAgent.a(this.af, DeviceFactory.a(l), "ap_connect_success");
                }
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.kuailian_init_plugin_success, (int) R.string.keep_phone_wifi_connect);
                q();
                return;
            }
            if (l != null) {
                MobclickAgent.a(this.af, DeviceFactory.a(l), "ap_downloadPlugin");
            }
            U_().removeMessages(4097);
            U_().sendEmptyMessageDelayed(118, 5000);
            ConfigPercent configPercent = new ConfigPercent();
            configPercent.f22585a = 80;
            configPercent.b = 100;
            a(configPercent.f22585a, configPercent, 5000);
            CoreApi.a().a(str, (CoreApi.DownloadPluginCallback) new CoreApi.DownloadPluginCallback() {
                private long c = 0;

                public void onDownInfoSuccess(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                }

                public void onProgress(PluginRecord pluginRecord, float f) {
                }

                public void onStartAlready(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                }

                public void onStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                    this.c = System.currentTimeMillis();
                }

                public void onSuccess(PluginRecord pluginRecord) {
                    if (this.c > 0 && pluginRecord != null) {
                        STAT.i.a(System.currentTimeMillis() - this.c, pluginRecord.o());
                    }
                    if (!ConfigStep.this.ag) {
                        ConfigStep.this.U_().removeMessages(4097);
                        ConfigStep.this.U_().removeMessages(118);
                        if (l != null) {
                            MobclickAgent.a(ConfigStep.this.af, DeviceFactory.a(l), "ap_connect_success");
                        }
                        ConfigStep.this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.kuailian_init_plugin_success, (int) R.string.keep_phone_wifi_connect);
                        ConfigStep.this.q();
                    }
                }

                public void onFailure(PluginError pluginError) {
                    if (!ConfigStep.this.ag) {
                        ConfigStep.this.U_().removeMessages(4097);
                        ConfigStep.this.U_().removeMessages(118);
                        MobclickAgent.a(ConfigStep.this.af, MiStatType.CLICK.getValue(), "connect_download_plugin_failure");
                        StatHelper.d("connect_download_plugin_failure");
                        ConfigStep.this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.kuailian_init_plugin_success, (int) R.string.keep_phone_wifi_connect);
                        ConfigStep.this.q();
                    }
                }

                public void onCancel() {
                    if (!ConfigStep.this.ag) {
                        ConfigStep.this.U_().removeMessages(4097);
                        ConfigStep.this.U_().removeMessages(118);
                        ConfigStep.this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.kuailian_init_plugin_success, (int) R.string.keep_phone_wifi_connect);
                        ConfigStep.this.q();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2, int i3) {
        if (this.af != null) {
            this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.FAILED, this.af.getString(i), i2, true);
            this.mCommonBindView.setBindFailed(i3);
        }
    }

    /* access modifiers changed from: protected */
    public void b(int i, int i2, int i3) {
        if (this.af != null) {
            String string = this.mCommonBindView.getResources().getString(i);
            String string2 = this.mCommonBindView.getResources().getString(R.string.kuailian_connect_fail_reason);
            SpannableString spannableString = new SpannableString(string + " " + string2);
            spannableString.setSpan(new ForegroundColorSpan(-16737793), string.length(), spannableString.length(), 17);
            spannableString.setSpan(new ClickableSpan() {
                public void updateDrawState(TextPaint textPaint) {
                    textPaint.setUnderlineText(false);
                }

                public void onClick(View view) {
                    ConfigStep.this.c(SmartConfigStep.Step.STEP_CONNECT_AP_ERROR2);
                }
            }, string.length(), spannableString.length(), 17);
            this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.FAILED, spannableString, i2, true);
            this.mCommonBindView.setBindFailed(i3);
        }
    }

    /* access modifiers changed from: protected */
    public void a(boolean z, int i, final SmartConfigStep.Step step) {
        if (this.af != null) {
            d();
            this.mCommonBindView.setConnectFailedTipsVisibility(z ? 0 : 8);
            if (z && i != 0) {
                this.mCommonBindView.setConnectFailedTipsText(this.af.getString(i));
            }
            if (z && step != null) {
                this.mCommonBindView.setConnectFailedTipsListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ConfigStep.this.c(step);
                    }
                });
            }
            this.mCommonBindView.setCommonBtnVisibility(0);
            this.mCommonBindView.setCommonBtnText(this.af.getString(R.string.retry));
            this.mCommonBindView.setCommonBtnListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ConfigStep.this.c();
                    if (step == SmartConfigStep.Step.STEP_CONNECT_AP_ERROR) {
                        STAT.d.I(ConfigStep.this.aj);
                    } else if (step == SmartConfigStep.Step.STEP_SEND_ROUTER_INFO_FINAL_ERROR) {
                        STAT.d.J(ConfigStep.this.aj);
                    } else if (step == SmartConfigStep.Step.STEP_FIND_DEVICE_FAILED_ERROR) {
                        STAT.d.K(ConfigStep.this.aj);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z, int i, final SmartConfigStep.Step step) {
        if (this.af != null) {
            d();
            this.mCommonBindView.setConnectFailedTipsVisibility(z ? 0 : 8);
            if (z && i != 0) {
                this.mCommonBindView.setConnectFailedTipsText(this.af.getString(i));
            }
            if (z && step != null) {
                this.mCommonBindView.setConnectFailedTipsListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ConfigStep.this.c(step);
                    }
                });
            }
            this.mCommonBindView.setCommonBtnVisibility(0);
            this.mCommonBindView.setCommonBtnText(this.af.getString(R.string.exit));
            this.mCommonBindView.setCommonBtnListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ConfigStep.this.d_(true);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void q() {
        STAT.c.i(this.aj);
        this.mCommonBindView.setBindSuccess();
        this.mCommonBindView.setCommonBtnVisibility(0);
        this.mCommonBindView.setCommonBtnListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.aW(ConfigStep.this.aj);
                ConfigStep.this.v();
            }
        });
        s();
        Device device = (Device) SmartConfigDataProvider.a().a(SmartConfigDataProvider.w);
        if (device != null) {
            String str = device.model;
            if ("chuangmi.camera.ipc009".equals(this.aj)) {
                str = "chuangmi.camera.ipc009";
            }
            DeviceBindStatis.b(SHApplication.getAppContext(), device.model, device.mac);
            Long l = (Long) SmartConfigDataProvider.a().a(SmartConfigDataProvider.ac);
            if (l == null || l.longValue() == 0) {
                STAT.d.a(str, -1);
            } else {
                STAT.d.a(str, System.currentTimeMillis() - l.longValue());
            }
            STAT.c.d(this.aj, this.ai);
            SmartConfigDataProvider.a().b(SmartConfigDataProvider.aa, "success");
            a(str);
            boolean booleanValue = ((Boolean) SmartConfigDataProvider.a().a(SmartConfigDataProvider.N, false)).booleanValue();
            DeviceApi.getInstance().reportNewBind(this.af, device.did, booleanValue ? "miui" : PageUrl.e, new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null && jSONObject.optInt("code", -1) == 0) {
                        Log.d("wangyh", "onSuccess: " + jSONObject.toString());
                    }
                }

                public void onFailure(Error error) {
                    Log.d("wangyh", "onFailure: " + error.toString());
                }
            });
            RemoteFamilyApi.a().a(device.did, (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.t, ""), (JSONObject) null, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    Log.d("timezone", "upload successfully!");
                }

                public void onFailure(Error error) {
                    Log.d("timezone", "upload failed! Error = " + error.toString());
                }
            });
            if (booleanValue) {
                List<String> C = HomeManager.a().C();
                ArrayList arrayList = C == null ? new ArrayList() : new ArrayList(C);
                arrayList.remove(device.did);
                arrayList.add(0, device.did);
                CommonUseDeviceDataManager.a().a((List<String>) arrayList);
                for (GridViewData next : HomeManager.a().F()) {
                    if (next.f18311a == GridViewData.GridType.TYPE_NORMAL && next.b.did.equals(device.did)) {
                        Intent intent = new Intent();
                        intent.setAction("com.xiaomi.smarthome.refresh_device");
                        intent.putExtra(LoginTask.f22078a, CoreApi.a().s());
                        intent.putExtra(GetDeviceTask.c, GetDeviceTask.a(next));
                        SHApplication.getAppContext().sendBroadcast(intent);
                        return;
                    }
                }
                return;
            }
            return;
        }
        LogUtil.c("ConfigStep", "finishStep device is null");
    }

    private void a(String str) {
        String str2;
        try {
            if (!SystemApi.c()) {
                str2 = "not_miui";
            } else {
                str2 = SystemProperties.get("ro.miui.ui.version.name", "");
            }
            if (((Boolean) SmartConfigDataProvider.a().a(SmartConfigDataProvider.N)).booleanValue()) {
                STAT.i.a(str, str2, SmartConfigMainActivity.DEVICE_FROM);
            } else if (SmartConfigMainActivity.DEVICE_FROM == 1) {
                STAT.i.a(str, str2, 1);
            } else {
                STAT.i.b(str, str2, SmartConfigMainActivity.DEVICE_FROM);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void v() {
        Device device = (Device) SmartConfigDataProvider.a().a(SmartConfigDataProvider.w);
        boolean booleanValue = ((Boolean) SmartConfigDataProvider.a().a(SmartConfigDataProvider.N, false)).booleanValue();
        LogUtilGrey.a("ConfigStep", "startChooseRoom device:" + device + " fromMiui:" + booleanValue);
        if (!booleanValue) {
            Context appContext = SHApplication.getAppContext();
            Intent intent = new Intent(appContext, InitDeviceRoomActivity.class);
            if (device != null) {
                intent.putExtra("device_id", device.did);
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                appContext.startActivity(intent);
                HomeManager.a().b(device);
            }
            d_(true);
            return;
        }
        int a2 = SHConfig.a().a(t);
        LogUtilGrey.a("ConfigStep", "startChooseRoom device:" + device + " showAgain:" + a2);
        if (a2 != 100) {
            r();
        } else {
            d_(true);
        }
    }

    private void r() {
        if (this.af != null) {
            try {
                View inflate = LayoutInflater.from(this.af).inflate(R.layout.dialog_connect_success, (ViewGroup) null);
                final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.remember);
                ((SimpleDraweeView) inflate.findViewById(R.id.tips_gif)).setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(new Uri.Builder().scheme("res").path(String.valueOf(R.drawable.miui_connect_success_dialog)).build()).setAutoPlayAnimations(true)).build());
                ((Button) inflate.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (checkBox.isChecked()) {
                            SHConfig.a().a(ConfigStep.t, 100);
                        }
                        ConfigStep.this.d_(true);
                    }
                });
                MLAlertDialog b2 = new MLAlertDialog.Builder(this.af).b(inflate).b();
                b2.setCancelable(false);
                b2.show();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(int i, int i2, Intent intent) {
        super.a(i, i2, intent);
        if (i != 100) {
            return;
        }
        if (i2 == 200) {
            this.v = true;
        } else if (i2 == 300) {
            SmartConfigDataProvider.a().b(SmartConfigDataProvider.ab, 9);
            a(SmartConfigStep.Step.STEP_QR_SCAN);
        }
    }

    private void s() {
        Device device = (Device) SmartConfigDataProvider.a().a(SmartConfigDataProvider.w);
        Home m2 = HomeManager.a().m();
        if (device != null && m2 != null) {
            HomeManager.a().a(m2, (Room) null, device, (HomeManager.IHomeOperationCallback) null);
        }
    }

    /* access modifiers changed from: protected */
    public void w() {
        Object a2 = SmartConfigDataProvider.a().a(SmartConfigDataProvider.c);
        if (a2 != null) {
            WifiAccountManager.a().b(((ScanResult) a2).BSSID);
        }
    }

    public String n() {
        Context context = this.af;
        if (context == null) {
            return "";
        }
        return a((long) ((WifiManager) context.getSystemService("wifi")).getDhcpInfo().gateway);
    }

    public boolean x() {
        WifiInfo connectionInfo = this.w != null ? this.w.getConnectionInfo() : null;
        if (connectionInfo == null) {
            return false;
        }
        String b2 = SmartConfigDataProvider.a().b();
        String ssid = connectionInfo.getSSID();
        return TextUtils.equals(b2, ssid) || TextUtils.equals(ssid, WifiSettingUtils.c(b2));
    }
}
