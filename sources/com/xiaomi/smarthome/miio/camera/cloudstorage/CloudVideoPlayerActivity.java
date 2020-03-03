package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.mi.global.shop.model.Tags;
import com.tencent.smtt.sdk.TbsReaderView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoDateListViewAdapter;
import com.xiaomi.smarthome.miio.camera.cloudstorage.adapter.PlayListAdapter;
import com.xiaomi.smarthome.miio.camera.cloudstorage.ijkpackage.IjkVideoView;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoChildListData;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDate;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.DailyList;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.ICloudVideoCallback;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.StatsRecord;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.StatsRecord2;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoFileUtils;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import com.xiaomi.smarthome.miio.camera.cloudstorage.views.RecyclerViewRefreshLayout;
import com.xiaomi.smarthome.miio.camera.cloudstorage.views.RecyclerViewRefreshLayoutEx;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.share.ShareObject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nonnull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class CloudVideoPlayerActivity extends CloudVideoBaseActivity implements View.OnClickListener, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnErrorListener, IMediaPlayer.OnInfoListener, IMediaPlayer.OnPreparedListener {
    public static final String FILE_ID = "fileId";
    private static final int LOADING_AUTO_HIDE = 5000;
    private static final String TAG = "CloudVideoPlayerActivity";
    /* access modifiers changed from: private */
    public CheckBox cbIsMute;
    /* access modifiers changed from: private */
    public CheckBox cbTogglePlay;
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
    public String did;
    /* access modifiers changed from: private */
    public long duration;
    /* access modifiers changed from: private */
    public String fileId;
    /* access modifiers changed from: private */
    public Runnable getCurrentPosRunnable = new Runnable() {
        public void run() {
            if (CloudVideoPlayerActivity.this.ijkVideoView != null) {
                final int currentPosition = CloudVideoPlayerActivity.this.ijkVideoView.getCurrentPosition();
                if (currentPosition > 0 && CloudVideoPlayerActivity.this.ijkVideoView.isPlaying()) {
                    int unused = CloudVideoPlayerActivity.this.storedPlayPosition = currentPosition;
                }
                CloudVideoPlayerActivity.this.setProgressTime(currentPosition);
                CloudVideoPlayerActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        int duration = CloudVideoPlayerActivity.this.ijkVideoView.getDuration() / 1000;
                        if (currentPosition / 1000 >= duration) {
                            TextView access$3500 = CloudVideoPlayerActivity.this.tvVideoStart;
                            access$3500.setText("" + CloudVideoPlayerActivity.this.intToDate((long) duration));
                            return;
                        }
                        TextView access$35002 = CloudVideoPlayerActivity.this.tvVideoStart;
                        access$35002.setText("" + CloudVideoPlayerActivity.this.intToDate((long) (currentPosition / 1000)));
                    }
                });
                CloudVideoPlayerActivity.this.mHandler.postDelayed(CloudVideoPlayerActivity.this.getCurrentPosRunnable, 500);
            }
        }
    };
    private IjkVideoView.IjkVideoInfo ijkVideoInfo = new IjkVideoView.IjkVideoInfo() {
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, final int i, final int i2, int i3, int i4) {
            if (CloudVideoPlayerActivity.this.ijkVideoView != null) {
                CloudVideoPlayerActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        if (CloudVideoPlayerActivity.this.getRequestedOrientation() == 1) {
                            int b = DisplayUtils.b(CloudVideoPlayerActivity.this.getContext());
                            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(CloudVideoPlayerActivity.this.ijkVideoView.getLayoutParams());
                            layoutParams.height = (i2 * b) / i;
                            layoutParams.width = b;
                            layoutParams.addRule(3, R.id.rlTitleBar);
                            CloudVideoPlayerActivity.this.ijkVideoView.setLayoutParams(layoutParams);
                            CloudVideoPlayerActivity.this.ijkVideoView.requestLayout();
                        }
                    }
                });
            }
        }

        public void onRenderViewClicked(View view) {
            CloudVideoPlayerActivity.this.toggleVideoCtrlViewTranslation();
        }

        public void onSurfaceChanged(int i, int i2) {
            CloudVideoPlayerActivity.this.ivVideoViewCover.bringToFront();
            CloudVideoPlayerActivity.this.rlTopRightCtrl.bringToFront();
            CloudVideoPlayerActivity.this.rlVideoViewBottomCtrl.bringToFront();
            CloudVideoPlayerActivity.this.ivVideoLoading.bringToFront();
        }

        public void onSurfaceCreated(int i, int i2) {
            CloudVideoPlayerActivity.this.ivVideoViewCover.bringToFront();
            CloudVideoPlayerActivity.this.rlTopRightCtrl.bringToFront();
            CloudVideoPlayerActivity.this.rlVideoViewBottomCtrl.bringToFront();
            CloudVideoPlayerActivity.this.ivVideoLoading.bringToFront();
        }

        public void onStartPlay() {
            if (CloudVideoPlayerActivity.this.mHandler != null) {
                CloudVideoPlayerActivity.this.mHandler.removeCallbacks(CloudVideoPlayerActivity.this.getCurrentPosRunnable);
                CloudVideoPlayerActivity.this.mHandler.post(CloudVideoPlayerActivity.this.getCurrentPosRunnable);
            }
        }

        public void onPausePlay() {
            if (CloudVideoPlayerActivity.this.mHandler != null) {
                CloudVideoPlayerActivity.this.mHandler.removeCallbacks(CloudVideoPlayerActivity.this.getCurrentPosRunnable);
            }
        }

        public void onStopPlayback() {
            if (CloudVideoPlayerActivity.this.mHandler != null) {
                CloudVideoPlayerActivity.this.mHandler.removeCallbacks(CloudVideoPlayerActivity.this.getCurrentPosRunnable);
            }
        }
    };
    /* access modifiers changed from: private */
    public IjkVideoView ijkVideoView;
    private boolean isShared;
    private boolean isV4;
    private ImageView ivDataLoading;
    private ImageView ivDeleteCurrentVideo;
    private ImageView ivFullScreen;
    private ImageView ivHeaderLeftBack;
    private ImageView ivHeaderRightSetting;
    /* access modifiers changed from: private */
    public ImageView ivImage;
    private ImageView ivSnapshot;
    private ImageView ivVideoDownload;
    /* access modifiers changed from: private */
    public ImageView ivVideoLoading;
    /* access modifiers changed from: private */
    public ImageView ivVideoViewCover;
    /* access modifiers changed from: private */
    public String model;
    private String[] monthArray;
    /* access modifiers changed from: private */
    public PlayListAdapter playListAdapter;
    private RelativeLayout rlTitleBar;
    /* access modifiers changed from: private */
    public RelativeLayout rlTopRightCtrl;
    /* access modifiers changed from: private */
    public RelativeLayout rlVideoViewBottomCtrl;
    /* access modifiers changed from: private */
    public RecyclerViewRefreshLayoutEx rvrlVideoList;
    private SeekBar sbProgress;
    /* access modifiers changed from: private */
    public long startTime;
    /* access modifiers changed from: private */
    public int storedPlayPosition = 0;
    private int storedState = 0;
    private String title;
    /* access modifiers changed from: private */
    public TextView tvBlankHint;
    /* access modifiers changed from: private */
    public TextView tvDownloadHint;
    private TextView tvTitleBarTitle;
    /* access modifiers changed from: private */
    public TextView tvVideoEnd;
    private TextView tvVideoInfo;
    /* access modifiers changed from: private */
    public TextView tvVideoStart;
    private String videoUrl;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Device b;
        super.onCreate(bundle);
        setContentView(R.layout.cs_activity_cloud_video_player);
        this.title = getIntent().getStringExtra("title");
        this.did = getIntent().getStringExtra("did");
        this.fileId = getIntent().getStringExtra("fileId");
        this.startTime = getIntent().getLongExtra("startTime", 0);
        this.duration = getIntent().getLongExtra("duration", 0);
        this.currentDateTS = getIntent().getLongExtra(CloudVideoListActivity.CURRENT_DATE_TS, 0);
        if (!TextUtils.isEmpty(this.did) && (b = SmartHomeDeviceManager.a().b(this.did)) != null) {
            this.model = b.model;
            this.isShared = b.isShared();
            this.isV4 = "chuangmi.camera.ipc009".equals(this.model) || "chuangmi.camera.ipc019".equals(this.model);
        }
        this.monthArray = SHApplication.getAppContext().getResources().getStringArray(R.array.cs_month_array);
        initViews();
        getData();
    }

    private void initViews() {
        this.rlTitleBar = (RelativeLayout) findViewById(R.id.rlTitleBar);
        this.ijkVideoView = (IjkVideoView) findViewById(R.id.ijkVideoView);
        this.ijkVideoView.setOnInfoListener(this);
        this.ijkVideoView.setOnErrorListener(this);
        this.ijkVideoView.setOnCompletionListener(this);
        this.ijkVideoView.setOnPreparedListener(this);
        this.ijkVideoView.setOnClickListener(this);
        this.ivHeaderRightSetting = (ImageView) findViewById(R.id.ivHeaderRightSetting);
        this.ivHeaderRightSetting.setOnClickListener(this);
        if (this.isShared) {
            this.ivHeaderRightSetting.setVisibility(8);
        }
        this.ivHeaderLeftBack = (ImageView) findViewById(R.id.ivHeaderLeftBack);
        this.ivHeaderLeftBack.setOnClickListener(this);
        this.tvVideoInfo = (TextView) findViewById(R.id.tvVideoInfo);
        this.tvVideoInfo.setOnClickListener(this);
        this.ivFullScreen = (ImageView) findViewById(R.id.ivFullScreen);
        this.ivFullScreen.setOnClickListener(this);
        this.ivDeleteCurrentVideo = (ImageView) findViewById(R.id.ivDeleteCurrentVideo);
        this.ivDeleteCurrentVideo.setOnClickListener(this);
        this.ivVideoDownload = (ImageView) findViewById(R.id.ivVideoDownload);
        this.ivVideoDownload.setOnClickListener(this);
        this.ivSnapshot = (ImageView) findViewById(R.id.ivSnapshot);
        this.ivVideoViewCover = (ImageView) findViewById(R.id.ivVideoViewCover);
        this.ivSnapshot.setOnClickListener(this);
        this.ivImage = (ImageView) findViewById(R.id.ivImage);
        this.rlVideoViewBottomCtrl = (RelativeLayout) findViewById(R.id.rlVideoViewBottomCtrl);
        this.rlTopRightCtrl = (RelativeLayout) findViewById(R.id.rlTopRightCtrl);
        this.tvBlankHint = (TextView) findViewById(R.id.tvBlankHint);
        this.rvrlVideoList = (RecyclerViewRefreshLayoutEx) findViewById(R.id.rvrlVideoList);
        this.rvrlVideoList.setMode(3);
        this.rvrlVideoList.setOnPullRefreshListener(new RecyclerViewRefreshLayout.OnPullRefreshListener() {
            public void onPullDistance(int i) {
            }

            public void onPullEnable(boolean z) {
            }

            public void onRefresh() {
                CloudVideoDate cloudVideoDate = (CloudVideoDate) CloudVideoPlayerActivity.this.dateListViewAdapter.getItem(CloudVideoPlayerActivity.this.dateListViewAdapter.selectedItemPosition);
                CloudVideoPlayerActivity.this.getPlayListLimit(cloudVideoDate, cloudVideoDate.timeStamp, CloudVideoListActivity.DAY_END_MILLIS + cloudVideoDate.timeStamp, true, true);
            }
        });
        this.rvrlVideoList.setFooterRefreshView(LayoutInflater.from(this).inflate(R.layout.list_load_more, (ViewGroup) null));
        this.rvrlVideoList.setOnPushLoadMoreListener(new RecyclerViewRefreshLayout.OnPushLoadMoreListener() {
            public void onPushDistance(int i) {
            }

            public void onPushEnable(boolean z) {
            }

            public void onLoadMore() {
                CloudVideoDate cloudVideoDate = (CloudVideoDate) CloudVideoPlayerActivity.this.dateListViewAdapter.getItem(CloudVideoPlayerActivity.this.dateListViewAdapter.selectedItemPosition);
                Object itemDataByPosition = CloudVideoPlayerActivity.this.playListAdapter.getItemDataByPosition(CloudVideoPlayerActivity.this.playListAdapter.getItemCount() - 1);
                if (itemDataByPosition instanceof CloudVideoChildListData) {
                    CloudVideoPlayerActivity.this.getPlayListLimit(cloudVideoDate, cloudVideoDate.timeStamp, ((CloudVideoChildListData) itemDataByPosition).startTime, false, true);
                    return;
                }
                CloudVideoPlayerActivity.this.rvrlVideoList.setLoadMore(false);
                if (CloudVideoPlayerActivity.this.rvrlVideoList.isRefreshing()) {
                    CloudVideoPlayerActivity.this.rvrlVideoList.setRefreshing(false);
                }
            }
        });
        this.rvrlVideoList.setRefreshing(false);
        this.rvrlVideoList.setLoadMore(false);
        this.sbProgress = (SeekBar) findViewById(R.id.sbProgress);
        this.sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                CloudVideoPlayerActivity.this.mHandler.removeCallbacks(CloudVideoPlayerActivity.this.getCurrentPosRunnable);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                CloudVideoPlayerActivity.this.seekTo(seekBar.getProgress());
                CloudVideoPlayerActivity.this.mHandler.postDelayed(CloudVideoPlayerActivity.this.getCurrentPosRunnable, 500);
            }
        });
        this.tvVideoStart = (TextView) findViewById(R.id.tvVideoStart);
        this.tvVideoEnd = (TextView) findViewById(R.id.tvVideoEnd);
        this.tvDownloadHint = (TextView) findViewById(R.id.tvDownloadHint);
        this.tvDownloadHint.setOnClickListener(this);
        this.cbTogglePlay = (CheckBox) findViewById(R.id.cbTogglePlay);
        this.cbTogglePlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    CloudVideoPlayerActivity.this.ijkVideoView.pause();
                    int unused = CloudVideoPlayerActivity.this.storedPlayPosition = 0;
                    CloudVideoPlayerActivity.this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            CloudVideoPlayerActivity.this.setImageBg();
                        }
                    }, 100);
                    return;
                }
                CloudVideoPlayerActivity.this.ijkVideoView.start();
                CloudVideoPlayerActivity.this.ivVideoViewCover.setVisibility(8);
                if (CloudVideoPlayerActivity.this.storedPlayPosition > 0) {
                    CloudVideoPlayerActivity.this.ijkVideoView.seekTo(CloudVideoPlayerActivity.this.storedPlayPosition);
                }
            }
        });
        this.cbIsMute = (CheckBox) findViewById(R.id.cbIsMute);
        this.cbIsMute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    CloudVideoPlayerActivity.this.ijkVideoView.mute(true);
                } else {
                    CloudVideoPlayerActivity.this.ijkVideoView.mute(false);
                }
            }
        });
        this.cbIsMute.setChecked(true);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.rlTitleBar.getLayoutParams());
        layoutParams.setMargins(0, TitleBarUtil.a(), 0, 0);
        this.rlTitleBar.setLayoutParams(layoutParams);
        this.rlTitleBar.bringToFront();
        this.tvTitleBarTitle = (TextView) findViewById(R.id.tvTitleBarTitle);
        this.tvTitleBarTitle.setVisibility(0);
        if (!TextUtils.isEmpty(this.title)) {
            this.tvTitleBarTitle.setText(this.title);
        }
        this.ijkVideoView.ijkVideoInfo = this.ijkVideoInfo;
        initVideoLoadingView();
        initDataLoadingView();
        initListView();
    }

    private synchronized void initVideoLoadingView() {
        this.ivVideoLoading = (ImageView) findViewById(R.id.ivVideoLoading);
    }

    private synchronized void initDataLoadingView() {
        this.ivDataLoading = (ImageView) findViewById(R.id.ivDataLoading);
    }

    private void initListView() {
        this.cvlvVideos = (CloudVideoListView) findViewById(R.id.cvlvVideos);
        this.cvlvVideos.setLayoutManager(new GridLayoutManager((Context) this, 3, 1, false));
        this.playListAdapter = new PlayListAdapter();
        this.cvlvVideos.setAdapter(this.playListAdapter);
        this.playListAdapter.iItemClickListener = new PlayListAdapter.IItemClickListener() {
            public void onItemClick(View view, int i, Object obj) {
                Object itemDataByPosition = CloudVideoPlayerActivity.this.playListAdapter.getItemDataByPosition(i);
                if (itemDataByPosition instanceof CloudVideoChildListData) {
                    CloudVideoChildListData cloudVideoChildListData = (CloudVideoChildListData) itemDataByPosition;
                    String unused = CloudVideoPlayerActivity.this.fileId = cloudVideoChildListData.fileId;
                    long unused2 = CloudVideoPlayerActivity.this.startTime = cloudVideoChildListData.startTime;
                    long unused3 = CloudVideoPlayerActivity.this.duration = cloudVideoChildListData.duration;
                    CloudVideoPlayerActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            CloudVideoPlayerActivity.this.getLinkAndPlay();
                        }
                    });
                }
            }
        };
        if (this.playListAdapter != null) {
            this.playListAdapter.setCurrentPlayItem(this.fileId);
            this.playListAdapter.notifyItemRangeChanged(0, this.playListAdapter.getItemCount(), "currentPlayPosition");
        }
        ArrayList arrayList = new ArrayList();
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        long timeInMillis = instance.getTimeInMillis();
        if (this.currentDateTS <= 0) {
            this.currentDateTS = timeInMillis;
        }
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.currentDateString = simpleDateFormat.format(Long.valueOf(timeInMillis));
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
        this.dateListViewAdapter.selectedItemPosition = getIntent().getIntExtra(CloudVideoListActivity.SELECTED_ITEM_POS, -1);
        this.dateListViewAdapter.iItemClickListener = new CloudVideoDateListViewAdapter.IItemClickListener<CloudVideoDate>() {
            public void onItemClick(View view, int i, CloudVideoDate cloudVideoDate) {
                LogUtil.a(CloudVideoPlayerActivity.TAG, "id:" + view.getId() + " position:" + i);
                if (cloudVideoDate != null) {
                    String unused = CloudVideoPlayerActivity.this.currentDateString = simpleDateFormat.format(Long.valueOf(cloudVideoDate.timeStamp));
                    long unused2 = CloudVideoPlayerActivity.this.currentDateTS = cloudVideoDate.timeStamp;
                }
                if (cloudVideoDate.isHasVideo) {
                    CloudVideoPlayerActivity.this.tvBlankHint.setVisibility(8);
                    CloudVideoPlayerActivity.this.getPlayListLimit(cloudVideoDate, cloudVideoDate.timeStamp, cloudVideoDate.timeStamp + CloudVideoListActivity.DAY_END_MILLIS, true, true);
                    return;
                }
                CloudVideoPlayerActivity.this.hideVideoLoading();
                if (CloudVideoPlayerActivity.this.rvrlVideoList != null) {
                    CloudVideoPlayerActivity.this.rvrlVideoList.setLoadMore(false);
                    if (CloudVideoPlayerActivity.this.rvrlVideoList.isRefreshing()) {
                        CloudVideoPlayerActivity.this.rvrlVideoList.setRefreshing(false);
                    }
                }
                CloudVideoPlayerActivity.this.tvBlankHint.setVisibility(0);
                CloudVideoPlayerActivity.this.playListAdapter.listData.clear();
                CloudVideoPlayerActivity.this.playListAdapter.notifyDataSetChanged();
            }
        };
        this.cvdlvDays.scrollToPosition(this.dateListViewAdapter.selectedItemPosition >= 0 ? this.dateListViewAdapter.selectedItemPosition : this.dateListViewAdapter.getItemCount() - 1);
    }

    private void getData() {
        getVideoDatesSerial();
        getLinkAndPlay();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a(TAG, "orientation:" + configuration.orientation);
        if (this.ijkVideoView != null && this.ijkVideoView.isPlaying()) {
            this.ijkVideoView.setRender(this.ijkVideoView.mCurrentRender);
        }
        if (this.ijkVideoView != null) {
            this.ijkVideoView.getState();
        }
        if (configuration.orientation == 1) {
            this.rlTitleBar.setVisibility(0);
            this.cvlvVideos.setVisibility(0);
            this.cvdlvDays.setVisibility(0);
            this.ivFullScreen.setImageResource(R.drawable.cs_player_enter_fullscreen);
            if (this.ijkVideoView != null) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.ijkVideoView.getLayoutParams());
                layoutParams.height = DisplayUtils.a(210.0f);
                layoutParams.addRule(3, R.id.rlTitleBar);
                this.ijkVideoView.setLayoutParams(layoutParams);
                this.ijkVideoView.requestLayout();
                return;
            }
            return;
        }
        this.rlTitleBar.setVisibility(8);
        this.cvlvVideos.setVisibility(8);
        this.cvdlvDays.setVisibility(8);
        this.ivFullScreen.setImageResource(R.drawable.cs_player_exit_fullscreen);
        if (this.ijkVideoView != null) {
            this.ijkVideoView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.ijkVideoView.requestLayout();
        }
    }

    /* access modifiers changed from: private */
    public void showVideoLoading(boolean z) {
        if (this.ivVideoLoading != null) {
            this.ivVideoLoading.setVisibility(0);
            Drawable drawable = this.ivVideoLoading.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).start();
            }
            if (z) {
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        CloudVideoPlayerActivity.this.hideVideoLoading();
                    }
                }, 5000);
            }
        }
    }

    /* access modifiers changed from: private */
    public void showDataLoading() {
        if (this.ivDataLoading != null && !isFinishing()) {
            this.ivDataLoading.setVisibility(0);
            this.ivDataLoading.bringToFront();
            Drawable drawable = this.ivDataLoading.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).start();
            }
        }
    }

    /* access modifiers changed from: private */
    public void hideVideoLoading() {
        if (this.ivVideoLoading != null) {
            Drawable drawable = this.ivVideoLoading.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).stop();
            }
            this.ivVideoLoading.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void hideDataLoading() {
        if (this.ivDataLoading != null) {
            Drawable drawable = this.ivDataLoading.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).stop();
            }
            this.ivDataLoading.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void showVideoInfo(@Nonnull String str, Drawable drawable) {
        if (!(this.ijkVideoView == null || this.ijkVideoView.getRenderView() == null || this.ijkVideoView.getRenderView().getView() == null)) {
            this.ijkVideoView.getRenderView().getView().setVisibility(8);
        }
        this.tvVideoInfo.setText(str);
        if (drawable != null) {
            this.tvVideoInfo.setCompoundDrawablePadding(50);
            this.tvVideoInfo.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, drawable, (Drawable) null, (Drawable) null);
        }
        this.tvVideoInfo.setVisibility(0);
        this.tvVideoInfo.bringToFront();
    }

    private void hideVideoInfo() {
        this.tvVideoInfo.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        this.tvVideoInfo.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void setImageBg() {
        Bitmap snapshot;
        String str = getFilesDir() + File.separator + this.did + File.separator + "cache";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        String str2 = str + File.separator + "coverPic.png";
        File file2 = new File(str2);
        if (!(this.ijkVideoView == null || (snapshot = this.ijkVideoView.snapshot()) == null)) {
            try {
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                snapshot.compress(Bitmap.CompressFormat.PNG, 0, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException unused) {
                str2 = null;
                this.ivVideoViewCover.setImageResource(R.drawable.camera_set_bg_01_nor);
            }
        }
        if (file2.exists()) {
            this.ivVideoViewCover.setImageBitmap(BitmapFactory.decodeFile(str2));
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        setImageBg();
        getWindow().setFlags(128, 128);
        if (this.ijkVideoView == null) {
            return;
        }
        if (this.storedState == 3 || this.storedState == 2 || this.storedState == 1 || this.storedState == 0) {
            showVideoLoading(false);
            this.ijkVideoView.start();
            this.ijkVideoView.seekTo(this.storedPlayPosition);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        getWindow().clearFlags(128);
        this.tvDownloadHint.setVisibility(8);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        if (getRequestedOrientation() != 1) {
            setOrientation(1);
        }
        if (this.ijkVideoView != null) {
            this.storedState = this.ijkVideoView.getState();
            this.ijkVideoView.pause();
        }
        if (this.getCurrentPosRunnable != null) {
            this.mHandler.removeCallbacks(this.getCurrentPosRunnable);
        }
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.ijkVideoView.stopPlayback();
        this.ijkVideoView.stopBackgroundPlay();
        this.ijkVideoView.release(true);
    }

    public void onBackPressed() {
        if (getRequestedOrientation() != 1) {
            setOrientation(1);
        } else {
            super.onBackPressed();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ijkVideoView /*2131429755*/:
                toggleVideoCtrlViewTranslation();
                return;
            case R.id.ivDeleteCurrentVideo /*2131430070*/:
                doCheckAndDelete();
                return;
            case R.id.ivFullScreen /*2131430076*/:
                if (getRequestedOrientation() == 1) {
                    setOrientation(0);
                    return;
                } else {
                    setOrientation(1);
                    return;
                }
            case R.id.ivHeaderLeftBack /*2131430078*/:
                onBackPressed();
                return;
            case R.id.ivHeaderRightSetting /*2131430080*/:
                CloudVideoNetUtils.getInstance().openCloudVideoManagePage(this, this.did);
                return;
            case R.id.ivSnapshot /*2131430096*/:
                takeSnapshot();
                return;
            case R.id.ivVideoDownload /*2131430099*/:
                downloadHint();
                return;
            case R.id.tvDownloadHint /*2131433119*/:
                this.tvDownloadHint.setVisibility(8);
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
            case R.id.tvVideoInfo /*2131433175*/:
                getLinkAndPlay();
                hideVideoInfo();
                return;
            default:
                return;
        }
    }

    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
        if (i == 3) {
            runOnUiThread(new Runnable() {
                public void run() {
                    CloudVideoPlayerActivity.this.hideVideoLoading();
                    CloudVideoPlayerActivity.this.ivVideoViewCover.setVisibility(8);
                    CloudVideoPlayerActivity.this.ijkVideoView.mute(CloudVideoPlayerActivity.this.cbIsMute.isChecked());
                    CloudVideoPlayerActivity.this.mHandler.removeCallbacks(CloudVideoPlayerActivity.this.getCurrentPosRunnable);
                    CloudVideoPlayerActivity.this.mHandler.post(CloudVideoPlayerActivity.this.getCurrentPosRunnable);
                }
            });
            LogUtil.a(TAG, "MEDIA_INFO_VIDEO_RENDERING_START");
            return false;
        } else if (i != 10002) {
            switch (i) {
                case 701:
                    LogUtil.a(TAG, "MEDIA_INFO_BUFFERING_START");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            CloudVideoPlayerActivity.this.showVideoLoading(false);
                        }
                    });
                    return false;
                case 702:
                    LogUtil.a(TAG, "MEDIA_INFO_BUFFERING_END");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            CloudVideoPlayerActivity.this.hideVideoLoading();
                            CloudVideoPlayerActivity.this.cbTogglePlay.setChecked(false);
                            CloudVideoPlayerActivity.this.ivVideoViewCover.setVisibility(8);
                            CloudVideoPlayerActivity.this.ijkVideoView.mute(CloudVideoPlayerActivity.this.cbIsMute.isChecked());
                            CloudVideoPlayerActivity.this.mHandler.removeCallbacks(CloudVideoPlayerActivity.this.getCurrentPosRunnable);
                            CloudVideoPlayerActivity.this.mHandler.post(CloudVideoPlayerActivity.this.getCurrentPosRunnable);
                        }
                    });
                    return false;
                default:
                    return false;
            }
        } else {
            runOnUiThread(new Runnable() {
                public void run() {
                    CloudVideoPlayerActivity.this.ivVideoViewCover.setVisibility(8);
                    CloudVideoPlayerActivity.this.mHandler.removeCallbacks(CloudVideoPlayerActivity.this.getCurrentPosRunnable);
                    CloudVideoPlayerActivity.this.mHandler.post(CloudVideoPlayerActivity.this.getCurrentPosRunnable);
                    CloudVideoPlayerActivity.this.hideVideoLoading();
                    CloudVideoPlayerActivity.this.ijkVideoView.mute(CloudVideoPlayerActivity.this.cbIsMute.isChecked());
                }
            });
            LogUtil.a(TAG, "MEDIA_INFO_AUDIO_RENDERING_START");
            return false;
        }
    }

    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
        this.mHandler.removeCallbacks(this.getCurrentPosRunnable);
        runOnUiThread(new Runnable() {
            public void run() {
                CloudVideoPlayerActivity.this.hideVideoLoading();
                CloudVideoPlayerActivity.this.showVideoInfo(CloudVideoPlayerActivity.this.getString(R.string.cs_network_error), CloudVideoPlayerActivity.this.getResources().getDrawable(R.drawable.cs_icon_replay));
            }
        });
        return false;
    }

    public void onCompletion(IMediaPlayer iMediaPlayer) {
        CloudVideoChildListData nextVideoData;
        this.mHandler.removeCallbacks(this.getCurrentPosRunnable);
        if (this.sbProgress != null) {
            this.sbProgress.setProgress(100);
        }
        this.cbTogglePlay.setChecked(true);
        if (this.playListAdapter != null && this.playListAdapter.currentPlayPosition < this.playListAdapter.getChildItemCount() - 1 && (nextVideoData = this.playListAdapter.getNextVideoData()) != null) {
            this.fileId = nextVideoData.fileId;
            this.startTime = nextVideoData.startTime;
            this.duration = nextVideoData.duration;
            runOnUiThread(new Runnable() {
                public void run() {
                    CloudVideoPlayerActivity.this.getLinkAndPlay();
                }
            });
        }
    }

    public void onPrepared(IMediaPlayer iMediaPlayer) {
        LogUtil.a(TAG, "mp.getDuration():" + iMediaPlayer.getDuration());
        final String longToDate = this.playListAdapter.longToDate(iMediaPlayer.getDuration() / 1000);
        if (iMediaPlayer != null && this.playListAdapter != null) {
            runOnUiThread(new Runnable() {
                public void run() {
                    TextView access$2600 = CloudVideoPlayerActivity.this.tvVideoEnd;
                    access$2600.setText("" + longToDate);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void getLinkAndPlay() {
        if (!TextUtils.isEmpty(this.did) && !TextUtils.isEmpty(this.fileId)) {
            this.videoUrl = CloudVideoNetUtils.getInstance().getPlayFileUrl(this.did, this.fileId, "H264");
            if (!(this.cvdlvDays == null || this.dateListViewAdapter == null)) {
                this.cvdlvDays.scrollToPosition(this.dateListViewAdapter.selectedItemPosition);
            }
            if (this.playListAdapter != null) {
                this.playListAdapter.setCurrentPlayItem(this.fileId);
                this.playListAdapter.notifyItemRangeChanged(0, this.playListAdapter.getItemCount(), "currentPlayPosition");
            }
            if (!(this.cvlvVideos == null || this.playListAdapter == null)) {
                this.cvlvVideos.scrollToPosition(this.playListAdapter.currentPlayPosition);
            }
            HashMap hashMap = new HashMap();
            MiServiceTokenInfo tokenInfo = CloudVideoNetUtils.getInstance().getTokenInfo();
            if (tokenInfo != null) {
                hashMap.put("Cookie", "yetAnotherServiceToken=" + tokenInfo.c);
                showVideoLoading(false);
                hideVideoInfo();
                this.ijkVideoView.setVideoURI(Uri.parse(this.videoUrl), hashMap);
                this.ijkVideoView.start();
                this.cbTogglePlay.setChecked(false);
                if (this.ijkVideoView.getRenderView() != null) {
                    this.ijkVideoView.getRenderView().getView().setVisibility(0);
                }
                LogUtil.a(TAG, "start play");
            }
        }
    }

    private void setOrientation(int i) {
        setRequestedOrientation(i);
    }

    /* access modifiers changed from: private */
    public void toggleVideoCtrlViewTranslation() {
        if (this.rlVideoViewBottomCtrl.getTranslationY() <= 0.0f) {
            this.rlVideoViewBottomCtrl.setTranslationY((float) this.rlVideoViewBottomCtrl.getHeight());
        } else {
            this.rlVideoViewBottomCtrl.setTranslationY(0.0f);
        }
        if (this.rlTopRightCtrl.getTranslationY() >= 0.0f) {
            this.rlTopRightCtrl.setTranslationY((float) (-this.rlTopRightCtrl.getHeight()));
        } else {
            this.rlTopRightCtrl.setTranslationY(0.0f);
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
                    showDataLoading();
                }
                this.cvlvVideos.setEnabled(false);
                CloudVideoNetUtils.getInstance().getVideoDailyListLimit(getContext(), jSONObject.toString(), new ICloudVideoCallback<List<DailyList>>() {
                    public void onCloudVideoSuccess(final List<DailyList> list, Object obj) {
                        if (!CloudVideoPlayerActivity.this.isFinishing()) {
                            CloudVideoPlayerActivity.this.cvlvVideos.setEnabled(true);
                            long localTimeZone = CloudVideoNetUtils.getInstance().toLocalTimeZone(((Long) obj).longValue());
                            long access$1900 = (CloudVideoPlayerActivity.this.currentDateTS + 86400000) - 1000;
                            CloudVideoPlayerActivity.this.rvrlVideoList.setLoadMore(false);
                            if (CloudVideoPlayerActivity.this.rvrlVideoList.isRefreshing()) {
                                CloudVideoPlayerActivity.this.rvrlVideoList.setRefreshing(false);
                            }
                            if (z) {
                                CloudVideoPlayerActivity.this.playListAdapter.clearAllData();
                            }
                            if (localTimeZone <= 0 || localTimeZone != access$1900) {
                                CloudVideoPlayerActivity.this.hideDataLoading();
                            } else if (list == null || list.isEmpty()) {
                                CloudVideoPlayerActivity.this.hideDataLoading();
                                if (CloudVideoPlayerActivity.this.playListAdapter != null) {
                                    CloudVideoPlayerActivity.this.playListAdapter.notifyDataSetChanged();
                                }
                                CloudVideoPlayerActivity.this.isTodayHasVideo();
                            } else {
                                new Thread(new Runnable() {
                                    public void run() {
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
                                        for (DailyList dailyList : list) {
                                            CloudVideoChildListData cloudVideoChildListData = new CloudVideoChildListData();
                                            cloudVideoChildListData.duration = dailyList.duration;
                                            cloudVideoChildListData.imgStoreId = dailyList.imgStoreId;
                                            cloudVideoChildListData.isPeople = dailyList.isHuman;
                                            cloudVideoChildListData.fileId = dailyList.fileId;
                                            cloudVideoChildListData.imgStoreUrl = CloudVideoNetUtils.getInstance().getSnapshotUrl(CloudVideoPlayerActivity.this.did, dailyList.fileId, dailyList.imgStoreId);
                                            cloudVideoChildListData.startTime = dailyList.createTime;
                                            CloudVideoPlayerActivity.this.playListAdapter.append(Integer.valueOf(simpleDateFormat.format(Long.valueOf(cloudVideoChildListData.startTime))).intValue(), cloudVideoChildListData);
                                        }
                                        CloudVideoPlayerActivity.this.runOnUiThread(
                                        /*  JADX ERROR: Method code generation error
                                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x006f: INVOKE  
                                              (wrap: com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity : 0x0068: IGET  (r0v2 com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity) = 
                                              (wrap: com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity$17 : 0x0066: IGET  (r0v1 com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity$17) = 
                                              (r8v0 'this' com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity$17$1 A[THIS])
                                             com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity.17.1.this$1 com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity$17)
                                             com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity.17.this$0 com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity)
                                              (wrap: com.xiaomi.smarthome.miio.camera.cloudstorage.-$$Lambda$CloudVideoPlayerActivity$17$1$DnCL-ulypWQycGO7DIXbSRj4e_k : 0x006c: CONSTRUCTOR  (r1v3 com.xiaomi.smarthome.miio.camera.cloudstorage.-$$Lambda$CloudVideoPlayerActivity$17$1$DnCL-ulypWQycGO7DIXbSRj4e_k) = 
                                              (r8v0 'this' com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity$17$1 A[THIS])
                                             call: com.xiaomi.smarthome.miio.camera.cloudstorage.-$$Lambda$CloudVideoPlayerActivity$17$1$DnCL-ulypWQycGO7DIXbSRj4e_k.<init>(com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity$17$1):void type: CONSTRUCTOR)
                                             com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity.runOnUiThread(java.lang.Runnable):void type: VIRTUAL in method: com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity.17.1.run():void, dex: classes7.dex
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                            	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                            	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                            	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:640)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                            	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:91)
                                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:697)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:156)
                                            	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                            	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                            	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                            	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                            	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:311)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:68)
                                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                            	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                            	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                            	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                                            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                                            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                                            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                                            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                                            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                                            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                                            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x006c: CONSTRUCTOR  (r1v3 com.xiaomi.smarthome.miio.camera.cloudstorage.-$$Lambda$CloudVideoPlayerActivity$17$1$DnCL-ulypWQycGO7DIXbSRj4e_k) = 
                                              (r8v0 'this' com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity$17$1 A[THIS])
                                             call: com.xiaomi.smarthome.miio.camera.cloudstorage.-$$Lambda$CloudVideoPlayerActivity$17$1$DnCL-ulypWQycGO7DIXbSRj4e_k.<init>(com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity$17$1):void type: CONSTRUCTOR in method: com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity.17.1.run():void, dex: classes7.dex
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                            	... 121 more
                                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.xiaomi.smarthome.miio.camera.cloudstorage.-$$Lambda$CloudVideoPlayerActivity$17$1$DnCL-ulypWQycGO7DIXbSRj4e_k, state: NOT_LOADED
                                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                            	... 127 more
                                            */
                                        /*
                                            this = this;
                                            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat
                                            java.lang.String r1 = "HH"
                                            r0.<init>(r1)
                                            java.util.List r1 = r7
                                            java.util.Iterator r1 = r1.iterator()
                                        L_0x000d:
                                            boolean r2 = r1.hasNext()
                                            if (r2 == 0) goto L_0x0066
                                            java.lang.Object r2 = r1.next()
                                            com.xiaomi.smarthome.miio.camera.cloudstorage.model.DailyList r2 = (com.xiaomi.smarthome.miio.camera.cloudstorage.model.DailyList) r2
                                            com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoChildListData r3 = new com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoChildListData
                                            r3.<init>()
                                            long r4 = r2.duration
                                            r3.duration = r4
                                            java.lang.String r4 = r2.imgStoreId
                                            r3.imgStoreId = r4
                                            boolean r4 = r2.isHuman
                                            r3.isPeople = r4
                                            java.lang.String r4 = r2.fileId
                                            r3.fileId = r4
                                            com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils r4 = com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils.getInstance()
                                            com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity$17 r5 = com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity.AnonymousClass17.this
                                            com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity r5 = com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity.this
                                            java.lang.String r5 = r5.did
                                            java.lang.String r6 = r2.fileId
                                            java.lang.String r7 = r2.imgStoreId
                                            java.lang.String r4 = r4.getSnapshotUrl(r5, r6, r7)
                                            r3.imgStoreUrl = r4
                                            long r4 = r2.createTime
                                            r3.startTime = r4
                                            com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity$17 r2 = com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity.AnonymousClass17.this
                                            com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity r2 = com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity.this
                                            com.xiaomi.smarthome.miio.camera.cloudstorage.adapter.PlayListAdapter r2 = r2.playListAdapter
                                            long r4 = r3.startTime
                                            java.lang.Long r4 = java.lang.Long.valueOf(r4)
                                            java.lang.String r4 = r0.format(r4)
                                            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                                            int r4 = r4.intValue()
                                            r2.append(r4, r3)
                                            goto L_0x000d
                                        L_0x0066:
                                            com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity$17 r0 = com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity.AnonymousClass17.this
                                            com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity r0 = com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity.this
                                            com.xiaomi.smarthome.miio.camera.cloudstorage.-$$Lambda$CloudVideoPlayerActivity$17$1$DnCL-ulypWQycGO7DIXbSRj4e_k r1 = new com.xiaomi.smarthome.miio.camera.cloudstorage.-$$Lambda$CloudVideoPlayerActivity$17$1$DnCL-ulypWQycGO7DIXbSRj4e_k
                                            r1.<init>(r8)
                                            r0.runOnUiThread(r1)
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity.AnonymousClass17.AnonymousClass1.run():void");
                                    }

                                    public static /* synthetic */ void lambda$run$0(AnonymousClass1 r1) {
                                        CloudVideoPlayerActivity.this.hideDataLoading();
                                        CloudVideoPlayerActivity.this.playListAdapter.notifyDataSetChanged();
                                    }
                                }).start();
                            }
                        }
                    }

                    public void onCloudVideoFailed(int i, String str) {
                        if (!CloudVideoPlayerActivity.this.isFinishing()) {
                            if (z && -90002 == i && CloudVideoPlayerActivity.this.playListAdapter != null) {
                                CloudVideoPlayerActivity.this.playListAdapter.clearAllData();
                            }
                            if (CloudVideoPlayerActivity.this.playListAdapter != null) {
                                CloudVideoPlayerActivity.this.playListAdapter.notifyDataSetChanged();
                            }
                            CloudVideoPlayerActivity.this.cvlvVideos.setEnabled(true);
                            CloudVideoPlayerActivity.this.rvrlVideoList.setLoadMore(false);
                            if (CloudVideoPlayerActivity.this.rvrlVideoList.isRefreshing()) {
                                CloudVideoPlayerActivity.this.rvrlVideoList.setRefreshing(false);
                            }
                            CloudVideoPlayerActivity.this.hideDataLoading();
                            CloudVideoPlayerActivity.this.isTodayHasVideo();
                            LogUtil.b(CloudVideoPlayerActivity.TAG, "errorCode:" + i + " errorInfo:" + str);
                        }
                    }
                });
            } catch (JSONException unused) {
                if (!isFinishing()) {
                    hideDataLoading();
                    this.rvrlVideoList.setLoadMore(false);
                    if (this.rvrlVideoList.isRefreshing()) {
                        this.rvrlVideoList.setRefreshing(false);
                    }
                }
            }
        } else {
            if (z) {
                this.playListAdapter.listData.clear();
                this.playListAdapter.notifyDataSetChanged();
            }
            this.rvrlVideoList.setLoadMore(false);
            if (this.rvrlVideoList.isRefreshing()) {
                this.rvrlVideoList.setRefreshing(false);
            }
        }
    }

    private void getVideoDates() {
        long currentTimeMillis = System.currentTimeMillis();
        long currentTimeMillis2 = System.currentTimeMillis() - CloudVideoListActivity.THIRTY_DAYS_MILLIS;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.did);
            jSONObject.put(Tags.Coupon.BEGIN_TIME, currentTimeMillis2);
            jSONObject.put("endTime", currentTimeMillis);
            jSONObject.put("region", Locale.getDefault().getCountry());
            showDataLoading();
            CloudVideoNetUtils.getInstance().getVideoDates(this, jSONObject.toString(), new ICloudVideoCallback<List<StatsRecord>>() {
                public void onCloudVideoSuccess(final List<StatsRecord> list, Object obj) {
                    if (!CloudVideoPlayerActivity.this.isFinishing()) {
                        CloudVideoPlayerActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                CloudVideoPlayerActivity.this.hideDataLoading();
                                LogUtil.a(CloudVideoPlayerActivity.TAG, "result:" + list);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                for (CloudVideoDate cloudVideoDate : CloudVideoPlayerActivity.this.dateListViewAdapter.cloudVideoDates) {
                                    cloudVideoDate.isHasVideo = false;
                                }
                                for (StatsRecord statsRecord : list) {
                                    if (!TextUtils.isEmpty(statsRecord.date)) {
                                        Iterator<CloudVideoDate> it = CloudVideoPlayerActivity.this.dateListViewAdapter.cloudVideoDates.iterator();
                                        while (true) {
                                            if (!it.hasNext()) {
                                                break;
                                            }
                                            CloudVideoDate next = it.next();
                                            if (statsRecord.date.equalsIgnoreCase(simpleDateFormat.format(Long.valueOf(next.timeStamp)))) {
                                                next.isHasVideo = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                                CloudVideoPlayerActivity.this.dateListViewAdapter.notifyDataSetChanged();
                                if (CloudVideoPlayerActivity.this.dateListViewAdapter != null && CloudVideoPlayerActivity.this.dateListViewAdapter.getItemCount() > 0) {
                                    CloudVideoDate cloudVideoDate2 = (CloudVideoDate) CloudVideoPlayerActivity.this.dateListViewAdapter.getItem(CloudVideoPlayerActivity.this.dateListViewAdapter.selectedItemPosition >= 0 ? CloudVideoPlayerActivity.this.dateListViewAdapter.selectedItemPosition : CloudVideoPlayerActivity.this.dateListViewAdapter.getItemCount() - 1);
                                    CloudVideoPlayerActivity.this.getPlayListLimit(cloudVideoDate2, cloudVideoDate2.timeStamp, CloudVideoListActivity.DAY_END_MILLIS + cloudVideoDate2.timeStamp, true, true);
                                }
                            }
                        });
                    }
                }

                public void onCloudVideoFailed(int i, String str) {
                    if (!CloudVideoPlayerActivity.this.isFinishing()) {
                        CloudVideoPlayerActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                CloudVideoPlayerActivity.this.hideDataLoading();
                            }
                        });
                        CloudVideoPlayerActivity.this.hideDataLoading();
                        LogUtil.b(CloudVideoPlayerActivity.TAG, "errorCode:" + i + ":" + str);
                    }
                }
            });
        } catch (JSONException e) {
            LogUtil.a(TAG, "exception:" + e.toString());
            hideDataLoading();
        }
    }

    private void getVideoDatesSerial() {
        int i;
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
                        long j2 = cloudVideoDate.timeStamp + CloudVideoListActivity.DAY_END_MILLIS;
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
                    CloudVideoNetUtils.getInstance().getVideoDatesSerial(this, jSONObject.toString(), new ICloudVideoCallback<List<StatsRecord2>>() {
                        public void onCloudVideoSuccess(List<StatsRecord2> list, Object obj) {
                            if (!CloudVideoPlayerActivity.this.isFinishing()) {
                                CloudVideoPlayerActivity.this.hideVideoLoading();
                                for (StatsRecord2 next : list) {
                                    for (CloudVideoDate next2 : CloudVideoPlayerActivity.this.dateListViewAdapter.cloudVideoDates) {
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
                                CloudVideoPlayerActivity.this.dateListViewAdapter.notifyDataSetChanged();
                                if (CloudVideoPlayerActivity.this.dateListViewAdapter.isAllDateDataUpdated() && CloudVideoPlayerActivity.this.dateListViewAdapter != null && CloudVideoPlayerActivity.this.dateListViewAdapter.getItemCount() > 0) {
                                    CloudVideoDate cloudVideoDate = (CloudVideoDate) CloudVideoPlayerActivity.this.dateListViewAdapter.getItem(CloudVideoPlayerActivity.this.dateListViewAdapter.selectedItemPosition >= 0 ? CloudVideoPlayerActivity.this.dateListViewAdapter.selectedItemPosition : CloudVideoPlayerActivity.this.dateListViewAdapter.getItemCount() - 1);
                                    CloudVideoPlayerActivity.this.getPlayListLimit(cloudVideoDate, cloudVideoDate.timeStamp, cloudVideoDate.timeStamp + CloudVideoListActivity.DAY_END_MILLIS, true, true);
                                }
                            }
                        }

                        public void onCloudVideoFailed(int i, String str) {
                            if (!CloudVideoPlayerActivity.this.isFinishing()) {
                                CloudVideoPlayerActivity.this.hideVideoLoading();
                                CloudVideoPlayerActivity.this.isTodayHasVideo();
                                LogUtil.b(CloudVideoPlayerActivity.TAG, "errorCode:" + i + ":" + str);
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
    public void isTodayHasVideo() {
        if (this.playListAdapter == null || this.playListAdapter.listData == null || !this.playListAdapter.isContainVideoData()) {
            this.tvBlankHint.setVisibility(0);
        } else {
            this.tvBlankHint.setVisibility(8);
        }
    }

    private void doCheckAndDelete() {
        new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.cs_delete_video)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("did", CloudVideoPlayerActivity.this.did);
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(CloudVideoPlayerActivity.this.fileId);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("fileIds", jSONArray);
                    jSONObject.put("fileIds", jSONObject2);
                    jSONObject.put("model", CloudVideoPlayerActivity.this.model);
                    CloudVideoPlayerActivity.this.showDataLoading();
                    CloudVideoNetUtils.getInstance().deleteFiles(CloudVideoPlayerActivity.this.getContext(), jSONObject.toString(), new ICloudVideoCallback<String>() {
                        public void onCloudVideoSuccess(String str, Object obj) {
                            if (!CloudVideoPlayerActivity.this.isFinishing()) {
                                CloudVideoPlayerActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        CloudVideoPlayerActivity.this.hideDataLoading();
                                        String unused = CloudVideoPlayerActivity.this.fileId = null;
                                        if (CloudVideoPlayerActivity.this.ijkVideoView != null) {
                                            CloudVideoPlayerActivity.this.ijkVideoView.stopPlayback();
                                        }
                                        CloudVideoDate cloudVideoDate = (CloudVideoDate) CloudVideoPlayerActivity.this.dateListViewAdapter.getItem(CloudVideoPlayerActivity.this.dateListViewAdapter.selectedItemPosition);
                                        CloudVideoPlayerActivity.this.getPlayListLimit(cloudVideoDate, cloudVideoDate.timeStamp, CloudVideoListActivity.DAY_END_MILLIS + cloudVideoDate.timeStamp, true, true);
                                    }
                                });
                            }
                        }

                        public void onCloudVideoFailed(int i, String str) {
                            if (!CloudVideoPlayerActivity.this.isFinishing()) {
                                CloudVideoPlayerActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        CloudVideoPlayerActivity.this.hideDataLoading();
                                    }
                                });
                                LogUtil.b(CloudVideoPlayerActivity.TAG, "errorCode:" + i + " errorInfo:" + str);
                            }
                        }
                    });
                } catch (JSONException unused) {
                    CloudVideoPlayerActivity.this.hideDataLoading();
                }
            }
        }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).d();
    }

    private void takeSnapshot() {
        final Bitmap snapshot = this.ijkVideoView.snapshot();
        if (snapshot != null) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        final String generateFilepath = CloudVideoFileUtils.generateFilepath(false, CloudVideoPlayerActivity.this.did);
                        FileOutputStream fileOutputStream = new FileOutputStream(generateFilepath);
                        snapshot.compress(Bitmap.CompressFormat.PNG, 0, fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        CloudVideoPlayerActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                ContentValues contentValues = new ContentValues(4);
                                contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
                                contentValues.put(Downloads._DATA, generateFilepath);
                                contentValues.put("mime_type", "image/jpeg");
                                try {
                                    if (!Build.MODEL.equals("HM 1SC")) {
                                        CloudVideoPlayerActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                                    }
                                    LogUtil.a(CloudVideoPlayerActivity.TAG, "snap success");
                                    CloudVideoPlayerActivity.this.ivImage.setImageBitmap(snapshot);
                                    CloudVideoPlayerActivity.this.ivImage.setVisibility(0);
                                    CloudVideoPlayerActivity.this.ivImage.bringToFront();
                                    CloudVideoPlayerActivity.this.ivImage.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            Intent intent = new Intent(CloudVideoPlayerActivity.this, CloudVideoLocalPicActivity.class);
                                            intent.putExtra(TbsReaderView.KEY_FILE_PATH, generateFilepath);
                                            CloudVideoPlayerActivity.this.startActivity(intent);
                                            CloudVideoPlayerActivity.this.ivImage.setVisibility(8);
                                        }
                                    });
                                    CloudVideoPlayerActivity.this.mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            CloudVideoPlayerActivity.this.ivImage.setVisibility(8);
                                        }
                                    }, 5000);
                                } catch (Throwable unused) {
                                }
                            }
                        });
                    } catch (IOException e) {
                        LogUtil.a(CloudVideoPlayerActivity.TAG, "IOException:" + e.getLocalizedMessage());
                    }
                }
            }).start();
        } else {
            LogUtil.b(TAG, "bitmap null");
        }
    }

    private void toImageFolder(File file) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.fromFile(file), ShareObject.d);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void doDownload() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Device b = SmartHomeDeviceManager.a().b(this.did);
        if (b != null) {
            ArrayList arrayList = new ArrayList();
            CloudVideoDownloadInfo cloudVideoDownloadInfo = new CloudVideoDownloadInfo();
            cloudVideoDownloadInfo.uid = b.userId;
            cloudVideoDownloadInfo.did = this.did;
            cloudVideoDownloadInfo.title = this.title;
            cloudVideoDownloadInfo.videoUrl = CloudVideoNetUtils.getInstance().getPlayFileUrl(this.did, this.fileId, "H264");
            cloudVideoDownloadInfo.fileId = this.fileId;
            cloudVideoDownloadInfo.mp4FilePath = null;
            cloudVideoDownloadInfo.m3u8FilePath = null;
            cloudVideoDownloadInfo.status = 4;
            cloudVideoDownloadInfo.createTime = System.currentTimeMillis();
            cloudVideoDownloadInfo.startTime = this.startTime;
            cloudVideoDownloadInfo.endTime = this.startTime + this.duration;
            cloudVideoDownloadInfo.duration = this.duration;
            cloudVideoDownloadInfo.createTimePretty = simpleDateFormat.format(Long.valueOf(cloudVideoDownloadInfo.createTime));
            cloudVideoDownloadInfo.startTimePretty = simpleDateFormat.format(Long.valueOf(cloudVideoDownloadInfo.startTime));
            cloudVideoDownloadInfo.endTimePretty = simpleDateFormat.format(Long.valueOf(cloudVideoDownloadInfo.endTime));
            cloudVideoDownloadInfo.size = 0;
            cloudVideoDownloadInfo.progress = 0;
            arrayList.add(cloudVideoDownloadInfo);
            CloudVideoDownloadManager.getInstance().insertRecords(arrayList);
            CloudVideoDownloadManager.getInstance().pullDownloadFromList(getContext(), b.userId, this.did);
        }
    }

    /* access modifiers changed from: private */
    public void showDownloadActivityHint() {
        this.tvDownloadHint.setVisibility(0);
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (CloudVideoPlayerActivity.this.tvDownloadHint != null) {
                    CloudVideoPlayerActivity.this.tvDownloadHint.setVisibility(8);
                }
            }
        }, 3000);
    }

    /* access modifiers changed from: private */
    public void seekTo(int i) {
        if (i >= 0 && this.ijkVideoView != null && this.ijkVideoView.getDuration() > 0) {
            int duration2 = (i * this.ijkVideoView.getDuration()) / 100;
            if (this.ijkVideoView.canSeekForward() && this.ijkVideoView.canSeekBackward()) {
                this.ijkVideoView.seekTo(duration2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void setProgressTime(int i) {
        int duration2 = this.ijkVideoView.getDuration();
        if (i >= 0 && this.ijkVideoView != null && duration2 > 0) {
            int ceil = (int) Math.ceil((double) ((i * 100) / duration2));
            if (ceil > 100 || i / 1000 >= duration2 / 1000) {
                this.sbProgress.setProgress(100);
            } else {
                this.sbProgress.setProgress(ceil);
            }
        }
    }

    private void downloadHint() {
        if (CloudVideoNetUtils.getInstance().isWifiConnected(this)) {
            doDownload();
            showDownloadActivityHint();
            return;
        }
        new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.cs_non_wifi_environment)).a((int) R.string.cs_go_on, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CloudVideoPlayerActivity.this.doDownload();
                CloudVideoPlayerActivity.this.showDownloadActivityHint();
            }
        }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
            }
        }).b((int) R.string.cs_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).d();
    }

    private boolean isFileDownloaded() {
        List<CloudVideoDownloadInfo> recordsFromDB;
        Device b = SmartHomeDeviceManager.a().b(this.did);
        if (!(b == null || (recordsFromDB = CloudVideoDownloadManager.getInstance().getRecordsFromDB(b.userId, this.did)) == null || recordsFromDB.size() <= 0)) {
            for (CloudVideoDownloadInfo cloudVideoDownloadInfo : recordsFromDB) {
                if (Math.abs(cloudVideoDownloadInfo.startTime - this.startTime) < 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public String intToDate(long j) {
        if (j <= 0) {
            return "00:00";
        }
        int i = (int) j;
        int i2 = i / 3600;
        int i3 = i2 * 3600;
        int i4 = ((int) (j - ((long) i3))) / 60;
        int i5 = (i - (i4 * 60)) - i3;
        StringBuilder sb = new StringBuilder();
        if (i2 > 0) {
            if (i2 < 10) {
                sb.append("0" + i2);
            } else {
                sb.append(i2);
            }
            sb.append(":");
        }
        if (i4 < 10) {
            sb.append("0" + i4);
        } else {
            sb.append(i4);
        }
        sb.append(":");
        if (i5 < 10) {
            sb.append("0" + i5);
        } else {
            sb.append(i5);
        }
        return sb.toString();
    }
}
