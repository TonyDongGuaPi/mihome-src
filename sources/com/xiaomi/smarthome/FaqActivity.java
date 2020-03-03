package com.xiaomi.smarthome;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.ViewUtils;
import com.xiaomi.smarthome.setting.ServerRouteUtil;

public class FaqActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static String f13369a = "/product/ap_reset.html";
    Context mContext;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        setContentView(R.layout.faq_activity);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FaqActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.about_faq);
        ViewUtils.a(this, getString(R.string.camera_serach_no_device_blue), (TextView) findViewById(R.id.blue_camera_reset_textview), 18, 20, (String) null);
        ViewUtils.a(this, getString(R.string.plug_serach_no_device_blue), (TextView) findViewById(R.id.blue_plug_reset_textview), 17, 19, (String) null);
        ViewUtils.a(this, getString(R.string.air_serach_no_device_green_2), (TextView) findViewById(R.id.green_airpurifier_reset_textview), 11, 13, buildUrl(f13369a));
        findViewById(R.id.camera_help_bar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View findViewById = FaqActivity.this.findViewById(R.id.camera_help_content);
                if (findViewById.getVisibility() == 0) {
                    findViewById.setVisibility(8);
                } else {
                    findViewById.setVisibility(0);
                }
            }
        });
        findViewById(R.id.plug_help_bar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View findViewById = FaqActivity.this.findViewById(R.id.plug_help_content);
                if (findViewById.getVisibility() == 0) {
                    findViewById.setVisibility(8);
                } else {
                    findViewById.setVisibility(0);
                }
            }
        });
        findViewById(R.id.airpurifier_help_bar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View findViewById = FaqActivity.this.findViewById(R.id.airpurifier_help_content);
                if (findViewById.getVisibility() == 0) {
                    findViewById.setVisibility(8);
                } else {
                    findViewById.setVisibility(0);
                }
            }
        });
    }

    public String buildUrl(String str) {
        return ServerRouteUtil.b(SHApplication.getAppContext()) + str;
    }
}
