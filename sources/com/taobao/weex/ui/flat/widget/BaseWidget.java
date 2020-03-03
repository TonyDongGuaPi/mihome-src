package com.taobao.weex.ui.flat.widget;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.view.View;
import com.taobao.weex.ui.flat.FlatGUIContext;
import com.taobao.weex.ui.view.border.BorderDrawable;
import com.taobao.weex.utils.WXViewUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@RestrictTo({RestrictTo.Scope.LIBRARY})
abstract class BaseWidget implements Widget {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private BorderDrawable backgroundBorder;
    private Rect borderBox = new Rect();
    private int bottomOffset;
    @NonNull
    private final FlatGUIContext context;
    private int leftOffset;
    private Point offsetOfContainer;
    private int rightOffset;
    private int topOffset;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(4430525476074684295L, "com/taobao/weex/ui/flat/widget/BaseWidget", 40);
        $jacocoData = a2;
        return a2;
    }

    BaseWidget(@NonNull FlatGUIContext flatGUIContext) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
        this.offsetOfContainer = new Point();
        this.context = flatGUIContext;
        $jacocoInit[2] = true;
    }

    public void setLayout(int i, int i2, int i3, int i4, int i5, int i6, Point point) {
        boolean[] $jacocoInit = $jacocoInit();
        this.offsetOfContainer = point;
        $jacocoInit[3] = true;
        this.borderBox.set(i3, i5, i + i3, i2 + i5);
        if (this.backgroundBorder == null) {
            $jacocoInit[4] = true;
        } else {
            $jacocoInit[5] = true;
            setBackgroundAndBorder(this.backgroundBorder);
            $jacocoInit[6] = true;
        }
        invalidate();
        $jacocoInit[7] = true;
    }

    public void setContentBox(int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        this.leftOffset = i;
        this.topOffset = i2;
        this.rightOffset = i3;
        this.bottomOffset = i4;
        $jacocoInit[8] = true;
        invalidate();
        $jacocoInit[9] = true;
    }

    public void setBackgroundAndBorder(@NonNull BorderDrawable borderDrawable) {
        boolean[] $jacocoInit = $jacocoInit();
        this.backgroundBorder = borderDrawable;
        $jacocoInit[10] = true;
        Rect rect = new Rect(this.borderBox);
        $jacocoInit[11] = true;
        rect.offset(-this.borderBox.left, -this.borderBox.top);
        $jacocoInit[12] = true;
        borderDrawable.setBounds(rect);
        $jacocoInit[13] = true;
        setCallback(borderDrawable);
        $jacocoInit[14] = true;
        invalidate();
        $jacocoInit[15] = true;
    }

    @NonNull
    public final Point getLocInFlatContainer() {
        boolean[] $jacocoInit = $jacocoInit();
        Point point = this.offsetOfContainer;
        $jacocoInit[16] = true;
        return point;
    }

    @Nullable
    public final BorderDrawable getBackgroundAndBorder() {
        boolean[] $jacocoInit = $jacocoInit();
        BorderDrawable borderDrawable = this.backgroundBorder;
        $jacocoInit[17] = true;
        return borderDrawable;
    }

    @NonNull
    public final Rect getBorderBox() {
        boolean[] $jacocoInit = $jacocoInit();
        Rect rect = this.borderBox;
        $jacocoInit[18] = true;
        return rect;
    }

    public final void draw(@NonNull Canvas canvas) {
        boolean[] $jacocoInit = $jacocoInit();
        canvas.save();
        $jacocoInit[19] = true;
        WXViewUtils.clipCanvasWithinBorderBox((Widget) this, canvas);
        $jacocoInit[20] = true;
        canvas.translate((float) this.borderBox.left, (float) this.borderBox.top);
        if (this.backgroundBorder == null) {
            $jacocoInit[21] = true;
        } else {
            $jacocoInit[22] = true;
            this.backgroundBorder.draw(canvas);
            $jacocoInit[23] = true;
        }
        canvas.clipRect(this.leftOffset, this.topOffset, this.borderBox.width() - this.rightOffset, this.borderBox.height() - this.bottomOffset);
        $jacocoInit[24] = true;
        canvas.translate((float) this.leftOffset, (float) this.topOffset);
        $jacocoInit[25] = true;
        onDraw(canvas);
        $jacocoInit[26] = true;
        canvas.restore();
        $jacocoInit[27] = true;
    }

    /* access modifiers changed from: protected */
    public void invalidate() {
        boolean[] $jacocoInit = $jacocoInit();
        Rect rect = new Rect(this.borderBox);
        $jacocoInit[28] = true;
        rect.offset(this.offsetOfContainer.x, this.offsetOfContainer.y);
        $jacocoInit[29] = true;
        if (this.context == null) {
            $jacocoInit[30] = true;
        } else {
            View widgetContainerView = this.context.getWidgetContainerView(this);
            if (widgetContainerView == null) {
                $jacocoInit[31] = true;
            } else {
                $jacocoInit[32] = true;
                widgetContainerView.invalidate(rect);
                $jacocoInit[33] = true;
            }
        }
        $jacocoInit[34] = true;
    }

    /* access modifiers changed from: protected */
    public void setCallback(@NonNull Drawable drawable) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.context == null) {
            $jacocoInit[35] = true;
        } else {
            View widgetContainerView = this.context.getWidgetContainerView(this);
            if (widgetContainerView == null) {
                $jacocoInit[36] = true;
            } else {
                $jacocoInit[37] = true;
                drawable.setCallback(widgetContainerView);
                $jacocoInit[38] = true;
            }
        }
        $jacocoInit[39] = true;
    }
}
