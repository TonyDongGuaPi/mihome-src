package com.xiaomi.smarthome.camera.activity.local;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.mijia.debug.Tag;
import com.mijia.model.local.LocalFileManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.XmMp4Player;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class LocalVideoPlayActivity extends CameraBaseActivity implements View.OnClickListener {
    static final int MSG_UPDATE_PROGRESS = 1;
    static final int MSG_UPDATE_SEEK_PROGRESS = 2;
    CheckBox cbTogglePlay;
    private boolean isMute = false;
    ImageView ivFullScreen;
    private View mBottomViewContainer;
    private TextView mDurationView;
    private ImageView mFullScreenView;
    private LocalFileManager.LocalFile mLocalFile;
    private LocalFileManager mLocalFileManager;
    private boolean mPlayAutoPause = false;
    /* access modifiers changed from: private */
    public TextView mPlayingView;
    private SeekBar mProgressBar;
    private View mProgressBarContainer;
    private int mRotation = 0;
    private SeekBar.OnSeekBarChangeListener mSeekBarChange = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (LocalVideoPlayActivity.this.mSeekBarTouched) {
                LocalVideoPlayActivity.this.mHandler.removeMessages(2);
                LocalVideoPlayActivity.this.mPlayingView.setText(LocalVideoPlayActivity.this.mTimeFormat.format(Integer.valueOf(i * 1000)));
                Message obtainMessage = LocalVideoPlayActivity.this.mHandler.obtainMessage();
                obtainMessage.what = 2;
                obtainMessage.arg1 = i;
                obtainMessage.sendToTarget();
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            LocalVideoPlayActivity.this.mSeekBarTouched = true;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            LocalVideoPlayActivity.this.mSeekBarTouched = false;
        }
    };
    boolean mSeekBarTouched = false;
    /* access modifiers changed from: private */
    public SimpleDateFormat mTimeFormat;
    private XmMp4Player mVideoPlayerRender;
    private FrameLayout mVideoViewFrame;
    private FrameLayout title_bar;
    private ImageView videoMute;
    private XmVideoViewGl xmVideoViewGl;

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
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
            default:
                return;
        }
    }

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        ((AudioManager) activity().getApplicationContext().getSystemService("audio")).requestAudioFocus((AudioManager.OnAudioFocusChangeListener) null, 3, 1);
        this.mLocalFileManager = this.mCameraDevice.b();
        if (this.mLocalFile == null) {
            this.mLocalFile = this.mLocalFileManager.b(getIntent().getStringExtra("file_path"));
        }
        if (this.mLocalFile == null) {
            LogUtil.b(Tag.b, "no local file");
            finish();
            return;
        }
        setContentView(R.layout.camera_activity_local_video_play);
        this.mTimeFormat = new SimpleDateFormat("mm:ss");
        this.mTimeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        initView();
    }

    private void initView() {
        this.title_bar = (FrameLayout) findViewById(R.id.title_bar);
        this.title_bar.bringToFront();
        this.videoMute = (ImageView) findViewById(R.id.video_mute);
        this.videoMute.setOnClickListener(this);
        this.ivFullScreen = (ImageView) findViewById(R.id.ivFullScreen);
        this.ivFullScreen.setOnClickListener(this);
        TextView textView = (TextView) findViewById(R.id.title_bar_title);
        if (this.mLocalFile != null) {
            textView.setText(this.mLocalFile.c.getName());
        }
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.mBottomViewContainer = findViewById(R.id.bottom_tools_container);
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
        this.xmVideoViewGl = XmPluginHostApi.instance().createMp4View(this, frameLayout, true);
        this.xmVideoViewGl.setMiniScale(true);
        this.mVideoPlayerRender = this.xmVideoViewGl.getMp4Player();
        this.mVideoPlayerRender.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public final void onCompletion(MediaPlayer mediaPlayer) {
                LocalVideoPlayActivity.this.runMainThread(new Runnable() {
                    public final void run() {
                        LocalVideoPlayActivity.lambda$null$0(LocalVideoPlayActivity.this);
                    }
                });
            }
        });
        this.mVideoPlayerRender.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                return LocalVideoPlayActivity.this.runMainThread(new Runnable() {
                    public final void run() {
                        LocalVideoPlayActivity.lambda$null$2(LocalVideoPlayActivity.this);
                    }
                });
            }
        });
        this.xmVideoViewGl.setVideoViewListener(new XmVideoViewGl.IVideoViewListener() {
            public final void onVideoViewClick() {
                LocalVideoPlayActivity.lambda$initView$4(LocalVideoPlayActivity.this);
            }
        });
        this.mFullScreenView = (ImageView) findViewById(R.id.full_screen);
        this.mFullScreenView.setOnClickListener(this);
        findViewById(R.id.flip).setOnClickListener(this);
        this.cbTogglePlay = (CheckBox) findViewById(R.id.cbTogglePlay);
        this.cbTogglePlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LocalVideoPlayActivity.lambda$initView$5(LocalVideoPlayActivity.this, compoundButton, z);
            }
        });
        findViewById(R.id.local_share).setOnClickListener(this);
        findViewById(R.id.local_delete).setOnClickListener(this);
        this.xmVideoViewGl.initial();
        this.mVideoPlayerRender.setRenderCallback(new XmMp4Player.RenderCallback() {
            public final void onInitialed() {
                LocalVideoPlayActivity.this.runMainThread(new Runnable() {
                    public final void run() {
                        LocalVideoPlayActivity.this.startPlay();
                    }
                });
            }
        });
    }

    public static /* synthetic */ void lambda$null$0(LocalVideoPlayActivity localVideoPlayActivity) {
        localVideoPlayActivity.mHandler.removeMessages(1);
        localVideoPlayActivity.mProgressBar.setProgress(localVideoPlayActivity.mProgressBar.getMax());
        localVideoPlayActivity.mPlayingView.setText(localVideoPlayActivity.mDurationView.getText());
        localVideoPlayActivity.cbTogglePlay.setChecked(true);
    }

    public static /* synthetic */ void lambda$null$2(LocalVideoPlayActivity localVideoPlayActivity) {
        localVideoPlayActivity.mHandler.removeMessages(1);
        localVideoPlayActivity.mProgressBar.setProgress(localVideoPlayActivity.mVideoPlayerRender.getCurrentPosition() / 1000);
        localVideoPlayActivity.mPlayingView.setText(localVideoPlayActivity.mTimeFormat.format(Integer.valueOf(localVideoPlayActivity.mVideoPlayerRender.getCurrentPosition())));
        localVideoPlayActivity.cbTogglePlay.setChecked(true);
    }

    public static /* synthetic */ void lambda$initView$4(LocalVideoPlayActivity localVideoPlayActivity) {
        if (localVideoPlayActivity.mProgressBarContainer.getVisibility() == 0) {
            AnimationUtils.loadAnimation(localVideoPlayActivity.activity(), R.anim.slide_out_top).setFillAfter(true);
            Animation loadAnimation = AnimationUtils.loadAnimation(localVideoPlayActivity.activity(), R.anim.slide_out_bottom);
            loadAnimation.setFillAfter(true);
            localVideoPlayActivity.mProgressBarContainer.setClickable(false);
            localVideoPlayActivity.mProgressBarContainer.setVisibility(4);
            localVideoPlayActivity.mProgressBarContainer.startAnimation(loadAnimation);
            return;
        }
        AnimationUtils.loadAnimation(localVideoPlayActivity.activity(), R.anim.slide_in_top).setFillAfter(true);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(localVideoPlayActivity.activity(), R.anim.slide_in_bottom);
        loadAnimation2.setFillAfter(true);
        localVideoPlayActivity.mProgressBarContainer.setClickable(true);
        localVideoPlayActivity.mProgressBarContainer.setVisibility(0);
        localVideoPlayActivity.mProgressBarContainer.startAnimation(loadAnimation2);
    }

    public static /* synthetic */ void lambda$initView$5(LocalVideoPlayActivity localVideoPlayActivity, CompoundButton compoundButton, boolean z) {
        if (z) {
            localVideoPlayActivity.pausePlay();
            localVideoPlayActivity.cbTogglePlay.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, localVideoPlayActivity.getResources().getDrawable(R.drawable.camera_icon_play02), (Drawable) null);
            return;
        }
        localVideoPlayActivity.startPlay();
        localVideoPlayActivity.cbTogglePlay.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, localVideoPlayActivity.getResources().getDrawable(R.drawable.camera_icon_pause02), (Drawable) null);
    }

    public void onResume() {
        super.onResume();
        this.mHandler.postDelayed(new Runnable() {
            public final void run() {
                LocalVideoPlayActivity.lambda$onResume$8(LocalVideoPlayActivity.this);
            }
        }, 500);
    }

    public static /* synthetic */ void lambda$onResume$8(LocalVideoPlayActivity localVideoPlayActivity) {
        if (localVideoPlayActivity.mPlayAutoPause) {
            localVideoPlayActivity.startPlay();
        }
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
            this.mVideoPlayerRender.openVideoPlayer(this.mLocalFile.c.getAbsolutePath());
            if (!this.isMute) {
                this.mVideoPlayerRender.setVolume(1);
            } else {
                this.mVideoPlayerRender.setVolume(0);
            }
            this.mPlayAutoPause = false;
        }
        this.mHandler.sendEmptyMessage(1);
    }

    private void pausePlay() {
        this.mVideoPlayerRender.pause();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeMessages(1);
        if (this.xmVideoViewGl != null) {
            this.xmVideoViewGl.release();
        }
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
            this.ivFullScreen.setImageResource(R.drawable.camera_alarm_icon_mixscreen);
            this.xmVideoViewGl.setVideoFrameSize(-1, -1, true);
            return;
        }
        getWindow().clearFlags(1024);
        this.title_bar.setVisibility(0);
        this.title_bar.bringToFront();
        this.mBottomViewContainer.setVisibility(0);
        this.ivFullScreen.setImageResource(R.drawable.camera_icon_fullscreen2);
        this.xmVideoViewGl.setVideoFrameSize(-1, -1, false);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.flip:
                this.mRotation += 90;
                this.mRotation %= 360;
                this.xmVideoViewGl.setRotation(this.mRotation);
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
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        LocalVideoPlayActivity.lambda$onClick$9(LocalVideoPlayActivity.this, dialogInterface, i);
                    }
                });
                builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
                builder.d();
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

    public static /* synthetic */ void lambda$onClick$9(LocalVideoPlayActivity localVideoPlayActivity, DialogInterface dialogInterface, int i) {
        if (localVideoPlayActivity.mLocalFile != null) {
            localVideoPlayActivity.mLocalFileManager.a(localVideoPlayActivity.mLocalFile);
            Toast.makeText(localVideoPlayActivity.activity(), R.string.local_file_delete_success, 0).show();
            localVideoPlayActivity.finish();
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
