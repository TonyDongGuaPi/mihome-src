package com.xiaomi.smarthome.camera.activity.local;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
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
import com.mijia.model.alarm.AlarmItemV2;
import com.mijia.model.alarm.AlarmManagerV2;
import com.mijia.model.alarm.AlarmNetUtils;
import com.mijia.model.local.LocalFileManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.XmMp4Player;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.activity.CameraPlayer2ExActivity;
import com.xiaomi.smarthome.camera.v4.activity.CameraPlayerActivity;
import com.xiaomi.smarthome.camera.view.widget.FeedbackDialog;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.fastvideo.VideoFrameDecoder;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.ThreadPool;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.cybergarage.http.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocalAlarmPlayerV2Activity extends CameraBaseActivity implements View.OnClickListener {
    public static final String KEY_IS_V4 = "is_v4";
    static final int MSG_SAVE_FAILURE = 15;
    static final int MSG_SAVE_START = 16;
    static final int MSG_SAVE_STOP = 17;
    static final int MSG_SAVE_SUCCESS = 14;
    static final int MSG_UPDATE_PROGRESS = 1;
    static final int MSG_UPDATE_SEEK_PROGRESS = 2;
    static final int MSG_WAIT_TIME = 3;
    private static final String TAG = "LocalAlarmPlayerV2Activity";
    CheckBox cbTogglePlay;
    /* access modifiers changed from: private */
    public String currentPlayFilePath = null;
    private boolean isMute = false;
    ImageView ivFullScreen;
    AlarmItemV2 mAlarItem;
    AlarmManagerV2 mAlarmFileManager;
    View mBottomViewContainer;
    private boolean mCanRetry = false;
    /* access modifiers changed from: private */
    public TextView mDurationView;
    ImageView mFullScreenView;
    AnimationDrawable mLoadingAnimation;
    private LocalAudioPlay mLocalAudioPlay = null;
    /* access modifiers changed from: private */
    public boolean mNeedCheck = false;
    private boolean mPlayAutoPause = false;
    /* access modifiers changed from: private */
    public TextView mPlayingView;
    SeekBar mProgressBar;
    View mProgressBarContainer;
    private int mRotation = 0;
    private SeekBar.OnSeekBarChangeListener mSeekBarChange = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (LocalAlarmPlayerV2Activity.this.mSeekBarTouched) {
                LocalAlarmPlayerV2Activity.this.mHandler.removeMessages(2);
                String format = LocalAlarmPlayerV2Activity.this.mTimeFormat.format(Integer.valueOf(i * 1000));
                if (!TextUtils.isEmpty(format)) {
                    LocalAlarmPlayerV2Activity.this.mPlayingView.setText(format);
                }
                Message obtainMessage = LocalAlarmPlayerV2Activity.this.mHandler.obtainMessage();
                obtainMessage.what = 2;
                obtainMessage.arg1 = i;
                obtainMessage.sendToTarget();
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            LocalAlarmPlayerV2Activity.this.mSeekBarTouched = true;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            LocalAlarmPlayerV2Activity.this.mSeekBarTouched = false;
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
                    String format = this.mTimeFormat.format(Integer.valueOf(currentPosition));
                    if (!TextUtils.isEmpty(format)) {
                        this.mPlayingView.setText(format);
                    }
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
        setContentView(R.layout.camera_activity_alarm_video_play_v2);
        this.mNeedCheck = getIntent().getBooleanExtra("check", false);
        SDKLog.e("alarm", "need check " + this.mNeedCheck);
        if (this.mNeedCheck) {
            enableVerifyPincode();
            SDKLog.e("alarm", "enableVerifyPincode " + this.mDeviceStat.isSetPinCode);
        }
        AudioManager audioManager = (AudioManager) activity().getApplicationContext().getSystemService("audio");
        if (audioManager != null) {
            audioManager.requestAudioFocus((AudioManager.OnAudioFocusChangeListener) null, 3, 1);
        }
        this.mAlarmFileManager = this.mCameraDevice.j();
        String stringExtra = getIntent().getStringExtra("alarmItem");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.mAlarItem = new AlarmItemV2(stringExtra);
        }
        boolean booleanExtra = getIntent().getBooleanExtra(ProcessInfo.ALIAS_PUSH, false);
        this.mTimeFormat = new SimpleDateFormat("mm:ss");
        this.mTimeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        initView();
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
        this.mToastImg.setOnClickListener(this);
        this.title_bar = (FrameLayout) findViewById(R.id.title_bar);
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
                SDKLog.d(LocalAlarmPlayerV2Activity.TAG, "onCompletion ");
                LocalAlarmPlayerV2Activity.this.runMainThread(new Runnable() {
                    public void run() {
                        LocalAlarmPlayerV2Activity.this.mHandler.removeMessages(1);
                        LocalAlarmPlayerV2Activity.this.mProgressBar.setProgress(LocalAlarmPlayerV2Activity.this.mProgressBar.getMax());
                        if (!TextUtils.isEmpty(LocalAlarmPlayerV2Activity.this.mDurationView.getText())) {
                            LocalAlarmPlayerV2Activity.this.mPlayingView.setText(LocalAlarmPlayerV2Activity.this.mDurationView.getText());
                        }
                        LocalAlarmPlayerV2Activity.this.cbTogglePlay.setChecked(true);
                        if (LocalAlarmPlayerV2Activity.this.mProgressBarContainer.getVisibility() == 8) {
                            AnimationUtils.loadAnimation(LocalAlarmPlayerV2Activity.this.activity(), R.anim.slide_in_top).setFillAfter(true);
                            Animation loadAnimation = AnimationUtils.loadAnimation(LocalAlarmPlayerV2Activity.this.activity(), R.anim.slide_in_bottom);
                            loadAnimation.setFillAfter(true);
                            LocalAlarmPlayerV2Activity.this.mProgressBarContainer.setClickable(true);
                            LocalAlarmPlayerV2Activity.this.mProgressBarContainer.setVisibility(0);
                            LocalAlarmPlayerV2Activity.this.mProgressBarContainer.startAnimation(loadAnimation);
                        }
                    }
                });
            }
        });
        this.mVideoPlayerRender.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                LocalAlarmPlayerV2Activity.this.cbTogglePlay.setChecked(false);
            }
        });
        this.mVideoPlayerRender.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                SDKLog.d(LocalAlarmPlayerV2Activity.TAG, "onError " + i);
                LocalAlarmPlayerV2Activity.this.runMainThread(new Runnable() {
                    public void run() {
                        LocalAlarmPlayerV2Activity.this.mProgressBar.setProgress(LocalAlarmPlayerV2Activity.this.mVideoPlayerRender.getCurrentPosition() / 1000);
                        String format = LocalAlarmPlayerV2Activity.this.mTimeFormat.format(Integer.valueOf(LocalAlarmPlayerV2Activity.this.mVideoPlayerRender.getCurrentPosition()));
                        if (!TextUtils.isEmpty(format)) {
                            LocalAlarmPlayerV2Activity.this.mPlayingView.setText(format);
                        }
                        LocalAlarmPlayerV2Activity.this.cbTogglePlay.setChecked(true);
                    }
                });
                return true;
            }
        });
        this.mVideoView.setVideoViewListener(new XmVideoViewGl.IVideoViewListener() {
            public void onVideoViewClick() {
                if (LocalAlarmPlayerV2Activity.this.mProgressBarContainer.isShown()) {
                    AnimationUtils.loadAnimation(LocalAlarmPlayerV2Activity.this.activity(), R.anim.slide_out_top).setFillAfter(true);
                    Animation loadAnimation = AnimationUtils.loadAnimation(LocalAlarmPlayerV2Activity.this.activity(), R.anim.slide_out_bottom);
                    loadAnimation.setFillAfter(true);
                    LocalAlarmPlayerV2Activity.this.mProgressBarContainer.setClickable(false);
                    LocalAlarmPlayerV2Activity.this.mProgressBarContainer.setVisibility(8);
                    LocalAlarmPlayerV2Activity.this.mProgressBarContainer.startAnimation(loadAnimation);
                    return;
                }
                AnimationUtils.loadAnimation(LocalAlarmPlayerV2Activity.this.activity(), R.anim.slide_in_top).setFillAfter(true);
                Animation loadAnimation2 = AnimationUtils.loadAnimation(LocalAlarmPlayerV2Activity.this.activity(), R.anim.slide_in_bottom);
                loadAnimation2.setFillAfter(true);
                LocalAlarmPlayerV2Activity.this.mProgressBarContainer.setClickable(true);
                LocalAlarmPlayerV2Activity.this.mProgressBarContainer.setVisibility(0);
                LocalAlarmPlayerV2Activity.this.mProgressBarContainer.startAnimation(loadAnimation2);
            }
        });
        this.mFullScreenView = (ImageView) findViewById(R.id.full_screen);
        this.cbTogglePlay = (CheckBox) findViewById(R.id.cbTogglePlay);
        this.cbTogglePlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    LocalAlarmPlayerV2Activity.this.pausePlay();
                    LocalAlarmPlayerV2Activity.this.cbTogglePlay.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, LocalAlarmPlayerV2Activity.this.getResources().getDrawable(R.drawable.camera_icon_play02), (Drawable) null);
                    return;
                }
                LocalAlarmPlayerV2Activity.this.startPlay();
                LocalAlarmPlayerV2Activity.this.cbTogglePlay.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, LocalAlarmPlayerV2Activity.this.getResources().getDrawable(R.drawable.camera_icon_pause02), (Drawable) null);
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
                LocalAlarmPlayerV2Activity.this.runMainThread(new Runnable() {
                    public void run() {
                        if (!LocalAlarmPlayerV2Activity.this.mNeedCheck || !LocalAlarmPlayerV2Activity.this.mCameraDevice.m()) {
                            LocalAlarmPlayerV2Activity.this.showNetAlarm();
                        }
                    }
                });
            }
        });
    }

    private void initFeedback() {
        final String str;
        if (XmPluginHostApi.instance().getApiLevel() >= 22 && XmPluginHostApi.instance().getGlobalSettingServer() != null && XmPluginHostApi.instance().getGlobalSettingServer().equals("cn") && this.mAlarItem != null && !TextUtils.isEmpty(this.mAlarItem.j)) {
            boolean contains = this.mAlarItem.j.contains("people");
            PrintStream printStream = System.out;
            printStream.println("mytest:getOnlyPeopleStatus:" + contains);
            if (contains) {
                ((TextView) findViewById(R.id.tvFeedback)).setText(R.string.nobody_video);
                str = getString(R.string.nobody_title);
            } else {
                ((TextView) findViewById(R.id.tvFeedback)).setText(R.string.hasbody_video);
                str = getString(R.string.hasbody_title);
            }
            findViewById(R.id.tvFeedback).setVisibility(0);
            findViewById(R.id.tvFeedback).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FeedbackDialog feedbackDialog = new FeedbackDialog(LocalAlarmPlayerV2Activity.this.getContext());
                    feedbackDialog.setTitle(str);
                    feedbackDialog.setModel(LocalAlarmPlayerV2Activity.this.mCameraDevice.getModel());
                    feedbackDialog.setOnClickListener(new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            LocalAlarmPlayerV2Activity.this.mCameraDevice.a(LocalAlarmPlayerV2Activity.this.mAlarItem.g, LocalAlarmPlayerV2Activity.this.mAlarItem.h, true, new Callback<String>() {
                                public void onSuccess(String str) {
                                    Toast.makeText(LocalAlarmPlayerV2Activity.this.activity(), R.string.thanks_feedback, 1).show();
                                }

                                public void onFailure(int i, String str) {
                                    Toast.makeText(LocalAlarmPlayerV2Activity.this.activity(), R.string.action_fail, 1).show();
                                }
                            }, true);
                        }
                    });
                    feedbackDialog.show();
                }
            });
        }
    }

    public void onResume() {
        super.onResume();
        if (this.mPlayAutoPause) {
            startPlay();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.mVideoPlayerRender != null && this.mVideoPlayerRender.isPlaying()) {
            pausePlay();
            this.mPlayAutoPause = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 9999) {
            return;
        }
        if (!TextUtils.isEmpty(XmPluginHostApi.instance().getDevicePincode(this.mDid))) {
            showNetAlarm();
            this.mCameraDevice.f((Callback<Void>) null);
            return;
        }
        LogUtil.b(TAG, "getDevicePincode empty ");
    }

    /* access modifiers changed from: package-private */
    public void startPlay() {
        LogUtil.a(TAG, "startPlay");
        if (!TextUtils.isEmpty(this.currentPlayFilePath)) {
            this.mVideoPlayerRender.openVideoPlayer(this.currentPlayFilePath);
            if (this.isMute) {
                this.mVideoPlayerRender.setVolume(0);
            } else {
                this.mVideoPlayerRender.setVolume(1);
            }
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
        this.mHandler.removeMessages(1);
        if (this.mLoadingAnimation != null && this.mLoadingAnimation.isRunning()) {
            this.mLoadingAnimation.stop();
        }
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
        if (NetworkUtil.c(activity())) {
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity());
            builder.b((int) R.string.push_net_alarm);
            builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    LocalAlarmPlayerV2Activity.this.finish();
                }
            });
            builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    if (LocalAlarmPlayerV2Activity.this.mAlarItem != null) {
                        LocalAlarmPlayerV2Activity.this.getAlarmFile(LocalAlarmPlayerV2Activity.this.mAlarItem);
                    } else {
                        LocalAlarmPlayerV2Activity.this.parseIntent();
                    }
                }
            });
            builder.d();
        } else if (this.mAlarItem != null) {
            getAlarmFile(this.mAlarItem);
        } else {
            parseIntent();
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
        Toast.makeText(activity(), R.string.sdcard_video_download_failed, 0).show();
        LogUtil.b(TAG, "Error alarm " + str);
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
        this.mToastText.setText(getString(R.string.loading));
    }

    /* access modifiers changed from: private */
    public void hideProgress() {
        if (this.mLoadingAnimation.isRunning()) {
            this.mLoadingAnimation.stop();
        }
        if (this.mToastCenter.getVisibility() == 0) {
            this.mToastCenter.setVisibility(8);
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
                        if (LocalAlarmPlayerV2Activity.this.mAlarItem != null) {
                            ArrayList<AlarmItemV2> arrayList = new ArrayList<>();
                            arrayList.add(LocalAlarmPlayerV2Activity.this.mAlarItem);
                            LocalAlarmPlayerV2Activity.this.activity().setResult(-1);
                            try {
                                JSONObject jSONObject = new JSONObject();
                                JSONArray jSONArray = new JSONArray();
                                for (AlarmItemV2 alarmItemV2 : arrayList) {
                                    jSONArray.put("" + alarmItemV2.g);
                                }
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("fileIds", jSONArray);
                                jSONObject.put("fileIds", jSONObject2);
                                jSONObject.put("model", LocalAlarmPlayerV2Activity.this.mCameraDevice.getModel());
                                jSONObject.put("did", LocalAlarmPlayerV2Activity.this.mCameraDevice.getDid());
                                AlarmNetUtils.a().j(LocalAlarmPlayerV2Activity.this.mCameraDevice.getModel(), jSONObject.toString(), new Callback<JSONObject>() {
                                    public void onSuccess(JSONObject jSONObject) {
                                        if (!LocalAlarmPlayerV2Activity.this.isFinishing()) {
                                            if (jSONObject == null) {
                                                ToastUtil.a((Context) LocalAlarmPlayerV2Activity.this.activity(), (int) R.string.delete_failed);
                                            } else if (jSONObject.optInt("code") == 0) {
                                                ToastUtil.a((Context) LocalAlarmPlayerV2Activity.this.activity(), (int) R.string.delete_sucess);
                                            } else {
                                                ToastUtil.a((Context) LocalAlarmPlayerV2Activity.this.activity(), (int) R.string.delete_failed);
                                            }
                                            LocalAlarmPlayerV2Activity.this.finish();
                                        }
                                    }

                                    public void onFailure(int i, String str) {
                                        if (!LocalAlarmPlayerV2Activity.this.isFinishing()) {
                                            ToastUtil.a((Context) LocalAlarmPlayerV2Activity.this.activity(), (int) R.string.delete_failed);
                                            LocalAlarmPlayerV2Activity.this.finish();
                                        }
                                    }
                                });
                            } catch (JSONException unused) {
                                ToastUtil.a((Context) LocalAlarmPlayerV2Activity.this.activity(), (int) R.string.delete_failed);
                                LocalAlarmPlayerV2Activity.this.finish();
                            }
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
                saveMediaFileWithoutHint();
                return;
            case R.id.title_bar_return:
                finish();
                return;
            case R.id.toast_icon:
                hideFail();
                if (this.mAlarItem != null) {
                    getAlarmFile(this.mAlarItem);
                    return;
                }
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

    private void saveMediaFileWithoutHint() {
        if (!TextUtils.isEmpty(this.currentPlayFilePath)) {
            final String a2 = FileUtil.a(true, this.mCameraDevice.getDid());
            if (!TextUtils.isEmpty(a2)) {
                ThreadPool.a(new Runnable() {
                    /* JADX WARNING: Removed duplicated region for block: B:36:0x009c A[SYNTHETIC, Splitter:B:36:0x009c] */
                    /* JADX WARNING: Removed duplicated region for block: B:41:0x00bd A[SYNTHETIC, Splitter:B:41:0x00bd] */
                    /* JADX WARNING: Removed duplicated region for block: B:50:0x00e7 A[SYNTHETIC, Splitter:B:50:0x00e7] */
                    /* JADX WARNING: Removed duplicated region for block: B:55:0x0108 A[SYNTHETIC, Splitter:B:55:0x0108] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                            r8 = this;
                            r0 = 0
                            r1 = 17
                            com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity r2 = com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity.this     // Catch:{ IOException -> 0x007e, all -> 0x007b }
                            android.os.Handler r2 = r2.mHandler     // Catch:{ IOException -> 0x007e, all -> 0x007b }
                            r3 = 16
                            r2.sendEmptyMessage(r3)     // Catch:{ IOException -> 0x007e, all -> 0x007b }
                            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x007e, all -> 0x007b }
                            com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity r3 = com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity.this     // Catch:{ IOException -> 0x007e, all -> 0x007b }
                            java.lang.String r3 = r3.currentPlayFilePath     // Catch:{ IOException -> 0x007e, all -> 0x007b }
                            r2.<init>(r3)     // Catch:{ IOException -> 0x007e, all -> 0x007b }
                            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0075, all -> 0x006e }
                            java.lang.String r4 = r0     // Catch:{ IOException -> 0x0075, all -> 0x006e }
                            r3.<init>(r4)     // Catch:{ IOException -> 0x0075, all -> 0x006e }
                            r0 = 1024(0x400, float:1.435E-42)
                            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x0069, all -> 0x0063 }
                        L_0x0022:
                            int r4 = r2.read(r0)     // Catch:{ IOException -> 0x0069, all -> 0x0063 }
                            r5 = -1
                            if (r4 == r5) goto L_0x002e
                            r5 = 0
                            r3.write(r0, r5, r4)     // Catch:{ IOException -> 0x0069, all -> 0x0063 }
                            goto L_0x0022
                        L_0x002e:
                            java.lang.String r0 = r0     // Catch:{ IOException -> 0x0069, all -> 0x0063 }
                            com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity r4 = com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity.this     // Catch:{ IOException -> 0x0069, all -> 0x0063 }
                            r4.toShare(r0)     // Catch:{ IOException -> 0x0069, all -> 0x0063 }
                            r2.close()     // Catch:{ IOException -> 0x0039 }
                            goto L_0x0054
                        L_0x0039:
                            r0 = move-exception
                            java.lang.String r2 = "LocalAlarmPlayerV2Activity"
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder
                            r4.<init>()
                            java.lang.String r5 = "IOException:"
                            r4.append(r5)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r4.append(r0)
                            java.lang.String r0 = r4.toString()
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r2, r0)
                        L_0x0054:
                            r3.close()     // Catch:{ IOException -> 0x0059 }
                            goto L_0x00dc
                        L_0x0059:
                            r0 = move-exception
                            java.lang.String r2 = "LocalAlarmPlayerV2Activity"
                            java.lang.StringBuilder r3 = new java.lang.StringBuilder
                            r3.<init>()
                            goto L_0x00c9
                        L_0x0063:
                            r0 = move-exception
                            r7 = r2
                            r2 = r0
                            r0 = r7
                            goto L_0x00e5
                        L_0x0069:
                            r0 = move-exception
                            r7 = r2
                            r2 = r0
                            r0 = r7
                            goto L_0x0080
                        L_0x006e:
                            r3 = move-exception
                            r7 = r3
                            r3 = r0
                            r0 = r2
                            r2 = r7
                            goto L_0x00e5
                        L_0x0075:
                            r3 = move-exception
                            r7 = r3
                            r3 = r0
                            r0 = r2
                            r2 = r7
                            goto L_0x0080
                        L_0x007b:
                            r2 = move-exception
                            r3 = r0
                            goto L_0x00e5
                        L_0x007e:
                            r2 = move-exception
                            r3 = r0
                        L_0x0080:
                            java.lang.String r4 = "LocalAlarmPlayerV2Activity"
                            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e4 }
                            r5.<init>()     // Catch:{ all -> 0x00e4 }
                            java.lang.String r6 = "IOException:"
                            r5.append(r6)     // Catch:{ all -> 0x00e4 }
                            java.lang.String r2 = r2.getLocalizedMessage()     // Catch:{ all -> 0x00e4 }
                            r5.append(r2)     // Catch:{ all -> 0x00e4 }
                            java.lang.String r2 = r5.toString()     // Catch:{ all -> 0x00e4 }
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r4, r2)     // Catch:{ all -> 0x00e4 }
                            if (r0 == 0) goto L_0x00bb
                            r0.close()     // Catch:{ IOException -> 0x00a0 }
                            goto L_0x00bb
                        L_0x00a0:
                            r0 = move-exception
                            java.lang.String r2 = "LocalAlarmPlayerV2Activity"
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder
                            r4.<init>()
                            java.lang.String r5 = "IOException:"
                            r4.append(r5)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r4.append(r0)
                            java.lang.String r0 = r4.toString()
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r2, r0)
                        L_0x00bb:
                            if (r3 == 0) goto L_0x00dc
                            r3.close()     // Catch:{ IOException -> 0x00c1 }
                            goto L_0x00dc
                        L_0x00c1:
                            r0 = move-exception
                            java.lang.String r2 = "LocalAlarmPlayerV2Activity"
                            java.lang.StringBuilder r3 = new java.lang.StringBuilder
                            r3.<init>()
                        L_0x00c9:
                            java.lang.String r4 = "IOException:"
                            r3.append(r4)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r3.append(r0)
                            java.lang.String r0 = r3.toString()
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r2, r0)
                        L_0x00dc:
                            com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity r0 = com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity.this
                            android.os.Handler r0 = r0.mHandler
                            r0.sendEmptyMessage(r1)
                            return
                        L_0x00e4:
                            r2 = move-exception
                        L_0x00e5:
                            if (r0 == 0) goto L_0x0106
                            r0.close()     // Catch:{ IOException -> 0x00eb }
                            goto L_0x0106
                        L_0x00eb:
                            r0 = move-exception
                            java.lang.String r4 = "LocalAlarmPlayerV2Activity"
                            java.lang.StringBuilder r5 = new java.lang.StringBuilder
                            r5.<init>()
                            java.lang.String r6 = "IOException:"
                            r5.append(r6)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r5.append(r0)
                            java.lang.String r0 = r5.toString()
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r4, r0)
                        L_0x0106:
                            if (r3 == 0) goto L_0x0127
                            r3.close()     // Catch:{ IOException -> 0x010c }
                            goto L_0x0127
                        L_0x010c:
                            r0 = move-exception
                            java.lang.StringBuilder r3 = new java.lang.StringBuilder
                            r3.<init>()
                            java.lang.String r4 = "IOException:"
                            r3.append(r4)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r3.append(r0)
                            java.lang.String r0 = r3.toString()
                            java.lang.String r3 = "LocalAlarmPlayerV2Activity"
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r3, r0)
                        L_0x0127:
                            com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity r0 = com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity.this
                            android.os.Handler r0 = r0.mHandler
                            r0.sendEmptyMessage(r1)
                            throw r2
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity.AnonymousClass12.run():void");
                    }
                });
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

    private void saveMediaFile() {
        if (!TextUtils.isEmpty(this.currentPlayFilePath)) {
            final String a2 = FileUtil.a(true, this.mCameraDevice.getDid());
            if (!TextUtils.isEmpty(a2)) {
                ThreadPool.a(new Runnable() {
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
                            com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity r2 = com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity.this     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            android.os.Handler r2 = r2.mHandler     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            r3 = 16
                            r2.sendEmptyMessage(r3)     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity r3 = com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity.this     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            java.lang.String r3 = r3.currentPlayFilePath     // Catch:{ IOException -> 0x0080, all -> 0x007d }
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
                            com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity r0 = com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity.this     // Catch:{ IOException -> 0x006b, all -> 0x0065 }
                            android.os.Handler r0 = r0.mHandler     // Catch:{ IOException -> 0x006b, all -> 0x0065 }
                            r4 = 14
                            r0.sendEmptyMessage(r4)     // Catch:{ IOException -> 0x006b, all -> 0x0065 }
                            r2.close()     // Catch:{ IOException -> 0x003b }
                            goto L_0x0056
                        L_0x003b:
                            r0 = move-exception
                            java.lang.String r2 = "LocalAlarmPlayerV2Activity"
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder
                            r4.<init>()
                            java.lang.String r5 = "IOException:"
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
                            java.lang.String r2 = "LocalAlarmPlayerV2Activity"
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
                            java.lang.String r4 = "LocalAlarmPlayerV2Activity"
                            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e6 }
                            r5.<init>()     // Catch:{ all -> 0x00e6 }
                            java.lang.String r6 = "IOException:"
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
                            java.lang.String r2 = "LocalAlarmPlayerV2Activity"
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder
                            r4.<init>()
                            java.lang.String r5 = "IOException:"
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
                            java.lang.String r2 = "LocalAlarmPlayerV2Activity"
                            java.lang.StringBuilder r3 = new java.lang.StringBuilder
                            r3.<init>()
                        L_0x00cb:
                            java.lang.String r4 = "IOException:"
                            r3.append(r4)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r3.append(r0)
                            java.lang.String r0 = r3.toString()
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r2, r0)
                        L_0x00de:
                            com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity r0 = com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity.this
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
                            java.lang.String r4 = "LocalAlarmPlayerV2Activity"
                            java.lang.StringBuilder r5 = new java.lang.StringBuilder
                            r5.<init>()
                            java.lang.String r6 = "IOException:"
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
                            java.lang.String r4 = "IOException:"
                            r3.append(r4)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r3.append(r0)
                            java.lang.String r0 = r3.toString()
                            java.lang.String r3 = "LocalAlarmPlayerV2Activity"
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r3, r0)
                        L_0x0129:
                            com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity r0 = com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity.this
                            android.os.Handler r0 = r0.mHandler
                            r0.sendEmptyMessage(r1)
                            throw r2
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.local.LocalAlarmPlayerV2Activity.AnonymousClass13.run():void");
                    }
                });
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

    /* access modifiers changed from: private */
    public void getAlarmFile(AlarmItemV2 alarmItemV2) {
        initFeedback();
        showProgress();
        this.mCameraDevice.j().a((Context) this, alarmItemV2, (AlarmManagerV2.IAlarmCallback<String>) new AlarmManagerV2.IAlarmCallback<String>() {
            public void onSuccess(String str, Object obj) {
                if (!LocalAlarmPlayerV2Activity.this.isFinishing()) {
                    LocalAlarmPlayerV2Activity.this.hideProgress();
                    LogUtil.a(LocalAlarmPlayerV2Activity.TAG, "result mCurrentPath:" + str);
                    if (!TextUtils.isEmpty(str)) {
                        String unused = LocalAlarmPlayerV2Activity.this.currentPlayFilePath = str;
                        TextView textView = (TextView) LocalAlarmPlayerV2Activity.this.findViewById(R.id.title_bar_title);
                        if (!TextUtils.isEmpty(LocalAlarmPlayerV2Activity.this.currentPlayFilePath)) {
                            LocalFileManager.LocalFile b = LocalAlarmPlayerV2Activity.this.mCameraDevice.b().b(str);
                            if (b != null) {
                                textView.setText(b.c.getName());
                            }
                            long access$1700 = LocalAlarmPlayerV2Activity.this.extractVideoDuration(LocalAlarmPlayerV2Activity.this.currentPlayFilePath);
                            LocalAlarmPlayerV2Activity.this.mProgressBar.setMax((int) (access$1700 / 1000));
                            TextView access$000 = LocalAlarmPlayerV2Activity.this.mDurationView;
                            access$000.setText("" + LocalAlarmPlayerV2Activity.this.mTimeFormat.format(Long.valueOf(access$1700)));
                        }
                        LocalAlarmPlayerV2Activity.this.startPlay();
                    }
                }
            }

            public void onFailure(int i, String str) {
                if (!LocalAlarmPlayerV2Activity.this.isFinishing()) {
                    LocalAlarmPlayerV2Activity.this.hideProgress();
                    LocalAlarmPlayerV2Activity localAlarmPlayerV2Activity = LocalAlarmPlayerV2Activity.this;
                    localAlarmPlayerV2Activity.showFail("" + i);
                    String unused = LocalAlarmPlayerV2Activity.this.currentPlayFilePath = null;
                    LogUtil.b(LocalAlarmPlayerV2Activity.TAG, "errorCode:" + i + " errorMessage:" + str);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void parseIntent() {
        String stringExtra = getIntent().getStringExtra("extra");
        if (!TextUtils.isEmpty(stringExtra)) {
            try {
                JSONObject jSONObject = new JSONObject(stringExtra);
                long optLong = jSONObject.optLong("createTime");
                String optString = jSONObject.optString("fileId");
                if (optLong > 0 && !TextUtils.isEmpty(optString)) {
                    loadAlarmVideo(optString);
                }
            } catch (JSONException unused) {
            }
        }
    }

    private void loadAlarmVideo(final String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("did", this.mCameraDevice.getDid());
                jSONObject.put("region", Locale.getDefault().getCountry());
                jSONObject.put("fileId", str);
                showProgress();
                AlarmNetUtils.a().k(this.mCameraDevice.getModel(), jSONObject.toString(), new Callback<JSONObject>() {
                    public void onSuccess(JSONObject jSONObject) {
                        JSONObject optJSONObject;
                        if (!LocalAlarmPlayerV2Activity.this.isFinishing()) {
                            LocalAlarmPlayerV2Activity.this.hideProgress();
                            if (jSONObject != null && jSONObject.optInt("code") == 0 && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
                                String optString = optJSONObject.optString("videoStoreId");
                                if (!TextUtils.isEmpty(optString)) {
                                    LocalAlarmPlayerV2Activity.this.mAlarItem = new AlarmItemV2();
                                    LocalAlarmPlayerV2Activity.this.mAlarItem.g = str;
                                    LocalAlarmPlayerV2Activity.this.mAlarItem.h = optString;
                                    LocalAlarmPlayerV2Activity.this.getAlarmFile(LocalAlarmPlayerV2Activity.this.mAlarItem);
                                }
                            }
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!LocalAlarmPlayerV2Activity.this.isFinishing()) {
                            LogUtil.b(LocalAlarmPlayerV2Activity.TAG, " getAlarmStoreId errorCode:" + i + HTTP.HEADER_LINE_DELIM + str);
                            LocalAlarmPlayerV2Activity.this.hideProgress();
                        }
                    }
                });
            } catch (JSONException unused) {
            }
        }
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
}
