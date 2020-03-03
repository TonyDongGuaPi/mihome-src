package com.miuipub.internal.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miuipub.internal.view.EditActionModeImpl;
import com.miuipub.internal.view.menu.ActionMenuItem;
import com.miuipub.internal.view.menu.ActionMenuView;
import com.miuipub.internal.view.menu.MenuBuilder;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import miuipub.v6.R;
import miuipub.view.ActionModeAnimationListener;
import miuipub.view.EditActionMode;

public class ActionBarContextView extends AbsActionBarView implements ActionModeView {
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private final VisibilityAnimListener e;
    private CharSequence f;
    private LinearLayout g;
    private Button h;
    private Button i;
    private TextView j;
    private int k;
    private Drawable l;
    private boolean m;
    /* access modifiers changed from: private */
    public ActionMenuItem n;
    /* access modifiers changed from: private */
    public ActionMenuItem o;
    /* access modifiers changed from: private */
    public WeakReference<ActionMode> p;
    /* access modifiers changed from: private */
    public Animator q;
    private boolean r;
    /* access modifiers changed from: private */
    public int s;
    private int t;
    private List<ActionModeAnimationListener> u;
    private float v;
    private boolean w;
    private View.OnClickListener x;

    /* access modifiers changed from: package-private */
    public int getActionBarStyle() {
        return 16843668;
    }

    public void initForMode(ActionMode actionMode) {
    }

    public void setSplitActionBar(boolean z) {
    }

    public /* bridge */ /* synthetic */ void animateToVisibility(int i2) {
        super.animateToVisibility(i2);
    }

    public /* bridge */ /* synthetic */ void dismissPopupMenus() {
        super.dismissPopupMenus();
    }

    public /* bridge */ /* synthetic */ ActionMenuView getActionMenuView() {
        return super.getActionMenuView();
    }

    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public /* bridge */ /* synthetic */ ActionMenuView getMenuView() {
        return super.getMenuView();
    }

    public /* bridge */ /* synthetic */ boolean isOverflowReserved() {
        return super.isOverflowReserved();
    }

    public /* bridge */ /* synthetic */ void postShowOverflowMenu() {
        super.postShowOverflowMenu();
    }

    public /* bridge */ /* synthetic */ void setContentHeight(int i2) {
        super.setContentHeight(i2);
    }

    public /* bridge */ /* synthetic */ void setSplitView(ActionBarContainer actionBarContainer) {
        super.setSplitView(actionBarContainer);
    }

    public /* bridge */ /* synthetic */ void setSplitWhenNarrow(boolean z) {
        super.setSplitWhenNarrow(z);
    }

    public /* bridge */ /* synthetic */ void setVisibility(int i2) {
        super.setVisibility(i2);
    }

    public ActionBarContextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843668);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.e = new VisibilityAnimListener();
        this.x = new View.OnClickListener() {
            public void onClick(View view) {
                ActionMenuItem actionMenuItem;
                if (view.getId() == 16908313) {
                    actionMenuItem = ActionBarContextView.this.n;
                } else {
                    actionMenuItem = ActionBarContextView.this.o;
                }
                EditActionModeImpl editActionModeImpl = (EditActionModeImpl) ActionBarContextView.this.p.get();
                if (editActionModeImpl != null) {
                    editActionModeImpl.a((MenuBuilder) editActionModeImpl.getMenu(), (MenuItem) actionMenuItem);
                }
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.V6_ActionMode, i2, 0);
        setBackgroundDrawable(obtainStyledAttributes.getDrawable(R.styleable.V6_ActionMode_android_background));
        this.k = obtainStyledAttributes.getResourceId(R.styleable.V6_ActionMode_android_titleTextStyle, 0);
        this.mContentHeight = obtainStyledAttributes.getLayoutDimension(R.styleable.V6_ActionMode_android_height, 0);
        this.l = obtainStyledAttributes.getDrawable(R.styleable.V6_ActionMode_android_backgroundSplit);
        Context context2 = context;
        this.n = new ActionMenuItem(context2, 0, EditActionMode.f3057a, 0, 0, context.getString(17039360));
        this.o = new ActionMenuItem(context2, 0, EditActionMode.b, 0, 0, context.getString(R.string.v6_action_mode_select_all));
        obtainStyledAttributes.recycle();
    }

    public void setContentInset(int i2) {
        this.t = i2;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.e(false);
            this.mActionMenuPresenter.b();
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.c = isOverflowMenuShowing();
        savedState.f8323a = getTitle();
        if (this.i != null) {
            savedState.b = this.i.getText();
        }
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setTitle(savedState.f8323a);
        setButton(EditActionMode.b, savedState.b);
        if (savedState.c) {
            postShowOverflowMenu();
        }
    }

    public CharSequence getTitle() {
        return this.f;
    }

    public void setTitle(CharSequence charSequence) {
        this.f = charSequence;
        if (this.j != null) {
            this.j.setText(charSequence);
            this.g.setVisibility(!TextUtils.isEmpty(this.f) ? 0 : 8);
        }
    }

    /* access modifiers changed from: protected */
    public void initTitle() {
        int i2 = 8;
        if (this.g == null) {
            this.g = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.v6_action_mode_title_item, this, false);
            this.h = (Button) this.g.findViewById(EditActionMode.f3057a);
            this.i = (Button) this.g.findViewById(EditActionMode.b);
            if (this.h != null) {
                CharSequence title = this.n.getTitle();
                this.h.setText(title);
                this.h.setVisibility(TextUtils.isEmpty(title) ? 8 : 0);
                this.h.setOnClickListener(this.x);
            }
            if (this.i != null) {
                CharSequence title2 = this.o.getTitle();
                this.i.setText(this.o.getTitle());
                this.i.setVisibility(TextUtils.isEmpty(title2) ? 8 : 0);
                this.i.setOnClickListener(this.x);
            }
            this.j = (TextView) this.g.findViewById(16908310);
            if (this.k != 0) {
                this.j.setTextAppearance(getContext(), this.k);
            }
        }
        this.j.setText(this.f);
        boolean z = !TextUtils.isEmpty(this.f);
        LinearLayout linearLayout = this.g;
        if (z) {
            i2 = 0;
        }
        linearLayout.setVisibility(i2);
        if (this.g.getParent() == null) {
            addView(this.g);
        }
    }

    public void closeMode() {
        endAnimation();
        this.s = 2;
    }

    public void killMode() {
        removeAllViews();
        if (this.u != null) {
            this.u.clear();
            this.u = null;
        }
        if (this.mSplitView != null) {
            this.mSplitView.removeView(this.mMenuView);
        }
        this.mMenuView = null;
        this.p = null;
    }

    public boolean showOverflowMenu() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.a();
    }

    public boolean hideOverflowMenu() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.e(false);
    }

    public boolean isOverflowMenuShowing() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.c();
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int i4;
        int size = View.MeasureSpec.getSize(i2);
        int size2 = this.mContentHeight > 0 ? this.mContentHeight : View.MeasureSpec.getSize(i3);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size2 - paddingTop, Integer.MIN_VALUE);
        int i5 = 0;
        if (this.mMenuView == null || this.mMenuView.getParent() != this) {
            i4 = 0;
        } else {
            paddingLeft = measureChildView(this.mMenuView, paddingLeft, makeMeasureSpec, 0);
            i4 = this.mMenuView.getMeasuredHeight() + 0;
        }
        if (!(this.g == null || this.g.getVisibility() == 8)) {
            this.g.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824), makeMeasureSpec);
            i4 += this.g.getMeasuredHeight();
        }
        if (this.mContentHeight <= 0) {
            int childCount = getChildCount();
            int i6 = 0;
            for (int i7 = 0; i7 < childCount; i7++) {
                int measuredHeight = getChildAt(i7).getMeasuredHeight() + paddingTop;
                if (measuredHeight > i6) {
                    i6 = measuredHeight;
                }
            }
            if (i6 > 0) {
                i5 = i6 + this.t;
            }
            setMeasuredDimension(size, i5);
            return;
        }
        if (i4 > 0) {
            i5 = this.mContentHeight + this.t;
        }
        setMeasuredDimension(size, i5);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop() + this.t;
        int paddingTop2 = (((i5 - i3) - getPaddingTop()) - getPaddingBottom()) - this.t;
        if (!(this.g == null || this.g.getVisibility() == 8)) {
            positionChild(this.g, paddingLeft, paddingTop, paddingTop2);
        }
        int paddingRight = (i4 - i2) - getPaddingRight();
        if (this.mMenuView != null && this.mMenuView.getParent() == this) {
            positionChildInverse(this.mMenuView, paddingRight, paddingTop, paddingTop2);
        }
        if (this.r) {
            this.s = 1;
            makeInOutAnimator(true).start();
            this.r = false;
        }
    }

    public boolean isTitleOptional() {
        return this.m;
    }

    public void setTitleOptional(boolean z) {
        if (z != this.m) {
            requestLayout();
        }
        this.m = z;
    }

    /* access modifiers changed from: protected */
    public void cancelAnimation() {
        if (this.q != null) {
            this.q.cancel();
            this.q = null;
        }
    }

    /* access modifiers changed from: protected */
    public void endAnimation() {
        if (this.q != null) {
            this.q.end();
            this.q = null;
        }
    }

    public float getAnimationProgress() {
        return this.v;
    }

    public void setAnimationProgress(float f2) {
        this.v = f2;
        notifyAnimationUpdate(this.w, this.v);
    }

    /* access modifiers changed from: protected */
    public Animator makeInOutAnimator(boolean z) {
        float f2;
        float f3;
        if (z == this.w && this.q != null) {
            return new AnimatorSet();
        }
        this.w = z;
        ActionMenuView actionMenuView = this.mMenuView;
        int height = actionMenuView.getHeight();
        float translationY = actionMenuView.getTranslationY();
        float f4 = 0.0f;
        if (z) {
            f4 = (float) ((-this.mContentHeight) - this.t);
            f2 = 0.0f;
            float f5 = ((float) height) + translationY;
            f3 = translationY;
            translationY = f5;
        } else {
            f2 = (float) ((-this.mContentHeight) - this.t);
            f3 = ((float) height) + translationY;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "TranslationY", new float[]{f4, f2});
        if (!this.mSplitActionBar) {
            ofFloat.addListener(this.e.a(z));
            return ofFloat;
        }
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mMenuView, "TranslationY", new float[]{translationY, f3});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this, "AnimationProgress", new float[]{0.0f, 1.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        animatorSet.addListener(this.e.a(z));
        animatorSet.setDuration(250);
        this.q = animatorSet;
        return animatorSet;
    }

    public void setButton(int i2, CharSequence charSequence) {
        int i3 = 0;
        if (i2 == 16908313) {
            if (this.h != null) {
                Button button = this.h;
                if (TextUtils.isEmpty(charSequence)) {
                    i3 = 8;
                }
                button.setVisibility(i3);
                this.h.setText(charSequence);
            }
            this.n.setTitle(charSequence);
        } else if (i2 == 16908314) {
            if (this.i != null) {
                Button button2 = this.i;
                if (TextUtils.isEmpty(charSequence)) {
                    i3 = 8;
                }
                button2.setVisibility(i3);
                this.i.setText(charSequence);
            }
            this.o.setTitle(charSequence);
        }
    }

    public void animateToVisibility(boolean z) {
        cancelAnimation();
        if (z) {
            setVisibility(0);
            this.r = true;
            return;
        }
        makeInOutAnimator(z).start();
    }

    public void notifyAnimationStart(boolean z) {
        if (this.u != null) {
            for (ActionModeAnimationListener a2 : this.u) {
                a2.a(z);
            }
        }
    }

    public void notifyAnimationUpdate(boolean z, float f2) {
        if (this.u != null) {
            for (ActionModeAnimationListener a2 : this.u) {
                a2.a(z, f2);
            }
        }
    }

    public void notifyAnimationEnd(boolean z) {
        if (this.u != null) {
            for (ActionModeAnimationListener b2 : this.u) {
                b2.b(z);
            }
        }
    }

    public void addAnimationListener(ActionModeAnimationListener actionModeAnimationListener) {
        if (actionModeAnimationListener != null) {
            if (this.u == null) {
                this.u = new ArrayList();
            }
            this.u.add(actionModeAnimationListener);
        }
    }

    public void removeAnimationListener(ActionModeAnimationListener actionModeAnimationListener) {
        if (actionModeAnimationListener != null && this.u != null) {
            this.u.remove(actionModeAnimationListener);
        }
    }

    private static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        public CharSequence f8323a;
        public CharSequence b;
        public boolean c;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.c = parcel.readInt() != 0;
            this.f8323a = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.b = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.c ? 1 : 0);
            TextUtils.writeToParcel(this.f8323a, parcel, 0);
            TextUtils.writeToParcel(this.b, parcel, 0);
        }
    }

    protected class VisibilityAnimListener implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        boolean f8324a;
        private boolean c = false;

        public void onAnimationRepeat(Animator animator) {
        }

        protected VisibilityAnimListener() {
        }

        public VisibilityAnimListener a(boolean z) {
            this.f8324a = z;
            return this;
        }

        public void onAnimationStart(Animator animator) {
            ActionBarContextView.this.setVisibility(0);
            this.c = false;
            ActionBarContextView.this.notifyAnimationStart(this.f8324a);
        }

        public void onAnimationEnd(Animator animator) {
            if (!this.c) {
                if (ActionBarContextView.this.s == 2) {
                    ActionBarContextView.this.notifyAnimationEnd(this.f8324a);
                    ActionBarContextView.this.killMode();
                }
                int i = 0;
                int unused = ActionBarContextView.this.s = 0;
                Animator unused2 = ActionBarContextView.this.q = null;
                ActionBarContextView.this.setVisibility(this.f8324a ? 0 : 8);
                if (ActionBarContextView.this.mSplitView != null && ActionBarContextView.this.mMenuView != null) {
                    ActionMenuView actionMenuView = ActionBarContextView.this.mMenuView;
                    if (!this.f8324a) {
                        i = 8;
                    }
                    actionMenuView.setVisibility(i);
                }
            }
        }

        public void onAnimationCancel(Animator animator) {
            this.c = true;
        }
    }
}
