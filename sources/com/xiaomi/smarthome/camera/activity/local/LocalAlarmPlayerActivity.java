package com.xiaomi.smarthome.camera.activity.local;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
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
import com.alipay.mobile.common.logging.api.ProcessInfo;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.mijia.model.alarm.AlarmItem;
import com.mijia.model.alarm.AlarmManager;
import com.mijia.model.alarm.FDSFileManager;
import com.mijia.model.local.LocalFileManager;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.XmMp4Player;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.activity.CameraPlayer2ExActivity;
import com.xiaomi.smarthome.camera.v4.activity.CameraPlayerActivity;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.fastvideo.VideoFrameDecoder;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class LocalAlarmPlayerActivity extends CameraBaseActivity implements View.OnClickListener {
    public static final String KEY_IS_V4 = "is_v4";
    static final int MSG_SAVE_FAILURE = 15;
    static final int MSG_SAVE_START = 16;
    static final int MSG_SAVE_STOP = 17;
    static final int MSG_SAVE_SUCCESS = 14;
    static final int MSG_UPDATE_PROGRESS = 1;
    static final int MSG_UPDATE_SEEK_PROGRESS = 2;
    static final int MSG_WATI_TIME = 3;
    CheckBox cbTogglePlay;
    /* access modifiers changed from: private */
    public boolean isInit = false;
    private boolean isMute = false;
    /* access modifiers changed from: private */
    public boolean isNetShowing = false;
    ImageView ivFullScreen;
    AlarmItem mAlarItem;
    AlarmManager mAlarmFileManager;
    private long mAlarmTime = 0;
    View mBottomViewContainer;
    /* access modifiers changed from: private */
    public boolean mCanRetry = false;
    /* access modifiers changed from: private */
    public TextView mDurationView;
    ImageView mFullScreenView;
    AnimationDrawable mLoadingAnimation;
    private LocalAudioPlay mLocalAudioPlay = null;
    LocalFileManager.LocalFile mLocalFile;
    private boolean mNeedCheck = false;
    protected boolean mNeedPincode = false;
    FDSFileManager.OnDownloadListener mOnDownloadListener = new FDSFileManager.OnDownloadListener() {
        public void onDownloading(FDSFileManager.DownloadItem downloadItem, String str, final int i) {
            if (LocalAlarmPlayerActivity.this.mAlarItem != null && downloadItem.f8040a.b == LocalAlarmPlayerActivity.this.mAlarItem.b) {
                LocalAlarmPlayerActivity.this.runMainThread(new Runnable() {
                    public void run() {
                        LocalAlarmPlayerActivity.this.mToastText.setText(LocalAlarmPlayerActivity.this.getString(R.string.loading) + i + Operators.MOD);
                    }
                });
            }
        }

        public void onDownloadSuccess(FDSFileManager.DownloadItem downloadItem, final String str) {
            if (LocalAlarmPlayerActivity.this.mAlarItem != null && downloadItem.f8040a.b == LocalAlarmPlayerActivity.this.mAlarItem.b) {
                LocalAlarmPlayerActivity.this.runMainThread(new Runnable() {
                    public void run() {
                        LocalAlarmPlayerActivity.this.hideProgress();
                        LocalAlarmPlayerActivity.this.mLocalFile = LocalAlarmPlayerActivity.this.mCameraDevice.b().b(str);
                        TextView textView = (TextView) LocalAlarmPlayerActivity.this.findViewById(R.id.title_bar_title);
                        if (LocalAlarmPlayerActivity.this.mLocalFile != null) {
                            textView.setText(LocalAlarmPlayerActivity.this.mLocalFile.c.getName());
                            LocalAlarmPlayerActivity.this.mDurationView.setText(LocalAlarmPlayerActivity.this.mTimeFormat.format(new Date(LocalAlarmPlayerActivity.this.mLocalFile.b)));
                            LocalAlarmPlayerActivity.this.mProgressBar.setMax((int) (LocalAlarmPlayerActivity.this.mLocalFile.b / 1000));
                            if (LocalAlarmPlayerActivity.this.isInit) {
                                LocalAlarmPlayerActivity.this.startPlay();
                            }
                        }
                    }
                });
            }
        }

        public void onDownloadFailed(FDSFileManager.DownloadItem downloadItem, String str, int i, final String str2) {
            if (LocalAlarmPlayerActivity.this.mAlarItem != null && downloadItem.f8040a.b == LocalAlarmPlayerActivity.this.mAlarItem.b) {
                LocalAlarmPlayerActivity.this.runMainThread(new Runnable() {
                    public void run() {
                        if (LocalAlarmPlayerActivity.this.mCanRetry) {
                            LocalAlarmPlayerActivity.this.mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    LocalAlarmPlayerActivity.this.doDown();
                                }
                            }, 2000);
                            return;
                        }
                        LocalAlarmPlayerActivity.this.mLocalFile = null;
                        LocalAlarmPlayerActivity.this.showFail(str2);
                    }
                });
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean mPlayAutoPause = false;
    /* access modifiers changed from: private */
    public TextView mPlayingView;
    SeekBar mProgressBar;
    View mProgressBarContainer;
    private int mRotation = 0;
    private SeekBar.OnSeekBarChangeListener mSeekBarChange = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (LocalAlarmPlayerActivity.this.mSeekBarTouched) {
                LocalAlarmPlayerActivity.this.mHandler.removeMessages(2);
                LocalAlarmPlayerActivity.this.mPlayingView.setText(LocalAlarmPlayerActivity.this.mTimeFormat.format(Integer.valueOf(i * 1000)));
                Message obtainMessage = LocalAlarmPlayerActivity.this.mHandler.obtainMessage();
                obtainMessage.what = 2;
                obtainMessage.arg1 = i;
                obtainMessage.sendToTarget();
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            LocalAlarmPlayerActivity.this.mSeekBarTouched = true;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            LocalAlarmPlayerActivity.this.mSeekBarTouched = false;
        }
    };
    boolean mSeekBarTouched = false;
    SimpleDateFormat mTimeFormat;
    LinearLayout mToastCenter;
    ImageView mToastImg;
    TextView mToastText;
    View mTopViewContainer;
    XmMp4Player mVideoPlayerRender;
    XmVideoViewGl mVideoView;
    FrameLayout mVideoViewFrame;
    private FrameLayout title_bar;
    private ImageView videoMute;

    public void handleMessage(Message message) {
        super.handleMessage(message);
        int i = message.what;
        switch (i) {
            case 1:
                this.mHandler.removeMessages(1);
                if (!this.mSeekBarTouched) {
                    int currentPosition = this.mVideoPlayerRender.getCurrentPosition();
                    this.mPlayingView.setText(this.mTimeFormat.format(Integer.valueOf(currentPosition)));
                    this.mProgressBar.setProgress(currentPosition / 1000);
                }
                this.mHandler.sendEmptyMessageDelayed(1, 1000);
                return;
            case 2:
                this.mVideoPlayerRender.seekTo(this.mProgressBar.getProgress() * 1000);
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
    }

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.camera_activity_alarm_video_play);
        this.mNeedCheck = getIntent().getBooleanExtra("check", false);
        SDKLog.e("alarm", "need check " + this.mNeedCheck);
        if (TextUtils.isEmpty(XmPluginHostApi.instance().getDevicePincode(this.mDid)) || this.mNeedCheck) {
            enableVerifyPincode();
            SDKLog.e("alarm", "enableVerifyPincode " + this.mDeviceStat.isSetPinCode);
            if (this.mCameraDevice.m()) {
                this.mNeedPincode = true;
                SDKLog.e("alarm", "need pincode ");
            }
        }
        if (this.mAlarmTime > 0) {
            this.mAlarItem = this.mAlarmFileManager.a(this.mAlarmTime);
        }
        AudioManager audioManager = (AudioManager) activity().getApplicationContext().getSystemService("audio");
        if (audioManager != null) {
            audioManager.requestAudioFocus((AudioManager.OnAudioFocusChangeListener) null, 3, 1);
        }
        this.mAlarmFileManager = this.mCameraDevice.i();
        this.mAlarmTime = getIntent().getLongExtra("time", 0) * 1000;
        boolean booleanExtra = getIntent().getBooleanExtra(ProcessInfo.ALIAS_PUSH, false);
        this.mTimeFormat = new SimpleDateFormat("mm:ss");
        this.mTimeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        initView();
        showNetAlarm();
        if (booleanExtra) {
            this.mCanRetry = true;
            this.mHandler.sendEmptyMessageDelayed(3, 18000);
        }
    }

    private void initView() {
        this.videoMute = (ImageView) findViewById(R.id.video_mute);
        this.videoMute.setOnClickListener(this);
        this.mToastCenter = (LinearLayout) findViewById(R.id.toast_container_center);
        this.mToastCenter.setOnClickListener(this);
        this.mToastText = (TextView) findViewById(R.id.toast_title);
        this.mToastImg = (ImageView) findViewById(R.id.toast_icon);
        if (this.isV4) {
            this.mToastText.setVisibility(0);
            this.mLoadingAnimation = (AnimationDrawable) getResources().getDrawable(R.drawable.anim_alarm_loading_v4);
        } else {
            this.mToastText.setVisibility(8);
            this.mLoadingAnimation = (AnimationDrawable) getResources().getDrawable(R.drawable.camera_anim_loading);
        }
        this.mToastImg.setImageDrawable(this.mLoadingAnimation);
        this.title_bar = (FrameLayout) findViewById(R.id.title_bar);
        TextView textView = (TextView) findViewById(R.id.title_bar_title);
        this.title_bar.bringToFront();
        if (this.mLocalFile != null) {
            textView.setText(this.mLocalFile.c.getName());
        }
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.mBottomViewContainer = findViewById(R.id.bottom_tools_container);
        this.mTopViewContainer = findViewById(R.id.top_tools_container);
        this.mProgressBarContainer = findViewById(R.id.progress_bar_container);
        this.mProgressBar = (SeekBar) findViewById(R.id.progress_bar);
        this.mDurationView = (TextView) findViewById(R.id.progress_duration);
        this.mPlayingView = (TextView) findViewById(R.id.progress_playtime);
        if (this.mLocalFile != null) {
            this.mProgressBar.setMax((int) (this.mLocalFile.b / 1000));
            this.mDurationView.setText(this.mTimeFormat.format(new Date(this.mLocalFile.b)));
        }
        this.mProgressBar.setOnSeekBarChangeListener(this.mSeekBarChange);
        this.mVideoViewFrame = (FrameLayout) findViewById(R.id.video_frame);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        FrameLayout frameLayout = new FrameLayout(activity());
        this.mVideoViewFrame.addView(frameLayout, 0, layoutParams);
        boolean contains = Util.c.contains(Build.MODEL);
        this.mVideoView = XmPluginHostApi.instance().createMp4View(activity(), frameLayout, !contains);
        this.mVideoView.setMiniScale(true);
        this.mVideoPlayerRender = this.mVideoView.getMp4Player();
        if (contains) {
            this.mLocalAudioPlay = new LocalAudioPlay(activity());
            this.mVideoPlayerRender.setAudioListener(this.mLocalAudioPlay);
        }
        this.mVideoPlayerRender.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                LocalAlarmPlayerActivity.this.runMainThread(new Runnable() {
                    public void run() {
                        LocalAlarmPlayerActivity.this.mHandler.removeMessages(1);
                        LocalAlarmPlayerActivity.this.mProgressBar.setProgress(LocalAlarmPlayerActivity.this.mProgressBar.getMax());
                        LocalAlarmPlayerActivity.this.mPlayingView.setText(LocalAlarmPlayerActivity.this.mDurationView.getText());
                        LocalAlarmPlayerActivity.this.cbTogglePlay.setChecked(true);
                        if (LocalAlarmPlayerActivity.this.mProgressBarContainer.getVisibility() == 8) {
                            AnimationUtils.loadAnimation(LocalAlarmPlayerActivity.this.activity(), R.anim.slide_in_top).setFillAfter(true);
                            Animation loadAnimation = AnimationUtils.loadAnimation(LocalAlarmPlayerActivity.this.activity(), R.anim.slide_in_bottom);
                            loadAnimation.setFillAfter(true);
                            LocalAlarmPlayerActivity.this.mProgressBarContainer.setClickable(true);
                            LocalAlarmPlayerActivity.this.mProgressBarContainer.setVisibility(0);
                            LocalAlarmPlayerActivity.this.mProgressBarContainer.startAnimation(loadAnimation);
                        }
                    }
                });
            }
        });
        this.mVideoPlayerRender.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                LocalAlarmPlayerActivity.this.runMainThread(new Runnable() {
                    public void run() {
                        LocalAlarmPlayerActivity.this.mProgressBar.setProgress(LocalAlarmPlayerActivity.this.mVideoPlayerRender.getCurrentPosition() / 1000);
                        LocalAlarmPlayerActivity.this.mPlayingView.setText(LocalAlarmPlayerActivity.this.mTimeFormat.format(Integer.valueOf(LocalAlarmPlayerActivity.this.mVideoPlayerRender.getCurrentPosition())));
                        LocalAlarmPlayerActivity.this.cbTogglePlay.setChecked(true);
                    }
                });
                return true;
            }
        });
        this.mVideoView.setVideoViewListener(new XmVideoViewGl.IVideoViewListener() {
            public void onVideoViewClick() {
                if (LocalAlarmPlayerActivity.this.mProgressBarContainer.isShown()) {
                    AnimationUtils.loadAnimation(LocalAlarmPlayerActivity.this.activity(), R.anim.slide_out_top).setFillAfter(true);
                    Animation loadAnimation = AnimationUtils.loadAnimation(LocalAlarmPlayerActivity.this.activity(), R.anim.slide_out_bottom);
                    loadAnimation.setFillAfter(true);
                    LocalAlarmPlayerActivity.this.mProgressBarContainer.setClickable(false);
                    LocalAlarmPlayerActivity.this.mProgressBarContainer.setVisibility(8);
                    LocalAlarmPlayerActivity.this.mProgressBarContainer.startAnimation(loadAnimation);
                    return;
                }
                AnimationUtils.loadAnimation(LocalAlarmPlayerActivity.this.activity(), R.anim.slide_in_top).setFillAfter(true);
                Animation loadAnimation2 = AnimationUtils.loadAnimation(LocalAlarmPlayerActivity.this.activity(), R.anim.slide_in_bottom);
                loadAnimation2.setFillAfter(true);
                LocalAlarmPlayerActivity.this.mProgressBarContainer.setClickable(true);
                LocalAlarmPlayerActivity.this.mProgressBarContainer.setVisibility(0);
                LocalAlarmPlayerActivity.this.mProgressBarContainer.startAnimation(loadAnimation2);
            }
        });
        this.mFullScreenView = (ImageView) findViewById(R.id.full_screen);
        this.cbTogglePlay = (CheckBox) findViewById(R.id.cbTogglePlay);
        this.cbTogglePlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    LocalAlarmPlayerActivity.this.pausePlay();
                    LocalAlarmPlayerActivity.this.cbTogglePlay.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, LocalAlarmPlayerActivity.this.getResources().getDrawable(R.drawable.camera_icon_play02), (Drawable) null);
                    return;
                }
                LocalAlarmPlayerActivity.this.startPlay();
                LocalAlarmPlayerActivity.this.cbTogglePlay.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, LocalAlarmPlayerActivity.this.getResources().getDrawable(R.drawable.camera_icon_pause02), (Drawable) null);
            }
        });
        this.ivFullScreen = (ImageView) findViewById(R.id.ivFullScreen);
        this.ivFullScreen.setOnClickListener(this);
        this.mFullScreenView.setOnClickListener(this);
        findViewById(R.id.flip).setOnClickListener(this);
        findViewById(R.id.local_share).setOnClickListener(this);
        findViewById(R.id.local_delete).setOnClickListener(this);
        findViewById(R.id.local_save).setOnClickListener(this);
        this.mVideoView.initial();
        this.mVideoPlayerRender.setRenderCallback(new XmMp4Player.RenderCallback() {
            public void onInitialed() {
                LocalAlarmPlayerActivity.this.runMainThread(new Runnable() {
                    public void run() {
                        boolean unused = LocalAlarmPlayerActivity.this.isInit = true;
                        if (LocalAlarmPlayerActivity.this.mLocalFile != null) {
                            LocalAlarmPlayerActivity.this.startPlay();
                        }
                    }
                });
            }
        });
        this.mCameraDevice.k().a(this.mOnDownloadListener);
    }

    public void onResume() {
        super.onResume();
        String devicePincode = XmPluginHostApi.instance().getDevicePincode(this.mDid);
        SDKLog.e("alarm", "onResume " + devicePincode);
        if (TextUtils.isEmpty(this.mCameraDevice.r())) {
            SDKLog.e("alarm", "isEmpty ");
            if (!this.mNeedPincode || !TextUtils.isEmpty(devicePincode)) {
                this.mCameraDevice.f(new Callback<Void>() {
                    public void onSuccess(Void voidR) {
                        if (!LocalAlarmPlayerActivity.this.isFinishing() && LocalAlarmPlayerActivity.this.mLocalFile == null) {
                            LocalAlarmPlayerActivity.this.showNetAlarm();
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!LocalAlarmPlayerActivity.this.isFinishing()) {
                            ToastUtil.a((Context) LocalAlarmPlayerActivity.this.activity(), (int) R.string.camera_play_error2);
                        }
                    }
                });
                return;
            }
            return;
        }
        if (!TextUtils.isEmpty(devicePincode) && this.mLocalFile == null && this.mNeedCheck) {
            showNetAlarm();
        }
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (LocalAlarmPlayerActivity.this.mPlayAutoPause) {
                    LocalAlarmPlayerActivity.this.startPlay();
                }
            }
        }, 500);
    }

    public void onPause() {
        super.onPause();
        if (this.mVideoPlayerRender != null && this.mVideoPlayerRender.isPlaying()) {
            pausePlay();
            this.mPlayAutoPause = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void startPlay() {
        if (!(this.mLocalFile == null || this.mLocalFile.c == null)) {
            if (this.isMute) {
                this.mVideoPlayerRender.setVolume(0);
            } else {
                this.mVideoPlayerRender.setVolume(1);
            }
            this.mVideoPlayerRender.openVideoPlayer(this.mLocalFile.c.getAbsolutePath());
            this.mPlayAutoPause = false;
        }
        this.mHandler.sendEmptyMessage(1);
    }

    /* access modifiers changed from: private */
    public void pausePlay() {
        this.mVideoPlayerRender.pause();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mCameraDevice != null) {
            this.mCameraDevice.k().b(this.mOnDownloadListener);
        }
        this.mHandler.removeMessages(1);
        if (this.mLoadingAnimation != null && this.mLoadingAnimation.isRunning()) {
            this.mLoadingAnimation.stop();
        }
        this.mOnDownloadListener = null;
        if (this.mVideoView != null) {
            this.mVideoView.release();
        }
        if (this.mLocalAudioPlay != null) {
            this.mLocalAudioPlay.release();
        }
        if (this.mVideoPlayerRender != null && (this.mVideoPlayerRender instanceof VideoFrameDecoder)) {
            ((VideoFrameDecoder) this.mVideoPlayerRender).f();
        }
    }

    public void onBackPressed() {
        if (1 != getRequestedOrientation()) {
            setOrientation(false);
            return;
        }
        if (count == 1) {
            Intent intent = new Intent();
            if (this.isV4) {
                intent.setClass(this, CameraPlayerActivity.class);
            } else {
                intent.setClass(this, CameraPlayer2ExActivity.class);
            }
            if (!TextUtils.isEmpty(XmPluginHostApi.instance().getDevicePincode(this.mDeviceStat.did))) {
                intent.putExtra("pincod", true);
            }
            startActivity(intent);
        }
        super.onBackPressed();
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
            this.ivFullScreen.setImageResource(R.drawable.camera_alarm_icon_mixscreen);
            this.mVideoView.setVideoFrameSize(-1, -1, true);
            return;
        }
        getWindow().clearFlags(1024);
        this.title_bar.setVisibility(0);
        this.title_bar.bringToFront();
        this.mBottomViewContainer.setVisibility(0);
        this.ivFullScreen.setImageResource(R.drawable.camera_icon_fullscreen2);
        this.mVideoView.setVideoFrameSize(-1, -1, false);
    }

    /* access modifiers changed from: private */
    public void showNetAlarm() {
        if (!TextUtils.isEmpty(this.mCameraDevice.r())) {
            if (!NetworkUtil.c(activity())) {
                doDown();
            } else if (!this.isNetShowing) {
                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity());
                builder.b((int) R.string.push_net_alarm);
                builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        LocalAlarmPlayerActivity.this.finish();
                    }
                });
                builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        LocalAlarmPlayerActivity.this.doDown();
                    }
                });
                builder.a((MLAlertDialog.DismissCallBack) new MLAlertDialog.DismissCallBack() {
                    public void beforeDismissCallBack() {
                    }

                    public void afterDismissCallBack() {
                        boolean unused = LocalAlarmPlayerActivity.this.isNetShowing = false;
                    }
                });
                builder.d();
                this.isNetShowing = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public void doDown() {
        showProgress();
        if (this.mAlarItem != null) {
            this.mCameraDevice.k().c(this.mAlarItem);
        } else {
            this.mAlarmFileManager.a(this.mAlarmTime, (Callback<AlarmItem>) new Callback<AlarmItem>() {
                public void onSuccess(AlarmItem alarmItem) {
                    LocalAlarmPlayerActivity.this.mAlarItem = alarmItem;
                    if (LocalAlarmPlayerActivity.this.mAlarItem != null) {
                        LocalAlarmPlayerActivity.this.mCameraDevice.k().c(LocalAlarmPlayerActivity.this.mAlarItem);
                    } else {
                        LocalAlarmPlayerActivity.this.showFail("no file");
                    }
                }

                public void onFailure(int i, String str) {
                    LocalAlarmPlayerActivity.this.showFail(str);
                }
            });
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
        this.mToastText.setText(R.string.load_refresh);
        this.mToastCenter.setTag(1);
        Toast.makeText(activity(), R.string.sdcard_video_download_failed, 0).show();
        SDKLog.e(Tag.b, "Error alarm " + str);
    }

    private void showProgress() {
        if (this.mToastCenter.getVisibility() == 8) {
            this.mToastCenter.setVisibility(0);
        }
        this.mToastImg.setImageDrawable(this.mLoadingAnimation);
        this.mLoadingAnimation.start();
        this.mToastCenter.setTag((Object) null);
        this.mToastText.setText(getString(R.string.loading) + 0 + Operators.MOD);
    }

    /* access modifiers changed from: private */
    public void hideProgress() {
        if (this.mLoadingAnimation.isRunning()) {
            this.mLoadingAnimation.stop();
        }
        if (this.mToastCenter.getVisibility() == 0) {
            this.mToastCenter.setVisibility(8);
            this.mToastCenter.setTag((Object) null);
            this.mToastText.setText("");
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
                        if (LocalAlarmPlayerActivity.this.mAlarItem != null) {
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(LocalAlarmPlayerActivity.this.mAlarItem);
                            LocalAlarmPlayerActivity.this.activity().setResult(-1);
                            LocalAlarmPlayerActivity.this.mAlarmFileManager.a((List<AlarmItem>) arrayList, (Callback<Void>) new Callback<Void>() {
                                public void onSuccess(Void voidR) {
                                    if (!LocalAlarmPlayerActivity.this.isFinishing()) {
                                        if (LocalAlarmPlayerActivity.this.mLocalFile != null) {
                                            LocalAlarmPlayerActivity.this.mLocalFile.c.delete();
                                        }
                                        LocalAlarmPlayerActivity.this.runMainThread(new Runnable() {
                                            public void run() {
                                                Toast.makeText(LocalAlarmPlayerActivity.this.activity(), R.string.local_file_delete_success, 0).show();
                                                LocalAlarmPlayerActivity.this.finish();
                                            }
                                        });
                                    }
                                }

                                public void onFailure(int i, String str) {
                                    if (!LocalAlarmPlayerActivity.this.isFinishing()) {
                                        LocalAlarmPlayerActivity.this.runMainThread(new Runnable() {
                                            public void run() {
                                                Toast.makeText(LocalAlarmPlayerActivity.this.activity(), R.string.local_file_delete_failed, 0).show();
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    }
                });
                builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
                builder.d();
                return;
            case R.id.local_save:
                saveMediaFile();
                return;
            case R.id.local_share:
                if (this.mLocalFile != null) {
                    openShareVideoActivity(activity(), "", "", this.mLocalFile.c.getAbsolutePath(), 1);
                    return;
                }
                return;
            case R.id.title_bar_return:
                finish();
                return;
            case R.id.toast_container_center:
                doDown();
                return;
            case R.id.video_mute:
                if (this.isMute) {
                    this.videoMute.setImageResource(R.drawable.camera_alarm_icon_unmute);
                    this.isMute = false;
                    this.mVideoPlayerRender.setVolume(1);
                    return;
                }
                this.videoMute.setImageResource(R.drawable.camera_alarm_icon_mute);
                this.isMute = true;
                this.mVideoPlayerRender.setVolume(0);
                return;
            default:
                return;
        }
    }

    private void saveMediaFile() {
        if (this.mLocalFile == null || this.mLocalFile.c == null || TextUtils.isEmpty(this.mLocalFile.c.getAbsolutePath())) {
            this.mHandler.sendEmptyMessage(15);
            return;
        }
        final String a2 = FileUtil.a(true, this.mCameraDevice.getDid());
        if (!TextUtils.isEmpty(a2)) {
            new Thread(new Runnable() {
                /* JADX WARNING: Removed duplicated region for block: B:36:0x00a2 A[SYNTHETIC, Splitter:B:36:0x00a2] */
                /* JADX WARNING: Removed duplicated region for block: B:41:0x00c3 A[SYNTHETIC, Splitter:B:41:0x00c3] */
                /* JADX WARNING: Removed duplicated region for block: B:50:0x00ed A[SYNTHETIC, Splitter:B:50:0x00ed] */
                /* JADX WARNING: Removed duplicated region for block: B:55:0x010e A[SYNTHETIC, Splitter:B:55:0x010e] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r8 = this;
                        r0 = 0
                        r1 = 17
                        com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerActivity r2 = com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerActivity.this     // Catch:{ IOException -> 0x0084, all -> 0x0081 }
                        android.os.Handler r2 = r2.mHandler     // Catch:{ IOException -> 0x0084, all -> 0x0081 }
                        r3 = 16
                        r2.sendEmptyMessage(r3)     // Catch:{ IOException -> 0x0084, all -> 0x0081 }
                        java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0084, all -> 0x0081 }
                        com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerActivity r3 = com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerActivity.this     // Catch:{ IOException -> 0x0084, all -> 0x0081 }
                        com.mijia.model.local.LocalFileManager$LocalFile r3 = r3.mLocalFile     // Catch:{ IOException -> 0x0084, all -> 0x0081 }
                        java.io.File r3 = r3.c     // Catch:{ IOException -> 0x0084, all -> 0x0081 }
                        java.lang.String r3 = r3.getAbsolutePath()     // Catch:{ IOException -> 0x0084, all -> 0x0081 }
                        r2.<init>(r3)     // Catch:{ IOException -> 0x0084, all -> 0x0081 }
                        java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x007b, all -> 0x0074 }
                        java.lang.String r4 = r0     // Catch:{ IOException -> 0x007b, all -> 0x0074 }
                        r3.<init>(r4)     // Catch:{ IOException -> 0x007b, all -> 0x0074 }
                        r0 = 1024(0x400, float:1.435E-42)
                        byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x006f, all -> 0x0069 }
                    L_0x0026:
                        int r4 = r2.read(r0)     // Catch:{ IOException -> 0x006f, all -> 0x0069 }
                        r5 = -1
                        if (r4 == r5) goto L_0x0032
                        r5 = 0
                        r3.write(r0, r5, r4)     // Catch:{ IOException -> 0x006f, all -> 0x0069 }
                        goto L_0x0026
                    L_0x0032:
                        com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerActivity r0 = com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerActivity.this     // Catch:{ IOException -> 0x006f, all -> 0x0069 }
                        android.os.Handler r0 = r0.mHandler     // Catch:{ IOException -> 0x006f, all -> 0x0069 }
                        r4 = 14
                        r0.sendEmptyMessage(r4)     // Catch:{ IOException -> 0x006f, all -> 0x0069 }
                        r2.close()     // Catch:{ IOException -> 0x003f }
                        goto L_0x005a
                    L_0x003f:
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
                    L_0x005a:
                        r3.close()     // Catch:{ IOException -> 0x005f }
                        goto L_0x00e2
                    L_0x005f:
                        r0 = move-exception
                        java.lang.String r2 = "alarm"
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder
                        r3.<init>()
                        goto L_0x00cf
                    L_0x0069:
                        r0 = move-exception
                        r7 = r2
                        r2 = r0
                        r0 = r7
                        goto L_0x00eb
                    L_0x006f:
                        r0 = move-exception
                        r7 = r2
                        r2 = r0
                        r0 = r7
                        goto L_0x0086
                    L_0x0074:
                        r3 = move-exception
                        r7 = r3
                        r3 = r0
                        r0 = r2
                        r2 = r7
                        goto L_0x00eb
                    L_0x007b:
                        r3 = move-exception
                        r7 = r3
                        r3 = r0
                        r0 = r2
                        r2 = r7
                        goto L_0x0086
                    L_0x0081:
                        r2 = move-exception
                        r3 = r0
                        goto L_0x00eb
                    L_0x0084:
                        r2 = move-exception
                        r3 = r0
                    L_0x0086:
                        java.lang.String r4 = "alarm"
                        java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ea }
                        r5.<init>()     // Catch:{ all -> 0x00ea }
                        java.lang.String r6 = ""
                        r5.append(r6)     // Catch:{ all -> 0x00ea }
                        java.lang.String r2 = r2.getLocalizedMessage()     // Catch:{ all -> 0x00ea }
                        r5.append(r2)     // Catch:{ all -> 0x00ea }
                        java.lang.String r2 = r5.toString()     // Catch:{ all -> 0x00ea }
                        com.xiaomi.smarthome.framework.log.LogUtil.b(r4, r2)     // Catch:{ all -> 0x00ea }
                        if (r0 == 0) goto L_0x00c1
                        r0.close()     // Catch:{ IOException -> 0x00a6 }
                        goto L_0x00c1
                    L_0x00a6:
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
                    L_0x00c1:
                        if (r3 == 0) goto L_0x00e2
                        r3.close()     // Catch:{ IOException -> 0x00c7 }
                        goto L_0x00e2
                    L_0x00c7:
                        r0 = move-exception
                        java.lang.String r2 = "alarm"
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder
                        r3.<init>()
                    L_0x00cf:
                        java.lang.String r4 = ""
                        r3.append(r4)
                        java.lang.String r0 = r0.getLocalizedMessage()
                        r3.append(r0)
                        java.lang.String r0 = r3.toString()
                        com.xiaomi.smarthome.framework.log.LogUtil.b(r2, r0)
                    L_0x00e2:
                        com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerActivity r0 = com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerActivity.this
                        android.os.Handler r0 = r0.mHandler
                        r0.sendEmptyMessage(r1)
                        return
                    L_0x00ea:
                        r2 = move-exception
                    L_0x00eb:
                        if (r0 == 0) goto L_0x010c
                        r0.close()     // Catch:{ IOException -> 0x00f1 }
                        goto L_0x010c
                    L_0x00f1:
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
                    L_0x010c:
                        if (r3 == 0) goto L_0x012d
                        r3.close()     // Catch:{ IOException -> 0x0112 }
                        goto L_0x012d
                    L_0x0112:
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
                    L_0x012d:
                        com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerActivity r0 = com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerActivity.this
                        android.os.Handler r0 = r0.mHandler
                        r0.sendEmptyMessage(r1)
                        throw r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerActivity.AnonymousClass15.run():void");
                }
            }).start();
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
}
