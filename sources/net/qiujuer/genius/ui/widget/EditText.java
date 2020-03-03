package net.qiujuer.genius.ui.widget;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.facebook.react.modules.appstate.AppStateModule;
import net.qiujuer.genius.ui.R;
import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.compat.UiCompat;
import net.qiujuer.genius.ui.drawable.shape.BorderShape;

public class EditText extends android.widget.EditText {
    private static final int ANIMATION_DURATION = 250;
    private static final Interpolator ANIMATION_INTERPOLATOR = new DecelerateInterpolator();
    private static final Property<EditText, TitleProperty> TITLE_PROPERTY = new Property<EditText, TitleProperty>(TitleProperty.class, "titleProperty") {
        /* renamed from: a */
        public TitleProperty get(EditText editText) {
            return editText.mCurTitleProperty;
        }

        /* renamed from: a */
        public void set(EditText editText, TitleProperty titleProperty) {
            editText.setTitleProperty(titleProperty);
        }
    };
    private boolean isAttachWindow;
    private boolean isHaveText;
    private ObjectAnimator mAnimator;
    /* access modifiers changed from: private */
    public TitleProperty mCurTitleProperty;
    private int mHintTitleModel;
    private Rect mHintTitlePadding = new Rect();
    private int mHintTitleTextSize;
    private ColorStateList mLineColor;
    private int mLineSize;
    private TextWatcher mTextWatcher;
    private TextPaint mTitlePaint;
    private int mTruePaddingTop;

    public EditText(Context context) {
        super(context);
    }

    public EditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, R.attr.gEditTextStyle, R.style.Genius_Widget_EditText);
    }

    public EditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i, R.style.Genius_Widget_EditText);
    }

    @TargetApi(21)
    public EditText(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet, i, i2);
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        Typeface a2;
        ColorStateList colorStateList;
        if (attributeSet != null) {
            this.mTruePaddingTop = super.getPaddingTop();
            Context context = getContext();
            Resources resources = getResources();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.EditText, i, i2);
            String string = obtainStyledAttributes.getString(R.styleable.EditText_gFont);
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.EditText_gLineSize, resources.getDimensionPixelSize(R.dimen.g_editText_lineSize));
            ColorStateList colorStateList2 = obtainStyledAttributes.getColorStateList(R.styleable.EditText_gLineColor);
            int i3 = obtainStyledAttributes.getInt(R.styleable.EditText_gHintTitle, 1);
            int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.EditText_gHintTitleTextSize, resources.getDimensionPixelSize(R.dimen.g_editText_hintTitleTextSize));
            int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.EditText_gHintTitlePaddingTop, 0);
            int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.EditText_gHintTitlePaddingBottom, 0);
            int dimensionPixelSize5 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.EditText_gHintTitlePaddingLeft, 0);
            int dimensionPixelSize6 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.EditText_gHintTitlePaddingRight, 0);
            obtainStyledAttributes.recycle();
            if (colorStateList2 == null) {
                if (Build.VERSION.SDK_INT >= 23) {
                    colorStateList2 = resources.getColorStateList(R.color.g_default_edit_view_line, (Resources.Theme) null);
                } else {
                    colorStateList2 = resources.getColorStateList(R.color.g_default_edit_view_line);
                }
            }
            if (!Ui.a(attributeSet, "textColorHint") || getHintTextColors() == null) {
                if (Build.VERSION.SDK_INT >= 23) {
                    colorStateList = resources.getColorStateList(R.color.g_default_edit_view_hint, (Resources.Theme) null);
                } else {
                    colorStateList = resources.getColorStateList(R.color.g_default_edit_view_hint);
                }
                setHintTextColor(colorStateList);
            }
            setLineSize(dimensionPixelSize);
            setLineColor(colorStateList2);
            setHintTitleTextSize(dimensionPixelSize2);
            setHintTitleModel(i3);
            if (!isInEditMode() && string != null && string.length() > 0 && (a2 = Ui.a(context, string)) != null) {
                setTypeface(a2);
            }
            if (!Ui.a(attributeSet, AppStateModule.APP_STATE_BACKGROUND)) {
                initBackground();
            }
            initHintTitleText();
            setHintTitlePadding(dimensionPixelSize5, dimensionPixelSize3, dimensionPixelSize6, dimensionPixelSize4);
        }
    }

    private void initBackground() {
        StateListDrawable stateListDrawable;
        int lineSize = getLineSize();
        if (lineSize == 0) {
            stateListDrawable = null;
        } else {
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.g_editText_lineSize_active_increment) + lineSize;
            int i = lineSize >> 1;
            float f = (float) lineSize;
            ShapeDrawable shapeDrawable = new ShapeDrawable(new BorderShape(new RectF(0.0f, 0.0f, 0.0f, f)));
            shapeDrawable.getPaint().setColor(getLineColor(new int[]{16842910}));
            float f2 = (float) dimensionPixelSize;
            ShapeDrawable shapeDrawable2 = new ShapeDrawable(new BorderShape(new RectF(0.0f, 0.0f, 0.0f, f2)));
            shapeDrawable2.getPaint().setColor(getLineColor(new int[]{16842919, 16842910}));
            ShapeDrawable shapeDrawable3 = new ShapeDrawable(new BorderShape(new RectF(0.0f, 0.0f, 0.0f, f2)));
            shapeDrawable3.getPaint().setColor(getLineColor(new int[]{16842908, 16842910}));
            float f3 = (float) i;
            ShapeDrawable shapeDrawable4 = new ShapeDrawable(new BorderShape(new RectF(0.0f, 0.0f, 0.0f, f3), f3, f));
            shapeDrawable4.getPaint().setColor(getLineColor(new int[]{-16842910}));
            stateListDrawable = createStateListDrawable(new Drawable[]{shapeDrawable2, shapeDrawable3, shapeDrawable, shapeDrawable4});
        }
        UiCompat.a((View) this, (Drawable) stateListDrawable);
    }

    private static StateListDrawable createStateListDrawable(Drawable[] drawableArr) {
        if (drawableArr == null || drawableArr.length < 4) {
            return null;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919, 16842910}, drawableArr[0]);
        stateListDrawable.addState(new int[]{16842908, 16842910}, drawableArr[1]);
        stateListDrawable.addState(new int[]{16842910}, drawableArr[2]);
        stateListDrawable.addState(new int[]{-16842910}, drawableArr[3]);
        return stateListDrawable;
    }

    private void initHintTitleText() {
        if (isShowTitle()) {
            if (this.mTitlePaint == null) {
                this.mTitlePaint = new TextPaint(1);
                this.mTitlePaint.density = getResources().getDisplayMetrics().density;
                this.mTitlePaint.setTextAlign(Paint.Align.LEFT);
                this.mTitlePaint.setTypeface(getTypeface());
            }
            if (this.mTextWatcher == null) {
                this.mTextWatcher = new TextWatcher() {
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public void afterTextChanged(Editable editable) {
                        EditText.this.checkShowTitle(editable, false);
                    }
                };
                addTextChangedListener(this.mTextWatcher);
            }
            checkShowTitle(getEditableText(), false);
            return;
        }
        if (this.mTextWatcher != null) {
            removeTextChangedListener(this.mTextWatcher);
            this.mTextWatcher = null;
        }
        this.mTitlePaint = null;
        this.mCurTitleProperty = null;
        this.mAnimator = null;
    }

    /* access modifiers changed from: private */
    public void checkShowTitle(Editable editable, boolean z) {
        if (isShowTitle() && getWidth() > 0) {
            boolean z2 = editable != null && editable.length() > 0;
            if (z2 != this.isHaveText || (z2 && z)) {
                this.isHaveText = z2;
                animateShowTitle(this.isHaveText);
            }
        }
    }

    private boolean isShowTitle() {
        return this.mHintTitleModel != 0;
    }

    public void setTypeface(Typeface typeface) {
        super.setTypeface(typeface);
        if (this.mTitlePaint != null) {
            this.mTitlePaint.setTypeface(typeface);
        }
    }

    public int getPaddingTop() {
        return this.mTruePaddingTop;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mTruePaddingTop = i2;
        if (isShowTitle()) {
            i2 += this.mHintTitleTextSize + this.mHintTitlePadding.top + this.mHintTitlePadding.bottom;
        }
        super.setPadding(i, i2, i3, i4);
    }

    public Rect getHintTitlePadding() {
        return this.mHintTitlePadding;
    }

    public void setHintTitlePadding(int i, int i2, int i3, int i4) {
        this.mHintTitlePadding.set(i, i2, i3, i4);
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    public void setLineSize(int i) {
        if (this.mLineSize != i) {
            this.mLineSize = i;
            invalidate();
        }
    }

    public void setLineColor(ColorStateList colorStateList) {
        if (this.mLineColor != colorStateList) {
            this.mLineColor = colorStateList;
            invalidate();
        }
    }

    public void setHintTitleModel(int i) {
        if (this.mHintTitleModel != i) {
            this.mHintTitleModel = i;
            initHintTitleText();
            setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
            invalidate();
        }
    }

    public void setHintTitleTextSize(int i) {
        if (this.mHintTitleTextSize != i) {
            this.mHintTitleTextSize = i;
            invalidate();
        }
    }

    public int getLineSize() {
        return this.mLineSize;
    }

    public ColorStateList getLineColor() {
        return this.mLineColor;
    }

    private int getLineColor(int[] iArr) {
        ColorStateList lineColor = getLineColor();
        if (lineColor == null) {
            return 0;
        }
        return lineColor.getColorForState(iArr, lineColor.getDefaultColor());
    }

    public int getHintTitleModel() {
        return this.mHintTitleModel;
    }

    public int getTitleTextSize() {
        return this.mHintTitleTextSize;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        CharSequence hint;
        super.onDraw(canvas);
        if (isShowTitle() && this.mTitlePaint != null && this.mCurTitleProperty != null && this.mCurTitleProperty.b != 0 && (hint = getHint()) != null) {
            int currentHintTextColor = getCurrentHintTextColor();
            int a2 = Ui.a(Color.alpha(currentHintTextColor), this.mCurTitleProperty.b);
            if (currentHintTextColor != 0 && a2 != 0 && this.mCurTitleProperty.f3154a > 0) {
                this.mTitlePaint.setTextSize((float) this.mCurTitleProperty.f3154a);
                this.mTitlePaint.setColor(currentHintTextColor);
                this.mTitlePaint.setAlpha(a2);
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                if ((scrollX | scrollY) == 0) {
                    canvas.drawText(hint, 0, hint.length(), (float) this.mCurTitleProperty.c, (float) (this.mCurTitleProperty.d + this.mCurTitleProperty.f3154a), this.mTitlePaint);
                    return;
                }
                canvas.translate((float) scrollX, (float) scrollY);
                canvas.drawText(hint, 0, hint.length(), (float) this.mCurTitleProperty.c, (float) (this.mCurTitleProperty.d + this.mCurTitleProperty.f3154a), this.mTitlePaint);
                canvas.translate((float) (-scrollX), (float) (-scrollY));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isAttachWindow = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isAttachWindow = false;
        if (this.mTextWatcher != null) {
            removeTextChangedListener(this.mTextWatcher);
            this.mTextWatcher = null;
        }
    }

    @TargetApi(19)
    private boolean isAttachWindow() {
        if (Build.VERSION.SDK_INT < 19) {
            return this.isAttachWindow;
        }
        return isAttachedToWindow();
    }

    public void invalidate() {
        if (isAttachWindow()) {
            super.invalidate();
        }
    }

    public void setGravity(int i) {
        int gravity = getGravity();
        super.setGravity(i);
        if (gravity != i) {
            checkShowTitle(getEditableText(), true);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        checkShowTitle(getEditableText(), true);
    }

    /* access modifiers changed from: private */
    public void setTitleProperty(TitleProperty titleProperty) {
        this.mCurTitleProperty = titleProperty;
        invalidate();
    }

    private int getTextLen() {
        TextPaint paint = getPaint();
        if (this.mTitlePaint != null) {
            return (int) paint.measureText(getHint().toString());
        }
        return 0;
    }

    private int getHintTextLen(int i) {
        TextPaint textPaint = this.mTitlePaint;
        if (textPaint == null) {
            return 0;
        }
        textPaint.setTextSize((float) i);
        return (int) textPaint.measureText(getHint().toString());
    }

    private TitleProperty getStartProperty(boolean z) {
        TitleProperty titleProperty = new TitleProperty();
        if (this.mCurTitleProperty != null) {
            titleProperty.a(this.mCurTitleProperty);
        } else if (z) {
            copyTextProperty(titleProperty);
        } else {
            copyHintProperty(titleProperty);
        }
        return titleProperty;
    }

    private TitleProperty getEndProperty(boolean z) {
        TitleProperty titleProperty = new TitleProperty();
        if (z) {
            copyHintProperty(titleProperty);
        } else {
            copyTextProperty(titleProperty);
        }
        return titleProperty;
    }

    @SuppressLint({"RtlHardcoded"})
    private TitleProperty copyTextProperty(TitleProperty titleProperty) {
        int gravity = getGravity() & 7;
        if (gravity != 1) {
            if (gravity != 3) {
                if (gravity != 5) {
                    if (gravity != 8388611) {
                        if (gravity != 8388613) {
                            int unused = titleProperty.c = getPaddingLeft();
                        }
                    }
                }
                int unused2 = titleProperty.c = (getWidth() - getPaddingRight()) - getTextLen();
            }
            int unused3 = titleProperty.c = getPaddingLeft();
        } else {
            int paddingLeft = getPaddingLeft();
            int unused4 = titleProperty.c = (paddingLeft + (((getWidth() - paddingLeft) - getPaddingRight()) >> 1)) - (getTextLen() / 2);
        }
        int unused5 = titleProperty.b = 0;
        int unused6 = titleProperty.f3154a = (int) getTextSize();
        int unused7 = titleProperty.d = super.getPaddingTop();
        return titleProperty;
    }

    @SuppressLint({"RtlHardcoded"})
    private TitleProperty copyHintProperty(TitleProperty titleProperty) {
        int unused = titleProperty.d = getPaddingTop() + this.mHintTitlePadding.top;
        int unused2 = titleProperty.b = 255;
        int unused3 = titleProperty.f3154a = this.mHintTitleTextSize;
        int gravity = getGravity() & 7;
        if (gravity != 1) {
            if (gravity != 3) {
                if (gravity != 5) {
                    if (gravity != 8388611) {
                        if (gravity != 8388613) {
                            int unused4 = titleProperty.c = getPaddingLeft() + this.mHintTitlePadding.left;
                        }
                    }
                }
                int unused5 = titleProperty.c = ((getWidth() - getPaddingRight()) - this.mHintTitlePadding.right) - getHintTextLen(titleProperty.f3154a);
            }
            int unused6 = titleProperty.c = getPaddingLeft() + this.mHintTitlePadding.left;
        } else {
            int paddingLeft = getPaddingLeft() + this.mHintTitlePadding.left;
            int unused7 = titleProperty.c = (paddingLeft + (((getWidth() - paddingLeft) - (getPaddingRight() + this.mHintTitlePadding.right)) >> 1)) - (getHintTextLen(titleProperty.f3154a) / 2);
        }
        return titleProperty;
    }

    private void animateShowTitle(boolean z) {
        TitleProperty startProperty = getStartProperty(z);
        TitleProperty endProperty = getEndProperty(z);
        ObjectAnimator titleAnimator = getTitleAnimator();
        titleAnimator.setObjectValues(new Object[]{startProperty, endProperty});
        if (isAttachWindow()) {
            titleAnimator.start();
        } else {
            setTitleProperty(endProperty);
        }
    }

    private ObjectAnimator getTitleAnimator() {
        if (this.mAnimator == null) {
            if (this.mCurTitleProperty == null) {
                this.mCurTitleProperty = new TitleProperty();
            }
            this.mAnimator = ObjectAnimator.ofObject(this, TITLE_PROPERTY, new TitleEvaluator(this.mCurTitleProperty), new TitleProperty[]{this.mCurTitleProperty});
            this.mAnimator.setDuration(250);
            this.mAnimator.setInterpolator(ANIMATION_INTERPOLATOR);
        } else {
            this.mAnimator.cancel();
        }
        return this.mAnimator;
    }

    static final class TitleProperty {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f3154a;
        /* access modifiers changed from: private */
        public int b = 255;
        /* access modifiers changed from: private */
        public int c;
        /* access modifiers changed from: private */
        public int d;

        TitleProperty() {
        }

        public void a(TitleProperty titleProperty) {
            this.f3154a = titleProperty.f3154a;
            this.b = titleProperty.b;
            this.c = titleProperty.c;
            this.d = titleProperty.d;
        }
    }

    private static final class TitleEvaluator implements TypeEvaluator<TitleProperty> {

        /* renamed from: a  reason: collision with root package name */
        private final TitleProperty f3153a;

        TitleEvaluator(TitleProperty titleProperty) {
            this.f3153a = titleProperty;
        }

        /* renamed from: a */
        public TitleProperty evaluate(float f, TitleProperty titleProperty, TitleProperty titleProperty2) {
            int unused = this.f3153a.c = (int) (((float) titleProperty.c) + (((float) (titleProperty2.c - titleProperty.c)) * f));
            int unused2 = this.f3153a.d = (int) (((float) titleProperty.d) + (((float) (titleProperty2.d - titleProperty.d)) * f));
            int unused3 = this.f3153a.f3154a = (int) (((float) titleProperty.f3154a) + (((float) (titleProperty2.f3154a - titleProperty.f3154a)) * f));
            int unused4 = this.f3153a.b = (int) (((float) titleProperty.b) + (((float) (titleProperty2.b - titleProperty.b)) * f));
            return this.f3153a;
        }
    }
}
