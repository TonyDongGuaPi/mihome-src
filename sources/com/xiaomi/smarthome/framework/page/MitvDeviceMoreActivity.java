package com.xiaomi.smarthome.framework.page;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.library.common.ApiHelper;

public class MitvDeviceMoreActivity extends BaseActivity {
    public static final String ARGS_KEY_DID = "did";
    public static final String ARGS_KEY_SETTING_CLICK = "setting_click";
    public static final String RESULT_KEY_FINISH = "result_data";
    public static final int RESULT_VAL_FINISH = 10;
    protected Context mContext;
    protected Device mDevice;
    View mEmpty;
    boolean mFinishing;
    View mMainview = null;
    View mMainviewFrame = null;
    View mMitvApp;
    View mMitvMovie;
    View mMitvPlay;
    View mMitvSyle;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        this.mFinishing = false;
        setContentView(R.layout.mitv_device_more_activity);
        this.mMainviewFrame = findViewById(R.id.device_more_frame);
        this.mMainview = findViewById(R.id.device_more);
        this.mDevice = SmartHomeDeviceManager.a().b(getIntent().getStringExtra("did"));
        findViewById(R.id.module_a_3_return_more_more_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MitvDeviceMoreActivity.this.onBackPressed();
            }
        });
        this.mMitvMovie = findViewById(R.id.mitv_movie);
        this.mMitvMovie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MitvDeviceMoreActivity.this.selectMenu(0);
            }
        });
        this.mMitvPlay = findViewById(R.id.mitv_play);
        this.mMitvPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MitvDeviceMoreActivity.this.selectMenu(1);
            }
        });
        this.mMitvApp = findViewById(R.id.mitv_app);
        this.mMitvApp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MitvDeviceMoreActivity.this.selectMenu(2);
            }
        });
        this.mMitvSyle = findViewById(R.id.mitv_style);
        this.mMitvSyle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MitvDeviceMoreActivity.this.selectMenu(3);
            }
        });
        this.mEmpty = findViewById(R.id.empty);
        this.mEmpty.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MitvDeviceMoreActivity.this.onBackPressed();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    public void onBackPressed() {
        setResult(0);
        b();
    }

    /* access modifiers changed from: package-private */
    public void selectMenu(int i) {
        Intent intent = new Intent();
        intent.putExtra("result_data", i);
        setResult(-1, intent);
        b();
    }

    /* access modifiers changed from: private */
    public void a() {
        this.mFinishing = true;
        finish();
        overridePendingTransition(0, 0);
    }

    private void b() {
        this.mFinishing = true;
        if (ApiHelper.f18555a) {
            ObjectAnimator ofObject = ObjectAnimator.ofObject(this.mMainviewFrame, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(getResources().getColor(R.color.black_30_transparent)), Integer.valueOf(getResources().getColor(R.color.black_00_transparent))});
            ofObject.setDuration(300);
            ofObject.start();
        } else {
            this.mMainviewFrame.setBackgroundColor(getResources().getColor(R.color.black_00_transparent));
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_out_top);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                MitvDeviceMoreActivity.this.a();
            }
        });
        this.mMainview.startAnimation(loadAnimation);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && !this.mFinishing) {
            if (ApiHelper.f18555a) {
                ObjectAnimator ofObject = ObjectAnimator.ofObject(this.mMainviewFrame, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(getResources().getColor(R.color.black_00_transparent)), Integer.valueOf(getResources().getColor(R.color.black_30_transparent))});
                ofObject.setDuration(300);
                ofObject.start();
            } else {
                this.mMainviewFrame.setBackgroundColor(getResources().getColor(R.color.black_30_transparent));
            }
            this.mMainview.setVisibility(0);
            this.mMainview.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_top));
        }
    }
}
