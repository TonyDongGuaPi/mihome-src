package com.xiaomi.smarthome.camera.activity.alarm;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.Utils.FileUtil;
import com.mi.global.bbs.manager.Region;
import com.mijia.app.AppConfig;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.mijia.model.alarm.AlarmItemV2;
import com.mijia.model.alarm.AlarmManagerV2;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.XmMp4Player;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.activity.local.LocalAudioPlay;
import com.xiaomi.smarthome.camera.view.CameraPullDownRefreshListView;
import com.xiaomi.smarthome.camera.view.widget.LoadMoreView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.plugin.mpk.PluginHostApiImpl;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.stat.d;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

public class AlarmV2Activity extends CameraBaseActivity implements View.OnClickListener {
    private static final String TAG = "AlarmV2Activity";
    private final int MSG_DOWN_FILE = 11;
    private final int MSG_SAVE_FAILURE = 15;
    private final int MSG_SAVE_START = 16;
    private final int MSG_SAVE_STOP = 17;
    private final int MSG_SAVE_SUCCESS = 14;
    private final int MSG_UPDATE_PROGRESS = 12;
    private final int MSG_UPDATE_SEEK_PROGRESS = 13;
    /* access modifiers changed from: private */
    public String currentPlayingFilePath = null;
    private boolean isCreate = true;
    /* access modifiers changed from: private */
    public boolean isMute = true;
    /* access modifiers changed from: private */
    public AlarmAdapterV2 mAdapter;
    /* access modifiers changed from: private */
    public AlarmItemV2 mAlarmItemV2;
    /* access modifiers changed from: private */
    public SimpleDateFormat mAlarmTimeFormat;
    /* access modifiers changed from: private */
    public TextView mAlarmTimeView;
    /* access modifiers changed from: private */
    public boolean mAutoPause = false;
    /* access modifiers changed from: private */
    public TextView mDurationView;
    private RelativeLayout mEmptyLayout;
    /* access modifiers changed from: private */
    public boolean mHasMore = true;
    private LinearLayout mHistoryLayout;
    /* access modifiers changed from: private */
    public boolean mIsMoreLoading = false;
    private View mLoadIng;
    /* access modifiers changed from: private */
    public LoadMoreView mLoadMoreView;
    private AnimationDrawable mLoadingAnimation;
    private LocalAudioPlay mLocalAudioPlay = null;
    /* access modifiers changed from: private */
    public CameraPullDownRefreshListView mMediaListView;
    private boolean mNeedCheck = false;
    private boolean mNeedPincode = false;
    /* access modifiers changed from: private */
    public long mPathTime = -1;
    /* access modifiers changed from: private */
    public ImageView mPauseBtn;
    private ImageView mPlayBg;
    /* access modifiers changed from: private */
    public ImageView mPlayBtn;
    /* access modifiers changed from: private */
    public TextView mPlayingView;
    /* access modifiers changed from: private */
    public SeekBar mProgressBar;
    /* access modifiers changed from: private */
    public XQProgressDialog mPullingDlg;
    /* access modifiers changed from: private */
    public FrameLayout mReplayLayout;
    private ImageView mScreenFull;
    private SeekBar.OnSeekBarChangeListener mSeekBarChange = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (AlarmV2Activity.this.mSeekBarTouched) {
                AlarmV2Activity.this.mHandler.removeMessages(13);
                AlarmV2Activity.this.mPlayingView.setText(AlarmV2Activity.this.mTimeFormat.format(Integer.valueOf(i * 1000)));
                Message obtainMessage = AlarmV2Activity.this.mHandler.obtainMessage();
                obtainMessage.what = 13;
                obtainMessage.arg1 = i;
                obtainMessage.sendToTarget();
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            AlarmV2Activity.this.mSeekBarTouched = true;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            AlarmV2Activity.this.mSeekBarTouched = false;
        }
    };
    boolean mSeekBarTouched = false;
    private View mShareView;
    private TextView mSubTitle;
    /* access modifiers changed from: private */
    public SimpleDateFormat mTimeFormat;
    /* access modifiers changed from: private */
    public XmMp4Player mVideoPlayerRender;
    private TextView mVideoProgress;
    /* access modifiers changed from: private */
    public View mVideoTool;
    private XmVideoViewGl mVideoView;
    private FrameLayout mVideoViewFrame;
    private FrameLayout titleBar;
    /* access modifiers changed from: private */
    public ImageView videoMute;

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 11:
                if (!PluginHostApiImpl.instance().checkAndRequestPermisson(this, true, (Callback<List<String>>) null, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                    Toast.makeText(activity(), R.string.no_write_permission, 1).show();
                    return;
                }
                if (this.mReplayLayout.getVisibility() != 8) {
                    this.mReplayLayout.setVisibility(8);
                }
                if (this.mCameraDevice != null) {
                    showLoading();
                    this.mCameraDevice.j().a((Context) this, this.mAlarmItemV2, (AlarmManagerV2.IAlarmCallback<String>) new AlarmManagerV2.IAlarmCallback<String>() {
                        public void onSuccess(String str, Object obj) {
                            if (!AlarmV2Activity.this.isFinishing()) {
                                AlarmV2Activity.this.hideLoading();
                                if (!TextUtils.isEmpty(str)) {
                                    AlarmV2Activity.this.startPlay(str, true);
                                }
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (!AlarmV2Activity.this.isFinishing()) {
                                AlarmV2Activity.this.hideLoading();
                                SDKLog.b(AlarmV2Activity.TAG, "getAlarmFile errorCode:" + i + " errorMessage:" + str);
                            }
                        }
                    });
                    return;
                }
                return;
            case 12:
                this.mHandler.removeMessages(12);
                if (!this.mSeekBarTouched) {
                    int currentPosition = this.mVideoPlayerRender.getCurrentPosition();
                    if (currentPosition >= 0 && currentPosition <= 60000) {
                        this.mPlayingView.setText(this.mTimeFormat.format(Integer.valueOf(currentPosition)));
                        this.mProgressBar.setProgress(currentPosition / 1000);
                    } else {
                        return;
                    }
                }
                this.mHandler.sendEmptyMessageDelayed(12, 200);
                return;
            case 13:
                this.mVideoPlayerRender.seekTo(this.mProgressBar.getProgress() * 1000);
                return;
            case 14:
                ToastUtil.a((Context) activity(), (int) R.string.save_success);
                return;
            case 15:
                ToastUtil.a((Context) activity(), (int) R.string.retry_download_media_file);
                return;
            case 16:
                if (this.mPullingDlg != null) {
                    this.mPullingDlg.show();
                    return;
                }
                return;
            case 17:
                if (this.mPullingDlg != null && this.mPullingDlg.isShowing()) {
                    this.mPullingDlg.dismiss();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPullingDlg = new XQProgressDialog(this);
        this.mPullingDlg.setCancelable(true);
        this.mPullingDlg.setMessage(getResources().getString(R.string.loading));
        setContentView(R.layout.camera_activity_alarm_v2);
        this.mNeedCheck = getIntent().getBooleanExtra("check", false);
        SDKLog.e("alarm", "need check " + this.mNeedCheck);
        if (this.mDeviceStat == null) {
            finish();
            return;
        }
        if (this.mNeedCheck) {
            enableVerifyPincode();
            SDKLog.e("alarm", "enableVerifyPincode " + this.mDeviceStat.isSetPinCode);
            if (this.mCameraDevice.m()) {
                this.mNeedPincode = true;
                SDKLog.b(TAG, "need pincode ");
            }
        }
        this.mTimeFormat = new SimpleDateFormat("mm:ss");
        this.mTimeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String str = null;
        if (XmPluginHostApi.instance().getSettingLocale() != null) {
            str = XmPluginHostApi.instance().getSettingLocale().getLanguage();
        }
        if (XmPluginHostApi.instance().getApiLevel() < 51 || (!"de".equalsIgnoreCase(str) && !d.u.equalsIgnoreCase(str) && !"fr".equalsIgnoreCase(str) && !"it".equalsIgnoreCase(str) && !d.U.equalsIgnoreCase(str) && !Region.RU.equalsIgnoreCase(str))) {
            this.mAlarmTimeFormat = new SimpleDateFormat("yyy-MM-dd");
        } else {
            this.mAlarmTimeFormat = new SimpleDateFormat("dd-MM-yyyy");
        }
        this.mAlarmItemV2 = new AlarmItemV2();
        initView();
        initVideoView();
        showEmptyView(true);
        this.mPullingDlg.show();
    }

    private void initView() {
        this.titleBar = (FrameLayout) findViewById(R.id.title_bar);
        this.mAlarmTimeView = (TextView) findViewById(R.id.alarm_time);
        this.mPlayBg = (ImageView) findViewById(R.id.img_alert_new_video_jpg);
        this.mLoadIng = findViewById(R.id.loading_lyt);
        this.mVideoProgress = (TextView) findViewById(R.id.text_new_video_progress);
        this.mHistoryLayout = (LinearLayout) findViewById(R.id.history_alarm_layout);
        this.mPauseBtn = (ImageView) findViewById(R.id.btn_alert_main_pause);
        this.mPlayBtn = (ImageView) findViewById(R.id.btn_alert_main_play);
        this.mPauseBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (AlarmV2Activity.this.mVideoPlayerRender.isPlaying()) {
                    AlarmV2Activity.this.mVideoPlayerRender.pause();
                }
                boolean unused = AlarmV2Activity.this.mAutoPause = false;
                AlarmV2Activity.this.mPauseBtn.setVisibility(8);
                AlarmV2Activity.this.mPlayBtn.setVisibility(0);
            }
        });
        this.mPlayBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (AlarmV2Activity.this.mReplayLayout.getVisibility() == 0) {
                    AlarmV2Activity.this.mReplayLayout.setVisibility(8);
                }
                AlarmV2Activity.this.startPlay(AlarmV2Activity.this.currentPlayingFilePath, false);
                AlarmV2Activity.this.mPlayBtn.setVisibility(8);
                if (AlarmV2Activity.this.mReplayLayout.getVisibility() == 0) {
                    AlarmV2Activity.this.mReplayLayout.setVisibility(8);
                }
                AlarmV2Activity.this.mPauseBtn.setVisibility(0);
            }
        });
        this.mScreenFull = (ImageView) findViewById(R.id.btn_alert_main_full_screen);
        this.mScreenFull.setOnClickListener(this);
        findViewById(R.id.ivSave).setOnClickListener(this);
        this.mProgressBar = (SeekBar) findViewById(R.id.progress_bar);
        this.mProgressBar.setOnSeekBarChangeListener(this.mSeekBarChange);
        this.mDurationView = (TextView) findViewById(R.id.progress_duration);
        this.mPlayingView = (TextView) findViewById(R.id.progress_playtime);
        this.mLoadingAnimation = (AnimationDrawable) getResources().getDrawable(R.drawable.camera_anim_loading);
        ((ImageView) findViewById(R.id.loading_anim)).setImageDrawable(this.mLoadingAnimation);
        findViewById(R.id.history_alarm).setOnClickListener(this);
        ImageView imageView = (ImageView) findViewById(R.id.title_bar_more);
        this.mShareView = findViewById(R.id.title_bar_share);
        this.mShareView.setVisibility(8);
        this.mShareView.setOnClickListener(this);
        imageView.setImageResource(R.drawable.camera_tittlebar_set);
        imageView.setOnClickListener(this);
        this.mEmptyLayout = (RelativeLayout) findViewById(R.id.layout_empty);
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.main_play_file);
        this.mSubTitle = (TextView) findViewById(R.id.sub_title_bar_title);
        this.mSubTitle.setVisibility(0);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        this.mMediaListView = (CameraPullDownRefreshListView) findViewById(R.id.list_event_listview);
        this.mAdapter = new AlarmAdapterV2(activity());
        this.mLoadMoreView = (LoadMoreView) getLayoutInflater().inflate(R.layout.camera_load_more_data, (ViewGroup) null);
        this.mMediaListView.addFooterView(this.mLoadMoreView);
        this.mLoadMoreView.setLoadingNone();
        this.mMediaListView.setAdapter(this.mAdapter);
        this.mMediaListView.setRefreshListener(new CameraPullDownRefreshListView.OnRefreshListener() {
            public void startRefresh() {
                AlarmV2Activity.this.loadData(true, false);
            }
        });
        this.mMediaListView.setOnLastItemVisibleListener(new CameraPullDownRefreshListView.OnLastItemVisibleListener() {
            public void onLastItemVisible() {
                if (AlarmV2Activity.this.mHasMore && !AlarmV2Activity.this.mIsMoreLoading) {
                    AlarmV2Activity.this.loadMoreData();
                }
            }
        });
        this.videoMute = (ImageView) findViewById(R.id.video_mute);
        this.videoMute.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (AlarmV2Activity.this.isMute) {
                    AlarmV2Activity.this.videoMute.setImageResource(R.drawable.camera_alarm_icon_unmute);
                    boolean unused = AlarmV2Activity.this.isMute = false;
                    AlarmV2Activity.this.mVideoPlayerRender.setVolume(1);
                    return;
                }
                AlarmV2Activity.this.videoMute.setImageResource(R.drawable.camera_alarm_icon_mute);
                boolean unused2 = AlarmV2Activity.this.isMute = true;
                AlarmV2Activity.this.mVideoPlayerRender.setVolume(0);
            }
        });
        this.mReplayLayout = (FrameLayout) findViewById(R.id.replay_layout);
        findViewById(R.id.replay_btn).setOnClickListener(this);
        this.mMediaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int i2 = i - 1;
                if (i2 >= 0 && i2 < AlarmV2Activity.this.mAdapter.getCount()) {
                    AlarmItemV2 unused = AlarmV2Activity.this.mAlarmItemV2 = AlarmV2Activity.this.mAdapter.getItem(i2);
                    AlarmV2Activity.this.mAlarmTimeView.setVisibility(0);
                    AlarmV2Activity.this.mAlarmTimeView.setText(AlarmV2Activity.this.mAlarmTimeFormat.format(Long.valueOf(AlarmV2Activity.this.mAlarmItemV2.c)));
                    if (AlarmV2Activity.this.mVideoPlayerRender.isPlaying()) {
                        AlarmV2Activity.this.mVideoPlayerRender.pause();
                        AlarmV2Activity.this.mVideoPlayerRender.seekTo(0);
                    }
                    AlarmV2Activity.this.mHandler.removeMessages(12);
                    AlarmV2Activity.this.mHandler.sendEmptyMessage(11);
                    AlarmV2Activity.this.mPauseBtn.setVisibility(0);
                    AlarmV2Activity.this.mPlayBtn.setVisibility(8);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void loadData(boolean z, final boolean z2) {
        this.mCameraDevice.j().a((Callback<Void>) new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!AlarmV2Activity.this.isFinishing()) {
                    if (AlarmV2Activity.this.mPullingDlg != null && AlarmV2Activity.this.mPullingDlg.isShowing()) {
                        AlarmV2Activity.this.mPullingDlg.dismiss();
                    }
                    if (AlarmV2Activity.this.mMediaListView.isRefreshing()) {
                        AlarmV2Activity.this.mMediaListView.postRefresh();
                    }
                    List<AlarmItemV2> c = AlarmV2Activity.this.mCameraDevice.j().c();
                    if (c == null || c.isEmpty()) {
                        AlarmV2Activity.this.showEmptyView(true);
                        return;
                    }
                    AlarmV2Activity.this.mAdapter.setData(c, AlarmV2Activity.this.mCameraDevice.getDid());
                    AlarmV2Activity.this.showEmptyView(false);
                    AlarmItemV2 unused = AlarmV2Activity.this.mAlarmItemV2 = c.get(0);
                    AlarmV2Activity.this.mAlarmTimeView.setVisibility(0);
                    AlarmV2Activity.this.mAlarmTimeView.setText(AlarmV2Activity.this.mAlarmTimeFormat.format(Long.valueOf(AlarmV2Activity.this.mAlarmItemV2.c)));
                    if (z2) {
                        if (AlarmV2Activity.this.mVideoPlayerRender.isPlaying()) {
                            AlarmV2Activity.this.mVideoPlayerRender.pause();
                            AlarmV2Activity.this.mVideoPlayerRender.seekTo(0);
                        }
                        AlarmV2Activity.this.mHandler.removeMessages(12);
                        AlarmV2Activity.this.mHandler.sendEmptyMessage(11);
                        AlarmV2Activity.this.mPauseBtn.setVisibility(0);
                        AlarmV2Activity.this.mPlayBtn.setVisibility(8);
                        long unused2 = AlarmV2Activity.this.mPathTime = AlarmV2Activity.this.mAlarmItemV2.c;
                    }
                }
            }

            public void onFailure(int i, String str) {
                if (!AlarmV2Activity.this.isFinishing()) {
                    if (AlarmV2Activity.this.mPullingDlg != null && AlarmV2Activity.this.mPullingDlg.isShowing()) {
                        AlarmV2Activity.this.mPullingDlg.dismiss();
                    }
                    if (AlarmV2Activity.this.mMediaListView.isRefreshing()) {
                        AlarmV2Activity.this.mMediaListView.postRefresh();
                    }
                    AlarmV2Activity.this.showEmptyView(true);
                }
            }
        }, z);
    }

    /* access modifiers changed from: private */
    public void loadMoreData() {
        this.mIsMoreLoading = true;
        List<AlarmItemV2> c = this.mCameraDevice.j().c();
        if (c != null && !c.isEmpty()) {
            this.mCameraDevice.j().a((Callback<Void>) new Callback<Void>() {
                public void onSuccess(Void voidR) {
                    if (!AlarmV2Activity.this.isFinishing()) {
                        boolean unused = AlarmV2Activity.this.mIsMoreLoading = false;
                        boolean unused2 = AlarmV2Activity.this.mHasMore = AlarmV2Activity.this.mCameraDevice.j().b();
                        if (AlarmV2Activity.this.mHasMore) {
                            AlarmV2Activity.this.mLoadMoreView.setLoadingData();
                        } else {
                            AlarmV2Activity.this.mLoadMoreView.setLoadingNone();
                        }
                        List<AlarmItemV2> c = AlarmV2Activity.this.mCameraDevice.j().c();
                        if (c != null && !c.isEmpty()) {
                            AlarmV2Activity.this.mAdapter.setData(c, AlarmV2Activity.this.mCameraDevice.getDid());
                        }
                    }
                }

                public void onFailure(int i, String str) {
                    if (!AlarmV2Activity.this.isFinishing()) {
                        boolean unused = AlarmV2Activity.this.mIsMoreLoading = false;
                        AlarmV2Activity.this.mLoadMoreView.setLoadingNone();
                    }
                }
            }, false);
        }
    }

    /* access modifiers changed from: private */
    public void showEmptyView(boolean z) {
        if (z) {
            this.mVideoViewFrame.setVisibility(8);
            this.mMediaListView.setVisibility(8);
            this.mHistoryLayout.setVisibility(8);
            this.mEmptyLayout.setVisibility(0);
            this.mShareView.setVisibility(8);
            return;
        }
        this.mVideoViewFrame.setVisibility(0);
        this.mMediaListView.setVisibility(0);
        this.mHistoryLayout.setVisibility(0);
        this.mEmptyLayout.setVisibility(8);
        this.mShareView.setVisibility(0);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_alert_main_full_screen:
                if (1 == getRequestedOrientation()) {
                    setOrientation(true);
                    return;
                } else {
                    setOrientation(false);
                    return;
                }
            case R.id.history_alarm:
                resetVideoStats();
                startActivity(new Intent(this, AlarmDayV2Activity.class));
                return;
            case R.id.ivSave:
                saveMediaFile();
                return;
            case R.id.replay_btn:
                this.mPlayBtn.performClick();
                this.mReplayLayout.setVisibility(8);
                return;
            case R.id.title_bar_more:
                if (this.mCameraDevice.isReadOnlyShared()) {
                    Toast.makeText(activity(), R.string.auth_fail, 0).show();
                    return;
                } else {
                    startActivity(new Intent(this, AlarmSettingV2Activity.class));
                    return;
                }
            case R.id.title_bar_return:
                finish();
                return;
            case R.id.title_bar_share:
                if (TextUtils.isEmpty(this.currentPlayingFilePath)) {
                    Toast.makeText(activity(), R.string.alarm_share_none, 0).show();
                    return;
                } else if (!new File(this.currentPlayingFilePath).exists()) {
                    Toast.makeText(activity(), R.string.alarm_share_none, 0).show();
                    return;
                } else {
                    saveMediaFileWithoutHint();
                    return;
                }
            default:
                return;
        }
    }

    public void onResume() {
        super.onResume();
        if (!this.mNeedPincode || !TextUtils.isEmpty(this.mCameraDevice.r())) {
            if (this.isCreate) {
                loadData(this.isCreate, true);
            }
            this.isCreate = false;
            if (this.mAutoPause && this.mReplayLayout.getVisibility() == 8) {
                this.mPauseBtn.setVisibility(0);
                this.mPlayBtn.setVisibility(8);
                startPlay(this.currentPlayingFilePath, false);
            }
        }
    }

    public void onStop() {
        super.onStop();
    }

    public void onPause() {
        super.onPause();
        if (this.mVideoPlayerRender.isPlaying()) {
            this.mAutoPause = true;
            this.mVideoPlayerRender.pause();
        }
        this.mHandler.removeCallbacksAndMessages((Object) null);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mVideoView != null) {
            this.mVideoView.release();
        }
        if (this.mLocalAudioPlay != null) {
            this.mLocalAudioPlay.release();
        }
        this.mHandler.removeMessages(12);
        if (this.mAdapter != null) {
            this.mAdapter.destroyDisplayImageOptions();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setWindow(configuration);
    }

    /* access modifiers changed from: private */
    public void startPlay(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            this.currentPlayingFilePath = null;
            this.mHandler.sendEmptyMessage(11);
            return;
        }
        this.mAdapter.addDownSuccess(this.mAlarmItemV2);
        this.mSubTitle.setText(this.mAlarmItemV2.k);
        this.mPlayBg.setVisibility(8);
        if (str.equals(this.currentPlayingFilePath)) {
            this.mVideoPlayerRender.openVideoPlayer(str);
            if (this.isMute) {
                this.mVideoPlayerRender.setVolume(0);
            } else {
                this.mVideoPlayerRender.setVolume(1);
            }
        } else {
            this.mVideoPlayerRender.changeSource(str);
        }
        this.currentPlayingFilePath = str;
        this.mPauseBtn.setVisibility(0);
        this.mPlayBtn.setVisibility(8);
        this.mShareView.setVisibility(0);
        this.mHandler.sendEmptyMessage(12);
    }

    private void setWindow(Configuration configuration) {
        if (configuration.orientation != 1) {
            activity().getWindow().setFlags(1024, 1024);
            this.titleBar.setVisibility(8);
            this.mScreenFull.setImageResource(R.drawable.camera_alarm_icon_mixscreen);
            this.mEmptyLayout.setVisibility(8);
            this.mMediaListView.setVisibility(8);
            this.mHistoryLayout.setVisibility(8);
            ViewGroup.LayoutParams layoutParams = this.mVideoViewFrame.getLayoutParams();
            layoutParams.height = -1;
            layoutParams.width = -1;
            this.mVideoView.setVideoFrameSize(-1, -1, true);
            return;
        }
        activity().getWindow().clearFlags(1024);
        this.mScreenFull.setImageResource(R.drawable.camera_icon_fullscreen2);
        this.titleBar.setVisibility(0);
        this.mMediaListView.setVisibility(0);
        this.mHistoryLayout.setVisibility(0);
        ViewGroup.LayoutParams layoutParams2 = this.mVideoViewFrame.getLayoutParams();
        layoutParams2.height = (AppConfig.b * 9) / 16;
        layoutParams2.width = -1;
        this.mVideoView.setVideoFrameSize(-1, -1, false);
    }

    public void onBackPressed() {
        if (1 != getRequestedOrientation()) {
            setOrientation(false);
        } else {
            super.onBackPressed();
        }
    }

    private void initVideoView() {
        this.mVideoTool = findViewById(R.id.layout_video_tools);
        this.mVideoViewFrame = (FrameLayout) findViewById(R.id.video_frame);
        this.mVideoViewFrame.getLayoutParams().height = (AppConfig.b * 9) / 16;
        FrameLayout frameLayout = new FrameLayout(activity());
        this.mVideoViewFrame.addView(frameLayout, 0, new FrameLayout.LayoutParams(-1, -1));
        boolean contains = Util.c.contains(Build.MODEL);
        this.mVideoView = XmPluginHostApi.instance().createMp4View(activity(), frameLayout, !contains);
        this.mVideoPlayerRender = this.mVideoView.getMp4Player();
        if (contains) {
            this.mLocalAudioPlay = new LocalAudioPlay(activity());
            this.mVideoPlayerRender.setAudioListener(this.mLocalAudioPlay);
        }
        this.mVideoView.setMiniScale(true);
        this.mVideoView.setVideoViewListener(new XmVideoViewGl.IVideoViewListener() {
            public void onVideoViewClick() {
                if (AlarmV2Activity.this.mVideoTool.getVisibility() == 8) {
                    AlarmV2Activity.this.mVideoTool.setVisibility(0);
                } else {
                    AlarmV2Activity.this.mVideoTool.setVisibility(8);
                }
            }
        });
        this.mVideoPlayerRender.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                AlarmV2Activity.this.runMainThread(new Runnable() {
                    public void run() {
                        AlarmV2Activity.this.mPlayBtn.setVisibility(0);
                        AlarmV2Activity.this.mPauseBtn.setVisibility(8);
                        AlarmV2Activity.this.mHandler.removeMessages(12);
                        AlarmV2Activity.this.mProgressBar.setProgress(AlarmV2Activity.this.mProgressBar.getMax());
                        if (AlarmV2Activity.this.mVideoPlayerRender.getDuration() >= 0) {
                            AlarmV2Activity.this.mPlayingView.setText(AlarmV2Activity.this.mTimeFormat.format(Integer.valueOf(AlarmV2Activity.this.mVideoPlayerRender.getDuration())));
                        }
                        AlarmV2Activity.this.mReplayLayout.setVisibility(0);
                    }
                });
            }
        });
        this.mVideoPlayerRender.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                AlarmV2Activity.this.runMainThread(new Runnable() {
                    public void run() {
                        AlarmV2Activity.this.showError();
                    }
                });
                return true;
            }
        });
        this.mVideoPlayerRender.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                AlarmV2Activity.this.runMainThread(new Runnable() {
                    public void run() {
                        AlarmV2Activity.this.mProgressBar.setMax(AlarmV2Activity.this.mVideoPlayerRender.getDuration() / 1000);
                        AlarmV2Activity.this.mProgressBar.setProgress(0);
                        AlarmV2Activity.this.mProgressBar.setVisibility(0);
                        AlarmV2Activity.this.mPlayingView.setText("00:00");
                        if (AlarmV2Activity.this.mReplayLayout.getVisibility() != 8) {
                            AlarmV2Activity.this.mReplayLayout.setVisibility(8);
                        }
                        AlarmV2Activity.this.mDurationView.setText(AlarmV2Activity.this.mTimeFormat.format(Integer.valueOf(AlarmV2Activity.this.mVideoPlayerRender.getDuration())));
                        if (AlarmV2Activity.this.isMute) {
                            AlarmV2Activity.this.mVideoPlayerRender.setVolume(0);
                        } else {
                            AlarmV2Activity.this.mVideoPlayerRender.setVolume(1);
                        }
                    }
                });
            }
        });
        this.mVideoView.initial();
    }

    /* access modifiers changed from: private */
    public void hideLoading() {
        if (this.mLoadIng.getVisibility() == 0) {
            this.mLoadIng.setVisibility(8);
        }
        this.mLoadingAnimation.stop();
    }

    private void showLoading() {
        if (this.mLoadIng.getVisibility() == 8) {
            this.mLoadIng.setVisibility(0);
        }
        if (!this.mLoadingAnimation.isRunning()) {
            this.mLoadingAnimation.start();
        }
    }

    private void resetVideoStats() {
        this.mHandler.removeMessages(12);
        this.mPlayBtn.setVisibility(0);
        this.mPauseBtn.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void showError() {
        this.mProgressBar.setProgress(0);
        this.mPlayingView.setText(this.mTimeFormat.format(0));
        this.mPauseBtn.setVisibility(8);
        this.mPlayBtn.setVisibility(0);
        this.mReplayLayout.setVisibility(0);
        this.mHandler.removeMessages(12);
    }

    private void saveMediaFile() {
        if (!TextUtils.isEmpty(this.currentPlayingFilePath)) {
            final String a2 = FileUtil.a(true, this.mCameraDevice.getDid());
            if (!TextUtils.isEmpty(a2)) {
                new Thread(new Runnable() {
                    /* JADX WARNING: Removed duplicated region for block: B:36:0x009e A[SYNTHETIC, Splitter:B:36:0x009e] */
                    /* JADX WARNING: Removed duplicated region for block: B:41:0x00bf A[SYNTHETIC, Splitter:B:41:0x00bf] */
                    /* JADX WARNING: Removed duplicated region for block: B:50:0x00e9 A[SYNTHETIC, Splitter:B:50:0x00e9] */
                    /* JADX WARNING: Removed duplicated region for block: B:55:0x010a A[SYNTHETIC, Splitter:B:55:0x010a] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                            r8 = this;
                            r0 = 0
                            r1 = 17
                            com.xiaomi.smarthome.camera.activity.alarm.AlarmV2Activity r2 = com.xiaomi.smarthome.camera.activity.alarm.AlarmV2Activity.this     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            android.os.Handler r2 = r2.mHandler     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            r3 = 16
                            r2.sendEmptyMessage(r3)     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            com.xiaomi.smarthome.camera.activity.alarm.AlarmV2Activity r3 = com.xiaomi.smarthome.camera.activity.alarm.AlarmV2Activity.this     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            java.lang.String r3 = r3.currentPlayingFilePath     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            r2.<init>(r3)     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0077, all -> 0x0070 }
                            java.lang.String r4 = r0     // Catch:{ IOException -> 0x0077, all -> 0x0070 }
                            r3.<init>(r4)     // Catch:{ IOException -> 0x0077, all -> 0x0070 }
                            r0 = 1024(0x400, float:1.435E-42)
                            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x006b, all -> 0x0065 }
                        L_0x0022:
                            int r4 = r2.read(r0)     // Catch:{ IOException -> 0x006b, all -> 0x0065 }
                            r5 = -1
                            if (r4 == r5) goto L_0x002e
                            r5 = 0
                            r3.write(r0, r5, r4)     // Catch:{ IOException -> 0x006b, all -> 0x0065 }
                            goto L_0x0022
                        L_0x002e:
                            com.xiaomi.smarthome.camera.activity.alarm.AlarmV2Activity r0 = com.xiaomi.smarthome.camera.activity.alarm.AlarmV2Activity.this     // Catch:{ IOException -> 0x006b, all -> 0x0065 }
                            android.os.Handler r0 = r0.mHandler     // Catch:{ IOException -> 0x006b, all -> 0x0065 }
                            r4 = 14
                            r0.sendEmptyMessage(r4)     // Catch:{ IOException -> 0x006b, all -> 0x0065 }
                            r2.close()     // Catch:{ IOException -> 0x003b }
                            goto L_0x0056
                        L_0x003b:
                            r0 = move-exception
                            java.lang.String r2 = "alarm"
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder
                            r4.<init>()
                            java.lang.String r5 = ""
                            r4.append(r5)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r4.append(r0)
                            java.lang.String r0 = r4.toString()
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r2, r0)
                        L_0x0056:
                            r3.close()     // Catch:{ IOException -> 0x005b }
                            goto L_0x00de
                        L_0x005b:
                            r0 = move-exception
                            java.lang.String r2 = "alarm"
                            java.lang.StringBuilder r3 = new java.lang.StringBuilder
                            r3.<init>()
                            goto L_0x00cb
                        L_0x0065:
                            r0 = move-exception
                            r7 = r2
                            r2 = r0
                            r0 = r7
                            goto L_0x00e7
                        L_0x006b:
                            r0 = move-exception
                            r7 = r2
                            r2 = r0
                            r0 = r7
                            goto L_0x0082
                        L_0x0070:
                            r3 = move-exception
                            r7 = r3
                            r3 = r0
                            r0 = r2
                            r2 = r7
                            goto L_0x00e7
                        L_0x0077:
                            r3 = move-exception
                            r7 = r3
                            r3 = r0
                            r0 = r2
                            r2 = r7
                            goto L_0x0082
                        L_0x007d:
                            r2 = move-exception
                            r3 = r0
                            goto L_0x00e7
                        L_0x0080:
                            r2 = move-exception
                            r3 = r0
                        L_0x0082:
                            java.lang.String r4 = "alarm"
                            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e6 }
                            r5.<init>()     // Catch:{ all -> 0x00e6 }
                            java.lang.String r6 = ""
                            r5.append(r6)     // Catch:{ all -> 0x00e6 }
                            java.lang.String r2 = r2.getLocalizedMessage()     // Catch:{ all -> 0x00e6 }
                            r5.append(r2)     // Catch:{ all -> 0x00e6 }
                            java.lang.String r2 = r5.toString()     // Catch:{ all -> 0x00e6 }
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r4, r2)     // Catch:{ all -> 0x00e6 }
                            if (r0 == 0) goto L_0x00bd
                            r0.close()     // Catch:{ IOException -> 0x00a2 }
                            goto L_0x00bd
                        L_0x00a2:
                            r0 = move-exception
                            java.lang.String r2 = "alarm"
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder
                            r4.<init>()
                            java.lang.String r5 = ""
                            r4.append(r5)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r4.append(r0)
                            java.lang.String r0 = r4.toString()
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r2, r0)
                        L_0x00bd:
                            if (r3 == 0) goto L_0x00de
                            r3.close()     // Catch:{ IOException -> 0x00c3 }
                            goto L_0x00de
                        L_0x00c3:
                            r0 = move-exception
                            java.lang.String r2 = "alarm"
                            java.lang.StringBuilder r3 = new java.lang.StringBuilder
                            r3.<init>()
                        L_0x00cb:
                            java.lang.String r4 = ""
                            r3.append(r4)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r3.append(r0)
                            java.lang.String r0 = r3.toString()
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r2, r0)
                        L_0x00de:
                            com.xiaomi.smarthome.camera.activity.alarm.AlarmV2Activity r0 = com.xiaomi.smarthome.camera.activity.alarm.AlarmV2Activity.this
                            android.os.Handler r0 = r0.mHandler
                            r0.sendEmptyMessage(r1)
                            return
                        L_0x00e6:
                            r2 = move-exception
                        L_0x00e7:
                            if (r0 == 0) goto L_0x0108
                            r0.close()     // Catch:{ IOException -> 0x00ed }
                            goto L_0x0108
                        L_0x00ed:
                            r0 = move-exception
                            java.lang.String r4 = "alarm"
                            java.lang.StringBuilder r5 = new java.lang.StringBuilder
                            r5.<init>()
                            java.lang.String r6 = ""
                            r5.append(r6)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r5.append(r0)
                            java.lang.String r0 = r5.toString()
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r4, r0)
                        L_0x0108:
                            if (r3 == 0) goto L_0x0129
                            r3.close()     // Catch:{ IOException -> 0x010e }
                            goto L_0x0129
                        L_0x010e:
                            r0 = move-exception
                            java.lang.StringBuilder r3 = new java.lang.StringBuilder
                            r3.<init>()
                            java.lang.String r4 = ""
                            r3.append(r4)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r3.append(r0)
                            java.lang.String r0 = r3.toString()
                            java.lang.String r3 = "alarm"
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r3, r0)
                        L_0x0129:
                            com.xiaomi.smarthome.camera.activity.alarm.AlarmV2Activity r0 = com.xiaomi.smarthome.camera.activity.alarm.AlarmV2Activity.this
                            android.os.Handler r0 = r0.mHandler
                            r0.sendEmptyMessage(r1)
                            throw r2
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.alarm.AlarmV2Activity.AnonymousClass15.run():void");
                    }
                }).start();
                return;
            }
            return;
        }
        this.mHandler.sendEmptyMessage(15);
    }

    /* access modifiers changed from: protected */
    public void setOrientation(boolean z) {
        if (z) {
            setRequestedOrientation(0);
        } else {
            setRequestedOrientation(1);
        }
    }

    private void saveMediaFileWithoutHint() {
        if (!TextUtils.isEmpty(this.currentPlayingFilePath)) {
            final String a2 = FileUtil.a(true, this.mCameraDevice.getDid());
            if (!TextUtils.isEmpty(a2)) {
                new Thread(new Runnable() {
                    /* JADX WARNING: Removed duplicated region for block: B:33:0x008a A[SYNTHETIC, Splitter:B:33:0x008a] */
                    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ab A[SYNTHETIC, Splitter:B:38:0x00ab] */
                    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ce A[SYNTHETIC, Splitter:B:45:0x00ce] */
                    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ef A[SYNTHETIC, Splitter:B:50:0x00ef] */
                    /* JADX WARNING: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                            r7 = this;
                            r0 = 0
                            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x006a, all -> 0x0065 }
                            com.xiaomi.smarthome.camera.activity.alarm.AlarmV2Activity r2 = com.xiaomi.smarthome.camera.activity.alarm.AlarmV2Activity.this     // Catch:{ IOException -> 0x006a, all -> 0x0065 }
                            java.lang.String r2 = r2.currentPlayingFilePath     // Catch:{ IOException -> 0x006a, all -> 0x0065 }
                            r1.<init>(r2)     // Catch:{ IOException -> 0x006a, all -> 0x0065 }
                            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0060, all -> 0x005a }
                            java.lang.String r3 = r0     // Catch:{ IOException -> 0x0060, all -> 0x005a }
                            r2.<init>(r3)     // Catch:{ IOException -> 0x0060, all -> 0x005a }
                            r0 = 1024(0x400, float:1.435E-42)
                            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x0058 }
                        L_0x0017:
                            int r3 = r1.read(r0)     // Catch:{ IOException -> 0x0058 }
                            r4 = -1
                            if (r3 == r4) goto L_0x0023
                            r4 = 0
                            r2.write(r0, r4, r3)     // Catch:{ IOException -> 0x0058 }
                            goto L_0x0017
                        L_0x0023:
                            java.lang.String r0 = r0     // Catch:{ IOException -> 0x0058 }
                            com.xiaomi.smarthome.camera.activity.alarm.AlarmV2Activity r3 = com.xiaomi.smarthome.camera.activity.alarm.AlarmV2Activity.this     // Catch:{ IOException -> 0x0058 }
                            r3.toShare(r0)     // Catch:{ IOException -> 0x0058 }
                            r1.close()     // Catch:{ IOException -> 0x002e }
                            goto L_0x0049
                        L_0x002e:
                            r0 = move-exception
                            java.lang.String r1 = "AlarmV2Activity"
                            java.lang.StringBuilder r3 = new java.lang.StringBuilder
                            r3.<init>()
                            java.lang.String r4 = ""
                            r3.append(r4)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r3.append(r0)
                            java.lang.String r0 = r3.toString()
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r1, r0)
                        L_0x0049:
                            r2.close()     // Catch:{ IOException -> 0x004e }
                            goto L_0x00ca
                        L_0x004e:
                            r0 = move-exception
                            java.lang.String r1 = "AlarmV2Activity"
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder
                            r2.<init>()
                            goto L_0x00b7
                        L_0x0058:
                            r0 = move-exception
                            goto L_0x006e
                        L_0x005a:
                            r2 = move-exception
                            r6 = r2
                            r2 = r0
                            r0 = r6
                            goto L_0x00cc
                        L_0x0060:
                            r2 = move-exception
                            r6 = r2
                            r2 = r0
                            r0 = r6
                            goto L_0x006e
                        L_0x0065:
                            r1 = move-exception
                            r2 = r0
                            r0 = r1
                            r1 = r2
                            goto L_0x00cc
                        L_0x006a:
                            r1 = move-exception
                            r2 = r0
                            r0 = r1
                            r1 = r2
                        L_0x006e:
                            java.lang.String r3 = "AlarmV2Activity"
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cb }
                            r4.<init>()     // Catch:{ all -> 0x00cb }
                            java.lang.String r5 = ""
                            r4.append(r5)     // Catch:{ all -> 0x00cb }
                            java.lang.String r0 = r0.getLocalizedMessage()     // Catch:{ all -> 0x00cb }
                            r4.append(r0)     // Catch:{ all -> 0x00cb }
                            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x00cb }
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r3, r0)     // Catch:{ all -> 0x00cb }
                            if (r1 == 0) goto L_0x00a9
                            r1.close()     // Catch:{ IOException -> 0x008e }
                            goto L_0x00a9
                        L_0x008e:
                            r0 = move-exception
                            java.lang.String r1 = "AlarmV2Activity"
                            java.lang.StringBuilder r3 = new java.lang.StringBuilder
                            r3.<init>()
                            java.lang.String r4 = ""
                            r3.append(r4)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r3.append(r0)
                            java.lang.String r0 = r3.toString()
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r1, r0)
                        L_0x00a9:
                            if (r2 == 0) goto L_0x00ca
                            r2.close()     // Catch:{ IOException -> 0x00af }
                            goto L_0x00ca
                        L_0x00af:
                            r0 = move-exception
                            java.lang.String r1 = "AlarmV2Activity"
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder
                            r2.<init>()
                        L_0x00b7:
                            java.lang.String r3 = ""
                            r2.append(r3)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r2.append(r0)
                            java.lang.String r0 = r2.toString()
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r1, r0)
                        L_0x00ca:
                            return
                        L_0x00cb:
                            r0 = move-exception
                        L_0x00cc:
                            if (r1 == 0) goto L_0x00ed
                            r1.close()     // Catch:{ IOException -> 0x00d2 }
                            goto L_0x00ed
                        L_0x00d2:
                            r1 = move-exception
                            java.lang.String r3 = "AlarmV2Activity"
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder
                            r4.<init>()
                            java.lang.String r5 = ""
                            r4.append(r5)
                            java.lang.String r1 = r1.getLocalizedMessage()
                            r4.append(r1)
                            java.lang.String r1 = r4.toString()
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r3, r1)
                        L_0x00ed:
                            if (r2 == 0) goto L_0x010e
                            r2.close()     // Catch:{ IOException -> 0x00f3 }
                            goto L_0x010e
                        L_0x00f3:
                            r1 = move-exception
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder
                            r2.<init>()
                            java.lang.String r3 = ""
                            r2.append(r3)
                            java.lang.String r1 = r1.getLocalizedMessage()
                            r2.append(r1)
                            java.lang.String r1 = r2.toString()
                            java.lang.String r2 = "AlarmV2Activity"
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r2, r1)
                        L_0x010e:
                            throw r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.alarm.AlarmV2Activity.AnonymousClass16.run():void");
                    }
                }).start();
                return;
            }
            return;
        }
        this.mHandler.sendEmptyMessage(15);
    }

    /* access modifiers changed from: private */
    public void toShare(String str) {
        if (!TextUtils.isEmpty(str)) {
            openShareVideoActivity(activity(), "", "", str, 1);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 9999) {
            return;
        }
        if (!TextUtils.isEmpty(XmPluginHostApi.instance().getDevicePincode(this.mDid))) {
            loadData(true, true);
            this.mCameraDevice.f((Callback<Void>) null);
            return;
        }
        SDKLog.e("alarm", "getDevicePincode empty ");
    }
}
