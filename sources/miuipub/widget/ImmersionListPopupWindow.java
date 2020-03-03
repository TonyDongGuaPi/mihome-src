package miuipub.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.miuipub.internal.util.DeviceHelper;
import com.miuipub.internal.variable.Android_Widget_PopupWindow_class;
import miuipub.app.IActivity;
import miuipub.util.AttributeResolver;
import miuipub.v6.R;
import miuipub.view.inputmethod.InputMethodHelper;

public class ImmersionListPopupWindow extends PopupWindow {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3086a = "ImmersionListPopupWindow";
    private static final int b = 3;
    /* access modifiers changed from: private */
    public Context c;
    /* access modifiers changed from: private */
    public FrameLayout d;
    /* access modifiers changed from: private */
    public View e;
    /* access modifiers changed from: private */
    public ListView f;
    /* access modifiers changed from: private */
    public ListAdapter g;
    /* access modifiers changed from: private */
    public AdapterView.OnItemClickListener h;
    /* access modifiers changed from: private */
    public LayoutAnimationController i;
    /* access modifiers changed from: private */
    public LayoutAnimationController j;
    private AnimationListener k = new AnimationListener();
    private View l;
    private Runnable m;
    private Drawable n;

    public ImmersionListPopupWindow(Context context) {
        super(context);
        this.c = context;
        setFocusable(true);
        setWindowLayoutMode(-1, -1);
        this.d = new FrameLayout(context);
        this.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ImmersionListPopupWindow.this.dismiss();
            }
        });
        super.setContentView(this.d);
        Android_Widget_PopupWindow_class android_Widget_PopupWindow_class = Android_Widget_PopupWindow_class.Factory.getInstance().get();
        android_Widget_PopupWindow_class.setLayoutInScreenEnabled(this, true);
        android_Widget_PopupWindow_class.setLayoutInsetDecor(this, true);
    }

    public void a(ListAdapter listAdapter) {
        this.g = listAdapter;
    }

    public void a(AdapterView.OnItemClickListener onItemClickListener) {
        this.h = onItemClickListener;
    }

    public void setContentView(View view) {
        this.e = view;
    }

    public View getContentView() {
        return this.e;
    }

    public void a(View view, ViewGroup viewGroup) {
        if (view == null) {
            Log.e(f3086a, "show: anchor is null");
            return;
        }
        if (this.e == null) {
            this.e = new ListView(this.c);
            this.e.setId(16908298);
            this.e.setPadding(0, 0, 0, this.c.getResources().getDimensionPixelSize(R.dimen.v6_immersion_list_padding_bottom));
        }
        if (!(this.d.getChildCount() == 1 && this.d.getChildAt(0) == this.e)) {
            this.d.removeAllViews();
            this.d.addView(this.e);
            ViewGroup.LayoutParams layoutParams = this.e.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
        }
        this.f = (ListView) this.e.findViewById(16908298);
        if (this.f == null) {
            Log.e(f3086a, "list not found");
            return;
        }
        this.f.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int headerViewsCount = i - ImmersionListPopupWindow.this.f.getHeaderViewsCount();
                if (headerViewsCount >= 0 && headerViewsCount < ImmersionListPopupWindow.this.g.getCount()) {
                    ImmersionListPopupWindow.this.h.onItemClick(adapterView, view, headerViewsCount, j);
                }
            }
        });
        if (DeviceHelper.d) {
            if (this.i == null) {
                this.i = a();
            }
            this.f.setLayoutAnimation(this.i);
            this.f.setLayoutAnimationListener(this.k);
            this.k.a(true);
        }
        if (this.l == null) {
            c(view, viewGroup);
        }
        this.f.setAdapter(this.g);
        ClipDrawable clipDrawable = new ClipDrawable(a(this.c, view));
        clipDrawable.setRangeRatio(DeviceHelper.d ? 0.0f : 1.0f);
        setBackgroundDrawable(clipDrawable);
        InputMethodHelper.a().b().hideSoftInputFromWindow(view.getWindowToken(), 0);
        a(view);
        showAtLocation(view, 0, 0, 0);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        this.n = drawable;
    }

    public Drawable getBackground() {
        return this.n;
    }

    private void a(View view) {
        if (!isShowing()) {
            Context context = view.getRootView().getContext();
            if (!(context instanceof IActivity)) {
                Log.w(f3086a, "Fail to setup translucent status for unknown context: " + context.getClass().getName());
                this.m = null;
                return;
            }
            final IActivity iActivity = (IActivity) context;
            final int a2 = iActivity.a();
            iActivity.a(AttributeResolver.a(context, R.attr.v6_immersionStatusBarStyle, 0));
            this.m = new Runnable() {
                public void run() {
                    iActivity.a(a2);
                }
            };
        }
    }

    public void b(View view, ViewGroup viewGroup) {
        if (DeviceHelper.d) {
            this.f.setLayoutAnimation(this.i);
            this.k.a(true);
        } else {
            a(1.0f);
        }
        showAtLocation(view, 0, 0, 0);
    }

    /* access modifiers changed from: protected */
    public void c(View view, ViewGroup viewGroup) {
        this.l = LayoutInflater.from(this.c).inflate(R.layout.v6_list_immersion_header, this.f, false);
        this.f.addHeaderView(this.l);
        View findViewById = this.l.findViewById(R.id.close);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ImmersionListPopupWindow.this.a(true);
                }
            });
            if (viewGroup == null) {
                b(findViewById);
            } else {
                d(view, viewGroup);
            }
        }
    }

    private void b(View view) {
        int e2 = AttributeResolver.e(this.c, 16843499);
        this.l.getLayoutParams().height = e2;
        view.measure(0, 0);
        this.l.setPadding(0, 0, 0, (e2 - view.getMeasuredHeight()) / 2);
    }

    private void d(View view, ViewGroup viewGroup) {
        this.l.getLayoutParams().height = viewGroup.getHeight();
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        viewGroup.getLocationOnScreen(iArr2);
        this.l.setPadding(0, 0, (iArr2[0] + viewGroup.getWidth()) - (iArr[0] + view.getWidth()), (iArr2[1] + viewGroup.getHeight()) - (iArr[1] + view.getHeight()));
    }

    /* access modifiers changed from: protected */
    public LayoutAnimationController a() {
        return AnimationUtils.loadLayoutAnimation(this.c, R.anim.v6_immersion_layout_fade_in);
    }

    /* access modifiers changed from: protected */
    public LayoutAnimationController b() {
        return AnimationUtils.loadLayoutAnimation(this.c, R.anim.v6_immersion_layout_fade_out);
    }

    /* access modifiers changed from: protected */
    public Animator a(LayoutAnimationController layoutAnimationController, boolean z) {
        ObjectAnimator objectAnimator;
        long j2;
        long j3 = 0;
        if (z) {
            objectAnimator = ObjectAnimator.ofFloat(getBackground(), "rangeRatio", new float[]{0.0f, 1.0f});
            objectAnimator.setInterpolator(new DecelerateInterpolator());
            j2 = layoutAnimationController.getAnimation().getDuration();
        } else {
            objectAnimator = ObjectAnimator.ofFloat(getBackground(), "rangeRatio", new float[]{1.0f, 0.0f});
            objectAnimator.setInterpolator(new AccelerateInterpolator());
            long duration = layoutAnimationController.getAnimation().getDuration();
            j3 = Math.max(((long) (((float) layoutAnimationController.getAnimation().getDuration()) * ((layoutAnimationController.getDelay() * ((float) (this.f.getAdapter().getCount() - 1))) + 1.0f))) - duration, 0);
            j2 = duration;
        }
        objectAnimator.setDuration(j2);
        objectAnimator.setStartDelay(j3);
        return objectAnimator;
    }

    public void dismiss() {
        if (!isShowing() || !DeviceHelper.d) {
            c();
        } else if (this.f.getLayoutAnimation() == null) {
            if (this.j == null) {
                this.j = b();
            }
            if (this.j != null) {
                this.f.setLayoutAnimation(this.j);
                this.f.setLayoutAnimationListener(this.k);
                this.k.a(false);
                this.f.startLayoutAnimation();
            }
            if (this.m != null) {
                this.m.run();
                this.m = null;
            }
        }
    }

    public void a(boolean z) {
        if (z) {
            dismiss();
        } else {
            c();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.m != null) {
            this.m.run();
            this.m = null;
        }
        if (!DeviceHelper.d) {
            a(0.0f);
        }
        super.dismiss();
    }

    private void a(float f2) {
        Drawable background = getBackground();
        if (background instanceof ClipDrawable) {
            ((ClipDrawable) background).setRangeRatio(f2);
        }
    }

    /* access modifiers changed from: protected */
    public Drawable a(Context context, View view) {
        return new ColorDrawable(context.getResources().getColor(R.color.v6_immersion_window_background_light));
    }

    private class ClipDrawable extends StateListDrawable {
        private Drawable mFooterBackground;
        private float mRangeRatio;

        public int getOpacity() {
            return -3;
        }

        public ClipDrawable(Drawable drawable) {
            this.mFooterBackground = AttributeResolver.b(ImmersionListPopupWindow.this.c, R.attr.v6_immersionWindowFooterBackground);
            addState(new int[0], drawable == null ? new ColorDrawable(ImmersionListPopupWindow.this.c.getResources().getColor(17170445)) : drawable);
        }

        public void setRangeRatio(float f) {
            this.mRangeRatio = f;
            invalidateSelf();
        }

        public void draw(Canvas canvas) {
            canvas.save();
            int bottom = (int) (((float) (ImmersionListPopupWindow.this.e.getBottom() + ImmersionListPopupWindow.this.d.getTop())) * this.mRangeRatio);
            canvas.clipRect(ImmersionListPopupWindow.this.e.getLeft(), 0, ImmersionListPopupWindow.this.e.getRight(), bottom);
            super.draw(canvas);
            canvas.restore();
            Rect bounds = getBounds();
            this.mFooterBackground.setBounds(bounds.left, bottom, bounds.right, bounds.bottom);
            this.mFooterBackground.setAlpha((int) (this.mRangeRatio * 255.0f));
            this.mFooterBackground.draw(canvas);
        }
    }

    private class AnimationListener implements Animation.AnimationListener {
        private boolean b;
        private Animator c;

        public void onAnimationRepeat(Animation animation) {
        }

        private AnimationListener() {
        }

        public void a(boolean z) {
            this.b = z;
        }

        public void onAnimationStart(Animation animation) {
            this.c = ImmersionListPopupWindow.this.a(this.b ? ImmersionListPopupWindow.this.i : ImmersionListPopupWindow.this.j, this.b);
            if (this.c != null) {
                this.c.start();
            }
        }

        public void onAnimationEnd(Animation animation) {
            if (this.c != null) {
                this.c.end();
                this.c = null;
            }
            ImmersionListPopupWindow.this.f.setLayoutAnimation((LayoutAnimationController) null);
            if (!this.b) {
                ImmersionListPopupWindow.this.c();
            }
        }
    }
}
