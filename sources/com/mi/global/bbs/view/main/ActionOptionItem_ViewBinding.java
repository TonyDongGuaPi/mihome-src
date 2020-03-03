package com.mi.global.bbs.view.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.Editor.FontTextView;

public class ActionOptionItem_ViewBinding implements Unbinder {
    private ActionOptionItem target;

    @UiThread
    public ActionOptionItem_ViewBinding(ActionOptionItem actionOptionItem) {
        this(actionOptionItem, actionOptionItem);
    }

    @UiThread
    public ActionOptionItem_ViewBinding(ActionOptionItem actionOptionItem, View view) {
        this.target = actionOptionItem;
        actionOptionItem.mItemHint = (FontTextView) Utils.findRequiredViewAsType(view, R.id.item_hint, "field 'mItemHint'", FontTextView.class);
        actionOptionItem.mItemAction = (ImageView) Utils.findRequiredViewAsType(view, R.id.item_action, "field 'mItemAction'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        ActionOptionItem actionOptionItem = this.target;
        if (actionOptionItem != null) {
            this.target = null;
            actionOptionItem.mItemHint = null;
            actionOptionItem.mItemAction = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
