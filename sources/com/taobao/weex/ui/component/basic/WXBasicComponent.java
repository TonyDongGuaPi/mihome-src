package com.taobao.weex.ui.component.basic;

import android.support.annotation.NonNull;
import android.view.View;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.dom.WXEvent;
import com.taobao.weex.dom.WXStyle;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.action.GraphicPosition;
import com.taobao.weex.ui.action.GraphicSize;
import com.taobao.weex.ui.component.WXComponent;
import java.util.Map;
import java.util.Set;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public abstract class WXBasicComponent<T extends View> {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private BasicComponentData mBasicComponentData;
    private String mComponentType;
    private Object mExtra;
    private boolean mIsLayoutRTL;
    private GraphicPosition mLayoutPosition;
    private GraphicSize mLayoutSize;
    private String mRef;
    private int mViewPortWidth = 750;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-5354090947572614988L, "com/taobao/weex/ui/component/basic/WXBasicComponent", 74);
        $jacocoData = a2;
        return a2;
    }

    public WXBasicComponent(BasicComponentData basicComponentData) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBasicComponentData = basicComponentData;
        this.mRef = basicComponentData.mRef;
        this.mComponentType = basicComponentData.mComponentType;
        $jacocoInit[0] = true;
    }

    public BasicComponentData getBasicComponentData() {
        boolean[] $jacocoInit = $jacocoInit();
        BasicComponentData basicComponentData = this.mBasicComponentData;
        $jacocoInit[1] = true;
        return basicComponentData;
    }

    /* access modifiers changed from: protected */
    public void bindComponent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mComponentType = wXComponent.getComponentType();
        $jacocoInit[2] = true;
        this.mRef = wXComponent.getRef();
        $jacocoInit[3] = true;
    }

    @NonNull
    public final WXStyle getStyles() {
        boolean[] $jacocoInit = $jacocoInit();
        WXStyle styles = this.mBasicComponentData.getStyles();
        $jacocoInit[4] = true;
        return styles;
    }

    @NonNull
    public final WXAttr getAttrs() {
        boolean[] $jacocoInit = $jacocoInit();
        WXAttr attrs = this.mBasicComponentData.getAttrs();
        $jacocoInit[5] = true;
        return attrs;
    }

    @NonNull
    public final WXEvent getEvents() {
        boolean[] $jacocoInit = $jacocoInit();
        WXEvent events = this.mBasicComponentData.getEvents();
        $jacocoInit[6] = true;
        return events;
    }

    @NonNull
    public final CSSShorthand getMargin() {
        boolean[] $jacocoInit = $jacocoInit();
        CSSShorthand margin = this.mBasicComponentData.getMargin();
        $jacocoInit[7] = true;
        return margin;
    }

    @NonNull
    public final CSSShorthand getPadding() {
        boolean[] $jacocoInit = $jacocoInit();
        CSSShorthand padding = this.mBasicComponentData.getPadding();
        $jacocoInit[8] = true;
        return padding;
    }

    @NonNull
    public CSSShorthand getBorder() {
        boolean[] $jacocoInit = $jacocoInit();
        CSSShorthand border = this.mBasicComponentData.getBorder();
        $jacocoInit[9] = true;
        return border;
    }

    public final void setMargins(@NonNull CSSShorthand cSSShorthand) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBasicComponentData.setMargins(cSSShorthand);
        $jacocoInit[10] = true;
    }

    public final void setPaddings(@NonNull CSSShorthand cSSShorthand) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBasicComponentData.setPaddings(cSSShorthand);
        $jacocoInit[11] = true;
    }

    public final void setBorders(@NonNull CSSShorthand cSSShorthand) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBasicComponentData.setBorders(cSSShorthand);
        $jacocoInit[12] = true;
    }

    public final void addAttr(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[13] = true;
        } else if (map.isEmpty()) {
            $jacocoInit[14] = true;
        } else {
            this.mBasicComponentData.addAttr(map);
            $jacocoInit[16] = true;
            return;
        }
        $jacocoInit[15] = true;
    }

    public final void addStyle(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[17] = true;
        } else if (map.isEmpty()) {
            $jacocoInit[18] = true;
        } else {
            this.mBasicComponentData.addStyle(map);
            $jacocoInit[20] = true;
            return;
        }
        $jacocoInit[19] = true;
    }

    public final void addStyle(Map<String, Object> map, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[21] = true;
        } else if (map.isEmpty()) {
            $jacocoInit[22] = true;
        } else {
            this.mBasicComponentData.addStyle(map, z);
            $jacocoInit[24] = true;
            return;
        }
        $jacocoInit[23] = true;
    }

    public final void updateStyle(Map<String, Object> map, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[25] = true;
        } else if (map.isEmpty()) {
            $jacocoInit[26] = true;
        } else {
            this.mBasicComponentData.getStyles().updateStyle(map, z);
            $jacocoInit[28] = true;
            return;
        }
        $jacocoInit[27] = true;
    }

    public final void addEvent(Set<String> set) {
        boolean[] $jacocoInit = $jacocoInit();
        if (set == null) {
            $jacocoInit[29] = true;
        } else if (set.isEmpty()) {
            $jacocoInit[30] = true;
        } else {
            this.mBasicComponentData.addEvent(set);
            $jacocoInit[32] = true;
            return;
        }
        $jacocoInit[31] = true;
    }

    public final void addShorthand(Map<String, String> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map.isEmpty()) {
            $jacocoInit[33] = true;
        } else if (this.mBasicComponentData == null) {
            $jacocoInit[34] = true;
        } else {
            $jacocoInit[35] = true;
            this.mBasicComponentData.addShorthand(map);
            $jacocoInit[36] = true;
        }
        $jacocoInit[37] = true;
    }

    public int getViewPortWidth() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mViewPortWidth;
        $jacocoInit[38] = true;
        return i;
    }

    public void setViewPortWidth(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mViewPortWidth = i;
        $jacocoInit[39] = true;
    }

    public Object getExtra() {
        boolean[] $jacocoInit = $jacocoInit();
        Object obj = this.mExtra;
        $jacocoInit[40] = true;
        return obj;
    }

    public void updateExtra(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mExtra = obj;
        $jacocoInit[41] = true;
    }

    public String getComponentType() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mComponentType;
        $jacocoInit[42] = true;
        return str;
    }

    public String getRef() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mRef;
        $jacocoInit[43] = true;
        return str;
    }

    public void setIsLayoutRTL(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mIsLayoutRTL = z;
        $jacocoInit[44] = true;
    }

    public boolean isLayoutRTL() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mIsLayoutRTL;
        $jacocoInit[45] = true;
        return z;
    }

    public GraphicPosition getLayoutPosition() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLayoutPosition != null) {
            $jacocoInit[46] = true;
        } else {
            $jacocoInit[47] = true;
            this.mLayoutPosition = new GraphicPosition(0.0f, 0.0f, 0.0f, 0.0f);
            $jacocoInit[48] = true;
        }
        GraphicPosition graphicPosition = this.mLayoutPosition;
        $jacocoInit[49] = true;
        return graphicPosition;
    }

    /* access modifiers changed from: protected */
    public void setLayoutPosition(GraphicPosition graphicPosition) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mLayoutPosition = graphicPosition;
        $jacocoInit[50] = true;
    }

    public GraphicSize getLayoutSize() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLayoutSize != null) {
            $jacocoInit[51] = true;
        } else {
            $jacocoInit[52] = true;
            this.mLayoutSize = new GraphicSize(0.0f, 0.0f);
            $jacocoInit[53] = true;
        }
        GraphicSize graphicSize = this.mLayoutSize;
        $jacocoInit[54] = true;
        return graphicSize;
    }

    /* access modifiers changed from: protected */
    public void setLayoutSize(GraphicSize graphicSize) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mLayoutSize = graphicSize;
        $jacocoInit[55] = true;
    }

    public float getCSSLayoutTop() {
        float f;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLayoutPosition == null) {
            f = 0.0f;
            $jacocoInit[56] = true;
        } else {
            f = this.mLayoutPosition.getTop();
            $jacocoInit[57] = true;
        }
        $jacocoInit[58] = true;
        return f;
    }

    public float getCSSLayoutBottom() {
        float f;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLayoutPosition == null) {
            f = 0.0f;
            $jacocoInit[59] = true;
        } else {
            f = this.mLayoutPosition.getBottom();
            $jacocoInit[60] = true;
        }
        $jacocoInit[61] = true;
        return f;
    }

    public float getCSSLayoutLeft() {
        float f;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLayoutPosition == null) {
            f = 0.0f;
            $jacocoInit[62] = true;
        } else {
            f = this.mLayoutPosition.getLeft();
            $jacocoInit[63] = true;
        }
        $jacocoInit[64] = true;
        return f;
    }

    public float getCSSLayoutRight() {
        float f;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLayoutPosition == null) {
            f = 0.0f;
            $jacocoInit[65] = true;
        } else {
            f = this.mLayoutPosition.getRight();
            $jacocoInit[66] = true;
        }
        $jacocoInit[67] = true;
        return f;
    }

    public float getLayoutWidth() {
        float f;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLayoutSize == null) {
            f = 0.0f;
            $jacocoInit[68] = true;
        } else {
            f = this.mLayoutSize.getWidth();
            $jacocoInit[69] = true;
        }
        $jacocoInit[70] = true;
        return f;
    }

    public float getLayoutHeight() {
        float f;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLayoutSize == null) {
            f = 0.0f;
            $jacocoInit[71] = true;
        } else {
            f = this.mLayoutSize.getHeight();
            $jacocoInit[72] = true;
        }
        $jacocoInit[73] = true;
        return f;
    }
}
