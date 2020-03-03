package com.tmall.wireless.vaf.virtualview.view.grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import com.tmall.wireless.vaf.virtualview.container.ClickHelper;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import com.tmall.wireless.vaf.virtualview.core.IView;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;

public class GridImp extends GridView implements IContainer, IView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9403a = "GridImp_TMTEST";
    protected int mBGColor = 0;
    protected Paint mBackgroundPaint;
    protected ViewBase mVirtualView;

    public void attachViews() {
    }

    public void destroy() {
    }

    public View getHolderView() {
        return this;
    }

    public int getType() {
        return -1;
    }

    public GridImp(Context context) {
        super(context);
    }

    public void setBackgroundColor(int i) {
        this.mBGColor = i;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mBGColor != 0) {
            int o = this.mVirtualView.o();
            if (this.mBackgroundPaint == null) {
                this.mBackgroundPaint = new Paint();
                this.mBackgroundPaint.setColor(this.mBGColor);
            }
            float f = (float) o;
            canvas.drawRect(f, f, (float) (this.mVirtualView.getComMeasuredWidth() - o), (float) (this.mVirtualView.getComMeasuredHeight() - o), this.mBackgroundPaint);
        }
    }

    public void measureComponent(int i, int i2) {
        measure(i, i2);
    }

    public void comLayout(int i, int i2, int i3, int i4) {
        layout(i, i2, i3, i4);
    }

    public void onComMeasure(int i, int i2) {
        onMeasure(i, i2);
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        onLayout(z, i, i2, i3, i4);
    }

    public int getComMeasuredWidth() {
        return getMeasuredWidth();
    }

    public int getComMeasuredHeight() {
        return getMeasuredHeight();
    }

    public void setVirtualView(ViewBase viewBase) {
        if (viewBase != null) {
            this.mVirtualView = viewBase;
            this.mVirtualView.b((View) this);
            if (this.mVirtualView.Y()) {
                setWillNotDraw(false);
            }
            new ClickHelper(this);
        }
    }

    public ViewBase getVirtualView() {
        return this.mVirtualView;
    }
}
