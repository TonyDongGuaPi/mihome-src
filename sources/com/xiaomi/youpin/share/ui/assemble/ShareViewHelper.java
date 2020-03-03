package com.xiaomi.youpin.share.ui.assemble;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.xiaomi.youpin.business_common.FrescoUtils;
import com.xiaomi.youpin.common.base.AsyncCallback;
import com.xiaomi.youpin.common.base.YouPinError;
import com.xiaomi.youpin.share.R;
import com.xiaomi.youpin.share.ShareUtil;
import com.xiaomi.youpin.share.config.YouPinShareApi;
import com.xiaomi.youpin.share.model.PosterData;
import com.xiaomi.yp_ui.widget.CommonLoadingView;

class ShareViewHelper {
    private TextView A;
    private TextView B;
    private TextView C;
    private SimpleDraweeView D;
    private SimpleDraweeView E;
    private ImageView F;
    /* access modifiers changed from: private */
    public CommonLoadingView G;
    private AnimWarpper H;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Activity f23751a;
    private boolean b;
    private View c;
    private View d;
    private RelativeLayout e;
    private TextView f;
    private RelativeLayout g;
    private RelativeLayout h;
    private RelativeLayout i;
    private RelativeLayout j;
    private RelativeLayout k;
    private RelativeLayout l;
    private ImageView m;
    private ImageView n;
    private ImageView o;
    private ImageView p;
    private ImageView q;
    private ImageView r;
    private View.OnClickListener s;
    private View.OnClickListener t;
    private View.OnClickListener u;
    private View.OnClickListener v;
    private View.OnClickListener w;
    private View.OnClickListener x;
    private TextView y;
    private TextView z;

    ShareViewHelper(Activity activity) {
        this.f23751a = activity;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (this.f23751a != null) {
            this.f23751a.overridePendingTransition(0, 0);
            this.f23751a.setContentView(R.layout.yp_share_assemble);
            this.H = new AnimWarpper();
            l();
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.H.e();
    }

    /* access modifiers changed from: package-private */
    public void c() {
        this.H.b();
    }

    /* access modifiers changed from: package-private */
    public void d() {
        this.d.setVisibility(0);
        this.d.setAlpha(0.0f);
    }

    /* access modifiers changed from: package-private */
    public void e() {
        this.b = true;
        this.H.c();
        this.f.setText("分享海报给好友");
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public void g() {
        boolean unused = this.H.f = false;
        this.G.postDelayed(new Runnable() {
            public final void run() {
                ShareViewHelper.this.w();
            }
        }, 200);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void w() {
        if (!this.H.f()) {
            this.G.setVisibility(0);
            this.G.startAnimation();
        }
    }

    /* access modifiers changed from: package-private */
    public void h() {
        this.H.d();
    }

    /* access modifiers changed from: package-private */
    public void i() {
        this.d.postDelayed(new Runnable() {
            public final void run() {
                ShareViewHelper.this.v();
            }
        }, 200);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void v() {
        this.H.e();
        this.H.d();
    }

    /* access modifiers changed from: package-private */
    public View j() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public void a(View.OnClickListener onClickListener) {
        this.s = onClickListener;
    }

    /* access modifiers changed from: package-private */
    public void b(View.OnClickListener onClickListener) {
        this.t = onClickListener;
    }

    /* access modifiers changed from: package-private */
    public void c(View.OnClickListener onClickListener) {
        this.u = onClickListener;
    }

    /* access modifiers changed from: package-private */
    public void d(View.OnClickListener onClickListener) {
        this.v = onClickListener;
    }

    /* access modifiers changed from: package-private */
    public void e(View.OnClickListener onClickListener) {
        this.w = onClickListener;
    }

    /* access modifiers changed from: package-private */
    public void f(View.OnClickListener onClickListener) {
        this.x = onClickListener;
    }

    /* access modifiers changed from: package-private */
    public void a(PosterData posterData, String str, Bitmap bitmap, final AsyncCallback<Void, YouPinError> asyncCallback) {
        this.y.setText(posterData.f23682a);
        this.z.setText(posterData.b);
        if (TextUtils.isEmpty(posterData.f)) {
            this.A.setVisibility(8);
        } else {
            this.A.setVisibility(0);
            this.A.setText(posterData.f);
        }
        this.B.setText(ShareUtil.a(posterData.d));
        if (posterData.e) {
            this.C.setVisibility(0);
        } else {
            this.C.setVisibility(8);
        }
        FrescoUtils.a(this.D, str, (ResizeOptions) null, 0, new FrescoUtils.OnImageLoadedCallback() {
            public void a(ImageInfo imageInfo) {
                if (asyncCallback != null) {
                    asyncCallback.b(null);
                }
            }

            public void a(Throwable th) {
                if (asyncCallback != null) {
                    asyncCallback.b(new YouPinError(-1, th.getMessage()));
                }
            }
        });
        ((GenericDraweeHierarchy) this.D.getHierarchy()).setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
        this.F.setImageBitmap(bitmap);
    }

    /* access modifiers changed from: package-private */
    public Bitmap k() {
        ((GenericDraweeHierarchy) this.E.getHierarchy()).setRoundingParams(RoundingParams.fromCornersRadius(0.0f));
        this.d.setDrawingCacheEnabled(true);
        this.d.buildDrawingCache(true);
        Bitmap drawingCache = this.d.getDrawingCache();
        if (drawingCache == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawingCache);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(-1);
        canvas.drawBitmap(drawingCache, 0.0f, 0.0f, new Paint());
        this.d.setDrawingCacheEnabled(false);
        float applyDimension = TypedValue.applyDimension(1, 10.0f, this.f23751a.getResources().getDisplayMetrics());
        ((GenericDraweeHierarchy) this.E.getHierarchy()).setRoundingParams(RoundingParams.fromCornersRadii(applyDimension, applyDimension, applyDimension, applyDimension));
        return createBitmap;
    }

    private void l() {
        this.c = this.f23751a.findViewById(R.id.yp_share_background);
        this.c.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ShareViewHelper.this.h(view);
            }
        });
        this.d = this.f23751a.findViewById(R.id.yp_share_poster_holder);
        this.D = (SimpleDraweeView) this.f23751a.findViewById(R.id.yp_share_poster_img);
        this.E = (SimpleDraweeView) this.f23751a.findViewById(R.id.yp_share_poster_sp_bg);
        this.F = (ImageView) this.f23751a.findViewById(R.id.yp_share_poster_qrcode);
        this.y = (TextView) this.f23751a.findViewById(R.id.yp_share_poster_title);
        this.z = (TextView) this.f23751a.findViewById(R.id.yp_share_poster_title_sub);
        this.B = (TextView) this.f23751a.findViewById(R.id.yp_share_rmb_price);
        this.C = (TextView) this.f23751a.findViewById(R.id.yp_share_rmb_pricemore);
        this.A = (TextView) this.f23751a.findViewById(R.id.yp_share_rmb_price_label);
        this.e = (RelativeLayout) this.f23751a.findViewById(R.id.yp_share_box);
        this.f = (TextView) this.f23751a.findViewById(R.id.yp_share_title);
        ((ImageView) this.f23751a.findViewById(R.id.yp_share_close)).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ShareViewHelper.this.g(view);
            }
        });
        this.g = (RelativeLayout) this.f23751a.findViewById(R.id.rl_yp_share_box_wechat);
        this.h = (RelativeLayout) this.f23751a.findViewById(R.id.rl_yp_share_box_wechat_circle);
        if (!YouPinShareApi.a().f()) {
            this.g.setVisibility(8);
            this.h.setVisibility(8);
        }
        this.i = (RelativeLayout) this.f23751a.findViewById(R.id.rl_yp_share_box_weibo);
        if (!YouPinShareApi.a().g()) {
            this.i.setVisibility(8);
        }
        this.j = (RelativeLayout) this.f23751a.findViewById(R.id.rl_yp_share_box_poster);
        this.k = (RelativeLayout) this.f23751a.findViewById(R.id.rl_yp_share_box_copy_link);
        this.l = (RelativeLayout) this.f23751a.findViewById(R.id.rl_yp_share_box_save);
        this.m = (ImageView) this.f23751a.findViewById(R.id.yp_share_box_wechat);
        this.n = (ImageView) this.f23751a.findViewById(R.id.yp_share_box_wechat_circle);
        this.o = (ImageView) this.f23751a.findViewById(R.id.yp_share_box_weibo);
        this.p = (ImageView) this.f23751a.findViewById(R.id.yp_share_box_poster);
        this.q = (ImageView) this.f23751a.findViewById(R.id.yp_share_box_copy_link);
        this.r = (ImageView) this.f23751a.findViewById(R.id.yp_share_box_save);
        this.m.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ShareViewHelper.this.f(view);
            }
        });
        this.n.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ShareViewHelper.this.e(view);
            }
        });
        this.o.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ShareViewHelper.this.d(view);
            }
        });
        this.p.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ShareViewHelper.this.c(view);
            }
        });
        this.q.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ShareViewHelper.this.b(view);
            }
        });
        this.r.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ShareViewHelper.this.a(view);
            }
        });
        this.G = (CommonLoadingView) this.f23751a.findViewById(R.id.yp_share_loading);
        this.G.setBackground((Drawable) null);
        this.G.postDelayed(new Runnable() {
            public final void run() {
                ShareViewHelper.this.u();
            }
        }, 200);
        m();
        o();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void h(View view) {
        if (!this.b) {
            this.H.d();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void g(View view) {
        this.H.d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void f(View view) {
        if (this.H.f() && !this.H.a() && this.s != null) {
            this.s.onClick(view);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e(View view) {
        if (this.H.f() && !this.H.a() && this.t != null) {
            this.t.onClick(view);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(View view) {
        if (this.H.f() && !this.H.a() && this.u != null) {
            this.u.onClick(view);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        if (this.H.f() && !this.H.a() && this.v != null) {
            this.v.onClick(view);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        if (this.H.f() && !this.H.a() && this.w != null) {
            this.w.onClick(view);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        if (this.H.f() && !this.H.a() && this.x != null) {
            this.x.onClick(view);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void u() {
        if (!this.H.f()) {
            this.G.setVisibility(0);
            this.G.startAnimation();
        }
    }

    private void m() {
        Intent intent = this.f23751a.getIntent();
        boolean booleanExtra = intent.getBooleanExtra("isShareWithPoster", false);
        this.b = booleanExtra;
        if (booleanExtra) {
            this.f.setText("分享海报给好友");
            this.j.setVisibility(8);
            this.k.setVisibility(8);
        } else {
            this.f.setText("分享给好友");
            this.l.setVisibility(8);
        }
        if (intent.getParcelableExtra("poster") == null) {
            n();
        }
    }

    private void n() {
        this.j.setVisibility(8);
    }

    private class AnimWarpper implements Animation.AnimationListener {

        /* renamed from: a  reason: collision with root package name */
        static final int f23753a = 0;
        static final int b = 1;
        static final int c = -1;
        private int e;
        /* access modifiers changed from: private */
        public boolean f;

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }

        private AnimWarpper() {
            this.e = 0;
            this.f = false;
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return this.e == 1 || this.e == -1;
        }

        /* access modifiers changed from: package-private */
        public void b() {
            if (this.f && !a()) {
                this.e = 1;
                ShareViewHelper.this.p().setAnimationListener(this);
            }
        }

        /* access modifiers changed from: package-private */
        public void c() {
            if (this.f && !a()) {
                this.e = 1;
                ShareViewHelper.this.r().setAnimationListener(this);
            }
        }

        /* access modifiers changed from: package-private */
        public void d() {
            if (this.f && !a()) {
                this.e = -1;
                ShareViewHelper.this.q().setAnimationListener(this);
            }
        }

        /* access modifiers changed from: package-private */
        public void e() {
            this.f = true;
            if (ShareViewHelper.this.G.isAnimationLoading()) {
                ShareViewHelper.this.G.stopAnimation();
            }
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(200);
            alphaAnimation.setInterpolator(new DecelerateInterpolator());
            ShareViewHelper.this.G.startAnimation(alphaAnimation);
            ShareViewHelper.this.G.setVisibility(8);
        }

        /* access modifiers changed from: package-private */
        public boolean f() {
            return this.f;
        }

        public void onAnimationEnd(Animation animation) {
            if (this.e == -1) {
                ShareViewHelper.this.f23751a.finish();
            }
            this.e = 0;
        }
    }

    private void o() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(200);
        alphaAnimation.setInterpolator(new DecelerateInterpolator());
        this.c.startAnimation(alphaAnimation);
        this.c.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public Animation p() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        translateAnimation.setDuration(200);
        translateAnimation.setInterpolator(new DecelerateInterpolator());
        this.e.startAnimation(translateAnimation);
        this.e.setVisibility(0);
        return translateAnimation;
    }

    /* access modifiers changed from: private */
    public Animation q() {
        Animation animation;
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(400);
        alphaAnimation.setInterpolator(new DecelerateInterpolator());
        alphaAnimation.setFillAfter(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        translateAnimation.setDuration(400);
        translateAnimation.setInterpolator(new DecelerateInterpolator());
        translateAnimation.setFillAfter(true);
        if (this.b) {
            animation = s();
            alphaAnimation.setStartOffset(animation.getStartOffset());
        } else {
            animation = alphaAnimation;
        }
        this.c.startAnimation(alphaAnimation);
        this.c.setVisibility(8);
        this.e.startAnimation(translateAnimation);
        this.e.setVisibility(8);
        return animation;
    }

    /* access modifiers changed from: private */
    public Animation r() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 2, 1.0f, 2, 0.0f);
        translateAnimation.setDuration(400);
        translateAnimation.setInterpolator(new DecelerateInterpolator());
        this.d.startAnimation(translateAnimation);
        this.d.setVisibility(0);
        this.d.setAlpha(1.0f);
        t();
        return translateAnimation;
    }

    private Animation s() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 2, 0.0f, 2, 1.2f);
        translateAnimation.setDuration(400);
        translateAnimation.setStartOffset(180);
        translateAnimation.setInterpolator(new DecelerateInterpolator());
        this.d.startAnimation(translateAnimation);
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }

    private Animation t() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(400);
        animationSet.setInterpolator(new DecelerateInterpolator());
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.1f, 1.0f, 1.1f, 1.0f, 1, 0.5f, 1, 0.5f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        this.l.startAnimation(animationSet);
        this.l.setVisibility(0);
        AnimationSet animationSet2 = new AnimationSet(true);
        animationSet2.setDuration(400);
        animationSet2.setInterpolator(new DecelerateInterpolator());
        ScaleAnimation scaleAnimation2 = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.8f, 1, 0.5f, 1, 0.5f);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, 0.0f);
        animationSet2.addAnimation(scaleAnimation2);
        animationSet2.addAnimation(alphaAnimation2);
        this.j.startAnimation(animationSet2);
        this.j.setVisibility(4);
        AnimationSet animationSet3 = new AnimationSet(true);
        animationSet3.setInterpolator(new DecelerateInterpolator());
        animationSet3.setDuration(200);
        ScaleAnimation scaleAnimation3 = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.8f, 1, 0.5f, 1, 0.5f);
        AlphaAnimation alphaAnimation3 = new AlphaAnimation(1.0f, 0.0f);
        animationSet3.addAnimation(scaleAnimation3);
        animationSet3.addAnimation(alphaAnimation3);
        this.k.startAnimation(animationSet3);
        this.k.setVisibility(4);
        return animationSet;
    }
}
