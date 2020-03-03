package com.xiaomi.smarthome.voice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.authorization.page.DeviceAuthMasterListActivity;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.mibrain.activity.MiBrainActivityNew;
import com.xiaomi.smarthome.mibrain.anothernamesetting.AnotherNameDevListActivity;
import com.xiaomi.smarthome.mibrain.roomsetting.XiaoAiListActivity;
import com.xiaomi.voiceassistant.mijava.NLProcessor;

public class VoiceSettingActivity extends BaseActivity {
    public static String FROM_SETTINGMAIN = "FROM_SETTINGMAIN";

    /* renamed from: a  reason: collision with root package name */
    private boolean f22803a = false;
    @BindView(2131429805)
    ImageView mAnoterNameDot;
    @BindView(2131433886)
    View mAnotherNameSetting;
    @BindView(2131428766)
    View mDeviceAuth;
    @BindView(2131429811)
    ImageView mDeviceAuthDot;
    @BindView(2131429825)
    ImageView mXiaoaiRoomDot;
    @BindView(2131434039)
    View mXiaoaiRoomSetting;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_voice_setting);
        if (getIntent() != null && getIntent().hasExtra(FROM_SETTINGMAIN)) {
            this.f22803a = getIntent().getBooleanExtra(FROM_SETTINGMAIN, false);
        }
        ButterKnife.bind((Activity) this);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                VoiceSettingActivity.this.a();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.audio_setting);
        if (CoreApi.a().D()) {
            this.mAnotherNameSetting.setVisibility(8);
        } else {
            this.mAnotherNameSetting.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    VoiceSettingActivity.this.c(view);
                }
            });
        }
        if (CoreApi.a().D()) {
            this.mXiaoaiRoomSetting.setVisibility(8);
        } else {
            this.mXiaoaiRoomSetting.setVisibility(0);
            this.mXiaoaiRoomSetting.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    VoiceSettingActivity.this.b(view);
                }
            });
        }
        ServerBean F = CoreApi.a().F();
        if (!CoreApi.a().q() || (CoreApi.a().D() && !ServerCompact.f(F))) {
            this.mDeviceAuth.setVisibility(8);
            return;
        }
        this.mDeviceAuth.setVisibility(0);
        this.mDeviceAuth.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                VoiceSettingActivity.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        Intent intent = new Intent(this, AnotherNameDevListActivity.class);
        intent.putExtra("show_guide", this.mAnoterNameDot.getVisibility() == 0);
        startActivity(intent);
        PreferenceUtils.c(getApplicationContext(), "voice_another_name_setting", true);
        this.mAnoterNameDot.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        Intent intent = new Intent(this, XiaoAiListActivity.class);
        intent.putExtra("show_guide", !PreferenceUtils.a("lab_xiaoai_red_dot_shown", false));
        startActivity(intent);
        PreferenceUtils.c(getApplicationContext(), "lab_xiaoai_red_dot_shown", true);
        this.mXiaoaiRoomDot.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        startActivity(new Intent(this, DeviceAuthMasterListActivity.class));
    }

    public void onBackPressed() {
        super.onBackPressed();
        a();
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.f22803a) {
            finish();
            return;
        }
        NLProcessor.b = "";
        startActivity(new Intent(this, MiBrainActivityNew.class));
        overridePendingTransition(0, 0);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }
}
