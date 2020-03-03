package eu.davidea.flipview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.libra.Color;
import com.xiaomi.smarthome.download.Constants;

public class FlipView extends ViewFlipper implements View.OnClickListener, SVGPictureDrawable {
    public static final int DEFAULT_INITIAL_DELAY = 500;
    public static final int DEFAULT_INTERVAL = 3000;
    public static final int FLIP_DURATION = 100;
    public static final int FLIP_INITIAL_DELAY = 250;
    public static final int FRONT_VIEW_INDEX = 0;
    public static final int INITIAL_ANIMATION_DURATION = 250;
    public static final int REAR_IMAGE_ANIMATION_DURATION = 150;
    public static final int REAR_VIEW_INDEX = 1;
    public static final int SCALE_STEP_DELAY = 35;
    public static final int STOP_LAYOUT_ANIMATION_DELAY = 1500;

    /* renamed from: a  reason: collision with root package name */
    private static final String f2585a = "FlipView";
    private static boolean b = false;
    private static final OnFlippingListener d = new OnFlippingListener() {
        public void a(FlipView flipView, boolean z) {
        }
    };
    private static boolean k = true;
    private static long s = 500;
    /* access modifiers changed from: private */
    public OnFlippingListener c = d;
    private TextView e;
    private ImageView f;
    private int g;
    /* access modifiers changed from: private */
    public ImageView h;
    private int i;
    private PictureDrawable j;
    private Animation l;
    /* access modifiers changed from: private */
    public Animation m;
    private long n;
    private long o;
    private long p;
    private long q;
    private long r;
    private int t = 3000;

    public interface OnFlippingListener {
        void a(FlipView flipView, boolean z);
    }

    public FlipView(Context context) {
        super(context);
        a((AttributeSet) null);
    }

    public FlipView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.FlipView, 0, 0);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.FlipView_checked, false);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.FlipView_enableInitialAnimation, false);
        if (!obtainStyledAttributes.getBoolean(R.styleable.FlipView_animateDesignLayoutOnly, false)) {
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.FlipView_frontLayout, R.layout.flipview_front);
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.FlipView_frontBackground);
            int color = obtainStyledAttributes.getColor(R.styleable.FlipView_frontBackgroundColor, Color.c);
            int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.FlipView_frontImage, 0);
            this.g = (int) obtainStyledAttributes.getDimension(R.styleable.FlipView_frontImagePadding, 0.0f);
            setFrontLayout(resourceId);
            if (drawable == null) {
                setChildBackgroundColor(0, color);
            } else {
                setChildBackgroundDrawable(0, drawable);
            }
            setFrontImage(resourceId2);
            int resourceId3 = obtainStyledAttributes.getResourceId(R.styleable.FlipView_rearLayout, R.layout.flipview_rear);
            Drawable drawable2 = obtainStyledAttributes.getDrawable(R.styleable.FlipView_rearBackground);
            int color2 = obtainStyledAttributes.getColor(R.styleable.FlipView_rearBackgroundColor, Color.c);
            int resourceId4 = obtainStyledAttributes.getResourceId(R.styleable.FlipView_rearImage, R.drawable.ic_action_done);
            this.i = (int) obtainStyledAttributes.getDimension(R.styleable.FlipView_rearImagePadding, 0.0f);
            addRearLayout(resourceId3);
            if (drawable2 == null) {
                setChildBackgroundColor(1, color2);
            } else {
                setChildBackgroundDrawable(1, drawable2);
            }
            setRearImage(resourceId4);
        }
        if (z) {
            flipSilently(true);
        }
        this.o = (long) obtainStyledAttributes.getInteger(R.styleable.FlipView_animationDuration, 100);
        this.p = (long) obtainStyledAttributes.getInteger(R.styleable.FlipView_rearImageAnimationDuration, 150);
        this.q = (long) obtainStyledAttributes.getInteger(R.styleable.FlipView_rearImageAnimationDelay, (int) this.o);
        this.r = (long) obtainStyledAttributes.getInteger(R.styleable.FlipView_anticipateInAnimationTime, 0);
        if (!isInEditMode()) {
            setMainAnimationDuration(this.o);
            if (obtainStyledAttributes.getBoolean(R.styleable.FlipView_animateRearImage, true)) {
                setRearImageAnimation(obtainStyledAttributes.getResourceId(R.styleable.FlipView_rearImageAnimation, 0));
            }
        }
        this.n = (long) obtainStyledAttributes.getInteger(R.styleable.FlipView_initialLayoutAnimationDuration, 250);
        setInitialLayoutAnimation(obtainStyledAttributes.getResourceId(R.styleable.FlipView_initialLayoutAnimation, 0));
        if (z2 && k && !isInEditMode()) {
            animateLayout(getInitialLayoutAnimation());
        }
        obtainStyledAttributes.recycle();
        if (isClickable()) {
            setOnClickListener(this);
        }
    }

    public void setOnFlippingListener(OnFlippingListener onFlippingListener) {
        this.c = onFlippingListener;
    }

    public void onClick(View view) {
        showNext();
    }

    public void setClickable(boolean z) {
        super.setClickable(z);
        if (z) {
            setOnClickListener(this);
        } else {
            setOnClickListener((View.OnClickListener) null);
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (!isAutoStart()) {
            return;
        }
        if (!z) {
            stopFlipping();
        } else {
            postDelayed(new Runnable() {
                public void run() {
                    if (FlipView.this.isEnabled()) {
                        FlipView.this.startFlipping();
                    }
                }
            }, (long) this.t);
        }
    }

    public static void enableLogs(boolean z) {
        b = z;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= 22;
    }

    public static Animation createScaleAnimation() {
        return new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
    }

    public static void resetLayoutAnimationDelay() {
        resetLayoutAnimationDelay(true, 500);
    }

    public static void resetLayoutAnimationDelay(boolean z, @IntRange(from = 0) long j2) {
        k = z;
        s = j2;
    }

    public static void stopLayoutAnimation() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                FlipView.resetLayoutAnimationDelay(false, 500);
            }
        }, Constants.x);
    }

    public void setFlipInterval(@IntRange(from = 0) int i2) {
        super.setFlipInterval(i2);
        this.t = i2;
    }

    public void setInAnimation(Context context, @AnimRes int i2) {
        if (!isInEditMode()) {
            super.setInAnimation(context, i2);
        }
    }

    public void setOutAnimation(Context context, @AnimRes int i2) {
        if (!isInEditMode()) {
            super.setOutAnimation(context, i2);
        }
    }

    private void a(@IntRange(from = 0) long j2) {
        if (getInAnimation() == null) {
            setInAnimation(getContext(), R.anim.grow_from_middle_x_axis);
        }
        super.getInAnimation().setDuration(j2);
        Animation inAnimation = super.getInAnimation();
        if (this.r <= j2) {
            j2 -= this.r;
        }
        inAnimation.setStartOffset(j2);
    }

    private void b(@IntRange(from = 0) long j2) {
        if (getOutAnimation() == null) {
            setOutAnimation(getContext(), R.anim.shrink_to_middle_x_axis);
        }
        super.getOutAnimation().setDuration(j2);
    }

    public void animateLayout(Animation animation) {
        startAnimation(animation);
    }

    public Animation getInitialLayoutAnimation() {
        return this.l;
    }

    public void setInitialLayoutAnimation(@AnimRes int i2) {
        Animation animation;
        if (i2 > 0) {
            try {
                animation = AnimationUtils.loadAnimation(getContext(), i2);
            } catch (Resources.NotFoundException unused) {
                String str = f2585a;
                Log.e(str, "Initial animation with id " + i2 + " could not be found. Initial animation cannot be set!");
                return;
            }
        } else {
            animation = createScaleAnimation();
        }
        setInitialLayoutAnimation(animation);
        if (b) {
            Log.d(f2585a, "Initial animation is active!");
        }
    }

    public final void setInitialLayoutAnimation(Animation animation) {
        this.l = animation;
        animation.setDuration(this.n);
        long j2 = s + 35;
        s = j2;
        animation.setStartOffset(j2);
        if (animation.getInterpolator() == null) {
            animation.setInterpolator(new DecelerateInterpolator());
        }
    }

    public Animation getRearImageAnimation() {
        return this.m;
    }

    public void setRearImageAnimation(@AnimRes int i2) {
        try {
            setRearImageAnimation(AnimationUtils.loadAnimation(getContext(), i2 > 0 ? i2 : R.anim.scale_up));
            if (b) {
                Log.d(f2585a, "Rear animation is active!");
            }
        } catch (Resources.NotFoundException unused) {
            String str = f2585a;
            Log.e(str, "Rear animation with id " + i2 + " could not be found. Rear animation cannot be set!");
        }
    }

    public void setRearImageAnimation(Animation animation) {
        this.m = animation;
        if (this.p > 0) {
            this.m.setDuration(this.p);
        }
    }

    public long getMainAnimationDuration() {
        return getInAnimation().getDuration();
    }

    public void setMainAnimationDuration(@IntRange(from = 0) long j2) {
        if (b) {
            String str = f2585a;
            Log.d(str, "Setting mainAnimationDuration=" + j2);
        }
        this.o = j2;
        a(j2);
        b(j2);
    }

    public long getInitialLayoutAnimationDuration() {
        return this.n;
    }

    public void setInitialLayoutAnimationDuration(@IntRange(from = 0) long j2) {
        if (b) {
            String str = f2585a;
            Log.d(str, "Setting initialLayoutAnimationDuration=" + j2);
        }
        this.n = j2;
        if (this.l != null) {
            this.l.setDuration(j2);
        }
    }

    public long getRearImageAnimationDuration() {
        return this.p;
    }

    public void setRearImageAnimationDuration(@IntRange(from = 0) long j2) {
        if (b) {
            String str = f2585a;
            Log.d(str, "Setting rearImageAnimationDuration=" + j2);
        }
        this.p = j2;
        if (this.m != null) {
            this.m.setDuration(j2);
        }
    }

    public long getAnticipateInAnimationTime() {
        return this.r;
    }

    public void setAnticipateInAnimationTime(@IntRange(from = 0) long j2) {
        if (b) {
            String str = f2585a;
            Log.d(str, "Setting anticipateInAnimationTime=" + j2);
        }
        this.r = j2;
    }

    public long getRearImageAnimationDelay() {
        return this.q;
    }

    public void setRearImageAnimationDelay(@IntRange(from = 0) long j2) {
        if (b) {
            String str = f2585a;
            Log.d(str, "Setting rearImageAnimationDelay=" + j2);
        }
        this.q = j2;
    }

    public final void showNext() {
        showNext(0);
    }

    public final void showNext(long j2) {
        if (b) {
            String str = f2585a;
            Log.d(str, "showNext " + (getDisplayedChild() + 1) + " delay=" + j2);
        }
        flip(getDisplayedChild() + 1, j2);
    }

    public final void showPrevious() {
        showPrevious(0);
    }

    public final void showPrevious(long j2) {
        if (b) {
            String str = f2585a;
            StringBuilder sb = new StringBuilder();
            sb.append("showPrevious ");
            sb.append(getDisplayedChild() - 1);
            sb.append(" delay=");
            sb.append(j2);
            Log.d(str, sb.toString());
        }
        flip(getDisplayedChild() - 1, j2);
    }

    public boolean isFlipped() {
        return getDisplayedChild() > 0;
    }

    public final void flip(boolean z) {
        flip(z, 0);
    }

    public final void flip(boolean z, long j2) {
        flip(z ? 1 : 0, j2);
    }

    public final void flip(int i2, @IntRange(from = 0) long j2) {
        if (isEnabled()) {
            final int a2 = a(i2);
            if (b) {
                String str = f2585a;
                Log.d(str, "Flip! whichChild=" + a2 + ", previousChild=" + getDisplayedChild() + ", delay=" + j2);
            }
            if (a2 != getDisplayedChild()) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        FlipView.this.setDisplayedChild(a2);
                        FlipView.this.a();
                        FlipView.this.c.a(FlipView.this, FlipView.this.isFlipped());
                    }
                }, j2);
            } else if (b) {
                String str2 = f2585a;
                Log.w(str2, "Already flipped to same whichChild=" + i2);
            }
        } else if (b) {
            Log.w(f2585a, "Can't flip while view is disabled");
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        if (isFlipped() && this.h != null && this.m != null) {
            this.h.setAlpha(0.0f);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    FlipView.this.h.setAlpha(1.0f);
                    FlipView.this.h.startAnimation(FlipView.this.m);
                }
            }, this.q);
        }
    }

    public final void flipSilently(boolean z) {
        flipSilently(z ? 1 : 0);
    }

    public final void flipSilently(int i2) {
        if (b) {
            String str = f2585a;
            Log.d(str, "flipSilently whichChild=" + i2);
        }
        int a2 = a(i2);
        Animation inAnimation = super.getInAnimation();
        Animation outAnimation = super.getOutAnimation();
        super.setInAnimation((Animation) null);
        super.setOutAnimation((Animation) null);
        super.setDisplayedChild(a2);
        super.setInAnimation(inAnimation);
        super.setOutAnimation(outAnimation);
    }

    private int a(int i2) {
        if (i2 < 0) {
            return 0;
        }
        return i2 > getChildCount() ? getChildCount() : i2;
    }

    public View getFrontLayout() {
        return getChildAt(0);
    }

    public void setFrontLayout(@LayoutRes int i2) {
        if (i2 == R.layout.flipview_front) {
            if (b) {
                Log.d(f2585a, "Adding inner FrontLayout");
            }
        } else if (b) {
            String str = f2585a;
            Log.d(str, "Setting user FrontLayout " + i2);
        }
        setFrontLayout(LayoutInflater.from(getContext()).inflate(i2, this, false));
    }

    public void setFrontLayout(@NonNull View view) {
        ViewGroup viewGroup;
        if (view instanceof ViewGroup) {
            if (b) {
                Log.d(f2585a, "FrontLayout is a ViewGroup");
            }
            viewGroup = (ViewGroup) view;
        } else {
            viewGroup = this;
        }
        if (viewGroup.getChildAt(0) instanceof ImageView) {
            if (b) {
                Log.d(f2585a, "Found ImageView in FrontLayout");
            }
            this.f = (ImageView) viewGroup.getChildAt(0);
        } else if (viewGroup.getChildAt(0) instanceof TextView) {
            if (b) {
                Log.d(f2585a, "Found TextView in FrontLayout");
            }
            this.e = (TextView) viewGroup.getChildAt(0);
        }
        addView(view, 0);
    }

    public View getRearLayout() {
        return getChildAt(1);
    }

    public void addRearLayout(@LayoutRes int i2) {
        if (i2 == R.layout.flipview_rear) {
            if (b) {
                Log.d(f2585a, "Adding inner RearLayout");
            }
        } else if (b) {
            String str = f2585a;
            Log.d(str, "Adding user RearLayout " + i2);
        }
        addRearLayout(LayoutInflater.from(getContext()).inflate(i2, this, false));
    }

    public void addRearLayout(@NonNull View view) {
        int i2;
        ViewGroup viewGroup;
        int childCount = getChildCount();
        if (b) {
            String str = f2585a;
            Log.d(str, "RearLayout index=" + childCount);
        }
        if (view instanceof ViewGroup) {
            if (b) {
                Log.d(f2585a, "RearLayout is a ViewGroup");
            }
            viewGroup = (ViewGroup) view;
            i2 = 0;
        } else {
            i2 = childCount;
            viewGroup = this;
        }
        if (viewGroup.getChildAt(i2) instanceof ImageView) {
            if (b) {
                Log.d(f2585a, "Found ImageView in the RearLayout");
            }
            this.h = (ImageView) viewGroup.getChildAt(i2);
        } else if (i2 > 2) {
            this.h = null;
        }
        addView(view, getChildCount() == 0 ? 1 : getChildCount());
    }

    public void addView(@NonNull View view, int i2) {
        if (view != null) {
            if (b) {
                String str = f2585a;
                Log.d(str, "Setting child view at index " + i2);
            }
            if (super.getChildAt(i2) != null) {
                super.removeViewAt(i2);
            }
            super.addView(view, i2, super.generateDefaultLayoutParams());
            return;
        }
        throw new IllegalArgumentException("The provided view must not be null");
    }

    public TextView getFrontTextView() {
        return this.e;
    }

    public void setFrontText(@Nullable CharSequence charSequence) {
        if (this.e == null) {
            Log.e(f2585a, "TextView not found in the first child of the FrontLayout. Text cannot be set!");
        } else {
            this.e.setText(charSequence);
        }
    }

    public ImageView getFrontImageView() {
        return this.f;
    }

    public void setFrontImage(int i2) {
        if (this.f == null) {
            if (this.e == null) {
                Log.e(f2585a, "ImageView not found in the first child of the FrontLayout. Image cannot be set!");
            }
        } else if (i2 == 0) {
            Log.e(f2585a, "Invalid imageResId=0");
        } else {
            try {
                this.f.setPadding(this.g, this.g, this.g, this.g);
                this.f.setImageResource(i2);
            } catch (Resources.NotFoundException unused) {
                String str = f2585a;
                Log.e(str, "No front resource image id " + i2 + " found. No Image can be set!");
            }
        }
    }

    public void setFrontImageBitmap(Bitmap bitmap) {
        if (this.f == null) {
            Log.e(f2585a, "ImageView not found in the first child of the FrontLayout. Bitmap cannot be set!");
        } else {
            this.f.setImageBitmap(bitmap);
        }
    }

    public ImageView getRearImageView() {
        return this.h;
    }

    public void setRearImage(int i2) {
        if (this.h == null) {
            Log.e(f2585a, "ImageView not found in the child of the RearLayout. Image cannot be set!");
        } else if (i2 == 0) {
            Log.e(f2585a, "Invalid imageResId=0");
        } else {
            try {
                this.h.setPadding(this.i, this.i, this.i, this.i);
                this.h.setImageResource(i2);
            } catch (Resources.NotFoundException unused) {
                String str = f2585a;
                Log.e(str, "No rear resource image id " + i2 + " found. Image cannot be set!");
            }
        }
    }

    public void setRearImageBitmap(Bitmap bitmap) {
        if (this.h == null) {
            Log.e(f2585a, "ImageView not found in the child of the RearLayout. Bitmap cannot be set!");
        } else {
            this.h.setImageBitmap(bitmap);
        }
    }

    public PictureDrawable getPictureDrawable() {
        return this.j;
    }

    public void setPictureDrawable(PictureDrawable pictureDrawable) {
        this.j = pictureDrawable;
        if (this.f == null) {
            Log.w(f2585a, "ImageView not found in the first child of the FrontLayout. Image cannot be set!");
            return;
        }
        this.f.setLayerType(1, (Paint) null);
        this.f.setImageDrawable(this.j);
    }

    public Bitmap createBitmapFrom(PictureDrawable pictureDrawable, float f2) {
        int ceil = (int) Math.ceil((double) TypedValue.applyDimension(1, f2, getResources().getDisplayMetrics()));
        Bitmap createBitmap = Bitmap.createBitmap(ceil, ceil, Bitmap.Config.ARGB_8888);
        pictureDrawable.setBounds(0, 0, ceil, ceil);
        new Canvas(createBitmap).drawPicture(pictureDrawable.getPicture(), pictureDrawable.getBounds());
        return createBitmap;
    }

    @TargetApi(21)
    public void setChildBackgroundDrawable(int i2, @DrawableRes int i3) {
        Drawable drawable;
        try {
            if (hasLollipop()) {
                drawable = getContext().getDrawable(i3);
            } else {
                drawable = getContext().getResources().getDrawable(i3);
            }
            setChildBackgroundDrawable(i2, drawable);
        } catch (Resources.NotFoundException unused) {
            String str = f2585a;
            Log.e(str, "Resource with id " + i3 + " could not be found. Drawable cannot be set!");
        }
    }

    public void setChildBackgroundDrawable(int i2, Drawable drawable) {
        if (getChildAt(i2) != null) {
            getChildAt(i2).setBackgroundDrawable(drawable);
        }
    }

    public Drawable getChildBackgroundDrawable(int i2) {
        return getChildAt(i2).getBackground();
    }

    public void setChildBackgroundColor(int i2, @ColorInt int i3) {
        setChildBackgroundDrawable(i2, (Drawable) createOvalShapeDrawable(i3));
    }

    public static ShapeDrawable createOvalShapeDrawable(@ColorInt int i2) {
        return a(i2, new OvalShape());
    }

    public static ShapeDrawable createArcShapeDrawable(@ColorInt int i2, float f2, float f3) {
        return a(i2, new ArcShape(f2, f3));
    }

    public static ShapeDrawable createRoundRectShapeDrawable(@ColorInt int i2, float[] fArr, RectF rectF, float[] fArr2) {
        return a(i2, new RoundRectShape(fArr, rectF, fArr2));
    }

    private static ShapeDrawable a(@ColorInt int i2, Shape shape) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(shape);
        shapeDrawable.getPaint().setColor(i2);
        shapeDrawable.getPaint().setAntiAlias(true);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        return shapeDrawable;
    }
}
