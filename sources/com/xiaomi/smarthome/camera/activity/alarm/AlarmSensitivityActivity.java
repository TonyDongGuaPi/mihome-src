package com.xiaomi.smarthome.camera.activity.alarm;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.mijia.camera.CameraProperties;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import org.json.JSONArray;

public class AlarmSensitivityActivity extends CameraBaseActivity implements View.OnClickListener {
    private static final String TAG = "AlarmSensitivityActivity";
    private ImageView iv_alarm_sensivity_hight;
    private ImageView iv_alarm_sensivity_low;
    private ImageView iv_alarm_sensivity_middle;
    CameraProperties mCameraProperties;
    XQProgressDialog mProgressDialog;
    private TextView tv_alarm_sensivity_hight;
    private TextView tv_alarm_sensivity_low;
    private TextView tv_alarm_sensivity_middle;
    private int type;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.camera_activity_alarm_sensivity);
        this.mCameraProperties = (CameraProperties) this.mCameraDevice.f();
        initView();
        loadData();
    }

    private void initView() {
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.alarm_sensivity_setting);
        findViewById(R.id.title_bar_more).setVisibility(8);
        findViewById(R.id.ll_alarm_sensivity_hight).setOnClickListener(this);
        findViewById(R.id.ll_alarm_sensivity_middle).setOnClickListener(this);
        findViewById(R.id.ll_alarm_sensivity_low).setOnClickListener(this);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        this.iv_alarm_sensivity_hight = (ImageView) findViewById(R.id.iv_alarm_sensivity_hight);
        this.tv_alarm_sensivity_hight = (TextView) findViewById(R.id.tv_alarm_sensivity_hight);
        this.iv_alarm_sensivity_middle = (ImageView) findViewById(R.id.iv_alarm_sensivity_middle);
        this.tv_alarm_sensivity_middle = (TextView) findViewById(R.id.tv_alarm_sensivity_middle);
        this.iv_alarm_sensivity_low = (ImageView) findViewById(R.id.iv_alarm_sensivity_low);
        this.tv_alarm_sensivity_low = (TextView) findViewById(R.id.tv_alarm_sensivity_low);
        this.mProgressDialog = new XQProgressDialog(activity());
        this.mProgressDialog.setMessage(getString(R.string.loading_data));
        this.mProgressDialog.show();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.title_bar_return) {
            switch (id) {
                case R.id.ll_alarm_sensivity_hight:
                    this.iv_alarm_sensivity_hight.setVisibility(0);
                    this.iv_alarm_sensivity_middle.setVisibility(4);
                    this.iv_alarm_sensivity_low.setVisibility(4);
                    if (Build.VERSION.SDK_INT >= 23) {
                        this.tv_alarm_sensivity_hight.setTextColor(getColor(R.color.leave_msg_playing));
                        this.tv_alarm_sensivity_middle.setTextColor(getColor(R.color.black));
                        this.tv_alarm_sensivity_low.setTextColor(getColor(R.color.black));
                    }
                    this.type = 3;
                    break;
                case R.id.ll_alarm_sensivity_low:
                    this.iv_alarm_sensivity_hight.setVisibility(4);
                    this.iv_alarm_sensivity_middle.setVisibility(4);
                    this.iv_alarm_sensivity_low.setVisibility(0);
                    if (Build.VERSION.SDK_INT >= 23) {
                        this.tv_alarm_sensivity_hight.setTextColor(getColor(R.color.black));
                        this.tv_alarm_sensivity_middle.setTextColor(getColor(R.color.black));
                        this.tv_alarm_sensivity_low.setTextColor(getColor(R.color.leave_msg_playing));
                    }
                    this.type = 1;
                    break;
                case R.id.ll_alarm_sensivity_middle:
                    this.iv_alarm_sensivity_hight.setVisibility(4);
                    this.iv_alarm_sensivity_middle.setVisibility(0);
                    this.iv_alarm_sensivity_low.setVisibility(4);
                    if (Build.VERSION.SDK_INT >= 23) {
                        this.tv_alarm_sensivity_hight.setTextColor(getColor(R.color.black));
                        this.tv_alarm_sensivity_middle.setTextColor(getColor(R.color.leave_msg_playing));
                        this.tv_alarm_sensivity_low.setTextColor(getColor(R.color.black));
                    }
                    this.type = 2;
                    break;
            }
        } else {
            onBackPressed();
        }
        setData(this.type);
    }

    private void loadData() {
        this.mCameraDevice.i().c((Callback<JSONArray>) new Callback<JSONArray>() {
            public void onSuccess(JSONArray jSONArray) {
                if (!AlarmSensitivityActivity.this.isFinishing()) {
                    if (AlarmSensitivityActivity.this.mProgressDialog.isShowing()) {
                        AlarmSensitivityActivity.this.mProgressDialog.dismiss();
                    }
                    if (jSONArray.length() > 0) {
                        AlarmSensitivityActivity.this.refreshUI(jSONArray.optInt(0));
                    }
                }
            }

            public void onFailure(int i, String str) {
                if (!AlarmSensitivityActivity.this.isFinishing()) {
                    if (AlarmSensitivityActivity.this.mProgressDialog.isShowing()) {
                        AlarmSensitivityActivity.this.mProgressDialog.dismiss();
                    }
                    ToastUtil.a((Context) AlarmSensitivityActivity.this.activity(), (CharSequence) AlarmSensitivityActivity.this.getString(R.string.tip_no_info));
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshUI(int i) {
        this.type = i;
        switch (i) {
            case 1:
                this.iv_alarm_sensivity_hight.setVisibility(4);
                this.iv_alarm_sensivity_middle.setVisibility(4);
                this.iv_alarm_sensivity_low.setVisibility(0);
                if (Build.VERSION.SDK_INT >= 23) {
                    this.tv_alarm_sensivity_hight.setTextColor(getColor(R.color.black));
                    this.tv_alarm_sensivity_middle.setTextColor(getColor(R.color.black));
                    this.tv_alarm_sensivity_low.setTextColor(getColor(R.color.leave_msg_playing));
                    return;
                }
                return;
            case 2:
                this.iv_alarm_sensivity_hight.setVisibility(4);
                this.iv_alarm_sensivity_middle.setVisibility(0);
                this.iv_alarm_sensivity_low.setVisibility(4);
                if (Build.VERSION.SDK_INT >= 23) {
                    this.tv_alarm_sensivity_hight.setTextColor(getColor(R.color.black));
                    this.tv_alarm_sensivity_middle.setTextColor(getColor(R.color.leave_msg_playing));
                    this.tv_alarm_sensivity_low.setTextColor(getColor(R.color.black));
                    return;
                }
                return;
            case 3:
                this.iv_alarm_sensivity_hight.setVisibility(0);
                this.iv_alarm_sensivity_middle.setVisibility(4);
                this.iv_alarm_sensivity_low.setVisibility(4);
                if (Build.VERSION.SDK_INT >= 23) {
                    this.tv_alarm_sensivity_hight.setTextColor(getColor(R.color.leave_msg_playing));
                    this.tv_alarm_sensivity_middle.setTextColor(getColor(R.color.black));
                    this.tv_alarm_sensivity_low.setTextColor(getColor(R.color.black));
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void setData(int i) {
        this.mCameraDevice.i().a((Callback<Void>) new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!AlarmSensitivityActivity.this.isFinishing()) {
                    if (AlarmSensitivityActivity.this.mProgressDialog.isShowing()) {
                        AlarmSensitivityActivity.this.mProgressDialog.dismiss();
                    }
                    ToastUtil.a((Context) AlarmSensitivityActivity.this.activity(), (int) R.string.settings_set_success);
                }
            }

            public void onFailure(int i, String str) {
                if (!AlarmSensitivityActivity.this.isFinishing()) {
                    if (AlarmSensitivityActivity.this.mProgressDialog.isShowing()) {
                        AlarmSensitivityActivity.this.mProgressDialog.dismiss();
                    }
                    ToastUtil.a((Context) AlarmSensitivityActivity.this.activity(), (int) R.string.smb_tip_set_fail);
                }
            }
        }, i);
        this.mCameraDevice.i().b((Callback<Void>) new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!AlarmSensitivityActivity.this.isFinishing()) {
                    if (AlarmSensitivityActivity.this.mProgressDialog.isShowing()) {
                        AlarmSensitivityActivity.this.mProgressDialog.dismiss();
                    }
                    ToastUtil.a((Context) AlarmSensitivityActivity.this.activity(), (int) R.string.settings_set_success);
                }
            }

            public void onFailure(int i, String str) {
                if (!AlarmSensitivityActivity.this.isFinishing()) {
                    if (AlarmSensitivityActivity.this.mProgressDialog.isShowing()) {
                        AlarmSensitivityActivity.this.mProgressDialog.dismiss();
                    }
                    ToastUtil.a((Context) AlarmSensitivityActivity.this.activity(), (int) R.string.smb_tip_set_fail);
                }
            }
        }, i);
    }

    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("alarmLevel", this.type);
        setResult(-1, intent);
        super.onBackPressed();
    }
}
