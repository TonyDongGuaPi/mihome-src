package com.xiaomi.smarthome.camera.activity.sdcard;

import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.Utils.FileUtil;
import com.Utils.NetworkUtil;
import com.Utils.TimeUtils;
import com.mijia.app.Event;
import com.mijia.camera.CameraPlayer;
import com.mijia.camera.CameraProperties;
import com.mijia.camera.Utils.BitmapUtils;
import com.mijia.camera.Utils.CameraOperationUtils;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.mijia.model.local.LocalFileManager;
import com.mijia.model.sdcard.DownloadSdCardManager;
import com.mijia.model.sdcard.SdcardManager;
import com.mijia.model.sdcard.TimeItem;
import com.mijia.model.sdcard.TimeItemDay;
import com.mijia.model.sdcard.TimeItemHour;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.XmMp4Record;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.activity.CameraPlayerBaseActivity;
import com.xiaomi.smarthome.camera.activity.local.LocalPicReviewActivity;
import com.xiaomi.smarthome.camera.activity.local.LocalVideoPlayActivity;
import com.xiaomi.smarthome.camera.view.TextViewS;
import com.xiaomi.smarthome.camera.view.recycle.RecyclerClickListener;
import com.xiaomi.smarthome.camera.view.widget.CenterDrawableCheckBox;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class SDCardPlayerActivity extends CameraPlayerBaseActivity implements DownloadSdCardManager.OnDownloadListener, RecyclerClickListener {
    static final int MSG_UPDATE_SEEK_PROGRESS = 1001;
    private static final String TAG = "SDCardPlayerActivity";
    private int UPDATE_DURATION = 500;
    private CenterDrawableCheckBox cdcToggleAudio;
    private CenterDrawableCheckBox cdcTogglePlay;
    private ImageView ivCameraShot;
    private ImageView ivFullScreen;
    /* access modifiers changed from: private */
    public LinearLayout llFuncPopup;
    private LinearLayout llSelectBottom;
    /* access modifiers changed from: private */
    public LinearLayout llVideoViewBottomCtrl;
    private SdcardManager mCameraSdcardFileManager;
    /* access modifiers changed from: private */
    public int mCurrentTime = 0;
    /* access modifiers changed from: private */
    public Date mDate;
    private int mDay;
    private DownloadSdCardManager mDownloadSdCardFileManager;
    private int mDuration;
    private int mEndTime;
    private FrameLayout mFLTitleBar;
    private int mHour;
    private CameraPlayer.IRecordTimeListener mIRecodeTimeListener = new CameraPlayer.IRecordTimeListener() {
        public void upDateTime(int i) {
            int access$2100 = SDCardPlayerActivity.this.mCurrentTime / 1000;
            int access$2200 = SDCardPlayerActivity.this.mLastTime != 0 ? i - SDCardPlayerActivity.this.mLastTime : 50;
            if (access$2200 <= 0 || access$2200 >= 900) {
                int unused = SDCardPlayerActivity.this.mCurrentTime = SDCardPlayerActivity.this.mCurrentTime + 50;
            } else {
                int unused2 = SDCardPlayerActivity.this.mCurrentTime = SDCardPlayerActivity.this.mCurrentTime + access$2200;
            }
            int unused3 = SDCardPlayerActivity.this.mLastTime = i;
            if (SDCardPlayerActivity.this.mCurrentTime / 1000 != access$2100) {
                SDCardPlayerActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        int access$2100 = SDCardPlayerActivity.this.mCurrentTime / 1000;
                        if (access$2100 > 1) {
                            SDCardPlayerActivity.this.upDateTimeTitle((access$2100 - 1) * 1000);
                        }
                    }
                });
            }
        }
    };
    private boolean mIsSetTime = false;
    private int mLastOffsetTime = 0;
    private long mLastSetTime = 0;
    /* access modifiers changed from: private */
    public int mLastTime = 0;
    private long mLastUpdateTime = 0;
    HashSet<Long> mNeedCheckList = new HashSet<>();
    private boolean mNeedSetTime = false;
    /* access modifiers changed from: private */
    public SeekBar mProgress;
    private XQProgressDialog mProgressDialog;
    private TextView mProgressEnd;
    /* access modifiers changed from: private */
    public TextView mProgressStart;
    public boolean mSeekBarTouched = false;
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("mm:ss");
    private int mStartTime;
    /* access modifiers changed from: private */
    public TimeItem mTimeItem;
    List<TimeItemData> mTimeItemDataList = new ArrayList();
    List<TimeItem> mTimeItemList = new ArrayList();
    private TextView mTimeUpdateView;
    private RecyclerView rvHistoryVideoList;
    /* access modifiers changed from: private */
    public SDCardHourAdapter sdCardVideoAdapter;
    private SimpleDateFormat sdf;
    private SimpleDateFormat sdfHHmm;
    private SimpleDateFormat sdfMMdd;
    private ImageView title_bar_more;
    private ImageView title_bar_return;
    private TextView tvBack;
    /* access modifiers changed from: private */
    public TextView tvRecordTimer;
    /* access modifiers changed from: private */
    public TextView tvSelectAll;
    private TextView tvSelectDelete;
    private TextView tvSelectSave;
    private TextViewS tvsMultiSpeed;

    /* access modifiers changed from: protected */
    public void detectSDCard() {
    }

    public boolean isHistory() {
        return true;
    }

    public void onRecyclerLongClick(View view) {
    }

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        SDKLog.b(TAG, "onCreate");
        setContentView(R.layout.camera_activity_camera_sdcard_player);
        this.mFLTitleBar = (FrameLayout) findViewById(R.id.title_bar);
        this.mFLTitleBar.setBackgroundResource(R.drawable.camera_shape_gradient_bg);
        this.title_bar_return = (ImageView) findViewById(R.id.title_bar_return);
        this.title_bar_return.setOnClickListener(this);
        this.title_bar_more = (ImageView) findViewById(R.id.title_bar_more);
        this.title_bar_more.setImageResource(R.drawable.camera_title_edit_drawable_white);
        this.title_bar_more.setOnClickListener(this);
        this.mFLTitleBar.bringToFront();
        this.mDay = getIntent().getExtras().getInt("mDay");
        this.mHour = getIntent().getExtras().getInt("mHour");
        this.mTimeItem = (TimeItem) getIntent().getExtras().getParcelable("time");
        if (this.mTimeItem == null) {
            ToastUtil.a((Context) activity(), (int) R.string.loading_failed);
            finish();
        }
        this.mStartTime = (int) (this.mTimeItem.f8098a / 1000);
        this.mEndTime = (int) (this.mTimeItem.c / 1000);
        this.mDuration = (int) (this.mTimeItem.b / 1000);
        this.mDate = new Date();
        initView();
        startPlay();
        if (this.mCameraPlayer != null) {
            this.mCameraPlayer.b(true);
        }
        this.mCameraSdcardFileManager = this.mCameraDevice.c();
        this.mDownloadSdCardFileManager = this.mCameraDevice.h();
        this.sdfMMdd = new SimpleDateFormat(getString(R.string.simple_date_format_mm_dd));
        this.sdfMMdd.setTimeZone(TimeUtils.a());
        this.sdfHHmm = new SimpleDateFormat(getString(R.string.simple_date_format_hh_mm));
        this.sdfHHmm.setTimeZone(TimeUtils.a());
        TextView textView = this.mTitleView;
        textView.setText("" + (this.sdfHHmm.format(Long.valueOf(this.mTimeItem.f8098a)) + "-" + this.sdfHHmm.format(Long.valueOf(this.mTimeItem.c))));
        loadData();
        Event.a(Event.aU);
    }

    private void initView() {
        this.llSelectBottom = (LinearLayout) findViewById(R.id.llSelectBottom);
        this.tvSelectSave = (TextView) findViewById(R.id.tvSelectSave);
        this.tvSelectSave.setOnClickListener(this);
        this.tvSelectDelete = (TextView) findViewById(R.id.tvSelectDelete);
        this.tvSelectDelete.setOnClickListener(this);
        this.ivCameraShot = (ImageView) findViewById(R.id.ivCameraShot);
        this.ivCameraShot.setOnClickListener(this);
        this.tvRecordTimer = (TextView) findViewById(R.id.tvRecordTimer);
        this.llVideoViewBottomCtrl = (LinearLayout) findViewById(R.id.llVideoViewBottomCtrl);
        this.tvsMultiSpeed = (TextViewS) findViewById(R.id.tvsMultiSpeed);
        this.tvsMultiSpeed.setOnClickListener(this);
        if (this.mCameraPlayer != null) {
            TextViewS textViewS = this.tvsMultiSpeed;
            textViewS.setText("" + this.mCameraPlayer.q() + "X");
        } else {
            this.tvsMultiSpeed.setText("1X");
        }
        this.cdcToggleAudio = (CenterDrawableCheckBox) findViewById(R.id.cdcToggleAudio);
        this.cdcToggleAudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.aV);
                SDKLog.b(SDCardPlayerActivity.TAG, "cdcToggleAudio isChecked:" + z);
                if (SDCardPlayerActivity.this.mCameraPlayer == null) {
                    return;
                }
                if (z) {
                    SDCardPlayerActivity.this.mCameraPlayer.b(false);
                } else {
                    SDCardPlayerActivity.this.mCameraPlayer.b(true);
                }
            }
        });
        this.cdcTogglePlay = (CenterDrawableCheckBox) findViewById(R.id.cdcTogglePlay);
        this.cdcTogglePlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LogUtil.a(SDCardPlayerActivity.TAG, "cdcTogglePlay isChecked:" + z);
                if (SDCardPlayerActivity.this.mCameraPlayer == null) {
                    return;
                }
                if (z) {
                    SDCardPlayerActivity.this.mCameraPlayer.g();
                    boolean unused = SDCardPlayerActivity.this.mUserPause = true;
                    return;
                }
                SDCardPlayerActivity.this.setPlayTime(SDCardPlayerActivity.this.mProgress.getProgress());
            }
        });
        this.ivFullScreen = (ImageView) findViewById(R.id.ivFullScreen);
        this.ivFullScreen.setOnClickListener(this);
        this.llFuncPopup = (LinearLayout) findViewById(R.id.llFuncPopup);
        this.llFuncPopup.setOnClickListener(this);
        this.mTimeUpdateView = (TextView) findViewById(R.id.time_container_center);
        this.mTitleView = (TextView) findViewById(R.id.title_bar_title);
        this.mTitleView.setText(R.string.record_files_sdcard_title);
        this.mTitleView.setTextColor(getResources().getColor(R.color.white));
        this.mVideoViewFrame = (FrameLayout) findViewById(R.id.video_frame);
        this.mVideoLayout = new FrameLayout(activity());
        this.mVideoViewFrame.addView(this.mVideoLayout, 0, new FrameLayout.LayoutParams(-1, -1));
        this.mVideoView = XmPluginHostApi.instance().createVideoView(activity(), this.mVideoLayout, true, 1);
        this.mLoadingView = LayoutInflater.from(this).inflate(R.layout.camera_progress, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.mVideoLayout.addView(this.mLoadingView, layoutParams);
        this.mLoadingView.setVisibility(8);
        this.mLoadingProgress = (TextView) this.mLoadingView.findViewById(R.id.loading_progress);
        this.mLoadingTitle = (TextView) this.mLoadingView.findViewById(R.id.loading_title);
        this.mLoadingImageView = (ImageView) this.mLoadingView.findViewById(R.id.loading_anim);
        this.mLoadingAnimation = (AnimationDrawable) getResources().getDrawable(R.drawable.camera_anim_camera_loading);
        this.mLoadingImageView.setImageDrawable(this.mLoadingAnimation);
        if (this.isV4) {
            this.mErrorRetryView = LayoutInflater.from(this).inflate(R.layout.camera_v4_error_retry, (ViewGroup) null);
        } else {
            this.mErrorRetryView = LayoutInflater.from(this).inflate(R.layout.camera_error_retry, (ViewGroup) null);
        }
        this.mVideoLayout.addView(this.mErrorRetryView, layoutParams);
        this.mErrorRetryView.setVisibility(8);
        this.mRetryView = this.mErrorRetryView.findViewById(R.id.retry_btn);
        this.mErrorInfoView = (TextView) this.mErrorRetryView.findViewById(R.id.error_info);
        if (this.isV4) {
            this.mPowerOffView = LayoutInflater.from(this).inflate(R.layout.camera_closed_v4, (ViewGroup) null);
        } else {
            this.mPowerOffView = LayoutInflater.from(this).inflate(R.layout.camera_closed, (ViewGroup) null);
        }
        this.mVideoLayout.addView(this.mPowerOffView, layoutParams);
        this.mPowerOffView.setVisibility(8);
        this.mVideoView.setVideoViewListener(new XmVideoViewGl.IVideoViewListener() {
            public void onVideoViewClick() {
                if (SDCardPlayerActivity.this.llVideoViewBottomCtrl.getTranslationY() > 0.0f) {
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(SDCardPlayerActivity.this.llVideoViewBottomCtrl, "translationY", new float[]{(float) SDCardPlayerActivity.this.llVideoViewBottomCtrl.getHeight(), 0.0f});
                    ofFloat.setDuration(200);
                    ofFloat.start();
                    return;
                }
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(SDCardPlayerActivity.this.llVideoViewBottomCtrl, "translationY", new float[]{0.0f, (float) SDCardPlayerActivity.this.llVideoViewBottomCtrl.getHeight()});
                ofFloat2.setDuration(200);
                ofFloat2.start();
            }
        });
        this.mPauseView = findViewById(R.id.pause_view);
        this.mCameraDevice.g().a(this.mPropertyChangeListener);
        this.mCameraDevice.g().a();
        this.mProgressStart = (TextView) findViewById(R.id.progress_playtime);
        this.mProgressEnd = (TextView) findViewById(R.id.progress_duration);
        this.mDate.setTime(this.mTimeItem.f8098a);
        this.mProgressStart.setText(this.mSimpleDateFormat.format(this.mDate));
        this.mDate.setTime(this.mTimeItem.c);
        this.mProgressEnd.setText(this.mSimpleDateFormat.format(this.mDate));
        this.mProgress = (SeekBar) findViewById(R.id.progress_bar);
        this.mProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                SDCardPlayerActivity.this.mSeekBarTouched = true;
                SDCardPlayerActivity.this.mHandler.removeMessages(1001);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                SDCardPlayerActivity.this.mHandler.removeMessages(1001);
                SDCardPlayerActivity.this.mDate.setTime(SDCardPlayerActivity.this.mTimeItem.f8098a + ((long) (SDCardPlayerActivity.this.mProgress.getProgress() * 1000)));
                SDCardPlayerActivity.this.mProgressStart.setText(SDCardPlayerActivity.this.mSimpleDateFormat.format(SDCardPlayerActivity.this.mDate));
                SDCardPlayerActivity.this.mHandler.sendEmptyMessageDelayed(1001, 500);
            }
        });
        this.mProgress.setMax(this.mDuration);
        showLoading();
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        this.mPauseView.setOnClickListener(this);
        this.mVideoView.initial();
        this.mCameraDevice.f().a(CameraProperties.c, (Callback<Void>) new Callback<Void>() {
            public void onFailure(int i, String str) {
            }

            public void onSuccess(Void voidR) {
                SDCardPlayerActivity.this.refreshUI();
            }
        });
        this.rvHistoryVideoList = (RecyclerView) findViewById(R.id.list_view);
        this.rvHistoryVideoList.setLayoutManager(new GridLayoutManager(activity(), 3));
        this.sdCardVideoAdapter = new SDCardHourAdapter(activity(), this, this.mDid, this.isV4);
        this.sdCardVideoAdapter.selectedPos = getIntent().getIntExtra("pos", -1);
        this.rvHistoryVideoList.setAdapter(this.sdCardVideoAdapter);
        this.rvHistoryVideoList.addItemDecoration(new SDCardItemDecoration());
        initEditView();
    }

    private void initEditView() {
        this.tvSelectAll = new TextView(activity());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 21;
        layoutParams.rightMargin = Util.a((Context) activity(), 14.0f);
        this.tvSelectAll.setPadding(10, 10, 10, 10);
        this.tvSelectAll.setLayoutParams(layoutParams);
        this.tvSelectAll.setBackgroundResource(R.drawable.camera_edit_select_all_white);
        this.tvSelectAll.setVisibility(8);
        this.mFLTitleBar.addView(this.tvSelectAll);
        this.tvSelectAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SDCardPlayerActivity.this.sdCardVideoAdapter == null) {
                    return;
                }
                if (SDCardPlayerActivity.this.sdCardVideoAdapter.getSelectCount() < SDCardPlayerActivity.this.sdCardVideoAdapter.getItemCount()) {
                    SDCardPlayerActivity.this.tvSelectAll.setBackgroundResource(R.drawable.camera_edit_deselect_all_white);
                    SDCardPlayerActivity.this.sdCardVideoAdapter.selectAll();
                    return;
                }
                SDCardPlayerActivity.this.tvSelectAll.setBackgroundResource(R.drawable.camera_edit_select_all_white);
                SDCardPlayerActivity.this.sdCardVideoAdapter.unSelectAll();
            }
        });
        this.tvBack = new TextView(activity());
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 19;
        layoutParams2.leftMargin = Util.a((Context) activity(), 14.0f);
        this.tvBack.setPadding(10, 10, 10, 10);
        this.tvBack.setLayoutParams(layoutParams2);
        this.tvBack.setBackgroundResource(R.drawable.camera_tittlebar_back_white);
        this.tvBack.setVisibility(8);
        this.mFLTitleBar.addView(this.tvBack);
        this.tvBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SDCardPlayerActivity.this.sdCardVideoAdapter.unSelectAll();
                SDCardPlayerActivity.this.sdCardVideoAdapter.setMultiSelectMode(false);
                SDCardPlayerActivity.this.hideEditView();
                SDCardPlayerActivity.this.refreshSelectTitle();
            }
        });
    }

    private void showEditView() {
        this.title_bar_more.setVisibility(8);
        this.title_bar_return.setVisibility(8);
        this.tvSelectAll.setVisibility(0);
        this.tvBack.setVisibility(0);
        this.llSelectBottom.setVisibility(0);
        this.rvHistoryVideoList.getHeight();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, this.rvHistoryVideoList.getHeight() - Util.a((Context) activity(), 67.0f));
        layoutParams.addRule(3, this.mVideoViewFrame.getId());
        this.rvHistoryVideoList.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: private */
    public void hideEditView() {
        this.title_bar_more.setVisibility(0);
        this.title_bar_return.setVisibility(0);
        this.tvSelectAll.setVisibility(8);
        this.tvBack.setVisibility(8);
        this.llSelectBottom.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(3, this.mVideoViewFrame.getId());
        this.rvHistoryVideoList.setLayoutParams(layoutParams);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mCameraPlayer != null) {
            this.mCameraPlayer.r();
            this.mCameraPlayer = null;
        }
        if (this.mVideoView != null) {
            this.mVideoView.release();
        }
        if (this.sdCardVideoAdapter != null) {
            this.sdCardVideoAdapter.destroyDisplayImageOptions();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.mCameraDevice != null && this.title_bar_more != null && this.mCameraDevice.isReadOnlyShared()) {
            this.title_bar_more.setEnabled(false);
        }
    }

    public void onPause() {
        if (this.mCameraPlayer != null) {
            this.mCameraPlayer.d(1);
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void doResume() {
        if (this.mUserPause && this.cdcTogglePlay.isChecked()) {
            return;
        }
        if (this.mProgress.getProgress() > this.mDuration) {
            setPlayTime(0);
        } else {
            setPlayTime(this.mProgress.getProgress());
        }
    }

    /* access modifiers changed from: protected */
    public void refreshUI() {
        if (this.mCameraDevice.f().c()) {
            if (this.mPowerOffView.getVisibility() == 0) {
                this.mPowerOffView.setVisibility(8);
                startPlay();
            }
        } else if (this.mPowerOffView.getVisibility() == 8) {
            this.mPowerOffView.setVisibility(0);
        }
        if (this.mCameraPlayer == null || this.mCameraPlayer.p()) {
            this.cdcToggleAudio.setChecked(false);
        } else {
            this.cdcToggleAudio.setChecked(true);
        }
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        int i = message.what;
        if (i == 2) {
            long b = this.mCameraPlayer.b();
            if (this.mLastUpdateTime == b || this.mSeekBarTouched) {
                this.mHandler.removeMessages(2);
                this.mHandler.sendEmptyMessageDelayed(2, (long) this.UPDATE_DURATION);
            } else if (Math.abs(b - ((long) this.mStartTime)) > 61) {
                this.mHandler.removeMessages(2);
                this.mHandler.sendEmptyMessageDelayed(2, (long) this.UPDATE_DURATION);
            } else {
                this.mLastUpdateTime = b;
                int i2 = (int) (b - (this.mTimeItem.f8098a / 1000));
                if (this.mIsSetTime) {
                    if (Math.abs(this.mLastOffsetTime - i2) <= 3 || System.currentTimeMillis() - this.mLastSetTime > 6000) {
                        this.mIsSetTime = false;
                        hideLoading();
                    } else {
                        this.mHandler.removeMessages(2);
                        this.mHandler.sendEmptyMessageDelayed(2, (long) this.UPDATE_DURATION);
                        return;
                    }
                }
                if (i2 < 0) {
                    this.mProgress.setProgress(1);
                } else if (i2 > this.mDuration) {
                    this.mProgress.setProgress(this.mDuration);
                    this.mDate.setTime(this.mTimeItem.c);
                    this.mProgressStart.setText(this.mSimpleDateFormat.format(this.mDate));
                    return;
                } else if (i2 > this.mProgress.getProgress()) {
                    this.mProgress.setProgress(i2);
                }
                this.mDate.setTime(this.mTimeItem.f8098a + ((long) (this.mProgress.getProgress() * 1000)));
                this.mProgressStart.setText(this.mSimpleDateFormat.format(this.mDate));
                this.mHandler.removeMessages(2);
                this.mHandler.sendEmptyMessageDelayed(2, (long) this.UPDATE_DURATION);
            }
        } else if (i == 1001) {
            this.mSeekBarTouched = false;
            SDKLog.d(Tag.c, "seek ");
            if (this.mProgress.getProgress() == this.mDuration) {
                this.mHandler.removeMessages(2);
                pauseCamera();
            } else if (this.mCameraPlayer != null && !this.mCameraPlayer.e()) {
                setPlayTime(this.mProgress.getProgress());
            }
        } else if (i == 4000) {
            this.mNeedSpeed = false;
            this.mTimeUpdateView.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void startPlay() {
        if (TextUtils.isEmpty(this.mCameraDevice.r()) || TextUtils.isEmpty(this.mCameraDevice.w())) {
            hideLoading();
            showError(getString(R.string.camera_play_error2));
            return;
        }
        if (this.mCameraPlayer == null) {
            this.mCameraPlayer = new CameraPlayer(this, this.mCameraDevice, this, this.mVideoView);
            this.mCameraPlayer.a(this.mIRecodeTimeListener);
        }
        if (!NetworkUtil.c(activity()) || this.mAllowMobileNetwork) {
            setPlayTime(0);
        } else {
            pauseCamera();
        }
    }

    public void onStop() {
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void resumeCamera() {
        this.mPauseView.setVisibility(8);
        int progress = this.mProgress.getProgress();
        if (progress >= this.mDuration) {
            setPlayTime(0);
            this.mProgress.setProgress(0);
        } else {
            setPlayTime(progress);
        }
        if (NetworkUtil.c(activity())) {
            this.mAllowMobileNetwork = true;
        }
        refreshUI();
        this.mHandler.removeMessages(2);
        this.mHandler.sendEmptyMessageDelayed(2, (long) this.UPDATE_DURATION);
    }

    public void onClick(View view) {
        doOnClick(view);
        switch (view.getId()) {
            case R.id.ivCameraShot:
                snapShot();
                return;
            case R.id.ivFullScreen:
                if (1 == getRequestedOrientation()) {
                    setOrientation(true);
                    return;
                } else {
                    setOrientation(false);
                    return;
                }
            case R.id.title_bar_more:
                if (this.mCameraDevice.isReadOnlyShared()) {
                    ToastUtil.a((Context) activity(), (int) R.string.auth_fail);
                    return;
                }
                if (!this.sdCardVideoAdapter.mIsMultiSelectMode) {
                    this.sdCardVideoAdapter.setMultiSelectMode(true);
                }
                showEditView();
                return;
            case R.id.tvSelectDelete:
                deleteSDCardFile();
                return;
            case R.id.tvSelectSave:
                saveSDCardFile();
                return;
            case R.id.tvsMultiSpeed:
                toggleSpeed();
                return;
            default:
                return;
        }
    }

    public void onBackPressed() {
        if (this.sdCardVideoAdapter != null && this.sdCardVideoAdapter.mIsMultiSelectMode) {
            this.sdCardVideoAdapter.unSelectAll();
            this.sdCardVideoAdapter.setMultiSelectMode(false);
            hideEditView();
        } else if (this.mFullScreen) {
            runOnUiThread(new Runnable() {
                public void run() {
                    SDCardPlayerActivity.this.setOrientation(false);
                }
            });
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: private */
    public void setPlayTime(int i) {
        if (i == this.mDuration) {
            i = this.mDuration - 2;
        }
        if (i < 0) {
            i = 0;
        }
        this.mLastSetTime = System.currentTimeMillis();
        this.mIsSetTime = true;
        this.mLastOffsetTime = i;
        this.mHandler.removeMessages(2);
        hidError();
        if (this.mPauseView.getVisibility() == 0) {
            this.mPauseView.setVisibility(8);
        }
        this.mUserPause = false;
        this.cdcTogglePlay.setChecked(false);
        if (this.mCameraPlayer != null) {
            this.mCameraPlayer.b(this.mStartTime, i, this.mEndTime);
        }
        if (this.mCameraPlayer != null && !this.mCameraPlayer.h()) {
            showLoading();
        }
        this.mHandler.sendEmptyMessageDelayed(2, 1000);
        SDKLog.d(Tag.b, "SDCardPlayer PlayTime " + this.mStartTime + " offset " + i + " end " + this.mEndTime);
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a1 A[Catch:{ Exception -> 0x010e }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a2 A[Catch:{ Exception -> 0x010e }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b3 A[Catch:{ Exception -> 0x010e }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c1 A[Catch:{ Exception -> 0x010e }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d2 A[Catch:{ Exception -> 0x010e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onServerCmd(int r6, byte[] r7) {
        /*
            r5 = this;
            boolean r0 = r5.isFinishing()
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            r0 = 61442(0xf002, float:8.6099E-41)
            if (r6 != r0) goto L_0x0118
            java.lang.String r6 = "CameraPlay"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "onServerCmd thread "
            r0.append(r1)
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.mijia.debug.SDKLog.d(r6, r0)
            java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x010e }
            r6.<init>(r7)     // Catch:{ Exception -> 0x010e }
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ Exception -> 0x010e }
            r7.<init>(r6)     // Catch:{ Exception -> 0x010e }
            java.lang.String r0 = "id"
            r1 = -1
            int r0 = r7.optInt(r0, r1)     // Catch:{ Exception -> 0x010e }
            java.lang.String r2 = "CameraPlay"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x010e }
            r3.<init>()     // Catch:{ Exception -> 0x010e }
            java.lang.String r4 = "id "
            r3.append(r4)     // Catch:{ Exception -> 0x010e }
            r3.append(r6)     // Catch:{ Exception -> 0x010e }
            java.lang.String r6 = r3.toString()     // Catch:{ Exception -> 0x010e }
            com.mijia.debug.SDKLog.d(r2, r6)     // Catch:{ Exception -> 0x010e }
            int r6 = r5.mStartTime     // Catch:{ Exception -> 0x010e }
            if (r0 != r6) goto L_0x0118
            java.lang.String r6 = "status"
            r0 = 0
            java.lang.String r6 = r7.optString(r6, r0)     // Catch:{ Exception -> 0x010e }
            if (r6 == 0) goto L_0x0118
            int r0 = r6.hashCode()     // Catch:{ Exception -> 0x010e }
            r2 = -1897432978(0xffffffff8ee7786e, float:-5.7061866E-30)
            r3 = 0
            r4 = 2
            if (r0 == r2) goto L_0x0093
            r2 = -1309954170(0xffffffffb1ebaf86, float:-6.859355E-9)
            if (r0 == r2) goto L_0x0089
            r2 = -1107950030(0xffffffffbdf60632, float:-0.120129004)
            if (r0 == r2) goto L_0x007f
            r2 = 250180107(0xee9720b, float:5.7548754E-30)
            if (r0 == r2) goto L_0x0075
            goto L_0x009d
        L_0x0075:
            java.lang.String r0 = "filenotfound"
            boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x010e }
            if (r0 == 0) goto L_0x009d
            r0 = 1
            goto L_0x009e
        L_0x007f:
            java.lang.String r0 = "readerror"
            boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x010e }
            if (r0 == 0) goto L_0x009d
            r0 = 3
            goto L_0x009e
        L_0x0089:
            java.lang.String r0 = "filefound"
            boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x010e }
            if (r0 == 0) goto L_0x009d
            r0 = 0
            goto L_0x009e
        L_0x0093:
            java.lang.String r0 = "endoffile"
            boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x010e }
            if (r0 == 0) goto L_0x009d
            r0 = 2
            goto L_0x009e
        L_0x009d:
            r0 = -1
        L_0x009e:
            switch(r0) {
                case 0: goto L_0x00d2;
                case 1: goto L_0x00c1;
                case 2: goto L_0x00b3;
                case 3: goto L_0x00a2;
                default: goto L_0x00a1;
            }     // Catch:{ Exception -> 0x010e }
        L_0x00a1:
            goto L_0x00f7
        L_0x00a2:
            r5.hideLoading()     // Catch:{ Exception -> 0x010e }
            r5.pauseCamera()     // Catch:{ Exception -> 0x010e }
            r7 = 2131498391(0x7f0c1597, float:1.8620402E38)
            java.lang.String r7 = r5.getString(r7)     // Catch:{ Exception -> 0x010e }
            r5.showError(r7)     // Catch:{ Exception -> 0x010e }
            goto L_0x00f7
        L_0x00b3:
            android.os.Handler r7 = r5.mHandler     // Catch:{ Exception -> 0x010e }
            r7.removeMessages(r4)     // Catch:{ Exception -> 0x010e }
            r5.pauseCamera()     // Catch:{ Exception -> 0x010e }
            android.widget.SeekBar r7 = r5.mProgress     // Catch:{ Exception -> 0x010e }
            r7.setProgress(r3)     // Catch:{ Exception -> 0x010e }
            goto L_0x00f7
        L_0x00c1:
            r5.hideLoading()     // Catch:{ Exception -> 0x010e }
            r5.pauseCamera()     // Catch:{ Exception -> 0x010e }
            r7 = 2131493771(0x7f0c038b, float:1.8611032E38)
            java.lang.String r7 = r5.getString(r7)     // Catch:{ Exception -> 0x010e }
            r5.showError(r7)     // Catch:{ Exception -> 0x010e }
            goto L_0x00f7
        L_0x00d2:
            java.lang.String r0 = "duration"
            int r7 = r7.optInt(r0, r1)     // Catch:{ Exception -> 0x010e }
            if (r7 <= 0) goto L_0x00f7
            int r0 = r5.mDuration     // Catch:{ Exception -> 0x010e }
            if (r0 == r7) goto L_0x00f7
            r5.mDuration = r7     // Catch:{ Exception -> 0x010e }
            android.widget.SeekBar r0 = r5.mProgress     // Catch:{ Exception -> 0x010e }
            int r0 = r0.getProgress()     // Catch:{ Exception -> 0x010e }
            if (r0 < r7) goto L_0x00ed
            android.widget.SeekBar r0 = r5.mProgress     // Catch:{ Exception -> 0x010e }
            r0.setProgress(r7)     // Catch:{ Exception -> 0x010e }
        L_0x00ed:
            android.widget.SeekBar r0 = r5.mProgress     // Catch:{ Exception -> 0x010e }
            r0.setMax(r7)     // Catch:{ Exception -> 0x010e }
            int r0 = r5.mStartTime     // Catch:{ Exception -> 0x010e }
            int r0 = r0 + r7
            r5.mEndTime = r0     // Catch:{ Exception -> 0x010e }
        L_0x00f7:
            java.lang.String r7 = "CameraPlay"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x010e }
            r0.<init>()     // Catch:{ Exception -> 0x010e }
            java.lang.String r1 = " onServerCmd status "
            r0.append(r1)     // Catch:{ Exception -> 0x010e }
            r0.append(r6)     // Catch:{ Exception -> 0x010e }
            java.lang.String r6 = r0.toString()     // Catch:{ Exception -> 0x010e }
            com.mijia.debug.SDKLog.d(r7, r6)     // Catch:{ Exception -> 0x010e }
            goto L_0x0118
        L_0x010e:
            r6 = move-exception
            java.lang.String r7 = "CameraPlay"
            java.lang.String r6 = r6.toString()
            com.mijia.debug.SDKLog.d(r7, r6)
        L_0x0118:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.sdcard.SDCardPlayerActivity.onServerCmd(int, byte[]):void");
    }

    public void onDisConnected() {
        this.mNeedSetTime = true;
    }

    public void onDisconnectedWithCode(int i) {
        this.mNeedSetTime = true;
    }

    public void onConnected() {
        super.onConnected();
        if (this.mNeedSetTime) {
            if (!this.cdcTogglePlay.isChecked()) {
                setPlayTime(this.mProgress.getProgress());
            }
            this.mNeedSetTime = false;
            SDKLog.d(Tag.b, "SDCardPlayer reconnect");
        }
    }

    private void loadData() {
        TimeItemHour b;
        List<TimeItem> arrayList = new ArrayList<>();
        List<TimeItemDay> g = this.mCameraSdcardFileManager.g();
        if (this.mDay >= 0 && this.mDay < g.size() && (b = g.get(this.mDay).b(this.mHour)) != null) {
            arrayList = b.b;
        }
        ArrayList arrayList2 = new ArrayList();
        for (TimeItem timeItem : arrayList) {
            TimeItemData timeItemData = new TimeItemData();
            timeItemData.timeItem = timeItem;
            timeItemData.title = this.sdfHHmm.format(Long.valueOf(timeItem.f8098a)) + "-" + this.sdfHHmm.format(Long.valueOf(timeItem.c));
            timeItemData.subTitle = this.sdfMMdd.format(Long.valueOf(timeItem.f8098a));
            timeItemData.localFile = this.mCameraDevice.b().a(timeItem.f8098a);
            timeItemData.downloadItem = this.mDownloadSdCardFileManager.a(timeItem);
            arrayList2.add(timeItemData);
        }
        this.mTimeItemDataList = arrayList2;
        if (this.mTimeItemDataList.size() > 0) {
            this.sdCardVideoAdapter.setData(this.mTimeItemDataList);
            this.sdCardVideoAdapter.notifyDataSetChanged();
        }
    }

    public void onDownloading(final DownloadSdCardManager.DownloadItem downloadItem, String str, int i) {
        runMainThread(new Runnable() {
            public void run() {
                if (SDCardPlayerActivity.this.mNeedCheckList.contains(Long.valueOf(downloadItem.f8073a.f8098a))) {
                    for (int i = 0; i < SDCardPlayerActivity.this.mTimeItemDataList.size(); i++) {
                        if (SDCardPlayerActivity.this.mTimeItemDataList.get(i).timeItem.equals(downloadItem.f8073a)) {
                            SDCardPlayerActivity.this.mTimeItemDataList.get(i).downloadItem = downloadItem;
                            SDCardPlayerActivity.this.sdCardVideoAdapter.notifyItemChanged(i);
                            return;
                        }
                    }
                }
            }
        });
    }

    public void onDownloadSuccess(DownloadSdCardManager.DownloadItem downloadItem, String str) {
        if (this.mNeedCheckList.contains(Long.valueOf(downloadItem.f8073a.f8098a))) {
            runMainThread(new Runnable() {
                public void run() {
                    SDCardPlayerActivity.this.refreshData();
                }
            });
        }
    }

    public void onDownloadFailed(DownloadSdCardManager.DownloadItem downloadItem, String str, int i, String str2) {
        if (this.mNeedCheckList.contains(Long.valueOf(downloadItem.f8073a.f8098a))) {
            runMainThread(new Runnable() {
                public void run() {
                    SDCardPlayerActivity.this.refreshData();
                }
            });
        }
    }

    public void onRecyclerClick(View view) {
        if (this.sdCardVideoAdapter != null) {
            SDKLog.b(TAG, "itemView:" + view.getId());
            int childLayoutPosition = this.rvHistoryVideoList.getChildLayoutPosition(view);
            if (!this.sdCardVideoAdapter.mIsMultiSelectMode) {
                this.sdCardVideoAdapter.setSelected(childLayoutPosition);
                TimeItemData data = this.sdCardVideoAdapter.getData(childLayoutPosition);
                this.mStartTime = (int) (data.timeItem.f8098a / 1000);
                this.mEndTime = (int) (data.timeItem.c / 1000);
                this.mDuration = ((int) data.timeItem.b) / 1000;
                this.mProgress.setMax(this.mDuration);
                this.mProgress.setProgress(0);
                this.mLastUpdateTime = 0;
                this.mTimeItem = data.timeItem;
                this.mDate.setTime(this.mTimeItem.f8098a);
                this.mProgressStart.setText(this.mSimpleDateFormat.format(this.mDate));
                this.mDate.setTime(this.mTimeItem.c);
                this.mProgressEnd.setText(this.mSimpleDateFormat.format(this.mDate));
                TextView textView = this.mTitleView;
                textView.setText("" + this.sdfHHmm.format(Long.valueOf(data.timeItem.f8098a)) + "-" + this.sdfHHmm.format(Long.valueOf(data.timeItem.c)));
                startPlay();
                this.sdCardVideoAdapter.notifyDataSetChanged();
            } else if (childLayoutPosition >= 0 && childLayoutPosition < this.sdCardVideoAdapter.getItemCount()) {
                this.sdCardVideoAdapter.selectToggle(childLayoutPosition);
                refreshSelectTitle();
                this.sdCardVideoAdapter.notifyItemChanged(childLayoutPosition);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setOrientation(boolean z) {
        if (!this.mCameraDevice.f().c()) {
            ToastUtil.a((Context) activity(), (int) R.string.power_off);
        } else if (z) {
            setRequestedOrientation(0);
        } else {
            setRequestedOrientation(1);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setWindow(configuration);
        if (configuration.orientation != 1) {
            this.mFullScreen = true;
            this.title_bar_more.setVisibility(8);
            this.rvHistoryVideoList.setVisibility(8);
            this.mVideoViewFrame.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.mVideoView.setIsFull(true);
            return;
        }
        this.mFullScreen = false;
        this.title_bar_more.setVisibility(0);
        this.rvHistoryVideoList.setVisibility(0);
        this.mVideoViewFrame.setLayoutParams(new RelativeLayout.LayoutParams(-1, Util.a((Context) activity(), 350.0f)));
        this.mVideoView.setIsFull(false);
    }

    private void toggleSpeed() {
        if (this.mCameraPlayer == null) {
            return;
        }
        if (this.mCameraPlayer.d()) {
            int q = this.mCameraPlayer.q();
            SDKLog.b(TAG, "speed:" + q);
            int i = 16;
            if (q == 1) {
                i = 4;
            } else if (q != 4) {
                i = 1;
            }
            this.mCameraPlayer.d(i);
            if (i == 1) {
                this.cdcToggleAudio.setEnabled(true);
            } else {
                if (this.cdcToggleAudio.isChecked()) {
                    this.cdcToggleAudio.performClick();
                }
                this.cdcToggleAudio.setEnabled(false);
            }
            TextViewS textViewS = this.tvsMultiSpeed;
            textViewS.setText("" + i + "X");
            return;
        }
        ToastUtil.a((Context) activity(), (int) R.string.sd_card_video_not_playing);
    }

    private void snapShot() {
        if (!this.mCameraDevice.f().c()) {
            ToastUtil.a((Context) activity(), (int) R.string.power_off);
        } else if (this.mCameraDevice == null || this.mCameraDevice.f() == null || !this.mCameraDevice.f().c()) {
            ToastUtil.a((Context) activity(), (int) R.string.power_off);
        } else if (this.mCameraPlayer == null || !this.mCameraPlayer.i()) {
            ToastUtil.a((Context) activity(), (int) R.string.snap_failed_paused);
        } else {
            this.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
                public void onSnap(final Bitmap bitmap) {
                    SDCardPlayerActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            SDCardPlayerActivity.this.onSnapShot(bitmap);
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void onSnapShot(Bitmap bitmap) {
        final String a2 = FileUtil.a(false, this.mCameraDevice.getDid());
        if (a2 != null && bitmap != null) {
            if (!CoreApi.a().D()) {
                bitmap = BitmapUtils.a(bitmap, activity());
            }
            if (bitmap != null) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(a2);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.close();
                } catch (IOException unused) {
                    return;
                }
            }
            final Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, 200, (bitmap.getHeight() * 200) / bitmap.getWidth(), false);
            runOnUiThread(new Runnable() {
                public void run() {
                    if (new File(a2).exists()) {
                        ImageView imageView = (ImageView) SDCardPlayerActivity.this.findViewById(R.id.ivShotPic);
                        if (SDCardPlayerActivity.this.llFuncPopup.getVisibility() == 0) {
                            SDCardPlayerActivity.this.llFuncPopup.setVisibility(8);
                        }
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) SDCardPlayerActivity.this.llFuncPopup.getLayoutParams();
                        layoutParams.bottomMargin = SDCardPlayerActivity.this.llVideoViewBottomCtrl.getHeight() + Util.a((Context) SDCardPlayerActivity.this.activity(), 6.0f);
                        SDCardPlayerActivity.this.llFuncPopup.setLayoutParams(layoutParams);
                        SDCardPlayerActivity.this.llFuncPopup.startAnimation(AnimationUtils.loadAnimation(SDCardPlayerActivity.this.activity(), R.anim.anim_snap_shot_in));
                        SDCardPlayerActivity.this.llFuncPopup.setVisibility(0);
                        SDCardPlayerActivity.this.dismissSnapshotPopupRunnable(3000);
                        if (createScaledBitmap != null) {
                            imageView.setImageBitmap(createScaledBitmap);
                        }
                        ContentValues contentValues = new ContentValues(4);
                        contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
                        contentValues.put(Downloads._DATA, a2);
                        contentValues.put("mime_type", "image/jpeg");
                        try {
                            if (!Build.MODEL.equals("HM 1SC")) {
                                SDCardPlayerActivity.this.activity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                            }
                            SDKLog.b(SDCardPlayerActivity.TAG, "snap success");
                        } catch (Throwable unused) {
                        }
                        final LocalFileManager.LocalFile b = SDCardPlayerActivity.this.mCameraDevice.b().b(a2);
                        if (b != null) {
                            imageView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    SDCardPlayerActivity.this.dismissSnapshotPopupRunnable(0);
                                    Intent intent = new Intent(SDCardPlayerActivity.this.activity(), LocalPicReviewActivity.class);
                                    intent.putExtra("file_path", b.d);
                                    SDCardPlayerActivity.this.startActivity(intent);
                                }
                            });
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void dismissSnapshotPopupRunnable(long j) {
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (SDCardPlayerActivity.this.llFuncPopup.getVisibility() != 8) {
                    SDCardPlayerActivity.this.llFuncPopup.startAnimation(AnimationUtils.loadAnimation(SDCardPlayerActivity.this.activity(), R.anim.anim_snap_shot_out));
                    SDCardPlayerActivity.this.llFuncPopup.setVisibility(8);
                }
            }
        }, j);
    }

    /* access modifiers changed from: protected */
    public void doStopRecord() {
        this.mLastTime = 0;
        this.mCurrentTime = 0;
        if (this.mCameraPlayer != null && this.mCameraPlayer.m()) {
            this.mCameraPlayer.a((XmMp4Record.IRecordListener) new XmMp4Record.IRecordListener() {
                public void onSuccess(String str) {
                    SDCardPlayerActivity.this.tvRecordTimer.setVisibility(8);
                    SDCardPlayerActivity.this.onVideoRecorded(str);
                }

                public void onFailed(int i, String str) {
                    SDKLog.b(SDCardPlayerActivity.TAG, "error:" + i + " info:" + str);
                    SDCardPlayerActivity.this.tvRecordTimer.setVisibility(8);
                    ToastUtil.a((Context) SDCardPlayerActivity.this.activity(), (int) R.string.snip_video_failed);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void upDateTimeTitle(int i) {
        this.tvRecordTimer.setText(this.mSimpleDateFormat.format(new Date((long) i)));
    }

    /* access modifiers changed from: private */
    public void onVideoRecorded(String str) {
        File file = new File(str);
        if (file.exists()) {
            activity().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
            final LocalFileManager.LocalFile b = this.mCameraDevice.b().b(str);
            if (b != null && this.mVideoView != null) {
                this.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
                    public void onSnap(Bitmap bitmap) {
                        if (bitmap != null) {
                            final Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, 200, (bitmap.getHeight() * 200) / bitmap.getWidth(), false);
                            SDCardPlayerActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    if (createScaledBitmap != null) {
                                        ImageView imageView = (ImageView) SDCardPlayerActivity.this.findViewById(R.id.ivShotPic);
                                        imageView.setImageBitmap(createScaledBitmap);
                                        if (SDCardPlayerActivity.this.llFuncPopup.getVisibility() == 0) {
                                            SDCardPlayerActivity.this.llFuncPopup.setVisibility(8);
                                        }
                                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) SDCardPlayerActivity.this.llFuncPopup.getLayoutParams();
                                        layoutParams.bottomMargin = SDCardPlayerActivity.this.llVideoViewBottomCtrl.getHeight() + Util.a((Context) SDCardPlayerActivity.this.activity(), 6.0f);
                                        SDCardPlayerActivity.this.llFuncPopup.setLayoutParams(layoutParams);
                                        SDCardPlayerActivity.this.llFuncPopup.startAnimation(AnimationUtils.loadAnimation(SDCardPlayerActivity.this.activity(), R.anim.anim_snap_shot_in));
                                        SDCardPlayerActivity.this.llFuncPopup.setVisibility(0);
                                        SDCardPlayerActivity.this.dismissSnapshotPopupRunnable(3000);
                                        imageView.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View view) {
                                                SDCardPlayerActivity.this.dismissSnapshotPopupRunnable(0);
                                                Intent intent = new Intent(SDCardPlayerActivity.this.activity(), LocalVideoPlayActivity.class);
                                                intent.putExtra("file_path", b.d);
                                                SDCardPlayerActivity.this.startActivity(intent);
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    }
                });
            }
        }
    }

    public void refreshSelectTitle() {
        if (this.sdCardVideoAdapter != null) {
            String str = this.sdfHHmm.format(Long.valueOf(this.mTimeItem.f8098a)) + "-" + this.sdfHHmm.format(Long.valueOf(this.mTimeItem.c));
            int selectCount = this.sdCardVideoAdapter.getSelectCount();
            if (this.sdCardVideoAdapter.mIsMultiSelectMode) {
                if (selectCount == 0) {
                    this.mTitleView.setText(str);
                } else {
                    this.mTitleView.setText(getString(R.string.select_title_2, new Object[]{Integer.valueOf(selectCount)}));
                }
                if (selectCount == 0 || selectCount != this.sdCardVideoAdapter.getItemCount()) {
                    this.tvSelectAll.setBackgroundResource(R.drawable.camera_edit_select_all_white);
                } else {
                    this.tvSelectAll.setBackgroundResource(R.drawable.camera_edit_deselect_all_white);
                }
            } else {
                this.mTitleView.setText(str);
            }
        }
    }

    private void saveSDCardFile() {
        ArrayList<TimeItem> selectItems = this.sdCardVideoAdapter.getSelectItems();
        initProgressDlg();
        CameraOperationUtils.b(activity(), this.mCameraDevice, selectItems, this.mProgressDialog, new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!SDCardPlayerActivity.this.isFinishing()) {
                    SDCardPlayerActivity.this.runMainThread(new Runnable() {
                        public void run() {
                            Toast.makeText(SDCardPlayerActivity.this.activity(), R.string.save_sucess, 0).show();
                            SDCardPlayerActivity.this.disProgressDlg();
                            SDCardPlayerActivity.this.hideEditView();
                            SDCardPlayerActivity.this.sdCardVideoAdapter.setMultiSelectMode(false);
                            SDCardPlayerActivity.this.refreshSelectTitle();
                            SDCardPlayerActivity.this.refreshData();
                        }
                    });
                }
            }

            public void onFailure(int i, String str) {
                if (!SDCardPlayerActivity.this.isFinishing()) {
                    SDCardPlayerActivity.this.runMainThread(new Runnable() {
                        public void run() {
                            SDCardPlayerActivity.this.disProgressDlg();
                            SDCardPlayerActivity.this.hideEditView();
                            SDCardPlayerActivity.this.refreshSelectTitle();
                            Toast.makeText(SDCardPlayerActivity.this.activity(), R.string.save_failed, 0).show();
                        }
                    });
                }
            }
        });
    }

    private void deleteSDCardFile() {
        ArrayList<TimeItem> selectItems = this.sdCardVideoAdapter.getSelectItems();
        initProgressDlg();
        CameraOperationUtils.a(activity(), this.mCameraDevice, selectItems, this.mProgressDialog, new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!SDCardPlayerActivity.this.isFinishing()) {
                    SDCardPlayerActivity.this.runMainThread(new Runnable() {
                        public void run() {
                            SDCardPlayerActivity.this.disProgressDlg();
                            SDCardPlayerActivity.this.hideEditView();
                            Toast.makeText(SDCardPlayerActivity.this.activity(), R.string.delete_sucess, 0).show();
                            SDCardPlayerActivity.this.sdCardVideoAdapter.setMultiSelectMode(false);
                            SDCardPlayerActivity.this.refreshSelectTitle();
                            SDCardPlayerActivity.this.refreshData();
                        }
                    });
                }
            }

            public void onFailure(int i, String str) {
                if (!SDCardPlayerActivity.this.isFinishing()) {
                    SDCardPlayerActivity.this.runMainThread(new Runnable() {
                        public void run() {
                            SDCardPlayerActivity.this.disProgressDlg();
                            SDCardPlayerActivity.this.hideEditView();
                            SDCardPlayerActivity.this.refreshSelectTitle();
                            Toast.makeText(SDCardPlayerActivity.this.activity(), R.string.delete_failed, 0).show();
                        }
                    });
                }
            }
        });
    }

    public void initProgressDlg() {
        if (this.mProgressDialog == null) {
            this.mProgressDialog = new XQProgressDialog(activity());
        }
    }

    public void disProgressDlg() {
        if (this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void refreshData() {
        List<TimeItemDay> g = this.mCameraDevice.c().g();
        if (this.mDay < 0 || this.mDay >= g.size()) {
            this.mTimeItemList = new ArrayList();
        } else {
            TimeItemHour b = g.get(this.mDay).b(this.mHour);
            if (b != null) {
                this.mTimeItemList = b.b;
            } else {
                this.mTimeItemList = new ArrayList();
            }
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mTimeItemList.size(); i++) {
            TimeItem timeItem = this.mTimeItemList.get(i);
            TimeItemData timeItemData = new TimeItemData();
            timeItemData.timeItem = timeItem;
            timeItemData.title = this.sdfHHmm.format(Long.valueOf(timeItem.f8098a)) + "-" + this.sdfHHmm.format(Long.valueOf(timeItem.b()));
            timeItemData.subTitle = this.mSimpleDateFormat.format(Long.valueOf(timeItem.f8098a));
            timeItemData.localFile = this.mCameraDevice.b().a(timeItem.f8098a);
            timeItemData.downloadItem = this.mDownloadSdCardFileManager.a(timeItem);
            arrayList.add(timeItemData);
            this.mNeedCheckList.add(Long.valueOf(timeItem.f8098a));
        }
        this.mTimeItemDataList = arrayList;
        if (this.mTimeItemDataList.isEmpty()) {
            this.rvHistoryVideoList.setVisibility(8);
        } else {
            this.rvHistoryVideoList.setVisibility(0);
        }
        this.sdCardVideoAdapter.setData(this.mTimeItemDataList);
        this.sdCardVideoAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void pauseCamera() {
        super.pauseCamera();
        this.cdcTogglePlay.setChecked(true);
    }
}
