package com.taobao.weex.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.method.MovementMethod;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.EditText;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXEditText extends EditText implements WXGestureObservable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private boolean mAllowCopyPaste = true;
    private boolean mAllowDisableMovement = true;
    private int mLines = 1;
    private WXGesture wxGesture;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-2552182447467067667L, "com/taobao/weex/ui/view/WXEditText", 44);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXEditText(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        if (Build.VERSION.SDK_INT >= 16) {
            $jacocoInit[0] = true;
            setBackground((Drawable) null);
            $jacocoInit[1] = true;
        } else {
            setBackgroundDrawable((Drawable) null);
            $jacocoInit[2] = true;
        }
        $jacocoInit[3] = true;
    }

    public void registerGestureListener(WXGesture wXGesture) {
        boolean[] $jacocoInit = $jacocoInit();
        this.wxGesture = wXGesture;
        $jacocoInit[4] = true;
    }

    public WXGesture getGestureListener() {
        boolean[] $jacocoInit = $jacocoInit();
        WXGesture wXGesture = this.wxGesture;
        $jacocoInit[5] = true;
        return wXGesture;
    }

    public void setLines(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        super.setLines(i);
        this.mLines = i;
        $jacocoInit[6] = true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (this.wxGesture == null) {
            $jacocoInit[7] = true;
        } else {
            $jacocoInit[8] = true;
            onTouchEvent |= this.wxGesture.onTouch(this, motionEvent);
            $jacocoInit[9] = true;
        }
        ViewParent parent = getParent();
        if (parent == null) {
            $jacocoInit[10] = true;
        } else {
            $jacocoInit[11] = true;
            int action = motionEvent.getAction() & 255;
            if (action != 3) {
                switch (action) {
                    case 0:
                        if (this.mLines < getLineCount()) {
                            $jacocoInit[14] = true;
                            parent.requestDisallowInterceptTouchEvent(true);
                            $jacocoInit[15] = true;
                            break;
                        } else {
                            $jacocoInit[13] = true;
                            break;
                        }
                    case 1:
                        break;
                    default:
                        $jacocoInit[12] = true;
                        break;
                }
            }
            parent.requestDisallowInterceptTouchEvent(false);
            $jacocoInit[16] = true;
        }
        $jacocoInit[17] = true;
        return onTouchEvent;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onSizeChanged(i, i2, i3, i4);
        $jacocoInit[18] = true;
        if (getLayout() == null) {
            $jacocoInit[19] = true;
        } else {
            $jacocoInit[20] = true;
            int height = getLayout().getHeight();
            if (!this.mAllowDisableMovement) {
                $jacocoInit[21] = true;
            } else if (i2 >= height) {
                $jacocoInit[22] = true;
            } else {
                $jacocoInit[23] = true;
                setMovementMethod((MovementMethod) null);
                $jacocoInit[24] = true;
            }
            setMovementMethod(getDefaultMovementMethod());
            $jacocoInit[25] = true;
        }
        $jacocoInit[26] = true;
    }

    public void setAllowDisableMovement(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mAllowDisableMovement = z;
        $jacocoInit[27] = true;
    }

    public void setAllowCopyPaste(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mAllowCopyPaste = z;
        if (z) {
            $jacocoInit[28] = true;
            setLongClickable(true);
            $jacocoInit[29] = true;
            setCustomSelectionActionModeCallback((ActionMode.Callback) null);
            if (Build.VERSION.SDK_INT < 23) {
                $jacocoInit[30] = true;
            } else {
                $jacocoInit[31] = true;
                setCustomInsertionActionModeCallback((ActionMode.Callback) null);
                $jacocoInit[32] = true;
            }
        } else {
            setLongClickable(false);
            $jacocoInit[33] = true;
            AnonymousClass1 r5 = new ActionMode.Callback(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXEditText this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-8950403879398329839L, "com/taobao/weex/ui/view/WXEditText$1", 5);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r3;
                    $jacocoInit[0] = true;
                }

                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    $jacocoInit()[1] = true;
                    return false;
                }

                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    $jacocoInit()[2] = true;
                    return false;
                }

                public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                    $jacocoInit()[3] = true;
                    return false;
                }

                public void onDestroyActionMode(ActionMode actionMode) {
                    $jacocoInit()[4] = true;
                }
            };
            if (Build.VERSION.SDK_INT < 23) {
                $jacocoInit[34] = true;
            } else {
                $jacocoInit[35] = true;
                setCustomInsertionActionModeCallback(r5);
                $jacocoInit[36] = true;
            }
            setCustomSelectionActionModeCallback(r5);
            $jacocoInit[37] = true;
        }
        $jacocoInit[38] = true;
    }

    public boolean onTextContextMenuItem(int i) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.mAllowCopyPaste) {
            $jacocoInit[39] = true;
        } else if (super.onTextContextMenuItem(i)) {
            $jacocoInit[40] = true;
        } else {
            z = false;
            $jacocoInit[42] = true;
            $jacocoInit[43] = true;
            return z;
        }
        $jacocoInit[41] = true;
        z = true;
        $jacocoInit[43] = true;
        return z;
    }
}
