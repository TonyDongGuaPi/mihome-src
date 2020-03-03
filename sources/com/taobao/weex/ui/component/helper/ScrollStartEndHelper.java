package com.taobao.weex.ui.component.helper;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXScroller;
import com.taobao.weex.ui.component.list.BasicListComponent;
import com.taobao.weex.ui.component.list.ListComponentView;
import com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList;
import com.taobao.weex.ui.view.refresh.wrapper.BounceRecyclerView;
import com.taobao.weex.utils.WXUtils;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ScrollStartEndHelper implements Runnable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private WXComponent component;
    private Handler handler = new Handler(Looper.getMainLooper());
    private boolean hasStart;
    private long minInterval;
    private int x;
    private int y;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4031357926131946389L, "com/taobao/weex/ui/component/helper/ScrollStartEndHelper", 38);
        $jacocoData = a2;
        return a2;
    }

    public ScrollStartEndHelper(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        this.component = wXComponent;
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
        this.minInterval = (long) WXUtils.getNumberInt(wXComponent.getAttrs().get("minscrolldelayinterval"), 32);
        $jacocoInit[2] = true;
    }

    public void onScrolled(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.component.getEvents().contains(Constants.Event.SCROLL_START)) {
            $jacocoInit[3] = true;
        } else {
            WXComponent wXComponent = this.component;
            $jacocoInit[4] = true;
            if (!wXComponent.getEvents().contains(Constants.Event.SCROLL_END)) {
                $jacocoInit[5] = true;
                $jacocoInit[15] = true;
            }
            $jacocoInit[6] = true;
        }
        this.x = i;
        this.y = i2;
        if (this.hasStart) {
            $jacocoInit[7] = true;
        } else {
            $jacocoInit[8] = true;
            if (!this.component.getEvents().contains(Constants.Event.SCROLL_START)) {
                $jacocoInit[9] = true;
            } else {
                $jacocoInit[10] = true;
                this.component.fireEvent(Constants.Event.SCROLL_START, getScrollEvent(i, i2));
                $jacocoInit[11] = true;
            }
            this.hasStart = true;
            $jacocoInit[12] = true;
        }
        this.handler.removeCallbacks(this);
        $jacocoInit[13] = true;
        this.handler.postDelayed(this, this.minInterval);
        $jacocoInit[14] = true;
        $jacocoInit[15] = true;
    }

    public void run() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.component.isDestoryed()) {
            $jacocoInit[16] = true;
            return;
        }
        if (!this.component.getEvents().contains(Constants.Event.SCROLL_END)) {
            $jacocoInit[17] = true;
        } else {
            $jacocoInit[18] = true;
            this.component.fireEvent(Constants.Event.SCROLL_END, getScrollEvent(this.x, this.y));
            $jacocoInit[19] = true;
        }
        this.hasStart = false;
        $jacocoInit[20] = true;
    }

    private Map<String, Object> getScrollEvent(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.component instanceof BasicListComponent) {
            BasicListComponent basicListComponent = (BasicListComponent) this.component;
            $jacocoInit[21] = true;
            if (!(basicListComponent.getHostView() instanceof ListComponentView)) {
                $jacocoInit[22] = true;
            } else {
                $jacocoInit[23] = true;
                ListComponentView listComponentView = (ListComponentView) basicListComponent.getHostView();
                if (listComponentView == null) {
                    $jacocoInit[24] = true;
                } else {
                    $jacocoInit[25] = true;
                    Map<String, Object> scrollEvent = basicListComponent.getScrollEvent(listComponentView.getInnerView(), i, i2);
                    $jacocoInit[26] = true;
                    return scrollEvent;
                }
            }
            $jacocoInit[27] = true;
        } else if (this.component instanceof WXRecyclerTemplateList) {
            WXRecyclerTemplateList wXRecyclerTemplateList = (WXRecyclerTemplateList) this.component;
            $jacocoInit[28] = true;
            Map<String, Object> scrollEvent2 = wXRecyclerTemplateList.getScrollEvent((RecyclerView) ((BounceRecyclerView) wXRecyclerTemplateList.getHostView()).getInnerView(), i, i2);
            $jacocoInit[29] = true;
            return scrollEvent2;
        } else if (!(this.component instanceof WXScroller)) {
            $jacocoInit[30] = true;
        } else {
            $jacocoInit[31] = true;
            Map<String, Object> scrollEvent3 = ((WXScroller) this.component).getScrollEvent(i, i2);
            $jacocoInit[32] = true;
            return scrollEvent3;
        }
        $jacocoInit[33] = true;
        return null;
    }

    public static boolean isScrollEvent(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if ("scroll".equals(str)) {
            $jacocoInit[34] = true;
            return true;
        } else if (Constants.Event.SCROLL_START.equals(str)) {
            $jacocoInit[35] = true;
            return true;
        } else if (Constants.Event.SCROLL_END.equals(str)) {
            $jacocoInit[36] = true;
            return true;
        } else {
            $jacocoInit[37] = true;
            return false;
        }
    }
}
