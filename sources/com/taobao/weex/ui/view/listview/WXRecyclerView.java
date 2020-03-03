package com.taobao.weex.ui.view.listview;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import com.taobao.weex.ui.view.listview.ExtendedLinearLayoutManager;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXRecyclerView extends RecyclerView implements WXGestureObservable {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final int TYPE_GRID_LAYOUT = 2;
    public static final int TYPE_LINEAR_LAYOUT = 1;
    public static final int TYPE_STAGGERED_GRID_LAYOUT = 3;
    private boolean hasTouch = false;
    private WXGesture mGesture;
    private boolean scrollable = true;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-5911662272694842159L, "com/taobao/weex/ui/view/listview/WXRecyclerView", 33);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXRecyclerView(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public boolean isScrollable() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.scrollable;
        $jacocoInit[1] = true;
        return z;
    }

    public void setScrollable(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.scrollable = z;
        $jacocoInit[2] = true;
    }

    public void initView(Context context, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        initView(context, i, 1, 32.0f, i2);
        $jacocoInit[3] = true;
    }

    @TargetApi(16)
    public void initView(Context context, int i, int i2, float f, int i3) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i == 2) {
            $jacocoInit[4] = true;
            setLayoutManager(new GridLayoutManager(context, i2, i3, false));
            $jacocoInit[5] = true;
        } else if (i == 3) {
            $jacocoInit[6] = true;
            setLayoutManager(new ExtendedStaggeredGridLayoutManager(i2, i3));
            $jacocoInit[7] = true;
        } else if (i != 1) {
            $jacocoInit[8] = true;
        } else {
            $jacocoInit[9] = true;
            setLayoutManager(new ExtendedLinearLayoutManager(context, i3, false));
            $jacocoInit[10] = true;
        }
        $jacocoInit[11] = true;
    }

    public void registerGestureListener(@Nullable WXGesture wXGesture) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mGesture = wXGesture;
        $jacocoInit[12] = true;
    }

    public WXGesture getGestureListener() {
        boolean[] $jacocoInit = $jacocoInit();
        WXGesture wXGesture = this.mGesture;
        $jacocoInit[13] = true;
        return wXGesture;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.scrollable) {
            $jacocoInit[14] = true;
            return true;
        }
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        $jacocoInit[15] = true;
        return onTouchEvent;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        this.hasTouch = true;
        $jacocoInit[16] = true;
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (this.mGesture == null) {
            $jacocoInit[17] = true;
        } else {
            $jacocoInit[18] = true;
            dispatchTouchEvent |= this.mGesture.onTouch(this, motionEvent);
            $jacocoInit[19] = true;
        }
        $jacocoInit[20] = true;
        return dispatchTouchEvent;
    }

    public void scrollTo(boolean z, int i, final int i2, final int i3) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!z) {
            $jacocoInit[21] = true;
            RecyclerView.LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                $jacocoInit[22] = true;
                ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(i, -i2);
                $jacocoInit[23] = true;
            } else if (!(layoutManager instanceof StaggeredGridLayoutManager)) {
                $jacocoInit[24] = true;
            } else {
                $jacocoInit[25] = true;
                ((StaggeredGridLayoutManager) layoutManager).scrollToPositionWithOffset(i, -i2);
                $jacocoInit[26] = true;
            }
            $jacocoInit[27] = true;
        } else {
            smoothScrollToPosition(i);
            if (i2 == 0) {
                $jacocoInit[28] = true;
            } else {
                $jacocoInit[29] = true;
                setOnSmoothScrollEndListener(new ExtendedLinearLayoutManager.OnSmoothScrollEndListener(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ WXRecyclerView this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(-8698444181618494174L, "com/taobao/weex/ui/view/listview/WXRecyclerView$1", 2);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r2;
                        $jacocoInit[0] = true;
                    }

                    public void onStop() {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0.post(WXThread.secure((Runnable) new Runnable(this) {
                            private static transient /* synthetic */ boolean[] $jacocoData;
                            final /* synthetic */ AnonymousClass1 this$1;

                            private static /* synthetic */ boolean[] $jacocoInit() {
                                boolean[] zArr = $jacocoData;
                                if (zArr != null) {
                                    return zArr;
                                }
                                boolean[] a2 = Offline.a(-8143472818621353247L, "com/taobao/weex/ui/view/listview/WXRecyclerView$1$1", 5);
                                $jacocoData = a2;
                                return a2;
                            }

                            {
                                boolean[] $jacocoInit = $jacocoInit();
                                this.this$1 = r3;
                                $jacocoInit[0] = true;
                            }

                            public void run() {
                                boolean[] $jacocoInit = $jacocoInit();
                                if (i3 == 1) {
                                    $jacocoInit[1] = true;
                                    this.this$1.this$0.smoothScrollBy(0, i2);
                                    $jacocoInit[2] = true;
                                } else {
                                    this.this$1.this$0.smoothScrollBy(i2, 0);
                                    $jacocoInit[3] = true;
                                }
                                $jacocoInit[4] = true;
                            }
                        }));
                        $jacocoInit[1] = true;
                    }
                });
                $jacocoInit[30] = true;
            }
        }
        $jacocoInit[31] = true;
    }

    public void setOnSmoothScrollEndListener(final ExtendedLinearLayoutManager.OnSmoothScrollEndListener onSmoothScrollEndListener) {
        boolean[] $jacocoInit = $jacocoInit();
        addOnScrollListener(new RecyclerView.OnScrollListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXRecyclerView this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(5274216659149515881L, "com/taobao/weex/ui/view/listview/WXRecyclerView$2", 7);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                boolean[] $jacocoInit = $jacocoInit();
                if (i != 0) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    recyclerView.removeOnScrollListener(this);
                    if (onSmoothScrollEndListener == null) {
                        $jacocoInit[3] = true;
                    } else {
                        $jacocoInit[4] = true;
                        onSmoothScrollEndListener.onStop();
                        $jacocoInit[5] = true;
                    }
                }
                $jacocoInit[6] = true;
            }
        });
        $jacocoInit[32] = true;
    }
}
