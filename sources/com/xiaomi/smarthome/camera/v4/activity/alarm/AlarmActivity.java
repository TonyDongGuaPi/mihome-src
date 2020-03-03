package com.xiaomi.smarthome.camera.v4.activity.alarm;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
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
import com.mijia.app.AppConfig;
import com.mijia.app.Event;
import com.mijia.model.alarm.AlarmItem;
import com.mijia.model.alarm.FDSFileManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.activity.local.LocalAudioPlay;
import com.xiaomi.smarthome.camera.v4.view.CustomPullDownRefreshListView;
import com.xiaomi.smarthome.camera.v4.view.VideoViewGl;
import com.xiaomi.smarthome.camera.view.widget.LoadMoreView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.fastvideo.VideoFrameDecoder;
import com.xiaomi.smarthome.fastvideo.VideoGlSurfaceView;
import com.xiaomi.smarthome.fastvideo.VideoPlayerBase;
import com.xiaomi.smarthome.fastvideo.VideoPlayerFFmpeg;
import com.xiaomi.smarthome.fastvideo.VideoPlayerRender;
import com.xiaomi.smarthome.fastvideo.decoder.Mp4LowBlockHevc;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.framework.plugin.mpk.PluginHostApiImpl;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class AlarmActivity extends CameraBaseActivity implements View.OnClickListener {
    static final int MSG_SAVE_FAILURE = 15;
    static final int MSG_SAVE_START = 16;
    static final int MSG_SAVE_STOP = 17;
    static final int MSG_SAVE_SUCCESS = 14;
    static final int MSG_UPDATE_PROGRESS = 12;
    static final int MSG_UPDATE_SEEK_PROGRESS = 13;
    final int MSG_DOWN_FILE = 11;
    /* access modifiers changed from: private */
    public String currentPlayingFilePath = null;
    /* access modifiers changed from: private */
    public boolean isMute = true;
    private ImageView ivSave;
    /* access modifiers changed from: private */
    public AlarmAdapter mAdapter;
    /* access modifiers changed from: private */
    public AlarmItem mAlarmItem;
    /* access modifiers changed from: private */
    public boolean mAutoPause = false;
    private String mCurrentPath = "";
    /* access modifiers changed from: private */
    public TextView mDurationView;
    private RelativeLayout mEmptyLayout;
    /* access modifiers changed from: private */
    public boolean mFullScreen;
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
    public CustomPullDownRefreshListView mMediaListView;
    FDSFileManager.OnDownloadListener mOnDownloadListener = new FDSFileManager.OnDownloadListener() {
        public void onDownloading(FDSFileManager.DownloadItem downloadItem, String str, final int i) {
            if (downloadItem.f8040a.b == AlarmActivity.this.mAlarmItem.b) {
                AlarmActivity.this.runMainThread(new Runnable() {
                    public void run() {
                        AlarmActivity.this.showProgress(i);
                        String unused = AlarmActivity.this.currentPlayingFilePath = null;
                    }
                });
            }
        }

        public void onDownloadSuccess(FDSFileManager.DownloadItem downloadItem, final String str) {
            if (downloadItem.f8040a.b == AlarmActivity.this.mAlarmItem.b) {
                AlarmActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        AlarmActivity.this.hideProgress();
                        AlarmActivity.this.mPauseBtn.setEnabled(true);
                        String unused = AlarmActivity.this.currentPlayingFilePath = str;
                        AlarmActivity.this.startPlay(str);
                    }
                });
            }
        }

        public void onDownloadFailed(FDSFileManager.DownloadItem downloadItem, String str, int i, String str2) {
            if (downloadItem.f8040a.b == AlarmActivity.this.mAlarmItem.b) {
                AlarmActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(AlarmActivity.this, R.string.sdcard_video_download_failed, 0).show();
                        AlarmActivity.this.mPauseBtn.setVisibility(8);
                        AlarmActivity.this.mPlayBtn.setVisibility(0);
                        String unused = AlarmActivity.this.currentPlayingFilePath = null;
                        AlarmActivity.this.hideProgress();
                    }
                });
            }
        }
    };
    /* access modifiers changed from: private */
    public ImageView mPauseBtn;
    /* access modifiers changed from: private */
    public ImageView mPlayBg;
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
            if (AlarmActivity.this.mSeekBarTouched) {
                AlarmActivity.this.mHandler.removeMessages(13);
                AlarmActivity.this.mPlayingView.setText(AlarmActivity.this.mTimeFormat.format(Integer.valueOf(i * 1000)));
                Message obtainMessage = AlarmActivity.this.mHandler.obtainMessage();
                obtainMessage.what = 13;
                obtainMessage.arg1 = i;
                obtainMessage.sendToTarget();
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            AlarmActivity.this.mSeekBarTouched = true;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            AlarmActivity.this.mSeekBarTouched = false;
        }
    };
    boolean mSeekBarTouched = false;
    private SharePrefHelper mSharePrefHelper;
    private View mShareView;
    /* access modifiers changed from: private */
    public SimpleDateFormat mTimeFormat;
    /* access modifiers changed from: private */
    public VideoPlayerBase mVideoPlayerRender;
    private TextView mVideoProgress;
    /* access modifiers changed from: private */
    public View mVideoTool;
    /* access modifiers changed from: private */
    public VideoViewGl mVideoView;
    private FrameLayout mVideoViewFrame;
    private FrameLayout title_bar;
    /* access modifiers changed from: private */
    public ImageView videoMute;

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 11:
                if (!PluginHostApiImpl.instance().checkAndRequestPermisson(this, true, (Callback<List<String>>) null, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                    Toast.makeText(this, R.string.no_write_permission, 1).show();
                    return;
                }
                if (this.mReplayLayout.getVisibility() == 0) {
                    this.mReplayLayout.setVisibility(8);
                }
                if (this.mCameraDevice != null) {
                    this.mCameraDevice.k().a(this.mOnDownloadListener);
                    this.mCameraDevice.k().c(this.mAlarmItem);
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
                ToastUtil.a((Context) this, (int) R.string.save_success);
                return;
            case 15:
                ToastUtil.a((Context) this, (int) R.string.retry_download_media_file);
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
        setContentView(R.layout.activity_alarm);
        this.mTimeFormat = new SimpleDateFormat("mm:ss");
        this.mTimeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        this.title_bar = (FrameLayout) findViewById(R.id.title_bar);
        this.mAlarmItem = new AlarmItem();
        this.mSharePrefHelper = new SharePrefHelper(this, "housekeping", this.mDid);
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        if (TextUtils.isEmpty(this.mSharePrefHelper.getStrValue("click_alarm_list"))) {
            this.mSharePrefHelper.putValue("click_alarm_list", format);
        } else if (this.mSharePrefHelper.getStrValue("click_alarm_list").indexOf(format) < 0) {
            this.mSharePrefHelper.putValue("click_alarm_list", format);
        }
        initView();
        initVideoView();
        showEmptyView(true);
        this.mPullingDlg.show();
        Event.c();
    }

    private void initView() {
        this.mPlayBg = (ImageView) findViewById(R.id.img_alert_new_video_jpg);
        this.mLoadIng = findViewById(R.id.loading_lyt);
        this.mVideoProgress = (TextView) findViewById(R.id.text_new_video_progress);
        this.mHistoryLayout = (LinearLayout) findViewById(R.id.history_alarm_layout);
        getResources().getDisplayMetrics();
        this.mPauseBtn = (ImageView) findViewById(R.id.btn_alert_main_pause);
        this.mPlayBtn = (ImageView) findViewById(R.id.btn_alert_main_play);
        this.mPauseBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (AlarmActivity.this.mVideoPlayerRender.isPlaying()) {
                    AlarmActivity.this.mVideoPlayerRender.pause();
                }
                boolean unused = AlarmActivity.this.mAutoPause = false;
                AlarmActivity.this.mPauseBtn.setVisibility(8);
                AlarmActivity.this.mPlayBtn.setVisibility(0);
            }
        });
        this.mPlayBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlarmActivity.this.mPauseBtn.setEnabled(false);
                AlarmActivity.this.mHandler.sendEmptyMessage(11);
                AlarmActivity.this.mPlayBtn.setVisibility(8);
                AlarmActivity.this.mPauseBtn.setVisibility(0);
            }
        });
        this.mScreenFull = (ImageView) findViewById(R.id.btn_alert_main_full_screen);
        this.mScreenFull.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlarmActivity.this.setFullScreen(!AlarmActivity.this.mFullScreen);
            }
        });
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
        imageView.setImageResource(R.drawable.std_tittlebar_main_device_set);
        imageView.setOnClickListener(this);
        this.mEmptyLayout = (RelativeLayout) findViewById(R.id.layout_empty);
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.main_play_file_v4);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        this.mMediaListView = (CustomPullDownRefreshListView) findViewById(R.id.list_event_listview);
        this.mAdapter = new AlarmAdapter(this, this.mDid);
        this.mLoadMoreView = (LoadMoreView) getLayoutInflater().inflate(R.layout.load_more_data, (ViewGroup) null);
        this.mMediaListView.addFooterView(this.mLoadMoreView);
        this.mMediaListView.setAdapter(this.mAdapter);
        this.mMediaListView.setRefreshListener(new CustomPullDownRefreshListView.OnRefreshListener() {
            public void startRefresh() {
                AlarmActivity.this.loadData(true);
            }
        });
        this.mMediaListView.setOnLastItemVisibleListener(new CustomPullDownRefreshListView.OnLastItemVisibleListener() {
            public void onLastItemVisible() {
                if (AlarmActivity.this.mHasMore && !AlarmActivity.this.mIsMoreLoading) {
                    AlarmActivity.this.loadMoreData();
                }
            }
        });
        this.videoMute = (ImageView) findViewById(R.id.video_mute);
        this.videoMute.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (AlarmActivity.this.isMute) {
                    AlarmActivity.this.videoMute.setImageResource(R.drawable.camera_alarm_icon_unmute);
                    boolean unused = AlarmActivity.this.isMute = false;
                    AlarmActivity.this.mVideoPlayerRender.setVolume(1);
                    return;
                }
                AlarmActivity.this.videoMute.setImageResource(R.drawable.camera_alarm_icon_mute);
                boolean unused2 = AlarmActivity.this.isMute = true;
                AlarmActivity.this.mVideoPlayerRender.setVolume(0);
            }
        });
        this.ivSave = (ImageView) findViewById(R.id.ivSave);
        this.ivSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlarmActivity.this.saveMediaFile();
            }
        });
        this.mReplayLayout = (FrameLayout) findViewById(R.id.replay_layout);
        findViewById(R.id.replay_btn).setOnClickListener(this);
        this.mMediaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int i2 = i - 1;
                if (i2 >= 0 && i2 < AlarmActivity.this.mAdapter.getCount()) {
                    AlarmItem unused = AlarmActivity.this.mAlarmItem = AlarmActivity.this.mAdapter.getItem(i2);
                    if (AlarmActivity.this.mVideoView != null && AlarmActivity.this.mVideoPlayerRender.isPlaying()) {
                        AlarmActivity.this.mVideoPlayerRender.pause();
                        AlarmActivity.this.mVideoPlayerRender.seekTo(0);
                    }
                    AlarmActivity.this.mHandler.removeMessages(12);
                    AlarmActivity.this.mHandler.sendEmptyMessage(11);
                    AlarmActivity.this.mPauseBtn.setVisibility(0);
                    AlarmActivity.this.mPlayBtn.setVisibility(8);
                }
            }
        });
        Date date = new Date(System.currentTimeMillis());
        ((TextView) findViewById(R.id.alarm_list_date)).setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    /* access modifiers changed from: private */
    public void loadData(boolean z) {
        this.mCameraDevice.i().a((Callback<Void>) new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!AlarmActivity.this.isFinishing()) {
                    boolean unused = AlarmActivity.this.mHasMore = AlarmActivity.this.mCameraDevice.i().b();
                    if (AlarmActivity.this.mHasMore) {
                        AlarmActivity.this.mLoadMoreView.setLoadingData();
                    } else {
                        AlarmActivity.this.mLoadMoreView.setLoadingNone();
                    }
                    if (AlarmActivity.this.mPullingDlg != null && AlarmActivity.this.mPullingDlg.isShowing()) {
                        AlarmActivity.this.mPullingDlg.dismiss();
                    }
                    if (AlarmActivity.this.mMediaListView.isRefreshing()) {
                        AlarmActivity.this.mMediaListView.postRefresh();
                    }
                    List<AlarmItem> c = AlarmActivity.this.mCameraDevice.i().c();
                    if (!c.isEmpty()) {
                        AlarmActivity.this.mAdapter.setData(c, AlarmActivity.this.mCameraDevice.getDid());
                        AlarmActivity.this.showEmptyView(false);
                        AlarmItem unused2 = AlarmActivity.this.mAlarmItem = c.get(0);
                        ImageLoader.a().a(AlarmActivity.this.mAlarmItem.a(), AlarmActivity.this.mPlayBg);
                        return;
                    }
                    AlarmActivity.this.showEmptyView(true);
                }
            }

            public void onFailure(int i, String str) {
                if (!AlarmActivity.this.isFinishing()) {
                    if (AlarmActivity.this.mPullingDlg != null && AlarmActivity.this.mPullingDlg.isShowing()) {
                        AlarmActivity.this.mPullingDlg.dismiss();
                    }
                    if (AlarmActivity.this.mMediaListView.isRefreshing()) {
                        AlarmActivity.this.mMediaListView.postRefresh();
                    }
                    AlarmActivity.this.showEmptyView(true);
                }
            }
        }, z);
    }

    /* access modifiers changed from: private */
    public void loadMoreData() {
        this.mIsMoreLoading = true;
        this.mCameraDevice.i().a((Callback<Void>) new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!AlarmActivity.this.isFinishing()) {
                    boolean unused = AlarmActivity.this.mIsMoreLoading = false;
                    boolean unused2 = AlarmActivity.this.mHasMore = AlarmActivity.this.mCameraDevice.i().b();
                    if (AlarmActivity.this.mHasMore) {
                        AlarmActivity.this.mLoadMoreView.setLoadingData();
                    } else {
                        AlarmActivity.this.mLoadMoreView.setLoadingNone();
                    }
                    AlarmActivity.this.mAdapter.setData(AlarmActivity.this.mCameraDevice.i().c(), AlarmActivity.this.mCameraDevice.getDid());
                }
            }

            public void onFailure(int i, String str) {
                if (!AlarmActivity.this.isFinishing()) {
                    boolean unused = AlarmActivity.this.mIsMoreLoading = false;
                    AlarmActivity.this.mLoadMoreView.setLoadingNone();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void showEmptyView(boolean z) {
        if (z) {
            this.mVideoViewFrame.setVisibility(8);
            this.mMediaListView.setVisibility(8);
            this.mHistoryLayout.setVisibility(8);
            this.mEmptyLayout.setVisibility(0);
            return;
        }
        this.mVideoViewFrame.setVisibility(0);
        this.mMediaListView.setVisibility(0);
        this.mHistoryLayout.setVisibility(0);
        this.mEmptyLayout.setVisibility(8);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.history_alarm:
                resetVideoStats();
                startActivity(new Intent(this, AlarmDayActivity.class));
                return;
            case R.id.replay_btn:
                this.mPlayBtn.performClick();
                return;
            case R.id.title_bar_more:
                startActivity(new Intent(this, AlarmSettingActivity.class));
                return;
            case R.id.title_bar_return:
                finish();
                return;
            case R.id.title_bar_share:
                if (TextUtils.isEmpty(this.mCurrentPath)) {
                    Toast.makeText(this, R.string.alarm_share_none, 0).show();
                    return;
                } else if (!new File(this.mCurrentPath).exists()) {
                    Toast.makeText(this, R.string.alarm_share_none, 0).show();
                    return;
                } else {
                    FrameManager.b().k().openSharePictureActivity(this, "", "", this.mCurrentPath);
                    return;
                }
            default:
                return;
        }
    }

    public void onResume() {
        super.onResume();
        loadData(false);
        if (this.mAutoPause && this.mReplayLayout.getVisibility() == 8) {
            this.mPauseBtn.setVisibility(0);
            this.mPlayBtn.setVisibility(8);
            startPlay(this.mCurrentPath);
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
        this.mHandler.removeMessages(12);
        if (this.mAdapter != null) {
            this.mAdapter.destroyDisplayImageOptions();
        }
        if (this.mLocalAudioPlay != null) {
            this.mLocalAudioPlay.release();
        }
        Event.d();
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0036, code lost:
        if (r0.indexOf("" + r5.mAlarmItem.b) < 0) goto L_0x0038;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void startPlay(java.lang.String r6) {
        /*
            r5 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            com.xiaomi.smarthome.camera.v4.activity.alarm.SharePrefHelper r0 = r5.mSharePrefHelper
            java.lang.String r1 = "click_alarm_list"
            java.lang.String r0 = r0.getStrValue(r1)
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0038
            com.xiaomi.smarthome.camera.v4.activity.alarm.SharePrefHelper r0 = r5.mSharePrefHelper
            java.lang.String r1 = "click_alarm_list"
            java.lang.String r0 = r0.getStrValue(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = ""
            r1.append(r2)
            com.mijia.model.alarm.AlarmItem r2 = r5.mAlarmItem
            long r2 = r2.b
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            int r0 = r0.indexOf(r1)
            if (r0 >= 0) goto L_0x005f
        L_0x0038:
            com.xiaomi.smarthome.camera.v4.activity.alarm.SharePrefHelper r0 = r5.mSharePrefHelper
            java.lang.String r1 = "click_alarm_list"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            com.xiaomi.smarthome.camera.v4.activity.alarm.SharePrefHelper r3 = r5.mSharePrefHelper
            java.lang.String r4 = "click_alarm_list"
            java.lang.String r3 = r3.getStrValue(r4)
            r2.append(r3)
            java.lang.String r3 = "|"
            r2.append(r3)
            com.mijia.model.alarm.AlarmItem r3 = r5.mAlarmItem
            long r3 = r3.b
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.putValue(r1, r2)
        L_0x005f:
            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmAdapter r0 = r5.mAdapter
            com.mijia.model.alarm.AlarmItem r1 = r5.mAlarmItem
            r0.addDownSuccess(r1)
            com.xiaomi.smarthome.camera.v4.view.VideoViewGl r0 = r5.mVideoView
            r1 = 0
            r0.setVisibility(r1)
            android.widget.ImageView r0 = r5.mPlayBg
            r2 = 8
            r0.setVisibility(r2)
            java.lang.String r0 = r5.mCurrentPath
            boolean r0 = r0.equals(r6)
            if (r0 != 0) goto L_0x0093
            com.xiaomi.smarthome.fastvideo.VideoPlayerBase r0 = r5.mVideoPlayerRender
            r0.changeSource(r6)
            boolean r0 = r5.isMute
            if (r0 == 0) goto L_0x008a
            com.xiaomi.smarthome.fastvideo.VideoPlayerBase r0 = r5.mVideoPlayerRender
            r0.setVolume(r1)
            goto L_0x0090
        L_0x008a:
            com.xiaomi.smarthome.fastvideo.VideoPlayerBase r0 = r5.mVideoPlayerRender
            r1 = 1
            r0.setVolume(r1)
        L_0x0090:
            r5.mCurrentPath = r6
            goto L_0x0098
        L_0x0093:
            com.xiaomi.smarthome.fastvideo.VideoPlayerBase r0 = r5.mVideoPlayerRender
            r0.openVideoPlayer(r6)
        L_0x0098:
            android.os.Handler r6 = r5.mHandler
            r0 = 12
            r6.sendEmptyMessage(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmActivity.startPlay(java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public void setFullScreen(boolean z) {
        this.mFullScreen = z;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (i < i2) {
            i = displayMetrics.heightPixels;
            i2 = displayMetrics.widthPixels;
        }
        if (this.mFullScreen) {
            getWindow().setFlags(1024, 1024);
            setRequestedOrientation(6);
            findViewById(R.id.title_bar).setVisibility(8);
            this.mScreenFull.setImageResource(R.drawable.home_icon_mixscreen);
            this.mEmptyLayout.setVisibility(8);
            this.mMediaListView.setVisibility(8);
            this.mHistoryLayout.setVisibility(8);
            this.mVideoViewFrame.getLayoutParams().width = i;
            this.mVideoViewFrame.getLayoutParams().height = i2;
            this.mVideoViewFrame.setLayoutParams(this.mVideoViewFrame.getLayoutParams());
            return;
        }
        getWindow().clearFlags(1024);
        setRequestedOrientation(1);
        this.mScreenFull.setImageResource(R.drawable.home_icon_fullscreen);
        findViewById(R.id.title_bar).setVisibility(0);
        this.mMediaListView.setVisibility(0);
        this.mHistoryLayout.setVisibility(0);
        this.mVideoViewFrame.getLayoutParams().width = i2;
        this.mVideoViewFrame.getLayoutParams().height = dip2px(this, 203.0f);
        this.mVideoViewFrame.setLayoutParams(this.mVideoViewFrame.getLayoutParams());
    }

    public void onBackPressed() {
        if (this.mFullScreen) {
            runOnUiThread(new Runnable() {
                public void run() {
                    AlarmActivity.this.setFullScreen(false);
                }
            });
        } else {
            super.onBackPressed();
        }
    }

    private void initVideoView() {
        this.mVideoTool = findViewById(R.id.layout_video_tools);
        if (Mp4LowBlockHevc.isBlock(AppConfig.d())) {
            this.mVideoPlayerRender = new VideoPlayerFFmpeg((VideoGlSurfaceView) null);
            this.mLocalAudioPlay = new LocalAudioPlay(this);
            this.mVideoPlayerRender.setAudioListener(this.mLocalAudioPlay);
        } else {
            this.mVideoPlayerRender = new VideoPlayerRender((VideoGlSurfaceView) null);
        }
        this.mVideoView = new VideoViewGl((Context) this, (VideoFrameDecoder) this.mVideoPlayerRender);
        this.mVideoViewFrame = (FrameLayout) findViewById(R.id.video_frame);
        this.mVideoViewFrame.getLayoutParams().height = (AppConfig.b * 9) / 16;
        this.mVideoViewFrame.addView(this.mVideoView, 0, new FrameLayout.LayoutParams(-1, -1));
        this.mVideoView.initial();
        this.mVideoView.setVideoViewListener(new VideoViewGl.IVideoViewListener() {
            public void onVideoViewClick() {
                if (AlarmActivity.this.mVideoTool.getVisibility() == 8) {
                    AlarmActivity.this.mVideoTool.setVisibility(0);
                } else {
                    AlarmActivity.this.mVideoTool.setVisibility(8);
                }
            }
        });
        this.mVideoPlayerRender.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                AlarmActivity.this.runMainThread(new Runnable() {
                    public void run() {
                        AlarmActivity.this.mPauseBtn.setEnabled(true);
                        AlarmActivity.this.mPlayBtn.setVisibility(0);
                        AlarmActivity.this.mPauseBtn.setVisibility(8);
                        AlarmActivity.this.mHandler.removeMessages(12);
                        AlarmActivity.this.mProgressBar.setProgress(AlarmActivity.this.mProgressBar.getMax());
                        if (AlarmActivity.this.mVideoPlayerRender.getDuration() >= 0) {
                            AlarmActivity.this.mPlayingView.setText(AlarmActivity.this.mTimeFormat.format(Integer.valueOf(AlarmActivity.this.mVideoPlayerRender.getDuration())));
                        }
                        AlarmActivity.this.mReplayLayout.setVisibility(0);
                    }
                });
            }
        });
        this.mVideoPlayerRender.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                AlarmActivity.this.runMainThread(new Runnable() {
                    public void run() {
                        AlarmActivity.this.showError();
                    }
                });
                return true;
            }
        });
        this.mVideoPlayerRender.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                AlarmActivity.this.runMainThread(new Runnable() {
                    public void run() {
                        AlarmActivity.this.mProgressBar.setMax(AlarmActivity.this.mVideoPlayerRender.getDuration() / 1000);
                        AlarmActivity.this.mProgressBar.setProgress(0);
                        AlarmActivity.this.mProgressBar.setVisibility(0);
                        AlarmActivity.this.mPlayingView.setText("00:00");
                        AlarmActivity.this.mDurationView.setText(AlarmActivity.this.mTimeFormat.format(Integer.valueOf(AlarmActivity.this.mVideoPlayerRender.getDuration())));
                        if (AlarmActivity.this.isMute) {
                            AlarmActivity.this.mVideoPlayerRender.setVolume(0);
                        } else {
                            AlarmActivity.this.mVideoPlayerRender.setVolume(1);
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void hideProgress() {
        if (this.mLoadIng.getVisibility() == 0) {
            this.mLoadIng.setVisibility(8);
        }
        this.mLoadingAnimation.stop();
        this.mVideoProgress.setText("");
    }

    /* access modifiers changed from: private */
    public void showProgress(int i) {
        if (this.mLoadIng.getVisibility() == 8) {
            this.mLoadIng.setVisibility(0);
        }
        if (!this.mLoadingAnimation.isRunning()) {
            this.mLoadingAnimation.start();
        }
        this.mVideoProgress.setText(getString(R.string.loading_hint, new Object[]{Integer.valueOf(i)}));
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

    /* access modifiers changed from: private */
    public void saveMediaFile() {
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
                            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmActivity r2 = com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmActivity.this     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            android.os.Handler r2 = r2.mHandler     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            r3 = 16
                            r2.sendEmptyMessage(r3)     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmActivity r3 = com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmActivity.this     // Catch:{ IOException -> 0x0080, all -> 0x007d }
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
                            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmActivity r0 = com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmActivity.this     // Catch:{ IOException -> 0x006b, all -> 0x0065 }
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
                            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmActivity r0 = com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmActivity.this
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
                            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmActivity r0 = com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmActivity.this
                            android.os.Handler r0 = r0.mHandler
                            r0.sendEmptyMessage(r1)
                            throw r2
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmActivity.AnonymousClass18.run():void");
                    }
                }).start();
                return;
            }
            return;
        }
        this.mHandler.sendEmptyMessage(15);
    }
}
