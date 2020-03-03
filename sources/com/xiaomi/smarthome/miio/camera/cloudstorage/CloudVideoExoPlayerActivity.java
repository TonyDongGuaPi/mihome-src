package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.mi.global.shop.model.Tags;
import com.mijia.app.Event;
import com.tencent.smtt.sdk.TbsReaderView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.camera.view.TextViewS;
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
import com.xiaomi.smarthome.miio.camera.cloudstorage.exopackage.MJExoPlayerViewP;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoChildListData;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDate;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.DailyList;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.ICloudVideoCallback;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.StatsRecord2;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoEventLogger;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoFileUtils;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoUtils;
import com.xiaomi.smarthome.miio.camera.cloudstorage.views.RecyclerViewRefreshLayout;
import com.xiaomi.smarthome.miio.camera.cloudstorage.views.RecyclerViewRefreshLayoutEx;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.share.ShareObject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CloudVideoExoPlayerActivity extends CloudVideoBaseActivity implements View.OnClickListener {
    public static final String FILE_ID = "fileId";
    private static final int LOADING_AUTO_HIDE = 5000;
    public static final int PLAYER_STATE_IDLE = 0;
    public static final int PLAYER_STATE_PAUSE = 1;
    public static final int PLAYER_STATE_PLAY = 2;
    private static final String TAG = "CloudVideoExoPlayerActivity";
    private AudioManager audioManager = null;
    private CheckBox cbIsMute;
    /* access modifiers changed from: private */
    public CheckBox cbTogglePlay;
    /* access modifiers changed from: private */
    public String currentDateString = null;
    /* access modifiers changed from: private */
    public long currentDateTS = 0;
    private String currentFileExtension = "ts";
    private int currentVolume;
    private CloudVideoDateListView cvdlvDays;
    /* access modifiers changed from: private */
    public CloudVideoListView cvlvVideos;
    /* access modifiers changed from: private */
    public CloudVideoDateListViewAdapter dateListViewAdapter;
    /* access modifiers changed from: private */
    public int datesUpdateVar = 0;
    /* access modifiers changed from: private */
    public String did;
    /* access modifiers changed from: private */
    public long duration;
    private long eventTimeStampPause;
    private long eventTimeStampResume;
    /* access modifiers changed from: private */
    public MJExoPlayerViewP exoVideoView;
    /* access modifiers changed from: private */
    public String fileId;
    /* access modifiers changed from: private */
    public Runnable getCurrentPosRunnable = new Runnable() {
        public void run() {
            if (CloudVideoExoPlayerActivity.this.simpleExoPlayer != null) {
                final long currentPosition = CloudVideoExoPlayerActivity.this.simpleExoPlayer.getCurrentPosition();
                if (currentPosition > 0) {
                    long unused = CloudVideoExoPlayerActivity.this.storedPlayPosition = currentPosition;
                }
                CloudVideoExoPlayerActivity.this.setProgressTime(currentPosition);
                if (CloudVideoExoPlayerActivity.this.simpleExoPlayer.getPlayWhenReady()) {
                    CloudVideoExoPlayerActivity.this.cbTogglePlay.setChecked(false);
                } else {
                    CloudVideoExoPlayerActivity.this.cbTogglePlay.setChecked(true);
                }
                CloudVideoExoPlayerActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        long duration = CloudVideoExoPlayerActivity.this.simpleExoPlayer.getDuration() / 1000;
                        if (currentPosition / 1000 >= duration) {
                            TextView access$400 = CloudVideoExoPlayerActivity.this.tvVideoStart;
                            access$400.setText("" + CloudVideoExoPlayerActivity.this.longToDate(duration));
                            return;
                        }
                        TextView access$4002 = CloudVideoExoPlayerActivity.this.tvVideoStart;
                        access$4002.setText("" + CloudVideoExoPlayerActivity.this.longToDate(currentPosition / 1000));
                    }
                });
                CloudVideoExoPlayerActivity.this.mHandler.postDelayed(CloudVideoExoPlayerActivity.this.getCurrentPosRunnable, 500);
            }
        }
    };
    private boolean isReadOnly;
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
    private ImageView ivVideoLoading;
    /* access modifiers changed from: private */
    public ImageView ivVideoViewCover;
    private LinearLayout llTopRightCtrl;
    private int mSpeed = 1;
    private int maxVolume;
    /* access modifiers changed from: private */
    public String model = null;
    private String[] monthArray;
    /* access modifiers changed from: private */
    public PlayListAdapter playListAdapter;
    private RelativeLayout rlTitleBar;
    private RelativeLayout rlVideoViewBottomCtrl;
    /* access modifiers changed from: private */
    public RecyclerViewRefreshLayoutEx rvrlVideoList;
    private SeekBar sbProgress;
    /* access modifiers changed from: private */
    public SimpleExoPlayer simpleExoPlayer;
    /* access modifiers changed from: private */
    public long startTime;
    /* access modifiers changed from: private */
    public long storedPlayPosition = 0;
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
    private TextViewS tvsMultiSpeed;
    private String videoUrl;

    static /* synthetic */ int access$2910(CloudVideoExoPlayerActivity cloudVideoExoPlayerActivity) {
        int i = cloudVideoExoPlayerActivity.datesUpdateVar;
        cloudVideoExoPlayerActivity.datesUpdateVar = i - 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Device b;
        super.onCreate(bundle);
        setContentView(R.layout.cs_activity_cloud_video_exo_player);
        this.title = getIntent().getStringExtra("title");
        this.did = getIntent().getStringExtra("did");
        this.fileId = getIntent().getStringExtra("fileId");
        this.startTime = getIntent().getLongExtra("startTime", 0);
        this.duration = getIntent().getLongExtra("duration", 0);
        this.currentDateTS = getIntent().getLongExtra(CloudVideoListActivity.CURRENT_DATE_TS, 0);
        this.audioManager = (AudioManager) getSystemService("audio");
        this.monthArray = SHApplication.getAppContext().getResources().getStringArray(R.array.cs_month_array);
        if (!TextUtils.isEmpty(this.did) && (b = SmartHomeDeviceManager.a().b(this.did)) != null) {
            this.model = b.model;
            this.isShared = b.isShared();
            this.isReadOnly = b.isSharedReadOnly();
            this.isV4 = "chuangmi.camera.ipc009".equals(this.model) || "chuangmi.camera.ipc019".equals(this.model);
        }
        initViews();
        getData();
        Event.a(Event.aX);
    }

    private void initViews() {
        this.rlTitleBar = (RelativeLayout) findViewById(R.id.rlTitleBar);
        this.exoVideoView = (MJExoPlayerViewP) findViewById(R.id.exoVideoView);
        this.exoVideoView.setPlayerViewListener(new MJExoPlayerViewP.PlayerViewListener() {
            public void onVideoSizeChanged(int i, int i2, int i3, float f) {
                LogUtil.a(CloudVideoExoPlayerActivity.TAG, "onVideoSizeChanged:" + i + " height:" + i2);
                if (CloudVideoExoPlayerActivity.this.getRequestedOrientation() == 1) {
                    int b = DisplayUtils.b(CloudVideoExoPlayerActivity.this.getContext());
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(CloudVideoExoPlayerActivity.this.exoVideoView.getLayoutParams());
                    layoutParams.height = (i2 * b) / i;
                    layoutParams.width = b;
                    layoutParams.addRule(3, R.id.rlTitleBar);
                    CloudVideoExoPlayerActivity.this.exoVideoView.setLayoutParams(layoutParams);
                    CloudVideoExoPlayerActivity.this.exoVideoView.requestLayout();
                }
            }

            public void onRenderedFirstFrame() {
                LogUtil.a(CloudVideoExoPlayerActivity.TAG, "onRenderedFirstFrame");
                if (CloudVideoExoPlayerActivity.this.simpleExoPlayer != null) {
                    String longToDate = CloudVideoExoPlayerActivity.this.longToDate(CloudVideoExoPlayerActivity.this.simpleExoPlayer.getDuration() / 1000);
                    TextView access$700 = CloudVideoExoPlayerActivity.this.tvVideoEnd;
                    access$700.setText("" + longToDate);
                }
                CloudVideoExoPlayerActivity.this.mHandler.post(CloudVideoExoPlayerActivity.this.getCurrentPosRunnable);
                CloudVideoExoPlayerActivity.this.hideVideoLoading();
            }
        });
        this.simpleExoPlayer = ExoPlayerFactory.newSimpleInstance((Context) this, (TrackSelector) new DefaultTrackSelector((TrackSelection.Factory) new AdaptiveTrackSelection.Factory(new DefaultBandwidthMeter())));
        if (this.audioManager != null) {
            this.maxVolume = this.audioManager.getStreamMaxVolume(1);
            this.currentVolume = this.audioManager.getStreamVolume(1);
            if (this.maxVolume > 0) {
                this.simpleExoPlayer.setVolume(((float) this.currentVolume) / ((float) this.maxVolume));
            }
        }
        this.exoVideoView.setPlayer(this.simpleExoPlayer);
        if (!(this.exoVideoView == null || this.exoVideoView.getVideoSurfaceView() == null)) {
            this.exoVideoView.getVideoSurfaceView().setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CloudVideoExoPlayerActivity.this.toggleVideoCtrlViewTranslation();
                }
            });
        }
        if (this.simpleExoPlayer != null) {
            this.simpleExoPlayer.addListener(new Player.EventListener() {
                public void onTimelineChanged(Timeline timeline, Object obj, int i) {
                    LogUtil.a(CloudVideoExoPlayerActivity.TAG, "onTimelineChanged:" + i);
                }

                public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
                    LogUtil.a(CloudVideoExoPlayerActivity.TAG, "onTracksChanged");
                }

                public void onLoadingChanged(boolean z) {
                    if (z) {
                        CloudVideoExoPlayerActivity.this.showVideoLoading(false);
                    } else {
                        CloudVideoExoPlayerActivity.this.hideVideoLoading();
                    }
                    LogUtil.a(CloudVideoExoPlayerActivity.TAG, "onLoadingChanged:" + z);
                }

                public void onPlayerStateChanged(boolean z, int i) {
                    switch (i) {
                        case 1:
                            CloudVideoExoPlayerActivity.this.hideVideoLoading();
                            break;
                        case 2:
                            CloudVideoExoPlayerActivity.this.showVideoLoading(false);
                            break;
                        case 3:
                            CloudVideoExoPlayerActivity.this.hideVideoLoading();
                            break;
                        case 4:
                            CloudVideoExoPlayerActivity.this.hideVideoLoading();
                            CloudVideoExoPlayerActivity.this.simpleExoPlayer.seekTo(0);
                            CloudVideoExoPlayerActivity.this.onCompletion();
                            CloudVideoExoPlayerActivity.this.mHandler.removeCallbacks(CloudVideoExoPlayerActivity.this.getCurrentPosRunnable);
                            break;
                    }
                    LogUtil.a(CloudVideoExoPlayerActivity.TAG, "onPlayerStateChanged:" + z + " playbackState:" + i);
                }

                public void onRepeatModeChanged(int i) {
                    LogUtil.a(CloudVideoExoPlayerActivity.TAG, "onRepeatModeChanged:" + i);
                }

                public void onShuffleModeEnabledChanged(boolean z) {
                    LogUtil.a(CloudVideoExoPlayerActivity.TAG, "onShuffleModeEnabledChanged:" + z);
                }

                public void onPlayerError(ExoPlaybackException exoPlaybackException) {
                    CloudVideoExoPlayerActivity.this.hideVideoLoading();
                    if (exoPlaybackException != null) {
                        LogUtil.a(CloudVideoExoPlayerActivity.TAG, "onPlayerError:" + exoPlaybackException.getLocalizedMessage() + "" + exoPlaybackException.type + " error:" + exoPlaybackException.toString());
                    }
                }

                public void onPositionDiscontinuity(int i) {
                    LogUtil.a(CloudVideoExoPlayerActivity.TAG, "onPositionDiscontinuity:" + i);
                }

                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                    LogUtil.a(CloudVideoExoPlayerActivity.TAG, "onPlaybackParametersChanged:" + playbackParameters.speed);
                }

                public void onSeekProcessed() {
                    LogUtil.a(CloudVideoExoPlayerActivity.TAG, "onSeekProcessed");
                }
            });
        } else {
            LogUtil.b(TAG, "simpleExoPlayer null");
        }
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
        this.tvsMultiSpeed = (TextViewS) findViewById(R.id.tvsMultiSpeed);
        TextViewS textViewS = this.tvsMultiSpeed;
        textViewS.setText("" + this.mSpeed + "X");
        this.tvsMultiSpeed.setOnClickListener(this);
        this.ivDeleteCurrentVideo = (ImageView) findViewById(R.id.ivDeleteCurrentVideo);
        this.ivDeleteCurrentVideo.setOnClickListener(this);
        this.ivVideoDownload = (ImageView) findViewById(R.id.ivVideoDownload);
        this.ivVideoDownload.setVisibility(0);
        this.ivVideoDownload.setOnClickListener(this);
        this.ivSnapshot = (ImageView) findViewById(R.id.ivSnapshot);
        this.ivVideoViewCover = (ImageView) findViewById(R.id.ivVideoViewCover);
        this.ivSnapshot.setOnClickListener(this);
        this.ivImage = (ImageView) findViewById(R.id.ivImage);
        this.rlVideoViewBottomCtrl = (RelativeLayout) findViewById(R.id.rlVideoViewBottomCtrl);
        this.llTopRightCtrl = (LinearLayout) findViewById(R.id.llTopRightCtrl);
        if (this.isReadOnly) {
            this.llTopRightCtrl.setVisibility(8);
        }
        this.tvBlankHint = (TextView) findViewById(R.id.tvBlankHint);
        this.rvrlVideoList = (RecyclerViewRefreshLayoutEx) findViewById(R.id.rvrlVideoList);
        this.rvrlVideoList.setMode(3);
        this.rvrlVideoList.setOnPullRefreshListener(new RecyclerViewRefreshLayout.OnPullRefreshListener() {
            public void onPullDistance(int i) {
            }

            public void onPullEnable(boolean z) {
            }

            public void onRefresh() {
                CloudVideoDate cloudVideoDate = (CloudVideoDate) CloudVideoExoPlayerActivity.this.dateListViewAdapter.getItem(CloudVideoExoPlayerActivity.this.dateListViewAdapter.selectedItemPosition);
                CloudVideoExoPlayerActivity.this.getPlayListLimit(cloudVideoDate, cloudVideoDate.timeStamp, CloudVideoListActivity.DAY_END_MILLIS + cloudVideoDate.timeStamp, true, true);
            }
        });
        this.rvrlVideoList.setFooterRefreshView(LayoutInflater.from(this).inflate(R.layout.list_load_more, (ViewGroup) null));
        this.rvrlVideoList.setOnPushLoadMoreListener(new RecyclerViewRefreshLayout.OnPushLoadMoreListener() {
            public void onPushDistance(int i) {
            }

            public void onPushEnable(boolean z) {
            }

            public void onLoadMore() {
                CloudVideoDate cloudVideoDate = (CloudVideoDate) CloudVideoExoPlayerActivity.this.dateListViewAdapter.getItem(CloudVideoExoPlayerActivity.this.dateListViewAdapter.selectedItemPosition);
                Object itemDataByPosition = CloudVideoExoPlayerActivity.this.playListAdapter.getItemDataByPosition(CloudVideoExoPlayerActivity.this.playListAdapter.getItemCount() - 1);
                if (itemDataByPosition instanceof CloudVideoChildListData) {
                    CloudVideoExoPlayerActivity.this.getPlayListLimit(cloudVideoDate, cloudVideoDate.timeStamp, ((CloudVideoChildListData) itemDataByPosition).startTime, false, true);
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
                CloudVideoExoPlayerActivity.this.mHandler.removeCallbacks(CloudVideoExoPlayerActivity.this.getCurrentPosRunnable);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                CloudVideoExoPlayerActivity.this.seekTo(seekBar.getProgress());
                CloudVideoExoPlayerActivity.this.mHandler.postDelayed(CloudVideoExoPlayerActivity.this.getCurrentPosRunnable, 500);
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
                    CloudVideoExoPlayerActivity.this.simpleExoPlayer.setPlayWhenReady(false);
                    CloudVideoExoPlayerActivity.this.mHandler.removeCallbacks(CloudVideoExoPlayerActivity.this.getCurrentPosRunnable);
                    long unused = CloudVideoExoPlayerActivity.this.storedPlayPosition = 0;
                    return;
                }
                CloudVideoExoPlayerActivity.this.simpleExoPlayer.setPlayWhenReady(true);
                CloudVideoExoPlayerActivity.this.ivVideoViewCover.setVisibility(8);
                CloudVideoExoPlayerActivity.this.mHandler.post(CloudVideoExoPlayerActivity.this.getCurrentPosRunnable);
                long unused2 = CloudVideoExoPlayerActivity.this.storedPlayPosition;
            }
        });
        this.cbIsMute = (CheckBox) findViewById(R.id.cbIsMute);
        this.cbIsMute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.aY);
                if (z) {
                    CloudVideoExoPlayerActivity.this.simpleExoPlayer.setVolume(0.0f);
                    return;
                }
                CloudVideoExoPlayerActivity.this.simpleExoPlayer.setVolume(1.0f);
                CloudVideoEventLogger.EventClick(CloudVideoExoPlayerActivity.this.model, CloudVideoEventLogger.CloudVideo_AudioNumber, (String) null);
            }
        });
        this.cbIsMute.setChecked(true);
        if (this.mSpeed != 1) {
            this.cbIsMute.setEnabled(false);
        } else {
            this.cbIsMute.setEnabled(true);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.rlTitleBar.getLayoutParams());
        layoutParams.setMargins(0, TitleBarUtil.a(), 0, 0);
        this.rlTitleBar.setLayoutParams(layoutParams);
        this.rlTitleBar.bringToFront();
        this.tvTitleBarTitle = (TextView) findViewById(R.id.tvTitleBarTitle);
        this.tvTitleBarTitle.setVisibility(0);
        if (!TextUtils.isEmpty(this.title)) {
            this.tvTitleBarTitle.setText(this.title);
        }
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
        this.playListAdapter.isDownloadEnabled = true;
        this.cvlvVideos.setAdapter(this.playListAdapter);
        this.playListAdapter.iItemClickListener = new PlayListAdapter.IItemClickListener() {
            public void onItemClick(View view, int i, Object obj) {
                CloudVideoChildListData cloudVideoChildListData;
                Object itemDataByPosition = CloudVideoExoPlayerActivity.this.playListAdapter.getItemDataByPosition(i);
                if ((itemDataByPosition instanceof CloudVideoChildListData) && (cloudVideoChildListData = (CloudVideoChildListData) itemDataByPosition) != null) {
                    String unused = CloudVideoExoPlayerActivity.this.fileId = cloudVideoChildListData.fileId;
                    long unused2 = CloudVideoExoPlayerActivity.this.startTime = cloudVideoChildListData.startTime;
                    long unused3 = CloudVideoExoPlayerActivity.this.duration = cloudVideoChildListData.duration;
                    CloudVideoExoPlayerActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            CloudVideoExoPlayerActivity.this.getLinkAndPlay();
                        }
                    });
                }
            }
        };
        if (this.playListAdapter != null && !TextUtils.isEmpty(this.fileId)) {
            this.playListAdapter.setCurrentPlayItem(this.fileId);
            this.playListAdapter.notifyItemRangeChanged(0, this.playListAdapter.getItemCount(), "currentPlayPosition");
        }
        ArrayList arrayList = new ArrayList();
        long currentDayTS0 = CloudVideoUtils.getCurrentDayTS0();
        if (this.currentDateTS <= 0) {
            this.currentDateTS = currentDayTS0;
        }
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.currentDateString = simpleDateFormat.format(Long.valueOf(currentDayTS0));
        for (int i = 29; i >= 0; i--) {
            CloudVideoDate cloudVideoDate = new CloudVideoDate();
            long j = currentDayTS0 - (((long) i) * 86400000);
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
                LogUtil.a(CloudVideoExoPlayerActivity.TAG, "id:" + view.getId() + " position:" + i);
                if (cloudVideoDate != null) {
                    String unused = CloudVideoExoPlayerActivity.this.currentDateString = simpleDateFormat.format(Long.valueOf(cloudVideoDate.timeStamp));
                    long unused2 = CloudVideoExoPlayerActivity.this.currentDateTS = cloudVideoDate.timeStamp;
                }
                if (cloudVideoDate.isHasVideo) {
                    CloudVideoExoPlayerActivity.this.tvBlankHint.setVisibility(8);
                    CloudVideoExoPlayerActivity.this.getPlayListLimit(cloudVideoDate, cloudVideoDate.timeStamp, cloudVideoDate.timeStamp + CloudVideoListActivity.DAY_END_MILLIS, true, true);
                    return;
                }
                CloudVideoExoPlayerActivity.this.hideVideoLoading();
                if (CloudVideoExoPlayerActivity.this.rvrlVideoList != null) {
                    CloudVideoExoPlayerActivity.this.rvrlVideoList.setLoadMore(false);
                    if (CloudVideoExoPlayerActivity.this.rvrlVideoList.isRefreshing()) {
                        CloudVideoExoPlayerActivity.this.rvrlVideoList.setRefreshing(false);
                    }
                }
                CloudVideoExoPlayerActivity.this.tvBlankHint.setVisibility(0);
                CloudVideoExoPlayerActivity.this.playListAdapter.listData.clear();
                CloudVideoExoPlayerActivity.this.playListAdapter.notifyDataSetChanged();
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
        if (configuration.orientation == 1) {
            getWindow().clearFlags(1024);
            this.rlTitleBar.setVisibility(0);
            this.cvlvVideos.setVisibility(0);
            this.cvdlvDays.setVisibility(0);
            this.ivFullScreen.setImageResource(R.drawable.cs_player_enter_fullscreen);
            if (this.exoVideoView != null) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.exoVideoView.getLayoutParams());
                layoutParams.height = DisplayUtils.a(210.0f);
                layoutParams.addRule(3, R.id.rlTitleBar);
                this.exoVideoView.setLayoutParams(layoutParams);
                this.exoVideoView.requestLayout();
                return;
            }
            return;
        }
        getWindow().setFlags(1024, 1024);
        this.rlTitleBar.setVisibility(8);
        this.cvlvVideos.setVisibility(8);
        this.cvdlvDays.setVisibility(8);
        this.ivFullScreen.setImageResource(R.drawable.cs_player_exit_fullscreen);
        if (this.exoVideoView != null) {
            this.exoVideoView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.exoVideoView.requestLayout();
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
                        CloudVideoExoPlayerActivity.this.hideVideoLoading();
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
            Drawable drawable = this.ivVideoLoading.getDrawable();
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
            Drawable drawable = this.ivVideoLoading.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).stop();
            }
            this.ivDataLoading.setVisibility(8);
        }
    }

    private void hideVideoInfo() {
        this.tvVideoInfo.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        this.tvVideoInfo.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.eventTimeStampResume = System.currentTimeMillis();
        this.eventTimeStampPause = this.eventTimeStampResume;
        getWindow().setFlags(128, 128);
        showVideoLoading(true);
        if (this.simpleExoPlayer != null && this.simpleExoPlayer.getCurrentPosition() > 0 && this.storedState == 2) {
            this.simpleExoPlayer.setPlayWhenReady(true);
            if (this.cbIsMute.isChecked()) {
                this.simpleExoPlayer.setVolume(0.0f);
            } else {
                this.simpleExoPlayer.setVolume(1.0f);
            }
            this.mHandler.post(this.getCurrentPosRunnable);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.eventTimeStampPause = System.currentTimeMillis();
        long j = this.eventTimeStampPause - this.eventTimeStampResume;
        if (j > 0) {
            CloudVideoEventLogger.EventDuration(this.model, CloudVideoEventLogger.CloudVideo_ViewTime, j, (String) null);
        }
        getWindow().clearFlags(128);
        this.tvDownloadHint.setVisibility(8);
        if (this.simpleExoPlayer != null) {
            if (this.simpleExoPlayer.getPlayWhenReady()) {
                this.storedState = 2;
            } else {
                this.storedState = 1;
            }
            this.simpleExoPlayer.setPlayWhenReady(false);
        }
        this.mHandler.removeCallbacks(this.getCurrentPosRunnable);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        if (getRequestedOrientation() != 1) {
            setOrientation(1);
        }
        if (this.getCurrentPosRunnable != null) {
            this.mHandler.removeCallbacks(this.getCurrentPosRunnable);
        }
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
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
            case R.id.ijkVideoView:
                toggleVideoCtrlViewTranslation();
                return;
            case R.id.ivDeleteCurrentVideo:
                if (!TextUtils.isEmpty(this.fileId)) {
                    doCheckAndDelete();
                    return;
                } else {
                    ToastUtil.a((int) R.string.cs_select_video);
                    return;
                }
            case R.id.ivFullScreen:
                if (getRequestedOrientation() == 1) {
                    CloudVideoEventLogger.EventClick(this.model, CloudVideoEventLogger.CloudVideo_FullScreenNumber, (String) null);
                    setOrientation(0);
                    return;
                }
                setOrientation(1);
                return;
            case R.id.ivHeaderLeftBack:
                onBackPressed();
                return;
            case R.id.ivHeaderRightSetting:
                CloudVideoNetUtils.getInstance().openCloudVideoManagePage(this, this.did);
                return;
            case R.id.ivSnapshot:
                takeSnapshot();
                return;
            case R.id.ivVideoDownload:
                downloadHint();
                return;
            case R.id.tvDownloadHint:
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
            case R.id.tvVideoInfo:
                getLinkAndPlay();
                hideVideoInfo();
                return;
            case R.id.tvsMultiSpeed:
                if (this.simpleExoPlayer != null) {
                    if (this.mSpeed == 1) {
                        this.mSpeed = 2;
                        this.cbIsMute.setChecked(true);
                        this.cbIsMute.setEnabled(false);
                    } else {
                        this.cbIsMute.setEnabled(true);
                        this.mSpeed = 1;
                    }
                    this.simpleExoPlayer.setPlaybackParameters(new PlaybackParameters((float) this.mSpeed, 1.0f));
                    TextViewS textViewS = this.tvsMultiSpeed;
                    textViewS.setText("" + this.mSpeed + "X");
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onCompletion() {
        this.mHandler.removeCallbacks(this.getCurrentPosRunnable);
        if (this.sbProgress != null && this.sbProgress.getProgress() < 100) {
            this.sbProgress.setProgress(100);
        }
        this.cbTogglePlay.setChecked(true);
    }

    /* access modifiers changed from: private */
    public void getLinkAndPlay() {
        if (!TextUtils.isEmpty(this.did) && !TextUtils.isEmpty(this.fileId)) {
            this.videoUrl = CloudVideoNetUtils.getInstance().getPlayFileUrl(this.did, this.fileId, "H265");
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
            if (!(this.exoVideoView == null || this.exoVideoView.getVideoSurfaceView() == null)) {
                this.exoVideoView.getVideoSurfaceView().setVisibility(0);
            }
            HashMap hashMap = new HashMap();
            MiServiceTokenInfo tokenInfo = CloudVideoNetUtils.getInstance().getTokenInfo();
            if (tokenInfo != null) {
                hashMap.put("Cookie", "yetAnotherServiceToken=" + tokenInfo.c);
                showVideoLoading(false);
                hideVideoInfo();
                this.simpleExoPlayer.prepare(buildMediaSource(this, this.videoUrl, hashMap));
                this.simpleExoPlayer.setPlayWhenReady(true);
                this.cbTogglePlay.setChecked(false);
                if (this.cbIsMute.isChecked()) {
                    this.simpleExoPlayer.setVolume(0.0f);
                } else {
                    this.simpleExoPlayer.setVolume(1.0f);
                }
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
        if (!this.isReadOnly) {
            if (this.llTopRightCtrl.getTranslationY() >= 0.0f) {
                this.llTopRightCtrl.setTranslationY((float) (-this.llTopRightCtrl.getHeight()));
            } else {
                this.llTopRightCtrl.setTranslationY(0.0f);
            }
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
                        if (!CloudVideoExoPlayerActivity.this.isFinishing()) {
                            CloudVideoExoPlayerActivity.this.cvlvVideos.setEnabled(true);
                            long localTimeZone = CloudVideoNetUtils.getInstance().toLocalTimeZone(((Long) obj).longValue());
                            long access$2200 = (CloudVideoExoPlayerActivity.this.currentDateTS + 86400000) - 1000;
                            CloudVideoExoPlayerActivity.this.rvrlVideoList.setLoadMore(false);
                            if (CloudVideoExoPlayerActivity.this.rvrlVideoList.isRefreshing()) {
                                CloudVideoExoPlayerActivity.this.rvrlVideoList.setRefreshing(false);
                            }
                            if (z) {
                                CloudVideoExoPlayerActivity.this.playListAdapter.clearAllData();
                            }
                            long dayTS0 = CloudVideoUtils.getDayTS0(localTimeZone);
                            long dayTS02 = CloudVideoUtils.getDayTS0(access$2200);
                            if (dayTS0 <= 0 || dayTS0 != dayTS02) {
                                CloudVideoExoPlayerActivity.this.hideDataLoading();
                                CloudVideoExoPlayerActivity.this.isTodayHasVideo();
                            } else if (list == null || list.isEmpty()) {
                                CloudVideoExoPlayerActivity.this.hideDataLoading();
                                if (CloudVideoExoPlayerActivity.this.playListAdapter != null) {
                                    CloudVideoExoPlayerActivity.this.playListAdapter.notifyDataSetChanged();
                                }
                                CloudVideoExoPlayerActivity.this.isTodayHasVideo();
                            } else {
                                final int allItemCount = CloudVideoExoPlayerActivity.this.playListAdapter.getAllItemCount();
                                new Thread(new Runnable() {
                                    public void run() {
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
                                        for (DailyList dailyList : list) {
                                            CloudVideoChildListData cloudVideoChildListData = new CloudVideoChildListData();
                                            cloudVideoChildListData.duration = dailyList.duration;
                                            cloudVideoChildListData.imgStoreId = dailyList.imgStoreId;
                                            cloudVideoChildListData.isPeople = dailyList.isHuman;
                                            cloudVideoChildListData.fileId = dailyList.fileId;
                                            cloudVideoChildListData.imgStoreUrl = CloudVideoNetUtils.getInstance().getSnapshotUrl(CloudVideoExoPlayerActivity.this.did, dailyList.fileId, dailyList.imgStoreId);
                                            cloudVideoChildListData.startTime = dailyList.createTime;
                                            CloudVideoExoPlayerActivity.this.playListAdapter.append(Integer.valueOf(simpleDateFormat.format(Long.valueOf(cloudVideoChildListData.startTime))).intValue(), cloudVideoChildListData);
                                        }
                                        CloudVideoExoPlayerActivity.this.runOnUiThread(new Runnable() {
                                            public void run() {
                                                CloudVideoChildListData currentVideoData;
                                                CloudVideoExoPlayerActivity.this.hideDataLoading();
                                                CloudVideoExoPlayerActivity.this.isTodayHasVideo();
                                                if (CloudVideoExoPlayerActivity.this.playListAdapter.getAllItemCount() == allItemCount) {
                                                    ToastUtil.a((int) R.string.cs_alarm_none_data);
                                                }
                                                if (TextUtils.isEmpty(CloudVideoExoPlayerActivity.this.fileId) && CloudVideoExoPlayerActivity.this.playListAdapter.currentPlayPosition < CloudVideoExoPlayerActivity.this.playListAdapter.getChildItemCount() && (currentVideoData = CloudVideoExoPlayerActivity.this.playListAdapter.getCurrentVideoData()) != null) {
                                                    String unused = CloudVideoExoPlayerActivity.this.fileId = currentVideoData.fileId;
                                                    long unused2 = CloudVideoExoPlayerActivity.this.startTime = currentVideoData.startTime;
                                                    long unused3 = CloudVideoExoPlayerActivity.this.duration = currentVideoData.duration;
                                                    CloudVideoExoPlayerActivity.this.getLinkAndPlay();
                                                }
                                                CloudVideoExoPlayerActivity.this.playListAdapter.setCurrentPlayItem(CloudVideoExoPlayerActivity.this.fileId);
                                                CloudVideoExoPlayerActivity.this.playListAdapter.notifyDataSetChanged();
                                                CloudVideoExoPlayerActivity.this.isTodayHasVideo();
                                            }
                                        });
                                    }
                                }).start();
                            }
                        }
                    }

                    public void onCloudVideoFailed(int i, String str) {
                        if (!CloudVideoExoPlayerActivity.this.isFinishing()) {
                            if (i == -90004) {
                                ToastUtil.a((CharSequence) CloudVideoExoPlayerActivity.this.getString(R.string.cs_alarm_none_data));
                            }
                            if (z && -90002 == i && CloudVideoExoPlayerActivity.this.playListAdapter != null) {
                                CloudVideoExoPlayerActivity.this.playListAdapter.clearAllData();
                            }
                            if (CloudVideoExoPlayerActivity.this.playListAdapter != null) {
                                CloudVideoExoPlayerActivity.this.playListAdapter.notifyDataSetChanged();
                            }
                            CloudVideoExoPlayerActivity.this.cvlvVideos.setEnabled(true);
                            CloudVideoExoPlayerActivity.this.rvrlVideoList.setLoadMore(false);
                            if (CloudVideoExoPlayerActivity.this.rvrlVideoList.isRefreshing()) {
                                CloudVideoExoPlayerActivity.this.rvrlVideoList.setRefreshing(false);
                            }
                            CloudVideoExoPlayerActivity.this.hideDataLoading();
                            CloudVideoExoPlayerActivity.this.isTodayHasVideo();
                            LogUtil.b(CloudVideoExoPlayerActivity.TAG, "errorCode:" + i + " errorInfo:" + str);
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

    private void getVideoDatesSerial() {
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
                    this.datesUpdateVar++;
                    CloudVideoNetUtils.getInstance().getVideoDatesSerial(this, jSONObject.toString(), new ICloudVideoCallback<List<StatsRecord2>>() {
                        public void onCloudVideoSuccess(List<StatsRecord2> list, Object obj) {
                            if (!CloudVideoExoPlayerActivity.this.isFinishing()) {
                                CloudVideoExoPlayerActivity.access$2910(CloudVideoExoPlayerActivity.this);
                                CloudVideoExoPlayerActivity.this.hideVideoLoading();
                                for (StatsRecord2 next : list) {
                                    for (CloudVideoDate next2 : CloudVideoExoPlayerActivity.this.dateListViewAdapter.cloudVideoDates) {
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
                                CloudVideoExoPlayerActivity.this.dateListViewAdapter.notifyDataSetChanged();
                                if (CloudVideoExoPlayerActivity.this.datesUpdateVar == 0 && CloudVideoExoPlayerActivity.this.dateListViewAdapter != null && CloudVideoExoPlayerActivity.this.dateListViewAdapter.getItemCount() > 0) {
                                    CloudVideoDate cloudVideoDate = (CloudVideoDate) CloudVideoExoPlayerActivity.this.dateListViewAdapter.getItem(CloudVideoExoPlayerActivity.this.dateListViewAdapter.selectedItemPosition >= 0 ? CloudVideoExoPlayerActivity.this.dateListViewAdapter.selectedItemPosition : CloudVideoExoPlayerActivity.this.dateListViewAdapter.getItemCount() - 1);
                                    CloudVideoExoPlayerActivity.this.getPlayListLimit(cloudVideoDate, cloudVideoDate.timeStamp, cloudVideoDate.timeStamp + CloudVideoListActivity.DAY_END_MILLIS, true, true);
                                }
                            }
                        }

                        public void onCloudVideoFailed(int i, String str) {
                            if (!CloudVideoExoPlayerActivity.this.isFinishing()) {
                                CloudVideoExoPlayerActivity.access$2910(CloudVideoExoPlayerActivity.this);
                                if (CloudVideoExoPlayerActivity.this.datesUpdateVar == 0 && CloudVideoExoPlayerActivity.this.dateListViewAdapter != null && CloudVideoExoPlayerActivity.this.dateListViewAdapter.getItemCount() > 0) {
                                    CloudVideoDate cloudVideoDate = (CloudVideoDate) CloudVideoExoPlayerActivity.this.dateListViewAdapter.getItem(CloudVideoExoPlayerActivity.this.dateListViewAdapter.selectedItemPosition >= 0 ? CloudVideoExoPlayerActivity.this.dateListViewAdapter.selectedItemPosition : CloudVideoExoPlayerActivity.this.dateListViewAdapter.getItemCount() - 1);
                                    CloudVideoExoPlayerActivity.this.getPlayListLimit(cloudVideoDate, cloudVideoDate.timeStamp, CloudVideoListActivity.DAY_END_MILLIS + cloudVideoDate.timeStamp, true, true);
                                }
                                CloudVideoExoPlayerActivity.this.hideVideoLoading();
                                CloudVideoExoPlayerActivity.this.isTodayHasVideo();
                                LogUtil.b(CloudVideoExoPlayerActivity.TAG, "errorCode:" + i + ":" + str);
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
        CloudVideoEventLogger.EventClick(this.model, CloudVideoEventLogger.CloudVideo_DeleteNumber, (String) null);
        new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.cs_delete_video)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("did", CloudVideoExoPlayerActivity.this.did);
                    jSONObject.put("model", CloudVideoExoPlayerActivity.this.model);
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(CloudVideoExoPlayerActivity.this.fileId);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("fileIds", jSONArray);
                    jSONObject.put("fileIds", jSONObject2);
                    CloudVideoExoPlayerActivity.this.showDataLoading();
                    CloudVideoNetUtils.getInstance().deleteFiles(CloudVideoExoPlayerActivity.this.getContext(), jSONObject.toString(), new ICloudVideoCallback<String>() {
                        public void onCloudVideoSuccess(String str, Object obj) {
                            if (!CloudVideoExoPlayerActivity.this.isFinishing()) {
                                String unused = CloudVideoExoPlayerActivity.this.fileId = null;
                                CloudVideoExoPlayerActivity.this.tvVideoStart.setText(R.string.cs_time_0);
                                CloudVideoExoPlayerActivity.this.tvVideoEnd.setText(R.string.cs_time_0);
                                CloudVideoExoPlayerActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        CloudVideoExoPlayerActivity.this.hideDataLoading();
                                        if (CloudVideoExoPlayerActivity.this.simpleExoPlayer != null) {
                                            CloudVideoExoPlayerActivity.this.simpleExoPlayer.stop();
                                            if (!(CloudVideoExoPlayerActivity.this.exoVideoView == null || CloudVideoExoPlayerActivity.this.exoVideoView.getVideoSurfaceView() == null)) {
                                                CloudVideoExoPlayerActivity.this.exoVideoView.getVideoSurfaceView().setVisibility(8);
                                            }
                                        }
                                        CloudVideoDate cloudVideoDate = (CloudVideoDate) CloudVideoExoPlayerActivity.this.dateListViewAdapter.getItem(CloudVideoExoPlayerActivity.this.dateListViewAdapter.selectedItemPosition);
                                        CloudVideoExoPlayerActivity.this.getPlayListLimit(cloudVideoDate, cloudVideoDate.timeStamp, CloudVideoListActivity.DAY_END_MILLIS + cloudVideoDate.timeStamp, true, true);
                                    }
                                });
                            }
                        }

                        public void onCloudVideoFailed(int i, String str) {
                            if (!CloudVideoExoPlayerActivity.this.isFinishing()) {
                                CloudVideoExoPlayerActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        CloudVideoExoPlayerActivity.this.hideDataLoading();
                                    }
                                });
                                LogUtil.b(CloudVideoExoPlayerActivity.TAG, "errorCode:" + i + " errorInfo:" + str);
                            }
                        }
                    });
                } catch (JSONException unused) {
                    CloudVideoExoPlayerActivity.this.hideDataLoading();
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
        CloudVideoEventLogger.EventClick(this.model, CloudVideoEventLogger.CloudVideo_ScreenshotNumber, (String) null);
        if (this.exoVideoView != null && (this.exoVideoView.getVideoSurfaceView() instanceof TextureView)) {
            final Bitmap bitmap = ((TextureView) this.exoVideoView.getVideoSurfaceView()).getBitmap();
            if (bitmap != null) {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            final String generateFilepath = CloudVideoFileUtils.generateFilepath(false, CloudVideoExoPlayerActivity.this.did);
                            FileOutputStream fileOutputStream = new FileOutputStream(generateFilepath);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 0, fileOutputStream);
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            CloudVideoExoPlayerActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    ContentValues contentValues = new ContentValues(4);
                                    contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
                                    contentValues.put(Downloads._DATA, generateFilepath);
                                    contentValues.put("mime_type", "image/jpeg");
                                    try {
                                        if (!Build.MODEL.equals("HM 1SC")) {
                                            CloudVideoExoPlayerActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                                        }
                                        LogUtil.a(CloudVideoExoPlayerActivity.TAG, "snap success");
                                        CloudVideoExoPlayerActivity.this.ivImage.setImageBitmap(bitmap);
                                        CloudVideoExoPlayerActivity.this.ivImage.setVisibility(0);
                                        CloudVideoExoPlayerActivity.this.ivImage.bringToFront();
                                        CloudVideoExoPlayerActivity.this.ivImage.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View view) {
                                                Intent intent = new Intent(CloudVideoExoPlayerActivity.this, CloudVideoLocalPicActivity.class);
                                                intent.putExtra(TbsReaderView.KEY_FILE_PATH, generateFilepath);
                                                CloudVideoExoPlayerActivity.this.startActivity(intent);
                                                CloudVideoExoPlayerActivity.this.ivImage.setVisibility(8);
                                            }
                                        });
                                        CloudVideoExoPlayerActivity.this.mHandler.postDelayed(new Runnable() {
                                            public void run() {
                                                CloudVideoExoPlayerActivity.this.ivImage.setVisibility(8);
                                            }
                                        }, 5000);
                                    } catch (Throwable unused) {
                                    }
                                }
                            });
                        } catch (IOException e) {
                            LogUtil.a(CloudVideoExoPlayerActivity.TAG, "IOException:" + e.getLocalizedMessage());
                        }
                    }
                }).start();
            } else {
                LogUtil.b(TAG, "bitmap null");
            }
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
        CloudVideoEventLogger.EventClick(this.model, CloudVideoEventLogger.CloudVideo_DownloadNumber, (String) null);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Device b = SmartHomeDeviceManager.a().b(this.did);
        if (b != null) {
            ArrayList arrayList = new ArrayList();
            CloudVideoDownloadInfo cloudVideoDownloadInfo = new CloudVideoDownloadInfo();
            cloudVideoDownloadInfo.uid = b.userId;
            cloudVideoDownloadInfo.did = this.did;
            cloudVideoDownloadInfo.title = this.title;
            cloudVideoDownloadInfo.videoUrl = CloudVideoNetUtils.getInstance().getPlayFileUrl(this.did, this.fileId, "H265");
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
                if (CloudVideoExoPlayerActivity.this.tvDownloadHint != null) {
                    CloudVideoExoPlayerActivity.this.tvDownloadHint.setVisibility(8);
                }
            }
        }, 3000);
    }

    /* access modifiers changed from: private */
    public void seekTo(int i) {
        if (i >= 0 && this.simpleExoPlayer != null && this.simpleExoPlayer.getDuration() > 0) {
            this.simpleExoPlayer.seekTo((long) ((int) ((((long) i) * this.simpleExoPlayer.getDuration()) / 100)));
        }
    }

    /* access modifiers changed from: private */
    public void setProgressTime(long j) {
        long duration2 = this.simpleExoPlayer.getDuration();
        if (j >= 0 && duration2 > 0) {
            int ceil = (int) Math.ceil((double) ((100 * j) / duration2));
            if (ceil >= 100 || j / 1000 >= duration2 / 1000) {
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
                CloudVideoExoPlayerActivity.this.doDownload();
                CloudVideoExoPlayerActivity.this.showDownloadActivityHint();
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
        Device b = SmartHomeDeviceManager.a().b(this.did);
        if (b == null) {
            return false;
        }
        for (CloudVideoDownloadInfo cloudVideoDownloadInfo : CloudVideoDownloadManager.getInstance().getRecordsFromDB(b.userId, this.did)) {
            if (Math.abs(cloudVideoDownloadInfo.startTime - this.startTime) < 1) {
                return true;
            }
        }
        return false;
    }

    public String longToDate(long j) {
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

    private MediaSource buildMediaSource(Context context, String str, Map<String, String> map) {
        Uri parse = Uri.parse(str);
        if (TextUtils.isEmpty(parse.getScheme()) || (!parse.getScheme().equals("http") && !parse.getScheme().equals("https"))) {
            return buildMediaSourceLocal(parse);
        }
        return buildMediaSourceHTTP(context, parse, map);
    }

    private HlsMediaSource buildMediaSourceHTTP(Context context, Uri uri, Map<String, String> map) {
        DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory(Util.getUserAgent(context, "SmartHome;Android"), new DefaultBandwidthMeter());
        if (map != null && map.size() > 0) {
            for (Map.Entry next : map.entrySet()) {
                defaultHttpDataSourceFactory.setDefaultRequestProperty("" + ((String) next.getKey()), "" + ((String) next.getValue()));
            }
        }
        return new HlsMediaSource.Factory((DataSource.Factory) defaultHttpDataSourceFactory).createMediaSource(uri);
    }

    private MediaSource buildMediaSourceLocal(Uri uri) {
        return new HlsMediaSource.Factory((DataSource.Factory) new FileDataSourceFactory()).createMediaSource(uri);
    }
}
