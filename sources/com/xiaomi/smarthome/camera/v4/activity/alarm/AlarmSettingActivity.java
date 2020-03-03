package com.xiaomi.smarthome.camera.v4.activity.alarm;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.mijia.app.Event;
import com.mijia.debug.SDKLog;
import com.mijia.model.alarm.AlarmConfig;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.view.widget.SettingsItemView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import java.lang.reflect.Array;
import org.json.JSONObject;

public class AlarmSettingActivity extends CameraBaseActivity implements View.OnClickListener {
    private View mAlarOpenLayout;
    AlarmConfig mAlarmConfig = new AlarmConfig();
    SettingsItemView mAlarmItem;
    SettingsItemView mAlarmLevel;
    SettingsItemView mAlarmOnlyPeoplePush;
    SettingsItemView mAlarmPush;
    SettingsItemView mAlarmPushTime;
    SettingsItemView mAlarmTime;
    private boolean mJump = false;
    /* access modifiers changed from: private */
    public int mLastLevel = 0;
    /* access modifiers changed from: private */
    public int mLevel = -1;
    private String mMinute = "";
    /* access modifiers changed from: private */
    public int mPeoplePush = -1;
    private String mServer;
    private XQProgressDialog mXQProgressDialog;

    /* access modifiers changed from: package-private */
    public void showProgressDialog() {
        dismissProgressDialog();
        this.mXQProgressDialog = new XQProgressDialog(this);
        this.mXQProgressDialog.setMessage(getString(R.string.smb_waiting));
        this.mXQProgressDialog.setCancelable(true);
        this.mXQProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                if (AlarmSettingActivity.this.mCameraDevice.i().d() == null) {
                    AlarmSettingActivity.this.finish();
                }
            }
        });
        this.mXQProgressDialog.show();
    }

    /* access modifiers changed from: package-private */
    public void dismissProgressDialog() {
        if (this.mXQProgressDialog != null) {
            this.mXQProgressDialog.dismiss();
            this.mXQProgressDialog = null;
        }
    }

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.activity_setting_alarm);
        this.mServer = XmPluginHostApi.instance().getGlobalSettingServer();
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.more_alarm_setting_v4);
        this.mMinute = getString(R.string.tip_time_minute);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.mJump = getIntent().getBooleanExtra("jump", false);
        this.mAlarmItem = (SettingsItemView) findViewById(R.id.settings_alarm);
        this.mAlarmItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AlarmSettingActivity.this.mAlarmConfig.f7965a = z ? 1 : 0;
                AlarmSettingActivity.this.mAlarmConfig.f = z;
                int unused = AlarmSettingActivity.this.mPeoplePush = z ^ true ? 1 : 0;
                AlarmSettingActivity.this.refreshUI();
            }
        });
        this.mAlarmPushTime = (SettingsItemView) findViewById(R.id.settings_alarm_push_time);
        this.mAlarmPushTime.setOnClickListener(this);
        this.mAlarmPush = (SettingsItemView) findViewById(R.id.settings_alarm_push);
        this.mAlarmPush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AlarmSettingActivity.this.mAlarmConfig.f = z ? 1 : 0;
                AlarmSettingActivity.this.refreshUI();
            }
        });
        this.mAlarmOnlyPeoplePush = (SettingsItemView) findViewById(R.id.settings_only_people_push);
        this.mCameraDevice.e(new Callback<Boolean>() {
            public void onFailure(int i, String str) {
            }

            public void onSuccess(Boolean bool) {
                AlarmSettingActivity.this.mAlarmOnlyPeoplePush.setChecked(bool.booleanValue());
            }
        });
        this.mAlarmOnlyPeoplePush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AlarmSettingActivity.this.mCameraDevice.b(z, (Callback<String>) new Callback<String>() {
                    public void onSuccess(String str) {
                    }

                    public void onFailure(int i, String str) {
                        AlarmSettingActivity.this.mAlarmOnlyPeoplePush.setChecked(false);
                    }
                });
            }
        });
        this.mAlarmTime = (SettingsItemView) findViewById(R.id.settings_alarm_time);
        this.mAlarmLevel = (SettingsItemView) findViewById(R.id.settings_alarm_level);
        this.mAlarmTime.setOnClickListener(this);
        this.mAlarmLevel.setOnClickListener(this);
        AlarmConfig d = this.mCameraDevice.i().d();
        if (d == null) {
            showProgressDialog();
        } else {
            this.mAlarmConfig.a(d);
        }
        this.mCameraDevice.i().b((Callback<AlarmConfig>) new Callback<AlarmConfig>() {
            public void onSuccess(AlarmConfig alarmConfig) {
                if (!AlarmSettingActivity.this.isFinishing()) {
                    if (AlarmSettingActivity.this.mAlarmConfig != null) {
                        AlarmSettingActivity.this.mAlarmConfig.a(alarmConfig);
                    }
                    AlarmSettingActivity.this.dismissProgressDialog();
                    AlarmSettingActivity.this.refreshUI();
                }
            }

            public void onFailure(int i, String str) {
                if (!AlarmSettingActivity.this.isFinishing()) {
                    AlarmSettingActivity.this.dismissProgressDialog();
                    Toast.makeText(AlarmSettingActivity.this, R.string.settings_update_failed, 0).show();
                    if (AlarmSettingActivity.this.mCameraDevice.i().d() == null) {
                        AlarmSettingActivity.this.finish();
                    }
                }
            }
        });
        this.mAlarOpenLayout = findViewById(R.id.alarm_open_layout);
        this.mCameraDevice.i().b((Callback<int[][]>) new Callback<int[][]>() {
            public void onSuccess(int[][] iArr) {
                if (!AlarmSettingActivity.this.isFinishing()) {
                    if (iArr == null) {
                        SDKLog.e("alarm", " alarm level null ");
                    } else if (iArr[0][0] == 1) {
                        AlarmSettingActivity.this.mAlarmLevel.setInfo(AlarmSettingActivity.this.getString(R.string.alarm_level_low));
                        int unused = AlarmSettingActivity.this.mLastLevel = 0;
                        int unused2 = AlarmSettingActivity.this.mLevel = 0;
                    } else {
                        AlarmSettingActivity.this.mAlarmLevel.setInfo(AlarmSettingActivity.this.getString(R.string.alarm_level_high));
                        int unused3 = AlarmSettingActivity.this.mLastLevel = 1;
                        int unused4 = AlarmSettingActivity.this.mLevel = 1;
                    }
                }
            }

            public void onFailure(int i, String str) {
                SDKLog.e("alarm", " alarm level " + str);
            }
        }, true);
        Event.a(Event.B);
        findViewById(R.id.settings_alarm_event_type).setVisibility(8);
    }

    public void onResume() {
        super.onResume();
        refreshUI();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mAlarmConfig = null;
        if (this.mXQProgressDialog != null) {
            if (this.mXQProgressDialog.isShowing()) {
                this.mXQProgressDialog.dismiss();
            }
            this.mXQProgressDialog = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void refreshUI() {
        if (this.mAlarmConfig.f7965a == 0) {
            this.mAlarmItem.setChecked(false);
            this.mAlarOpenLayout.setVisibility(8);
            return;
        }
        this.mAlarmItem.setChecked(true);
        this.mAlarOpenLayout.setVisibility(0);
        SettingsItemView settingsItemView = this.mAlarmPushTime;
        settingsItemView.setInfo(this.mAlarmConfig.g + this.mMinute);
        this.mAlarmPush.setChecked(this.mAlarmConfig.f != 0);
        if (this.mPeoplePush != -1) {
            this.mAlarmOnlyPeoplePush.setChecked(this.mPeoplePush == 0);
        }
        this.mAlarmOnlyPeoplePush.setVisibility(this.mAlarmConfig.f == 0 ? 8 : 0);
        if (this.mAlarmConfig.g != 0) {
            this.mAlarmPushTime.setVisibility(0);
        } else {
            this.mAlarmPushTime.setVisibility(8);
        }
        if (this.mAlarmConfig.f7965a == 1) {
            this.mAlarmTime.setInfo(getString(R.string.alarm_time_all));
        } else if (this.mAlarmConfig.f7965a == 2) {
            int i = (this.mAlarmConfig.b * 60) + this.mAlarmConfig.c;
            int i2 = (this.mAlarmConfig.d * 60) + this.mAlarmConfig.e;
            if (i == 480 && i2 == 1200) {
                this.mAlarmTime.setInfo(getString(R.string.alarm_time_day));
            } else if (i == 1200 && i2 == 480) {
                this.mAlarmTime.setInfo(getString(R.string.alarm_time_night));
            } else {
                this.mAlarmTime.setInfo(getString(R.string.alarm_time_user));
            }
        }
        if (this.mLevel == 0) {
            this.mAlarmLevel.setInfo(getString(R.string.alarm_level_low));
        } else if (this.mLevel == 1) {
            this.mAlarmLevel.setInfo(getString(R.string.alarm_level_high));
        }
    }

    public void finish() {
        super.finish();
        if (this.mJump) {
            startActivity(new Intent(this, AlarmActivity.class));
        }
    }

    public void onBackPressed() {
        if (this.mPeoplePush != -1) {
            this.mCameraDevice.b(this.mPeoplePush == 0, (Callback<String>) null);
        }
        AlarmConfig d = this.mCameraDevice.i().d();
        if (d != null) {
            if (!d.equals(this.mAlarmConfig)) {
                showProgressDialog();
                this.mCameraDevice.i().a(this.mAlarmConfig, (Callback<JSONObject>) new Callback<JSONObject>() {
                    public void onSuccess(JSONObject jSONObject) {
                        if (!AlarmSettingActivity.this.isFinishing()) {
                            AlarmSettingActivity.this.runMainThread(new Runnable() {
                                public void run() {
                                    AlarmSettingActivity.this.dismissProgressDialog();
                                    Toast.makeText(AlarmSettingActivity.this, R.string.settings_set_success, 0).show();
                                    AlarmSettingActivity.this.finish();
                                }
                            });
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!AlarmSettingActivity.this.isFinishing()) {
                            AlarmSettingActivity.this.runMainThread(new Runnable() {
                                public void run() {
                                    AlarmSettingActivity.this.dismissProgressDialog();
                                    MLAlertDialog.Builder builder = new MLAlertDialog.Builder(AlarmSettingActivity.this);
                                    builder.b((int) R.string.settings_set_failed);
                                    builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            AlarmSettingActivity.this.finish();
                                        }
                                    });
                                    builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
                                    builder.d();
                                }
                            });
                        }
                    }
                });
                return;
            }
            super.onBackPressed();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        AlarmDirectionBean alarmDirectionBean;
        super.onActivityResult(i, i2, intent);
        if (i == 1001 && i2 == -1 && intent != null && (alarmDirectionBean = (AlarmDirectionBean) intent.getParcelableExtra("data")) != null) {
            setTime(alarmDirectionBean);
            refreshUI();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settings_alarm_level:
                setLevel();
                return;
            case R.id.settings_alarm_push_time:
                showSetTime();
                return;
            case R.id.settings_alarm_time:
                Intent intent = new Intent();
                AlarmDirectionBean alarmDirectionBean = new AlarmDirectionBean();
                if (this.mAlarmConfig.f7965a == 1) {
                    alarmDirectionBean.mTimeType = this.mAlarmConfig.f7965a;
                    intent.putExtra("data", alarmDirectionBean);
                    intent.setClass(this, AlarmDirectionTimeActivity.class);
                    startActivityForResult(intent, 1001);
                    return;
                } else if (this.mAlarmConfig.f7965a == 2) {
                    int i = (this.mAlarmConfig.b * 60) + this.mAlarmConfig.c;
                    int i2 = (this.mAlarmConfig.d * 60) + this.mAlarmConfig.e;
                    if (i == 480 && i2 == 1200) {
                        alarmDirectionBean.mTimeType = 2;
                    } else if (i == 1200 && i2 == 480) {
                        alarmDirectionBean.mTimeType = 3;
                    } else {
                        alarmDirectionBean.mTimeType = 4;
                        alarmDirectionBean.mStartTime = i;
                        alarmDirectionBean.mEndTime = i2;
                    }
                    intent.putExtra("data", alarmDirectionBean);
                    intent.setClass(this, AlarmDirectionTimeActivity.class);
                    startActivityForResult(intent, 1001);
                    return;
                } else {
                    return;
                }
            case R.id.title_bar_return:
                onBackPressed();
                return;
            default:
                return;
        }
    }

    private void setTime(AlarmDirectionBean alarmDirectionBean) {
        if (alarmDirectionBean.mTimeType == 1) {
            this.mAlarmConfig.f7965a = 1;
        } else if (alarmDirectionBean.mTimeType == 2) {
            this.mAlarmConfig.f7965a = 2;
            this.mAlarmConfig.b = 8;
            this.mAlarmConfig.c = 0;
            this.mAlarmConfig.d = 20;
            this.mAlarmConfig.e = 0;
        } else if (alarmDirectionBean.mTimeType == 3) {
            this.mAlarmConfig.f7965a = 2;
            this.mAlarmConfig.b = 20;
            this.mAlarmConfig.c = 0;
            this.mAlarmConfig.d = 8;
            this.mAlarmConfig.e = 0;
        } else if (alarmDirectionBean.mTimeType == 4) {
            this.mAlarmConfig.f7965a = 2;
            this.mAlarmConfig.b = alarmDirectionBean.mStartTime / 60;
            this.mAlarmConfig.c = alarmDirectionBean.mStartTime % 60;
            this.mAlarmConfig.d = alarmDirectionBean.mEndTime / 60;
            this.mAlarmConfig.e = alarmDirectionBean.mEndTime % 60;
        }
    }

    private void showSetTime() {
        int i;
        final AlarmConfig alarmConfig = new AlarmConfig();
        alarmConfig.a(this.mAlarmConfig);
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        int i2 = this.mAlarmConfig.g;
        if (i2 != 3) {
            if (i2 != 5) {
                if (i2 == 10) {
                    i = 2;
                } else if (i2 == 30) {
                    i = 3;
                }
            }
            i = 1;
        } else {
            i = 0;
        }
        builder.a((CharSequence[]) new String[]{"3" + this.mMinute, "5" + this.mMinute, "10" + this.mMinute, "30" + this.mMinute}, i, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        alarmConfig.g = 3;
                        return;
                    case 1:
                        alarmConfig.g = 5;
                        return;
                    case 2:
                        alarmConfig.g = 10;
                        return;
                    case 3:
                        alarmConfig.g = 30;
                        return;
                    default:
                        alarmConfig.g = 5;
                        return;
                }
            }
        });
        builder.a((int) R.string.settings_alarm_push_time);
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AlarmSettingActivity.this.mAlarmConfig.a(alarmConfig);
                AlarmSettingActivity.this.refreshUI();
            }
        });
        builder.d();
    }

    private void setLevel() {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        builder.a((int) R.string.alarm_level);
        View inflate = LayoutInflater.from(this).inflate(R.layout.file_setting_record_item, (ViewGroup) null);
        builder.b(inflate);
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (AlarmSettingActivity.this.mLevel != AlarmSettingActivity.this.mLastLevel) {
                    int[][] iArr = (int[][]) Array.newInstance(int.class, new int[]{4, 8});
                    if (AlarmSettingActivity.this.mLastLevel == 0) {
                        for (int i2 = 0; i2 < 4; i2++) {
                            for (int i3 = 0; i3 < 8; i3++) {
                                iArr[i2][i3] = 1;
                            }
                        }
                    } else {
                        for (int i4 = 0; i4 < 4; i4++) {
                            for (int i5 = 0; i5 < 8; i5++) {
                                iArr[i4][i5] = 3;
                            }
                        }
                    }
                    AlarmSettingActivity.this.mCameraDevice.i().a((Callback<Void>) new Callback<Void>() {
                        public void onSuccess(Void voidR) {
                            if (!AlarmSettingActivity.this.isFinishing()) {
                                int unused = AlarmSettingActivity.this.mLevel = AlarmSettingActivity.this.mLastLevel;
                                Toast.makeText(AlarmSettingActivity.this, R.string.settings_set_success, 0).show();
                                AlarmSettingActivity.this.refreshUI();
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (!AlarmSettingActivity.this.isFinishing()) {
                                Toast.makeText(AlarmSettingActivity.this, R.string.set_failed, 0).show();
                            }
                        }
                    }, iArr);
                }
                dialogInterface.dismiss();
            }
        });
        TextView textView = (TextView) inflate.findViewById(R.id.text1);
        TextView textView2 = (TextView) inflate.findViewById(R.id.text2);
        TextView textView3 = (TextView) inflate.findViewById(R.id.title1);
        TextView textView4 = (TextView) inflate.findViewById(R.id.title2);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.select_icon1);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.select_icon2);
        MLAlertDialog b = builder.b();
        inflate.findViewById(R.id.layout3).setVisibility(8);
        inflate.findViewById(R.id.list_item_line3).setVisibility(8);
        textView.setText(R.string.alarm_level_high);
        textView3.setText(R.string.alarm_level_high_title);
        textView2.setText(R.string.alarm_level_low);
        textView4.setText(R.string.alarm_level_low_title);
        if (this.mLevel == 1) {
            imageView.setVisibility(0);
            textView.setSelected(true);
            textView3.setSelected(true);
            imageView2.setVisibility(4);
            textView2.setSelected(false);
            textView4.setSelected(false);
        } else {
            imageView2.setVisibility(0);
            textView2.setSelected(true);
            textView4.setSelected(true);
            imageView.setVisibility(4);
            textView.setSelected(false);
            textView3.setSelected(false);
        }
        final ImageView imageView3 = imageView2;
        final TextView textView5 = textView2;
        final TextView textView6 = textView4;
        final ImageView imageView4 = imageView;
        AnonymousClass12 r8 = r0;
        final TextView textView7 = textView;
        TextView textView8 = textView4;
        View findViewById = inflate.findViewById(R.id.layout2);
        final TextView textView9 = textView3;
        AnonymousClass12 r0 = new View.OnClickListener() {
            public void onClick(View view) {
                imageView3.setVisibility(0);
                textView5.setSelected(true);
                textView6.setSelected(true);
                imageView4.setVisibility(4);
                textView7.setSelected(false);
                textView9.setSelected(false);
                int unused = AlarmSettingActivity.this.mLastLevel = 0;
            }
        };
        findViewById.setOnClickListener(r8);
        final ImageView imageView5 = imageView;
        final TextView textView10 = textView;
        final TextView textView11 = textView3;
        final ImageView imageView6 = imageView2;
        final TextView textView12 = textView2;
        final TextView textView13 = textView8;
        inflate.findViewById(R.id.layout1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                imageView5.setVisibility(0);
                textView10.setSelected(true);
                textView11.setSelected(true);
                imageView6.setVisibility(4);
                textView12.setSelected(false);
                textView13.setSelected(false);
                int unused = AlarmSettingActivity.this.mLastLevel = 1;
            }
        });
        b.show();
    }
}
