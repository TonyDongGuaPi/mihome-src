package com.xiaomi.smarthome.camera.activity.alarm;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.mijia.app.Event;
import com.mijia.model.alarm.AlarmConfigV2;
import com.mijia.model.alarm.AlarmManagerV2;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.view.widget.SettingsItemViewMultiLine;
import com.xiaomi.smarthome.scene.api.SceneApi;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmEventTypeSettingActivity extends CameraBaseActivity implements View.OnClickListener {
    /* access modifiers changed from: private */
    public AlarmConfigV2 alarmConfigV2 = new AlarmConfigV2();
    /* access modifiers changed from: private */
    public AlarmManagerV2 alarmManagerV2 = null;
    private SceneManager.IScenceListener mListener = new SceneManager.IScenceListener() {
        public void onRefreshScenceFailed(int i) {
        }

        public void onRefreshScenceSuccess(int i) {
            List<SceneApi.SmartHomeScene> d;
            if (AlarmEventTypeSettingActivity.this.isValid() && (d = SceneManager.x().d(AlarmEventTypeSettingActivity.this.mCameraDevice.getDid())) != null && d.size() > 0) {
                AlarmEventTypeSettingActivity.this.settingItemAIPush.setVisibility(0);
            }
        }
    };
    /* access modifiers changed from: private */
    public SettingsItemViewMultiLine settingItemAIPush;
    private SettingsItemViewMultiLine settingItemBabyCryPush;
    private SettingsItemViewMultiLine settingItemHumanPush;
    private SettingsItemViewMultiLine settingItemMotionPush;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        this.alarmManagerV2 = this.mCameraDevice.j();
        setContentView(R.layout.camera_activity_setting_alarm_event_type);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        findViewById(R.id.title_bar_more).setVisibility(8);
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.push_event_type);
        this.settingItemMotionPush = (SettingsItemViewMultiLine) findViewById(R.id.settting_item_area_change);
        this.settingItemMotionPush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.bp, "type", (Object) Integer.valueOf(z ? 1 : 2));
                AlarmEventTypeSettingActivity.this.setAreaPush(z);
            }
        });
        this.settingItemHumanPush = (SettingsItemViewMultiLine) findViewById(R.id.settting_item_people);
        this.settingItemHumanPush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.bq, "type", (Object) Integer.valueOf(z ? 1 : 2));
                AlarmEventTypeSettingActivity.this.putHumanPush(z);
            }
        });
        this.settingItemAIPush = (SettingsItemViewMultiLine) findViewById(R.id.settting_item_auto);
        this.settingItemAIPush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.bs, "type", (Object) Integer.valueOf(z ? 1 : 2));
                AlarmEventTypeSettingActivity.this.putAIPush(z);
            }
        });
        this.settingItemAIPush.setVisibility(8);
        this.settingItemBabyCryPush = (SettingsItemViewMultiLine) findViewById(R.id.settting_item_baby_cry);
        this.settingItemBabyCryPush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.br, "type", (Object) Integer.valueOf(z ? 1 : 2));
                AlarmEventTypeSettingActivity.this.putBabyPush(z);
            }
        });
        if (!this.mCameraDevice.e().f()) {
            this.settingItemBabyCryPush.setVisibility(8);
        }
        getAlarmConfig();
        SceneManager.x().a(this.mDid, this.mListener);
    }

    /* access modifiers changed from: private */
    public void refreshUI() {
        this.settingItemMotionPush.setChecked(this.alarmConfigV2.l);
        this.settingItemHumanPush.setChecked(this.alarmConfigV2.p);
        this.settingItemAIPush.setChecked(this.alarmConfigV2.m);
        this.settingItemBabyCryPush.setChecked(this.alarmConfigV2.n);
    }

    public void getAlarmConfig() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", this.mCameraDevice.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            this.alarmManagerV2.a(this.mCameraDevice.getModel(), jSONObject, (AlarmManagerV2.IAlarmCallback) new AlarmManagerV2.IAlarmCallback() {
                public void onSuccess(Object obj, Object obj2) {
                    if (!AlarmEventTypeSettingActivity.this.isFinishing()) {
                        AlarmEventTypeSettingActivity.this.alarmConfigV2.a(AlarmEventTypeSettingActivity.this.alarmManagerV2.a());
                        AlarmEventTypeSettingActivity.this.refreshUI();
                    }
                }

                public void onFailure(int i, String str) {
                    if (!AlarmEventTypeSettingActivity.this.isFinishing()) {
                        AlarmEventTypeSettingActivity.this.alarmConfigV2.a(AlarmEventTypeSettingActivity.this.alarmManagerV2.a());
                        AlarmEventTypeSettingActivity.this.refreshUI();
                    }
                }
            });
        } catch (JSONException unused) {
            this.alarmConfigV2.a(this.alarmManagerV2.a());
            refreshUI();
        }
    }

    /* access modifiers changed from: private */
    public void processNetResult() {
        if (!isFinishing()) {
            this.alarmConfigV2.a(this.alarmManagerV2.a());
            refreshUI();
        }
    }

    /* access modifiers changed from: private */
    public void setAreaPush(boolean z) {
        this.alarmManagerV2.a(z, (AlarmManagerV2.IAlarmCallback) new AlarmManagerV2.IAlarmCallback() {
            public void onSuccess(Object obj, Object obj2) {
                AlarmEventTypeSettingActivity.this.processNetResult();
            }

            public void onFailure(int i, String str) {
                AlarmEventTypeSettingActivity.this.processNetResult();
            }
        });
    }

    /* access modifiers changed from: private */
    public void putHumanPush(boolean z) {
        this.alarmManagerV2.b(z, (AlarmManagerV2.IAlarmCallback) new AlarmManagerV2.IAlarmCallback() {
            public void onSuccess(Object obj, Object obj2) {
                AlarmEventTypeSettingActivity.this.processNetResult();
            }

            public void onFailure(int i, String str) {
                AlarmEventTypeSettingActivity.this.processNetResult();
            }
        });
    }

    /* access modifiers changed from: private */
    public void putBabyPush(boolean z) {
        this.alarmManagerV2.d(z, new AlarmManagerV2.IAlarmCallback() {
            public void onSuccess(Object obj, Object obj2) {
                AlarmEventTypeSettingActivity.this.processNetResult();
            }

            public void onFailure(int i, String str) {
                AlarmEventTypeSettingActivity.this.processNetResult();
            }
        });
    }

    /* access modifiers changed from: private */
    public void putAIPush(boolean z) {
        this.alarmManagerV2.c(z, new AlarmManagerV2.IAlarmCallback() {
            public void onSuccess(Object obj, Object obj2) {
                AlarmEventTypeSettingActivity.this.processNetResult();
            }

            public void onFailure(int i, String str) {
                AlarmEventTypeSettingActivity.this.processNetResult();
            }
        });
    }

    public void onClick(View view) {
        if (view.getId() == R.id.title_bar_return) {
            finish();
        }
    }
}
