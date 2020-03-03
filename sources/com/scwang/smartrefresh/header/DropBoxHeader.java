package com.scwang.smartrefresh.header;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import com.scwang.smartrefresh.header.internal.pathview.PathsDrawable;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;
import com.scwang.smartrefresh.layout.util.DensityUtil;

public class DropBoxHeader extends InternalAbstract implements RefreshHeader {
    protected static int[] drawable1Colors = {-1249039, -245496};
    protected static String[] drawable1Paths = {"M3 2h18v20h-18z", "m4,1c-1.105,0 -2,0.895 -2,2v3,11 3,1c0,1.105 0.895,2 2,2h2,12 2c1.105,0 2,-0.895 2,-2v-1,-3 -11,-3c0,-1.105 -0.895,-2 -2,-2h-2,-12 -2zM3.5,3h1c0.276,0 0.5,0.224 0.5,0.5v1c0,0.276 -0.224,0.5 -0.5,0.5h-1c-0.276,0 -0.5,-0.224 -0.5,-0.5v-1c0,-0.276 0.224,-0.5 0.5,-0.5zM19.5,3h1c0.276,0 0.5,0.224 0.5,0.5v1c0,0.276 -0.224,0.5 -0.5,0.5h-1c-0.276,0 -0.5,-0.224 -0.5,-0.5v-1c0,-0.276 0.224,-0.5 0.5,-0.5zM3.5,6h1c0.276,0 0.5,0.224 0.5,0.5v1c0,0.276 -0.224,0.5 -0.5,0.5h-1c-0.276,0 -0.5,-0.224 -0.5,-0.5v-1c0,-0.276 0.224,-0.5 0.5,-0.5zM19.5,6h1c0.276,0 0.5,0.224 0.5,0.5v1c0,0.276 -0.224,0.5 -0.5,0.5h-1c-0.276,0 -0.5,-0.224 -0.5,-0.5v-1c0,-0.276 0.224,-0.5 0.5,-0.5zM3.5,9h1c0.276,0 0.5,0.224 0.5,0.5v1c0,0.276 -0.224,0.5 -0.5,0.5h-1c-0.276,0 -0.5,-0.224 -0.5,-0.5v-1c0,-0.276 0.224,-0.5 0.5,-0.5zM19.5,9h1c0.276,0 0.5,0.224 0.5,0.5v1c0,0.276 -0.224,0.5 -0.5,0.5h-1c-0.276,0 -0.5,-0.224 -0.5,-0.5v-1c0,-0.276 0.224,-0.5 0.5,-0.5zM3.5,12h1c0.276,0 0.5,0.224 0.5,0.5v1c0,0.276 -0.224,0.5 -0.5,0.5h-1c-0.276,0 -0.5,-0.224 -0.5,-0.5v-1c0,-0.276 0.224,-0.5 0.5,-0.5zM19.5,12h1c0.276,0 0.5,0.224 0.5,0.5v1c0,0.276 -0.224,0.5 -0.5,0.5h-1c-0.276,0 -0.5,-0.224 -0.5,-0.5v-1c0,-0.276 0.224,-0.5 0.5,-0.5zM3.5,15h1c0.276,0 0.5,0.224 0.5,0.5v1c0,0.276 -0.224,0.5 -0.5,0.5h-1c-0.276,0 -0.5,-0.224 -0.5,-0.5v-1c0,-0.276 0.224,-0.5 0.5,-0.5zM19.5,15h1c0.276,0 0.5,0.224 0.5,0.5v1c0,0.276 -0.224,0.5 -0.5,0.5h-1c-0.276,0 -0.5,-0.224 -0.5,-0.5v-1c0,-0.276 0.224,-0.5 0.5,-0.5zM3.5,18h1c0.276,0 0.5,0.224 0.5,0.5v1c0,0.276 -0.224,0.5 -0.5,0.5h-1c-0.276,0 -0.5,-0.224 -0.5,-0.5v-1c0,-0.276 0.224,-0.5 0.5,-0.5zM19.5,18h1c0.276,0 0.5,0.224 0.5,0.5v1c0,0.276 -0.224,0.5 -0.5,0.5h-1c-0.276,0 -0.5,-0.224 -0.5,-0.5v-1c0,-0.276 0.224,-0.5 0.5,-0.5z"};
    protected static int[] drawable2Colors = {-76695, -2773417};
    protected static String[] drawable2Paths = {"M49,16.5l-14,-14l-27,0l0,53l41,0z", "m16,23.5h25c0.55,0 1,-0.45 1,-1 0,-0.55 -0.45,-1 -1,-1L16,21.5c-0.55,0 -1,0.45 -1,1 0,0.55 0.45,1 1,1z", "m16,15.5h10c0.55,0 1,-0.45 1,-1 0,-0.55 -0.45,-1 -1,-1L16,13.5c-0.55,0 -1,0.45 -1,1 0,0.55 0.45,1 1,1z", "M41,29.5L16,29.5c-0.55,0 -1,0.45 -1,1 0,0.55 0.45,1 1,1h25c0.55,0 1,-0.45 1,-1 0,-0.55 -0.45,-1 -1,-1z", "M41,37.5L16,37.5c-0.55,0 -1,0.45 -1,1 0,0.55 0.45,1 1,1h25c0.55,0 1,-0.45 1,-1 0,-0.55 -0.45,-1 -1,-1z", "M41,45.5L16,45.5c-0.55,0 -1,0.45 -1,1 0,0.55 0.45,1 1,1h25c0.55,0 1,-0.45 1,-1 0,-0.55 -0.45,-1 -1,-1z", "M49,16.5l-14,-14l0,14z"};
    protected static int[] drawable3Colors = {-6760607};
    protected static String[] drawable3Paths = {"M6.021,2.188L6.021,11.362C5.46,11.327 4.843,11.414 4.229,11.663C2.624,12.312 1.696,13.729 2.155,14.825C2.62,15.924 4.294,16.284 5.898,15.634C7.131,15.134 7.856,14.184 7.965,13.272L7.958,4.387L15.02,3.028L15.02,9.406C14.422,9.343 13.746,9.432 13.076,9.703C11.471,10.353 10.544,11.77 11.004,12.866C11.467,13.964 13.141,14.325 14.746,13.675C15.979,13.174 16.836,12.224 16.947,11.313L16.958,0.002L6.021,2.188L6.021,2.188Z"};
    protected int mAccentColor;
    protected BoxBody mBoxBody;
    protected Drawable mDrawable1;
    protected Drawable mDrawable2;
    protected Drawable mDrawable3;
    protected ValueAnimator mDropOutAnimator;
    protected boolean mDropOutOverFlow;
    protected float mDropOutPercent;
    protected int mHeaderHeight;
    protected Paint mPaint;
    protected Path mPath;
    protected ValueAnimator mReboundAnimator;
    protected float mReboundPercent;
    protected RefreshState mState;

    public DropBoxHeader(Context context) {
        this(context, (AttributeSet) null);
    }

    public DropBoxHeader(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DropBoxHeader(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPath = new Path();
        this.mPaint = new Paint();
        this.mBoxBody = new BoxBody();
        this.mPaint.setAntiAlias(true);
        this.mAccentColor = -9524737;
        setBackgroundColor(-14141883);
        setMinimumHeight(DensityUtil.a(150.0f));
        this.mSpinnerStyle = SpinnerStyle.Scale;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DropBoxHeader);
        if (obtainStyledAttributes.hasValue(R.styleable.DropBoxHeader_dhDrawable1)) {
            this.mDrawable1 = obtainStyledAttributes.getDrawable(R.styleable.DropBoxHeader_dhDrawable1);
        } else {
            PathsDrawable pathsDrawable = new PathsDrawable();
            pathsDrawable.a(drawable1Colors);
            if (!pathsDrawable.a(drawable1Paths)) {
                pathsDrawable.a(2, 1, 20, 22);
            }
            this.mDrawable1 = pathsDrawable;
        }
        if (obtainStyledAttributes.hasValue(R.styleable.DropBoxHeader_dhDrawable2)) {
            this.mDrawable2 = obtainStyledAttributes.getDrawable(R.styleable.DropBoxHeader_dhDrawable2);
        } else {
            PathsDrawable pathsDrawable2 = new PathsDrawable();
            pathsDrawable2.a(drawable2Colors);
            if (!pathsDrawable2.a(drawable2Paths)) {
                pathsDrawable2.a(8, 3, 41, 53);
            }
            this.mDrawable2 = pathsDrawable2;
        }
        if (obtainStyledAttributes.hasValue(R.styleable.DropBoxHeader_dhDrawable3)) {
            this.mDrawable3 = obtainStyledAttributes.getDrawable(R.styleable.DropBoxHeader_dhDrawable3);
        } else {
            PathsDrawable pathsDrawable3 = new PathsDrawable();
            pathsDrawable3.a(drawable3Colors);
            if (!pathsDrawable3.a(drawable3Paths)) {
                pathsDrawable3.a(2, 0, 15, 16);
            }
            this.mDrawable3 = pathsDrawable3;
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        AccelerateInterpolator accelerateInterpolator = new AccelerateInterpolator();
        this.mReboundAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f, 0.0f});
        this.mReboundAnimator.setInterpolator(accelerateInterpolator);
        this.mReboundAnimator.setDuration(300);
        this.mReboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                DropBoxHeader dropBoxHeader = DropBoxHeader.this;
                DropBoxHeader.this.mReboundPercent = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                dropBoxHeader.invalidate();
            }
        });
        this.mReboundAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (DropBoxHeader.this.mState != RefreshState.Refreshing) {
                    DropBoxHeader.this.mDropOutPercent = 0.0f;
                } else if (DropBoxHeader.this.mDropOutAnimator != null) {
                    DropBoxHeader.this.mDropOutAnimator.start();
                }
            }
        });
        this.mDropOutAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.mDropOutAnimator.setInterpolator(accelerateInterpolator);
        this.mDropOutAnimator.setDuration(300);
        this.mDropOutAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (DropBoxHeader.this.mDropOutPercent < 1.0f || DropBoxHeader.this.mDropOutPercent >= 3.0f) {
                    DropBoxHeader.this.mDropOutPercent = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                } else if (DropBoxHeader.this.mDropOutPercent < 2.0f) {
                    DropBoxHeader.this.mDropOutPercent = ((Float) valueAnimator.getAnimatedValue()).floatValue() + 1.0f;
                } else if (DropBoxHeader.this.mDropOutPercent < 3.0f) {
                    DropBoxHeader.this.mDropOutPercent = ((Float) valueAnimator.getAnimatedValue()).floatValue() + 2.0f;
                    if (DropBoxHeader.this.mDropOutPercent == 3.0f) {
                        DropBoxHeader.this.mDropOutOverFlow = true;
                    }
                }
                DropBoxHeader.this.invalidate();
            }
        });
        this.mDropOutAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (DropBoxHeader.this.mReboundAnimator != null) {
                    DropBoxHeader.this.mReboundAnimator.start();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mReboundAnimator != null) {
            this.mReboundAnimator.removeAllUpdateListeners();
            this.mReboundAnimator.removeAllListeners();
            this.mReboundAnimator = null;
        }
        if (this.mDropOutAnimator != null) {
            this.mDropOutAnimator.removeAllUpdateListeners();
            this.mDropOutAnimator.removeAllListeners();
            this.mDropOutAnimator = null;
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        int width = getWidth();
        BoxBody a2 = a(width, getHeight(), a());
        this.mPaint.setColor(ColorUtils.setAlphaComponent(this.mAccentColor, 150));
        canvas.drawPath(b(a2), this.mPaint);
        this.mPaint.setColor(this.mAccentColor);
        canvas.drawPath(a(a2), this.mPaint);
        if (isInEditMode()) {
            this.mDropOutPercent = 2.5f;
        }
        if (this.mDropOutPercent > 0.0f) {
            canvas.clipPath(a(a2, width));
            float min = Math.min(this.mDropOutPercent, 1.0f);
            Rect bounds = this.mDrawable1.getBounds();
            int i = width / 2;
            bounds.offsetTo(i - (bounds.width() / 2), ((int) (((float) ((a2.b - (bounds.height() / 2)) + bounds.height())) * min)) - bounds.height());
            this.mDrawable1.draw(canvas);
            float min2 = Math.min(Math.max(this.mDropOutPercent - 1.0f, 0.0f), 1.0f);
            Rect bounds2 = this.mDrawable2.getBounds();
            bounds2.offsetTo(i - (bounds2.width() / 2), ((int) (((float) ((a2.b - (bounds2.height() / 2)) + bounds2.height())) * min2)) - bounds2.height());
            this.mDrawable2.draw(canvas);
            float min3 = Math.min(Math.max(this.mDropOutPercent - 2.0f, 0.0f), 1.0f);
            Rect bounds3 = this.mDrawable3.getBounds();
            bounds3.offsetTo(i - (bounds3.width() / 2), ((int) (((float) ((a2.b - (bounds3.height() / 2)) + bounds3.height())) * min3)) - bounds3.height());
            this.mDrawable3.draw(canvas);
            if (this.mDropOutOverFlow) {
                bounds.offsetTo(i - (bounds.width() / 2), a2.b - (bounds.height() / 2));
                this.mDrawable1.draw(canvas);
                bounds2.offsetTo(i - (bounds2.width() / 2), a2.b - (bounds2.height() / 2));
                this.mDrawable2.draw(canvas);
                bounds3.offsetTo(i - (bounds3.width() / 2), a2.b - (bounds3.height() / 2));
                this.mDrawable3.draw(canvas);
            }
        }
        super.dispatchDraw(canvas);
    }

    private int a() {
        return this.mHeaderHeight / 5;
    }

    @NonNull
    private Path a(BoxBody boxBody, int i) {
        this.mPath.reset();
        this.mPath.lineTo(0.0f, (float) boxBody.f);
        this.mPath.lineTo((float) boxBody.e, (float) boxBody.f);
        this.mPath.lineTo((float) boxBody.f8731a, (float) boxBody.b);
        this.mPath.lineTo((float) boxBody.h, (float) boxBody.f);
        float f = (float) i;
        this.mPath.lineTo(f, (float) boxBody.f);
        this.mPath.lineTo(f, 0.0f);
        this.mPath.close();
        return this.mPath;
    }

    @NonNull
    private BoxBody a(int i, int i2, int i3) {
        return this.mBoxBody.a(i, i2, i3, i3 / 2);
    }

    @NonNull
    private Path a(BoxBody boxBody) {
        this.mPath.reset();
        double d = (double) this.mReboundPercent;
        Double.isNaN(d);
        double d2 = d * 1.2566370614359172d;
        float f = (float) (((boxBody.f8731a - boxBody.e) * 4) / 5);
        double d3 = 1.0471975511965976d - (d2 / 2.0d);
        float sin = ((float) Math.sin(d3)) * f;
        float cos = ((float) Math.cos(d3)) * f;
        this.mPath.moveTo((float) boxBody.e, (float) boxBody.f);
        this.mPath.lineTo((float) boxBody.f8731a, (float) boxBody.d);
        this.mPath.lineTo(((float) boxBody.f8731a) - sin, ((float) boxBody.d) - cos);
        this.mPath.lineTo(((float) boxBody.e) - sin, ((float) boxBody.f) - cos);
        this.mPath.close();
        double d4 = d2 + 1.0471975511965976d;
        float sin2 = ((float) Math.sin(d4)) * f;
        float cos2 = ((float) Math.cos(d4)) * f;
        this.mPath.moveTo((float) boxBody.e, (float) boxBody.f);
        this.mPath.lineTo((float) boxBody.f8731a, (float) ((boxBody.c + boxBody.d) / 2));
        this.mPath.lineTo(((float) boxBody.f8731a) - sin2, ((float) ((boxBody.c + boxBody.d) / 2)) + cos2);
        this.mPath.lineTo(((float) boxBody.e) - sin2, ((float) boxBody.f) + cos2);
        this.mPath.close();
        float sin3 = ((float) Math.sin(d3)) * f;
        float cos3 = ((float) Math.cos(d3)) * f;
        this.mPath.moveTo((float) boxBody.h, (float) boxBody.f);
        this.mPath.lineTo((float) boxBody.f8731a, (float) boxBody.d);
        this.mPath.lineTo(((float) boxBody.f8731a) + sin3, ((float) boxBody.d) - cos3);
        this.mPath.lineTo(((float) boxBody.h) + sin3, ((float) boxBody.f) - cos3);
        this.mPath.close();
        float sin4 = ((float) Math.sin(d4)) * f;
        float cos4 = f * ((float) Math.cos(d4));
        this.mPath.moveTo((float) boxBody.h, (float) boxBody.f);
        this.mPath.lineTo((float) boxBody.f8731a, (float) ((boxBody.c + boxBody.d) / 2));
        this.mPath.lineTo(((float) boxBody.f8731a) + sin4, ((float) ((boxBody.c + boxBody.d) / 2)) + cos4);
        this.mPath.lineTo(((float) boxBody.h) + sin4, ((float) boxBody.f) + cos4);
        this.mPath.close();
        return this.mPath;
    }

    @NonNull
    private Path b(BoxBody boxBody) {
        this.mPath.reset();
        this.mPath.moveTo((float) boxBody.e, (float) boxBody.g);
        this.mPath.lineTo((float) boxBody.f8731a, (float) boxBody.c);
        this.mPath.lineTo((float) boxBody.h, (float) boxBody.g);
        this.mPath.quadTo(((float) boxBody.h) + (((float) (boxBody.i / 2)) * this.mReboundPercent), (float) boxBody.b, (float) boxBody.h, (float) boxBody.f);
        this.mPath.lineTo((float) boxBody.f8731a, (float) boxBody.d);
        this.mPath.lineTo((float) boxBody.e, (float) boxBody.f);
        this.mPath.quadTo(((float) boxBody.e) - (((float) (boxBody.i / 2)) * this.mReboundPercent), (float) boxBody.b, (float) boxBody.e, (float) boxBody.g);
        this.mPath.close();
        return this.mPath;
    }

    public void onMoving(boolean z, float f, int i, int i2, int i3) {
        if (!z || this.mState != RefreshState.Refreshing) {
            this.mReboundPercent = (((float) Math.max(0, i - i2)) * 1.0f) / ((float) i3);
        }
    }

    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
        this.mState = refreshState2;
        if (refreshState2 == RefreshState.None) {
            this.mDropOutOverFlow = false;
        }
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        if (iArr.length > 0) {
            setBackgroundColor(iArr[0]);
            if (iArr.length > 1) {
                this.mAccentColor = iArr[1];
            }
        }
    }

    public void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i2) {
        this.mHeaderHeight = i;
        int a2 = a();
        this.mDrawable1.setBounds(0, 0, a2, a2);
        this.mDrawable2.setBounds(0, 0, a2, a2);
        this.mDrawable3.setBounds(0, 0, a2, a2);
    }

    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        if (this.mDropOutAnimator != null) {
            this.mDropOutAnimator.start();
        }
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        this.mDropOutPercent = 0.0f;
        return 0;
    }

    private static class BoxBody {

        /* renamed from: a  reason: collision with root package name */
        int f8731a;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h;
        int i;

        private BoxBody() {
        }

        /* access modifiers changed from: package-private */
        public BoxBody a(int i2, int i3, int i4, int i5) {
            this.i = i4;
            this.f8731a = i2 / 2;
            this.c = i3 - i5;
            this.d = this.c - (i4 * 2);
            int i6 = this.f8731a;
            double d2 = (double) i4;
            double sin = Math.sin(1.0471975511965976d);
            Double.isNaN(d2);
            this.e = i6 - ((int) (d2 * sin));
            int i7 = i4 / 2;
            this.f = this.d + i7;
            this.g = this.c - i7;
            this.h = i2 - this.e;
            this.b = this.c - i4;
            return this;
        }
    }
}
