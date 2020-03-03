package com.taobao.weex.ui.view.refresh.core;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.ui.view.refresh.circlebar.CircleProgressBar;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXRefreshView extends FrameLayout {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private CircleProgressBar circleProgressBar;
    private LinearLayout linearLayout;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-5986357301065951918L, "com/taobao/weex/ui/view/refresh/core/WXRefreshView", 43);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ CircleProgressBar access$002(WXRefreshView wXRefreshView, CircleProgressBar circleProgressBar2) {
        boolean[] $jacocoInit = $jacocoInit();
        wXRefreshView.circleProgressBar = circleProgressBar2;
        $jacocoInit[41] = true;
        return circleProgressBar2;
    }

    static /* synthetic */ LinearLayout access$100(WXRefreshView wXRefreshView) {
        boolean[] $jacocoInit = $jacocoInit();
        LinearLayout linearLayout2 = wXRefreshView.linearLayout;
        $jacocoInit[42] = true;
        return linearLayout2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXRefreshView(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        setupViews();
        $jacocoInit[1] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXRefreshView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[2] = true;
        setupViews();
        $jacocoInit[3] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXRefreshView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[4] = true;
        setupViews();
        $jacocoInit[5] = true;
    }

    private void setupViews() {
        boolean[] $jacocoInit = $jacocoInit();
        this.linearLayout = new LinearLayout(getContext());
        $jacocoInit[6] = true;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        $jacocoInit[7] = true;
        this.linearLayout.setOrientation(1);
        $jacocoInit[8] = true;
        this.linearLayout.setGravity(17);
        $jacocoInit[9] = true;
        addView(this.linearLayout, layoutParams);
        $jacocoInit[10] = true;
    }

    public void setContentGravity(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.linearLayout == null) {
            $jacocoInit[11] = true;
        } else {
            $jacocoInit[12] = true;
            this.linearLayout.setGravity(i);
            $jacocoInit[13] = true;
        }
        $jacocoInit[14] = true;
    }

    public void setRefreshView(final View view) {
        boolean[] $jacocoInit = $jacocoInit();
        if (view == null) {
            $jacocoInit[15] = true;
            return;
        }
        post(WXThread.secure((Runnable) new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXRefreshView this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-2224422763957454921L, "com/taobao/weex/ui/view/refresh/core/WXRefreshView$1", 12);
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
                View view = view;
                $jacocoInit[1] = true;
                if (view.getParent() == null) {
                    $jacocoInit[2] = true;
                } else {
                    $jacocoInit[3] = true;
                    ((ViewGroup) view.getParent()).removeView(view);
                    $jacocoInit[4] = true;
                }
                int i = 0;
                $jacocoInit[5] = true;
                while (true) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    if (i < viewGroup.getChildCount()) {
                        $jacocoInit[6] = true;
                        View childAt = viewGroup.getChildAt(i);
                        if (!(childAt instanceof CircleProgressBar)) {
                            $jacocoInit[7] = true;
                        } else {
                            $jacocoInit[8] = true;
                            WXRefreshView.access$002(this.this$0, (CircleProgressBar) childAt);
                            $jacocoInit[9] = true;
                        }
                        i++;
                        $jacocoInit[10] = true;
                    } else {
                        WXRefreshView.access$100(this.this$0).addView(view);
                        $jacocoInit[11] = true;
                        return;
                    }
                }
            }
        }));
        $jacocoInit[16] = true;
    }

    public void setProgressBgColor(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.circleProgressBar == null) {
            $jacocoInit[17] = true;
        } else {
            $jacocoInit[18] = true;
            this.circleProgressBar.setBackgroundColor(i);
            $jacocoInit[19] = true;
        }
        $jacocoInit[20] = true;
    }

    public void setProgressColor(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.circleProgressBar == null) {
            $jacocoInit[21] = true;
        } else {
            $jacocoInit[22] = true;
            this.circleProgressBar.setColorSchemeColors(i);
            $jacocoInit[23] = true;
        }
        $jacocoInit[24] = true;
    }

    /* access modifiers changed from: protected */
    public void startAnimation() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.circleProgressBar == null) {
            $jacocoInit[25] = true;
        } else {
            $jacocoInit[26] = true;
            this.circleProgressBar.start();
            $jacocoInit[27] = true;
        }
        $jacocoInit[28] = true;
    }

    public void setStartEndTrim(float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.circleProgressBar == null) {
            $jacocoInit[29] = true;
        } else {
            $jacocoInit[30] = true;
            this.circleProgressBar.setStartEndTrim(f, f2);
            $jacocoInit[31] = true;
        }
        $jacocoInit[32] = true;
    }

    /* access modifiers changed from: protected */
    public void stopAnimation() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.circleProgressBar == null) {
            $jacocoInit[33] = true;
        } else {
            $jacocoInit[34] = true;
            this.circleProgressBar.stop();
            $jacocoInit[35] = true;
        }
        $jacocoInit[36] = true;
    }

    public void setProgressRotation(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.circleProgressBar == null) {
            $jacocoInit[37] = true;
        } else {
            $jacocoInit[38] = true;
            this.circleProgressBar.setProgressRotation(f);
            $jacocoInit[39] = true;
        }
        $jacocoInit[40] = true;
    }
}
