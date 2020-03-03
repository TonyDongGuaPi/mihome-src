package com.xiaomi.smarthome.camera.v4.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.Utils.FileUtil;
import com.Utils.NetworkUtil;
import com.Utils.TimeUtils;
import com.amap.api.services.core.AMapException;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.debug.SDKLog;
import com.mi.global.bbs.manager.Region;
import com.mijia.app.AppCode;
import com.mijia.app.AppConfig;
import com.mijia.app.Event;
import com.mijia.camera.CameraPlayer;
import com.mijia.camera.CameraPlayerEx;
import com.mijia.camera.CameraProperties;
import com.mijia.camera.ICameraPlayerListener;
import com.mijia.camera.Utils.BitmapUtils;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.Tag;
import com.mijia.model.alarm.AlarmNetUtils;
import com.mijia.model.local.LocalFileClear;
import com.mijia.model.local.LocalFileManager;
import com.mijia.model.local.LocalSettings;
import com.mijia.model.property.CameraPropertyBase;
import com.mijia.model.sdcard.SDCardInfo;
import com.mijia.model.sdcard.TimeItem;
import com.sina.weibo.sdk.statistic.LogBuilder;
import com.smarthome_camera.BuildConfig;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.camera.IMISSListener;
import com.xiaomi.smarthome.camera.LocalLicenseUtil;
import com.xiaomi.smarthome.camera.XmMp4Record;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.activity.CameraPlayerBaseActivity;
import com.xiaomi.smarthome.camera.activity.alarm.AlarmAISwitchActivity;
import com.xiaomi.smarthome.camera.activity.alarm.AlarmSettingV2Activity;
import com.xiaomi.smarthome.camera.activity.alarm2.AlarmVideoActivity;
import com.xiaomi.smarthome.camera.activity.local.AlbumActivity;
import com.xiaomi.smarthome.camera.activity.local.LocalPicReviewActivity;
import com.xiaomi.smarthome.camera.activity.local.LocalVideoPlayActivity;
import com.xiaomi.smarthome.camera.activity.sdcard.SDCardActivity;
import com.xiaomi.smarthome.camera.activity.setting.FileManagerSettingActivity;
import com.xiaomi.smarthome.camera.activity.setting.SDCardStatusActivityNew;
import com.xiaomi.smarthome.camera.v4.activity.CameraPlayerActivity;
import com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmActivity;
import com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmGuideActivity;
import com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmSettingActivity;
import com.xiaomi.smarthome.camera.v4.activity.setting.MoreCameraSettingActivity;
import com.xiaomi.smarthome.camera.v4.activity.setting.NoMemoryCardActivity;
import com.xiaomi.smarthome.camera.v4.view.AngleView;
import com.xiaomi.smarthome.camera.v4.view.DirectionCtrlView;
import com.xiaomi.smarthome.camera.v4.view.MoreDialog;
import com.xiaomi.smarthome.camera.v4.view.TimeLineControlView;
import com.xiaomi.smarthome.camera.v4.view.TimeLineWithDatePickView;
import com.xiaomi.smarthome.camera.view.GuidePage1;
import com.xiaomi.smarthome.camera.view.MultiStateTextView;
import com.xiaomi.smarthome.camera.view.SDCardHintDialog;
import com.xiaomi.smarthome.camera.view.widget.CenterDrawableCheckBox;
import com.xiaomi.smarthome.camera.view.widget.MultiStateView;
import com.xiaomi.smarthome.camera.view.widget.WaveView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.FirmwareUpdateResult;
import com.xiaomi.smarthome.device.api.IXmPluginHostActivity;
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
import com.xiaomi.smarthome.framework.plugin.mpk.PluginHostApiImpl;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.lineargradient.LinearGradientManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.UserLicenseDialog;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.ICloudVideoCallback;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import com.xiaomi.stat.d;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraPlayerActivity extends CameraPlayerBaseActivity implements ICameraPlayerListener, VideoGlSurfaceView.DistortCallBack {
    private static final String TAG = "CameraPlayerActivity";
    private final int ANGLE_VIEW_GONE_DELAYED_TIME = 3000;
    private final int CALL_VOLUME_DELAY = 500;
    private final int FIRST_RESUME = AMapException.CODE_AMAP_NEARBY_INVALID_USERID;
    private final int MSG_CALL_VOLUME = 2101;
    private final int MSG_DISMISS_HINT = 1002;
    private final int MSG_UPDATE_FIRM = 1;
    private DirectionCtrlView.OnDirectionCtrlListener OnDirectionCtrlListener = new DirectionCtrlView.OnDirectionCtrlListener() {
        private Runnable angleViewVisibilityGoneRunnable = new Runnable() {
            public final void run() {
                CameraPlayerActivity.this.mAVCamera.setVisibility(8);
            }
        };

        public void onClickPTZDirection(int i) {
            if (CameraPlayerActivity.this.mCameraDevice == null) {
                return;
            }
            if (!CameraPlayerActivity.this.mCameraDevice.f().c()) {
                ToastUtil.a((Context) CameraPlayerActivity.this, (int) R.string.power_off);
                return;
            }
            Event.a(i);
            switch (i) {
                case 1:
                    if (CameraPlayerActivity.this.mCameraPlayer != null) {
                        CameraPlayerActivity.this.mCameraPlayer.c(1);
                    }
                    if (CameraPlayerActivity.this.mCameraPlayerEx != null) {
                        CameraPlayerActivity.this.mCameraPlayerEx.b(1, (IMISSListener) null);
                        return;
                    }
                    return;
                case 2:
                    if (CameraPlayerActivity.this.mCameraPlayer != null) {
                        CameraPlayerActivity.this.mCameraPlayer.c(2);
                    }
                    if (CameraPlayerActivity.this.mCameraPlayerEx != null) {
                        CameraPlayerActivity.this.mCameraPlayerEx.b(2, (IMISSListener) null);
                        return;
                    }
                    return;
                case 3:
                    if (CameraPlayerActivity.this.mCameraPlayer != null) {
                        CameraPlayerActivity.this.mCameraPlayer.c(3);
                    }
                    if (CameraPlayerActivity.this.mCameraPlayerEx != null) {
                        CameraPlayerActivity.this.mCameraPlayerEx.b(3, (IMISSListener) null);
                        return;
                    }
                    return;
                case 4:
                    if (CameraPlayerActivity.this.mCameraPlayer != null) {
                        CameraPlayerActivity.this.mCameraPlayer.c(4);
                    }
                    if (CameraPlayerActivity.this.mCameraPlayerEx != null) {
                        CameraPlayerActivity.this.mCameraPlayerEx.b(4, (IMISSListener) null);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        public void onActionDown() {
            if (CameraPlayerActivity.this.mCameraPlayer != null && CameraPlayerActivity.this.mCameraPlayer.d() && CameraPlayerActivity.this.getRequestedOrientation() == 1) {
                CameraPlayerActivity.this.runOnUiThread(new Runnable() {
                    public final void run() {
                        CameraPlayerActivity.AnonymousClass2.lambda$onActionDown$1(CameraPlayerActivity.AnonymousClass2.this);
                    }
                });
            }
            if (CameraPlayerActivity.this.mCameraPlayerEx != null && CameraPlayerActivity.this.mCameraPlayerEx.g() && CameraPlayerActivity.this.getRequestedOrientation() == 1) {
                CameraPlayerActivity.this.runOnUiThread(new Runnable() {
                    public final void run() {
                        CameraPlayerActivity.AnonymousClass2.lambda$onActionDown$2(CameraPlayerActivity.AnonymousClass2.this);
                    }
                });
            }
        }

        public static /* synthetic */ void lambda$onActionDown$1(AnonymousClass2 r2) {
            CameraPlayerActivity.this.mHandler.removeCallbacks(r2.angleViewVisibilityGoneRunnable);
            CameraPlayerActivity.this.mAVCamera.setVisibility(0);
        }

        public static /* synthetic */ void lambda$onActionDown$2(AnonymousClass2 r2) {
            CameraPlayerActivity.this.mHandler.removeCallbacks(r2.angleViewVisibilityGoneRunnable);
            CameraPlayerActivity.this.mAVCamera.setVisibility(0);
        }

        public void onActionUp(boolean z) {
            Event.a(Event.ax);
            if (z && CameraPlayerActivity.this.mCameraPlayer != null) {
                CameraPlayerActivity.this.mCameraPlayer.c(-1001);
            }
            if (z && CameraPlayerActivity.this.mCameraPlayerEx != null) {
                CameraPlayerActivity.this.mCameraPlayerEx.b(-1001, (IMISSListener) null);
            }
            Event.z();
            if (CameraPlayerActivity.this.mCameraPlayer != null && CameraPlayerActivity.this.mCameraPlayer.d() && CameraPlayerActivity.this.getRequestedOrientation() == 1) {
                CameraPlayerActivity.this.runOnUiThread(new Runnable() {
                    public final void run() {
                        CameraPlayerActivity.this.mHandler.postDelayed(CameraPlayerActivity.AnonymousClass2.this.angleViewVisibilityGoneRunnable, 3000);
                    }
                });
            }
            if (CameraPlayerActivity.this.mCameraPlayerEx != null && CameraPlayerActivity.this.mCameraPlayerEx.g() && CameraPlayerActivity.this.getRequestedOrientation() == 1) {
                CameraPlayerActivity.this.runOnUiThread(new Runnable() {
                    public final void run() {
                        CameraPlayerActivity.this.mHandler.postDelayed(CameraPlayerActivity.AnonymousClass2.this.angleViewVisibilityGoneRunnable, 3000);
                    }
                });
            }
        }
    };
    final int REQUEST_MORE_ACTIVITY = 1220;
    private final int UPDATE_CHECK = Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS;
    private final int UPDATE_PLAY_TIME_DURATION = 2000;
    /* access modifiers changed from: private */
    public FrameLayout flCloudVideoTips;
    private FrameLayout flTitleBar;
    private boolean isSnapshotting = false;
    private boolean isStartPlay = false;
    private ImageView ivCameraShot;
    private ImageView ivFullScreen;
    private ImageView ivShotPic;
    /* access modifiers changed from: private */
    public AngleView mAVCamera;
    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (CameraPlayerActivity.this.mCameraDevice.c() != null && CameraPlayerActivity.this.mCameraDevice.c().f().equals(intent.getAction())) {
                if (!CameraPlayerActivity.this.isFinishing()) {
                    CameraPlayerActivity.this.mTimeLineControlView.setTimeItems(CameraPlayerActivity.this.mCameraDevice.c().i());
                } else {
                    return;
                }
            }
            if (CameraPlayerActivity.this.mCameraDevice.d() != null && CameraPlayerActivity.this.mCameraDevice.d().f().equals(intent.getAction()) && !CameraPlayerActivity.this.isFinishing()) {
                CameraPlayerActivity.this.mTimeLineControlView.setTimeItems(CameraPlayerActivity.this.mCameraDevice.d().i());
            }
        }
    };
    /* access modifiers changed from: private */
    public CheckBox mCBPlayBack;
    /* access modifiers changed from: private */
    public CenterDrawableCheckBox mCDCCameraRecord;
    /* access modifiers changed from: private */
    public CenterDrawableCheckBox mCDCToggleAudio;
    private WaveView mCallWave;
    /* access modifiers changed from: private */
    public MultiStateView mCdcToggleSleep;
    private int mCurrentTime = 0;
    private DirectionCtrlView mDcvDirectionCtrl;
    /* access modifiers changed from: private */
    public DirectionCtrlView mDcvDirectionCtrlLandscape;
    private boolean mIsInit = false;
    private boolean mIsReal = true;
    boolean mIsSetPlayTime = false;
    private RelativeLayout mLLBottomTools;
    /* access modifiers changed from: private */
    public LinearLayout mLLFuncPopup;
    private LinearLayout mLLVideoViewBottomCtrl;
    private View mLandLayout;
    private long mLastPlayTime = 0;
    long mLastSetPlayTime = 0;
    private int mLastSetStart = 0;
    private int mLastTime = 0;
    private LocalBroadcastManager mLocalBroadcastManager;
    private MultiStateTextView mMSTVVoice;
    private boolean mNeedCheckAlive = false;
    /* access modifiers changed from: private */
    public boolean mNeedLicense = false;
    private View mNewFirmView;
    private RelativeLayout mRLDirectionCtl;
    private RelativeLayout mRLParent;
    private SDCardHintDialog mSDCardHintDialog;
    private long mSelectTime;
    private SimpleDateFormat mSimpleDateFormat;
    private TextView mTVAlarm;
    private TextView mTVAlbum;
    /* access modifiers changed from: private */
    public TextView mTVRecordTimer;
    /* access modifiers changed from: private */
    public TextView mTVSResolution;
    private TimeLineControlView.TimeLineCallback mTimeCallBack = new TimeLineControlView.TimeLineCallback() {
        public void onUpdateTime(long j) {
            LogUtil.a(CameraPlayerActivity.TAG, "TimeLineCallback onUpdateTime:" + j);
            CameraPlayerActivity.this.mTimeUpdateView.setText(TimeUtils.a(j));
            if (CameraPlayerActivity.this.mNeedSpeed) {
                CameraPlayerActivity.this.mHandler.removeMessages(4000);
                CameraPlayerActivity.this.mNeedSpeed = false;
            }
            if (CameraPlayerActivity.this.mTimeUpdateView.getVisibility() != 0) {
                CameraPlayerActivity.this.mTimeUpdateView.setVisibility(0);
            }
        }

        public void onSelectTime(long j) {
            LogUtil.a(CameraPlayerActivity.TAG, "TimeLineCallback onSelectTime:" + j);
            if (j == 0) {
                CameraPlayerActivity.this.setPlayTime(j);
                if (!(CameraPlayerActivity.this.mCameraDevice == null || CameraPlayerActivity.this.mCameraPlayerEx == null || !CameraPlayerActivity.this.mCameraDevice.n())) {
                    if (CameraPlayerActivity.this.mCameraPlayerEx.u()) {
                        CameraPlayerActivity.this.mCameraPlayerEx.b(false, (IMISSListener) null);
                    } else {
                        CameraPlayerActivity.this.mCameraPlayerEx.b(true, (IMISSListener) null);
                    }
                    CameraPlayerActivity.this.mCameraPlayerEx.a(true, (IMISSListener) null);
                }
            } else {
                CameraPlayerActivity.this.mHandler.removeMessages(2);
                CameraPlayerActivity.this.setPlayTime(j);
            }
            Event.r();
            if (CameraPlayerActivity.this.mTimeUpdateView.getVisibility() == 0) {
                CameraPlayerActivity.this.mTimeUpdateView.setVisibility(8);
            }
        }

        public void onPlayLive() {
            LogUtil.a(CameraPlayerActivity.TAG, "TimeLineCallback onPlayLive");
            CameraPlayerActivity.this.setPlayTime(0);
            CameraPlayerActivity.this.mCDCToggleAudio.setEnabled(true);
            CameraPlayerActivity.this.mCBMuteLandscape.setEnabled(true);
            if (CameraPlayerActivity.this.mTimeUpdateView.getVisibility() == 0) {
                CameraPlayerActivity.this.mTimeUpdateView.setVisibility(8);
            }
            if (CameraPlayerActivity.this.mCameraPlayerEx != null) {
                if (CameraPlayerActivity.this.mCameraPlayerEx.u()) {
                    CameraPlayerActivity.this.mCameraPlayerEx.b(false, (IMISSListener) null);
                } else {
                    CameraPlayerActivity.this.mCameraPlayerEx.b(true, (IMISSListener) null);
                }
                CameraPlayerActivity.this.mCameraPlayerEx.a(true, (IMISSListener) null);
            }
        }

        public void onCancel() {
            LogUtil.a(CameraPlayerActivity.TAG, "TimeLineCallback onCancel");
            Event.s();
            if (CameraPlayerActivity.this.mTimeUpdateView.getVisibility() == 0) {
                CameraPlayerActivity.this.mTimeUpdateView.setVisibility(8);
            }
        }
    };
    private FrameLayout mTimeLineLand;
    private FrameLayout mTimeLineProtrait;
    /* access modifiers changed from: private */
    public TextView mTimeUpdateView;
    private View mTitleMore;
    private CameraPlayerEx.IVideoLiveListener mVideoLiveExListener = new CameraPlayerEx.IVideoLiveListener() {
        public void onVideoLiveChanged(boolean z) {
            CameraPlayerActivity.this.runOnUiThread(new Runnable(z) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    CameraPlayerActivity.AnonymousClass4.lambda$onVideoLiveChanged$0(CameraPlayerActivity.AnonymousClass4.this, this.f$1);
                }
            });
        }

        public static /* synthetic */ void lambda$onVideoLiveChanged$0(AnonymousClass4 r3, boolean z) {
            if (z) {
                if (CameraPlayerActivity.this.getRequestedOrientation() != 1) {
                    CameraPlayerActivity.this.mDcvDirectionCtrlLandscape.setVisibility(0);
                }
                CameraPlayerActivity.this.setResolutionText(CameraPlayerActivity.this.mCameraDevice.e().d());
            } else {
                if (CameraPlayerActivity.this.mCameraPlayerEx != null && CameraPlayerActivity.this.mCameraPlayerEx.t()) {
                    CameraPlayerActivity.this.doStopCall();
                }
                if (CameraPlayerActivity.this.mCameraPlayerEx != null) {
                    TextView access$1200 = CameraPlayerActivity.this.mTVSResolution;
                    access$1200.setText("" + CameraPlayerActivity.this.mCameraPlayerEx.v() + "X");
                } else {
                    CameraPlayerActivity.this.mTVSResolution.setText("1X");
                }
                if (CameraPlayerActivity.this.getRequestedOrientation() != 1) {
                    CameraPlayerActivity.this.mDcvDirectionCtrlLandscape.setVisibility(8);
                }
            }
            CameraPlayerActivity.this.mVideoViewFrame.invalidate();
        }
    };
    private CameraPlayer.IVideoLiveListener mVideoLiveListener = new CameraPlayer.IVideoLiveListener() {
        public void onVideoLiveChanged(boolean z) {
            CameraPlayerActivity.this.runOnUiThread(new Runnable(z) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    CameraPlayerActivity.AnonymousClass3.lambda$onVideoLiveChanged$0(CameraPlayerActivity.AnonymousClass3.this, this.f$1);
                }
            });
        }

        public static /* synthetic */ void lambda$onVideoLiveChanged$0(AnonymousClass3 r3, boolean z) {
            if (z) {
                if (CameraPlayerActivity.this.getRequestedOrientation() != 1) {
                    CameraPlayerActivity.this.mDcvDirectionCtrlLandscape.setVisibility(0);
                }
                CameraPlayerActivity.this.setResolutionText(CameraPlayerActivity.this.mCameraDevice.e().d());
            } else {
                if (CameraPlayerActivity.this.mCameraPlayer != null && CameraPlayerActivity.this.mCameraPlayer.o()) {
                    CameraPlayerActivity.this.doStopCall();
                }
                if (CameraPlayerActivity.this.mCameraPlayer != null) {
                    TextView access$1200 = CameraPlayerActivity.this.mTVSResolution;
                    access$1200.setText("" + CameraPlayerActivity.this.mCameraPlayer.q() + "X");
                } else {
                    CameraPlayerActivity.this.mTVSResolution.setText("1X");
                }
                if (CameraPlayerActivity.this.getRequestedOrientation() != 1) {
                    CameraPlayerActivity.this.mDcvDirectionCtrlLandscape.setVisibility(8);
                }
            }
            CameraPlayerActivity.this.mVideoViewFrame.invalidate();
        }
    };
    /* access modifiers changed from: private */
    public boolean mVideoPlaying = false;
    private MoreDialog moreDialog;
    private CameraPlayerEx.IResolutionChangedListener resolutionChangedExListener = new CameraPlayerEx.IResolutionChangedListener() {
        public void onResolutionChanged(int i, int i2) {
            if (CameraPlayerActivity.this.mCameraPlayerEx == null) {
                return;
            }
            if (CameraPlayerActivity.this.mCameraPlayerEx.f()) {
                CameraPlayerActivity.this.runOnUiThread(new Runnable() {
                    public final void run() {
                        CameraPlayerActivity.AnonymousClass6.lambda$onResolutionChanged$0(CameraPlayerActivity.AnonymousClass6.this);
                    }
                });
            } else {
                CameraPlayerActivity.this.runOnUiThread(new Runnable() {
                    public final void run() {
                        CameraPlayerActivity.this.refreshUI();
                    }
                });
            }
        }

        public static /* synthetic */ void lambda$onResolutionChanged$0(AnonymousClass6 r2) {
            CameraPlayerActivity.this.setResolutionText(CameraPlayerActivity.this.mCameraDevice.e().d());
            CameraPlayerActivity.this.refreshUI();
        }
    };
    private CameraPlayer.IResolutionChangedListener resolutionChangedListener = new CameraPlayer.IResolutionChangedListener() {
        public void onResolutionChanged(int i, int i2) {
            if (CameraPlayerActivity.this.mCameraPlayer == null) {
                return;
            }
            if (CameraPlayerActivity.this.mCameraPlayer.c()) {
                CameraPlayerActivity.this.runOnUiThread(new Runnable() {
                    public final void run() {
                        CameraPlayerActivity.AnonymousClass7.lambda$onResolutionChanged$0(CameraPlayerActivity.AnonymousClass7.this);
                    }
                });
            } else {
                CameraPlayerActivity.this.runOnUiThread(new Runnable() {
                    public final void run() {
                        CameraPlayerActivity.this.refreshUI();
                    }
                });
            }
        }

        public static /* synthetic */ void lambda$onResolutionChanged$0(AnonymousClass7 r2) {
            CameraPlayerActivity.this.setResolutionText(CameraPlayerActivity.this.mCameraDevice.e().d());
            CameraPlayerActivity.this.refreshUI();
        }
    };
    int sdcardErrorCode = 0;
    boolean sdcardGetSuccess = false;
    int sdcardStatus = 0;
    /* access modifiers changed from: package-private */
    public int selectedIndex;
    Runnable snapshotRunnable = new Runnable() {
        public void run() {
            if (CameraPlayerActivity.this.mLLFuncPopup.getVisibility() != 8) {
                CameraPlayerActivity.this.mLLFuncPopup.startAnimation(AnimationUtils.loadAnimation(CameraPlayerActivity.this, R.anim.anim_snap_shot_out));
                CameraPlayerActivity.this.mLLFuncPopup.setVisibility(8);
            }
        }
    };
    private TextView tvCloudVideo;

    private int getResolutionIndex(int i) {
        if (i != 1) {
            return i != 3 ? 0 : 2;
        }
        return 1;
    }

    static /* synthetic */ void lambda$toggleResolution$26(DialogInterface dialogInterface, int i) {
    }

    public boolean isHistory() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean shouldRecordPlayTime() {
        return true;
    }

    public void onDisConnected() {
        super.onDisConnected();
        this.mTimeLineControlView.setTimeItems(new ArrayList());
    }

    public void onDisconnectedWithCode(int i) {
        super.onDisconnectedWithCode(i);
        this.mTimeLineControlView.setTimeItems(new ArrayList());
    }

    /* access modifiers changed from: protected */
    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        this.mHandler.postDelayed(new Runnable() {
            public final void run() {
                LocalFileClear.a((CameraDevice) CameraPlayerActivity.this.mCameraDevice);
            }
        }, 2000);
        Event.a(this.mCameraDevice.getDid(), this.mCameraDevice.getModel());
        setContentView(R.layout.activity_camera_player);
        this.flTitleBar = (FrameLayout) findViewById(R.id.title_bar);
        this.flTitleBar.setBackgroundResource(R.drawable.shape_gradient_bg);
        this.flTitleBar.bringToFront();
        if (!getIntent().getBooleanExtra("pincod", false)) {
            enableVerifyPincode();
            if (this.mCameraDevice.m()) {
                this.mNeedPincode = true;
            }
        }
        this.mLocalBroadcastManager = LocalBroadcastManager.getInstance(XmPluginHostApi.instance().context());
        this.mSimpleDateFormat = new SimpleDateFormat("mm:ss");
        this.mSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
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
                    if (!CameraPlayerActivity.this.isFinishing()) {
                        CameraPlayerActivity.this.isPowerCheck = true;
                        CameraPlayerActivity.this.refreshUI();
                        CameraPlayerActivity.this.mCameraDevice.a(XmPluginHostApi.instance().isUsrExpPlanEnabled(CameraPlayerActivity.this.mCameraDevice.getDid()), (Callback<Void>) null);
                    }
                }
            });
        }
        showLicense();
        showCloudVideoTips();
        Event.a(Event.am);
        Event.a();
    }

    private void showCloudVideoTips() {
        if (this.mCameraDevice.e().o()) {
            CloudVideoNetUtils.getInstance().getCloudPromoteTips(this.mDid, new ICloudVideoCallback<String>() {
                public void onCloudVideoSuccess(String str, Object obj) {
                    CameraPlayerActivity.this.flCloudVideoTips.setVisibility(0);
                    CameraPlayerActivity.this.flCloudVideoTips.setOnClickListener(new View.OnClickListener() {
                        public final void onClick(View view) {
                            CameraPlayerActivity.this.onClick(view);
                        }
                    });
                    ((TextView) CameraPlayerActivity.this.findViewById(R.id.tv_cloud_video_tips)).setText(str);
                    if (CameraPlayerActivity.this.mCameraDevice.e().n() == 0) {
                        CameraPlayerActivity.this.mCameraDevice.e().b(System.currentTimeMillis());
                    }
                }

                public void onCloudVideoFailed(int i, String str) {
                    Log.d(CameraPlayerActivity.TAG, str);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.mLandLayout = findViewById(R.id.land_layout);
        this.mTimeLineLand = (FrameLayout) findViewById(R.id.time_line_land);
        this.mTimeLineProtrait = (FrameLayout) findViewById(R.id.time_line_portrait);
        this.mNewFirmView = findViewById(R.id.title_bar_redpoint);
        this.mTitleView = (TextView) findViewById(R.id.title_bar_title);
        this.mTimeUpdateView = (TextView) findViewById(R.id.time_container_center);
        this.mDcvDirectionCtrl = (DirectionCtrlView) findViewById(R.id.dcvDirectionCtrl);
        this.mDcvDirectionCtrl.setOnDirectionCtrlListener(this.OnDirectionCtrlListener);
        this.mDcvDirectionCtrlLandscape = (DirectionCtrlView) findViewById(R.id.dcvDirectionCtrlLandscape);
        this.mDcvDirectionCtrlLandscape.setBackgroundResource(R.drawable.shape_gray_oval_bg);
        this.mDcvDirectionCtrlLandscape.setOnDirectionCtrlListener(this.OnDirectionCtrlListener);
        this.mTitleMore = findViewById(R.id.title_bar_more);
        this.ivShotPic = (ImageView) findViewById(R.id.ivShotPic);
        if (this.mCameraDevice.isReadOnlyShared()) {
            this.mTitleMore.setVisibility(8);
        } else {
            this.mTitleMore.setVisibility(0);
        }
        this.mVideoViewFrame = (FrameLayout) findViewById(R.id.video_frame);
        String str = Build.MODEL;
        int i = (int) (AppConfig.d * 350.0f);
        if (!TextUtils.isEmpty(str) && (str.equals("MIX") || str.equals("MIX 2") || str.equals("MIX 2S") || str.equals("MI 8") || str.equals("MI 8 SE"))) {
            i = (int) (AppConfig.d * 450.0f);
        }
        this.mVideoViewFrame.getLayoutParams().height = i;
        this.mVideoViewFrame.setLayoutParams(this.mVideoViewFrame.getLayoutParams());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        FrameLayout frameLayout = new FrameLayout(this);
        this.mVideoViewFrame.addView(frameLayout, 0, layoutParams);
        this.mVideoView = XmPluginHostApi.instance().createVideoView(this, frameLayout, true, 2);
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
        frameLayout.addView(this.mLoadingView, layoutParams2);
        this.mLoadingProgress = (TextView) this.mLoadingView.findViewById(R.id.loading_progress);
        this.mLoadingTitle = (TextView) this.mLoadingView.findViewById(R.id.loading_title);
        this.mLoadingImageView = (ImageView) this.mLoadingView.findViewById(R.id.loading_anim);
        this.mLoadingAnimation = (AnimationDrawable) getResources().getDrawable(R.drawable.camera_anim_camera_loading);
        this.mLoadingImageView.setImageDrawable(this.mLoadingAnimation);
        this.mLoadingView.setVisibility(8);
        this.mErrorRetryView = LayoutInflater.from(this).inflate(R.layout.camera_v4_error_retry, (ViewGroup) null);
        frameLayout.addView(this.mErrorRetryView, layoutParams2);
        this.mErrorRetryView.setVisibility(8);
        this.mRetryView = this.mErrorRetryView.findViewById(R.id.retry_btn);
        this.mErrorInfoView = (TextView) this.mErrorRetryView.findViewById(R.id.error_info);
        this.mPowerOffView = LayoutInflater.from(this).inflate(R.layout.camera_closed_v4, (ViewGroup) null);
        frameLayout.addView(this.mPowerOffView, layoutParams2);
        this.mPowerOffView.setVisibility(8);
        this.mHintView = LayoutInflater.from(this).inflate(R.layout.camera_hint_view, (ViewGroup) null);
        frameLayout.addView(this.mHintView, layoutParams2);
        this.mVideoView.setVideoViewListener(new XmVideoViewGl.IVideoViewListener() {
            public final void onVideoViewClick() {
                CameraPlayerActivity.lambda$initView$1(CameraPlayerActivity.this);
            }
        });
        this.mFrameRate = (TextView) findViewById(R.id.sub_title_bar_title);
        this.mTVExitFullScreen = (TextView) findViewById(R.id.tvExitFullScreen);
        this.mTVExitFullScreen.setOnClickListener(this);
        this.mCBMuteLandscape = (CheckBox) findViewById(R.id.cbMuteLandscape);
        this.mCBMuteLandscape.setOnTouchListener(new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return CameraPlayerActivity.lambda$initView$2(CameraPlayerActivity.this, view, motionEvent);
            }
        });
        this.mCBMuteLandscape.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CameraPlayerActivity.lambda$initView$3(CameraPlayerActivity.this, compoundButton, z);
            }
        });
        this.mCBVoiceLandscape = (CheckBox) findViewById(R.id.cbVoiceLandscape);
        this.mCBVoiceLandscape.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CameraPlayerActivity.lambda$initView$4(CameraPlayerActivity.this, compoundButton, z);
            }
        });
        this.mLLRightCtrlLandscape = (LinearLayout) findViewById(R.id.llRightCtrlLandscape);
        this.mVideoView.initial();
        if (this.mCameraDevice == null || !this.mCameraDevice.n()) {
            this.mCameraDevice.c().a(40000);
        } else {
            this.mCameraDevice.d().a(40000);
        }
        this.mTimeLineControlView = (TimeLineWithDatePickView) findViewById(R.id.time_line_date_pick);
        this.mTimeLineControlView.synCurrentTime(System.currentTimeMillis());
        this.mTimeLineControlView.setTimeLineCallback(this.mTimeCallBack);
        this.mTitleMore.setOnClickListener(this);
        this.mRetryView.setOnClickListener(this);
        this.mLLBottomTools = (RelativeLayout) findViewById(R.id.llBottomTools);
        this.tvCloudVideo = (TextView) findViewById(R.id.tvCloudVideo);
        this.flCloudVideoTips = (FrameLayout) findViewById(R.id.fl_cloud_video_tips);
        this.mLLFuncPopup = (LinearLayout) findViewById(R.id.llFuncPopup);
        this.mLLVideoViewBottomCtrl = (LinearLayout) findViewById(R.id.llVideoViewBottomCtrl);
        this.mRLDirectionCtl = (RelativeLayout) findViewById(R.id.rlDirectionCtl);
        this.mRLParent = (RelativeLayout) findViewById(R.id.rlParent);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        this.mTVAlbum = (TextView) findViewById(R.id.tvAlbum);
        this.mTVAlbum.setOnClickListener(this);
        this.mTVSResolution = (TextView) findViewById(R.id.tvsResolution);
        this.mTVSResolution.setOnClickListener(this);
        this.ivFullScreen = (ImageView) findViewById(R.id.ivFullScreen);
        this.ivFullScreen.setOnClickListener(this);
        this.ivCameraShot = (ImageView) findViewById(R.id.ivCameraShot);
        this.ivCameraShot.setOnClickListener(this);
        this.mCBPlayBack = (CheckBox) findViewById(R.id.cbPlayBack);
        this.mCBPlayBack.setOnTouchListener(new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return CameraPlayerActivity.lambda$initView$5(CameraPlayerActivity.this, view, motionEvent);
            }
        });
        this.mCBPlayBack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CameraPlayerActivity.lambda$initView$6(CameraPlayerActivity.this, compoundButton, z);
            }
        });
        this.mPauseView = findViewById(R.id.pause_view);
        this.mPauseView.setOnClickListener(this);
        this.mTVAlarm = (TextView) findViewById(R.id.tvAlarm);
        this.mTVAlarm.setOnClickListener(this);
        findViewById(R.id.tvStorage).setOnClickListener(this);
        this.mTVRecordTimer = (TextView) findViewById(R.id.tvRecordTimer);
        this.mAVCamera = (AngleView) findViewById(R.id.avCamera);
        this.mAVCamera.angle = 0.0f;
        this.mCDCCameraRecord = (CenterDrawableCheckBox) findViewById(R.id.cdcCameraRecord);
        this.mCDCCameraRecord.setOnTouchListener(new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return CameraPlayerActivity.lambda$initView$7(CameraPlayerActivity.this, view, motionEvent);
            }
        });
        this.mCDCCameraRecord.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CameraPlayerActivity.lambda$initView$8(CameraPlayerActivity.this, compoundButton, z);
            }
        });
        findViewById(R.id.tvMore).setOnClickListener(this);
        this.mCallWave = (WaveView) findViewById(R.id.call_wave);
        this.mMSTVVoice = (MultiStateTextView) findViewById(R.id.mstvVoice);
        this.mMSTVVoice.addState(new MultiStateTextView.StateItem(R.string.call_start, R.drawable.call_start_bg, new View.OnClickListener() {
            public final void onClick(View view) {
                CameraPlayerActivity.lambda$initView$9(CameraPlayerActivity.this, view);
            }
        }));
        this.mMSTVVoice.addState(new MultiStateTextView.StateItem(R.string.call_end, R.drawable.call_end_bg, new View.OnClickListener() {
            public final void onClick(View view) {
                CameraPlayerActivity.lambda$initView$10(CameraPlayerActivity.this, view);
            }
        }));
        this.mMSTVVoice.setCurrentState(0);
        this.mCDCToggleAudio = (CenterDrawableCheckBox) findViewById(R.id.cdcToggleAudio);
        this.mCDCToggleAudio.setOnTouchListener(new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return CameraPlayerActivity.lambda$initView$11(CameraPlayerActivity.this, view, motionEvent);
            }
        });
        this.mCDCToggleAudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CameraPlayerActivity.lambda$initView$12(CameraPlayerActivity.this, compoundButton, z);
            }
        });
        this.mVideoView.setVideoViewScaleListener($$Lambda$CameraPlayerActivity$iMk9rdPZsqdzlbe65a3QXPgkPXY.INSTANCE);
        this.mCdcToggleSleep = (MultiStateView) findViewById(R.id.cdcToggleSleep);
        this.mCdcToggleSleep.addState(new MultiStateView.StateItem(R.drawable.camera_icon_sleep_new, new View.OnClickListener() {
            public final void onClick(View view) {
                CameraPlayerActivity.lambda$initView$14(CameraPlayerActivity.this, view);
            }
        }));
        this.mCdcToggleSleep.addState(new MultiStateView.StateItem(R.drawable.camera_icon_sleep_stop_new, new View.OnClickListener() {
            public final void onClick(View view) {
                CameraPlayerActivity.lambda$initView$15(CameraPlayerActivity.this, view);
            }
        }));
        if (!isShowFace() || CoreApi.a().D()) {
            this.mCdcToggleSleep.setVisibility(8);
            findViewById(R.id.camera_player_space_holder_1).setVisibility(8);
            findViewById(R.id.camera_player_space_holder_2).setVisibility(0);
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.mCDCToggleAudio.getLayoutParams();
            layoutParams3.leftMargin = Util.a((Context) this, 26.0f);
            layoutParams3.rightMargin = 0;
            this.mCDCToggleAudio.setLayoutParams(layoutParams3);
        } else {
            this.mCdcToggleSleep.setVisibility(0);
            findViewById(R.id.camera_player_space_holder_1).setVisibility(0);
            findViewById(R.id.camera_player_space_holder_2).setVisibility(8);
            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) this.mCDCToggleAudio.getLayoutParams();
            layoutParams4.rightMargin = Util.a((Context) this, 26.0f);
            layoutParams4.leftMargin = 0;
            this.mCDCToggleAudio.setLayoutParams(layoutParams4);
        }
        if (this.mCameraDevice != null && this.mCameraDevice.f() != null && isShowFace() && !CoreApi.a().D()) {
            this.mCdcToggleSleep.setCurrentState(this.mCameraDevice.f().c() ^ true ? 1 : 0);
        }
        TextView textView = (TextView) findViewById(R.id.tvMore);
        if (!isShowFace() || CoreApi.a().D()) {
            textView.setText(R.string.item_shortcut);
            textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.home_icon_more), (Drawable) null, (Drawable) null);
        }
    }

    public static /* synthetic */ void lambda$initView$1(CameraPlayerActivity cameraPlayerActivity) {
        if (cameraPlayerActivity.mCameraDevice.f().c()) {
            cameraPlayerActivity.videoClick();
        }
    }

    public static /* synthetic */ boolean lambda$initView$2(CameraPlayerActivity cameraPlayerActivity, View view, MotionEvent motionEvent) {
        if (cameraPlayerActivity.mCameraDevice.f().c()) {
            return false;
        }
        if (motionEvent.getAction() == 1) {
            ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.power_off);
        }
        return true;
    }

    public static /* synthetic */ void lambda$initView$3(CameraPlayerActivity cameraPlayerActivity, CompoundButton compoundButton, boolean z) {
        Event.a(Event.ar);
        cameraPlayerActivity.mCDCToggleAudio.setChecked(z);
        if (cameraPlayerActivity.mCameraPlayer != null) {
            if (z) {
                cameraPlayerActivity.mCameraPlayer.b(false);
            } else {
                cameraPlayerActivity.mCameraPlayer.b(true);
            }
        }
        if (cameraPlayerActivity.mCameraPlayerEx == null) {
            return;
        }
        if (z) {
            cameraPlayerActivity.mCameraPlayerEx.b(false);
        } else {
            cameraPlayerActivity.mCameraPlayerEx.b(true);
        }
    }

    public static /* synthetic */ void lambda$initView$4(CameraPlayerActivity cameraPlayerActivity, CompoundButton compoundButton, boolean z) {
        LogUtil.a(TAG, "mCBVoiceLandscape:" + z);
        if (cameraPlayerActivity.mCameraPlayer != null) {
            if (z) {
                if (!PluginHostApiImpl.instance().checkAndRequestPermisson(cameraPlayerActivity, true, (Callback<List<String>>) null, "android.permission.RECORD_AUDIO")) {
                    ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.audio_permission_denied);
                    cameraPlayerActivity.mCBVoiceLandscape.setChecked(false);
                    return;
                } else if (!cameraPlayerActivity.mCameraPlayer.c() || !cameraPlayerActivity.mTimeLineControlView.isPlayRealTime()) {
                    ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.not_on_live);
                    cameraPlayerActivity.mCBVoiceLandscape.setChecked(false);
                    return;
                } else if (!cameraPlayerActivity.mCameraPlayer.o()) {
                    cameraPlayerActivity.mTimeLineControlView.cameraFuncStatus = TimeLineWithDatePickView.CAMERA_FUNC_STATUS.STATUS_SPEAK;
                    cameraPlayerActivity.mCameraPlayer.k();
                    Event.g();
                    cameraPlayerActivity.mCBMuteLandscape.setChecked(true);
                }
            } else {
                Event.h();
                cameraPlayerActivity.mCameraPlayer.l();
                cameraPlayerActivity.mCBMuteLandscape.setChecked(false);
                cameraPlayerActivity.mTimeLineControlView.cameraFuncStatus = TimeLineWithDatePickView.CAMERA_FUNC_STATUS.STATUS_DEFAULT;
                cameraPlayerActivity.mHandler.removeMessages(2101);
            }
        }
        if (cameraPlayerActivity.mCameraPlayerEx == null) {
            return;
        }
        if (z) {
            if (!PluginHostApiImpl.instance().checkAndRequestPermisson(cameraPlayerActivity, true, (Callback<List<String>>) null, "android.permission.RECORD_AUDIO")) {
                ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.audio_permission_denied);
                cameraPlayerActivity.mCBVoiceLandscape.setChecked(false);
            } else if (!cameraPlayerActivity.mCameraPlayerEx.f() || !cameraPlayerActivity.mTimeLineControlView.isPlayRealTime()) {
                ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.not_on_live);
                cameraPlayerActivity.mCBVoiceLandscape.setChecked(false);
            } else if (!cameraPlayerActivity.mCameraPlayerEx.t()) {
                cameraPlayerActivity.mTimeLineControlView.cameraFuncStatus = TimeLineWithDatePickView.CAMERA_FUNC_STATUS.STATUS_SPEAK;
                cameraPlayerActivity.mCameraPlayerEx.p();
                cameraPlayerActivity.mCBMuteLandscape.setChecked(true);
            }
        } else {
            cameraPlayerActivity.mCameraPlayerEx.q();
            cameraPlayerActivity.mCBMuteLandscape.setChecked(false);
            cameraPlayerActivity.mTimeLineControlView.cameraFuncStatus = TimeLineWithDatePickView.CAMERA_FUNC_STATUS.STATUS_DEFAULT;
            cameraPlayerActivity.mHandler.removeMessages(2101);
        }
    }

    public static /* synthetic */ boolean lambda$initView$5(CameraPlayerActivity cameraPlayerActivity, View view, MotionEvent motionEvent) {
        if (cameraPlayerActivity.mCameraDevice.f().c()) {
            return false;
        }
        if (motionEvent.getAction() == 1) {
            ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.power_off);
        }
        return true;
    }

    public static /* synthetic */ void lambda$initView$6(CameraPlayerActivity cameraPlayerActivity, CompoundButton compoundButton, boolean z) {
        LogUtil.a(TAG, "mCBPlayBack checked:" + z);
        if (!cameraPlayerActivity.mCameraDevice.f().c() || !cameraPlayerActivity.canStepOut(R.string.speaking_block, R.string.recording_block)) {
            return;
        }
        if (!cameraPlayerActivity.sdcardGetSuccess) {
            ToastUtil.a((Context) cameraPlayerActivity.activity(), (int) R.string.sd_card_hint_title);
        } else if (cameraPlayerActivity.mCameraPlayer != null && !cameraPlayerActivity.mCameraPlayer.d()) {
            ToastUtil.a((Context) cameraPlayerActivity.activity(), (int) R.string.no_playback_for_connect);
        } else if (cameraPlayerActivity.mCameraPlayerEx != null && !cameraPlayerActivity.mCameraPlayerEx.g()) {
            ToastUtil.a((Context) cameraPlayerActivity.activity(), (int) R.string.no_playback_for_connect);
        } else if (cameraPlayerActivity.sdcardStatus == 4) {
            ToastUtil.a((Context) cameraPlayerActivity, (CharSequence) cameraPlayerActivity.getString(R.string.camera_storage_sdcard_formating_tips));
        } else if (cameraPlayerActivity.sdcardStatus == 3) {
            cameraPlayerActivity.startActivity(new Intent(cameraPlayerActivity, SDCardStatusActivityNew.class));
        } else if (cameraPlayerActivity.sdcardStatus == 1 || cameraPlayerActivity.sdcardStatus == 5) {
            cameraPlayerActivity.startActivity(new Intent(cameraPlayerActivity, NoMemoryCardActivity.class));
        } else {
            Event.a(Event.ay);
            if (z) {
                cameraPlayerActivity.mDcvDirectionCtrl.startAnimation(AnimationUtils.loadAnimation(cameraPlayerActivity, R.anim.anim_ctrl_view_alpha_1_to_0));
                cameraPlayerActivity.mDcvDirectionCtrl.setVisibility(4);
                cameraPlayerActivity.mTimeLineProtrait.startAnimation(AnimationUtils.loadAnimation(cameraPlayerActivity, R.anim.anim_ctrl_view_in));
                cameraPlayerActivity.mTimeLineProtrait.setVisibility(0);
                return;
            }
            cameraPlayerActivity.mDcvDirectionCtrl.startAnimation(AnimationUtils.loadAnimation(cameraPlayerActivity, R.anim.anim_ctrl_view_alpha_0_to_1));
            cameraPlayerActivity.mDcvDirectionCtrl.setVisibility(0);
            cameraPlayerActivity.mTimeLineProtrait.startAnimation(AnimationUtils.loadAnimation(cameraPlayerActivity, R.anim.anim_ctrl_view_out));
            cameraPlayerActivity.mTimeLineProtrait.setVisibility(8);
            if (cameraPlayerActivity.mCameraDevice == null || !cameraPlayerActivity.mCameraDevice.n()) {
                if (cameraPlayerActivity.mCameraPlayer != null && cameraPlayerActivity.mCameraPlayer.d() && !cameraPlayerActivity.mCameraPlayer.c()) {
                    cameraPlayerActivity.setPlayTime(0);
                    if (cameraPlayerActivity.mTimeUpdateView.getVisibility() == 0) {
                        cameraPlayerActivity.mTimeUpdateView.setVisibility(8);
                    }
                }
            } else if (cameraPlayerActivity.mCameraPlayerEx != null && cameraPlayerActivity.mCameraPlayerEx.g() && !cameraPlayerActivity.mCameraPlayerEx.f()) {
                cameraPlayerActivity.setPlayTime(0);
                if (cameraPlayerActivity.mCameraPlayerEx.u()) {
                    cameraPlayerActivity.mCameraPlayerEx.b(false, (IMISSListener) null);
                } else {
                    cameraPlayerActivity.mCameraPlayerEx.b(true, (IMISSListener) null);
                }
                cameraPlayerActivity.mCameraPlayerEx.a(true, (IMISSListener) null);
                if (cameraPlayerActivity.mTimeUpdateView.getVisibility() == 0) {
                    cameraPlayerActivity.mTimeUpdateView.setVisibility(8);
                }
            }
        }
    }

    public static /* synthetic */ boolean lambda$initView$7(CameraPlayerActivity cameraPlayerActivity, View view, MotionEvent motionEvent) {
        if (cameraPlayerActivity.mCameraDevice.f().c()) {
            return false;
        }
        if (motionEvent.getAction() == 1) {
            ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.power_off);
        }
        return true;
    }

    public static /* synthetic */ void lambda$initView$8(CameraPlayerActivity cameraPlayerActivity, CompoundButton compoundButton, boolean z) {
        LogUtil.a(TAG, "mCDCCameraRecord:" + z);
        Event.a(Event.at);
        if (z) {
            cameraPlayerActivity.mCDCCameraRecord.setBackgroundResource(R.drawable.home_icon_camera_recording_v4);
            cameraPlayerActivity.mLastTime = 0;
            cameraPlayerActivity.mCurrentTime = 0;
            String a2 = FileUtil.a(true, cameraPlayerActivity.mCameraDevice.getDid());
            if (cameraPlayerActivity.mCameraDevice == null || !cameraPlayerActivity.mCameraDevice.n()) {
                if (cameraPlayerActivity.mCameraPlayer != null && !cameraPlayerActivity.mCameraPlayer.d()) {
                    ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.record_not_connect);
                    cameraPlayerActivity.mCDCCameraRecord.setBackgroundResource(R.drawable.home_icon_camera_record_v4);
                    cameraPlayerActivity.mCDCCameraRecord.setChecked(false);
                    return;
                }
            } else if (cameraPlayerActivity.mCameraPlayerEx != null && !cameraPlayerActivity.mCameraPlayerEx.g()) {
                ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.record_not_connect);
                cameraPlayerActivity.mCDCCameraRecord.setBackgroundResource(R.drawable.home_icon_camera_record_v4);
                cameraPlayerActivity.mCDCCameraRecord.setChecked(false);
                return;
            }
            if (!PluginHostApiImpl.instance().checkAndRequestPermisson(cameraPlayerActivity, true, (Callback<List<String>>) null, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.no_write_permission);
                cameraPlayerActivity.mCDCCameraRecord.setBackgroundResource(R.drawable.home_icon_camera_record_v4);
                cameraPlayerActivity.mCDCCameraRecord.setChecked(false);
            } else if (!TextUtils.isEmpty(a2)) {
                if (cameraPlayerActivity.mCameraDevice == null || !cameraPlayerActivity.mCameraDevice.n()) {
                    if (cameraPlayerActivity.mCameraPlayer != null) {
                        cameraPlayerActivity.mCameraPlayer.a(a2, 2);
                    }
                } else if (cameraPlayerActivity.mCameraPlayerEx != null) {
                    cameraPlayerActivity.mCameraPlayerEx.a(a2, 2);
                    cameraPlayerActivity.mCameraPlayerEx.b(true, (IMISSListener) null);
                }
                cameraPlayerActivity.mTVRecordTimer.setText(cameraPlayerActivity.mSimpleDateFormat.format(new Date(0)));
                cameraPlayerActivity.mTVRecordTimer.setVisibility(0);
                LogUtil.a(TAG, "startRecord");
                Event.v();
            } else {
                ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.snip_video_failed);
            }
        } else {
            Event.w();
            cameraPlayerActivity.mCDCCameraRecord.setBackgroundResource(R.drawable.home_icon_camera_record_v4);
            cameraPlayerActivity.doStopRecord();
        }
    }

    public static /* synthetic */ void lambda$initView$9(CameraPlayerActivity cameraPlayerActivity, View view) {
        Event.a(Event.aB);
        if (!PluginHostApiImpl.instance().checkAndRequestPermisson(cameraPlayerActivity, true, (Callback<List<String>>) null, "android.permission.RECORD_AUDIO")) {
            ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.audio_permission_denied);
            return;
        }
        if (cameraPlayerActivity.mCameraPlayer != null) {
            if (!cameraPlayerActivity.mCameraPlayer.d()) {
                ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.call_no_play);
                return;
            } else if (!cameraPlayerActivity.mCameraPlayer.c() || !cameraPlayerActivity.mTimeLineControlView.isPlayRealTime()) {
                ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.not_on_live);
                return;
            } else if (cameraPlayerActivity.mCameraDevice.isReadOnlyShared()) {
                ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.auth_fail);
                return;
            } else {
                if (!cameraPlayerActivity.mCDCToggleAudio.isChecked()) {
                    cameraPlayerActivity.mCDCToggleAudio.setChecked(true);
                }
                if (!cameraPlayerActivity.mCameraPlayer.o()) {
                    Event.a("call");
                    cameraPlayerActivity.mCameraPlayer.k();
                    cameraPlayerActivity.mHandler.sendEmptyMessageDelayed(2101, 500);
                    cameraPlayerActivity.mMSTVVoice.setCurrentState(1);
                    cameraPlayerActivity.mTimeLineControlView.cameraFuncStatus = TimeLineWithDatePickView.CAMERA_FUNC_STATUS.STATUS_SPEAK;
                    cameraPlayerActivity.startCallAnim();
                    Event.g();
                }
            }
        }
        if (cameraPlayerActivity.mCameraPlayerEx == null) {
            return;
        }
        if (!cameraPlayerActivity.mCameraPlayerEx.g()) {
            ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.call_no_play);
        } else if (!cameraPlayerActivity.mCameraPlayerEx.f() || !cameraPlayerActivity.mTimeLineControlView.isPlayRealTime()) {
            ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.not_on_live);
        } else if (cameraPlayerActivity.mCameraDevice.isReadOnlyShared()) {
            ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.auth_fail);
        } else {
            if (!cameraPlayerActivity.mCDCToggleAudio.isChecked()) {
                cameraPlayerActivity.mCDCToggleAudio.setChecked(true);
            }
            if (!cameraPlayerActivity.mCameraPlayerEx.t()) {
                Event.a("call");
                cameraPlayerActivity.mCameraPlayerEx.p();
                cameraPlayerActivity.mHandler.sendEmptyMessageDelayed(2101, 500);
                cameraPlayerActivity.mMSTVVoice.setCurrentState(1);
                cameraPlayerActivity.mTimeLineControlView.cameraFuncStatus = TimeLineWithDatePickView.CAMERA_FUNC_STATUS.STATUS_SPEAK;
                cameraPlayerActivity.startCallAnim();
            }
        }
    }

    public static /* synthetic */ void lambda$initView$10(CameraPlayerActivity cameraPlayerActivity, View view) {
        cameraPlayerActivity.mCDCToggleAudio.setEnabled(true);
        if (cameraPlayerActivity.mCameraPlayer != null) {
            cameraPlayerActivity.mCameraPlayer.l();
            Event.h();
            cameraPlayerActivity.mHandler.removeMessages(2101);
            if (cameraPlayerActivity.mCDCToggleAudio.isChecked()) {
                cameraPlayerActivity.mCDCToggleAudio.setChecked(false);
            }
            cameraPlayerActivity.mMSTVVoice.setCurrentState(0);
            cameraPlayerActivity.mTimeLineControlView.cameraFuncStatus = TimeLineWithDatePickView.CAMERA_FUNC_STATUS.STATUS_DEFAULT;
            ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.stop_voice);
            cameraPlayerActivity.stopCallAnim();
        }
        if (cameraPlayerActivity.mCameraPlayerEx != null) {
            cameraPlayerActivity.mCameraPlayerEx.q();
            cameraPlayerActivity.mHandler.removeMessages(2101);
            if (cameraPlayerActivity.mCDCToggleAudio.isChecked()) {
                cameraPlayerActivity.mCDCToggleAudio.setChecked(false);
            }
            cameraPlayerActivity.mMSTVVoice.setCurrentState(0);
            cameraPlayerActivity.mTimeLineControlView.cameraFuncStatus = TimeLineWithDatePickView.CAMERA_FUNC_STATUS.STATUS_DEFAULT;
            ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.stop_voice);
            cameraPlayerActivity.stopCallAnim();
        }
    }

    public static /* synthetic */ boolean lambda$initView$11(CameraPlayerActivity cameraPlayerActivity, View view, MotionEvent motionEvent) {
        if (cameraPlayerActivity.mCameraDevice.f().c()) {
            return false;
        }
        if (motionEvent.getAction() == 1) {
            ToastUtil.a((Context) cameraPlayerActivity, (int) R.string.power_off);
        }
        return true;
    }

    public static /* synthetic */ void lambda$initView$12(CameraPlayerActivity cameraPlayerActivity, CompoundButton compoundButton, boolean z) {
        cameraPlayerActivity.mCBMuteLandscape.setChecked(z);
        Event.a(Event.ar);
        if (cameraPlayerActivity.mCameraPlayer != null) {
            if (z) {
                cameraPlayerActivity.mCameraPlayer.b(false);
            } else {
                cameraPlayerActivity.mCameraPlayer.b(true);
            }
        }
        if (cameraPlayerActivity.mCameraPlayerEx == null) {
            return;
        }
        if (z) {
            cameraPlayerActivity.mCameraPlayerEx.b(false);
        } else {
            cameraPlayerActivity.mCameraPlayerEx.b(true);
        }
    }

    static /* synthetic */ void lambda$initView$13(int i) {
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

    public static /* synthetic */ void lambda$initView$14(CameraPlayerActivity cameraPlayerActivity, View view) {
        if (cameraPlayerActivity.mCameraDevice.isReadOnlyShared()) {
            Toast.makeText(cameraPlayerActivity, R.string.auth_fail, 0).show();
        } else if (cameraPlayerActivity.mCameraDevice.f().b(CameraPropertyBase.l) && cameraPlayerActivity.mCameraDevice.deviceStat().isOnline && cameraPlayerActivity.mCameraDevice != null && cameraPlayerActivity.mCameraDevice.f() != null) {
            cameraPlayerActivity.mCameraDevice.f().a(CameraPropertyBase.l, false, (Callback<Void>) new Callback<Void>() {
                public void onSuccess(Void voidR) {
                    CameraPlayerActivity.this.mCdcToggleSleep.setCurrentState(1);
                    CameraPlayerActivity.this.mCameraDevice.g().a(CameraPropertyBase.l);
                    SDKLog.b(CameraPlayerActivity.TAG, "set sleep success");
                }

                public void onFailure(int i, String str) {
                    CameraPlayerActivity.this.mCdcToggleSleep.setCurrentState(0);
                    ToastUtil.a(CameraPlayerActivity.this.getContext(), (int) R.string.action_fail);
                    SDKLog.b(CameraPlayerActivity.TAG, "set sleep failed:" + i + " s:" + str);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$initView$15(CameraPlayerActivity cameraPlayerActivity, View view) {
        if (cameraPlayerActivity.mCameraDevice.isReadOnlyShared()) {
            Toast.makeText(cameraPlayerActivity, R.string.auth_fail, 0).show();
        } else if (!cameraPlayerActivity.mCameraDevice.f().b(CameraPropertyBase.l) && cameraPlayerActivity.mCameraDevice.deviceStat().isOnline && cameraPlayerActivity.mCameraDevice != null && cameraPlayerActivity.mCameraDevice.f() != null) {
            cameraPlayerActivity.mCameraDevice.f().a(CameraPropertyBase.l, true, (Callback<Void>) new Callback<Void>() {
                public void onSuccess(Void voidR) {
                    CameraPlayerActivity.this.mCdcToggleSleep.setCurrentState(0);
                    CameraPlayerActivity.this.mCameraDevice.g().a(CameraPropertyBase.l);
                    SDKLog.b(CameraPlayerActivity.TAG, "set sleep success");
                }

                public void onFailure(int i, String str) {
                    CameraPlayerActivity.this.mCdcToggleSleep.setCurrentState(1);
                    ToastUtil.a(CameraPlayerActivity.this.getContext(), (int) R.string.action_fail);
                    SDKLog.b(CameraPlayerActivity.TAG, "set sleep failed:" + i + " s:" + str);
                }
            });
        }
    }

    private boolean isShowFace() {
        AlarmNetUtils.b(!"chuangmi.camera.ipc009".equals(this.mDeviceStat.model) || AlarmNetUtils.a(this.mCameraDevice.p(), BuildConfig.j));
        return AlarmNetUtils.e();
    }

    public boolean canStepOut() {
        return canStepOut(0, 0);
    }

    public boolean canStepOut(int i, int i2) {
        if (this.mCameraPlayer != null) {
            if (this.mCameraPlayer.n() || this.mCameraPlayer.o()) {
                if (i > 0) {
                    ToastUtil.a((Context) this, i, 1);
                } else {
                    ToastUtil.a((Context) this, (int) R.string.speaking_block, 1);
                }
                return false;
            } else if (this.mCameraPlayer.m()) {
                if (i2 > 0) {
                    ToastUtil.a((Context) this, i2, 1);
                } else {
                    ToastUtil.a((Context) this, (int) R.string.recording_block, 1);
                }
                return false;
            }
        }
        if (this.mCameraPlayerEx != null) {
            if (this.mCameraPlayerEx.s() || this.mCameraPlayerEx.t()) {
                if (i > 0) {
                    ToastUtil.a((Context) this, i, 1);
                } else {
                    ToastUtil.a((Context) this, (int) R.string.speaking_block, 1);
                }
                return false;
            } else if (this.mCameraPlayerEx.r()) {
                if (i2 > 0) {
                    ToastUtil.a((Context) this, i2, 1);
                } else {
                    ToastUtil.a((Context) this, (int) R.string.recording_block, 1);
                }
                return false;
            }
        }
        return true;
    }

    public void setPlayTime(long j) {
        setPlayTime(j, true);
    }

    public void setPlayTime(long j, boolean z) {
        int i;
        int i2;
        int i3;
        LogUtil.a(TAG, "setPlayTime setPlayTime setPlayTime");
        TimeItem a2 = this.mCameraDevice.c().a(j);
        if (this.mCameraDevice.d() != null && this.mCameraDevice.n()) {
            a2 = this.mCameraDevice.d().a(j);
        }
        boolean z2 = true;
        if (a2 != null) {
            LogUtil.a(TAG, "last set time before " + TimeUtils.b(j));
            i2 = (int) (a2.f8098a / 1000);
            i = a2.f8098a < j ? ((int) (j - a2.f8098a)) / 1000 : 0;
            StringBuilder sb = new StringBuilder();
            sb.append("last set time after  ");
            long j2 = (long) (i * 1000);
            sb.append(TimeUtils.b(a2.f8098a + j2));
            LogUtil.a(TAG, sb.toString());
            this.mIsSetPlayTime = true;
            this.mSelectTime = (long) (i2 + i);
            this.mLastSetStart = i2;
            this.mTimeLineControlView.updatePlayTime(a2.f8098a + j2, false);
        } else {
            LogUtil.a(TAG, "last set time alive");
            runMainThread(new Runnable() {
                public void run() {
                    CameraPlayerActivity.this.mCDCToggleAudio.setEnabled(true);
                }
            });
            this.mHandler.removeMessages(2);
            this.mNeedCheckAlive = true;
            this.mIsSetPlayTime = false;
            this.mTimeLineControlView.updatePlayTime(System.currentTimeMillis(), true);
            if (this.mCameraPlayer != null && this.mCameraPlayer.c() && !this.mCameraPlayer.h()) {
                z2 = false;
            }
            if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.f() || this.mCameraPlayerEx.k()) {
                i3 = 0;
            } else {
                i3 = 0;
                z2 = false;
            }
            i2 = 0;
        }
        if (!this.mIsUserPause && z2) {
            showLoading("");
        }
        this.mLastSetPlayTime = System.currentTimeMillis();
        if (this.mCameraPlayer != null && (!this.mCameraPlayer.h() || !z)) {
            this.mCameraPlayer.b(i2, i, 0);
        }
        if (this.mCameraPlayerEx != null && (!this.mCameraPlayerEx.k() || !z)) {
            this.mCameraPlayerEx.a(i2, i, 0);
        }
        this.mHandler.removeMessages(2);
        this.mHandler.sendEmptyMessageDelayed(2, 3000);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a(TAG, "newConfig.orientation:" + configuration.orientation);
        setWindow(configuration);
        if (configuration.orientation != 1) {
            if (this.mVideoViewFrame != null) {
                ViewGroup.LayoutParams layoutParams = this.mVideoViewFrame.getLayoutParams();
                layoutParams.height = -1;
                layoutParams.width = -1;
            }
            this.mTimeLineControlView.setBackgroundColor(getResources().getColor(R.color.land_timeline_bg));
            this.flTitleBar.setVisibility(8);
            this.mLLBottomTools.setVisibility(8);
            this.mLLVideoViewBottomCtrl.setVisibility(8);
            this.mRLDirectionCtl.setVisibility(8);
            this.mRLParent.setBackgroundColor(-16777216);
            this.mTimeLineProtrait.removeAllViews();
            this.mTimeLineLand.removeAllViews();
            if (this.mCameraDevice.c().i == null || !(this.mCameraDevice.c().i.e == 0 || this.mCameraDevice.c().i.e == 2)) {
                this.mTimeLineLand.setVisibility(8);
            } else {
                this.mTimeLineLand.addView(this.mTimeLineControlView);
                this.mTimeLineLand.setVisibility(0);
            }
            this.mDcvDirectionCtrlLandscape.setVisibility(0);
            this.mLLRightCtrlLandscape.setVisibility(0);
            this.mFullScreen = true;
            this.mVideoView.setIsFull(true);
            this.mAVCamera.setVisibility(8);
            if (this.mCameraPlayer != null && this.mCameraPlayer.c()) {
                this.mDcvDirectionCtrlLandscape.setVisibility(0);
            } else if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.f()) {
                this.mDcvDirectionCtrlLandscape.setVisibility(8);
            } else {
                this.mDcvDirectionCtrlLandscape.setVisibility(0);
            }
            if (this.mMSTVVoice.getCurrentState() == 1) {
                this.mCBVoiceLandscape.setChecked(true);
                this.mTimeLineControlView.cameraFuncStatus = TimeLineWithDatePickView.CAMERA_FUNC_STATUS.STATUS_SPEAK;
                return;
            }
            this.mCBVoiceLandscape.setChecked(false);
            this.mTimeLineControlView.cameraFuncStatus = TimeLineWithDatePickView.CAMERA_FUNC_STATUS.STATUS_DEFAULT;
            return;
        }
        try {
            if (this.mVideoViewFrame != null) {
                ViewGroup.LayoutParams layoutParams2 = this.mVideoViewFrame.getLayoutParams();
                layoutParams2.height = (int) (AppConfig.d * 350.0f);
                String str = Build.MODEL;
                if (!TextUtils.isEmpty(str) && (str.equals("MIX") || str.equals("MIX 2") || str.equals("MIX 2S") || str.equals("MI 8") || str.equals("MI 8 SE"))) {
                    layoutParams2.height = (int) (AppConfig.d * 400.0f);
                }
                layoutParams2.width = -1;
            }
            this.mTimeLineControlView.setBackgroundColor(getResources().getColor(R.color.camera_white_bg));
            this.flTitleBar.setVisibility(0);
            this.mLLBottomTools.setVisibility(0);
            this.mLLVideoViewBottomCtrl.setVisibility(0);
            this.mTimeLineLand.removeAllViews();
            this.mTimeLineProtrait.removeAllViews();
            this.mTimeLineProtrait.addView(this.mTimeLineControlView);
            this.mRLDirectionCtl.setVisibility(0);
            this.mRLParent.setBackgroundColor(-1);
            this.mDcvDirectionCtrlLandscape.setVisibility(8);
            this.mLLRightCtrlLandscape.setVisibility(8);
            this.mLandLayout.setVisibility(8);
            this.mFullScreen = false;
            this.mVideoView.setIsFull(false);
            if (this.mCBVoiceLandscape.isChecked()) {
                this.mMSTVVoice.setCurrentState(1);
                startCallAnim();
                return;
            }
            this.mMSTVVoice.setCurrentState(0);
            stopCallAnim();
        } catch (Exception e) {
            LogUtil.b(TAG, "onConfigurationChanged:" + e.getLocalizedMessage());
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
                case R.id.ivCameraShot:
                    LogUtil.a(TAG, "ivCameraShot click");
                    Event.a(Event.c);
                    Event.a(Event.as);
                    snapShot();
                    return;
                case R.id.ivFullScreen:
                    LogUtil.a(TAG, "ivFullScreen click");
                    setOrientation(true);
                    Event.a(Event.aw);
                    return;
                case R.id.mstvSleep:
                    LogUtil.a(TAG, "R.id.mstvSleep:");
                    return;
                case R.id.mstvVoice:
                    LogUtil.a(TAG, "mstvVoice click");
                    return;
                case R.id.title_bar_more:
                    Event.a(Event.aJ);
                    if (canStepOut()) {
                        Event.a(Event.j);
                        ArrayList arrayList = new ArrayList();
                        IXmPluginHostActivity.IntentMenuItem intentMenuItem = new IXmPluginHostActivity.IntentMenuItem();
                        intentMenuItem.name = getString(R.string.more_camera_setting);
                        intentMenuItem.intent = new Intent(this, MoreCameraSettingActivity.class);
                        intentMenuItem.intent.putExtra("extra_device_did", this.mDid);
                        intentMenuItem.intent.putExtra("is_v4", this.isV4);
                        arrayList.add(intentMenuItem);
                        IXmPluginHostActivity.IntentMenuItem intentMenuItem2 = new IXmPluginHostActivity.IntentMenuItem();
                        intentMenuItem2.name = getString(R.string.more_alarm_setting_v4);
                        if (!AlarmNetUtils.b() || !AlarmNetUtils.e()) {
                            intentMenuItem2.intent = new Intent(this, AlarmSettingActivity.class);
                        } else {
                            intentMenuItem2.intent = new Intent(this, AlarmSettingV2Activity.class);
                        }
                        intentMenuItem2.intent.putExtra("extra_device_did", this.mDid);
                        intentMenuItem2.intent.putExtra("is_v4", this.isV4);
                        arrayList.add(intentMenuItem2);
                        IXmPluginHostActivity.IntentMenuItem intentMenuItem3 = new IXmPluginHostActivity.IntentMenuItem();
                        intentMenuItem3.name = getString(R.string.more_store_setting);
                        intentMenuItem3.intent = new Intent();
                        intentMenuItem3.intent.setClass(this, FileManagerSettingActivity.class);
                        intentMenuItem3.intent.putExtra("extra_device_did", this.mDid);
                        intentMenuItem3.intent.putExtra("is_v4", this.isV4);
                        arrayList.add(intentMenuItem3);
                        if (isShowFace() && !CoreApi.a().D()) {
                            IXmPluginHostActivity.IntentMenuItem intentMenuItem4 = new IXmPluginHostActivity.IntentMenuItem();
                            intentMenuItem4.name = getString(R.string.album);
                            intentMenuItem4.intent = new Intent(this, AlbumActivity.class);
                            intentMenuItem4.intent.putExtra("extra_device_did", this.mDid);
                            arrayList.add(intentMenuItem4);
                        }
                        if (isShowFace() && !CoreApi.a().D() && !this.mCameraDevice.isShared()) {
                            IXmPluginHostActivity.IntentMenuItem intentMenuItem5 = new IXmPluginHostActivity.IntentMenuItem();
                            intentMenuItem5.name = getString(R.string.face_ai_setting);
                            intentMenuItem5.intent = new Intent(this, AlarmAISwitchActivity.class);
                            intentMenuItem5.intent.putExtra("extra_device_did", this.mDid);
                            if (!this.mCameraDevice.e().f()) {
                                intentMenuItem5.goBuyVip = true;
                            }
                            arrayList.add(intentMenuItem5);
                        }
                        Intent intent = new Intent();
                        intent.putExtra(DeviceMoreActivity.ARGS_SECURITY_SETTING_ENABLE, true);
                        intent.putExtra(DeviceMoreNewActivity.AUTO_DISMISS, false);
                        if (!this.mCameraDevice.isShared() && AlarmNetUtils.b() && AlarmNetUtils.c() && AlarmNetUtils.e()) {
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
                                if (!"cn".equalsIgnoreCase(ServerUtil.b()) || TextUtils.isEmpty(v4LocalLicense.mPlan)) {
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
                                    if ("cn".equalsIgnoreCase(ServerUtil.b())) {
                                        bitmap = BitmapUtils.a(bitmap, CameraPlayerActivity.this);
                                    }
                                    String a2 = FileUtil.a(false, CameraPlayerActivity.this.mCameraDevice.getDid());
                                    if (a2 != null) {
                                        try {
                                            FileOutputStream fileOutputStream = new FileOutputStream(a2);
                                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                                            fileOutputStream.close();
                                            bitmap.recycle();
                                            CameraPlayerActivity.this.openShareMediaActivity(CameraPlayerActivity.this.mDeviceStat.name, "", a2);
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
                    LogUtil.a(TAG, "tvAlarm click");
                    Event.n();
                    Event.a(Event.az);
                    if (!this.mCameraDevice.f().c()) {
                        ToastUtil.a((Context) this, (int) R.string.power_off);
                        return;
                    } else if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        if (this.mCameraDevice.e().j() && !this.mCameraDevice.isReadOnlyShared()) {
                            Intent intent3 = new Intent(this, AlarmGuideActivity.class);
                            if (isShowFace()) {
                                intent3.putExtra("useNewAlarmVideo", true);
                            }
                            startActivity(intent3);
                            return;
                        } else if (isShowFace()) {
                            startActivity(new Intent(this, AlarmVideoActivity.class));
                            return;
                        } else {
                            startActivity(new Intent(this, AlarmActivity.class));
                            return;
                        }
                    } else {
                        return;
                    }
                case R.id.tvAlbum:
                case R.id.tvAlbum2:
                    Event.a(Event.aI);
                    LogUtil.a(TAG, "R.id.tvAlbum");
                    if (canStepOut()) {
                        startActivity(new Intent(this, AlbumActivity.class));
                        return;
                    }
                    return;
                case R.id.tvCalibration:
                    startCalibration();
                    return;
                case R.id.tvCancel:
                    if (this.moreDialog != null) {
                        this.moreDialog.dismiss();
                        return;
                    }
                    return;
                case R.id.tvCloudVideo:
                    Event.a(Event.aC);
                    if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        if (!this.mCameraDevice.f().c()) {
                            ToastUtil.a((Context) this, (int) R.string.power_off);
                            return;
                        } else if (AlarmNetUtils.e() && AlarmNetUtils.c()) {
                            if (!this.mCameraDevice.isShared() || this.mCameraDevice.e().f()) {
                                if (this.mCameraDevice.e().f() || this.mCameraDevice.e().e()) {
                                    FrameManager.b().k().openCloudVideoListActivity(this, this.mCameraDevice.getDid(), this.mCameraDevice.getName());
                                } else if (activity() != null) {
                                    CloudVideoNetUtils.getInstance().openCloudVideoBuyPage(this, this.mCameraDevice.getDid());
                                }
                                this.mCameraDevice.e().j(true);
                                this.flCloudVideoTips.setVisibility(8);
                                return;
                            }
                            ToastUtil.a((Context) this, (int) R.string.cloud_share_hint);
                            return;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                case R.id.tvExitFullScreen:
                    setOrientation(false);
                    return;
                case R.id.tvMore:
                    LogUtil.a(TAG, "tvMore click");
                    Event.a(Event.b);
                    Event.a(Event.aE);
                    if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        if (!isShowFace() || CoreApi.a().D()) {
                            Event.a(Event.aE);
                            popupMoreDialog();
                            return;
                        } else if (!this.mCameraDevice.f().c()) {
                            ToastUtil.a((Context) this, (int) R.string.power_off);
                            return;
                        } else {
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
                        }
                    } else {
                        return;
                    }
                case R.id.tvMotionTrack:
                    LogUtil.a(TAG, "R.id.tvMotionTrack");
                    return;
                case R.id.tvPIP:
                    LogUtil.a(TAG, "tvPIP click");
                    if (!this.mCameraDevice.f().c()) {
                        ToastUtil.a((Context) this, (int) R.string.power_off);
                        return;
                    } else if (canStepOut(R.string.speaking_block, R.string.recording_block)) {
                        if (XmPluginHostApi.instance().getApiLevel() < 57) {
                            LogUtil.a(TAG, "pip not 36");
                            ToastUtil.a((Context) this, (CharSequence) getResources().getString(R.string.api_tip_title));
                            return;
                        }
                        Event.a(Event.m);
                        Event.a(Event.aH);
                        if (com.Utils.Util.a((Context) this)) {
                            XmPluginHostApi.instance().openCameraFloatingWindow(this.mCameraDevice.getDid());
                            if (this.moreDialog != null) {
                                this.moreDialog.dismiss();
                            }
                            finish();
                            return;
                        }
                        ToastUtil.a((Context) this, (CharSequence) getResources().getString(R.string.float_tip));
                        return;
                    } else {
                        return;
                    }
                case R.id.tvStorage:
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
                            startActivity(new Intent(this, SDCardActivity.class));
                            return;
                        }
                    } else {
                        return;
                    }
                case R.id.tvsResolution:
                    LogUtil.a(TAG, "tvsResolution click");
                    if (!this.mCameraDevice.f().c()) {
                        ToastUtil.a((Context) this, (int) R.string.power_off);
                        return;
                    } else if (this.mCameraPlayer != null && !this.mCameraPlayer.c()) {
                        toggleSpeed();
                        return;
                    } else if (this.mCameraPlayerEx == null || this.mCameraPlayerEx.f()) {
                        toggleResolution();
                        return;
                    } else {
                        toggleSpeed();
                        return;
                    }
                default:
                    return;
            }
        }
    }

    private void startCalibration() {
        Event.a(Event.f);
        Event.a(Event.aG);
        LogUtil.a(TAG, "R.id.tvCalibration");
        if (this.mCameraPlayer == null || !this.mCameraPlayer.i()) {
            if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.l()) {
                ToastUtil.a((Context) this, (int) R.string.not_connect_check);
            } else if (this.mCameraDevice.isReadOnlyShared()) {
                ToastUtil.a((Context) this, (int) R.string.auth_fail);
            } else if (!this.mCameraDevice.f().c()) {
                ToastUtil.a((Context) this, (int) R.string.power_off);
            } else {
                ToastUtil.a((Context) this, (int) R.string.calibrating);
                this.mCameraPlayerEx.b(5, (IMISSListener) null);
            }
        } else if (this.mCameraDevice.isReadOnlyShared()) {
            ToastUtil.a((Context) this, (int) R.string.auth_fail);
        } else if (!this.mCameraDevice.f().c()) {
            ToastUtil.a((Context) this, (int) R.string.power_off);
        } else {
            ToastUtil.a((Context) this, (int) R.string.calibrating);
            this.mCameraPlayer.c(5);
        }
    }

    /* access modifiers changed from: protected */
    public void doStopRecord() {
        this.mLastTime = 0;
        this.mCurrentTime = 0;
        if (this.mCameraDevice == null || !this.mCameraDevice.n()) {
            if (this.mCameraPlayer != null && this.mCameraPlayer.m()) {
                this.mCameraPlayer.a((XmMp4Record.IRecordListener) new XmMp4Record.IRecordListener() {
                    public void onSuccess(String str) {
                        CameraPlayerActivity.this.mTVRecordTimer.setVisibility(8);
                        CameraPlayerActivity.this.onVideoRecorded(str);
                        if (CameraPlayerActivity.this.mCDCCameraRecord.isChecked()) {
                            CameraPlayerActivity.this.mCDCCameraRecord.setChecked(false);
                        }
                    }

                    public void onFailed(int i, String str) {
                        CameraPlayerActivity.this.mTVRecordTimer.setVisibility(8);
                        if (CameraPlayerActivity.this.mCDCCameraRecord.isChecked()) {
                            CameraPlayerActivity.this.mCDCCameraRecord.setChecked(false);
                        }
                        if (i == -2) {
                            ToastUtil.a((Context) CameraPlayerActivity.this, (int) R.string.snip_video_failed_time_mini);
                        } else {
                            ToastUtil.a((Context) CameraPlayerActivity.this, (int) R.string.snip_video_failed);
                        }
                    }
                });
            } else if (this.mCDCCameraRecord.isChecked()) {
                this.mCDCCameraRecord.setChecked(false);
            }
        } else if (this.mCameraPlayerEx != null && this.mCameraPlayerEx.r()) {
            if (this.mCameraPlayerEx.u()) {
                this.mCameraPlayerEx.b(false, (IMISSListener) null);
            }
            this.mCameraPlayerEx.a((XmMp4Record.IRecordListener) new XmMp4Record.IRecordListener() {
                public void onSuccess(String str) {
                    CameraPlayerActivity.this.mTVRecordTimer.setVisibility(8);
                    CameraPlayerActivity.this.onVideoRecorded(str);
                    if (CameraPlayerActivity.this.mCDCCameraRecord.isChecked()) {
                        CameraPlayerActivity.this.mCDCCameraRecord.setChecked(false);
                    }
                }

                public void onFailed(int i, String str) {
                    CameraPlayerActivity.this.mTVRecordTimer.setVisibility(8);
                    if (CameraPlayerActivity.this.mCDCCameraRecord.isChecked()) {
                        CameraPlayerActivity.this.mCDCCameraRecord.setChecked(false);
                    }
                    if (i == -2) {
                        ToastUtil.a((Context) CameraPlayerActivity.this, (int) R.string.snip_video_failed_time_mini);
                    } else {
                        ToastUtil.a((Context) CameraPlayerActivity.this, (int) R.string.snip_video_failed);
                    }
                }
            });
        } else if (this.mCDCCameraRecord.isChecked()) {
            this.mCDCCameraRecord.setChecked(false);
        }
    }

    /* access modifiers changed from: protected */
    public void doStopCall() {
        if (this.mCameraPlayer != null && this.mCameraPlayer.o()) {
            this.mCameraPlayer.l();
            this.mHandler.removeMessages(2101);
        }
        if (this.mCameraPlayerEx != null && this.mCameraPlayerEx.t()) {
            this.mCameraPlayerEx.q();
            this.mHandler.removeMessages(2101);
        }
        if (this.mMSTVVoice.getCurrentState() == 1) {
            this.mMSTVVoice.setCurrentState(0);
            this.mCallWave.setVisibility(8);
            this.mCallWave.stopAnimator();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mDcvDirectionCtrl, "translationY", new float[]{0.0f});
            ofFloat.setDuration(200);
            ofFloat.start();
        }
        if (this.mCBVoiceLandscape.isChecked()) {
            this.mCBVoiceLandscape.setChecked(false);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1220) {
            if (this.mDeviceStat != null) {
                boolean isUsrExpPlanEnabled = XmPluginHostApi.instance().isUsrExpPlanEnabled(this.mDeviceStat.did);
                Event.ch = isUsrExpPlanEnabled;
                this.mCameraDevice.a(isUsrExpPlanEnabled, (Callback<Void>) null);
            }
            if (i2 == -1 && intent != null) {
                String stringExtra = intent.getStringExtra("result_data");
                if (!TextUtils.isEmpty(stringExtra) && stringExtra.equals(DeviceMoreActivity.ARGS_RESULT_REMOVE_LICENSE)) {
                    this.mCameraDevice.e().g(true);
                    finish();
                }
                if (intent.getBooleanExtra("start_calibration", false)) {
                    startCalibration();
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
    public void setResolutionText(int i) {
        switch (i) {
            case 1:
                this.mTVSResolution.setText(R.string.quality_low);
                return;
            case 2:
            case 3:
                this.mTVSResolution.setText(R.string.quality_fhd);
                return;
            default:
                this.mTVSResolution.setText(R.string.quality_auto);
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void setOrientation(boolean z) {
        if (!z) {
            setRequestedOrientation(1);
            Event.y();
        } else if (!this.mCameraDevice.f().c()) {
            ToastUtil.a((Context) this, (int) R.string.power_off);
        } else {
            setRequestedOrientation(0);
            Event.x();
        }
    }

    private void popupMoreDialog() {
        this.moreDialog = new MoreDialog(this, R.style.popupDialog, this.mCameraDevice, this, AlarmNetUtils.b() && AlarmNetUtils.c() && AlarmNetUtils.e());
        this.moreDialog.moreDialogListener = new MoreDialog.IMoreDialogListener() {
            public void onPowerStateChanged(boolean z) {
                LogUtil.a(CameraPlayerActivity.TAG, "isPowerOn:" + z);
                if (z) {
                    CameraPlayerActivity.this.mFrameRate.setVisibility(0);
                } else {
                    CameraPlayerActivity.this.mFrameRate.setVisibility(8);
                }
                if (!z && CameraPlayerActivity.this.mCBPlayBack.isChecked()) {
                    CameraPlayerActivity.this.mCBPlayBack.performClick();
                }
            }
        };
        this.moreDialog.show();
        this.moreDialog.setCancelable(true);
    }

    public void onResume() {
        super.onResume();
        Event.q();
        TimeUtils.b();
        isCloudVideoUser();
        this.mCameraDevice.updateDeviceStatus();
        IntentFilter intentFilter = new IntentFilter();
        if (this.mCameraDevice == null || !this.mCameraDevice.n()) {
            intentFilter.addAction(this.mCameraDevice.c().f());
        } else {
            intentFilter.addAction(this.mCameraDevice.d().f());
        }
        this.mLocalBroadcastManager.registerReceiver(this.mBroadcastReceiver, intentFilter);
        if (this.mCameraDevice == null || !this.mCameraDevice.n()) {
            this.mCameraDevice.c().j();
            this.mCameraDevice.c().a(40000);
            this.mTimeLineControlView.setTimeItems(this.mCameraDevice.c().i());
        } else {
            this.mCameraDevice.d().j();
            this.mCameraDevice.d().a(40000);
            this.mTimeLineControlView.setTimeItems(this.mCameraDevice.d().i());
        }
        if (this.mCameraDevice.isOwner()) {
            this.mCameraDevice.a(getContext(), (Callback<FirmwareUpdateResult>) new Callback<FirmwareUpdateResult>() {
                public void onFailure(int i, String str) {
                }

                public void onSuccess(FirmwareUpdateResult firmwareUpdateResult) {
                    Message.obtain(CameraPlayerActivity.this.mHandler, 1, firmwareUpdateResult).sendToTarget();
                }
            });
        }
        ((TextView) findViewById(R.id.title_bar_title)).setText(this.mCameraDevice.getName());
        if (!this.mNeedLicense || this.mCameraDevice.isShared()) {
            this.mHandler.sendEmptyMessage(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
            if (this.mNeedPincode) {
                this.mHandler.removeMessages(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
                this.mHandler.sendEmptyMessageDelayed(AMapException.CODE_AMAP_NEARBY_INVALID_USERID, 1000);
            } else {
                this.mHandler.removeMessages(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
                this.mHandler.sendEmptyMessage(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
            }
        }
        if (this.mTimeUpdateView.getVisibility() == 0) {
            this.mTimeUpdateView.setVisibility(8);
        }
        this.mNeedPincode = false;
        detectSDCard();
    }

    public void onStop() {
        super.onStop();
        this.mHandler.removeMessages(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
        doStopCall();
        doStopRecord();
        if (this.mCameraPlayer != null) {
            this.mIsReal = this.mCameraPlayer.c();
        }
        if (this.mCameraPlayerEx != null) {
            this.mIsReal = this.mCameraPlayerEx.f();
        }
    }

    public void onPause() {
        super.onPause();
        this.mLocalBroadcastManager.unregisterReceiver(this.mBroadcastReceiver);
        this.mHandler.removeMessages(2);
        dismissSnapshotPopupRunnable(0);
    }

    /* access modifiers changed from: protected */
    public void resumeCamera() {
        this.mPauseView.setVisibility(8);
        boolean isPlayRealTime = this.mTimeLineControlView.isPlayRealTime();
        if (NetworkUtil.c(this)) {
            this.mAllowMobileNetwork = true;
        }
        if (!this.isStartPlay) {
            if (this.mCameraPlayer != null) {
                this.mCameraPlayer.f();
            }
            if (this.mCameraPlayerEx != null) {
                this.mCameraPlayerEx.a((IMISSListener) new IMISSListener() {
                    public void onProgress(int i) {
                    }

                    public void onSuccess(String str, Object obj) {
                        if (CameraPlayerActivity.this.mCameraPlayerEx.u()) {
                            CameraPlayerActivity.this.toggleRemoteAV(true, false);
                        } else {
                            CameraPlayerActivity.this.toggleRemoteAV(true, true);
                        }
                        boolean unused = CameraPlayerActivity.this.mVideoPlaying = true;
                    }

                    public void onFailed(int i, String str) {
                        boolean unused = CameraPlayerActivity.this.mVideoPlaying = false;
                    }
                });
            }
        } else if (!this.mIsReal || !isPlayRealTime) {
            setPlayTime(this.mTimeLineControlView.getSelectTime(), false);
        } else {
            setPlayTime(0, false);
        }
        refreshUI();
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 1:
                if (!((FirmwareUpdateResult) message.obj).isLatest) {
                    this.mNewFirmView.setVisibility(0);
                    return;
                }
                return;
            case 2:
                if (this.mCameraPlayer == null && this.mCameraPlayerEx == null) {
                    this.mHandler.removeMessages(2);
                    this.mHandler.sendEmptyMessageDelayed(2, 2000);
                    return;
                }
                long currentTimeMillis = System.currentTimeMillis() / 1000;
                if (this.mCameraPlayer != null) {
                    currentTimeMillis = this.mCameraPlayer.b();
                }
                if (this.mCameraPlayerEx != null) {
                    currentTimeMillis = this.mCameraPlayerEx.e();
                }
                if (this.mLastPlayTime == currentTimeMillis) {
                    this.mHandler.removeMessages(2);
                    this.mHandler.sendEmptyMessageDelayed(2, 2000);
                    return;
                }
                if (this.mNeedCheckAlive) {
                    if ((this.mCameraPlayer != null && !this.mCameraPlayer.c()) || System.currentTimeMillis() - this.mLastSetPlayTime > 10000) {
                        this.mHandler.removeMessages(2);
                        this.mHandler.sendEmptyMessageDelayed(2, 2000);
                        return;
                    } else if ((this.mCameraPlayerEx == null || this.mCameraPlayerEx.f()) && System.currentTimeMillis() - this.mLastSetPlayTime <= 10000) {
                        hideLoading();
                        this.mNeedCheckAlive = false;
                    } else {
                        this.mHandler.removeMessages(2);
                        this.mHandler.sendEmptyMessageDelayed(2, 2000);
                        return;
                    }
                }
                if (!this.mTimeLineControlView.getIsPress()) {
                    if (!this.mIsSetPlayTime) {
                        if (this.mCameraPlayer != null) {
                            this.mTimeLineControlView.updatePlayTime(currentTimeMillis * 1000, this.mCameraPlayer.c());
                        }
                        if (this.mCameraPlayerEx != null) {
                            this.mTimeLineControlView.updatePlayTime(1000 * currentTimeMillis, this.mCameraPlayerEx.f());
                        }
                    } else if (Math.abs(this.mSelectTime - currentTimeMillis) < 10 || System.currentTimeMillis() - this.mLastSetPlayTime > 10000) {
                        this.mIsSetPlayTime = false;
                        if (this.mCameraPlayer != null) {
                            this.mTimeLineControlView.updatePlayTime(currentTimeMillis * 1000, this.mCameraPlayer.c());
                        }
                        if (this.mCameraPlayerEx != null) {
                            this.mTimeLineControlView.updatePlayTime(currentTimeMillis * 1000, this.mCameraPlayerEx.f());
                        }
                        hideLoading();
                        LogUtil.a(TAG, "update " + Math.abs(this.mSelectTime - currentTimeMillis) + "  " + ((System.currentTimeMillis() - this.mLastSetPlayTime) / 1000));
                    }
                }
                this.mLastPlayTime = currentTimeMillis;
                this.mHandler.removeMessages(2);
                this.mHandler.sendEmptyMessageDelayed(2, 2000);
                return;
            case 1002:
                this.mHintView.setVisibility(8);
                return;
            case AMapException.CODE_AMAP_NEARBY_INVALID_USERID:
                hidError();
                if (!NetworkUtil.c(this) || this.mAllowMobileNetwork) {
                    showLoading(getString(R.string.camera_play_initial_0));
                }
                startPlay();
                return;
            case 2101:
                if (this.mCameraPlayer != null && this.mCameraPlayer.o()) {
                    this.mHandler.sendEmptyMessageDelayed(2101, 500);
                    this.mCallWave.setVolume(this.mCameraPlayer.s());
                }
                if (this.mCameraPlayerEx != null && this.mCameraPlayerEx.t()) {
                    this.mHandler.sendEmptyMessageDelayed(2101, 500);
                    this.mCallWave.setVolume(this.mCameraPlayerEx.x());
                    return;
                }
                return;
            case Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS:
                this.mCameraDevice.b(getContext(), (Callback<Integer[]>) new Callback<Integer[]>() {
                    public void onSuccess(Integer[] numArr) {
                        if (numArr != null) {
                            if (numArr[0].intValue() == 0) {
                                CameraPlayerActivity.this.hideUpdateIng(true);
                                CameraPlayerActivity.this.mHandler.removeMessages(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
                            } else if (numArr[1].intValue() < 100) {
                                CameraPlayerActivity.this.showUpdateIng(numArr[1].intValue());
                                CameraPlayerActivity.this.mHandler.sendEmptyMessageDelayed(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS, com.xiaomi.smarthome.download.Constants.x);
                            } else {
                                CameraPlayerActivity.this.hideUpdateIng(true);
                                CameraPlayerActivity.this.mHandler.removeMessages(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
                                CameraPlayerActivity.this.startPlay();
                            }
                        }
                    }

                    public void onFailure(int i, String str) {
                        CameraPlayerActivity.this.hideUpdateIng(false);
                        CameraPlayerActivity.this.mHandler.removeMessages(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
                    }
                });
                return;
            case 4000:
                this.mNeedSpeed = false;
                this.mTimeUpdateView.setVisibility(8);
                return;
            default:
                return;
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
        if (this.mCameraDevice != null && this.mCameraDevice.n()) {
            if (this.mCameraPlayer != null) {
                this.mCameraPlayer.a(true);
                this.mCameraPlayer = null;
            }
            if (!TextUtils.isEmpty(this.mCameraDevice.getModel()) && !TextUtils.isEmpty(this.mCameraDevice.getDid())) {
                if (this.mCameraDevice.f().c()) {
                    if (this.mPauseView.getVisibility() == 0) {
                        this.mPauseView.setVisibility(8);
                    }
                    if (this.mCameraPlayerEx == null) {
                        this.mCameraPlayerEx = new CameraPlayerEx(this, this.mCameraDevice, this, this.mVideoView);
                        this.mCameraPlayerEx.a((CameraPlayerEx.IRecordTimeListener) new CameraPlayerEx.IRecordTimeListener() {
                            public void upDateTime(int i) {
                                CameraPlayerActivity.this.updateTime(i);
                            }
                        });
                    }
                    if (this.mCameraPlayerEx != null) {
                        this.mCameraPlayerEx.c = this.resolutionChangedExListener;
                    }
                    if (this.mCameraPlayerEx != null) {
                        this.mCameraPlayerEx.d = this.mVideoLiveExListener;
                    }
                    if (!NetworkUtil.c(this) || this.mAllowMobileNetwork) {
                        if (this.mCameraPlayerEx != null) {
                            this.mCameraPlayerEx.a((IMISSListener) new IMISSListener() {
                                public void onProgress(int i) {
                                }

                                public void onSuccess(String str, Object obj) {
                                    if (CameraPlayerActivity.this.mCameraPlayerEx.u()) {
                                        CameraPlayerActivity.this.toggleRemoteAV(true, false);
                                    } else {
                                        CameraPlayerActivity.this.toggleRemoteAV(true, true);
                                    }
                                    boolean unused = CameraPlayerActivity.this.mVideoPlaying = true;
                                }

                                public void onFailed(int i, String str) {
                                    boolean unused = CameraPlayerActivity.this.mVideoPlaying = false;
                                }
                            });
                        }
                        this.isStartPlay = true;
                    } else {
                        this.mIsUserPause = true;
                        pauseCamera();
                    }
                    this.mHandler.sendEmptyMessageDelayed(2, 2000);
                } else if (this.mPowerOffView.getVisibility() == 8) {
                    this.mPowerOffView.setVisibility(0);
                }
            }
        } else if (TextUtils.isEmpty(this.mCameraDevice.r()) || TextUtils.isEmpty(this.mCameraDevice.w())) {
            updatePwd();
        } else if (this.mCameraDevice.f().c()) {
            if (this.mPauseView.getVisibility() == 0) {
                this.mPauseView.setVisibility(8);
            }
            if (this.mCameraPlayer == null) {
                this.mCameraPlayer = new CameraPlayer(this, this.mCameraDevice, this, this.mVideoView);
                this.mCameraPlayer.a((CameraPlayer.IRecordTimeListener) new CameraPlayer.IRecordTimeListener() {
                    public void upDateTime(int i) {
                        CameraPlayerActivity.this.updateTime(i);
                    }
                });
            }
            if (this.mCameraPlayer != null) {
                this.mCameraPlayer.f7862a = this.resolutionChangedListener;
            }
            if (this.mCameraPlayer != null) {
                this.mCameraPlayer.b = this.mVideoLiveListener;
            }
            if (!NetworkUtil.c(this) || this.mAllowMobileNetwork) {
                if (this.mCameraPlayer != null) {
                    this.mCameraPlayer.f();
                }
                this.isStartPlay = true;
            } else {
                this.mIsUserPause = true;
                pauseCamera();
            }
            this.mHandler.sendEmptyMessageDelayed(2, 2000);
        } else if (this.mPowerOffView.getVisibility() == 8) {
            this.mPowerOffView.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public void refreshUI() {
        if (!this.mCameraDevice.f().c()) {
            if (this.mPowerOffView.getVisibility() == 8) {
                this.mPowerOffView.setVisibility(0);
                this.mPowerOffView.bringToFront();
                if (this.mCameraPlayer != null) {
                    this.mCameraPlayer.g();
                }
                if (this.mCameraPlayerEx != null) {
                    this.mCameraPlayerEx.j();
                }
                if (this.mPauseView.getVisibility() == 0) {
                    this.mPauseView.setVisibility(8);
                }
            }
            if (isShowFace() && !CoreApi.a().D()) {
                this.mCdcToggleSleep.setCurrentState(1);
            }
            if (this.mCameraPlayer != null) {
                if (this.mCameraPlayer.m()) {
                    doStopRecord();
                }
                if (this.mCameraPlayer.o()) {
                    doStopCall();
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
        } else {
            if (this.mPowerOffView.getVisibility() == 0) {
                this.mPowerOffView.setVisibility(8);
                if (this.mCameraPlayer != null) {
                    this.mCameraPlayer.f();
                } else if (this.mCameraPlayerEx != null) {
                    this.mCameraPlayerEx.a((IMISSListener) new IMISSListener() {
                        public void onProgress(int i) {
                        }

                        public void onSuccess(String str, Object obj) {
                            if (CameraPlayerActivity.this.mCameraPlayerEx.u()) {
                                CameraPlayerActivity.this.toggleRemoteAV(true, false);
                            } else {
                                CameraPlayerActivity.this.toggleRemoteAV(true, true);
                            }
                            boolean unused = CameraPlayerActivity.this.mVideoPlaying = true;
                        }

                        public void onFailed(int i, String str) {
                            boolean unused = CameraPlayerActivity.this.mVideoPlaying = false;
                        }
                    });
                } else {
                    startPlay();
                }
            }
            if (isShowFace() && !CoreApi.a().D()) {
                this.mCdcToggleSleep.setCurrentState(0);
            }
        }
        if (this.mCameraDevice != null && this.mCameraDevice.f().g()) {
            this.mFrameRate.setText(getString(R.string.is_move_track));
        }
    }

    public void onDestroy() {
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
        if (this.mCameraDevice != null) {
            clearAllCacheRecording();
            if (this.mCameraDevice.n()) {
                this.mCameraDevice.d().b();
            } else {
                this.mCameraDevice.c().b();
            }
        }
        if (this.mCameraPlayer != null) {
            this.mCameraPlayer.a(this.mShowView);
        }
        if (this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.a(this.mShowView);
        }
        if (this.mNetworkMonitor != null) {
            this.mNetworkMonitor.unregister();
        }
        super.onDestroy();
        Event.b();
    }

    public void onServerCmd(int i, byte[] bArr) {
        String optString;
        if (!isFinishing()) {
            if (i == 61442) {
                try {
                    String str = new String(bArr);
                    JSONObject jSONObject = new JSONObject(str);
                    int optInt = jSONObject.optInt("id", -1);
                    LogUtil.a(TAG, "id " + str);
                    if (optInt == this.mLastSetStart && (optString = jSONObject.optString("status", (String) null)) != null) {
                        if (optString.equals("filenotfound")) {
                            this.mSelectTime = (long) (this.mLastSetStart + 60);
                            LogUtil.a(TAG, " onServerCmd file not find to alive");
                        } else if (optString.equals("readerror")) {
                            long optInt2 = (((long) jSONObject.optInt(LogBuilder.i)) * 1000) + 61000;
                            TimeItem a2 = this.mCameraDevice.c().a(optInt2);
                            if (this.mCameraDevice != null && this.mCameraDevice.n()) {
                                a2 = this.mCameraDevice.d().a(optInt2);
                            }
                            if (a2 != null) {
                                this.mTimeLineControlView.updatePlayTime(a2.f8098a, false);
                            }
                        }
                    }
                } catch (Exception e) {
                    LogUtil.b(TAG, "" + e.toString());
                }
            } else if (i == 61446 || i == 275) {
                String str2 = new String(bArr);
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        final JSONObject jSONObject2 = new JSONObject(str2);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                CameraPlayerActivity.this.parseAngle(jSONObject2);
                            }
                        });
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doResume() {
        this.mPauseView.setVisibility(8);
        if (this.mCameraDevice == null || !this.mCameraDevice.n()) {
            if (this.mCameraPlayer != null && this.mCameraPlayer.i()) {
                if (this.mIsReal) {
                    setPlayTime(0, false);
                } else {
                    setPlayTime(this.mTimeLineControlView.getSelectTime(), false);
                }
            }
        } else if (this.mCameraPlayerEx != null && this.mCameraPlayerEx.l()) {
            if (this.mIsReal) {
                setPlayTime();
            } else {
                setPlayTime(this.mTimeLineControlView.getSelectTime(), false);
            }
        }
    }

    private void videoClick() {
        if (getRequestedOrientation() != 1) {
            if (this.mLandLayout.getVisibility() == 0) {
                if (this.mCameraPlayer != null && this.mCameraPlayer.c()) {
                    this.mDcvDirectionCtrlLandscape.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_direction_view_out));
                    this.mDcvDirectionCtrlLandscape.setVisibility(8);
                } else if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.f()) {
                    this.mDcvDirectionCtrlLandscape.setVisibility(8);
                } else {
                    this.mDcvDirectionCtrlLandscape.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_direction_view_out));
                    this.mDcvDirectionCtrlLandscape.setVisibility(8);
                }
                this.mLLRightCtrlLandscape.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_ctrl_view_x_trans_out));
                this.mLLRightCtrlLandscape.setVisibility(8);
                this.mTimeLineLand.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom));
                this.mTimeLineLand.setVisibility(8);
                this.mHandler.postDelayed(new Runnable() {
                    public final void run() {
                        CameraPlayerActivity.this.mLandLayout.setVisibility(8);
                    }
                }, 300);
                return;
            }
            this.mLandLayout.setVisibility(0);
            if (this.mCameraPlayer != null && this.mCameraPlayer.c()) {
                this.mDcvDirectionCtrlLandscape.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_direction_view_in));
                this.mDcvDirectionCtrlLandscape.setVisibility(0);
            } else if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.f()) {
                this.mDcvDirectionCtrlLandscape.setVisibility(8);
            } else {
                this.mDcvDirectionCtrlLandscape.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_direction_view_in));
                this.mDcvDirectionCtrlLandscape.setVisibility(0);
            }
            this.mLLRightCtrlLandscape.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_ctrl_view_x_trans_in));
            this.mLLRightCtrlLandscape.setVisibility(0);
            this.mTimeLineLand.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom));
            this.mTimeLineLand.setVisibility(0);
        } else if (this.mLLVideoViewBottomCtrl.getTranslationY() > 0.0f) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mLLVideoViewBottomCtrl, "translationY", new float[]{(float) this.mLLVideoViewBottomCtrl.getHeight(), 0.0f});
            ofFloat.setDuration(200);
            ofFloat.start();
        } else {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mLLVideoViewBottomCtrl, "translationY", new float[]{0.0f, (float) this.mLLVideoViewBottomCtrl.getHeight()});
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
        } else if (this.mCameraPlayer != null && this.mCameraPlayer.i()) {
            this.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
                public final void onSnap(Bitmap bitmap) {
                    CameraPlayerActivity.this.runOnUiThread(new Runnable(bitmap) {
                        private final /* synthetic */ Bitmap f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            CameraPlayerActivity.this.onSnapShot(this.f$1);
                        }
                    });
                }
            });
        } else if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.l()) {
            this.isSnapshotting = false;
            ToastUtil.a((Context) this, (int) R.string.snap_failed_paused);
        } else {
            this.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
                public final void onSnap(Bitmap bitmap) {
                    CameraPlayerActivity.this.runOnUiThread(new Runnable(bitmap) {
                        private final /* synthetic */ Bitmap f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            CameraPlayerActivity.this.onSnapShot(this.f$1);
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void onSnapShot(Bitmap bitmap) {
        String a2 = FileUtil.a(false, this.mCameraDevice.getDid());
        if (a2 == null || bitmap == null) {
            this.isSnapshotting = false;
            return;
        }
        if ("cn".equalsIgnoreCase(ServerUtil.b())) {
            bitmap = BitmapUtils.a(bitmap, this);
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
        runOnUiThread(new Runnable(a2, Bitmap.createScaledBitmap(bitmap, 200, (bitmap.getHeight() * 200) / bitmap.getWidth(), false)) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ Bitmap f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                CameraPlayerActivity.lambda$onSnapShot$21(CameraPlayerActivity.this, this.f$1, this.f$2);
            }
        });
    }

    public static /* synthetic */ void lambda$onSnapShot$21(CameraPlayerActivity cameraPlayerActivity, String str, Bitmap bitmap) {
        if (new File(str).exists()) {
            if (cameraPlayerActivity.mLLFuncPopup.getVisibility() == 0) {
                cameraPlayerActivity.mLLFuncPopup.setVisibility(8);
            }
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) cameraPlayerActivity.mLLFuncPopup.getLayoutParams();
            layoutParams.bottomMargin = cameraPlayerActivity.mLLVideoViewBottomCtrl.getHeight() + Util.a((Context) cameraPlayerActivity, 6.0f);
            cameraPlayerActivity.mLLFuncPopup.setLayoutParams(layoutParams);
            cameraPlayerActivity.mLLFuncPopup.startAnimation(AnimationUtils.loadAnimation(cameraPlayerActivity, R.anim.anim_snap_shot_in));
            cameraPlayerActivity.mLLFuncPopup.setVisibility(0);
            cameraPlayerActivity.dismissSnapshotPopupRunnable(3000);
            if (bitmap != null) {
                cameraPlayerActivity.ivShotPic.setImageBitmap(bitmap);
            }
            ContentValues contentValues = new ContentValues(4);
            contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
            contentValues.put(Downloads._DATA, str);
            contentValues.put("mime_type", "image/jpeg");
            try {
                if (!Build.MODEL.equals("HM 1SC")) {
                    cameraPlayerActivity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                }
                LogUtil.a(TAG, "snap success");
                cameraPlayerActivity.isSnapshotting = false;
                final LocalFileManager.LocalFile b = cameraPlayerActivity.mCameraDevice.b().b(str);
                if (b != null) {
                    cameraPlayerActivity.ivShotPic.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (CameraPlayerActivity.this.canStepOut(R.string.speaking_block, R.string.recording_block)) {
                                CameraPlayerActivity.this.dismissSnapshotPopupRunnable(0);
                                Intent intent = new Intent(CameraPlayerActivity.this, LocalPicReviewActivity.class);
                                intent.putExtra("file_path", b.d);
                                CameraPlayerActivity.this.startActivity(intent);
                            }
                        }
                    });
                }
            } catch (Throwable unused) {
                cameraPlayerActivity.isSnapshotting = false;
            }
        } else {
            cameraPlayerActivity.isSnapshotting = false;
        }
    }

    /* access modifiers changed from: private */
    public void dismissSnapshotPopupRunnable(long j) {
        this.mHandler.removeCallbacks(this.snapshotRunnable);
        this.mHandler.postDelayed(this.snapshotRunnable, j);
    }

    /* access modifiers changed from: private */
    public void onVideoRecorded(String str) {
        File file = new File(str);
        if (file.exists()) {
            sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
            LocalFileManager.LocalFile b = this.mCameraDevice.b().b(str);
            if (b != null && this.mVideoView != null) {
                this.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback(b) {
                    private final /* synthetic */ LocalFileManager.LocalFile f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onSnap(Bitmap bitmap) {
                        CameraPlayerActivity.lambda$onVideoRecorded$24(CameraPlayerActivity.this, this.f$1, bitmap);
                    }
                });
            }
        }
    }

    public static /* synthetic */ void lambda$onVideoRecorded$24(CameraPlayerActivity cameraPlayerActivity, LocalFileManager.LocalFile localFile, Bitmap bitmap) {
        if (bitmap != null) {
            cameraPlayerActivity.runOnUiThread(new Runnable(Bitmap.createScaledBitmap(bitmap, 200, (bitmap.getHeight() * 200) / bitmap.getWidth(), false), localFile) {
                private final /* synthetic */ Bitmap f$1;
                private final /* synthetic */ LocalFileManager.LocalFile f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    CameraPlayerActivity.lambda$null$23(CameraPlayerActivity.this, this.f$1, this.f$2);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$null$23(CameraPlayerActivity cameraPlayerActivity, Bitmap bitmap, LocalFileManager.LocalFile localFile) {
        if (bitmap != null) {
            cameraPlayerActivity.ivShotPic.setImageBitmap(bitmap);
            if (cameraPlayerActivity.mLLFuncPopup.getVisibility() == 0) {
                cameraPlayerActivity.mLLFuncPopup.setVisibility(8);
            }
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) cameraPlayerActivity.mLLFuncPopup.getLayoutParams();
            layoutParams.bottomMargin = cameraPlayerActivity.mLLVideoViewBottomCtrl.getHeight() + Util.a((Context) cameraPlayerActivity, 6.0f);
            cameraPlayerActivity.mLLFuncPopup.setLayoutParams(layoutParams);
            cameraPlayerActivity.mLLFuncPopup.startAnimation(AnimationUtils.loadAnimation(cameraPlayerActivity, R.anim.anim_snap_shot_in));
            cameraPlayerActivity.mLLFuncPopup.setVisibility(0);
            cameraPlayerActivity.dismissSnapshotPopupRunnable(3000);
            cameraPlayerActivity.ivShotPic.setOnClickListener(new View.OnClickListener(localFile) {
                private final /* synthetic */ LocalFileManager.LocalFile f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    CameraPlayerActivity.lambda$null$22(CameraPlayerActivity.this, this.f$1, view);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$null$22(CameraPlayerActivity cameraPlayerActivity, LocalFileManager.LocalFile localFile, View view) {
        if (cameraPlayerActivity.canStepOut(R.string.speaking_block, R.string.recording_block)) {
            cameraPlayerActivity.dismissSnapshotPopupRunnable(0);
            Intent intent = new Intent();
            intent.putExtra("file_path", localFile.d);
            intent.setClass(cameraPlayerActivity, LocalVideoPlayActivity.class);
            cameraPlayerActivity.startActivity(intent);
        }
    }

    public boolean isDistort() {
        return this.mCameraDevice.e().c();
    }

    private void toggleResolution() {
        Event.a(Event.au);
        if (this.mCameraPlayer != null && !this.mCameraPlayer.c()) {
            ToastUtil.a((Context) this, (int) R.string.history_video_resolution_hd_only);
        } else if (this.mCameraPlayerEx != null && !this.mCameraPlayerEx.f()) {
            ToastUtil.a((Context) this, (int) R.string.history_video_resolution_hd_only);
        } else if (this.mCameraPlayer != null && this.mCameraPlayer.m()) {
            ToastUtil.a((Context) this, (int) R.string.record_resolution_block);
        } else if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.r()) {
            Event.a(Event.h);
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
            builder.a((int) R.string.quality_choice);
            String[] strArr = {getString(R.string.quality_auto), getString(R.string.quality_low), getString(R.string.quality_fhd)};
            this.selectedIndex = this.mCameraDevice.e().d();
            if (this.selectedIndex == 3) {
                this.selectedIndex = 2;
            }
            builder.a((CharSequence[]) strArr, this.selectedIndex, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    CameraPlayerActivity.this.selectedIndex = i;
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) $$Lambda$CameraPlayerActivity$wIc0nPahVoDfV6SZBJfJAA1w64.INSTANCE).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(strArr) {
                private final /* synthetic */ String[] f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    CameraPlayerActivity.lambda$toggleResolution$27(CameraPlayerActivity.this, this.f$1, dialogInterface, i);
                }
            }).d();
        } else {
            ToastUtil.a((Context) this, (int) R.string.record_resolution_block);
        }
    }

    public static /* synthetic */ void lambda$toggleResolution$27(CameraPlayerActivity cameraPlayerActivity, String[] strArr, DialogInterface dialogInterface, int i) {
        if (cameraPlayerActivity.selectedIndex >= 0 && cameraPlayerActivity.selectedIndex < strArr.length && !TextUtils.isEmpty(strArr[cameraPlayerActivity.selectedIndex])) {
            if (cameraPlayerActivity.selectedIndex == 0) {
                Event.a(Event.av, "type", (Object) 1);
            } else if (cameraPlayerActivity.selectedIndex == 1) {
                Event.a(Event.av, "type", (Object) 2);
            } else if (cameraPlayerActivity.selectedIndex == 2) {
                Event.a(Event.av, "type", (Object) 3);
            }
            cameraPlayerActivity.setResolutionText(cameraPlayerActivity.selectedIndex);
            if (cameraPlayerActivity.selectedIndex == 2) {
                cameraPlayerActivity.selectedIndex = 3;
            }
            cameraPlayerActivity.mCameraDevice.e().b(cameraPlayerActivity.selectedIndex);
            if (cameraPlayerActivity.mCameraPlayer != null) {
                cameraPlayerActivity.mCameraPlayer.b(cameraPlayerActivity.selectedIndex);
            }
            if (cameraPlayerActivity.mCameraPlayerEx != null) {
                cameraPlayerActivity.mCameraPlayerEx.a(cameraPlayerActivity.selectedIndex, (IMISSListener) new IMISSListener() {
                    public void onFailed(int i, String str) {
                    }

                    public void onProgress(int i) {
                    }

                    public void onSuccess(String str, Object obj) {
                    }
                });
            }
        }
    }

    private void upDateTimeTitle(int i) {
        this.mTVRecordTimer.setText(this.mSimpleDateFormat.format(new Date((long) i)));
    }

    /* access modifiers changed from: private */
    public void parseAngle(JSONObject jSONObject) {
        if (jSONObject != null) {
            int optInt = jSONObject.optInt(LinearGradientManager.PROP_ANGLE);
            int optInt2 = jSONObject.optInt("ret");
            LogUtil.a(TAG, "ret " + optInt2 + " angele " + optInt);
            if (optInt2 < 0) {
                if (optInt2 == -5) {
                    Toast.makeText(this, R.string.calibrating, 0).show();
                } else if (this.mHintView.getVisibility() != 0) {
                    this.mHintView.setVisibility(0);
                }
                this.mHandler.removeMessages(1002);
                this.mHandler.sendEmptyMessageDelayed(1002, 1000);
                return;
            }
            double d = (double) optInt;
            Double.isNaN(d);
            this.mAVCamera.setAngle((float) (d * -3.6d));
            this.mHandler.removeMessages(1002);
            this.mHandler.sendEmptyMessage(1002);
        }
    }

    /* access modifiers changed from: protected */
    public void detectSDCard() {
        if (this.mCameraDevice != null) {
            this.sdcardStatus = 0;
            AnonymousClass28 r0 = new Callback<SDCardInfo>() {
                public void onSuccess(SDCardInfo sDCardInfo) {
                    LogUtil.a(CameraPlayerActivity.TAG, "detectSDCard onSuccess:" + sDCardInfo.e);
                    CameraPlayerActivity.this.sdcardGetSuccess = true;
                    if (CameraPlayerActivity.this.mCameraDevice.e().f8058a && (sDCardInfo.e == 1 || sDCardInfo.e == 3 || sDCardInfo.e == 5)) {
                        CameraPlayerActivity.this.showSDCardHintDialog();
                    }
                    CameraPlayerActivity.this.sdcardStatus = sDCardInfo.e;
                }

                public void onFailure(int i, String str) {
                    LogUtil.b(CameraPlayerActivity.TAG, "detectSDCard onFailure:" + i + " s:" + str);
                    if (-97 != i) {
                        if (i == -2003 || i == -2002 || i == -2005 || i == -2000) {
                            CameraPlayerActivity.this.sdcardGetSuccess = true;
                        } else {
                            CameraPlayerActivity.this.sdcardGetSuccess = false;
                        }
                        if (i == -2003) {
                            CameraPlayerActivity.this.sdcardStatus = 1;
                        } else if (i == -2000) {
                            CameraPlayerActivity.this.sdcardStatus = 4;
                        } else if (i == -2005) {
                            CameraPlayerActivity.this.sdcardStatus = 5;
                        } else if (i == -2002) {
                            CameraPlayerActivity.this.sdcardStatus = 3;
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
        this.mSDCardHintDialog = new SDCardHintDialog(this, R.style.popupDialog);
        this.mSDCardHintDialog.show();
        this.mSDCardHintDialog.setCancelable(true);
        this.mCameraDevice.e().f8058a = false;
    }

    private void checkMinLevel() {
        if (this.mCameraDevice.f().a(CameraProperties.f7888a) > 1) {
            showUpdateDlg(false);
        }
    }

    private void startCallAnim() {
        Event.g();
        int height = this.mRLDirectionCtl.getHeight() - this.mDcvDirectionCtrl.getHeight();
        this.mCallWave.getLayoutParams().height = (int) (((float) height) - (AppConfig.d * 10.0f));
        this.mCallWave.setVisibility(0);
        this.mCallWave.startAnimator();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mDcvDirectionCtrl, "translationY", new float[]{0.0f, (float) (-((int) (((float) (height / 2)) - (AppConfig.d * 10.0f))))});
        ofFloat.setDuration(200);
        ofFloat.start();
    }

    private void stopCallAnim() {
        Event.h();
        this.mCallWave.setVisibility(8);
        this.mCallWave.stopAnimator();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mDcvDirectionCtrl, "translationY", new float[]{0.0f});
        ofFloat.setDuration(200);
        ofFloat.start();
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
        showGuide();
    }

    private void showLicense() {
        if (!this.mCameraDevice.isShared() && XmPluginHostApi.instance().getApiLevel() > 47 && this.mCameraDevice.e().i()) {
            this.mIsInit = true;
            this.mNeedLicense = true;
            LocalLicenseUtil.LocalLicense v4LocalLicense = LocalLicenseUtil.getV4LocalLicense(getResources());
            if (v4LocalLicense.mLicense <= 0 || v4LocalLicense.mPrivacy <= 0) {
                LogUtil.a(Tag.b, "load raw fail");
                this.mCameraDevice.e().g(false);
                if (this.mCameraDevice.e().h()) {
                    initGuideCenter();
                }
            } else if (XmPluginHostApi.instance().getApiLevel() >= 67) {
                Intent intent = new Intent();
                if (!"cn".equalsIgnoreCase(ServerUtil.b()) || TextUtils.isEmpty(v4LocalLicense.mPlan)) {
                    intent.putExtra("enable_privacy_setting", false);
                } else {
                    intent.putExtra("enable_privacy_setting", true);
                    intent.putExtra(DeviceMoreActivity.ARGS_USR_EXP_PLAN_CONTENT, Html.fromHtml(v4LocalLicense.mPlan));
                }
                if (this.mDeviceStat != null && !TextUtils.isEmpty(this.mDeviceStat.did)) {
                    showUserLicenseDialog(getString(R.string.privacy_title), getString(R.string.licences_content), v4LocalLicense.mLicense, getString(R.string.privacy_content), v4LocalLicense.mPrivacy, new View.OnClickListener() {
                        public void onClick(View view) {
                            boolean unused = CameraPlayerActivity.this.mNeedLicense = false;
                            CameraPlayerActivity.this.mHandler.removeMessages(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
                            CameraPlayerActivity.this.mHandler.sendEmptyMessage(AMapException.CODE_AMAP_NEARBY_INVALID_USERID);
                            CameraPlayerActivity.this.mHandler.sendEmptyMessage(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
                            if (CameraPlayerActivity.this.mCameraDevice != null) {
                                CameraPlayerActivity.this.mCameraDevice.e().g(false);
                                if (CameraPlayerActivity.this.mCameraDevice.e().h()) {
                                    CameraPlayerActivity.this.initGuideCenter();
                                }
                                boolean isUsrExpPlanEnabled = XmPluginHostApi.instance().isUsrExpPlanEnabled(CameraPlayerActivity.this.mDid);
                                Event.ch = isUsrExpPlanEnabled;
                                CameraPlayerActivity.this.mCameraDevice.a(isUsrExpPlanEnabled, (Callback<Void>) null);
                            }
                        }
                    }, intent);
                }
            }
        }
    }

    private void clearAllCacheRecording() {
        new Thread(new Runnable() {
            public final void run() {
                CameraPlayerActivity.lambda$clearAllCacheRecording$28(CameraPlayerActivity.this);
            }
        }).start();
    }

    public static /* synthetic */ void lambda$clearAllCacheRecording$28(CameraPlayerActivity cameraPlayerActivity) {
        File[] listFiles;
        String c = FileUtil.c(cameraPlayerActivity.mCameraDevice.getDid());
        if (!TextUtils.isEmpty(c)) {
            File file = new File(c);
            if (file.exists() && (listFiles = file.listFiles()) != null) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }
    }

    private void toggleSpeed() {
        int i = 16;
        if (this.mCameraPlayer != null) {
            if (this.mCameraPlayer.d()) {
                int q = this.mCameraPlayer.q();
                LogUtil.a(TAG, "speed:" + q);
                int i2 = q != 1 ? q != 4 ? 1 : 16 : 4;
                this.mCameraPlayer.d(i2);
                if (i2 == 1) {
                    this.mCDCToggleAudio.setEnabled(true);
                    this.mCBMuteLandscape.setEnabled(true);
                } else {
                    if (this.mCDCToggleAudio.isChecked()) {
                        this.mCDCToggleAudio.performClick();
                    }
                    this.mCDCToggleAudio.setEnabled(false);
                    this.mCBMuteLandscape.setEnabled(false);
                }
                TextView textView = this.mTVSResolution;
                textView.setText("" + i2 + "X");
            } else {
                ToastUtil.a((Context) this, (int) R.string.sd_card_video_not_playing);
            }
        }
        if (this.mCameraPlayerEx == null) {
            return;
        }
        if (this.mCameraPlayerEx.g()) {
            int v = this.mCameraPlayerEx.v();
            LogUtil.a(TAG, "speed:" + v);
            if (v == 1) {
                i = 4;
            } else if (v != 4) {
                i = 1;
            }
            this.mCameraPlayerEx.b(i);
            if (i == 1) {
                this.mCDCToggleAudio.setEnabled(true);
                this.mCBMuteLandscape.setEnabled(true);
            } else {
                if (this.mCDCToggleAudio.isChecked()) {
                    this.mCDCToggleAudio.performClick();
                }
                this.mCDCToggleAudio.setEnabled(false);
                this.mCBMuteLandscape.setEnabled(false);
            }
            TextView textView2 = this.mTVSResolution;
            textView2.setText("" + i + "X");
            return;
        }
        ToastUtil.a((Context) this, (int) R.string.sd_card_video_not_playing);
    }

    private void showGuide() {
        GuidePage1 guidePage1 = new GuidePage1(this, R.style.guide_dialog);
        guidePage1.show();
        guidePage1.setCancelable(true);
        this.mCameraDevice.e().f(false);
    }

    private void showUpdateDlg(boolean z) {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        builder.a((int) R.string.api_tip);
        builder.b((int) R.string.api_tip_title);
        builder.a(false);
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                CameraPlayerActivity.lambda$showUpdateDlg$29(CameraPlayerActivity.this, dialogInterface, i);
            }
        });
        builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(z) {
            private final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                CameraPlayerActivity.lambda$showUpdateDlg$30(CameraPlayerActivity.this, this.f$1, dialogInterface, i);
            }
        });
        builder.d();
    }

    public static /* synthetic */ void lambda$showUpdateDlg$29(CameraPlayerActivity cameraPlayerActivity, DialogInterface dialogInterface, int i) {
        if (!cameraPlayerActivity.isFinishing()) {
            dialogInterface.dismiss();
            cameraPlayerActivity.finish();
        }
    }

    public static /* synthetic */ void lambda$showUpdateDlg$30(CameraPlayerActivity cameraPlayerActivity, boolean z, DialogInterface dialogInterface, int i) {
        if (!cameraPlayerActivity.isFinishing()) {
            if (z) {
                cameraPlayerActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(AppCode.u)));
            }
            dialogInterface.dismiss();
            cameraPlayerActivity.finish();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004f A[Catch:{ Exception -> 0x00ea }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0065 A[Catch:{ Exception -> 0x00ea }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a5 A[Catch:{ Exception -> 0x00ea }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b9 A[Catch:{ Exception -> 0x00ea }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00fa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void isCloudVideoUser() {
        /*
            r7 = this;
            boolean r0 = com.mijia.model.alarm.AlarmNetUtils.e()
            if (r0 == 0) goto L_0x011d
            com.mijia.camera.MijiaCameraDevice r0 = r7.mCameraDevice
            java.lang.String r0 = r0.getDid()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x011d
            r0 = 1
            r1 = 0
            java.lang.String r2 = "chuangmi.camera.ipc009"
            com.xiaomi.smarthome.device.api.DeviceStat r3 = r7.mDeviceStat     // Catch:{ Exception -> 0x00ea }
            java.lang.String r3 = r3.model     // Catch:{ Exception -> 0x00ea }
            boolean r2 = r2.equals(r3)     // Catch:{ Exception -> 0x00ea }
            if (r2 == 0) goto L_0x0031
            com.mijia.camera.MijiaCameraDevice r2 = r7.mCameraDevice     // Catch:{ Exception -> 0x00ea }
            java.lang.String r2 = r2.p()     // Catch:{ Exception -> 0x00ea }
            java.lang.String r3 = "3.4.2_0200"
            boolean r2 = com.mijia.model.alarm.AlarmNetUtils.a((java.lang.String) r2, (java.lang.String) r3)     // Catch:{ Exception -> 0x00ea }
            if (r2 == 0) goto L_0x002f
            goto L_0x0031
        L_0x002f:
            r2 = 0
            goto L_0x0032
        L_0x0031:
            r2 = 1
        L_0x0032:
            com.mijia.model.alarm.AlarmNetUtils.b(r2)     // Catch:{ Exception -> 0x00ea }
            boolean r2 = com.mijia.model.alarm.AlarmNetUtils.b()     // Catch:{ Exception -> 0x00ea }
            r3 = 2131433095(0x7f0b1687, float:1.8487966E38)
            r4 = 8
            r5 = 2131433111(0x7f0b1697, float:1.8487998E38)
            if (r2 == 0) goto L_0x0065
            boolean r2 = com.mijia.model.alarm.AlarmNetUtils.c()     // Catch:{ Exception -> 0x00ea }
            if (r2 == 0) goto L_0x0065
            boolean r2 = com.mijia.model.alarm.AlarmNetUtils.e()     // Catch:{ Exception -> 0x00ea }
            if (r2 == 0) goto L_0x0065
            android.view.View r2 = r7.findViewById(r5)     // Catch:{ Exception -> 0x00ea }
            r2.setVisibility(r1)     // Catch:{ Exception -> 0x00ea }
            android.view.View r2 = r7.findViewById(r5)     // Catch:{ Exception -> 0x00ea }
            r2.setOnClickListener(r7)     // Catch:{ Exception -> 0x00ea }
            android.view.View r2 = r7.findViewById(r3)     // Catch:{ Exception -> 0x00ea }
            r2.setVisibility(r4)     // Catch:{ Exception -> 0x00ea }
            goto L_0x007b
        L_0x0065:
            android.view.View r2 = r7.findViewById(r5)     // Catch:{ Exception -> 0x00ea }
            r2.setVisibility(r4)     // Catch:{ Exception -> 0x00ea }
            android.view.View r2 = r7.findViewById(r5)     // Catch:{ Exception -> 0x00ea }
            r4 = 0
            r2.setOnClickListener(r4)     // Catch:{ Exception -> 0x00ea }
            android.view.View r2 = r7.findViewById(r3)     // Catch:{ Exception -> 0x00ea }
            r2.setVisibility(r1)     // Catch:{ Exception -> 0x00ea }
        L_0x007b:
            boolean r2 = com.mijia.model.alarm.AlarmNetUtils.b()     // Catch:{ Exception -> 0x00ea }
            if (r2 == 0) goto L_0x00ea
            boolean r2 = com.mijia.model.alarm.AlarmNetUtils.e()     // Catch:{ Exception -> 0x00ea }
            if (r2 == 0) goto L_0x00ea
            com.mijia.camera.MijiaCameraDevice r2 = r7.mCameraDevice     // Catch:{ Exception -> 0x00ea }
            com.mijia.model.alarm.AlarmManager r2 = r2.i()     // Catch:{ Exception -> 0x00ea }
            long r2 = r2.g()     // Catch:{ Exception -> 0x00ea }
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x00ea
            com.mijia.camera.MijiaCameraDevice r2 = r7.mCameraDevice     // Catch:{ Exception -> 0x00ea }
            com.mijia.model.local.LocalSettings r2 = r2.e()     // Catch:{ Exception -> 0x00ea }
            long r2 = r2.k()     // Catch:{ Exception -> 0x00ea }
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x00b9
            com.mijia.camera.MijiaCameraDevice r2 = r7.mCameraDevice     // Catch:{ Exception -> 0x00ea }
            com.mijia.model.alarm.AlarmManager r2 = r2.i()     // Catch:{ Exception -> 0x00ea }
            com.mijia.camera.MijiaCameraDevice r3 = r7.mCameraDevice     // Catch:{ Exception -> 0x00ea }
            com.mijia.model.local.LocalSettings r3 = r3.e()     // Catch:{ Exception -> 0x00ea }
            long r3 = r3.k()     // Catch:{ Exception -> 0x00ea }
            r2.b((long) r3)     // Catch:{ Exception -> 0x00ea }
            goto L_0x00ea
        L_0x00b9:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x00ea }
            r2.<init>()     // Catch:{ Exception -> 0x00ea }
            java.lang.String r3 = "did"
            com.mijia.camera.MijiaCameraDevice r4 = r7.mCameraDevice     // Catch:{ Exception -> 0x00ea }
            java.lang.String r4 = r4.getDid()     // Catch:{ Exception -> 0x00ea }
            r2.put(r3, r4)     // Catch:{ Exception -> 0x00ea }
            java.lang.String r3 = "region"
            java.util.Locale r4 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x00ea }
            java.lang.String r4 = r4.getCountry()     // Catch:{ Exception -> 0x00ea }
            r2.put(r3, r4)     // Catch:{ Exception -> 0x00ea }
            com.mijia.camera.MijiaCameraDevice r3 = r7.mCameraDevice     // Catch:{ Exception -> 0x00ea }
            com.mijia.model.alarm.AlarmManager r3 = r3.i()     // Catch:{ Exception -> 0x00ea }
            com.mijia.camera.MijiaCameraDevice r4 = r7.mCameraDevice     // Catch:{ Exception -> 0x00ea }
            java.lang.String r4 = r4.getModel()     // Catch:{ Exception -> 0x00ea }
            com.xiaomi.smarthome.camera.v4.activity.CameraPlayerActivity$30 r5 = new com.xiaomi.smarthome.camera.v4.activity.CameraPlayerActivity$30     // Catch:{ Exception -> 0x00ea }
            r5.<init>()     // Catch:{ Exception -> 0x00ea }
            r3.a((java.lang.String) r4, (org.json.JSONObject) r2, (com.mijia.model.alarm.AlarmManager.IAlarmCallback) r5)     // Catch:{ Exception -> 0x00ea }
        L_0x00ea:
            com.mijia.camera.MijiaCameraDevice r2 = r7.mCameraDevice
            com.mijia.model.local.LocalSettings r2 = r2.e()
            int r2 = r2.p()
            if (r2 != 0) goto L_0x00fa
            com.mijia.model.alarm.AlarmNetUtils.a((boolean) r0)
            goto L_0x0109
        L_0x00fa:
            com.mijia.camera.MijiaCameraDevice r2 = r7.mCameraDevice
            com.mijia.model.local.LocalSettings r2 = r2.e()
            int r2 = r2.p()
            if (r2 != r0) goto L_0x0109
            com.mijia.model.alarm.AlarmNetUtils.a((boolean) r1)
        L_0x0109:
            com.mijia.camera.MijiaCameraDevice r0 = r7.mCameraDevice
            java.lang.String r0 = r0.getDid()
            com.mijia.camera.MijiaCameraDevice r1 = r7.mCameraDevice
            java.lang.String r1 = r1.getModel()
            com.xiaomi.smarthome.camera.v4.activity.CameraPlayerActivity$31 r2 = new com.xiaomi.smarthome.camera.v4.activity.CameraPlayerActivity$31
            r2.<init>()
            com.mijia.model.alarm.AlarmNetUtils.l(r0, r1, r2)
        L_0x011d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.v4.activity.CameraPlayerActivity.isCloudVideoUser():void");
    }

    /* access modifiers changed from: private */
    public void processCVExpireUI(long j) {
        int currentTimeMillis = j - System.currentTimeMillis() < 86400000 ? 1 : (int) (((((j - System.currentTimeMillis()) / 1000) / 60) / 60) / 24);
        if (currentTimeMillis == 1 || currentTimeMillis == 3 || currentTimeMillis == 7) {
            String q = this.mCameraDevice.e().q();
            if (!q.equals(j + "|" + currentTimeMillis)) {
                ((TextView) findViewById(R.id.tvCloudEndTip)).setText(getResources().getQuantityString(R.plurals.home_cloud_will_end_tip, currentTimeMillis, new Object[]{Integer.valueOf(currentTimeMillis)}));
                findViewById(R.id.ivCloudEndTip).setOnClickListener(new View.OnClickListener(j, currentTimeMillis) {
                    private final /* synthetic */ long f$1;
                    private final /* synthetic */ int f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r4;
                    }

                    public final void onClick(View view) {
                        CameraPlayerActivity.lambda$processCVExpireUI$31(CameraPlayerActivity.this, this.f$1, this.f$2, view);
                    }
                });
                findViewById(R.id.rlCloudEndTip).setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        FrameManager.b().k().openCloudVideoListActivity(CameraPlayerActivity.this, CameraPlayerActivity.this.mCameraDevice.getDid(), CameraPlayerActivity.this.mCameraDevice.getName());
                    }
                });
                findViewById(R.id.rlCloudEndTip).setVisibility(0);
            }
        }
    }

    public static /* synthetic */ void lambda$processCVExpireUI$31(CameraPlayerActivity cameraPlayerActivity, long j, int i, View view) {
        cameraPlayerActivity.findViewById(R.id.rlCloudEndTip).setVisibility(8);
        LocalSettings e = cameraPlayerActivity.mCameraDevice.e();
        e.a(j + "|" + i);
    }

    public void showUserLicenseDialog(String str, String str2, int i, String str3, int i2, View.OnClickListener onClickListener, Intent intent) {
        new UserLicenseDialog.Builder(this).a(str).b(str2).a(i).c(str3).b(i2).a(intent).d(this.mDid).a(onClickListener).a().a();
    }

    public void openMoreMenu(ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i, Intent intent) {
        DeviceMoreActivity.openMoreMenu((Activity) this, this.mDid, arrayList, z, i, intent);
    }

    public void openMoreMenu2(ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i, Intent intent, Intent intent2) {
        DeviceMoreNewActivity.openMoreMenu(this, this.mDid, arrayList, z, i, intent, intent2);
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

    /* access modifiers changed from: private */
    public void updateTime(int i) {
        int i2 = this.mCurrentTime / 1000;
        if (this.mLastTime != 0) {
            int i3 = i - this.mLastTime;
            if (i3 <= 0 || i3 >= 900) {
                this.mCurrentTime += 50;
            } else {
                this.mCurrentTime += i3;
            }
        } else {
            this.mCurrentTime += 50;
        }
        this.mLastTime = i;
        if (this.mCurrentTime / 1000 != i2) {
            runOnUiThread(new Runnable() {
                public final void run() {
                    CameraPlayerActivity.lambda$updateTime$33(CameraPlayerActivity.this);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$updateTime$33(CameraPlayerActivity cameraPlayerActivity) {
        int i = cameraPlayerActivity.mCurrentTime / 1000;
        if (i > 1) {
            cameraPlayerActivity.upDateTimeTitle((i - 1) * 1000);
        }
    }

    public void setPlayTime() {
        if (!this.mIsUserPause) {
            showLoading("");
        }
        if (this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.a((IMISSListener) new IMISSListener() {
                public void onProgress(int i) {
                }

                public void onSuccess(String str, Object obj) {
                    if (CameraPlayerActivity.this.mCameraPlayerEx.u()) {
                        CameraPlayerActivity.this.toggleRemoteAV(true, false);
                    } else {
                        CameraPlayerActivity.this.toggleRemoteAV(true, true);
                    }
                    boolean unused = CameraPlayerActivity.this.mVideoPlaying = true;
                }

                public void onFailed(int i, String str) {
                    boolean unused = CameraPlayerActivity.this.mVideoPlaying = false;
                }
            });
        }
    }
}
