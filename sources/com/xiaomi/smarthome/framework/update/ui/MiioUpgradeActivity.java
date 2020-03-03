package com.xiaomi.smarthome.framework.update.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import com.google.android.exoplayer2.C;
import com.hannto.printservice.hanntoprintservice.entity.PrinterParmater;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.runtime.util.TitleBarUtil;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.update.api.UpdateApi;
import com.xiaomi.smarthome.framework.update.api.entity.MiioUpdateInfo;
import com.xiaomi.smarthome.library.common.widget.PieProgressBar;
import com.xiaomi.smarthome.miio.activity.DeviceOfflineDetailActivity;
import com.xiaomi.smarthome.miio.update.ModelUpdateInfo;
import java.lang.reflect.Method;
import org.json.JSONArray;
import org.json.JSONObject;

public class MiioUpgradeActivity extends BaseActivity {
    public static int MAX_ELAPSE_PERCENT = 90;
    public static final int MAX_INSTALLING_COUNT = 90;
    public static String MIIO_UPGRADE_DID = "miio_upgrade_did";
    public static String MIIO_UPGRADE_NAME = "miio_upgrade_name";
    public static String MIIO_UPGRADE_PID = "miio_upgrade_pid";
    public static int MIIO_UPGRADE_TIME = 2000;
    public static final int REFRESH_UI_MSG = 10002;
    public static int TIMEOUT_INVALID_TIME = 5;
    public static final int UPDATE_INFO_MSG = 10001;

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f17798a = {PrinterParmater.i, "downloading", "downloaded", "wait_install", "installing", "installed", "failed", "busy"};
    private static final String b = "MiioUpgradeActivity";
    private static final String d = "force_fsg_nav_bar";
    private boolean c = false;
    ValueAnimator mAnimator;
    Button mBtnBottom;
    String mDestVersion;
    Device mDevice;
    int mDisplayProgress = 0;
    DISPLAY_STATE mDisplayState;
    ModelUpdateInfo mInfo;
    int mInstallingCount = 0;
    ProgressBar mInstallingProgressBar;
    boolean mIsFailure = false;
    boolean mIsPollingInfo = false;
    int mLastDisplayProgress = 0;
    OTA_STATE mLastState;
    View mLoadingView;
    MiioUpdateInfo mMiioUpdateInfo;
    PieProgressBar mPbProgress;
    int mRetartCount = 0;
    int mRetryCount = -1;
    boolean mTimeout = false;
    TextView mTxtProgress;
    TextView mTxtUpdateDesc;
    TextView mTxtUpdateTitle;
    TextView mTxtUpdatingTip;
    boolean mUpdateSucc = false;
    ViewSwitcher mVsRoot;

    enum DISPLAY_STATE {
        DISPLAY_IDLE,
        DISPLAY_DOWNLOADING,
        DISPLAY_DOWNLOADED,
        DISPLAY_WAIT_INSTALL,
        DISPLAY_INSTALLING,
        DISPLAY_INSTALLED,
        DISPLAY_FAILED,
        DISPLAY_BUSY,
        DISPLAY_FINISHED
    }

    enum OTA_STATE {
        OTA_STATE_IDLE,
        OTA_STATE_DOWNLOADING,
        OTA_STATE_DOWNLOADED,
        OTA_STATE_WAIT_INSTALL,
        OTA_STATE_INSTALLING,
        OTA_STATE_INSTALLED,
        OTA_STATE_FAILED,
        OTA_STATE_BUSY
    }

    /* access modifiers changed from: package-private */
    public void greyLog(String str) {
    }

    public void handleMessage(Message message) {
        if (message.what == 10001) {
            StringBuilder sb = new StringBuilder();
            sb.append("handleMessage UPDATE_INFO_MSG ,args: ( ");
            boolean z = true;
            sb.append(message.arg1 != 0);
            sb.append(",");
            int i = -1;
            sb.append(message.arg2 == 0 ? -1 : message.arg2);
            sb.append(" )");
            greyLog(sb.toString());
            if (message.arg1 == 0) {
                z = false;
            }
            if (message.arg2 != 0) {
                i = message.arg2;
            }
            updateModelUpdateInfo(z, i);
        } else if (message.what == 10002) {
            greyLog("handleMessage REFRESH_UI_MSG ");
            h();
        }
        super.handleMessage(message);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        greyLog("onCreate ");
        setContentView(R.layout.miio_setting_update);
        Intent intent = getIntent();
        this.mInfo = new ModelUpdateInfo();
        this.mInfo.b = intent.getStringExtra(MIIO_UPGRADE_DID);
        this.mInfo.c = intent.getIntExtra(MIIO_UPGRADE_PID, 0);
        this.mInfo.d = intent.getStringExtra(MIIO_UPGRADE_NAME);
        this.mDevice = SmartHomeDeviceManager.a().b(this.mInfo.b);
        if (this.mDevice == null) {
            finish();
            return;
        }
        a();
        this.mVsRoot = (ViewSwitcher) findViewById(R.id.vs_root);
        this.mLoadingView = findViewById(R.id.ll_loading);
        this.mPbProgress = (PieProgressBar) findViewById(R.id.pb_progress);
        this.mTxtProgress = (TextView) findViewById(R.id.txt_progress);
        this.mTxtUpdateTitle = (TextView) findViewById(R.id.txt_update_title);
        this.mTxtUpdateDesc = (TextView) findViewById(R.id.txt_update_desc);
        this.mTxtUpdatingTip = (TextView) findViewById(R.id.txt_updating_tip);
        this.mBtnBottom = (Button) findViewById(R.id.btn_bottom);
        this.mPbProgress.setPercentView(this.mTxtProgress);
        this.mInstallingProgressBar = (ProgressBar) findViewById(R.id.installing_progress_bar);
        if (!this.mDevice.isOnline) {
            getWindow().getDecorView().post(new Runnable() {
                public void run() {
                    MiioUpgradeActivity.this.b();
                }
            });
        }
        e();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        greyLog("onResume: ");
        super.onResume();
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 10001;
        obtainMessage.arg1 = 0;
        this.mHandler.sendMessage(obtainMessage);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        greyLog("onPause: ");
        if (this.mAnimator != null) {
            this.mAnimator.cancel();
        }
        this.mHandler.removeMessages(10001);
        this.mHandler.removeMessages(10002);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        greyLog("onDestroy: ");
        CoreApi.a().a(this.mInfo.b, false);
    }

    private void a() {
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiioUpgradeActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(this.mInfo.d);
    }

    /* access modifiers changed from: private */
    public void b() {
        FrameLayout frameLayout = (FrameLayout) getWindow().getDecorView();
        final ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.device_offline_floating_view, (ViewGroup) null);
        viewGroup.setClickable(false);
        viewGroup.findViewById(R.id.read_detail_tv).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MiioUpgradeActivity.this, DeviceOfflineDetailActivity.class);
                if (MiioUpgradeActivity.this.mDevice != null) {
                    intent.putExtra("extra_model", MiioUpgradeActivity.this.mDevice.model);
                }
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                MiioUpgradeActivity.this.getApplicationContext().startActivity(intent);
            }
        });
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        Resources resources = getResources();
        if (TitleBarUtil.TRANSLUCENT_STATUS_ENABLED) {
            layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.std_titlebar_height) + resources.getDimensionPixelOffset(R.dimen.title_bar_top_padding);
        } else {
            layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.std_titlebar_height) + resources.getDimensionPixelOffset(R.dimen.title_bar_top_padding) + resources.getDimensionPixelOffset(R.dimen.title_bar_top_padding);
        }
        this.c = d();
        if (this.c) {
            if (Build.VERSION.SDK_INT < 24 || !isInMultiWindowMode()) {
                layoutParams.bottomMargin = a((Context) this);
            } else {
                layoutParams.bottomMargin = 0;
            }
        }
        frameLayout.addView(viewGroup, layoutParams);
        viewGroup.findViewById(R.id.delete_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiioUpgradeActivity.this.b(viewGroup.findViewById(R.id.bottom_rl));
            }
        });
        a(viewGroup.findViewById(R.id.bottom_rl));
    }

    private int a(Context context) {
        if (!hasNavBar(context)) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    public static boolean hasNavBar(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (identifier == 0) {
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
        boolean z = resources.getBoolean(identifier);
        String c2 = c();
        if ("1".equals(c2)) {
            return false;
        }
        if ("0".equals(c2)) {
            return true;
        }
        return z;
    }

    private static String c() {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class});
                declaredMethod.setAccessible(true);
                return (String) declaredMethod.invoke((Object) null, new Object[]{"qemu.hw.mainkeys"});
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    private boolean d() {
        if (Build.VERSION.SDK_INT >= 17 && Settings.Global.getInt(getContentResolver(), d, 0) != 0) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            Point point = new Point();
            Point point2 = new Point();
            defaultDisplay.getSize(point);
            defaultDisplay.getRealSize(point2);
            if (point2.y != point.y) {
                return true;
            }
            return false;
        }
        boolean hasPermanentMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey();
        boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
        if (hasPermanentMenuKey || deviceHasKey) {
            return false;
        }
        return true;
    }

    private void a(final View view) {
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                view.setVisibility(0);
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                int bottom = viewGroup.getBottom() - viewGroup.getTop();
                float f = MiioUpgradeActivity.this.getResources().getDisplayMetrics().density;
                float f2 = (float) bottom;
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.Y, new float[]{f2, f2 - (f * 70.0f)});
                ofFloat.setInterpolator(new TimeInterpolator() {
                    public float getInterpolation(float f) {
                        if (f <= 0.8f) {
                            double d = (double) f;
                            Double.isNaN(d);
                            return (float) (d * 1.5d);
                        }
                        double d2 = (double) f;
                        Double.isNaN(d2);
                        return (float) (2.0d - d2);
                    }
                });
                ofFloat.setDuration(600);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(ofFloat);
                animatorSet.start();
            }
        }, 1000);
    }

    /* access modifiers changed from: private */
    public void b(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.Y, new float[]{(float) (viewGroup.getHeight() - view.getHeight()), (float) viewGroup.getHeight()});
        ofFloat.setDuration(500);
        ofFloat.start();
    }

    private void e() {
        if (this.mVsRoot.getCurrentView() != this.mLoadingView) {
            this.mVsRoot.showNext();
        }
        greyLog("showLoading");
    }

    private void f() {
        if (this.mVsRoot.getCurrentView() == this.mLoadingView) {
            this.mVsRoot.showNext();
        }
        greyLog("showInfo");
    }

    /* access modifiers changed from: package-private */
    public void updateModelUpdateInfo(final boolean z, final int i) {
        greyLog("updateModelUpdateInfo, args:( ignoreError :" + z + ",retryCount: " + i + " ); mIsPollingInfo: " + this.mIsPollingInfo);
        if (!this.mIsPollingInfo) {
            this.mIsPollingInfo = true;
            UpdateApi.a().f(this, this.mInfo.b, this.mInfo.c, new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (MiioUpgradeActivity.this.isValid()) {
                        Log.d("ABC", "result " + jSONObject);
                        MiioUpgradeActivity miioUpgradeActivity = MiioUpgradeActivity.this;
                        miioUpgradeActivity.greyLog("updateModelUpdateInfo result: " + jSONObject);
                        MiioUpgradeActivity.this.mIsPollingInfo = false;
                        try {
                            MiioUpgradeActivity.this.a(jSONObject, i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(Error error) {
                    if (MiioUpgradeActivity.this.isValid()) {
                        Log.d("ABC", "update info error " + error.b());
                        MiioUpgradeActivity miioUpgradeActivity = MiioUpgradeActivity.this;
                        miioUpgradeActivity.greyLog("update info error " + error.b() + "; ignoreError: " + z);
                        MiioUpgradeActivity.this.mIsPollingInfo = false;
                        if (!z) {
                            MiioUpgradeActivity.this.mIsFailure = true;
                            MiioUpgradeActivity.this.mTimeout = true;
                            CoreApi.a().a(MiioUpgradeActivity.this.mInfo.b, false);
                            MiioUpgradeActivity.this.mDisplayState = DISPLAY_STATE.DISPLAY_FAILED;
                            MiioUpgradeActivity.this.mHandler.sendEmptyMessage(10002);
                        } else if (i == -1) {
                            Message obtainMessage = MiioUpgradeActivity.this.mHandler.obtainMessage();
                            obtainMessage.what = 10001;
                            obtainMessage.arg1 = 1;
                            MiioUpgradeActivity.this.mHandler.sendMessageDelayed(obtainMessage, (long) MiioUpgradeActivity.MIIO_UPGRADE_TIME);
                        } else {
                            int i = i - 1;
                            if (i > 0) {
                                Log.d("ABC", "retry " + i);
                                MiioUpgradeActivity miioUpgradeActivity2 = MiioUpgradeActivity.this;
                                miioUpgradeActivity2.greyLog("retry " + i);
                                Message obtainMessage2 = MiioUpgradeActivity.this.mHandler.obtainMessage();
                                obtainMessage2.what = 10001;
                                obtainMessage2.arg1 = 1;
                                obtainMessage2.arg2 = i;
                                MiioUpgradeActivity.this.mHandler.sendMessageDelayed(obtainMessage2, (long) MiioUpgradeActivity.MIIO_UPGRADE_TIME);
                                return;
                            }
                            MiioUpgradeActivity.this.mTimeout = true;
                            CoreApi.a().a(MiioUpgradeActivity.this.mInfo.b, false);
                            MiioUpgradeActivity.this.mHandler.sendEmptyMessage(10002);
                        }
                    }
                }
            });
        }
    }

    private OTA_STATE a(MiioUpdateInfo miioUpdateInfo) {
        OTA_STATE ota_state = this.mLastState == null ? OTA_STATE.OTA_STATE_IDLE : this.mLastState;
        for (int i = 0; i < f17798a.length; i++) {
            if (TextUtils.equals(miioUpdateInfo.g, f17798a[i])) {
                if (i != 0 || miioUpdateInfo.f <= 0 || miioUpdateInfo.f > 100) {
                    ota_state = OTA_STATE.values()[i];
                } else {
                    ota_state = OTA_STATE.values()[i + 1];
                }
            }
        }
        return ota_state;
    }

    /* access modifiers changed from: private */
    public void a(JSONObject jSONObject, int i) {
        MiioUpdateInfo a2 = MiioUpdateInfo.a(jSONObject);
        if (a2 == null) {
            Log.d("ABC", "failed null ");
            greyLog("这个分支进不去");
            if (this.mDisplayState == null) {
                this.mIsFailure = true;
                this.mDisplayState = DISPLAY_STATE.DISPLAY_FAILED;
            } else if (this.mMiioUpdateInfo != null) {
                g();
            } else {
                this.mIsFailure = true;
                this.mDisplayState = DISPLAY_STATE.DISPLAY_FAILED;
            }
        } else {
            greyLog("总是走这个分支");
            if (this.mMiioUpdateInfo != null && this.mMiioUpdateInfo.f17780a) {
                long j = this.mMiioUpdateInfo.k;
                if (a2.k == 0) {
                    a2.k = j;
                }
            }
            if (TextUtils.isEmpty(a2.b)) {
                a2.b = this.mDevice.version;
            }
            this.mMiioUpdateInfo = a2;
            OTA_STATE a3 = a(a2);
            greyLog("state:" + a3);
            if (this.mLastState == null || a3.ordinal() == OTA_STATE.OTA_STATE_IDLE.ordinal() || a3.ordinal() > this.mLastState.ordinal() || a3.ordinal() == OTA_STATE.OTA_STATE_FAILED.ordinal()) {
                this.mLastState = a3;
            }
            greyLog("mLastState:" + this.mLastState);
            if (this.mLastState.ordinal() == OTA_STATE.OTA_STATE_IDLE.ordinal()) {
                greyLog("空闲状态进入的分支");
                if (verifyVersionCode(this.mDestVersion)) {
                    greyLog("升级成功");
                    this.mUpdateSucc = true;
                    SmartHomeDeviceManager.a().s();
                    this.mDisplayState = DISPLAY_STATE.DISPLAY_FINISHED;
                } else {
                    this.mRetryCount = i;
                    greyLog("升级失败，重试？mRetryCount:" + this.mRetryCount);
                    if (i == -1) {
                        this.mDisplayState = DISPLAY_STATE.DISPLAY_IDLE;
                    }
                }
            } else {
                greyLog("非空闲状态进入的分支");
                if (this.mMiioUpdateInfo.f17780a) {
                    this.mDestVersion = a2.c;
                }
                if (verifyVersionCode(this.mDestVersion)) {
                    greyLog("升级成功");
                    this.mUpdateSucc = true;
                    SmartHomeDeviceManager.a().s();
                    this.mDisplayState = DISPLAY_STATE.DISPLAY_FINISHED;
                } else {
                    greyLog("没有升级成功");
                    long currentTimeMillis = (System.currentTimeMillis() / 1000) - this.mMiioUpdateInfo.k;
                    if ((currentTimeMillis - ((long) this.mMiioUpdateInfo.j) <= ((long) TIMEOUT_INVALID_TIME) || this.mMiioUpdateInfo.f17780a) && !this.mMiioUpdateInfo.e) {
                        greyLog("超时但在升级中||没超时||不是最新版本||下载||下载完成||等待安装||安装中||安装完成||失败||忙");
                        DISPLAY_STATE display_state = DISPLAY_STATE.values()[this.mLastState.ordinal()];
                        if (this.mDisplayState == null || display_state.ordinal() > this.mDisplayState.ordinal()) {
                            this.mDisplayState = display_state;
                        }
                        if (this.mDisplayState.ordinal() <= DISPLAY_STATE.DISPLAY_IDLE.ordinal() || this.mDisplayState.ordinal() >= DISPLAY_STATE.DISPLAY_INSTALLING.ordinal()) {
                            greyLog("超时但在升级中||没超时||不是最新版本||安装中||安装完成||失败||忙");
                            if (currentTimeMillis > ((long) this.mMiioUpdateInfo.j)) {
                                greyLog("超时");
                                if (!this.mMiioUpdateInfo.f17780a) {
                                    this.mDisplayState = DISPLAY_STATE.DISPLAY_IDLE;
                                }
                            }
                        } else {
                            greyLog("下载，下载完成 或 等待安装中");
                            if (currentTimeMillis > ((long) this.mMiioUpdateInfo.j)) {
                                greyLog("超时了");
                                if (currentTimeMillis - ((long) this.mMiioUpdateInfo.j) < ((long) TIMEOUT_INVALID_TIME)) {
                                    this.mTimeout = true;
                                    this.mDisplayState = DISPLAY_STATE.DISPLAY_FAILED;
                                } else if (!a2.f17780a) {
                                    this.mDisplayState = DISPLAY_STATE.DISPLAY_IDLE;
                                }
                            } else {
                                int a4 = a(currentTimeMillis, (long) this.mMiioUpdateInfo.j, MAX_ELAPSE_PERCENT);
                                greyLog("没有超时 progress: " + a4);
                                if (this.mMiioUpdateInfo.f > 0) {
                                    greyLog("正常的进度:" + a4);
                                    if (this.mMiioUpdateInfo.f > a4) {
                                        a4 = this.mMiioUpdateInfo.f;
                                    }
                                    greyLog("mDisplayProgress: " + this.mDisplayProgress);
                                    if (a4 > this.mDisplayProgress) {
                                        this.mDisplayProgress = a4;
                                        if (this.mDisplayProgress > this.mMiioUpdateInfo.f && this.mMiioUpdateInfo.f < 90 && this.mDisplayProgress > 90) {
                                            this.mDisplayProgress = 90;
                                        }
                                        if (this.mDisplayProgress > 100) {
                                            this.mDisplayProgress = 100;
                                        }
                                    }
                                } else {
                                    greyLog("异常的进度 可能为-1 也可能大于100");
                                    if (a4 > this.mDisplayProgress) {
                                        this.mDisplayProgress = a4;
                                        if (this.mDisplayProgress > 90) {
                                            this.mDisplayProgress = 90;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        greyLog("超时并且没有在升级中 或者 已经是最新版本了");
                        this.mDisplayState = DISPLAY_STATE.DISPLAY_IDLE;
                    }
                }
            }
        }
        Log.d("ABC", "display state " + this.mDisplayState);
        this.mHandler.sendEmptyMessage(10002);
    }

    private void g() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (currentTimeMillis - this.mMiioUpdateInfo.k > ((long) this.mMiioUpdateInfo.j)) {
            if ((currentTimeMillis - this.mMiioUpdateInfo.k) - ((long) this.mMiioUpdateInfo.j) < ((long) TIMEOUT_INVALID_TIME)) {
                this.mTimeout = true;
                this.mDisplayState = DISPLAY_STATE.DISPLAY_FAILED;
            } else if (!this.mMiioUpdateInfo.f17780a) {
                this.mDisplayState = DISPLAY_STATE.DISPLAY_IDLE;
            }
        }
        greyLog("calTimeout");
    }

    private int a(long j, long j2, int i) {
        if (j2 > 0) {
            return (int) ((j / j2) * ((long) i));
        }
        return (int) (j / 2);
    }

    /* access modifiers changed from: package-private */
    public boolean verifyVersionCode(String str) {
        if (this.mMiioUpdateInfo == null) {
            return false;
        }
        String str2 = this.mMiioUpdateInfo.b;
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return false;
        }
        if (str2.startsWith(JSMethod.NOT_SET)) {
            str2 = str2.substring(1);
        }
        if (str.startsWith(JSMethod.NOT_SET)) {
            str = str.substring(1);
        }
        return str2.equals(str);
    }

    private void h() {
        if (this.mDisplayState != null) {
            f();
            this.mInstallingProgressBar.setVisibility(8);
            this.mTxtUpdateTitle.setVisibility(0);
            this.mBtnBottom.setEnabled(true);
            greyLog("refreshUI---mDisplayState:" + this.mDisplayState + "---mIsFailure:" + this.mIsFailure + "---mTimeout:" + this.mTimeout);
            if (this.mIsFailure || this.mTimeout || this.mDisplayState.ordinal() == DISPLAY_STATE.DISPLAY_FAILED.ordinal()) {
                Log.d("ABC", "fail type failed " + this.mIsFailure + " timeout " + this.mTimeout + " state " + this.mDisplayState.ordinal());
                i();
                greyLog("showFailure");
            } else if (this.mDisplayState.ordinal() == DISPLAY_STATE.DISPLAY_DOWNLOADING.ordinal() || this.mDisplayState.ordinal() == DISPLAY_STATE.DISPLAY_DOWNLOADED.ordinal()) {
                j();
                greyLog("showProgress");
            } else if (this.mDisplayState.ordinal() == DISPLAY_STATE.DISPLAY_WAIT_INSTALL.ordinal()) {
                l();
                greyLog("showWaitInstall");
            } else if (this.mDisplayState.ordinal() == DISPLAY_STATE.DISPLAY_INSTALLING.ordinal() || this.mDisplayState.ordinal() == DISPLAY_STATE.DISPLAY_INSTALLED.ordinal()) {
                m();
                greyLog("showInstalling");
            } else if (!this.mMiioUpdateInfo.e) {
                n();
                greyLog("showUpdateInfo");
            } else {
                greyLog("showFinish");
                o();
            }
        }
    }

    private void i() {
        Log.d("ABC", "showFailure");
        this.mTxtUpdateDesc.setVisibility(8);
        this.mTxtUpdatingTip.setVisibility(8);
        this.mBtnBottom.setVisibility(this.mMiioUpdateInfo != null ? 0 : 8);
        this.mBtnBottom.setText(R.string.retry);
        this.mBtnBottom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiioUpgradeActivity.this.p();
                MiioUpgradeActivity.this.mDisplayState = DISPLAY_STATE.DISPLAY_DOWNLOADING;
                MiioUpgradeActivity.this.mHandler.sendEmptyMessage(10002);
                MiioUpgradeActivity.this.startModelUpdate();
            }
        });
        this.mPbProgress.setBackgroundResource(R.drawable.update_img_failed);
        this.mPbProgress.setPercent(0.0f);
        this.mTxtProgress.setVisibility(8);
        this.mInstallingProgressBar.setVisibility(8);
        this.mPbProgress.setVisibility(0);
        if (!CoreApi.a().q()) {
            this.mTxtUpdateTitle.setText(R.string.update_failed_not_login);
            this.mPbProgress.setOnClickListener((View.OnClickListener) null);
        } else if (!this.mDevice.isOnline) {
            this.mTxtUpdateTitle.setText(R.string.update_failed_offline);
        } else if (this.mTimeout) {
            this.mTxtUpdateTitle.setText(R.string.update_failed_timeout);
        } else if (!TextUtils.isEmpty(this.mMiioUpdateInfo.i)) {
            this.mTxtUpdateTitle.setText(this.mMiioUpdateInfo.i);
        } else {
            this.mTxtUpdateTitle.setText(R.string.update_failed_retry);
        }
    }

    private void j() {
        Log.d("ABC", "showProgress");
        this.mTxtUpdateTitle.setText(R.string.model_updating);
        this.mTxtUpdateDesc.setVisibility(8);
        this.mTxtUpdatingTip.setVisibility(0);
        if (this.mLastState != OTA_STATE.OTA_STATE_FAILED || TextUtils.isEmpty(this.mMiioUpdateInfo.i)) {
            this.mTxtUpdatingTip.setText(R.string.miio_update_tips);
        } else {
            this.mTxtUpdatingTip.setText(this.mMiioUpdateInfo.i);
        }
        this.mBtnBottom.setVisibility(4);
        this.mPbProgress.setVisibility(0);
        this.mPbProgress.setOnClickListener((View.OnClickListener) null);
        this.mPbProgress.setBackgroundResource(R.drawable.kuailian_progress_filled_not);
        this.mTxtProgress.setVisibility(0);
        if ((this.mLastDisplayProgress != 0 || this.mDisplayProgress == 100) && this.mDisplayProgress - this.mLastDisplayProgress >= 10) {
            LogUtil.a("ABC", "animate Progress: mDisplayProgress: " + this.mDisplayProgress + " ;mLastDisplayProgress: " + this.mLastDisplayProgress);
            greyLog("animate Progress: mDisplayProgress: " + this.mDisplayProgress + " ;mLastDisplayProgress: " + this.mLastDisplayProgress);
            k();
        } else {
            this.mPbProgress.setPercent((float) this.mDisplayProgress);
            this.mLastDisplayProgress = this.mDisplayProgress;
            LogUtil.a("ABC", "showProgress: mDisplayProgress: " + this.mDisplayProgress + " ;mLastDisplayProgress: " + this.mLastDisplayProgress);
            greyLog("showProgress: mDisplayProgress: " + this.mDisplayProgress + " ;mLastDisplayProgress: " + this.mLastDisplayProgress);
        }
        if (this.mRetryCount == -1) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 10001;
            obtainMessage.arg1 = 1;
            this.mHandler.sendMessageDelayed(obtainMessage, (long) MIIO_UPGRADE_TIME);
            return;
        }
        this.mRetryCount--;
        if (this.mRetryCount == 0) {
            this.mTimeout = true;
            this.mHandler.sendEmptyMessage(10002);
            return;
        }
        Message obtainMessage2 = this.mHandler.obtainMessage();
        obtainMessage2.what = 10001;
        obtainMessage2.arg1 = 1;
        obtainMessage2.arg2 = this.mRetryCount;
        this.mHandler.sendMessageDelayed(obtainMessage2, (long) MIIO_UPGRADE_TIME);
    }

    private void k() {
        if (this.mAnimator == null) {
            this.mAnimator = new ValueAnimator();
            this.mAnimator.setFloatValues(new float[]{0.0f, 1.0f});
            this.mAnimator.setDuration(1000);
            this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int animatedFraction = (int) (((float) MiioUpgradeActivity.this.mLastDisplayProgress) + (((float) (MiioUpgradeActivity.this.mDisplayProgress - MiioUpgradeActivity.this.mLastDisplayProgress)) * valueAnimator.getAnimatedFraction()));
                    MiioUpgradeActivity.this.mPbProgress.setPercent((float) animatedFraction);
                    LogUtil.a("ABC", "onAnimationUpdate: " + animatedFraction);
                    MiioUpgradeActivity miioUpgradeActivity = MiioUpgradeActivity.this;
                    miioUpgradeActivity.greyLog("onAnimationUpdate: " + animatedFraction);
                }
            });
            this.mAnimator.addListener(new Animator.AnimatorListener() {
                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    MiioUpgradeActivity.this.mLastDisplayProgress = MiioUpgradeActivity.this.mDisplayProgress;
                    MiioUpgradeActivity.this.mPbProgress.setPercent((float) MiioUpgradeActivity.this.mDisplayProgress);
                    LogUtil.a("ABC", "onAnimationEnd: " + MiioUpgradeActivity.this.mDisplayProgress);
                    MiioUpgradeActivity miioUpgradeActivity = MiioUpgradeActivity.this;
                    miioUpgradeActivity.greyLog("onAnimationEnd: " + MiioUpgradeActivity.this.mDisplayProgress);
                }

                public void onAnimationCancel(Animator animator) {
                    MiioUpgradeActivity.this.mPbProgress.setPercent((float) MiioUpgradeActivity.this.mDisplayProgress);
                    MiioUpgradeActivity.this.mLastDisplayProgress = MiioUpgradeActivity.this.mDisplayProgress;
                }
            });
        }
        if (this.mAnimator.isRunning()) {
            LogUtil.a("ABC", "cancel the running animator : ");
            greyLog("cancel the running animator : ");
            this.mAnimator.cancel();
        }
        this.mAnimator.start();
    }

    private void l() {
        Log.d("ABC", "showWaitInstall");
        this.mTxtProgress.setVisibility(8);
        this.mPbProgress.setBackgroundResource(R.drawable.update_img_update);
        this.mPbProgress.setPercent(0.0f);
        this.mPbProgress.setOnClickListener((View.OnClickListener) null);
        TextView textView = this.mTxtUpdateTitle;
        textView.setText(getResources().getString(R.string.list_item_curr_version) + " " + this.mMiioUpdateInfo.b + "\n\n" + getResources().getString(R.string.list_item_latest_version) + " " + this.mMiioUpdateInfo.c);
        this.mTxtUpdateDesc.setVisibility(0);
        this.mTxtUpdateDesc.setText(this.mMiioUpdateInfo.d);
        this.mTxtUpdatingTip.setVisibility(8);
        this.mBtnBottom.setVisibility(0);
        this.mBtnBottom.setText(R.string.update_now);
        this.mBtnBottom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiioUpgradeActivity.this.mDisplayState = DISPLAY_STATE.DISPLAY_INSTALLING;
                MiioUpgradeActivity.this.mHandler.sendEmptyMessage(10002);
                if (MiioUpgradeActivity.this.mLastState.ordinal() == OTA_STATE.OTA_STATE_DOWNLOADED.ordinal()) {
                    MiioUpgradeActivity.this.startRestartUpdate();
                } else {
                    MiioUpgradeActivity.this.startModelUpdate();
                }
            }
        });
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 10001;
        obtainMessage.arg1 = 1;
        this.mHandler.sendMessageDelayed(obtainMessage, (long) MIIO_UPGRADE_TIME);
    }

    /* access modifiers changed from: package-private */
    public void startRestartUpdate() {
        greyLog("startRestartUpdate");
        if (!this.mIsPollingInfo) {
            this.mIsPollingInfo = true;
            this.mDestVersion = this.mMiioUpdateInfo.c;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("method", "miIO.ota_install");
                jSONObject.put("params", new JSONArray());
            } catch (Exception unused) {
            }
            DeviceApi.getInstance().rpcAsyncRemote(this, this.mInfo.b, jSONObject.toString(), new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    Log.d("ABC", "start restart success " + jSONObject);
                    MiioUpgradeActivity miioUpgradeActivity = MiioUpgradeActivity.this;
                    miioUpgradeActivity.greyLog("start restart success " + jSONObject);
                    MiioUpgradeActivity.this.mIsPollingInfo = false;
                    Message obtainMessage = MiioUpgradeActivity.this.mHandler.obtainMessage();
                    obtainMessage.what = 10001;
                    obtainMessage.arg1 = 1;
                    MiioUpgradeActivity.this.mHandler.sendMessageDelayed(obtainMessage, (long) MiioUpgradeActivity.MIIO_UPGRADE_TIME);
                    MiioUpgradeActivity.this.mHandler.sendEmptyMessage(10002);
                }

                public void onFailure(Error error) {
                    Log.d("ABC", "on failure 615 " + error.b());
                    MiioUpgradeActivity miioUpgradeActivity = MiioUpgradeActivity.this;
                    miioUpgradeActivity.greyLog("on failure 615 " + error.b());
                    MiioUpgradeActivity.this.mIsPollingInfo = false;
                    if (error.a() == ErrorCode.ERROR_REQUEST_TIME_OUT.getCode()) {
                        Message obtainMessage = MiioUpgradeActivity.this.mHandler.obtainMessage();
                        obtainMessage.what = 10001;
                        obtainMessage.arg1 = 1;
                        obtainMessage.arg2 = 5;
                        MiioUpgradeActivity.this.mHandler.sendMessageDelayed(obtainMessage, (long) MiioUpgradeActivity.MIIO_UPGRADE_TIME);
                    } else {
                        MiioUpgradeActivity.this.mIsFailure = true;
                    }
                    MiioUpgradeActivity.this.mHandler.sendEmptyMessage(10002);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void startModelUpdate() {
        greyLog("startModelUpdate");
        if (!this.mIsPollingInfo) {
            this.mIsPollingInfo = true;
            this.mDestVersion = this.mMiioUpdateInfo.c;
            CoreApi.a().a(this.mInfo.b, true);
            UpdateApi.a().g(this, this.mInfo.b, this.mInfo.c, new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    Log.d("ABC", "start model update success " + jSONObject);
                    MiioUpgradeActivity.this.mIsPollingInfo = false;
                    MiioUpgradeActivity miioUpgradeActivity = MiioUpgradeActivity.this;
                    miioUpgradeActivity.greyLog("start model update success " + jSONObject);
                    CoreApi.a().a(MiioUpgradeActivity.this.mInfo.b, true);
                    Message obtainMessage = MiioUpgradeActivity.this.mHandler.obtainMessage();
                    obtainMessage.what = 10001;
                    obtainMessage.arg1 = 1;
                    MiioUpgradeActivity.this.mHandler.sendMessageDelayed(obtainMessage, (long) MiioUpgradeActivity.MIIO_UPGRADE_TIME);
                }

                public void onFailure(Error error) {
                    Log.d("ABC", "on fail 642 " + error.b() + " " + error.a());
                    MiioUpgradeActivity miioUpgradeActivity = MiioUpgradeActivity.this;
                    miioUpgradeActivity.greyLog("on fail 642 " + error.b() + " " + error.a());
                    MiioUpgradeActivity.this.mIsPollingInfo = false;
                    if (error.a() == ErrorCode.ERROR_REQUEST_TIME_OUT.getCode()) {
                        Message obtainMessage = MiioUpgradeActivity.this.mHandler.obtainMessage();
                        obtainMessage.what = 10001;
                        obtainMessage.arg1 = 1;
                        obtainMessage.arg2 = 5;
                        MiioUpgradeActivity.this.mHandler.sendMessageDelayed(obtainMessage, (long) MiioUpgradeActivity.MIIO_UPGRADE_TIME);
                    } else {
                        MiioUpgradeActivity.this.mIsFailure = true;
                        CoreApi.a().a(MiioUpgradeActivity.this.mInfo.b, false);
                    }
                    MiioUpgradeActivity.this.mHandler.sendEmptyMessage(10002);
                }
            });
        }
    }

    private void m() {
        Log.d("ABC", "show installing");
        this.mTxtUpdateTitle.setText(R.string.update_installing);
        this.mTxtUpdateDesc.setVisibility(8);
        this.mTxtUpdatingTip.setVisibility(0);
        this.mBtnBottom.setVisibility(4);
        this.mInstallingProgressBar.setVisibility(0);
        this.mPbProgress.setVisibility(8);
        this.mPbProgress.setOnClickListener((View.OnClickListener) null);
        this.mPbProgress.setBackgroundResource(R.drawable.kuailian_progress_filled_not);
        this.mTxtProgress.setVisibility(8);
        this.mTxtUpdatingTip.setText(R.string.update_installing_info);
        if (this.mDisplayState.ordinal() == DISPLAY_STATE.DISPLAY_INSTALLING.ordinal()) {
            if ((System.currentTimeMillis() / 1000) - this.mMiioUpdateInfo.k > ((long) this.mMiioUpdateInfo.j)) {
                this.mInstallingCount++;
            }
            if (this.mInstallingCount > 90) {
                this.mTimeout = true;
                this.mHandler.sendEmptyMessage(10002);
                return;
            }
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 10001;
            obtainMessage.arg1 = 1;
            this.mHandler.sendMessageDelayed(obtainMessage, (long) MIIO_UPGRADE_TIME);
            return;
        }
        this.mRetartCount++;
        if (this.mRetartCount > 90) {
            this.mTimeout = true;
            this.mHandler.sendEmptyMessage(10002);
            return;
        }
        Message obtainMessage2 = this.mHandler.obtainMessage();
        obtainMessage2.what = 10001;
        obtainMessage2.arg1 = 1;
        this.mHandler.sendMessageDelayed(obtainMessage2, (long) MIIO_UPGRADE_TIME);
    }

    private void n() {
        Log.d("ABC", "showUpdateInfo");
        this.mTxtProgress.setVisibility(8);
        this.mPbProgress.setBackgroundResource(R.drawable.update_img_update);
        this.mPbProgress.setPercent(0.0f);
        this.mPbProgress.setOnClickListener((View.OnClickListener) null);
        TextView textView = this.mTxtUpdateTitle;
        textView.setText(getResources().getString(R.string.list_item_curr_version) + " " + this.mMiioUpdateInfo.b + "\n\n" + getResources().getString(R.string.list_item_latest_version) + " " + this.mMiioUpdateInfo.c);
        this.mTxtUpdateDesc.setVisibility(0);
        this.mTxtUpdateDesc.setText(this.mMiioUpdateInfo.d);
        this.mTxtUpdatingTip.setVisibility(8);
        this.mBtnBottom.setVisibility(0);
        this.mBtnBottom.setText(R.string.update_now);
        this.mBtnBottom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiioUpgradeActivity.this.p();
                MiioUpgradeActivity.this.mDisplayState = DISPLAY_STATE.DISPLAY_DOWNLOADING;
                MiioUpgradeActivity.this.mHandler.sendEmptyMessage(10002);
                MiioUpgradeActivity.this.startModelUpdate();
            }
        });
        if (!this.mDevice.isOnline) {
            this.mBtnBottom.setEnabled(false);
        }
    }

    private void o() {
        Log.d("ABC", "showFinish");
        if (this.mUpdateSucc) {
            this.mPbProgress.setBackgroundResource(R.drawable.update_img_success);
            TextView textView = this.mTxtUpdateTitle;
            textView.setText(getResources().getString(R.string.model_update_success) + "\n\n" + getResources().getString(R.string.app_curr_version) + " " + this.mMiioUpdateInfo.b);
        } else {
            this.mPbProgress.setBackgroundResource(R.drawable.update_img_latest);
            TextView textView2 = this.mTxtUpdateTitle;
            textView2.setText(getResources().getString(R.string.model_latest) + "\n\n" + getResources().getString(R.string.app_curr_version) + " " + this.mMiioUpdateInfo.b);
        }
        this.mTxtProgress.setVisibility(8);
        this.mPbProgress.setPercent(0.0f);
        this.mPbProgress.setOnClickListener((View.OnClickListener) null);
        this.mPbProgress.setVisibility(0);
        this.mTxtUpdateDesc.setVisibility(8);
        this.mTxtUpdatingTip.setVisibility(8);
        this.mBtnBottom.setVisibility(0);
        this.mBtnBottom.setText(R.string.ok_button);
        this.mBtnBottom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiioUpgradeActivity.this.finish();
            }
        });
    }

    /* access modifiers changed from: private */
    public void p() {
        this.mIsFailure = false;
        this.mTimeout = false;
        this.mRetartCount = 0;
        this.mDisplayProgress = 0;
        this.mLastDisplayProgress = 0;
        this.mLastState = OTA_STATE.OTA_STATE_IDLE;
        this.mInstallingCount = 0;
        this.mDisplayState = DISPLAY_STATE.DISPLAY_IDLE;
        this.mRetryCount = -1;
    }
}
