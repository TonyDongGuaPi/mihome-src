package com.xiaomi.smarthome.scene;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.widget.MinSecTimerPicker;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator;
import java.util.GregorianCalendar;

public class SmartHomeSceneTimerDelay extends BaseActivity {
    static final int DAY = 86400;
    public static final int FLAG_EVERYDAY = 127;
    public static final int FLAG_WORKDAY = 62;
    static final int HOUR = 3600;
    static final int MINUTE = 60;
    static final int SECOND = 60;
    SceneApi.Action mActionDevice;
    View mBackBtn;
    View mConfirmBtn;
    Context mContext;
    MinSecTimerPicker mTimePicker;
    TextView mTitle;

    public static String getRemainTimeHint(Context context, SceneApi.LaunchSceneTimer launchSceneTimer) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.smarthome_scene_timedelay);
        this.mContext = this;
        this.mTitle = (TextView) findViewById(R.id.module_a_3_return_title);
        this.mTitle.setText(R.string.smarthome_scene_delay);
        this.mBackBtn = findViewById(R.id.module_a_3_return_btn);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartHomeSceneTimerDelay.this.finish();
            }
        });
        this.mConfirmBtn = findViewById(R.id.module_a_3_right_text_btn);
        ((TextView) this.mConfirmBtn).setText(R.string.ok_button);
        if (!getIntent().getBooleanExtra("is_from_recommend", false)) {
            this.mActionDevice = CreateSceneManager.a().k();
        } else {
            this.mActionDevice = RecommendSceneCreator.a().h.k.get(getIntent().getIntExtra("delay_action_pos", 0));
        }
        this.mTimePicker = (MinSecTimerPicker) findViewById(R.id.smarthome_scene_time_picker);
        this.mTimePicker.setOnTimeChangedListener(new MinSecTimerPicker.OnTimeChangedListener() {
            public void a(MinSecTimerPicker minSecTimerPicker, int i, int i2) {
                if (minSecTimerPicker.getCurrentMin().intValue() == 0 && minSecTimerPicker.getCurrentSec().intValue() == 0) {
                    SmartHomeSceneTimerDelay.this.mConfirmBtn.setEnabled(false);
                } else {
                    SmartHomeSceneTimerDelay.this.mConfirmBtn.setEnabled(true);
                }
            }
        });
        if (this.mTimePicker.getCurrentMin().intValue() == 0 && this.mTimePicker.getCurrentSec().intValue() == 0) {
            this.mConfirmBtn.setEnabled(false);
        } else {
            this.mConfirmBtn.setEnabled(true);
        }
        this.mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartHomeSceneTimerDelay.this.b();
            }
        });
        a();
    }

    private void a() {
        new GregorianCalendar().getTimeZone();
        if (this.mActionDevice != null) {
            int i = this.mActionDevice.g.g;
            this.mTimePicker.setCurrentMin(Integer.valueOf(i / 60));
            this.mTimePicker.setCurrentSec(Integer.valueOf(i % 60));
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        int intValue = this.mTimePicker.getCurrentMin().intValue();
        int intValue2 = this.mTimePicker.getCurrentSec().intValue();
        if (this.mActionDevice != null) {
            this.mActionDevice.g.g = (intValue * 60) + intValue2;
            setResult(-1);
        } else {
            Intent intent = new Intent();
            intent.putExtra("value", (intValue * 60) + intValue2);
            setResult(-1, intent);
        }
        finish();
    }
}
