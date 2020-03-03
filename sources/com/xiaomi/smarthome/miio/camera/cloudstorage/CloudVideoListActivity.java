package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mi.global.shop.model.Tags;
import com.mijia.app.Event;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoDateListViewAdapter;
import com.xiaomi.smarthome.miio.camera.cloudstorage.adapter.PlayListAdapter;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoChildListData;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDate;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoUserStatus;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.DailyList;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.ICloudVideoCallback;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.StatsRecord2;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoUtils;
import com.xiaomi.smarthome.miio.camera.cloudstorage.views.RecyclerViewRefreshLayout;
import com.xiaomi.smarthome.miio.camera.cloudstorage.views.RecyclerViewRefreshLayoutEx;
import com.xiaomi.smarthome.stat.STAT;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CloudVideoListActivity extends CloudVideoBaseActivity implements View.OnClickListener {
    public static final String CURRENT_DATE_TS = "currentDateTS";
    public static final long DAY_END_MILLIS = 86399000;
    public static final long DAY_MILLIS = 86400000;
    public static final String DID = "did";
    public static final String DURATION = "duration";
    private static final int LOADING_AUTO_HIDE_MILLIS = 5000;
    public static final int MAX_DAYS = 29;
    public static final String MODEL = "model";
    public static final String SELECTED_ITEM_POS = "selectedItemPosition";
    public static final String START_TIME = "startTime";
    private static final String TAG = "CloudVideoListActivity";
    public static final long THIRTY_DAYS_MILLIS = 2592000000L;
    public static final String TITLE = "title";
    public static final int VIDEO_LIST_SPAN_COUNT = 3;
    /* access modifiers changed from: private */
    public String currentDateString = null;
    /* access modifiers changed from: private */
    public long currentDateTS = 0;
    private CloudVideoDateListView cvdlvDays;
    /* access modifiers changed from: private */
    public CloudVideoListView cvlvVideos;
    /* access modifiers changed from: private */
    public CloudVideoDateListViewAdapter dateListViewAdapter;
    /* access modifiers changed from: private */
    public int datesUpdateVar = 0;
    /* access modifiers changed from: private */
    public String did = null;
    private boolean isReadyOnly;
    /* access modifiers changed from: private */
    public boolean isShared;
    private boolean isV4;
    private ImageView ivCrown;
    private ImageView ivHeaderLeftBack;
    /* access modifiers changed from: private */
    public ImageView ivHeaderRightEdit;
    private ImageView ivHeaderRightSetting;
    private ImageView ivLoading;
    /* access modifiers changed from: private */
    public LinearLayout llBottomCtrl;
    /* access modifiers changed from: private */
    public String model = null;
    private String[] monthArray;
    /* access modifiers changed from: private */
    public PlayListAdapter playListAdapter;
    private RelativeLayout rlCloudVideo;
    private RelativeLayout rlTitleBar;
    /* access modifiers changed from: private */
    public RecyclerViewRefreshLayoutEx rvrlVideoList;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /* access modifiers changed from: private */
    public String title = null;
    /* access modifiers changed from: private */
    public TextView tvBlankHint;
    /* access modifiers changed from: private */
    public TextView tvCapacity;
    private TextView tvDelete;
    private TextView tvDownload;
    /* access modifiers changed from: private */
    public TextView tvDownloadHint;
    /* access modifiers changed from: private */
    public TextView tvInterval;
    /* access modifiers changed from: private */
    public TextView tvManage;
    /* access modifiers changed from: private */
    public TextView tvOrderName;
    private TextView tvTitleBarTitle;
    /* access modifiers changed from: private */
    public TextView tvValidity;

    static /* synthetic */ int access$2510(CloudVideoListActivity cloudVideoListActivity) {
        int i = cloudVideoListActivity.datesUpdateVar;
        cloudVideoListActivity.datesUpdateVar = i - 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Device b;
        super.onCreate(bundle);
        Event.a(Event.aW);
        setContentView(R.layout.cs_activity_video_list);
        this.did = getIntent().getStringExtra("did");
        this.title = getIntent().getStringExtra("title");
        if (TextUtils.isEmpty(this.did)) {
            finish();
        }
        if (!TextUtils.isEmpty(this.did) && (b = SmartHomeDeviceManager.a().b(this.did)) != null) {
            this.model = b.model;
            this.isReadyOnly = b.isSharedReadOnly();
            this.isShared = b.isShared();
            this.isV4 = "chuangmi.camera.ipc009".equals(this.model) || "chuangmi.camera.ipc019".equals(this.model);
        }
        this.monthArray = SHApplication.getAppContext().getResources().getStringArray(R.array.cs_month_array);
        initViews();
        STAT.e.f();
        Event.i();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getStatus();
        getCapacity();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        exitEditMode();
        this.tvDownloadHint.setVisibility(8);
    }

    private void initViews() {
        this.rlTitleBar = (RelativeLayout) findViewById(R.id.rlTitleBar);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.rlTitleBar.getLayoutParams());
        layoutParams.setMargins(0, TitleBarUtil.a(), 0, 0);
        this.rlTitleBar.setLayoutParams(layoutParams);
        this.rlTitleBar.bringToFront();
        this.tvTitleBarTitle = (TextView) findViewById(R.id.tvTitleBarTitle);
        this.tvTitleBarTitle.setVisibility(0);
        this.tvTitleBarTitle.setText(R.string.cs_my_service);
        this.ivHeaderLeftBack = (ImageView) findViewById(R.id.ivHeaderLeftBack);
        this.ivHeaderLeftBack.setOnClickListener(this);
        this.ivHeaderRightSetting = (ImageView) findViewById(R.id.ivHeaderRightSetting);
        this.ivHeaderRightSetting.setVisibility(8);
        this.ivHeaderRightSetting.setOnClickListener(this);
        this.ivHeaderRightEdit = (ImageView) findViewById(R.id.ivHeaderRightEdit);
        this.ivHeaderRightEdit.setVisibility(0);
        this.ivHeaderRightEdit.setOnClickListener(this);
        this.llBottomCtrl = (LinearLayout) findViewById(R.id.llBottomCtrl);
        this.tvDelete = (TextView) findViewById(R.id.tvDelete);
        this.tvDelete.setOnClickListener(this);
        this.tvDownload = (TextView) findViewById(R.id.tvDownload);
        this.tvDownload.setVisibility(0);
        this.tvDownload.setOnClickListener(this);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(this.tvDelete.getLayoutParams());
        layoutParams2.setMargins(DisplayUtils.a(20.0f), 0, 0, 0);
        this.tvDelete.setLayoutParams(layoutParams2);
        this.tvBlankHint = (TextView) findViewById(R.id.tvBlankHint);
        this.tvDownloadHint = (TextView) findViewById(R.id.tvDownloadHint);
        this.tvDownloadHint.setOnClickListener(this);
        this.rlCloudVideo = (RelativeLayout) findViewById(R.id.rl_cloud_video);
        this.ivCrown = (ImageView) findViewById(R.id.iv_crown);
        this.tvOrderName = (TextView) findViewById(R.id.tv_order_name);
        this.tvInterval = (TextView) findViewById(R.id.tv_interval);
        this.tvCapacity = (TextView) findViewById(R.id.tv_capacity);
        this.tvValidity = (TextView) findViewById(R.id.tv_validity);
        this.tvManage = (TextView) findViewById(R.id.tv_manage);
        this.tvManage.setOnClickListener(this);
        if (this.isShared) {
            this.tvManage.setVisibility(8);
        }
        this.tvManage.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    CloudVideoListActivity.this.tvManage.setAlpha(0.5f);
                    return false;
                } else if (motionEvent.getAction() != 1) {
                    return false;
                } else {
                    CloudVideoListActivity.this.tvManage.setAlpha(1.0f);
                    return false;
                }
            }
        });
        this.rvrlVideoList = (RecyclerViewRefreshLayoutEx) findViewById(R.id.rvrlVideoList);
        this.rvrlVideoList.setMode(3);
        this.rvrlVideoList.setOnPullRefreshListener(new RecyclerViewRefreshLayout.OnPullRefreshListener() {
            public void onPullDistance(int i) {
            }

            public void onPullEnable(boolean z) {
            }

            public void onRefresh() {
                CloudVideoListActivity.this.exitEditMode();
                CloudVideoDate cloudVideoDate = (CloudVideoDate) CloudVideoListActivity.this.dateListViewAdapter.getItem(CloudVideoListActivity.this.dateListViewAdapter.selectedItemPosition);
                CloudVideoListActivity.this.getPlayListLimit(cloudVideoDate, cloudVideoDate.timeStamp, CloudVideoListActivity.DAY_END_MILLIS + cloudVideoDate.timeStamp, true, true);
            }
        });
        this.rvrlVideoList.setFooterRefreshView(LayoutInflater.from(this).inflate(R.layout.list_load_more, (ViewGroup) null));
        this.rvrlVideoList.setOnPushLoadMoreListener(new RecyclerViewRefreshLayout.OnPushLoadMoreListener() {
            public void onPushDistance(int i) {
            }

            public void onPushEnable(boolean z) {
            }

            public void onLoadMore() {
                CloudVideoDate cloudVideoDate = (CloudVideoDate) CloudVideoListActivity.this.dateListViewAdapter.getItem(CloudVideoListActivity.this.dateListViewAdapter.selectedItemPosition);
                Object itemDataByPosition = CloudVideoListActivity.this.playListAdapter.getItemDataByPosition(CloudVideoListActivity.this.playListAdapter.getItemCount() - 1);
                if (itemDataByPosition instanceof CloudVideoChildListData) {
                    long j = cloudVideoDate.timeStamp;
                    long j2 = ((CloudVideoChildListData) itemDataByPosition).startTime;
                    LogUtil.a(CloudVideoListActivity.TAG, "onLoadMore beginTime:" + CloudVideoListActivity.this.sdf.format(Long.valueOf(j)) + " endTime:" + CloudVideoListActivity.this.sdf.format(Long.valueOf(j2)));
                    CloudVideoListActivity.this.getPlayListLimit(cloudVideoDate, j, j2, false, true);
                    return;
                }
                CloudVideoListActivity.this.rvrlVideoList.setLoadMore(false);
                if (CloudVideoListActivity.this.rvrlVideoList.isRefreshing()) {
                    CloudVideoListActivity.this.rvrlVideoList.setRefreshing(false);
                }
            }
        });
        this.rvrlVideoList.setRefreshing(false);
        this.rvrlVideoList.setLoadMore(false);
        initLoadingView();
        initListView();
        getVideoDatesSerial(true);
        if (this.isReadyOnly) {
            this.llBottomCtrl.setVisibility(8);
            this.ivHeaderRightEdit.setVisibility(8);
            this.playListAdapter.disableLongPress();
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
                    if (!CloudVideoListActivity.this.isFinishing() && cloudVideoUserStatus != null) {
                        LogUtil.a(CloudVideoListActivity.TAG, "onCloudVideoSuccess" + cloudVideoUserStatus.toString());
                        CloudVideoListActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(LanguageUtil.c(ServerCompact.c(CloudVideoListActivity.this.getApplicationContext())));
                                CloudVideoListActivity.this.tvOrderName.setText(cloudVideoUserStatus.packageType);
                                String string = CloudVideoListActivity.this.getString(R.string.cs_service_time);
                                String string2 = CloudVideoListActivity.this.getString(R.string.cs_validity);
                                long currentTimeMillis = System.currentTimeMillis() - cloudVideoUserStatus.startTime;
                                long currentTimeMillis2 = cloudVideoUserStatus.endTime - System.currentTimeMillis();
                                int access$700 = CloudVideoListActivity.this.millisToDay(currentTimeMillis2);
                                CloudVideoListActivity.this.tvInterval.setText(String.format(string, new Object[]{Integer.valueOf(CloudVideoListActivity.this.millisToDay(currentTimeMillis))}));
                                if (!cloudVideoUserStatus.vip) {
                                    String string3 = CloudVideoListActivity.this.getString(R.string.cs_expire);
                                    int indexOf = string3.indexOf("#start#");
                                    int indexOf2 = string3.indexOf("#end#") - "#start#".length();
                                    String replace = string3.replace("#start#", "").replace("#end#", "");
                                    AnonymousClass1 r4 = new ClickableSpan() {
                                        public void onClick(View view) {
                                            if (!CloudVideoListActivity.this.isShared) {
                                                CloudVideoNetUtils.getInstance().openCloudVideoBuyPage(CloudVideoListActivity.this, CloudVideoListActivity.this.did);
                                            }
                                        }

                                        public void updateDrawState(TextPaint textPaint) {
                                            super.updateDrawState(textPaint);
                                            textPaint.setUnderlineText(true);
                                            textPaint.setColor(CloudVideoListActivity.this.getResources().getColor(R.color.cloud_video_gold));
                                        }
                                    };
                                    SpannableString spannableString = new SpannableString(replace);
                                    spannableString.setSpan(r4, indexOf, indexOf2, 33);
                                    CloudVideoListActivity.this.tvValidity.setText(spannableString);
                                    CloudVideoListActivity.this.tvValidity.setMovementMethod(LinkMovementMethod.getInstance());
                                } else if (cloudVideoUserStatus.isRenew) {
                                    CloudVideoListActivity.this.tvValidity.setText(String.format(CloudVideoListActivity.this.getString(R.string.cs_validity_deduct_order), new Object[]{simpleDateFormat.format(new Date(cloudVideoUserStatus.endTime))}));
                                } else if (access$700 <= 7) {
                                    String format = String.format(string2, new Object[]{simpleDateFormat.format(new Date(cloudVideoUserStatus.endTime)), Integer.valueOf(CloudVideoListActivity.this.millisToDay(currentTimeMillis2))});
                                    int indexOf3 = format.indexOf("#start#");
                                    int indexOf4 = format.indexOf("#end#") - "#start#".length();
                                    String replace2 = format.replace("#start#", "").replace("#end#", "");
                                    ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(CloudVideoListActivity.this.getResources().getColor(R.color.cloud_video_gold));
                                    SpannableString spannableString2 = new SpannableString(replace2);
                                    spannableString2.setSpan(foregroundColorSpan, indexOf3, indexOf4, 33);
                                    CloudVideoListActivity.this.tvValidity.setText(spannableString2);
                                } else {
                                    CloudVideoListActivity.this.tvValidity.setText(String.format(CloudVideoListActivity.this.getString(R.string.cs_validity_2), new Object[]{simpleDateFormat.format(new Date(cloudVideoUserStatus.endTime))}));
                                }
                            }
                        });
                    }
                }

                public void onCloudVideoFailed(int i, String str) {
                    if (!CloudVideoListActivity.this.isFinishing()) {
                        CloudVideoListActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                            }
                        });
                        LogUtil.b(CloudVideoListActivity.TAG, "errorCode:" + i + " errorInfo:" + str);
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
                    if (!CloudVideoListActivity.this.isFinishing()) {
                        CloudVideoListActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                String access$1200 = CloudVideoListActivity.this.capacityTranslation(l.longValue());
                                String string = CloudVideoListActivity.this.getString(R.string.cs_capacity);
                                CloudVideoListActivity.this.tvCapacity.setText(String.format(string, new Object[]{access$1200}));
                            }
                        });
                    }
                }

                public void onCloudVideoFailed(int i, String str) {
                    if (!CloudVideoListActivity.this.isFinishing()) {
                        LogUtil.b(CloudVideoListActivity.TAG, "errorCode:" + i + " errorInfo:" + str);
                    }
                }
            });
        } catch (JSONException unused) {
        }
    }

    private void initLoadingView() {
        this.ivLoading = (ImageView) findViewById(R.id.ivLoading);
    }

    private void showVideoLoading(boolean z) {
        if (this.ivLoading != null) {
            this.ivLoading.setVisibility(0);
            Drawable drawable = this.ivLoading.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).start();
            }
            if (z) {
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        CloudVideoListActivity.this.hideVideoLoading();
                    }
                }, 5000);
            }
        }
    }

    /* access modifiers changed from: private */
    public void hideVideoLoading() {
        if (this.ivLoading != null && !isFinishing()) {
            Drawable drawable = this.ivLoading.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).stop();
            }
            this.ivLoading.setVisibility(8);
        }
    }

    private void initListView() {
        this.cvlvVideos = (CloudVideoListView) findViewById(R.id.cvlvVideos);
        this.playListAdapter = new PlayListAdapter();
        this.playListAdapter.isDownloadEnabled = true;
        this.cvlvVideos.setLayoutManager(new GridLayoutManager((Context) this, 3, 1, false));
        this.cvlvVideos.setAdapter(this.playListAdapter);
        this.playListAdapter.modeChangedListener = new PlayListAdapter.ModeChangedListener() {
            public void onEditModeChanged(boolean z) {
                if (z) {
                    CloudVideoListActivity.this.llBottomCtrl.setVisibility(0);
                } else {
                    CloudVideoListActivity.this.llBottomCtrl.setVisibility(8);
                }
                CloudVideoListActivity.this.getWindow().getDecorView().requestLayout();
            }
        };
        this.playListAdapter.iItemClickListener = new PlayListAdapter.IItemClickListener() {
            public void onItemClick(View view, int i, Object obj) {
                Object itemDataByPosition = CloudVideoListActivity.this.playListAdapter.getItemDataByPosition(i);
                if (itemDataByPosition instanceof CloudVideoChildListData) {
                    CloudVideoChildListData cloudVideoChildListData = (CloudVideoChildListData) itemDataByPosition;
                    Intent intent = new Intent(CloudVideoListActivity.this.getContext(), CloudVideoExoPlayerActivity.class);
                    if (!TextUtils.isEmpty(CloudVideoListActivity.this.title)) {
                        intent.putExtra("title", CloudVideoListActivity.this.title);
                    }
                    if (!TextUtils.isEmpty(CloudVideoListActivity.this.model)) {
                        intent.putExtra("model", CloudVideoListActivity.this.model);
                    }
                    intent.putExtra("did", CloudVideoListActivity.this.did);
                    intent.putExtra("fileId", cloudVideoChildListData.fileId);
                    intent.putExtra(CloudVideoListActivity.SELECTED_ITEM_POS, CloudVideoListActivity.this.dateListViewAdapter.selectedItemPosition);
                    intent.putExtra("startTime", cloudVideoChildListData.startTime);
                    intent.putExtra("duration", cloudVideoChildListData.duration);
                    intent.putExtra(CloudVideoListActivity.CURRENT_DATE_TS, CloudVideoListActivity.this.currentDateTS);
                    CloudVideoListActivity.this.startActivity(intent);
                }
            }
        };
        this.playListAdapter.iItemLongClickListener = new PlayListAdapter.IItemLongClickListener() {
            public void onItemLongClick(View view, int i) {
                if (!CloudVideoListActivity.this.playListAdapter.getEditMode()) {
                    CloudVideoListActivity.this.enterEditMode();
                    Object itemDataByPosition = CloudVideoListActivity.this.playListAdapter.getItemDataByPosition(i);
                    if (itemDataByPosition instanceof CloudVideoChildListData) {
                        ((CloudVideoChildListData) itemDataByPosition).isSelected = true;
                        CloudVideoListActivity.this.playListAdapter.notifyItemChanged(i, "itemChanged");
                    }
                    CloudVideoListActivity.this.ivHeaderRightEdit.setImageResource(R.drawable.cs_icon_edit_select_all);
                }
            }
        };
        ArrayList arrayList = new ArrayList();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        long timeInMillis = instance.getTimeInMillis();
        this.currentDateString = simpleDateFormat.format(Long.valueOf(timeInMillis));
        this.currentDateTS = timeInMillis;
        LogUtil.a(TAG, "currentDateString:" + this.currentDateString);
        for (int i = 29; i >= 0; i--) {
            CloudVideoDate cloudVideoDate = new CloudVideoDate();
            long j = timeInMillis - (((long) i) * 86400000);
            String format = simpleDateFormat.format(Long.valueOf(j));
            cloudVideoDate.day = format.split("-")[2];
            cloudVideoDate.month = format.split("-")[1];
            cloudVideoDate.monthChinaPattern = this.monthArray[Integer.valueOf(cloudVideoDate.month).intValue() - 1];
            cloudVideoDate.year = format.split("-")[0];
            cloudVideoDate.timeStamp = j;
            arrayList.add(cloudVideoDate);
        }
        this.cvdlvDays = (CloudVideoDateListView) findViewById(R.id.cvdlvDays);
        this.dateListViewAdapter = new CloudVideoDateListViewAdapter(arrayList);
        this.cvdlvDays.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.cvdlvDays.setAdapter(this.dateListViewAdapter);
        this.cvdlvDays.scrollToPosition(this.dateListViewAdapter.getItemCount() - 1);
        this.dateListViewAdapter.selectedItemPosition = this.dateListViewAdapter.getItemCount() - 1;
        this.dateListViewAdapter.iItemClickListener = new CloudVideoDateListViewAdapter.IItemClickListener<CloudVideoDate>() {
            public void onItemClick(View view, int i, CloudVideoDate cloudVideoDate) {
                CloudVideoListActivity.this.exitEditMode();
                if (cloudVideoDate != null) {
                    String unused = CloudVideoListActivity.this.currentDateString = simpleDateFormat.format(Long.valueOf(cloudVideoDate.timeStamp));
                    long unused2 = CloudVideoListActivity.this.currentDateTS = cloudVideoDate.timeStamp;
                }
                if (cloudVideoDate.isHasVideo) {
                    CloudVideoListActivity.this.tvBlankHint.setVisibility(8);
                    CloudVideoListActivity.this.getPlayListLimit(cloudVideoDate, cloudVideoDate.timeStamp, cloudVideoDate.timeStamp + CloudVideoListActivity.DAY_END_MILLIS, true, true);
                    return;
                }
                CloudVideoListActivity.this.hideVideoLoading();
                if (CloudVideoListActivity.this.rvrlVideoList != null) {
                    CloudVideoListActivity.this.rvrlVideoList.setLoadMore(false);
                    if (CloudVideoListActivity.this.rvrlVideoList.isRefreshing()) {
                        CloudVideoListActivity.this.rvrlVideoList.setRefreshing(false);
                    }
                }
                CloudVideoListActivity.this.tvBlankHint.setVisibility(0);
                CloudVideoListActivity.this.playListAdapter.listData.clear();
                CloudVideoListActivity.this.playListAdapter.notifyDataSetChanged();
            }
        };
    }

    public void onBackPressed() {
        this.ivHeaderRightEdit.setImageResource(R.drawable.cs_icon_edit_selector);
        if (this.playListAdapter == null || !this.playListAdapter.getEditMode()) {
            super.onBackPressed();
            return;
        }
        this.playListAdapter.setEditMode(false);
        this.playListAdapter.notifyDataSetChanged();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivHeaderLeftBack:
                onBackPressed();
                return;
            case R.id.ivHeaderRightEdit:
                if (this.playListAdapter == null) {
                    return;
                }
                if (this.playListAdapter.getEditMode()) {
                    if (this.playListAdapter.getSelectedItemCount() >= this.playListAdapter.getChildItemCount()) {
                        this.ivHeaderRightEdit.setImageResource(R.drawable.cs_icon_edit_select_all);
                        this.playListAdapter.unSelectAllItem();
                        return;
                    }
                    this.ivHeaderRightEdit.setImageResource(R.drawable.cs_icon_edit_deselect_all);
                    this.playListAdapter.selectAllItem();
                    return;
                } else if (this.playListAdapter == null || this.playListAdapter.getChildItemCount() <= 0) {
                    ToastUtil.a((int) R.string.cs_no_video_today);
                    return;
                } else {
                    enterEditMode();
                    if (this.playListAdapter.getEditMode()) {
                        this.ivHeaderRightEdit.setImageResource(R.drawable.cs_icon_edit_select_all);
                        return;
                    }
                    return;
                }
            case R.id.tvDelete:
                if (this.playListAdapter == null || this.playListAdapter.getSelectedItemCount() <= 0) {
                    ToastUtil.a((int) R.string.cs_select_video);
                    return;
                } else if (this.playListAdapter.getSelectedItems().size() > 50) {
                    ToastUtil.a((int) R.string.cs_max_delete_50);
                    return;
                } else {
                    videoDeleteCheck(this.playListAdapter.getSelectedItems());
                    return;
                }
            case R.id.tvDownload:
                int selectedItemCount = this.playListAdapter.getSelectedItemCount();
                if (this.playListAdapter != null && selectedItemCount > 0 && selectedItemCount <= 5) {
                    downloadHint();
                    return;
                } else if (this.playListAdapter == null || selectedItemCount <= 5) {
                    ToastUtil.a((int) R.string.cs_select_video);
                    return;
                } else {
                    ToastUtil.a((int) R.string.cs_max_download_5);
                    return;
                }
            case R.id.tvDownloadHint:
                Intent intent = new Intent(this, CloudVideoDownloadActivity.class);
                intent.putExtra("did", this.did);
                if (!TextUtils.isEmpty(this.title)) {
                    intent.putExtra("title", this.title);
                }
                Device b = SmartHomeDeviceManager.a().b(this.did);
                if (b != null) {
                    intent.putExtra("uid", b.userId);
                    startActivity(intent);
                    return;
                }
                return;
            case R.id.tv_manage:
                STAT.d.as();
                CloudVideoNetUtils.getInstance().openCloudVideoManagePage(this, this.did);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void getPlayListLimit(CloudVideoDate cloudVideoDate, long j, long j2, final boolean z, boolean z2) {
        if (cloudVideoDate.isHasVideo) {
            try {
                LogUtil.a(TAG, "getPlayListLimit:" + cloudVideoDate.year + "-" + cloudVideoDate.month + "-" + cloudVideoDate.day);
                long gMT8TimeZone = CloudVideoNetUtils.getInstance().toGMT8TimeZone(j);
                long gMT8TimeZone2 = CloudVideoNetUtils.getInstance().toGMT8TimeZone(j2);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("did", this.did);
                jSONObject.put("region", Locale.getDefault().getCountry());
                jSONObject.put("language", Locale.getDefault().getLanguage());
                jSONObject.put(Tags.Coupon.BEGIN_TIME, gMT8TimeZone);
                jSONObject.put("endTime", gMT8TimeZone2);
                jSONObject.put("limit", 40);
                if (z2) {
                    showVideoLoading(false);
                }
                this.cvlvVideos.setEnabled(false);
                CloudVideoNetUtils.getInstance().getVideoDailyListLimit(getContext(), jSONObject.toString(), new ICloudVideoCallback<List<DailyList>>() {
                    public void onCloudVideoSuccess(final List<DailyList> list, Object obj) {
                        if (!CloudVideoListActivity.this.isFinishing()) {
                            CloudVideoListActivity.this.cvlvVideos.setEnabled(true);
                            long localTimeZone = CloudVideoNetUtils.getInstance().toLocalTimeZone(((Long) obj).longValue());
                            long access$1800 = (CloudVideoListActivity.this.currentDateTS + 86400000) - 1000;
                            CloudVideoListActivity.this.rvrlVideoList.setLoadMore(false);
                            if (CloudVideoListActivity.this.rvrlVideoList.isRefreshing()) {
                                CloudVideoListActivity.this.rvrlVideoList.setRefreshing(false);
                            }
                            if (z) {
                                CloudVideoListActivity.this.playListAdapter.clearAllData();
                            }
                            long dayTS0 = CloudVideoUtils.getDayTS0(localTimeZone);
                            long dayTS02 = CloudVideoUtils.getDayTS0(access$1800);
                            if (localTimeZone <= 0 || dayTS0 != dayTS02) {
                                CloudVideoListActivity.this.hideVideoLoading();
                            } else if (list == null || list.isEmpty()) {
                                CloudVideoListActivity.this.hideVideoLoading();
                                if (CloudVideoListActivity.this.playListAdapter != null) {
                                    CloudVideoListActivity.this.playListAdapter.notifyDataSetChanged();
                                }
                                CloudVideoListActivity.this.isTodayHasVideo();
                            } else {
                                final int allItemCount = CloudVideoListActivity.this.playListAdapter.getAllItemCount();
                                new Thread(new Runnable() {
                                    public void run() {
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
                                        for (DailyList dailyList : list) {
                                            CloudVideoChildListData cloudVideoChildListData = new CloudVideoChildListData();
                                            cloudVideoChildListData.duration = dailyList.duration;
                                            cloudVideoChildListData.imgStoreId = dailyList.imgStoreId;
                                            cloudVideoChildListData.isPeople = dailyList.isHuman;
                                            cloudVideoChildListData.fileId = dailyList.fileId;
                                            cloudVideoChildListData.imgStoreUrl = CloudVideoNetUtils.getInstance().getSnapshotUrl(CloudVideoListActivity.this.did, dailyList.fileId, dailyList.imgStoreId);
                                            cloudVideoChildListData.startTime = dailyList.createTime;
                                            CloudVideoListActivity.this.playListAdapter.append(Integer.valueOf(simpleDateFormat.format(Long.valueOf(cloudVideoChildListData.startTime))).intValue(), cloudVideoChildListData);
                                        }
                                        CloudVideoListActivity.this.runOnUiThread(new Runnable() {
                                            public void run() {
                                                CloudVideoListActivity.this.hideVideoLoading();
                                                CloudVideoListActivity.this.isTodayHasVideo();
                                                if (CloudVideoListActivity.this.playListAdapter.getAllItemCount() == allItemCount) {
                                                    ToastUtil.a((int) R.string.cs_alarm_none_data);
                                                }
                                                CloudVideoListActivity.this.playListAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }).start();
                            }
                        }
                    }

                    public void onCloudVideoFailed(int i, String str) {
                        if (!CloudVideoListActivity.this.isFinishing()) {
                            if (z && -90002 == i && CloudVideoListActivity.this.playListAdapter != null) {
                                CloudVideoListActivity.this.playListAdapter.clearAllData();
                            }
                            if (CloudVideoListActivity.this.playListAdapter != null) {
                                CloudVideoListActivity.this.playListAdapter.notifyDataSetChanged();
                            }
                            CloudVideoListActivity.this.cvlvVideos.setEnabled(true);
                            CloudVideoListActivity.this.rvrlVideoList.setLoadMore(false);
                            if (CloudVideoListActivity.this.rvrlVideoList.isRefreshing()) {
                                CloudVideoListActivity.this.rvrlVideoList.setRefreshing(false);
                            }
                            CloudVideoListActivity.this.hideVideoLoading();
                            CloudVideoListActivity.this.isTodayHasVideo();
                            LogUtil.b(CloudVideoListActivity.TAG, "errorCode:" + i + " errorInfo:" + str);
                        }
                    }
                });
            } catch (JSONException unused) {
                if (!isFinishing()) {
                    hideVideoLoading();
                    this.rvrlVideoList.setLoadMore(false);
                    if (this.rvrlVideoList.isRefreshing()) {
                        this.rvrlVideoList.setRefreshing(false);
                    }
                }
            }
        } else {
            if (z) {
                this.playListAdapter.listData.clear();
                if (!this.playListAdapter.getEditMode()) {
                    this.playListAdapter.notifyDataSetChanged();
                }
            }
            this.rvrlVideoList.setLoadMore(false);
            if (this.rvrlVideoList.isRefreshing()) {
                this.rvrlVideoList.setRefreshing(false);
            }
        }
    }

    /* access modifiers changed from: private */
    public void getVideoDatesSerial(final boolean z) {
        int i;
        this.datesUpdateVar = 0;
        int i2 = 29;
        for (int i3 = 0; i3 < 5; i3++) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("did", this.did);
                Locale I = CoreApi.a().I();
                if (I != null) {
                    jSONObject.put("region", I.getCountry());
                } else {
                    jSONObject.put("region", Locale.getDefault().getCountry());
                }
                JSONArray jSONArray = new JSONArray();
                i = i2;
                int i4 = 0;
                while (i4 < 6) {
                    try {
                        JSONObject jSONObject2 = new JSONObject();
                        CloudVideoDate cloudVideoDate = this.dateListViewAdapter.cloudVideoDates.get(i);
                        long j = cloudVideoDate.timeStamp;
                        long j2 = cloudVideoDate.timeStamp + DAY_END_MILLIS;
                        long gMT8TimeZone = CloudVideoNetUtils.getInstance().toGMT8TimeZone(j);
                        long gMT8TimeZone2 = CloudVideoNetUtils.getInstance().toGMT8TimeZone(j2);
                        jSONObject2.put(Tags.Coupon.BEGIN_TIME, gMT8TimeZone);
                        jSONObject2.put("endTime", gMT8TimeZone2);
                        jSONArray.put(jSONObject2);
                        i--;
                        i4++;
                    } catch (JSONException e) {
                        e = e;
                        hideVideoLoading();
                        LogUtil.b(TAG, "exception:" + e.toString());
                        i2 = i;
                    }
                }
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("intervals", jSONArray);
                jSONObject.put("intervals", jSONObject3);
                if (!TextUtils.isEmpty(this.did)) {
                    showVideoLoading(false);
                    this.datesUpdateVar++;
                    CloudVideoNetUtils.getInstance().getVideoDatesSerial(this, jSONObject.toString(), new ICloudVideoCallback<List<StatsRecord2>>() {
                        public void onCloudVideoSuccess(List<StatsRecord2> list, Object obj) {
                            if (!CloudVideoListActivity.this.isFinishing()) {
                                CloudVideoListActivity.access$2510(CloudVideoListActivity.this);
                                CloudVideoListActivity.this.hideVideoLoading();
                                for (StatsRecord2 next : list) {
                                    for (CloudVideoDate next2 : CloudVideoListActivity.this.dateListViewAdapter.cloudVideoDates) {
                                        if (CloudVideoNetUtils.getInstance().toLocalTimeZone(next.timeStamp) == next2.timeStamp + CloudVideoListActivity.DAY_END_MILLIS) {
                                            if (next.isExist) {
                                                next2.isHasVideo = true;
                                            } else {
                                                next2.isHasVideo = false;
                                            }
                                            next2.lastUpdateTS = System.currentTimeMillis();
                                        }
                                    }
                                }
                                CloudVideoListActivity.this.dateListViewAdapter.notifyDataSetChanged();
                                CloudVideoListActivity.this.isTodayHasVideo();
                                if (((CloudVideoDate) CloudVideoListActivity.this.dateListViewAdapter.getItem(CloudVideoListActivity.this.dateListViewAdapter.selectedItemPosition)).isHasVideo) {
                                    CloudVideoListActivity.this.tvBlankHint.setVisibility(8);
                                    if (CloudVideoListActivity.this.datesUpdateVar == 0 && z && CloudVideoListActivity.this.dateListViewAdapter != null && CloudVideoListActivity.this.dateListViewAdapter.getItemCount() > 0) {
                                        CloudVideoDate cloudVideoDate = (CloudVideoDate) CloudVideoListActivity.this.dateListViewAdapter.getItem(CloudVideoListActivity.this.dateListViewAdapter.selectedItemPosition >= 0 ? CloudVideoListActivity.this.dateListViewAdapter.selectedItemPosition : CloudVideoListActivity.this.dateListViewAdapter.getItemCount() - 1);
                                        CloudVideoListActivity.this.getPlayListLimit(cloudVideoDate, cloudVideoDate.timeStamp, cloudVideoDate.timeStamp + CloudVideoListActivity.DAY_END_MILLIS, true, true);
                                        return;
                                    }
                                    return;
                                }
                                CloudVideoListActivity.this.hideVideoLoading();
                                if (CloudVideoListActivity.this.rvrlVideoList != null) {
                                    CloudVideoListActivity.this.rvrlVideoList.setLoadMore(false);
                                    if (CloudVideoListActivity.this.rvrlVideoList.isRefreshing()) {
                                        CloudVideoListActivity.this.rvrlVideoList.setRefreshing(false);
                                    }
                                }
                                CloudVideoListActivity.this.tvBlankHint.setVisibility(0);
                                CloudVideoListActivity.this.playListAdapter.listData.clear();
                                CloudVideoListActivity.this.playListAdapter.notifyDataSetChanged();
                            }
                        }

                        public void onCloudVideoFailed(int i, String str) {
                            if (!CloudVideoListActivity.this.isFinishing()) {
                                CloudVideoListActivity.this.hideVideoLoading();
                                CloudVideoListActivity.this.isTodayHasVideo();
                                CloudVideoListActivity.access$2510(CloudVideoListActivity.this);
                                if (CloudVideoListActivity.this.datesUpdateVar == 0 && z && CloudVideoListActivity.this.dateListViewAdapter != null && CloudVideoListActivity.this.dateListViewAdapter.getItemCount() > 0) {
                                    CloudVideoDate cloudVideoDate = (CloudVideoDate) CloudVideoListActivity.this.dateListViewAdapter.getItem(CloudVideoListActivity.this.dateListViewAdapter.selectedItemPosition >= 0 ? CloudVideoListActivity.this.dateListViewAdapter.selectedItemPosition : CloudVideoListActivity.this.dateListViewAdapter.getItemCount() - 1);
                                    CloudVideoListActivity.this.getPlayListLimit(cloudVideoDate, cloudVideoDate.timeStamp, CloudVideoListActivity.DAY_END_MILLIS + cloudVideoDate.timeStamp, true, true);
                                }
                                LogUtil.b(CloudVideoListActivity.TAG, "errorCode:" + i + ":" + str);
                            }
                        }
                    });
                } else {
                    ToastUtil.a((int) R.string.cs_device_info_fail);
                }
            } catch (JSONException e2) {
                e = e2;
                i = i2;
                hideVideoLoading();
                LogUtil.b(TAG, "exception:" + e.toString());
                i2 = i;
            }
            i2 = i;
        }
    }

    /* access modifiers changed from: private */
    public void deleteVideos(List<CloudVideoChildListData> list) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.did);
            jSONObject.put("model", this.model);
            JSONArray jSONArray = new JSONArray();
            for (CloudVideoChildListData cloudVideoChildListData : list) {
                jSONArray.put(cloudVideoChildListData.fileId);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("fileIds", jSONArray);
            jSONObject.put("fileIds", jSONObject2);
            showVideoLoading(true);
            CloudVideoNetUtils.getInstance().deleteFiles(getContext(), jSONObject.toString(), new ICloudVideoCallback<String>() {
                public void onCloudVideoSuccess(String str, Object obj) {
                    if (!CloudVideoListActivity.this.isFinishing()) {
                        CloudVideoListActivity.this.hideVideoLoading();
                        CloudVideoListActivity.this.exitEditMode();
                        CloudVideoListActivity.this.getVideoDatesSerial(false);
                        CloudVideoDate cloudVideoDate = (CloudVideoDate) CloudVideoListActivity.this.dateListViewAdapter.getItem(CloudVideoListActivity.this.dateListViewAdapter.selectedItemPosition);
                        CloudVideoListActivity.this.getPlayListLimit(cloudVideoDate, cloudVideoDate.timeStamp, CloudVideoListActivity.DAY_END_MILLIS + cloudVideoDate.timeStamp, true, true);
                        CloudVideoListActivity.this.setResult(-1);
                    }
                }

                public void onCloudVideoFailed(int i, String str) {
                    if (!CloudVideoListActivity.this.isFinishing()) {
                        CloudVideoListActivity.this.hideVideoLoading();
                        ToastUtil.a((Context) CloudVideoListActivity.this, (int) R.string.delete_failed);
                        LogUtil.b(CloudVideoListActivity.TAG, "errorCode:" + i + " errorInfo:" + str);
                        CloudVideoListActivity.this.exitEditMode();
                        CloudVideoListActivity.this.getVideoDatesSerial(false);
                    }
                }
            });
        } catch (JSONException unused) {
            hideVideoLoading();
        }
    }

    /* access modifiers changed from: private */
    public void exitEditMode() {
        this.ivHeaderRightEdit.setImageResource(R.drawable.cs_icon_edit_selector);
        if (this.playListAdapter != null && this.playListAdapter.getEditMode()) {
            this.playListAdapter.setEditMode(false);
            this.playListAdapter.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: private */
    public void enterEditMode() {
        if (this.rvrlVideoList != null) {
            this.rvrlVideoList.setRefreshing(false);
            this.rvrlVideoList.setLoadMore(false);
        }
        if (this.playListAdapter != null && !this.playListAdapter.getEditMode()) {
            this.playListAdapter.setEditMode(true);
            this.playListAdapter.notifyDataSetChanged();
        }
    }

    private void videoDeleteCheck(final List<CloudVideoChildListData> list) {
        new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.cs_delete_video)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CloudVideoListActivity.this.deleteVideos(list);
            }
        }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                CloudVideoListActivity.this.exitEditMode();
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CloudVideoListActivity.this.exitEditMode();
            }
        }).d();
    }

    /* access modifiers changed from: private */
    public void isTodayHasVideo() {
        if (this.playListAdapter == null || this.playListAdapter.listData == null || !this.playListAdapter.isContainVideoData()) {
            this.tvBlankHint.setVisibility(0);
        } else {
            this.tvBlankHint.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void addDownloadList(List<CloudVideoChildListData> list) {
        Device b;
        if (!TextUtils.isEmpty(this.did) && list != null && (b = SmartHomeDeviceManager.a().b(this.did)) != null) {
            ArrayList arrayList = new ArrayList();
            for (CloudVideoChildListData next : list) {
                CloudVideoDownloadInfo cloudVideoDownloadInfo = new CloudVideoDownloadInfo();
                cloudVideoDownloadInfo.uid = b.userId;
                cloudVideoDownloadInfo.did = this.did;
                if (!TextUtils.isEmpty(cloudVideoDownloadInfo.uid) && !TextUtils.isEmpty(cloudVideoDownloadInfo.did)) {
                    cloudVideoDownloadInfo.videoUrl = CloudVideoNetUtils.getInstance().getPlayFileUrl(this.did, next.fileId, "H265");
                    cloudVideoDownloadInfo.mp4FilePath = null;
                    cloudVideoDownloadInfo.m3u8FilePath = null;
                    cloudVideoDownloadInfo.fileId = next.fileId;
                    if (!TextUtils.isEmpty(this.title)) {
                        cloudVideoDownloadInfo.title = this.title;
                    }
                    cloudVideoDownloadInfo.status = 4;
                    cloudVideoDownloadInfo.createTime = System.currentTimeMillis();
                    cloudVideoDownloadInfo.startTime = next.startTime;
                    cloudVideoDownloadInfo.endTime = next.startTime + next.duration;
                    cloudVideoDownloadInfo.duration = next.duration;
                    cloudVideoDownloadInfo.createTimePretty = this.sdf.format(Long.valueOf(cloudVideoDownloadInfo.createTime));
                    cloudVideoDownloadInfo.startTimePretty = this.sdf.format(Long.valueOf(cloudVideoDownloadInfo.startTime));
                    cloudVideoDownloadInfo.endTimePretty = this.sdf.format(Long.valueOf(cloudVideoDownloadInfo.endTime));
                    cloudVideoDownloadInfo.size = 0;
                    cloudVideoDownloadInfo.progress = 0;
                    arrayList.add(cloudVideoDownloadInfo);
                }
            }
            CloudVideoDownloadManager.getInstance().insertRecords(arrayList);
            CloudVideoDownloadManager.getInstance().pullDownloadFromList(getContext(), b.userId, this.did);
        }
    }

    /* access modifiers changed from: private */
    public void showDownloadActivityHint() {
        this.tvDownloadHint.setVisibility(0);
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (CloudVideoListActivity.this.tvDownloadHint != null) {
                    CloudVideoListActivity.this.tvDownloadHint.setVisibility(8);
                }
            }
        }, 3000);
    }

    private void downloadHint() {
        if (CloudVideoNetUtils.getInstance().isWifiConnected(this)) {
            addDownloadList(this.playListAdapter.getSelectedItems());
            showDownloadActivityHint();
            exitEditMode();
            return;
        }
        new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.cs_non_wifi_environment)).a((int) R.string.cs_go_on, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CloudVideoListActivity.this.addDownloadList(CloudVideoListActivity.this.playListAdapter.getSelectedItems());
                CloudVideoListActivity.this.showDownloadActivityHint();
                CloudVideoListActivity.this.exitEditMode();
            }
        }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
            }
        }).b((int) R.string.cs_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).d();
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

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Event.j();
    }
}
