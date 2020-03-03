package com.taobao.weex.ui.flat.widget;

import android.graphics.Canvas;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.view.View;
import com.taobao.weex.common.Destroyable;
import com.taobao.weex.ui.flat.FlatGUIContext;
import com.taobao.weex.ui.view.border.BorderDrawable;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public class AndroidViewWidget extends BaseWidget implements Destroyable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    @Nullable
    private View mView;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5576716561839458389L, "com/taobao/weex/ui/flat/widget/AndroidViewWidget", 21);
        $jacocoData = a2;
        return a2;
    }

    public /* synthetic */ void setBackgroundAndBorder(@NonNull BorderDrawable borderDrawable) {
        boolean[] $jacocoInit = $jacocoInit();
        super.setBackgroundAndBorder(borderDrawable);
        $jacocoInit[19] = true;
    }

    public /* synthetic */ void setLayout(int i, int i2, int i3, int i4, int i5, int i6, Point point) {
        boolean[] $jacocoInit = $jacocoInit();
        super.setLayout(i, i2, i3, i4, i5, i6, point);
        $jacocoInit[20] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AndroidViewWidget(@NonNull FlatGUIContext flatGUIContext) {
        super(flatGUIContext);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public void setContentView(@Nullable View view) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mView = view;
        $jacocoInit[1] = true;
    }

    public void setContentBox(int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mView == null) {
            $jacocoInit[2] = true;
        } else {
            $jacocoInit[3] = true;
            this.mView.setPadding(i, i2, i3, i4);
            $jacocoInit[4] = true;
            invalidate();
            $jacocoInit[5] = true;
        }
        $jacocoInit[6] = true;
    }

    public void onDraw(@NonNull Canvas canvas) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mView == null) {
            $jacocoInit[7] = true;
        } else {
            $jacocoInit[8] = true;
            this.mView.draw(canvas);
            $jacocoInit[9] = true;
        }
        $jacocoInit[10] = true;
    }

    public void invalidate() {
        boolean[] $jacocoInit = $jacocoInit();
        super.invalidate();
        if (this.mView == null) {
            $jacocoInit[11] = true;
        } else {
            $jacocoInit[12] = true;
            this.mView.invalidate();
            $jacocoInit[13] = true;
        }
        $jacocoInit[14] = true;
    }

    @Nullable
    public View getView() {
        boolean[] $jacocoInit = $jacocoInit();
        View view = this.mView;
        $jacocoInit[15] = true;
        return view;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mView == null) {
            $jacocoInit[16] = true;
        } else {
            this.mView = null;
            $jacocoInit[17] = true;
        }
        $jacocoInit[18] = true;
    }
}
