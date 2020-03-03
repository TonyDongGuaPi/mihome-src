package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.ResetDevicePage;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.statistic.MiStatType;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.stat.STAT;

public class FindDeviceFailedFinalStep extends SmartConfigStep {
    @BindView(2131428052)
    View mButton1;
    @BindView(2131428054)
    View mButton2;
    @BindView(2131428056)
    View mButton3;
    @BindView(2131428053)
    TextView mButtonName_1;
    @BindView(2131428057)
    TextView mButtonName_3;
    @BindView(2131428792)
    SimpleDraweeView mDeviceImage;
    @BindView(2131430969)
    View mReturnBtn;
    @BindView(2131432900)
    TextView mTips;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131430975)
    TextView mTitlebarTv;

    public void a(Message message) {
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public SmartConfigStep.Step f() {
        return null;
    }

    public void a(Context context) {
        PluginRecord d;
        a(context, R.layout.smart_config_passwd_failed_ui);
        TitleBarUtil.a(this.mTitleBar);
        ScanResult scanResult = (ScanResult) SmartConfigDataProvider.a().a(SmartConfigDataProvider.h);
        boolean z = true;
        if (scanResult == null || (d = CoreApi.a().d(DeviceFactory.a(scanResult))) == null || d.c().C() != 1) {
            z = false;
        }
        this.mTitlebarTv.setText(this.af.getString(R.string.kuailian_connect_device));
        this.mTips.setText(this.af.getString(R.string.please_check_device_light));
        final String str = (String) SmartConfigDataProvider.a().a("device_model");
        if (str != null) {
            DeviceFactory.a(this.mDeviceImage, DeviceFactory.n(str), 0);
        }
        STAT.d.H(str);
        if (z) {
            this.mButtonName_1.setText(R.string.smart_config_bright);
            this.mButtonName_3.setText(R.string.smart_config_blink_fast);
        } else {
            this.mButtonName_1.setText(R.string.smart_config_blue_light_bright);
            this.mButtonName_3.setText(R.string.smart_config_blue_light_blinking);
        }
        this.mButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ScanResult scanResult = (ScanResult) SmartConfigDataProvider.a().a(SmartConfigDataProvider.h);
                if (scanResult != null) {
                    MobclickAgent.a(FindDeviceFailedFinalStep.this.af, DeviceFactory.a(scanResult), "ap_connect_server_failure_choose1");
                }
                MobclickAgent.a(FindDeviceFailedFinalStep.this.af, MiStatType.CLICK.getValue(), "connect_get_device_failure");
                StatHelper.d("connect_get_device_failure");
                STAT.d.B(str);
                FindDeviceFailedFinalStep.this.D();
            }
        });
        this.mButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context appContext = FindDeviceFailedFinalStep.this.af == null ? SHApplication.getAppContext() : FindDeviceFailedFinalStep.this.af;
                MobclickAgent.a(appContext, MiStatType.CLICK.getValue(), "connect_device_connect_failure");
                StatHelper.d("connect_device_connect_failure");
                STAT.d.A(str);
                Intent intent = new Intent(appContext, ResetDevicePage.class);
                ScanResult scanResult = (ScanResult) SmartConfigDataProvider.a().a(SmartConfigDataProvider.h);
                if (scanResult != null) {
                    MobclickAgent.a(appContext, DeviceFactory.a(scanResult), "ap_connect_server_failure_choose2");
                }
                intent.putExtra("model", DeviceFactory.a(scanResult));
                appContext.startActivity(intent);
                FindDeviceFailedFinalStep.this.d_(true);
            }
        });
        this.mButton2.setVisibility(8);
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FindDeviceFailedFinalStep.this.a();
            }
        });
        this.mTitlebarTv.setText(this.af.getString(R.string.kuailian_connect_device));
        StatHelper.A();
    }

    public boolean a() {
        d_(false);
        return true;
    }
}
