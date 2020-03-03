package com.xiaomi.smarthome.framework.page;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.ApiHelper;
import java.util.ArrayList;
import java.util.Iterator;

public class CommonDeviceMoreActivity extends BaseActivity {
    public static final String ARGS_KEY_DID = "did";
    public static final String ARGS_KEY_SETTING_CLICK = "setting_click";
    public static final String MENUS = "menus";
    public static final String RESULT_KEY_FINISH = "menu";
    public static final int RESULT_VAL_FINISH = 10;
    protected Context mContext;
    View mEmpty;
    boolean mFinishing;
    View mMainview = null;
    View mMainviewFrame = null;
    LinearLayout mMenuContainer;
    ArrayList<String> mMenus;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        this.mFinishing = false;
        this.mMenus = getIntent().getStringArrayListExtra("menus");
        if (this.mMenus == null || this.mMenus.size() == 0) {
            finish();
            return;
        }
        setContentView(R.layout.common_device_more_activity);
        this.mMainviewFrame = findViewById(R.id.device_more_frame);
        this.mMainview = findViewById(R.id.device_more);
        this.mMenuContainer = (LinearLayout) findViewById(R.id.menu_container);
        LayoutInflater from = LayoutInflater.from(this);
        Iterator<String> it = this.mMenus.iterator();
        while (it.hasNext()) {
            final String next = it.next();
            View inflate = from.inflate(R.layout.common_device_more_menu_item, (ViewGroup) null);
            ((TextView) inflate.findViewById(R.id.menu_item)).setText(next);
            inflate.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CommonDeviceMoreActivity.this.selectMenu(next);
                }
            });
            this.mMenuContainer.addView(inflate, new ViewGroup.LayoutParams(-1, getResources().getDimensionPixelOffset(R.dimen.menu_item_height)));
        }
        findViewById(R.id.module_a_3_return_more_more_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommonDeviceMoreActivity.this.onBackPressed();
            }
        });
        this.mEmpty = findViewById(R.id.empty);
        this.mEmpty.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommonDeviceMoreActivity.this.onBackPressed();
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
    public void selectMenu(String str) {
        Intent intent = new Intent();
        intent.putExtra("menu", str);
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
                CommonDeviceMoreActivity.this.a();
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
