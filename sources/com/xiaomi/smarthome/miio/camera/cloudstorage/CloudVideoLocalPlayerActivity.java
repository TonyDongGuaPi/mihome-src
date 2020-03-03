package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.tencent.smtt.sdk.TbsReaderView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.ijkpackage.IjkVideoView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class CloudVideoLocalPlayerActivity extends CloudVideoBaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "CloudVideoLocalPlayerActivity";
    /* access modifiers changed from: private */
    public CheckBox cbIsMute;
    private CheckBox cbTogglePlay;
    /* access modifiers changed from: private */
    public int currentProgress = 0;
    /* access modifiers changed from: private */
    public int duration;
    /* access modifiers changed from: private */
    public Runnable getCurrentPosRunnable = new Runnable() {
        public void run() {
            if (CloudVideoLocalPlayerActivity.this.ijkVideoViewLocal != null) {
                if (CloudVideoLocalPlayerActivity.this.ijkVideoViewLocal.getDuration() > 0) {
                    int unused = CloudVideoLocalPlayerActivity.this.duration = CloudVideoLocalPlayerActivity.this.ijkVideoViewLocal.getDuration() / 1000;
                }
                final int currentPosition = CloudVideoLocalPlayerActivity.this.ijkVideoViewLocal.getCurrentPosition();
                CloudVideoLocalPlayerActivity.this.setProgressTime(currentPosition);
                CloudVideoLocalPlayerActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        int duration = CloudVideoLocalPlayerActivity.this.ijkVideoViewLocal.getDuration() / 1000;
                        if (currentPosition / 1000 >= duration) {
                            TextView access$1400 = CloudVideoLocalPlayerActivity.this.tvVideoStart;
                            access$1400.setText("" + CloudVideoLocalPlayerActivity.this.longToDate((long) duration));
                            return;
                        }
                        TextView access$14002 = CloudVideoLocalPlayerActivity.this.tvVideoStart;
                        access$14002.setText("" + CloudVideoLocalPlayerActivity.this.longToDate((long) (currentPosition / 1000)));
                    }
                });
            }
            CloudVideoLocalPlayerActivity.this.mHandler.postDelayed(CloudVideoLocalPlayerActivity.this.getCurrentPosRunnable, 500);
        }
    };
    private IjkVideoView.IjkVideoInfo ijkVideoInfoLocal = new IjkVideoView.IjkVideoInfo() {
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
        }

        public void onRenderViewClicked(View view) {
            CloudVideoLocalPlayerActivity.this.toggleVideoCtrlViewTranslation();
        }

        public void onSurfaceChanged(int i, int i2) {
            CloudVideoLocalPlayerActivity.this.rlVideoViewBottomCtrl.bringToFront();
        }

        public void onSurfaceCreated(int i, int i2) {
            CloudVideoLocalPlayerActivity.this.rlVideoViewBottomCtrl.bringToFront();
        }

        public void onStartPlay() {
            CloudVideoLocalPlayerActivity.this.rlVideoViewBottomCtrl.bringToFront();
            CloudVideoLocalPlayerActivity.this.takeImageBg();
            CloudVideoLocalPlayerActivity.this.ivVideoViewCover.setVisibility(8);
        }

        public void onPausePlay() {
            CloudVideoLocalPlayerActivity.this.setImageBg();
            CloudVideoLocalPlayerActivity.this.ivVideoViewCover.setVisibility(0);
            CloudVideoLocalPlayerActivity.this.ivVideoViewCover.bringToFront();
            CloudVideoLocalPlayerActivity.this.rlVideoViewBottomCtrl.bringToFront();
        }

        public void onStopPlayback() {
            CloudVideoLocalPlayerActivity.this.ivVideoViewCover.setVisibility(0);
            CloudVideoLocalPlayerActivity.this.ivVideoViewCover.bringToFront();
            CloudVideoLocalPlayerActivity.this.rlVideoViewBottomCtrl.bringToFront();
        }
    };
    /* access modifiers changed from: private */
    public IjkVideoView ijkVideoViewLocal;
    private ImageView ivFullScreen;
    private ImageView ivHeaderLeftBack;
    private ImageView ivHeaderRightSetting;
    /* access modifiers changed from: private */
    public ImageView ivVideoViewCover;
    private RelativeLayout rlTitleBar;
    /* access modifiers changed from: private */
    public RelativeLayout rlVideoViewBottomCtrl;
    /* access modifiers changed from: private */
    public SeekBar sbProgress;
    private int storedState = 0;
    private TextView tvTitleBarTitle;
    /* access modifiers changed from: private */
    public TextView tvVideoEnd;
    /* access modifiers changed from: private */
    public TextView tvVideoStart;
    private String videoFilePath = null;
    private String videoM3U8LocalPath = null;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cs_activity_local_player);
        initViews();
    }

    private void initViews() {
        this.rlTitleBar = (RelativeLayout) findViewById(R.id.rlTitleBar);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.rlTitleBar.getLayoutParams());
        layoutParams.setMargins(0, TitleBarUtil.a(), 0, 0);
        this.rlTitleBar.setLayoutParams(layoutParams);
        this.rlTitleBar.bringToFront();
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
        this.cbIsMute.setChecked(true);
        this.tvVideoStart = (TextView) findViewById(R.id.tvVideoStart);
        this.tvVideoEnd = (TextView) findViewById(R.id.tvVideoEnd);
        this.sbProgress = (SeekBar) findViewById(R.id.sbProgress);
        this.sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z) {
                    int unused = CloudVideoLocalPlayerActivity.this.currentProgress = CloudVideoLocalPlayerActivity.this.transProgress2(i) * 1000;
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                CloudVideoLocalPlayerActivity.this.mHandler.removeCallbacks(CloudVideoLocalPlayerActivity.this.getCurrentPosRunnable);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                CloudVideoLocalPlayerActivity.this.ijkVideoViewLocal.seekTo(CloudVideoLocalPlayerActivity.this.currentProgress);
                CloudVideoLocalPlayerActivity.this.mHandler.postDelayed(CloudVideoLocalPlayerActivity.this.getCurrentPosRunnable, 1000);
            }
        });
        this.ijkVideoViewLocal = (IjkVideoView) findViewById(R.id.ijkVideoViewLocal);
        this.ijkVideoViewLocal.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            public void onPrepared(final IMediaPlayer iMediaPlayer) {
                CloudVideoLocalPlayerActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        if (iMediaPlayer != null) {
                            LogUtil.a(CloudVideoLocalPlayerActivity.TAG, "duration:" + iMediaPlayer.getDuration());
                            int unused = CloudVideoLocalPlayerActivity.this.duration = ((int) iMediaPlayer.getDuration()) / 1000;
                            TextView access$1000 = CloudVideoLocalPlayerActivity.this.tvVideoEnd;
                            access$1000.setText("" + CloudVideoLocalPlayerActivity.this.longToDate((long) CloudVideoLocalPlayerActivity.this.duration));
                        }
                    }
                });
            }
        });
        this.ijkVideoViewLocal.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                CloudVideoLocalPlayerActivity.this.mHandler.removeCallbacks(CloudVideoLocalPlayerActivity.this.getCurrentPosRunnable);
                CloudVideoLocalPlayerActivity.this.sbProgress.setProgress(100);
            }
        });
        this.ijkVideoViewLocal.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                CloudVideoLocalPlayerActivity.this.mHandler.removeCallbacks(CloudVideoLocalPlayerActivity.this.getCurrentPosRunnable);
                return false;
            }
        });
        this.ijkVideoViewLocal.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                if (i == 3) {
                    CloudVideoLocalPlayerActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            CloudVideoLocalPlayerActivity.this.hideLoading();
                            CloudVideoLocalPlayerActivity.this.ijkVideoViewLocal.mute(CloudVideoLocalPlayerActivity.this.cbIsMute.isChecked());
                            CloudVideoLocalPlayerActivity.this.mHandler.removeCallbacks(CloudVideoLocalPlayerActivity.this.getCurrentPosRunnable);
                            CloudVideoLocalPlayerActivity.this.mHandler.post(CloudVideoLocalPlayerActivity.this.getCurrentPosRunnable);
                        }
                    });
                    LogUtil.a(CloudVideoLocalPlayerActivity.TAG, "MEDIA_INFO_VIDEO_RENDERING_START");
                    return false;
                } else if (i != 10002) {
                    switch (i) {
                        case 701:
                            LogUtil.a(CloudVideoLocalPlayerActivity.TAG, "MEDIA_INFO_BUFFERING_START");
                            return false;
                        case 702:
                            LogUtil.a(CloudVideoLocalPlayerActivity.TAG, "MEDIA_INFO_BUFFERING_END");
                            return false;
                        default:
                            return false;
                    }
                } else {
                    CloudVideoLocalPlayerActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            CloudVideoLocalPlayerActivity.this.mHandler.removeCallbacks(CloudVideoLocalPlayerActivity.this.getCurrentPosRunnable);
                            CloudVideoLocalPlayerActivity.this.mHandler.post(CloudVideoLocalPlayerActivity.this.getCurrentPosRunnable);
                            CloudVideoLocalPlayerActivity.this.hideLoading();
                            CloudVideoLocalPlayerActivity.this.ijkVideoViewLocal.mute(CloudVideoLocalPlayerActivity.this.cbIsMute.isChecked());
                        }
                    });
                    LogUtil.a(CloudVideoLocalPlayerActivity.TAG, "MEDIA_INFO_AUDIO_RENDERING_START");
                    return false;
                }
            }
        });
        this.ijkVideoViewLocal.ijkVideoInfo = this.ijkVideoInfoLocal;
        this.videoFilePath = getIntent().getStringExtra(TbsReaderView.KEY_FILE_PATH);
        this.videoM3U8LocalPath = getIntent().getStringExtra("m3u8LocalPath");
        parseM3U8Folder();
        parseFileName();
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
        String substring = !TextUtils.isEmpty(this.videoFilePath) ? this.videoFilePath.substring(this.videoFilePath.lastIndexOf("/") + 1) : null;
        if (!TextUtils.isEmpty(this.videoM3U8LocalPath)) {
            substring = this.videoM3U8LocalPath.substring(this.videoM3U8LocalPath.lastIndexOf("/") + 1);
        }
        if (!TextUtils.isEmpty(substring)) {
            this.tvTitleBarTitle.setText(substring);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getWindow().setFlags(128, 128);
        if (this.ijkVideoViewLocal != null) {
            setImageBg();
            if (this.storedState == 3) {
                this.ijkVideoViewLocal.start();
            } else if (this.storedState != 4) {
                startPlay();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        getWindow().clearFlags(128);
        if (this.ijkVideoViewLocal != null) {
            this.storedState = this.ijkVideoViewLocal.getState();
            this.ijkVideoViewLocal.pause();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        stopPlay();
        super.onDestroy();
    }

    public void onBackPressed() {
        if (getRequestedOrientation() != 1) {
            setRequestedOrientation(1);
        } else {
            super.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.ijkVideoViewLocal != null) {
            this.ijkVideoViewLocal.setRender(this.ijkVideoViewLocal.mCurrentRender);
        }
        if (configuration.orientation == 1) {
            this.rlTitleBar.setVisibility(0);
            if (this.ijkVideoViewLocal != null) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.ijkVideoViewLocal.getLayoutParams());
                layoutParams.height = DisplayUtils.a(210.0f);
                layoutParams.addRule(15);
                this.ijkVideoViewLocal.setLayoutParams(layoutParams);
                this.ijkVideoViewLocal.requestLayout();
                return;
            }
            return;
        }
        this.rlTitleBar.setVisibility(8);
        if (this.ijkVideoViewLocal != null) {
            this.ijkVideoViewLocal.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.ijkVideoViewLocal.requestLayout();
        }
    }

    private void startPlay() {
        String str = !TextUtils.isEmpty(this.videoM3U8LocalPath) ? this.videoM3U8LocalPath : this.videoFilePath;
        LogUtil.a(TAG, "filePath:" + str);
        if (!TextUtils.isEmpty(str) && this.ijkVideoViewLocal != null) {
            this.ijkVideoViewLocal.setVideoPath(str);
            this.ijkVideoViewLocal.start();
            this.mHandler.post(this.getCurrentPosRunnable);
        }
    }

    private void stopPlay() {
        this.mHandler.removeCallbacks(this.getCurrentPosRunnable);
        if (this.ijkVideoViewLocal != null) {
            this.ijkVideoViewLocal.stopPlayback();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.ivFullScreen) {
            if (id == R.id.ivHeaderLeftBack) {
                onBackPressed();
            }
        } else if (getRequestedOrientation() != 1) {
            setRequestedOrientation(1);
        } else {
            setRequestedOrientation(0);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int id = compoundButton.getId();
        if (id != R.id.cbIsMute) {
            if (id != R.id.cbTogglePlay || this.ijkVideoViewLocal == null) {
                return;
            }
            if (z) {
                this.ijkVideoViewLocal.pause();
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        CloudVideoLocalPlayerActivity.this.takeImageBg();
                    }
                }, 100);
                return;
            }
            this.ijkVideoViewLocal.start();
        } else if (this.ijkVideoViewLocal == null) {
        } else {
            if (z) {
                this.ijkVideoViewLocal.mute(true);
            } else {
                this.ijkVideoViewLocal.mute(false);
            }
        }
    }

    /* access modifiers changed from: private */
    public void setProgressTime(int i) {
        int duration2 = this.ijkVideoViewLocal.getDuration();
        if (i >= 0 && this.ijkVideoViewLocal != null && duration2 > 0) {
            int ceil = (int) Math.ceil((double) ((i * 100) / duration2));
            if (ceil > 100 || i / 1000 >= duration2 / 1000) {
                this.sbProgress.setProgress(100);
            } else {
                this.sbProgress.setProgress(ceil);
            }
        }
    }

    public String longToDate(long j) {
        if (j < 0) {
            return "";
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

    private int transProgress1(int i) {
        if (this.duration > 0) {
            return (i * 100) / this.duration;
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public int transProgress2(int i) {
        int i2 = (i * this.duration) / 100;
        LogUtil.a(TAG, "transProgress2:" + i2);
        return i2;
    }

    /* access modifiers changed from: private */
    public void toggleVideoCtrlViewTranslation() {
        if (this.rlVideoViewBottomCtrl.getTranslationY() <= 0.0f) {
            this.rlVideoViewBottomCtrl.setTranslationY((float) this.rlVideoViewBottomCtrl.getHeight());
        } else {
            this.rlVideoViewBottomCtrl.setTranslationY(0.0f);
        }
    }

    /* access modifiers changed from: private */
    public void setImageBg() {
        String str = getFilesDir() + File.separator + "local_player" + File.separator + "cache";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        String str2 = str + File.separator + "coverPic.png";
        if (new File(str2).exists()) {
            this.ivVideoViewCover.setImageBitmap(BitmapFactory.decodeFile(str2));
        }
    }

    /* access modifiers changed from: private */
    public void takeImageBg() {
        Bitmap snapshot;
        String str = getFilesDir() + File.separator + "local_player" + File.separator + "cache";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        String str2 = str + File.separator + "coverPic.png";
        File file2 = new File(str2);
        if (this.ijkVideoViewLocal != null && (snapshot = this.ijkVideoViewLocal.snapshot()) != null) {
            try {
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                snapshot.compress(Bitmap.CompressFormat.PNG, 0, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException unused) {
                this.ivVideoViewCover.setImageResource(R.drawable.set_bg_02_visual_nor);
            }
        }
    }
}
