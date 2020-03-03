package com.taobao.weex.ui.component.helper;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.lang.ref.WeakReference;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class SoftKeyboardDetector {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final int KEYBOARD_VISIBLE_THRESHOLD_DIP = 100;

    public interface OnKeyboardEventListener {
        void onKeyboardEvent(boolean z);
    }

    public interface Unregister {
        void execute();
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(33169562156236246L, "com/taobao/weex/ui/component/helper/SoftKeyboardDetector", 27);
        $jacocoData = a2;
        return a2;
    }

    public SoftKeyboardDetector() {
        $jacocoInit()[0] = true;
    }

    public static Unregister registerKeyboardEventListener(Activity activity, final OnKeyboardEventListener onKeyboardEventListener) {
        boolean[] $jacocoInit = $jacocoInit();
        if (activity == null) {
            $jacocoInit[1] = true;
        } else if (onKeyboardEventListener == null) {
            $jacocoInit[2] = true;
        } else {
            if (activity.getWindow() == null) {
                $jacocoInit[4] = true;
            } else {
                $jacocoInit[5] = true;
                WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                if (attributes == null) {
                    $jacocoInit[6] = true;
                } else {
                    int i = attributes.softInputMode;
                    if (i == 48) {
                        $jacocoInit[7] = true;
                    } else if (i != 32) {
                        $jacocoInit[8] = true;
                    } else {
                        $jacocoInit[9] = true;
                    }
                    WXLogUtils.e("SoftKeyboard detector can't work with softInputMode is SOFT_INPUT_ADJUST_NOTHING or SOFT_INPUT_ADJUST_PAN");
                    $jacocoInit[10] = true;
                    return null;
                }
            }
            final View activityRoot = getActivityRoot(activity);
            if (activityRoot == null) {
                $jacocoInit[11] = true;
                WXLogUtils.e("Activity root is null!");
                $jacocoInit[12] = true;
                return null;
            }
            AnonymousClass1 r1 = new ViewTreeObserver.OnGlobalLayoutListener() {
                private static transient /* synthetic */ boolean[] $jacocoData;
                private final int threshold;
                private final Rect visibleFrame = new Rect();
                private boolean wasKeyboardOpened;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-3793955581615639468L, "com/taobao/weex/ui/component/helper/SoftKeyboardDetector$1", 9);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    $jacocoInit[0] = true;
                    $jacocoInit[1] = true;
                    this.threshold = WXViewUtils.dip2px(100.0f);
                    this.wasKeyboardOpened = false;
                    $jacocoInit[2] = true;
                }

                public void onGlobalLayout() {
                    boolean z;
                    boolean[] $jacocoInit = $jacocoInit();
                    activityRoot.getWindowVisibleDisplayFrame(this.visibleFrame);
                    $jacocoInit[3] = true;
                    if (activityRoot.getRootView().getHeight() - this.visibleFrame.height() > this.threshold) {
                        $jacocoInit[4] = true;
                        z = true;
                    } else {
                        z = false;
                        $jacocoInit[5] = true;
                    }
                    if (z == this.wasKeyboardOpened) {
                        $jacocoInit[6] = true;
                        return;
                    }
                    this.wasKeyboardOpened = z;
                    $jacocoInit[7] = true;
                    onKeyboardEventListener.onKeyboardEvent(z);
                    $jacocoInit[8] = true;
                }
            };
            $jacocoInit[13] = true;
            activityRoot.getViewTreeObserver().addOnGlobalLayoutListener(r1);
            $jacocoInit[14] = true;
            DefaultUnRegister defaultUnRegister = new DefaultUnRegister(activity, r1);
            $jacocoInit[15] = true;
            return defaultUnRegister;
        }
        WXLogUtils.e("Activity or listener is null!");
        $jacocoInit[3] = true;
        return null;
    }

    public static boolean isKeyboardVisible(Activity activity) {
        boolean[] $jacocoInit = $jacocoInit();
        Rect rect = new Rect();
        $jacocoInit[16] = true;
        View activityRoot = getActivityRoot(activity);
        boolean z = false;
        if (activityRoot != null) {
            $jacocoInit[17] = true;
            activityRoot.getWindowVisibleDisplayFrame(rect);
            $jacocoInit[18] = true;
            int height = activityRoot.getRootView().getHeight() - rect.height();
            $jacocoInit[19] = true;
            if (height > WXViewUtils.dip2px(100.0f)) {
                $jacocoInit[20] = true;
                z = true;
            } else {
                $jacocoInit[21] = true;
            }
            $jacocoInit[22] = true;
            return z;
        }
        $jacocoInit[23] = true;
        return false;
    }

    @Nullable
    public static View getActivityRoot(Activity activity) {
        boolean[] $jacocoInit = $jacocoInit();
        if (activity != null) {
            $jacocoInit[24] = true;
            View findViewById = activity.findViewById(16908290);
            $jacocoInit[25] = true;
            return findViewById;
        }
        $jacocoInit[26] = true;
        return null;
    }

    public static final class DefaultUnRegister implements Unregister {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private WeakReference<Activity> activityRef;
        private WeakReference<ViewTreeObserver.OnGlobalLayoutListener> listenerRef;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-8643545473973176911L, "com/taobao/weex/ui/component/helper/SoftKeyboardDetector$DefaultUnRegister", 13);
            $jacocoData = a2;
            return a2;
        }

        public DefaultUnRegister(Activity activity, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
            this.activityRef = new WeakReference<>(activity);
            $jacocoInit[1] = true;
            this.listenerRef = new WeakReference<>(onGlobalLayoutListener);
            $jacocoInit[2] = true;
        }

        public void execute() {
            boolean[] $jacocoInit = $jacocoInit();
            Activity activity = (Activity) this.activityRef.get();
            $jacocoInit[3] = true;
            ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = (ViewTreeObserver.OnGlobalLayoutListener) this.listenerRef.get();
            if (activity == null) {
                $jacocoInit[4] = true;
            } else if (onGlobalLayoutListener == null) {
                $jacocoInit[5] = true;
            } else {
                $jacocoInit[6] = true;
                View activityRoot = SoftKeyboardDetector.getActivityRoot(activity);
                if (activityRoot == null) {
                    $jacocoInit[7] = true;
                } else if (Build.VERSION.SDK_INT >= 16) {
                    $jacocoInit[8] = true;
                    activityRoot.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
                    $jacocoInit[9] = true;
                } else {
                    activityRoot.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
                    $jacocoInit[10] = true;
                }
            }
            this.activityRef.clear();
            $jacocoInit[11] = true;
            this.listenerRef.clear();
            $jacocoInit[12] = true;
        }
    }
}
