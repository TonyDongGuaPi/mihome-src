package com.xiaomi.smarthome.camera.lowpower;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.mijia.debug.SDKLog;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.lowpower.entity.EffectiveTimeSpan;
import com.xiaomi.smarthome.camera.lowpower.entity.LinkageDeviceInfo;
import com.xiaomi.smarthome.camera.lowpower.linkage.DeviceLinkSelectActivity;
import com.xiaomi.smarthome.camera.lowpower.timerange.CreateTimeEdit2Activity;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.lite.scene.SceneLogUtil;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScreenDeviceLinkageSettingActivity extends CameraBaseActivity implements View.OnClickListener {
    public static final String TAG = "ScreenDeviceLinkageSettingActivity";
    public static int sActivityCounts;
    private String dataList;
    private boolean isMultiChoice;
    private boolean isToSelectPage;
    /* access modifiers changed from: private */
    public LinkageManager linkManager;
    /* access modifiers changed from: private */
    public EffectiveTimeSpan mEffectiveTimeSpan;
    private int maxLength;
    private View selectActiveTimeRange;
    private View selectScreenDevice;
    private TextView timeRangeText;

    /* access modifiers changed from: protected */
    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        sActivityCounts++;
        if (sActivityCounts > 1) {
            finish();
            return;
        }
        setContentView(R.layout.activity_screen_device_linkage_setting);
        this.isMultiChoice = getIntent().getBooleanExtra("extra_multi_choice", true);
        this.maxLength = getIntent().getIntExtra("extra_max_length", 3);
        this.isToSelectPage = getIntent().getBooleanExtra("extra_to_select_page", false);
        this.dataList = getIntent().getStringExtra("extra_screen_device_list");
        initViews();
        initData();
        if (this.isToSelectPage) {
            Intent intent = new Intent(this, DeviceLinkSelectActivity.class);
            intent.putExtra("extra_multi_choice", this.isMultiChoice);
            intent.putExtra("max_length", this.maxLength);
            startActivity(intent);
        }
    }

    private void initData() {
        this.linkManager = LinkageManager.getInstance();
        if (!TextUtils.isEmpty(this.dataList)) {
            try {
                restoreDeviceListFromJSON(this.mDeviceStat.did, new JSONArray(this.dataList));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            this.linkManager.getLinkageDeviceList(this.mDeviceStat.did, this.mDeviceStat.model, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    SDKLog.b("xm111", "success result:");
                    for (LinkageDeviceInfo deviceStat : ScreenDeviceLinkageSettingActivity.this.linkManager.getLinkageList()) {
                        SDKLog.b("xm111", " deviceStat:" + deviceStat.getDeviceStat() + " ");
                    }
                }

                public void onFailure(int i, String str) {
                    SDKLog.b("xm111", "error:" + str);
                }
            });
        }
        this.linkManager.getTimeRange(this.mDeviceStat.model, this.mDeviceStat.did, new Callback<JSONObject>() {
            public void onSuccess(JSONObject jSONObject) {
                Log.d("xm111", "" + jSONObject.toString());
                EffectiveTimeSpan unused = ScreenDeviceLinkageSettingActivity.this.mEffectiveTimeSpan = EffectiveTimeSpan.parseFromJsonObject(jSONObject);
                ScreenDeviceLinkageSettingActivity.this.updateTimeRange();
            }

            public void onFailure(int i, String str) {
                Log.d("xm111", "" + str.toString());
                ScreenDeviceLinkageSettingActivity.this.updateTimeRange();
            }
        });
    }

    private void restoreDeviceListFromJSON(String str, JSONArray jSONArray) {
        LinkageManager.getInstance().restoreDeviceListFromJSON(str, jSONArray);
    }

    private void initViews() {
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ScreenDeviceLinkageSettingActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.link_device);
        this.selectScreenDevice = findViewById(R.id.listitem_condition_select);
        this.selectActiveTimeRange = findViewById(R.id.listitem_timespan);
        this.selectActiveTimeRange.setOnClickListener(this);
        this.selectScreenDevice.setOnClickListener(this);
        this.timeRangeText = (TextView) findViewById(R.id.tv_timespan);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.listitem_condition_select) {
            Intent intent = new Intent(this, DeviceLinkSelectActivity.class);
            intent.putExtra("extra_multi_choice", this.isMultiChoice);
            intent.putExtra("max_length", this.maxLength);
            startActivity(intent);
        } else if (id == R.id.listitem_timespan) {
            Intent intent2 = new Intent(this, CreateTimeEdit2Activity.class);
            intent2.putExtra("time_span", this.mEffectiveTimeSpan);
            startActivityForResult(intent2, 1);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        sActivityCounts--;
        if (this.linkManager != null) {
            this.linkManager.destroyInstance();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            final EffectiveTimeSpan effectiveTimeSpan = (EffectiveTimeSpan) intent.getParcelableExtra("time_span");
            this.linkManager.submitTimeRange(this.mDeviceStat.model, this.mDeviceStat.did, effectiveTimeSpan.toJSON().toString(), new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    EffectiveTimeSpan unused = ScreenDeviceLinkageSettingActivity.this.mEffectiveTimeSpan = effectiveTimeSpan;
                    ScreenDeviceLinkageSettingActivity.this.updateTimeRange();
                }

                public void onFailure(int i, String str) {
                    ToastUtil.a((int) R.string.action_fail);
                    ScreenDeviceLinkageSettingActivity.this.updateTimeRange();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void updateTimeRange() {
        if (this.mEffectiveTimeSpan == null) {
            this.timeRangeText.setText(getString(R.string.scene_exetime_all_day));
        } else if (this.mEffectiveTimeSpan.fromHour == this.mEffectiveTimeSpan.toHour && this.mEffectiveTimeSpan.fromMin == this.mEffectiveTimeSpan.toMin) {
            this.timeRangeText.setText(getString(R.string.scene_exetime_all_day));
        } else {
            int rawOffset = new GregorianCalendar().getTimeZone().getRawOffset();
            int convert = (int) TimeUnit.HOURS.convert((long) rawOffset, TimeUnit.MILLISECONDS);
            SceneLogUtil.a("offsetHOser----" + convert + "--mGTMoffeset---" + rawOffset);
            int i = (((this.mEffectiveTimeSpan.fromHour + convert) + -8) + 24) % 24;
            int i2 = this.mEffectiveTimeSpan.fromMin;
            int i3 = (((this.mEffectiveTimeSpan.toHour + convert) + -8) + 24) % 24;
            int i4 = this.mEffectiveTimeSpan.toMin;
            if (i3 < i || (i3 == i && i4 < i2)) {
                TextView textView = this.timeRangeText;
                textView.setText(formatTime(i, i2) + "-" + formatTime(i3, i4) + Operators.BRACKET_START_STR + getResources().getString(R.string.scene_exetime_second_day) + Operators.BRACKET_END_STR);
                return;
            }
            TextView textView2 = this.timeRangeText;
            textView2.setText(formatTime(i, i2) + "-" + formatTime(i3, i4));
        }
    }

    private String formatTime(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        if (i >= 0 && i <= 9) {
            sb.append("0");
        }
        sb.append(i);
        sb.append(":");
        if (i2 >= 0 && i2 <= 9) {
            sb.append("0");
        }
        sb.append(i2);
        return sb.toString();
    }
}
