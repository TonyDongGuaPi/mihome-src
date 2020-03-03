package com.xiaomi.smarthome.camera.activity.local;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.TrafficStats;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.mijia.camera.CameraPlayerEx;
import com.mijia.camera.MediaRemuxing;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.xiaomi.connection.ByteOperator;
import com.xiaomi.mistream.XmStreamClient;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.IStreamCmdMessageListener;
import com.xiaomi.smarthome.camera.XmMp4Player;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.activity.CameraPlayerBaseActivity;
import com.xiaomi.smarthome.camera.activity.CommandTreatment;
import com.xiaomi.smarthome.camera.activity.timelapse.TCPClient;
import com.xiaomi.smarthome.camera.activity.timelapse.TimeLapseTaskManager;
import com.xiaomi.smarthome.camera.activity.timelapse.Timelapse;
import com.xiaomi.smarthome.camera.activity.timelapse.TimelapseTask;
import com.xiaomi.smarthome.camera.view.RoundProgressBar;
import com.xiaomi.smarthome.constants.AppConstants;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimelapsePhotographPlayActivity extends CameraPlayerBaseActivity implements View.OnClickListener, IStreamCmdMessageListener {
    private static final int CONVERT_END = 26044;
    private static final int CONVERT_ORIGINAL_VIDEO = 26041;
    private static final int CONVERT_START = 26043;
    public static final int DISPLAY_DOWNLOAD_SPEED = 1000;
    private static final int MSG_UPDATE_PROGRESS = 1;
    private static final int MSG_UPDATE_SEEK_PROGRESS = 2;
    /* access modifiers changed from: private */
    public static final String TAG = AlbumActivity.class.getSimpleName();
    private static int fileLength = 0;
    private static long recieveLength = 0;
    private Bitmap bitmapTemp;
    /* access modifiers changed from: private */
    public CheckBox cbTogglePlay;
    private long fileDuration;
    private String fileName = "time_Task_";
    private boolean isDisplay;
    private boolean isDownLoading = false;
    private boolean isSameWifi = false;
    private ImageView ivFullScreen;
    private ImageView iv_download_video;
    private long lastTimeStamp = 0;
    private long lastTotalRxBytes = 0;
    private LinearLayout ll_video_frame;
    private View mBottomViewContainer;
    /* access modifiers changed from: private */
    public TextView mDurationView;
    /* access modifiers changed from: private */
    public boolean mFullScreen;
    private ImageView mFullScreenView;
    private File mLocalFile;
    private View mPauseView;
    /* access modifiers changed from: private */
    public boolean mPlayAutoPause = false;
    private View mPlayView;
    /* access modifiers changed from: private */
    public TextView mPlayingView;
    /* access modifiers changed from: private */
    public SeekBar mProgressBar;
    /* access modifiers changed from: private */
    public View mProgressBarContainer;
    private int mRotation = 0;
    private SeekBar.OnSeekBarChangeListener mSeekBarChange = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (TimelapsePhotographPlayActivity.this.mSeekBarTouched) {
                TimelapsePhotographPlayActivity.this.mHandler.removeMessages(2);
                TimelapsePhotographPlayActivity.this.mPlayingView.setText(TimelapsePhotographPlayActivity.this.mTimeFormat.format(Integer.valueOf(i * 1000)));
                Message obtainMessage = TimelapsePhotographPlayActivity.this.mHandler.obtainMessage();
                obtainMessage.what = 2;
                obtainMessage.arg1 = i;
                obtainMessage.sendToTarget();
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            boolean unused = TimelapsePhotographPlayActivity.this.mSeekBarTouched = true;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            boolean unused = TimelapsePhotographPlayActivity.this.mSeekBarTouched = false;
        }
    };
    /* access modifiers changed from: private */
    public boolean mSeekBarTouched = false;
    /* access modifiers changed from: private */
    public SimpleDateFormat mTimeFormat;
    private TimeLapseTaskManager mTimeLapseTaskManager;
    private View mTopViewContainer;
    /* access modifiers changed from: private */
    public XmMp4Player mVideoPlayerRender;
    /* access modifiers changed from: private */
    public FrameLayout mVideoViewFrame;
    /* access modifiers changed from: private */
    public int mVideoViewFrameHeight;
    private XmStreamClient mXmStreamClient;
    private final String mp4FileName = "Time_Lapse.mp4";
    /* access modifiers changed from: private */
    public File originalMp4File;
    private String phoneSSID;
    /* access modifiers changed from: private */
    public String picStr;
    private int port;
    private RelativeLayout rl_fragment4_2c_timelapse_play_video_screen_download_video;
    private RelativeLayout rl_start_downloading;
    private String rootVideoPath;
    private RoundProgressBar roundprogressbar;
    private long startRealTimeInsec;
    private long startTimeInsec;
    TimerTask task = new TimerTask() {
        public void run() {
            TimelapsePhotographPlayActivity.this.showNetSpeed();
        }
    };
    private Timer timer;
    private FrameLayout title_bar;
    private TextView tv_download_speed;
    private TextView tv_video_size;
    /* access modifiers changed from: private */
    public String videoPath;
    private File videofile;
    private XmVideoViewGl xmVideoViewGl;

    /* access modifiers changed from: protected */
    public void detectSDCard() {
    }

    /* access modifiers changed from: protected */
    public void doResume() {
    }

    public boolean isHistory() {
        return false;
    }

    public void onDisconnectedWithCode(int i) {
    }

    /* access modifiers changed from: protected */
    public void refreshUI() {
    }

    /* access modifiers changed from: protected */
    public void resumeCamera() {
    }

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        ((AudioManager) activity().getApplicationContext().getSystemService("audio")).requestAudioFocus((AudioManager.OnAudioFocusChangeListener) null, 3, 1);
        setContentView(R.layout.camera_activity_timelapse_player);
        this.videoPath = getIntent().getStringExtra("file_path");
        this.startRealTimeInsec = getIntent().getLongExtra("file_realtime", 0);
        this.startTimeInsec = getIntent().getLongExtra("file_time", 0);
        this.rootVideoPath = getIntent().getStringExtra("video_path");
        String str = TAG;
        SDKLog.e(str, " 获取来的 videoPath = " + this.videoPath + ",获取的加上时区的时间 startRealTimeInsec=" + this.startRealTimeInsec + ",rootVideoPath =" + this.rootVideoPath + ",没有加上时区的时间=" + this.startTimeInsec);
        this.mTimeFormat = new SimpleDateFormat("mm:ss");
        initView();
        this.mTimeLapseTaskManager = new TimeLapseTaskManager(this.mCameraDevice, this);
    }

    private void initView() {
        this.title_bar = (FrameLayout) findViewById(R.id.title_bar);
        this.title_bar.bringToFront();
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.rl_start_downloading = (RelativeLayout) findViewById(R.id.rl_start_downloading);
        this.rl_fragment4_2c_timelapse_play_video_screen_download_video = (RelativeLayout) findViewById(R.id.rl_fragment4_2c_timelapse_play_video_screen_download_video);
        this.iv_download_video = (ImageView) findViewById(R.id.iv_download_video);
        this.iv_download_video.setOnClickListener(this);
        this.tv_video_size = (TextView) findViewById(R.id.tv_video_size);
        this.roundprogressbar = (RoundProgressBar) findViewById(R.id.roundprogressbar);
        this.roundprogressbar.setCricleProgressColor(Color.parseColor("#00c598"));
        this.tv_download_speed = (TextView) findViewById(R.id.tv_download_speed);
        this.ll_video_frame = (LinearLayout) findViewById(R.id.ll_video_frame);
        ((TextView) findViewById(R.id.title_bar_title)).setText(Util.a(this.startTimeInsec * 1000));
        String str = TAG;
        SDKLog.e(str, "标题时间戳=" + Util.a(this.startTimeInsec * 1000));
        this.mBottomViewContainer = findViewById(R.id.bottom_tools_container);
        this.mTopViewContainer = findViewById(R.id.top_tools_container);
        this.mProgressBarContainer = findViewById(R.id.progress_bar_container);
        this.mProgressBar = (SeekBar) findViewById(R.id.progress_bar);
        this.mDurationView = (TextView) findViewById(R.id.progress_duration);
        this.mPlayingView = (TextView) findViewById(R.id.progress_playtime);
        this.mProgressBar.setOnSeekBarChangeListener(this.mSeekBarChange);
        this.mVideoViewFrame = (FrameLayout) findViewById(R.id.video_frame);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        FrameLayout frameLayout = new FrameLayout(activity());
        this.mVideoViewFrame.addView(frameLayout, 0, layoutParams);
        this.xmVideoViewGl = XmPluginHostApi.instance().createMp4View(activity(), frameLayout, true);
        this.xmVideoViewGl.setMiniScale(true);
        this.mVideoPlayerRender = this.xmVideoViewGl.getMp4Player();
        this.mVideoPlayerRender.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                TimelapsePhotographPlayActivity.this.runMainThread(new Runnable() {
                    public void run() {
                        TimelapsePhotographPlayActivity.this.mHandler.removeMessages(1);
                        TimelapsePhotographPlayActivity.this.mProgressBar.setProgress(TimelapsePhotographPlayActivity.this.mProgressBar.getMax());
                        TimelapsePhotographPlayActivity.this.mPlayingView.setText(TimelapsePhotographPlayActivity.this.mDurationView.getText());
                        TimelapsePhotographPlayActivity.this.cbTogglePlay.setChecked(true);
                    }
                });
            }
        });
        this.mVideoPlayerRender.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                TimelapsePhotographPlayActivity.this.runMainThread(new Runnable() {
                    public void run() {
                        TimelapsePhotographPlayActivity.this.mHandler.removeMessages(1);
                        TimelapsePhotographPlayActivity.this.mProgressBar.setProgress(TimelapsePhotographPlayActivity.this.mVideoPlayerRender.getCurrentPosition() / 1000);
                        TimelapsePhotographPlayActivity.this.mPlayingView.setText(TimelapsePhotographPlayActivity.this.mTimeFormat.format(Integer.valueOf(TimelapsePhotographPlayActivity.this.mVideoPlayerRender.getCurrentPosition())));
                        TimelapsePhotographPlayActivity.this.cbTogglePlay.setChecked(true);
                    }
                });
                return true;
            }
        });
        this.xmVideoViewGl.setVideoViewListener(new XmVideoViewGl.IVideoViewListener() {
            public void onVideoViewClick() {
                if (TimelapsePhotographPlayActivity.this.mProgressBarContainer.getVisibility() == 0) {
                    AnimationUtils.loadAnimation(TimelapsePhotographPlayActivity.this.activity(), R.anim.slide_out_top).setFillAfter(true);
                    Animation loadAnimation = AnimationUtils.loadAnimation(TimelapsePhotographPlayActivity.this.activity(), R.anim.slide_out_bottom);
                    loadAnimation.setFillAfter(true);
                    TimelapsePhotographPlayActivity.this.mProgressBarContainer.setClickable(false);
                    TimelapsePhotographPlayActivity.this.mProgressBarContainer.setVisibility(8);
                    TimelapsePhotographPlayActivity.this.mProgressBarContainer.startAnimation(loadAnimation);
                    return;
                }
                AnimationUtils.loadAnimation(TimelapsePhotographPlayActivity.this.activity(), R.anim.slide_in_top).setFillAfter(true);
                Animation loadAnimation2 = AnimationUtils.loadAnimation(TimelapsePhotographPlayActivity.this.activity(), R.anim.slide_in_bottom);
                loadAnimation2.setFillAfter(true);
                TimelapsePhotographPlayActivity.this.mProgressBarContainer.setClickable(true);
                TimelapsePhotographPlayActivity.this.mProgressBarContainer.setVisibility(0);
                TimelapsePhotographPlayActivity.this.mProgressBarContainer.startAnimation(loadAnimation2);
            }
        });
        this.mFullScreenView = (ImageView) findViewById(R.id.full_screen);
        this.mFullScreenView.setOnClickListener(this);
        findViewById(R.id.flip).setOnClickListener(this);
        findViewById(R.id.local_share).setOnClickListener(this);
        findViewById(R.id.local_delete).setOnClickListener(this);
        this.xmVideoViewGl.initial();
        this.mVideoPlayerRender.setRenderCallback(new XmMp4Player.RenderCallback() {
            public void onInitialed() {
                TimelapsePhotographPlayActivity.this.runMainThread(new Runnable() {
                    public void run() {
                        TimelapsePhotographPlayActivity.this.startPlay();
                    }
                });
            }
        });
        this.cbTogglePlay = (CheckBox) findViewById(R.id.cbTogglePlay);
        this.cbTogglePlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    TimelapsePhotographPlayActivity.this.pausePlay();
                    TimelapsePhotographPlayActivity.this.cbTogglePlay.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, TimelapsePhotographPlayActivity.this.getResources().getDrawable(R.drawable.home_icon_play02), (Drawable) null);
                    return;
                }
                TimelapsePhotographPlayActivity.this.startPlay();
                TimelapsePhotographPlayActivity.this.cbTogglePlay.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, TimelapsePhotographPlayActivity.this.getResources().getDrawable(R.drawable.home_icon_pause02), (Drawable) null);
            }
        });
        this.ivFullScreen = (ImageView) findViewById(R.id.ivFullScreen);
        this.ivFullScreen.setOnClickListener(this);
    }

    private void initData() {
        String str = TAG;
        SDKLog.e(str, "did=" + this.mCameraDevice.getDid());
        if (this.videoPath == null || !new File(this.videoPath).exists()) {
            LogUtil.a(TAG, "没有下载，显示下载的布局");
            TCPClient.getInstance(activity()).setFlag(true);
            this.rl_fragment4_2c_timelapse_play_video_screen_download_video.setVisibility(0);
            return;
        }
        LogUtil.a(TAG, "视频文件存在走了本地播放");
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                LogUtil.a(TimelapsePhotographPlayActivity.TAG, "延时2s播放");
                TimelapsePhotographPlayActivity.this.mVideoPlayerRender.openVideoPlayer(TimelapsePhotographPlayActivity.this.videoPath);
            }
        }, 2000);
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
            case R.id.iv_download_video:
                judgeownloadVideo();
                return;
            case R.id.local_delete:
                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity());
                builder.a((int) R.string.delete_title);
                builder.a((int) R.string.delete, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TimelapsePhotographPlayActivity.this.deleteTimeLapse();
                    }
                });
                builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
                builder.d();
                return;
            case R.id.local_share:
                SDKLog.e(TAG, "分享 mLocalFile=" + this.mLocalFile);
                if (this.mLocalFile != null) {
                    if (XmPluginHostApi.instance().getApiLevel() >= 29) {
                        openShareVideoActivity(this, "", "", this.mLocalFile.getAbsolutePath(), 17);
                        return;
                    }
                    openSharePictureActivity("", "", this.mLocalFile.getAbsolutePath());
                    return;
                }
                return;
            case R.id.title_bar_return:
                onBackPressed();
                return;
            default:
                return;
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

    private void judgeownloadVideo() {
        if (!this.isDownLoading) {
            this.isDisplay = true;
            refreshPhoneSSID();
            if (!TextUtils.isEmpty(this.phoneSSID) && this.phoneSSID.contains("5G")) {
                LogUtil.a(TAG, "走了5");
                this.phoneSSID = this.phoneSSID.replaceAll(AppConstants.r, "");
                if (this.phoneSSID.contains(this.mCameraDevice.y()) || (!TextUtils.isEmpty(this.mCameraDevice.y()) && this.mCameraDevice.y().contains(this.phoneSSID))) {
                    this.isSameWifi = true;
                } else {
                    this.isSameWifi = false;
                }
            } else if ((TextUtils.isEmpty(this.phoneSSID) || !this.phoneSSID.contains(this.mCameraDevice.y())) && (TextUtils.isEmpty(this.mCameraDevice.y()) || !this.mCameraDevice.y().contains(this.phoneSSID))) {
                this.isSameWifi = false;
            } else {
                this.isSameWifi = true;
            }
            String str = TAG;
            LogUtil.a(str, "摄像头ssid = " + this.mCameraDevice.y() + "   手机ssid = " + this.phoneSSID + "  是否同网=" + this.isSameWifi);
            if (!TextUtils.isEmpty(this.videoPath)) {
                return;
            }
            if (this.isSameWifi) {
                initDownload();
            } else {
                ToastUtil.a((Context) activity(), (int) R.string.timelapse_connect_camera_failed_not_lan);
            }
        }
    }

    private void refreshPhoneSSID() {
        try {
            this.phoneSSID = ((WifiManager) activity().getApplicationContext().getSystemService("wifi")).getConnectionInfo().getSSID().replaceAll("\"", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initDownload() {
        if (this.isDownLoading) {
            SDKLog.c(TAG, "download is already started!");
            return;
        }
        this.rl_start_downloading.setVisibility(0);
        this.lastTotalRxBytes = getTotalRxBytes();
        this.lastTimeStamp = System.currentTimeMillis();
        if (this.timer == null) {
            this.timer = new Timer();
            if (this.task != null) {
                this.timer.schedule(this.task, 1000, 1000);
            }
        }
        getSDTimeLapsePhotographyFile();
    }

    private long getTotalRxBytes() {
        if (TrafficStats.getUidRxBytes(getApplicationInfo().uid) == -1) {
            return 0;
        }
        return TrafficStats.getTotalRxBytes() / 1024;
    }

    /* access modifiers changed from: private */
    public void showNetSpeed() {
        long totalRxBytes = getTotalRxBytes();
        long currentTimeMillis = System.currentTimeMillis();
        this.lastTimeStamp = currentTimeMillis;
        this.lastTotalRxBytes = totalRxBytes;
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1000;
        obtainMessage.obj = String.valueOf(((totalRxBytes - this.lastTotalRxBytes) * 1000) / (currentTimeMillis - this.lastTimeStamp)) + " kb/s";
        this.mHandler.sendMessage(obtainMessage);
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        SDKLog.e("AlbumActivity", "延时摄影详情页 what=" + message.what);
        int i = message.what;
        if (i == 1000) {
            SDKLog.e(TAG, "显示下载速度");
            this.tv_download_speed.setText((String) message.obj);
        } else if (i == 21007) {
            String str = TAG;
            SDKLog.e(str, "延时摄影数据删除成功 msg.arg1=" + message.arg1);
            if (message.arg1 == 1) {
                ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.local_file_delete_success));
                activity().setResult(-1, new Intent());
                finish();
                return;
            }
            LogUtil.a(TAG, "延时摄影删除失败");
            ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.delete_timelapse_failed));
        } else if (i == 21010) {
            SDKLog.e(TAG, "获取新版延时摄影下载");
            if (message.obj != null) {
                byte[] bArr = (byte[]) message.obj;
                byte b = bArr[0];
                this.port = ByteOperator.a(bArr, 1);
                String str2 = TAG;
                SDKLog.e(str2, "result=" + b + ",port=" + this.port);
                if (b == 0 || b == 1 || b == 2) {
                    closeTimeLapsePhotographySever(this.port);
                    UIForCancelDownload();
                    cancleTask();
                    ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.timelapse_downfaile));
                    return;
                }
                String ip = this.mCameraDevice.getIp();
                String str3 = TAG;
                SDKLog.e(str3, "Ip地址=" + ip);
                this.isDownLoading = true;
                TCPClient.getInstance(activity()).createClient(ip, this.port, this.mHandler, this.rootVideoPath, this.startRealTimeInsec);
            }
        } else if (i != CONVERT_ORIGINAL_VIDEO) {
            if (i == 99999) {
                SDKLog.e(TAG, "socket 服务超时");
                closeTimeLapsePhotographySever(this.port);
                UIForCancelDownload();
                ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.socket_client_timeout));
            } else if (i != 210011) {
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
                    default:
                        switch (i) {
                            case 1000000:
                                SDKLog.e(TAG, "socket服务出现问题了，请重试");
                                closeTimeLapsePhotographySever(this.port);
                                UIForCancelDownload();
                                ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.socket_client));
                                return;
                            case 1000001:
                                fileLength = ((Integer) message.obj).intValue();
                                return;
                            case 1000002:
                                this.isDownLoading = true;
                                recieveLength = (long) message.arg1;
                                int i2 = (int) ((recieveLength * 100) / ((long) fileLength));
                                String str4 = TAG;
                                Log.d(str4, "当前的下载的进度=" + i2);
                                this.rl_start_downloading.setVisibility(0);
                                this.roundprogressbar.setProgress(i2);
                                return;
                            case 1000003:
                                SDKLog.e(TAG, "新版延时摄影下载成功");
                                closeTimeLapsePhotographySever(this.port);
                                this.isDownLoading = false;
                                String str5 = (String) message.obj;
                                String str6 = TAG;
                                SDKLog.e(str6, "下载成功的文件路径=" + str5);
                                if (str5 != null) {
                                    convert(str5);
                                    return;
                                }
                                return;
                            case 1000004:
                                closeTimeLapsePhotographySever(this.port);
                                UIForCancelDownload();
                                String str7 = TAG;
                                SDKLog.e(str7, "新版延时摄影下载失败 msg.arg1=" + message.arg1);
                                if (message.arg1 == 1) {
                                    cancleTask();
                                    return;
                                } else {
                                    ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.network_not_stable));
                                    return;
                                }
                            case 1000005:
                                SDKLog.e(TAG, "取消延时摄影下载");
                                closeTimeLapsePhotographySever(this.port);
                                TCPClient.getInstance(activity()).setFlag(true);
                                return;
                            default:
                                return;
                        }
                }
            } else {
                SDKLog.e(TAG, "关闭下载服务");
                if (message.obj != null) {
                    this.isDownLoading = false;
                    byte b2 = ((byte[]) message.obj)[0];
                    String str8 = TAG;
                    SDKLog.e(str8, "result=" + b2);
                    if (b2 == 1) {
                        SDKLog.e(TAG, "关闭服务器成功");
                        TCPClient.getInstance(activity()).closeSocket();
                        return;
                    }
                    SDKLog.e(TAG, "关闭服务器失败");
                }
            }
        } else if (message.arg1 != CONVERT_START && message.arg1 == CONVERT_END) {
            this.rl_start_downloading.setVisibility(8);
            this.rl_fragment4_2c_timelapse_play_video_screen_download_video.setVisibility(8);
            cancleTask();
            this.videoPath = this.originalMp4File.getAbsolutePath();
            startPlay();
            Log.d(TAG, "开始播放了。。。。");
        }
    }

    /* access modifiers changed from: protected */
    public void startPlay() {
        String str = TAG;
        SDKLog.e(str, "startPlay did=" + this.mCameraDevice.getDid());
        if (this.videoPath == null || !new File(this.videoPath).exists()) {
            SDKLog.e(TAG, "没有下载，显示下载的布局");
            TCPClient.getInstance(activity()).setFlag(true);
            this.rl_fragment4_2c_timelapse_play_video_screen_download_video.setVisibility(0);
            this.ll_video_frame.setVisibility(8);
            findViewById(R.id.local_share).setVisibility(8);
            return;
        }
        this.ll_video_frame.setVisibility(0);
        this.mLocalFile = new File(this.videoPath);
        if (this.mLocalFile != null) {
            extractVideoInfo(this.mLocalFile);
            this.mProgressBar.setMax((int) (this.fileDuration / 1000));
            this.mDurationView.setText(this.mTimeFormat.format(new Date(this.fileDuration)));
        }
        SDKLog.e(TAG, "视频文件存在走了本地播放");
        this.mVideoPlayerRender.openVideoPlayer(this.videoPath);
        findViewById(R.id.local_share).setVisibility(0);
        this.mPlayAutoPause = false;
        this.mHandler.sendEmptyMessage(1);
        saveBitmap(this.bitmapTemp, this.videoPath);
    }

    public void getSDTimeLapsePhotographyFile() {
        this.startRealTimeInsec = getIntent().getLongExtra("file_realtime", 0);
        if (this.startRealTimeInsec != 0) {
            this.fileName += this.startRealTimeInsec;
            SDKLog.e(TAG, "延时摄影下载的文件 fileName=" + this.fileName);
            this.mTimeLapseTaskManager.getSDTimeLapsePhotographyFile(1, this.fileName, this);
        }
    }

    public void closeTimeLapsePhotographySever(int i) {
        this.mTimeLapseTaskManager.closeTimeLapsePhotographySever(i, this);
    }

    public void onServerCmd(int i, byte[] bArr) {
        String str = TAG;
        SDKLog.e(str, "延时摄影详情页 CommandTreatment onServerCmd type=" + i);
        new CommandTreatment(this.mHandler, this.mCameraDevice).processData(bArr);
    }

    public void onConnected() {
        super.onConnected();
        SDKLog.e(TAG, "摄像头连接成功，授权+校准时间+延时摄影查询功能");
        this.mCameraPlayerEx.A();
        this.mCameraPlayerEx.z();
        this.mTimeLapseTaskManager.getTimeLapseTask(this);
    }

    private void UIForCancelDownload() {
        this.rl_start_downloading.setVisibility(8);
        this.rl_fragment4_2c_timelapse_play_video_screen_download_video.setVisibility(0);
    }

    private void cancleTask() {
        if (this.task != null) {
            this.task.cancel();
            this.task = null;
        }
        if (this.timer != null) {
            this.timer.cancel();
            this.timer = null;
        }
    }

    private void convert(final String str) {
        findViewById(R.id.local_share).setVisibility(0);
        String str2 = TAG;
        Log.d(str2, "convert...filePath=" + str);
        this.originalMp4File = new File(this.rootVideoPath + "/" + "Time_Lapse.mp4");
        String str3 = TAG;
        Log.d(str3, "originalMp4File=" + this.originalMp4File);
        new File(str);
        new Thread() {
            public void run() {
                TimelapsePhotographPlayActivity.this.mHandler.obtainMessage(TimelapsePhotographPlayActivity.CONVERT_ORIGINAL_VIDEO, TimelapsePhotographPlayActivity.CONVERT_START, -1).sendToTarget();
                MediaRemuxing.remuxing(TimelapsePhotographPlayActivity.this.activity(), str, TimelapsePhotographPlayActivity.this.originalMp4File.getAbsolutePath());
                TimelapsePhotographPlayActivity.this.mHandler.obtainMessage(TimelapsePhotographPlayActivity.CONVERT_ORIGINAL_VIDEO, TimelapsePhotographPlayActivity.CONVERT_END, -1).sendToTarget();
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public void setFullScreen(boolean z) {
        this.mFullScreen = z;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (i > i2) {
            i = displayMetrics.heightPixels;
            i2 = displayMetrics.widthPixels;
        }
        if (this.mFullScreen) {
            setRequestedOrientation(6);
            this.mFullScreenView.setImageResource(R.drawable.home_icon_mixscreen);
            findViewById(R.id.title_bar).setVisibility(8);
            this.xmVideoViewGl.setVideoFrameSize(i2, i, true);
            this.mBottomViewContainer.setVisibility(8);
            this.mTopViewContainer.setPadding(0, getResources().getDimensionPixelOffset(R.dimen.full_screen_top_bar_padding), 0, 0);
            return;
        }
        this.mFullScreenView.setImageResource(R.drawable.home_icon_fullscreen);
        this.mTopViewContainer.setPadding(0, 0, 0, 0);
        if (this.mVideoViewFrameHeight == 0) {
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    int unused = TimelapsePhotographPlayActivity.this.mVideoViewFrameHeight = TimelapsePhotographPlayActivity.this.mVideoViewFrame.getHeight();
                    TimelapsePhotographPlayActivity.this.setFullScreen(TimelapsePhotographPlayActivity.this.mFullScreen);
                }
            }, 300);
            return;
        }
        setRequestedOrientation(1);
        findViewById(R.id.title_bar).setVisibility(0);
        this.xmVideoViewGl.setVideoFrameSize(i, this.mVideoViewFrameHeight, false);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setWindowNew(configuration);
    }

    private void setWindowNew(Configuration configuration) {
        if (configuration.orientation != 1) {
            getWindow().setFlags(1024, 1024);
            this.title_bar.setVisibility(8);
            this.mBottomViewContainer.setVisibility(8);
            this.ivFullScreen.setImageResource(R.drawable.home_icon_mixscreen);
            this.xmVideoViewGl.setVideoFrameSize(-1, -1, true);
            return;
        }
        getWindow().clearFlags(1024);
        this.title_bar.setVisibility(0);
        this.title_bar.bringToFront();
        this.mBottomViewContainer.setVisibility(0);
        this.ivFullScreen.setImageResource(R.drawable.home_icon_fullscreen);
        this.xmVideoViewGl.setVideoFrameSize(-1, -1, false);
    }

    public void onBackPressed() {
        String str = TAG;
        SDKLog.e(str, "onBackPressed isDownLoading=" + this.isDownLoading);
        if (1 != getRequestedOrientation()) {
            setOrientation(false);
        } else if (this.isDownLoading) {
            cancleDownLoad();
        } else {
            finish();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeMessages(1);
        if (this.xmVideoViewGl != null) {
            this.xmVideoViewGl.release();
        }
        SDKLog.e(TAG, "延时摄像详情页 onDestroy");
    }

    /* access modifiers changed from: private */
    public void pausePlay() {
        this.mVideoPlayerRender.pause();
    }

    public void onPause() {
        super.onPause();
        this.mCameraPlayerEx.w();
        this.mCameraPlayerEx = null;
        if (this.mVideoPlayerRender != null && this.mVideoPlayerRender.isPlaying()) {
            pausePlay();
            this.mPlayAutoPause = true;
        }
    }

    public void onResume() {
        super.onResume();
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (TimelapsePhotographPlayActivity.this.mPlayAutoPause) {
                    TimelapsePhotographPlayActivity.this.startPlay();
                }
            }
        }, 500);
        if (this.mCameraPlayerEx == null) {
            this.mCameraPlayerEx = new CameraPlayerEx(this.mCameraDevice, this);
        }
    }

    private void extractVideoInfo(File file) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
            String str = TAG;
            SDKLog.e(str, "extractVideoInfo localFile=" + file.getAbsolutePath());
            String extractMetadata = mediaMetadataRetriever.extractMetadata(9);
            this.bitmapTemp = mediaMetadataRetriever.getFrameAtTime();
            if (!TextUtils.isEmpty(extractMetadata)) {
                this.fileDuration = Long.valueOf(extractMetadata).longValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            mediaMetadataRetriever.release();
            throw th;
        }
        mediaMetadataRetriever.release();
    }

    /* access modifiers changed from: private */
    public void deleteTimeLapse() {
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        String str = TAG;
        SDKLog.e(str, " 删除Timelapse：" + this.startTimeInsec);
        File deleTimeLapseFolder = deleTimeLapseFolder();
        if (deleTimeLapseFolder != null) {
            String str2 = TAG;
            SDKLog.e(str2, "deletFolderPath =" + deleTimeLapseFolder.getAbsolutePath());
        }
        TimelapseTask timeLapseFromList = Timelapse.getTimeLapseFromList(this.startTimeInsec);
        if (timeLapseFromList != null) {
            timeLapseFromList.deleteLocalData(deleTimeLapseFolder);
        }
        arrayList.add(new TimelapseTask(3, (int) (this.startTimeInsec + ((long) Util.d()))));
        if (arrayList.size() > 0) {
            SDKLog.c(TAG, "=====================================0");
            SDKLog.e(TAG, "发送了获取延时摄影列表的命令");
            this.mTimeLapseTaskManager.deleteRecord(arrayList, this);
        }
    }

    private File deleTimeLapseFolder() {
        if (this.rootVideoPath == null || !this.rootVideoPath.contains("video")) {
            return null;
        }
        String[] split = this.rootVideoPath.split("/video");
        if (split.length > 0) {
            return new File(split[0]);
        }
        return null;
    }

    private void cancleDownLoad() {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity());
        builder.a((int) R.string.cancel_download);
        builder.b((int) R.string.confirm_cancel_download);
        builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                TCPClient.getInstance(TimelapsePhotographPlayActivity.this.activity()).setFlag(false);
                TimelapsePhotographPlayActivity.this.finish();
            }
        });
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.d();
    }

    /* access modifiers changed from: private */
    public boolean findPathPic(String str) {
        if (str != null) {
            try {
                String[] split = str.split("video");
                if (split != null && split.length > 0) {
                    String str2 = split[0];
                    String str3 = TAG;
                    LogUtil.a(str3, "findPathPic===" + str2);
                    this.picStr = str2 + "pic/";
                    String str4 = TAG;
                    LogUtil.a(str4, "findPathPic picFile===" + this.picStr);
                    String[] list = new File(this.picStr).list();
                    if (list == null || list.length <= 0) {
                        return false;
                    }
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void saveBitmap(final Bitmap bitmap, final String str) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    boolean unused = TimelapsePhotographPlayActivity.this.findPathPic(str);
                    if (bitmap != null) {
                        String access$800 = TimelapsePhotographPlayActivity.TAG;
                        SDKLog.e(access$800, "file ==" + TimelapsePhotographPlayActivity.this.picStr);
                        File file = new File(TimelapsePhotographPlayActivity.this.picStr);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        String access$2000 = TimelapsePhotographPlayActivity.this.picStr;
                        File file2 = new File(access$2000, System.currentTimeMillis() + ".jpg");
                        String access$8002 = TimelapsePhotographPlayActivity.TAG;
                        SDKLog.e(access$8002, "file ==" + file2.getAbsolutePath());
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                        fileOutputStream.close();
                    }
                    LogUtil.a(TimelapsePhotographPlayActivity.TAG, "保存图片成功");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void onSendCmdError() {
        SDKLog.e(TAG, "onSendCmdError");
        ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.operation_failed));
    }
}
