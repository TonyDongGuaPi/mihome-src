package com.taobao.weex.ui.action;

import android.support.v4.util.ArrayMap;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentFactory;
import com.taobao.weex.ui.component.WXVContainer;
import java.util.Map;
import java.util.Set;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public abstract class GraphicActionAbstractAddElement extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;
    protected Map<String, String> mAttributes;
    protected float[] mBorders;
    protected String mComponentType;
    protected Set<String> mEvents;
    protected int mIndex = -1;
    protected float[] mMargins;
    protected float[] mPaddings;
    protected String mParentRef;
    protected Map<String, String> mStyle;
    private long startTime;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(2385043595656756736L, "com/taobao/weex/ui/action/GraphicActionAbstractAddElement", 28);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GraphicActionAbstractAddElement(WXSDKInstance wXSDKInstance, String str) {
        super(wXSDKInstance, str);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.startTime = System.currentTimeMillis();
        $jacocoInit[1] = true;
    }

    /* access modifiers changed from: protected */
    public WXComponent createComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        boolean[] $jacocoInit = $jacocoInit();
        long currentTimeMillis = System.currentTimeMillis();
        if (basicComponentData == null) {
            $jacocoInit[2] = true;
        } else {
            $jacocoInit[3] = true;
            basicComponentData.addStyle(this.mStyle);
            $jacocoInit[4] = true;
            basicComponentData.addAttr(this.mAttributes);
            $jacocoInit[5] = true;
            basicComponentData.addEvent(this.mEvents);
            $jacocoInit[6] = true;
            basicComponentData.addShorthand(this.mMargins, CSSShorthand.TYPE.MARGIN);
            $jacocoInit[7] = true;
            basicComponentData.addShorthand(this.mPaddings, CSSShorthand.TYPE.PADDING);
            $jacocoInit[8] = true;
            basicComponentData.addShorthand(this.mBorders, CSSShorthand.TYPE.BORDER);
            $jacocoInit[9] = true;
        }
        WXComponent newInstance = WXComponentFactory.newInstance(wXSDKInstance, wXVContainer, basicComponentData);
        $jacocoInit[10] = true;
        WXSDKManager.getInstance().getWXRenderManager().registerComponent(getPageId(), getRef(), newInstance);
        $jacocoInit[11] = true;
        if (this.mStyle == null) {
            $jacocoInit[12] = true;
        } else if (!this.mStyle.containsKey("transform")) {
            $jacocoInit[13] = true;
        } else if (newInstance.getTransition() != null) {
            $jacocoInit[14] = true;
        } else {
            $jacocoInit[15] = true;
            ArrayMap arrayMap = new ArrayMap(2);
            $jacocoInit[16] = true;
            arrayMap.put("transform", this.mStyle.get("transform"));
            Map<String, String> map = this.mStyle;
            $jacocoInit[17] = true;
            arrayMap.put(Constants.Name.TRANSFORM_ORIGIN, map.get(Constants.Name.TRANSFORM_ORIGIN));
            $jacocoInit[18] = true;
            newInstance.addAnimationForElement(arrayMap);
            $jacocoInit[19] = true;
        }
        wXSDKInstance.onComponentCreate(newInstance, System.currentTimeMillis() - currentTimeMillis);
        $jacocoInit[20] = true;
        return newInstance;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        getWXSDKIntance().callActionAddElementTime(System.currentTimeMillis() - this.startTime);
        $jacocoInit[21] = true;
    }

    public String getComponentType() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mComponentType;
        $jacocoInit[22] = true;
        return str;
    }

    public String getParentRef() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mParentRef;
        $jacocoInit[23] = true;
        return str;
    }

    public int getIndex() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mIndex;
        $jacocoInit[24] = true;
        return i;
    }

    public Map<String, String> getStyle() {
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, String> map = this.mStyle;
        $jacocoInit[25] = true;
        return map;
    }

    public Map<String, String> getAttributes() {
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, String> map = this.mAttributes;
        $jacocoInit[26] = true;
        return map;
    }

    public Set<String> getEvents() {
        boolean[] $jacocoInit = $jacocoInit();
        Set<String> set = this.mEvents;
        $jacocoInit[27] = true;
        return set;
    }
}
