package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoUserStatus;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.ICloudVideoCallback;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoEventLogger;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoUtils;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class CloudVideoSettingActivity extends CloudVideoBaseActivity implements View.OnClickListener {
    private static final String TAG = "CloudVideoSettingActivity";
    private String did;
    private ImageView ivHeaderLeftBack;
    private ImageView ivHeaderRightSetting;
    private String model;
    private RelativeLayout rlCloudServiceEULA;
    private RelativeLayout rlDownloadVideo;
    private RelativeLayout rlTitleBar;
    /* access modifiers changed from: private */
    public SwitchButton sbCloudVideoService;
    private String title;
    /* access modifiers changed from: private */
    public TextView tvBuy;
    /* access modifiers changed from: private */
    public TextView tvDurationHint;
    /* access modifiers changed from: private */
    public TextView tvStatus;
    /* access modifiers changed from: private */
    public TextView tvStorageDesc;
    private TextView tvSubTitle;
    /* access modifiers changed from: private */
    public TextView tvTitle;
    private TextView tvTitleBarTitle;
    /* access modifiers changed from: private */
    public TextView tvUsageDesc;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Device b;
        super.onCreate(bundle);
        setContentView(R.layout.cs_activity_setting);
        this.did = getIntent().getStringExtra("did");
        this.title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(this.did) && (b = SmartHomeDeviceManager.a().b(this.did)) != null) {
            this.model = b.model;
        }
        initViews();
        CloudVideoEventLogger.EventClick(CloudVideoUtils.getDeviceModel(this.did), CloudVideoEventLogger.CloudServiceNumber, (String) null);
    }

    private void initViews() {
        this.rlTitleBar = (RelativeLayout) findViewById(R.id.rlTitleBar);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.rlTitleBar.getLayoutParams());
        layoutParams.setMargins(0, TitleBarUtil.a(), 0, 0);
        this.rlTitleBar.setLayoutParams(layoutParams);
        this.rlTitleBar.bringToFront();
        this.ivHeaderLeftBack = (ImageView) findViewById(R.id.ivHeaderLeftBack);
        this.ivHeaderLeftBack.setOnClickListener(this);
        this.rlCloudServiceEULA = (RelativeLayout) findViewById(R.id.rlCloudServiceEULA);
        this.rlCloudServiceEULA.setOnClickListener(this);
        this.ivHeaderRightSetting = (ImageView) findViewById(R.id.ivHeaderRightSetting);
        this.ivHeaderRightSetting.setVisibility(8);
        this.tvBuy = (TextView) findViewById(R.id.tvBuy);
        this.tvBuy.setOnClickListener(this);
        this.tvTitle = (TextView) findViewById(R.id.tvTitle);
        this.tvSubTitle = (TextView) findViewById(R.id.tvSubTitle);
        if (!TextUtils.isEmpty(this.title)) {
            this.tvSubTitle.setText(this.title);
        }
        this.tvStatus = (TextView) findViewById(R.id.tvStatus);
        this.tvUsageDesc = (TextView) findViewById(R.id.tvUsageDesc);
        this.tvStorageDesc = (TextView) findViewById(R.id.tvStorageDesc);
        this.tvDurationHint = (TextView) findViewById(R.id.tvDurationHint);
        this.tvTitleBarTitle = (TextView) findViewById(R.id.tvTitleBarTitle);
        this.tvTitleBarTitle.setVisibility(0);
        this.tvTitleBarTitle.setText(R.string.cs_my_service);
        this.tvTitleBarTitle.setTextSize(16.0f);
        this.tvTitleBarTitle.setTextColor(-16777216);
        this.rlDownloadVideo = (RelativeLayout) findViewById(R.id.rlDownloadVideo);
        this.rlDownloadVideo.setVisibility(0);
        this.rlDownloadVideo.setOnClickListener(this);
        this.sbCloudVideoService = (SwitchButton) findViewById(R.id.sbCloudVideoService);
        this.sbCloudVideoService.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LogUtil.a(CloudVideoSettingActivity.TAG, "isChecked:" + z);
                CloudVideoSettingActivity.this.setUsage(z);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getStatus();
        getCapacity();
        getUsage();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ivHeaderLeftBack) {
            onBackPressed();
        } else if (id == R.id.rlCloudServiceEULA) {
            CloudVideoEventLogger.EventClick(CloudVideoUtils.getDeviceModel(this.did), CloudVideoEventLogger.CloudServiceInfoNumber, (String) null);
            String str = CloudVideoWebActivity.REQUEST_CLOUD_SERVICE_EULA;
            Locale d = ServerCompact.d(getContext());
            if (d != null) {
                str = str + "?locale=" + d.getLanguage();
            } else {
                Locale locale = Locale.getDefault();
                if (locale != null) {
                    str = str + "?locale=" + locale.getLanguage();
                }
            }
            LogUtil.a(TAG, "url:" + str);
            Intent intent = new Intent(getContext(), CloudVideoWebActivity.class);
            intent.putExtra("url", str);
            intent.putExtra("title", getString(R.string.cs_service));
            intent.putExtra("did", this.did);
            startActivity(intent);
        } else if (id == R.id.rlDownloadVideo) {
            CloudVideoEventLogger.EventClick(CloudVideoUtils.getDeviceModel(this.did), CloudVideoEventLogger.DownloadListNumber, (String) null);
            Intent intent2 = new Intent(getContext(), CloudVideoDownloadActivity.class);
            intent2.putExtra("did", this.did);
            Device b = SmartHomeDeviceManager.a().b(this.did);
            if (b != null) {
                intent2.putExtra("uid", b.userId);
                startActivity(intent2);
            }
        } else if (id == R.id.tvBuy) {
            CloudVideoNetUtils.getInstance().openCloudVideoBuyPage(this, this.did);
        }
    }

    private void getStatus() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", this.did);
            Locale I = CoreApi.a().I();
            if (I != null) {
                jSONObject.put("region", I.getCountry());
            } else {
                jSONObject.put("region", Locale.getDefault().getCountry());
            }
            LogUtil.a(TAG, "jsonObject:" + jSONObject.toString());
            CloudVideoNetUtils.getInstance().getSettingStatus(this, jSONObject.toString(), new ICloudVideoCallback<CloudVideoUserStatus>() {
                public void onCloudVideoSuccess(final CloudVideoUserStatus cloudVideoUserStatus, Object obj) {
                    if (!CloudVideoSettingActivity.this.isFinishing() && cloudVideoUserStatus != null) {
                        LogUtil.a(CloudVideoSettingActivity.TAG, "onCloudVideoSuccess" + cloudVideoUserStatus.toString());
                        CloudVideoSettingActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                if (cloudVideoUserStatus.vip) {
                                    CloudVideoSettingActivity.this.sbCloudVideoService.setEnabled(true);
                                    CloudVideoSettingActivity.this.tvTitle.setVisibility(0);
                                    TextView access$200 = CloudVideoSettingActivity.this.tvTitle;
                                    access$200.setText("" + cloudVideoUserStatus.packageType);
                                    TextView access$300 = CloudVideoSettingActivity.this.tvStatus;
                                    access$300.setText("" + CloudVideoSettingActivity.this.getString(R.string.cs_service_in_use));
                                    TextView access$400 = CloudVideoSettingActivity.this.tvBuy;
                                    access$400.setText("" + CloudVideoSettingActivity.this.getString(R.string.cs_renew_service));
                                    CloudVideoSettingActivity.this.tvDurationHint.setVisibility(0);
                                    TextView access$500 = CloudVideoSettingActivity.this.tvDurationHint;
                                    access$500.setText("" + String.format(CloudVideoSettingActivity.this.getString(R.string.cs_service_duration), new Object[]{simpleDateFormat.format(Long.valueOf(cloudVideoUserStatus.startTime)), simpleDateFormat.format(Long.valueOf(cloudVideoUserStatus.endTime))}));
                                    SpannableString spannableString = new SpannableString("" + CloudVideoSettingActivity.this.millisToDay(System.currentTimeMillis() - cloudVideoUserStatus.startTime) + CloudVideoSettingActivity.this.getString(R.string.cs_day));
                                    if (!TextUtils.isEmpty(spannableString.toString())) {
                                        spannableString.setSpan(new RelativeSizeSpan(2.5f), 0, spannableString.length() - (!TextUtils.isEmpty(CloudVideoSettingActivity.this.getString(R.string.cs_day)) ? CloudVideoSettingActivity.this.getString(R.string.cs_day).length() : 0), 0);
                                        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#D8A24F")), 0, spannableString.length(), 0);
                                        CloudVideoSettingActivity.this.tvUsageDesc.setText(spannableString);
                                        return;
                                    }
                                    return;
                                }
                                CloudVideoSettingActivity.this.sbCloudVideoService.setChecked(false);
                                CloudVideoSettingActivity.this.sbCloudVideoService.setEnabled(false);
                                CloudVideoSettingActivity.this.tvTitle.setVisibility(8);
                                if (cloudVideoUserStatus.status == 1) {
                                    CloudVideoSettingActivity.this.tvDurationHint.setVisibility(0);
                                    TextView access$3002 = CloudVideoSettingActivity.this.tvStatus;
                                    access$3002.setText("" + CloudVideoSettingActivity.this.getString(R.string.cs_service_expired));
                                } else if (cloudVideoUserStatus.status == 2) {
                                    CloudVideoSettingActivity.this.tvDurationHint.setVisibility(0);
                                    TextView access$3003 = CloudVideoSettingActivity.this.tvStatus;
                                    access$3003.setText("" + CloudVideoSettingActivity.this.getString(R.string.cs_service_in_use));
                                } else {
                                    CloudVideoSettingActivity.this.tvDurationHint.setVisibility(8);
                                    TextView access$3004 = CloudVideoSettingActivity.this.tvStatus;
                                    access$3004.setText("" + CloudVideoSettingActivity.this.getString(R.string.cs_service_not_use));
                                }
                                TextView access$5002 = CloudVideoSettingActivity.this.tvDurationHint;
                                access$5002.setText("" + String.format(CloudVideoSettingActivity.this.getString(R.string.cs_service_duration), new Object[]{simpleDateFormat.format(Long.valueOf(cloudVideoUserStatus.startTime)), simpleDateFormat.format(Long.valueOf(cloudVideoUserStatus.endTime))}));
                                TextView access$4002 = CloudVideoSettingActivity.this.tvBuy;
                                access$4002.setText("" + CloudVideoSettingActivity.this.getString(R.string.cs_buy_service));
                                SpannableString spannableString2 = new SpannableString(CloudVideoSettingActivity.this.getString(R.string.cs_day_0));
                                if (!TextUtils.isEmpty(spannableString2.toString())) {
                                    spannableString2.setSpan(new RelativeSizeSpan(2.5f), 0, spannableString2.length() - 1, 0);
                                    spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#D8A24F")), 0, spannableString2.length(), 0);
                                    CloudVideoSettingActivity.this.tvUsageDesc.setText(spannableString2);
                                }
                            }
                        });
                    }
                }

                public void onCloudVideoFailed(int i, String str) {
                    if (!CloudVideoSettingActivity.this.isFinishing()) {
                        CloudVideoSettingActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                            }
                        });
                        LogUtil.b(CloudVideoSettingActivity.TAG, "errorCode:" + i + " errorInfo:" + str);
                    }
                }
            });
        } catch (JSONException unused) {
        }
    }

    private void getCapacity() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", this.did);
            jSONObject.put("region", Locale.getDefault().getCountry());
            CloudVideoNetUtils.getInstance().getSettingCapacity(this, jSONObject.toString(), new ICloudVideoCallback<Long>() {
                public void onCloudVideoSuccess(final Long l, Object obj) {
                    if (!CloudVideoSettingActivity.this.isFinishing()) {
                        CloudVideoSettingActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                String access$800 = CloudVideoSettingActivity.this.capacityTranslation(l.longValue());
                                if (!TextUtils.isEmpty(access$800)) {
                                    SpannableString spannableString = new SpannableString(access$800);
                                    if (access$800.toLowerCase().equals("max")) {
                                        spannableString.setSpan(new RelativeSizeSpan(2.5f), 0, spannableString.length(), 0);
                                    } else {
                                        spannableString.setSpan(new RelativeSizeSpan(2.5f), 0, spannableString.length() - 2, 0);
                                    }
                                    spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#D8A24F")), 0, spannableString.length(), 0);
                                    CloudVideoSettingActivity.this.tvStorageDesc.setText(spannableString);
                                }
                            }
                        });
                    }
                }

                public void onCloudVideoFailed(int i, String str) {
                    if (!CloudVideoSettingActivity.this.isFinishing()) {
                        LogUtil.b(CloudVideoSettingActivity.TAG, "errorCode:" + i + " errorInfo:" + str);
                    }
                }
            });
        } catch (JSONException unused) {
        }
    }

    private void getUsage() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", this.did);
            Locale I = CoreApi.a().I();
            if (I != null) {
                jSONObject.put("region", I.getCountry());
            } else {
                jSONObject.put("region", Locale.getDefault().getCountry());
            }
            CloudVideoNetUtils.getInstance().getSettingUsage(this, jSONObject.toString(), new ICloudVideoCallback<Boolean>() {
                public void onCloudVideoSuccess(final Boolean bool, Object obj) {
                    if (!CloudVideoSettingActivity.this.isFinishing()) {
                        LogUtil.a(CloudVideoSettingActivity.TAG, "isOpen:" + bool);
                        CloudVideoSettingActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                CloudVideoSettingActivity.this.sbCloudVideoService.setChecked(bool.booleanValue());
                            }
                        });
                    }
                }

                public void onCloudVideoFailed(int i, String str) {
                    if (!CloudVideoSettingActivity.this.isFinishing()) {
                        LogUtil.b(CloudVideoSettingActivity.TAG, "errorCode:" + i + " errorInfo:" + str);
                    }
                }
            });
        } catch (JSONException unused) {
        }
    }

    /* access modifiers changed from: private */
    public void setUsage(final boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", this.did);
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("open", z);
            CloudVideoNetUtils.getInstance().setSettingUsage(this, jSONObject.toString(), new ICloudVideoCallback<Boolean>() {
                public void onCloudVideoSuccess(Boolean bool, Object obj) {
                    if (!CloudVideoSettingActivity.this.isFinishing()) {
                    }
                }

                public void onCloudVideoFailed(int i, String str) {
                    if (!CloudVideoSettingActivity.this.isFinishing()) {
                        CloudVideoSettingActivity.this.sbCloudVideoService.setChecked(!z);
                        LogUtil.b(CloudVideoSettingActivity.TAG, "errorCode:" + i + " errorInfo:" + str);
                    }
                }
            });
        } catch (JSONException unused) {
        }
    }

    /* access modifiers changed from: private */
    public String capacityTranslation(long j) {
        if (j < 1024) {
            try {
                return String.valueOf(j) + "MB";
            } catch (Exception unused) {
                return "MAX";
            }
        } else if (j < 1048576) {
            return String.valueOf(j / 1024) + ServerCompact.i;
        } else if (j >= 1073741824) {
            return "MAX";
        } else {
            return String.valueOf(j / 1048576) + "TB";
        }
    }

    /* access modifiers changed from: private */
    public int millisToDay(long j) {
        if (j > 0) {
            return ((int) (j / 86400000)) + 1;
        }
        return 0;
    }
}
