package com.xiaomi.smarthome.splashads;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.miot.store.api.MiotStoreApi;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.framework.statistic.StatUtil;
import com.xiaomi.smarthome.splashads.model.MiuiAdsInfo;
import java.lang.ref.WeakReference;

public class MiuiSplashActivity extends BaseActivity {
    public static final String KEY_JUMP_TO_FLAG = "SplashActivity.jumpTo.flag";

    /* renamed from: a  reason: collision with root package name */
    private final int f22725a = 2;
    private final int b = 2000;
    private final int c = 7000;
    private ViewStub d;
    private ViewStub e;
    ImageView mAdImageView;
    String mAdJumpParam;
    int mAdJumpType = -1;
    View mAdLayer;
    Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 2) {
                MiuiSplashActivity.this.finish();
            }
        }
    };
    MiuiAdsInfo mInfo;
    boolean mIsAdShowing = false;
    View mJumpLogoView;
    FrameLayout mJumpView;
    FrameLayout mLabelFl;
    TextView mLabelTV;
    View mNormalLayer;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.splash_activity);
        this.d = (ViewStub) findViewById(R.id.normal_layer_stub);
        this.e = (ViewStub) findViewById(R.id.ad_layer_stub);
        this.mInfo = MiuiSplashAdsManager.a().a((Context) this);
        if (this.mInfo == null) {
            if (!CoreApi.a().D()) {
                MiuiSplashAdsManager.a().d();
            }
            finish();
        } else {
            b();
        }
        CoreApi.a().a((Context) this, (CoreApi.IsCoreReadyCallback) new MyCoreReadyCallback());
    }

    private static class MyCoreReadyCallback implements CoreApi.IsCoreReadyCallback {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<MiuiSplashActivity> f22729a;

        private MyCoreReadyCallback(MiuiSplashActivity miuiSplashActivity) {
            this.f22729a = new WeakReference<>(miuiSplashActivity);
        }

        public void onCoreReady() {
            MiuiSplashActivity miuiSplashActivity = (MiuiSplashActivity) this.f22729a.get();
            if (miuiSplashActivity != null && miuiSplashActivity.isValid() && !CoreApi.a().D()) {
                MiotStoreApi.a().updateJSBundler();
            }
        }
    }

    private void a() {
        if (this.e != null) {
            this.e.inflate();
            this.mAdLayer = findViewById(R.id.ad_layer);
            this.mAdImageView = (ImageView) findViewById(R.id.ad_image);
            this.mJumpView = (FrameLayout) findViewById(R.id.jump_image);
            this.mJumpLogoView = findViewById(R.id.logo_jump);
            this.mJumpLogoView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MiuiSplashActivity.this.mHandler.removeMessages(2);
                    MiuiSplashActivity.this.mHandler.sendEmptyMessage(2);
                    StatHelper.aC();
                    MobclickAgent.a((Context) MiuiSplashActivity.this, StatUtil.h, "miui_splash_skip_view_monitor");
                }
            });
            this.mLabelFl = (FrameLayout) findViewById(R.id.ad_label_fl);
            this.mLabelTV = (TextView) findViewById(R.id.ad_label_tv);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages((Object) null);
    }

    private void b() {
        this.mIsAdShowing = true;
        if (this.mNormalLayer != null) {
            this.mNormalLayer.setVisibility(4);
        }
        if (this.mAdLayer == null) {
            a();
        }
        try {
            this.mAdLayer.setBackgroundColor(-1);
            this.mAdLayer.setVisibility(0);
            if (this.mInfo == null || TextUtils.isEmpty(this.mInfo.g)) {
                this.mLabelFl.setVisibility(8);
            } else {
                this.mLabelFl.setVisibility(0);
                this.mLabelTV.setText(this.mInfo.g);
            }
            MiuiSplashAdsManager.a().a(this, this.mAdImageView, this.mInfo, new IShowAdsCallBack() {
                public void a() {
                    long j = MiuiSplashActivity.this.mInfo.n;
                    if (j > 7000) {
                        j = 7000;
                    }
                    Handler handler = MiuiSplashActivity.this.mHandler;
                    if (j <= 0) {
                        j = 4500;
                    }
                    handler.sendEmptyMessageDelayed(2, j);
                }

                public void b() {
                    MiuiSplashActivity.this.finish();
                }
            });
        } catch (Exception unused) {
        }
    }
}
