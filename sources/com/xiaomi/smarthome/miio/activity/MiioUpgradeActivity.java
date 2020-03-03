package com.xiaomi.smarthome.miio.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
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
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.runtime.util.TitleBarUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.update.api.UpdateApi;
import com.xiaomi.smarthome.library.common.widget.PieProgressBar;
import com.xiaomi.smarthome.miio.update.ModelUpdateInfo;
import org.json.JSONArray;
import org.json.JSONObject;

public class MiioUpgradeActivity extends BaseActivity {
    public static String MIIO_UPGRADE_DID = "miio_upgrade_did";
    public static int MIIO_UPGRADE_MAX = 120;
    public static String MIIO_UPGRADE_NAME = "miio_upgrade_name";
    public static String MIIO_UPGRADE_PID = "miio_upgrade_pid";
    public static int MIIO_UPGRADE_TIME = 2000;

    /* renamed from: a  reason: collision with root package name */
    private static int f11793a;
    static String[] sLinuxFirmModel = {"chuangmi.camera.xiaobai", "rockrobo.vacuum.v1"};
    Button mBtnBottom;
    String mDestVersion = null;
    Device mDevice;
    ModelUpdateInfo mInfo;
    ProgressBar mInstallingProgressBar;
    boolean mIsFailure = false;
    boolean mIsInstalling = false;
    boolean mIsTimeOut = false;
    boolean mIsUpdating = false;
    boolean mIsUpdatingInfo = false;
    View mLoadingView;
    PieProgressBar mPbProgress;
    TextView mTxtProgress;
    TextView mTxtUpdateDesc;
    TextView mTxtUpdateTitle;
    TextView mTxtUpdatingTip;
    int mUpdateCount = 0;
    Runnable mUpdateModelInfoRunnable = new Runnable() {
        public void run() {
            MiioUpgradeActivity.this.updateModelUpdateInfo(true);
        }
    };
    boolean mUpdateSucc = false;
    ViewSwitcher mVsRoot;

    /* access modifiers changed from: package-private */
    public boolean isLinuxFirmModel(String str) {
        for (String equals : sLinuxFirmModel) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void initTitleBar() {
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiioUpgradeActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(this.mInfo.d);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.miio_setting_update);
        Intent intent = getIntent();
        this.mInfo = new ModelUpdateInfo();
        this.mInfo.b = intent.getStringExtra(MIIO_UPGRADE_DID);
        this.mInfo.c = intent.getIntExtra(MIIO_UPGRADE_PID, 0);
        this.mInfo.d = intent.getStringExtra(MIIO_UPGRADE_NAME);
        this.mDevice = SmartHomeDeviceManager.a().b(this.mInfo.b);
        if (this.mDevice == null || !isLinuxFirmModel(this.mDevice.model)) {
            MIIO_UPGRADE_MAX = 120;
        } else {
            MIIO_UPGRADE_MAX = 600;
        }
        initTitleBar();
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
        b();
        c();
        updateModelUpdateInfo(false);
        if (!this.mDevice.isOnline) {
            getWindow().getDecorView().post(new Runnable() {
                public void run() {
                    MiioUpgradeActivity.this.j();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mUpdateCount = 0;
    }

    /* access modifiers changed from: package-private */
    public void startRestartUpdate() {
        this.mUpdateCount = 0;
        this.mIsInstalling = true;
        this.mDestVersion = this.mInfo.g;
        a();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("method", "miIO.ota_install");
            jSONObject.put("params", new JSONArray());
        } catch (Exception unused) {
        }
        this.mHandler.removeCallbacks(this.mUpdateModelInfoRunnable);
        DeviceApi.getInstance().rpcAsyncRemote(this, this.mInfo.b, jSONObject.toString(), new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                MiioUpgradeActivity.this.mIsInstalling = true;
                MiioUpgradeActivity.this.mIsFailure = false;
                MiioUpgradeActivity.this.mIsTimeOut = false;
                MiioUpgradeActivity.this.a();
            }

            public void onFailure(Error error) {
                MiioUpgradeActivity.this.mIsInstalling = false;
                MiioUpgradeActivity.this.mIsFailure = true;
                MiioUpgradeActivity.this.mIsTimeOut = false;
                MiioUpgradeActivity.this.a();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void startModelUpdate() {
        this.mUpdateCount = 0;
        this.mIsUpdating = true;
        this.mDestVersion = this.mInfo.g;
        this.mInfo.j = 0;
        if ("downloaded".equals(this.mInfo.k) || "wait_install".equals(this.mInfo.k)) {
            this.mIsInstalling = true;
        }
        this.mIsFailure = false;
        this.mIsTimeOut = false;
        a();
        this.mHandler.removeCallbacks(this.mUpdateModelInfoRunnable);
        UpdateApi.a().g(this, this.mInfo.b, this.mInfo.c, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                MiioUpgradeActivity.this.mIsUpdating = true;
                MiioUpgradeActivity.this.mIsFailure = false;
                MiioUpgradeActivity.this.mIsTimeOut = false;
                MiioUpgradeActivity.this.a();
            }

            public void onFailure(Error error) {
                MiioUpgradeActivity.this.mIsUpdating = false;
                MiioUpgradeActivity.this.mIsFailure = true;
                MiioUpgradeActivity.this.mIsTimeOut = false;
                if ("downloaded".equals(MiioUpgradeActivity.this.mInfo.k) || "wait_install".equals(MiioUpgradeActivity.this.mInfo.k)) {
                    MiioUpgradeActivity.this.mIsInstalling = false;
                }
                MiioUpgradeActivity.this.a();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void updateModelUpdateInfo(final boolean z) {
        if (!this.mIsFailure && !this.mIsTimeOut && !this.mIsUpdatingInfo) {
            this.mIsUpdatingInfo = true;
            UpdateApi.a().f(this, this.mInfo.b, this.mInfo.c, new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    MiioUpgradeActivity.this.a(jSONObject);
                }

                public void onFailure(Error error) {
                    MiioUpgradeActivity.this.mIsUpdatingInfo = false;
                    if (z) {
                        MiioUpgradeActivity.this.mHandler.postDelayed(MiioUpgradeActivity.this.mUpdateModelInfoRunnable, (long) MiioUpgradeActivity.MIIO_UPGRADE_TIME);
                        return;
                    }
                    MiioUpgradeActivity.this.mIsFailure = true;
                    MiioUpgradeActivity.this.mIsTimeOut = false;
                    MiioUpgradeActivity.this.mIsUpdating = false;
                    MiioUpgradeActivity.this.mIsInstalling = false;
                    MiioUpgradeActivity.this.a();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        this.mIsUpdatingInfo = false;
        this.mIsFailure = false;
        this.mIsTimeOut = false;
        this.mInfo.a(jSONObject);
        if (TextUtils.equals(PrinterParmater.i, this.mInfo.k)) {
            this.mInfo.j = 0;
        }
        this.mIsUpdating = this.mInfo.e;
        if ("failed".equals(this.mInfo.k)) {
            this.mIsFailure = true;
        }
        if (!"installing".equals(this.mInfo.k) || this.mInfo.h || !this.mInfo.e) {
            this.mIsInstalling = false;
        } else {
            this.mIsInstalling = true;
        }
        if (verifyVersionCode()) {
            this.mIsUpdating = false;
            this.mUpdateSucc = true;
            this.mIsInstalling = false;
        }
        if (this.mInfo.o > 0) {
            MIIO_UPGRADE_MAX = this.mInfo.o / 2;
        }
        a();
    }

    /* access modifiers changed from: package-private */
    public boolean verifyVersionCode() {
        if (this.mInfo == null) {
            return false;
        }
        String str = this.mInfo.f;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String str2 = this.mDestVersion;
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        if (str.startsWith(JSMethod.NOT_SET)) {
            str = str.substring(1);
        }
        if (str2.startsWith(JSMethod.NOT_SET)) {
            str2 = str2.substring(1);
        }
        return str.equals(str2);
    }

    /* access modifiers changed from: private */
    public void a() {
        d();
        this.mInstallingProgressBar.setVisibility(this.mIsInstalling ? 0 : 8);
        this.mTxtUpdateTitle.setVisibility(0);
        this.mBtnBottom.setEnabled(true);
        if (this.mIsFailure || this.mIsTimeOut) {
            e();
        } else if (this.mIsUpdating || "downloading".equals(this.mInfo.k) || this.mIsInstalling) {
            f();
        } else if ("downloaded".equals(this.mInfo.k) || "wait_install".equals(this.mInfo.k)) {
            g();
        } else if (!this.mInfo.h) {
            h();
        } else {
            i();
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        this.mIsUpdatingInfo = false;
        this.mUpdateCount = 0;
        this.mIsInstalling = false;
        this.mIsFailure = false;
        this.mIsTimeOut = false;
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.mVsRoot.getCurrentView() != this.mLoadingView) {
            this.mVsRoot.showNext();
        }
    }

    private void d() {
        if (this.mVsRoot.getCurrentView() == this.mLoadingView) {
            this.mVsRoot.showNext();
        }
    }

    private void e() {
        this.mTxtUpdateDesc.setVisibility(8);
        this.mTxtUpdatingTip.setVisibility(8);
        this.mBtnBottom.setVisibility(0);
        this.mBtnBottom.setText(R.string.retry);
        this.mBtnBottom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiioUpgradeActivity.this.b();
                MiioUpgradeActivity.this.c();
                MiioUpgradeActivity.this.updateModelUpdateInfo(false);
            }
        });
        this.mPbProgress.setBackgroundResource(R.drawable.update_img_failed);
        this.mPbProgress.setPercent(0.0f);
        this.mTxtProgress.setVisibility(8);
        if (!CoreApi.a().q()) {
            this.mTxtUpdateTitle.setText(R.string.update_failed_not_login);
            this.mPbProgress.setOnClickListener((View.OnClickListener) null);
        } else if (this.mIsTimeOut) {
            this.mTxtUpdateTitle.setText(R.string.update_failed_timeout);
        } else if (!TextUtils.isEmpty(this.mInfo.n)) {
            this.mTxtUpdateTitle.setText(this.mInfo.n);
        } else {
            this.mTxtUpdateTitle.setText(R.string.update_failed_retry);
        }
    }

    private void f() {
        if (this.mIsInstalling) {
            this.mTxtUpdateTitle.setText(R.string.update_installing);
            this.mTxtUpdateDesc.setVisibility(8);
            this.mTxtUpdatingTip.setVisibility(0);
            this.mBtnBottom.setVisibility(4);
            this.mPbProgress.setVisibility(8);
            this.mPbProgress.setOnClickListener((View.OnClickListener) null);
            this.mPbProgress.setBackgroundResource(R.drawable.kuailian_progress_filled_not);
            this.mTxtProgress.setVisibility(8);
            this.mTxtUpdatingTip.setText(R.string.update_installing_info);
        } else {
            this.mTxtUpdateTitle.setText(R.string.model_updating);
            this.mTxtUpdateDesc.setVisibility(8);
            this.mTxtUpdatingTip.setVisibility(0);
            this.mTxtUpdatingTip.setText(R.string.miio_update_tips);
            this.mBtnBottom.setVisibility(4);
            this.mPbProgress.setVisibility(0);
            this.mPbProgress.setOnClickListener((View.OnClickListener) null);
            this.mPbProgress.setBackgroundResource(R.drawable.kuailian_progress_filled_not);
            this.mTxtProgress.setVisibility(0);
            if (this.mInfo.j >= 0 && this.mInfo.j < 100) {
                this.mPbProgress.setPercent((float) this.mInfo.j);
            } else if (this.mInfo.j >= 100) {
                this.mPbProgress.setPercent(99.0f);
            }
        }
        if (!verifyVersionCode()) {
            this.mUpdateCount++;
            this.mHandler.removeCallbacks(this.mUpdateModelInfoRunnable);
            if (this.mUpdateCount > MIIO_UPGRADE_MAX) {
                this.mUpdateCount = 0;
                this.mIsTimeOut = true;
                this.mIsUpdating = false;
                this.mIsInstalling = false;
                a();
                return;
            }
            this.mHandler.postDelayed(this.mUpdateModelInfoRunnable, (long) MIIO_UPGRADE_TIME);
        }
    }

    private void g() {
        this.mTxtProgress.setVisibility(8);
        this.mPbProgress.setBackgroundResource(R.drawable.update_img_update);
        this.mPbProgress.setPercent(0.0f);
        this.mPbProgress.setOnClickListener((View.OnClickListener) null);
        TextView textView = this.mTxtUpdateTitle;
        textView.setText(getResources().getString(R.string.list_item_curr_version) + " " + this.mInfo.f + "\n\n" + getResources().getString(R.string.list_item_latest_version) + " " + this.mInfo.g);
        this.mTxtUpdateDesc.setVisibility(0);
        this.mTxtUpdateDesc.setText(this.mInfo.i);
        this.mTxtUpdatingTip.setVisibility(8);
        this.mBtnBottom.setVisibility(0);
        this.mBtnBottom.setText(R.string.update_now);
        this.mBtnBottom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if ("downloaded".equals(MiioUpgradeActivity.this.mInfo.k)) {
                    MiioUpgradeActivity.this.startRestartUpdate();
                } else {
                    MiioUpgradeActivity.this.startModelUpdate();
                }
            }
        });
    }

    private void h() {
        this.mTxtProgress.setVisibility(8);
        this.mPbProgress.setBackgroundResource(R.drawable.update_img_update);
        this.mPbProgress.setPercent(0.0f);
        this.mPbProgress.setOnClickListener((View.OnClickListener) null);
        TextView textView = this.mTxtUpdateTitle;
        textView.setText(getResources().getString(R.string.list_item_curr_version) + " " + this.mInfo.f + "\n\n" + getResources().getString(R.string.list_item_latest_version) + " " + this.mInfo.g);
        this.mTxtUpdateDesc.setVisibility(0);
        this.mTxtUpdateDesc.setText(this.mInfo.i);
        this.mTxtUpdatingTip.setVisibility(8);
        this.mBtnBottom.setVisibility(0);
        this.mBtnBottom.setText(R.string.update_now);
        this.mBtnBottom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiioUpgradeActivity.this.startModelUpdate();
            }
        });
        if (!this.mDevice.isOnline) {
            this.mBtnBottom.setEnabled(false);
        }
    }

    private void i() {
        if (this.mUpdateSucc) {
            this.mPbProgress.setBackgroundResource(R.drawable.update_img_success);
            TextView textView = this.mTxtUpdateTitle;
            textView.setText(getResources().getString(R.string.model_update_success) + "\n\n" + getResources().getString(R.string.app_curr_version) + " " + this.mInfo.f);
        } else {
            this.mPbProgress.setBackgroundResource(R.drawable.update_img_latest);
            TextView textView2 = this.mTxtUpdateTitle;
            textView2.setText(getResources().getString(R.string.model_latest) + "\n\n" + getResources().getString(R.string.app_curr_version) + " " + this.mInfo.f);
        }
        this.mTxtProgress.setVisibility(8);
        this.mPbProgress.setPercent(0.0f);
        this.mPbProgress.setOnClickListener((View.OnClickListener) null);
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
    public void j() {
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
            layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.std_titlebar_height);
        } else {
            layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.std_titlebar_height) + resources.getDimensionPixelOffset(R.dimen.title_bar_top_padding);
        }
        frameLayout.addView(viewGroup, layoutParams);
        viewGroup.findViewById(R.id.delete_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiioUpgradeActivity.this.a(viewGroup.findViewById(R.id.bottom_rl));
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.Y, new float[]{(float) (viewGroup.getHeight() - view.getHeight()), (float) viewGroup.getHeight()});
        ofFloat.setDuration(500);
        ofFloat.start();
    }
}
