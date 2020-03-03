package com.xiaomi.smarthome.camera.activity;

import android.animation.ObjectAnimator;
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
import com.mijia.app.AppCode;
import com.mijia.app.Event;
import com.mijia.camera.CameraPlayer;
import com.mijia.camera.CameraProperties;
import com.mijia.camera.ICameraPlayerListener;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.mijia.model.local.LocalFileClear;
import com.mijia.model.local.LocalFileManager;
import com.mijia.model.property.CameraPropertyBase;
import com.mijia.model.sdcard.SDCardInfo;
import com.tutk.TuTkClient;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.camera.LocalLicenseUtil;
import com.xiaomi.smarthome.camera.XmMp4Record;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.activity.alarm.AlarmAISwitchActivity;
import com.xiaomi.smarthome.camera.activity.alarm.AlarmGuideActivity;
import com.xiaomi.smarthome.camera.activity.alarm.AlarmSettingV2Activity;
import com.xiaomi.smarthome.camera.activity.alarm2.AlarmVideoActivity;
import com.xiaomi.smarthome.camera.activity.local.AlbumActivity;
import com.xiaomi.smarthome.camera.activity.local.LocalPicReviewActivity;
import com.xiaomi.smarthome.camera.activity.local.LocalVideoPlayActivity;
import com.xiaomi.smarthome.camera.activity.sdcard.SDCardTimeLinePlayerActivity;
import com.xiaomi.smarthome.camera.activity.setting.FileManagerSettingActivity;
import com.xiaomi.smarthome.camera.activity.setting.MoreCameraSettingActivity;
import com.xiaomi.smarthome.camera.activity.setting.SDCardStatusActivityNew;
import com.xiaomi.smarthome.camera.v4.activity.setting.NoMemoryCardActivity;
import com.xiaomi.smarthome.camera.view.SDCardHintDialog;
import com.xiaomi.smarthome.camera.view.widget.MultiStateView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceUpdateInfo;
import com.xiaomi.smarthome.device.api.IXmPluginHostActivity;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.fastvideo.VideoGlSurfaceView;
import com.xiaomi.smarthome.fastvideo.VideoViewGlImpl;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.util.ServerUtil;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.CommonShareActivity;
import com.xiaomi.smarthome.framework.page.DeviceMoreActivity;
import com.xiaomi.smarthome.framework.page.DeviceMoreNewActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.UserLicenseDialog;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.ICloudVideoCallback;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraPlayer2Activity extends CameraPlayerBaseActivity implements ICameraPlayerListener {
    public static final String CLOUD_AND_ALARM_SUPPORT_FW_VERSION = "3.4.4.0039";
    private static final String TAG = "CameraPlayer2Activity";
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
    public boolean isAudioOn = false;
    /* access modifiers changed from: private */
    public boolean isSnapshotting = false;
    private boolean isStartPlay = false;
    private ImageView ivFullScreen;
    private LinearLayout llBottomTools;
    private LinearLayout llCall;
    private LinearLayout llRightCtrlContainer;
    /* access modifiers changed from: private */
    public int mCurrentTime = 0;
    private long mFullInTime = 0;
    private CameraPlayer.IRecordTimeListener mIRecodeTimeListener = new CameraPlayer.IRecordTimeListener() {
        public void upDateTime(int i) {
            int access$1200 = CameraPlayer2Activity.this.mCurrentTime / 1000;
            if (CameraPlayer2Activity.this.mLastTime != -1) {
                int access$1300 = i - CameraPlayer2Activity.this.mLastTime;
                if (access$1300 <= 0 || access$1300 >= 500) {
                    int unused = CameraPlayer2Activity.this.mCurrentTime = CameraPlayer2Activity.this.mCurrentTime + 50;
                } else {
                    int unused2 = CameraPlayer2Activity.this.mCurrentTime = CameraPlayer2Activity.this.mCurrentTime + access$1300;
                }
            } else {
                int unused3 = CameraPlayer2Activity.this.mCurrentTime = CameraPlayer2Activity.this.mCurrentTime + 50;
            }
            int unused4 = CameraPlayer2Activity.this.mLastTime = i;
            if (CameraPlayer2Activity.this.mCurrentTime / 1000 != access$1200) {
                CameraPlayer2Activity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        int access$1200 = CameraPlayer2Activity.this.mCurrentTime / 1000;
                        if (access$1200 > 1) {
                            CameraPlayer2Activity.this.upDateTimeTitle(access$1200 - 1);
                        }
                    }
                });
            }
        }
    };
    private boolean mIsInit = false;
    /* access modifiers changed from: private */
    public LinearLayout mLLFuncPopup;
    private LinearLayout mLLTopCtrlLandscape;
    /* access modifiers changed from: private */
    public MultiStateView mLandRecord;
    /* access modifiers changed from: private */
    public TextView mLandRecordTimer;
    /* access modifiers changed from: private */
    public int mLastTime = -1;
    /* access modifiers changed from: private */
    public boolean mNeedLicense = false;
    private View mNewFirmView;
    private SDCardHintDialog mSDCardHintDialog;
    private TextView mTimeUpdateView;
    private View mTitleMore;
    /* access modifiers changed from: private */
    public MultiStateView msvAudio;
    /* access modifiers changed from: private */
    public MultiStateView msvCall;
    /* access modifiers changed from: private */
    public MultiStateView msvLandAudio;
    /* access modifiers changed from: private */
    public MultiStateView msvVideoRecord;
    private CameraPlayer.IResolutionChangedListener resolutionChangedListener = new CameraPlayer.IResolutionChangedListener() {
        public void onResolutionChanged(int i, int i2) {
            if (CameraPlayer2Activity.this.mCameraPlayer == null) {
                return;
            }
            if (CameraPlayer2Activity.this.mCameraPlayer.c()) {
                CameraPlayer2Activity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        CameraPlayer2Activity.this.refreshUI();
                    }
                });
            } else {
                CameraPlayer2Activity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        CameraPlayer2Activity.this.refreshUI();
                    }
                });
            }
        }
    };
    int sdcardErrorCode = 0;
    boolean sdcardGetSuccess = false;
    int selectedIndex;
    private TextView tvAlert;
    /* access modifiers changed from: private */
    public TextView tvCloud;
    /* access modifiers changed from: private */
    public TextView tvFace;
    private TextView tvFloatingWindow;
    private TextView tvFullScreen;
    private TextView tvLandResolution;
    private TextView tvLandSleep;
    private TextView tvPlayback;
    private TextView tvResolution;
    private TextView tvSleep;
    private TextView tvSnapshot;

    private int getResolutionIndex(int i) {
        if (i != 1) {
            return i != 3 ? 0 : 2;
        }
        return 1;
    }

    public boolean isHistory() {
        return false;
    }

    public void onServerCmd(int i, byte[] bArr) {
    }

    /* access modifiers changed from: protected */
    public boolean shouldRecordPlayTime() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                LocalFileClear.a((CameraDevice) CameraPlayer2Activity.this.mCameraDevice);
            }
        }, 2000);
        setContentView(R.layout.activity_camera_player2);
        this.flTitleBar = (FrameLayout) findViewById(R.id.title_bar);
        this.flTitleBar.setBackgroundResource(R.drawable.shape_gradient_bg);
        this.flTitleBar.bringToFront();
        if (XmPluginHostApi.instance().getApiLevel() < 63) {
            showUpdateDlg(false);
            return;
        }
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
        setResolutionText(getResolutionIndex(this.mCameraDevice.e().d()), false);
        this.mNetworkMonitor.register(activity());
        if (!(this.mCameraDevice == null || this.mCameraDevice.f() == null)) {
            this.mCameraDevice.f().a(CameraProperties.c, (Callback<Void>) new Callback<Void>() {
                public void onFailure(int i, String str) {
                }

                public void onSuccess(Void voidR) {
                    boolean isUsrExpPlanEnabled;
                    if (!CameraPlayer2Activity.this.isFinishing()) {
                        CameraPlayer2Activity.this.mPowerCheck = true;
                        CameraPlayer2Activity.this.refreshUI();
                        CameraPlayer2Activity.this.checkMinLevel();
                        if (XmPluginHostApi.instance().getApiLevel() > 66 && CameraPlayer2Activity.this.mCameraDevice.f().b(CameraProperties.b) != (isUsrExpPlanEnabled = XmPluginHostApi.instance().isUsrExpPlanEnabled(CameraPlayer2Activity.this.mCameraDevice.getDid()))) {
                            CameraPlayer2Activity.this.mCameraDevice.f().a(CameraProperties.b, isUsrExpPlanEnabled, (Callback<Void>) null);
                        }
                    }
                }
            });
        }
        showLicense();
        showCloudVideoTips();
        TuTkClient.mConnect_Public_Key = 0;
        if (!(this.mCameraDevice == null || this.mCameraDevice.f() == null)) {
            this.mCameraDevice.f().x = new CameraPropertyBase.ICameraPropertyListener() {
                public void onCameraPropertyChanged(String str) {
                    if (!TextUtils.isEmpty(str) && CameraPropertyBase.l.equals(str)) {
                        CameraPlayer2Activity.this.mCameraDevice.g().a(CameraPropertyBase.l);
                        CameraPlayer2Activity.this.toggleCtrlContainerVisibility();
                        if (CameraPlayer2Activity.this.mCameraPlayer != null && CameraPlayer2Activity.this.mCameraDevice != null && !CameraPlayer2Activity.this.mCameraDevice.f().c()) {
                            CameraPlayer2Activity.this.hideLoading();
                            CameraPlayer2Activity.this.mCameraPlayer.g();
                        }
                    }
                }
            };
        }
        toggleCtrlContainerVisibility();
        Event.a(this.mCameraDevice.getDid(), this.mCameraDevice.getModel());
        Event.a(Event.am);
        Event.a();
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
        this.tvPlayback = (TextView) findViewById(R.id.tvPlayback);
        this.tvPlayback.setOnClickListener(this);
        this.llRightCtrlContainer = (LinearLayout) findViewById(R.id.llRightCtrlContainer);
        this.llRightCtrlContainer.setVisibility(0);
        this.llCall = (LinearLayout) findViewById(R.id.llCall);
        this.mVideoViewFrame = (FrameLayout) findViewById(R.id.video_frame);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        FrameLayout frameLayout = new FrameLayout(activity());
        this.mVideoViewFrame.addView(frameLayout, 0, layoutParams);
        this.mVideoView = XmPluginHostApi.instance().createVideoView(activity(), frameLayout, true, 1);
        this.mVideoView.setDistort(0.24375f, 0.04411765f);
        this.mVideoView.setTouch(false);
        float f = 1.0f;
        this.mSurfaceViewScale = 1.0f;
        this.mSurfaceViewOffsetX = 0.0f;
        this.mSurfaceViewOffsetY = 0.0f;
        if (this.mCameraDevice != null && !TextUtils.isEmpty(this.mCameraDevice.getModel()) && !TextUtils.isEmpty(this.mCameraDevice.getModel())) {
            String a2 = MD5Util.a(this.mCameraDevice.getModel() + this.mCameraDevice.getDid());
            this.mSurfaceViewScale = SharePrefsManager.a(SHApplication.getAppContext(), a2, "scale", 1.0f);
            this.mSurfaceViewOffsetX = SharePrefsManager.a(SHApplication.getAppContext(), a2, CameraPlayerBaseActivity.SP_KEY_OFFSET_X, 0.0f);
            this.mSurfaceViewOffsetY = SharePrefsManager.a(SHApplication.getAppContext(), a2, CameraPlayerBaseActivity.SP_KEY_OFFSET_Y, 0.0f);
        }
        XmVideoViewGl xmVideoViewGl = this.mVideoView;
        if (this.mSurfaceViewScale > 0.0f) {
            f = this.mSurfaceViewScale;
        }
        xmVideoViewGl.setScale(f, false);
        ((VideoViewGlImpl) this.mVideoView).a(this.mSurfaceViewOffsetX);
        ((VideoViewGlImpl) this.mVideoView).b(this.mSurfaceViewOffsetY);
        this.mLoadingView = LayoutInflater.from(this).inflate(R.layout.camera_progress, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        layoutParams2.gravity = 17;
        frameLayout.addView(this.mLoadingView, layoutParams2);
        this.mLoadingProgress = (TextView) this.mLoadingView.findViewById(R.id.loading_progress);
        this.mLoadingTitle = (TextView) this.mLoadingView.findViewById(R.id.loading_title);
        this.mLoadingImageView = (ImageView) this.mLoadingView.findViewById(R.id.loading_anim);
        this.mLoadingAnimation = (AnimationDrawable) getResources().getDrawable(R.drawable.camera_anim_camera_loading);
        this.mLoadingImageView.setImageDrawable(this.mLoadingAnimation);
        if (!Util.a()) {
            this.mLoadingImageView.setVisibility(8);
        }
        this.mLoadingView.setVisibility(8);
        this.mErrorRetryView = LayoutInflater.from(this).inflate(R.layout.camera_error_retry, (ViewGroup) null);
        frameLayout.addView(this.mErrorRetryView, layoutParams2);
        this.mErrorRetryView.setVisibility(8);
        this.mRetryView = this.mErrorRetryView.findViewById(R.id.retry_btn);
        this.mErrorInfoView = (TextView) this.mErrorRetryView.findViewById(R.id.error_info);
        this.mPowerOffView = LayoutInflater.from(this).inflate(R.layout.camera_closed, (ViewGroup) null);
        frameLayout.addView(this.mPowerOffView, layoutParams2);
        this.mPowerOffView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SDKLog.b(CameraPlayer2Activity.TAG, "mPowerOffView");
                if (CameraPlayer2Activity.this.mCameraDevice != null && CameraPlayer2Activity.this.mCameraDevice.f() != null) {
                    CameraPlayer2Activity.this.mCameraDevice.f().a(CameraPropertyBase.l, true, (Callback<Void>) new Callback<Void>() {
                        public void onSuccess(Void voidR) {
                            CameraPlayer2Activity.this.mCameraDevice.f().a(CameraPropertyBase.l, (Object) true);
                            if (!CameraPlayer2Activity.this.isFinishing()) {
                                CameraPlayer2Activity.this.toggleCtrlContainerVisibility();
                                SDKLog.b(CameraPlayer2Activity.TAG, "set wakeup success");
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (!CameraPlayer2Activity.this.isFinishing()) {
                                CameraPlayer2Activity.this.mCameraDevice.f().a(CameraPropertyBase.l, (Object) false);
                                CameraPlayer2Activity.this.toggleCtrlContainerVisibility();
                                ToastUtil.a((Context) CameraPlayer2Activity.this.activity(), (int) R.string.action_fail);
                                SDKLog.b(CameraPlayer2Activity.TAG, "set wakeup failed:" + i + " s:" + str);
                            }
                        }
                    });
                }
            }
        });
        this.mPowerOffView.setVisibility(8);
        this.mVideoView.setVideoViewListener(new XmVideoViewGl.IVideoViewListener() {
            public void onVideoViewClick() {
                if (CameraPlayer2Activity.this.mCameraDevice.f().c()) {
                    CameraPlayer2Activity.this.videoClick();
                }
            }
        });
        this.mFrameRate = (TextView) findViewById(R.id.sub_title_bar_title);
        this.mCBVoiceLandscape = (CheckBox) findViewById(R.id.cbVoiceLandscape);
        this.mCBVoiceLandscape.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SDKLog.b(CameraPlayer2Activity.TAG, "mCBVoiceLandscape:" + z);
                if (CameraPlayer2Activity.this.mCameraPlayer == null) {
                    return;
                }
                if (z) {
                    if (XmPluginHostApi.instance().getApiLevel() >= 75) {
                        if (!XmPluginHostApi.instance().checkAndRequestPermisson(CameraPlayer2Activity.this.activity(), true, new Callback<List<String>>() {
                            public void onSuccess(List<String> list) {
                                if (!CameraPlayer2Activity.this.mCameraPlayer.c()) {
                                    ToastUtil.a((Context) CameraPlayer2Activity.this.activity(), (int) R.string.not_on_live);
                                    CameraPlayer2Activity.this.mCBVoiceLandscape.setChecked(false);
                                } else if (!CameraPlayer2Activity.this.mCameraPlayer.o()) {
                                    CameraPlayer2Activity.this.mCameraPlayer.k();
                                    Event.g();
                                }
                            }

                            public void onFailure(int i, String str) {
                                ToastUtil.a((Context) CameraPlayer2Activity.this.activity(), (int) R.string.audio_permission_denied);
                                CameraPlayer2Activity.this.mCBVoiceLandscape.setChecked(false);
                            }
                        }, "android.permission.RECORD_AUDIO")) {
                            return;
                        }
                    } else if (!com.Utils.Util.b(CameraPlayer2Activity.this.activity(), "android.permission.RECORD_AUDIO")) {
                        ToastUtil.a((Context) CameraPlayer2Activity.this.activity(), (int) R.string.audio_permission_denied);
                        CameraPlayer2Activity.this.mCBVoiceLandscape.setChecked(false);
                        return;
                    }
                    if (!CameraPlayer2Activity.this.mCameraPlayer.c()) {
                        ToastUtil.a((Context) CameraPlayer2Activity.this.activity(), (int) R.string.not_on_live);
                        CameraPlayer2Activity.this.mCBVoiceLandscape.setChecked(false);
                    } else if (!CameraPlayer2Activity.this.mCameraPlayer.o()) {
                        CameraPlayer2Activity.this.mCameraPlayer.k();
                        Event.g();
                    }
                } else {
                    CameraPlayer2Activity.this.mCameraPlayer.l();
                    CameraPlayer2Activity.this.mHandler.removeMessages(2101);
                    Event.h();
                }
            }
        });
        this.mVideoView.initial();
        this.mVideoView.setVideoViewScaleListener(new XmVideoViewGl.IVideoViewScaleListener() {
            public void reportVideoViewScaleEvent(int i) {
                String str = "";
                switch (i) {
                    case 1:
                        str = Event.aq;
                        break;
                    case 2:
                        str = Event.ao;
                        break;
                    case 3:
                        str = Event.ap;
                        break;
                }
                if (!TextUtils.isEmpty(str)) {
                    Event.a(str);
                }
            }
        });
        this.mLLTopCtrlLandscape = (LinearLayout) findViewById(R.id.llLandTopCtrlLandscape);
        findViewById(R.id.ivLandSnapShot).setOnClickListener(this);
        findViewById(R.id.ivExitFullscreen).setOnClickListener(this);
        this.mRetryView.setOnClickListener(this);
        this.mLLFuncPopup = (LinearLayout) findViewById(R.id.llFuncPopup);
        this.llBottomTools = (LinearLayout) findViewById(R.id.llBottomTools);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        this.tvLandResolution = (TextView) findViewById(R.id.tvLandResolution);
        this.tvLandResolution.setOnClickListener(this);
        this.tvResolution = (TextView) findViewById(R.id.tvResolution);
        this.tvResolution.setOnClickListener(this);
        this.tvSleep = (TextView) findViewById(R.id.tvSleep);
        this.tvSleep.setOnClickListener(this);
        this.tvLandSleep = (TextView) findViewById(R.id.tvLandSleep);
        this.tvLandSleep.setOnClickListener(this);
        this.mPauseView = findViewById(R.id.pause_view);
        this.mPauseView.setOnClickListener(this);
        this.tvAlert = (TextView) findViewById(R.id.tvAlert);
        this.tvAlert.setOnClickListener(this);
        this.tvFace = (TextView) findViewById(R.id.tv_face);
        this.tvFace.setOnClickListener(this);
        this.tvFullScreen = (TextView) findViewById(R.id.tvFullScreen);
        this.tvFullScreen.setOnClickListener(this);
        this.tvFloatingWindow = (TextView) findViewById(R.id.tvFloatingWindow);
        this.tvFloatingWindow.setOnClickListener(this);
        this.tvSnapshot = (TextView) findViewById(R.id.tvSnapshot);
        this.tvSnapshot.setOnClickListener(this);
        this.tvCloud = (TextView) findViewById(R.id.tvCloud);
        this.flCloudVideoTips = (FrameLayout) findViewById(R.id.fl_cloud_video_tips);
        if (this.mCameraDevice.isShared()) {
            this.flCloudVideoTips.setVisibility(8);
        }
        if (!this.mCameraDevice.isShared() && Util.e()) {
            this.tvCloud.setVisibility(0);
            this.tvCloud.setOnClickListener(this);
        }
        this.mLandRecordTimer = (TextView) findViewById(R.id.landRecordTimer);
        this.msvAudio = (MultiStateView) findViewById(R.id.msvAudio);
        this.msvVideoRecord = (MultiStateView) findViewById(R.id.msvVideoRecord);
        this.msvCall = (MultiStateView) findViewById(R.id.msvCall);
        this.msvLandAudio = (MultiStateView) findViewById(R.id.msvLandAudio);
    }

    private void showCloudVideoTips() {
        if (!this.mCameraDevice.isShared() && this.mCameraDevice.e().o()) {
            CloudVideoNetUtils.getInstance().getCloudPromoteTips(this.mDid, new ICloudVideoCallback<String>() {
                public void onCloudVideoSuccess(String str, Object obj) {
                    CameraPlayer2Activity.this.flCloudVideoTips.setVisibility(0);
                    CameraPlayer2Activity.this.flCloudVideoTips.setOnClickListener(new View.OnClickListener() {
                        public final void onClick(View view) {
                            CameraPlayer2Activity.this.onClick(view);
                        }
                    });
                    ((TextView) CameraPlayer2Activity.this.findViewById(R.id.tv_cloud_video_tips)).setText(str);
                    if (CameraPlayer2Activity.this.mCameraDevice.e().n() == 0) {
                        CameraPlayer2Activity.this.mCameraDevice.e().b(System.currentTimeMillis());
                    }
                }

                public void onCloudVideoFailed(int i, String str) {
                    Log.d(CameraPlayer2Activity.TAG, str);
                }
            });
        }
    }

    public boolean canStepOut() {
        return canStepOut(0, 0);
    }

    public boolean canStepOut(int i, int i2) {
        if (this.mCameraPlayer == null) {
            return true;
        }
        if (this.mCameraPlayer.n() || this.mCameraPlayer.o()) {
            if (i > 0) {
                ToastUtil.a((Context) activity(), i);
            } else {
                ToastUtil.a((Context) activity(), (int) R.string.speaking_block);
            }
            return false;
        } else if (!this.mCameraPlayer.m()) {
            return true;
        } else {
            if (i2 > 0) {
                ToastUtil.a((Context) activity(), i2);
            } else {
                ToastUtil.a((Context) activity(), (int) R.string.recording_block);
            }
            return false;
        }
    }

    public void setPlayTime() {
        if (!this.mUserPause && this.mCameraDevice != null && this.mCameraDevice.f().c()) {
            hidError();
            showLoading("");
        }
        if (this.mCameraPlayer != null) {
            this.mCameraPlayer.f();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        SDKLog.b(TAG, "newConfig.orientation:" + configuration.orientation);
        setWindow(configuration);
        if (configuration.orientation != 1) {
            this.flTitleBar.setVisibility(8);
            this.llRightCtrlContainer.setVisibility(8);
            this.llBottomTools.setVisibility(8);
            this.mLLTopCtrlLandscape.setVisibility(0);
            this.mFullScreen = true;
            this.mVideoView.setIsFull(true);
            if (this.mCameraPlayer != null && this.mCameraPlayer.m()) {
                this.mLandRecordTimer.setVisibility(0);
            }
            this.mFullInTime = System.currentTimeMillis();
            return;
        }
        this.flTitleBar.setVisibility(0);
        this.llRightCtrlContainer.setVisibility(0);
        this.llBottomTools.setVisibility(0);
        this.mLLTopCtrlLandscape.setVisibility(8);
        this.mFullScreen = false;
        this.mVideoView.setIsFull(false);
        if (this.mCameraPlayer != null && this.mCameraPlayer.m()) {
            this.mLandRecordTimer.setVisibility(8);
        }
        if (this.mFullInTime > 0) {
            this.mFullInTime = 0;
        }
        this.llCall.setVisibility(0);
        if (this.mCameraDevice != null && this.mCameraDevice.f() != null && this.mCameraDevice.f().c() && this.llRightCtrlContainer.getTranslationX() <= 0.0f) {
            this.llCall.setTranslationY(0.0f);
        }
    }

    public void onClick(View view) {
        if (!doOnClick(view)) {
            switch (view.getId()) {
                case R.id.fl_cloud_video_tips:
                    CloudVideoNetUtils.getInstance().openCloudVideoBuyPage(this, this.mCameraDevice.getDid());
                    this.mCameraDevice.e().j(true);
                    this.flCloudVideoTips.setVisibility(8);
                    return;
                case R.id.ivExitFullscreen:
                    setOrientation(false);
                    return;
                case R.id.ivFullScreen:
                case R.id.tvFullScreen:
                    SDKLog.b(TAG, "ivFullScreen click");
                    Event.a(Event.aw);
                    setOrientation(true);
                    return;
                case R.id.ivLandSnapShot:
                case R.id.tvSnapshot:
                    SDKLog.b(TAG, "ivCameraShot click");
                    Event.a(Event.c);
                    Event.a(Event.as);
                    snapShot();
                    return;
                case R.id.title_bar_more:
                    if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        if (this.mCameraDevice.isReadOnlyShared()) {
                            ToastUtil.a((Context) activity(), (int) R.string.auth_fail);
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
                        if (!CoreApi.a().D() && !this.mCameraDevice.isShared()) {
                            IXmPluginHostActivity.IntentMenuItem intentMenuItem4 = new IXmPluginHostActivity.IntentMenuItem();
                            intentMenuItem4.name = getString(R.string.face_ai_setting);
                            intentMenuItem4.intent = new Intent(this, AlarmAISwitchActivity.class);
                            intentMenuItem4.intent.putExtra("extra_device_did", this.mDid);
                            if (!this.mCameraDevice.e().f()) {
                                intentMenuItem4.goBuyVip = true;
                            }
                            arrayList.add(intentMenuItem4);
                        }
                        IXmPluginHostActivity.IntentMenuItem intentMenuItem5 = new IXmPluginHostActivity.IntentMenuItem();
                        intentMenuItem5.name = getString(R.string.album);
                        intentMenuItem5.intent = new Intent(this, AlbumActivity.class);
                        intentMenuItem5.intent.putExtra("extra_device_did", this.mDid);
                        arrayList.add(intentMenuItem5);
                        Intent intent = new Intent();
                        intent.putExtra(DeviceMoreActivity.ARGS_SECURITY_SETTING_ENABLE, true);
                        intent.putExtra(DeviceMoreNewActivity.AUTO_DISMISS, false);
                        if (!this.mCameraDevice.isShared()) {
                            intent.putExtra("cloud_storage", true);
                            intent.putExtra("title", this.mCameraDevice.getName());
                        }
                        if (XmPluginHostApi.instance().getApiLevel() > 52) {
                            LocalLicenseUtil.LocalLicense v3LocalLicense = LocalLicenseUtil.getV3LocalLicense(getResources());
                            int i = v3LocalLicense.mLicense;
                            int i2 = v3LocalLicense.mPrivacy;
                            Intent intent2 = new Intent();
                            if (i > 0 && i2 > 0) {
                                intent2.putExtra(DeviceMoreActivity.ARGS_ENABLE_REMOVE_LICENSE, true);
                                intent2.putExtra(DeviceMoreActivity.ARGS_LICENSE_HTML_RES, i);
                                intent2.putExtra(DeviceMoreActivity.ARGS_PRIVACY_HTML_RES, i2);
                                intent2.putExtra("enable_privacy_setting", !CoreApi.a().D());
                                intent2.putExtra(DeviceMoreActivity.ARGS_USR_EXP_PLAN_CONTENT, Html.fromHtml(v3LocalLicense.mPlan));
                            }
                            openMoreMenu2(arrayList, true, 1220, intent, intent2);
                        }
                        this.mNewFirmView.setVisibility(8);
                        SDKLog.d(Tag.b, "more");
                        return;
                    }
                    return;
                case R.id.title_bar_share:
                    if (!this.mCameraDevice.f().c()) {
                        ToastUtil.a((Context) activity(), (int) R.string.power_off_share);
                        return;
                    } else if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        this.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
                            public void onSnap(Bitmap bitmap) {
                                String a2;
                                if (bitmap != null && (a2 = FileUtil.a(false, CameraPlayer2Activity.this.mCameraDevice.getDid())) != null) {
                                    try {
                                        FileOutputStream fileOutputStream = new FileOutputStream(a2);
                                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                                        fileOutputStream.close();
                                        bitmap.recycle();
                                        CameraPlayer2Activity.this.openShareMediaActivity(CameraPlayer2Activity.this.mDeviceStat.name, "", a2);
                                    } catch (IOException unused) {
                                    }
                                }
                            }
                        });
                        return;
                    } else {
                        return;
                    }
                case R.id.tvAlert:
                    SDKLog.b(TAG, "tvAlarm click");
                    Event.a(Event.d);
                    Event.a(Event.az);
                    if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        if (!this.mCameraDevice.f().c()) {
                            ToastUtil.a((Context) activity(), (int) R.string.power_off);
                            return;
                        } else if (!this.mCameraDevice.e().j() || this.mCameraDevice.isReadOnlyShared()) {
                            startActivity(new Intent(this, AlarmVideoActivity.class));
                            return;
                        } else {
                            startActivity(new Intent(this, AlarmGuideActivity.class));
                            return;
                        }
                    } else {
                        return;
                    }
                case R.id.tvCloud:
                    Event.a(Event.aC);
                    if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        if (!this.mCameraDevice.f().c()) {
                            ToastUtil.a((Context) this, (int) R.string.power_off);
                            return;
                        }
                        if (this.mCameraDevice.e().f() || this.mCameraDevice.e().e()) {
                            FrameManager.b().k().openCloudVideoListActivity(this, this.mCameraDevice.getDid(), this.mCameraDevice.getName());
                        } else if (activity() != null) {
                            CloudVideoNetUtils.getInstance().openCloudVideoBuyPage(this, this.mCameraDevice.getDid());
                        }
                        this.mCameraDevice.e().j(true);
                        this.flCloudVideoTips.setVisibility(8);
                        return;
                    }
                    return;
                case R.id.tvFloat:
                    SDKLog.b(TAG, "tvPIP click");
                    if (!this.mCameraDevice.f().c()) {
                        ToastUtil.a((Context) activity(), (int) R.string.power_off);
                        return;
                    } else if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        if (XmPluginHostApi.instance().getApiLevel() < 57) {
                            SDKLog.e(Tag.b, "pip not 36");
                            ToastUtil.a((Context) activity(), (CharSequence) getResources().getString(R.string.api_tip_title));
                            return;
                        }
                        Event.a(Event.m);
                        if (com.Utils.Util.a((Context) activity())) {
                            XmPluginHostApi.instance().openCameraFloatingWindow(this.mCameraDevice.getDid());
                            finish();
                            return;
                        }
                        ToastUtil.a((Context) activity(), (CharSequence) getResources().getString(R.string.float_tip));
                        return;
                    } else {
                        return;
                    }
                case R.id.tvFloatingWindow:
                    LogUtil.a(TAG, "tvFloatingWindow click");
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
                case R.id.tvLandResolution:
                case R.id.tvResolution:
                    SDKLog.b(TAG, "tvsResolution click");
                    Event.a(Event.h);
                    if (!this.mCameraDevice.f().c()) {
                        ToastUtil.a((Context) activity(), (int) R.string.power_off);
                        return;
                    } else {
                        toggleResolution();
                        return;
                    }
                case R.id.tvLandSleep:
                case R.id.tvSleep:
                    Event.a(Event.aF);
                    if (canStepOut(R.string.speaking_block, R.string.recording_block) && this.mCameraDevice != null && this.mCameraDevice.f() != null) {
                        this.mCameraDevice.f().a(CameraPropertyBase.l, false, (Callback<Void>) new Callback<Void>() {
                            public void onSuccess(Void voidR) {
                                if (!CameraPlayer2Activity.this.isFinishing()) {
                                    Event.a(Event.l);
                                    CameraPlayer2Activity.this.mCameraDevice.g().a(CameraPropertyBase.l);
                                    CameraPlayer2Activity.this.mCameraDevice.f().a(CameraPropertyBase.l, (Object) false);
                                    CameraPlayer2Activity.this.toggleCtrlContainerVisibility();
                                    SDKLog.b(CameraPlayer2Activity.TAG, "set sleep success");
                                }
                            }

                            public void onFailure(int i, String str) {
                                if (!CameraPlayer2Activity.this.isFinishing()) {
                                    CameraPlayer2Activity.this.mCameraDevice.g().a(CameraPropertyBase.l);
                                    CameraPlayer2Activity.this.mCameraDevice.f().a(CameraPropertyBase.l, (Object) true);
                                    CameraPlayer2Activity.this.toggleCtrlContainerVisibility();
                                    ToastUtil.a((Context) CameraPlayer2Activity.this.activity(), (int) R.string.action_fail);
                                    SDKLog.b(CameraPlayer2Activity.TAG, "set sleep failed:" + i + " s:" + str);
                                }
                            }
                        });
                        return;
                    }
                    return;
                case R.id.tvPlayback:
                    Event.a(Event.aA);
                    if (!this.mCameraDevice.f().c()) {
                        ToastUtil.a((Context) activity(), (int) R.string.power_off);
                        return;
                    } else if (this.mCameraPlayer == null || !this.mCameraPlayer.d()) {
                        ToastUtil.a((Context) activity(), (int) R.string.no_playback_for_connect);
                        return;
                    } else if (!this.sdcardGetSuccess) {
                        ToastUtil.a((Context) activity(), (int) R.string.sd_card_hint_title);
                        return;
                    } else if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        if (this.sdcardErrorCode == -2003 || this.sdcardErrorCode == -2005) {
                            startActivity(new Intent(this, NoMemoryCardActivity.class));
                            return;
                        } else if (this.sdcardErrorCode == -2000) {
                            ToastUtil.a((Context) this, (CharSequence) getString(R.string.camera_storage_sdcard_formating_tips));
                            return;
                        } else if (this.sdcardErrorCode == -2002) {
                            startActivity(new Intent(this, SDCardStatusActivityNew.class));
                            return;
                        } else {
                            SDCardInfo sDCardInfo = this.mCameraDevice.c().i;
                            if (sDCardInfo != null) {
                                int i3 = sDCardInfo.e;
                                if (i3 == 4) {
                                    ToastUtil.a((Context) this, (CharSequence) getString(R.string.camera_storage_sdcard_formating_tips));
                                    return;
                                } else if (i3 == 3) {
                                    startActivity(new Intent(this, SDCardStatusActivityNew.class));
                                    return;
                                } else if (i3 == 1 || i3 == 5) {
                                    startActivity(new Intent(this, NoMemoryCardActivity.class));
                                    return;
                                }
                            }
                            startActivity(new Intent(this, SDCardTimeLinePlayerActivity.class));
                            return;
                        }
                    } else {
                        return;
                    }
                case R.id.tv_face:
                    Event.a(Event.aD);
                    if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        if (!this.mCameraDevice.f().c()) {
                            ToastUtil.a((Context) this, (int) R.string.power_off);
                            return;
                        } else {
                            FrameManager.b().k().openFaceManagerActivity(this, this.mCameraDevice.getDid());
                            return;
                        }
                    } else {
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
        Event.a(Event.at);
        if (this.mCameraPlayer == null || !this.mCameraPlayer.m()) {
            this.mLastTime = -1;
            this.mCurrentTime = 0;
            if (!this.mCameraDevice.f().c()) {
                ToastUtil.a((Context) activity(), (int) R.string.power_off);
                this.mLandRecord.setCurrentState(0);
                this.msvVideoRecord.setCurrentState(0);
                return;
            }
            String a2 = FileUtil.a(true, this.mCameraDevice.getDid());
            if (this.mCameraPlayer == null || !this.mCameraPlayer.i()) {
                ToastUtil.a((Context) activity(), (int) R.string.record_not_connect);
                this.mLandRecord.setCurrentState(0);
                this.msvVideoRecord.setCurrentState(0);
            } else if (!com.Utils.Util.b(activity(), "android.permission.WRITE_EXTERNAL_STORAGE")) {
                ToastUtil.a((Context) activity(), (int) R.string.no_write_permission);
                this.mLandRecord.setCurrentState(0);
                this.msvVideoRecord.setCurrentState(0);
            } else if (!TextUtils.isEmpty(a2)) {
                this.mCameraPlayer.a(a2, 1);
                if (this.mFullScreen) {
                    this.mLandRecordTimer.setText(getString(R.string.main_recording, new Object[]{"00:00:00"}));
                    this.mLandRecordTimer.setVisibility(0);
                } else if (this.mFrameRate.getVisibility() != 0) {
                    this.mFrameRate.setVisibility(0);
                    this.mFrameRate.setText(getString(R.string.main_recording, new Object[]{"00:00:00"}));
                }
                SDKLog.b(TAG, "startRecord");
                this.mLandRecord.setCurrentState(1);
                this.msvVideoRecord.setCurrentState(1);
            } else {
                ToastUtil.a((Context) activity(), (int) R.string.snip_video_failed);
                this.mLandRecord.setCurrentState(0);
                this.msvVideoRecord.setCurrentState(0);
            }
        } else {
            this.mLandRecord.setCurrentState(1);
            this.msvVideoRecord.setCurrentState(1);
        }
    }

    /* access modifiers changed from: protected */
    public void doStopRecord() {
        int i = this.mCurrentTime;
        this.mLastTime = -1;
        this.mCurrentTime = 0;
        this.mFrameRate.setTextColor(getResources().getColor(R.color.sub_title_bar_text_color_white));
        if (this.mCameraPlayer == null || !this.mCameraPlayer.m()) {
            if (this.mLandRecord != null) {
                this.mLandRecord.setCurrentState(0);
            }
            if (this.msvVideoRecord != null) {
                this.msvVideoRecord.setCurrentState(0);
                return;
            }
            return;
        }
        this.mCameraPlayer.a((XmMp4Record.IRecordListener) new XmMp4Record.IRecordListener() {
            public void onSuccess(String str) {
                CameraPlayer2Activity.this.mLandRecordTimer.setVisibility(8);
                CameraPlayer2Activity.this.onVideoRecorded(str);
                if (CameraPlayer2Activity.this.mLandRecord != null) {
                    CameraPlayer2Activity.this.mLandRecord.setCurrentState(0);
                }
                if (CameraPlayer2Activity.this.msvVideoRecord != null) {
                    CameraPlayer2Activity.this.msvVideoRecord.setCurrentState(0);
                }
            }

            public void onFailed(int i, String str) {
                CameraPlayer2Activity.this.mLandRecordTimer.setVisibility(8);
                if (CameraPlayer2Activity.this.mLandRecord != null) {
                    CameraPlayer2Activity.this.mLandRecord.setCurrentState(0);
                }
                if (CameraPlayer2Activity.this.msvVideoRecord != null) {
                    CameraPlayer2Activity.this.msvVideoRecord.setCurrentState(0);
                }
                if (i == -2) {
                    ToastUtil.a((Context) CameraPlayer2Activity.this.activity(), (int) R.string.snip_video_failed_time_mini);
                } else {
                    ToastUtil.a((Context) CameraPlayer2Activity.this.activity(), (int) R.string.snip_video_failed);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void doStopCall() {
        if (this.mCameraPlayer != null && this.mCameraPlayer.o()) {
            this.mCameraPlayer.l();
            this.mHandler.removeMessages(2101);
        }
        if (this.msvCall != null && this.msvCall.getCurrentState() == 1) {
            this.msvCall.setCurrentState(0);
        }
        if (this.isAudioOn) {
            this.msvAudio.setCurrentState(1);
            this.msvLandAudio.setCurrentState(1);
            if (this.mCameraPlayer != null) {
                this.mCameraPlayer.b(false);
                return;
            }
            return;
        }
        this.msvAudio.setCurrentState(0);
        this.msvLandAudio.setCurrentState(0);
        if (this.mCameraPlayer != null) {
            this.mCameraPlayer.b(true);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1220) {
            if (XmPluginHostApi.instance().getApiLevel() > 66 && this.mDeviceStat != null) {
                boolean isUsrExpPlanEnabled = XmPluginHostApi.instance().isUsrExpPlanEnabled(this.mDeviceStat.did);
                Event.ch = isUsrExpPlanEnabled;
                if (isUsrExpPlanEnabled != this.mCameraDevice.f().b(CameraProperties.b)) {
                    this.mCameraDevice.f().a(CameraProperties.b, isUsrExpPlanEnabled, (Callback<Void>) null);
                }
            }
            if (i2 == -1 && intent != null) {
                String stringExtra = intent.getStringExtra("result_data");
                if (TextUtils.isEmpty(stringExtra) || !stringExtra.equals(DeviceMoreActivity.ARGS_RESULT_REMOVE_LICENSE)) {
                    String stringExtra2 = intent.getStringExtra("menu");
                    if (!TextUtils.isEmpty(stringExtra2) && stringExtra2.equals(getString(R.string.more_alarm_setting))) {
                        Toast.makeText(activity(), R.string.auth_fail, 0).show();
                        return;
                    }
                    return;
                }
                this.mCameraDevice.e().g(true);
                finish();
                return;
            }
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: private */
    public void setResolutionText(int i, boolean z) {
        if (this.tvResolution != null) {
            switch (i) {
                case 1:
                    this.tvResolution.setText(R.string.quality_low);
                    this.tvLandResolution.setText(R.string.quality_low);
                    return;
                case 2:
                case 3:
                    this.tvResolution.setText(R.string.quality_fhd);
                    this.tvLandResolution.setText(R.string.quality_fhd);
                    return;
                default:
                    this.tvResolution.setText(R.string.quality_auto);
                    this.tvLandResolution.setText(R.string.quality_auto);
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setOrientation(boolean z) {
        if (!z) {
            setRequestedOrientation(1);
        } else if (!this.mCameraDevice.f().c()) {
            ToastUtil.a((Context) activity(), (int) R.string.power_off);
        } else {
            setRequestedOrientation(0);
        }
    }

    public void onResume() {
        super.onResume();
        TimeUtils.b();
        isCloudVideoUser();
        this.mCameraDevice.updateDeviceStatus();
        if (this.mCameraDevice.isOwner()) {
            this.mCameraDevice.checkDeviceUpdateInfo(new Callback<DeviceUpdateInfo>() {
                public void onFailure(int i, String str) {
                }

                public void onSuccess(DeviceUpdateInfo deviceUpdateInfo) {
                    Message.obtain(CameraPlayer2Activity.this.mHandler, 1, deviceUpdateInfo).sendToTarget();
                }
            });
        }
        ((TextView) findViewById(R.id.title_bar_title)).setText(this.mCameraDevice.getName());
        if (!this.mNeedLicense || this.mCameraDevice.isShared()) {
            if (this.mCameraPlayer == null) {
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
        if (this.isAudioOn) {
            this.msvAudio.setCurrentState(1);
            this.msvLandAudio.setCurrentState(1);
        } else {
            this.msvAudio.setCurrentState(0);
            this.msvLandAudio.setCurrentState(0);
        }
        detectSDCard();
    }

    public void onStop() {
        super.onStop();
        this.mHandler.removeMessages(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
        doStopCall();
        doStopRecord();
    }

    public void onPause() {
        if (this.msvAudio.getCurrentState() == 1) {
            this.isAudioOn = true;
        } else {
            this.isAudioOn = false;
        }
        super.onPause();
        dismissSnapshotPopupRunnable(0);
    }

    /* access modifiers changed from: protected */
    public void resumeCamera() {
        if (this.mPauseView != null) {
            this.mPauseView.setVisibility(8);
        }
        if (NetworkUtil.c(activity())) {
            this.mAllowMobileNetwork = true;
        }
        if (this.isStartPlay || this.mCameraPlayer == null) {
            setPlayTime();
        } else {
            this.mCameraPlayer.f();
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
                                CameraPlayer2Activity.this.hideUpdateIng(true);
                                CameraPlayer2Activity.this.mHandler.removeMessages(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
                            } else if (numArr[1].intValue() < 100) {
                                CameraPlayer2Activity.this.showUpdateIng(numArr[1].intValue());
                                CameraPlayer2Activity.this.mHandler.sendEmptyMessageDelayed(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS, com.xiaomi.smarthome.download.Constants.x);
                            } else {
                                CameraPlayer2Activity.this.hideUpdateIng(true);
                                CameraPlayer2Activity.this.mHandler.removeMessages(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
                                CameraPlayer2Activity.this.startPlay();
                            }
                        }
                    }

                    public void onFailure(int i, String str) {
                        CameraPlayer2Activity.this.hideUpdateIng(false);
                        CameraPlayer2Activity.this.mHandler.removeMessages(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
                    }
                });
            } else if (i != 4000) {
                switch (i) {
                    case AMapException.CODE_AMAP_NEARBY_INVALID_USERID:
                        hidError();
                        if ((!NetworkUtil.c(activity()) || this.mAllowMobileNetwork) && this.mCameraDevice.f().c()) {
                            hidError();
                            showLoading(getString(R.string.camera_play_initial_0));
                        }
                        startPlay();
                        return;
                    case 2101:
                        if (this.mCameraPlayer != null && this.mCameraPlayer.o()) {
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
        if (TextUtils.isEmpty(this.mCameraDevice.r()) || TextUtils.isEmpty(this.mCameraDevice.w())) {
            updatePwd();
        } else if (this.mCameraDevice.f().c()) {
            if (this.mPauseView.getVisibility() == 0) {
                this.mPauseView.setVisibility(8);
            }
            if (this.mCameraPlayer == null) {
                this.mCameraPlayer = new CameraPlayer(this, this.mCameraDevice, this, this.mVideoView);
                this.mCameraPlayer.a(this.mIRecodeTimeListener);
            }
            if (this.mCameraPlayer != null) {
                this.mCameraPlayer.f7862a = this.resolutionChangedListener;
            }
            if (!NetworkUtil.c(activity()) || this.mAllowMobileNetwork) {
                this.mCameraPlayer.f();
                this.isStartPlay = true;
                return;
            }
            pauseCamera();
        } else if (this.mPowerOffView.getVisibility() == 8) {
            this.mPowerOffView.setVisibility(0);
            hideLoading();
        }
    }

    /* access modifiers changed from: protected */
    public void refreshUI() {
        if (XmPluginHostApi.instance().getApiLevel() >= 62) {
            if (!this.mCameraDevice.f().c()) {
                if (this.mPowerOffView.getVisibility() == 8) {
                    this.mPowerOffView.setVisibility(0);
                    hideLoading();
                    if (this.mCameraPlayer != null) {
                        this.mCameraPlayer.g();
                    }
                    if (this.mPauseView.getVisibility() == 0) {
                        this.mPauseView.setVisibility(8);
                    }
                }
                if (this.mCameraPlayer != null) {
                    if (this.mCameraPlayer.m()) {
                        doStopRecord();
                    }
                    if (this.mCameraPlayer.o()) {
                        doStopCall();
                    }
                }
            } else if (this.mPowerOffView.getVisibility() == 0) {
                this.mPowerOffView.setVisibility(8);
                if (this.mCameraPlayer != null) {
                    this.mCameraPlayer.f();
                } else {
                    startPlay();
                }
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mCameraPlayer != null) {
            this.mCameraPlayer.a(this.mShowView);
        }
        if (this.mVideoView != null) {
            if (this.mCameraDevice != null && !TextUtils.isEmpty(this.mCameraDevice.getModel()) && !TextUtils.isEmpty(this.mCameraDevice.getDid())) {
                String a2 = MD5Util.a(this.mCameraDevice.getModel() + this.mCameraDevice.getDid());
                this.mSurfaceViewScale = this.mVideoView.getScale();
                if (this.mSurfaceViewScale >= ((VideoGlSurfaceView) this.mVideoView.getSurfaceView()).getMiniScale()) {
                    SharePrefsManager.b(SHApplication.getAppContext(), a2, "scale", this.mSurfaceViewScale);
                    this.mSurfaceViewOffsetX = ((VideoViewGlImpl) this.mVideoView).a();
                    SharePrefsManager.b(SHApplication.getAppContext(), a2, CameraPlayerBaseActivity.SP_KEY_OFFSET_X, this.mSurfaceViewOffsetX);
                    this.mSurfaceViewOffsetY = ((VideoViewGlImpl) this.mVideoView).b();
                    SharePrefsManager.b(SHApplication.getAppContext(), a2, CameraPlayerBaseActivity.SP_KEY_OFFSET_Y, this.mSurfaceViewOffsetY);
                } else {
                    SharePrefsManager.b(SHApplication.getAppContext(), a2, "scale", 1.0f);
                    SharePrefsManager.b(SHApplication.getAppContext(), a2, CameraPlayerBaseActivity.SP_KEY_OFFSET_X, 0.0f);
                    SharePrefsManager.b(SHApplication.getAppContext(), a2, CameraPlayerBaseActivity.SP_KEY_OFFSET_Y, 0.0f);
                }
            }
            this.mVideoView.release();
        }
        if (this.mNetworkMonitor != null) {
            this.mNetworkMonitor.unregister();
        }
        if (this.mCameraDevice != null) {
            clearAllCacheRecording();
        }
        Event.b();
    }

    /* access modifiers changed from: protected */
    public void doResume() {
        this.mPauseView.setVisibility(8);
        if (this.mCameraPlayer == null || this.mCameraPlayer.i()) {
            setPlayTime();
            return;
        }
        showLastLoading();
        this.mCameraPlayer.a(0);
    }

    /* access modifiers changed from: private */
    public void videoClick() {
        SDKLog.b(TAG, "videoClick");
        if (this.mCameraDevice != null && this.mCameraDevice.g() != null && this.mCameraDevice != null && this.mCameraDevice.f() != null && this.mCameraDevice.f().c()) {
            if (getRequestedOrientation() != 1) {
                if (this.mLLTopCtrlLandscape.getTranslationY() < 0.0f) {
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mLLTopCtrlLandscape, "translationY", new float[]{(float) (-this.mLLTopCtrlLandscape.getHeight()), 0.0f});
                    ofFloat.setDuration(200);
                    ofFloat.start();
                } else {
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mLLTopCtrlLandscape, "translationY", new float[]{0.0f, (float) (-this.mLLTopCtrlLandscape.getHeight())});
                    ofFloat2.setDuration(200);
                    ofFloat2.start();
                }
                if (this.llCall.getTranslationY() > 0.0f) {
                    ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.llCall, "translationY", new float[]{(float) this.llCall.getHeight(), 0.0f});
                    ofFloat3.setDuration(200);
                    ofFloat3.start();
                    return;
                }
                ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.llCall, "translationY", new float[]{0.0f, (float) this.llCall.getHeight()});
                ofFloat4.setDuration(200);
                ofFloat4.start();
                return;
            }
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.llRightCtrlContainer.getLayoutParams();
            if (this.llRightCtrlContainer.getTranslationX() > 0.0f) {
                ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.llRightCtrlContainer, "translationX", new float[]{(float) (this.llRightCtrlContainer.getWidth() + layoutParams.rightMargin), 0.0f});
                ofFloat5.setDuration(200);
                ofFloat5.start();
            } else {
                ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.llRightCtrlContainer, "translationX", new float[]{0.0f, (float) (this.llRightCtrlContainer.getWidth() + layoutParams.rightMargin)});
                ofFloat6.setDuration(200);
                ofFloat6.start();
            }
            if (this.llCall.getTranslationY() > 0.0f) {
                ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(this.llCall, "translationY", new float[]{(float) this.llCall.getHeight(), 0.0f});
                ofFloat7.setDuration(200);
                ofFloat7.start();
                return;
            }
            ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(this.llCall, "translationY", new float[]{0.0f, (float) this.llCall.getHeight()});
            ofFloat8.setDuration(200);
            ofFloat8.start();
        }
    }

    /* access modifiers changed from: private */
    public void toggleCtrlContainerVisibility() {
        boolean c = this.mCameraDevice.f().c();
        SDKLog.b(TAG, "isShow::" + c);
        if (getRequestedOrientation() == 1) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.llRightCtrlContainer.getLayoutParams();
            if (c) {
                if (this.llRightCtrlContainer.getTranslationX() > 0.0f) {
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.llRightCtrlContainer, "translationX", new float[]{(float) (this.llRightCtrlContainer.getWidth() + layoutParams.rightMargin), 0.0f});
                    ofFloat.setDuration(200);
                    ofFloat.start();
                }
                if (this.llCall.getTranslationY() > 0.0f) {
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.llCall, "translationY", new float[]{(float) this.llCall.getHeight(), 0.0f});
                    ofFloat2.setDuration(200);
                    ofFloat2.start();
                    return;
                }
                return;
            }
            if (this.llRightCtrlContainer.getTranslationX() <= 0.0f) {
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.llRightCtrlContainer, "translationX", new float[]{0.0f, (float) (this.llRightCtrlContainer.getWidth() + layoutParams.rightMargin)});
                ofFloat3.setDuration(200);
                ofFloat3.start();
            }
            if (this.llCall.getTranslationY() <= 0.0f) {
                ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.llCall, "translationY", new float[]{0.0f, (float) this.llCall.getHeight()});
                ofFloat4.setDuration(200);
                ofFloat4.start();
            }
        } else if (c) {
            if (this.mLLTopCtrlLandscape.getTranslationY() < 0.0f) {
                this.mLLTopCtrlLandscape.bringToFront();
                ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.mLLTopCtrlLandscape, "translationY", new float[]{(float) (-this.mLLTopCtrlLandscape.getHeight()), 0.0f});
                ofFloat5.setDuration(200);
                ofFloat5.start();
            }
            if (this.llCall.getTranslationY() > 0.0f) {
                ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.llCall, "translationY", new float[]{(float) this.llCall.getHeight(), 0.0f});
                ofFloat6.setDuration(200);
                ofFloat6.start();
            }
        } else {
            if (this.mLLTopCtrlLandscape.getTranslationY() >= 0.0f) {
                ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(this.mLLTopCtrlLandscape, "translationY", new float[]{0.0f, (float) (-this.mLLTopCtrlLandscape.getHeight())});
                ofFloat7.setDuration(200);
                ofFloat7.start();
            }
            if (this.llCall.getTranslationY() <= 0.0f) {
                ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(this.llCall, "translationY", new float[]{0.0f, (float) this.llCall.getHeight()});
                ofFloat8.setDuration(200);
                ofFloat8.start();
            }
        }
    }

    private void snapShot() {
        if (this.isSnapshotting) {
            ToastUtil.a((Context) activity(), (int) R.string.btn_click_too_much);
            return;
        }
        this.isSnapshotting = true;
        if (!this.mCameraDevice.f().c()) {
            ToastUtil.a((Context) activity(), (int) R.string.power_off);
            this.isSnapshotting = false;
        } else if (!com.Utils.Util.b(activity(), "android.permission.WRITE_EXTERNAL_STORAGE")) {
            ToastUtil.a((Context) activity(), (int) R.string.no_write_permission);
            this.isSnapshotting = false;
        } else if (this.mCameraDevice == null || this.mCameraDevice.f() == null || !this.mCameraDevice.f().c()) {
            this.isSnapshotting = false;
            ToastUtil.a((Context) activity(), (int) R.string.power_off);
        } else if (this.mCameraPlayer == null || !this.mCameraPlayer.i()) {
            this.isSnapshotting = false;
            ToastUtil.a((Context) activity(), (int) R.string.snap_failed_paused);
        } else {
            this.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
                public void onSnap(final Bitmap bitmap) {
                    CameraPlayer2Activity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            CameraPlayer2Activity.this.onSnapShot(bitmap);
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
        if (bitmap != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(a2);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.close();
            } catch (IOException unused) {
                this.isSnapshotting = false;
                return;
            }
        }
        final Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, 300, (bitmap.getHeight() * 300) / bitmap.getWidth(), false);
        runOnUiThread(new Runnable() {
            public void run() {
                if (new File(a2).exists()) {
                    ImageView imageView = (ImageView) CameraPlayer2Activity.this.findViewById(R.id.ivShotPic);
                    if (CameraPlayer2Activity.this.mLLFuncPopup.getVisibility() == 0) {
                        CameraPlayer2Activity.this.mLLFuncPopup.setVisibility(8);
                    }
                    CameraPlayer2Activity.this.mLLFuncPopup.setLayoutParams((FrameLayout.LayoutParams) CameraPlayer2Activity.this.mLLFuncPopup.getLayoutParams());
                    CameraPlayer2Activity.this.mLLFuncPopup.startAnimation(AnimationUtils.loadAnimation(CameraPlayer2Activity.this.activity(), R.anim.anim_snap_shot_in));
                    CameraPlayer2Activity.this.mLLFuncPopup.setVisibility(0);
                    CameraPlayer2Activity.this.dismissSnapshotPopupRunnable(3000);
                    if (createScaledBitmap != null) {
                        imageView.setImageBitmap(createScaledBitmap);
                    }
                    ContentValues contentValues = new ContentValues(4);
                    contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
                    contentValues.put(Downloads._DATA, a2);
                    contentValues.put("mime_type", "image/jpeg");
                    try {
                        if (!Build.MODEL.equals("HM 1SC")) {
                            CameraPlayer2Activity.this.activity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                        }
                        SDKLog.b(CameraPlayer2Activity.TAG, "snap success");
                        boolean unused = CameraPlayer2Activity.this.isSnapshotting = false;
                        final LocalFileManager.LocalFile b = CameraPlayer2Activity.this.mCameraDevice.b().b(a2);
                        if (b != null) {
                            imageView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    if (CameraPlayer2Activity.this.canStepOut(R.string.speaking_block, R.string.recording_block)) {
                                        CameraPlayer2Activity.this.dismissSnapshotPopupRunnable(0);
                                        Intent intent = new Intent(CameraPlayer2Activity.this, LocalPicReviewActivity.class);
                                        intent.putExtra("file_path", b.d);
                                        CameraPlayer2Activity.this.startActivity(intent);
                                    }
                                }
                            });
                        }
                    } catch (Throwable unused2) {
                        boolean unused3 = CameraPlayer2Activity.this.isSnapshotting = false;
                    }
                } else {
                    boolean unused4 = CameraPlayer2Activity.this.isSnapshotting = false;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void dismissSnapshotPopupRunnable(long j) {
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (CameraPlayer2Activity.this.mLLFuncPopup != null && CameraPlayer2Activity.this.mLLFuncPopup.getVisibility() != 8) {
                    CameraPlayer2Activity.this.mLLFuncPopup.startAnimation(AnimationUtils.loadAnimation(CameraPlayer2Activity.this.activity(), R.anim.anim_snap_shot_out));
                    CameraPlayer2Activity.this.mLLFuncPopup.setVisibility(8);
                }
            }
        }, j);
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
                            final Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, 300, (bitmap.getHeight() * 300) / bitmap.getWidth(), false);
                            CameraPlayer2Activity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    if (createScaledBitmap != null) {
                                        ImageView imageView = (ImageView) CameraPlayer2Activity.this.findViewById(R.id.ivShotPic);
                                        imageView.setImageBitmap(createScaledBitmap);
                                        if (CameraPlayer2Activity.this.mLLFuncPopup.getVisibility() == 0) {
                                            CameraPlayer2Activity.this.mLLFuncPopup.setVisibility(8);
                                        }
                                        CameraPlayer2Activity.this.mLLFuncPopup.setLayoutParams((FrameLayout.LayoutParams) CameraPlayer2Activity.this.mLLFuncPopup.getLayoutParams());
                                        CameraPlayer2Activity.this.mLLFuncPopup.startAnimation(AnimationUtils.loadAnimation(CameraPlayer2Activity.this.activity(), R.anim.anim_snap_shot_in));
                                        CameraPlayer2Activity.this.mLLFuncPopup.setVisibility(0);
                                        CameraPlayer2Activity.this.dismissSnapshotPopupRunnable(3000);
                                        imageView.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View view) {
                                                if (CameraPlayer2Activity.this.canStepOut(R.string.speaking_block, R.string.recording_block)) {
                                                    CameraPlayer2Activity.this.dismissSnapshotPopupRunnable(0);
                                                    Intent intent = new Intent(CameraPlayer2Activity.this, LocalVideoPlayActivity.class);
                                                    intent.putExtra("file_path", b.d);
                                                    CameraPlayer2Activity.this.startActivity(intent);
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
        Event.a(Event.au);
        if (this.mCameraPlayer != null && !this.mCameraPlayer.c()) {
            ToastUtil.a((Context) activity(), (int) R.string.history_video_resolution_hd_only);
        } else if (this.mCameraPlayer == null || !this.mCameraPlayer.m()) {
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity());
            builder.a((int) R.string.quality_choice);
            final String[] strArr = {getString(R.string.quality_auto), getString(R.string.quality_low), getString(R.string.quality_fhd)};
            this.selectedIndex = this.mCameraDevice.e().d();
            if (this.selectedIndex == 3) {
                this.selectedIndex = 2;
            }
            builder.a((CharSequence[]) strArr, this.selectedIndex, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CameraPlayer2Activity.this.selectedIndex = i;
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (CameraPlayer2Activity.this.selectedIndex >= 0 && CameraPlayer2Activity.this.selectedIndex < strArr.length && !TextUtils.isEmpty(strArr[CameraPlayer2Activity.this.selectedIndex])) {
                        CameraPlayer2Activity.this.setResolutionText(CameraPlayer2Activity.this.selectedIndex, true);
                        if (CameraPlayer2Activity.this.selectedIndex == 0) {
                            Event.a(Event.av, "type", (Object) 1);
                        } else if (CameraPlayer2Activity.this.selectedIndex == 1) {
                            Event.a(Event.av, "type", (Object) 2);
                        } else if (CameraPlayer2Activity.this.selectedIndex == 2) {
                            Event.a(Event.av, "type", (Object) 3);
                        }
                        if (CameraPlayer2Activity.this.selectedIndex == 2) {
                            CameraPlayer2Activity.this.selectedIndex = 3;
                        }
                        CameraPlayer2Activity.this.mCameraDevice.e().b(CameraPlayer2Activity.this.selectedIndex);
                        if (CameraPlayer2Activity.this.mCameraPlayer != null) {
                            CameraPlayer2Activity.this.mCameraPlayer.b(CameraPlayer2Activity.this.selectedIndex);
                        }
                    }
                }
            }).d();
        } else {
            ToastUtil.a((Context) activity(), (int) R.string.record_resolution_block);
        }
    }

    /* access modifiers changed from: private */
    public void upDateTimeTitle(int i) {
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String format = String.format("%s:%s:%s", new Object[]{decimalFormat.format((long) (i / 3600)), decimalFormat.format((long) ((i / 60) % 60)), decimalFormat.format((long) (i % 60))});
        this.mFrameRate.setTextColor(-65536);
        this.mFrameRate.setText(getString(R.string.main_recording, new Object[]{format}));
        this.mLandRecordTimer.setText(getString(R.string.main_recording, new Object[]{format}));
    }

    /* access modifiers changed from: protected */
    public void detectSDCard() {
        if (this.mCameraDevice != null) {
            this.sdcardErrorCode = 0;
            this.mCameraDevice.c().b((Callback<SDCardInfo>) new Callback<SDCardInfo>() {
                public void onSuccess(SDCardInfo sDCardInfo) {
                    CameraPlayer2Activity.this.sdcardGetSuccess = true;
                    SDKLog.b(CameraPlayer2Activity.TAG, "detectSDCard onSuccess:" + sDCardInfo.e);
                    if (!CameraPlayer2Activity.this.mCameraDevice.e().f8058a) {
                        return;
                    }
                    if (sDCardInfo.e == 1 || sDCardInfo.e == 3 || sDCardInfo.e == 5) {
                        CameraPlayer2Activity.this.showSDCardHintDialog();
                    }
                }

                public void onFailure(int i, String str) {
                    SDKLog.b(CameraPlayer2Activity.TAG, "detectSDCard onFailure:" + i + " s:" + str);
                    if (i == -2003 || i == -2002 || i == -2005) {
                        CameraPlayer2Activity.this.sdcardGetSuccess = true;
                        CameraPlayer2Activity.this.sdcardErrorCode = i;
                        return;
                    }
                    CameraPlayer2Activity.this.sdcardGetSuccess = false;
                }
            }, true);
        }
    }

    /* access modifiers changed from: private */
    public void showSDCardHintDialog() {
        this.mSDCardHintDialog = new SDCardHintDialog(activity(), R.style.popupDialog);
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

    /* access modifiers changed from: private */
    public void initGuideCenter() {
        if (XmPluginHostApi.instance().getApiLevel() >= 75) {
            XmPluginHostApi.instance().checkAndRequestPermisson(activity(), true, (Callback<List<String>>) null, "android.permission.WRITE_EXTERNAL_STORAGE");
        }
    }

    private void showLicense() {
        if (this.mDeviceStat != null && !TextUtils.isEmpty(this.mDeviceStat.did)) {
            try {
                if (this.mCameraDevice.e().i()) {
                    this.mIsInit = true;
                    this.mNeedLicense = true;
                    LocalLicenseUtil.LocalLicense v3LocalLicense = LocalLicenseUtil.getV3LocalLicense(getResources());
                    int i = v3LocalLicense.mLicense;
                    int i2 = v3LocalLicense.mPrivacy;
                    if (i > 0) {
                        if (i2 > 0) {
                            if (XmPluginHostApi.instance().getApiLevel() >= 67) {
                                Intent intent = new Intent();
                                if ("cn".equalsIgnoreCase(ServerUtil.b())) {
                                    intent.putExtra("enable_privacy_setting", true);
                                    intent.putExtra(DeviceMoreActivity.ARGS_USR_EXP_PLAN_CONTENT, Html.fromHtml(v3LocalLicense.mPlan));
                                } else {
                                    intent.putExtra("enable_privacy_setting", false);
                                }
                                if (this.mDeviceStat == null) {
                                    return;
                                }
                                if (!TextUtils.isEmpty(this.mDeviceStat.did)) {
                                    showUserLicenseDialog(getString(R.string.privacy_title), getString(R.string.licences_content), i, getString(R.string.privacy_content), i2, new View.OnClickListener() {
                                        public void onClick(View view) {
                                            boolean unused = CameraPlayer2Activity.this.mNeedLicense = false;
                                            CameraPlayer2Activity.this.mHandler.removeMessages(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
                                            CameraPlayer2Activity.this.mHandler.sendEmptyMessage(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
                                            CameraPlayer2Activity.this.mHandler.sendEmptyMessage(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
                                            CameraPlayer2Activity.this.mCameraDevice.e().g(false);
                                            if (CameraPlayer2Activity.this.mCameraDevice.e().h()) {
                                                CameraPlayer2Activity.this.initGuideCenter();
                                            }
                                        }
                                    }, intent);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                    }
                    SDKLog.e(Tag.b, "load raw fail");
                    this.mCameraDevice.e().g(false);
                    if (this.mCameraDevice.e().h()) {
                        initGuideCenter();
                    }
                }
            } catch (Exception e) {
                LogUtil.b(TAG, "showLicense exception:" + e.getLocalizedMessage());
            }
        }
    }

    private void clearAllCacheRecording() {
        new Thread(new Runnable() {
            public void run() {
                File[] listFiles;
                String c = FileUtil.c(CameraPlayer2Activity.this.mCameraDevice.getDid());
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

    private void showUpdateDlg(final boolean z) {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity());
        builder.a((int) R.string.api_tip);
        builder.b((int) R.string.api_tip_title);
        builder.a(false);
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!CameraPlayer2Activity.this.isFinishing()) {
                    dialogInterface.dismiss();
                    CameraPlayer2Activity.this.finish();
                }
            }
        });
        builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!CameraPlayer2Activity.this.isFinishing()) {
                    if (z) {
                        CameraPlayer2Activity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(AppCode.u)));
                    }
                    dialogInterface.dismiss();
                    CameraPlayer2Activity.this.finish();
                }
            }
        });
        builder.d();
    }

    private void initMulti() {
        this.mLandRecord = (MultiStateView) findViewById(R.id.msvLandRecord);
        this.mLandRecord.addState(new MultiStateView.StateItem(R.drawable.selector_icon_record, new View.OnClickListener() {
            public void onClick(View view) {
                CameraPlayer2Activity.this.doStartRecord();
            }
        }));
        this.mLandRecord.addState(new MultiStateView.StateItem(R.drawable.selector_icon_recording, new View.OnClickListener() {
            public void onClick(View view) {
                CameraPlayer2Activity.this.doStopRecord();
            }
        }));
        this.mLandRecord.setCurrentState(0);
        this.msvAudio.addState(new MultiStateView.StateItem(R.drawable.selector_icon_audio_off, new View.OnClickListener() {
            public void onClick(View view) {
                Event.a(Event.ar);
                if (CameraPlayer2Activity.this.mCameraPlayer != null) {
                    CameraPlayer2Activity.this.mCameraPlayer.b(false);
                    CameraPlayer2Activity.this.msvAudio.setCurrentState(1);
                    CameraPlayer2Activity.this.msvLandAudio.setCurrentState(1);
                }
            }
        }));
        this.msvAudio.addState(new MultiStateView.StateItem(R.drawable.selector_icon_audio_on, new View.OnClickListener() {
            public void onClick(View view) {
                Event.a(Event.ar);
                if (CameraPlayer2Activity.this.mCameraPlayer != null) {
                    CameraPlayer2Activity.this.mCameraPlayer.b(true);
                    CameraPlayer2Activity.this.msvAudio.setCurrentState(0);
                    CameraPlayer2Activity.this.msvLandAudio.setCurrentState(0);
                }
            }
        }));
        this.msvAudio.setCurrentState(0);
        this.msvVideoRecord.addState(new MultiStateView.StateItem(R.drawable.selector_icon_record, new View.OnClickListener() {
            public void onClick(View view) {
                CameraPlayer2Activity.this.doStartRecord();
            }
        }));
        this.msvVideoRecord.addState(new MultiStateView.StateItem(R.drawable.selector_icon_recording, new View.OnClickListener() {
            public void onClick(View view) {
                CameraPlayer2Activity.this.doStopRecord();
            }
        }));
        this.msvVideoRecord.setCurrentState(0);
        this.msvLandAudio.addState(new MultiStateView.StateItem(R.drawable.selector_icon_audio_off, new View.OnClickListener() {
            public void onClick(View view) {
                Event.a(Event.ar);
                if (CameraPlayer2Activity.this.mCameraPlayer != null) {
                    CameraPlayer2Activity.this.mCameraPlayer.b(false);
                    CameraPlayer2Activity.this.msvAudio.setCurrentState(1);
                    CameraPlayer2Activity.this.msvLandAudio.setCurrentState(1);
                }
            }
        }));
        this.msvLandAudio.addState(new MultiStateView.StateItem(R.drawable.selector_icon_audio_on, new View.OnClickListener() {
            public void onClick(View view) {
                Event.a(Event.ar);
                if (CameraPlayer2Activity.this.mCameraPlayer != null) {
                    CameraPlayer2Activity.this.mCameraPlayer.b(true);
                    CameraPlayer2Activity.this.msvAudio.setCurrentState(0);
                    CameraPlayer2Activity.this.msvLandAudio.setCurrentState(0);
                }
            }
        }));
        this.msvLandAudio.setCurrentState(0);
        this.msvCall.addState(new MultiStateView.StateItem(R.drawable.selector_icon_call, new View.OnClickListener() {
            public void onClick(View view) {
                Event.a(Event.aB);
                CameraPlayer2Activity.this.msvCall.startAnimation(AnimationUtils.loadAnimation(CameraPlayer2Activity.this.activity(), R.anim.anim_scale_in_out));
                if (CameraPlayer2Activity.this.mCameraPlayer == null || !CameraPlayer2Activity.this.mCameraPlayer.d()) {
                    ToastUtil.a((Context) CameraPlayer2Activity.this.activity(), (int) R.string.call_no_play);
                } else if (!CameraPlayer2Activity.this.mCameraPlayer.c()) {
                    ToastUtil.a((Context) CameraPlayer2Activity.this.activity(), (int) R.string.not_on_live);
                } else {
                    boolean unused = CameraPlayer2Activity.this.isAudioOn = CameraPlayer2Activity.this.msvAudio.getCurrentState() == 1;
                    if (XmPluginHostApi.instance().getApiLevel() >= 75) {
                        if (!XmPluginHostApi.instance().checkAndRequestPermisson(CameraPlayer2Activity.this.activity(), true, new Callback<List<String>>() {
                            public void onSuccess(List<String> list) {
                                if (!CameraPlayer2Activity.this.mCameraPlayer.o()) {
                                    CameraPlayer2Activity.this.mCameraPlayer.k();
                                    CameraPlayer2Activity.this.mHandler.sendEmptyMessageDelayed(2101, 500);
                                    CameraPlayer2Activity.this.msvAudio.setCurrentState(1);
                                    CameraPlayer2Activity.this.msvLandAudio.setCurrentState(1);
                                    CameraPlayer2Activity.this.msvCall.setCurrentState(1);
                                    Event.g();
                                }
                            }

                            public void onFailure(int i, String str) {
                                ToastUtil.a((Context) CameraPlayer2Activity.this.activity(), (int) R.string.audio_permission_denied);
                            }
                        }, "android.permission.RECORD_AUDIO")) {
                            return;
                        }
                    } else if (!com.Utils.Util.b(CameraPlayer2Activity.this.activity(), "android.permission.RECORD_AUDIO")) {
                        ToastUtil.a((Context) CameraPlayer2Activity.this.activity(), (int) R.string.audio_permission_denied);
                        return;
                    }
                    if (!CameraPlayer2Activity.this.mCameraPlayer.o()) {
                        CameraPlayer2Activity.this.mCameraPlayer.k();
                        Event.h();
                        CameraPlayer2Activity.this.mHandler.sendEmptyMessageDelayed(2101, 500);
                        CameraPlayer2Activity.this.msvCall.setCurrentState(1);
                        CameraPlayer2Activity.this.msvAudio.setCurrentState(1);
                        CameraPlayer2Activity.this.msvLandAudio.setCurrentState(1);
                    }
                }
            }
        }));
        this.msvCall.addState(new MultiStateView.StateItem(R.drawable.selector_icon_calling, new View.OnClickListener() {
            public void onClick(View view) {
                CameraPlayer2Activity.this.msvCall.startAnimation(AnimationUtils.loadAnimation(CameraPlayer2Activity.this.activity(), R.anim.anim_scale_in_out));
                if (CameraPlayer2Activity.this.mCameraPlayer != null) {
                    CameraPlayer2Activity.this.mCameraPlayer.l();
                    CameraPlayer2Activity.this.mHandler.removeMessages(2101);
                    CameraPlayer2Activity.this.msvCall.setCurrentState(0);
                }
                if (CameraPlayer2Activity.this.isAudioOn) {
                    CameraPlayer2Activity.this.msvAudio.setCurrentState(1);
                    CameraPlayer2Activity.this.msvLandAudio.setCurrentState(1);
                    if (CameraPlayer2Activity.this.mCameraPlayer != null) {
                        CameraPlayer2Activity.this.mCameraPlayer.b(false);
                        return;
                    }
                    return;
                }
                CameraPlayer2Activity.this.msvAudio.setCurrentState(0);
                CameraPlayer2Activity.this.msvLandAudio.setCurrentState(0);
                CameraPlayer2Activity.this.mCameraPlayer.b(true);
            }
        }));
        this.msvCall.setCurrentState(0);
    }

    private void isCloudVideoUser() {
        if (Util.e() && !TextUtils.isEmpty(this.mCameraDevice.getDid())) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("did", this.mCameraDevice.getDid());
                jSONObject.put("region", Locale.getDefault().getCountry());
                XmPluginHostApi.instance().callSmartHomeApi(this.mCameraDevice.getModel(), "business.smartcamera.", "/miot/camera/app/v1/vip/status", "GET", jSONObject.toString(), new Callback<JSONObject>() {
                    public void onSuccess(JSONObject jSONObject) {
                        JSONObject optJSONObject;
                        if (!CameraPlayer2Activity.this.isFinishing() && jSONObject != null && jSONObject.optInt("code", -1) == 0 && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
                            boolean optBoolean = optJSONObject.optBoolean("vip");
                            CameraPlayer2Activity.this.mCameraDevice.e().d(optJSONObject.optBoolean("closeWindow"));
                            CameraPlayer2Activity.this.mCameraDevice.e().c(optBoolean);
                            if (!CameraPlayer2Activity.this.mCameraDevice.e().f() || CameraPlayer2Activity.this.mCameraDevice.isShared()) {
                                CameraPlayer2Activity.this.tvFace.setVisibility(8);
                            } else {
                                CameraPlayer2Activity.this.tvFace.setVisibility(0);
                            }
                            if (CameraPlayer2Activity.this.mCameraDevice.e().f()) {
                                CameraPlayer2Activity.this.tvCloud.setVisibility(0);
                            } else if (CameraPlayer2Activity.this.mCameraDevice.isShared()) {
                                CameraPlayer2Activity.this.tvCloud.setVisibility(8);
                            } else {
                                CameraPlayer2Activity.this.tvCloud.setVisibility(0);
                            }
                            CameraPlayer2Activity.this.tvCloud.setOnClickListener(new View.OnClickListener() {
                                public final void onClick(View view) {
                                    CameraPlayer2Activity.this.onClick(view);
                                }
                            });
                            CameraPlayer2Activity.this.firmwareSupportCloudVideoDlg();
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!CameraPlayer2Activity.this.isFinishing()) {
                        }
                    }
                }, Parser.DEFAULT_PARSER);
            } catch (JSONException unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void firmwareSupportCloudVideoDlg() {
        if (this.mCameraDevice.e().f() && this.mCameraDevice.isOwner() && Util.e()) {
            this.mCameraDevice.c(getContext(), new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    boolean optBoolean = jSONObject.optBoolean("updating", false);
                    boolean optBoolean2 = jSONObject.optBoolean("isLatest", false);
                    if (!optBoolean && !optBoolean2) {
                        jSONObject.optString("curr");
                    }
                }

                public void onFailure(int i, String str) {
                    SDKLog.b(CameraPlayer2Activity.TAG, "getCurrentFirmWareVersion i:" + i + " s:" + str);
                }
            });
        }
    }

    public void openMoreMenu(ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i, Intent intent) {
        DeviceMoreActivity.openMoreMenu(activity(), this.mDid, arrayList, z, i, intent);
    }

    public void openMoreMenu2(ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i, Intent intent, Intent intent2) {
        DeviceMoreNewActivity.openMoreMenu(activity(), this.mDid, arrayList, z, i, intent, intent2);
        Event.a(Event.aJ);
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

    public void showUserLicenseDialog(String str, String str2, int i, String str3, int i2, View.OnClickListener onClickListener, Intent intent) {
        new UserLicenseDialog.Builder(this).a(str).b(str2).a(i).c(str3).b(i2).a(intent).d(this.mDid).a(onClickListener).a().a();
    }
}
