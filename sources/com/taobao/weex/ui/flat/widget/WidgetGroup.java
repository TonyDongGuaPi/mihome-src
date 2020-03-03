package com.taobao.weex.ui.flat.widget;

import android.graphics.Canvas;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import com.taobao.weex.ui.flat.FlatGUIContext;
import com.taobao.weex.ui.view.border.BorderDrawable;
import java.util.LinkedList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public class WidgetGroup extends BaseWidget {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private List<Widget> mChildren = new LinkedList();

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8322428578144046683L, "com/taobao/weex/ui/flat/widget/WidgetGroup", 12);
        $jacocoData = a2;
        return a2;
    }

    public /* synthetic */ void setBackgroundAndBorder(@NonNull BorderDrawable borderDrawable) {
        boolean[] $jacocoInit = $jacocoInit();
        super.setBackgroundAndBorder(borderDrawable);
        $jacocoInit[9] = true;
    }

    public /* synthetic */ void setContentBox(int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        super.setContentBox(i, i2, i3, i4);
        $jacocoInit[10] = true;
    }

    public /* synthetic */ void setLayout(int i, int i2, int i3, int i4, int i5, int i6, Point point) {
        boolean[] $jacocoInit = $jacocoInit();
        super.setLayout(i, i2, i3, i4, i5, i6, point);
        $jacocoInit[11] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WidgetGroup(@NonNull FlatGUIContext flatGUIContext) {
        super(flatGUIContext);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
    }

    public void replaceAll(@NonNull List<Widget> list) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mChildren = list;
        $jacocoInit[2] = true;
        invalidate();
        $jacocoInit[3] = true;
    }

    public List<Widget> getChildren() {
        boolean[] $jacocoInit = $jacocoInit();
        List<Widget> list = this.mChildren;
        $jacocoInit[4] = true;
        return list;
    }

    public void onDraw(@NonNull Canvas canvas) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[5] = true;
        for (Widget draw : this.mChildren) {
            $jacocoInit[6] = true;
            draw.draw(canvas);
            $jacocoInit[7] = true;
        }
        $jacocoInit[8] = true;
    }
}
