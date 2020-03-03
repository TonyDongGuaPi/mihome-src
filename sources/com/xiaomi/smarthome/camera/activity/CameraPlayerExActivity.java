package com.xiaomi.smarthome.camera.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.Utils.FileUtil;
import com.Utils.NetworkUtil;
import com.Utils.TimeUtils;
import com.amap.api.services.core.AMapException;
import com.mi.global.bbs.manager.Region;
import com.mijia.app.AppCode;
import com.mijia.app.AppConfig;
import com.mijia.app.Event;
import com.mijia.camera.CameraPlayerEx;
import com.mijia.camera.CameraProperties;
import com.mijia.camera.ICameraPlayerListener;
import com.mijia.camera.Utils.BitmapUtils;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.mijia.model.alarm.AlarmManager;
import com.mijia.model.alarm.AlarmNetUtils;
import com.mijia.model.local.LocalFileClear;
import com.mijia.model.local.LocalFileManager;
import com.mijia.model.local.LocalSettings;
import com.mijia.model.property.CameraPropertyBase;
import com.mijia.model.sdcard.SDCardInfo;
import com.smarthome_camera.BuildConfig;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.IMISSListener;
import com.xiaomi.smarthome.camera.LocalLicenseUtil;
import com.xiaomi.smarthome.camera.XmMp4Record;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.activity.alarm.AlarmGuideActivity;
import com.xiaomi.smarthome.camera.activity.alarm.AlarmSettingV2Activity;
import com.xiaomi.smarthome.camera.activity.alarm.AlarmV2Activity;
import com.xiaomi.smarthome.camera.activity.local.AlbumActivity;
import com.xiaomi.smarthome.camera.activity.local.LocalPicReviewActivity;
import com.xiaomi.smarthome.camera.activity.local.LocalVideoPlayActivity;
import com.xiaomi.smarthome.camera.activity.sdcard.SDCardActivity;
import com.xiaomi.smarthome.camera.activity.setting.FileManagerSettingActivity;
import com.xiaomi.smarthome.camera.activity.setting.MoreCameraSettingActivity;
import com.xiaomi.smarthome.camera.v4.activity.setting.NoMemoryCardActivity;
import com.xiaomi.smarthome.camera.view.GuidePage1;
import com.xiaomi.smarthome.camera.view.MultiStateTextView;
import com.xiaomi.smarthome.camera.view.SDCardHintDialog;
import com.xiaomi.smarthome.camera.view.TextViewP;
import com.xiaomi.smarthome.camera.view.TextViewS;
import com.xiaomi.smarthome.camera.view.widget.BottomScrollView;
import com.xiaomi.smarthome.camera.view.widget.CenterDrawableCheckBox;
import com.xiaomi.smarthome.camera.view.widget.MultiStateView;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceUpdateInfo;
import com.xiaomi.smarthome.device.api.IXmPluginHostActivity;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.CommonShareActivity;
import com.xiaomi.smarthome.framework.page.DeviceMoreActivity;
import com.xiaomi.smarthome.framework.page.DeviceMoreNewActivity;
import com.xiaomi.smarthome.framework.plugin.mpk.PluginHostApiImpl;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.UserLicenseDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.ICloudVideoCallback;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import com.xiaomi.stat.d;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

public class CameraPlayerExActivity extends CameraPlayerBaseActivity implements ICameraPlayerListener {
    private static final String CLOUD_SUPPORT_FW_VERSION = "3.4.4.0038";
    private static final String TAG = "CameraPlayerExActivity";
    private final int CALL_VOLUME_DELAY = 500;
    private final int FIRST_RESUME = AMapException.CODE_AMAP_NEARBY_INVALID_USERID;
    private final int MSG_CALL_VOLUME = 2101;
    private final int MSG_UPDATE_FIRM = 1;
    final int REQUEST_MORE_ACTIVITY = 1220;
    private final int UPDATE_CHECK = Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS;
    /* access modifiers changed from: private */
    public FrameLayout flCloudVideoTips;
    private FrameLayout flTitleBar;
    /* access modifiers changed from: private */
    public boolean isSnapshotting = false;
    /* access modifiers changed from: private */
    public boolean isStartPlay = false;
    private ImageView ivFullScreen;
    /* access modifiers changed from: private */
    public BottomScrollView mBottomGroup;
    private View mBottomTools;
    /* access modifiers changed from: private */
    public CenterDrawableCheckBox mCDCToggleAudio;
    /* access modifiers changed from: private */
    public int mCurrentTime = 0;
    private CameraPlayerEx.IRecordTimeListener mIRecodeTimeListener = new CameraPlayerEx.IRecordTimeListener() {
        public void upDateTime(int i) {
            int access$1700 = CameraPlayerExActivity.this.mCurrentTime / 1000;
            if (CameraPlayerExActivity.this.mLastTime != -1) {
                int access$1800 = i - CameraPlayerExActivity.this.mLastTime;
                if (access$1800 <= 0 || access$1800 >= 500) {
                    int unused = CameraPlayerExActivity.this.mCurrentTime = CameraPlayerExActivity.this.mCurrentTime + 50;
                } else {
                    int unused2 = CameraPlayerExActivity.this.mCurrentTime = CameraPlayerExActivity.this.mCurrentTime + access$1800;
                }
            } else {
                int unused3 = CameraPlayerExActivity.this.mCurrentTime = CameraPlayerExActivity.this.mCurrentTime + 50;
            }
            int unused4 = CameraPlayerExActivity.this.mLastTime = i;
            if (CameraPlayerExActivity.this.mCurrentTime / 1000 != access$1700) {
                CameraPlayerExActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        int access$1700 = CameraPlayerExActivity.this.mCurrentTime / 1000;
                        if (access$1700 > 1) {
                            CameraPlayerExActivity.this.upDateTimeTitle(access$1700 - 1);
                        }
                    }
                });
            }
        }
    };
    private boolean mIsInit = false;
    /* access modifiers changed from: private */
    public LinearLayout mLLFuncPopup;
    /* access modifiers changed from: private */
    public MultiStateView mLandRecord;
    /* access modifiers changed from: private */
    public TextView mLandRecordTimer;
    /* access modifiers changed from: private */
    public int mLastTime = -1;
    /* access modifiers changed from: private */
    public MultiStateView mMSTVVoice;
    /* access modifiers changed from: private */
    public boolean mNeedLicense = false;
    private View mNewFirmView;
    /* access modifiers changed from: private */
    public MultiStateView mRecord;
    private SDCardHintDialog mSDCardHintDialog;
    private TextView mTVAlarm;
    /* access modifiers changed from: private */
    public TextView mTVRecordTimer;
    private TextViewP mTVSLandResolution;
    private TextViewS mTVSResolution;
    private TextView mTimeUpdateView;
    private View mTitleMore;
    /* access modifiers changed from: private */
    public MultiStateTextView mTvMore;
    /* access modifiers changed from: private */
    public MultiStateTextView mTvSleep;
    /* access modifiers changed from: private */
    public LinearLayout mViewFloat;
    int selectedIndex;
    private TextView tvCloudVideo;

    private int getResolutionIndex(int i) {
        if (i != 1) {
            return i != 3 ? 0 : 2;
        }
        return 1;
    }

    /* access modifiers changed from: private */
    public void initGuideCenter() {
    }

    public boolean isHistory() {
        return false;
    }

    public void onServerCmd(int i, byte[] bArr) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                LocalFileClear.a((CameraDevice) CameraPlayerExActivity.this.mCameraDevice);
            }
        }, 2000);
        Event.a(this.mCameraDevice.getDid(), this.mCameraDevice.getModel());
        LogUtil.a(TAG, "getDid:" + this.mCameraDevice.getDid() + " getModel:" + this.mCameraDevice.getModel());
        SDKLog.a(this.mCameraDevice.getModel());
        setContentView(R.layout.camera_activity_camera_player);
        this.flTitleBar = (FrameLayout) findViewById(R.id.title_bar);
        this.flTitleBar.setBackgroundResource(R.drawable.camera_shape_gradient_bg);
        this.flTitleBar.bringToFront();
        if (!getIntent().getBooleanExtra("pincod", false)) {
            enableVerifyPincode();
            if (this.mCameraDevice.m()) {
                this.mNeedPincode = true;
            }
        }
        if (getIntent().getBooleanExtra("fail_unbind", false) && this.mCameraDevice.isOwner()) {
            Intent intent = new Intent();
            intent.putExtra(DeviceMoreActivity.ARGS_SECURITY_SETTING_ENABLE, false);
            intent.putExtra(DeviceMoreActivity.ARGS_UNBIND_ENABLE, true);
            intent.putExtra(DeviceMoreActivity.ARGS_DELETE_ENABLE, true);
            intent.putExtra(DeviceMoreActivity.ARGS_SHARE_EBABLE, false);
            openMoreMenu(new ArrayList(), true, 1002, intent);
            finish();
        }
        initView();
        initMulti();
        if (!(this.mCameraDevice == null || this.mCameraDevice.g() == null)) {
            this.mCameraDevice.g().a(this.mPropertyChangeListener);
            this.mCameraDevice.g().a();
        }
        setResolutionText(getResolutionIndex(this.mCameraDevice.e().d()));
        this.mNetworkMonitor.register(this);
        if (!(this.mCameraDevice == null || this.mCameraDevice.f() == null)) {
            this.mCameraDevice.f().a(CameraProperties.c, (Callback<Void>) new Callback<Void>() {
                public void onFailure(int i, String str) {
                }

                public void onSuccess(Void voidR) {
                    if (!CameraPlayerExActivity.this.isFinishing()) {
                        CameraPlayerExActivity.this.mPowerCheck = true;
                        CameraPlayerExActivity.this.refreshUI();
                        CameraPlayerExActivity.this.checkMinLevel();
                    }
                }
            });
        }
        showLicense();
        showCloudVideoTips();
        getFirmwareVersion();
    }

    private void showCloudVideoTips() {
        if (this.mCameraDevice.e().o()) {
            CloudVideoNetUtils.getInstance().getCloudPromoteTips(this.mDid, new ICloudVideoCallback<String>() {
                public void onCloudVideoSuccess(String str, Object obj) {
                    CameraPlayerExActivity.this.flCloudVideoTips.setVisibility(0);
                    CameraPlayerExActivity.this.flCloudVideoTips.setOnClickListener(new View.OnClickListener() {
                        public final void onClick(View view) {
                            CameraPlayerExActivity.this.onClick(view);
                        }
                    });
                    ((TextView) CameraPlayerExActivity.this.findViewById(R.id.tv_cloud_video_tips)).setText(str);
                    if (CameraPlayerExActivity.this.mCameraDevice.e().n() == 0) {
                        CameraPlayerExActivity.this.mCameraDevice.e().b(System.currentTimeMillis());
                    }
                }

                public void onCloudVideoFailed(int i, String str) {
                    Log.d(CameraPlayerExActivity.TAG, str);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.mNewFirmView = findViewById(R.id.title_bar_redpoint);
        this.mTitleView = (TextView) findViewById(R.id.title_bar_title);
        this.mTimeUpdateView = (TextView) findViewById(R.id.time_container_center);
        this.mTitleMore = findViewById(R.id.title_bar_more);
        this.mTitleMore.setVisibility(0);
        this.mTitleMore.setOnClickListener(this);
        findViewById(R.id.title_bar_share).setVisibility(8);
        this.mViewFloat = (LinearLayout) findViewById(R.id.view_float);
        this.mViewFloat.setVisibility(0);
        this.mVideoViewFrame = (FrameLayout) findViewById(R.id.video_frame);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        FrameLayout frameLayout = new FrameLayout(this);
        this.mVideoViewFrame.addView(frameLayout, 0, layoutParams);
        this.mVideoView = XmPluginHostApi.instance().createVideoView(this, frameLayout, true, 1);
        this.mVideoView.setDistort(0.24375f, 0.04411765f);
        this.mVideoView.setTouch(false);
        this.mVideoView.setMiniScale(false);
        this.mLoadingView = LayoutInflater.from(this).inflate(R.layout.camera_progress, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        frameLayout.addView(this.mLoadingView, layoutParams2);
        this.mLoadingProgress = (TextView) this.mLoadingView.findViewById(R.id.loading_progress);
        this.mLoadingTitle = (TextView) this.mLoadingView.findViewById(R.id.loading_title);
        this.mLoadingImageView = (ImageView) this.mLoadingView.findViewById(R.id.loading_anim);
        this.mLoadingAnimation = (AnimationDrawable) getResources().getDrawable(R.drawable.camera_anim_camera_loading);
        this.mLoadingImageView.setImageDrawable(this.mLoadingAnimation);
        this.mLoadingView.setVisibility(8);
        this.mErrorRetryView = LayoutInflater.from(this).inflate(R.layout.camera_error_retry, (ViewGroup) null);
        frameLayout.addView(this.mErrorRetryView, layoutParams2);
        this.mErrorRetryView.setVisibility(8);
        this.mRetryView = this.mErrorRetryView.findViewById(R.id.retry_btn);
        this.mErrorInfoView = (TextView) this.mErrorRetryView.findViewById(R.id.error_info);
        this.mPowerOffView = LayoutInflater.from(this).inflate(R.layout.camera_closed, (ViewGroup) null);
        frameLayout.addView(this.mPowerOffView, layoutParams2);
        this.mPowerOffView.setVisibility(8);
        this.mVideoView.setVideoViewListener(new XmVideoViewGl.IVideoViewListener() {
            public void onVideoViewClick() {
                if (CameraPlayerExActivity.this.mCameraDevice.f().c()) {
                    CameraPlayerExActivity.this.videoClick();
                }
            }
        });
        this.mFrameRate = (TextView) findViewById(R.id.sub_title_bar_title);
        this.mCBMuteLandscape = (CheckBox) findViewById(R.id.land_audio);
        this.mCBMuteLandscape.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CameraPlayerExActivity.this.mCDCToggleAudio.setChecked(z);
                if (CameraPlayerExActivity.this.mCameraPlayerEx != null) {
                    if (z) {
                        CameraPlayerExActivity.this.mCameraPlayerEx.b(false);
                    } else {
                        CameraPlayerExActivity.this.mCameraPlayerEx.b(true);
                    }
                }
            }
        });
        this.mCBVoiceLandscape = (CheckBox) findViewById(R.id.cbVoiceLandscape);
        this.mCBVoiceLandscape.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SDKLog.b(CameraPlayerExActivity.TAG, "mCBVoiceLandscape:" + z);
                if (CameraPlayerExActivity.this.mCameraPlayerEx == null) {
                    return;
                }
                if (z) {
                    if (!PluginHostApiImpl.instance().checkAndRequestPermisson(CameraPlayerExActivity.this, true, (Callback<List<String>>) null, "android.permission.RECORD_AUDIO")) {
                        ToastUtil.a((Context) CameraPlayerExActivity.this, (int) R.string.audio_permission_denied);
                        CameraPlayerExActivity.this.mCBVoiceLandscape.setChecked(false);
                    } else if (!CameraPlayerExActivity.this.mCameraPlayerEx.f()) {
                        ToastUtil.a((Context) CameraPlayerExActivity.this, (int) R.string.not_on_live);
                        CameraPlayerExActivity.this.mCBVoiceLandscape.setChecked(false);
                    } else if (!CameraPlayerExActivity.this.mCameraPlayerEx.t()) {
                        CameraPlayerExActivity.this.mCameraPlayerEx.p();
                        CameraPlayerExActivity.this.mCBMuteLandscape.setChecked(true);
                    }
                } else {
                    CameraPlayerExActivity.this.mCameraPlayerEx.q();
                    CameraPlayerExActivity.this.mCBMuteLandscape.setChecked(false);
                    CameraPlayerExActivity.this.mHandler.removeMessages(2101);
                }
            }
        });
        this.mVideoView.initial();
        this.mLLRightCtrlLandscape = (LinearLayout) findViewById(R.id.llRightCtrlLandscape);
        findViewById(R.id.land_shot).setOnClickListener(this);
        findViewById(R.id.land_mixScreen).setOnClickListener(this);
        this.mRetryView.setOnClickListener(this);
        this.mBottomTools = findViewById(R.id.llBottomTools);
        this.mBottomTools.setVisibility(0);
        this.tvCloudVideo = (TextView) findViewById(R.id.tvCloudVideo);
        this.flCloudVideoTips = (FrameLayout) findViewById(R.id.fl_cloud_video_tips);
        this.mLLFuncPopup = (LinearLayout) findViewById(R.id.llFuncPopup);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        this.mTVSResolution = (TextViewS) findViewById(R.id.tvsResolution);
        this.mTVSLandResolution = (TextViewP) findViewById(R.id.tvsLandResolution);
        findViewById(R.id.tvsResolution_layout).setOnClickListener(this);
        this.mTVSLandResolution.setOnClickListener(this);
        this.ivFullScreen = (ImageView) findViewById(R.id.ivFullScreen);
        this.ivFullScreen.setOnClickListener(this);
        this.mPauseView = findViewById(R.id.pause_view);
        this.mPauseView.setOnClickListener(this);
        this.mTVAlarm = (TextView) findViewById(R.id.tvAlarm);
        this.mTVAlarm.setOnClickListener(this);
        findViewById(R.id.tvSnap).setOnClickListener(this);
        findViewById(R.id.tvStorage).setOnClickListener(this);
        findViewById(R.id.tvFloat).setOnClickListener(this);
        if (this.mCameraDevice.isShared() || !Util.a()) {
            findViewById(R.id.tvAlbum).setVisibility(0);
            findViewById(R.id.tvAlbum).setOnClickListener(this);
        } else {
            findViewById(R.id.tvCloudVideo).setVisibility(0);
            findViewById(R.id.tvCloudVideo).setOnClickListener(this);
            findViewById(R.id.tvAlbum2).setVisibility(0);
            findViewById(R.id.tvAlbum2).setOnClickListener(this);
            findViewById(R.id.viewDividerStorage2).setVisibility(0);
            findViewById(R.id.tvPlaceHolder).setVisibility(8);
            findViewById(R.id.viewDividerPlaceHolder).setVisibility(8);
        }
        this.mTvSleep = (MultiStateTextView) findViewById(R.id.tvSleep);
        this.mTVRecordTimer = (TextView) findViewById(R.id.tvRecordTimer);
        this.mLandRecordTimer = (TextView) findViewById(R.id.landRecordTimer);
        this.mRecord = (MultiStateView) findViewById(R.id.cdcCameraRecord);
        this.mBottomGroup = (BottomScrollView) findViewById(R.id.bottom_group);
        this.mMSTVVoice = (MultiStateView) findViewById(R.id.mstvVoice);
        this.mTvMore = (MultiStateTextView) findViewById(R.id.tvMore);
        this.mCDCToggleAudio = (CenterDrawableCheckBox) findViewById(R.id.cdcToggleAudio);
        this.mCDCToggleAudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CameraPlayerExActivity.this.mCBMuteLandscape.setChecked(z);
                if (z) {
                    CameraPlayerExActivity.this.mCameraPlayerEx.b(false);
                } else {
                    CameraPlayerExActivity.this.mCameraPlayerEx.b(true);
                }
            }
        });
    }

    public boolean canStepOut() {
        return canStepOut(0, 0);
    }

    public boolean canStepOut(int i, int i2) {
        if (this.mCameraPlayerEx == null) {
            return true;
        }
        if (this.mCameraPlayerEx.s() || this.mCameraPlayerEx.t()) {
            if (i > 0) {
                ToastUtil.a((Context) this, i);
            } else {
                ToastUtil.a((Context) this, (int) R.string.speaking_block);
            }
            return false;
        } else if (!this.mCameraPlayerEx.r()) {
            return true;
        } else {
            if (i2 > 0) {
                ToastUtil.a((Context) this, i2);
            } else {
                ToastUtil.a((Context) this, (int) R.string.recording_block);
            }
            return false;
        }
    }

    public void setPlayTime() {
        if (!this.mUserPause) {
            hidError();
            showLoading("");
        }
        if (this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.a((IMISSListener) new IMISSListener() {
                public void onProgress(int i) {
                }

                public void onSuccess(String str, Object obj) {
                    CameraPlayerExActivity.this.toggleRemoteAV(true, true);
                    boolean unused = CameraPlayerExActivity.this.isStartPlay = true;
                }

                public void onFailed(int i, String str) {
                    boolean unused = CameraPlayerExActivity.this.isStartPlay = false;
                }
            });
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        SDKLog.b(TAG, "newConfig.orientation:" + configuration.orientation);
        setWindow(configuration);
        if (configuration.orientation != 1) {
            this.flTitleBar.setVisibility(8);
            this.mBottomTools.setVisibility(8);
            this.mViewFloat.setVisibility(8);
            this.mLLRightCtrlLandscape.setVisibility(0);
            this.mCBVoiceLandscape.setVisibility(0);
            this.mFullScreen = true;
            this.mVideoView.setIsFull(true);
            if (this.mMSTVVoice.getCurrentState() == 1) {
                this.mCBVoiceLandscape.setChecked(true);
            } else {
                this.mCBVoiceLandscape.setChecked(false);
            }
            if (this.mCameraPlayerEx != null && this.mCameraPlayerEx.r()) {
                this.mLandRecordTimer.setVisibility(0);
                return;
            }
            return;
        }
        this.flTitleBar.setVisibility(0);
        this.mBottomTools.setVisibility(0);
        this.mViewFloat.setVisibility(0);
        this.mLLRightCtrlLandscape.setVisibility(8);
        this.mCBVoiceLandscape.setVisibility(8);
        this.mFullScreen = false;
        this.mVideoView.setIsFull(false);
        if (this.mCBVoiceLandscape.isChecked()) {
            this.mMSTVVoice.setCurrentState(1);
        } else {
            this.mMSTVVoice.setCurrentState(0);
        }
        if (this.mCameraPlayerEx != null && this.mCameraPlayerEx.r()) {
            this.mTVRecordTimer.setVisibility(0);
            this.mLandRecordTimer.setVisibility(8);
        }
    }

    public void onClick(View view) {
        if (!doOnClick(view)) {
            switch (view.getId()) {
                case R.id.ivFullScreen:
                    SDKLog.b(TAG, "ivFullScreen click");
                    Event.a("full");
                    setOrientation(true);
                    return;
                case R.id.land_mixScreen:
                    setOrientation(false);
                    return;
                case R.id.land_shot:
                case R.id.tvSnap:
                    SDKLog.b(TAG, "ivCameraShot click");
                    Event.a(Event.c);
                    snapShot();
                    return;
                case R.id.title_bar_more:
                    if (canStepOut()) {
                        if (this.mCameraDevice.isReadOnlyShared()) {
                            ToastUtil.a((Context) this, (int) R.string.auth_fail);
                            return;
                        }
                        Event.a(Event.j);
                        ArrayList arrayList = new ArrayList();
                        IXmPluginHostActivity.IntentMenuItem intentMenuItem = new IXmPluginHostActivity.IntentMenuItem();
                        intentMenuItem.name = getString(R.string.more_camera_setting);
                        intentMenuItem.intent = new Intent(this, MoreCameraSettingActivity.class);
                        intentMenuItem.intent.putExtra("gpu", isGPUDecoder());
                        intentMenuItem.intent.putExtra("extra_device_did", this.mDid);
                        arrayList.add(intentMenuItem);
                        if (this.mCameraDevice.isReadOnlyShared()) {
                            IXmPluginHostActivity.StringMenuItem stringMenuItem = new IXmPluginHostActivity.StringMenuItem();
                            stringMenuItem.name = getString(R.string.more_alarm_setting);
                            arrayList.add(stringMenuItem);
                        } else {
                            IXmPluginHostActivity.IntentMenuItem intentMenuItem2 = new IXmPluginHostActivity.IntentMenuItem();
                            intentMenuItem2.name = getString(R.string.more_alarm_setting);
                            intentMenuItem2.intent = new Intent(this, AlarmSettingV2Activity.class);
                            intentMenuItem2.intent.putExtra("extra_device_did", this.mDid);
                            arrayList.add(intentMenuItem2);
                        }
                        IXmPluginHostActivity.IntentMenuItem intentMenuItem3 = new IXmPluginHostActivity.IntentMenuItem();
                        intentMenuItem3.name = getString(R.string.more_store_setting);
                        intentMenuItem3.intent = new Intent(this, FileManagerSettingActivity.class);
                        intentMenuItem3.intent.putExtra("extra_device_did", this.mDid);
                        arrayList.add(intentMenuItem3);
                        Intent intent = new Intent();
                        intent.putExtra(DeviceMoreActivity.ARGS_SECURITY_SETTING_ENABLE, true);
                        intent.putExtra(DeviceMoreNewActivity.AUTO_DISMISS, false);
                        if (!this.mCameraDevice.isShared()) {
                            intent.putExtra("cloud_storage", true);
                            intent.putExtra("title", this.mCameraDevice.getName());
                        }
                        if (XmPluginHostApi.instance().getApiLevel() > 52) {
                            LocalLicenseUtil.LocalLicense v4LocalLicense = LocalLicenseUtil.getV4LocalLicense(getResources());
                            Intent intent2 = new Intent();
                            if (v4LocalLicense.mLicense > 0 && v4LocalLicense.mPrivacy > 0) {
                                intent2.putExtra(DeviceMoreActivity.ARGS_ENABLE_REMOVE_LICENSE, true);
                                intent2.putExtra(DeviceMoreActivity.ARGS_LICENSE_HTML_RES, v4LocalLicense.mLicense);
                                intent2.putExtra(DeviceMoreActivity.ARGS_PRIVACY_HTML_RES, v4LocalLicense.mPrivacy);
                            }
                            if (XmPluginHostApi.instance().getApiLevel() >= 67) {
                                if (!"cn".equals(LocaleUtil.a().getCountry()) || TextUtils.isEmpty(v4LocalLicense.mPlan)) {
                                    intent2.putExtra("enable_privacy_setting", false);
                                } else {
                                    intent2.putExtra("enable_privacy_setting", true);
                                    intent2.putExtra(DeviceMoreActivity.ARGS_USR_EXP_PLAN_CONTENT, Html.fromHtml(v4LocalLicense.mPlan));
                                }
                            }
                            openMoreMenu2(arrayList, true, 1220, intent, intent2);
                        } else {
                            openMoreMenu(arrayList, true, 1220, intent);
                        }
                        this.mNewFirmView.setVisibility(8);
                        SDKLog.d(Tag.b, "more");
                        return;
                    }
                    return;
                case R.id.title_bar_share:
                    if (!this.mCameraDevice.f().c()) {
                        ToastUtil.a((Context) this, (int) R.string.power_off_share);
                        return;
                    } else if (canStepOut()) {
                        this.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
                            public void onSnap(Bitmap bitmap) {
                                if (bitmap != null) {
                                    if ("cn".equals(XmPluginHostApi.instance().getGlobalSettingServer())) {
                                        bitmap = BitmapUtils.a(bitmap, CameraPlayerExActivity.this);
                                    }
                                    String a2 = FileUtil.a(false, CameraPlayerExActivity.this.mCameraDevice.getDid());
                                    if (a2 != null) {
                                        try {
                                            FileOutputStream fileOutputStream = new FileOutputStream(a2);
                                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                                            fileOutputStream.close();
                                            bitmap.recycle();
                                            CameraPlayerExActivity.this.openShareMediaActivity(CameraPlayerExActivity.this.mDeviceStat.name, "", a2);
                                        } catch (IOException unused) {
                                        }
                                    }
                                }
                            }
                        });
                        return;
                    } else {
                        return;
                    }
                case R.id.tvAlarm:
                    SDKLog.b(TAG, "tvAlarm click");
                    Event.a(Event.d);
                    if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        if (!this.mCameraDevice.e().j() || this.mCameraDevice.isReadOnlyShared()) {
                            startActivity(new Intent(this, AlarmV2Activity.class));
                            return;
                        } else {
                            startActivity(new Intent(this, AlarmGuideActivity.class));
                            return;
                        }
                    } else {
                        return;
                    }
                case R.id.tvAlbum:
                case R.id.tvAlbum2:
                    if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        SDKLog.b(TAG, "R.id.tvAlbum");
                        Event.a("album");
                        startActivity(new Intent(this, AlbumActivity.class));
                        return;
                    }
                    return;
                case R.id.tvCloudVideo:
                    if (!canStepOut(R.string.speaking_block, R.string.recording_block) || !AlarmNetUtils.b() || !AlarmNetUtils.c()) {
                        return;
                    }
                    if (this.mCameraDevice.isShared()) {
                        ToastUtil.a((Context) this, (int) R.string.cloud_share_hint);
                        return;
                    }
                    if (AlarmNetUtils.e()) {
                        if (AlarmNetUtils.d()) {
                            Event.p();
                            FrameManager.b().k().openCloudVideoListActivity(this, this.mCameraDevice.getDid(), this.mCameraDevice.getName());
                        } else {
                            CloudVideoNetUtils.getInstance().openCloudVideoBuyPage(this, this.mCameraDevice.getDid());
                        }
                    }
                    this.mCameraDevice.e().j(true);
                    this.flCloudVideoTips.setVisibility(8);
                    return;
                case R.id.tvFloat:
                    SDKLog.b(TAG, "tvPIP click");
                    if (!this.mCameraDevice.f().c()) {
                        ToastUtil.a((Context) this, (int) R.string.power_off);
                        return;
                    } else if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        Event.a(Event.m);
                        if (com.Utils.Util.a((Context) this)) {
                            XmPluginHostApi.instance().openCameraFloatingWindow(this.mCameraDevice.getDid());
                            finish();
                            return;
                        }
                        ToastUtil.a((Context) this, (CharSequence) getResources().getString(R.string.float_tip));
                        return;
                    } else {
                        return;
                    }
                case R.id.tvMore:
                    SDKLog.b(TAG, "tvMore click");
                    if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        final int i = (int) (((float) AppConfig.b) - (AppConfig.d * 72.0f));
                        SDKLog.b(TAG, "test " + i);
                        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i});
                        ofInt.setInterpolator(new LinearInterpolator());
                        ofInt.setDuration(500);
                        ofInt.start();
                        ofInt.addListener(new AnimatorListenerAdapter() {
                            public void onAnimationCancel(Animator animator) {
                                super.onAnimationCancel(animator);
                                CameraPlayerExActivity.this.mBottomGroup.setScrollX(i);
                            }

                            public void onAnimationEnd(Animator animator) {
                                super.onAnimationEnd(animator);
                                CameraPlayerExActivity.this.mBottomGroup.setScrollX(i);
                            }
                        });
                        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                CameraPlayerExActivity.this.mBottomGroup.setScrollX(intValue);
                                SDKLog.b(CameraPlayerExActivity.TAG, "test222 " + intValue);
                            }
                        });
                        return;
                    }
                    return;
                case R.id.tvStorage:
                    Event.o();
                    if (!this.mCameraDevice.f().c()) {
                        ToastUtil.a((Context) this, (int) R.string.power_off);
                        return;
                    } else if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        int a2 = this.mCameraDevice.f().a(CameraPropertyBase.k);
                        if (this.mCameraDevice.c().i == null || a2 == 1 || a2 == 5) {
                            startActivity(new Intent(this, NoMemoryCardActivity.class));
                            return;
                        } else {
                            startActivity(new Intent(this, SDCardActivity.class));
                            return;
                        }
                    } else {
                        return;
                    }
                case R.id.tvsLandResolution:
                case R.id.tvsResolution_layout:
                    SDKLog.b(TAG, "tvsResolution click");
                    if (!this.mCameraDevice.f().c()) {
                        ToastUtil.a((Context) this, (int) R.string.power_off);
                        return;
                    } else {
                        toggleResolution();
                        return;
                    }
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void doStartRecord() {
        Event.a(Event.e);
        if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.r()) {
            this.mLastTime = -1;
            this.mCurrentTime = 0;
            if (!this.mCameraDevice.f().c()) {
                ToastUtil.a((Context) this, (int) R.string.power_off);
                this.mRecord.setCurrentState(0);
                this.mLandRecord.setCurrentState(0);
                return;
            }
            String a2 = FileUtil.a(true, this.mCameraDevice.getDid());
            if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.l()) {
                ToastUtil.a((Context) this, (int) R.string.record_not_connect);
                this.mRecord.setCurrentState(0);
                this.mLandRecord.setCurrentState(0);
                return;
            }
            if (!PluginHostApiImpl.instance().checkAndRequestPermisson(this, true, (Callback<List<String>>) null, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                ToastUtil.a((Context) this, (int) R.string.no_write_permission);
                this.mRecord.setCurrentState(0);
                this.mLandRecord.setCurrentState(0);
            } else if (!TextUtils.isEmpty(a2)) {
                this.mCameraPlayerEx.a(a2, 1);
                if (this.mFullScreen) {
                    this.mLandRecordTimer.setText(getString(R.string.main_recording, new Object[]{"00:00:00"}));
                    this.mLandRecordTimer.setVisibility(0);
                } else {
                    this.mTVRecordTimer.setText(getString(R.string.main_recording, new Object[]{"00:00:00"}));
                    this.mTVRecordTimer.setVisibility(0);
                }
                SDKLog.b(TAG, "startRecord");
                this.mRecord.setCurrentState(1);
                this.mLandRecord.setCurrentState(1);
            } else {
                ToastUtil.a((Context) this, (int) R.string.snip_video_failed);
                this.mRecord.setCurrentState(0);
                this.mLandRecord.setCurrentState(0);
            }
        } else {
            this.mRecord.setCurrentState(1);
            this.mLandRecord.setCurrentState(1);
        }
    }

    /* access modifiers changed from: protected */
    public void doStopRecord() {
        this.mLastTime = -1;
        this.mCurrentTime = 0;
        if (this.mCameraPlayerEx != null && this.mCameraPlayerEx.r()) {
            this.mCameraPlayerEx.a((XmMp4Record.IRecordListener) new XmMp4Record.IRecordListener() {
                public void onSuccess(final String str) {
                    CameraPlayerExActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            CameraPlayerExActivity.this.mTVRecordTimer.setVisibility(8);
                            CameraPlayerExActivity.this.mLandRecordTimer.setVisibility(8);
                            CameraPlayerExActivity.this.onVideoRecorded(str);
                            if (CameraPlayerExActivity.this.mRecord != null) {
                                CameraPlayerExActivity.this.mRecord.setCurrentState(0);
                                CameraPlayerExActivity.this.mLandRecord.setCurrentState(0);
                            }
                        }
                    });
                }

                public void onFailed(final int i, String str) {
                    CameraPlayerExActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            CameraPlayerExActivity.this.mTVRecordTimer.setVisibility(8);
                            CameraPlayerExActivity.this.mLandRecordTimer.setVisibility(8);
                            if (CameraPlayerExActivity.this.mRecord != null) {
                                CameraPlayerExActivity.this.mRecord.setCurrentState(0);
                                CameraPlayerExActivity.this.mLandRecord.setCurrentState(0);
                            }
                            if (i == -2) {
                                ToastUtil.a((Context) CameraPlayerExActivity.this, (int) R.string.snip_video_failed_time_mini);
                            } else {
                                ToastUtil.a((Context) CameraPlayerExActivity.this, (int) R.string.snip_video_failed);
                            }
                        }
                    });
                }
            });
        } else if (this.mRecord != null) {
            this.mRecord.setCurrentState(0);
            this.mLandRecord.setCurrentState(0);
        }
    }

    /* access modifiers changed from: protected */
    public void doStopCall() {
        if (this.mCameraPlayerEx != null && this.mCameraPlayerEx.t()) {
            this.mCameraPlayerEx.q();
            this.mHandler.removeMessages(2101);
        }
        if (this.mMSTVVoice != null && this.mMSTVVoice.getCurrentState() == 1) {
            this.mMSTVVoice.setCurrentState(0);
        }
        if (this.mCBVoiceLandscape != null && this.mCBVoiceLandscape.isChecked()) {
            this.mCBVoiceLandscape.setChecked(false);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 1220) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 == -1 && intent != null) {
            String stringExtra = intent.getStringExtra("result_data");
            if (TextUtils.isEmpty(stringExtra) || !stringExtra.equals(DeviceMoreActivity.ARGS_RESULT_REMOVE_LICENSE)) {
                String stringExtra2 = intent.getStringExtra("menu");
                if (!TextUtils.isEmpty(stringExtra2) && stringExtra2.equals(getString(R.string.more_alarm_setting))) {
                    Toast.makeText(this, R.string.auth_fail, 0).show();
                    return;
                }
                return;
            }
            this.mCameraDevice.e().g(true);
            finish();
        }
    }

    /* access modifiers changed from: private */
    public void setResolutionText(int i) {
        if (this.mTVSResolution != null) {
            switch (i) {
                case 1:
                    this.mTVSResolution.setText(R.string.quality_low);
                    this.mTVSLandResolution.setText(R.string.quality_low);
                    return;
                case 2:
                case 3:
                    this.mTVSResolution.setText(R.string.quality_fhd);
                    this.mTVSLandResolution.setText(R.string.quality_fhd);
                    return;
                default:
                    this.mTVSResolution.setText(R.string.quality_auto);
                    this.mTVSLandResolution.setText(R.string.quality_auto);
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setOrientation(boolean z) {
        if (!z) {
            setRequestedOrientation(1);
        } else if (!this.mCameraDevice.f().c()) {
            ToastUtil.a((Context) this, (int) R.string.power_off);
        } else {
            setRequestedOrientation(0);
        }
    }

    public void onResume() {
        super.onResume();
        TimeUtils.b();
        isCloudVideoUser();
        this.mCameraDevice.updateDeviceStatus();
        if (this.mCameraDevice.n()) {
            this.mCameraDevice.d().j();
            this.mCameraDevice.d().a(40000);
        } else {
            this.mCameraDevice.c().j();
            this.mCameraDevice.c().a(40000);
        }
        if (this.mCameraDevice.isOwner()) {
            this.mCameraDevice.checkDeviceUpdateInfo(new Callback<DeviceUpdateInfo>() {
                public void onFailure(int i, String str) {
                }

                public void onSuccess(DeviceUpdateInfo deviceUpdateInfo) {
                    Message.obtain(CameraPlayerExActivity.this.mHandler, 1, deviceUpdateInfo).sendToTarget();
                }
            });
        }
        ((TextView) findViewById(R.id.title_bar_title)).setText(this.mCameraDevice.getName());
        if (!this.mNeedLicense) {
            if (this.mCameraPlayerEx == null) {
                if (this.mNeedPincode) {
                    this.mHandler.removeMessages(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
                    this.mHandler.sendEmptyMessageDelayed(AMapException.CODE_AMAP_NEARBY_INVALID_USERID, 1000);
                } else {
                    this.mHandler.removeMessages(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
                    this.mHandler.sendEmptyMessage(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
                }
            }
            this.mHandler.sendEmptyMessage(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
        }
        if (this.mTimeUpdateView != null && this.mTimeUpdateView.getVisibility() == 0) {
            this.mTimeUpdateView.setVisibility(8);
        }
        this.mNeedPincode = false;
    }

    public void onStop() {
        super.onStop();
        this.mHandler.removeMessages(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
        doStopCall();
        doStopRecord();
    }

    public void onPause() {
        if (this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.j();
        }
        dismissSnapshotPopupRunnable(0);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void resumeCamera() {
        if (this.mPauseView != null) {
            this.mPauseView.setVisibility(8);
        }
        if (NetworkUtil.c(this)) {
            this.mAllowMobileNetwork = true;
        }
        if (!this.isStartPlay) {
            hidError();
            showLoading("");
            if (this.mCameraPlayerEx != null) {
                this.mCameraPlayerEx.a((IMISSListener) new IMISSListener() {
                    public void onProgress(int i) {
                    }

                    public void onSuccess(String str, Object obj) {
                        CameraPlayerExActivity.this.toggleRemoteAV(true, true);
                        boolean unused = CameraPlayerExActivity.this.isStartPlay = true;
                    }

                    public void onFailed(int i, String str) {
                        boolean unused = CameraPlayerExActivity.this.isStartPlay = false;
                    }
                });
            }
        } else {
            setPlayTime();
        }
        refreshUI();
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        int i = message.what;
        if (i != 1) {
            if (i == 3051) {
                this.mCameraDevice.b(getContext(), (Callback<Integer[]>) new Callback<Integer[]>() {
                    public void onSuccess(Integer[] numArr) {
                        if (numArr != null) {
                            if (numArr[0].intValue() == 0) {
                                CameraPlayerExActivity.this.hideUpdateIng(true);
                                CameraPlayerExActivity.this.mHandler.removeMessages(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
                            } else if (numArr[1].intValue() < 100) {
                                CameraPlayerExActivity.this.showUpdateIng(numArr[1].intValue());
                                CameraPlayerExActivity.this.mHandler.sendEmptyMessageDelayed(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS, com.xiaomi.smarthome.download.Constants.x);
                            } else {
                                CameraPlayerExActivity.this.hideUpdateIng(true);
                                CameraPlayerExActivity.this.mHandler.removeMessages(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
                                CameraPlayerExActivity.this.startPlay();
                            }
                        }
                    }

                    public void onFailure(int i, String str) {
                        CameraPlayerExActivity.this.hideUpdateIng(false);
                        CameraPlayerExActivity.this.mHandler.removeMessages(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
                    }
                });
            } else if (i != 4000) {
                switch (i) {
                    case AMapException.CODE_AMAP_NEARBY_INVALID_USERID:
                        hidError();
                        if (!NetworkUtil.c(this) || this.mAllowMobileNetwork) {
                            hidError();
                            showLoading(getString(R.string.camera_play_initial_0));
                        }
                        startPlay();
                        return;
                    case 2101:
                        if (this.mCameraPlayerEx != null && this.mCameraPlayerEx.t()) {
                            this.mHandler.sendEmptyMessageDelayed(2101, 500);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } else {
                this.mNeedSpeed = false;
                this.mTimeUpdateView.setVisibility(8);
            }
        } else if (((DeviceUpdateInfo) message.obj).mHasNewFirmware) {
            this.mNewFirmView.setVisibility(0);
        }
    }

    public void onBackPressed() {
        if (getRequestedOrientation() != 1) {
            setOrientation(false);
        } else if (canStepOut()) {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void startPlay() {
        if (TextUtils.isEmpty(this.mCameraDevice.getModel()) || TextUtils.isEmpty(this.mCameraDevice.getDid())) {
            showError(getString(R.string.camera_play_error2));
        } else if (this.mCameraDevice.f().c()) {
            if (this.mPauseView.getVisibility() == 0) {
                this.mPauseView.setVisibility(8);
            }
            if (this.mCameraPlayerEx == null) {
                this.mCameraPlayerEx = new CameraPlayerEx(this, this.mCameraDevice, this, this.mVideoView);
                this.mCameraPlayerEx.a(this.mIRecodeTimeListener);
            }
            if (!NetworkUtil.c(this) || this.mAllowMobileNetwork) {
                hidError();
                showLoading("");
                this.mCameraPlayerEx.a((IMISSListener) new IMISSListener() {
                    public void onProgress(int i) {
                    }

                    public void onSuccess(String str, Object obj) {
                        CameraPlayerExActivity.this.toggleRemoteAV(true, true);
                        boolean unused = CameraPlayerExActivity.this.isStartPlay = true;
                    }

                    public void onFailed(int i, String str) {
                        boolean unused = CameraPlayerExActivity.this.isStartPlay = false;
                    }
                });
                return;
            }
            pauseCamera();
        } else if (this.mPowerOffView.getVisibility() == 8) {
            this.mPowerOffView.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public void refreshUI() {
        if (!this.mCameraDevice.f().c()) {
            this.mTvSleep.setCurrentState(0);
            if (this.mPowerOffView.getVisibility() == 8) {
                this.mPowerOffView.setVisibility(0);
                if (this.mCameraPlayerEx != null) {
                    this.mCameraPlayerEx.j();
                }
                if (this.mPauseView.getVisibility() == 0) {
                    this.mPauseView.setVisibility(8);
                    return;
                }
                return;
            }
            return;
        }
        this.mTvSleep.setCurrentState(1);
        if (this.mPowerOffView.getVisibility() == 0) {
            this.mPowerOffView.setVisibility(8);
            startPlay();
        }
    }

    public void onDestroy() {
        if (this.mVideoView != null) {
            this.mVideoView.release();
        }
        if (this.mCameraDevice != null) {
            if (this.mCameraDevice.n()) {
                this.mCameraDevice.d().b();
            } else {
                this.mCameraDevice.c().b();
            }
        }
        if (this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.a(true);
        }
        if (this.mNetworkMonitor != null) {
            this.mNetworkMonitor.unregister();
        }
        super.onDestroy();
        clearAllCacheRecording();
    }

    /* access modifiers changed from: protected */
    public void doResume() {
        this.mPauseView.setVisibility(8);
        setPlayTime();
    }

    /* access modifiers changed from: private */
    public void toggleRemoteAV(boolean z, boolean z2) {
        if (this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.a(z, (IMISSListener) null);
            this.mCameraPlayerEx.b(z2, (IMISSListener) null);
        }
    }

    /* access modifiers changed from: private */
    public void videoClick() {
        if (getRequestedOrientation() != 1) {
            if (this.mLLRightCtrlLandscape.getVisibility() == 0) {
                this.mLLRightCtrlLandscape.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_top));
                this.mLLRightCtrlLandscape.setVisibility(8);
                this.mCBVoiceLandscape.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_ctrl_view_x_trans_out));
                this.mCBVoiceLandscape.setVisibility(8);
                return;
            }
            this.mLLRightCtrlLandscape.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_top));
            this.mLLRightCtrlLandscape.setVisibility(0);
            this.mCBVoiceLandscape.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_ctrl_view_x_trans_in));
            this.mCBVoiceLandscape.setVisibility(0);
        } else if (this.mViewFloat.getTranslationY() > 0.0f) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mViewFloat, "translationY", new float[]{(float) this.mViewFloat.getHeight(), 0.0f});
            ofFloat.setDuration(200);
            ofFloat.start();
        } else {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mViewFloat, "translationY", new float[]{0.0f, (float) this.mViewFloat.getHeight()});
            ofFloat2.setDuration(200);
            ofFloat2.start();
        }
    }

    private void snapShot() {
        if (this.isSnapshotting) {
            ToastUtil.a((Context) this, (int) R.string.btn_click_too_much);
            return;
        }
        this.isSnapshotting = true;
        if (!this.mCameraDevice.f().c()) {
            ToastUtil.a((Context) this, (int) R.string.power_off);
            this.isSnapshotting = false;
            return;
        }
        if (!PluginHostApiImpl.instance().checkAndRequestPermisson(this, true, (Callback<List<String>>) null, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            ToastUtil.a((Context) this, (int) R.string.no_write_permission);
            this.isSnapshotting = false;
        } else if (this.mCameraDevice == null || this.mCameraDevice.f() == null || !this.mCameraDevice.f().c()) {
            this.isSnapshotting = false;
            ToastUtil.a((Context) this, (int) R.string.power_off);
        } else if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.l()) {
            this.isSnapshotting = false;
            ToastUtil.a((Context) this, (int) R.string.snap_failed_paused);
        } else {
            this.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
                public void onSnap(final Bitmap bitmap) {
                    CameraPlayerExActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            CameraPlayerExActivity.this.onSnapShot(bitmap);
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void onSnapShot(Bitmap bitmap) {
        final String a2 = FileUtil.a(false, this.mCameraDevice.getDid());
        if (a2 == null || bitmap == null) {
            this.isSnapshotting = false;
            return;
        }
        Bitmap a3 = BitmapUtils.a(bitmap, this);
        if (a3 != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(a2);
                a3.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.close();
            } catch (IOException unused) {
                this.isSnapshotting = false;
                return;
            }
        }
        final Bitmap createScaledBitmap = Bitmap.createScaledBitmap(a3, 200, (a3.getHeight() * 200) / a3.getWidth(), false);
        runOnUiThread(new Runnable() {
            public void run() {
                if (new File(a2).exists()) {
                    ImageView imageView = (ImageView) CameraPlayerExActivity.this.findViewById(R.id.ivShotPic);
                    if (CameraPlayerExActivity.this.mLLFuncPopup.getVisibility() == 0) {
                        CameraPlayerExActivity.this.mLLFuncPopup.setVisibility(8);
                    }
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CameraPlayerExActivity.this.mLLFuncPopup.getLayoutParams();
                    layoutParams.bottomMargin = CameraPlayerExActivity.this.mViewFloat.getHeight() + Util.a((Context) CameraPlayerExActivity.this.activity(), 6.0f);
                    CameraPlayerExActivity.this.mLLFuncPopup.setLayoutParams(layoutParams);
                    CameraPlayerExActivity.this.mLLFuncPopup.startAnimation(AnimationUtils.loadAnimation(CameraPlayerExActivity.this.activity(), R.anim.anim_snap_shot_in));
                    CameraPlayerExActivity.this.mLLFuncPopup.setVisibility(0);
                    CameraPlayerExActivity.this.dismissSnapshotPopupRunnable(3000);
                    if (createScaledBitmap != null) {
                        imageView.setImageBitmap(createScaledBitmap);
                    }
                    ContentValues contentValues = new ContentValues(4);
                    contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
                    contentValues.put(Downloads._DATA, a2);
                    contentValues.put("mime_type", "image/jpeg");
                    try {
                        if (!Build.MODEL.equals("HM 1SC")) {
                            CameraPlayerExActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                        }
                        SDKLog.b(CameraPlayerExActivity.TAG, "snap success");
                        boolean unused = CameraPlayerExActivity.this.isSnapshotting = false;
                        final LocalFileManager.LocalFile b = CameraPlayerExActivity.this.mCameraDevice.b().b(a2);
                        if (b != null) {
                            imageView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    if (CameraPlayerExActivity.this.canStepOut(R.string.speaking_block, R.string.recording_block)) {
                                        CameraPlayerExActivity.this.dismissSnapshotPopupRunnable(0);
                                        Intent intent = new Intent(CameraPlayerExActivity.this, LocalPicReviewActivity.class);
                                        intent.putExtra("file_path", b.d);
                                        CameraPlayerExActivity.this.startActivity(intent);
                                    }
                                }
                            });
                        }
                    } catch (Throwable unused2) {
                        boolean unused3 = CameraPlayerExActivity.this.isSnapshotting = false;
                    }
                } else {
                    boolean unused4 = CameraPlayerExActivity.this.isSnapshotting = false;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void dismissSnapshotPopupRunnable(long j) {
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (CameraPlayerExActivity.this.mLLFuncPopup != null && CameraPlayerExActivity.this.mLLFuncPopup.getVisibility() != 8) {
                    CameraPlayerExActivity.this.mLLFuncPopup.startAnimation(AnimationUtils.loadAnimation(CameraPlayerExActivity.this.activity(), R.anim.anim_snap_shot_out));
                    CameraPlayerExActivity.this.mLLFuncPopup.setVisibility(8);
                }
            }
        }, j);
    }

    /* access modifiers changed from: private */
    public void onVideoRecorded(String str) {
        File file = new File(str);
        if (file.exists()) {
            sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
            final LocalFileManager.LocalFile b = this.mCameraDevice.b().b(str);
            if (b != null && this.mVideoView != null) {
                this.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
                    public void onSnap(Bitmap bitmap) {
                        if (bitmap != null) {
                            final Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, 200, (bitmap.getHeight() * 200) / bitmap.getWidth(), false);
                            CameraPlayerExActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    if (createScaledBitmap != null) {
                                        ImageView imageView = (ImageView) CameraPlayerExActivity.this.findViewById(R.id.ivShotPic);
                                        imageView.setImageBitmap(createScaledBitmap);
                                        if (CameraPlayerExActivity.this.mLLFuncPopup.getVisibility() == 0) {
                                            CameraPlayerExActivity.this.mLLFuncPopup.setVisibility(8);
                                        }
                                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CameraPlayerExActivity.this.mLLFuncPopup.getLayoutParams();
                                        layoutParams.bottomMargin = CameraPlayerExActivity.this.mViewFloat.getHeight() + Util.a((Context) CameraPlayerExActivity.this.activity(), 6.0f);
                                        CameraPlayerExActivity.this.mLLFuncPopup.setLayoutParams(layoutParams);
                                        CameraPlayerExActivity.this.mLLFuncPopup.startAnimation(AnimationUtils.loadAnimation(CameraPlayerExActivity.this.activity(), R.anim.anim_snap_shot_in));
                                        CameraPlayerExActivity.this.mLLFuncPopup.setVisibility(0);
                                        CameraPlayerExActivity.this.dismissSnapshotPopupRunnable(3000);
                                        imageView.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View view) {
                                                if (CameraPlayerExActivity.this.canStepOut(R.string.speaking_block, R.string.recording_block)) {
                                                    CameraPlayerExActivity.this.dismissSnapshotPopupRunnable(0);
                                                    Intent intent = new Intent(CameraPlayerExActivity.this, LocalVideoPlayActivity.class);
                                                    intent.putExtra("file_path", b.d);
                                                    CameraPlayerExActivity.this.startActivity(intent);
                                                }
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

    private void toggleResolution() {
        if (this.mCameraPlayerEx != null && !this.mCameraPlayerEx.f()) {
            ToastUtil.a((Context) this, (int) R.string.history_video_resolution_hd_only);
        } else if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.r()) {
            Event.a(Event.h);
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
            builder.a((int) R.string.quality_choice);
            final String[] strArr = {getString(R.string.quality_auto), getString(R.string.quality_low), getString(R.string.quality_fhd)};
            this.selectedIndex = this.mCameraDevice.e().d();
            if (this.selectedIndex == 3) {
                this.selectedIndex = 2;
            }
            builder.a((CharSequence[]) strArr, this.selectedIndex, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CameraPlayerExActivity.this.selectedIndex = i;
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (CameraPlayerExActivity.this.selectedIndex >= 0 && CameraPlayerExActivity.this.selectedIndex < strArr.length && !TextUtils.isEmpty(strArr[CameraPlayerExActivity.this.selectedIndex])) {
                        CameraPlayerExActivity.this.setResolutionText(CameraPlayerExActivity.this.selectedIndex);
                        if (CameraPlayerExActivity.this.selectedIndex == 2) {
                            CameraPlayerExActivity.this.selectedIndex = 3;
                        }
                        CameraPlayerExActivity.this.mCameraDevice.e().b(CameraPlayerExActivity.this.selectedIndex);
                        if (CameraPlayerExActivity.this.mCameraPlayerEx != null) {
                            CameraPlayerExActivity.this.mCameraPlayerEx.a(CameraPlayerExActivity.this.selectedIndex);
                        }
                    }
                }
            }).d();
        } else {
            ToastUtil.a((Context) this, (int) R.string.record_resolution_block);
        }
    }

    /* access modifiers changed from: private */
    public void upDateTimeTitle(int i) {
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String format = String.format("%s:%s:%s", new Object[]{decimalFormat.format((long) (i / 3600)), decimalFormat.format((long) ((i / 60) % 60)), decimalFormat.format((long) (i % 60))});
        this.mTVRecordTimer.setText(getString(R.string.main_recording, new Object[]{format}));
        this.mLandRecordTimer.setText(getString(R.string.main_recording, new Object[]{format}));
    }

    /* access modifiers changed from: protected */
    public void detectSDCard() {
        if (this.mCameraDevice == null) {
            return;
        }
        if (this.mCameraDevice.n()) {
            this.mCameraDevice.d().b((Callback<SDCardInfo>) new Callback<SDCardInfo>() {
                public void onSuccess(SDCardInfo sDCardInfo) {
                    SDKLog.b(CameraPlayerExActivity.TAG, "detectSDCard onSuccess:" + sDCardInfo.e);
                    if (!CameraPlayerExActivity.this.mCameraDevice.e().f8058a) {
                        return;
                    }
                    if (sDCardInfo.e == 1 || sDCardInfo.e == 3 || sDCardInfo.e == 5) {
                        CameraPlayerExActivity.this.showSDCardHintDialog();
                    }
                }

                public void onFailure(int i, String str) {
                    SDKLog.b(CameraPlayerExActivity.TAG, "detectSDCard onFailure:" + i + " s:" + str);
                }
            }, true);
        } else {
            this.mCameraDevice.c().b((Callback<SDCardInfo>) new Callback<SDCardInfo>() {
                public void onSuccess(SDCardInfo sDCardInfo) {
                    SDKLog.b(CameraPlayerExActivity.TAG, "detectSDCard onSuccess:" + sDCardInfo.e);
                    if (!CameraPlayerExActivity.this.mCameraDevice.e().f8058a) {
                        return;
                    }
                    if (sDCardInfo.e == 1 || sDCardInfo.e == 3 || sDCardInfo.e == 5) {
                        CameraPlayerExActivity.this.showSDCardHintDialog();
                    }
                }

                public void onFailure(int i, String str) {
                    SDKLog.b(CameraPlayerExActivity.TAG, "detectSDCard onFailure:" + i + " s:" + str);
                }
            }, true);
        }
    }

    /* access modifiers changed from: private */
    public void showSDCardHintDialog() {
        this.mSDCardHintDialog = new SDCardHintDialog(this, R.style.popupDialog);
        this.mSDCardHintDialog.show();
        this.mSDCardHintDialog.setCancelable(true);
        this.mCameraDevice.e().f8058a = false;
    }

    /* access modifiers changed from: private */
    public void checkMinLevel() {
        if (this.mCameraDevice.f().a(CameraProperties.f7888a) > 1) {
            showUpdateDlg(false);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && !this.mIsInit) {
            this.mIsInit = true;
            if (this.mCameraDevice.e().h()) {
                initGuideCenter();
            }
        }
    }

    private void showLicense() {
        if (this.mCameraDevice.e().i()) {
            this.mIsInit = true;
            this.mNeedLicense = true;
            LocalLicenseUtil.LocalLicense v4LocalLicense = LocalLicenseUtil.getV4LocalLicense(getResources());
            if (v4LocalLicense.mLicense <= 0 || v4LocalLicense.mPrivacy <= 0) {
                SDKLog.e(Tag.b, "load raw fail");
                this.mCameraDevice.e().g(false);
                if (this.mCameraDevice.e().h()) {
                    initGuideCenter();
                }
            } else if (XmPluginHostApi.instance().getApiLevel() >= 67) {
                Intent intent = new Intent();
                if ("cn".equals(XmPluginHostApi.instance().getGlobalSettingServer())) {
                    intent.putExtra("enable_privacy_setting", true);
                    intent.putExtra(DeviceMoreActivity.ARGS_USR_EXP_PLAN_CONTENT, v4LocalLicense.mPlan);
                } else {
                    intent.putExtra("enable_privacy_setting", false);
                }
                if (this.mDeviceStat != null && !TextUtils.isEmpty(this.mDeviceStat.did)) {
                    showUserLicenseDialog(getString(R.string.privacy_title), getString(R.string.licences_content), v4LocalLicense.mLicense, getString(R.string.privacy_content), v4LocalLicense.mPrivacy, new View.OnClickListener() {
                        public void onClick(View view) {
                            boolean unused = CameraPlayerExActivity.this.mNeedLicense = false;
                            CameraPlayerExActivity.this.mHandler.removeMessages(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
                            CameraPlayerExActivity.this.mHandler.sendEmptyMessage(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
                            CameraPlayerExActivity.this.mHandler.sendEmptyMessage(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
                            if (CameraPlayerExActivity.this.mCameraDevice != null) {
                                CameraPlayerExActivity.this.mCameraDevice.e().g(false);
                                if (CameraPlayerExActivity.this.mCameraDevice.e().h()) {
                                    CameraPlayerExActivity.this.initGuideCenter();
                                }
                                boolean isUsrExpPlanEnabled = XmPluginHostApi.instance().isUsrExpPlanEnabled(CameraPlayerExActivity.this.mDid);
                                Event.ch = isUsrExpPlanEnabled;
                                CameraPlayerExActivity.this.mCameraDevice.a(isUsrExpPlanEnabled, (Callback<Void>) null);
                            }
                        }
                    }, intent);
                }
            }
        }
    }

    private void clearAllCacheRecording() {
        new Thread(new Runnable() {
            public void run() {
                File[] listFiles;
                String c = FileUtil.c(CameraPlayerExActivity.this.mCameraDevice.getDid());
                if (!TextUtils.isEmpty(c)) {
                    File file = new File(c);
                    if (file.exists() && (listFiles = file.listFiles()) != null) {
                        for (File delete : listFiles) {
                            delete.delete();
                        }
                    }
                }
            }
        }).start();
    }

    private void showGuide() {
        GuidePage1 guidePage1 = new GuidePage1(this, R.style.guide_dialog);
        guidePage1.show();
        guidePage1.setCancelable(true);
        this.mCameraDevice.e().f(false);
    }

    private void showUpdateDlg(final boolean z) {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        builder.a((int) R.string.api_tip);
        builder.b((int) R.string.api_tip_title);
        builder.a(false);
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!CameraPlayerExActivity.this.isFinishing()) {
                    dialogInterface.dismiss();
                    CameraPlayerExActivity.this.finish();
                }
            }
        });
        builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!CameraPlayerExActivity.this.isFinishing()) {
                    if (z) {
                        CameraPlayerExActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(AppCode.u)));
                    }
                    dialogInterface.dismiss();
                    CameraPlayerExActivity.this.finish();
                }
            }
        });
        builder.d();
    }

    private void initMulti() {
        this.mTvSleep.addState(new MultiStateTextView.StateItem(R.string.wake_up, R.drawable.camera_icon_wakeup, new View.OnClickListener() {
            public void onClick(View view) {
                if (CameraPlayerExActivity.this.mCameraDevice.isReadOnlyShared()) {
                    Toast.makeText(CameraPlayerExActivity.this, R.string.auth_fail, 0).show();
                } else if (CameraPlayerExActivity.this.mCameraDevice != null && CameraPlayerExActivity.this.mCameraDevice.f() != null) {
                    CameraPlayerExActivity.this.mCameraDevice.f().a(CameraPropertyBase.l, true, (Callback<Void>) new Callback<Void>() {
                        public void onSuccess(Void voidR) {
                            CameraPlayerExActivity.this.mTvSleep.setCurrentState(1);
                            SDKLog.b(CameraPlayerExActivity.TAG, "set wakeup success");
                        }

                        public void onFailure(int i, String str) {
                            ToastUtil.a((Context) CameraPlayerExActivity.this, (int) R.string.action_fail);
                            SDKLog.b(CameraPlayerExActivity.TAG, "set wakeup failed:" + i + " s:" + str);
                        }
                    });
                }
            }
        }));
        this.mTvSleep.addState(new MultiStateTextView.StateItem(R.string.sleep, R.drawable.camera_icon_sleep, new View.OnClickListener() {
            public void onClick(View view) {
                Event.a(Event.l);
                if (CameraPlayerExActivity.this.mCameraDevice.isReadOnlyShared()) {
                    Toast.makeText(CameraPlayerExActivity.this, R.string.auth_fail, 0).show();
                } else if (CameraPlayerExActivity.this.canStepOut(R.string.speaking_block, R.string.recording_block) && CameraPlayerExActivity.this.mCameraDevice != null && CameraPlayerExActivity.this.mCameraDevice.f() != null) {
                    CameraPlayerExActivity.this.mCameraDevice.f().a(CameraPropertyBase.l, false, (Callback<Void>) new Callback<Void>() {
                        public void onSuccess(Void voidR) {
                            if (!CameraPlayerExActivity.this.isFinishing()) {
                                CameraPlayerExActivity.this.mTvSleep.setCurrentState(0);
                                CameraPlayerExActivity.this.mCameraDevice.g().a(CameraPropertyBase.l);
                                SDKLog.b(CameraPlayerExActivity.TAG, "set sleep success");
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (!CameraPlayerExActivity.this.isFinishing()) {
                                ToastUtil.a((Context) CameraPlayerExActivity.this, (int) R.string.action_fail);
                                SDKLog.b(CameraPlayerExActivity.TAG, "set sleep failed:" + i + " s:" + str);
                            }
                        }
                    });
                }
            }
        }));
        this.mTvSleep.setCurrentState(this.mCameraDevice.f().c() ? 1 : 0);
        this.mRecord.addState(new MultiStateView.StateItem(R.drawable.camera_icon_camera_record_v4, new View.OnClickListener() {
            public void onClick(View view) {
                CameraPlayerExActivity.this.doStartRecord();
            }
        }));
        this.mRecord.addState(new MultiStateView.StateItem(R.drawable.camera_icon_camera_recording_v4, new View.OnClickListener() {
            public void onClick(View view) {
                CameraPlayerExActivity.this.doStopRecord();
            }
        }));
        this.mRecord.setCurrentState(0);
        this.mLandRecord = (MultiStateView) findViewById(R.id.land_record);
        this.mLandRecord.addState(new MultiStateView.StateItem(R.drawable.camera_land_record, new View.OnClickListener() {
            public void onClick(View view) {
                CameraPlayerExActivity.this.doStartRecord();
            }
        }));
        this.mLandRecord.addState(new MultiStateView.StateItem(R.drawable.camera_land_record_end, new View.OnClickListener() {
            public void onClick(View view) {
                CameraPlayerExActivity.this.doStopRecord();
            }
        }));
        this.mLandRecord.setCurrentState(0);
        this.mTvMore.addState(new MultiStateTextView.StateItem(R.string.item_shortcut, R.drawable.camera_icon_more2, new View.OnClickListener() {
            public void onClick(View view) {
                CameraPlayerExActivity.this.mTvMore.setCurrentState(1);
                final int childWidth = CameraPlayerExActivity.this.mBottomGroup.getChildWidth();
                ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{childWidth});
                ofInt.setInterpolator(new LinearInterpolator());
                ofInt.setDuration(300);
                ofInt.start();
                ofInt.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationCancel(Animator animator) {
                        super.onAnimationCancel(animator);
                        CameraPlayerExActivity.this.mBottomGroup.setScrollX(childWidth);
                    }

                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        CameraPlayerExActivity.this.mBottomGroup.setScrollX(childWidth);
                    }
                });
                ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        CameraPlayerExActivity.this.mBottomGroup.setScrollX(((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                });
            }
        }));
        this.mTvMore.addState(new MultiStateTextView.StateItem(R.string.item_shortcut, R.drawable.camera_icon_more_close2, new View.OnClickListener() {
            public void onClick(View view) {
                CameraPlayerExActivity.this.mTvMore.setCurrentState(0);
                final int childWidth = CameraPlayerExActivity.this.mBottomGroup.getChildWidth();
                ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{childWidth});
                ofInt.setInterpolator(new LinearInterpolator());
                ofInt.setDuration(300);
                ofInt.start();
                ofInt.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationCancel(Animator animator) {
                        super.onAnimationCancel(animator);
                        CameraPlayerExActivity.this.mBottomGroup.setScrollX(0);
                    }

                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        CameraPlayerExActivity.this.mBottomGroup.setScrollX(0);
                    }
                });
                ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        CameraPlayerExActivity.this.mBottomGroup.setScrollX(childWidth - ((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                });
            }
        }));
        this.mTvMore.setCurrentState(0);
        this.mMSTVVoice.addState(new MultiStateView.StateItem(R.drawable.camera_call_start2_bg, new View.OnClickListener() {
            public void onClick(View view) {
                if (!PluginHostApiImpl.instance().checkAndRequestPermisson(CameraPlayerExActivity.this, true, (Callback<List<String>>) null, "android.permission.RECORD_AUDIO")) {
                    ToastUtil.a((Context) CameraPlayerExActivity.this, (int) R.string.audio_permission_denied);
                } else if (CameraPlayerExActivity.this.mCameraPlayerEx == null || !CameraPlayerExActivity.this.mCameraPlayerEx.g()) {
                    ToastUtil.a((Context) CameraPlayerExActivity.this, (int) R.string.call_no_play);
                } else if (!CameraPlayerExActivity.this.mCameraPlayerEx.f()) {
                    ToastUtil.a((Context) CameraPlayerExActivity.this, (int) R.string.not_on_live);
                } else {
                    if (!CameraPlayerExActivity.this.mCDCToggleAudio.isChecked()) {
                        CameraPlayerExActivity.this.mCDCToggleAudio.setChecked(true);
                    }
                    if (!CameraPlayerExActivity.this.mCameraPlayerEx.t()) {
                        Event.a("call");
                        CameraPlayerExActivity.this.mCameraPlayerEx.p();
                        CameraPlayerExActivity.this.mHandler.sendEmptyMessageDelayed(2101, 500);
                        CameraPlayerExActivity.this.mMSTVVoice.setCurrentState(1);
                    }
                }
            }
        }));
        this.mMSTVVoice.addState(new MultiStateView.StateItem(R.drawable.camera_call_end2_bg, new View.OnClickListener() {
            public void onClick(View view) {
                CameraPlayerExActivity.this.mCDCToggleAudio.setEnabled(true);
                if (CameraPlayerExActivity.this.mCameraPlayerEx != null) {
                    CameraPlayerExActivity.this.mCameraPlayerEx.q();
                    CameraPlayerExActivity.this.mHandler.removeMessages(2101);
                    if (CameraPlayerExActivity.this.mCDCToggleAudio.isChecked()) {
                        CameraPlayerExActivity.this.mCDCToggleAudio.setChecked(false);
                    }
                    CameraPlayerExActivity.this.mMSTVVoice.setCurrentState(0);
                    ToastUtil.a((Context) CameraPlayerExActivity.this, (int) R.string.stop_voice);
                }
            }
        }));
        this.mMSTVVoice.setCurrentState(0);
    }

    private void isCloudVideoUser() {
        if (AlarmNetUtils.b() && !TextUtils.isEmpty(this.mCameraDevice.getDid())) {
            try {
                AlarmNetUtils.b(AlarmNetUtils.a(this.mCameraDevice.p(), BuildConfig.j));
                if (!AlarmNetUtils.b() || !AlarmNetUtils.c()) {
                    findViewById(R.id.tvCloudVideo).setVisibility(8);
                    findViewById(R.id.tvCloudVideo).setOnClickListener((View.OnClickListener) null);
                    findViewById(R.id.tvAlbum).setVisibility(0);
                } else {
                    findViewById(R.id.tvCloudVideo).setVisibility(0);
                    findViewById(R.id.tvAlbum).setVisibility(8);
                }
                if (AlarmNetUtils.b() && AlarmNetUtils.e() && this.mCameraDevice.i().g() < 0) {
                    if (this.mCameraDevice.e().k() > 0) {
                        this.mCameraDevice.i().b(this.mCameraDevice.e().k());
                    } else {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("did", this.mCameraDevice.getDid());
                        jSONObject.put("region", Locale.getDefault().getCountry());
                        this.mCameraDevice.i().a(this.mCameraDevice.getModel(), jSONObject, (AlarmManager.IAlarmCallback) new AlarmManager.IAlarmCallback() {
                            public void onFailure(int i, String str) {
                            }

                            public void onSuccess(Object obj, Object obj2) {
                                CameraPlayerExActivity.this.mCameraDevice.e().a(CameraPlayerExActivity.this.mCameraDevice.j().a().h);
                            }
                        });
                    }
                }
            } catch (Exception unused) {
            }
            if (this.mCameraDevice.e().p() == 0) {
                AlarmNetUtils.a(true);
                findViewById(R.id.tvCloudVideo).setOnClickListener(this);
            } else if (this.mCameraDevice.e().p() == 1) {
                AlarmNetUtils.a(false);
                findViewById(R.id.tvCloudVideo).setOnClickListener(this);
            }
            AlarmNetUtils.l(this.mCameraDevice.getDid(), this.mCameraDevice.getModel(), new Callback<JSONObject>() {
                /* JADX WARNING: Removed duplicated region for block: B:18:0x005d A[Catch:{ NullPointerException -> 0x0122 }] */
                /* JADX WARNING: Removed duplicated region for block: B:24:0x0082 A[Catch:{ NullPointerException -> 0x0122 }] */
                /* JADX WARNING: Removed duplicated region for block: B:35:0x00c9 A[Catch:{ NullPointerException -> 0x0122 }] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onSuccess(org.json.JSONObject r7) {
                    /*
                        r6 = this;
                        com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity r0 = com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity.this
                        boolean r0 = r0.isFinishing()
                        if (r0 == 0) goto L_0x0009
                        return
                    L_0x0009:
                        if (r7 == 0) goto L_0x013d
                        java.lang.String r0 = "code"
                        r1 = -1
                        int r0 = r7.optInt(r0, r1)     // Catch:{ NullPointerException -> 0x0122 }
                        if (r0 != 0) goto L_0x013d
                        java.lang.String r0 = "data"
                        org.json.JSONObject r7 = r7.optJSONObject(r0)     // Catch:{ NullPointerException -> 0x0122 }
                        if (r7 == 0) goto L_0x013d
                        java.lang.String r0 = "vip"
                        boolean r0 = r7.optBoolean(r0)     // Catch:{ NullPointerException -> 0x0122 }
                        java.lang.String r1 = "closeWindow"
                        boolean r1 = r7.optBoolean(r1)     // Catch:{ NullPointerException -> 0x0122 }
                        java.io.PrintStream r2 = java.lang.System.out     // Catch:{ NullPointerException -> 0x0122 }
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NullPointerException -> 0x0122 }
                        r3.<init>()     // Catch:{ NullPointerException -> 0x0122 }
                        java.lang.String r4 = "mytest:cloud: vip:"
                        r3.append(r4)     // Catch:{ NullPointerException -> 0x0122 }
                        r3.append(r0)     // Catch:{ NullPointerException -> 0x0122 }
                        java.lang.String r4 = " window:"
                        r3.append(r4)     // Catch:{ NullPointerException -> 0x0122 }
                        r3.append(r1)     // Catch:{ NullPointerException -> 0x0122 }
                        java.lang.String r3 = r3.toString()     // Catch:{ NullPointerException -> 0x0122 }
                        r2.println(r3)     // Catch:{ NullPointerException -> 0x0122 }
                        r2 = 0
                        r3 = 1
                        if (r1 == 0) goto L_0x004f
                        if (r0 == 0) goto L_0x004d
                        goto L_0x004f
                    L_0x004d:
                        r4 = 0
                        goto L_0x0050
                    L_0x004f:
                        r4 = 1
                    L_0x0050:
                        com.mijia.model.alarm.AlarmNetUtils.a((boolean) r4)     // Catch:{ NullPointerException -> 0x0122 }
                        com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity r4 = com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity.this     // Catch:{ NullPointerException -> 0x0122 }
                        com.mijia.camera.MijiaCameraDevice r4 = r4.mCameraDevice     // Catch:{ NullPointerException -> 0x0122 }
                        com.mijia.model.local.LocalSettings r4 = r4.e()     // Catch:{ NullPointerException -> 0x0122 }
                        if (r1 == 0) goto L_0x0062
                        if (r0 == 0) goto L_0x0060
                        goto L_0x0062
                    L_0x0060:
                        r1 = 1
                        goto L_0x0063
                    L_0x0062:
                        r1 = 0
                    L_0x0063:
                        r4.c((int) r1)     // Catch:{ NullPointerException -> 0x0122 }
                        com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity r1 = com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity.this     // Catch:{ NullPointerException -> 0x0122 }
                        r4 = 2131433111(0x7f0b1697, float:1.8487998E38)
                        android.view.View r1 = r1.findViewById(r4)     // Catch:{ NullPointerException -> 0x0122 }
                        com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity r4 = com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity.this     // Catch:{ NullPointerException -> 0x0122 }
                        r1.setOnClickListener(r4)     // Catch:{ NullPointerException -> 0x0122 }
                        java.lang.String r1 = "endTime"
                        long r4 = r7.optLong(r1)     // Catch:{ NullPointerException -> 0x0122 }
                        java.lang.String r1 = "status"
                        int r7 = r7.optInt(r1)     // Catch:{ NullPointerException -> 0x0122 }
                        if (r0 == 0) goto L_0x00c9
                        org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ NullPointerException -> 0x0122 }
                        r7.<init>()     // Catch:{ NullPointerException -> 0x0122 }
                        java.lang.String r0 = "did"
                        com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity r1 = com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity.this     // Catch:{ JSONException -> 0x00b2 }
                        java.lang.String r1 = r1.mDid     // Catch:{ JSONException -> 0x00b2 }
                        r7.put(r0, r1)     // Catch:{ JSONException -> 0x00b2 }
                        com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x00b2 }
                        java.util.Locale r0 = r0.I()     // Catch:{ JSONException -> 0x00b2 }
                        if (r0 == 0) goto L_0x00a4
                        java.lang.String r1 = "region"
                        java.lang.String r0 = r0.getCountry()     // Catch:{ JSONException -> 0x00b2 }
                        r7.put(r1, r0)     // Catch:{ JSONException -> 0x00b2 }
                        goto L_0x00b6
                    L_0x00a4:
                        java.lang.String r0 = "region"
                        java.util.Locale r1 = java.util.Locale.getDefault()     // Catch:{ JSONException -> 0x00b2 }
                        java.lang.String r1 = r1.getCountry()     // Catch:{ JSONException -> 0x00b2 }
                        r7.put(r0, r1)     // Catch:{ JSONException -> 0x00b2 }
                        goto L_0x00b6
                    L_0x00b2:
                        r0 = move-exception
                        r0.printStackTrace()     // Catch:{ NullPointerException -> 0x0122 }
                    L_0x00b6:
                        com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils r0 = com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils.getInstance()     // Catch:{ NullPointerException -> 0x0122 }
                        com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity r1 = com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity.this     // Catch:{ NullPointerException -> 0x0122 }
                        java.lang.String r7 = r7.toString()     // Catch:{ NullPointerException -> 0x0122 }
                        com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity$42$1 r2 = new com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity$42$1     // Catch:{ NullPointerException -> 0x0122 }
                        r2.<init>(r4)     // Catch:{ NullPointerException -> 0x0122 }
                        r0.getDeductOrders(r1, r7, r2)     // Catch:{ NullPointerException -> 0x0122 }
                        goto L_0x013d
                    L_0x00c9:
                        if (r7 != r3) goto L_0x013d
                        com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity r7 = com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity.this     // Catch:{ NullPointerException -> 0x0122 }
                        com.mijia.camera.MijiaCameraDevice r7 = r7.mCameraDevice     // Catch:{ NullPointerException -> 0x0122 }
                        com.mijia.model.local.LocalSettings r7 = r7.e()     // Catch:{ NullPointerException -> 0x0122 }
                        java.lang.String r7 = r7.q()     // Catch:{ NullPointerException -> 0x0122 }
                        java.lang.String r0 = "0|0"
                        boolean r7 = r7.equals(r0)     // Catch:{ NullPointerException -> 0x0122 }
                        if (r7 != 0) goto L_0x013d
                        com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity r7 = com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity.this     // Catch:{ NullPointerException -> 0x0122 }
                        r0 = 2131433106(0x7f0b1692, float:1.8487988E38)
                        android.view.View r7 = r7.findViewById(r0)     // Catch:{ NullPointerException -> 0x0122 }
                        android.widget.TextView r7 = (android.widget.TextView) r7     // Catch:{ NullPointerException -> 0x0122 }
                        com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity r0 = com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity.this     // Catch:{ NullPointerException -> 0x0122 }
                        r1 = 2131495535(0x7f0c0a6f, float:1.861461E38)
                        java.lang.String r0 = r0.getString(r1)     // Catch:{ NullPointerException -> 0x0122 }
                        r7.setText(r0)     // Catch:{ NullPointerException -> 0x0122 }
                        com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity r7 = com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity.this     // Catch:{ NullPointerException -> 0x0122 }
                        r0 = 2131430068(0x7f0b0ab4, float:1.8481827E38)
                        android.view.View r7 = r7.findViewById(r0)     // Catch:{ NullPointerException -> 0x0122 }
                        com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity$42$2 r0 = new com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity$42$2     // Catch:{ NullPointerException -> 0x0122 }
                        r0.<init>()     // Catch:{ NullPointerException -> 0x0122 }
                        r7.setOnClickListener(r0)     // Catch:{ NullPointerException -> 0x0122 }
                        com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity r7 = com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity.this     // Catch:{ NullPointerException -> 0x0122 }
                        r0 = 2131431928(0x7f0b11f8, float:1.84856E38)
                        android.view.View r7 = r7.findViewById(r0)     // Catch:{ NullPointerException -> 0x0122 }
                        com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity$42$3 r1 = new com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity$42$3     // Catch:{ NullPointerException -> 0x0122 }
                        r1.<init>()     // Catch:{ NullPointerException -> 0x0122 }
                        r7.setOnClickListener(r1)     // Catch:{ NullPointerException -> 0x0122 }
                        com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity r7 = com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity.this     // Catch:{ NullPointerException -> 0x0122 }
                        android.view.View r7 = r7.findViewById(r0)     // Catch:{ NullPointerException -> 0x0122 }
                        r7.setVisibility(r2)     // Catch:{ NullPointerException -> 0x0122 }
                        goto L_0x013d
                    L_0x0122:
                        r7 = move-exception
                        java.lang.String r0 = "CameraPlayerExActivity"
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder
                        r1.<init>()
                        java.lang.String r2 = "NullPointerException:"
                        r1.append(r2)
                        java.lang.String r7 = r7.getLocalizedMessage()
                        r1.append(r7)
                        java.lang.String r7 = r1.toString()
                        com.xiaomi.smarthome.framework.log.LogUtil.b(r0, r7)
                    L_0x013d:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.CameraPlayerExActivity.AnonymousClass42.onSuccess(org.json.JSONObject):void");
                }

                public void onFailure(int i, String str) {
                    if (!CameraPlayerExActivity.this.isFinishing()) {
                    }
                }
            });
        }
    }

    private void getFirmwareVersion() {
        if (this.mCameraDevice != null) {
            this.mCameraDevice.c(getContext(), new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("jsonObject:");
                    sb.append(jSONObject != null ? jSONObject.toString() : "");
                    LogUtil.a(CameraPlayerExActivity.TAG, sb.toString());
                    jSONObject.optBoolean("updating", false);
                    jSONObject.optBoolean("isLatest", false);
                    jSONObject.optString("curr");
                }

                public void onFailure(int i, String str) {
                    LogUtil.b(CameraPlayerExActivity.TAG, "getFirmwareVersion i:" + i + " s:" + str);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void processCVExpireUI(final long j) {
        final int currentTimeMillis = j - System.currentTimeMillis() < 86400000 ? 1 : (int) (((((j - System.currentTimeMillis()) / 1000) / 60) / 60) / 24);
        PrintStream printStream = System.out;
        printStream.println("mytest:" + j + "|" + currentTimeMillis);
        if (currentTimeMillis == 1 || currentTimeMillis == 3 || currentTimeMillis == 7) {
            String q = this.mCameraDevice.e().q();
            if (!q.equals(j + "|" + currentTimeMillis)) {
                ((TextView) findViewById(R.id.tvCloudEndTip)).setText(getResources().getQuantityString(R.plurals.home_cloud_will_end_tip, currentTimeMillis));
                findViewById(R.id.ivCloudEndTip).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        CameraPlayerExActivity.this.findViewById(R.id.rlCloudEndTip).setVisibility(8);
                        LocalSettings e = CameraPlayerExActivity.this.mCameraDevice.e();
                        e.a(j + "|" + currentTimeMillis);
                    }
                });
                findViewById(R.id.rlCloudEndTip).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        FrameManager.b().k().openCloudVideoListActivity(CameraPlayerExActivity.this, CameraPlayerExActivity.this.mCameraDevice.getDid(), CameraPlayerExActivity.this.mCameraDevice.getName());
                    }
                });
                findViewById(R.id.rlCloudEndTip).setVisibility(0);
            }
        }
    }

    public void showUserLicenseDialog(String str, String str2, int i, String str3, int i2, View.OnClickListener onClickListener, Intent intent) {
        new UserLicenseDialog.Builder(this).a(str).b(str2).a(i).c(str3).b(i2).a(intent).d(this.mDid).a(onClickListener).a().a();
    }

    public void openMoreMenu(ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i, Intent intent) {
        DeviceMoreActivity.openMoreMenu(activity(), this.mDid, arrayList, z, i, intent);
    }

    public void openMoreMenu2(ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i, Intent intent, Intent intent2) {
        DeviceMoreNewActivity.openMoreMenu(activity(), this.mDid, arrayList, z, i, intent, intent2);
    }

    public void openShareMediaActivity(String str, String str2, String str3) {
        Intent intent = new Intent(this, CommonShareActivity.class);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("ShareTitle", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("ShareContent", str2);
        }
        intent.putExtra(CommonShareActivity.SHARE_IMAGE_FILE_ZIP_URL, str3);
        startActivity(intent);
    }

    private boolean isMijiaLgIncloudSyCurrentLg(String str) {
        String[] strArr = {"zh", "en", d.u, Region.RU, "ko", "it", "fr", "de", "in", d.U, "vi", "ja", "th"};
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String equals : strArr) {
            if (str.equals(equals)) {
                return true;
            }
        }
        return false;
    }
}
