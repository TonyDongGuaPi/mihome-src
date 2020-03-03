package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.mapsweeper;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import com.alipay.mobile.security.bio.utils.DisplayUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.bridge.ReadableArray;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;

public class CommonSweeperView extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int f17612a = 20;
    private static final short o = 1;
    private static final short p = 2;
    private static final short q = 3;
    private Context b;
    private AbstractDraweeControllerBuilder c;
    /* access modifiers changed from: private */
    public ImageView d;
    private String e;
    /* access modifiers changed from: private */
    public ImageView f;
    private String g;
    private int h;
    private int i;
    private Animation j;
    private boolean k;
    private Scroller l;
    private MapPoint m;
    private float n;
    private Handler r;

    public CommonSweeperView(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public CommonSweeperView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CommonSweeperView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.e = "";
        this.g = "";
        this.m = new MapPoint();
        this.n = 1.0f;
        this.r = new Handler() {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        CommonSweeperView.this.a();
                        return;
                    case 2:
                        if (CommonSweeperView.this.f != null) {
                            CommonSweeperView.this.f.setVisibility(0);
                            return;
                        }
                        return;
                    case 3:
                        if (CommonSweeperView.this.d != null) {
                            CommonSweeperView.this.d.setVisibility(0);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        this.b = context;
        a(context);
    }

    private void a(Context context) {
        this.j = AnimationUtils.loadAnimation(context, R.anim.anim_sweeper_circular_scale);
        this.l = new Scroller(context);
    }

    public void initSweeperView(Image image) {
        if (image != null) {
            if (getChildCount() > 0) {
                removeAllViews();
            }
            int i2 = 20;
            int i3 = image.h <= 0 ? 20 : image.h;
            if (image.g > 0) {
                i2 = image.g;
            }
            this.d = a(image.b, image.f);
            if (this.d != null) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DisplayUtil.dip2px(this.b, (float) i2), DisplayUtil.dip2px(this.b, (float) i3));
                layoutParams.addRule(13, -1);
                this.h = layoutParams.width;
                this.i = layoutParams.height;
                layoutParams.width = layoutParams.width < 3 ? layoutParams.width : layoutParams.width - 2;
                layoutParams.height = layoutParams.height < 3 ? layoutParams.height : layoutParams.height - 2;
                this.d.setVisibility(4);
                addView(this.d, layoutParams);
                this.e = b(image.b);
            }
            this.f = a(image.f17614a, image.f);
            if (this.f != null) {
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(DisplayUtil.dip2px(this.b, (float) i2), DisplayUtil.dip2px(this.b, (float) i3));
                layoutParams2.addRule(13, -1);
                this.h = layoutParams2.width;
                this.i = layoutParams2.height;
                this.f.setVisibility(4);
                addView(this.f, layoutParams2);
                this.g = b(image.f17614a);
            }
            this.r.sendEmptyMessageDelayed(2, 500);
            this.r.sendEmptyMessageDelayed(3, 500);
            int i4 = 126;
            int i5 = image.d < 0 ? 126 : image.d;
            if (image.e >= 0) {
                i4 = image.e;
            }
            setCurrentPosition(i5, i4);
        }
    }

    public void updateSweeperView(Image image) {
        if (image != null) {
            if (this.d != null) {
                if (image.b != null && image.b.size() > 0) {
                    String b2 = b(image.b);
                    if (!this.e.equals(b2)) {
                        int a2 = a(image.b);
                        if (a2 != -1) {
                            this.d.setImageResource(a2);
                        } else {
                            Log.e("MiotMapSweeperView", "updateSweeperView error  " + b2);
                        }
                        this.e = b2;
                    }
                }
                if (image.b == null) {
                    stopSweeperCircularAnimator();
                    this.d.setVisibility(4);
                } else {
                    this.d.setVisibility(0);
                    a();
                }
            }
            if (this.f != null) {
                if (image.f17614a != null && image.f17614a.size() > 0) {
                    String b3 = b(image.f17614a);
                    if (!this.g.equals(b3)) {
                        int a3 = a(image.f17614a);
                        if (a3 != -1) {
                            this.d.setImageResource(a3);
                        } else {
                            Log.e("MiotMapSweeperView", "updateSweeperView error  " + b3);
                        }
                        this.g = b3;
                    }
                }
                if (image.f17614a == null) {
                    this.f.setVisibility(4);
                } else {
                    this.f.setVisibility(0);
                }
            }
        }
    }

    public void setCurrentPosition(int i2, int i3) {
        this.m.f17616a = i2;
        this.m.b = i3;
    }

    public MapPoint getCurrentPosition() {
        return this.m;
    }

    private ImageView a(ReadableArray readableArray, int i2) {
        ImageView imageView = new ImageView(this.b);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        int a2 = a(readableArray);
        if (a2 != -1) {
            imageView.setImageResource(a2);
        }
        imageView.setRotation((float) i2);
        return imageView;
    }

    private int a(ReadableArray readableArray) {
        String b2 = b(readableArray);
        if (b2.contains("robotbgcharging")) {
            return R.drawable.icon_rn_robot_bg_charging;
        }
        if (b2.contains("robotbgerror")) {
            return R.drawable.icon_rn_robot_bg_error;
        }
        if (b2.contains("robotbgsweeping")) {
            return R.drawable.icon_rn_robot_bg_sweeping;
        }
        if (b2.contains("resources_images_robot")) {
            return R.drawable.icon_rn_robot_image_robot;
        }
        if (b2.contains("resources_images_charger")) {
            return R.drawable.icon_rn_robot_image_charger;
        }
        Log.e("MiotMapSweeperView", "updateSweeperView error  " + b2);
        return -1;
    }

    private String b(ReadableArray readableArray) {
        if (readableArray == null) {
            return "";
        }
        try {
            String string = readableArray.getMap(0).getString("uri");
            return string == null ? "" : string;
        } catch (Exception unused) {
            return "";
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
    }

    public AbstractDraweeControllerBuilder getDraweeControllerBuilder() {
        if (this.c == null) {
            this.c = Fresco.newDraweeControllerBuilder();
        }
        return this.c;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.r.sendEmptyMessageDelayed(1, 200);
    }

    /* access modifiers changed from: private */
    public void a() {
        RnPluginLog.a("CommonSweeperView startSweeperCircularAnimatorDelayed...mIsAnimating=" + this.k);
        if (this.d != null && !this.k) {
            this.d.startAnimation(this.j);
        }
        this.k = true;
    }

    public void startSweeperCircularAnimatorDelayed(long j2) {
        RnPluginLog.a("CommonSweeperView startSweeperCircularAnimatorDelayed...");
        this.r.sendEmptyMessageDelayed(1, j2);
    }

    public void stopSweeperCircularAnimator() {
        RnPluginLog.a("CommonSweeperView stopSweeperCircularAnimator...mIsAnimating=" + this.k);
        if (this.d != null && this.k) {
            this.d.clearAnimation();
        }
        this.k = false;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        stopSweeperCircularAnimator();
        c();
        super.onDetachedFromWindow();
    }

    public void smoothScrollTo(int i2, int i3) {
        a(i2 - this.l.getFinalX(), i3 - this.l.getFinalY());
    }

    private void a(int i2, int i3) {
        this.l.startScroll(this.l.getFinalX(), this.l.getFinalY(), i2, i3);
        invalidate();
    }

    public void computeScroll() {
        if (this.l.computeScrollOffset()) {
            scrollTo(this.l.getCurrX(), this.l.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }

    public void setZoom(float f2) {
        if (this.n != f2) {
            if (f2 - this.n > 1.5f) {
                if (this.f != null) {
                    this.f.setVisibility(4);
                }
                if (this.d != null) {
                    this.d.setVisibility(4);
                }
            }
            this.n = f2;
            stopSweeperCircularAnimator();
            b();
        }
        this.r.sendEmptyMessageDelayed(2, 500);
        this.r.sendEmptyMessageDelayed(3, 500);
    }

    private void b() {
        float f2 = (((float) this.h) / this.n) + (this.n / 10.0f);
        float f3 = (((float) this.i) / this.n) + (this.n / 10.0f);
        int i2 = 1;
        if (this.f != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
            layoutParams.width = f2 < 1.0f ? 1 : (int) f2;
            layoutParams.height = f3 < 1.0f ? 1 : (int) f3;
            this.f.setLayoutParams(layoutParams);
            RnPluginLog.a("CommonSweeperView Sweeper  updateSweeperViewByZoom-->params.width=" + layoutParams.width + "  params.height=" + layoutParams.height + "  mZoom=" + this.n + "  mOriginSweeperWidth=" + this.h + "  mOriginSweeperHeight=" + this.i);
        }
        if (this.d != null) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.d.getLayoutParams();
            layoutParams2.width = f2 < 1.0f ? 1 : (int) f2;
            if (f3 >= 1.0f) {
                i2 = (int) f3;
            }
            layoutParams2.height = i2;
            layoutParams2.width = layoutParams2.width < 3 ? layoutParams2.width : layoutParams2.width - 2;
            layoutParams2.height = layoutParams2.height < 3 ? layoutParams2.height : layoutParams2.height - 2;
            this.d.setLayoutParams(layoutParams2);
        }
    }

    private void c() {
        if (this.r != null) {
            this.r.removeMessages(1);
            this.r.removeMessages(2);
            this.r.removeMessages(3);
        }
    }
}
