package com.xiaomi.youpin.share.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.xiaomi.youpin.business_common.FrescoUtils;
import com.xiaomi.youpin.common.base.AsyncCallback;
import com.xiaomi.youpin.common.base.YouPinError;
import com.xiaomi.youpin.common.util.SizeUtils;
import com.xiaomi.youpin.share.R;
import com.xiaomi.youpin.share.ShareUtil;
import com.xiaomi.youpin.share.model.PosterData;

public class SharePosterView extends LinearLayout {
    public static final int FLAG_DISMISSING = 5;
    public static final int FLAG_SCALE_LARGE = 3;
    public static final int FLAG_SCALE_LARGE_TO_SMALL = 4;
    public static final int FLAG_SCALE_SMALL = 1;
    public static final int FLAG_SCALE_SMALL_TO_LARGE = 2;
    public static final int FLAG_SCREEN_SHOTTING = 0;

    /* renamed from: a  reason: collision with root package name */
    private static final float f23730a = 0.7f;
    private static final float b = 0.85f;
    private static final float c = 1.0f;
    private static final int d = 300;
    private static final int e = 300;
    private static final int f = 300;
    private static final int g = 500;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private SimpleDraweeView m;
    private ImageView n;
    /* access modifiers changed from: private */
    public int o;
    /* access modifiers changed from: private */
    public Animator.AnimatorListener p;
    /* access modifiers changed from: private */
    public Animator.AnimatorListener q;
    private AnimatorSet r;
    private AnimatorSet s;

    public SharePosterView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SharePosterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SharePosterView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.o = 0;
        a();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec((View.MeasureSpec.getSize(i2) * 16) / 9, 1073741824));
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.yp_view_poster, this, true);
        setOrientation(1);
        setBackgroundResource(R.drawable.yp_share_poster_background);
        setGravity(1);
        this.h = (TextView) findViewById(R.id.yp_new_share_poster_title);
        this.i = (TextView) findViewById(R.id.yp_new_share_poster_desc);
        this.j = (TextView) findViewById(R.id.yp_new_share_poster_tag);
        this.k = (TextView) findViewById(R.id.yp_new_share_poster_price);
        this.l = (TextView) findViewById(R.id.yp_new_share_poster_pricemore);
        this.m = (SimpleDraweeView) findViewById(R.id.yp_new_share_poster_img);
        this.n = (ImageView) findViewById(R.id.yp_new_share_poster_qrcode);
        setEnabled(false);
        int i2 = SizeUtils.a().x;
        int i3 = SizeUtils.a().y;
        setPivotX((float) (i2 / 2));
        setPivotY((float) (i3 / 3));
        b();
        c();
    }

    private void b() {
        this.r = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "scaleX", new float[]{0.7f, b});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "scaleY", new float[]{0.7f, b});
        this.r.setDuration(300);
        this.r.setInterpolator(new DecelerateInterpolator());
        this.r.play(ofFloat).with(ofFloat2);
        this.r.addListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animator) {
                int unused = SharePosterView.this.o = 2;
                if (SharePosterView.this.p != null) {
                    SharePosterView.this.p.onAnimationStart(animator);
                }
            }

            public void onAnimationEnd(Animator animator) {
                int unused = SharePosterView.this.o = 3;
                if (SharePosterView.this.p != null) {
                    SharePosterView.this.p.onAnimationEnd(animator);
                }
            }

            public void onAnimationCancel(Animator animator) {
                if (SharePosterView.this.p != null) {
                    SharePosterView.this.p.onAnimationCancel(animator);
                }
            }

            public void onAnimationRepeat(Animator animator) {
                if (SharePosterView.this.p != null) {
                    SharePosterView.this.p.onAnimationRepeat(animator);
                }
            }
        });
    }

    private void c() {
        this.s = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "scaleX", new float[]{b, 0.7f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "scaleY", new float[]{b, 0.7f});
        this.s.setDuration(300);
        this.s.setInterpolator(new DecelerateInterpolator());
        this.s.play(ofFloat).with(ofFloat2);
        this.s.addListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animator) {
                int unused = SharePosterView.this.o = 4;
                if (SharePosterView.this.q != null) {
                    SharePosterView.this.q.onAnimationStart(animator);
                }
            }

            public void onAnimationEnd(Animator animator) {
                int unused = SharePosterView.this.o = 1;
                if (SharePosterView.this.q != null) {
                    SharePosterView.this.q.onAnimationEnd(animator);
                }
            }

            public void onAnimationCancel(Animator animator) {
                if (SharePosterView.this.q != null) {
                    SharePosterView.this.q.onAnimationCancel(animator);
                }
            }

            public void onAnimationRepeat(Animator animator) {
                if (SharePosterView.this.q != null) {
                    SharePosterView.this.q.onAnimationRepeat(animator);
                }
            }
        });
    }

    public int getFlag() {
        return this.o;
    }

    public Bitmap getBitmap() {
        setDrawingCacheEnabled(true);
        buildDrawingCache(true);
        Bitmap drawingCache = getDrawingCache();
        if (drawingCache == null) {
            return drawingCache;
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawingCache);
        setDrawingCacheEnabled(false);
        return createBitmap;
    }

    public void setPoster(PosterData posterData, String str, Bitmap bitmap, final AsyncCallback<Void, YouPinError> asyncCallback) {
        this.h.setText(posterData.f23682a);
        this.i.setText(posterData.b);
        if (TextUtils.isEmpty(posterData.f)) {
            this.j.setVisibility(8);
        } else {
            this.j.setVisibility(0);
            this.j.setText(posterData.f);
        }
        this.k.setText(ShareUtil.a(posterData.d));
        if (posterData.e) {
            this.l.setVisibility(0);
        } else {
            this.l.setVisibility(8);
        }
        FrescoUtils.a(this.m, str, (ResizeOptions) null, 0, new FrescoUtils.OnImageLoadedCallback() {
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
        this.n.setImageBitmap(bitmap);
    }

    public void startScreenshots(final Animator.AnimatorListener animatorListener) {
        AnimatorSet animatorSet = new AnimatorSet();
        int i2 = SizeUtils.a().y;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "scaleX", new float[]{1.0f, 0.7f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "scaleY", new float[]{1.0f, 0.7f});
        animatorSet.setDuration(300);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.addListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animator) {
                SharePosterView.this.setVisibility(0);
                if (animatorListener != null) {
                    animatorListener.onAnimationStart(animator);
                }
            }

            public void onAnimationEnd(Animator animator) {
                int unused = SharePosterView.this.o = 1;
                SharePosterView.this.setEnabled(true);
                if (animatorListener != null) {
                    animatorListener.onAnimationEnd(animator);
                }
            }

            public void onAnimationCancel(Animator animator) {
                if (animatorListener != null) {
                    animatorListener.onAnimationCancel(animator);
                }
            }

            public void onAnimationRepeat(Animator animator) {
                if (animatorListener != null) {
                    animatorListener.onAnimationRepeat(animator);
                }
            }
        });
        animatorSet.start();
    }

    public void setZoomAnimatorListener(Animator.AnimatorListener animatorListener, Animator.AnimatorListener animatorListener2) {
        this.p = animatorListener;
        this.q = animatorListener2;
    }

    public void startZoom() {
        if (this.o == 1) {
            startZoomOut();
        } else if (this.o == 3) {
            startZoomIn();
        }
    }

    public void startZoomOut() {
        this.r.start();
    }

    public void startZoomIn() {
        this.s.start();
    }

    public void dismiss(final Animator.AnimatorListener animatorListener) {
        if (this.o != 5) {
            this.o = 5;
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "translationY", new float[]{0.0f, (float) SizeUtils.a().y});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "alpha", new float[]{1.0f, 0.0f});
            animatorSet.setDuration(500);
            animatorSet.setInterpolator(new AccelerateInterpolator());
            animatorSet.play(ofFloat).with(ofFloat2);
            animatorSet.addListener(new Animator.AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    if (animatorListener != null) {
                        animatorListener.onAnimationStart(animator);
                    }
                }

                public void onAnimationEnd(Animator animator) {
                    if (animatorListener != null) {
                        animatorListener.onAnimationEnd(animator);
                    }
                }

                public void onAnimationCancel(Animator animator) {
                    if (animatorListener != null) {
                        animatorListener.onAnimationCancel(animator);
                    }
                }

                public void onAnimationRepeat(Animator animator) {
                    if (animatorListener != null) {
                        animatorListener.onAnimationRepeat(animator);
                    }
                }
            });
            animatorSet.start();
        }
    }
}
