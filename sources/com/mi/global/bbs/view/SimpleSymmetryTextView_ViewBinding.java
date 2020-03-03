package com.mi.global.bbs.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class SimpleSymmetryTextView_ViewBinding implements Unbinder {
    private SimpleSymmetryTextView target;

    @UiThread
    public SimpleSymmetryTextView_ViewBinding(SimpleSymmetryTextView simpleSymmetryTextView) {
        this(simpleSymmetryTextView, simpleSymmetryTextView);
    }

    @UiThread
    public SimpleSymmetryTextView_ViewBinding(SimpleSymmetryTextView simpleSymmetryTextView, View view) {
        this.target = simpleSymmetryTextView;
        simpleSymmetryTextView.itemLeftText = (TextView) Utils.findRequiredViewAsType(view, R.id.item_left_text, "field 'itemLeftText'", TextView.class);
        simpleSymmetryTextView.itemRightText = (TextView) Utils.findRequiredViewAsType(view, R.id.item_right_text, "field 'itemRightText'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        SimpleSymmetryTextView simpleSymmetryTextView = this.target;
        if (simpleSymmetryTextView != null) {
            this.target = null;
            simpleSymmetryTextView.itemLeftText = null;
            simpleSymmetryTextView.itemRightText = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
