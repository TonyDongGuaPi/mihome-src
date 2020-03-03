package com.taobao.weex.ui.view.refresh.wrapper;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.view.WXLoadingLayout;
import com.taobao.weex.ui.view.WXRefreshLayout;
import com.taobao.weex.ui.view.refresh.core.WXRefreshView;
import com.taobao.weex.ui.view.refresh.core.WXSwipeLayout;
import com.taobao.weex.utils.WXResourceUtils;
import com.taobao.weex.utils.WXUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public abstract class BaseBounceView<T extends View> extends FrameLayout {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private T mInnerView;
    private int mOrientation;
    protected WXSwipeLayout swipeLayout;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(2033448662945374327L, "com/taobao/weex/ui/view/refresh/wrapper/BaseBounceView", 100);
        $jacocoData = a2;
        return a2;
    }

    public abstract void onLoadmoreComplete();

    public abstract void onRefreshingComplete();

    public abstract T setInnerView(Context context);

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BaseBounceView(Context context, int i) {
        this(context, (AttributeSet) null, i);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BaseBounceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        boolean[] $jacocoInit = $jacocoInit();
        this.mOrientation = 1;
        this.mOrientation = i;
        $jacocoInit[1] = true;
    }

    public int getOrientation() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mOrientation;
        $jacocoInit[2] = true;
        return i;
    }

    public void init(Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        createBounceView(context);
        $jacocoInit[3] = true;
    }

    /* access modifiers changed from: package-private */
    public boolean isVertical() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mOrientation == 1) {
            $jacocoInit[4] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[5] = true;
        }
        $jacocoInit[6] = true;
        return z;
    }

    public void setOnRefreshListener(WXSwipeLayout.WXOnRefreshListener wXOnRefreshListener) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.swipeLayout == null) {
            $jacocoInit[7] = true;
        } else {
            $jacocoInit[8] = true;
            this.swipeLayout.setOnRefreshListener(wXOnRefreshListener);
            $jacocoInit[9] = true;
        }
        $jacocoInit[10] = true;
    }

    public void setOnLoadingListener(WXSwipeLayout.WXOnLoadingListener wXOnLoadingListener) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.swipeLayout == null) {
            $jacocoInit[11] = true;
        } else {
            $jacocoInit[12] = true;
            this.swipeLayout.setOnLoadingListener(wXOnLoadingListener);
            $jacocoInit[13] = true;
        }
        $jacocoInit[14] = true;
    }

    public void finishPullRefresh() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.swipeLayout == null) {
            $jacocoInit[15] = true;
        } else {
            $jacocoInit[16] = true;
            this.swipeLayout.finishPullRefresh();
            $jacocoInit[17] = true;
        }
        $jacocoInit[18] = true;
    }

    public void finishPullLoad() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.swipeLayout == null) {
            $jacocoInit[19] = true;
        } else {
            $jacocoInit[20] = true;
            this.swipeLayout.finishPullLoad();
            $jacocoInit[21] = true;
        }
        $jacocoInit[22] = true;
    }

    private WXSwipeLayout createBounceView(Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        this.swipeLayout = new WXSwipeLayout(context);
        $jacocoInit[23] = true;
        this.swipeLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        $jacocoInit[24] = true;
        this.mInnerView = setInnerView(context);
        if (this.mInnerView == null) {
            $jacocoInit[25] = true;
            return null;
        }
        this.swipeLayout.addTargetView(this.mInnerView);
        $jacocoInit[26] = true;
        addView(this.swipeLayout, -1, -1);
        WXSwipeLayout wXSwipeLayout = this.swipeLayout;
        $jacocoInit[27] = true;
        return wXSwipeLayout;
    }

    public T getInnerView() {
        boolean[] $jacocoInit = $jacocoInit();
        T t = this.mInnerView;
        $jacocoInit[28] = true;
        return t;
    }

    public void setHeaderView(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        setRefreshEnable(true);
        if (this.swipeLayout == null) {
            $jacocoInit[29] = true;
        } else {
            $jacocoInit[30] = true;
            WXRefreshView headerView = this.swipeLayout.getHeaderView();
            if (headerView == null) {
                $jacocoInit[31] = true;
            } else if (wXComponent == null) {
                $jacocoInit[32] = true;
            } else {
                $jacocoInit[33] = true;
                $jacocoInit[34] = true;
                this.swipeLayout.setRefreshHeight((int) wXComponent.getLayoutHeight());
                $jacocoInit[35] = true;
                $jacocoInit[36] = true;
                String string = WXUtils.getString((String) wXComponent.getStyles().get("backgroundColor"), (String) null);
                if (string == null) {
                    $jacocoInit[37] = true;
                } else {
                    $jacocoInit[38] = true;
                    if (TextUtils.isEmpty(string)) {
                        $jacocoInit[39] = true;
                    } else {
                        $jacocoInit[40] = true;
                        int color = WXResourceUtils.getColor(string);
                        if (color == 0) {
                            $jacocoInit[41] = true;
                        } else {
                            $jacocoInit[42] = true;
                            this.swipeLayout.setRefreshBgColor(color);
                            $jacocoInit[43] = true;
                        }
                    }
                }
                headerView.setRefreshView(wXComponent.getHostView());
                $jacocoInit[44] = true;
            }
        }
        $jacocoInit[45] = true;
    }

    public void setFooterView(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        setLoadmoreEnable(true);
        if (this.swipeLayout == null) {
            $jacocoInit[46] = true;
        } else {
            $jacocoInit[47] = true;
            WXRefreshView footerView = this.swipeLayout.getFooterView();
            if (footerView == null) {
                $jacocoInit[48] = true;
            } else if (wXComponent == null) {
                $jacocoInit[49] = true;
            } else {
                $jacocoInit[50] = true;
                $jacocoInit[51] = true;
                this.swipeLayout.setLoadingHeight((int) wXComponent.getLayoutHeight());
                $jacocoInit[52] = true;
                $jacocoInit[53] = true;
                String string = WXUtils.getString((String) wXComponent.getStyles().get("backgroundColor"), (String) null);
                if (string == null) {
                    $jacocoInit[54] = true;
                } else {
                    $jacocoInit[55] = true;
                    if (TextUtils.isEmpty(string)) {
                        $jacocoInit[56] = true;
                    } else {
                        $jacocoInit[57] = true;
                        int color = WXResourceUtils.getColor(string);
                        if (color == 0) {
                            $jacocoInit[58] = true;
                        } else {
                            $jacocoInit[59] = true;
                            this.swipeLayout.setLoadingBgColor(color);
                            $jacocoInit[60] = true;
                        }
                    }
                }
                footerView.setRefreshView(wXComponent.getHostView());
                $jacocoInit[61] = true;
            }
        }
        $jacocoInit[62] = true;
    }

    public void removeFooterView(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        setLoadmoreEnable(false);
        if (this.swipeLayout == null) {
            $jacocoInit[63] = true;
        } else {
            $jacocoInit[64] = true;
            if (this.swipeLayout.getFooterView() == null) {
                $jacocoInit[65] = true;
            } else {
                $jacocoInit[66] = true;
                this.swipeLayout.setLoadingHeight(0);
                $jacocoInit[67] = true;
                this.swipeLayout.getFooterView().removeView(wXComponent.getHostView());
                $jacocoInit[68] = true;
                this.swipeLayout.finishPullLoad();
                $jacocoInit[69] = true;
            }
        }
        $jacocoInit[70] = true;
    }

    public void removeHeaderView(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        setRefreshEnable(false);
        if (this.swipeLayout == null) {
            $jacocoInit[71] = true;
        } else {
            $jacocoInit[72] = true;
            if (this.swipeLayout.getHeaderView() == null) {
                $jacocoInit[73] = true;
            } else {
                $jacocoInit[74] = true;
                this.swipeLayout.setRefreshHeight(0);
                $jacocoInit[75] = true;
                this.swipeLayout.getHeaderView().removeView(wXComponent.getHostView());
                $jacocoInit[76] = true;
                this.swipeLayout.finishPullRefresh();
                $jacocoInit[77] = true;
            }
        }
        $jacocoInit[78] = true;
    }

    public void setRefreshEnable(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.swipeLayout == null) {
            $jacocoInit[79] = true;
        } else {
            $jacocoInit[80] = true;
            this.swipeLayout.setPullRefreshEnable(z);
            $jacocoInit[81] = true;
        }
        $jacocoInit[82] = true;
    }

    public void setLoadmoreEnable(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.swipeLayout == null) {
            $jacocoInit[83] = true;
        } else {
            $jacocoInit[84] = true;
            this.swipeLayout.setPullLoadEnable(z);
            $jacocoInit[85] = true;
        }
        $jacocoInit[86] = true;
    }

    public void removeView(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        if (view instanceof WXLoadingLayout) {
            $jacocoInit[87] = true;
            finishPullLoad();
            $jacocoInit[88] = true;
            setLoadmoreEnable(false);
            if (this.swipeLayout == null) {
                $jacocoInit[89] = true;
            } else {
                $jacocoInit[90] = true;
                this.swipeLayout.removeView(this.swipeLayout.getFooterView());
                $jacocoInit[91] = true;
            }
        } else if (view instanceof WXRefreshLayout) {
            $jacocoInit[92] = true;
            finishPullRefresh();
            $jacocoInit[93] = true;
            setRefreshEnable(false);
            if (this.swipeLayout == null) {
                $jacocoInit[94] = true;
            } else {
                $jacocoInit[95] = true;
                this.swipeLayout.removeView(this.swipeLayout.getHeaderView());
                $jacocoInit[96] = true;
            }
        } else {
            super.removeView(view);
            $jacocoInit[97] = true;
        }
        $jacocoInit[98] = true;
    }

    public WXSwipeLayout getSwipeLayout() {
        boolean[] $jacocoInit = $jacocoInit();
        WXSwipeLayout wXSwipeLayout = this.swipeLayout;
        $jacocoInit[99] = true;
        return wXSwipeLayout;
    }
}
