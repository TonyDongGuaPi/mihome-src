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
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.Utils.FileUtil;
import com.Utils.NetworkUtil;
import com.Utils.TimeUtils;
import com.amap.api.services.core.AMapException;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mijia.app.AppCode;
import com.mijia.app.AppConfig;
import com.mijia.app.Event;
import com.mijia.camera.CameraPlayerEx;
import com.mijia.camera.CameraProperties;
import com.mijia.camera.Utils.Util;
import com.mijia.camera.lite.CameraFrameManager;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.mijia.model.alarm.AlarmNetUtils;
import com.mijia.model.local.LocalFileClear;
import com.mijia.model.local.LocalFileManager;
import com.mijia.model.property.CameraPropertyBase;
import com.mijia.model.sdcard.SDCardInfo;
import com.taobao.weex.annotation.JSMethod;
import com.tutk.TuTkClient;
import com.xiaomi.CameraDevice;
import com.xiaomi.connection.ByteOperator;
import com.xiaomi.mistream.MIStreamStatistic;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.camera.IMISSListener;
import com.xiaomi.smarthome.camera.IStreamCmdMessageListener;
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
import com.xiaomi.smarthome.camera.activity.sdcard.SDCardActivity;
import com.xiaomi.smarthome.camera.activity.sdcard.SDCardTimeLinePlayerExActivity;
import com.xiaomi.smarthome.camera.activity.setting.FileManagerSettingActivity;
import com.xiaomi.smarthome.camera.activity.setting.MoreCameraSettingActivity;
import com.xiaomi.smarthome.camera.activity.setting.SDCardStatusActivityNew;
import com.xiaomi.smarthome.camera.activity.setting.VoiceBroadCastActivity;
import com.xiaomi.smarthome.camera.activity.timelapse.DownLoadTimeLapseDemo;
import com.xiaomi.smarthome.camera.v4.activity.setting.NoMemoryCardActivity;
import com.xiaomi.smarthome.camera.view.SDCardHintDialog;
import com.xiaomi.smarthome.camera.view.widget.GuideView;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraV3UpgradePlayerActivity extends CameraPlayerBaseActivity implements IStreamCmdMessageListener {
    private static final String TAG = "CameraV3UpgradePlayerActivity";
    private final int CALL_VOLUME_DELAY = 500;
    private final int FIRST_RESUME = AMapException.CODE_AMAP_NEARBY_INVALID_USERID;
    private final int MSG_CALL_VOLUME = 2101;
    private final int MSG_UPDATE_FIRM = 1;
    final int REQUEST_MORE_ACTIVITY = 1220;
    private final int UPDATE_CHECK = Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS;
    /* access modifiers changed from: private */
    public FrameLayout flCloudVideoTips;
    private FrameLayout flTitleBar;
    private int fps = 20;
    private ViewGroup llBottomTools;
    /* access modifiers changed from: private */
    public boolean mAudioOn = false;
    /* access modifiers changed from: private */
    public boolean mCallingAudioOn = false;
    /* access modifiers changed from: private */
    public int mCurrentTime = 0;
    private View mExitFullScreen;
    private long mFullInTime = 0;
    /* access modifiers changed from: private */
    public GuideView mGuideCenter;
    private PopupWindow mGuideMore;
    private CameraPlayerEx.IRecordTimeListener mIRecodeTimeListener = new CameraPlayerEx.IRecordTimeListener() {
        public void upDateTime(int i) {
            int access$2400 = CameraV3UpgradePlayerActivity.this.mCurrentTime / 1000;
            if (CameraV3UpgradePlayerActivity.this.mLastTime != -1) {
                int access$2500 = i - CameraV3UpgradePlayerActivity.this.mLastTime;
                if (access$2500 <= 0 || access$2500 >= 500) {
                    int unused = CameraV3UpgradePlayerActivity.this.mCurrentTime = CameraV3UpgradePlayerActivity.this.mCurrentTime + 50;
                } else {
                    int unused2 = CameraV3UpgradePlayerActivity.this.mCurrentTime = CameraV3UpgradePlayerActivity.this.mCurrentTime + access$2500;
                }
            } else {
                int unused3 = CameraV3UpgradePlayerActivity.this.mCurrentTime = CameraV3UpgradePlayerActivity.this.mCurrentTime + 50;
            }
            int unused4 = CameraV3UpgradePlayerActivity.this.mLastTime = i;
            if (CameraV3UpgradePlayerActivity.this.mCurrentTime / 1000 != access$2400) {
                CameraV3UpgradePlayerActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        int access$2400 = CameraV3UpgradePlayerActivity.this.mCurrentTime / 1000;
                        if (access$2400 > 1) {
                            CameraV3UpgradePlayerActivity.this.upDateTimeTitle(access$2400 - 1);
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
    public MultiStateView mLandAudioView;
    /* access modifiers changed from: private */
    public TextView mLandRecordTimer;
    /* access modifiers changed from: private */
    public MultiStateView mLandRecordView;
    private TextView mLandResolution;
    /* access modifiers changed from: private */
    public MultiStateView mLandSleepView;
    private ImageView mLandSnapshot;
    /* access modifiers changed from: private */
    public int mLastTime = -1;
    /* access modifiers changed from: private */
    public boolean mNeedLicense = false;
    private View mNewFirmView;
    /* access modifiers changed from: private */
    public MultiStateView mPortAudioView;
    /* access modifiers changed from: private */
    public MultiStateView mPortCallView;
    /* access modifiers changed from: private */
    public MultiStateView mPortRecordView;
    private TextView mPortResolution;
    /* access modifiers changed from: private */
    public MultiStateView mPortSleepView;
    private ImageView mPortSnapshot;
    private LinearLayout mPortraitControlLayout;
    private SDCardHintDialog mSDCardHintDialog;
    /* access modifiers changed from: private */
    public boolean mSnapshot = false;
    private TextView mTimeUpdateView;
    private ImageView mTitleMore;
    /* access modifiers changed from: private */
    public boolean mVideoPlaying = false;
    /* access modifiers changed from: private */
    public TextView record_time;
    private String recordingfilePath;
    private CameraPlayerEx.IResolutionChangedListener resolutionChangedListener = new CameraPlayerEx.IResolutionChangedListener() {
        public void onResolutionChanged(int i, int i2) {
            if (CameraV3UpgradePlayerActivity.this.mCameraPlayerEx == null) {
                return;
            }
            if (CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.f()) {
                CameraV3UpgradePlayerActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        CameraV3UpgradePlayerActivity.this.refreshUI();
                    }
                });
            } else {
                CameraV3UpgradePlayerActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        CameraV3UpgradePlayerActivity.this.refreshUI();
                    }
                });
            }
        }
    };
    boolean sdcardGetSuccess = false;
    int sdcardStatus = 0;
    int selectedIndex;
    private TextView tvAlert;
    private TextView tvCloud;
    private TextView tvFace;
    private ImageView tvFullScreen;
    private TextView tvPlayback;

    private int getResolutionIndex(int i) {
        if (i != 1) {
            return i != 3 ? 0 : 2;
        }
        return 1;
    }

    /* access modifiers changed from: private */
    public int getResolutionIndexNew(int i) {
        switch (i) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return 0;
        }
    }

    private void initMulti() {
    }

    public boolean isHistory() {
        return false;
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
                LocalFileClear.a((CameraDevice) CameraV3UpgradePlayerActivity.this.mCameraDevice);
            }
        }, 2000);
        setContentView(R.layout.activity_camera_player_v3_upgrade_new);
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
            this.mCameraDevice.g().b();
            setResolutionTextNew(getResolutionIndexNew(this.mCameraDevice.e().d()));
        }
        this.mNetworkMonitor.register(activity());
        if (!(this.mCameraDevice == null || this.mCameraDevice.f() == null)) {
            this.mCameraDevice.f().a(CameraProperties.d, (Callback<Void>) new Callback<Void>() {
                public void onFailure(int i, String str) {
                }

                public void onSuccess(Void voidR) {
                    if (!CameraV3UpgradePlayerActivity.this.isFinishing()) {
                        CameraV3UpgradePlayerActivity.this.mPowerCheck = true;
                        CameraV3UpgradePlayerActivity.this.refreshUI();
                        CameraV3UpgradePlayerActivity.this.checkMinLevel();
                        boolean b = CameraV3UpgradePlayerActivity.this.mCameraDevice.f().b(CameraProperties.b);
                        boolean isUsrExpPlanEnabled = XmPluginHostApi.instance().isUsrExpPlanEnabled(CameraV3UpgradePlayerActivity.this.mCameraDevice.getDid());
                        if (b != isUsrExpPlanEnabled) {
                            CameraV3UpgradePlayerActivity.this.mCameraDevice.f().a(CameraProperties.b, isUsrExpPlanEnabled, (Callback<Void>) null);
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
                        CameraV3UpgradePlayerActivity.this.mCameraDevice.g().a(CameraPropertyBase.l);
                        if (CameraV3UpgradePlayerActivity.this.mCameraPlayerEx != null && CameraV3UpgradePlayerActivity.this.mCameraDevice != null && !CameraV3UpgradePlayerActivity.this.mCameraDevice.f().c()) {
                            CameraV3UpgradePlayerActivity.this.hideLoading();
                            CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.j();
                        }
                    }
                }
            };
        }
        if (!(this.mCameraDevice == null || this.mCameraDevice.f() == null)) {
            this.mCameraDevice.f().a((Callback<JSONArray>) new Callback<JSONArray>() {
                public void onFailure(int i, String str) {
                }

                public void onSuccess(JSONArray jSONArray) {
                    SDKLog.e(CameraPropertyBase.e, "jsonArray==" + jSONArray.toString());
                    if (jSONArray.length() > 1) {
                        String optString = jSONArray.optString(0);
                        String optString2 = jSONArray.optString(1);
                        SDKLog.e(CameraPropertyBase.e, "isAuto==" + optString + ",kbps=" + optString2);
                        if ("on".equals(optString)) {
                            SDKLog.e(CameraPropertyBase.e, "获取自动");
                            CameraV3UpgradePlayerActivity.this.mCameraDevice.e().b(1);
                        } else if ("off".equals(optString)) {
                            if (Integer.parseInt(optString2) > 70) {
                                SDKLog.e(CameraPropertyBase.e, "获取高清");
                                CameraV3UpgradePlayerActivity.this.mCameraDevice.e().b(2);
                            } else {
                                SDKLog.e(CameraPropertyBase.e, "获取流畅");
                                CameraV3UpgradePlayerActivity.this.mCameraDevice.e().b(0);
                            }
                        }
                        if (CameraV3UpgradePlayerActivity.this.mCameraDevice != null && CameraV3UpgradePlayerActivity.this.mCameraDevice.e() != null) {
                            CameraV3UpgradePlayerActivity.this.setResolutionTextNew(CameraV3UpgradePlayerActivity.this.getResolutionIndexNew(CameraV3UpgradePlayerActivity.this.mCameraDevice.e().d()));
                        }
                    }
                }
            });
        }
        Event.a(this.mCameraDevice.getDid(), this.mCameraDevice.getModel());
        Event.a(Event.am);
        Event.a();
        SDKLog.a(this.mCameraDevice.getModel());
    }

    /* access modifiers changed from: protected */
    public void initView() {
        initTitlebar();
        initCallView();
        initVideoView();
        initVideoControlView();
        initImagePopupView();
        initBottomNavigationView();
        this.mLandRecordTimer = (TextView) findViewById(R.id.landRecordTimer);
        this.record_time = (TextView) findViewById(R.id.record_time);
    }

    private void initBottomNavigationView() {
        this.llBottomTools = (ViewGroup) findViewById(R.id.port_bottom_functions);
        this.tvAlert = (TextView) findViewById(R.id.tvAlert);
        this.tvAlert.setOnClickListener(this);
        this.tvFace = (TextView) findViewById(R.id.tvFace);
        this.tvCloud = (TextView) findViewById(R.id.tvCloud);
        this.flCloudVideoTips = (FrameLayout) findViewById(R.id.fl_cloud_video_tips);
        if (this.mCameraDevice.isShared()) {
            this.flCloudVideoTips.setVisibility(8);
        }
        if (!XmPluginHostApi.instance().isInternationalServer(this)) {
            this.tvCloud.setVisibility(0);
            this.tvCloud.setOnClickListener(this);
            this.tvFace.setVisibility(0);
            this.tvFace.setOnClickListener(this);
        } else {
            this.tvCloud.setVisibility(8);
            this.tvFace.setVisibility(8);
        }
        this.tvPlayback = (TextView) findViewById(R.id.tvPlayback);
        this.tvPlayback.setOnClickListener(this);
    }

    private void initImagePopupView() {
        this.mLLFuncPopup = (LinearLayout) findViewById(R.id.llFuncPopup);
    }

    private void initVideoControlView() {
        this.mPortraitControlLayout = (LinearLayout) findViewById(R.id.port_control_layout);
        this.mPortraitControlLayout.setVisibility(0);
        this.mLLTopCtrlLandscape = (LinearLayout) findViewById(R.id.llLandTopCtrlLandscape);
        this.mLLTopCtrlLandscape.setVisibility(8);
        this.tvFullScreen = (ImageView) findViewById(R.id.port_fullscreen);
        this.tvFullScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CameraV3UpgradePlayerActivity.this.setOrientation(true);
                Event.a(Event.aw);
            }
        });
        this.mExitFullScreen = findViewById(R.id.land_fullScreen);
        this.mExitFullScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CameraV3UpgradePlayerActivity.this.setOrientation(false);
                Event.a(Event.aw);
            }
        });
        $$Lambda$CameraV3UpgradePlayerActivity$pChVuoi4WyvfSWr2QEIJO4dNch4 r0 = new View.OnClickListener() {
            public final void onClick(View view) {
                CameraV3UpgradePlayerActivity.lambda$initVideoControlView$0(CameraV3UpgradePlayerActivity.this, view);
            }
        };
        this.mPortResolution = (TextView) findViewById(R.id.port_resolution);
        this.mPortResolution.setOnClickListener(r0);
        this.mLandResolution = (TextView) findViewById(R.id.land_resolution);
        this.mLandResolution.setOnClickListener(r0);
        this.mPortSnapshot = (ImageView) findViewById(R.id.port_snapshot_view);
        this.mLandSnapshot = (ImageView) findViewById(R.id.land_snapshot);
        $$Lambda$CameraV3UpgradePlayerActivity$kcjEH9ptRaDk7INYorCg98Nh8Q8 r02 = new View.OnClickListener() {
            public final void onClick(View view) {
                CameraV3UpgradePlayerActivity.lambda$initVideoControlView$1(CameraV3UpgradePlayerActivity.this, view);
            }
        };
        this.mPortSnapshot.setOnClickListener(r02);
        this.mLandSnapshot.setOnClickListener(r02);
        this.mPortAudioView = (MultiStateView) findViewById(R.id.port_audio_view);
        this.mLandAudioView = (MultiStateView) findViewById(R.id.land_audio_view);
        MultiStateView.StateItem stateItem = new MultiStateView.StateItem(R.drawable.camera_control_audio_mute_v1, new View.OnClickListener() {
            public void onClick(View view) {
                if (!CameraV3UpgradePlayerActivity.this.mCameraDevice.f().c()) {
                    ToastUtil.a((Context) CameraV3UpgradePlayerActivity.this, (int) R.string.power_off);
                    return;
                }
                Event.a(Event.ar);
                if (CameraV3UpgradePlayerActivity.this.mCameraPlayerEx != null) {
                    CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.b(false);
                    CameraV3UpgradePlayerActivity.this.mPortAudioView.setCurrentState(1);
                    CameraV3UpgradePlayerActivity.this.mLandAudioView.setCurrentState(1);
                }
            }
        });
        MultiStateView.StateItem stateItem2 = new MultiStateView.StateItem(R.drawable.camera_control_audio_v1, new View.OnClickListener() {
            public void onClick(View view) {
                if (!CameraV3UpgradePlayerActivity.this.mCameraDevice.f().c()) {
                    ToastUtil.a((Context) CameraV3UpgradePlayerActivity.this, (int) R.string.power_off);
                    return;
                }
                Event.a(Event.ar);
                if (CameraV3UpgradePlayerActivity.this.mCameraPlayerEx != null) {
                    CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.b(true);
                    CameraV3UpgradePlayerActivity.this.mPortAudioView.setCurrentState(0);
                    CameraV3UpgradePlayerActivity.this.mLandAudioView.setCurrentState(0);
                }
            }
        });
        this.mPortAudioView.addState(stateItem);
        this.mPortAudioView.addState(stateItem2);
        this.mPortAudioView.setCurrentState(0);
        this.mLandAudioView.addState(stateItem);
        this.mLandAudioView.addState(stateItem2);
        this.mLandAudioView.setCurrentState(0);
        this.mPortRecordView = (MultiStateView) findViewById(R.id.port_record_view);
        this.mLandRecordView = (MultiStateView) findViewById(R.id.land_record);
        MultiStateView.StateItem stateItem3 = new MultiStateView.StateItem(R.drawable.camera_control_record_v1, new View.OnClickListener() {
            public void onClick(View view) {
                if (!CameraV3UpgradePlayerActivity.this.mCameraDevice.f().c()) {
                    ToastUtil.a((Context) CameraV3UpgradePlayerActivity.this, (int) R.string.power_off);
                } else {
                    CameraV3UpgradePlayerActivity.this.doStartRecord();
                }
            }
        });
        MultiStateView.StateItem stateItem4 = new MultiStateView.StateItem(R.drawable.camera_control_recording_v1, new View.OnClickListener() {
            public void onClick(View view) {
                if (!CameraV3UpgradePlayerActivity.this.mCameraDevice.f().c()) {
                    ToastUtil.a((Context) CameraV3UpgradePlayerActivity.this, (int) R.string.power_off);
                } else {
                    CameraV3UpgradePlayerActivity.this.doStopRecord();
                }
            }
        });
        this.mPortRecordView.addState(stateItem3);
        this.mPortRecordView.addState(stateItem4);
        this.mPortRecordView.setCurrentState(0);
        this.mLandRecordView.addState(stateItem3);
        this.mLandRecordView.addState(stateItem4);
        this.mLandRecordView.setCurrentState(0);
        this.mPortSleepView = (MultiStateView) findViewById(R.id.port_sleep_view);
        this.mLandSleepView = (MultiStateView) findViewById(R.id.land_sleep_view);
        MultiStateView.StateItem stateItem5 = new MultiStateView.StateItem(R.drawable.camera_control_sleep_v1, new View.OnClickListener() {
            public void onClick(View view) {
                if (CameraV3UpgradePlayerActivity.this.mCameraDevice.isReadOnlyShared()) {
                    Toast.makeText(CameraV3UpgradePlayerActivity.this, R.string.auth_fail, 0).show();
                } else if (CameraV3UpgradePlayerActivity.this.mCameraDevice != null && CameraV3UpgradePlayerActivity.this.mCameraDevice.f() != null) {
                    CameraV3UpgradePlayerActivity.this.mCameraDevice.f().a(CameraPropertyBase.l, false, (Callback<Void>) new Callback<Void>() {
                        public void onSuccess(Void voidR) {
                            CameraV3UpgradePlayerActivity.this.mPortSleepView.setCurrentState(1);
                            CameraV3UpgradePlayerActivity.this.mLandSleepView.setCurrentState(1);
                            CameraV3UpgradePlayerActivity.this.mCameraDevice.g().a(CameraPropertyBase.l);
                            CameraV3UpgradePlayerActivity.this.mCameraDevice.f().a(CameraPropertyBase.l, (Object) false);
                            com.debug.SDKLog.b(CameraV3UpgradePlayerActivity.TAG, "set sleep success");
                            if (CameraV3UpgradePlayerActivity.this.mCameraDevice.n() && CameraV3UpgradePlayerActivity.this.mCameraPlayerEx != null) {
                                CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.o();
                            }
                            CameraV3UpgradePlayerActivity.this.toggleCtrlContainerVisibility();
                        }

                        public void onFailure(int i, String str) {
                            CameraV3UpgradePlayerActivity.this.mPortSleepView.setCurrentState(0);
                            CameraV3UpgradePlayerActivity.this.mLandSleepView.setCurrentState(0);
                            ToastUtil.a(CameraV3UpgradePlayerActivity.this.getContext(), (int) R.string.action_fail);
                            com.debug.SDKLog.b(CameraV3UpgradePlayerActivity.TAG, "set sleep failed:" + i + " s:" + str);
                            CameraV3UpgradePlayerActivity.this.mCameraDevice.g().a(CameraPropertyBase.l);
                            CameraV3UpgradePlayerActivity.this.mCameraDevice.f().a(CameraPropertyBase.l, (Object) true);
                            CameraV3UpgradePlayerActivity.this.toggleCtrlContainerVisibility();
                        }
                    });
                }
            }
        });
        MultiStateView.StateItem stateItem6 = new MultiStateView.StateItem(R.drawable.camera_control_sleep_stop_v1, new View.OnClickListener() {
            public void onClick(View view) {
                if (CameraV3UpgradePlayerActivity.this.mCameraDevice.isReadOnlyShared()) {
                    Toast.makeText(CameraV3UpgradePlayerActivity.this, R.string.auth_fail, 0).show();
                } else if (!CameraV3UpgradePlayerActivity.this.mCameraDevice.f().b(CameraPropertyBase.l) && CameraV3UpgradePlayerActivity.this.mCameraDevice != null && CameraV3UpgradePlayerActivity.this.mCameraDevice.f() != null) {
                    CameraV3UpgradePlayerActivity.this.mCameraDevice.f().a(CameraPropertyBase.l, true, (Callback<Void>) new Callback<Void>() {
                        public void onSuccess(Void voidR) {
                            CameraV3UpgradePlayerActivity.this.mPortSleepView.setCurrentState(0);
                            CameraV3UpgradePlayerActivity.this.mLandSleepView.setCurrentState(0);
                            CameraV3UpgradePlayerActivity.this.mCameraDevice.g().a(CameraPropertyBase.l);
                            CameraV3UpgradePlayerActivity.this.mCameraDevice.f().a(CameraPropertyBase.l, (Object) true);
                            com.debug.SDKLog.b(CameraV3UpgradePlayerActivity.TAG, "set sleep success");
                            CameraV3UpgradePlayerActivity.this.toggleCtrlContainerVisibility();
                        }

                        public void onFailure(int i, String str) {
                            CameraV3UpgradePlayerActivity.this.mPortSleepView.setCurrentState(1);
                            CameraV3UpgradePlayerActivity.this.mLandSleepView.setCurrentState(1);
                            ToastUtil.a(CameraV3UpgradePlayerActivity.this.getContext(), (int) R.string.action_fail);
                            CameraV3UpgradePlayerActivity.this.mCameraDevice.g().a(CameraPropertyBase.l);
                            CameraV3UpgradePlayerActivity.this.mCameraDevice.f().a(CameraPropertyBase.l, (Object) false);
                            CameraV3UpgradePlayerActivity.this.toggleCtrlContainerVisibility();
                            com.debug.SDKLog.b(CameraV3UpgradePlayerActivity.TAG, "set sleep failed:" + i + " s:" + str);
                        }
                    });
                }
            }
        });
        this.mPortSleepView.addState(stateItem5);
        this.mPortSleepView.addState(stateItem6);
        this.mPortSleepView.setCurrentState(0);
        this.mLandSleepView.addState(stateItem5);
        this.mLandSleepView.addState(stateItem6);
        this.mLandSleepView.setCurrentState(0);
    }

    public static /* synthetic */ void lambda$initVideoControlView$0(CameraV3UpgradePlayerActivity cameraV3UpgradePlayerActivity, View view) {
        Event.a(Event.h);
        if (!cameraV3UpgradePlayerActivity.mCameraDevice.f().c()) {
            ToastUtil.a((Context) cameraV3UpgradePlayerActivity.activity(), (int) R.string.power_off);
        } else {
            cameraV3UpgradePlayerActivity.toggleResolution();
        }
    }

    public static /* synthetic */ void lambda$initVideoControlView$1(CameraV3UpgradePlayerActivity cameraV3UpgradePlayerActivity, View view) {
        Event.a(Event.as);
        cameraV3UpgradePlayerActivity.snapShot();
    }

    private void initVideoView() {
        this.mVideoViewFrame = (FrameLayout) findViewById(R.id.camera_up_layout);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        FrameLayout frameLayout = new FrameLayout(activity());
        this.mVideoViewFrame.addView(frameLayout, 0, layoutParams);
        this.mVideoView = XmPluginHostApi.instance().createVideoView(activity(), frameLayout, true, 2);
        this.mVideoView.setDistort(0.2734375f, 0.04411765f);
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
        ((ImageView) this.mErrorRetryView.findViewById(R.id.retry_btn)).setImageResource(R.drawable.camera_state_sequence_sleep_hualai);
        frameLayout.addView(this.mErrorRetryView, layoutParams2);
        this.mErrorRetryView.setVisibility(8);
        this.mRetryView = this.mErrorRetryView.findViewById(R.id.retry_btn);
        this.mErrorInfoView = (TextView) this.mErrorRetryView.findViewById(R.id.error_info);
        this.mPowerOffView = LayoutInflater.from(this).inflate(R.layout.camera_closed, (ViewGroup) null);
        ((ImageView) this.mPowerOffView.findViewById(R.id.icon)).setImageResource(R.drawable.camera_state_sequence_sleep_hualai);
        frameLayout.addView(this.mPowerOffView, layoutParams2);
        this.mPowerOffView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SDKLog.e(CameraV3UpgradePlayerActivity.TAG, "mPowerOffView");
                if (CameraV3UpgradePlayerActivity.this.mCameraDevice != null && CameraV3UpgradePlayerActivity.this.mCameraDevice.f() != null) {
                    CameraV3UpgradePlayerActivity.this.mCameraDevice.f().a(CameraPropertyBase.l, true, (Callback<Void>) new Callback<Void>() {
                        public void onSuccess(Void voidR) {
                            if (!CameraV3UpgradePlayerActivity.this.isFinishing()) {
                                CameraV3UpgradePlayerActivity.this.mCameraDevice.g().a(CameraPropertyBase.l);
                                CameraV3UpgradePlayerActivity.this.mPortAudioView.setCurrentState(0);
                                CameraV3UpgradePlayerActivity.this.mLandAudioView.setCurrentState(0);
                                if (CameraV3UpgradePlayerActivity.this.mCameraPlayerEx != null) {
                                    CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.b(true);
                                }
                                SDKLog.e(CameraV3UpgradePlayerActivity.TAG, "set wakeup success");
                                CameraV3UpgradePlayerActivity.this.toggleCtrlContainerVisibility();
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (!CameraV3UpgradePlayerActivity.this.isFinishing()) {
                                CameraV3UpgradePlayerActivity.this.mPortAudioView.setCurrentState(0);
                                CameraV3UpgradePlayerActivity.this.mLandAudioView.setCurrentState(0);
                                if (CameraV3UpgradePlayerActivity.this.mCameraPlayerEx != null) {
                                    CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.b(true);
                                }
                                CameraV3UpgradePlayerActivity.this.mCameraDevice.f().a(CameraPropertyBase.l, (Object) false);
                                ToastUtil.a((Context) CameraV3UpgradePlayerActivity.this.activity(), (int) R.string.action_fail);
                                SDKLog.e(CameraV3UpgradePlayerActivity.TAG, "set wakeup failed:" + i + " s:" + str);
                                CameraV3UpgradePlayerActivity.this.toggleCtrlContainerVisibility();
                            }
                        }
                    });
                }
            }
        });
        this.mPowerOffView.setVisibility(8);
        this.mVideoView.setVideoViewListener(new XmVideoViewGl.IVideoViewListener() {
            public void onVideoViewClick() {
                if (CameraV3UpgradePlayerActivity.this.mCameraDevice.f().c()) {
                    CameraV3UpgradePlayerActivity.this.videoClick();
                }
            }
        });
        this.mFrameRate = (TextView) findViewById(R.id.sub_title_bar_title);
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
        this.mRetryView.setOnClickListener(this);
        this.mPauseView = findViewById(R.id.pause_view);
        this.mPauseView.setOnClickListener(this);
        this.mVideoView.setVideoViewScaleListener($$Lambda$CameraV3UpgradePlayerActivity$zsq9AJvODGqNLoe9l5J_BugXx2E.INSTANCE);
    }

    static /* synthetic */ void lambda$initVideoView$2(int i) {
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

    private void initCallView() {
        this.mPortCallView = (MultiStateView) findViewById(R.id.port_call_view);
        this.mLandCallView = (MultiStateView) findViewById(R.id.land_call_view);
        AnonymousClass16 r0 = new View.OnClickListener() {
            public void onClick(View view) {
                if (!CameraV3UpgradePlayerActivity.this.mCameraDevice.f().c()) {
                    ToastUtil.a((Context) CameraV3UpgradePlayerActivity.this, (int) R.string.power_off);
                    return;
                }
                Event.a(Event.aB);
                if (CameraV3UpgradePlayerActivity.this.getRequestedOrientation() != 1) {
                    CameraV3UpgradePlayerActivity.this.mLandCallView.startAnimation(AnimationUtils.loadAnimation(CameraV3UpgradePlayerActivity.this.activity(), R.anim.anim_scale_in_out));
                } else {
                    CameraV3UpgradePlayerActivity.this.mPortCallView.startAnimation(AnimationUtils.loadAnimation(CameraV3UpgradePlayerActivity.this.activity(), R.anim.anim_scale_in_out));
                }
                if (CameraV3UpgradePlayerActivity.this.mCameraPlayerEx == null || !CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.g()) {
                    ToastUtil.a((Context) CameraV3UpgradePlayerActivity.this.activity(), (int) R.string.call_no_play);
                } else if (!CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.f()) {
                    ToastUtil.a((Context) CameraV3UpgradePlayerActivity.this.activity(), (int) R.string.not_on_live);
                } else {
                    boolean unused = CameraV3UpgradePlayerActivity.this.mCallingAudioOn = CameraV3UpgradePlayerActivity.this.mPortAudioView.getCurrentState() == 1;
                    if (XmPluginHostApi.instance().checkAndRequestPermisson(CameraV3UpgradePlayerActivity.this.activity(), true, new Callback<List<String>>() {
                        public void onSuccess(List<String> list) {
                        }

                        public void onFailure(int i, String str) {
                            ToastUtil.a((Context) CameraV3UpgradePlayerActivity.this.activity(), (int) R.string.audio_permission_denied);
                        }
                    }, "android.permission.RECORD_AUDIO")) {
                        if (!CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.t()) {
                            CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.p();
                            CameraV3UpgradePlayerActivity.this.mHandler.sendEmptyMessageDelayed(2101, 500);
                            CameraV3UpgradePlayerActivity.this.mLandCallView.setCurrentState(1);
                            CameraV3UpgradePlayerActivity.this.mPortCallView.setCurrentState(1);
                            CameraV3UpgradePlayerActivity.this.mPortAudioView.setCurrentState(1);
                            CameraV3UpgradePlayerActivity.this.mLandAudioView.setCurrentState(1);
                            Event.g();
                            return;
                        }
                        SDKLog.e(CameraV3UpgradePlayerActivity.TAG, "正在通话中");
                    }
                }
            }
        };
        MultiStateView.StateItem stateItem = new MultiStateView.StateItem(R.drawable.icon_call_new, r0);
        MultiStateView.StateItem stateItem2 = new MultiStateView.StateItem(R.drawable.selector_icon_call, r0);
        AnonymousClass17 r02 = new View.OnClickListener() {
            public void onClick(View view) {
                if (!CameraV3UpgradePlayerActivity.this.mCameraDevice.f().c()) {
                    ToastUtil.a((Context) CameraV3UpgradePlayerActivity.this, (int) R.string.power_off);
                    return;
                }
                if (CameraV3UpgradePlayerActivity.this.getRequestedOrientation() != 1) {
                    CameraV3UpgradePlayerActivity.this.mLandCallView.startAnimation(AnimationUtils.loadAnimation(CameraV3UpgradePlayerActivity.this.activity(), R.anim.anim_scale_in_out));
                } else {
                    CameraV3UpgradePlayerActivity.this.mPortCallView.startAnimation(AnimationUtils.loadAnimation(CameraV3UpgradePlayerActivity.this.activity(), R.anim.anim_scale_in_out));
                }
                if (CameraV3UpgradePlayerActivity.this.mCameraPlayerEx != null) {
                    CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.q();
                    CameraV3UpgradePlayerActivity.this.mHandler.removeMessages(2101);
                    CameraV3UpgradePlayerActivity.this.mLandCallView.setCurrentState(0);
                    CameraV3UpgradePlayerActivity.this.mPortCallView.setCurrentState(0);
                    Event.h();
                }
                if (CameraV3UpgradePlayerActivity.this.mAudioOn) {
                    CameraV3UpgradePlayerActivity.this.mPortAudioView.setCurrentState(1);
                    CameraV3UpgradePlayerActivity.this.mLandAudioView.setCurrentState(1);
                    if (CameraV3UpgradePlayerActivity.this.mCameraPlayerEx != null) {
                        CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.b(false);
                        return;
                    }
                    return;
                }
                CameraV3UpgradePlayerActivity.this.mPortAudioView.setCurrentState(0);
                CameraV3UpgradePlayerActivity.this.mLandAudioView.setCurrentState(0);
                if (CameraV3UpgradePlayerActivity.this.mCameraPlayerEx != null) {
                    CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.b(true);
                }
            }
        };
        MultiStateView.StateItem stateItem3 = new MultiStateView.StateItem(R.drawable.icon_calling_new, r02);
        MultiStateView.StateItem stateItem4 = new MultiStateView.StateItem(R.drawable.selector_icon_calling, r02);
        this.mLandCallView.addState(stateItem2);
        this.mPortCallView.addState(stateItem);
        this.mLandCallView.addState(stateItem4);
        this.mPortCallView.addState(stateItem3);
        this.mPortCallView.setCurrentState(0);
        this.mLandCallView.setCurrentState(0);
    }

    private void initTitlebar() {
        this.mNewFirmView = findViewById(R.id.title_bar_redpoint);
        this.mTitleView = (TextView) findViewById(R.id.title_bar_title);
        this.mTimeUpdateView = (TextView) findViewById(R.id.time_container_center);
        this.mTitleMore = (ImageView) findViewById(R.id.title_bar_more);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        ((ImageView) findViewById(R.id.title_bar_return)).setImageResource(R.drawable.camera_icon_back_white);
        this.mTitleMore.setVisibility(0);
        this.mTitleMore.setImageResource(R.drawable.camera_icon_right_more_white);
        this.mTitleMore.setOnClickListener(this);
        findViewById(R.id.title_bar_share).setVisibility(8);
    }

    private void showCloudVideoTips() {
        if (!this.mCameraDevice.isShared() && this.mCameraDevice.e().o()) {
            CloudVideoNetUtils.getInstance().getCloudPromoteTips(this.mDid, new ICloudVideoCallback<String>() {
                public void onCloudVideoSuccess(String str, Object obj) {
                    CameraV3UpgradePlayerActivity.this.flCloudVideoTips.setVisibility(0);
                    CameraV3UpgradePlayerActivity.this.flCloudVideoTips.setOnClickListener(new View.OnClickListener() {
                        public final void onClick(View view) {
                            CameraV3UpgradePlayerActivity.this.onClick(view);
                        }
                    });
                    ((TextView) CameraV3UpgradePlayerActivity.this.findViewById(R.id.tv_cloud_video_tips)).setText(str);
                    if (CameraV3UpgradePlayerActivity.this.mCameraDevice.e().n() == 0) {
                        CameraV3UpgradePlayerActivity.this.mCameraDevice.e().b(System.currentTimeMillis());
                    }
                }

                public void onCloudVideoFailed(int i, String str) {
                    LogUtil.a(CameraV3UpgradePlayerActivity.TAG, str);
                }
            });
        }
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
                ToastUtil.a((Context) activity(), i);
            } else {
                ToastUtil.a((Context) activity(), (int) R.string.speaking_block);
            }
            return false;
        } else if (!this.mCameraPlayerEx.r()) {
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
        if (!this.mUserPause) {
            showLoading("");
        }
        if (this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.a((IMISSListener) new IMISSListener() {
                public void onProgress(int i) {
                }

                public void onSuccess(String str, Object obj) {
                    if (CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.u()) {
                        CameraV3UpgradePlayerActivity.this.toggleRemoteAV(true, false);
                    } else {
                        CameraV3UpgradePlayerActivity.this.toggleRemoteAV(true, true);
                    }
                    boolean unused = CameraV3UpgradePlayerActivity.this.mVideoPlaying = true;
                }

                public void onFailed(int i, String str) {
                    boolean unused = CameraV3UpgradePlayerActivity.this.mVideoPlaying = false;
                }
            });
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        SDKLog.e(TAG, "newConfig.orientation:" + configuration.orientation);
        setWindow(configuration);
        if (configuration.orientation != 1) {
            this.flTitleBar.setVisibility(8);
            this.mPortraitControlLayout.setVisibility(8);
            this.llBottomTools.setVisibility(8);
            this.mLLTopCtrlLandscape.setVisibility(0);
            this.mFullScreen = true;
            this.mVideoView.setIsFull(true);
            if (this.mCameraPlayerEx != null && this.mCameraPlayerEx.r()) {
                this.mLandRecordTimer.setVisibility(0);
            }
            this.mFullInTime = System.currentTimeMillis();
            this.mLandCallView.setVisibility(0);
            this.mPortCallView.setVisibility(8);
            changeVideoFrame(false);
            return;
        }
        this.flTitleBar.setVisibility(0);
        this.mPortraitControlLayout.setVisibility(0);
        this.llBottomTools.setVisibility(0);
        this.mLLTopCtrlLandscape.setVisibility(8);
        this.mFullScreen = false;
        this.mVideoView.setIsFull(false);
        if (this.mCameraPlayerEx != null && this.mCameraPlayerEx.r()) {
            this.mLandRecordTimer.setVisibility(8);
        }
        if (this.mFullInTime > 0) {
            this.mFullInTime = 0;
        }
        this.mPortCallView.setVisibility(0);
        this.mLandCallView.setVisibility(8);
        changeVideoFrame(true);
    }

    private void changeVideoFrame(boolean z) {
        if (z) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.mVideoViewFrame.getLayoutParams();
            layoutParams.matchConstraintPercentHeight = 0.65f;
            layoutParams.bottomToBottom = -1;
            layoutParams.leftToLeft = 0;
            layoutParams.rightToRight = 0;
            layoutParams.topToTop = 0;
            this.mVideoViewFrame.setLayoutParams(layoutParams);
            return;
        }
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.mVideoViewFrame.getLayoutParams();
        layoutParams2.bottomToBottom = 0;
        layoutParams2.leftToLeft = 0;
        layoutParams2.rightToRight = 0;
        layoutParams2.topToTop = 0;
        layoutParams2.matchConstraintPercentHeight = 1.0f;
        this.mVideoViewFrame.setLayoutParams(layoutParams2);
    }

    public void onClick(View view) {
        if (!doOnClick(view)) {
            switch (view.getId()) {
                case R.id.fl_cloud_video_tips:
                    CloudVideoNetUtils.getInstance().openCloudVideoBuyPage(this, this.mCameraDevice.getDid());
                    this.mCameraDevice.e().j(true);
                    this.flCloudVideoTips.setVisibility(8);
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
                        intentMenuItem3.name = getString(R.string.setting_voice_broadcast);
                        intentMenuItem3.intent = new Intent(this, VoiceBroadCastActivity.class);
                        intentMenuItem3.intent.putExtra("extra_device_did", this.mDid);
                        arrayList.add(intentMenuItem3);
                        IXmPluginHostActivity.IntentMenuItem intentMenuItem4 = new IXmPluginHostActivity.IntentMenuItem();
                        intentMenuItem4.name = getString(R.string.more_store_setting);
                        intentMenuItem4.intent = new Intent(this, FileManagerSettingActivity.class);
                        intentMenuItem4.intent.putExtra("extra_device_did", this.mDid);
                        arrayList.add(intentMenuItem4);
                        if (this.mCameraDevice.e().f() && !this.mCameraDevice.isReadOnlyShared()) {
                            IXmPluginHostActivity.IntentMenuItem intentMenuItem5 = new IXmPluginHostActivity.IntentMenuItem();
                            intentMenuItem5.name = getString(R.string.face_ai_setting);
                            intentMenuItem5.intent = new Intent(this, AlarmAISwitchActivity.class);
                            intentMenuItem5.intent.putExtra("extra_device_did", this.mDid);
                            arrayList.add(intentMenuItem5);
                        }
                        IXmPluginHostActivity.IntentMenuItem intentMenuItem6 = new IXmPluginHostActivity.IntentMenuItem();
                        intentMenuItem6.name = getString(R.string.album);
                        intentMenuItem6.intent = new Intent(this, AlbumActivity.class);
                        intentMenuItem6.intent.putExtra("extra_device_did", this.mDid);
                        arrayList.add(intentMenuItem6);
                        Intent intent = new Intent();
                        intent.putExtra(DeviceMoreActivity.ARGS_SECURITY_SETTING_ENABLE, true);
                        intent.putExtra(DeviceMoreNewActivity.AUTO_DISMISS, false);
                        if (!this.mCameraDevice.isShared()) {
                            intent.putExtra("cloud_storage", true);
                            intent.putExtra("title", this.mCameraDevice.getName());
                        }
                        if (XmPluginHostApi.instance().getApiLevel() > 52) {
                            LocalLicenseUtil.LocalLicense v3UpgradeLicense = LocalLicenseUtil.getV3UpgradeLicense(getResources());
                            int i = v3UpgradeLicense.mLicense;
                            int i2 = v3UpgradeLicense.mPrivacy;
                            Intent intent2 = new Intent();
                            if (!(i == 0 || i2 == 0)) {
                                intent2.putExtra(DeviceMoreActivity.ARGS_ENABLE_REMOVE_LICENSE, true);
                                intent2.putExtra(DeviceMoreActivity.ARGS_LICENSE_HTML_RES, i);
                                intent2.putExtra(DeviceMoreActivity.ARGS_PRIVACY_HTML_RES, i2);
                                intent2.putExtra("enable_privacy_setting", !CoreApi.a().D());
                                intent2.putExtra(DeviceMoreActivity.ARGS_USR_EXP_PLAN_URI, v3UpgradeLicense.mPlan);
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
                                if (bitmap != null && (a2 = FileUtil.a(false, CameraV3UpgradePlayerActivity.this.mCameraDevice.getDid())) != null) {
                                    try {
                                        FileOutputStream fileOutputStream = new FileOutputStream(a2);
                                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                                        fileOutputStream.close();
                                        bitmap.recycle();
                                        CameraV3UpgradePlayerActivity.this.openShareMediaActivity(CameraV3UpgradePlayerActivity.this.mDeviceStat.name, "", a2);
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
                    SDKLog.e(TAG, "tvAlarm click");
                    Event.a(Event.d);
                    Event.a(Event.az);
                    if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        if (!this.mCameraDevice.e().j() || this.mCameraDevice.isReadOnlyShared()) {
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
                    Event.a(Event.aC);
                    if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        if (!this.mCameraDevice.f().c()) {
                            ToastUtil.a((Context) this, (int) R.string.power_off);
                            return;
                        }
                        if (AlarmNetUtils.c()) {
                            if (!this.mCameraDevice.isShared() || this.mCameraDevice.e().f()) {
                                if (this.mCameraDevice.e().f() || this.mCameraDevice.e().e()) {
                                    FrameManager.b().k().openCloudVideoListActivity(this, this.mCameraDevice.getDid(), this.mCameraDevice.getName());
                                } else if (activity() != null) {
                                    CloudVideoNetUtils.getInstance().openCloudVideoBuyPage(this, this.mCameraDevice.getDid());
                                }
                                this.mCameraDevice.e().j(true);
                            } else {
                                ToastUtil.a((Context) this, (int) R.string.cloud_share_hint);
                                return;
                            }
                        }
                        this.flCloudVideoTips.setVisibility(8);
                        return;
                    }
                    return;
                case R.id.tvFace:
                    Event.a(Event.aD);
                    if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        if (!this.mCameraDevice.f().c()) {
                            ToastUtil.a((Context) this, (int) R.string.power_off);
                            return;
                        }
                        Event.a(Event.aD);
                        if (this.mCameraDevice.isShared()) {
                            ToastUtil.a((Context) this, (CharSequence) getString(R.string.no_permit_for_face_tips));
                            return;
                        } else if (!this.mCameraDevice.e().f()) {
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put("is_vip_user", false);
                                jSONObject.put("is_from_camera", true);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            FrameManager.b().k().openFaceManagerActivity(BaseQuickAdapter.u, this, this.mCameraDevice.getDid(), jSONObject.toString());
                            return;
                        } else {
                            FrameManager.b().k().openFaceManagerActivity(this, this.mCameraDevice.getDid());
                            return;
                        }
                    } else {
                        return;
                    }
                case R.id.tvPlayback:
                    Event.o();
                    Event.a(Event.aA);
                    if (!this.mCameraDevice.f().c()) {
                        ToastUtil.a((Context) this, (int) R.string.power_off);
                        return;
                    } else if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        if (this.mCameraPlayer != null && !this.mCameraPlayer.d()) {
                            ToastUtil.a((Context) activity(), (int) R.string.no_playback_for_connect);
                            return;
                        } else if (this.mCameraPlayerEx != null && !this.mCameraPlayerEx.g()) {
                            ToastUtil.a((Context) activity(), (int) R.string.no_playback_for_connect);
                            return;
                        } else if (!this.sdcardGetSuccess) {
                            ToastUtil.a((Context) activity(), (int) R.string.sd_card_hint_title);
                            return;
                        } else if (this.sdcardStatus == 4) {
                            ToastUtil.a((Context) this, (CharSequence) getString(R.string.camera_storage_sdcard_formating_tips));
                            return;
                        } else if (this.sdcardStatus == 3) {
                            startActivity(new Intent(this, SDCardStatusActivityNew.class));
                            return;
                        } else if (this.sdcardStatus == 1 || this.sdcardStatus == 5) {
                            startActivity(new Intent(this, NoMemoryCardActivity.class));
                            return;
                        } else {
                            startActivity(new Intent(this, isFirmWareSupportTimelineVersion(this.mCameraDevice.p(), "4.0.4_0068") ? SDCardTimeLinePlayerExActivity.class : SDCardActivity.class));
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

    private boolean isFirmWareSupportTimelineVersion(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        String substring = (str.lastIndexOf(JSMethod.NOT_SET) == -1 || str.lastIndexOf(JSMethod.NOT_SET) >= str.length() - 1) ? str : str.substring(str.lastIndexOf(JSMethod.NOT_SET) + 1);
        if (str2.lastIndexOf(".") != -1 && str2.lastIndexOf(JSMethod.NOT_SET) < str2.length() - 1) {
            str2 = str2.substring(str.lastIndexOf(JSMethod.NOT_SET) + 1);
        }
        try {
            if (Integer.parseInt(substring) - Integer.parseInt(str2) > 0) {
                return true;
            }
            return false;
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public void doStartRecord() {
        Event.a(Event.e);
        Event.a(Event.at);
        if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.r()) {
            this.mLastTime = -1;
            this.mCurrentTime = 0;
            if (!this.mCameraDevice.f().c()) {
                ToastUtil.a((Context) activity(), (int) R.string.power_off);
                this.mLandRecordView.setCurrentState(0);
                this.mPortRecordView.setCurrentState(0);
                return;
            }
            this.recordingfilePath = FileUtil.a(true, this.mCameraDevice.getDid());
            if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.l()) {
                ToastUtil.a((Context) activity(), (int) R.string.record_not_connect);
                this.mLandRecordView.setCurrentState(0);
                this.mPortRecordView.setCurrentState(0);
            } else if (!com.Utils.Util.b(activity(), "android.permission.WRITE_EXTERNAL_STORAGE")) {
                ToastUtil.a((Context) activity(), (int) R.string.no_write_permission);
                this.mLandRecordView.setCurrentState(0);
                this.mPortRecordView.setCurrentState(0);
            } else if (!TextUtils.isEmpty(this.recordingfilePath)) {
                this.mCameraPlayerEx.a((IStreamCmdMessageListener) this);
                SDKLog.e(TAG, "获取当前的帧率");
                if (this.mFullScreen) {
                    this.mLandRecordTimer.setText(getString(R.string.main_recording, new Object[]{"00:00:00"}));
                    this.mLandRecordTimer.setVisibility(0);
                } else if (this.record_time.getVisibility() != 0) {
                    this.record_time.setVisibility(0);
                    this.record_time.setText(getString(R.string.main_recording, new Object[]{"00:00:00"}));
                }
                SDKLog.e(TAG, "startRecord");
                this.mLandRecordView.setCurrentState(1);
                this.mPortRecordView.setCurrentState(1);
                if (this.mCameraPlayerEx != null) {
                    this.mCameraPlayerEx.b(true, (IMISSListener) null);
                }
            } else {
                ToastUtil.a((Context) activity(), (int) R.string.snip_video_failed);
                this.mLandRecordView.setCurrentState(0);
                this.mPortRecordView.setCurrentState(0);
            }
        } else {
            this.mLandRecordView.setCurrentState(1);
            this.mPortRecordView.setCurrentState(1);
        }
    }

    /* access modifiers changed from: protected */
    public void doStopRecord() {
        int i = this.mCurrentTime;
        this.mLastTime = -1;
        this.mCurrentTime = 0;
        if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.r()) {
            if (this.mLandRecordView != null) {
                this.mLandRecordView.setCurrentState(0);
            }
            if (this.mPortRecordView != null) {
                this.mPortRecordView.setCurrentState(0);
                return;
            }
            return;
        }
        if (this.mCameraPlayerEx.u()) {
            this.mCameraPlayerEx.b(false, (IMISSListener) null);
        }
        this.mCameraPlayerEx.a((XmMp4Record.IRecordListener) new XmMp4Record.IRecordListener() {
            public void onSuccess(final String str) {
                CameraV3UpgradePlayerActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        CameraV3UpgradePlayerActivity.this.mLandRecordTimer.setVisibility(8);
                        CameraV3UpgradePlayerActivity.this.onVideoRecorded(str);
                        if (CameraV3UpgradePlayerActivity.this.mLandRecordView != null) {
                            CameraV3UpgradePlayerActivity.this.mLandRecordView.setCurrentState(0);
                        }
                        if (CameraV3UpgradePlayerActivity.this.mPortRecordView != null) {
                            CameraV3UpgradePlayerActivity.this.mPortRecordView.setCurrentState(0);
                        }
                        CameraV3UpgradePlayerActivity.this.record_time.setVisibility(8);
                    }
                });
            }

            public void onFailed(final int i, String str) {
                CameraV3UpgradePlayerActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        CameraV3UpgradePlayerActivity.this.mLandRecordTimer.setVisibility(8);
                        if (CameraV3UpgradePlayerActivity.this.mLandRecordView != null) {
                            CameraV3UpgradePlayerActivity.this.mLandRecordView.setCurrentState(0);
                        }
                        if (CameraV3UpgradePlayerActivity.this.mPortRecordView != null) {
                            CameraV3UpgradePlayerActivity.this.mPortRecordView.setCurrentState(0);
                        }
                        if (i == -2) {
                            ToastUtil.a((Context) CameraV3UpgradePlayerActivity.this.activity(), (int) R.string.snip_video_failed_time_mini);
                        } else {
                            ToastUtil.a((Context) CameraV3UpgradePlayerActivity.this.activity(), (int) R.string.snip_video_failed);
                        }
                        CameraV3UpgradePlayerActivity.this.record_time.setVisibility(8);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public void doStopCall() {
        if (this.mCameraPlayerEx != null && this.mCameraPlayerEx.t()) {
            this.mCameraPlayerEx.q();
            this.mHandler.removeMessages(2101);
        }
        if (this.mLandCallView != null && this.mLandCallView.getCurrentState() == 1) {
            this.mLandCallView.setCurrentState(0);
        }
        if (this.mCallingAudioOn) {
            this.mPortAudioView.setCurrentState(1);
            this.mLandAudioView.setCurrentState(1);
            if (this.mCameraPlayerEx != null) {
                this.mCameraPlayerEx.b(false);
            }
        } else if (!CameraFrameManager.a().a(this.mCameraDevice.getDid())) {
            this.mPortAudioView.setCurrentState(0);
            this.mLandAudioView.setCurrentState(0);
            if (this.mCameraPlayerEx != null) {
                this.mCameraPlayerEx.b(true);
            }
        }
        this.mAudioOn = this.mCallingAudioOn;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1220) {
            if (this.mDeviceStat != null) {
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
                    }
                } else {
                    this.mCameraDevice.e().g(true);
                    finish();
                }
                if (intent.getBooleanExtra("open_float_window", false)) {
                    finish();
                    return;
                }
                return;
            }
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: private */
    public void setResolutionTextNew(int i) {
        if (this.mLandResolution != null) {
            switch (i) {
                case 0:
                    this.mLandResolution.setText(R.string.quality_smooth);
                    this.mPortResolution.setText(R.string.quality_smooth);
                    return;
                case 1:
                    this.mPortResolution.setText(R.string.quality_auto);
                    this.mLandResolution.setText(R.string.quality_auto);
                    return;
                case 2:
                    this.mLandResolution.setText(R.string.quality_fhd);
                    this.mPortResolution.setText(R.string.quality_fhd);
                    return;
                default:
                    this.mPortResolution.setText(R.string.quality_smooth);
                    this.mLandResolution.setText(R.string.quality_smooth);
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setOrientation(boolean z) {
        if (!z) {
            setRequestedOrientation(1);
            if (this.mCameraPlayerEx != null && this.mCameraPlayerEx.r()) {
                this.record_time.setVisibility(0);
            }
        } else if (!this.mCameraDevice.f().c()) {
            ToastUtil.a((Context) activity(), (int) R.string.power_off);
        } else {
            setRequestedOrientation(0);
            this.record_time.setVisibility(8);
        }
    }

    public void onResume() {
        super.onResume();
        TimeUtils.b();
        MIStreamStatistic.getInstance().latestLiveVideo = System.currentTimeMillis();
        if (XmPluginHostApi.instance().getApiLevel() >= 63) {
            SDKLog.e(TAG, "onResume  ....isTimeLapsePhotography=" + this.isTimeLapsePhotography);
            if (this.isTimeLapsePhotography) {
                doResume();
            }
            this.isTimeLapsePhotography = false;
            isCloudVideoUser();
            this.mCameraDevice.updateDeviceStatus();
            if (this.mCameraDevice.isOwner()) {
                this.mCameraDevice.checkDeviceUpdateInfo(new Callback<DeviceUpdateInfo>() {
                    public void onFailure(int i, String str) {
                    }

                    public void onSuccess(DeviceUpdateInfo deviceUpdateInfo) {
                        Message.obtain(CameraV3UpgradePlayerActivity.this.mHandler, 1, deviceUpdateInfo).sendToTarget();
                    }
                });
            }
            ((TextView) findViewById(R.id.title_bar_title)).setText(this.mCameraDevice.getName());
            if (!this.mNeedLicense || this.mCameraDevice.isShared()) {
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
            if (this.mAudioOn) {
                this.mPortAudioView.setCurrentState(1);
                this.mLandAudioView.setCurrentState(1);
                if (this.mCameraPlayerEx != null) {
                    this.mCameraPlayerEx.b(false);
                }
            } else {
                this.mPortAudioView.setCurrentState(0);
                this.mLandAudioView.setCurrentState(0);
                if (this.mCameraPlayerEx != null) {
                    this.mCameraPlayerEx.b(true);
                }
            }
            detectSDCard();
        }
    }

    public void onStop() {
        super.onStop();
        this.mHandler.removeMessages(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
        doStopCall();
        doStopRecord();
    }

    public void onPause() {
        SDKLog.e(TAG, "onpause  ....isTimeLapsePhotography=" + this.isTimeLapsePhotography);
        if (!this.isTimeLapsePhotography && this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.j();
        }
        if (this.mPortAudioView.getCurrentState() == 1) {
            this.mAudioOn = true;
        } else {
            this.mAudioOn = false;
        }
        dismissSnapshotPopupRunnable(0);
        eventLiveVideoDuration();
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void resumeCamera() {
        if (this.mPauseView != null) {
            this.mPauseView.setVisibility(8);
        }
        if (NetworkUtil.c(activity())) {
            this.mAllowMobileNetwork = true;
        }
        if (!this.mVideoPlaying) {
            hidError();
            showLoading("");
            if (!(this.mCameraPlayerEx == null || this.mCameraDevice == null || !this.mCameraDevice.f().c())) {
                this.mCameraPlayerEx.a((IMISSListener) new IMISSListener() {
                    public void onProgress(int i) {
                    }

                    public void onSuccess(String str, Object obj) {
                        if (CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.u()) {
                            CameraV3UpgradePlayerActivity.this.toggleRemoteAV(true, false);
                        } else {
                            CameraV3UpgradePlayerActivity.this.toggleRemoteAV(true, true);
                        }
                        boolean unused = CameraV3UpgradePlayerActivity.this.mVideoPlaying = true;
                    }

                    public void onFailed(int i, String str) {
                        boolean unused = CameraV3UpgradePlayerActivity.this.mVideoPlaying = false;
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
        switch (message.what) {
            case 1:
                if (((DeviceUpdateInfo) message.obj).mHasNewFirmware) {
                    this.mNewFirmView.setVisibility(0);
                    return;
                }
                return;
            case AMapException.CODE_AMAP_NEARBY_INVALID_USERID:
                hidError();
                if ((!NetworkUtil.c(activity()) || this.mAllowMobileNetwork) && this.mCameraDevice.f().c()) {
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
            case Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS:
                this.mCameraDevice.b(getContext(), (Callback<Integer[]>) new Callback<Integer[]>() {
                    public void onSuccess(Integer[] numArr) {
                        if (numArr != null) {
                            if (numArr[0].intValue() == 0) {
                                CameraV3UpgradePlayerActivity.this.hideUpdateIng(true);
                                CameraV3UpgradePlayerActivity.this.mHandler.removeMessages(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
                            } else if (numArr[1].intValue() < 100) {
                                CameraV3UpgradePlayerActivity.this.showUpdateIng(numArr[1].intValue());
                                CameraV3UpgradePlayerActivity.this.mHandler.sendEmptyMessageDelayed(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS, com.xiaomi.smarthome.download.Constants.x);
                            } else {
                                CameraV3UpgradePlayerActivity.this.hideUpdateIng(true);
                                CameraV3UpgradePlayerActivity.this.mHandler.removeMessages(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
                                CameraV3UpgradePlayerActivity.this.startPlay();
                            }
                        }
                    }

                    public void onFailure(int i, String str) {
                        CameraV3UpgradePlayerActivity.this.hideUpdateIng(false);
                        CameraV3UpgradePlayerActivity.this.mHandler.removeMessages(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
                    }
                });
                return;
            case 4000:
                this.mNeedSpeed = false;
                this.mTimeUpdateView.setVisibility(8);
                return;
            case CommandTreatment.RES_VIDEO_PARAM /*21003*/:
                byte[] bArr = (byte[]) message.obj;
                if (bArr != null && bArr.length >= 6) {
                    int a2 = ByteOperator.a(bArr, 0);
                    LogUtil.a(TAG, "获取的码流=" + a2);
                    if (a2 >= 90) {
                        this.mCameraDevice.e().b(2);
                    } else {
                        this.mCameraDevice.e().b(0);
                    }
                    setResolutionTextNew(getResolutionIndexNew(this.mCameraDevice.e().d()));
                    return;
                }
                return;
            case CommandTreatment.GET_CURRENT_FRAME_RATE /*210012*/:
                SDKLog.e(TAG, "获取设备实时帧率");
                byte[] bArr2 = (byte[]) message.obj;
                if (bArr2 != null) {
                    this.fps = bArr2[0];
                    SDKLog.e(TAG, "data fps===" + this.fps + ",recordingfilePath=" + this.recordingfilePath);
                    this.mCameraPlayerEx.a(this.recordingfilePath, 2, this.fps);
                    return;
                }
                return;
            case CommandTreatment.OPEN_SPEAK /*210013*/:
                SDKLog.e(TAG, "打开speek通道的结果");
                byte[] bArr3 = (byte[]) message.obj;
                SDKLog.e(TAG, "data 0===" + bArr3[0]);
                SDKLog.e(TAG, "data 1===" + bArr3[1]);
                return;
            default:
                return;
        }
    }

    public void onBackPressed() {
        if (getRequestedOrientation() != 1) {
            setOrientation(false);
        } else if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void startPlay() {
        if (TextUtils.isEmpty(this.mCameraDevice.getModel()) || TextUtils.isEmpty(this.mCameraDevice.getDid())) {
            showError(getString(R.string.camera_play_error2));
        } else if (!this.mCameraDevice.f().c()) {
            hideLoading();
            if (this.mPowerOffView.getVisibility() == 8) {
                Log.d("cheng", "powerOff:" + Log.getStackTraceString(new Exception()));
                this.mPowerOffView.setVisibility(0);
            }
        } else {
            if (this.mPauseView.getVisibility() == 0) {
                this.mPauseView.setVisibility(8);
            }
            if (this.mCameraPlayerEx == null) {
                this.mCameraPlayerEx = new CameraPlayerEx(this, this.mCameraDevice, this, this.mVideoView);
                this.mCameraPlayerEx.a(this.mIRecodeTimeListener);
            }
            if (this.mCameraPlayerEx != null) {
                this.mCameraPlayerEx.c = this.resolutionChangedListener;
            }
            if (!NetworkUtil.c(this) || this.mAllowMobileNetwork) {
                if (this.mCameraDevice != null && this.mCameraDevice.f().c()) {
                    hidError();
                    showLoading("");
                    this.mCameraPlayerEx.a((IMISSListener) new IMISSListener() {
                        public void onProgress(int i) {
                        }

                        public void onSuccess(String str, Object obj) {
                            if (CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.u()) {
                                CameraV3UpgradePlayerActivity.this.toggleRemoteAV(true, false);
                            } else {
                                CameraV3UpgradePlayerActivity.this.toggleRemoteAV(true, true);
                            }
                            boolean unused = CameraV3UpgradePlayerActivity.this.mVideoPlaying = true;
                        }

                        public void onFailed(int i, String str) {
                            boolean unused = CameraV3UpgradePlayerActivity.this.mVideoPlaying = false;
                        }
                    });
                }
                this.mCameraPlayerEx.c(false);
                return;
            }
            pauseCamera();
        }
    }

    /* access modifiers changed from: protected */
    public void refreshUI() {
        if (!this.mCameraDevice.f().c()) {
            hideLoading();
            if (this.mPowerOffView.getVisibility() == 8) {
                Log.d("cheng", "powerOff:" + Log.getStackTraceString(new Exception()));
                this.mPowerOffView.setVisibility(0);
                if (this.mCameraPlayerEx != null) {
                    this.mCameraPlayerEx.j();
                }
                if (this.mPauseView.getVisibility() == 0) {
                    this.mPauseView.setVisibility(8);
                }
            }
            if (this.mCameraPlayerEx != null) {
                if (this.mCameraPlayerEx.r()) {
                    doStopRecord();
                }
                if (this.mCameraPlayerEx.t()) {
                    doStopCall();
                }
            }
            this.mLandSleepView.setCurrentState(1);
            this.mPortSleepView.setCurrentState(1);
            return;
        }
        this.mLandSleepView.setCurrentState(0);
        this.mPortSleepView.setCurrentState(0);
        if (this.mPowerOffView.getVisibility() == 0) {
            this.mPowerOffView.setVisibility(8);
            if (this.mCameraPlayerEx != null) {
                this.mCameraPlayerEx.a((IMISSListener) new IMISSListener() {
                    public void onProgress(int i) {
                    }

                    public void onSuccess(String str, Object obj) {
                        if (CameraV3UpgradePlayerActivity.this.mCameraPlayerEx.u()) {
                            CameraV3UpgradePlayerActivity.this.toggleRemoteAV(true, false);
                        } else {
                            CameraV3UpgradePlayerActivity.this.toggleRemoteAV(true, true);
                        }
                        boolean unused = CameraV3UpgradePlayerActivity.this.mVideoPlaying = true;
                    }

                    public void onFailed(int i, String str) {
                        boolean unused = CameraV3UpgradePlayerActivity.this.mVideoPlaying = false;
                    }
                });
            } else {
                startPlay();
            }
        }
    }

    public void onDestroy() {
        if (this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.a(this.mShowView && !CameraFrameManager.a().a(this.mCameraDevice.getDid()));
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
        super.onDestroy();
        Event.b();
    }

    public void onServerCmd(int i, byte[] bArr) {
        if (i == CameraPlayerEx.f) {
            new CommandTreatment(this.mHandler, this.mCameraDevice).processData(bArr);
        } else if (i == 263) {
            LogUtil.a(TAG, "MISS_CMD_SPEAKER_START_RESP:" + bArr.toString());
            try {
                int intValue = Integer.valueOf(new String(bArr)).intValue();
                if (intValue == -1) {
                    if (this.mCameraPlayerEx != null) {
                        this.mCameraPlayerEx.q();
                    }
                    this.mHandler.removeMessages(2101);
                    this.mLandCallView.setCurrentState(0);
                    this.mPortCallView.setCurrentState(0);
                    if (this.mAudioOn) {
                        this.mPortAudioView.setCurrentState(1);
                        this.mLandAudioView.setCurrentState(1);
                        if (this.mCameraPlayerEx != null) {
                            this.mCameraPlayerEx.b(false);
                        }
                    } else {
                        this.mPortAudioView.setCurrentState(0);
                        this.mLandAudioView.setCurrentState(0);
                        if (this.mCameraPlayerEx != null) {
                            this.mCameraPlayerEx.b(true);
                        }
                    }
                    ToastUtil.a((Context) this, (int) R.string.devcie_on_calling);
                } else if (4 == intValue) {
                    ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.speak_info));
                    this.mLandCallView.setCurrentState(0);
                    this.mPortCallView.setCurrentState(0);
                    this.mPortAudioView.setCurrentState(0);
                    this.mLandAudioView.setCurrentState(0);
                    SDKLog.e("Huang", "onServerCmd setMute true 33333333");
                    this.mCameraPlayerEx.b(true);
                    this.mCameraPlayerEx.q();
                    this.mCameraPlayerEx.b();
                } else if (1 != intValue) {
                    ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.speak_faile));
                    this.mLandCallView.setCurrentState(0);
                    this.mPortCallView.setCurrentState(0);
                    this.mPortAudioView.setCurrentState(0);
                    this.mLandAudioView.setCurrentState(0);
                    SDKLog.e("Huang", "onServerCmd setMute true 44444444");
                    this.mCameraPlayerEx.b(true);
                    this.mCameraPlayerEx.q();
                    this.mCameraPlayerEx.b();
                }
            } catch (Exception e) {
                LogUtil.b(TAG, "Exception:" + e.getLocalizedMessage());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doResume() {
        this.mPauseView.setVisibility(8);
        if (this.mCameraPlayerEx == null || this.mCameraPlayerEx.l()) {
            setPlayTime();
            return;
        }
        showLastLoading();
        this.mCameraPlayerEx.a(0, false);
    }

    /* access modifiers changed from: private */
    public void videoClick() {
        SDKLog.e(TAG, "videoClick");
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
                if (this.mLandCallView.getTranslationY() > 0.0f) {
                    ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.mLandCallView, "translationY", new float[]{(float) this.mLandCallView.getHeight(), 0.0f});
                    ofFloat3.setDuration(200);
                    ofFloat3.start();
                    return;
                }
                ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.mLandCallView, "translationY", new float[]{0.0f, (float) this.mLandCallView.getHeight()});
                ofFloat4.setDuration(200);
                ofFloat4.start();
                return;
            }
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mPortraitControlLayout.getLayoutParams();
            if (this.mPortraitControlLayout.getTranslationY() > 0.0f) {
                ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.mPortraitControlLayout, "translationY", new float[]{(float) this.mPortraitControlLayout.getHeight(), 0.0f});
                ofFloat5.setDuration(200);
                ofFloat5.start();
                return;
            }
            ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.mPortraitControlLayout, "translationY", new float[]{0.0f, (float) this.mPortraitControlLayout.getHeight()});
            ofFloat6.setDuration(200);
            ofFloat6.start();
        }
    }

    /* access modifiers changed from: private */
    public void toggleCtrlContainerVisibility() {
        boolean c = this.mCameraDevice.f().c();
        SDKLog.e(TAG, "isShow::" + c);
        if (getRequestedOrientation() == 1) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mPortraitControlLayout.getLayoutParams();
            if (c) {
                if (this.mPortraitControlLayout.getTranslationY() > 0.0f) {
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mPortraitControlLayout, "translationY", new float[]{(float) this.mPortraitControlLayout.getHeight(), 0.0f});
                    ofFloat.setDuration(200);
                    ofFloat.start();
                }
            } else if (this.mPortraitControlLayout.getTranslationY() <= 0.0f) {
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mPortraitControlLayout, "translationY", new float[]{0.0f, (float) this.mPortraitControlLayout.getHeight()});
                ofFloat2.setDuration(200);
                ofFloat2.start();
            }
        } else if (c) {
            if (this.mLLTopCtrlLandscape.getTranslationY() < 0.0f) {
                this.mLLTopCtrlLandscape.bringToFront();
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.mLLTopCtrlLandscape, "translationY", new float[]{(float) (-this.mLLTopCtrlLandscape.getHeight()), 0.0f});
                ofFloat3.setDuration(200);
                ofFloat3.start();
            }
            if (this.mLandCallView.getTranslationY() > 0.0f) {
                ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.mLandCallView, "translationY", new float[]{(float) this.mLandCallView.getHeight(), 0.0f});
                ofFloat4.setDuration(200);
                ofFloat4.start();
            }
        } else {
            if (this.mLLTopCtrlLandscape.getTranslationY() >= 0.0f) {
                ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.mLLTopCtrlLandscape, "translationY", new float[]{0.0f, (float) (-this.mLLTopCtrlLandscape.getHeight())});
                ofFloat5.setDuration(200);
                ofFloat5.start();
            }
            if (this.mLandCallView.getTranslationY() == 0.0f) {
                ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.mLandCallView, "translationY", new float[]{0.0f, (float) this.mLandCallView.getHeight()});
                ofFloat6.setDuration(200);
                ofFloat6.start();
            }
        }
    }

    private void snapShot() {
        if (this.mSnapshot) {
            ToastUtil.a((Context) activity(), (int) R.string.btn_click_too_much);
            return;
        }
        this.mSnapshot = true;
        if (!this.mCameraDevice.f().c()) {
            ToastUtil.a((Context) activity(), (int) R.string.power_off);
            this.mSnapshot = false;
        } else if (!com.Utils.Util.b(activity(), "android.permission.WRITE_EXTERNAL_STORAGE")) {
            ToastUtil.a((Context) activity(), (int) R.string.no_write_permission);
            this.mSnapshot = false;
        } else if (this.mCameraDevice == null || this.mCameraDevice.f() == null || !this.mCameraDevice.f().c()) {
            this.mSnapshot = false;
            ToastUtil.a((Context) activity(), (int) R.string.power_off);
        } else if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.l()) {
            this.mSnapshot = false;
            ToastUtil.a((Context) activity(), (int) R.string.snap_failed_paused);
        } else {
            this.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
                public void onSnap(final Bitmap bitmap) {
                    CameraV3UpgradePlayerActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            CameraV3UpgradePlayerActivity.this.onSnapShot(bitmap);
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
            this.mSnapshot = false;
            return;
        }
        if (bitmap != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(a2);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.close();
            } catch (IOException unused) {
                this.mSnapshot = false;
                return;
            }
        }
        final Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, 300, (bitmap.getHeight() * 300) / bitmap.getWidth(), false);
        runOnUiThread(new Runnable() {
            public void run() {
                if (new File(a2).exists()) {
                    ImageView imageView = (ImageView) CameraV3UpgradePlayerActivity.this.findViewById(R.id.ivShotPic);
                    if (CameraV3UpgradePlayerActivity.this.mLLFuncPopup.getVisibility() == 0) {
                        CameraV3UpgradePlayerActivity.this.mLLFuncPopup.setVisibility(8);
                    }
                    CameraV3UpgradePlayerActivity.this.mLLFuncPopup.setLayoutParams((FrameLayout.LayoutParams) CameraV3UpgradePlayerActivity.this.mLLFuncPopup.getLayoutParams());
                    CameraV3UpgradePlayerActivity.this.mLLFuncPopup.startAnimation(AnimationUtils.loadAnimation(CameraV3UpgradePlayerActivity.this.activity(), R.anim.anim_snap_shot_in));
                    CameraV3UpgradePlayerActivity.this.mLLFuncPopup.setVisibility(0);
                    CameraV3UpgradePlayerActivity.this.dismissSnapshotPopupRunnable(3000);
                    if (createScaledBitmap != null) {
                        imageView.setImageBitmap(createScaledBitmap);
                    }
                    ContentValues contentValues = new ContentValues(4);
                    contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
                    contentValues.put(Downloads._DATA, a2);
                    contentValues.put("mime_type", "image/jpeg");
                    try {
                        if (!Build.MODEL.equals("HM 1SC")) {
                            CameraV3UpgradePlayerActivity.this.activity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                        }
                        SDKLog.e(CameraV3UpgradePlayerActivity.TAG, "snap success");
                        boolean unused = CameraV3UpgradePlayerActivity.this.mSnapshot = false;
                        final LocalFileManager.LocalFile b = CameraV3UpgradePlayerActivity.this.mCameraDevice.b().b(a2);
                        if (b != null) {
                            imageView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    if (CameraV3UpgradePlayerActivity.this.canStepOut(R.string.speaking_block, R.string.recording_block)) {
                                        CameraV3UpgradePlayerActivity.this.dismissSnapshotPopupRunnable(0);
                                        Intent intent = new Intent(CameraV3UpgradePlayerActivity.this, LocalPicReviewActivity.class);
                                        intent.putExtra("file_path", b.d);
                                        CameraV3UpgradePlayerActivity.this.startActivity(intent);
                                    }
                                }
                            });
                        }
                    } catch (Throwable unused2) {
                        boolean unused3 = CameraV3UpgradePlayerActivity.this.mSnapshot = false;
                    }
                } else {
                    boolean unused4 = CameraV3UpgradePlayerActivity.this.mSnapshot = false;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void dismissSnapshotPopupRunnable(long j) {
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (CameraV3UpgradePlayerActivity.this.mLLFuncPopup != null && CameraV3UpgradePlayerActivity.this.mLLFuncPopup.getVisibility() != 8) {
                    CameraV3UpgradePlayerActivity.this.mLLFuncPopup.startAnimation(AnimationUtils.loadAnimation(CameraV3UpgradePlayerActivity.this.activity(), R.anim.anim_snap_shot_out));
                    CameraV3UpgradePlayerActivity.this.mLLFuncPopup.setVisibility(8);
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
                            CameraV3UpgradePlayerActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    if (createScaledBitmap != null) {
                                        ImageView imageView = (ImageView) CameraV3UpgradePlayerActivity.this.findViewById(R.id.ivShotPic);
                                        imageView.setImageBitmap(createScaledBitmap);
                                        if (CameraV3UpgradePlayerActivity.this.mLLFuncPopup.getVisibility() == 0) {
                                            CameraV3UpgradePlayerActivity.this.mLLFuncPopup.setVisibility(8);
                                        }
                                        CameraV3UpgradePlayerActivity.this.mLLFuncPopup.setLayoutParams((FrameLayout.LayoutParams) CameraV3UpgradePlayerActivity.this.mLLFuncPopup.getLayoutParams());
                                        CameraV3UpgradePlayerActivity.this.mLLFuncPopup.startAnimation(AnimationUtils.loadAnimation(CameraV3UpgradePlayerActivity.this.activity(), R.anim.anim_snap_shot_in));
                                        CameraV3UpgradePlayerActivity.this.mLLFuncPopup.setVisibility(0);
                                        CameraV3UpgradePlayerActivity.this.dismissSnapshotPopupRunnable(3000);
                                        imageView.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View view) {
                                                if (CameraV3UpgradePlayerActivity.this.canStepOut(R.string.speaking_block, R.string.recording_block)) {
                                                    CameraV3UpgradePlayerActivity.this.dismissSnapshotPopupRunnable(0);
                                                    Intent intent = new Intent(CameraV3UpgradePlayerActivity.this, LocalVideoPlayActivity.class);
                                                    intent.putExtra("file_path", b.d);
                                                    CameraV3UpgradePlayerActivity.this.startActivity(intent);
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
        if (this.mCameraPlayerEx != null && !this.mCameraPlayerEx.f()) {
            ToastUtil.a((Context) activity(), (int) R.string.history_video_resolution_hd_only);
        } else if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.r()) {
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity());
            builder.a((int) R.string.quality_choice);
            final String[] strArr = {getString(R.string.quality_smooth), getString(R.string.quality_auto), getString(R.string.quality_fhd)};
            this.selectedIndex = this.mCameraDevice.e().d();
            if (this.selectedIndex == 3) {
                this.selectedIndex = 2;
            }
            builder.a((CharSequence[]) strArr, this.selectedIndex, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CameraV3UpgradePlayerActivity.this.selectedIndex = i;
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (CameraV3UpgradePlayerActivity.this.selectedIndex >= 0 && CameraV3UpgradePlayerActivity.this.selectedIndex < strArr.length && !TextUtils.isEmpty(strArr[CameraV3UpgradePlayerActivity.this.selectedIndex])) {
                        CameraV3UpgradePlayerActivity.this.setResolutionTextNew(CameraV3UpgradePlayerActivity.this.selectedIndex);
                        SDKLog.e(CameraV3UpgradePlayerActivity.TAG, "设置了码流=" + CameraV3UpgradePlayerActivity.this.selectedIndex);
                        CameraV3UpgradePlayerActivity.this.mCameraDevice.e().b(CameraV3UpgradePlayerActivity.this.selectedIndex);
                        if (CameraV3UpgradePlayerActivity.this.mCameraPlayerEx != null) {
                            boolean z = false;
                            int i2 = 110;
                            if (CameraV3UpgradePlayerActivity.this.selectedIndex == 1) {
                                z = true;
                            } else if (CameraV3UpgradePlayerActivity.this.selectedIndex != 2) {
                                i2 = 20;
                            }
                            CameraV3UpgradePlayerActivity.this.mCameraDevice.f().a(z, i2, (Callback<Void>) new Callback<Void>() {
                                public void onFailure(int i, String str) {
                                }

                                public void onSuccess(Void voidR) {
                                }
                            });
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
        this.mLandRecordTimer.setText(getString(R.string.main_recording, new Object[]{format}));
        this.record_time.setTextColor(-65536);
        this.record_time.setText(getString(R.string.main_recording, new Object[]{format}));
    }

    /* access modifiers changed from: protected */
    public void detectSDCard() {
        if (this.mCameraDevice != null) {
            this.sdcardStatus = 0;
            AnonymousClass36 r0 = new Callback<SDCardInfo>() {
                public void onSuccess(SDCardInfo sDCardInfo) {
                    LogUtil.a(CameraV3UpgradePlayerActivity.TAG, "detectSDCard onSuccess:" + sDCardInfo.e);
                    CameraV3UpgradePlayerActivity.this.sdcardGetSuccess = true;
                    if (CameraV3UpgradePlayerActivity.this.mCameraDevice.e().f8058a && (sDCardInfo.e == 1 || sDCardInfo.e == 3 || sDCardInfo.e == 5)) {
                        CameraV3UpgradePlayerActivity.this.showSDCardHintDialog();
                    }
                    CameraV3UpgradePlayerActivity.this.sdcardStatus = sDCardInfo.e;
                }

                public void onFailure(int i, String str) {
                    LogUtil.b(CameraV3UpgradePlayerActivity.TAG, "detectSDCard onFailure:" + i + " s:" + str);
                    if (-97 != i) {
                        if (i == -2003 || i == -2002 || i == -2005 || i == -2000) {
                            CameraV3UpgradePlayerActivity.this.sdcardGetSuccess = true;
                        } else {
                            CameraV3UpgradePlayerActivity.this.sdcardGetSuccess = false;
                        }
                        if (i == -2003) {
                            CameraV3UpgradePlayerActivity.this.sdcardStatus = 1;
                        } else if (i == -2000) {
                            CameraV3UpgradePlayerActivity.this.sdcardStatus = 4;
                        } else if (i == -2005) {
                            CameraV3UpgradePlayerActivity.this.sdcardStatus = 5;
                        } else if (i == -2002) {
                            CameraV3UpgradePlayerActivity.this.sdcardStatus = 3;
                        }
                    }
                }
            };
            if (this.mCameraDevice.n()) {
                this.mCameraDevice.d().b((Callback<SDCardInfo>) r0, true);
            } else {
                this.mCameraDevice.c().b((Callback<SDCardInfo>) r0, true);
            }
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
        XmPluginHostApi.instance().checkAndRequestPermisson(activity(), true, (Callback<List<String>>) null, "android.permission.WRITE_EXTERNAL_STORAGE");
        if (this.mGuideCenter == null) {
            try {
                TextView textView = new TextView(activity());
                textView.setText(R.string.guide_center);
                textView.setTextSize(0, (float) ((int) (AppConfig.d * 15.0f)));
                textView.setTextColor(getResources().getColor(R.color.white));
                textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.guide_center_bg, 0, 0, 0);
                textView.setGravity(16);
                textView.setCompoundDrawablePadding((int) (AppConfig.d * 5.0f));
                this.mGuideCenter = GuideView.Builder.newInstance(activity()).setContainerView(this.mVideoViewFrame).setCustomGuideView(textView).build();
                this.mGuideCenter.show();
                this.mGuideCenter.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        CameraV3UpgradePlayerActivity.this.mGuideCenter.hide();
                        CameraV3UpgradePlayerActivity.this.mCameraDevice.e().f(false);
                    }
                });
            } catch (Exception unused) {
                this.mCameraDevice.e().f(false);
            }
        }
    }

    private void showLicense() {
        if (this.mDeviceStat != null && !TextUtils.isEmpty(this.mDeviceStat.did)) {
            if (this.mCameraDevice.isShared()) {
                SDKLog.e(TAG, "分享用户不弹出隐私协议");
                return;
            }
            try {
                if (this.mCameraDevice.e().i()) {
                    this.mIsInit = true;
                    this.mNeedLicense = true;
                    LocalLicenseUtil.LocalLicense v3UpgradeLicense = LocalLicenseUtil.getV3UpgradeLicense(getResources());
                    int i = v3UpgradeLicense.mLicense;
                    int i2 = v3UpgradeLicense.mPrivacy;
                    if (i != 0) {
                        if (i2 != 0) {
                            if (XmPluginHostApi.instance().getApiLevel() >= 67) {
                                Intent intent = new Intent();
                                if ("cn".equalsIgnoreCase(ServerUtil.b())) {
                                    intent.putExtra("enable_privacy_setting", true);
                                    intent.putExtra(DeviceMoreActivity.ARGS_USR_EXP_PLAN_URI, v3UpgradeLicense.mPlan);
                                } else {
                                    intent.putExtra("enable_privacy_setting", false);
                                }
                                if (this.mDeviceStat == null) {
                                    return;
                                }
                                if (!TextUtils.isEmpty(this.mDeviceStat.did)) {
                                    showUserLicenseDialog(getString(R.string.privacy_title), getString(R.string.licences_content), i, getString(R.string.privacy_content), i2, new View.OnClickListener() {
                                        public void onClick(View view) {
                                            boolean unused = CameraV3UpgradePlayerActivity.this.mNeedLicense = false;
                                            CameraV3UpgradePlayerActivity.this.mHandler.removeMessages(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
                                            CameraV3UpgradePlayerActivity.this.mHandler.sendEmptyMessage(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
                                            CameraV3UpgradePlayerActivity.this.mHandler.sendEmptyMessage(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
                                            CameraV3UpgradePlayerActivity.this.mCameraDevice.e().g(false);
                                            if (CameraV3UpgradePlayerActivity.this.mCameraDevice.e().h()) {
                                                CameraV3UpgradePlayerActivity.this.initGuideCenter();
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
                String c = FileUtil.c(CameraV3UpgradePlayerActivity.this.mCameraDevice.getDid());
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
                if (!CameraV3UpgradePlayerActivity.this.isFinishing()) {
                    dialogInterface.dismiss();
                    CameraV3UpgradePlayerActivity.this.finish();
                }
            }
        });
        builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!CameraV3UpgradePlayerActivity.this.isFinishing()) {
                    if (z) {
                        CameraV3UpgradePlayerActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(AppCode.u)));
                    }
                    dialogInterface.dismiss();
                    CameraV3UpgradePlayerActivity.this.finish();
                }
            }
        });
        builder.d();
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
                        if (!CameraV3UpgradePlayerActivity.this.isFinishing() && jSONObject != null && jSONObject.optInt("code", -1) == 0 && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
                            boolean optBoolean = optJSONObject.optBoolean("vip");
                            CameraV3UpgradePlayerActivity.this.mCameraDevice.e().d(optJSONObject.optBoolean("closeWindow"));
                            CameraV3UpgradePlayerActivity.this.mCameraDevice.e().c(optBoolean);
                            CameraV3UpgradePlayerActivity.this.firmwareSupportCloudVideoDlg();
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!CameraV3UpgradePlayerActivity.this.isFinishing()) {
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
                    SDKLog.e(CameraV3UpgradePlayerActivity.TAG, "getCurrentFirmWareVersion i:" + i + " s:" + str);
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
        if (!this.mCameraDevice.isShared()) {
            new UserLicenseDialog.Builder(this).a(str).b(str2).a(i).c(str3).b(i2).a(intent).d(this.mDid).a(onClickListener).a().a();
        }
    }

    /* access modifiers changed from: private */
    public void toggleRemoteAV(boolean z, boolean z2) {
        if (this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.a(z, (IMISSListener) null);
            this.mCameraPlayerEx.b(z2, (IMISSListener) null);
        }
    }

    private void eventLiveVideoDuration() {
        if (MIStreamStatistic.getInstance().latestLiveVideo > 0 && this.mCameraDevice != null) {
            try {
                MIStreamStatistic.getInstance().sendCameraWatchDuration(this.mCameraDevice.getModel(), this.mCameraDevice.getDid(), System.currentTimeMillis() - MIStreamStatistic.getInstance().latestLiveVideo);
            } catch (Exception e) {
                LogUtil.a(TAG, "timestamp:" + e.getLocalizedMessage());
            }
        }
    }

    public void onConnected() {
        super.onConnected();
        SDKLog.e(TAG, "摄像机连接成功");
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                CameraV3UpgradePlayerActivity.this.connectedSuccessfully();
            }
        }, 500);
        judgeDownloadTimeLapseDemo();
        doResume();
    }

    private String getDownloadFilePath() {
        String d = FileUtil.d();
        if (TextUtils.isEmpty(d)) {
            return d;
        }
        File file = new File(d);
        if (!file.exists()) {
            file.mkdirs();
        }
        return d + File.separator + "timelaps_demo.gif";
    }

    private void judgeDownloadTimeLapseDemo() {
        if (!new File(getDownloadFilePath()).exists()) {
            SDKLog.b("AlbumActivity", "gif 图片本地不存在 下载 ");
            DownLoadTimeLapseDemo.getInstance(activity(), this.mCameraDevice.getDid(), this.mHandler).downLoadFile();
            return;
        }
        SDKLog.b("AlbumActivity", "gif 图片本地存在 不下载 ");
    }

    /* access modifiers changed from: private */
    public void connectedSuccessfully() {
        if (this.mCameraPlayerEx != null) {
            SDKLog.e(TAG, "mNeedLicense22 ==" + this.mNeedLicense + ",mCameraDevice did ==" + this.mCameraDevice.getDid() + ",mCameraDevice uid=" + this.mCameraDevice.r());
            SDKLog.e(TAG, "发送10002");
            this.mCameraPlayerEx.A();
            SDKLog.e(TAG, "打开设备端的声音");
            this.mCameraPlayerEx.B();
            SDKLog.e("Huang", "connectedSuccessfully setMute true llllllllllll");
            this.mCameraPlayerEx.b(true);
            SDKLog.e(TAG, "发送校时命令");
            this.mCameraPlayerEx.z();
            SDKLog.e(TAG, "发送了拉RDT数据 isHistory=" + isHistory());
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    if (!CameraV3UpgradePlayerActivity.this.isHistory()) {
                        CameraV3UpgradePlayerActivity.this.mCameraDevice.d().a((Callback<Void>) null, true);
                    }
                }
            }, 2000);
        }
    }

    public void onSendCmdError() {
        SDKLog.e(TAG, "onSendCmdError");
        ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.operation_failed));
    }
}
