package com.xiaomi.smarthome.camera.v4.activity.alarm;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.Utils.TimeZoneUtils;
import com.mijia.app.Event;
import com.mijia.model.alarm.AlarmConfigV2;
import com.mijia.model.alarm.AlarmManager;
import com.mijia.model.alarm.AlarmNetUtils;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.view.widget.SettingsItemView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.plugin.mpk.PluginHostApiImpl;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.wxapi.WxPushHelper;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmSettingV2Activity extends CameraBaseActivity implements View.OnClickListener {
    private final int BIND_WX_CODE = 1005;
    AlarmConfigV2 alarmConfigV2 = new AlarmConfigV2();
    AlarmManager alarmManagerV2 = null;
    SettingsItemView mAlarmHumanOnly;
    SettingsItemView mAlarmItem;
    SettingsItemView mAlarmLevel;
    SettingsItemView mAlarmMove;
    private View mAlarmOpenLayout;
    SettingsItemView mAlarmPush;
    SettingsItemView mAlarmPushTime;
    SettingsItemView mAlarmTime;
    private boolean mJump = false;
    /* access modifiers changed from: private */
    public int mLastLevel = 0;
    /* access modifiers changed from: private */
    public int mLevel = -1;
    private String mMinute = "";
    SettingsItemView mWxPush;
    private XQProgressDialog mXQProgressDialog;

    /* access modifiers changed from: package-private */
    public void showProgressDialog() {
        dismissProgressDialog();
        this.mXQProgressDialog = new XQProgressDialog(this);
        this.mXQProgressDialog.setMessage(getString(R.string.smb_waiting));
        this.mXQProgressDialog.setCancelable(true);
        this.mXQProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                if (AlarmSettingV2Activity.this.mCameraDevice.i().d() == null) {
                    AlarmSettingV2Activity.this.finish();
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
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.more_alarm_setting_v4);
        this.mMinute = getString(R.string.tip_time_minute);
        this.mJump = getIntent().getBooleanExtra("jump", false);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.mAlarmItem = (SettingsItemView) findViewById(R.id.settings_alarm);
        this.mAlarmItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.bi, "type", (Object) Integer.valueOf(z ? 1 : 2));
                Event.a(Event.C);
                AlarmSettingV2Activity.this.setMotionDetection(z, AlarmSettingV2Activity.this.alarmConfigV2.e, AlarmSettingV2Activity.this.alarmConfigV2.b, AlarmSettingV2Activity.this.alarmConfigV2.c, AlarmSettingV2Activity.this.alarmConfigV2.i);
            }
        });
        this.mAlarmPushTime = (SettingsItemView) findViewById(R.id.settings_alarm_push_time);
        this.mAlarmPushTime.setOnClickListener(this);
        this.mAlarmPush = (SettingsItemView) findViewById(R.id.settings_alarm_push);
        this.mAlarmPush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.G);
                Event.a(Event.bm, "type", (Object) Integer.valueOf(z ? 1 : 2));
                AlarmSettingV2Activity.this.setPush(z);
            }
        });
        this.mAlarmHumanOnly = (SettingsItemView) findViewById(R.id.settings_only_people_push);
        this.mAlarmHumanOnly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.H);
                Event.a(Event.bn, "type", (Object) Integer.valueOf(z ? 1 : 2));
                AlarmSettingV2Activity.this.setHumanOnly(z);
            }
        });
        this.mAlarmMove = (SettingsItemView) findViewById(R.id.settings_alarm_move);
        this.mAlarmMove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AlarmSettingV2Activity.this.setMotionDetection(AlarmSettingV2Activity.this.alarmConfigV2.f7966a, AlarmSettingV2Activity.this.alarmConfigV2.e, AlarmSettingV2Activity.this.alarmConfigV2.b, AlarmSettingV2Activity.this.alarmConfigV2.c, z);
                Event.a(Event.bl, "type", (Object) Integer.valueOf(z ? 1 : 2));
            }
        });
        this.mAlarmTime = (SettingsItemView) findViewById(R.id.settings_alarm_time);
        this.mAlarmLevel = (SettingsItemView) findViewById(R.id.settings_alarm_level);
        this.mAlarmTime.setOnClickListener(this);
        this.mAlarmLevel.setOnClickListener(this);
        this.alarmManagerV2 = this.mCameraDevice.i();
        this.mAlarmOpenLayout = findViewById(R.id.alarm_open_layout);
        Event.a(Event.B);
    }

    public void onResume() {
        super.onResume();
        getAlarmConfig();
        refreshUI();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mXQProgressDialog != null) {
            if (this.mXQProgressDialog.isShowing()) {
                this.mXQProgressDialog.dismiss();
            }
            this.mXQProgressDialog = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void refreshUI() {
        if (!this.alarmConfigV2.f7966a) {
            this.mAlarmItem.setChecked(false);
            this.mAlarmOpenLayout.setVisibility(8);
            return;
        }
        this.mAlarmItem.setChecked(true);
        this.mAlarmOpenLayout.setVisibility(0);
        this.mAlarmTime.setInfo(getAlarmTimeInfo());
        if (this.alarmConfigV2.e > 0) {
            this.mAlarmPushTime.setVisibility(0);
            SettingsItemView settingsItemView = this.mAlarmPushTime;
            settingsItemView.setInfo(this.alarmConfigV2.e + this.mMinute);
        } else {
            this.mAlarmPushTime.setVisibility(8);
        }
        this.mAlarmHumanOnly.setChecked(this.alarmConfigV2.f);
        this.mAlarmPush.setChecked(this.alarmConfigV2.d);
        this.mAlarmMove.setChecked(this.alarmConfigV2.i);
        if (this.mAlarmPush.isChecked()) {
            this.mAlarmHumanOnly.setVisibility(0);
        } else {
            this.mAlarmHumanOnly.setVisibility(8);
        }
        String globalSettingServer = XmPluginHostApi.instance().getGlobalSettingServer();
        if (!TextUtils.isEmpty(globalSettingServer) && "cn".equals(globalSettingServer)) {
            this.mAlarmMove.setVisibility(0);
        }
        if (this.mLevel == 0) {
            this.mAlarmLevel.setInfo(getString(R.string.alarm_level_low));
        } else if (this.mLevel == 1) {
            this.mAlarmLevel.setInfo(getString(R.string.alarm_level_high));
        }
    }

    public void finish() {
        super.finish();
        if (!this.mJump) {
            return;
        }
        if (AlarmNetUtils.e()) {
            startActivity(new Intent(this, AlarmV2Activity.class));
        } else {
            startActivity(new Intent(this, AlarmActivity.class));
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001 && i2 == -1 && intent != null) {
            String stringExtra = intent.getStringExtra("startTime");
            String stringExtra2 = intent.getStringExtra("endTime");
            if (!TextUtils.isEmpty(stringExtra) && !TextUtils.isEmpty(stringExtra2)) {
                this.alarmConfigV2.b = stringExtra;
                this.alarmConfigV2.c = stringExtra2;
                setMotionDetection(this.alarmConfigV2.f7966a, this.alarmConfigV2.e, this.alarmConfigV2.b, this.alarmConfigV2.c, this.alarmConfigV2.i);
            }
            refreshUI();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settings_alarm_level:
                Event.a(Event.E);
                setLevel();
                return;
            case R.id.settings_alarm_push_time:
                Event.a(Event.F);
                showSetTime();
                return;
            case R.id.settings_alarm_time:
                Event.a(Event.D);
                Intent intent = new Intent();
                intent.putExtra("startTime", "" + this.alarmConfigV2.b);
                intent.putExtra("endTime", "" + this.alarmConfigV2.c);
                intent.setClass(this, AlarmDirectionTimeV2Activity.class);
                startActivityForResult(intent, 1001);
                return;
            case R.id.title_bar_return:
                onBackPressed();
                return;
            default:
                return;
        }
    }

    private void showSetTime() {
        int i;
        final AlarmConfigV2 alarmConfigV22 = new AlarmConfigV2();
        alarmConfigV22.a(this.alarmConfigV2);
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        int i2 = alarmConfigV22.e;
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
                int i2 = 3;
                switch (i) {
                    case 0:
                        alarmConfigV22.e = 3;
                        break;
                    case 1:
                        alarmConfigV22.e = 5;
                        i2 = 2;
                        break;
                    case 2:
                        alarmConfigV22.e = 10;
                        break;
                    case 3:
                        alarmConfigV22.e = 30;
                        i2 = 4;
                        break;
                }
                i2 = 1;
                Event.a(Event.bk, "type", (Object) Integer.valueOf(i2));
            }
        });
        builder.a((int) R.string.settings_alarm_push_time);
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                int i2 = alarmConfigV22.e;
                if (i2 == 3) {
                    Event.a(Event.K, "type", (Object) 1);
                } else if (i2 == 5) {
                    Event.a(Event.K, "type", (Object) 2);
                } else if (i2 == 10) {
                    Event.a(Event.K, "type", (Object) 3);
                } else if (i2 == 30) {
                    Event.a(Event.K, "type", (Object) 4);
                }
                AlarmSettingV2Activity.this.alarmConfigV2.a(alarmConfigV22);
                AlarmSettingV2Activity.this.setMotionDetection(AlarmSettingV2Activity.this.alarmConfigV2.f7966a, AlarmSettingV2Activity.this.alarmConfigV2.e, AlarmSettingV2Activity.this.alarmConfigV2.b, AlarmSettingV2Activity.this.alarmConfigV2.c, AlarmSettingV2Activity.this.alarmConfigV2.i);
                AlarmSettingV2Activity.this.refreshUI();
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
                if (AlarmSettingV2Activity.this.mLevel != AlarmSettingV2Activity.this.mLastLevel) {
                    int[][] iArr = (int[][]) Array.newInstance(int.class, new int[]{4, 8});
                    int i2 = 1;
                    if (AlarmSettingV2Activity.this.mLastLevel == 0) {
                        for (int i3 = 0; i3 < 4; i3++) {
                            for (int i4 = 0; i4 < 8; i4++) {
                                iArr[i3][i4] = 1;
                            }
                        }
                    } else {
                        for (int i5 = 0; i5 < 4; i5++) {
                            for (int i6 = 0; i6 < 8; i6++) {
                                iArr[i5][i6] = 3;
                            }
                        }
                    }
                    if (AlarmSettingV2Activity.this.mLastLevel == 0) {
                        Event.a(Event.J, "type", (Object) 1);
                    } else {
                        Event.a(Event.J, "type", (Object) 3);
                    }
                    String str = Event.bj;
                    if (AlarmSettingV2Activity.this.mLastLevel != 0) {
                        i2 = 2;
                    }
                    Event.a(str, "type", (Object) Integer.valueOf(i2));
                    try {
                        HashMap hashMap = new HashMap();
                        hashMap.put("did", AlarmSettingV2Activity.this.mCameraDevice.getDid());
                        hashMap.put("region", Locale.getDefault().getCountry());
                        JSONArray jSONArray = new JSONArray();
                        for (int put : AlarmSettingV2Activity.this.dimension2ToDimension1(iArr)) {
                            jSONArray.put(put);
                        }
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("sensitive", jSONArray);
                        hashMap.put("sensitive", jSONObject.toString());
                        AlarmNetUtils.a().g(AlarmSettingV2Activity.this.mCameraDevice.getModel(), hashMap.toString(), new Callback<JSONObject>() {
                            public void onSuccess(JSONObject jSONObject) {
                                if (!AlarmSettingV2Activity.this.isFinishing()) {
                                    AlarmSettingV2Activity.this.runOnUiThread(new Runnable() {
                                        public void run() {
                                            if (!AlarmSettingV2Activity.this.isFinishing()) {
                                                int unused = AlarmSettingV2Activity.this.mLevel = AlarmSettingV2Activity.this.mLastLevel;
                                                Toast.makeText(AlarmSettingV2Activity.this, R.string.settings_set_success, 0).show();
                                                AlarmSettingV2Activity.this.refreshUI();
                                            }
                                        }
                                    });
                                }
                            }

                            public void onFailure(int i, String str) {
                                if (!AlarmSettingV2Activity.this.isFinishing()) {
                                    Toast.makeText(AlarmSettingV2Activity.this, R.string.set_failed, 0).show();
                                }
                            }
                        });
                    } catch (JSONException unused) {
                    }
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
        AnonymousClass9 r8 = r0;
        final TextView textView7 = textView;
        TextView textView8 = textView4;
        View findViewById = inflate.findViewById(R.id.layout2);
        final TextView textView9 = textView3;
        AnonymousClass9 r0 = new View.OnClickListener() {
            public void onClick(View view) {
                imageView3.setVisibility(0);
                textView5.setSelected(true);
                textView6.setSelected(true);
                imageView4.setVisibility(4);
                textView7.setSelected(false);
                textView9.setSelected(false);
                int unused = AlarmSettingV2Activity.this.mLastLevel = 0;
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
                int unused = AlarmSettingV2Activity.this.mLastLevel = 1;
            }
        });
        b.show();
    }

    public void getAlarmConfig() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", this.mCameraDevice.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            this.alarmManagerV2.a(this.mCameraDevice.getModel(), jSONObject, (AlarmManager.IAlarmCallback) new AlarmManager.IAlarmCallback() {
                public void onSuccess(Object obj, Object obj2) {
                    if (!AlarmSettingV2Activity.this.isFinishing()) {
                        AlarmSettingV2Activity.this.alarmConfigV2.a(AlarmSettingV2Activity.this.alarmManagerV2.f());
                        if (AlarmSettingV2Activity.this.alarmConfigV2.g[0] == 1) {
                            int unused = AlarmSettingV2Activity.this.mLastLevel = 0;
                            int unused2 = AlarmSettingV2Activity.this.mLevel = 0;
                        } else {
                            int unused3 = AlarmSettingV2Activity.this.mLastLevel = 1;
                            int unused4 = AlarmSettingV2Activity.this.mLevel = 1;
                        }
                        AlarmSettingV2Activity.this.refreshUI();
                    }
                }

                public void onFailure(int i, String str) {
                    if (!AlarmSettingV2Activity.this.isFinishing()) {
                        AlarmSettingV2Activity.this.alarmConfigV2.a(AlarmSettingV2Activity.this.alarmManagerV2.f());
                        AlarmSettingV2Activity.this.refreshUI();
                    }
                }
            });
        } catch (JSONException unused) {
            this.alarmConfigV2.a(this.alarmManagerV2.f());
            refreshUI();
        }
    }

    public void setHumanOnly(boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", this.mCameraDevice.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("open", z);
            this.alarmManagerV2.f(this.mCameraDevice.getModel(), jSONObject, new AlarmManager.IAlarmCallback() {
                public void onSuccess(Object obj, Object obj2) {
                    if (!AlarmSettingV2Activity.this.isFinishing()) {
                        AlarmSettingV2Activity.this.alarmConfigV2.a(AlarmSettingV2Activity.this.alarmManagerV2.f());
                        AlarmSettingV2Activity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                AlarmSettingV2Activity.this.refreshUI();
                            }
                        });
                    }
                }

                public void onFailure(int i, String str) {
                    if (!AlarmSettingV2Activity.this.isFinishing()) {
                        AlarmSettingV2Activity.this.mAlarmHumanOnly.setChecked(AlarmSettingV2Activity.this.alarmConfigV2.f);
                        AlarmSettingV2Activity.this.refreshUI();
                    }
                }
            });
        } catch (JSONException unused) {
            this.mAlarmHumanOnly.setChecked(this.alarmConfigV2.f);
            refreshUI();
        }
    }

    /* access modifiers changed from: private */
    public void setMotionDetection(boolean z, int i, String str, String str2, boolean z2) {
        try {
            if (!TextUtils.isEmpty(str)) {
                String a2 = TimeZoneUtils.a().a(str);
                if (!TextUtils.isEmpty(a2)) {
                    str = a2;
                }
            } else {
                str = "00:00:00";
            }
            if (!TextUtils.isEmpty(str2)) {
                String a3 = TimeZoneUtils.a().a(str2);
                if (!TextUtils.isEmpty(a3)) {
                    str2 = a3;
                }
            } else {
                str2 = "23:59:59";
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", this.mCameraDevice.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("open", z);
            jSONObject.put(Constants.Name.INTERVAL, i);
            jSONObject.put("startTime", str);
            jSONObject.put("endTime", str2);
            jSONObject.put("trackSwitch", z2);
            this.alarmManagerV2.b(this.mCameraDevice.getModel(), jSONObject, (AlarmManager.IAlarmCallback) new AlarmManager.IAlarmCallback() {
                public void onSuccess(Object obj, Object obj2) {
                    if (!AlarmSettingV2Activity.this.isFinishing()) {
                        AlarmSettingV2Activity.this.alarmConfigV2.a(AlarmSettingV2Activity.this.alarmManagerV2.f());
                        AlarmSettingV2Activity.this.refreshUI();
                    }
                }

                public void onFailure(int i, String str) {
                    if (!AlarmSettingV2Activity.this.isFinishing()) {
                        AlarmSettingV2Activity.this.mAlarmItem.setChecked(AlarmSettingV2Activity.this.alarmConfigV2.f7966a);
                        AlarmSettingV2Activity.this.refreshUI();
                    }
                }
            });
        } catch (JSONException unused) {
            this.mAlarmItem.setChecked(this.alarmConfigV2.f7966a);
            refreshUI();
        }
    }

    /* access modifiers changed from: private */
    public void setPush(boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", this.mCameraDevice.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("open", z);
            this.alarmManagerV2.d(this.mCameraDevice.getModel(), jSONObject, new AlarmManager.IAlarmCallback() {
                public void onSuccess(Object obj, Object obj2) {
                    if (!AlarmSettingV2Activity.this.isFinishing()) {
                        AlarmSettingV2Activity.this.alarmConfigV2.a(AlarmSettingV2Activity.this.alarmManagerV2.f());
                        AlarmSettingV2Activity.this.refreshUI();
                    }
                }

                public void onFailure(int i, String str) {
                    if (!AlarmSettingV2Activity.this.isFinishing()) {
                        AlarmSettingV2Activity.this.mAlarmPush.setChecked(AlarmSettingV2Activity.this.alarmConfigV2.d);
                        AlarmSettingV2Activity.this.refreshUI();
                    }
                }
            });
        } catch (JSONException unused) {
            this.mAlarmPush.setChecked(this.alarmConfigV2.d);
            refreshUI();
        }
    }

    private String getAlarmTimeInfo() {
        if (this.alarmConfigV2.b.equals("00:00:00") && (this.alarmConfigV2.c.equals("23:59:59") || this.alarmConfigV2.c.equals(AlarmDirectionTimeV2Activity.ALL_DAY_END_TIME_OLD))) {
            return getString(R.string.alarm_time_all);
        }
        if (this.alarmConfigV2.b.equals("08:00:00") && this.alarmConfigV2.c.equals("20:00:00")) {
            return getString(R.string.alarm_time_day);
        }
        if (!this.alarmConfigV2.b.equals("20:00:00") || !this.alarmConfigV2.c.equals("08:00:00")) {
            return getString(R.string.alarm_time_user);
        }
        return getString(R.string.alarm_time_night);
    }

    /* access modifiers changed from: private */
    public int[] dimension2ToDimension1(int[][] iArr) {
        int[] iArr2 = new int[32];
        int i = 0;
        int i2 = 0;
        while (i < 4) {
            int i3 = i2;
            int i4 = 0;
            while (i4 < 8) {
                iArr2[i3] = iArr[i][i4];
                i4++;
                i3++;
            }
            i++;
            i2 = i3;
        }
        return iArr2;
    }

    /* access modifiers changed from: private */
    public void initWxStatus() {
        String globalSettingServer = XmPluginHostApi.instance().getGlobalSettingServer();
        boolean z = !TextUtils.isEmpty(globalSettingServer) && globalSettingServer.toLowerCase().equals("cn");
        if (XmPluginHostApi.instance().getApiLevel() >= 75 && z && this.mDeviceStat != null && this.mCameraDevice.isOwner()) {
            this.mWxPush.setVisibility(0);
            PluginHostApiImpl.instance().getWxPushSwitchState(this.mCameraDevice.getModel(), this.mCameraDevice.getDid(), new Callback<Boolean>() {
                public void onFailure(int i, String str) {
                }

                public void onSuccess(Boolean bool) {
                    if (!AlarmSettingV2Activity.this.isFinishing()) {
                        AlarmSettingV2Activity.this.wxPushRefresh(bool.booleanValue());
                    }
                }
            });
        }
    }

    private void setWxPush(final boolean z) {
        WxPushHelper.a().a(this.mCameraDevice.getModel(), this.mCameraDevice.getDid(), z, (Callback<Boolean>) new Callback<Boolean>() {
            public void onSuccess(Boolean bool) {
                if (!AlarmSettingV2Activity.this.isFinishing()) {
                    AlarmSettingV2Activity.this.wxPushRefresh(z);
                    ToastUtil.a((Context) AlarmSettingV2Activity.this, (int) R.string.smb_tip_set_success);
                }
            }

            public void onFailure(int i, String str) {
                if (!AlarmSettingV2Activity.this.isFinishing()) {
                    ToastUtil.a((Context) AlarmSettingV2Activity.this, (int) R.string.smb_tip_set_fail);
                    AlarmSettingV2Activity.this.initWxStatus();
                }
            }
        });
    }

    private void changeWxPush(final boolean z) {
        if (!z) {
            setWxPush(false);
            return;
        }
        this.mWxPush.setEnabled(false);
        PluginHostApiImpl.instance().setWxPush(this, this.mCameraDevice.getModel(), this.mCameraDevice.getDid(), z, 1005, new Callback<Boolean>() {
            public void onSuccess(Boolean bool) {
                if (!AlarmSettingV2Activity.this.isFinishing()) {
                    AlarmSettingV2Activity.this.wxPushRefresh(z);
                    ToastUtil.a((Context) AlarmSettingV2Activity.this, (int) R.string.smb_tip_set_success);
                }
            }

            public void onFailure(int i, String str) {
                if (!AlarmSettingV2Activity.this.isFinishing()) {
                    ToastUtil.a((Context) AlarmSettingV2Activity.this, (int) R.string.smb_tip_set_fail);
                    AlarmSettingV2Activity.this.initWxStatus();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void wxPushRefresh(boolean z) {
        this.mWxPush.setEnabled(true);
        this.mWxPush.setChecked(z);
    }
}
