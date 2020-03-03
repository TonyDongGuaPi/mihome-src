package com.xiaomi.smarthome.camera.v4.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.Utils.FileUtil;
import com.Utils.NetworkMonitor;
import com.Utils.NetworkUtil;
import com.mijia.app.AppConfig;
import com.mijia.app.Event;
import com.mijia.camera.CameraPlayer;
import com.mijia.camera.CameraPlayerEx;
import com.mijia.camera.ICameraPlayerListener;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.mijia.model.local.LocalSettings;
import com.mijia.model.property.CameraPropertyBase;
import com.mijia.model.property.PropertyManger;
import com.mijia.model.sdcard.TimeItem;
import com.taobao.weex.el.parse.Operators;
import com.tutk.TuTkClient;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.IMISSListener;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.v4.view.TimeLineWithDatePickView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class CameraPlayerBaseActivity extends CameraBaseActivity implements View.OnClickListener, ICameraPlayerListener {
    public static final int MSG_UPDATE_PLAY_PROGRESS = 4;
    public static final int MSG_UPDATE_PLAY_TIME = 2;
    protected static final int PIC_SHOW_DURATION = 3000;
    public static final int SPEED_MISS = 4000;
    private static final String TAG = "CameraPlayerBaseActivity";
    public static final String TIME_STAMP = "time";
    private static boolean isAppConfigInit = false;
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
    public boolean isConnect = false;
    protected boolean isPowerCheck = false;
    private boolean isUpdateIng = false;
    protected boolean mAllowMobileNetwork = false;
    CheckBox mCBMuteLandscape;
    CheckBox mCBVoiceLandscape;
    protected CameraPlayer mCameraPlayer;
    protected CameraPlayerEx mCameraPlayerEx;
    /* access modifiers changed from: private */
    public long mConnectStartTime = 0;
    /* access modifiers changed from: private */
    public long mEmptyTime = 0;
    protected TextView mErrorInfoView;
    protected View mErrorRetryView;
    TextView mFrameRate;
    protected boolean mFullScreen;
    protected View mHintView;
    protected boolean mIsUserPause = false;
    LinearLayout mLLRightCtrlLandscape;
    protected AnimationDrawable mLoadingAnimation;
    protected ImageView mLoadingImageView;
    protected TextView mLoadingProgress;
    protected TextView mLoadingTitle;
    protected View mLoadingView;
    protected boolean mNeedPincode = false;
    protected boolean mNeedSpeed = false;
    boolean mNeedTry = false;
    NetworkMonitor mNetworkMonitor = new NetworkMonitor();
    NetworkMonitor.OnNetworkChange mOnNetworkChange = new NetworkMonitor.OnNetworkChange() {
        public void onChange(int i) {
        }

        public void onConnecting(int i) {
        }

        public void onDisconnected(int i) {
            CameraPlayerBaseActivity.this.showError(CameraPlayerBaseActivity.this.getString(R.string.common_net_error));
        }

        public void onConnected(int i) {
            if ((!NetworkUtil.c(CameraPlayerBaseActivity.this) || CameraPlayerBaseActivity.this.mAllowMobileNetwork) && !CameraPlayerBaseActivity.this.mIsUserPause) {
                if (CameraPlayerBaseActivity.this.mCameraPlayer != null && CameraPlayerBaseActivity.this.isConnect) {
                    CameraPlayerBaseActivity.this.hidError();
                    CameraPlayerBaseActivity.this.doResume();
                }
                if (CameraPlayerBaseActivity.this.mCameraPlayerEx != null && CameraPlayerBaseActivity.this.isConnect) {
                    CameraPlayerBaseActivity.this.hidError();
                    CameraPlayerBaseActivity.this.doResume();
                    return;
                }
                return;
            }
            CameraPlayerBaseActivity.this.hidError();
            CameraPlayerBaseActivity.this.pauseCamera();
        }
    };
    public View mPauseView;
    protected View mPowerOffView;
    private int mProgress = 0;
    protected PropertyManger.OnPropertyChangeListener mPropertyChangeListener = new PropertyManger.OnPropertyChangeListener() {
        public void onPropertyChanged(CameraDevice cameraDevice, final HashSet<String> hashSet) {
            CameraPlayerBaseActivity.this.mHandler.post(new Runnable() {
                public void run() {
                    if (hashSet.contains(CameraPropertyBase.l)) {
                        SDKLog.e(Tag.b, "power change");
                        CameraPlayerBaseActivity.this.refreshUI();
                        if (NetworkUtil.b(CameraPlayerBaseActivity.this) || (NetworkUtil.c(CameraPlayerBaseActivity.this) && CameraPlayerBaseActivity.this.mAllowMobileNetwork)) {
                            if (CameraPlayerBaseActivity.this.mCameraDevice.f().c() && CameraPlayerBaseActivity.this.mCameraPlayer != null && !CameraPlayerBaseActivity.this.mCameraPlayer.d()) {
                                CameraPlayerBaseActivity.this.doResume();
                            }
                            if (CameraPlayerBaseActivity.this.mCameraDevice.f().c() && CameraPlayerBaseActivity.this.mCameraPlayerEx != null && !CameraPlayerBaseActivity.this.mCameraPlayerEx.g()) {
                                CameraPlayerBaseActivity.this.doResume();
                            }
                        }
                    } else if (hashSet.contains(LocalSettings.b)) {
                        CameraPlayerBaseActivity.this.mAllowMobileNetwork = !CameraPlayerBaseActivity.this.mCameraDevice.e().b();
                    } else if (hashSet.contains("track") && CameraPlayerBaseActivity.this.mCameraDevice != null && CameraPlayerBaseActivity.this.mCameraDevice.f().g() && CameraPlayerBaseActivity.this.mFrameRate != null) {
                        CameraPlayerBaseActivity.this.mFrameRate.setText(CameraPlayerBaseActivity.this.getString(R.string.is_move_track));
                    }
                }
            });
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
    TextView mTVExitFullScreen;
    protected TimeLineWithDatePickView mTimeLineControlView;
    protected TextView mTitleView;
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
    public abstract void refreshUI();

    /* access modifiers changed from: protected */
    public abstract void resumeCamera();

    /* access modifiers changed from: protected */
    public boolean shouldRecordPlayTime() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract void startPlay();

    static /* synthetic */ int access$2910(CameraPlayerBaseActivity cameraPlayerBaseActivity) {
        int i = cameraPlayerBaseActivity.mPwdCount;
        cameraPlayerBaseActivity.mPwdCount = i - 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        TitleBarUtil.b((Activity) this);
        getWindow().clearFlags(2048);
        this.mAllowMobileNetwork = !this.mCameraDevice.e().b();
        this.UPDATE_ING = getResources().getString(R.string.update_ing);
        if (!isAppConfigInit) {
            AppConfig.a((Context) this);
            isAppConfigInit = true;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mConnectStartTime = 0;
        if (this.mCameraDevice != null) {
            this.mCameraDevice.g().b(this.mPropertyChangeListener);
        }
    }

    public void onResume() {
        super.onResume();
        if (!this.mIsUserPause && this.mCameraPlayer != null && this.mCameraPlayer.h()) {
            if (!NetworkUtil.d(this)) {
                pauseCamera();
                showError(getString(R.string.common_net_error));
            } else if (!NetworkUtil.c(this) || this.mAllowMobileNetwork) {
                doResume();
            } else {
                pauseCamera();
            }
        }
        if (!this.mIsUserPause && this.mCameraPlayerEx != null && this.mCameraPlayerEx.k()) {
            if (!NetworkUtil.d(this)) {
                pauseCamera();
                showError(getString(R.string.common_net_error));
            } else if (!NetworkUtil.c(this) || this.mAllowMobileNetwork) {
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
        if (this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.j();
        }
        recordVideoPlayTime();
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what != 4) {
            return;
        }
        if (!this.isUpdateIng || this.mLoadingView.getVisibility() != 0) {
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
        SDKLog.d(Tag.b, "prepare " + i);
        this.mProgress = i;
        if (i == 5 && this.mRetryCount == 0) {
            this.mConnectStartTime = System.currentTimeMillis();
        }
        if (i >= 101) {
            if (this.mVideoView != null) {
                this.mVideoView.setTouch(true);
            }
            if (i == 101 && this.mCameraPlayer != null && !isHistory() && this.mCameraPlayer.c()) {
                this.mCameraPlayer.c(6);
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        CameraPlayerBaseActivity.this.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
                            public void onSnap(Bitmap bitmap) {
                                if (bitmap != null) {
                                    try {
                                        FileOutputStream fileOutputStream = new FileOutputStream(FileUtil.b(CameraPlayerBaseActivity.this.mCameraDevice.getDid()));
                                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                                        CameraPlayerBaseActivity.this.mCameraDevice.e().e(false);
                                        fileOutputStream.close();
                                        bitmap.recycle();
                                    } catch (IOException unused) {
                                    }
                                }
                            }
                        });
                    }
                }, 2000);
            }
            if (i == 101 && this.mCameraPlayerEx != null && !isHistory() && this.mCameraPlayerEx.f()) {
                this.mCameraPlayerEx.b(6, (IMISSListener) null);
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        CameraPlayerBaseActivity.this.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
                            public void onSnap(Bitmap bitmap) {
                                if (bitmap != null) {
                                    try {
                                        FileOutputStream fileOutputStream = new FileOutputStream(FileUtil.b(CameraPlayerBaseActivity.this.mCameraDevice.getDid()));
                                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                                        CameraPlayerBaseActivity.this.mCameraDevice.e().e(false);
                                        fileOutputStream.close();
                                        bitmap.recycle();
                                    } catch (IOException unused) {
                                    }
                                }
                            }
                        });
                    }
                }, 2000);
            }
            if (this.mConnectStartTime > 0) {
                final long currentTimeMillis = System.currentTimeMillis() - this.mConnectStartTime;
                this.mConnectStartTime = 0;
                if (currentTimeMillis > 0 && currentTimeMillis < ((long) (this.mRetryCount * 60000)) + 120000) {
                    Event.a(Event.f7858a, "type", (Object) Integer.valueOf((int) currentTimeMillis));
                    this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            String str = "";
                            if (CameraPlayerBaseActivity.this.mCameraDevice.u() != null) {
                                ArrayList<Pair> arrayList = new ArrayList<>();
                                ((TuTkClient) CameraPlayerBaseActivity.this.mCameraDevice.u()).getP2PInfo(arrayList);
                                if (!arrayList.isEmpty()) {
                                    for (Pair pair : arrayList) {
                                        if (((String) pair.first).equals("P2P_Mode")) {
                                            str = (String) pair.second;
                                        }
                                    }
                                }
                            }
                            Event.a(1, CameraPlayerBaseActivity.this.mRetryCount, currentTimeMillis, str);
                        }
                    }, 800);
                }
            }
            if (!this.mShowView) {
                this.mShowView = true;
                int i2 = Build.VERSION.SDK_INT;
                if (i2 >= 16 && !isGPUDecoder()) {
                    String str = Event.S;
                    Event.a(str, "type", (Object) Build.MODEL + " SDK:" + i2);
                }
            }
            this.mRetryCount = 0;
            hideLoading();
            hidError();
            refreshUI();
            SDKLog.e(Tag.b, "onPrepare finish");
            this.startPlayTimeStamp = System.currentTimeMillis();
        } else if (i != 0) {
        } else {
            if (this.mLoadingView.getVisibility() != 0 || this.mRetryCount == 0) {
                showLoading(getString(R.string.camera_play_initial_0));
            }
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
                SDKLog.e(Tag.b, "PlayError PWD");
                this.mRetryCount++;
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        if (CameraPlayerBaseActivity.this.mRetryCount <= 3) {
                            CameraPlayerBaseActivity.this.updatePwd();
                            return;
                        }
                        CameraPlayerBaseActivity.this.hideLoading();
                        CameraPlayerBaseActivity.this.showError(CameraPlayerBaseActivity.this.getString(R.string.camera_play_error1, new Object[]{Integer.valueOf(i)}));
                        long currentTimeMillis = System.currentTimeMillis() - CameraPlayerBaseActivity.this.mConnectStartTime;
                        if (currentTimeMillis > 0 && currentTimeMillis < 120000) {
                            Event.a(i, CameraPlayerBaseActivity.this.mRetryCount, currentTimeMillis);
                        }
                        long unused = CameraPlayerBaseActivity.this.mConnectStartTime = 0;
                        int unused2 = CameraPlayerBaseActivity.this.mRetryCount = 0;
                    }
                }, 5000);
            } else if (this.isUpdateIng && this.mLoadingView.getVisibility() == 0) {
                return;
            } else {
                if (!this.mCameraDevice.deviceStat().isOnline) {
                    hideLoading();
                    showError(getString(R.string.device_offline));
                    Event.a(-1, 0, 0);
                } else if (i == -90) {
                    if (this.mCameraDevice.deviceStat().isOnline) {
                        this.mCameraDevice.c((Callback<Void>) null);
                    }
                    dealRetry(false, i);
                } else if (!this.isPowerCheck) {
                    this.mCameraDevice.f().a(new String[]{CameraPropertyBase.l, CameraPropertyBase.p}, (Callback<Void>) new Callback<Void>() {
                        public void onSuccess(Void voidR) {
                            if (!CameraPlayerBaseActivity.this.isFinishing()) {
                                CameraPlayerBaseActivity.this.isPowerCheck = true;
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

    public void onVideoInfo(int i, int i2, int i3, final int i4, int i5) {
        if (this.mCameraDevice.f().c()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    if (!(CameraPlayerBaseActivity.this.mFrameRate == null || CameraPlayerBaseActivity.this.mFrameRate.getVisibility() == 0)) {
                        CameraPlayerBaseActivity.this.mFrameRate.setVisibility(0);
                    }
                    if (CameraPlayerBaseActivity.this.mFrameRate == null) {
                        return;
                    }
                    if (CameraPlayerBaseActivity.this.mCameraDevice == null || !CameraPlayerBaseActivity.this.mCameraDevice.f().g()) {
                        TextView textView = CameraPlayerBaseActivity.this.mFrameRate;
                        textView.setText("" + i4 + CameraPlayerBaseActivity.this.getString(R.string.bps));
                        return;
                    }
                    CameraPlayerBaseActivity.this.mFrameRate.setText(CameraPlayerBaseActivity.this.getString(R.string.is_move_track));
                }
            });
        }
    }

    public void onConnected() {
        SDKLog.d(TAG, "onConnected");
        this.isConnect = true;
        detectSDCard();
        if (this.mCameraPlayer != null && this.mCameraPlayer.h()) {
            this.mCameraPlayer.g();
        }
        if (this.mCameraPlayerEx != null && this.mCameraPlayerEx.k()) {
            this.mCameraPlayerEx.j();
        }
        if (this.mCameraDevice == null || !this.mCameraDevice.n()) {
            this.mCameraDevice.c().j();
            this.mCameraDevice.c().a(40000);
            List<TimeItem> i = this.mCameraDevice.c().i();
            if (this.mTimeLineControlView != null) {
                this.mTimeLineControlView.setTimeItems(i);
                return;
            }
            return;
        }
        this.mCameraDevice.d().j();
        this.mCameraDevice.d().a(40000);
        List<TimeItem> i2 = this.mCameraDevice.d().i();
        if (this.mTimeLineControlView != null) {
            this.mTimeLineControlView.setTimeItems(i2);
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

    public void onStarted() {
        this.mHandler.post(new Runnable() {
            public void run() {
                if (!CameraPlayerBaseActivity.this.isHistory() && CameraPlayerBaseActivity.this.mCameraDevice != null) {
                    if (CameraPlayerBaseActivity.this.mCameraDevice.n()) {
                        CameraPlayerBaseActivity.this.mCameraDevice.d().a((Callback<Void>) null, true);
                    } else {
                        CameraPlayerBaseActivity.this.mCameraDevice.c().a((Callback<Void>) null, true);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void updatePwd() {
        onPrepare(0);
        String str = "";
        if (XmPluginHostApi.instance().getApiLevel() >= 32) {
            str = XmPluginHostApi.instance().getDevicePincode(this.mDeviceStat.did);
        }
        if (TextUtils.isEmpty(str) && !this.mCameraDevice.m()) {
            this.mNeedTry = true;
        }
        SDKLog.e(Tag.b, "start upd");
        this.mCameraDevice.f(new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!CameraPlayerBaseActivity.this.isFinishing()) {
                    SDKLog.e(Tag.b, "updatePwd:onSuccess");
                    CameraPlayerBaseActivity.this.mCameraDevice.u().updateInfo(CameraPlayerBaseActivity.this.mCameraDevice.t());
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
                                public void run() {
                                    CameraPlayerBaseActivity.this.updatePwd();
                                }
                            }, 1000);
                            return;
                        }
                    }
                    SDKLog.e(Tag.b, "updatePwd:onFailure:" + str + " uid: " + CameraPlayerBaseActivity.this.mCameraDevice.r() + " pwd:" + CameraPlayerBaseActivity.this.mCameraDevice.w());
                    if (!CameraPlayerBaseActivity.this.mNeedTry || CameraPlayerBaseActivity.this.mPwdCount <= 0) {
                        CameraPlayerBaseActivity.this.hideLoading();
                        CameraPlayerBaseActivity.this.showError(CameraPlayerBaseActivity.this.getString(R.string.camera_play_error2));
                        return;
                    }
                    CameraPlayerBaseActivity.access$2910(CameraPlayerBaseActivity.this);
                    CameraPlayerBaseActivity.this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            CameraPlayerBaseActivity.this.updatePwd();
                        }
                    }, 3000);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void pauseCamera() {
        if (this.mPauseView != null && this.mPauseView.getVisibility() == 8 && this.mDeviceStat.isOnline) {
            this.mPauseView.setVisibility(0);
        }
        if (this.mCameraPlayer != null) {
            this.mCameraPlayer.g();
        }
        if (this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.j();
        }
        if (NetworkUtil.c(this) && !this.mAllowMobileNetwork) {
            ToastUtil.a((Context) this, (int) R.string.nowifi_pausing);
        }
        hideLoading();
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
            this.mIsUserPause = false;
            resumeCamera();
        } else if (id == R.id.retry_btn) {
            this.mRetryCount = 0;
            if (!NetworkUtil.d(this)) {
                showError(getString(R.string.common_net_error));
            } else if (!NetworkUtil.c(this) || this.mAllowMobileNetwork) {
                hidError();
                showLoading(getString(R.string.camera_play_initial_0));
                if (this.mCameraPlayer != null) {
                    this.mCameraPlayer.j();
                } else if (this.mCameraPlayerEx != null) {
                    this.mCameraPlayerEx.n();
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
        if (this.mLoadingView.getVisibility() != 0) {
            this.mLoadingAnimation.start();
            this.mLoadingView.setVisibility(0);
            this.mLoadingTitle.setText(R.string.camera_play_initial_buffer);
            this.mLoadingProgress.setText("");
        }
    }

    /* access modifiers changed from: protected */
    public void showLastLoading() {
        if (!this.isUpdateIng || this.mLoadingView.getVisibility() != 0) {
            if (this.mLoadingView.getVisibility() != 0) {
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

    /* access modifiers changed from: protected */
    public void showLoading(String str) {
        if (this.mLoadingView.getVisibility() != 0) {
            this.mLoadingAnimation.start();
            this.mHandler.removeMessages(4);
            this.mLoadingProgress.setTag((Object) null);
            if (this.mProgress < 100) {
                this.mHandler.sendEmptyMessage(4);
            }
            this.mLoadingView.setVisibility(0);
        }
        if (!this.isUpdateIng) {
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

    /* access modifiers changed from: protected */
    public void hideLoading() {
        if (!this.isUpdateIng || !NetworkUtil.a((Context) this)) {
            this.mLoadingAnimation.stop();
            this.mLoadingView.setVisibility(8);
            this.mLoadingProgress.setText("");
            this.mLoadingProgress.setTag((Object) null);
            this.mHandler.removeMessages(4);
        }
    }

    /* access modifiers changed from: protected */
    public void showUpdateIng(int i) {
        this.isUpdateIng = true;
        if (this.mLoadingView.getVisibility() != 0) {
            this.mLoadingAnimation.start();
            this.mLoadingView.setVisibility(0);
        }
        if (this.mPauseView != null && this.mPauseView.getVisibility() == 0) {
            this.mPauseView.setVisibility(8);
        }
        CharSequence text = this.mLoadingTitle.getText();
        if (text != null && !text.toString().equals(this.UPDATE_ING)) {
            if (this.mCameraPlayer != null) {
                this.mCameraPlayer.g();
            }
            if (this.mCameraPlayerEx != null) {
                this.mCameraPlayerEx.j();
            }
        }
        this.mLoadingTitle.setText(this.UPDATE_ING);
        TextView textView = this.mLoadingProgress;
        textView.setText("" + i + Operators.MOD);
        this.mLoadingProgress.setTag((Object) null);
    }

    /* access modifiers changed from: protected */
    public void hideUpdateIng(boolean z) {
        CharSequence text;
        this.isUpdateIng = false;
        if (this.mLoadingView.getVisibility() == 0 && (text = this.mLoadingTitle.getText()) != null && text.toString().equals(this.UPDATE_ING)) {
            this.mLoadingAnimation.stop();
            this.mLoadingView.setVisibility(8);
            this.mLoadingProgress.setText("");
            this.mLoadingTitle.setText("");
            this.mLoadingProgress.setTag((Object) null);
            if (z) {
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        CameraPlayerBaseActivity.this.startPlay();
                    }
                }, 3000);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void hidError() {
        if (this.mErrorRetryView.getVisibility() == 0) {
            this.mErrorRetryView.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void showError(String str) {
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
        if (this.mPauseView.getVisibility() == 0) {
            this.mPauseView.setVisibility(8);
        }
        if (!this.mCameraDevice.f().c()) {
            if (this.mPowerOffView.getVisibility() == 8) {
                this.mPowerOffView.setVisibility(0);
            }
            this.mErrorRetryView.setVisibility(8);
            return;
        }
        if (this.mErrorRetryView.getVisibility() != 0) {
            if (this.mLoadingView.getVisibility() != 0 || !((String) this.mLoadingTitle.getText()).equals(this.UPDATE_ING)) {
                this.mErrorRetryView.setVisibility(0);
                refreshUI();
            } else {
                refreshUI();
                return;
            }
        }
        this.mErrorInfoView.setText(str);
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
    public void dealRetry(boolean r11, final int r12) {
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
            com.xiaomi.smarthome.camera.v4.activity.CameraPlayerBaseActivity$13 r0 = new com.xiaomi.smarthome.camera.v4.activity.CameraPlayerBaseActivity$13
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
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.v4.activity.CameraPlayerBaseActivity.dealRetry(boolean, int):void");
    }

    private void retry() {
        this.mRetryCount = 0;
        if (!NetworkUtil.d(this)) {
            showError(getString(R.string.common_net_error));
        } else if (!NetworkUtil.c(this) || this.mAllowMobileNetwork) {
            hidError();
            showLoading(getString(R.string.camera_play_initial_0));
            if (this.mCameraPlayer != null) {
                this.mCameraPlayer.j();
            } else if (this.mCameraPlayerEx != null) {
                this.mCameraPlayerEx.n();
            } else {
                startPlay();
            }
        } else {
            hidError();
            pauseCamera();
        }
    }

    public void onPauseCamera() {
        pauseCamera();
    }

    /* access modifiers changed from: protected */
    public void toggleRemoteAV(boolean z, boolean z2) {
        if (this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.a(z, (IMISSListener) null);
            this.mCameraPlayerEx.b(z2, (IMISSListener) null);
        }
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
