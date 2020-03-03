package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.content.Context;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.FileDataSourceFactory;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.exopackage.MJExoPlayerViewP;
import java.io.File;

public class CloudVideoLocalExoPlayerActivity extends CloudVideoBaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "CloudVideoLocalExoPlayerActivity";
    private AudioManager audioManager = null;
    /* access modifiers changed from: private */
    public CheckBox cbIsMute;
    /* access modifiers changed from: private */
    public CheckBox cbTogglePlay;
    private int currentVolume;
    private MJExoPlayerViewP exoVideoView;
    /* access modifiers changed from: private */
    public Runnable getCurrentPosRunnable = new Runnable() {
        public void run() {
            if (CloudVideoLocalExoPlayerActivity.this.simpleExoPlayer != null) {
                final long currentPosition = CloudVideoLocalExoPlayerActivity.this.simpleExoPlayer.getCurrentPosition();
                if (currentPosition > 0) {
                    long unused = CloudVideoLocalExoPlayerActivity.this.storedPlayPosition = currentPosition;
                }
                CloudVideoLocalExoPlayerActivity.this.setProgressTime(currentPosition);
                if (CloudVideoLocalExoPlayerActivity.this.simpleExoPlayer.getPlayWhenReady()) {
                    CloudVideoLocalExoPlayerActivity.this.cbTogglePlay.setChecked(false);
                } else {
                    CloudVideoLocalExoPlayerActivity.this.cbTogglePlay.setChecked(true);
                }
                CloudVideoLocalExoPlayerActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        long duration = CloudVideoLocalExoPlayerActivity.this.simpleExoPlayer.getDuration() / 1000;
                        if (currentPosition / 1000 >= duration) {
                            TextView access$400 = CloudVideoLocalExoPlayerActivity.this.tvVideoStart;
                            access$400.setText("" + CloudVideoLocalExoPlayerActivity.this.longToDate(duration));
                            return;
                        }
                        TextView access$4002 = CloudVideoLocalExoPlayerActivity.this.tvVideoStart;
                        access$4002.setText("" + CloudVideoLocalExoPlayerActivity.this.longToDate(currentPosition / 1000));
                    }
                });
                CloudVideoLocalExoPlayerActivity.this.mHandler.postDelayed(CloudVideoLocalExoPlayerActivity.this.getCurrentPosRunnable, 500);
            }
        }
    };
    private ImageView ivFullScreen;
    private ImageView ivHeaderLeftBack;
    private ImageView ivHeaderRightSetting;
    private ImageView ivVideoViewCover;
    private int maxVolume;
    private RelativeLayout rlTitleBar;
    private RelativeLayout rlVideoViewBottomCtrl;
    private SeekBar sbProgress;
    /* access modifiers changed from: private */
    public SimpleExoPlayer simpleExoPlayer;
    /* access modifiers changed from: private */
    public long storedPlayPosition = 0;
    private int storedState = 0;
    private TextView tvTitleBarTitle;
    /* access modifiers changed from: private */
    public TextView tvVideoEnd;
    /* access modifiers changed from: private */
    public TextView tvVideoStart;
    private String videoM3U8LocalPath = null;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cs_activity_local_exo_player);
        initViews();
    }

    private void initViews() {
        this.rlTitleBar = (RelativeLayout) findViewById(R.id.rlTitleBar);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.rlTitleBar.getLayoutParams());
        layoutParams.setMargins(0, TitleBarUtil.a(), 0, 0);
        this.rlTitleBar.setLayoutParams(layoutParams);
        this.rlTitleBar.bringToFront();
        this.audioManager = (AudioManager) getSystemService("audio");
        this.rlVideoViewBottomCtrl = (RelativeLayout) findViewById(R.id.rlVideoViewBottomCtrl);
        this.ivHeaderLeftBack = (ImageView) findViewById(R.id.ivHeaderLeftBack);
        this.ivHeaderLeftBack.setImageResource(R.drawable.std_tittlebar_main_device_back_white);
        this.ivHeaderLeftBack.setOnClickListener(this);
        this.tvTitleBarTitle = (TextView) findViewById(R.id.tvTitleBarTitle);
        this.tvTitleBarTitle.setVisibility(0);
        this.tvTitleBarTitle.setTextColor(-1);
        this.ivVideoViewCover = (ImageView) findViewById(R.id.ivVideoViewCover);
        this.ivHeaderRightSetting = (ImageView) findViewById(R.id.ivHeaderRightSetting);
        this.ivHeaderRightSetting.setVisibility(8);
        this.ivFullScreen = (ImageView) findViewById(R.id.ivFullScreen);
        this.ivFullScreen.setOnClickListener(this);
        this.cbTogglePlay = (CheckBox) findViewById(R.id.cbTogglePlay);
        this.cbTogglePlay.setOnCheckedChangeListener(this);
        this.cbIsMute = (CheckBox) findViewById(R.id.cbIsMute);
        this.cbIsMute.setOnCheckedChangeListener(this);
        this.tvVideoStart = (TextView) findViewById(R.id.tvVideoStart);
        this.tvVideoEnd = (TextView) findViewById(R.id.tvVideoEnd);
        this.sbProgress = (SeekBar) findViewById(R.id.sbProgress);
        this.sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                CloudVideoLocalExoPlayerActivity.this.mHandler.removeCallbacks(CloudVideoLocalExoPlayerActivity.this.getCurrentPosRunnable);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                CloudVideoLocalExoPlayerActivity.this.seekTo(seekBar.getProgress());
                CloudVideoLocalExoPlayerActivity.this.mHandler.postDelayed(CloudVideoLocalExoPlayerActivity.this.getCurrentPosRunnable, 500);
            }
        });
        this.exoVideoView = (MJExoPlayerViewP) findViewById(R.id.exoVideoView);
        this.simpleExoPlayer = ExoPlayerFactory.newSimpleInstance((Context) this, (TrackSelector) new DefaultTrackSelector((TrackSelection.Factory) new AdaptiveTrackSelection.Factory(new DefaultBandwidthMeter())));
        if (this.audioManager != null) {
            this.maxVolume = this.audioManager.getStreamMaxVolume(1);
            this.currentVolume = this.audioManager.getStreamVolume(1);
            if (this.maxVolume > 0) {
                this.simpleExoPlayer.setVolume(((float) this.currentVolume) / ((float) this.maxVolume));
            }
        }
        this.exoVideoView.setPlayer(this.simpleExoPlayer);
        if (this.simpleExoPlayer != null) {
            this.simpleExoPlayer.addListener(new Player.EventListener() {
                public void onTimelineChanged(Timeline timeline, Object obj, int i) {
                    LogUtil.a(CloudVideoLocalExoPlayerActivity.TAG, "onTimelineChanged:" + i);
                }

                public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
                    LogUtil.a(CloudVideoLocalExoPlayerActivity.TAG, "onTracksChanged");
                }

                public void onLoadingChanged(boolean z) {
                    LogUtil.a(CloudVideoLocalExoPlayerActivity.TAG, "onLoadingChanged:" + z);
                }

                public void onPlayerStateChanged(boolean z, int i) {
                    switch (i) {
                        case 4:
                            CloudVideoLocalExoPlayerActivity.this.simpleExoPlayer.seekTo(0);
                            CloudVideoLocalExoPlayerActivity.this.onCompletion();
                            CloudVideoLocalExoPlayerActivity.this.mHandler.removeCallbacks(CloudVideoLocalExoPlayerActivity.this.getCurrentPosRunnable);
                            break;
                    }
                    LogUtil.a(CloudVideoLocalExoPlayerActivity.TAG, "onPlayerStateChanged:" + z + " playbackState:" + i);
                }

                public void onRepeatModeChanged(int i) {
                    LogUtil.a(CloudVideoLocalExoPlayerActivity.TAG, "onRepeatModeChanged:" + i);
                }

                public void onShuffleModeEnabledChanged(boolean z) {
                    LogUtil.a(CloudVideoLocalExoPlayerActivity.TAG, "onShuffleModeEnabledChanged:" + z);
                }

                public void onPlayerError(ExoPlaybackException exoPlaybackException) {
                    if (exoPlaybackException != null) {
                        LogUtil.a(CloudVideoLocalExoPlayerActivity.TAG, "onPlayerError:" + exoPlaybackException.getLocalizedMessage() + "" + exoPlaybackException.type + " error:" + exoPlaybackException.toString());
                    }
                }

                public void onPositionDiscontinuity(int i) {
                    LogUtil.a(CloudVideoLocalExoPlayerActivity.TAG, "onPositionDiscontinuity:" + i);
                }

                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                    LogUtil.a(CloudVideoLocalExoPlayerActivity.TAG, "onPlaybackParametersChanged:" + playbackParameters.speed);
                }

                public void onSeekProcessed() {
                    LogUtil.a(CloudVideoLocalExoPlayerActivity.TAG, "onSeekProcessed");
                }
            });
        } else {
            LogUtil.b(TAG, "simpleExoPlayer null");
        }
        if (!(this.exoVideoView == null || this.exoVideoView.getVideoSurfaceView() == null)) {
            this.exoVideoView.getVideoSurfaceView().setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CloudVideoLocalExoPlayerActivity.this.toggleVideoCtrlViewTranslation();
                }
            });
        }
        this.exoVideoView.setPlayerViewListener(new MJExoPlayerViewP.PlayerViewListener() {
            public void onVideoSizeChanged(int i, int i2, int i3, float f) {
            }

            public void onRenderedFirstFrame() {
                if (CloudVideoLocalExoPlayerActivity.this.simpleExoPlayer != null) {
                    String longToDate = CloudVideoLocalExoPlayerActivity.this.longToDate(CloudVideoLocalExoPlayerActivity.this.simpleExoPlayer.getDuration() / 1000);
                    TextView access$800 = CloudVideoLocalExoPlayerActivity.this.tvVideoEnd;
                    access$800.setText("" + longToDate);
                    if (CloudVideoLocalExoPlayerActivity.this.cbIsMute.isChecked()) {
                        CloudVideoLocalExoPlayerActivity.this.simpleExoPlayer.setVolume(0.0f);
                    } else {
                        CloudVideoLocalExoPlayerActivity.this.simpleExoPlayer.setVolume(1.0f);
                    }
                }
                CloudVideoLocalExoPlayerActivity.this.mHandler.post(CloudVideoLocalExoPlayerActivity.this.getCurrentPosRunnable);
            }
        });
        this.videoM3U8LocalPath = getIntent().getStringExtra("m3u8LocalPath");
        if (!TextUtils.isEmpty(this.videoM3U8LocalPath) && this.videoM3U8LocalPath.toLowerCase().equals("null")) {
            this.videoM3U8LocalPath = null;
        }
        parseM3U8Folder();
        parseFileName();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.storedState == 2) {
            this.simpleExoPlayer.setPlayWhenReady(true);
            this.mHandler.post(this.getCurrentPosRunnable);
            return;
        }
        if (!TextUtils.isEmpty(this.videoM3U8LocalPath)) {
            this.simpleExoPlayer.prepare(buildM3U8MediaSource(Uri.parse(this.videoM3U8LocalPath)));
        }
        this.simpleExoPlayer.setPlayWhenReady(true);
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
    public void onPause() {
        getWindow().clearFlags(128);
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

    public void onBackPressed() {
        if (getRequestedOrientation() != 1) {
            setOrientation(1);
        } else {
            super.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a(TAG, "orientation:" + configuration.orientation);
        if (configuration.orientation == 1) {
            getWindow().clearFlags(1024);
            this.rlTitleBar.setVisibility(0);
            this.ivFullScreen.setImageResource(R.drawable.cs_player_enter_fullscreen);
            if (this.exoVideoView != null) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.exoVideoView.getLayoutParams());
                layoutParams.height = DisplayUtils.a(210.0f);
                layoutParams.addRule(13, -1);
                this.exoVideoView.setLayoutParams(layoutParams);
                this.exoVideoView.requestLayout();
                return;
            }
            return;
        }
        getWindow().setFlags(1024, 1024);
        this.rlTitleBar.setVisibility(8);
        this.ivFullScreen.setImageResource(R.drawable.cs_player_exit_fullscreen);
        if (this.exoVideoView != null) {
            this.exoVideoView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.exoVideoView.requestLayout();
        }
    }

    private HlsMediaSource buildM3U8MediaSource(Uri uri) {
        return new HlsMediaSource.Factory((DataSource.Factory) new FileDataSourceFactory()).createMediaSource(uri);
    }

    private void parseM3U8Folder() {
        if (!TextUtils.isEmpty(this.videoM3U8LocalPath)) {
            File file = new File(this.videoM3U8LocalPath);
            if (file.exists() && file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    if (file2.getAbsolutePath().endsWith("plain.m3u8")) {
                        this.videoM3U8LocalPath = file2.getAbsolutePath();
                        return;
                    }
                }
            }
        }
    }

    private void parseFileName() {
        String substring = !TextUtils.isEmpty(this.videoM3U8LocalPath) ? this.videoM3U8LocalPath.substring(this.videoM3U8LocalPath.lastIndexOf("/") + 1) : null;
        if (!TextUtils.isEmpty(substring)) {
            this.tvTitleBarTitle.setText(substring);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.ivFullScreen) {
            if (id == R.id.ivHeaderLeftBack) {
                onBackPressed();
            }
        } else if (getRequestedOrientation() == 1) {
            setOrientation(0);
        } else {
            setOrientation(1);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int id = compoundButton.getId();
        if (id != R.id.cbIsMute) {
            if (id == R.id.cbTogglePlay) {
                if (z) {
                    this.simpleExoPlayer.setPlayWhenReady(false);
                    this.mHandler.removeCallbacks(this.getCurrentPosRunnable);
                    this.storedPlayPosition = 0;
                    return;
                }
                this.simpleExoPlayer.setPlayWhenReady(true);
                this.ivVideoViewCover.setVisibility(8);
                this.mHandler.post(this.getCurrentPosRunnable);
                int i = (this.storedPlayPosition > 0 ? 1 : (this.storedPlayPosition == 0 ? 0 : -1));
            }
        } else if (z) {
            this.simpleExoPlayer.setVolume(0.0f);
        } else {
            this.simpleExoPlayer.setVolume(1.0f);
        }
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

    /* access modifiers changed from: private */
    public void setProgressTime(long j) {
        long duration = this.simpleExoPlayer.getDuration();
        if (j >= 0 && duration > 0) {
            int ceil = (int) Math.ceil((double) ((100 * j) / duration));
            if (ceil >= 100 || j / 1000 >= duration / 1000) {
                this.sbProgress.setProgress(100);
            } else {
                this.sbProgress.setProgress(ceil);
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
        }
        this.cbTogglePlay.setChecked(true);
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
    }
}
