package com.xiaomi.smarthome.camera.activity.alarm2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.Utils.FileUtil;
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
import com.mijia.app.Event;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.mijia.model.alarm.AlarmNetUtils;
import com.mijia.model.alarmcloud.AlarmCloudCallback;
import com.mijia.model.alarmcloud.AlarmCloudManager;
import com.mijia.model.alarmcloud.AlarmVideo;
import com.miui.tsmclient.util.StringUtils;
import com.p2p.audio.AudioFoucsTool;
import com.smarthome_camera.BuildConfig;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.activity.alarm.AlarmDayV2Activity;
import com.xiaomi.smarthome.camera.activity.alarm.AlarmSettingV2Activity;
import com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter;
import com.xiaomi.smarthome.camera.activity.alarm2.adapter.RecyclerEventTypeAdapter;
import com.xiaomi.smarthome.camera.activity.alarm2.bean.EventType;
import com.xiaomi.smarthome.camera.activity.alarm2.util.AnimUtil;
import com.xiaomi.smarthome.camera.activity.alarm2.util.TopSmoothScroller;
import com.xiaomi.smarthome.camera.view.calendar.CEn7DayRecyclerAdapterNew;
import com.xiaomi.smarthome.camera.view.calendar.CalendarDate;
import com.xiaomi.smarthome.camera.view.calendar.DateUtils;
import com.xiaomi.smarthome.camera.view.calendar.YdCatCalendarView;
import com.xiaomi.smarthome.camera.view.recycle.RecyclerViewRefreshLayout;
import com.xiaomi.smarthome.camera.view.recycle.RecyclerViewRefreshLayoutEx;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoDownloadActivity;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoDownloadManager;
import com.xiaomi.smarthome.miio.camera.cloudstorage.exopackage.MJExoPlayerViewP;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoUserStatus;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.shop.utils.DisplayUtils;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class AlarmVideoNormalPlayerActivity extends CameraBaseActivity implements View.OnClickListener {
    public static final int PLAYER_STATE_IDLE = 0;
    public static final int PLAYER_STATE_PAUSE = 1;
    public static final int PLAYER_STATE_PLAY = 2;
    private static final int REQUEST_CODE_SHARE = 1;
    public static final String TAG = "AlarmVideoNormalPlayerActivity";
    private static final String[] mDevicePropKeys = {"electricity"};
    /* access modifiers changed from: private */
    public View calendar_container;
    /* access modifiers changed from: private */
    public CheckBox cbIsMute;
    /* access modifiers changed from: private */
    public CheckBox cbIsMuteLand;
    /* access modifiers changed from: private */
    public CheckBox cbTogglePlay;
    /* access modifiers changed from: private */
    public CheckBox cbTogglePlayLand;
    /* access modifiers changed from: private */
    public long createTime;
    /* access modifiers changed from: private */
    public int currentPosition = 0;
    YdCatCalendarView custom_calendar;
    /* access modifiers changed from: private */
    public boolean dateNoneDefault;
    private long endTime;
    RecyclerEventTypeAdapter eventTypeAdapter;
    /* access modifiers changed from: private */
    public String[] eventTypes = {"Default", AlarmCloudManager.OBJECT_MOTION, AlarmCloudManager.PEOPLE_MOTION, AlarmCloudManager.BABY_CRY, AlarmCloudManager.FACE, AlarmCloudManager.AI};
    /* access modifiers changed from: private */
    public String[] eventTypesDesc = {SHApplication.getAppContext().getResources().getString(R.string.event_type_all), SHApplication.getAppContext().getResources().getString(R.string.event_type_obj_motion), SHApplication.getAppContext().getResources().getString(R.string.event_type_people_motion), SHApplication.getAppContext().getResources().getString(R.string.event_type_baby_cry), SHApplication.getAppContext().getString(R.string.unknown_people_desc), SHApplication.getAppContext().getResources().getString(R.string.event_type_ai)};
    RecyclerView event_type_recycler;
    private MJExoPlayerViewP exoVideoView;
    /* access modifiers changed from: private */
    public String fileId;
    private FrameLayout flControlContainer;
    private FrameLayout flTitleBar;
    private FrameLayout flVideoContainer;
    /* access modifiers changed from: private */
    public Runnable getCurrentPosRunnable = new Runnable() {
        public void run() {
            if (AlarmVideoNormalPlayerActivity.this.simpleExoPlayer != null) {
                final long currentPosition = AlarmVideoNormalPlayerActivity.this.simpleExoPlayer.getCurrentPosition();
                AlarmVideoNormalPlayerActivity.this.setProgressTime(currentPosition);
                if (AlarmVideoNormalPlayerActivity.this.simpleExoPlayer.getPlayWhenReady()) {
                    AlarmVideoNormalPlayerActivity.this.cbTogglePlay.setChecked(false);
                    AlarmVideoNormalPlayerActivity.this.cbTogglePlayLand.setChecked(false);
                } else {
                    AlarmVideoNormalPlayerActivity.this.cbTogglePlay.setChecked(true);
                    AlarmVideoNormalPlayerActivity.this.cbTogglePlayLand.setChecked(true);
                }
                AlarmVideoNormalPlayerActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        AlarmVideoNormalPlayerActivity.this.simpleExoPlayer.getDuration();
                        AlarmVideoNormalPlayerActivity.this.setStartTime(currentPosition);
                    }
                });
                AlarmVideoNormalPlayerActivity.this.mHandler.postDelayed(AlarmVideoNormalPlayerActivity.this.getCurrentPosRunnable, 500);
            }
        }
    };
    /* access modifiers changed from: private */
    public int index;
    /* access modifiers changed from: private */
    public boolean isAlarm;
    /* access modifiers changed from: private */
    public boolean isNetRefreshing;
    boolean isPlaying = false;
    private boolean isSharing;
    private ImageView ivDeleteCurrentVideo;
    private ImageView ivFullScreen;
    private ImageView ivFullScreenLand;
    private View ivVideoBack;
    private ImageView ivVideoDownload;
    private ImageView ivVideoLoading;
    private View ivVideoShare;
    /* access modifiers changed from: private */
    public ImageView ivVideoViewCover;
    ImageView iv_more_record;
    ImageView iv_next_day;
    private View llTopRightCtrl;
    LinearLayout ll_calendar_bg;
    LinearLayout ll_empty_content;
    private int loadTimes;
    private View loadingCircle;
    private View loadingContainer;
    /* access modifiers changed from: private */
    public AlarmVideoAdapter mAlarmVideoAdapter = null;
    CloudVideoDownloadManager.ICloudVideoDownloadListener mCloudVideoDownloadListener = new CloudVideoDownloadManager.ICloudVideoDownloadListener() {
        public void onInfo(CloudVideoDownloadInfo cloudVideoDownloadInfo, int i) {
        }

        public void onProgress(CloudVideoDownloadInfo cloudVideoDownloadInfo, int i) {
        }

        public void onSpeed(CloudVideoDownloadInfo cloudVideoDownloadInfo, int i) {
        }

        public void onStart(CloudVideoDownloadInfo cloudVideoDownloadInfo) {
        }

        public void onStop(CloudVideoDownloadInfo cloudVideoDownloadInfo, int i) {
        }

        public void onFinish(CloudVideoDownloadInfo cloudVideoDownloadInfo) {
            LogUtil.a(AlarmVideoNormalPlayerActivity.TAG, "onFinish " + cloudVideoDownloadInfo.m3u8LocalPath);
            if (cloudVideoDownloadInfo.fileId.equals(AlarmVideoNormalPlayerActivity.this.fileId)) {
                if (!AlarmVideoNormalPlayerActivity.this.isAlarm) {
                    ToastUtil.a((int) R.string.save_success);
                } else {
                    AlarmVideoNormalPlayerActivity.this.m3u8ToMp4(cloudVideoDownloadInfo.m3u8LocalPath);
                }
            }
        }

        public void onCancelled(CloudVideoDownloadInfo cloudVideoDownloadInfo) {
            AlarmVideoNormalPlayerActivity.this.hideLoading();
            if (!AlarmVideoNormalPlayerActivity.this.isAlarm) {
                ToastUtil.a((int) R.string.save_fail);
            }
        }

        public void onError(CloudVideoDownloadInfo cloudVideoDownloadInfo, int i) {
            AlarmVideoNormalPlayerActivity.this.hideLoading();
            if (!AlarmVideoNormalPlayerActivity.this.isAlarm) {
                ToastUtil.a((int) R.string.save_success);
            }
        }
    };
    /* access modifiers changed from: private */
    public EventType mCurEventType = new EventType();
    /* access modifiers changed from: private */
    public long mEndPoint = 0;
    /* access modifiers changed from: private */
    public long mEndTime = 0;
    LayoutInflater mLayoutInflater;
    private SceneManager.IScenceListener mListener = new SceneManager.IScenceListener() {
        public void onRefreshScenceFailed(int i) {
        }

        public void onRefreshScenceSuccess(int i) {
            List<SceneApi.SmartHomeScene> list;
            if (AlarmVideoNormalPlayerActivity.this.isValid()) {
                if (!SceneManager.x().n()) {
                    list = SceneManager.x().j();
                } else {
                    list = SceneManager.x().d(AlarmVideoNormalPlayerActivity.this.mCameraDevice.getDid());
                }
                if (list != null && list.size() > 0 && AlarmVideoNormalPlayerActivity.this.eventTypeAdapter != null && AlarmVideoNormalPlayerActivity.this.eventTypeAdapter.getItemCount() > 0 && !AlarmVideoNormalPlayerActivity.this.eventTypeAdapter.containsAiType()) {
                    EventType eventType = new EventType();
                    eventType.name = AlarmVideoNormalPlayerActivity.this.eventTypes[AlarmVideoNormalPlayerActivity.this.eventTypes.length - 1];
                    eventType.desc = AlarmVideoNormalPlayerActivity.this.eventTypesDesc[AlarmVideoNormalPlayerActivity.this.eventTypesDesc.length - 1];
                    AlarmVideoNormalPlayerActivity.this.eventTypeAdapter.addEvent(eventType);
                }
            }
        }
    };
    private ImageView mLoadingView;
    private boolean mNeedLicense = false;
    /* access modifiers changed from: private */
    public List<AlarmVideo> mRecordList = new ArrayList();
    private SimpleDateFormat mSdf = new SimpleDateFormat(StringUtils.EXPECT_TIME_FORMAT, Locale.CHINA);
    /* access modifiers changed from: private */
    public long mStartPoint = 0;
    /* access modifiers changed from: private */
    public long mStartTime = 0;
    private SimpleDateFormat mTimeFormat;
    private CloudVideoUserStatus mUserStatus;
    /* access modifiers changed from: private */
    public int mVisibleItemCount;
    /* access modifiers changed from: private */
    public int offset;
    RecyclerViewRefreshLayoutEx ptrLayout;
    private View rlVideoViewBottomCtrl;
    private View rlVideoViewBottomCtrlLand;
    RecyclerView rv_event_list;
    private SeekBar sbProgress;
    private SeekBar sbProgressLand;
    private CalendarDate selectedDate;
    /* access modifiers changed from: private */
    public SimpleExoPlayer simpleExoPlayer;
    private long storedPlayPosition = 0;
    private int storedState = 0;
    TextView sub_title_bar_title;
    private int targetHeight = -1;
    ImageView title_bar_more;
    ImageView title_bar_redpoint;
    TextView title_bar_title;
    /* access modifiers changed from: private */
    public TextView tvCloudStorageHint;
    /* access modifiers changed from: private */
    public View tvDownloadHint;
    private TextView tvVideoEnd;
    private TextView tvVideoEndLand;
    private TextView tvVideoInfo;
    private TextView tvVideoStart;
    private TextView tvVideoStartLand;
    private TextView tvVideoTitle;
    TextView tv_all_record;
    TextView tv_cur_day;
    TextView tv_request_result;

    public void doCreate(Bundle bundle) {
        Event.a(Event.aK);
        super.doCreate(bundle);
        setContentView(R.layout.camera_activity_alarm_video_normal_player);
        if (!getIntent().getBooleanExtra("pincod", false)) {
            enableVerifyPincode();
        }
        initVideo();
        initVideoList();
        getVideoUrl(true);
        this.loadingContainer = findViewById(R.id.loading_area);
        this.loadingCircle = findViewById(R.id.image_circle);
        this.loadingContainer.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    public void finish() {
        setResult(-1);
        super.finish();
    }

    public void onDestroy() {
        super.onDestroy();
        CloudVideoDownloadManager.getInstance().removeListener(this.mCloudVideoDownloadListener);
    }

    private void initVideo() {
        this.fileId = getIntent().getStringExtra("fileId");
        this.offset = getIntent().getIntExtra("offset", 0);
        this.createTime = getIntent().getLongExtra("createTime", 0);
        this.isAlarm = getIntent().getBooleanExtra("isAlarm", true);
        this.flTitleBar = (FrameLayout) findViewById(R.id.title_bar);
        this.llTopRightCtrl = findViewById(R.id.llTopRightCtrl);
        if (this.mCameraDevice.isReadOnlyShared()) {
            this.llTopRightCtrl.setVisibility(8);
        }
        this.rlVideoViewBottomCtrl = findViewById(R.id.rlVideoViewBottomCtrl);
        this.rlVideoViewBottomCtrlLand = findViewById(R.id.rlVideoViewBottomCtrlLand);
        this.ivVideoLoading = (ImageView) findViewById(R.id.ivVideoLoading);
        this.tvVideoInfo = (TextView) findViewById(R.id.tvVideoInfo);
        this.tvVideoInfo.setOnClickListener(this);
        this.ivDeleteCurrentVideo = (ImageView) findViewById(R.id.ivDeleteCurrentVideo);
        this.ivDeleteCurrentVideo.setOnClickListener(this);
        this.ivVideoDownload = (ImageView) findViewById(R.id.ivVideoDownload);
        this.ivVideoDownload.setVisibility(0);
        this.ivVideoDownload.setOnClickListener(this);
        this.ivVideoShare = findViewById(R.id.ivVideoShare);
        this.ivVideoShare.setOnClickListener(this);
        this.ivVideoBack = findViewById(R.id.ivVideoBack);
        this.ivVideoBack.setOnClickListener(this);
        this.tvVideoTitle = (TextView) findViewById(R.id.tvVideoTitle);
        this.tvVideoTitle.setText(getString(R.string.housekeeping));
        this.ivVideoViewCover = (ImageView) findViewById(R.id.ivVideoViewCover);
        this.tvDownloadHint = findViewById(R.id.tvDownloadHint);
        this.tvDownloadHint.setOnClickListener(this);
        this.tvCloudStorageHint = (TextView) findViewById(R.id.tvCloudStorageHint);
        this.tvCloudStorageHint.setText(R.string.alarm_cloud_no_buy_tip);
        this.tvCloudStorageHint.setOnClickListener(this);
        initShareView(this.isAlarm);
        initPlayer();
        this.flVideoContainer = (FrameLayout) findViewById(R.id.fl_video_container);
        this.flControlContainer = (FrameLayout) findViewById(R.id.fl_control_container);
    }

    /* access modifiers changed from: private */
    public void initShareView(boolean z) {
        if (!z) {
            this.ivVideoShare.setVisibility(8);
        } else {
            this.ivVideoShare.setVisibility(0);
        }
    }

    private void initPlayer() {
        this.exoVideoView = (MJExoPlayerViewP) findViewById(R.id.exoVideoView);
        this.exoVideoView.setPlayerViewListener(new MJExoPlayerViewP.PlayerViewListener() {
            public void onVideoSizeChanged(int i, int i2, int i3, float f) {
                AlarmVideoNormalPlayerActivity.this.getRequestedOrientation();
            }

            public void onRenderedFirstFrame() {
                if (AlarmVideoNormalPlayerActivity.this.simpleExoPlayer != null) {
                    AlarmVideoNormalPlayerActivity.this.setEndTime(AlarmVideoNormalPlayerActivity.this.simpleExoPlayer.getDuration());
                }
                AlarmVideoNormalPlayerActivity.this.mHandler.post(AlarmVideoNormalPlayerActivity.this.getCurrentPosRunnable);
                AlarmVideoNormalPlayerActivity.this.hideVideoLoading();
            }
        });
        this.simpleExoPlayer = ExoPlayerFactory.newSimpleInstance((Context) this, (TrackSelector) new DefaultTrackSelector((TrackSelection.Factory) new AdaptiveTrackSelection.Factory(new DefaultBandwidthMeter())));
        this.exoVideoView.setPlayer(this.simpleExoPlayer);
        if (!(this.exoVideoView == null || this.exoVideoView.getVideoSurfaceView() == null)) {
            this.exoVideoView.getVideoSurfaceView().setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AlarmVideoNormalPlayerActivity.this.toggleVideoCtrlViewTranslation();
                }
            });
        }
        if (this.simpleExoPlayer != null) {
            this.simpleExoPlayer.addListener(new Player.EventListener() {
                public void onTimelineChanged(Timeline timeline, Object obj, int i) {
                    LogUtil.a(AlarmVideoNormalPlayerActivity.TAG, "onTimelineChanged:" + i);
                }

                public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
                    LogUtil.a(AlarmVideoNormalPlayerActivity.TAG, "onTracksChanged");
                }

                public void onLoadingChanged(boolean z) {
                    if (z) {
                        AlarmVideoNormalPlayerActivity.this.showVideoLoading(false);
                    } else {
                        AlarmVideoNormalPlayerActivity.this.hideVideoLoading();
                    }
                    LogUtil.a(AlarmVideoNormalPlayerActivity.TAG, "onLoadingChanged:" + z);
                }

                public void onPlayerStateChanged(boolean z, int i) {
                    switch (i) {
                        case 1:
                            AlarmVideoNormalPlayerActivity.this.hideVideoLoading();
                            break;
                        case 2:
                            AlarmVideoNormalPlayerActivity.this.showVideoLoading(false);
                            break;
                        case 3:
                            AlarmVideoNormalPlayerActivity.this.hideVideoLoading();
                            break;
                        case 4:
                            AlarmVideoNormalPlayerActivity.this.hideVideoLoading();
                            AlarmVideoNormalPlayerActivity.this.simpleExoPlayer.seekTo(0);
                            AlarmVideoNormalPlayerActivity.this.onCompletion();
                            AlarmVideoNormalPlayerActivity.this.mHandler.removeCallbacks(AlarmVideoNormalPlayerActivity.this.getCurrentPosRunnable);
                            break;
                    }
                    LogUtil.a(AlarmVideoNormalPlayerActivity.TAG, "onPlayerStateChanged:" + z + " playbackState:" + i);
                }

                public void onRepeatModeChanged(int i) {
                    LogUtil.a(AlarmVideoNormalPlayerActivity.TAG, "onRepeatModeChanged:" + i);
                }

                public void onShuffleModeEnabledChanged(boolean z) {
                    LogUtil.a(AlarmVideoNormalPlayerActivity.TAG, "onShuffleModeEnabledChanged:" + z);
                }

                public void onPlayerError(ExoPlaybackException exoPlaybackException) {
                    AlarmVideoNormalPlayerActivity.this.hideVideoLoading();
                    if (Util.b() && !AlarmVideoNormalPlayerActivity.this.mCameraDevice.isShared()) {
                        if (!AlarmVideoNormalPlayerActivity.this.hideCloudVideoBuyTips()) {
                            AlarmVideoNormalPlayerActivity.this.tvCloudStorageHint.setVisibility(0);
                        } else {
                            return;
                        }
                    }
                    if (exoPlaybackException != null) {
                        LogUtil.a(AlarmVideoNormalPlayerActivity.TAG, "onPlayerError:" + exoPlaybackException.getLocalizedMessage() + "" + exoPlaybackException.type + " error:" + exoPlaybackException.toString());
                    }
                }

                public void onPositionDiscontinuity(int i) {
                    LogUtil.a(AlarmVideoNormalPlayerActivity.TAG, "onPositionDiscontinuity:" + i);
                }

                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                    LogUtil.a(AlarmVideoNormalPlayerActivity.TAG, "onPlaybackParametersChanged:" + playbackParameters.speed);
                }

                public void onSeekProcessed() {
                    LogUtil.a(AlarmVideoNormalPlayerActivity.TAG, "onSeekProcessed");
                }
            });
        } else {
            LogUtil.b(TAG, "simpleExoPlayer null");
        }
        this.cbTogglePlay = (CheckBox) findViewById(R.id.cbTogglePlay);
        this.cbTogglePlayLand = (CheckBox) findViewById(R.id.cbTogglePlayLand);
        this.cbTogglePlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AlarmVideoNormalPlayerActivity.this.simpleExoPlayer.setPlayWhenReady(false);
                    AlarmVideoNormalPlayerActivity.this.mHandler.removeCallbacks(AlarmVideoNormalPlayerActivity.this.getCurrentPosRunnable);
                    if (Util.b() && !AlarmVideoNormalPlayerActivity.this.mCameraDevice.isShared()) {
                        if (!AlarmVideoNormalPlayerActivity.this.hideCloudVideoBuyTips()) {
                            AlarmVideoNormalPlayerActivity.this.tvCloudStorageHint.setVisibility(0);
                        } else {
                            return;
                        }
                    }
                } else {
                    AlarmVideoNormalPlayerActivity.this.simpleExoPlayer.setPlayWhenReady(true);
                    AlarmVideoNormalPlayerActivity.this.ivVideoViewCover.setVisibility(8);
                    AlarmVideoNormalPlayerActivity.this.mHandler.post(AlarmVideoNormalPlayerActivity.this.getCurrentPosRunnable);
                    AlarmVideoNormalPlayerActivity.this.tvCloudStorageHint.setVisibility(8);
                }
                if (z != AlarmVideoNormalPlayerActivity.this.cbTogglePlayLand.isChecked()) {
                    AlarmVideoNormalPlayerActivity.this.cbTogglePlayLand.setChecked(z);
                }
            }
        });
        this.cbTogglePlayLand.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AlarmVideoNormalPlayerActivity.this.simpleExoPlayer.setPlayWhenReady(false);
                    AlarmVideoNormalPlayerActivity.this.mHandler.removeCallbacks(AlarmVideoNormalPlayerActivity.this.getCurrentPosRunnable);
                    if (Util.b() && !AlarmVideoNormalPlayerActivity.this.mCameraDevice.isShared()) {
                        if (!AlarmVideoNormalPlayerActivity.this.hideCloudVideoBuyTips()) {
                            AlarmVideoNormalPlayerActivity.this.tvCloudStorageHint.setVisibility(0);
                        } else {
                            return;
                        }
                    }
                } else {
                    AlarmVideoNormalPlayerActivity.this.simpleExoPlayer.setPlayWhenReady(true);
                    AlarmVideoNormalPlayerActivity.this.ivVideoViewCover.setVisibility(8);
                    AlarmVideoNormalPlayerActivity.this.mHandler.post(AlarmVideoNormalPlayerActivity.this.getCurrentPosRunnable);
                    AlarmVideoNormalPlayerActivity.this.tvCloudStorageHint.setVisibility(8);
                }
                if (z != AlarmVideoNormalPlayerActivity.this.cbTogglePlay.isChecked()) {
                    AlarmVideoNormalPlayerActivity.this.cbTogglePlay.setChecked(z);
                }
            }
        });
        this.cbIsMute = (CheckBox) findViewById(R.id.cbIsMute);
        this.cbIsMuteLand = (CheckBox) findViewById(R.id.cbIsMuteLand);
        this.cbIsMute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.aL);
                if (z) {
                    AlarmVideoNormalPlayerActivity.this.simpleExoPlayer.setVolume(0.0f);
                    AudioFoucsTool.b(AlarmVideoNormalPlayerActivity.this, "audioFoucs");
                } else {
                    AlarmVideoNormalPlayerActivity.this.simpleExoPlayer.setVolume(1.0f);
                    AudioFoucsTool.a(AlarmVideoNormalPlayerActivity.this, "audioFoucs");
                }
                if (z != AlarmVideoNormalPlayerActivity.this.cbIsMuteLand.isChecked()) {
                    AlarmVideoNormalPlayerActivity.this.cbIsMuteLand.setChecked(z);
                }
            }
        });
        this.cbIsMuteLand.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.aL);
                if (z != AlarmVideoNormalPlayerActivity.this.cbIsMute.isChecked()) {
                    AlarmVideoNormalPlayerActivity.this.cbIsMute.setChecked(z);
                }
                if (z) {
                    AlarmVideoNormalPlayerActivity.this.simpleExoPlayer.setVolume(0.0f);
                } else {
                    AlarmVideoNormalPlayerActivity.this.simpleExoPlayer.setVolume(1.0f);
                }
            }
        });
        this.cbIsMute.setChecked(true);
        this.cbIsMuteLand.setChecked(true);
        this.sbProgress = (SeekBar) findViewById(R.id.sbProgress);
        this.sbProgressLand = (SeekBar) findViewById(R.id.sbProgressLand);
        this.sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                AlarmVideoNormalPlayerActivity.this.mHandler.removeCallbacks(AlarmVideoNormalPlayerActivity.this.getCurrentPosRunnable);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                AlarmVideoNormalPlayerActivity.this.seekTo(seekBar.getProgress());
                AlarmVideoNormalPlayerActivity.this.mHandler.postDelayed(AlarmVideoNormalPlayerActivity.this.getCurrentPosRunnable, 500);
            }
        });
        this.sbProgressLand.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                AlarmVideoNormalPlayerActivity.this.mHandler.removeCallbacks(AlarmVideoNormalPlayerActivity.this.getCurrentPosRunnable);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                AlarmVideoNormalPlayerActivity.this.seekTo(seekBar.getProgress());
                AlarmVideoNormalPlayerActivity.this.mHandler.postDelayed(AlarmVideoNormalPlayerActivity.this.getCurrentPosRunnable, 500);
            }
        });
        this.tvVideoStart = (TextView) findViewById(R.id.tvVideoStart);
        this.tvVideoStartLand = (TextView) findViewById(R.id.tvVideoStartLand);
        this.tvVideoEnd = (TextView) findViewById(R.id.tvVideoEnd);
        this.tvVideoEndLand = (TextView) findViewById(R.id.tvVideoEndLand);
        this.ivFullScreen = (ImageView) findViewById(R.id.ivFullScreen);
        this.ivFullScreenLand = (ImageView) findViewById(R.id.ivFullScreenLand);
        this.ivFullScreenLand.setOnClickListener(this);
        this.ivFullScreen.setOnClickListener(this);
    }

    /* access modifiers changed from: package-private */
    public boolean hideCloudVideoBuyTips() {
        return "chuangmi.camera.ipc009".equals(this.mCameraDevice.getModel()) && !AlarmNetUtils.a(this.mCameraDevice.p(), BuildConfig.j);
    }

    private void initVideoList() {
        findView();
        initEventType();
        initView();
        initData();
        this.mHandler.post(new Runnable() {
            public void run() {
                AlarmVideoNormalPlayerActivity.this.calculateHeight();
            }
        });
        refreshContent();
        updateSelectParamByUserStatus();
    }

    private void initEventType() {
        String r = this.mCameraDevice.e().r();
        for (int i = 0; i < this.eventTypes.length; i++) {
            if (r.equals(this.eventTypes[i])) {
                this.mCurEventType.name = this.eventTypes[i];
                this.mCurEventType.desc = this.eventTypesDesc[i];
                return;
            }
        }
    }

    private void initData() {
        this.index = getIntent().getIntExtra("index", 0);
        this.selectedDate = (CalendarDate) getIntent().getParcelableExtra("dateTime");
        if (this.index != 0) {
            this.dateNoneDefault = true;
        }
        long todayStartTime = getTodayStartTime();
        long currentTimeMillis = System.currentTimeMillis();
        this.tv_cur_day.setText(DateUtils.getMonthDay(System.currentTimeMillis() + 1));
        if (this.selectedDate != null) {
            todayStartTime = this.selectedDate.getDayStartAndEndTimeMillis()[0];
            currentTimeMillis = this.selectedDate.getDayStartAndEndTimeMillis()[1];
            this.tv_cur_day.setText(DateUtils.getMonthDay(1 + todayStartTime));
        }
        this.mStartTime = todayStartTime;
        this.mStartPoint = todayStartTime;
        this.mEndTime = currentTimeMillis;
        this.mEndPoint = currentTimeMillis;
        SceneManager.x().a(this.mDid, this.mListener);
    }

    private long getTodayStartTime() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        return instance.getTimeInMillis();
    }

    private void findView() {
        this.calendar_container = findViewById(R.id.calendar_area);
        findViewById(R.id.see_all_video).setOnClickListener(this);
        this.title_bar_more = (ImageView) findViewById(R.id.title_bar_more);
        this.title_bar_more.setImageResource(R.drawable.house_keeping_setting);
        this.title_bar_more.setOnClickListener(this);
        if (this.mCameraDevice.isReadOnlyShared()) {
            this.title_bar_more.setVisibility(8);
        }
        this.custom_calendar = (YdCatCalendarView) findViewById(R.id.custom_calendar);
        this.ll_calendar_bg = (LinearLayout) findViewById(R.id.ll_calendar_bg);
        this.ll_calendar_bg.setOnClickListener(this);
        this.title_bar_title = (TextView) findViewById(R.id.title_bar_title);
        this.sub_title_bar_title = (TextView) findViewById(R.id.sub_title_bar_title);
        this.tv_cur_day = (TextView) findViewById(R.id.tv_cur_day);
        this.tv_cur_day.setOnClickListener(this);
        this.iv_next_day = (ImageView) findViewById(R.id.iv_next_day);
        this.iv_more_record = (ImageView) findViewById(R.id.iv_more_record);
        this.ptrLayout = (RecyclerViewRefreshLayoutEx) findViewById(R.id.ptr);
        this.tv_all_record = (TextView) findViewById(R.id.tv_all_record);
        this.rv_event_list = (RecyclerView) findViewById(R.id.rv_event_list);
        this.event_type_recycler = (RecyclerView) findViewById(R.id.event_type_recycler);
        this.ll_empty_content = (LinearLayout) findViewById(R.id.ll_empty_content);
        this.tv_request_result = (TextView) findViewById(R.id.tv_request_result);
        this.title_bar_redpoint = (ImageView) findViewById(R.id.title_bar_redpoint);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        findViewById(R.id.ll_all_record).setOnClickListener(this);
        this.iv_next_day.setOnClickListener(this);
        this.mLoadingView = (ImageView) findViewById(R.id.ivLoading);
    }

    private void initView() {
        this.mAlarmVideoAdapter = new AlarmVideoAdapter(this);
        this.mAlarmVideoAdapter.setOnItemClickedListener(new AlarmVideoAdapter.ItemClickedListener() {
            public void onItemClicked(final AlarmVideo alarmVideo, final int i) {
                AlarmVideoNormalPlayerActivity.this.mCameraDevice.a().markEvent(alarmVideo.fileId, alarmVideo.offset, new Callback<Boolean>() {
                    public void onFailure(int i, String str) {
                    }

                    public void onSuccess(Boolean bool) {
                        if (!AlarmVideoNormalPlayerActivity.this.isFinishing() && bool.booleanValue()) {
                            alarmVideo.isRead = true;
                            if (AlarmVideoNormalPlayerActivity.this.mRecordList != null && i != -1) {
                                AlarmVideoNormalPlayerActivity.this.mAlarmVideoAdapter.notifyItemChanged(i);
                            }
                        }
                    }
                });
                int unused = AlarmVideoNormalPlayerActivity.this.currentPosition = i;
                long unused2 = AlarmVideoNormalPlayerActivity.this.createTime = alarmVideo.createTime;
                String unused3 = AlarmVideoNormalPlayerActivity.this.fileId = alarmVideo.fileId;
                int unused4 = AlarmVideoNormalPlayerActivity.this.offset = alarmVideo.offset;
                boolean unused5 = AlarmVideoNormalPlayerActivity.this.isAlarm = alarmVideo.isAlarm;
                AlarmVideoNormalPlayerActivity.this.initShareView(AlarmVideoNormalPlayerActivity.this.isAlarm);
                AlarmVideoNormalPlayerActivity.this.highLightPlaying();
                AlarmVideoNormalPlayerActivity.this.getVideoUrl(true);
            }
        });
        ((TextView) findViewById(R.id.title_bar_title)).setText(getString(R.string.housekeeping));
        int width = getWindowManager().getDefaultDisplay().getWidth();
        if (this.targetHeight == -1) {
            this.targetHeight = (width * 720) / 1280;
        }
        initRecycler();
        initPtr();
        this.custom_calendar.setCalendarListener(new YdCatCalendarView.YdCatCalendarListener() {
            public void clickOnLeftPage() {
            }

            public void clickOnRightPage() {
            }

            public void clickOnDate(final CalendarDate calendarDate, View view) {
                AlarmVideoNormalPlayerActivity.this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        AlarmVideoNormalPlayerActivity.this.showOrHideCalendar(false, (AnimUtil.AnimEndListener) null);
                        long[] dayStartAndEndTimeMillis = calendarDate.getDayStartAndEndTimeMillis();
                        SDKLog.b(AlarmVideoNormalPlayerActivity.TAG, calendarDate.getYear() + "-" + calendarDate.getMonth() + "-" + calendarDate.getDay());
                        long unused = AlarmVideoNormalPlayerActivity.this.mEndTime = dayStartAndEndTimeMillis[1];
                        long unused2 = AlarmVideoNormalPlayerActivity.this.mStartPoint = dayStartAndEndTimeMillis[0];
                        long unused3 = AlarmVideoNormalPlayerActivity.this.mStartTime = AlarmVideoNormalPlayerActivity.this.mStartPoint;
                        long unused4 = AlarmVideoNormalPlayerActivity.this.mEndPoint = AlarmVideoNormalPlayerActivity.this.mEndTime;
                        AlarmVideoNormalPlayerActivity.this.tv_cur_day.setText(DateUtils.getMonthDay(AlarmVideoNormalPlayerActivity.this.mStartTime + 1));
                        AlarmVideoNormalPlayerActivity.this.refreshContent();
                    }
                }, 300);
            }
        });
    }

    /* access modifiers changed from: private */
    public void highLightPlaying() {
        this.mAlarmVideoAdapter.highLightPlaying(this.currentPosition);
    }

    private void initPtr() {
        this.ptrLayout.setFooterRefreshView(LayoutInflater.from(this).inflate(R.layout.camera_list_load_more, (ViewGroup) null));
        this.ptrLayout.setOnPullRefreshListener(new RecyclerViewRefreshLayout.OnPullRefreshListener() {
            public void onPullDistance(int i) {
            }

            public void onPullEnable(boolean z) {
            }

            public void onRefresh() {
                AlarmVideoNormalPlayerActivity.this.refreshContent();
            }
        });
        this.ptrLayout.setOnPushLoadMoreListener(new RecyclerViewRefreshLayout.OnPushLoadMoreListener() {
            public void onPushDistance(int i) {
            }

            public void onPushEnable(boolean z) {
            }

            public void onLoadMore() {
                AlarmVideoNormalPlayerActivity.this.getEventList(AlarmVideoNormalPlayerActivity.this.mStartTime, AlarmVideoNormalPlayerActivity.this.mEndTime, 2);
            }
        });
    }

    private void initRecycler() {
        this.rv_event_list.setLayoutManager(new LinearLayoutManager(activity(), 1, false));
        this.rv_event_list.setAdapter(this.mAlarmVideoAdapter);
        this.rv_event_list.setHasFixedSize(true);
        this.mTimeFormat = new SimpleDateFormat("mm:ss");
        this.mTimeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    /* access modifiers changed from: private */
    public void refreshContent() {
        showLoadingView();
        long[] dayStartAndEndTimeMillis = DateUtils.getCalendarDate(System.currentTimeMillis()).getDayStartAndEndTimeMillis();
        if (this.mEndPoint > dayStartAndEndTimeMillis[0] && this.mEndPoint < dayStartAndEndTimeMillis[1]) {
            this.mEndPoint = System.currentTimeMillis();
        }
        getEventList(this.mStartPoint, this.mEndPoint, 1);
    }

    /* access modifiers changed from: private */
    public void getEventList(long j, long j2, final int i) {
        SDKLog.b(TAG, "startTime=" + j + " endTime=" + j2);
        if (!this.isNetRefreshing) {
            this.isNetRefreshing = true;
            if (i == 1) {
                this.ptrLayout.setLoadMore(true);
                this.ptrLayout.setRefreshing(false);
                this.isNetRefreshing = false;
                this.loadTimes = 0;
                this.ptrLayout.setMode(3);
                this.mAlarmVideoAdapter.setToTheEnd(false);
                this.ll_empty_content.setVisibility(8);
            }
            this.mCameraDevice.a().getEventList(this.mCurEventType.name, j, j2, new AlarmCloudCallback<ArrayList<AlarmVideo>>() {
                public void onSuccess(ArrayList<AlarmVideo> arrayList) {
                }

                public void onSuccess(ArrayList<AlarmVideo> arrayList, long j, boolean z) {
                    AlarmVideoNormalPlayerActivity.this.hideLoadingView();
                    boolean unused = AlarmVideoNormalPlayerActivity.this.isNetRefreshing = false;
                    if (AlarmVideoNormalPlayerActivity.this.ptrLayout.isRefreshing()) {
                        AlarmVideoNormalPlayerActivity.this.ptrLayout.setRefreshing(false);
                    }
                    if (i == 1) {
                        AlarmVideoNormalPlayerActivity.this.mRecordList.clear();
                    }
                    AlarmVideoNormalPlayerActivity.this.mRecordList.addAll(arrayList);
                    if (i == 1 && arrayList != null && arrayList.size() > 0) {
                        Iterator<AlarmVideo> it = arrayList.iterator();
                        while (it.hasNext()) {
                            AlarmVideo next = it.next();
                            if (next.fileId == null || !next.fileId.equalsIgnoreCase(AlarmVideoNormalPlayerActivity.this.fileId)) {
                                next.isPlaying = false;
                            } else {
                                next.isPlaying = true;
                            }
                        }
                    }
                    if (AlarmVideoNormalPlayerActivity.this.mRecordList == null || AlarmVideoNormalPlayerActivity.this.mRecordList.size() <= 0) {
                        AlarmVideoNormalPlayerActivity.this.mAlarmVideoAdapter.update(AlarmVideoNormalPlayerActivity.this.mRecordList);
                        AlarmVideoNormalPlayerActivity.this.ll_empty_content.setVisibility(0);
                        AlarmVideoNormalPlayerActivity.this.tv_request_result.setText(AlarmVideoNormalPlayerActivity.this.getEmptyStringByType(AlarmVideoNormalPlayerActivity.this.mCurEventType.name));
                        AlarmVideoNormalPlayerActivity.this.ptrLayout.setEnabled(true);
                        return;
                    }
                    AlarmVideoNormalPlayerActivity.this.ll_empty_content.setVisibility(8);
                    AlarmVideoNormalPlayerActivity.this.ptrLayout.setVisibility(0);
                    AlarmVideoNormalPlayerActivity.this.ptrLayout.setEnabled(true);
                    if (!z) {
                        AlarmVideoNormalPlayerActivity.this.calculateHeight();
                        AlarmVideoNormalPlayerActivity.this.ptrLayout.setMode(1);
                        AlarmVideoNormalPlayerActivity.this.ptrLayout.setLoadMore(false);
                        if (AlarmVideoNormalPlayerActivity.this.mRecordList.size() < AlarmVideoNormalPlayerActivity.this.mVisibleItemCount) {
                            AlarmVideoNormalPlayerActivity.this.mAlarmVideoAdapter.setToTheEnd(false);
                        } else {
                            AlarmVideoNormalPlayerActivity.this.mAlarmVideoAdapter.setToTheEnd(true);
                        }
                    } else {
                        long unused2 = AlarmVideoNormalPlayerActivity.this.mEndTime = j;
                        AlarmVideoNormalPlayerActivity.this.mAlarmVideoAdapter.setToTheEnd(false);
                        AlarmVideoNormalPlayerActivity.this.ptrLayout.setMode(3);
                        AlarmVideoNormalPlayerActivity.this.ptrLayout.setLoadMore(false);
                    }
                    AlarmVideoNormalPlayerActivity.this.mAlarmVideoAdapter.update(AlarmVideoNormalPlayerActivity.this.mRecordList);
                    if (AlarmVideoNormalPlayerActivity.this.dateNoneDefault) {
                        boolean unused3 = AlarmVideoNormalPlayerActivity.this.dateNoneDefault = false;
                        AlarmVideoNormalPlayerActivity.this.smoothScrollToPosition(AlarmVideoNormalPlayerActivity.this.index);
                    }
                }

                public void onFailure(int i, String str) {
                    AlarmVideoNormalPlayerActivity.this.hideLoadingView();
                    boolean unused = AlarmVideoNormalPlayerActivity.this.isNetRefreshing = false;
                    if (AlarmVideoNormalPlayerActivity.this.ptrLayout.isRefreshing()) {
                        AlarmVideoNormalPlayerActivity.this.ptrLayout.setRefreshing(false);
                    }
                    AlarmVideoNormalPlayerActivity.this.ptrLayout.setLoadMore(false);
                    if (i == 1) {
                        AlarmVideoNormalPlayerActivity.this.mRecordList.clear();
                        AlarmVideoNormalPlayerActivity.this.mAlarmVideoAdapter.update(AlarmVideoNormalPlayerActivity.this.mRecordList);
                        AlarmVideoNormalPlayerActivity.this.ll_empty_content.setVisibility(0);
                        AlarmVideoNormalPlayerActivity.this.tv_request_result.setText(AlarmVideoNormalPlayerActivity.this.getEmptyStringByType(AlarmVideoNormalPlayerActivity.this.mCurEventType.name));
                        AlarmVideoNormalPlayerActivity.this.ptrLayout.setMode(1);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void smoothScrollToPosition(final int i) {
        this.mHandler.post(new Runnable() {
            public void run() {
                TopSmoothScroller topSmoothScroller = new TopSmoothScroller(AlarmVideoNormalPlayerActivity.this.getContext());
                topSmoothScroller.setTargetPosition(i);
                if (AlarmVideoNormalPlayerActivity.this.rv_event_list.getLayoutManager() != null) {
                    AlarmVideoNormalPlayerActivity.this.rv_event_list.getLayoutManager().startSmoothScroll(topSmoothScroller);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void toggleVideoCtrlViewTranslation() {
        if (!this.mCameraDevice.isReadOnlyShared()) {
            if (this.llTopRightCtrl.getTranslationY() >= 0.0f) {
                this.llTopRightCtrl.setTranslationY((float) (-this.llTopRightCtrl.getHeight()));
                if (getRequestedOrientation() != 1) {
                    this.rlVideoViewBottomCtrlLand.setTranslationY((float) this.rlVideoViewBottomCtrlLand.getHeight());
                    return;
                }
                return;
            }
            this.llTopRightCtrl.setTranslationY(0.0f);
            if (getRequestedOrientation() != 1) {
                this.rlVideoViewBottomCtrlLand.setTranslationY(0.0f);
            }
        }
    }

    /* access modifiers changed from: private */
    public void seekTo(int i) {
        if (i >= 0 && this.simpleExoPlayer != null && this.simpleExoPlayer.getDuration() > 0) {
            this.simpleExoPlayer.seekTo((long) ((int) ((((long) i) * this.simpleExoPlayer.getDuration()) / 100)));
        }
    }

    public void onCompletion() {
        this.mHandler.removeCallbacks(this.getCurrentPosRunnable);
        if (this.sbProgress != null && this.sbProgress.getProgress() < 100) {
            this.sbProgress.setProgress(100);
            this.sbProgressLand.setProgress(100);
        }
        this.cbTogglePlay.setChecked(true);
        this.cbTogglePlayLand.setChecked(true);
    }

    /* access modifiers changed from: private */
    public void showVideoLoading(boolean z) {
        LogUtil.a(TAG, "showVideoLoading");
        if (this.ivVideoLoading != null) {
            this.ivVideoLoading.setVisibility(0);
            Drawable drawable = this.ivVideoLoading.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).start();
            }
            if (z) {
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        AlarmVideoNormalPlayerActivity.this.hideVideoLoading();
                    }
                }, 5000);
            }
        }
    }

    /* access modifiers changed from: private */
    public void hideVideoLoading() {
        LogUtil.a(TAG, "hideVideoLoading");
        if (this.ivVideoLoading != null) {
            Drawable drawable = this.ivVideoLoading.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).stop();
            }
            this.ivVideoLoading.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void setProgressTime(long j) {
        long duration = this.simpleExoPlayer.getDuration();
        if (j >= 0 && duration > 0) {
            int ceil = (int) Math.ceil((double) ((100 * j) / duration));
            if (ceil >= 100 || j / 1000 >= duration / 1000) {
                this.sbProgress.setProgress(100);
                this.sbProgressLand.setProgress(100);
                return;
            }
            this.sbProgress.setProgress(ceil);
            this.sbProgressLand.setProgress(ceil);
        }
    }

    /* access modifiers changed from: private */
    public void setStartTime(long j) {
        this.tvVideoStart.setText(this.mTimeFormat.format(Long.valueOf(j)));
        this.tvVideoStartLand.setText(this.mTimeFormat.format(Long.valueOf(j)));
    }

    /* access modifiers changed from: private */
    public void setEndTime(long j) {
        this.tvVideoEnd.setText(this.mTimeFormat.format(Long.valueOf(j)));
        this.tvVideoEndLand.setText(this.mTimeFormat.format(Long.valueOf(j)));
    }

    /* access modifiers changed from: private */
    public void getVideoUrl(boolean z) {
        String videoFileUrl = CloudVideoNetUtils.getInstance().getVideoFileUrl(this.mCameraDevice.getDid(), this.mCameraDevice.getModel(), this.fileId, this.isAlarm);
        LogUtil.a(TAG, "videoUrl = " + videoFileUrl);
        HashMap hashMap = new HashMap();
        MiServiceTokenInfo tokenInfo = CloudVideoNetUtils.getInstance().getTokenInfo();
        if (tokenInfo != null) {
            showVideoLoading(false);
            hashMap.put("Cookie", "yetAnotherServiceToken=" + tokenInfo.c);
            this.simpleExoPlayer.prepare(buildMediaSource(this, videoFileUrl, hashMap));
            this.simpleExoPlayer.setPlayWhenReady(z);
            this.simpleExoPlayer.seekTo((long) (this.offset * 3000));
            if (this.cbIsMute.isChecked()) {
                this.simpleExoPlayer.setVolume(0.0f);
            } else {
                this.simpleExoPlayer.setVolume(1.0f);
            }
        }
    }

    private MediaSource buildMediaSource(Context context, String str, Map<String, String> map) {
        Uri parse = Uri.parse(str);
        if (TextUtils.isEmpty(parse.getScheme()) || (!parse.getScheme().equals("http") && !parse.getScheme().equals("https"))) {
            return buildMediaSourceLocal(parse);
        }
        return buildMediaSourceHTTP(context, parse, map);
    }

    private HlsMediaSource buildMediaSourceHTTP(Context context, Uri uri, Map<String, String> map) {
        DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory(com.google.android.exoplayer2.util.Util.getUserAgent(context, "SmartHome;Android"), new DefaultBandwidthMeter());
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

    private void updateSelectParamByUserStatus() {
        updateEventType(false);
        CEn7DayRecyclerAdapterNew.CLOUD_DAYS = 6;
        long currentTimeMillis = System.currentTimeMillis();
        this.custom_calendar.initRecycler(activity());
        this.custom_calendar.addHaveVideoDay(DateUtils.getCalendarDate(currentTimeMillis - (CEn7DayRecyclerAdapterNew.CLOUD_DAYS * 86400000)));
        this.custom_calendar.refreshFlag();
    }

    private void updateEventType(boolean z) {
        this.mAlarmVideoAdapter.setVipStatus(z);
        int length = this.eventTypes.length;
        final ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        for (int i = 0; i < length - 1; i++) {
            if (z || !(i == 3 || i == 4)) {
                EventType eventType = new EventType();
                eventType.name = this.eventTypes[i];
                eventType.desc = this.eventTypesDesc[i];
                arrayList.add(eventType);
                if (this.mCurEventType.name.equals(eventType.name)) {
                    z2 = true;
                }
            }
        }
        if (!z2) {
            this.mCurEventType.name = this.eventTypes[0];
            this.mCurEventType.desc = this.eventTypesDesc[0];
            this.mCameraDevice.e().b(this.mCurEventType.name);
        }
        this.tv_all_record.setText(this.mCurEventType.desc);
        this.eventTypeAdapter = new RecyclerEventTypeAdapter(activity(), arrayList, new RecyclerEventTypeAdapter.OnItemClick() {
            public void onItemClick(int i) {
                EventType unused = AlarmVideoNormalPlayerActivity.this.mCurEventType = (EventType) arrayList.get(i);
                AlarmVideoNormalPlayerActivity.this.tv_all_record.setText(AlarmVideoNormalPlayerActivity.this.mCurEventType.desc);
                AlarmVideoNormalPlayerActivity.this.mCameraDevice.e().b(AlarmVideoNormalPlayerActivity.this.mCurEventType.name);
                AlarmVideoNormalPlayerActivity.this.onEventTypeBgClick();
                AlarmVideoNormalPlayerActivity.this.refreshContent();
                int i2 = 1;
                if (!"Default".equalsIgnoreCase(AlarmVideoNormalPlayerActivity.this.mCurEventType.name)) {
                    if (AlarmCloudManager.OBJECT_MOTION.equalsIgnoreCase(AlarmVideoNormalPlayerActivity.this.mCurEventType.name)) {
                        i2 = 2;
                    } else if (AlarmCloudManager.PEOPLE_MOTION.equalsIgnoreCase(AlarmVideoNormalPlayerActivity.this.mCurEventType.name)) {
                        i2 = 3;
                    } else if (AlarmCloudManager.BABY_CRY.equalsIgnoreCase(AlarmVideoNormalPlayerActivity.this.mCurEventType.name)) {
                        i2 = 4;
                    } else if (AlarmCloudManager.FACE.equalsIgnoreCase(AlarmVideoNormalPlayerActivity.this.mCurEventType.name)) {
                        i2 = 5;
                    } else if (AlarmCloudManager.AI.equalsIgnoreCase(AlarmVideoNormalPlayerActivity.this.mCurEventType.name)) {
                        i2 = 6;
                    }
                }
                Event.a(Event.bG, "type", (Object) Integer.valueOf(i2));
            }
        });
        this.event_type_recycler.setLayoutManager(new GridLayoutManager(activity(), 2));
        this.event_type_recycler.setAdapter(this.eventTypeAdapter);
    }

    /* access modifiers changed from: private */
    public void onEventTypeBgClick() {
        showOrHideEventType(false, (AnimUtil.AnimEndListener) null);
    }

    /* access modifiers changed from: private */
    public void showOrHideEventType(boolean z, AnimUtil.AnimEndListener animEndListener) {
        if (z) {
            AnimUtil.animLayoutTop(activity(), true, (View) null, this.ll_calendar_bg, this.event_type_recycler, animEndListener);
            this.iv_more_record.setImageResource(R.drawable.icon_select_up);
            this.tv_all_record.setTextColor(getResources().getColorStateList(R.color.leave_msg_playing));
            return;
        }
        AnimUtil.animLayoutTop(activity(), false, (View) null, this.ll_calendar_bg, this.event_type_recycler, animEndListener);
        this.iv_more_record.setImageResource(R.drawable.icon_select_down);
        this.tv_all_record.setTextColor(getResources().getColorStateList(R.color.black));
    }

    private void showLoadingView() {
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(0);
            Drawable drawable = this.mLoadingView.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).start();
            }
        }
    }

    /* access modifiers changed from: private */
    public void hideLoadingView() {
        if (this.mLoadingView != null) {
            Drawable drawable = this.mLoadingView.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).stop();
            }
            this.mLoadingView.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void calculateHeight() {
        this.mVisibleItemCount = (int) Math.ceil((double) (((float) this.rv_event_list.getMeasuredHeight()) / (((float) DisplayUtils.a((Context) this, 88.0f)) * 1.0f)));
    }

    /* access modifiers changed from: private */
    public void showOrHideCalendar(boolean z, AnimUtil.AnimEndListener animEndListener) {
        if (z) {
            AnimUtil.animLayoutTop(activity(), true, (View) null, this.ll_calendar_bg, this.calendar_container, animEndListener);
            this.iv_next_day.setImageResource(R.drawable.icon_select_up);
            this.tv_cur_day.setTextColor(getResources().getColorStateList(R.color.leave_msg_playing));
            return;
        }
        AnimUtil.animLayoutTop(activity(), false, (View) null, this.ll_calendar_bg, this.calendar_container, animEndListener);
        this.iv_next_day.setImageResource(R.drawable.icon_select_down);
        this.tv_cur_day.setTextColor(getResources().getColorStateList(R.color.black));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getEmptyStringByType(java.lang.String r2) {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case -1293551627: goto L_0x0044;
                case -1085510111: goto L_0x003a;
                case -740191200: goto L_0x0030;
                case 2088: goto L_0x0026;
                case 2181757: goto L_0x001c;
                case 722651973: goto L_0x0012;
                case 1316906260: goto L_0x0008;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x004e
        L_0x0008:
            java.lang.String r0 = "BabyCry"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004e
            r2 = 5
            goto L_0x004f
        L_0x0012:
            java.lang.String r0 = "PeopleMotion"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004e
            r2 = 2
            goto L_0x004f
        L_0x001c:
            java.lang.String r0 = "Face"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004e
            r2 = 3
            goto L_0x004f
        L_0x0026:
            java.lang.String r0 = "AI"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004e
            r2 = 6
            goto L_0x004f
        L_0x0030:
            java.lang.String r0 = "KnownFace"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004e
            r2 = 4
            goto L_0x004f
        L_0x003a:
            java.lang.String r0 = "Default"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004e
            r2 = 0
            goto L_0x004f
        L_0x0044:
            java.lang.String r0 = "ObjectMotion"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x004e
            r2 = 1
            goto L_0x004f
        L_0x004e:
            r2 = -1
        L_0x004f:
            r0 = 2131494852(0x7f0c07c4, float:1.8613224E38)
            switch(r2) {
                case 0: goto L_0x0082;
                case 1: goto L_0x007a;
                case 2: goto L_0x0072;
                case 3: goto L_0x006a;
                case 4: goto L_0x006a;
                case 5: goto L_0x0062;
                case 6: goto L_0x005a;
                default: goto L_0x0055;
            }
        L_0x0055:
            java.lang.String r2 = r1.getString(r0)
            return r2
        L_0x005a:
            r2 = 2131494850(0x7f0c07c2, float:1.861322E38)
            java.lang.String r2 = r1.getString(r2)
            return r2
        L_0x0062:
            r2 = 2131494851(0x7f0c07c3, float:1.8613222E38)
            java.lang.String r2 = r1.getString(r2)
            return r2
        L_0x006a:
            r2 = 2131494853(0x7f0c07c5, float:1.8613226E38)
            java.lang.String r2 = r1.getString(r2)
            return r2
        L_0x0072:
            r2 = 2131494856(0x7f0c07c8, float:1.8613232E38)
            java.lang.String r2 = r1.getString(r2)
            return r2
        L_0x007a:
            r2 = 2131494857(0x7f0c07c9, float:1.8613234E38)
            java.lang.String r2 = r1.getString(r2)
            return r2
        L_0x0082:
            java.lang.String r2 = r1.getString(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.alarm2.AlarmVideoNormalPlayerActivity.getEmptyStringByType(java.lang.String):java.lang.String");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivDeleteCurrentVideo:
                showDeleteVideoDialog();
                Event.a(Event.bI);
                return;
            case R.id.ivFullScreen:
            case R.id.ivFullScreenLand:
                Event.a(Event.aN);
                if (getRequestedOrientation() == 1) {
                    setRequestedOrientation(0);
                    return;
                } else {
                    setRequestedOrientation(1);
                    return;
                }
            case R.id.ivVideoBack:
                onBackPressed();
                return;
            case R.id.ivVideoDownload:
                tryDownload();
                return;
            case R.id.ivVideoShare:
                tryShare();
                return;
            case R.id.iv_next_day:
            case R.id.tv_cur_day:
                onSelectDayClick();
                return;
            case R.id.ll_all_record:
                onAllRecordClicked();
                return;
            case R.id.ll_calendar_bg:
                if (this.calendar_container.getVisibility() == 0) {
                    onCalendarBgClicked();
                    return;
                } else if (this.event_type_recycler.getVisibility() == 0) {
                    onEventTypeBgClick();
                    return;
                } else {
                    return;
                }
            case R.id.see_all_video:
                Event.a(Event.aO);
                showOrHideCalendar(false, (AnimUtil.AnimEndListener) null);
                startActivityForResult(new Intent(this, AlarmDayV2Activity.class), 17);
                return;
            case R.id.title_bar_more:
                startActivity(new Intent(this, AlarmSettingV2Activity.class));
                Event.a(Event.aR);
                return;
            case R.id.title_bar_return:
                onBackPressed();
                return;
            case R.id.tvCloudStorageHint:
                CloudVideoNetUtils.getInstance().openCloudVideoBuyPage(this, this.mCameraDevice.getDid());
                Event.a(Event.bH);
                return;
            case R.id.tvDownloadHint:
                this.tvDownloadHint.setVisibility(8);
                Intent intent = new Intent(this, CloudVideoDownloadActivity.class);
                intent.putExtra("did", this.mCameraDevice.getDid());
                intent.putExtra("title", this.mCameraDevice.getName());
                Device b = SmartHomeDeviceManager.a().b(this.mCameraDevice.getDid());
                if (b != null) {
                    intent.putExtra("uid", b.userId);
                    startActivity(intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void tryShare() {
        Event.a(Event.aP);
        this.isSharing = true;
        if (CloudVideoNetUtils.getInstance().isWifiConnected(this)) {
            doDownload();
        } else {
            new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.cs_non_wifi_environment)).a((int) R.string.cs_go_on, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    AlarmVideoNormalPlayerActivity.this.doDownload();
                }
            }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                }
            }).b((int) R.string.cs_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).d();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a(TAG, "orientation:" + configuration.orientation);
        if (configuration.orientation == 1) {
            getWindow().clearFlags(1024);
            this.flTitleBar.setVisibility(0);
            this.ivFullScreen.setImageResource(R.drawable.camera_icon_alarm_player_fullscreen);
            this.ivFullScreenLand.setImageResource(R.drawable.camera_icon_alarm_player_fullscreen);
            if (this.flVideoContainer != null) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.exoVideoView.getLayoutParams());
                layoutParams.height = com.xiaomi.smarthome.library.common.util.DisplayUtils.a(210.0f);
                this.flVideoContainer.setLayoutParams(layoutParams);
                this.flVideoContainer.requestLayout();
            }
            this.rlVideoViewBottomCtrlLand.setVisibility(8);
            this.rlVideoViewBottomCtrl.setVisibility(0);
            this.tvVideoTitle.setVisibility(8);
            this.ivVideoBack.setVisibility(8);
            return;
        }
        getWindow().setFlags(1024, 1024);
        this.flTitleBar.setVisibility(8);
        this.ivFullScreen.setImageResource(R.drawable.camera_icon_alarm_player_exit_fullscreen);
        this.ivFullScreenLand.setImageResource(R.drawable.camera_icon_alarm_player_exit_fullscreen);
        if (this.flVideoContainer != null) {
            this.flVideoContainer.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            this.flVideoContainer.requestLayout();
        }
        this.rlVideoViewBottomCtrlLand.setVisibility(0);
        this.rlVideoViewBottomCtrl.setVisibility(8);
        this.tvVideoTitle.setVisibility(0);
        this.ivVideoBack.setVisibility(0);
    }

    public void onBackPressed() {
        if (getRequestedOrientation() != 1) {
            setRequestedOrientation(1);
        } else {
            super.onBackPressed();
        }
    }

    private void tryDownload() {
        Event.a(Event.aM);
        this.isSharing = false;
        if (CloudVideoNetUtils.getInstance().isWifiConnected(this)) {
            doDownload();
        } else {
            new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.cs_non_wifi_environment)).a((int) R.string.cs_go_on, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    AlarmVideoNormalPlayerActivity.this.doDownload();
                }
            }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                }
            }).b((int) R.string.cs_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).d();
        }
    }

    /* access modifiers changed from: private */
    public void doDownload() {
        long duration = this.simpleExoPlayer.getDuration();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Device b = SmartHomeDeviceManager.a().b(this.mCameraDevice.getDid());
        if (b != null) {
            ArrayList arrayList = new ArrayList();
            CloudVideoDownloadInfo cloudVideoDownloadInfo = new CloudVideoDownloadInfo();
            cloudVideoDownloadInfo.uid = b.userId;
            cloudVideoDownloadInfo.did = this.mCameraDevice.getDid();
            cloudVideoDownloadInfo.title = this.mCameraDevice.getName();
            cloudVideoDownloadInfo.videoUrl = CloudVideoNetUtils.getInstance().getVideoFileUrl(this.mCameraDevice.getDid(), this.mCameraDevice.getModel(), this.fileId, this.isAlarm);
            cloudVideoDownloadInfo.fileId = this.fileId;
            cloudVideoDownloadInfo.mp4FilePath = null;
            cloudVideoDownloadInfo.m3u8FilePath = null;
            cloudVideoDownloadInfo.status = 4;
            cloudVideoDownloadInfo.createTime = System.currentTimeMillis();
            cloudVideoDownloadInfo.startTime = this.mStartTime;
            cloudVideoDownloadInfo.endTime = this.mStartTime + duration;
            cloudVideoDownloadInfo.duration = duration / 1000;
            cloudVideoDownloadInfo.createTimePretty = simpleDateFormat.format(Long.valueOf(cloudVideoDownloadInfo.createTime));
            cloudVideoDownloadInfo.startTimePretty = simpleDateFormat.format(Long.valueOf(cloudVideoDownloadInfo.startTime));
            cloudVideoDownloadInfo.endTimePretty = simpleDateFormat.format(Long.valueOf(cloudVideoDownloadInfo.endTime));
            cloudVideoDownloadInfo.size = 0;
            cloudVideoDownloadInfo.progress = 0;
            if (!this.isAlarm) {
                showDownloadActivityHint();
                CloudVideoDownloadManager.getInstance().addListener(this.mCloudVideoDownloadListener);
            } else {
                showLoading();
                CloudVideoDownloadManager.getInstance().addListener(this.mCloudVideoDownloadListener);
            }
            arrayList.add(cloudVideoDownloadInfo);
            CloudVideoDownloadManager.getInstance().insertRecords(arrayList);
            CloudVideoDownloadManager.getInstance().pullDownloadFromList(getContext(), b.userId, this.mCameraDevice.getDid());
        }
    }

    private boolean checkFileHasDownload() {
        Device b = SmartHomeDeviceManager.a().b(this.mCameraDevice.getDid());
        if (b == null) {
            return false;
        }
        List<CloudVideoDownloadInfo> records = CloudVideoDownloadManager.getInstance().getRecords(b.userId, b.did);
        int i = 0;
        while (i < records.size()) {
            CloudVideoDownloadInfo cloudVideoDownloadInfo = records.get(i);
            if (!this.fileId.equals(cloudVideoDownloadInfo.fileId) || cloudVideoDownloadInfo.status == 2) {
                i++;
            } else if (cloudVideoDownloadInfo.status != 0) {
                return true;
            } else {
                ToastUtil.a((int) R.string.save_success);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void m3u8ToMp4(String str) {
        String a2 = FileUtil.a(true, this.mCameraDevice.getDid());
        new M3u8ToMp4Task(this.isSharing).execute(new String[]{str, a2});
    }

    private void showDeleteVideoDialog() {
        new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.cs_delete_video)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AlarmVideoNormalPlayerActivity.this.mCameraDevice.a().deleteFiles(new Callback() {
                    public void onFailure(int i, String str) {
                    }

                    public void onSuccess(Object obj) {
                        AlarmVideoNormalPlayerActivity.this.deleteSwitchVideo();
                        AlarmVideoNormalPlayerActivity.this.refreshContent();
                    }
                }, AlarmVideoNormalPlayerActivity.this.isAlarm, AlarmVideoNormalPlayerActivity.this.fileId);
            }
        }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).d();
    }

    /* access modifiers changed from: private */
    public void deleteSwitchVideo() {
        AlarmVideo alarmVideo;
        if (this.mAlarmVideoAdapter.getItemCount() > 1) {
            if (this.mAlarmVideoAdapter.isTheLastVideo(this.currentPosition)) {
                alarmVideo = this.mAlarmVideoAdapter.getItem(0);
                this.currentPosition = 0;
            } else {
                alarmVideo = this.mAlarmVideoAdapter.getItem(this.currentPosition + 1);
                this.currentPosition++;
            }
            if (alarmVideo != null) {
                this.createTime = alarmVideo.createTime;
                this.fileId = alarmVideo.fileId;
                this.offset = alarmVideo.offset;
                this.isAlarm = alarmVideo.isAlarm;
                getVideoUrl(false);
                return;
            }
            return;
        }
        finish();
    }

    private void onSelectDayClick() {
        Event.a(Event.bF);
        boolean z = false;
        if (this.event_type_recycler.getVisibility() == 0) {
            showOrHideEventType(false, new AnimUtil.AnimEndListener() {
                public void animEnd() {
                    AlarmVideoNormalPlayerActivity.this.showOrHideCalendar(AlarmVideoNormalPlayerActivity.this.calendar_container.getVisibility() != 0, (AnimUtil.AnimEndListener) null);
                }
            });
            return;
        }
        if (this.calendar_container.getVisibility() != 0) {
            z = true;
        }
        showOrHideCalendar(z, (AnimUtil.AnimEndListener) null);
    }

    private void onAllRecordClicked() {
        boolean z = false;
        if (this.calendar_container.getVisibility() == 0) {
            showOrHideCalendar(false, new AnimUtil.AnimEndListener() {
                public void animEnd() {
                    AlarmVideoNormalPlayerActivity.this.showOrHideEventType(AlarmVideoNormalPlayerActivity.this.event_type_recycler.getVisibility() != 0, (AnimUtil.AnimEndListener) null);
                }
            });
            return;
        }
        if (this.event_type_recycler.getVisibility() != 0) {
            z = true;
        }
        showOrHideEventType(z, (AnimUtil.AnimEndListener) null);
    }

    public void onCalendarBgClicked() {
        showOrHideCalendar(false, (AnimUtil.AnimEndListener) null);
    }

    class M3u8ToMp4Task extends AsyncTask<String, Void, String> {
        boolean isSharing;

        M3u8ToMp4Task(boolean z) {
            this.isSharing = z;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:44:0x011d A[SYNTHETIC, Splitter:B:44:0x011d] */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x016c  */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x0171 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:53:0x0172 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:56:0x0178 A[SYNTHETIC, Splitter:B:56:0x0178] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String doInBackground(java.lang.String... r8) {
            /*
                r7 = this;
                r0 = 0
                r0 = r8[r0]
                r1 = 1
                r8 = r8[r1]
                java.io.File r1 = new java.io.File
                r1.<init>(r0)
                java.io.File[] r1 = r1.listFiles()
                if (r1 != 0) goto L_0x0014
                java.lang.String r8 = ""
                return r8
            L_0x0014:
                java.util.List r1 = java.util.Arrays.asList(r1)
                com.xiaomi.smarthome.camera.activity.alarm2.AlarmVideoNormalPlayerActivity$M3u8ToMp4Task$1 r2 = new com.xiaomi.smarthome.camera.activity.alarm2.AlarmVideoNormalPlayerActivity$M3u8ToMp4Task$1
                r2.<init>()
                java.util.Collections.sort(r1, r2)
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.util.Iterator r1 = r1.iterator()
            L_0x002e:
                boolean r4 = r1.hasNext()
                if (r4 == 0) goto L_0x0082
                java.lang.Object r4 = r1.next()
                java.io.File r4 = (java.io.File) r4
                java.lang.String r4 = r4.getAbsolutePath()
                java.lang.String r5 = ".ts"
                boolean r5 = r4.contains(r5)
                if (r5 == 0) goto L_0x0060
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "file '"
                r5.append(r6)
                r5.append(r4)
                java.lang.String r4 = "'\r\n"
                r5.append(r4)
                java.lang.String r4 = r5.toString()
                r2.append(r4)
                goto L_0x002e
            L_0x0060:
                java.lang.String r5 = "mp4"
                boolean r5 = r4.contains(r5)
                if (r5 == 0) goto L_0x002e
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "file '"
                r5.append(r6)
                r5.append(r4)
                java.lang.String r4 = "'\r\n"
                r5.append(r4)
                java.lang.String r4 = r5.toString()
                r3.append(r4)
                goto L_0x002e
            L_0x0082:
                java.lang.String r1 = "M3u8ToMp4Task"
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r5 = "concat =  "
                r4.append(r5)
                java.lang.String r5 = r2.toString()
                r4.append(r5)
                java.lang.String r5 = " concatMp4 = "
                r4.append(r5)
                java.lang.String r5 = r3.toString()
                r4.append(r5)
                java.lang.String r4 = r4.toString()
                com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r1, (java.lang.String) r4)
                boolean r1 = android.text.TextUtils.isEmpty(r2)
                if (r1 == 0) goto L_0x00b7
                boolean r1 = android.text.TextUtils.isEmpty(r3)
                if (r1 == 0) goto L_0x00b7
                java.lang.String r8 = ""
                return r8
            L_0x00b7:
                java.io.File r1 = new java.io.File
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                r4.append(r0)
                java.lang.String r0 = "/fileList.txt"
                r4.append(r0)
                java.lang.String r0 = r4.toString()
                r1.<init>(r0)
                boolean r0 = r1.exists()
                if (r0 != 0) goto L_0x00db
                r1.createNewFile()     // Catch:{ IOException -> 0x00d7 }
                goto L_0x00db
            L_0x00d7:
                r0 = move-exception
                r0.printStackTrace()
            L_0x00db:
                r0 = 0
                java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x0115, all -> 0x0112 }
                java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0115, all -> 0x0112 }
                r5.<init>(r1)     // Catch:{ IOException -> 0x0115, all -> 0x0112 }
                r4.<init>(r5)     // Catch:{ IOException -> 0x0115, all -> 0x0112 }
                boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch:{ IOException -> 0x0110 }
                if (r0 != 0) goto L_0x00f8
                java.lang.String r0 = r2.toString()     // Catch:{ IOException -> 0x0110 }
                byte[] r0 = r0.getBytes()     // Catch:{ IOException -> 0x0110 }
                r4.write(r0)     // Catch:{ IOException -> 0x0110 }
                goto L_0x0109
            L_0x00f8:
                boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch:{ IOException -> 0x0110 }
                if (r0 != 0) goto L_0x0109
                java.lang.String r0 = r3.toString()     // Catch:{ IOException -> 0x0110 }
                byte[] r0 = r0.getBytes()     // Catch:{ IOException -> 0x0110 }
                r4.write(r0)     // Catch:{ IOException -> 0x0110 }
            L_0x0109:
                r4.flush()     // Catch:{ IOException -> 0x0110 }
                r4.close()     // Catch:{ IOException -> 0x0121 }
                goto L_0x0125
            L_0x0110:
                r0 = move-exception
                goto L_0x0118
            L_0x0112:
                r8 = move-exception
                r4 = r0
                goto L_0x0176
            L_0x0115:
                r2 = move-exception
                r4 = r0
                r0 = r2
            L_0x0118:
                r0.printStackTrace()     // Catch:{ all -> 0x0175 }
                if (r4 == 0) goto L_0x0125
                r4.close()     // Catch:{ IOException -> 0x0121 }
                goto L_0x0125
            L_0x0121:
                r0 = move-exception
                r0.printStackTrace()
            L_0x0125:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "ffmpeg -f concat -safe 0 -i "
                r0.append(r2)
                java.lang.String r1 = r1.getAbsolutePath()
                r0.append(r1)
                java.lang.String r1 = " -c copy "
                r0.append(r1)
                r0.append(r8)
                java.lang.String r0 = r0.toString()
                java.lang.String r1 = "M3u8ToMp4Task"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "begin transform "
                r2.append(r3)
                r2.append(r0)
                java.lang.String r2 = r2.toString()
                com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r1, (java.lang.String) r2)
                com.xiaomi.smarthome.device.api.XmPluginHostApi r1 = com.xiaomi.smarthome.device.api.XmPluginHostApi.instance()
                com.xiaomi.smarthome.camera.activity.alarm2.AlarmVideoNormalPlayerActivity r2 = com.xiaomi.smarthome.camera.activity.alarm2.AlarmVideoNormalPlayerActivity.this
                com.mijia.camera.MijiaCameraDevice r2 = r2.mCameraDevice
                java.lang.String r2 = r2.getModel()
                int r0 = r1.videoConverter(r2, r0)
                if (r0 != 0) goto L_0x016f
                r7.deleteM3U8()
            L_0x016f:
                if (r0 != 0) goto L_0x0172
                return r8
            L_0x0172:
                java.lang.String r8 = ""
                return r8
            L_0x0175:
                r8 = move-exception
            L_0x0176:
                if (r4 == 0) goto L_0x0180
                r4.close()     // Catch:{ IOException -> 0x017c }
                goto L_0x0180
            L_0x017c:
                r0 = move-exception
                r0.printStackTrace()
            L_0x0180:
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.alarm2.AlarmVideoNormalPlayerActivity.M3u8ToMp4Task.doInBackground(java.lang.String[]):java.lang.String");
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String str) {
            if (!AlarmVideoNormalPlayerActivity.this.isFinishing()) {
                LogUtil.a("M3u8ToMp4Task", "onPostExecute result " + str);
                AlarmVideoNormalPlayerActivity.this.hideLoading();
                if (!TextUtils.isEmpty(str)) {
                    if (this.isSharing) {
                        AlarmVideoNormalPlayerActivity.this.openShareVideoActivity(AlarmVideoNormalPlayerActivity.this, "", "", str, 1);
                    } else {
                        ToastUtil.a((int) R.string.save_success);
                    }
                } else if (this.isSharing) {
                    ToastUtil.a((int) R.string.share_failed);
                } else {
                    ToastUtil.a((int) R.string.save_fail);
                }
            }
        }

        private void deleteM3U8() {
            Device b = SmartHomeDeviceManager.a().b(AlarmVideoNormalPlayerActivity.this.mCameraDevice.getDid());
            if (b != null) {
                List<CloudVideoDownloadInfo> records = CloudVideoDownloadManager.getInstance().getRecords(b.userId, b.did);
                CloudVideoDownloadInfo cloudVideoDownloadInfo = null;
                for (int i = 0; i < records.size(); i++) {
                    cloudVideoDownloadInfo = records.get(i);
                    if (cloudVideoDownloadInfo.fileId.equals(AlarmVideoNormalPlayerActivity.this.fileId)) {
                        break;
                    }
                }
                if (cloudVideoDownloadInfo != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(cloudVideoDownloadInfo);
                    CloudVideoDownloadManager.getInstance().deleteRecords(arrayList);
                }
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 1) {
            if (i == 17 && i2 == -1) {
                refreshContent();
            }
        } else if (i2 == -1) {
            Event.a(Event.aQ);
        }
    }

    public void onResume() {
        super.onResume();
        this.ivVideoViewCover.setVisibility(8);
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

    public void onPause() {
        super.onPause();
        if (this.simpleExoPlayer != null) {
            if (this.simpleExoPlayer.getPlayWhenReady()) {
                this.storedState = 2;
            } else {
                this.storedState = 1;
            }
            this.simpleExoPlayer.setPlayWhenReady(false);
        }
        this.mHandler.removeCallbacks(this.getCurrentPosRunnable);
    }

    public void showLoading() {
        this.loadingContainer.setVisibility(0);
        this.loadingCircle.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_rotate_360));
    }

    public void hideLoading() {
        this.loadingCircle.clearAnimation();
        this.loadingContainer.setVisibility(8);
    }

    private void showDownloadActivityHint() {
        this.tvDownloadHint.setVisibility(0);
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (AlarmVideoNormalPlayerActivity.this.tvDownloadHint != null) {
                    AlarmVideoNormalPlayerActivity.this.tvDownloadHint.setVisibility(8);
                }
            }
        }, 3000);
    }
}
