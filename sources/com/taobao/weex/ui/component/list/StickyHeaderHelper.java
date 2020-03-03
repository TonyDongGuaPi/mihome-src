package com.taobao.weex.ui.component.list;

import android.view.View;
import android.view.ViewGroup;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.utils.WXLogUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class StickyHeaderHelper {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private String mCurrentStickyRef;
    private Map<String, WXCell> mHeaderComps;
    private Map<String, View> mHeaderViews = new HashMap();
    private final ViewGroup mParent;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5053029495492765304L, "com/taobao/weex/ui/component/list/StickyHeaderHelper", 108);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ ViewGroup access$000(StickyHeaderHelper stickyHeaderHelper) {
        boolean[] $jacocoInit = $jacocoInit();
        ViewGroup viewGroup = stickyHeaderHelper.mParent;
        $jacocoInit[106] = true;
        return viewGroup;
    }

    static /* synthetic */ void access$100(StickyHeaderHelper stickyHeaderHelper) {
        boolean[] $jacocoInit = $jacocoInit();
        stickyHeaderHelper.changeFrontStickyVisible();
        $jacocoInit[107] = true;
    }

    public StickyHeaderHelper(ViewGroup viewGroup) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
        this.mHeaderComps = new HashMap();
        this.mCurrentStickyRef = null;
        this.mParent = viewGroup;
        $jacocoInit[2] = true;
    }

    public void notifyStickyShow(WXCell wXCell) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXCell == null) {
            $jacocoInit[3] = true;
            return;
        }
        this.mHeaderComps.put(wXCell.getRef(), wXCell);
        if (this.mCurrentStickyRef != null) {
            $jacocoInit[4] = true;
            WXCell wXCell2 = this.mHeaderComps.get(this.mCurrentStickyRef);
            $jacocoInit[5] = true;
            if (wXCell2 == null) {
                $jacocoInit[6] = true;
            } else if (wXCell.getScrollPositon() <= wXCell2.getScrollPositon()) {
                $jacocoInit[7] = true;
                $jacocoInit[10] = true;
            } else {
                $jacocoInit[8] = true;
            }
            this.mCurrentStickyRef = wXCell.getRef();
            $jacocoInit[9] = true;
            $jacocoInit[10] = true;
        } else {
            this.mCurrentStickyRef = wXCell.getRef();
            $jacocoInit[11] = true;
        }
        if (this.mCurrentStickyRef == null) {
            $jacocoInit[12] = true;
            WXLogUtils.e("Current Sticky ref is null.");
            $jacocoInit[13] = true;
            return;
        }
        WXCell wXCell3 = this.mHeaderComps.get(this.mCurrentStickyRef);
        $jacocoInit[14] = true;
        ViewGroup realView = wXCell3.getRealView();
        if (realView == null) {
            $jacocoInit[15] = true;
            WXLogUtils.e("Sticky header's real view is null.");
            $jacocoInit[16] = true;
            return;
        }
        View view = this.mHeaderViews.get(wXCell3.getRef());
        if (view != null) {
            $jacocoInit[17] = true;
            view.bringToFront();
            $jacocoInit[18] = true;
        } else {
            this.mHeaderViews.put(wXCell3.getRef(), realView);
            $jacocoInit[19] = true;
            float translationX = realView.getTranslationX();
            $jacocoInit[20] = true;
            float translationY = realView.getTranslationY();
            $jacocoInit[21] = true;
            wXCell3.removeSticky();
            $jacocoInit[22] = true;
            ViewGroup viewGroup = (ViewGroup) realView.getParent();
            if (viewGroup == null) {
                $jacocoInit[23] = true;
            } else {
                $jacocoInit[24] = true;
                viewGroup.removeView(realView);
                $jacocoInit[25] = true;
            }
            realView.setTag(wXCell3.getRef());
            $jacocoInit[26] = true;
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
            $jacocoInit[27] = true;
            this.mParent.addView(realView, marginLayoutParams);
            $jacocoInit[28] = true;
            realView.setTag(this);
            $jacocoInit[29] = true;
            if (wXCell3.getStickyOffset() <= 0) {
                $jacocoInit[30] = true;
            } else {
                $jacocoInit[31] = true;
                ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) realView.getLayoutParams();
                $jacocoInit[32] = true;
                if (wXCell3.getStickyOffset() == marginLayoutParams2.topMargin) {
                    $jacocoInit[33] = true;
                } else {
                    $jacocoInit[34] = true;
                    marginLayoutParams2.topMargin = wXCell3.getStickyOffset();
                    $jacocoInit[35] = true;
                }
            }
            realView.setTranslationX(translationX);
            $jacocoInit[36] = true;
            realView.setTranslationY(translationY);
            $jacocoInit[37] = true;
        }
        changeFrontStickyVisible();
        $jacocoInit[38] = true;
        if (!wXCell3.getEvents().contains("sticky")) {
            $jacocoInit[39] = true;
        } else {
            $jacocoInit[40] = true;
            wXCell3.fireEvent("sticky");
            $jacocoInit[41] = true;
        }
        $jacocoInit[42] = true;
    }

    public void notifyStickyRemove(WXCell wXCell) {
        final WXCell wXCell2;
        boolean[] $jacocoInit = $jacocoInit();
        if (wXCell == null) {
            $jacocoInit[43] = true;
            return;
        }
        if (this.mHeaderComps.containsValue(wXCell)) {
            wXCell2 = this.mHeaderComps.remove(wXCell.getRef());
            $jacocoInit[44] = true;
        } else {
            $jacocoInit[45] = true;
            wXCell2 = wXCell;
        }
        $jacocoInit[46] = true;
        final View remove = this.mHeaderViews.remove(wXCell.getRef());
        if (wXCell2 == null) {
            $jacocoInit[47] = true;
        } else if (remove == null) {
            $jacocoInit[48] = true;
        } else {
            if (this.mCurrentStickyRef == null) {
                $jacocoInit[52] = true;
            } else if (!this.mCurrentStickyRef.equals(wXCell.getRef())) {
                $jacocoInit[53] = true;
            } else {
                this.mCurrentStickyRef = null;
                $jacocoInit[54] = true;
            }
            this.mParent.post(WXThread.secure((Runnable) new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ StickyHeaderHelper this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-4937126048181232544L, "com/taobao/weex/ui/component/list/StickyHeaderHelper$1", 7);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r2;
                    $jacocoInit[0] = true;
                }

                public void run() {
                    boolean[] $jacocoInit = $jacocoInit();
                    StickyHeaderHelper.access$000(this.this$0).removeView(remove);
                    $jacocoInit[1] = true;
                    if (remove.getVisibility() == 0) {
                        $jacocoInit[2] = true;
                    } else {
                        $jacocoInit[3] = true;
                        remove.setVisibility(0);
                        $jacocoInit[4] = true;
                    }
                    wXCell2.recoverySticky();
                    $jacocoInit[5] = true;
                    StickyHeaderHelper.access$100(this.this$0);
                    $jacocoInit[6] = true;
                }
            }));
            $jacocoInit[55] = true;
            if (!wXCell2.getEvents().contains(Constants.Event.UNSTICKY)) {
                $jacocoInit[56] = true;
            } else {
                $jacocoInit[57] = true;
                wXCell2.fireEvent(Constants.Event.UNSTICKY);
                $jacocoInit[58] = true;
            }
            $jacocoInit[59] = true;
            return;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[49] = true;
        } else {
            $jacocoInit[50] = true;
        }
        $jacocoInit[51] = true;
    }

    public void updateStickyView(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[60] = true;
        ArrayList<WXCell> arrayList = new ArrayList<>();
        $jacocoInit[61] = true;
        for (Map.Entry<String, WXCell> value : this.mHeaderComps.entrySet()) {
            $jacocoInit[62] = true;
            $jacocoInit[63] = true;
            WXCell wXCell = (WXCell) value.getValue();
            $jacocoInit[64] = true;
            int scrollPositon = wXCell.getScrollPositon();
            if (scrollPositon > i) {
                $jacocoInit[65] = true;
                arrayList.add(wXCell);
                $jacocoInit[66] = true;
            } else if (scrollPositon != i) {
                $jacocoInit[67] = true;
            } else {
                $jacocoInit[68] = true;
                this.mCurrentStickyRef = wXCell.getRef();
                $jacocoInit[69] = true;
                View view = this.mHeaderViews.get(wXCell.getRef());
                if (view == null) {
                    $jacocoInit[70] = true;
                } else {
                    $jacocoInit[71] = true;
                    view.bringToFront();
                    $jacocoInit[72] = true;
                    changeFrontStickyVisible();
                    $jacocoInit[73] = true;
                }
            }
            $jacocoInit[74] = true;
        }
        $jacocoInit[75] = true;
        for (WXCell notifyStickyRemove : arrayList) {
            $jacocoInit[76] = true;
            notifyStickyRemove(notifyStickyRemove);
            $jacocoInit[77] = true;
        }
        $jacocoInit[78] = true;
    }

    public void clearStickyHeaders() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mHeaderViews.size() <= 0) {
            $jacocoInit[79] = true;
            return;
        }
        Iterator<Map.Entry<String, WXCell>> it = this.mHeaderComps.entrySet().iterator();
        $jacocoInit[80] = true;
        while (it.hasNext()) {
            $jacocoInit[81] = true;
            $jacocoInit[82] = true;
            $jacocoInit[83] = true;
            it.remove();
            $jacocoInit[84] = true;
            notifyStickyRemove((WXCell) it.next().getValue());
            $jacocoInit[85] = true;
        }
        $jacocoInit[86] = true;
    }

    private void changeFrontStickyVisible() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mHeaderViews.size() <= 0) {
            $jacocoInit[87] = true;
            return;
        }
        $jacocoInit[88] = true;
        int childCount = this.mParent.getChildCount() - 1;
        $jacocoInit[89] = true;
        boolean z = false;
        while (childCount >= 0) {
            $jacocoInit[90] = true;
            View childAt = this.mParent.getChildAt(childCount);
            $jacocoInit[91] = true;
            if (!z) {
                $jacocoInit[92] = true;
            } else if (!(childAt.getTag() instanceof StickyHeaderHelper)) {
                $jacocoInit[93] = true;
            } else {
                $jacocoInit[94] = true;
                if (childAt.getVisibility() == 8) {
                    $jacocoInit[95] = true;
                } else {
                    $jacocoInit[96] = true;
                    childAt.setVisibility(8);
                    $jacocoInit[97] = true;
                }
                childCount--;
                $jacocoInit[104] = true;
            }
            if (!(childAt.getTag() instanceof StickyHeaderHelper)) {
                $jacocoInit[98] = true;
            } else {
                $jacocoInit[99] = true;
                if (childAt == null) {
                    $jacocoInit[100] = true;
                } else if (childAt.getVisibility() == 0) {
                    $jacocoInit[101] = true;
                } else {
                    $jacocoInit[102] = true;
                    childAt.setVisibility(0);
                    $jacocoInit[103] = true;
                }
                z = true;
            }
            childCount--;
            $jacocoInit[104] = true;
        }
        $jacocoInit[105] = true;
    }
}
