package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import butterknife.BindView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.device.BaikeApi;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.framework.webview.SmartHomeWebView;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.operation.js_sdk.OperationCommonWebViewActivity;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.stat.STAT;
import io.reactivex.functions.Consumer;
import java.util.Locale;

public class CameraResetStep extends SmartConfigStep {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public long f22493a;
    @BindView(2131431178)
    Button mButton;
    @BindView(2131428307)
    CheckBox mCheck;
    @BindView(2131428309)
    View mCheckBoxRootView;
    @BindView(2131428781)
    TextView mDeviceDetail;
    @BindView(2131430969)
    View mReturnBtn;
    @BindView(2131430975)
    TextView mTitle;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131430280)
    SmartHomeWebView mWebView;

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
        String str;
        String str2;
        a(context, R.layout.smart_config_camera_reset);
        this.f22493a = System.currentTimeMillis();
        STAT.c.a(0);
        TitleBarUtil.a(this.mTitleBar);
        this.mTitle.setText(R.string.camera_connect_guide);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }
        });
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        String str3 = (String) SmartConfigDataProvider.a().a("device_model");
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
            sb.append(str3);
            sb.append("&locale=");
            sb.append(LocaleUtil.a(I));
            str = sb.toString();
        } else {
            str = "https://home.mi.com/views/deviceReset.html?model=" + str3 + "&locale=" + LocaleUtil.a(I);
        }
        this.mWebView.loadUrl(str);
        this.mCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CameraResetStep.this.mButton.setEnabled(z);
                if (z) {
                    CameraResetStep.this.mButton.setTextColor(CameraResetStep.this.mButton.getResources().getColor(R.color.blue_btn_text_enable));
                } else {
                    CameraResetStep.this.mButton.setTextColor(CameraResetStep.this.mButton.getResources().getColor(R.color.blue_btn_text_disable));
                }
            }
        });
        this.mCheckBoxRootView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CameraResetStep.this.mCheck.setChecked(!CameraResetStep.this.mCheck.isChecked());
            }
        });
        this.mButton.setEnabled(false);
        this.mButton.setTextColor(this.af.getResources().getColor(R.color.blue_btn_text_disable));
        this.mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.c.a(CameraResetStep.this.f22493a);
                CameraResetStep.this.a(SmartConfigStep.Step.STEP_CHOOSE_WIFI);
            }
        });
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CameraResetStep.this.a();
            }
        });
        SmartConfigDataProvider.a().b(SmartConfigDataProvider.R, true);
        BaikeApi.a(str3).subscribe(new Consumer(context, str3) {
            private final /* synthetic */ Context f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void accept(Object obj) {
                CameraResetStep.this.a(this.f$1, this.f$2, (String) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Context context, String str, String str2) throws Exception {
        this.mDeviceDetail.setVisibility(0);
        this.mDeviceDetail.setOnClickListener(new View.OnClickListener(context, str2, str) {
            private final /* synthetic */ Context f$0;
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                CameraResetStep.a(this.f$0, this.f$1, this.f$2, view);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Context context, String str, String str2, View view) {
        OperationCommonWebViewActivity.start(context, str, (String) null);
        STAT.d.bn(str2);
    }

    public boolean a() {
        STAT.c.a(this.f22493a);
        d_(false);
        return true;
    }
}
