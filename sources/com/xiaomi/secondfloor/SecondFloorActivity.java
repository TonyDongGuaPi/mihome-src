package com.xiaomi.secondfloor;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import com.xiaomi.yp_ui.utils.FrescoImageLoader;
import java.util.HashMap;

public class SecondFloorActivity extends CommonBaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public SimpleDraweeView f13058a;
    private FrameLayout b;
    private FrameLayout c;
    private ImageView d;
    private String e;
    private String f;
    private int g;
    /* access modifiers changed from: private */
    public int h;
    /* access modifiers changed from: private */
    public int i;
    private boolean j = false;
    private ResizeViewControllerListener k = new ResizeViewControllerListener();

    /* access modifiers changed from: protected */
    public String getPageName() {
        return "$Transiton$";
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_second_floor);
        this.f13058a = (SimpleDraweeView) findViewById(R.id.sf_image);
        this.b = (FrameLayout) findViewById(R.id.sf_container);
        this.d = (ImageView) findViewById(R.id.sf_close);
        this.c = (FrameLayout) findViewById(R.id.sf_close_area);
        a(getIntent());
        this.f13058a.setVisibility(0);
        this.d.setVisibility(0);
        this.b.setAlpha(0.0f);
        this.c.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SecondFloorActivity.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        XmPluginHostApi.instance().addTouchRecord2("shut_down", "", XmPluginHostApi.instance().createSpm(getPageName(), "shut_down", "0"), "");
        finish();
    }

    /* access modifiers changed from: protected */
    public String getUrl() {
        return this.f;
    }

    private void a(Intent intent) {
        if (intent != null) {
            Pair<String, HashMap<String, String>> parseUrlAndParams = UrlConstants.parseUrlAndParams(intent.getStringExtra("url"));
            if (parseUrlAndParams == null) {
                finish();
            }
            if (!TextUtils.equals((CharSequence) parseUrlAndParams.first, UrlConstants.second_floor) || parseUrlAndParams.second == null) {
                finish();
            } else {
                if (((HashMap) parseUrlAndParams.second).containsKey("imageUrl")) {
                    this.e = (String) ((HashMap) parseUrlAndParams.second).get("imageUrl");
                }
                if (((HashMap) parseUrlAndParams.second).containsKey("contentUrl")) {
                    this.f = (String) ((HashMap) parseUrlAndParams.second).get("contentUrl");
                }
                if (((HashMap) parseUrlAndParams.second).containsKey("posY")) {
                    try {
                        this.g = Integer.parseInt((String) ((HashMap) parseUrlAndParams.second).get("posY"));
                    } catch (NumberFormatException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            updateImage(this.e);
            updateContent(this.f);
            a();
        }
    }

    public void updateImage(String str) {
        if (this.f13058a != null && !TextUtils.isEmpty(str)) {
            this.f13058a.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(str) {
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void onGlobalLayout() {
                    SecondFloorActivity.this.a(this.f$1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(String str) {
        if (!this.j) {
            this.j = true;
            this.h = this.f13058a.getWidth();
            this.i = this.f13058a.getHeight();
            new FrescoImageLoader.Builder().a((DraweeView<GenericDraweeHierarchy>) this.f13058a).b(0).a((ControllerListener) this.k).a(ScalingUtils.ScaleType.CENTER_CROP).a(str).a().a();
        }
    }

    public void updateContent(String str) {
        if (!TextUtils.isEmpty(str)) {
            Fragment fragmentByUrl = XmPluginHostApi.instance().getFragmentByUrl(this, str, false);
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            if (supportFragmentManager != null && fragmentByUrl != null) {
                FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
                beginTransaction.replace(R.id.sf_container, fragmentByUrl);
                beginTransaction.commitAllowingStateLoss();
            }
        }
    }

    private void a() {
        if (this.f13058a != null && this.d != null && this.b != null && this.mHandler != null) {
            this.mHandler.postDelayed(new Runnable() {
                public final void run() {
                    SecondFloorActivity.this.b();
                }
            }, 2000);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b() {
        this.b.setAlpha(1.0f);
        this.d.setVisibility(8);
        this.f13058a.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        if (this.f13058a != null) {
            this.f13058a.clearAnimation();
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) (this.g - i2), 0.0f);
            translateAnimation.setDuration(500);
            this.f13058a.setAnimation(translateAnimation);
            this.f13058a.startAnimation(translateAnimation);
        }
    }

    private class ResizeViewControllerListener extends BaseControllerListener<ImageInfo> {
        private Pair<Integer, Integer> b;

        /* renamed from: a */
        public void onIntermediateImageSet(String str, ImageInfo imageInfo) {
        }

        public void onFailure(String str, Throwable th) {
        }

        private ResizeViewControllerListener() {
        }

        /* renamed from: a */
        public void onFinalImageSet(String str, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
            if (imageInfo != null) {
                int height = imageInfo.getHeight();
                int width = imageInfo.getWidth();
                if (this.b == null) {
                    this.b = new Pair<>(Integer.valueOf(height), Integer.valueOf(width));
                }
                if (b()) {
                    a();
                }
            }
        }

        private boolean b() {
            return SecondFloorActivity.this.h > 0 && SecondFloorActivity.this.i > 0;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            if (this.b != null) {
                int intValue = ((Integer) this.b.first).intValue();
                int intValue2 = ((Integer) this.b.second).intValue();
                this.b = null;
                int access$100 = (int) ((((float) (SecondFloorActivity.this.h * intValue)) * 1.0f) / ((float) intValue2));
                int access$200 = access$100 - SecondFloorActivity.this.i;
                if (access$200 > 0 && SecondFloorActivity.this.f13058a != null && (SecondFloorActivity.this.f13058a.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) SecondFloorActivity.this.f13058a.getLayoutParams();
                    marginLayoutParams.height = access$100;
                    marginLayoutParams.topMargin = -access$200;
                    SecondFloorActivity.this.f13058a.setLayoutParams(marginLayoutParams);
                    SecondFloorActivity.this.a(intValue);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.f13058a != null) {
            this.f13058a.clearAnimation();
        }
    }
}
