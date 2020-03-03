package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.LocalRouterDeviceInfo;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.RouterRemoteApi;
import com.xiaomi.smarthome.library.common.widget.PieProgressBar;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import java.util.Iterator;

public class RouterInfoStep extends SmartConfigStep {

    /* renamed from: a  reason: collision with root package name */
    private long f22669a;
    @BindView(2131427828)
    TextView mBarText;
    @BindView(2131432516)
    SimpleDraweeView mDeviceIcon;
    @BindView(2131431178)
    Button mNextBtn;
    @BindView(2131427827)
    PieProgressBar mProgressBar;
    @BindView(2131427826)
    View mProgressContainer;
    @BindView(2131432518)
    TextView mRouterInfo;
    @BindView(2131432517)
    TextView mRouterInfoSubTitle;
    @BindView(2131427829)
    TextView mRouterInfoTitle;
    @BindView(2131427825)
    View mRouterInfoView;
    @BindView(2131432064)
    TextView mSwitchRouterBtn;

    public void c() {
    }

    public void d() {
    }

    public SmartConfigStep.Step f() {
        return SmartConfigStep.Step.STEP_GET_ROUTER_INFO;
    }

    public void a(Message message) {
        switch (message.what) {
            case 116:
                b();
                return;
            case 117:
                float currentTimeMillis = (((float) (System.currentTimeMillis() - this.f22669a)) * 100.0f) / 30000.0f;
                if (currentTimeMillis <= 101.0f) {
                    this.mProgressBar.setPercent(currentTimeMillis);
                    U_().sendEmptyMessageDelayed(117, 100);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void a(Context context) {
        a(context, R.layout.smart_config_get_router_info_ui);
        this.mProgressContainer.setVisibility(0);
        this.mRouterInfoView.setVisibility(8);
        this.mProgressBar.setPercentView(this.mBarText);
        this.mProgressBar.setPercent(0.0f);
        this.mProgressBar.setDuration(30);
        this.f22669a = System.currentTimeMillis();
        this.mNextBtn.setVisibility(8);
        this.mRouterInfoTitle.setText(R.string.router_test_testing);
        U_().sendEmptyMessageDelayed(116, 30000);
        U_().sendEmptyMessageDelayed(117, 100);
        U_().post(new Runnable() {
            public void run() {
                LocalRouterDeviceInfo.a().a((AsyncResponseCallback<RouterRemoteApi.WifiDetail>) new AsyncResponseCallback<RouterRemoteApi.WifiDetail>() {
                    public void a(int i, Object obj) {
                    }

                    public void a(RouterRemoteApi.WifiDetail wifiDetail) {
                        RouterRemoteApi.WifiInfo wifiInfo;
                        String str = (String) SmartConfigDataProvider.a().a("device_model");
                        boolean z = str != null && DeviceFactory.a(str);
                        Iterator<RouterRemoteApi.WifiInfo> it = wifiDetail.f16425a.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                wifiInfo = null;
                                break;
                            }
                            wifiInfo = it.next();
                            if ((z && wifiInfo.f16426a > 20 && wifiInfo.b && !TextUtils.isEmpty(wifiInfo.c)) || (wifiInfo.f16426a > 0 && wifiInfo.f16426a < 20 && wifiInfo.b && !TextUtils.isEmpty(wifiInfo.c))) {
                                break;
                            }
                        }
                        if (RouterInfoStep.this.U_() != null) {
                            RouterInfoStep.this.U_().removeMessages(116);
                            RouterInfoStep.this.U_().removeMessages(117);
                        }
                        if (!RouterInfoStep.this.ag) {
                            if (wifiInfo == null) {
                                RouterInfoStep.this.b();
                                return;
                            }
                            RouterInfoStep.this.mProgressContainer.setVisibility(8);
                            RouterInfoStep.this.mRouterInfoView.setVisibility(0);
                            RouterInfoStep.this.a(wifiInfo);
                        }
                    }

                    public void a(int i) {
                        if (!RouterInfoStep.this.ag) {
                            RouterInfoStep.this.b();
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(final RouterRemoteApi.WifiInfo wifiInfo) {
        String str;
        this.mRouterInfoView.setVisibility(0);
        this.mProgressContainer.setVisibility(8);
        this.mNextBtn.setVisibility(0);
        String str2 = (String) SmartConfigDataProvider.a().a("device_model");
        if (str2 != null) {
            String j = DeviceFactory.j(str2);
            DeviceFactory.a(j, this.mDeviceIcon, (int) R.drawable.device_list_phone_no);
            Device k = DeviceFactory.k(j);
            if (k != null) {
                str = k.name;
            } else {
                str = SHApplication.getAppContext().getString(R.string.other_device);
            }
            this.mRouterInfo.setText(String.format(this.af.getString(R.string.router_test_info), new Object[]{str}));
            this.mRouterInfoSubTitle.setText(String.format(this.af.getString(R.string.router_test_sub_info), new Object[]{wifiInfo.c}));
            SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(this.af.getString(R.string.router_test_switch));
            valueOf.setSpan(new ClickableSpan() {
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(RouterInfoStep.this.af.getResources().getColor(R.color.class_text_27));
                    textPaint.setUnderlineText(true);
                }

                public void onClick(View view) {
                    RouterInfoStep.this.mRouterInfo = null;
                    RouterInfoStep.this.a(SmartConfigStep.Step.STEP_CHOOSE_WIFI);
                }
            }, 0, valueOf.length(), 33);
            this.mSwitchRouterBtn.setHighlightColor(0);
            this.mSwitchRouterBtn.setText(valueOf);
            this.mSwitchRouterBtn.setMovementMethod(LinkMovementMethod.getInstance());
            this.mNextBtn.setText(R.string.next_button);
            this.mNextBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SmartConfigDataProvider.a().b(SmartConfigDataProvider.b, wifiInfo);
                    SmartConfigDataProvider.a().b(SmartConfigDataProvider.p);
                    RouterInfoStep.this.D();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        a(SmartConfigStep.Step.STEP_CHOOSE_WIFI);
    }

    public void e() {
        if (U_() != null) {
            U_().removeMessages(116);
            U_().removeMessages(117);
        }
    }
}
