package com.xiaomi.smarthome.library.common.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.xiaomi.smarthome.library.common.widget.DragSortListView;

public class XMBaseDragSortListView extends DragSortListView {
    public XMBaseDragSortListView(Context context) {
        super(context);
    }

    public XMBaseDragSortListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public XMBaseDragSortListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (ArrayIndexOutOfBoundsException unused) {
            return false;
        } catch (IndexOutOfBoundsException unused2) {
            return false;
        }
    }
}
