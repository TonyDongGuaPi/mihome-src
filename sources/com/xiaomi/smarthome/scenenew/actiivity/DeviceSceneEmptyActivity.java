package com.xiaomi.smarthome.scenenew.actiivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.framework.page.BaseActivity;

public class DeviceSceneEmptyActivity extends BaseActivity {
    public static final String DEVICE_ID = "device_id";

    /* renamed from: a  reason: collision with root package name */
    private String f21805a;
    protected Device mDevice;
    @BindView(2131430969)
    ImageView mReturnIV;
    @BindView(2131430975)
    TextView mTitleTV;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_scene_layout);
        ButterKnife.bind((Activity) this);
        this.mTitleTV.setText(R.string.scene_plugin_title);
        this.mReturnIV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceSceneEmptyActivity.this.finish();
            }
        });
    }
}
