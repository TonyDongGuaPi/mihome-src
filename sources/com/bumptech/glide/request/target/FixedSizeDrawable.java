package com.bumptech.glide.request.target;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import com.bumptech.glide.util.Preconditions;

public class FixedSizeDrawable extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    private final Matrix f5070a;
    private final RectF b;
    private final RectF c;
    private Drawable d;
    private State e;
    private boolean f;

    public FixedSizeDrawable(Drawable drawable, int i, int i2) {
        this(new State(drawable.getConstantState(), i, i2), drawable);
    }

    FixedSizeDrawable(State state, Drawable drawable) {
        this.e = (State) Preconditions.a(state);
        this.d = (Drawable) Preconditions.a(drawable);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        this.f5070a = new Matrix();
        this.b = new RectF(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        this.c = new RectF();
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        this.c.set((float) i, (float) i2, (float) i3, (float) i4);
        a();
    }

    public void setBounds(@NonNull Rect rect) {
        super.setBounds(rect);
        this.c.set(rect);
        a();
    }

    private void a() {
        this.f5070a.setRectToRect(this.b, this.c, Matrix.ScaleToFit.CENTER);
    }

    public void setChangingConfigurations(int i) {
        this.d.setChangingConfigurations(i);
    }

    public int getChangingConfigurations() {
        return this.d.getChangingConfigurations();
    }

    @Deprecated
    public void setDither(boolean z) {
        this.d.setDither(z);
    }

    public void setFilterBitmap(boolean z) {
        this.d.setFilterBitmap(z);
    }

    public Drawable.Callback getCallback() {
        return this.d.getCallback();
    }

    @RequiresApi(19)
    public int getAlpha() {
        return this.d.getAlpha();
    }

    public void setColorFilter(int i, @NonNull PorterDuff.Mode mode) {
        this.d.setColorFilter(i, mode);
    }

    public void clearColorFilter() {
        this.d.clearColorFilter();
    }

    @NonNull
    public Drawable getCurrent() {
        return this.d.getCurrent();
    }

    public boolean setVisible(boolean z, boolean z2) {
        return this.d.setVisible(z, z2);
    }

    public int getIntrinsicWidth() {
        return this.e.f5071a;
    }

    public int getIntrinsicHeight() {
        return this.e.b;
    }

    public int getMinimumWidth() {
        return this.d.getMinimumWidth();
    }

    public int getMinimumHeight() {
        return this.d.getMinimumHeight();
    }

    public boolean getPadding(@NonNull Rect rect) {
        return this.d.getPadding(rect);
    }

    public void invalidateSelf() {
        super.invalidateSelf();
        this.d.invalidateSelf();
    }

    public void unscheduleSelf(@NonNull Runnable runnable) {
        super.unscheduleSelf(runnable);
        this.d.unscheduleSelf(runnable);
    }

    public void scheduleSelf(@NonNull Runnable runnable, long j) {
        super.scheduleSelf(runnable, j);
        this.d.scheduleSelf(runnable, j);
    }

    public void draw(@NonNull Canvas canvas) {
        canvas.save();
        canvas.concat(this.f5070a);
        this.d.draw(canvas);
        canvas.restore();
    }

    public void setAlpha(int i) {
        this.d.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.d.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return this.d.getOpacity();
    }

    @NonNull
    public Drawable mutate() {
        if (!this.f && super.mutate() == this) {
            this.d = this.d.mutate();
            this.e = new State(this.e);
            this.f = true;
        }
        return this;
    }

    public Drawable.ConstantState getConstantState() {
        return this.e;
    }

    static final class State extends Drawable.ConstantState {

        /* renamed from: a  reason: collision with root package name */
        final int f5071a;
        final int b;
        private final Drawable.ConstantState c;

        public int getChangingConfigurations() {
            return 0;
        }

        State(State state) {
            this(state.c, state.f5071a, state.b);
        }

        State(Drawable.ConstantState constantState, int i, int i2) {
            this.c = constantState;
            this.f5071a = i;
            this.b = i2;
        }

        @NonNull
        public Drawable newDrawable() {
            return new FixedSizeDrawable(this, this.c.newDrawable());
        }

        @NonNull
        public Drawable newDrawable(Resources resources) {
            return new FixedSizeDrawable(this, this.c.newDrawable(resources));
        }
    }
}
