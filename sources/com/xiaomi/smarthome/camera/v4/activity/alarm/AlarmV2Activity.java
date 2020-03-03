package com.xiaomi.smarthome.camera.v4.activity.alarm;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
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
import com.mi.global.bbs.manager.Region;
import com.mijia.app.AppConfig;
import com.mijia.app.Event;
import com.mijia.camera.Utils.Util;
import com.mijia.model.alarm.AlarmItem;
import com.mijia.model.alarm.AlarmManager;
import com.mijia.model.alarm.AlarmNetUtils;
import com.mijia.model.alarm.FDSFileManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.XmMp4Player;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.activity.local.LocalAudioPlay;
import com.xiaomi.smarthome.camera.v4.view.CustomPullDownRefreshListView;
import com.xiaomi.smarthome.camera.view.widget.LoadMoreView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.fastvideo.VideoFrameDecoder;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.framework.plugin.mpk.PluginHostApiImpl;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import com.xiaomi.stat.d;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmV2Activity extends CameraBaseActivity implements View.OnClickListener {
    static final int MSG_SAVE_FAILURE = 15;
    static final int MSG_SAVE_START = 16;
    static final int MSG_SAVE_STOP = 17;
    static final int MSG_SAVE_SUCCESS = 14;
    static final int MSG_UPDATE_PROGRESS = 12;
    static final int MSG_UPDATE_SEEK_PROGRESS = 13;
    private static final int REQUEST_CODE_GOTO_ALARM_DAY = 1001;
    private static final int REQUEST_CODE_SHARE = 1;
    final int MSG_DOWN_FILE = 11;
    /* access modifiers changed from: private */
    public String currentPlayingFilePath = null;
    private boolean isCreate = true;
    /* access modifiers changed from: private */
    public boolean isMute = true;
    private ImageView ivSave;
    /* access modifiers changed from: private */
    public AlarmAdapterV2 mAdapter;
    /* access modifiers changed from: private */
    public AlarmItem mAlarmItem;
    /* access modifiers changed from: private */
    public SimpleDateFormat mAlarmTimeFormat;
    /* access modifiers changed from: private */
    public TextView mAlarmTimeView;
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
            if (downloadItem.f8040a.b == AlarmV2Activity.this.mAlarmItem.b) {
                AlarmV2Activity.this.runMainThread(new Runnable() {
                    public void run() {
                        AlarmV2Activity.this.showProgress(i);
                        String unused = AlarmV2Activity.this.currentPlayingFilePath = null;
                    }
                });
            }
        }

        public void onDownloadSuccess(FDSFileManager.DownloadItem downloadItem, final String str) {
            if (downloadItem.f8040a.b == AlarmV2Activity.this.mAlarmItem.b) {
                AlarmV2Activity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        AlarmV2Activity.this.hideProgress();
                        AlarmV2Activity.this.mPauseBtn.setEnabled(true);
                        String unused = AlarmV2Activity.this.currentPlayingFilePath = str;
                        AlarmV2Activity.this.startPlay(str);
                    }
                });
            }
        }

        public void onDownloadFailed(FDSFileManager.DownloadItem downloadItem, String str, int i, String str2) {
            if (downloadItem.f8040a.b == AlarmV2Activity.this.mAlarmItem.b) {
                AlarmV2Activity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(AlarmV2Activity.this, R.string.sdcard_video_download_failed, 0).show();
                        AlarmV2Activity.this.mPauseBtn.setVisibility(8);
                        AlarmV2Activity.this.mPlayBtn.setVisibility(0);
                        String unused = AlarmV2Activity.this.currentPlayingFilePath = null;
                        AlarmV2Activity.this.hideProgress();
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
    private SharePrefHelper mSharePrefHelper;
    private View mShareView;
    /* access modifiers changed from: private */
    public SimpleDateFormat mTimeFormat;
    /* access modifiers changed from: private */
    public XmMp4Player mVideoPlayerRender;
    private TextView mVideoProgress;
    /* access modifiers changed from: private */
    public View mVideoTool;
    /* access modifiers changed from: private */
    public XmVideoViewGl mVideoView;
    private FrameLayout mVideoViewFrame;
    private FrameLayout title_bar;
    /* access modifiers changed from: private */
    public TextView tvCloudStorageHint;
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
                if (this.mCameraDevice == null) {
                    return;
                }
                if (!this.mAlarmItem.l) {
                    this.mCameraDevice.k().a(this.mOnDownloadListener);
                    this.mCameraDevice.k().c(this.mAlarmItem);
                    return;
                } else if (this.mCameraDevice != null) {
                    this.mHandler.sendEmptyMessage(16);
                    this.mCameraDevice.i().a((Context) this, this.mAlarmItem, (AlarmManager.IAlarmCallback<String>) new AlarmManager.IAlarmCallback<String>() {
                        public void onSuccess(String str, Object obj) {
                            if (!AlarmV2Activity.this.isFinishing()) {
                                AlarmV2Activity.this.mHandler.sendEmptyMessage(17);
                                if (!TextUtils.isEmpty(str)) {
                                    AlarmV2Activity.this.mPauseBtn.setEnabled(true);
                                    String unused = AlarmV2Activity.this.currentPlayingFilePath = str;
                                    AlarmV2Activity.this.startPlay(str);
                                }
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (!AlarmV2Activity.this.isFinishing()) {
                                Toast.makeText(AlarmV2Activity.this, R.string.sdcard_video_download_failed, 0).show();
                                AlarmV2Activity.this.mPauseBtn.setVisibility(8);
                                AlarmV2Activity.this.mPlayBtn.setVisibility(0);
                                String unused = AlarmV2Activity.this.currentPlayingFilePath = null;
                                AlarmV2Activity.this.mHandler.sendEmptyMessage(17);
                            }
                        }
                    });
                    return;
                } else {
                    return;
                }
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

    public void doCreate(Bundle bundle) {
        Event.a(Event.aK);
        super.doCreate(bundle);
        this.mPullingDlg = new XQProgressDialog(this);
        this.mPullingDlg.setCancelable(true);
        this.mPullingDlg.setMessage(getResources().getString(R.string.loading));
        setContentView(R.layout.activity_alarm);
        this.mTimeFormat = new SimpleDateFormat("mm:ss");
        String language = XmPluginHostApi.instance().getSettingLocale() != null ? XmPluginHostApi.instance().getSettingLocale().getLanguage() : null;
        if (XmPluginHostApi.instance().getApiLevel() < 51 || (!"de".equalsIgnoreCase(language) && !d.u.equalsIgnoreCase(language) && !"fr".equalsIgnoreCase(language) && !"it".equalsIgnoreCase(language) && !d.U.equalsIgnoreCase(language) && !Region.RU.equalsIgnoreCase(language))) {
            this.mAlarmTimeFormat = new SimpleDateFormat("yyy-MM-dd");
        } else {
            this.mAlarmTimeFormat = new SimpleDateFormat("dd-MM-yyyy");
        }
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
        if (this.mCameraDevice.i().g() < 0) {
            if (this.mCameraDevice.e().k() > 0) {
                this.mCameraDevice.i().b(this.mCameraDevice.e().k());
            } else {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("did", this.mCameraDevice.getDid());
                    jSONObject.put("region", Locale.getDefault().getCountry());
                    this.mCameraDevice.i().a(this.mCameraDevice.getModel(), jSONObject, (AlarmManager.IAlarmCallback) new AlarmManager.IAlarmCallback() {
                        public void onFailure(int i, String str) {
                        }

                        public void onSuccess(Object obj, Object obj2) {
                            AlarmV2Activity.this.mCameraDevice.e().a(AlarmV2Activity.this.mCameraDevice.i().f().h);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
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
        this.mAlarmTimeView = (TextView) findViewById(R.id.alarm_list_date);
        getResources().getDisplayMetrics();
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
                AlarmV2Activity.this.mPauseBtn.setEnabled(false);
                AlarmV2Activity.this.mHandler.sendEmptyMessage(11);
                AlarmV2Activity.this.mPlayBtn.setVisibility(8);
                AlarmV2Activity.this.mPauseBtn.setVisibility(0);
            }
        });
        this.mScreenFull = (ImageView) findViewById(R.id.btn_alert_main_full_screen);
        this.mScreenFull.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlarmV2Activity.this.setFullScreen(!AlarmV2Activity.this.mFullScreen);
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
        this.mAdapter = new AlarmAdapterV2(this, this.mDid);
        this.mLoadMoreView = (LoadMoreView) getLayoutInflater().inflate(R.layout.load_more_data, (ViewGroup) null);
        this.mMediaListView.addFooterView(this.mLoadMoreView);
        this.mMediaListView.setAdapter(this.mAdapter);
        this.mMediaListView.setRefreshListener(new CustomPullDownRefreshListView.OnRefreshListener() {
            public void startRefresh() {
                AlarmV2Activity.this.loadData(true, false);
            }
        });
        this.mMediaListView.setOnLastItemVisibleListener(new CustomPullDownRefreshListView.OnLastItemVisibleListener() {
            public void onLastItemVisible() {
                if (AlarmV2Activity.this.mHasMore && !AlarmV2Activity.this.mIsMoreLoading) {
                    AlarmV2Activity.this.loadMoreData();
                }
            }
        });
        this.videoMute = (ImageView) findViewById(R.id.video_mute);
        this.videoMute.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Event.a(Event.aL);
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
        this.ivSave = (ImageView) findViewById(R.id.ivSave);
        this.ivSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlarmV2Activity.this.saveMediaFile();
            }
        });
        this.mReplayLayout = (FrameLayout) findViewById(R.id.replay_layout);
        findViewById(R.id.replay_btn).setOnClickListener(this);
        this.mMediaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int i2 = i - 1;
                if (i2 >= 0 && i2 < AlarmV2Activity.this.mAdapter.getCount()) {
                    AlarmItem unused = AlarmV2Activity.this.mAlarmItem = AlarmV2Activity.this.mAdapter.getItem(i2);
                    AlarmV2Activity.this.mAlarmTimeView.setText(AlarmV2Activity.this.mAlarmTimeFormat.format(Long.valueOf(AlarmV2Activity.this.mAlarmItem.b)));
                    if (AlarmV2Activity.this.mVideoView != null && AlarmV2Activity.this.mVideoPlayerRender.isPlaying()) {
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
        this.tvCloudStorageHint = (TextView) findViewById(R.id.tvCloudStorageHint);
    }

    /* access modifiers changed from: private */
    public void loadData(boolean z, final boolean z2) {
        this.mCameraDevice.i().c(new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!AlarmV2Activity.this.isFinishing()) {
                    boolean unused = AlarmV2Activity.this.mHasMore = AlarmV2Activity.this.mCameraDevice.i().b();
                    if (AlarmV2Activity.this.mHasMore) {
                        AlarmV2Activity.this.mLoadMoreView.setLoadingData();
                    } else {
                        AlarmV2Activity.this.mLoadMoreView.setLoadingNone();
                    }
                    if (AlarmV2Activity.this.mPullingDlg != null && AlarmV2Activity.this.mPullingDlg.isShowing()) {
                        AlarmV2Activity.this.mPullingDlg.dismiss();
                    }
                    if (AlarmV2Activity.this.mMediaListView.isRefreshing()) {
                        AlarmV2Activity.this.mMediaListView.postRefresh();
                    }
                    final List<AlarmItem> c = AlarmV2Activity.this.mCameraDevice.i().c();
                    if (!c.isEmpty()) {
                        AlarmV2Activity.this.mAdapter.setData(c, AlarmV2Activity.this.mCameraDevice.getDid());
                        AlarmV2Activity.this.showEmptyView(false);
                        AlarmItem unused2 = AlarmV2Activity.this.mAlarmItem = c.get(0);
                        AlarmV2Activity.this.mAlarmTimeView.setText(AlarmV2Activity.this.mAlarmTimeFormat.format(Long.valueOf(AlarmV2Activity.this.mAlarmItem.b)));
                        if (ImageLoader.a().b()) {
                            if (AlarmV2Activity.this.mAlarmItem.l) {
                                ImageLoader.a().a(AlarmV2Activity.this.mAlarmItem.r, AlarmV2Activity.this.mPlayBg);
                            } else {
                                ImageLoader.a().a(AlarmV2Activity.this.mAlarmItem.a(), AlarmV2Activity.this.mPlayBg);
                            }
                        }
                        if (z2) {
                            AlarmV2Activity.this.mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    AlarmItem unused = AlarmV2Activity.this.mAlarmItem = (AlarmItem) c.get(0);
                                    if (AlarmV2Activity.this.mVideoPlayerRender.isPlaying()) {
                                        AlarmV2Activity.this.mVideoPlayerRender.pause();
                                        AlarmV2Activity.this.mVideoPlayerRender.seekTo(0);
                                    }
                                    AlarmV2Activity.this.mHandler.removeMessages(12);
                                    AlarmV2Activity.this.mHandler.sendEmptyMessage(11);
                                    AlarmV2Activity.this.mPauseBtn.setVisibility(0);
                                    AlarmV2Activity.this.mPlayBtn.setVisibility(8);
                                }
                            }, 500);
                            return;
                        }
                        return;
                    }
                    AlarmV2Activity.this.showEmptyView(true);
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
        this.mCameraDevice.i().c(new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!AlarmV2Activity.this.isFinishing()) {
                    boolean unused = AlarmV2Activity.this.mIsMoreLoading = false;
                    boolean unused2 = AlarmV2Activity.this.mHasMore = AlarmV2Activity.this.mCameraDevice.i().b();
                    if (AlarmV2Activity.this.mHasMore) {
                        AlarmV2Activity.this.mLoadMoreView.setLoadingData();
                    } else {
                        AlarmV2Activity.this.mLoadMoreView.setLoadingNone();
                    }
                    AlarmV2Activity.this.mAdapter.setData(AlarmV2Activity.this.mCameraDevice.i().c(), AlarmV2Activity.this.mCameraDevice.getDid());
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
                Event.a(Event.aO);
                if (AlarmNetUtils.e()) {
                    startActivityForResult(new Intent(this, AlarmDayV2Activity.class), 1001);
                    return;
                } else {
                    startActivity(new Intent(this, AlarmDayActivity.class));
                    return;
                }
            case R.id.replay_btn:
                this.mPlayBtn.performClick();
                return;
            case R.id.title_bar_more:
                Event.a(Event.aR);
                if (AlarmNetUtils.e()) {
                    startActivity(new Intent(this, AlarmSettingV2Activity.class));
                    return;
                } else {
                    startActivity(new Intent(this, AlarmSettingActivity.class));
                    return;
                }
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
                    shareVideo();
                    return;
                }
            default:
                return;
        }
    }

    private void shareVideo() {
        Event.a(Event.aP);
        if (!TextUtils.isEmpty(this.currentPlayingFilePath)) {
            final String a2 = FileUtil.a(true, this.mCameraDevice.getDid());
            if (!TextUtils.isEmpty(a2)) {
                new Thread(new Runnable() {
                    /* JADX WARNING: Removed duplicated region for block: B:33:0x008f A[SYNTHETIC, Splitter:B:33:0x008f] */
                    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b0 A[SYNTHETIC, Splitter:B:38:0x00b0] */
                    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d3 A[SYNTHETIC, Splitter:B:45:0x00d3] */
                    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f4 A[SYNTHETIC, Splitter:B:50:0x00f4] */
                    /* JADX WARNING: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                            r8 = this;
                            r0 = 0
                            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x006f, all -> 0x006a }
                            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmV2Activity r2 = com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmV2Activity.this     // Catch:{ IOException -> 0x006f, all -> 0x006a }
                            java.lang.String r2 = r2.currentPlayingFilePath     // Catch:{ IOException -> 0x006f, all -> 0x006a }
                            r1.<init>(r2)     // Catch:{ IOException -> 0x006f, all -> 0x006a }
                            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0065, all -> 0x005f }
                            java.lang.String r3 = r0     // Catch:{ IOException -> 0x0065, all -> 0x005f }
                            r2.<init>(r3)     // Catch:{ IOException -> 0x0065, all -> 0x005f }
                            r0 = 1024(0x400, float:1.435E-42)
                            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x005d }
                        L_0x0017:
                            int r3 = r1.read(r0)     // Catch:{ IOException -> 0x005d }
                            r4 = -1
                            if (r3 == r4) goto L_0x0023
                            r4 = 0
                            r2.write(r0, r4, r3)     // Catch:{ IOException -> 0x005d }
                            goto L_0x0017
                        L_0x0023:
                            java.lang.String r0 = r0     // Catch:{ IOException -> 0x005d }
                            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmV2Activity r3 = com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmV2Activity.this     // Catch:{ IOException -> 0x005d }
                            java.lang.String r4 = ""
                            java.lang.String r5 = ""
                            r6 = 1
                            com.xiaomi.smarthome.framework.page.PictureShareActivity.share(r3, r4, r5, r0, r6)     // Catch:{ IOException -> 0x005d }
                            r1.close()     // Catch:{ IOException -> 0x0033 }
                            goto L_0x004e
                        L_0x0033:
                            r0 = move-exception
                            java.lang.String r1 = "alarm"
                            java.lang.StringBuilder r3 = new java.lang.StringBuilder
                            r3.<init>()
                            java.lang.String r4 = ""
                            r3.append(r4)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r3.append(r0)
                            java.lang.String r0 = r3.toString()
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r1, r0)
                        L_0x004e:
                            r2.close()     // Catch:{ IOException -> 0x0053 }
                            goto L_0x00cf
                        L_0x0053:
                            r0 = move-exception
                            java.lang.String r1 = "alarm"
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder
                            r2.<init>()
                            goto L_0x00bc
                        L_0x005d:
                            r0 = move-exception
                            goto L_0x0073
                        L_0x005f:
                            r2 = move-exception
                            r7 = r2
                            r2 = r0
                            r0 = r7
                            goto L_0x00d1
                        L_0x0065:
                            r2 = move-exception
                            r7 = r2
                            r2 = r0
                            r0 = r7
                            goto L_0x0073
                        L_0x006a:
                            r1 = move-exception
                            r2 = r0
                            r0 = r1
                            r1 = r2
                            goto L_0x00d1
                        L_0x006f:
                            r1 = move-exception
                            r2 = r0
                            r0 = r1
                            r1 = r2
                        L_0x0073:
                            java.lang.String r3 = "alarm"
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d0 }
                            r4.<init>()     // Catch:{ all -> 0x00d0 }
                            java.lang.String r5 = ""
                            r4.append(r5)     // Catch:{ all -> 0x00d0 }
                            java.lang.String r0 = r0.getLocalizedMessage()     // Catch:{ all -> 0x00d0 }
                            r4.append(r0)     // Catch:{ all -> 0x00d0 }
                            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x00d0 }
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r3, r0)     // Catch:{ all -> 0x00d0 }
                            if (r1 == 0) goto L_0x00ae
                            r1.close()     // Catch:{ IOException -> 0x0093 }
                            goto L_0x00ae
                        L_0x0093:
                            r0 = move-exception
                            java.lang.String r1 = "alarm"
                            java.lang.StringBuilder r3 = new java.lang.StringBuilder
                            r3.<init>()
                            java.lang.String r4 = ""
                            r3.append(r4)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r3.append(r0)
                            java.lang.String r0 = r3.toString()
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r1, r0)
                        L_0x00ae:
                            if (r2 == 0) goto L_0x00cf
                            r2.close()     // Catch:{ IOException -> 0x00b4 }
                            goto L_0x00cf
                        L_0x00b4:
                            r0 = move-exception
                            java.lang.String r1 = "alarm"
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder
                            r2.<init>()
                        L_0x00bc:
                            java.lang.String r3 = ""
                            r2.append(r3)
                            java.lang.String r0 = r0.getLocalizedMessage()
                            r2.append(r0)
                            java.lang.String r0 = r2.toString()
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r1, r0)
                        L_0x00cf:
                            return
                        L_0x00d0:
                            r0 = move-exception
                        L_0x00d1:
                            if (r1 == 0) goto L_0x00f2
                            r1.close()     // Catch:{ IOException -> 0x00d7 }
                            goto L_0x00f2
                        L_0x00d7:
                            r1 = move-exception
                            java.lang.String r3 = "alarm"
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder
                            r4.<init>()
                            java.lang.String r5 = ""
                            r4.append(r5)
                            java.lang.String r1 = r1.getLocalizedMessage()
                            r4.append(r1)
                            java.lang.String r1 = r4.toString()
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r3, r1)
                        L_0x00f2:
                            if (r2 == 0) goto L_0x0113
                            r2.close()     // Catch:{ IOException -> 0x00f8 }
                            goto L_0x0113
                        L_0x00f8:
                            r1 = move-exception
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder
                            r2.<init>()
                            java.lang.String r3 = ""
                            r2.append(r3)
                            java.lang.String r1 = r1.getLocalizedMessage()
                            r2.append(r1)
                            java.lang.String r1 = r2.toString()
                            java.lang.String r2 = "alarm"
                            com.xiaomi.smarthome.framework.log.LogUtil.b(r2, r1)
                        L_0x0113:
                            throw r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmV2Activity.AnonymousClass13.run():void");
                    }
                }).start();
                return;
            }
            return;
        }
        this.mHandler.sendEmptyMessage(15);
    }

    public void onResume() {
        super.onResume();
        if (this.isCreate) {
            loadData(this.isCreate, true);
        }
        this.isCreate = false;
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
        if (this.mVideoPlayerRender != null && (this.mVideoPlayerRender instanceof VideoFrameDecoder)) {
            ((VideoFrameDecoder) this.mVideoPlayerRender).f();
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
            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmAdapterV2 r0 = r5.mAdapter
            com.mijia.model.alarm.AlarmItem r1 = r5.mAlarmItem
            r0.addDownSuccess(r1)
            android.widget.ImageView r0 = r5.mPlayBg
            r1 = 8
            r0.setVisibility(r1)
            java.lang.String r0 = r5.mCurrentPath
            boolean r0 = r0.equals(r6)
            r1 = 0
            if (r0 != 0) goto L_0x008e
            com.xiaomi.smarthome.camera.XmMp4Player r0 = r5.mVideoPlayerRender
            r0.changeSource(r6)
            boolean r0 = r5.isMute
            if (r0 == 0) goto L_0x0085
            com.xiaomi.smarthome.camera.XmMp4Player r0 = r5.mVideoPlayerRender
            r0.setVolume(r1)
            goto L_0x008b
        L_0x0085:
            com.xiaomi.smarthome.camera.XmMp4Player r0 = r5.mVideoPlayerRender
            r2 = 1
            r0.setVolume(r2)
        L_0x008b:
            r5.mCurrentPath = r6
            goto L_0x0093
        L_0x008e:
            com.xiaomi.smarthome.camera.XmMp4Player r0 = r5.mVideoPlayerRender
            r0.openVideoPlayer(r6)
        L_0x0093:
            android.view.View r6 = r5.mShareView
            r6.setVisibility(r1)
            android.os.Handler r6 = r5.mHandler
            r0 = 12
            r6.sendEmptyMessage(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmV2Activity.startPlay(java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public void setFullScreen(boolean z) {
        Event.a(Event.aN);
        this.mFullScreen = z;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
            int i = displayMetrics.heightPixels;
            int i2 = displayMetrics.widthPixels;
        }
        if (this.mFullScreen) {
            setRequestedOrientation(6);
        } else {
            setRequestedOrientation(1);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration != null) {
            if (configuration.orientation == 1) {
                this.mScreenFull.setImageResource(R.drawable.home_icon_fullscreen);
                findViewById(R.id.title_bar).setVisibility(0);
                this.mMediaListView.setVisibility(0);
                this.mHistoryLayout.setVisibility(0);
                ViewGroup.LayoutParams layoutParams = this.mVideoViewFrame.getLayoutParams();
                layoutParams.width = -1;
                layoutParams.height = dip2px(this, 203.0f);
                this.mVideoViewFrame.setLayoutParams(layoutParams);
                this.mVideoView.setVideoFrameSize(-1, -1, false);
                return;
            }
            findViewById(R.id.title_bar).setVisibility(8);
            this.mScreenFull.setImageResource(R.drawable.home_icon_mixscreen);
            this.mEmptyLayout.setVisibility(8);
            this.mMediaListView.setVisibility(8);
            this.mHistoryLayout.setVisibility(8);
            ViewGroup.LayoutParams layoutParams2 = this.mVideoViewFrame.getLayoutParams();
            layoutParams2.height = -1;
            layoutParams2.width = -1;
            this.mVideoViewFrame.setLayoutParams(layoutParams2);
            this.mVideoView.setVideoFrameSize(-1, -1, true);
        }
    }

    public void onBackPressed() {
        if (this.mFullScreen) {
            runOnUiThread(new Runnable() {
                public void run() {
                    AlarmV2Activity.this.setFullScreen(false);
                }
            });
        } else {
            super.onBackPressed();
        }
    }

    private void initVideoView() {
        this.mVideoTool = findViewById(R.id.layout_video_tools);
        this.mVideoViewFrame = (FrameLayout) findViewById(R.id.video_frame);
        this.mVideoViewFrame.getLayoutParams().height = (AppConfig.b * 9) / 16;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        FrameLayout frameLayout = new FrameLayout(activity());
        this.mVideoView = XmPluginHostApi.instance().createMp4View(activity(), frameLayout, !Util.c.contains(Build.MODEL));
        this.mVideoView.setMiniScale(true);
        this.mVideoViewFrame.addView(frameLayout, 0, layoutParams);
        this.mVideoView.initial();
        this.mVideoView.setVideoViewListener(new XmVideoViewGl.IVideoViewListener() {
            public void onVideoViewClick() {
                if (AlarmV2Activity.this.mVideoTool.getVisibility() == 8) {
                    AlarmV2Activity.this.mVideoTool.setVisibility(0);
                } else {
                    AlarmV2Activity.this.mVideoTool.setVisibility(8);
                }
            }
        });
        this.mVideoPlayerRender = this.mVideoView.getMp4Player();
        this.mVideoPlayerRender.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                AlarmV2Activity.this.runMainThread(new Runnable() {
                    public void run() {
                        AlarmV2Activity.this.setCloudStorageHint();
                        AlarmV2Activity.this.mPauseBtn.setEnabled(true);
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
                        AlarmV2Activity.this.setCloudStorageHint();
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
                        if (AlarmV2Activity.this.tvCloudStorageHint != null) {
                            AlarmV2Activity.this.tvCloudStorageHint.setVisibility(8);
                        }
                        AlarmV2Activity.this.mProgressBar.setMax(AlarmV2Activity.this.mVideoPlayerRender.getDuration() / 1000);
                        AlarmV2Activity.this.mProgressBar.setProgress(0);
                        AlarmV2Activity.this.mProgressBar.setVisibility(0);
                        AlarmV2Activity.this.mPlayingView.setText("00:00");
                        AlarmV2Activity.this.mDurationView.setText(AlarmV2Activity.this.mTimeFormat.format(Integer.valueOf(AlarmV2Activity.this.mVideoPlayerRender.getDuration())));
                        if (AlarmV2Activity.this.mReplayLayout.getVisibility() == 0) {
                            AlarmV2Activity.this.mReplayLayout.setVisibility(8);
                        }
                        AlarmV2Activity.this.mPauseBtn.setVisibility(0);
                        AlarmV2Activity.this.mPlayBtn.setVisibility(8);
                        if (AlarmV2Activity.this.isMute) {
                            AlarmV2Activity.this.mVideoPlayerRender.setVolume(0);
                        } else {
                            AlarmV2Activity.this.mVideoPlayerRender.setVolume(1);
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
        Event.a(Event.aM);
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
                            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmV2Activity r2 = com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmV2Activity.this     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            android.os.Handler r2 = r2.mHandler     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            r3 = 16
                            r2.sendEmptyMessage(r3)     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0080, all -> 0x007d }
                            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmV2Activity r3 = com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmV2Activity.this     // Catch:{ IOException -> 0x0080, all -> 0x007d }
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
                            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmV2Activity r0 = com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmV2Activity.this     // Catch:{ IOException -> 0x006b, all -> 0x0065 }
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
                            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmV2Activity r0 = com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmV2Activity.this
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
                            com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmV2Activity r0 = com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmV2Activity.this
                            android.os.Handler r0 = r0.mHandler
                            r0.sendEmptyMessage(r1)
                            throw r2
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmV2Activity.AnonymousClass21.run():void");
                    }
                }).start();
                return;
            }
            return;
        }
        this.mHandler.sendEmptyMessage(15);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001 && i2 == -1) {
            loadData(true, false);
        }
        if (i == 1 && i2 == -1) {
            Event.a(Event.aQ);
        }
    }

    /* access modifiers changed from: private */
    public void setCloudStorageHint() {
        if (!Util.b()) {
            return;
        }
        if (this.mCameraDevice == null || !this.mCameraDevice.isShared()) {
            this.tvCloudStorageHint.setVisibility(0);
            if (this.mCameraDevice.e().f()) {
                this.tvCloudStorageHint.setText(R.string.alarm_cloud_buy_tip);
            } else {
                this.tvCloudStorageHint.setText(R.string.alarm_cloud_no_buy_tip);
            }
            this.tvCloudStorageHint.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    try {
                        if (AlarmV2Activity.this.mCameraDevice.e().f()) {
                            FrameManager.b().k().openCloudVideoListActivity(AlarmV2Activity.this, AlarmV2Activity.this.mCameraDevice.getDid(), AlarmV2Activity.this.mCameraDevice.getName());
                        } else {
                            CloudVideoNetUtils.getInstance().openCloudVideoBuyPage(AlarmV2Activity.this, AlarmV2Activity.this.mCameraDevice.getDid());
                        }
                    } catch (Exception unused) {
                    }
                    Event.a(Event.bH);
                }
            });
        }
    }
}
