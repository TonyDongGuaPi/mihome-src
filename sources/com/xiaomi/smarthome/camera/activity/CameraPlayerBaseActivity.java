package com.xiaomi.smarthome.camera.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.Utils.FileUtil;
import com.Utils.NetworkMonitor;
import com.Utils.NetworkUtil;
import com.mijia.app.Event;
import com.mijia.camera.CameraPlayer;
import com.mijia.camera.CameraPlayerEx;
import com.mijia.camera.ICameraPlayerListener;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.Tag;
import com.mijia.model.local.LocalSettings;
import com.mijia.model.property.CameraPropertyBase;
import com.mijia.model.property.PropertyManger;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.IMISSListener;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.activity.CameraPlayerBaseActivity;
import com.xiaomi.smarthome.camera.view.widget.MultiStateView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.download.Constants;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;

public abstract class CameraPlayerBaseActivity extends CameraBaseActivity implements View.OnClickListener, ICameraPlayerListener {
    public static final float DEFAULT_SCALE_HLC6 = 1.0f;
    public static final float DEFAULT_SCALE_IPC = 1.0f;
    public static final int MSG_UPDATE_PLAY_PROGRESS = 4;
    public static final int MSG_UPDATE_PLAY_TIME = 2;
    protected static final int PIC_SHOW_DURATION = 3000;
    public static final int SPEED_MISS = 4000;
    public static final String SP_KEY_OFFSET_X = "offsetX";
    public static final String SP_KEY_OFFSET_Y = "offsetY";
    public static final String SP_KEY_SCALE = "scale";
    private static final String TAG = "CameraPlayerBaseActivity";
    public static final String TIME_STAMP = "time";
    protected static final int mMinAppLevel = 63;
    private final long LIMIT_CONNECT_TIME = 120000;
    private final int RETRY_TIME = 90000;
    private String UPDATE_ING = "";
    protected Runnable delayedReconnectRunnable = new Runnable() {
        public void run() {
            if (CameraPlayerBaseActivity.this.mCameraPlayerEx != null) {
                CameraPlayerBaseActivity.this.mCameraPlayerEx.a((IMISSListener) new IMISSListener() {
                    public void onProgress(int i) {
                    }

                    public void onSuccess(String str, Object obj) {
                        LogUtil.a(CameraPlayerBaseActivity.TAG, "handleVideoPlayErrorOnMISS onSuccess:" + str);
                    }

                    public void onFailed(int i, String str) {
                        LogUtil.a(CameraPlayerBaseActivity.TAG, "handleVideoPlayErrorOnMISS onFailed:" + i + ":" + str);
                    }
                });
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean is4GToWifi = false;
    /* access modifiers changed from: private */
    public boolean isChangeNetworkType = false;
    protected boolean isTimeLapsePhotography = false;
    protected boolean mAllowMobileNetwork = false;
    CheckBox mCBMuteLandscape;
    CheckBox mCBVoiceLandscape;
    protected CameraPlayer mCameraPlayer;
    protected CameraPlayerEx mCameraPlayerEx;
    /* access modifiers changed from: private */
    public long mConnectStartTime = 0;
    /* access modifiers changed from: private */
    public boolean mConnected = false;
    /* access modifiers changed from: private */
    public long mEmptyTime = 0;
    protected TextView mErrorInfoView;
    protected View mErrorRetryView;
    TextView mFrameRate;
    protected boolean mFullScreen;
    LinearLayout mLLRightCtrlLandscape;
    MultiStateView mLandCallView;
    protected AnimationDrawable mLoadingAnimation;
    protected ImageView mLoadingImageView;
    protected TextView mLoadingProgress;
    protected TextView mLoadingTitle;
    protected View mLoadingView;
    protected boolean mNeedPincode = false;
    protected boolean mNeedSpeed = false;
    boolean mNeedTry = false;
    protected NetworkMonitor mNetworkMonitor = new NetworkMonitor();
    NetworkMonitor.OnNetworkChange mOnNetworkChange = new NetworkMonitor.OnNetworkChange() {
        public void onDisconnected(int i) {
            if (CameraPlayerBaseActivity.this.mCameraDevice.n() && CameraPlayerBaseActivity.this.mCameraPlayerEx != null) {
                CameraPlayerBaseActivity.this.pauseCamera();
                CameraPlayerBaseActivity.this.mCameraPlayerEx.o();
            }
            CameraPlayerBaseActivity.this.showError(CameraPlayerBaseActivity.this.getString(R.string.common_net_error));
        }

        public void onConnected(int i) {
            if ((!CameraPlayerBaseActivity.this.is4GToWifi || CameraPlayerBaseActivity.this.isHistory()) && ((NetworkUtil.c(XmPluginHostApi.instance().context()) && !CameraPlayerBaseActivity.this.mAllowMobileNetwork) || CameraPlayerBaseActivity.this.mUserPause)) {
                CameraPlayerBaseActivity.this.hidError();
                CameraPlayerBaseActivity.this.pauseCamera();
            } else if (CameraPlayerBaseActivity.this.mCameraDevice.n()) {
                if (CameraPlayerBaseActivity.this.mCameraPlayerEx != null && CameraPlayerBaseActivity.this.mConnected) {
                    CameraPlayerBaseActivity.this.hidError();
                    CameraPlayerBaseActivity.this.doResume();
                } else if (CameraPlayerBaseActivity.this.mCameraPlayerEx != null && CameraPlayerBaseActivity.this.isChangeNetworkType) {
                    boolean unused = CameraPlayerBaseActivity.this.isChangeNetworkType = false;
                    CameraPlayerBaseActivity.this.hidError();
                    CameraPlayerBaseActivity.this.doResume();
                }
            } else if (CameraPlayerBaseActivity.this.mCameraPlayer != null && CameraPlayerBaseActivity.this.mConnected) {
                CameraPlayerBaseActivity.this.hidError();
                CameraPlayerBaseActivity.this.doResume();
            } else if (CameraPlayerBaseActivity.this.mCameraPlayer != null && CameraPlayerBaseActivity.this.isChangeNetworkType) {
                CameraPlayerBaseActivity.this.hidError();
                CameraPlayerBaseActivity.this.doResume();
                boolean unused2 = CameraPlayerBaseActivity.this.isChangeNetworkType = false;
            }
        }

        public void onConnecting(int i) {
            if (CameraPlayerBaseActivity.this.mCameraDevice.n() && CameraPlayerBaseActivity.this.mCameraPlayerEx != null) {
                CameraPlayerBaseActivity.this.pauseCamera();
                CameraPlayerBaseActivity.this.mCameraPlayerEx.o();
            }
        }

        public void onChange(int i) {
            if (CameraPlayerBaseActivity.this.mCameraDevice.n() && CameraPlayerBaseActivity.this.mCameraPlayerEx != null) {
                CameraPlayerBaseActivity.this.pauseCamera();
                CameraPlayerBaseActivity.this.mCameraPlayerEx.o();
            }
            boolean unused = CameraPlayerBaseActivity.this.isChangeNetworkType = true;
            if (i == 1) {
                boolean unused2 = CameraPlayerBaseActivity.this.is4GToWifi = true;
            } else {
                boolean unused3 = CameraPlayerBaseActivity.this.is4GToWifi = false;
            }
        }
    };
    public View mPauseView;
    protected boolean mPowerCheck = false;
    protected View mPowerOffView;
    private int mProgress = 0;
    protected PropertyManger.OnPropertyChangeListener mPropertyChangeListener = new PropertyManger.OnPropertyChangeListener() {
        public void onPropertyChanged(CameraDevice cameraDevice, HashSet<String> hashSet) {
            CameraPlayerBaseActivity.this.mHandler.post(new Runnable(hashSet) {
                private final /* synthetic */ HashSet f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    CameraPlayerBaseActivity.AnonymousClass1.lambda$onPropertyChanged$0(CameraPlayerBaseActivity.AnonymousClass1.this, this.f$1);
                }
            });
        }

        public static /* synthetic */ void lambda$onPropertyChanged$0(AnonymousClass1 r1, HashSet hashSet) {
            if (hashSet.contains(CameraPropertyBase.l)) {
                LogUtil.a(Tag.b, "power change");
                CameraPlayerBaseActivity.this.refreshUI();
                if (NetworkUtil.b(XmPluginHostApi.instance().context()) || (NetworkUtil.c(XmPluginHostApi.instance().context()) && CameraPlayerBaseActivity.this.mAllowMobileNetwork)) {
                    if (CameraPlayerBaseActivity.this.mCameraDevice.f().c() && CameraPlayerBaseActivity.this.mCameraPlayer != null && !CameraPlayerBaseActivity.this.mCameraPlayer.d()) {
                        CameraPlayerBaseActivity.this.doResume();
                    }
                    if (CameraPlayerBaseActivity.this.mCameraDevice.f().c() && CameraPlayerBaseActivity.this.mCameraPlayerEx != null && !CameraPlayerBaseActivity.this.mCameraPlayerEx.m()) {
                        CameraPlayerBaseActivity.this.doResume();
                    }
                }
            } else if (hashSet.contains(LocalSettings.b)) {
                CameraPlayerBaseActivity.this.mAllowMobileNetwork = !CameraPlayerBaseActivity.this.mCameraDevice.e().b();
            }
        }
    };
    /* access modifiers changed from: private */
    public int mPwdCount = 5;
    /* access modifiers changed from: private */
    public int mRetryCount = 0;
    protected View mRetryView;
    protected boolean mShowView = false;
    protected float mSurfaceViewOffsetX = 0.0f;
    protected float mSurfaceViewOffsetY = 0.0f;
    protected float mSurfaceViewScale = 1.0f;
    protected TextView mTitleView;
    private boolean mUpdating = false;
    protected boolean mUserPause = false;
    protected FrameLayout mVideoLayout;
    protected XmVideoViewGl mVideoView;
    protected FrameLayout mVideoViewFrame;
    private long startPlayTimeStamp = 0;

    public boolean canStepOut(int i, int i2) {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract void detectSDCard();

    /* access modifiers changed from: protected */
    public abstract void doResume();

    /* access modifiers changed from: protected */
    public void doStopCall() {
    }

    /* access modifiers changed from: protected */
    public void doStopRecord() {
    }

    public abstract boolean isHistory();

    /* access modifiers changed from: protected */
    public void onPlayError() {
    }

    /* access modifiers changed from: protected */
    public abstract void refreshUI();

    /* access modifiers changed from: protected */
    public abstract void resumeCamera();

    /* access modifiers changed from: protected */
    public boolean shouldRecordPlayTime() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract void startPlay();

    static /* synthetic */ int access$710(CameraPlayerBaseActivity cameraPlayerBaseActivity) {
        int i = cameraPlayerBaseActivity.mPwdCount;
        cameraPlayerBaseActivity.mPwdCount = i - 1;
        return i;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mConnectStartTime = 0;
        if (this.mCameraDevice != null) {
            this.mCameraDevice.g().b(this.mPropertyChangeListener);
        }
    }

    /* access modifiers changed from: protected */
    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        TitleBarUtil.b((Activity) this);
        getWindow().clearFlags(2048);
        this.mAllowMobileNetwork = !this.mCameraDevice.e().b();
        this.UPDATE_ING = getResources().getString(R.string.update_ing);
    }

    public void onResume() {
        super.onResume();
        if (this.mCameraDevice.n()) {
            if (!this.mUserPause && this.mCameraPlayerEx != null && this.mCameraPlayerEx.k()) {
                if (!NetworkUtil.d(XmPluginHostApi.instance().context())) {
                    pauseCamera();
                    showError(getString(R.string.common_net_error));
                } else if (!NetworkUtil.c(XmPluginHostApi.instance().context()) || this.mAllowMobileNetwork) {
                    doResume();
                } else {
                    pauseCamera();
                }
            }
        } else if (!this.mUserPause && this.mCameraPlayer != null && this.mCameraPlayer.h()) {
            if (!NetworkUtil.d(XmPluginHostApi.instance().context())) {
                pauseCamera();
                showError(getString(R.string.common_net_error));
            } else if (!NetworkUtil.c(XmPluginHostApi.instance().context()) || this.mAllowMobileNetwork) {
                doResume();
            } else {
                pauseCamera();
            }
        }
        this.mNetworkMonitor.addListener(this.mOnNetworkChange);
        refreshUI();
    }

    public void onStop() {
        super.onStop();
        if (this.mCameraPlayer != null) {
            this.mCameraPlayer.g();
        }
        LogUtil.a(TAG, "onpause  ....isTimeLapsePhotography=" + this.isTimeLapsePhotography);
        if (!this.isTimeLapsePhotography && this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.j();
        }
        recordVideoPlayTime();
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what != 4) {
            return;
        }
        if (!this.mUpdating || this.mLoadingView == null || this.mLoadingView.getVisibility() != 0) {
            if (this.mLoadingProgress.getTag() != null) {
                int intValue = ((Integer) this.mLoadingProgress.getTag()).intValue();
                if (intValue < 100 && this.mProgress <= 100) {
                    if (intValue == this.mProgress) {
                        this.mHandler.sendEmptyMessageDelayed(4, 100);
                        return;
                    } else if (intValue <= this.mProgress || this.mRetryCount == 0) {
                        int i = intValue + 1;
                        if (i > this.mProgress) {
                            this.mLoadingTitle.setText(Util.d(getResources(), this.mProgress));
                            TextView textView = this.mLoadingProgress;
                            textView.setText("" + this.mProgress + Operators.MOD);
                            this.mLoadingProgress.setTag(Integer.valueOf(this.mProgress));
                        } else if (i <= 100) {
                            this.mLoadingTitle.setText(Util.d(getResources(), i));
                            TextView textView2 = this.mLoadingProgress;
                            textView2.setText("" + i + Operators.MOD);
                            this.mLoadingProgress.setTag(Integer.valueOf(i));
                        }
                    } else if (this.mLoadingProgress.getVisibility() == 0) {
                        this.mHandler.sendEmptyMessageDelayed(4, 20);
                        return;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } else if (this.mProgress <= 100) {
                this.mLoadingTitle.setText(Util.d(getResources(), this.mProgress));
                TextView textView3 = this.mLoadingProgress;
                textView3.setText("" + this.mProgress + Operators.MOD);
                this.mLoadingProgress.setTag(Integer.valueOf(this.mProgress));
            } else {
                return;
            }
            if (this.mLoadingProgress.getVisibility() == 0) {
                this.mHandler.sendEmptyMessageDelayed(4, 20);
                return;
            }
            return;
        }
        this.mHandler.sendEmptyMessageDelayed(4, 1000);
    }

    public void onPause() {
        super.onPause();
        this.mNetworkMonitor.removeListener(this.mOnNetworkChange);
        this.mConnectStartTime = 0;
    }

    public void onPrepare(int i) {
        LogUtil.a(Tag.b, "prepare " + i);
        this.mProgress = i;
        if (i == 5 && this.mRetryCount == 0) {
            this.mConnectStartTime = System.currentTimeMillis();
        }
        if (i >= 101) {
            if (this.mVideoView != null) {
                this.mVideoView.setTouch(true);
            }
            if (this.mCameraDevice.n()) {
                if (i == 101 && this.mCameraPlayerEx != null && !isHistory() && this.mCameraPlayerEx.f()) {
                    this.mHandler.postDelayed(new Runnable() {
                        public final void run() {
                            CameraPlayerBaseActivity.lambda$onPrepare$1(CameraPlayerBaseActivity.this);
                        }
                    }, 2000);
                }
            } else if (i == 101 && this.mCameraPlayer != null && !isHistory() && this.mCameraPlayer.c()) {
                this.mHandler.postDelayed(new Runnable() {
                    public final void run() {
                        CameraPlayerBaseActivity.lambda$onPrepare$3(CameraPlayerBaseActivity.this);
                    }
                }, 2000);
            }
            if (this.mConnectStartTime > 0) {
                long currentTimeMillis = System.currentTimeMillis() - this.mConnectStartTime;
                this.mConnectStartTime = 0;
                if (currentTimeMillis > 0 && currentTimeMillis < ((long) (this.mRetryCount * 60000)) + 120000) {
                    Event.a(Event.f7858a, "time", (Object) Integer.valueOf((int) currentTimeMillis));
                    Event.a(1, this.mRetryCount, currentTimeMillis);
                }
            }
            if (!this.mShowView) {
                this.mShowView = true;
                this.mHandler.postDelayed(new Runnable() {
                    public final void run() {
                        CameraPlayerBaseActivity.lambda$onPrepare$4(CameraPlayerBaseActivity.this);
                    }
                }, Constants.x);
            }
            this.mRetryCount = 0;
            hideLoading();
            hidError();
            refreshUI();
            LogUtil.a(Tag.b, "onPrepare finish");
            this.startPlayTimeStamp = System.currentTimeMillis();
        } else if (i == 0 && this.mCameraDevice != null && this.mCameraDevice.f().c()) {
            if ((this.mLoadingView != null && this.mLoadingView.getVisibility() != 0) || this.mRetryCount == 0) {
                String string = getString(R.string.camera_play_initial_0);
                hidError();
                showLoading(string);
            }
        }
    }

    public static /* synthetic */ void lambda$onPrepare$1(CameraPlayerBaseActivity cameraPlayerBaseActivity) {
        if (cameraPlayerBaseActivity.mVideoView != null) {
            cameraPlayerBaseActivity.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
                public final void onSnap(Bitmap bitmap) {
                    CameraPlayerBaseActivity.lambda$null$0(CameraPlayerBaseActivity.this, bitmap);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$null$0(CameraPlayerBaseActivity cameraPlayerBaseActivity, Bitmap bitmap) {
        if (bitmap != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(FileUtil.b(cameraPlayerBaseActivity.mCameraDevice.getDid()));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                cameraPlayerBaseActivity.mCameraDevice.e().e(false);
                fileOutputStream.close();
                bitmap.recycle();
            } catch (IOException unused) {
            }
        }
    }

    public static /* synthetic */ void lambda$onPrepare$3(CameraPlayerBaseActivity cameraPlayerBaseActivity) {
        if (cameraPlayerBaseActivity.mVideoView != null) {
            cameraPlayerBaseActivity.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
                public final void onSnap(Bitmap bitmap) {
                    CameraPlayerBaseActivity.lambda$null$2(CameraPlayerBaseActivity.this, bitmap);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$null$2(CameraPlayerBaseActivity cameraPlayerBaseActivity, Bitmap bitmap) {
        if (bitmap != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(FileUtil.b(cameraPlayerBaseActivity.mCameraDevice.getDid()));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                cameraPlayerBaseActivity.mCameraDevice.e().e(false);
                fileOutputStream.close();
                bitmap.recycle();
            } catch (IOException unused) {
            }
        }
    }

    public static /* synthetic */ void lambda$onPrepare$4(CameraPlayerBaseActivity cameraPlayerBaseActivity) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 16 && !cameraPlayerBaseActivity.isGPUDecoder()) {
            String str = Event.S;
            Event.a(str, "type", (Object) Build.MODEL + " SDK:" + i);
        }
    }

    public void onRetry(int i, String str, int i2) {
        if (i == -90) {
            if (this.mCameraDevice.deviceStat().isOnline) {
                this.mCameraDevice.c((Callback<Void>) null);
            }
            dealRetry(false, i);
        }
    }

    public void onVideoPlayError(final int i, String str) {
        if (this.mCameraDevice == null || !this.mCameraDevice.n()) {
            if (i == -20009) {
                LogUtil.a(Tag.b, "PlayError PWD");
                this.mRetryCount++;
                if (this.mRetryCount <= 3) {
                    updatePwd();
                } else {
                    hideLoading();
                    showError(getString(R.string.camera_play_error1, new Object[]{Integer.valueOf(i)}));
                    long currentTimeMillis = System.currentTimeMillis() - this.mConnectStartTime;
                    if (currentTimeMillis > 0 && currentTimeMillis < 120000) {
                        Event.a(i, this.mRetryCount, currentTimeMillis);
                    }
                    this.mConnectStartTime = 0;
                    this.mRetryCount = 0;
                }
            } else if (i == -6) {
                ToastManager.a().a(getResources().getString(R.string.max_client_exceed));
            } else if (!this.mUpdating || this.mLoadingView == null || this.mLoadingView.getVisibility() != 0) {
                this.mRetryCount = 4;
                if (!this.mCameraDevice.deviceStat().isOnline) {
                    hideLoading();
                    showError(getString(R.string.device_offline));
                    Event.a(-1, 0, 0);
                } else if (i == -90) {
                    if (this.mCameraDevice.deviceStat().isOnline) {
                        this.mCameraDevice.c((Callback<Void>) null);
                    }
                    dealRetry(false, i);
                } else if (!this.mPowerCheck) {
                    this.mCameraDevice.f().a(new String[]{CameraPropertyBase.l, CameraPropertyBase.p}, (Callback<Void>) new Callback<Void>() {
                        public void onSuccess(Void voidR) {
                            if (!CameraPlayerBaseActivity.this.isFinishing()) {
                                CameraPlayerBaseActivity.this.mPowerCheck = true;
                                if (!CameraPlayerBaseActivity.this.mCameraDevice.f().c()) {
                                    CameraPlayerBaseActivity.this.hideLoading();
                                    CameraPlayerBaseActivity.this.refreshUI();
                                } else if (CameraPlayerBaseActivity.this.mCameraDevice.f().e()) {
                                    CameraPlayerBaseActivity.this.hideLoading();
                                    CameraPlayerBaseActivity.this.showError(CameraPlayerBaseActivity.this.getString(R.string.max_client_3));
                                } else {
                                    CameraPlayerBaseActivity.this.dealRetry(true, i);
                                }
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (!CameraPlayerBaseActivity.this.isFinishing()) {
                                CameraPlayerBaseActivity.this.hideLoading();
                                CameraPlayerBaseActivity.this.showError(CameraPlayerBaseActivity.this.getString(R.string.camera_play_error1, new Object[]{Integer.valueOf(i)}));
                                long currentTimeMillis = System.currentTimeMillis() - CameraPlayerBaseActivity.this.mConnectStartTime;
                                if (currentTimeMillis > 0 && currentTimeMillis < 120000) {
                                    Event.a(i, CameraPlayerBaseActivity.this.mRetryCount, currentTimeMillis);
                                }
                            }
                        }
                    });
                } else if (i != -20015) {
                    dealRetry(true, i);
                } else if (this.mCameraDevice.f().c()) {
                    this.mCameraDevice.f().a(new String[]{CameraPropertyBase.p}, (Callback<Void>) new Callback<Void>() {
                        public void onSuccess(Void voidR) {
                            if (!CameraPlayerBaseActivity.this.isFinishing()) {
                                if (CameraPlayerBaseActivity.this.mCameraDevice.f().e()) {
                                    CameraPlayerBaseActivity.this.hideLoading();
                                    CameraPlayerBaseActivity.this.showError(CameraPlayerBaseActivity.this.getString(R.string.max_client_3));
                                    return;
                                }
                                CameraPlayerBaseActivity.this.dealRetry(true, i);
                            }
                        }

                        public void onFailure(int i, String str) {
                            CameraPlayerBaseActivity.this.hideLoading();
                            CameraPlayerBaseActivity.this.showError(CameraPlayerBaseActivity.this.getString(R.string.camera_play_error1, new Object[]{Integer.valueOf(i)}));
                        }
                    });
                } else {
                    hideLoading();
                    refreshUI();
                }
            } else {
                return;
            }
            recordVideoPlayTime();
            return;
        }
        if (!this.mCameraDevice.deviceStat().isOnline) {
            hideLoading();
            showError(getString(R.string.device_offline));
        }
        LogUtil.a(TAG, "error:" + i + " errorInfo:" + str);
        int i2 = this.mRetryCount;
        this.mRetryCount = i2 + 1;
        if (i2 <= 3 && (i == 19 || i == 3 || i == 4)) {
            handleVideoPlayErrorOnMISS();
        }
        if (this.mRetryCount > 3) {
            hideLoading();
            showError(getString(R.string.camera_play_error1, new Object[]{Integer.valueOf(i)}));
            this.mConnectStartTime = 0;
            this.mRetryCount = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void checkMaxClient() {
        if (this.mCameraDevice != null && this.mCameraDevice.f() != null) {
            this.mCameraDevice.f().a(new String[]{CameraPropertyBase.p}, (Callback<Void>) new Callback<Void>() {
                public void onSuccess(Void voidR) {
                    if (!CameraPlayerBaseActivity.this.isFinishing()) {
                        CameraPlayerBaseActivity.this.hideLoading();
                        if (CameraPlayerBaseActivity.this.mCameraDevice.f().e()) {
                            CameraPlayerBaseActivity.this.showError(CameraPlayerBaseActivity.this.getString(R.string.max_client_3));
                        }
                    }
                }

                public void onFailure(int i, String str) {
                    if (!CameraPlayerBaseActivity.this.isFinishing()) {
                        CameraPlayerBaseActivity.this.hideLoading();
                    }
                }
            });
        }
    }

    public void onVideoInfo(int i, int i2, int i3, int i4, int i5) {
        if (this.mCameraDevice.f().c()) {
            if (this.mCameraDevice.n()) {
                if (this.mCameraPlayerEx != null && this.mCameraPlayerEx.r()) {
                    return;
                }
            } else if (this.mCameraPlayer != null && this.mCameraPlayer.m()) {
                return;
            }
            this.mHandler.post(new Runnable(i4) {
                private final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    CameraPlayerBaseActivity.lambda$onVideoInfo$7(CameraPlayerBaseActivity.this, this.f$1);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$onVideoInfo$7(CameraPlayerBaseActivity cameraPlayerBaseActivity, int i) {
        if (!(cameraPlayerBaseActivity.mFrameRate == null || cameraPlayerBaseActivity.mFrameRate.getVisibility() == 0)) {
            cameraPlayerBaseActivity.mFrameRate.setVisibility(0);
        }
        if (cameraPlayerBaseActivity.mFrameRate != null) {
            cameraPlayerBaseActivity.mFrameRate.setTextColor(cameraPlayerBaseActivity.getResources().getColor(R.color.sub_title_bar_text_color_white));
            TextView textView = cameraPlayerBaseActivity.mFrameRate;
            textView.setText("" + i + "KB/S");
        }
        if (cameraPlayerBaseActivity.mCameraDevice.n()) {
            if (cameraPlayerBaseActivity.mCameraPlayerEx != null && !cameraPlayerBaseActivity.mCameraPlayerEx.r()) {
                cameraPlayerBaseActivity.mHandler.post(new Runnable(i) {
                    private final /* synthetic */ int f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        CameraPlayerBaseActivity.lambda$null$5(CameraPlayerBaseActivity.this, this.f$1);
                    }
                });
            }
        } else if (cameraPlayerBaseActivity.mCameraPlayer != null && !cameraPlayerBaseActivity.mCameraPlayer.m()) {
            cameraPlayerBaseActivity.mHandler.post(new Runnable(i) {
                private final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    CameraPlayerBaseActivity.lambda$null$6(CameraPlayerBaseActivity.this, this.f$1);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$null$5(CameraPlayerBaseActivity cameraPlayerBaseActivity, int i) {
        if (!(cameraPlayerBaseActivity.mFrameRate == null || cameraPlayerBaseActivity.mFrameRate.getVisibility() == 0)) {
            cameraPlayerBaseActivity.mFrameRate.setVisibility(0);
        }
        if (cameraPlayerBaseActivity.mFrameRate != null) {
            TextView textView = cameraPlayerBaseActivity.mFrameRate;
            textView.setText("" + i + "KB/S");
        }
    }

    public static /* synthetic */ void lambda$null$6(CameraPlayerBaseActivity cameraPlayerBaseActivity, int i) {
        if (!(cameraPlayerBaseActivity.mFrameRate == null || cameraPlayerBaseActivity.mFrameRate.getVisibility() == 0)) {
            cameraPlayerBaseActivity.mFrameRate.setVisibility(0);
        }
        if (cameraPlayerBaseActivity.mFrameRate != null) {
            TextView textView = cameraPlayerBaseActivity.mFrameRate;
            textView.setText("" + i + "KB/S");
        }
    }

    public void onConnected() {
        this.mConnected = true;
        detectSDCard();
        if (this.mCameraDevice.n()) {
            if (this.mCameraPlayerEx != null && this.mCameraPlayerEx.k()) {
                this.mCameraPlayerEx.j();
            }
            if (this.mCameraPlayerEx != null) {
                if (this.mCameraPlayerEx.u()) {
                    this.mCameraPlayerEx.b(false, (IMISSListener) null);
                } else {
                    this.mCameraPlayerEx.b(true, (IMISSListener) null);
                }
                this.mCameraPlayerEx.b((IMISSListener) null);
            }
        } else if (this.mCameraPlayer != null && this.mCameraPlayer.h()) {
            this.mCameraPlayer.g();
        }
    }

    public void onDisConnected() {
        if (this.mFrameRate != null) {
            this.mFrameRate.setVisibility(8);
        }
    }

    public void onDisconnectedWithCode(int i) {
        if (this.mFrameRate != null) {
            this.mFrameRate.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void updatePwd() {
        onPrepare(0);
        if (TextUtils.isEmpty(XmPluginHostApi.instance().getDevicePincode(this.mDid)) && !this.mCameraDevice.m()) {
            this.mNeedTry = true;
        }
        LogUtil.a(Tag.b, "start upd");
        this.mCameraDevice.f(new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!CameraPlayerBaseActivity.this.isFinishing()) {
                    LogUtil.a(Tag.b, "updatePwd:onSuccess");
                    CameraPlayerBaseActivity.this.startPlay();
                }
            }

            public void onFailure(int i, String str) {
                if (!CameraPlayerBaseActivity.this.isFinishing()) {
                    if (i == 33) {
                        long j = 0;
                        if (CameraPlayerBaseActivity.this.mEmptyTime == 0) {
                            long unused = CameraPlayerBaseActivity.this.mEmptyTime = System.currentTimeMillis();
                        } else {
                            j = System.currentTimeMillis() - CameraPlayerBaseActivity.this.mEmptyTime;
                        }
                        if (j <= 5000) {
                            CameraPlayerBaseActivity.this.mHandler.postDelayed(new Runnable() {
                                public final void run() {
                                    CameraPlayerBaseActivity.this.updatePwd();
                                }
                            }, 1000);
                            return;
                        }
                    }
                    LogUtil.a(Tag.b, "updatePwd:onFailure:" + str + " uid: " + CameraPlayerBaseActivity.this.mCameraDevice.r() + " pwd:" + CameraPlayerBaseActivity.this.mCameraDevice.w());
                    if (!CameraPlayerBaseActivity.this.mNeedTry || CameraPlayerBaseActivity.this.mPwdCount <= 0) {
                        CameraPlayerBaseActivity.this.hideLoading();
                        CameraPlayerBaseActivity.this.showError(CameraPlayerBaseActivity.this.getString(R.string.camera_play_error2));
                        return;
                    }
                    CameraPlayerBaseActivity.access$710(CameraPlayerBaseActivity.this);
                    CameraPlayerBaseActivity.this.mHandler.postDelayed(new Runnable() {
                        public final void run() {
                            CameraPlayerBaseActivity.this.updatePwd();
                        }
                    }, 3000);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void pauseCamera() {
        if (this.mPauseView != null && this.mPauseView.getVisibility() == 8) {
            this.mPauseView.setVisibility(0);
        }
        if (this.mCameraDevice.n()) {
            if (this.mCameraPlayerEx != null) {
                this.mCameraPlayerEx.j();
            }
        } else if (this.mCameraPlayer != null) {
            this.mCameraPlayer.g();
        }
        if (NetworkUtil.c(XmPluginHostApi.instance().context()) && !this.mAllowMobileNetwork) {
            ToastUtil.a((Context) this, (int) R.string.nowifi_pausing);
            this.mUserPause = true;
        }
        hideLoading();
        recordVideoPlayTime();
    }

    private void recordVideoPlayTime() {
        if (shouldRecordPlayTime() && this.startPlayTimeStamp > 0) {
            Event.a(Event.an, "time", (Object) Long.valueOf((System.currentTimeMillis() - this.startPlayTimeStamp) / 1000));
            this.startPlayTimeStamp = 0;
        }
    }

    public boolean doOnClick(View view) {
        int id = view.getId();
        if (id == R.id.pause_view) {
            this.mUserPause = false;
            resumeCamera();
        } else if (id == R.id.retry_btn) {
            this.mRetryCount = 0;
            if (!NetworkUtil.d(XmPluginHostApi.instance().context())) {
                showError(getString(R.string.common_net_error));
            } else if (!NetworkUtil.c(XmPluginHostApi.instance().context()) || this.mAllowMobileNetwork) {
                hidError();
                showLoading(getString(R.string.camera_play_initial_0));
                if (this.mCameraDevice.n()) {
                    if (this.mCameraPlayerEx != null) {
                        this.mCameraPlayerEx.n();
                    } else {
                        startPlay();
                    }
                } else if (this.mCameraPlayer != null) {
                    this.mCameraPlayer.j();
                } else {
                    startPlay();
                }
            } else {
                hidError();
                pauseCamera();
            }
        } else if (id != R.id.title_bar_return) {
            return false;
        } else {
            onBackPressed();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void showLoading() {
        if (this.mLoadingView != null) {
            this.mLoadingAnimation.start();
            this.mLoadingView.bringToFront();
            this.mLoadingView.setVisibility(0);
            this.mLoadingTitle.setText(R.string.camera_play_initial_buffer);
            this.mLoadingProgress.setText("");
            if (this.mPauseView != null && this.mPauseView.getVisibility() == 0) {
                this.mPauseView.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void showLastLoading() {
        if (this.mLoadingView != null) {
            if (!this.mUpdating || this.mLoadingView.getVisibility() != 0) {
                if (this.mLoadingView.getVisibility() != 0) {
                    this.mLoadingView.bringToFront();
                    this.mLoadingAnimation.start();
                    this.mLoadingView.setVisibility(0);
                }
                this.mLoadingTitle.setText(Util.d(getResources(), this.mProgress));
                if (this.mProgress > 100) {
                    this.mLoadingProgress.setText("100%");
                    return;
                }
                TextView textView = this.mLoadingProgress;
                textView.setText("" + this.mProgress + Operators.MOD);
                this.mLoadingProgress.setTag(Integer.valueOf(this.mProgress));
                this.mHandler.removeMessages(4);
                this.mHandler.sendEmptyMessage(4);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void showLoading(String str) {
        if (!this.mCameraDevice.f().c()) {
            refreshUI();
        } else if (this.mLoadingView != null) {
            this.mLoadingView.bringToFront();
            this.mLoadingAnimation.start();
            this.mHandler.removeMessages(4);
            this.mLoadingProgress.setTag((Object) null);
            if (this.mProgress < 100) {
                this.mHandler.sendEmptyMessage(4);
            }
            this.mLoadingView.setVisibility(0);
            if (!this.mUpdating) {
                if (!TextUtils.isEmpty(str)) {
                    this.mLoadingTitle.setText(str);
                    this.mLoadingProgress.setText("0%");
                    this.mLoadingProgress.setTag((Object) null);
                    return;
                }
                this.mLoadingTitle.setText(R.string.camera_play_initial_buffer);
                this.mLoadingProgress.setText("");
                this.mLoadingProgress.setTag((Object) null);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void hideLoading() {
        if (this.mLoadingView != null) {
            if (!this.mUpdating || !NetworkUtil.a(XmPluginHostApi.instance().context())) {
                this.mLoadingAnimation.stop();
                this.mLoadingProgress.setText("");
                this.mLoadingProgress.setTag((Object) null);
                this.mHandler.removeMessages(4);
                this.mLoadingView.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void showUpdateIng(int i) {
        if (this.mLoadingView != null) {
            this.mUpdating = true;
            if (this.mLoadingView.getVisibility() != 0) {
                this.mLoadingAnimation.start();
                this.mLoadingView.setVisibility(0);
            }
            if (this.mPauseView != null && this.mPauseView.getVisibility() == 0) {
                this.mPauseView.setVisibility(8);
            }
            CharSequence text = this.mLoadingTitle.getText();
            if (text != null && !text.toString().equals(this.UPDATE_ING)) {
                if (this.mCameraDevice.n()) {
                    if (this.mCameraPlayerEx != null) {
                        this.mCameraPlayerEx.j();
                    }
                } else if (this.mCameraPlayer != null) {
                    this.mCameraPlayer.g();
                }
            }
            this.mLoadingTitle.setText(this.UPDATE_ING);
            TextView textView = this.mLoadingProgress;
            textView.setText("" + i + Operators.MOD);
            this.mLoadingProgress.setTag((Object) null);
        }
    }

    /* access modifiers changed from: protected */
    public void hideUpdateIng(boolean z) {
        CharSequence text;
        if (this.mLoadingView != null) {
            this.mUpdating = false;
            if (this.mLoadingView.getVisibility() == 0 && (text = this.mLoadingTitle.getText()) != null && text.toString().equals(this.UPDATE_ING)) {
                this.mLoadingAnimation.stop();
                this.mLoadingView.setVisibility(8);
                this.mLoadingProgress.setText("");
                this.mLoadingTitle.setText("");
                this.mLoadingProgress.setTag((Object) null);
                if (z) {
                    this.mHandler.postDelayed(new Runnable() {
                        public final void run() {
                            CameraPlayerBaseActivity.this.startPlay();
                        }
                    }, 3000);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void hidError() {
        if (this.mErrorRetryView != null && this.mErrorRetryView.getVisibility() == 0) {
            this.mErrorRetryView.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void showError(String str) {
        if (this.mCameraDevice.n()) {
            if (this.mCameraPlayerEx != null) {
                if (this.mCameraPlayerEx.r()) {
                    doStopRecord();
                }
                if (this.mCameraPlayerEx.t()) {
                    doStopCall();
                }
            }
        } else if (this.mCameraPlayer != null) {
            if (this.mCameraPlayer.m()) {
                doStopRecord();
            }
            if (this.mCameraPlayer.o()) {
                doStopCall();
            }
        }
        if (this.mPauseView != null && this.mPauseView.getVisibility() == 0) {
            this.mPauseView.setVisibility(8);
        }
        if (!this.mCameraDevice.f().c()) {
            if (this.mPowerOffView != null && this.mPowerOffView.getVisibility() == 8) {
                this.mPowerOffView.setVisibility(0);
                Log.d("cheng", "powerOff:" + Log.getStackTraceString(new Exception()));
            }
            this.mErrorRetryView.setVisibility(8);
        } else {
            if (this.mErrorRetryView.getVisibility() != 0) {
                if (this.mLoadingView == null || this.mLoadingView.getVisibility() != 0 || !((String) this.mLoadingTitle.getText()).equals(this.UPDATE_ING)) {
                    this.mErrorRetryView.setVisibility(0);
                    refreshUI();
                } else {
                    refreshUI();
                    return;
                }
            }
            this.mErrorInfoView.setText(str);
        }
        onPlayError();
    }

    /* access modifiers changed from: protected */
    public boolean isGPUDecoder() {
        return this.mVideoView != null && this.mVideoView.isGPUDecoder();
    }

    /* access modifiers changed from: protected */
    public void setWindow(Configuration configuration) {
        if (configuration.orientation == 2) {
            getWindow().setFlags(1024, 1024);
        } else {
            getWindow().clearFlags(1024);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dealRetry(boolean r11, int r12) {
        /*
            r10 = this;
            long r0 = java.lang.System.currentTimeMillis()
            long r2 = r10.mConnectStartTime
            long r0 = r0 - r2
            long r2 = r10.mConnectStartTime
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            r2 = 1
            r3 = 0
            if (r6 <= 0) goto L_0x0032
            r6 = 90000(0x15f90, double:4.4466E-319)
            int r8 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x0032
            if (r11 == 0) goto L_0x0026
            int r11 = r10.mRetryCount
            r6 = 3
            if (r11 >= r6) goto L_0x0032
            int r11 = r10.mRetryCount
            int r11 = r11 + r2
            r10.mRetryCount = r11
        L_0x0024:
            r11 = 1
            goto L_0x0033
        L_0x0026:
            int r11 = r10.mRetryCount
            r6 = 5
            if (r11 <= r6) goto L_0x002c
            goto L_0x0032
        L_0x002c:
            int r11 = r10.mRetryCount
            int r11 = r11 + r2
            r10.mRetryCount = r11
            goto L_0x0024
        L_0x0032:
            r11 = 0
        L_0x0033:
            if (r11 == 0) goto L_0x0042
            android.os.Handler r11 = r10.mHandler
            com.xiaomi.smarthome.camera.activity.-$$Lambda$CameraPlayerBaseActivity$Id4dgsHbXQa8HLuihwJBJqevgVY r0 = new com.xiaomi.smarthome.camera.activity.-$$Lambda$CameraPlayerBaseActivity$Id4dgsHbXQa8HLuihwJBJqevgVY
            r0.<init>(r12)
            r1 = 2000(0x7d0, double:9.88E-321)
            r11.postDelayed(r0, r1)
            goto L_0x0074
        L_0x0042:
            r10.hideLoading()
            r11 = 2131493769(0x7f0c0389, float:1.8611027E38)
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r6 = java.lang.Integer.valueOf(r12)
            r2[r3] = r6
            java.lang.String r11 = r10.getString(r11, r2)
            r10.showError(r11)
            int r11 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r11 <= 0) goto L_0x0070
            r6 = 120000(0x1d4c0, double:5.9288E-319)
            int r11 = r10.mRetryCount
            r2 = 60000(0xea60, float:8.4078E-41)
            int r11 = r11 * r2
            long r8 = (long) r11
            long r8 = r8 + r6
            int r11 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r11 >= 0) goto L_0x0070
            int r11 = r10.mRetryCount
            com.mijia.app.Event.a((int) r12, (int) r11, (long) r0)
        L_0x0070:
            r10.mConnectStartTime = r4
            r10.mRetryCount = r3
        L_0x0074:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.CameraPlayerBaseActivity.dealRetry(boolean, int):void");
    }

    public static /* synthetic */ void lambda$dealRetry$9(CameraPlayerBaseActivity cameraPlayerBaseActivity, int i) {
        LogUtil.a(Tag.b, "retry " + cameraPlayerBaseActivity.mRetryCount + " " + i);
        if (cameraPlayerBaseActivity.mCameraDevice.n()) {
            if (cameraPlayerBaseActivity.mCameraPlayerEx != null) {
                cameraPlayerBaseActivity.mCameraPlayerEx.n();
            }
        } else if (cameraPlayerBaseActivity.mCameraPlayer != null) {
            cameraPlayerBaseActivity.mCameraPlayer.j();
        }
    }

    public void onPauseCamera() {
        pauseCamera();
    }

    /* access modifiers changed from: protected */
    public void setIsConnected() {
        this.mConnected = true;
    }

    /* access modifiers changed from: protected */
    public void handleVideoPlayErrorOnMISS() {
        super.handleVideoPlayErrorOnMISS();
        if (this.mCameraPlayerEx != null && this.mHandler != null) {
            LogUtil.a(TAG, "run handleVideoPlayErrorOnMISS");
            this.mCameraPlayerEx.o();
            this.mHandler.removeCallbacks(this.delayedReconnectRunnable);
            this.mHandler.postDelayed(this.delayedReconnectRunnable, 5000);
        }
    }
}
