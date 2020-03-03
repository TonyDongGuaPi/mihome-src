package com.tmall.wireless.vaf.virtualview.view.image;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.tmall.wireless.vaf.virtualview.container.ClickHelper;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import com.tmall.wireless.vaf.virtualview.core.IView;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;

public class NativeImageImp extends ImageView implements IContainer, IView {
    private static final String TAG = "NativeImageImp_TMTEST";
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

    public NativeImageImp(Context context) {
        super(context);
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

    public void measureComponent(int i, int i2) {
        measure(i, i2);
    }

    public void comLayout(int i, int i2, int i3, int i4) {
        layout(i, i2, i3, i4);
    }

    public void setVirtualView(ViewBase viewBase) {
        this.mVirtualView = viewBase;
        viewBase.b((View) this);
        new ClickHelper(this);
    }

    public ViewBase getVirtualView() {
        return this.mVirtualView;
    }
}
