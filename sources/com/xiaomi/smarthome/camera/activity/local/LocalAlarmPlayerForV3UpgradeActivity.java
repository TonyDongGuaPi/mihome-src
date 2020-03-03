package com.xiaomi.smarthome.camera.activity.local;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.Utils.FileUtil;
import com.Utils.NetworkUtil;
import com.Utils.Util;
import com.alipay.mobile.common.logging.api.ProcessInfo;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.mijia.model.alarm.AlarmItemV2;
import com.mijia.model.alarm.AlarmManagerV2;
import com.mijia.model.alarm.AlarmNetUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.XmMp4Player;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.exopackage.MJExoPlayer;
import com.xiaomi.smarthome.camera.view.ControlMatrixView;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocalAlarmPlayerForV3UpgradeActivity extends CameraBaseActivity implements View.OnClickListener {
    static final int MSG_SAVE_FAILURE = 15;
    static final int MSG_SAVE_START = 16;
    static final int MSG_SAVE_STOP = 17;
    static final int MSG_SAVE_SUCCESS = 14;
    static final int MSG_UPDATE_PROGRESS = 1;
    static final int MSG_UPDATE_SEEK_PROGRESS = 2;
    static final int MSG_WATI_TIME = 3;
    private static final String TAG = "LocalAlarmPlayerV2Activity";
    private final int EVENT_UPDATE_PROGRESS = 102;
    CheckBox cbTogglePlay;
    /* access modifiers changed from: private */
    public long curTime = 0;
    /* access modifiers changed from: private */
    public String currentPlayFilePath = null;
    private ControlMatrixView custom_control_matrix;
    private FrameLayout fl_container;
    private boolean isInit = false;
    private boolean isMute = false;
    /* access modifiers changed from: private */
    public boolean isNetShowing = false;
    private boolean isResumed = false;
    ImageView ivFullScreen;
    /* access modifiers changed from: private */
    public boolean mActivePause = false;
    AlarmItemV2 mAlarItem;
    AlarmManagerV2 mAlarmFileManager;
    View mBottomViewContainer;
    private boolean mCanRetry = false;
    /* access modifiers changed from: private */
    public TextView mDurationView;
    ImageView mFullScreenView;
    /* access modifiers changed from: private */
    public boolean mHasStart = false;
    /* access modifiers changed from: private */
    public boolean mIsPlaying = false;
    AnimationDrawable mLoadingAnimation;
    private LocalAudioPlay mLocalAudioPlay = null;
    private boolean mPlayAutoPause = false;
    /* access modifiers changed from: private */
    public TextView mPlayingView;
    SeekBar mProgressBar;
    View mProgressBarContainer;
    private int mRotation = 0;
    private SeekBar.OnSeekBarChangeListener mSeekBarChange = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (LocalAlarmPlayerForV3UpgradeActivity.this.mSeekBarTouched) {
                SDKLog.b(LocalAlarmPlayerForV3UpgradeActivity.TAG, "onProgressChanged ");
                long unused = LocalAlarmPlayerForV3UpgradeActivity.this.curTime = 0;
                LocalAlarmPlayerForV3UpgradeActivity.this.mjExoPlayer.seekTo((long) i);
                LocalAlarmPlayerForV3UpgradeActivity.this.mPlayingView.setText(LocalAlarmPlayerForV3UpgradeActivity.convertMMSS(i / 1000));
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            LocalAlarmPlayerForV3UpgradeActivity.this.mSeekBarTouched = true;
            LocalAlarmPlayerForV3UpgradeActivity.this.mjExoPlayer.pausePlay();
            LocalAlarmPlayerForV3UpgradeActivity.this.mHandler.removeMessages(102);
            SDKLog.b(LocalAlarmPlayerForV3UpgradeActivity.TAG, "onStartTrackingTouch ");
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            SDKLog.b(LocalAlarmPlayerForV3UpgradeActivity.TAG, "onStopTrackingTouch ");
            if (!LocalAlarmPlayerForV3UpgradeActivity.this.mActivePause) {
                LocalAlarmPlayerForV3UpgradeActivity.this.mSeekBarTouched = false;
                LocalAlarmPlayerForV3UpgradeActivity.this.mjExoPlayer.resumePlay();
                boolean unused = LocalAlarmPlayerForV3UpgradeActivity.this.mIsPlaying = true;
                LocalAlarmPlayerForV3UpgradeActivity.this.sendMsgUpdateProgressOnce();
            }
        }
    };
    boolean mSeekBarTouched = false;
    SimpleDateFormat mTimeFormat;
    LinearLayout mToastCenter;
    ImageView mToastImg;
    View mTopViewContainer;
    private String mUrl = "";
    XmMp4Player mVideoPlayerRender;
    XmVideoViewGl mVideoView;
    FrameLayout mVideoViewFrame;
    /* access modifiers changed from: private */
    public MJExoPlayer mjExoPlayer;
    private FrameLayout title_bar;
    /* access modifiers changed from: private */
    public String videoFilePath;
    private ImageView videoMute;

    public void handleMessage(Message message) {
        super.handleMessage(message);
        int i = message.what;
        if (i != 102) {
            switch (i) {
                case 1:
                case 2:
                    return;
                case 3:
                    this.mCanRetry = false;
                    return;
                default:
                    switch (i) {
                        case 14:
                            ToastUtil.a((Context) activity(), (int) R.string.save_success);
                            return;
                        case 15:
                            ToastUtil.a((Context) activity(), (int) R.string.retry_download_media_file);
                            return;
                        default:
                            return;
                    }
            }
        } else {
            this.mDurationView.setText(convertMMSS(this.mProgressBar.getMax() / 1000));
            long currentPosition = this.mjExoPlayer.getCurrentPosition();
            if (this.curTime > currentPosition) {
                this.mHandler.sendEmptyMessageDelayed(102, 100);
                return;
            }
            if (currentPosition > this.mjExoPlayer.getDuration()) {
                currentPosition = this.mjExoPlayer.getDuration();
            }
            this.curTime = currentPosition;
            int i2 = (int) ((long) ((((int) (currentPosition + 500)) / 1000) * 1000));
            this.mProgressBar.setProgress(i2);
            this.mPlayingView.setText(convertMMSS(i2 / 1000));
            this.mHandler.sendEmptyMessageDelayed(102, 100);
            if (this.mjExoPlayer.getPlaybackState() == 4) {
                this.mHandler.removeMessages(102);
                this.mIsPlaying = false;
                this.cbTogglePlay.setChecked(true);
                if (this.mProgressBarContainer.getVisibility() == 8) {
                    AnimationUtils.loadAnimation(activity(), R.anim.slide_in_top).setFillAfter(true);
                    Animation loadAnimation = AnimationUtils.loadAnimation(activity(), R.anim.slide_in_bottom);
                    loadAnimation.setFillAfter(true);
                    this.mProgressBarContainer.setClickable(true);
                    this.mProgressBarContainer.setVisibility(0);
                    this.mProgressBarContainer.startAnimation(loadAnimation);
                }
            }
        }
    }

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.activity_alarm_video_play_for_v3_upgrade);
        AudioManager audioManager = (AudioManager) activity().getApplicationContext().getSystemService("audio");
        if (audioManager != null) {
            audioManager.requestAudioFocus((AudioManager.OnAudioFocusChangeListener) null, 3, 1);
        }
        this.mAlarmFileManager = this.mCameraDevice.j();
        String stringExtra = getIntent().getStringExtra("alarmItem");
        SDKLog.b(TAG, "fileId:" + stringExtra);
        if (!TextUtils.isEmpty(stringExtra)) {
            this.mAlarItem = new AlarmItemV2(stringExtra);
        }
        boolean booleanExtra = getIntent().getBooleanExtra(ProcessInfo.ALIAS_PUSH, false);
        this.mTimeFormat = new SimpleDateFormat("mm:ss");
        this.mTimeFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        initView();
        initViewNew();
        showNetAlarm();
        if (booleanExtra) {
            this.mCanRetry = true;
            this.mHandler.sendEmptyMessageDelayed(3, 18000);
        }
        if (this.mAlarItem != null) {
            playVideo();
        }
    }

    private void initView() {
        this.videoMute = (ImageView) findViewById(R.id.video_mute);
        this.videoMute.setOnClickListener(this);
        this.mToastCenter = (LinearLayout) findViewById(R.id.toast_container_center);
        this.mToastCenter.setOnClickListener(this);
        this.mToastImg = (ImageView) findViewById(R.id.toast_icon);
        this.mLoadingAnimation = (AnimationDrawable) getResources().getDrawable(R.drawable.camera_anim_loading);
        this.mToastImg.setImageDrawable(this.mLoadingAnimation);
        this.mToastImg.setOnClickListener(this);
        this.title_bar = (FrameLayout) findViewById(R.id.title_bar);
        TextView textView = (TextView) findViewById(R.id.title_bar_title);
        this.title_bar.bringToFront();
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.mBottomViewContainer = findViewById(R.id.bottom_tools_container);
        this.mTopViewContainer = findViewById(R.id.top_tools_container);
        this.mProgressBarContainer = findViewById(R.id.progress_bar_container);
        this.mProgressBar = (SeekBar) findViewById(R.id.progress_bar);
        this.mDurationView = (TextView) findViewById(R.id.progress_duration);
        this.mPlayingView = (TextView) findViewById(R.id.progress_playtime);
        if (this.mAlarItem != null) {
            this.mProgressBar.setMax((int) this.mAlarItem.f7969a);
            this.mDurationView.setText(this.mTimeFormat.format(new Date(this.mAlarItem.f7969a * 1000)));
        }
        this.mProgressBar.setOnSeekBarChangeListener(this.mSeekBarChange);
        this.mVideoViewFrame = (FrameLayout) findViewById(R.id.video_frame);
        this.mFullScreenView = (ImageView) findViewById(R.id.full_screen);
        this.cbTogglePlay = (CheckBox) findViewById(R.id.cbTogglePlay);
        this.cbTogglePlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    LocalAlarmPlayerForV3UpgradeActivity.this.pausePlay();
                    LocalAlarmPlayerForV3UpgradeActivity.this.cbTogglePlay.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, LocalAlarmPlayerForV3UpgradeActivity.this.getResources().getDrawable(R.drawable.home_icon_play02), (Drawable) null);
                    return;
                }
                boolean unused = LocalAlarmPlayerForV3UpgradeActivity.this.mIsPlaying = true;
                LocalAlarmPlayerForV3UpgradeActivity.this.playVideo();
                LocalAlarmPlayerForV3UpgradeActivity.this.cbTogglePlay.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, LocalAlarmPlayerForV3UpgradeActivity.this.getResources().getDrawable(R.drawable.home_icon_pause02), (Drawable) null);
            }
        });
        this.ivFullScreen = (ImageView) findViewById(R.id.ivFullScreen);
        this.ivFullScreen.setOnClickListener(this);
        this.mFullScreenView.setOnClickListener(this);
        findViewById(R.id.flip).setOnClickListener(this);
        findViewById(R.id.local_share).setOnClickListener(this);
        findViewById(R.id.local_delete).setOnClickListener(this);
        findViewById(R.id.local_save).setOnClickListener(this);
    }

    private void initViewNew() {
        this.custom_control_matrix = (ControlMatrixView) findViewById(R.id.custom_control_matrix);
        this.fl_container = (FrameLayout) findViewById(R.id.fl_container);
        this.mjExoPlayer = XmPluginHostApi.instance().createExoPlayer(this, this.fl_container, (AttributeSet) null, 0);
        this.mjExoPlayer.setVolume(0.0f);
        this.mjExoPlayer.setPlayerListener(new MJExoPlayer.MJExoPlayerListener() {
            public void onLoadingChanged(boolean z) {
            }

            public void onPlaybackParametersChanged(float f) {
            }

            public void onPlayerError(int i) {
            }

            public void onPlayerStateChanged(boolean z, int i) {
            }

            public void onPositionDiscontinuity(int i) {
            }

            public void onRepeatModeChanged(int i) {
            }

            public void onSeekProcessed() {
            }

            public void onShuffleModeEnabledChanged(boolean z) {
            }

            public void onVideoSizeChanged(int i, int i2, int i3, float f) {
            }

            public void onVideoSurfaceViewClicked(View view) {
            }

            public void onVideoSurfaceViewLongClicked(View view) {
            }

            public void onRenderedFirstFrame() {
                LocalAlarmPlayerForV3UpgradeActivity.this.mLoadingAnimation.stop();
                long duration = LocalAlarmPlayerForV3UpgradeActivity.this.mjExoPlayer.getDuration();
                LogUtil.a(LocalAlarmPlayerForV3UpgradeActivity.TAG, "onRenderedFirstFrame len=" + duration);
                int i = (((int) (duration + 500)) / 1000) * 1000;
                LogUtil.a(LocalAlarmPlayerForV3UpgradeActivity.TAG, "onRenderedFirstFrame max=" + i);
                LocalAlarmPlayerForV3UpgradeActivity.this.mProgressBar.setMax(i);
                LocalAlarmPlayerForV3UpgradeActivity.this.sendMsgUpdateProgressOnce();
            }
        });
        this.custom_control_matrix.setListener(new ControlMatrixView.ClickListener() {
            public void onClick() {
                if (LocalAlarmPlayerForV3UpgradeActivity.this.mProgressBarContainer.isShown()) {
                    AnimationUtils.loadAnimation(LocalAlarmPlayerForV3UpgradeActivity.this.activity(), R.anim.slide_out_top).setFillAfter(true);
                    Animation loadAnimation = AnimationUtils.loadAnimation(LocalAlarmPlayerForV3UpgradeActivity.this.activity(), R.anim.slide_out_bottom);
                    loadAnimation.setFillAfter(true);
                    LocalAlarmPlayerForV3UpgradeActivity.this.mProgressBarContainer.setClickable(false);
                    LocalAlarmPlayerForV3UpgradeActivity.this.mProgressBarContainer.setVisibility(8);
                    LocalAlarmPlayerForV3UpgradeActivity.this.mProgressBarContainer.startAnimation(loadAnimation);
                    return;
                }
                AnimationUtils.loadAnimation(LocalAlarmPlayerForV3UpgradeActivity.this.activity(), R.anim.slide_in_top).setFillAfter(true);
                Animation loadAnimation2 = AnimationUtils.loadAnimation(LocalAlarmPlayerForV3UpgradeActivity.this.activity(), R.anim.slide_in_bottom);
                loadAnimation2.setFillAfter(true);
                LocalAlarmPlayerForV3UpgradeActivity.this.mProgressBarContainer.setClickable(true);
                LocalAlarmPlayerForV3UpgradeActivity.this.mProgressBarContainer.setVisibility(0);
                LocalAlarmPlayerForV3UpgradeActivity.this.mProgressBarContainer.startAnimation(loadAnimation2);
            }
        });
    }

    public void sendMsgUpdateProgressOnce() {
        this.mHandler.removeMessages(102);
        this.mHandler.sendEmptyMessage(102);
    }

    public void onResume() {
        super.onResume();
        this.isResumed = true;
        if (this.mjExoPlayer != null) {
            int playbackState = this.mjExoPlayer.getPlaybackState();
            if (playbackState != 2 && !this.mActivePause) {
                this.curTime = 0;
                this.mjExoPlayer.resumePlay();
                sendMsgUpdateProgressOnce();
            } else if (playbackState == 2) {
                this.curTime = 0;
                this.mjExoPlayer.startPlay(this.mUrl);
                sendMsgUpdateProgressOnce();
            }
        }
    }

    public void onPause() {
        super.onPause();
        this.isResumed = false;
        if (this.mjExoPlayer != null) {
            this.mjExoPlayer.pausePlay();
        }
        this.mIsPlaying = false;
        this.mHandler.removeMessages(102);
    }

    public void onStop() {
        super.onStop();
        this.mHandler.removeMessages(102);
    }

    /* access modifiers changed from: package-private */
    public void startPlay() {
        if (!TextUtils.isEmpty(this.currentPlayFilePath) && this.isInit) {
            if (this.isMute) {
                this.mVideoPlayerRender.setVolume(0);
            } else {
                this.mVideoPlayerRender.setVolume(1);
            }
            this.mPlayAutoPause = false;
            this.mVideoPlayerRender.openVideoPlayer(this.currentPlayFilePath);
        }
        this.mHandler.sendEmptyMessage(1);
    }

    /* access modifiers changed from: private */
    public void pausePlay() {
        this.mIsPlaying = false;
        this.mActivePause = true;
        this.mjExoPlayer.pausePlay();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeMessages(1);
        if (this.mLoadingAnimation != null && this.mLoadingAnimation.isRunning()) {
            this.mLoadingAnimation.stop();
        }
        if (this.mjExoPlayer != null) {
            this.mjExoPlayer.stopPlay();
        }
        this.mHandler.removeMessages(102);
    }

    public void onBackPressed() {
        if (1 != getRequestedOrientation()) {
            setOrientation(false);
        } else {
            super.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setWindow(configuration);
    }

    private void setWindow(Configuration configuration) {
        if (configuration.orientation != 1) {
            getWindow().setFlags(1024, 1024);
            this.title_bar.setVisibility(8);
            this.mBottomViewContainer.setVisibility(8);
            this.ivFullScreen.setImageResource(R.drawable.alarm_icon_mixscreen);
            return;
        }
        getWindow().clearFlags(1024);
        this.title_bar.setVisibility(0);
        this.title_bar.bringToFront();
        this.mBottomViewContainer.setVisibility(0);
        this.ivFullScreen.setImageResource(R.drawable.alarm_icon_fullscreen);
    }

    private void showNetAlarm() {
        if (!TextUtils.isEmpty(this.mCameraDevice.r()) && NetworkUtil.c(activity()) && !this.isNetShowing) {
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity());
            builder.b((int) R.string.push_net_alarm);
            builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    LocalAlarmPlayerForV3UpgradeActivity.this.finish();
                }
            });
            builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    boolean unused = LocalAlarmPlayerForV3UpgradeActivity.this.isNetShowing = false;
                }
            });
            builder.d();
            this.isNetShowing = true;
        }
    }

    /* access modifiers changed from: private */
    public void showFail(String str) {
        if (this.mToastCenter.getVisibility() == 8) {
            this.mToastCenter.setVisibility(0);
        }
        if (this.mLoadingAnimation.isRunning()) {
            this.mLoadingAnimation.stop();
        }
        this.mToastImg.setImageResource(R.drawable.camera_icon_refresh_nor);
        Toast.makeText(activity(), R.string.sdcard_video_download_failed, 0).show();
        SDKLog.e(Tag.b, "Error alarm " + str);
    }

    private void hideFail() {
        this.mToastCenter.setVisibility(8);
    }

    private void showProgress() {
        if (this.mToastCenter.getVisibility() == 8) {
            this.mToastCenter.setVisibility(0);
        }
        this.mToastImg.setImageDrawable(this.mLoadingAnimation);
        this.mLoadingAnimation.start();
    }

    /* access modifiers changed from: private */
    public void hideProgress() {
        if (this.mLoadingAnimation.isRunning()) {
            this.mLoadingAnimation.stop();
        }
        if (this.mToastCenter.getVisibility() == 0) {
            this.mToastCenter.setVisibility(8);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.flip:
                this.mRotation += 90;
                this.mRotation %= 360;
                this.mVideoView.setRotation(this.mRotation);
                return;
            case R.id.ivFullScreen:
                if (1 == getRequestedOrientation()) {
                    setOrientation(true);
                    return;
                } else {
                    setOrientation(false);
                    return;
                }
            case R.id.local_delete:
                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity());
                builder.a((int) R.string.delete_title);
                builder.a((int) R.string.delete, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (LocalAlarmPlayerForV3UpgradeActivity.this.mAlarItem != null) {
                            ArrayList<AlarmItemV2> arrayList = new ArrayList<>();
                            arrayList.add(LocalAlarmPlayerForV3UpgradeActivity.this.mAlarItem);
                            LocalAlarmPlayerForV3UpgradeActivity.this.activity().setResult(-1);
                            try {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("did", LocalAlarmPlayerForV3UpgradeActivity.this.mCameraDevice.getDid());
                                JSONArray jSONArray = new JSONArray();
                                for (AlarmItemV2 alarmItemV2 : arrayList) {
                                    jSONArray.put("" + alarmItemV2.g);
                                }
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("fileIds", jSONArray);
                                jSONObject.put("fileIds", jSONObject2);
                                LogUtil.a(LocalAlarmPlayerForV3UpgradeActivity.TAG, "jsonObject.toString()=" + jSONObject.toString());
                                AlarmNetUtils.a().m(LocalAlarmPlayerForV3UpgradeActivity.this.mCameraDevice.getModel(), jSONObject.toString(), new Callback<JSONObject>() {
                                    public void onSuccess(JSONObject jSONObject) {
                                        if (!LocalAlarmPlayerForV3UpgradeActivity.this.isFinishing()) {
                                            if (jSONObject == null) {
                                                ToastUtil.a((Context) LocalAlarmPlayerForV3UpgradeActivity.this.activity(), (int) R.string.delete_failed);
                                            } else if (jSONObject.optInt("code") == 0) {
                                                ToastUtil.a((Context) LocalAlarmPlayerForV3UpgradeActivity.this.activity(), (int) R.string.delete_sucess);
                                            } else {
                                                ToastUtil.a((Context) LocalAlarmPlayerForV3UpgradeActivity.this.activity(), (int) R.string.delete_failed);
                                            }
                                            LocalAlarmPlayerForV3UpgradeActivity.this.finish();
                                        }
                                    }

                                    public void onFailure(int i, String str) {
                                        if (!LocalAlarmPlayerForV3UpgradeActivity.this.isFinishing()) {
                                            ToastUtil.a((Context) LocalAlarmPlayerForV3UpgradeActivity.this.activity(), (int) R.string.delete_failed);
                                            LocalAlarmPlayerForV3UpgradeActivity.this.finish();
                                        }
                                    }
                                });
                            } catch (JSONException unused) {
                                ToastUtil.a((Context) LocalAlarmPlayerForV3UpgradeActivity.this.activity(), (int) R.string.delete_failed);
                                LocalAlarmPlayerForV3UpgradeActivity.this.finish();
                            }
                        }
                    }
                });
                builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
                builder.d();
                return;
            case R.id.local_save:
                startDownloadVideo(false);
                return;
            case R.id.local_share:
                onShareClicked();
                return;
            case R.id.title_bar_return:
                finish();
                return;
            case R.id.toast_icon:
                hideFail();
                if (this.mAlarItem != null) {
                    playVideo();
                    return;
                }
                return;
            case R.id.video_mute:
                if (this.isMute) {
                    this.videoMute.setImageResource(R.drawable.camera_alarm_icon_unmute);
                    this.isMute = false;
                    this.mjExoPlayer.setVolume(1.0f);
                    return;
                }
                this.videoMute.setImageResource(R.drawable.camera_alarm_icon_mute);
                this.isMute = true;
                this.mjExoPlayer.setVolume(0.0f);
                return;
            default:
                return;
        }
    }

    public void onShareClicked() {
        SDKLog.b(TAG, "分享了 videoFilePath=" + this.videoFilePath);
        if (TextUtils.isEmpty(this.videoFilePath)) {
            startDownloadVideo(true);
        } else if (!new File(this.videoFilePath).exists()) {
            startDownloadVideo(true);
        } else {
            toShare(this.videoFilePath);
        }
    }

    private void saveMediaFileWithoutHint() {
        if (!TextUtils.isEmpty(this.currentPlayFilePath)) {
            final String a2 = FileUtil.a(true, this.mCameraDevice.getDid());
            if (!TextUtils.isEmpty(a2)) {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            LocalAlarmPlayerForV3UpgradeActivity.this.mHandler.sendEmptyMessage(16);
                            FileInputStream fileInputStream = new FileInputStream(LocalAlarmPlayerForV3UpgradeActivity.this.currentPlayFilePath);
                            FileOutputStream fileOutputStream = new FileOutputStream(a2);
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = fileInputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                            fileInputStream.close();
                            LocalAlarmPlayerForV3UpgradeActivity.this.toShare(a2);
                        } catch (IOException unused) {
                        } catch (Throwable th) {
                            LocalAlarmPlayerForV3UpgradeActivity.this.mHandler.sendEmptyMessage(17);
                            throw th;
                        }
                        LocalAlarmPlayerForV3UpgradeActivity.this.mHandler.sendEmptyMessage(17);
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
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (XmPluginHostApi.instance().getApiLevel() >= 29) {
            openShareVideoActivity(this, "", "", str, 17);
        } else {
            openSharePictureActivity("", "", str);
        }
    }

    private void saveMediaFile() {
        if (!TextUtils.isEmpty(this.currentPlayFilePath)) {
            final String a2 = FileUtil.a(true, this.mCameraDevice.getDid());
            if (!TextUtils.isEmpty(a2)) {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            LocalAlarmPlayerForV3UpgradeActivity.this.mHandler.sendEmptyMessage(16);
                            FileInputStream fileInputStream = new FileInputStream(LocalAlarmPlayerForV3UpgradeActivity.this.currentPlayFilePath);
                            FileOutputStream fileOutputStream = new FileOutputStream(a2);
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = fileInputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                            fileInputStream.close();
                            LocalAlarmPlayerForV3UpgradeActivity.this.mHandler.sendEmptyMessage(14);
                        } catch (IOException unused) {
                        } catch (Throwable th) {
                            LocalAlarmPlayerForV3UpgradeActivity.this.mHandler.sendEmptyMessage(17);
                            throw th;
                        }
                        LocalAlarmPlayerForV3UpgradeActivity.this.mHandler.sendEmptyMessage(17);
                    }
                }).start();
                return;
            }
            return;
        }
        this.mHandler.sendEmptyMessage(15);
    }

    private void startDownloadVideo(final boolean z) {
        this.videoFilePath = FileUtil.a(true, this.mCameraDevice.getDid());
        if (TextUtils.isEmpty(this.videoFilePath)) {
            this.videoFilePath = FileUtil.a(true, this.mCameraDevice.getDid());
        }
        LogUtil.a(TAG, "startDownloadVideo videoFilePath=" + this.videoFilePath);
        if (!Util.b(activity(), "android.permission.WRITE_EXTERNAL_STORAGE")) {
            ToastUtil.a((Context) activity(), (CharSequence) activity().getString(R.string.check_storage_permission));
        } else if (!this.mHasStart) {
            this.mHandler.sendEmptyMessage(16);
            this.mHasStart = true;
            this.mCameraDevice.j().a(this.mDeviceStat.model, this.mDeviceStat.did, this.mAlarItem.g, this.videoFilePath, new AlarmNetUtils.MiDownloadNewListener() {
                public void onProgress(int i) {
                }

                public void onStart() {
                    LogUtil.a(LocalAlarmPlayerForV3UpgradeActivity.TAG, "startDownloadVideo onStart");
                }

                public void onComplete() {
                    LogUtil.a(LocalAlarmPlayerForV3UpgradeActivity.TAG, "startDownloadVideo onComplete");
                    boolean unused = LocalAlarmPlayerForV3UpgradeActivity.this.mHasStart = false;
                    LocalAlarmPlayerForV3UpgradeActivity.this.mHandler.post(new Runnable() {
                        public void run() {
                            LocalAlarmPlayerForV3UpgradeActivity.this.hideProgress();
                            if (z) {
                                LocalAlarmPlayerForV3UpgradeActivity.this.toShare(LocalAlarmPlayerForV3UpgradeActivity.this.videoFilePath);
                                return;
                            }
                            LocalAlarmPlayerForV3UpgradeActivity.this.mHandler.sendEmptyMessage(14);
                            LocalAlarmPlayerForV3UpgradeActivity.this.mHandler.sendEmptyMessage(17);
                        }
                    });
                }

                public void onError(final int i, String str) {
                    LogUtil.a(LocalAlarmPlayerForV3UpgradeActivity.TAG, "startDownloadVideo onError");
                    boolean unused = LocalAlarmPlayerForV3UpgradeActivity.this.mHasStart = false;
                    File file = new File(LocalAlarmPlayerForV3UpgradeActivity.this.videoFilePath);
                    if (file.exists()) {
                        file.delete();
                    }
                    LocalAlarmPlayerForV3UpgradeActivity.this.mHandler.post(new Runnable() {
                        public void run() {
                            LocalAlarmPlayerForV3UpgradeActivity.this.hideProgress();
                            boolean z = z;
                            if (i == 1002) {
                                ToastUtil.a((Context) LocalAlarmPlayerForV3UpgradeActivity.this.activity(), (CharSequence) LocalAlarmPlayerForV3UpgradeActivity.this.getString(R.string.net_wrong), 0);
                            } else if (i == 103) {
                                if (z) {
                                    ToastUtil.a((Context) LocalAlarmPlayerForV3UpgradeActivity.this.activity(), (int) R.string.share_fail, 0);
                                } else {
                                    ToastUtil.a((Context) LocalAlarmPlayerForV3UpgradeActivity.this.activity(), (int) R.string.download_fail, 0);
                                }
                            } else if (i == -4001) {
                                ToastUtil.a((Context) LocalAlarmPlayerForV3UpgradeActivity.this.activity(), (int) R.string.check_storage_permission, 0);
                            } else {
                                Activity activity = LocalAlarmPlayerForV3UpgradeActivity.this.activity();
                                ToastUtil.a((Context) activity, (CharSequence) LocalAlarmPlayerForV3UpgradeActivity.this.getString(R.string.download_err) + i, 0);
                            }
                        }
                    });
                    LocalAlarmPlayerForV3UpgradeActivity.this.mHandler.sendEmptyMessage(17);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void setOrientation(boolean z) {
        if (z) {
            setRequestedOrientation(0);
        } else {
            setRequestedOrientation(1);
        }
    }

    private void getAlarmFile(AlarmItemV2 alarmItemV2) {
        showProgress();
        this.mCameraDevice.j().a((Context) this, alarmItemV2, (AlarmManagerV2.IAlarmCallback<String>) new AlarmManagerV2.IAlarmCallback<String>() {
            public void onSuccess(String str, Object obj) {
                if (!LocalAlarmPlayerForV3UpgradeActivity.this.isFinishing()) {
                    LocalAlarmPlayerForV3UpgradeActivity.this.hideProgress();
                    SDKLog.b(LocalAlarmPlayerForV3UpgradeActivity.TAG, "result mCurrentPath:" + str);
                    if (!TextUtils.isEmpty(str)) {
                        String unused = LocalAlarmPlayerForV3UpgradeActivity.this.currentPlayFilePath = str;
                        if (!TextUtils.isEmpty(LocalAlarmPlayerForV3UpgradeActivity.this.currentPlayFilePath)) {
                            long access$1600 = LocalAlarmPlayerForV3UpgradeActivity.this.extractVideoDuration(LocalAlarmPlayerForV3UpgradeActivity.this.currentPlayFilePath);
                            LocalAlarmPlayerForV3UpgradeActivity.this.mProgressBar.setMax((int) (access$1600 / 1000));
                            TextView access$1700 = LocalAlarmPlayerForV3UpgradeActivity.this.mDurationView;
                            access$1700.setText("" + LocalAlarmPlayerForV3UpgradeActivity.this.mTimeFormat.format(Long.valueOf(access$1600)));
                        }
                    }
                }
            }

            public void onFailure(int i, String str) {
                if (!LocalAlarmPlayerForV3UpgradeActivity.this.isFinishing()) {
                    LocalAlarmPlayerForV3UpgradeActivity.this.hideProgress();
                    LocalAlarmPlayerForV3UpgradeActivity localAlarmPlayerForV3UpgradeActivity = LocalAlarmPlayerForV3UpgradeActivity.this;
                    localAlarmPlayerForV3UpgradeActivity.showFail("" + i);
                    String unused = LocalAlarmPlayerForV3UpgradeActivity.this.currentPlayFilePath = null;
                    SDKLog.b(LocalAlarmPlayerForV3UpgradeActivity.TAG, "errorCode:" + i + " errorMessage:" + str);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void playVideo() {
        LogUtil.a(TAG, "走了playVideo");
        this.mActivePause = false;
        this.curTime = 0;
        if (this.isMute) {
            this.mjExoPlayer.setVolume(0.0f);
        } else {
            this.mjExoPlayer.setVolume(1.0f);
        }
        LogUtil.a(TAG, "播放前的视屏地址mUrl=" + this.mUrl);
        if (TextUtils.isEmpty(this.mUrl)) {
            this.mUrl = this.mCameraDevice.j().a(this.mDeviceStat.model, this.mDeviceStat.did, this.mAlarItem.g);
            LogUtil.a(TAG, "m3u8 URL=" + this.mUrl);
            this.mjExoPlayer.startPlay(this.mUrl);
        } else if (this.mjExoPlayer.getPlaybackState() == 3) {
            this.mjExoPlayer.resumePlay();
        } else if (this.mjExoPlayer.getPlaybackState() == 4) {
            this.mjExoPlayer.startPlay(this.mUrl);
            this.mDurationView.setText(convertMMSS(0));
            this.mProgressBar.setProgress(0);
        }
        this.mIsPlaying = true;
        sendMsgUpdateProgressOnce();
    }

    /* access modifiers changed from: private */
    public long extractVideoDuration(String str) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(str);
            String extractMetadata = mediaMetadataRetriever.extractMetadata(9);
            if (!TextUtils.isEmpty(extractMetadata)) {
                long longValue = Long.valueOf(extractMetadata).longValue();
                mediaMetadataRetriever.release();
                return longValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            mediaMetadataRetriever.release();
            throw th;
        }
        mediaMetadataRetriever.release();
        return 0;
    }

    /* access modifiers changed from: private */
    public static String convertMMSS(int i) {
        String str;
        int i2 = i % 3600;
        int i3 = i2 / 60;
        int i4 = i2 % 60;
        if (i3 < 10) {
            str = "" + "0" + i3;
        } else {
            str = "" + i3;
        }
        if (i4 < 10) {
            return str + ":0" + i4;
        }
        return str + ":" + i4;
    }
}
