package com.xiaomi.smarthome.camera.activity.alarm;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.mijia.model.alarm.AlarmConfigV2;
import com.mijia.model.alarm.AlarmManagerV2;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.view.widget.SettingsItemView;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmAISwitchActivity extends CameraBaseActivity implements View.OnClickListener {
    AlarmConfigV2 alarmConfigV2 = new AlarmConfigV2();
    AlarmManagerV2 alarmManagerV2 = null;
    private SettingsItemView settingItemBabyCry;
    private SettingsItemView settingItemFace;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.camera_activity_alarm_ai_switch);
        this.alarmManagerV2 = this.mCameraDevice.j();
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.settingItemFace = (SettingsItemView) findViewById(R.id.settting_item_face);
        this.settingItemBabyCry = (SettingsItemView) findViewById(R.id.settting_item_baby_cry);
        this.settingItemFace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AlarmAISwitchActivity.this.alarmManagerV2.e(z, new AlarmManagerV2.IAlarmCallback() {
                    public void onSuccess(Object obj, Object obj2) {
                        AlarmAISwitchActivity.this.alarmConfigV2.a(AlarmAISwitchActivity.this.alarmManagerV2.a());
                        AlarmAISwitchActivity.this.refreshUI();
                    }

                    public void onFailure(int i, String str) {
                        AlarmAISwitchActivity.this.alarmConfigV2.a(AlarmAISwitchActivity.this.alarmManagerV2.a());
                        AlarmAISwitchActivity.this.refreshUI();
                    }
                });
            }
        });
        this.settingItemBabyCry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AlarmAISwitchActivity.this.alarmManagerV2.f(z, new AlarmManagerV2.IAlarmCallback() {
                    public void onSuccess(Object obj, Object obj2) {
                        AlarmAISwitchActivity.this.alarmConfigV2.a(AlarmAISwitchActivity.this.alarmManagerV2.a());
                        AlarmAISwitchActivity.this.refreshUI();
                    }

                    public void onFailure(int i, String str) {
                        AlarmAISwitchActivity.this.alarmConfigV2.a(AlarmAISwitchActivity.this.alarmManagerV2.a());
                        AlarmAISwitchActivity.this.refreshUI();
                    }
                });
            }
        });
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.face_ai_setting);
        getAlarmConfig();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.title_bar_return) {
            finish();
        }
    }

    /* access modifiers changed from: private */
    public void refreshUI() {
        this.settingItemFace.setChecked(this.alarmConfigV2.k);
        this.settingItemBabyCry.setChecked(this.alarmConfigV2.j);
    }

    public void getAlarmConfig() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", this.mCameraDevice.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            this.alarmManagerV2.a(this.mCameraDevice.getModel(), jSONObject, (AlarmManagerV2.IAlarmCallback) new AlarmManagerV2.IAlarmCallback() {
                public void onSuccess(Object obj, Object obj2) {
                    if (!AlarmAISwitchActivity.this.isFinishing()) {
                        AlarmAISwitchActivity.this.alarmConfigV2.a(AlarmAISwitchActivity.this.alarmManagerV2.a());
                        AlarmAISwitchActivity.this.refreshUI();
                    }
                }

                public void onFailure(int i, String str) {
                    if (!AlarmAISwitchActivity.this.isFinishing()) {
                        AlarmAISwitchActivity.this.alarmConfigV2.a(AlarmAISwitchActivity.this.alarmManagerV2.a());
                        AlarmAISwitchActivity.this.refreshUI();
                    }
                }
            });
        } catch (JSONException unused) {
            this.alarmConfigV2.a(this.alarmManagerV2.a());
            refreshUI();
        }
    }
}
